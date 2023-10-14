package com.bit.homework.controller.member;

import com.bit.homework.controller.Controller;
import com.bit.homework.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveBookmarkController implements Controller {

    private MemberService memberService = MemberService.getInstance();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "redirect:/signin";
        }

        Integer memberId = (Integer) session.getAttribute("memberId");
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));

        memberService.removeBookmark(memberId, boardId);

        return "redirect:/mypage";
    }
}
