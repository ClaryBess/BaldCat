<%@ page import="com.baldcat.Handler.Handler" %>
<%@ page import="com.baldcat.entity.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/5/27
  Time: 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- custom CSS -->
    <link href="css/main.css" rel="stylesheet" type="text/css" />
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <!-- END custom CSS -->
    <title>主页</title>
</head>
<body>
<!-- Header -->
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
                    <li class="active"><a href="index.jsp">首页</a></li>
                    <li><a href="login.jsp">个人空间</a></li>
                    <li><a href="login.jsp">写博客</a></li>
                    <li><a href="login.jsp">登录/注册</a></li>
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

    <%
        Handler handler=new Handler();
        List<Blog> blogs=handler.blogOrderByLike();
        request.setAttribute("blogs",blogs);
        List<Blog> blogs1=handler.searchByTag("JAVA");
        request.setAttribute("blogs1",blogs1);
        List<Blog> blogs2=handler.searchByTag("系统编程");
        request.setAttribute("blogs2",blogs2);

    %>

</header>
<!-- END header -->
<!-- header slider -->
<div class="slate_gray">
    <div class="container">
        <div class="row header_news_panel">
            <!-- Tab panes -->
            <div class="col-sm-8 tab-content tab-content_mob-p0">
                <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <img src="img/content/slide1.jpg" alt="main img" class="tab-pane__img">
                    <div class="header_news_text tab-pane__block">
                        <p class="tab-pane__category yel_line">${blogs.get(0).tag1}</p>
                        <a class="tab-pane__title" href="post?BlogID=${blogs.get(0).blogID}">${blogs.get(0).title}</a>
                        <p class="tab-pane__text">${blogs.get(0).getAbstarct(30)}</p>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="profile">
                    <img src="img/content/slide2.jpg" alt="main img" class="tab-pane__img">
                    <div class="header_news_text tab-pane__block">
                        <p class="tab-pane__category yel_line">${blogs.get(1).tag1}</p>
                        <a class="tab-pane__title" href="post?BlogID=${blogs.get(1).blogID}">${blogs.get(1).title}</a>
                        <p class="tab-pane__text">${blogs.get(1).getAbstarct(30)}</p>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="messages">
                    <img src="img/content/slide1.jpg" alt="main img" class="tab-pane__img">
                    <div class="header_news_text tab-pane__block">
                        <p class="tab-pane__category yel_line">${blogs.get(2).tag1}</p>
                        <a class="tab-pane__title" href="post?BlogID=${blogs.get(2).blogID}">${blogs.get(2).title}</a>
                        <p class="tab-pane__text">${blogs.get(2).getAbstarct(30)}</p>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="settings">
                    <img src="img/content/slide2.jpg" alt="main img" class="tab-pane__img">
                    <div class="header_news_text tab-pane__block">
                        <p class="tab-pane__category yel_line">${blogs.get(3).tag1}</p>
                        <a class="tab-pane__title" href="post?BlogID=${blogs.get(3).blogID}">${blogs.get(3).title}</a>
                        <p class="tab-pane__text">${blogs.get(3).getAbstarct(30)}</p>
                    </div>
                </div>
            </div>
            <!-- END Tab panes -->
            <!-- Nav tabs -->
            <div class="col-sm-4 news-tabs">
                <p class="news-tabs__title h2">博文推荐</p>
                <ul class="news-tabs__nav nav nav-tabs shadow_text" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#home" role="tab" data-toggle="tab">
                            <span class="time">${blogs.get(0).userName}</span>
                            ${blogs.get(0).title}
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="#profile" role="tab" data-toggle="tab">
                            <span class="time">${blogs.get(1).userName}</span>
                            ${blogs.get(1).title}
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="#messages" role="tab" data-toggle="tab">
                            <span class="time">${blogs.get(2).userName}</span>
                            ${blogs.get(2).title}
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="#settings" role="tab" data-toggle="tab">
                            <span class="time">${blogs.get(3).userName}</span>
                            ${blogs.get(3).title}
                        </a>
                    </li>
                </ul>
            </div>
            <!-- END Nav tabs -->
        </div>
    </div>
