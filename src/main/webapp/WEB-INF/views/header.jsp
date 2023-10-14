<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page
        language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Insert title here</title>
</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button
                    type="button"
                    class="navbar-toggle"
                    data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1"
            >
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span> <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/boards">Start Simple Web</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/boards">Board</a></li>
                <c:if test="${not empty sessionScope.memberId}">
                    <li><a href="/logout">LogOut</a></li>
                </c:if>
                <c:if test="${empty sessionScope.memberId}">
                    <li><a href="/signin">LogIn</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.memberId}">
                    <li><a href="/mypage">Mypage</a></li>
                </c:if>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<!-- Page Header -->
</body>
</html>
