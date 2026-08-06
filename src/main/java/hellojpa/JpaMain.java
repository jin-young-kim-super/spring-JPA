package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class JpaMain {

    public static void main(String[] args) {
      EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        try {

            Member member = new Member();
            member.setId("ID_A"); // 기본키 value를 개발자가 직접 지정
            member.setUsername("memberA");
            entityManager.persist(member);

            transaction.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            transaction.rollback(); // 트랜잭션 종료
        } finally {
            // STEP 4. DB 종료
            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
