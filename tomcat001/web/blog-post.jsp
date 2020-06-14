<%@ page import="com.baldcat.entity.Blog" %><%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/5/27
  Time: 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>博文</title>

    <!-- Styles -->

    <link href="search-css/bootstrap.css" rel="stylesheet" />
    <link href="search-css/style.css" rel="stylesheet" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>



<!-- Masthead
================================================== -->
<header>

    <div class="row">
        <div class="logo">
            <a href="index.jsp"><img src="img/logo.png" alt="" /></a>
        </div><!-- logo -->
        <div class="navigation" >
            <div id="menu" class="fix-fish-menu clearfix">
                <ul id="nav" class="sf-menu" style="margin-left: 100px;">
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="login.jsp">个人空间</a></li>
                    <li> <a href="login.jsp">写博客</a></li>
                    <li> <a href="login.jsp">登录/注册</a></li>
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
<!-- Blog
================================================== -->
<div class="row entry-blog">
    <div class="span9">
        <!-- START POST #1 -->
        <div class="row entry-post">
            <div class="span3">
                <div class="entry-meta clearfix">
                    <span><strong><i class="icon-user"></i> 作者：</strong> <a href="visit?UserID=${blogP.getUserID()}">${blogP.getUserName()}</a></span>
                    <span><strong><i class="icon-comment"></i> 评论：</strong> <a>${blogP.getCommentNumber()}</a></span>
                    <span><strong><i class="icon-heart"></i> 点赞：</strong> <a>${blogP.getLikeNumber()}</a></span>
                    <span><strong><i class="icon-tags"></i> 标签:</strong></span>
                    <ul class="tags clearfix">
                        <c:forEach items="${blogP.tags}" var="tag">
                            <li><a>${tag}</a></li>
                        </c:forEach>
                    </ul>
                </div><!-- entry-meta -->
            </div><!-- span3 -->

            <!-- POST FORMAT - IMAGE -->
            <div class="span6">
                <div class="row">
                    <div class="format-image">
                        <h1 style="margin-left: 20px;">${blogP.getTitle()}</h1>
                        <div class="view thumb-post span5">
                            <div class="post-date-meta">
                                <span class="year">${blogP.year}</span>
                                <span class="date">${blogP.monthDay}</span>
                            </div><!-- post-date-meta -->
                        </div><!-- thumb-post -->
                        <div class="entry-text span9">
                            <p>${blogP.getContent()}</p>
                        </div><!--   -->
                    </div><!-- entryext -->
                </div><!-- span6 -->
            </div><!-- row -->
        </div><!-- span6 -->
    </div><!-- row -->

    <!-- DIVIDER -->
    <div class="row margin40">
        <div class="span3"><div class="border-5-1"></div></div>
        <div class="span6"><div class="border-5-1 hide-border"></div></div>
    </div><!-- row -->
    <!-- END POST #1 -->

    <!-- ============================================================================================= -->


    <!-- COMMENTS  -->
    <div class="row">
        <div class="span3">
            <h3>${blogP.commentNumber} Comments:</h3>
        </div><!-- span3 -->
        <div class="span6">
            <h3 class="text-buffer">Comment Text:</h3>
        </div><!-- span6 -->
    </div><!-- row -->

    <c:forEach items="${comments}" var="blogComment">
    <!-- First Comment -->
    <div class="row comment">
        <div class="span3">
            <div class="well clearfix">
                <div class="user-info">
                    <span class="author"><a href="visit?UserID=${blogComment.getUserID()}">${blogComment.getUserName()}</a></span>
                    <span class="date"><small>${blogComment.getDateTime().toString()}</small></span>
                </div><!-- user-info -->
            </div><!-- well -->
        </div><!-- span3 -->
        <div class="span6">
            <div class="comment-text well">
                <p class="last">${blogComment.getContent()}</p>
            </div><!-- comment-text -->
        </div><!-- span6 -->
    </div><!-- row -->
    </c:forEach>

    <!-- DIVIDER -->
    <div class="row margin10-40">
        <div class="span3"><div class="border-5-1"></div></div>
        <div class="span6"><div class="border-5-1 hide-border"></div></div>
    </div><!-- row -->
    <!-- END COMMENTS -->


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
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/superfish.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.tweet.js"></script>
<script src="js/jflickrfeed.min.js"></script>
<script src="js/jquery.flexslider-min.js"></script>
<script src="js/jquery.fitvid.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/jquery.preloader.js"></script>

<!-- Support CSS3 Media Queries for IE 6-8 -->
<script src="js/respond.min.js"></script>

<script src="js/custom.js"></script>

<!-- For Facebook Social Plugins -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1&appId=297648427000173";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>


<!-- For Images Preloading -->
<script type="text/javascript">var runFancy = true;</script>

<!--[if IE]>
<script type="text/javascript">
    runFancy = false;
</script>
<![endif]-->

<script type="text/javascript">
    if (runFancy) {
        jQuery.noConflict()(function($){
            $(".view").preloader();
        });
    };
</script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

