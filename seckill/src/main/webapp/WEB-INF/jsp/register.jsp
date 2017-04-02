//用户登录
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body><!-- pageContext.request.contextPath 绝对路径-->
    <form action="/seckill/doRegister" method="post"  class="form-horizontal" role="form">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
                <input type="text" name="username" class="form-control" id="username" placeholder="填写用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密 码</label>
            <div class="col-sm-10">
                <input type="text" name="password" class="form-control" id="password" placeholder="填写密码">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">手机号</label>
            <div class="col-sm-10">
                <input type="text" name="phone" class="form-control" id="phone" placeholder="填写手机号">
            </div>
        </div>
        <input type="submit" class="btn btn-default">
    </form>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
