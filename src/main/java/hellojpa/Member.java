package hellojpa;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // @Entity : JPA 로딩 시, "아!! JPA를 사용하는 아이구나"라고 인식
// @Table(name="Member")  @Table이 없으면, 클래스명을 테이블명이라고 디폴트로 인식한다
public class Member {

    @Id // primary key임을 알려줌
    private Long id;

    //@Column(name="username") : @Column 설정이 없으면 필드명을 컬럼명으로 자동 인식한다.
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
