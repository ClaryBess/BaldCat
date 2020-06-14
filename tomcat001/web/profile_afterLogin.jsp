<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.baldcat.entity.User" %>
<%@ page import="com.baldcat.entity.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="com.baldcat.Handler.Handler" %><%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/6/8
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户空间</title>

    <!-- Styles -->

    <link href="profile-css/bootstrap.css" rel="stylesheet"/>
    <link href="profile-css/style.css" rel="stylesheet"/>

    <style type="text/css">
        .auto-style1 {
            width: 100%;
            zoom: 1;
            text-align: right;
        }
    </style>
<body>

<div class="container">

    <!-- Masthead
    ================================================== -->
    <header>

        <div class="row">
            <div class="logo">
                <a href="index.jsp"><img src="img/logo.png" alt=""/></a>
            </div><!-- logo -->
            <div class="navigation">
                <div id="menu" class="fix-fish-menu clearfix">
                    <ul id="nav" class="sf-menu">
                        <li><a href="index_afterLogin.jsp">首页</a></li>
                        <li><a href="profile_self.jsp">个人空间</a></li>
                        <li><a href="markdown.jsp">写博客</a></li>
                        <li> <a href="info.jsp">个人信息</a></li>
                        <li> <a href="/logout">注销</a> </li>
                    </ul><!-- end #nav  -->
                </div><!-- end #menu  -->
            </div><!-- navigation -->
            <form action="/searchML" class="search" method="get" id="search">
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
        <div class="span9">

            <%
                User visit=(User) request.getAttribute("visit");
                Handler handler=new Handler();
                List<Blog> blogs=handler.blogsOfUser(visit.getUserID().toString());
                List<User> follows=handler.myFollow(visit.getUserID());
                request.setAttribute("blogs",blogs);
                request.setAttribute("follows",follows);
                User user=(User) session.getAttribute("user");
                boolean hasFollow=handler.hasFollow(user.getUserID(),Integer.parseInt(visit.getUserID().toString()));
                request.setAttribute("has",hasFollow);
            %>
            <c:forEach items="${blogs}" var="blog">
                <!-- START POST #1 -->
                <div class="row entry-post">
                    <div class="span4">
                        <div class="entry-meta clearfix">
                            <span><strong><i class="icon-comment"></i> 评论：</strong> <a href="#">${blog.commentNumber}</a></span>
                            <span><strong><i class="icon-heart"></i> 点赞：</strong> <a href="#">${blog.likeNumber}</a></span>
                            <span><strong><i class="icon-tags"></i> 标签:</strong></span>
                            <ul class="tags clearfix">
                                <c:forEach items="${blog.tags}" var="tag">
                                    <li><a>${tag}</a></li>
                                </c:forEach>
                            </ul>
                        </div><!-- entry-meta -->
                    </div><!-- span4 -->

                    <!-- POST FORMAT - IMAGE -->
                    <div class="span6">
                        <div class="row">
                            <div class="span6 format-image">
                                <div class="thumb-post view">
                                    <div class="post-date-meta">
                                        <span class="year">${blog.year}</span>
                                        <span class="date">${blog.monthDay}</span>
                                    </div><!-- post-date-meta -->
                                </div><!-- thumb-post -->
                                <div class="entry-text">
                                    <h3><a href="postL?BlogID=${blog.blogID}">${blog.title}</a></h3>
                                    <p style="display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical;overflow:hidden;"> ${blog.getAbstarct(300)}</p>
                                    <a class="btn" href="postL?BlogID=${blog.blogID}">阅读全文</a>
                                </div><!-- entry-text -->
                            </div><!-- span6 -->
                        </div><!-- row -->
                    </div><!-- span6 -->
                </div><!-- row -->

                <!-- DIVIDER -->
                <div class="row margin40">
                    <div class="span4">
                        <div class="border-5-1"></div>
                    </div>
                    <div class="span5">
                        <div class="border-5-1 "></div>
                    </div>
                </div><!-- row -->
                <!-- END POST #1 -->
            </c:forEach>
        </div><!-- span9 -->
            <!-- ============================================================================================= -->

        <!-- SIDEBAR -->
        <div class="span3 sidebar">
            <div class="entry-meta clearfix">
                <span><strong><i class="icon-user"></i> 用户名：</strong> <a>${visit.name}</a></span>
                <span><strong><i class="icon-comment"></i>性别：</strong> <a>${visit.printGender()}</a></span>
                <span><strong><i class="icon-comment"></i>生日：</strong> <a>${visit.birthday}</a></span>
                <span><strong><i class="icon-comment"></i> 粉丝数：</strong> <a>${visit.getFansNumber()}</a></span>

                <c:if test="${has}">
                    <a class="btn">♥ 已关注 ♥</a>
                </c:if>
                <c:if test="${!has}">
                    <a class="btn" href="follow">♥ 关注Ta ♥</a>
                </c:if>


                <!-- <div class="entry-share">
                  <span><strong><i class="icon-share"></i> Share:</strong></span>
                </div> --> <!-- entry-share -->
            </div><!-- entry-meta -->

            <!-- About -->
            <div class="widget well">
                <h5>个人签名</h5>
                <p class="last">${visit.introduction}</p>
                <p></p>
            </div><!-- .widget -->

            <div class="widget widget-list well">
                <h5>关注列表</h5>
                <ul class="nav nav-list">
                    <c:forEach items="${follows}" var="follow">
                        <li><strong><a href=visitL?UserID=${follow.userID}><i class="icon-user"></i> ${follow.name} </a></strong>&nbsp;&nbsp;粉丝数：${follow.fansNumber}</li>
                    </c:forEach>
                </ul>
            </div><!-- widget -->

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
