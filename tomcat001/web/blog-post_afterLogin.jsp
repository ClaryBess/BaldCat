<%@ page import="com.baldcat.Handler.Handler" %>
<%@ page import="com.baldcat.entity.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="com.baldcat.entity.BlogComment" %>
<%@ page import="com.baldcat.entity.User" %>
<<%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/5/27
  Time: 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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

        // function SendMsg() {
        //         var getcomment = jQuery('textarea').val();
        //     jQuery.ajax({
        //         //几个参数需要注意一下
        //         type: "POST",//方法类型
        //         dataType: "json",//预期服务器返回的数据类型
        //         // url: "../../src/com/baldact/controller/AddCommentLServlet/commentL" ,//url
        //         url:"/commentL",
        //         data: {'getcomms':getcomment},
        //         success: function (result) {
        //             console.log(result);//打印服务端返回的数据(调试用)
        //             if (result.resultCode == 200) {
        //                 alert("SUCCESS");
        //             }
        //             alert(data);
        //             ;
        //         },
        //         error : function() {
        //             alert("异常！");
        //         }
        //     });
        // }
    </script>
    <%
        Handler handler=new Handler();
        User user=(User) session.getAttribute("user");
        Blog blogP=(Blog) session.getAttribute("blogP");
        boolean hasLike=handler.hasLike(blogP.getBlogID(),user.getUserID());
        request.setAttribute("has",hasLike);
    %>

</header>
<!-- Blog
================================================== -->
<div class="row entry-blog">
    <div class="span9">
        <!-- START POST #1 -->
        <div class="row entry-post">
            <div class="span3">
                <div class="entry-meta clearfix">
                    <span><strong><i class="icon-user"></i> 作者：</strong> <a href="visitL?UserID=${blogP.getUserID()}">${blogP.getUserName()}</a></span>
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
                            <img src="${blogP.getPath()}" alt="" />
                            <p>${blogP.getContent()}</p>
                        </div><!--   -->
                    </div><!-- entryext -->
                </div><!-- span6 -->
            </div><!-- row -->
        </div><!-- span6 -->
    </div><!-- row -->
    <div class="row">
        <c:if test="${!has}">
            <button type="submit" class="btn like-btn"><a href="like">❤给此博文点赞</a></button>
        </c:if>
        <c:if test="${has}">
            <button class="btn like-btn"><a>❤已点赞</a></button>
        </c:if>
    </div>
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
                    <span class="author"><a href="visitL?UserID=${blogComment.getUserID()}">${blogComment.getUserName()}</a></span>
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

    <div class="row">
        <div class="comment">
            <h3>发表评论：</h3>
        </div>

    </div><!-- row -->

    <!-- Replay Form -->
    <div class="row margin0-40">
        <form  action="/commentL" method="post" id="comment-form" class="clearfix">
            <textarea id="comment" name="comment" rows="5"></textarea>
            <button type="submit" class="btn" onclick="SendMsg()">提交评论</button>
        </form>
    </div><!-- row -->

</div><!-- span9 -->
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
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.min.js"></script>
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
