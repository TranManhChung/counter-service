package DataAccess;

import Entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountDAO {
    private static SessionFactory factory = null;
    private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final static Lock writeLock = readWriteLock.writeLock();
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
    HashMap<String, Long> cached = new HashMap<>();

    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Account.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        factory = configuration.buildSessionFactory(srvcReg);
    }

    public long setBalance(String userId, long balance) {
        Account account = getAccount(userId);

        //create new thread to save or update record into db
        executor.execute(()->{
            saveOrUpdate(account, userId, balance);
        });

        return balance;
    }

    public long getBalance(String userId, long balance) {
        Account account = getAccount(userId);

        executor.execute(()->{
            saveOrUpdate(account, userId, balance);
        });

        return account == null ? balance : account.getBalance();
    }

    public long increaseBalance(String userId, long amount) {
        Account account = getAccount(userId);

        long balance = account == null ? amount : account.getBalance() + amount;

        executor.execute(()->{
            saveOrUpdate(account, userId, balance);
        });

        return balance;
    }

    public long decreaseBalance(String userId, long amount) {
        Account account = getAccount(userId);

        long balance = account == null ? amount : account.getBalance() - amount;

        executor.execute(()->{
            saveOrUpdate(account, userId, balance);
        });

        return balance;
    }

    private Account getAccount(String userId){
        Session sessionFind = factory.openSession();
        Account account = sessionFind.find(Account.class, userId);
        sessionFind.close();

        return account;
    }

    private void saveOrUpdate(Account account, String userId, long balance){
        //open session to find record in db
        Account existAccount = null;
        //check lai xem da luu trong db chua
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
            synchronized (this){
                account = new Account(userId, balance);
            }


            System.out.println("---------NEW USER ID: " + account.getUserId());

            try {
                writeLock.lock();
                session.save(account);
                //System.out.println("insert is successfully");
            } finally {
                writeLock.unlock();
            }
        }else if (balance > 0 && balance != account.getBalance()){
            System.out.println("--------CHECK: " + account.getUserId());
            account.setBalance(balance);

            try {
                writeLock.lock();
                session.update(account);
                //System.out.println("update is successfully");
            } finally {
                writeLock.unlock();
            }
        }

        trans.commit();
        session.close();
    }
}
