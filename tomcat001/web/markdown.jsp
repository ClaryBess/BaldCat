<%--
  Created by IntelliJ IDEA.
  User: 祖泊宁
  Date: 2020/5/27
  Time: 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>写博客</title>
    <script src="showdown-master/dist/showdown.min.js"></script>
    <link href="search-css/style.css" rel='stylesheet' type='text/css' media="all" />
    <link href="search-css/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
    .markdown-here-wrapper {
        font-size: 16px;
        line-height: 1.8em;
        letter-spacing: 0.1em;
    }

    #area pre,
    code {
        font-size: 14px;
        font-family: Roboto, 'Courier New', Consolas, Inconsolata, Courier, monospace;
        margin: auto 5px;
    }

    #area code {
        white-space: pre-wrap;
        border-radius: 2px;
        display: inline;
    }

    #area pre {
        font-size: 15px;
        line-height: 1.4em;
        display: block;
    !important;
    }

    #area pre code {
        white-space: pre;
        overflow: auto;
        border-radius: 3px;
        padding: 1px 1px;
        display: block !important;
    }

    #area strong,
    b {
        color: #BF360C;
    }

    #area em,
    i {
        color: #009688;
    }

    #area hr {
        border: 1px solid #BF360C;
        margin: 1.5em auto;
    }

    #area p {
        margin: 1.5em 5px !important;
    }

    #area table,
    pre,
    dl,
    blockquote,
    q,
    ul,
    ol {
        margin: 10px 5px;
    }

    #area ul,
    ol {
        padding-left: 15px;
    }

    #area li {
        margin: 10px;
    }

    #area li p {
        margin: 10px 0 !important;
    }

    #area ul ul,
    ul ol,
    ol ul,
    ol ol {
        margin: 0;
        padding-left: 10px;
    }

    #area ul {
        list-style-type: circle;
    }

    #area dl {
        padding: 0;
    }

    #area dl dt {
        font-size: 1em;
        font-weight: bold;
        font-style: italic;
    }

    #area dl dd {
        margin: 0 0 10px;
        padding: 0 10px;
    }

    #area blockquote,
    q {
        border-left: 2px solid black;
        padding: 0 10px;
        color: #7F7F7F;
        quotes: none;
        margin-left: 1em;
    }

    #area blockquote::before,
    blockquote::after,
    q::before,
    q::after {
        content: none;
    }

    #area h1,
    #area h2,
    #area h3,
    h4,
    h5,
    h6 {
        margin: 20px 0 10px;
        padding: 0;
        /*font-style: bold !important;*/
        color: black !important;
        text-align: center !important;
        margin: 1.5em 5px !important;
        padding: 0.5em 1em !important;
    }

    #area h1 {
        font-size: 24px !important;
        border-bottom: 1px solid #ddd !important;
    }

    #area h2 {
        font-size: 20px !important;
        border-bottom: 1px solid #eee !important;
    }

    #area h3 {
        font-size: 18px;
    }

    #area h4 {
        font-size: 16px;
    }


    #area table {
        padding: 0;
        border-collapse: collapse;
        border-spacing: 0;
        font-size: 1em;
        font: inherit;
        border: 0;
        margin: 0 auto;
    }

    #area tbody {
        margin: 0;
        padding: 0;
        border: 0;
    }

    #area table tr {
        border: 0;
        border-top: 1px solid #CCC;
        background-color: white;
        margin: 0;
        padding: 0;
    }

    #area table tr:nth-child(2n) {
        background-color: #F8F8F8;
    }

    #area table tr th,
    #area table tr td {
        font-size: 16px;
        border: 1px solid #CCC;
        margin: 0;
        padding: 5px 10px;
    }

    #area table tr th {
        font-weight: bold;
        color: #eee;
        border: 1px solid #009688;
        background-color: #009688;
    }
    #area>table {
        width: 100%;
        table-layout: fixed;
    }

    #area table tr td {
        margin: 0;
        padding: 6px;
        border: 0;

    }


    #md-area {

        width: 100%;
        height: 600px;
        font-size: 18px;
        overflow: hidden;
        font-weight: bold;
        outline: none;
    }

    #show-area {
        height: 610px;
        background-color: #FCF6E5;
        margin-top: -7px;
    }

    .clearfix:before {
        content: "";
        display: table;
    }
    .blog-container{
        width: 90%;
        background: #EFEFEF;
        padding: 30px 0;
        margin: 0 auto;
        border-radius: 4px;
    }
    .blog-button-row{
        float:right;
        width: 100%;
        margin: 10px 20px 20px 0;
    }
    .blog-button-row .submit-blog{
        padding:7px;
        background-color:#DA7B55;
        width:80px;
        border:none;
        cursor:pointer;
        color:#fff;
        font-weight: 300;
        font-family: 'Nunito', sans-serif;
        font-size:18px;
        margin-right: 3%;
        float:right;
        text-transform:uppercase;
        border-radius:4px;
        transition: all 0.5s ease-in-out;
        -webkit-transition: all 0.5s ease-in-out;
        -moz-transition: all 0.5s ease-in-out;
        -o-transition: all 0.5s ease-in-out;
    }
    .blog-button-row .submit-blog:hover {
        background-color:#F15A23;
        color:#fff;
    }
    .blog-button-row .reset-blog {
        padding:7px;
        background-color:#585858;
        width:80px;
        border:none;
        /*-- AgileITS --*/
        cursor:pointer;
        color:#fff;
        font-size:18px;
        float:right;
        margin-right:30px;
        font-weight: 300;
        font-family: 'Nunito', sans-serif;
        text-transform:uppercase;
        border-radius:4px;
        transition: all 0.5s ease-in-out;
        -webkit-transition: all 0.5s ease-in-out;
        -moz-transition: all 0.5s ease-in-out;
        -o-transition: all 0.5s ease-in-out;
    }
    .blog-button-row .reset-blog:hover {
        background-color:#3A3A3A;
        color:#fff;
    }
    #label_file{
        font-size: 14px;
        color: #fff;
        border-radius: 5px;
        height: 30px;
        display: inline;
        padding: 5px 10px;
        margin-left: 10px;
        background: #303135;
        font-weight: normal;
        cursor: pointer;
    }
