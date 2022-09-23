<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap Login Form Template</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="<c:url value="/user_assets/bootstrap/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/user_assets/font-awesome/css/font-awesome.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/user_assets/css/form-elements.css"/>">
        <link rel="stylesheet" href="<c:url value="/user_assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/user_assets/css/login.css"/>">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="<c:url value="/user_assets/ico/favicon.png"/>">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/user_assets/ico/apple-touch-icon-144-precomposed.png"/>">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value="/user_assets/ico/apple-touch-icon-114-precomposed.png"/>">
        <link rel="apple-touch-icon-precomposed" href="<c:url value="/user_assets/ico/apple-touch-icon-57-precomposed.png"/>">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/user_assets/ico/apple-touch-icon-72-precomposed.png"/>">

    </head>

    <body>
        <jsp:include page="/page/nav.jsp" />
        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>萍乡学院</strong> 图书馆管理系统</h1>
                            <% 
								String msg = request.getParameter("page/msg");
								if(msg != null)
								{
                                    out.println("<h2>"+msg+"</h2>");
                                }
							%>
                            
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登录</h3>
                        			<c:choose>
                                        <c:when test="${requestScope.warn != null}" >
                                            <p class="warning">${requestScope.warn}</p>
                                        </c:when>
                                        <c:when test="${requestScope.msg != null}" >
                                            <p>${requestScope.msg}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>请输入您的用户名和密码</p>
                                        </c:otherwise>
                                    </c:choose>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action='<c:url value= "/login.do" />' method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" name="username" placeholder="用户名" class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="password" placeholder="密码" class="form-password form-control" id="form-password">
			                        </div>
			                        <button type="submit" class="btn">登录</button>
			                    	<div class="register text-right"><a href='<c:url value="/page/user/register.jsp" />'>没有账号？去注册</a></div>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyrights"><a href="../index.jsp" title="萍乡学院图书馆管理系统">萍乡学院图书馆管理系统</a></div>


        <!-- Javascript -->
        <script src="<c:url value="/user_assets/js/jquery-1.11.1.min.js"/>"></script>
        <script src="<c:url value="/user_assets/bootstrap/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/user_assets/js/jquery.backstretch.min.js"/>"></script>
        <script src="<c:url value="/user_assets/js/login.js"/>"></script>
        
        <!--[if lt IE 10]>
            <script src="<c:url value="/user_assets/js/placeholder.js"/>"></script>
        <![endif]-->

    </body>

</html>