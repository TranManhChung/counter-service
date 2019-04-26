package DataAccess;

import Entity.Account;
import Entity.SessionUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountDAO {
    private static SessionFactory factory = null;
    private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final static Lock writeLock = readWriteLock.writeLock();
    private HashMap<String, Long> cached = new HashMap<>();

    static {
        factory = SessionUtil.getSessionFactory();
    }

    private Account getAccount(String userId){
        Session sessionFind = factory.openSession();
        Account account = sessionFind.find(Account.class, userId);
        sessionFind.close();

        return account;
    }

    public Account getAccountFromCacheMemory(String userId){
        long balance = -1;
        try{
            balance = cached.get(userId);
        }catch (NullPointerException e){
            //System.out.println(e.getMessage());
        }

        if (balance == -1){
            return null;
        }

        return new Account(userId, balance);
    }

    public void saveOrUpdate(Account account, String userId, long balance){
        Account existAccount = null;
        //check lai xem da luu trong db chua, vi nhieu thread co the gui toi de tao 1 account cung 1 id
        synchronized (this){
            existAccount = getAccount(userId);
        }

        if (existAccount != null){
            return;
        }
        //open session to begin transaction db
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        if (account == null){
            //if it null, create new record
            account = new Account(userId, balance);

            //insert to cache
            cached.put(userId, balance);

            System.out.println("---------NEW USER ID: " + account.getUserId());

            try {
                writeLock.lock();
                session.save(account);
                //System.out.println("insert is successfully");
            } finally {
                writeLock.unlock();
            }
        }else if (balance > 0 && balance != account.getBalance()){
            System.out.println("--------UPDATE USER: " + account.getUserId());
            account.setBalance(balance);

            //update to cache
            cached.put(userId, balance);

            try {
                writeLock.lock();
                session.update(account);
            } finally {
                writeLock.unlock();
            }
        }

        trans.commit();
        session.clear();//clear all entity cached in session
        session.close();
    }
}
