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
        transaction.begin();

        try {
            // UPDATE 쿼리는 따로 persist하지 않아도 된다.
            Member member1 = entityManager.find(Member.class, 1L);
            member1.setName("changed name");
            Member member2 = entityManager.find(Member.class, 2L);
            entityManager.remove(member2);
            transaction.commit();
        } catch (Exception e) {
            // CRUD 수행 중 문제가 발생하면 트랜잭션을 롤백한다.
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
