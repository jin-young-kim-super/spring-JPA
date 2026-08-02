package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

/**
 * 스프링 없이 순수 자바와 JPA만을 가지고 개발(스프링으로 통합이 되면 코드가 깔끔해진)
 * -> 아래 클래스가 가장 로우 레벨의 코드이므로, 잘 익혀 두자! 추상화된 스프링 사용하면 앞으로 이 코드는
 * 못 볼 것이다.
 * STEP1-4까지가 큰 과정이다. 그리고 JPA 탄생 철학인, 컬렉션을 DB처럼 사용!!이라는 관점을 가지고 보자
 */


public class JpaMain {

    public static void main(String[] args) {
        // "hello" : peresistence.xml에 설정된 persistence-unit을 입력
        // -> STEP1. persistence.xml 정보를 읽어 들여서, 하이버네이트와 H2 데이터 베이스를 연결한다.
        // Persistence 클래스 : 자바 표준 클래스
        // -> 엔티티 매니저 팩토리는 애플리케이션 로딩 시, 딱 1개만 만들어 진다.
        // 그래고 각 요청마다 SQL문 실행 시, 그떄마다 엔티티 매니저 팩토리로부터 엔티티 매니저를 획득해서 커넥션 처럼 사용을 한다.
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("hello");

        // STEP 2. 필요할 때마다 EntityManger를 획득
        // -> 지금은 엔티티 매지져가 뭔지 이해 못해도  ㄱㅊ
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // STEP 3. 이 사이에서 DB 작업이 일어난다
        // -> JPA의 모든 변경은 반드시 트랜잭션 내에서 실행을 한다.
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        try {
            // INSERT문 실행
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("helloA");
//            entityManager.persist(member);

            // SELECT + DELETE문 실행
//            Member findMember = entityManager.find(Member.class, 1L);
//            entityManager.remove(findMember);

            // SELECT + UPDATE문 실행
//            Member findMember = entityManager.find(Member.class, 1L);
//            findMember.setName("HelloJPA"); // 자바 setter 코드만 해줬는데, DB에 반영이 된다.
            // JPA의 탄생 철학은 컬렉션을 마치 DB처럼 다루게 위함을 실현하기 위하여, JPA에서는 JPA를 통해서
            // 엔티티 매니저를 통해서 엔티티를 가지고 오게 되면, 그 엔티티는 JPA의 관리대상이 된다.
            // 그래서 자바 코드만으로 값을 변경해도 JPA가 커밋 시점에 값이 변경됐는지 체크하여
            // 자동으로 UPDATE 쿼리를 만들어 DB에 반영된다.

            // JPQL(= 객체 지향 SQL문, 테이블이 아닌 객체로 SQL문 작성) 실행
            // -> JPA의 탄생 철학을 떠올려라("애플리케이션의 객체 중심성을 떨어 뜨리지 않는다")
            // 그래서 JPQL에는 절대 테이블이 들어가는 것이 아니라 전부 객체가 들어가고 JPQL도 전부 객체 엔티티 취급을 해서, SQL문을 작성한다
            // 개발자는 DB 설계를 신경 쓰지 않아도 ,즉 복잡한 SQL문을 작성하지 않고 자바 코드로 마치 컬렉션에서 조회하듯 DB에서 원한느 데이터를 조회할 수가 있고,
            // 또한 페이징 처리 시, 복잡한 SQL문 없이 자바 코드로 처리할 수가 있다.
            List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // 페이징 처리
                    .setMaxResults(10) // 페이징 처리
                    .getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

            transaction.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            transaction.rollback(); // 트랜잭션 종료
        } finally {
            // STEP 4. DB 종료
            entityManager.close();
        }
        entityManagerFactory.close(); // 엔티티 매니저 팩토리는 딱 1개 있기 때문에, 애플리케이션 종료 시에만 닫혀야 한다.

    }
}
