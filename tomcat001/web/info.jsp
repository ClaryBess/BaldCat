<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/6/3
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>个人信息</title>

    <!-- Styles -->
    <link href="profile-css/bootstrap.css" rel="stylesheet" type='text/css' media="all" />
    <link href="profile-css/style.css" rel="stylesheet" type="text/css" />


<body>

<div class="container">

    <!-- Masthead
    ================================================== -->
    <header>

        <div class="row">
            <div class="logo">
                <a href="index.html"><img src="img/logo.png" alt=""/></a>
            </div><!-- logo -->
            <div class="navigation">
                <div id="menu" class="fix-fish-menu clearfix">
                    <ul id="nav" class="sf-menu">
                        <li><a href="index_afterLogin.jsp">首页</a></li>
                        <li ><a href="profile_self.jsp">个人空间</a></li>
                        <li><a href="markdown.jsp">写博客</a></li>
                        <li class="active"> <a href="info.jsp">个人信息</a></li>
                        <li> <a href="/logout">注销</a> </li>
                    </ul><!-- end #nav  -->
                </div><!-- end #menu  -->
            </div><!-- navigation -->
            <form action="/searchML" class="search" method="post" id="search">
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
                    location.href="searchML?search="+document.getElementById("search1").value;
                }
                if(val==2){
                    location.href="searchTL?search="+document.getElementById("search1").value;
                }
            }
        </script>

    </header>

    <!-- Blog
    ================================================== -->
    <div class="row entry-blog">
        <!-- SIDEBAR -->
        <div class="span10" >

            <table align="center"width="100%" height="100%">
                <tr class="entry-meta clearfix">
                    <td width="27%"></td>
                    <td >
                        <br/>
                        <%request.setCharacterEncoding("utf-8");%>
                        <div ><a class="btn" href="edit.jsp"><font size="3">🖊 修改个人信息 🖊</font></a>
                            <br/><br/><br/>
                            <span><strong><i class="icon-user"></i> <font size="3px"> 用户名：</font></strong>
                                <a><font size="3px"> ${user.getName()}</font></a></span><br/>
                            <span><strong><i class="icon-pencil"></i><font size="3px"> 性别：</font></strong>
                                <a><font size="3px">${user.printGender()}</font></a></span><br/>
                            <span><strong><i class="icon-time"></i><font size="3px"> 生日：</font></strong>
                                <a><font size="3px">${user.getBirthday()}</font></a></span><br/>
                            <!-- <div class="entry-share">
                              <span><strong><i class="icon-share"></i> Share:</strong></span>
                            </div> --> <!-- entry-share -->

                            <span><strong><i class="icon-comment"></i><font size="3px"> 个人签名</font></strong></span>
                            <p ><font size="3" color="#8d8d8d" ><strong> ${user.getIntroduction()}</strong></font></p>
                        </div>
                        <div align="center">

                            <br/><br/><br/><br/><br/><br/><br/><br/><br/>
                        </div>
                    </td>
                    <td width="30%"></td>
                </tr>

            </table>


        </div><!-- span3 -->

    </div><!-- row -->


    <!-- Footer
    ================================================== -->
    <footer class="footer slate_gray">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <p class="copyright">Copyright &copy; 2020.BaldCat All rights reserved.</p>
                </div>
                <div class="col-sm-6">
                    <div class="social navbar-right">
                        <p class="social__text">Contact us: xxxxxxx@xxxx.com</p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Javascripts
    ================================================== -->
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/custom.js"></script>
</body>
</html>
