package com.bit.homework.service;

import com.bit.homework.domain.Member;
import com.bit.homework.repository.member.MemberRepository;
import com.bit.homework.repository.member.MemberRepositoryImpl;

public class MemberService {
    private static final MemberService instance = new MemberService();

    public static MemberService getInstance() {
        return instance;
    }

    private MemberService() {
    }

    private MemberRepository memberRepository = MemberRepositoryImpl.getInstance();

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void addBookmark(Integer memberId, Integer boardId) {
        memberRepository.addBookmark(memberId, boardId);
    }

    public void removeBookmark(Integer memberId, Integer boardId) {
        memberRepository.removeBookmark(memberId, boardId);
    }

    public void addBoardLikes(Integer memberId, Integer boardId) {
        memberRepository.addBoardLikes(memberId, boardId);
    }

    public void removeBoardLikes(Integer memberId, Integer boardId) {
        memberRepository.removeBoardLikes(memberId, boardId);
    }

    public void addBoardDislikes(Integer memberId, Integer boardId) {
        memberRepository.addBoardDislikes(memberId, boardId);
    }

    public void removeBoardDislikes(Integer memberId, Integer boardId) {
        memberRepository.removeBoardDislikes(memberId, boardId);
    }

    public boolean existsBookmark(Integer memberId, Integer boardId) {
        return memberRepository.existsBookmark(memberId, boardId);
    }

    public boolean existsBoardLikes(Integer memberId, Integer boardId) {
        return memberRepository.existsBoardLikes(memberId, boardId);
    }

    public boolean existsBoardDislikes(Integer memberId, Integer boardId) {
        return memberRepository.existsBoardDislikes(memberId, boardId);
    }

}
