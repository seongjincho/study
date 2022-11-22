package hello.hellospring;

import javax.persistence.*;

// orm object relation mapping(객체를 관계형 데이터베이스 테이블을 매핑한다)
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // Id는 pk
    private Long id;
    //@Column(name = "username")  // 컬럼명이  username라면 어노테이션으로 지정 가능
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
