<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/25
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="num" class="com.xxc.homework.entity.RandomNum" scope="session" />
<c:choose>
    <c:when test="${param.num != null}">
        <jsp:setProperty name="num" property="num" value='${param.num}' />
    </c:when>
    <c:otherwise>
        <jsp:setProperty name="num" property="state" value="2" />
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>猜数字游戏</title>
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <script src="<c:url value="/js/bootstrap.js"/>"></script>
    <link href="<c:url value="/css/game/game.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/game/game.js"/>"></script>
</head>
<body>
<main class="container">
    <div class="inner_background">
        <div class="row title">
            <div class="game_title text-center col-md-4 col-md-offset-5"><h2>猜数字游戏</h2></div>
        </div>
        <div class='row start <c:if test="${sessionScope.num.state != 2}">hide</c:if>'>
            <button class="btn btn-success btn-lg btn_start col-md-4 col-md-offset-5">开始游戏</button>
        </div>
        <div class='game_content <c:if test="${sessionScope.num.state == 2}">hide</c:if>'>
            <div class="row">
                <div class="num col-md-offset-5 col-md-4 text-center">
                    <h2 class="text-info">数字为:
                        <span class="text-danger">
                            <c:choose>
                                <c:when test="${sessionScope.num.state == 1}">
                                    ${sessionScope.num.num}
                                </c:when>
                                <c:otherwise>
                                    ???
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </h2>
                </div><br/>
            </div>
            <c:choose>
                <c:when test="${sessionScope.num.state == 0}">
                    <div class="row text-warning">
                        <div class="col-md-4 col-md-offset-5 text-center msg"><span>这是第${sessionScope.num.count}次猜,猜大了</span></div>
                    </div>
                </c:when>
                <c:when test="${sessionScope.num.state == -1}">
                    <div class="row text-warning">
                        <div class="col-md-4 col-md-offset-5 text-center msg"><span>这是第${sessionScope.num.count}次猜,猜小了</span></div>
                    </div>
                </c:when>
                <c:when test="${sessionScope.num.state == 1}">
                    <div class="row text-success">
                        <div class="col-md-4 col-md-offset-5 text-center msg"><span>这是第${sessionScope.num.count}次猜,猜对了</span></div>
                    </div>
                    <jsp:setProperty name="num" property="count" value="0" />
                </c:when>
            </c:choose>
            <div class="row form">
                <div class="col-md-5 col-md-offset-6">
                    <form method="get" class="form-inline">
                        <label class="control-label" for="num">请输入您猜的数字(100以内):</label><br/>
                        <input class="form-control" type="number" id="num" name="num">
                        <button class="btn btn-info">确定</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
