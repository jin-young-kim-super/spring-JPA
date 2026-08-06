package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id // 기본키(PK) 매핑 : @Id와 @GeneratedValue, 이 2개 중 하나만 사용
    //@GeneratedValue(strategy = GenerationType.AUTO) : 기본키 값을 개발자가 아닌, DB의 자동 생성기능에 의해 생성키며 기본키값 저장
    private String id;

    @Column(name="name",nullable = false)
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}