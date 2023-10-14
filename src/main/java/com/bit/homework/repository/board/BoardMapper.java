package com.bit.homework.repository.board;

import com.bit.homework.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    void save(Board board);

    void update(@Param("boardId") Integer boardId, @Param("board") Board board);

    void delete(Integer boardId);

    Board findById(Integer boardId);

    //TODO: Map대신 @Param Dto dto 적용
    List<Board> findAll(Map<String, Object> params);

    //TODO: Map대신 @Param Dto dto 적용
    int getTotalCount(Map<String, Object> params);

    void increaseViews(Integer boardId);

    void addLikes(Integer boardId);

    void cancelLikes(Integer boardId);

    void addDislikes(Integer boardId);

    void cancelDislikes(Integer boardId);

    List<Board> findAllByMemberBookmark(Integer memberId);

    int getLikesCount(Integer boardId);

    int getDislikesCount(Integer boardId);
}
