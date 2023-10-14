package com.bit.homework.controller.member.api;

import com.bit.homework.controller.Controller;
import com.bit.homework.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddBookmarkApiController implements Controller {

    private MemberService memberService = MemberService.getInstance();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            //TODO: 401 Unauthorized 로 변경
            return "status/500";
        }

        Integer memberId = (Integer) session.getAttribute("memberId");
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));

        boolean exists = memberService.existsBookmark(memberId, boardId);
        if (exists) {
            return "status/400";
        }

        memberService.addBookmark(memberId, boardId);

        return "status/200";
    }
}
