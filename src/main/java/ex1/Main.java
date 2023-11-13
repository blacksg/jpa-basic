package ex1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ex1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // 새 엔티티 저장
        transaction.begin();
        Member member = new Member();
        member.setId(2L);
        member.setName("second member");
        entityManager.persist(member);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
