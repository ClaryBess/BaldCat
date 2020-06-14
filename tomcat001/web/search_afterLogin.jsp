<%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/5/27
  Time: 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>搜索</title>

    <!-- Styles -->

    <link href="search-css/bootstrap.css" rel="stylesheet" />
    <link href="search-css/style.css" rel="stylesheet" />

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
                <a href="index.jsp"><img src="img/logo.png" alt="" /></a>
            </div><!-- logo -->
            <div class="navigation">
                <div id="menu" class="fix-fish-menu clearfix">
                    <ul id="nav" class="sf-menu" style="margin-left: 30px;">
                        <li><a href="index_afterLogin.jsp">首页</a></li>
                        <li><a href="profile_self.jsp">个人空间</a></li>
                        <li> <a href="markdown.jsp">写博客</a></li>
                        <li> <a href="info.jsp">个人信息</a></li>
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
        <div class="span9">

            <c:forEach items="${blogs}" var="blog">
            <!-- START POST #1 -->
            <div class="row">
                <div class="span3">
                    <div class="entry-meta clearfix">
                        <span><strong><i class="icon-user"></i> 作者：</strong> <a href="visitL?UserID=${blog.getUserID()}">${blog.userName}</a></span>
                        <span><strong><i class="icon-comment"></i> 评论：</strong> <a>${blog.getCommentNumber()}</a></span>
                        <span><strong><i class="icon-heart"></i> 点赞：</strong> <a>${blog.getLikeNumber()}</a></span>
                        <span><strong><i class="icon-tags"></i> 标签:</strong></span>
                        <ul class="tags clearfix">
                            <c:forEach items="${blog.tags}" var="tag">
                                <li><a>${tag}</a></li>
                            </c:forEach>
                        </ul>
                    </div><!-- entry-meta -->
                </div><!-- span3 -->

                <!-- POST FORMAT - IMAGE -->
                <div class="span6">
                    <div class="row">
                        <div class="span9 format-image">
                            <div class="span9 entry-text">
                                <h3><a href="blog-post.jsp">${blog.getTitle()}</a></h3>
                                <p style="display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical;overflow:hidden;"> ${blog.getAbstarct(300)}</p>
                                <a class="btn" href="postL?BlogID=${blog.blogID}">阅读全文</a>
                            </div><!-- entry-text -->
                        </div><!-- span6 -->
                    </div><!-- row -->
                </div><!-- span6 -->
            </div><!-- row -->

            <!-- DIVIDER -->
            <div class="row margin40">
                <div class="span3"><div class="border-5-1"></div></div>
                <div class="span6"><div class="border-5-1 hide-border"></div></div>
            </div><!-- row -->
            <!-- END POST #1 --></c:forEach>

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
