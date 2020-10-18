package me.zw.step04.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
