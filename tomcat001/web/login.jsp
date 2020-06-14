<%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/5/27
  Time: 3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- css files -->
    <link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
    <link href="css/bootstrap-log.css" rel="stylesheet" type="text/css" />
    <!-- /css files -->
</head>

<body>
    <%
        if(request.getAttribute("message") =="fail"){
     %>
      <script type="text/javascript">
             alert("登录失败！");
          </script>
  <%}%>
<header>

    <!-- Navbar
    ================================================== -->
    <div class="row">
        <div class="logo">
            <a href="index.jsp"><img src="img/logo.png" alt="" /></a>
        </div><!-- logo -->
        <div class="navigation">
            <div id="menu" class="fix-fish-menu clearfix">
                <ul id="nav" class="sf-menu">
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="#">个人空间</a></li>
                    <li> <a href="#">写博客</a></li>
                    <li class="active"> <a href="login.jsp">登录/注册</a></li>
                </ul><!-- end #nav  -->
            </div><!-- end #menu  -->
        </div><!-- navigation -->
        <form action="/searchM" class="search" method="get" id="search">
            <input id="search1" type="text" name="search" value="SEARCH" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'SEARCH';}">
            <div class="button-row">
                <input type="button" class="search-button" value="搜索博文" onclick="test(1)">
                <input type="button" class="search-button" value="搜索标签" onclick="test(2)">
            </div>
        </form>
    </div><!-- row -->
    <script type="text/javascript">
        function test(val){
            //判断参数
            if(val==1){
                location.href="searchM?search="+document.getElementById("search1").value;
            }
            if(val==2){
                location.href="searchT?search="+document.getElementById("search1").value;
            }
        }
    </script>

</header>
<div class="log">
    <div class="content1">
        <h2>登录</h2>
        <form action="/login" method="post" id="loginForm">
            <input type="text" name="username" value="USERNAME" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'USERNAME';}">
            <input type="password" name="password" value="PASSWORD" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'PASSWORD';}">
            <div class="button-row">
                <input type="submit" class="sign-in" value="登录">
                <input type="reset" class="reset" value="清空">
                <div class="clear"></div>
            </div>
        </form>
        <a href="register.jsp">注册账号</a>
    </div>
    <div class="clear"></div>
</div>
<div class="wrap wrap_white">
    <div class="container title">
        <h2> </h2>
    </div>
</div>

<footer class="footer slate_gray">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <p class="copyright">Copyright &copy; 2020.BaldCat All rights reserved.</p>
            </div>
            <div class="col-sm-6">
                <p class="social__text">Contact us: xxxxxxx@xxxx.com</p>
            </div>
        </div>
    </div>
</footer>

</body>
</html>