package me.zw.hellospring.repository;

import me.zw.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong(0);

    LongUnaryOperator operator = (n) -> n + 1;

    @Override
    public Member save(Member member) {
        member.setId(sequence.updateAndGet(operator));
        store.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();

    }
}
