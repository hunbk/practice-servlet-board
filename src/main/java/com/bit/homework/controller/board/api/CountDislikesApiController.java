package com.bit.homework.controller.board.api;

import com.bit.homework.controller.Controller;
import com.bit.homework.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountDislikesApiController implements Controller {

    private BoardService boardService = BoardService.getInstance();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        Integer boardId = Integer.parseInt(request.getParameter("boardId"));
        int count = boardService.getDislikesCount(boardId);
        request.setAttribute("count", count);

        return "data/count";
    }
}
