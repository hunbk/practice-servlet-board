package com.bit.homework.controller.member;

import com.bit.homework.controller.AbstractController;
import com.bit.homework.domain.Member;
import com.bit.homework.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpController extends AbstractController {

    private MemberService memberService = MemberService.getInstance();

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

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        return "signup";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");

        if (email.isBlank() || nickname.isBlank() || password.isBlank() || passwordCheck.isBlank()) {
            request.setAttribute("globalError", "공백은 허용되지 않습니다.");
            return "signup";
        }

        Member foundMember = memberService.findByEmail(email);

        Map<String, String> errors = new HashMap<>();
        if (foundMember != null) {
            errors.put("existsEmail", "이미 존재하는 이메일입니다.");
        }
        if (errors.isEmpty() && !password.equals(passwordCheck)) {
            errors.put("passwordNotMatch", "비밀번호가 일치하지 않습니다.");
        }
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            return "signup";
        }

        Member member = new Member(email, password, nickname);
        memberService.save(member);

        return "redirect:/signin";
    }
}
