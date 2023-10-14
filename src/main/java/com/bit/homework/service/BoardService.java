package com.bit.homework.service;

import com.bit.homework.domain.Board;
import com.bit.homework.repository.board.BoardRepository;
import com.bit.homework.repository.board.BoardRepositoryImpl;

import java.util.List;
import java.util.Map;

public class BoardService {
    private static final BoardService instance = new BoardService();

    public static BoardService getInstance() {
        return instance;
    }

    private BoardService() {
    }

    /**
     * OCP 원칙을 위반하는 코드
     */
    private BoardRepository boardRepository = BoardRepositoryImpl.getInstance();

    //TODO: 생성자 주입 적용
//    private final BoardMapper boardMapper;
//    public BoardService(BoardMapper boardMapper) {
//        this.boardMapper = boardMapper;
//    }

    public Board findById(Integer boardId) {
        return boardRepository.findById(boardId);
    }

    public List<Board> findAll(Map<String, Object> params) {
        return boardRepository.findAll(params);
    }

    public int getTotalCount(Map<String, Object> params) {
        return boardRepository.getTotalCount(params);
    }

    public void save(Board board) {
        boardRepository.save(board);
    }

    public void update(Integer boardId, Board board) {
        boardRepository.update(boardId, board);
    }

    public void delete(Integer boardId) {
        boardRepository.delete(boardId);
    }

    public void increaseViews(Integer boardId) {
        boardRepository.increaseViews(boardId);
    }

    public void addLikes(Integer boardId) {
        boardRepository.addLikes(boardId);
    }

    public void cancelLikes(Integer boardId) {
        boardRepository.cancelLikes(boardId);
    }

    public void addDislikes(Integer boardId) {
        boardRepository.addDislikes(boardId);
    }

    public void cancelDislikes(Integer boardId) {
        boardRepository.cancelDislikes(boardId);
    }

    public List<Board> findAllByMemberBookmark(Integer memberId) {
        return boardRepository.findAllByMemberBookmark(memberId);
    }

    public int getLikesCount(Integer boardId) {
        return boardRepository.getLikesCount(boardId);
    }

    public int getDislikesCount(Integer boardId) {
        return boardRepository.getDislikesCount(boardId);
    }
}
