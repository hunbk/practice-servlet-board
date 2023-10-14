package com.bit.homework.repository.member;

import com.bit.homework.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Integer memberId);

    Member findByEmail(String email);

    void addBookmark(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    void removeBookmark(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    void addBoardLikes(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    void removeBoardLikes(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    void addBoardDislikes(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    void removeBoardDislikes(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    boolean existsBookmark(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    boolean existsBoardLikes(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);

    boolean existsBoardDislikes(@Param("memberId") Integer memberId, @Param("boardId") Integer boardId);
}
