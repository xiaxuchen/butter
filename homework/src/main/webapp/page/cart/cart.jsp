<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加商品</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="<c:url value="/js/cart/cart.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/css/cart/cart.css"/>" >
</head>
<body>
    <jsp:include page="/page/nav.jsp" />
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 col-lg-offset-4 col-sm-offset-2 col-lg-4 col-sm-8">
                    <div class="form-title text-center">
                        <h3>选购商品</h3>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action='<c:url value="/cart.do" />' method="post" class="cart-form">
                            <div class="form-group">
                                <label for="good" class="sr-only">请输入想要购买的商品</label><br>
                                <input id="good" name="good" class="form-control" type="text" placeholder="请输入想要购买的商品"/>
                            </div>
                            <button class="btn btn-default" type="submit" >添加</button>
                            <a href="cart_show.jsp" class="btn btn-link">查看购物车</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
