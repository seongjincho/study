package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class MemberController {
    private final MemberService memberService;

    // memberservice와 MemoryMemberRepository에 @service ,@repository 어노테이션 등록
    //스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
