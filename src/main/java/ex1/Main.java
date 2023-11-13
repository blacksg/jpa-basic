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

        // 모든 CRUD는 트랜잭션 내에서 수행해야 한다.
        transaction.begin();
        Member member = new Member();
        member.setId(1L);
        member.setName("first member");
        entityManager.persist(member);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
