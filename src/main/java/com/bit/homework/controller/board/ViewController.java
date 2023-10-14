package com.bit.homework.controller.board;

import com.bit.homework.controller.Controller;
import com.bit.homework.domain.Board;
import com.bit.homework.service.BoardService;
import com.bit.homework.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewController implements Controller {

    private BoardService boardService = BoardService.getInstance();
    private MemberService memberService = MemberService.getInstance();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));

        Board foundBoard = boardService.findById(boardId);
        if (foundBoard == null) {
            return "redirect:/boards";
        }

        //새로고침 판별
        String cacheControlHeader = request.getHeader("Cache-Control");
        if (cacheControlHeader == null || cacheControlHeader.contains("no-cache")) {
            boardService.increaseViews(boardId);
        }

        request.setAttribute("board", foundBoard);

        //로그인한 회원의 현재 게시글 스크랩 여부
        boolean bookmarked = false;
        //로그인한 회원의 현재 게시글 좋아요/싫어요 여부
        boolean liked = false;
        boolean disliked = false;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("memberId") != null) {
            Integer memberId = (Integer) session.getAttribute("memberId");
            bookmarked = memberService.existsBookmark(memberId, boardId);
            liked = memberService.existsBoardLikes(memberId, boardId);
            disliked = memberService.existsBoardDislikes(memberId, boardId);
        }
        request.setAttribute("bookmarked", bookmarked);
        request.setAttribute("liked", liked);
        request.setAttribute("disliked", disliked);

        //목록으로 돌아갈 때 검색, 페이지 적용된 페이지로
        request.setAttribute("type", request.getParameter("type"));
        request.setAttribute("query", request.getParameter("query"));
        request.setAttribute("page", request.getParameter("page"));

        return "info";
    }
}
