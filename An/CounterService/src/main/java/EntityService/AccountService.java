package EntityService;

import DataAccess.AccountDAO;
import Entity.Account;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountService {
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static AccountDAO accountDAO = new AccountDAO();

    public long setBalance(String userId, long balance) {
        Account account = accountDAO.getAccountFromCacheMemory(userId);

        //create new thread to save or update record into db
        executor.execute(()->{
            accountDAO.saveOrUpdate(account, userId, balance);
        });

        return balance;
    }

    public long getBalance(String userId, long balance) {
        Account account = accountDAO.getAccountFromCacheMemory(userId);

        executor.execute(()->{
            accountDAO.saveOrUpdate(account, userId, balance);
        });

        return account == null ? balance : account.getBalance();
    }

    public long increaseBalance(String userId, long amount) {
        Account account = accountDAO.getAccountFromCacheMemory(userId);

        if (account == null){
            System.out.println("---------CANT'N CREATE NEW ACCOUNT FROM INCREASE METHOD---------");

        }

        long balance = account == null ? amount : account.getBalance() + amount;

        executor.execute(()->{
            accountDAO.saveOrUpdate(account, userId, balance);
        });

        return balance;
    }

    public long decreaseBalance(String userId, long amount) {
        Account account = accountDAO.getAccountFromCacheMemory(userId);

        if (account == null){
            System.out.println("---------CANT'N CREATE NEW ACCOUNT FROM DECREASE METHOD---------");

        }

        long balance = account == null ? amount : account.getBalance() - amount;

        executor.execute(()->{
            accountDAO.saveOrUpdate(account, userId, balance);
        });

        return balance;
    }
}