</div>
<!-- END header slider -->
<!-- top news-->
<section>
    <!-- top news -->
    <!-- title -->
    <div class="wrap wrap_white">
        <div class="container title">
            <h1 class="title__h1 underscore">热点博文</h1>
        </div>
    </div>
    <!-- END title -->
    <div class="rap wrap_gray pt20">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="thumbnail thumbnail_big">
                        <img src="img/content/news1.jpg" height="350" width="560" alt="News">
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">${blogs.get(4).tag1}</p>
                                <a href="post?BlogID=${blogs.get(4).blogID}" class="news__head">${blogs.get(4).title}</a>
                                <p class="news__desc">${blogs.get(4).getAbstarct(180)}</p>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs.get(4).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs.get(4).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs.get(4).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs.get(5).blogID}" class="thumbnail__link">
                            <img src="img/content/news2.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">${blogs.get(5).tag1}</p>
                                <a href="post?BlogID=${blogs.get(5).blogID}" class="news__link">
                                    <p class="news__text">${blogs.get(5).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs.get(5).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs.get(5).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs.get(5).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs.get(6).blogID}" class="thumbnail__link">
                            <img src="img/content/news3.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">${blogs.get(6).tag1}</p>
                                <a href="post?BlogID=${blogs.get(6).blogID}" class="news__link">
                                    <p class="news__text">${blogs.get(6).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs.get(6).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs.get(6).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs.get(6).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs.get(7).blogID}" class="thumbnail__link">
                            <img src="img/content/news4.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <i class="icon-play"></i>
                                <p class="news__category yellow-line">${blogs.get(7).tag1}</p>
                                <a href="post?BlogID=${blogs.get(7).blogID}" class="news__link">
                                    <p class="news__text">${blogs.get(7).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs.get(7).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs.get(7).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs.get(7).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs.get(8).blogID}" class="thumbnail__link">
                            <img src="img/content/news5.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">${blogs.get(8).tag1}</p>
                                <a href="post?BlogID=${blogs.get(8).blogID}" class="news__link">
                                    <p class="news__text">${blogs.get(8).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs.get(8).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs.get(8).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs.get(8).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        <div class="row"><br></div>
    </div>
    <div class="wrap wrap_white">
        <div class="container title">
            <h1 class="title__h1 underscore">JAVA</h1>
        </div>
    </div>
    <div class="rap wrap_gray pt20">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs1.get(0).blogID}" class="thumbnail__link">
                            <img src="img/content/news6.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <i class="icon-play"></i>
                                <p class="news__category yellow-line">${blogs1.get(0).tag1}</p>
                                <a href="post?BlogID=${blogs1.get(0).blogID}" class="news__link">
                                    <p class="news__text">${blogs1.get(0).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs1.get(0).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs1.get(0).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs1.get(0).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs1.get(1).blogID}" class="thumbnail__link">
                            <img src="img/content/news7.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">${blogs1.get(1).tag1}</p>
                                <a href="post?BlogID=${blogs1.get(1).blogID}" class="news__link">
                                    <p class="news__text">${blogs1.get(1).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs1.get(1).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs1.get(1).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs1.get(1).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs1.get(2).blogID}" class="thumbnail__link">
                            <img src="img/content/news8.jpg" height="153" width="270" alt="News" >
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <i class="icon-play"></i>
                                <p class="news__category yellow-line">${blogs1.get(2).tag1}</p>
                                <a href="post?BlogID=${blogs1.get(2).blogID}" class="news__link">
                                    <p class="news__text">${blogs1.get(2).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs1.get(2).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs1.get(2).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs1.get(2).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="post?BlogID=${blogs1.get(3).blogID}"class="thumbnail__link">
                            <img src="img/content/news9.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">${blogs1.get(3).tag1}</p>
                                <a href="post?BlogID=${blogs1.get(3).blogID}" class="news__link">
                                    <p class="news__text">${blogs1.get(3).title}</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">${blogs1.get(3).userName}</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs1.get(3).commentNumber}
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i></i>❤${blogs1.get(3).likeNumber}
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wrap wrap_white">
            <div class="container title">
                <h1 class="title__h1 underscore">系统编程</h1>
            </div>
        </div>
        <div class="rap wrap_gray pt20">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="thumbnail thumbnail_small">
                            <a href="post?BlogID=${blogs2.get(0).blogID}" class="thumbnail__link">
                                <img src="img/content/news6.jpg" height="153" width="270" alt="News" >
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <i class="icon-play"></i>
                                    <p class="news__category yellow-line">${blogs2.get(0).tag1}</p>
                                    <a href="post?BlogID=${blogs2.get(0).blogID}" class="news__link">
                                        <p class="news__text">${blogs2.get(0).title}</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">${blogs2.get(0).userName}</span>
                                    <ul class="posted__icon">
                                        <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs2.get(0).commentNumber}
                                            </span>
                                        </li>
                                        <li>
                                                <span>
                                                <i></i>❤${blogs2.get(0).likeNumber}
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="thumbnail thumbnail_small">
                            <a href="post?BlogID=${blogs2.get(1).blogID}" class="thumbnail__link">
                                <img src="img/content/news7.jpg" height="153" width="270" alt="News" >
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">${blogs2.get(1).tag1}</p>
                                    <a href="post?BlogID=${blogs2.get(1).blogID}" class="news__link">
                                        <p class="news__text">${blogs2.get(1).title}</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">${blogs2.get(1).userName}</span>
                                    <ul class="posted__icon">
                                        <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs2.get(1).commentNumber}
                                            </span>
                                        </li>
                                        <li>
                                                <span>
                                                <i></i>❤${blogs2.get(1).likeNumber}
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="thumbnail thumbnail_small">
                            <a href="post?BlogID=${blogs2.get(2).blogID}" class="thumbnail__link">
                                <img src="img/content/news8.jpg" height="153" width="270" alt="News" >
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <i class="icon-play"></i>
                                    <p class="news__category yellow-line">${blogs2.get(2).tag1}</p>
                                    <a href="post?BlogID=${blogs2.get(2).blogID}" class="news__link">
                                        <p class="news__text">${blogs2.get(2).title}</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">${blogs2.get(2).userName}</span>
                                    <ul class="posted__icon">
                                        <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs2.get(2).commentNumber}
                                            </span>
                                        </li>
                                        <li>
                                                <span>
                                                <i></i>❤${blogs2.get(2).likeNumber}
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="thumbnail thumbnail_small">
                            <a href="post?BlogID=${blogs2.get(3).blogID}" class="thumbnail__link">
                                <img src="img/content/news9.jpg" height="153" width="270" alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">${blogs2.get(3).tag1}</p>
                                    <a href="post?BlogID=${blogs2.get(3).blogID}" class="news__link">
                                        <p class="news__text">${blogs2.get(3).title}</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">${blogs2.get(3).userName}</span>
                                    <ul class="posted__icon">
                                        <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${blogs2.get(3).commentNumber}
                                            </span>
                                        </li>
                                        <li>
                                                <span>
                                                <i></i>❤${blogs2.get(3).likeNumber}
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    <!-- /container-->
</section>
<!-- /top news -->
<!-- Footer -->
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
<!-- END Footer -->
<!-- All JavaScript libraries -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Custom JavaScript -->
<script src="js/main.js"></script>
</body>
</html>

