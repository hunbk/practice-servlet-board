package com.bit.homework.controller.member;

import com.bit.homework.controller.Controller;
import com.bit.homework.domain.Board;
import com.bit.homework.service.BoardService;
import com.bit.homework.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyPageController implements Controller {

    private MemberService memberService = MemberService.getInstance();
    private BoardService boardService = BoardService.getInstance();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "redirect:/signin";
        }

        Integer memberId = (Integer) session.getAttribute("memberId");

        List<Board> boards = boardService.findAllByMemberBookmark(memberId);
        request.setAttribute("boards", boards);

        //TODO: 검색, 페이징 처리

        return "mypage";
    }
}
