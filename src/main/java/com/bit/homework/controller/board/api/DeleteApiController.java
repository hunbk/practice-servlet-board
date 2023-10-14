package com.bit.homework.controller.board.api;

import com.bit.homework.controller.Controller;
import com.bit.homework.domain.Board;
import com.bit.homework.service.BoardService;
import com.bit.homework.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteApiController implements Controller {

    private BoardService boardService = BoardService.getInstance();
    private MemberService memberService = MemberService.getInstance();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "status/500";
        }

        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        Board foundBoard = boardService.findById(boardId);

        Integer memberId = (Integer) session.getAttribute("memberId");
        if (!foundBoard.getMemberId().equals(memberId)) {
            return "status/403";
        }

        boardService.delete(boardId);

        return "status/204";
    }
}
