package com.bit.homework.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class View {
    private String viewPath;
    private boolean isRedirect;

    public View(String viewPath, boolean isRedirect) {
        this.viewPath = viewPath;
        this.isRedirect = isRedirect;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isRedirect) {
            response.sendRedirect(viewPath);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
            requestDispatcher.forward(request, response);
        }
    }
}
