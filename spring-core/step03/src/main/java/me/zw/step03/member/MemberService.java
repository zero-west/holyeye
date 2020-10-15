package me.zw.step03.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
