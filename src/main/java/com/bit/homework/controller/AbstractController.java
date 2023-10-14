package com.bit.homework.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController implements Controller {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            return doGet(request, response);
        } else if (method.equals("POST")) {
            return doPost(request, response);
        } else {
            throw new ServletException("잘못된 요청입니다.");
        }
    }

    public abstract String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public abstract String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
