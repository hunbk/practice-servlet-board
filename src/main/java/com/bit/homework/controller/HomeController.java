package com.bit.homework.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController implements Controller {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "redirect:/boards";
    }
}
