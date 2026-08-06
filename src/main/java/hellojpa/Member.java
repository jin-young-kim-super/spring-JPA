package hellojpa;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name") // 애플케이션에서는 "username", 칼럼에서는 "name"으로 쓰고 싶다는 의지!
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // 애플리케이션에서는 Enum 타입을 쓰고 싶지만, DB에는 Enum타입이 없다.그래서 이렇게 매핑을 해주면 JPA가 Enum 타입에 맞춰서 자동으로 컬럼을 변환해준다.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 애플리케이션에선는 자바가 제공하는 Date 타입을 사용하고 싶지만, DB에는 자바의 Date와는 다른 시간 포멧을 사용한다.
    private Date createdDate;         // @Temporal : 자바의 Date 타입을 컬럼과 매핑할 때 사용

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // @Lob : DB에서 예를 들어 문자열의 경우 varchar를 사용하는데, varchar에서 허용되는 문자열 길이 이상을 사용하고 싶을 떄 사용
         // DDL을 실행하면 h2 데이터 베이스의 경우, CLOB이라는 데이터 타입으로 매핑이 된다.(CLOB의 C는 Character)
    private String description;

    @Transient // @Transient : 테이블 칼럼과 매핑하지말어줘! 하는 필드에 사용! 만약 @Transient가 없으면 temp가 DDL 실행 시 반영이 되버린다
    private int temp;

    //Getter, Setter…
}