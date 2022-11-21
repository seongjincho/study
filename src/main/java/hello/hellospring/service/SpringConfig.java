package hello.hellospring.service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //@Autowired DataSource dataSource;
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {  // MemberRepository : interface
        //return new MemoryMemberRepository(); // MemoryMemberRepository : 구현체
        // config를통한 방법은 컴포넌트 스캔 방법에 비해 구현체를 바꾸기가 쉽다    return new DbMemberRepository();
        //return new JdbcMemberRepository(dataSource); // 다형성을 활용 , 개방-폐쇄 원칙 , 순수jdbc
        return new JdbcTemplateMemberRepository(dataSource);// jdbc 템플릿
    }


}
