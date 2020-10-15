package me.zw.step03.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
