<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>Start Simple Web</title>

    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href="/css/clean-blog.css" rel="stylesheet"/>
    <link href="/css/board.css" rel="stylesheet"/>
    <!-- Custom Fonts -->
    <link
            href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
            rel="stylesheet"
            type="text/css"
    />
    <link
            href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
            rel="stylesheet"
            type="text/css"
    />
    <link
            href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
            rel="stylesheet"
            type="text/css"
    />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            let deleteButton = document.getElementById('delete-button');
            if (deleteButton != null) {
                deleteButton.addEventListener('click', () => {
                    let boardId = document.getElementById('boardId').value;
                    const url = '/api/boards/delete?boardId=' + boardId;
                    fetch(url)
                        .then(response => {
                            if (response.ok) {
                                return response.text();
                            } else {
                                throw new Error('Request failed');
                            }
                        })
                        .then(data => {
                            if (data == '204') {
                                alert('삭제 완료');
                                location.href = '/boards';
                            } else if (data == '403') {
                                alert('삭제 권한이 없습니다.');
                            } else if (data == '500') {
                                alert('로그인이 필요합니다.');
                                location.href = '/signin';
                            }
                        })
                        .catch(error => {
                            console.error(error);
                        });
                });
            }


            let bookmarkButton = document.getElementById('bookmark-button');
            bookmarkButton.addEventListener('click', () => {
                let boardId = document.getElementById('boardId').value;
                const isBookmarked = bookmarkButton.dataset.bookmarked;
                if (isBookmarked === 'false') {
                    addBookmark(boardId);
                } else {
                    removeBookmark(boardId);
                }
            });

            function addBookmark(boardId) {
                const url = '/api/members/bookmark/add?boardId=' + boardId;
                fetch(url)
                    .then(response => {
                        if (response.ok) {
                            return response.text();
                        } else {
                            throw new Error('Request failed');
                        }
                    })
                    .then(data => {
                        if (data == '200') {
                            //스크랩 취소로 변경
                            bookmarkButton.dataset.bookmarked = 'true';
                            bookmarkButton.innerText = '스크랩 취소';
                            alert('스크랩 추가');
                        } else if (data == '400') {
                            alert('이미 스크랩에 존재함');
                        } else if (data == '500') {
                            alert('로그인이 필요합니다.');
                            location.href = '/signin';
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
            }

            function removeBookmark(boardId) {
                const url = '/api/members/bookmark/remove?boardId=' + boardId;
                fetch(url)
                    .then(response => {
                        if (response.ok) {
                            return response.text();
                        } else {
                            throw new Error('Request failed');
                        }
                    })
                    .then(data => {
                        if (data == '200') {
                            //스크랩으로 변경
                            bookmarkButton.dataset.bookmarked = 'false';
                            bookmarkButton.innerText = '스크랩';
                            alert('스크랩 취소');
                        } else if (data == '500') {
                            alert('로그인이 필요합니다.');
                            location.href = '/signin';
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
            }

            let likesCount = document.getElementById('likes-count');
            let dislikesCount = document.getElementById('dislikes-count');
            let likesText = document.getElementById('likes-text');
            let dislikesText = document.getElementById('dislikes-text');

            let likesButton = document.getElementById('likes-button');
            likesButton.addEventListener('click', () => {
                let boardId = document.getElementById('boardId').value;
                boardLikes(boardId);
            });

            let dislikesButton = document.getElementById('dislikes-button');
            dislikesButton.addEventListener('click', () => {
                let boardId = document.getElementById('boardId').value;
                boardDislikes(boardId);
            });

            function boardLikes(boardId) {
                const url = '/api/boards/likes?boardId=' + boardId;
                fetch(url)
                    .then(response => {
                        if (response.ok) {
                            return response.text();
                        } else {
                            throw new Error('Request failed');
                        }
                    })
                    .then(data => {
                        if (data === '200') {
                            //좋아요 상태 변경
                            if (likesButton.dataset.liked == 'true') {
                                //좋아요 취소 실행 후
                                likesButton.dataset.liked = 'false'
                                likesText.innerText = '좋아요';
                            } else {
                                //좋아요 실행 후
                                likesButton.dataset.liked = 'true'
                                likesText.innerText = '좋아요 취소';
                            }
                            //좋아요 카운트 업데이트
                            updateLikesCount(boardId);
                        } else if (data === '401') {
                            alert('로그인이 필요합니다.');
                            location.href = '/signin';
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
            }

            async function updateLikesCount(boardId) {
                likesCount.innerText = await fetch('/api/boards/likes/count?boardId=' + boardId)
                    .then(response => response.text());
            }

            function boardDislikes(boardId) {
                const url = '/api/boards/dislikes?boardId=' + boardId;
                fetch(url)
                    .then(response => {
                        if (response.ok) {
                            return response.text();
                        } else {
                            throw new Error('Request failed');
                        }
                    })
                    .then(data => {
                        if (data == '200') {
                            //싫어요 상태 변경
                            if (dislikesButton.dataset.disliked == 'true') {
                                //싫어요 취소 실행 후
                                dislikesButton.dataset.disliked = 'false'
                                dislikesText.innerText = '싫어요';
                            } else {
                                //싫어요 실행 후
                                dislikesButton.dataset.disliked = 'true'
                                dislikesText.innerText = '싫어요 취소';
                            }
                            //싫어요 카운트 업데이트
                            updateDislikesCount(boardId);
                        } else if (data == '401') {
                            alert('로그인이 필요합니다.');
                            location.href = '/signin';
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
            }

            async function updateDislikesCount(boardId) {
                dislikesCount.innerText = await fetch('/api/boards/dislikes/count?boardId=' + boardId)
                    .then(response => response.text());
            }
        });
    </script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header
        class="intro-header"
        style="background-image: url('/img/about-bg.jpg'); height: 200px"
>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div
                        class="site-heading"
                        style="margin-top: 10px; padding-top: 35px; padding-bottom: 35px"
                >
                    <h2>SAMPLE</h2>
                    <span class="subheading">BULLETIN BOARD</span>
                </div>
            </div>
        </div>
    </div>
</header>
<c:set var="board" value="${board}"/>
<div class="board-field">
    <input id="boardId" type="hidden" name="boardId" value="${board.boardId}">
    <input id="memberId" type="hidden" name="memberId" value="${board.memberId}">
    <div class="list-group">
        <div class="list-group-item board-title">
            <div class="board-title">
                <span class="board-category">[${board.category}]</span>${board.subject}
            </div>
            <div class="board-meta" style="font-weight: 400; font-size: 1.2rem; color: #404040">
                <p>
                    <i class="glyphicon glyphicon-user"></i>${board.writer}
                    <i class="glyphicon glyphicon-comment"></i>${0}
                    <i class="glyphicon glyphicon-ok"></i>${board.views}
                    <i class="glyphicon glyphicon-time"></i> ${board.createdDate}
                    <i class="glyphicon glyphicon-thumbs-up"></i><span id="likes-count">${board.likes}</span>
                    <i class="glyphicon glyphicon-thumbs-down"></i><span id="dislikes-count">${board.dislikes}</span>
                </p>
            </div>
            <div class="clear"></div>
        </div>
        <div class="list-group-item">
            <span class="board-contents">${board.content}</span>
            <p style="text-align: center; margin-top: 30px">
                <button id="likes-button" class="btn btn-success" data-liked="${liked}">
                    <i class="glyphicon glyphicon-thumbs-up"></i><span id="likes-text">${liked ? "좋아요 취소" : "좋아요"}</span>
                </button>
                <button id="dislikes-button" class="btn btn-warning" data-disliked="${disliked}">
                    <i class="glyphicon glyphicon-thumbs-down"></i><span id="dislikes-text">${disliked ? "싫어요 취소" : "싫어요"}</span>
                </button>
            </p>
        </div>
        <div
                class="bottom"
                style="margin: 10px; margin-top: 20px; text-align: right"
        >
            <a href="/boards?type=${type}&query=${query}&page=${page}" class="btn btn-default btn-xs pull-left">목록으로</a>
            <c:if test="${not empty sessionScope.memberId
                            and sessionScope.memberId eq board.memberId}">
                <a href="/boards/update?boardId=${board.boardId}" class="btn btn-default btn-xs">수정</a>
                <button id="delete-button" class="btn btn-default btn-xs">삭제</button>
            </c:if>
<%--            <c:if test="${not empty sessionScope.memberId}">--%>
<%--            </c:if>--%>
<%--            <c:set var="bookmarked" value="${requestScope.bookmarked}" />--%>
            <button id="bookmark-button" class="btn btn-default btn-xs" data-bookmarked="${bookmarked}">${bookmarked ? "스크랩 취소" : "스크랩"}</button>
        </div>
        <div class="clear"></div>
    </div>
</div>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <ul class="list-inline text-center">
                    <li>
                        <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-envelope-o fa-stack-1x fa-inverse"></i>
                  </span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-home fa-stack-1x fa-inverse"></i>
                  </span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                  </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">
                    Copyright &copy;2016 SIST. All rights reserved | code by milib
                </p>
            </div>
        </div>
    </div>
</footer>
<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/js/clean-blog.min.js"></script>
</body>
</html>
