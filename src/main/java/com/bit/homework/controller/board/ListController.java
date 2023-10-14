package com.bit.homework.controller.board;

import com.bit.homework.controller.Controller;
import com.bit.homework.domain.Board;
import com.bit.homework.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ListController implements Controller {

    /**
     * OCP 원칙을 위배하는 코드
     */
    private BoardService boardService = BoardService.getInstance();

    //TODO: 생성자 주입 적용
//    private final BoardService boardService;
//    public ListController(BoardService boardService) {
//        this.boardService = boardService;
//    }

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<>();

        //검색 기능
        String type = request.getParameter("type");
        String query = request.getParameter("query");
        params.put("type", type);
        params.put("query", query);

        //페이징 기능
        int pageSize = 5;
        int currentPage = Optional.ofNullable(request.getParameter("page"))
                .map(Integer::parseInt)
                .orElse(1); //디폴트 페이지 1로 설정
        int offset = (currentPage - 1) * pageSize;

        params.put("pageSize", pageSize);
        params.put("currentPage", currentPage);
        params.put("offset", offset);

        int totalCount = boardService.getTotalCount(params);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        int pageRange = 4;
        int startPage = ((currentPage - 1) / pageRange) * pageRange + 1;
        int endPage = Math.min(startPage + (pageRange - 1), totalPages);

        //게시글 리스트
        List<Board> boards = boardService.findAll(params);
        request.setAttribute("boards", boards);

        //페이지 정보
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", currentPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);

        //검색 정보
        request.setAttribute("type", type);
        request.setAttribute("query", query);

        return "board";
    }
}
