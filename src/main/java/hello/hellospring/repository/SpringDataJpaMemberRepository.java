package hello.hellospring.repository;

import hello.hellospring.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {  //Member의 pk id 타입 Long


    // JPQL seledt m from Member m where m.name = ?
    // 메서드 이름 만으로 조회 기능 제공
    @Override
    Optional<Member> findByName(String name); // 스프링 데이터 jpa에서 제공하는 부분이 아님 키로 가능한것은 다있다
}
