package com.bit.homework.repository.board;

import com.bit.homework.domain.Board;

import java.util.List;
import java.util.Map;

public interface BoardRepository {

    void save(Board board);

    void update(Integer boardId, Board board);

    void delete(Integer boardId);

    Board findById(Integer boardId);

    List<Board> findAll(Map<String, Object> params);

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
