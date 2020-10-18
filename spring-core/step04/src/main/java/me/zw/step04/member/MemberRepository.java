package me.zw.step04.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
