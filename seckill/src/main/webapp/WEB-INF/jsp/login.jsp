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
<body>
    <form action="/seckill/doLogin" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
                <input type="text" name="username" class="form-control" id="username" placeholder="用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密 码</label>
            <div class="col-sm-10">
                <input type="text" name="password" class="form-control" id="password" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">手机号</label>
            <div class="col-sm-10">
                <input type="text" name="phone" class="form-control" id="phone" placeholder="手机号">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox">记住我
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
        </div>
    </form>
    <a class="btn btn-info" href="/seckill/register" target="_blank">免费注册</a>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
