package DataAccess;

import Entity.Account;
import Entity.SessionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import server.CounterServiceImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountDAO {
    private static SessionFactory factory;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();
    private final static Lock lock = new ReentrantLock();
    private static Logger logger = Logger.getLogger(CounterServiceImpl.class);
    //private static int connPool = 0;
    //ExecutorService executorService = Executors.newFixedThreadPool(100);//max of connection is 100, fix in hibernate.cfg.xml

    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Account.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        factory = configuration.buildSessionFactory(srvcReg);
    }

    public long getBalance(String userId, long balance) {
            Account account = null;
            Session session = factory.openSession();

            try {
                readLock.lock();
                //tim record nay duoi db
                account = session.find(Account.class, userId);
            } finally {
                readLock.unlock();
            }

            if (account == null) {//neu record chua co thi ta tien hanh ghi vao db
                account = new Account(userId, balance);

                try {
                    writeLock.lock();
                    //luu vao db
                    session.save(account);
                } finally {
                    writeLock.unlock();
                }
            }

            session.close();
            return account.getBalance();

    }

    public long setBalance(String userId, long balance) {
        Account account = null;
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        try{
            readLock.lock();
            account = session.find(Account.class, userId);
        }finally {
            readLock.unlock();
        }

        if (account == null) {
            account = new Account(userId, balance);

            try{
                writeLock.lock();
                session.save(account);
            }finally {
                writeLock.unlock();
            }
        }else if (balance > 0 && account.getBalance() != balance){//neu da co thi ta set lai balance
            account.setBalance(balance);

            try{
                writeLock.lock();
                session.update(account);
            } finally {
                writeLock.unlock();
            }
        }

        trans.commit();
        session.close();

        return account.getBalance();
    }

    public long increaseBalance(String userId, long amount) {
        Account account = null;
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        try{
            readLock.lock();
            account = session.find(Account.class, userId);
        }finally {
            readLock.unlock();
        }

        if (account == null) {
            account = new Account(userId, amount);

            try {
                writeLock.lock();
                session.save(account);
            } finally {
                writeLock.unlock();
            }
        }else if (amount > 0){
            account.setBalance(account.getBalance() + amount);

            try {
                writeLock.lock();
                session.update(account);
            }finally {
                writeLock.unlock();
            }
        }

        trans.commit();
        session.close();

        return account.getBalance();
     }

     public long decreaseBalance(String userId, long amount) {
         Account account = null;
         Session session = factory.openSession();
         Transaction trans = session.beginTransaction();

         try{
             readLock.lock();
             account = session.find(Account.class, userId);
         }finally {
             readLock.unlock();
         }

         if (account == null) {
             account = new Account(userId, amount);

             try {
                 writeLock.lock();
                 session.save(account);
             } finally {
                 writeLock.unlock();
             }
         }else if (amount > 0 && account.getBalance() > amount){
             account.setBalance(account.getBalance() - amount);

             try {
                 writeLock.lock();
                 session.update(account);
             }finally {
                 writeLock.unlock();
             }
         }

         trans.commit();
         session.close();

         return account.getBalance();
    }
}
