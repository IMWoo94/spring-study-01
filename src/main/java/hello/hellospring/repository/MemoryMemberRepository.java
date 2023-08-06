package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository{

    //private static Map<Long, Member> store = new HashMap<>();
    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
//    private static long sequence = 0L;
    private static AtomicLong sequence = new AtomicLong(0L);

    public void clearStore() {
        store.clear();
    }
    @Override
    public Member save(Member member) {
        member.setId(sequence.getAndIncrement());
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
