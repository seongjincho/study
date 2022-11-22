package hello.hellospring.service;

import hello.hellospring.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 통합 테스트
// 2개의 어노테이션이 필요함 간편하게 처리가능
@SpringBootTest
@Transactional   // @Commit 은 데이터를 커밋함
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // 이 부분을 @Transactional 이 처리 해준다
//    @BeforeEach // 동작하기전에 넣어줌
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
//
//    @AfterEach  // 테스트 순서가 보장되지 않기때문에 값을 초기화 시키기 위해 메소드가 끝날때마다 실행
//    public void afterEach(){
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() {
        //given  주어진
        Member member = new Member();
        member.setName("spring100");
        //when 상황
        Long saveId = memberService.join(member);

        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);  // shift + cmd + 좌우 방향키로 라인 끝 라인처음 드래그
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123");
//        }

        // try catch 말고 다른 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //then
    }


}