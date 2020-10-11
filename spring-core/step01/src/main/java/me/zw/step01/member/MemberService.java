package me.zw.step01.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
