package me.zw.step02.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
