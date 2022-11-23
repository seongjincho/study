package hello.hellospring.controller;

import hello.hellospring.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService memberService;
    // memberservice와 MemoryMemberRepository에 @service ,@repository 어노테이션 등록
    //스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService.getClass() = " + memberService.getClass());
        //class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$b2a0a49c
    }
    /*
    셍상자 주입 (생성자 주입 권장)
    private MemberService memberService;
    public MemberService(MemberRepository memberRepository) {  //외부에서 넣어주도록 변경 DI
        this.memberRepository = memberRepository;
    }
    필드주입
    @Autowired private MemberService memberService;

    setter 주입
    private MemberService memberService;
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

  */

    @GetMapping("/members/new") // url 작성후 엔터는 기본적으로 GetMapping  , 조회
    public String createForm() {
        return "members/createMemberForm";
    }

    //같은 url이여도 get, post에 따라 다르게 작성 가능
    @PostMapping("/members/new") // data를 form으로 던질때
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        //soutv
        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }




}
