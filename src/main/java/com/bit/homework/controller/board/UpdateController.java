package com.bit.homework.controller.board;

import com.bit.homework.controller.AbstractController;
import com.bit.homework.domain.Board;
import com.bit.homework.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateController extends AbstractController {

    private BoardService boardService = BoardService.getInstance();

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "redirect:/signin";
        }

        int boardId = Integer.parseInt(request.getParameter("boardId"));
        Board board = boardService.findById(boardId);
        System.out.println("board = " + board);

        request.setAttribute("board", board);

        return "update";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "redirect:/signin";
        }

        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        String category = request.getParameter("category");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        Board board = new Board();
        board.setBoardId(boardId);
        board.setCategory(category);
        board.setSubject(subject);
        board.setContent(content);
        boardService.update(boardId, board);

        return "redirect:/boards";
    }
}
