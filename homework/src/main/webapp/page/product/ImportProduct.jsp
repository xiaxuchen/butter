<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/10
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>导入商品信息</title>
</head>
<body>
    <form role="form" action="/product/import" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="file">商品信息导入</label>
            <input type="file" id="file" name="file">
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</body>
</html>
