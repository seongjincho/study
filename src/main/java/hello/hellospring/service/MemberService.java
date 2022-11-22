package hello.hellospring.service;

import hello.hellospring.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // jpa를 사용할때 주의점 트랜잭션이 항상 있어야함
public class MemberService {
// 테스트 케이스 쉽게 만들기 cmd + shift + t

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {  //외부에서 넣어주도록 변경 DI
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복회원X
        //Optional<Member> result = memberRepository.findByName(member.getName()); // cmd + opt + v 앞에 = 만들어줌
        // ifPresent() Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
        //result.orElseGet()
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validateDuplicateMember(member);
        // ctrl + t 로 extract method로 특정 로직 따로 메소드로 뺄수있다 

        memberRepository.save(member); // 중복회원 검증
        return member.getId();     /// opt + shift + 방향키로 줄 옮기기
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
