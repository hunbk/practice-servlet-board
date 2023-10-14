package com.bit.homework.controller.board.api;

import com.bit.homework.controller.Controller;
import com.bit.homework.service.BoardService;
import com.bit.homework.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DislikesApiController implements Controller {

    private BoardService boardService = BoardService.getInstance();
    private MemberService memberService = MemberService.getInstance();


    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "status/401";
        }

        Integer memberId = (Integer) session.getAttribute("memberId");
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));

        boolean exists = memberService.existsBoardDislikes(memberId, boardId);
        if (exists) {
            //싫어요 취소
            memberService.removeBoardDislikes(memberId, boardId);
            boardService.cancelDislikes(boardId);
        } else {
            //싫어요
            memberService.addBoardDislikes(memberId, boardId);
            boardService.addDislikes(boardId);
        }

        return "status/200";
    }
}
