package com.bit.homework.repository.member;

import com.bit.homework.domain.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Integer memberId);

    Member findByEmail(String email);

    void addBookmark(Integer memberId, Integer boardId);

    void removeBookmark(Integer memberId, Integer boardId);

    boolean existsBookmark(Integer memberId, Integer boardId);

    void addBoardLikes(Integer memberId, Integer boardId);

    void removeBoardLikes(Integer memberId, Integer boardId);

    boolean existsBoardLikes(Integer memberId, Integer boardId);

    void addBoardDislikes(Integer memberId, Integer boardId);

    void removeBoardDislikes(Integer memberId, Integer boardId);

    boolean existsBoardDislikes(Integer memberId, Integer boardId);
}
