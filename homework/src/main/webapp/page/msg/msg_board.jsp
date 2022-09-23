<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/6
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>留言板</title>
    <!--图标库-->
    <!--<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">-->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="<c:url value="/css/reset.css"/>">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.js"/>"></script>
    <script src="<c:url value="/js/msg/msg.js"/>"></script>
    <link href="<c:url value="/css/msg/msg_board.css"/>" rel="stylesheet">
</head>
<body>
<jsp:include page="/page/nav.jsp" />
<!--留言区-->
<article class="root container">
    <!--留言主体-->
    <main>
        <ul class="list-group content">
            <li class="font-bigger list-group-item header">
                <c:choose>
                    <c:when test="${isMine}">
                        <a href="/msg/show"><button class="btn btn-default btn-leave-msg">最新留言</button></a>
                        <a href="/msg/show/mine"><button class="btn btn-info btn-leave-msg">我的留言</button></a>
                    </c:when>
                    <c:otherwise>
                        <a href="/msg/show"><button class="btn btn-info btn-leave-msg">最新留言</button></a>
                        <a href="/msg/show/mine"><button class="btn btn-default btn-leave-msg">我的留言</button></a>
                    </c:otherwise>
                </c:choose>
            </li>
            <c:forEach items="${messages}" var="msg">
                <li class="list-group-item">
                    <div class="msg <c:if test="${sessionScope.user.username eq msg.user.username}">writable</c:if>">
                        <c:if test="${sessionScope.user.username eq msg.user.username}">
                        <form action="/msg/alter" method="post">
                            <input type="hidden" name="id" value="${msg.id}">
                            <input type="hidden" name="isMine" value="${isMine}">
                            <input type="hidden" name="page" value="${curPage}">
                            </c:if>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <img src="<c:url value="/images/msg/head_img.png"/>" class="head-img">
                                    <span class="username">${msg.user.username}</span>
                                    <c:if test="${sessionScope.user.username eq msg.user.username}">
                                        <a href="<c:url value="/msg/delete?isMine=${isMine}&id=${msg.id}"/>"><button type="button" class="close font-bigger" aria-hidden="true">&times;</button></a>
                                    </c:if>
                                </li>
                                <li class="list-group-item" style="word-wrap:break-word">
                                    <span class="content">${msg.content}</span>
                                </li>
                            </ul>
                            <c:if test="${sessionScope.user.username eq msg.user.username}">
                        </form>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
            <li class="list-group-item footer">
                <ul>
                    <c:forEach var="i" begin="1" end="${pages}">
                    <c:choose>
                    <c:when test="${not isMine}">
                    <a href="<c:url value='/msg/show?page=${i}'/>">
                        </c:when>
                        <c:otherwise>
                        <a href="<c:url value='/msg/show/mine?page=${i}'/>">
                            </c:otherwise>
                            </c:choose>
                            <li class="text-center list-inline page_count <c:if test="${i != curPage}">not_cur_page</c:if>" >
                                    ${i}
                            </li>
                        </a>
                        </c:forEach>
                </ul>
            </li>
        </ul>
    </main>
    <hr>
    <!--留言主体-->
    <section>
        <form role="form" method="post" action="<c:url value="/msg/add"/>">
            <div class="form-group">
                <input type="hidden" name="isMine" value="${isMine}">
                <label for="board">留言板</label>
                <textarea class="form-control" id="board" name="content"></textarea>
                <button class="btn btn-lg btn-success btn-commit float-right pull-right">提交</button>
            </div>
        </form>
    </section>
</article>
<!--留言区-->
</body>
</html>