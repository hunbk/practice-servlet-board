package com.bit.homework.controller.board;

import com.bit.homework.controller.AbstractController;
import com.bit.homework.domain.Board;
import com.bit.homework.domain.Member;
import com.bit.homework.service.BoardService;
import com.bit.homework.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WriteController extends AbstractController {

    private BoardService boardService = BoardService.getInstance();
    private MemberService memberService = MemberService.getInstance();

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "redirect:/signin";
        }

        return "write";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("memberId") == null) {
            return "redirect:/signin";
        }

        String category = request.getParameter("category");
        String password = request.getParameter("password");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        Integer memberId = (Integer) session.getAttribute("memberId");
        Member foundMember = memberService.findById(memberId);
        String writer = foundMember.getNickname();

        Board board = new Board(memberId, writer, category, password, subject, content, "");
        boardService.save(board);

        return "redirect:/boards";
    }
}
