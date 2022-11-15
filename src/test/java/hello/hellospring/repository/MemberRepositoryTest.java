package hello.hellospring.repository;

import hello.hellospring.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    // 테스트를 먼저 만들고 구현하는건 테스트 주도 개발 tdd

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 테스트 순서가 보장되지 않기때문에 값을 초기화 시키기 위해 메소드가 끝날때마다 실행
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring"); // cmd + shift + enter 하면 자동으로 줄바꿈

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member)); // soutv
        //Assertions.assertEquals(member, null);  //틀리면 노란엑스 표시
        //Assertions.assertEquals(member, result); // 맞으면 초록체크
        //위에껀 junit꺼 import org.junit.jupiter.api.Assertions;

        //Assertions.assertThat(member).isEqualTo(result);// org.assertj.core.api.Assertions
        assertThat(member).isEqualTo(result);// Assertions  부분에서 option + enter 누르면 스태틱으로 짧게
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // 이름 바꿀때 한번에 바꿔지게 shift + fn + f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
