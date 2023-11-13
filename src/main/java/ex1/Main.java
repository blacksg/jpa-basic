package ex1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ex1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // JPQL: 특정 데이터베이스에 종속적이지 않고 엔티티 객체를 대상으로 하는 쿼리 언어
            List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class)
                .setFirstResult(2)
                .setMaxResults(5)
                .getResultList();
            for (Member member : resultList) {
                System.out.println("member.id=" + member.getId());
            }
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
