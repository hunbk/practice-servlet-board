package com.bit.homework.controller;

import com.bit.homework.controller.board.ListController;
import com.bit.homework.controller.board.UpdateController;
import com.bit.homework.controller.board.ViewController;
import com.bit.homework.controller.board.WriteController;
import com.bit.homework.controller.board.api.*;
import com.bit.homework.controller.member.*;
import com.bit.homework.controller.member.api.AddBookmarkApiController;
import com.bit.homework.controller.member.api.RemoveBookmarkApiController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DispatcherServlet", urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {

    private Map<String, Controller> controllerMap = new HashMap<>();

    public DispatcherServlet() {
        //TODO: 싱글톤 방식의 한계(OCP 원칙을 어김) 개선하기
        //home
        controllerMap.put("/", new HomeController());

        //board
        controllerMap.put("/boards", new ListController());
        controllerMap.put("/boards/new", new WriteController());
        controllerMap.put("/boards/view", new ViewController());
        controllerMap.put("/boards/update", new UpdateController());
        controllerMap.put("/api/boards/delete", new DeleteApiController());
        controllerMap.put("/api/boards/likes", new LikesApiController());
        controllerMap.put("/api/boards/dislikes", new DislikesApiController());
        controllerMap.put("/api/boards/likes/count", new CountLikesApiController());
        controllerMap.put("/api/boards/dislikes/count", new CountDislikesApiController());


        //member
        controllerMap.put("/signin", new SignInController());
        controllerMap.put("/signup", new SignUpController());
        controllerMap.put("/logout", new LogoutController());
        controllerMap.put("/mypage", new MyPageController());
        controllerMap.put("/members/bookmark/remove", new RemoveBookmarkController());
        controllerMap.put("/api/members/bookmark/add", new AddBookmarkApiController());
        controllerMap.put("/api/members/bookmark/remove", new RemoveBookmarkApiController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: 서블릿 필터 적용(세션 로그인 체크)
        String requestURI = request.getRequestURI();

        Controller controller = controllerMap.get(requestURI);
        if (controller == null) {
            //요청이 매핑된 컨트롤러가 없는 경우 디폴트 서블릿에게 위임
            RequestDispatcher defaultDispatcher = request.getServletContext().getNamedDispatcher("default");
            defaultDispatcher.forward(request, response);
            return;
        }

        String viewName = controller.process(request, response);

        View view = viewResolver(viewName);
        view.render(request, response);
    }

    private View viewResolver(String viewName) {
        if (viewName.startsWith("redirect:")) {
            return new View(viewName.substring("redirect:".length()), true);
        }
        return new View("/WEB-INF/views/" + viewName + ".jsp", false);
    }
}
