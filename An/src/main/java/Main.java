//import DataAccess.AccountDAO;
//import DataAccess.StudentDAO;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class Main {
//    public static void main(String args[]){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//
//        AccountDAO accountDAO = new AccountDAO(em);
//
//        System.out.println(accountDAO.getBalance("001"));
//
//        //insert
////        transaction.begin();
////        Student student = new Student();
////        student.setName("Hoang An");
////        em.persist(student);
////        transaction.commit();
//
//        //select
////        transaction.begin();
////        Student student = em.find(Student.class, 1);
////        transaction.commit();
//
//        //update
////        Student student = em.find(Student.class, 1);
////
////        transaction.begin();
////        student.setName("Vo Hoang An");
////        transaction.commit();
//
//        //delete
////        Student student = em.find(Student.class, 1);
////
////        transaction.begin();
////        em.remove(student);
////        transaction.commit();
//
//    }
//}