</style>

<body>
<header>

    <!-- Navbar
    ================================================== -->
    <div class="row">
        <div class="logo">
            <a href="index.jsp"><img src="img/logo.png" alt="" /></a>
        </div><!-- logo -->
        <div class="navigation">
            <div id="menu" class="fix-fish-menu clearfix">
                <ul id="nav" class="sf-menu" style="margin-left: 30px;">
                    <li><a href="index_afterLogin.jsp">首页</a></li>
                    <li><a href="profile_self.jsp">个人空间</a></li>
                    <li class="active"> <a href="#">写博客</a></li>
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
<div class="blog-container">
    <div class="row" style="margin-left: 40px; ">
        <h1 style="float:left;">🖊编写你的博文</h1>
    </div>
    <form class="blog-write" method="post" id="addBlogForm" enctype="multipart/form-data" action="/addBlog">
        <div class="row non-md" style="margin: 40px 0 5px 20%; ">
            <p>博文标题：</p>
<%--            <input type="text" name="title" value="博文标题" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '博文标题';}">--%>
            <input type="text" name="title" placeholder="博文标题">

        </div>
        <div class="row non-md" style="margin: 20px 0 25px 20%;">
            <p>博文标签：</p>
            <input type="text" name="Tag1" placeholder="JAVA;算法;数据库（最多添加5个）">
        </div>
        <%--<div id="area" style="width: 92%; margin: 0 auto;">
            <table>
                <tr>
                    <td width="100%">
                        <label>
                            <textarea name="" id="md-area" onkeyup=mdSwitch()></textarea>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><input type="file"  name="file"></td>
                    <td></td>
                </tr>
            </table>
        </div>--%>

        <div id="area" style="width: 100%; margin: 0 0 0 0;">
            <%--<div style="margin-left: 4%;height: auto;padding-bottom: 20px">
                <span>摘要图片：</span>
                <img src="" id="imageview" class="item1_img" style="display: none;" >
                <label for="fileupload" id="label_file">上传文件
                </label>
                <input type="file" name="upload" id="fileupload" style="display: none"/>
            </div>--%>
            <div id="editor" name="content" style="width:96%;min-height: 400px;margin-left: 4%"></div>
        </div>

        <div class="blog-button-row">
            <input type="button" class="submit-blog" id="submitButton" value="提交">
            <input type="reset" class="reset-blog" value="清空" onclick="window.location.reload();">
        </div>

<%--        <input type="hidden" name="Content" id="content">--%>
    </form>
</div>

<div class="wrap wrap_white">
    <div class="container title">
        <h2> <br/></h2>
    </div>
</div>
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

<script src="js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/umedit/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/umedit/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/umedit/lang/zh-cn/zh-cn.js"></script>
<script>
    function mdSwitch() {
        var mdValue = document.getElementById("md-area").value;
        var converter = new showdown.Converter();
        var html = converter.makeHtml(mdValue);
        document.getElementById("show-area").innerHTML = html;
    };

    var ue = UE.getEditor('editor');

    $(function () {
        $("#submitButton").click(function () {
            //设置文本的描述
            //获取富文本正文
           /* var text = ue.getContent();
            text = text.substring(0,150)+"...";
            //设置描述
            $('#content').val(text);*/
            // 提交表单
            $("#addBlogForm").submit();
        });


        /*原理是把本地图片路径："D(盘符):/image/..."转为"http://..."格式路径来进行显示图片*/
        $("#fileupload").change(function() {
            var $file = $(this);
            var objUrl = $file[0].files[0];
            //获得一个http格式的url路径:mozilla(firefox)||webkit or chrome
            var windowURL = window.URL || window.webkitURL;
            //createObjectURL创建一个指向该参数对象(图片)的URL
            var dataURL;
            dataURL = windowURL.createObjectURL(objUrl);
            $("#imageview").attr("src",dataURL);
            console.log($('#imageview').attr('style'));
            if($('#imageview').attr('style') === 'display: none;'){
                $('#imageview').attr('style','inline');
                $('#imageview').width("300px");
                $('#imageview').height("180px");
                $('.update_pic').attr('style', 'margin-bottom: 150px;');
            }
        });
    })
</script>

</body>
</html>

