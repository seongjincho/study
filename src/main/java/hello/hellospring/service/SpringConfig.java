package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {  // MemberRepository : interface
        return new MemoryMemberRepository(); // MemoryMemberRepository : 구현체
        // config를통한 방법은 컴포넌트 스캔 방법에 비해 구현체를 바꾸기가 쉽다    return new DbMemberRepository();
    }


}
