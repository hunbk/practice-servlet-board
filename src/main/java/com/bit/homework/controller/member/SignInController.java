package com.bit.homework.controller.member;

import com.bit.homework.controller.AbstractController;
import com.bit.homework.domain.Member;
import com.bit.homework.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInController extends AbstractController {

    private MemberService memberService = MemberService.getInstance();

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "signin";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.isBlank() || password.isBlank()) {
            request.setAttribute("globalError", "공백은 허용되지 않습니다.");
            return "signin";
        }

        Member foundMember = memberService.findByEmail(email);
        if (foundMember == null || !foundMember.getPassword().equals(password)) {
            request.setAttribute("loginFail", "로그인 실패.");
            return "signin";
        }

        HttpSession session = request.getSession();
        session.setAttribute("memberId", foundMember.getMemberId());

        return "redirect:/boards";
    }
}
