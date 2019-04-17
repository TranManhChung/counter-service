package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("school");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager(){
        return entityManager;
    }

    public static void stop(){
        entityManagerFactory.close();
    }

    public static void closeEntityManager(){
        entityManager.close();
    }
}

/*
Khi mot doi tuong goi ham getEtittyManager thi no se tham chieu toi doi tuong etityManager cua class nay
Do do no khi dong entityManager nay lai thi tat ca cac doi tuong tham chieu no deu ko dung duoc
* */