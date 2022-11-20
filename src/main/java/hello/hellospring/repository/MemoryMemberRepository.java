package hello.hellospring.repository;

import hello.hellospring.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();  // 실무에서는 동시성 문제를 고려해 ConcurrentHashMap룰 사용
    private static long sequence = 0L; // 실무에서는 동시성 문제를 고려해  AtomicLong를 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();  // roop를 돌면서 찾느다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() {  // 테스트 케이스 돌릴때 순서가 보장 되지 않기 때문에 값을 클리어
        store.clear();
    }

    // 작성한 코드를 테스트케이스 작성하여 정상적으로 작동하는지 검증
}
