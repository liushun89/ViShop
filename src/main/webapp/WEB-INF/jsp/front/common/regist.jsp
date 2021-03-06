<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <title>Regist Page</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="vendor/amazeui/css/amazeui.min.css"/>
    <link rel="stylesheet" href="resource/css/common/regist.css"/>
</head>
<body>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <h3>注册</h3>
        <hr>
        <br>
        <br>

        <form action="/user/regist" method="post" class="am-form">
            <label for="username">用户名:</label>
            <input type="text" name="username" id="username" value="">
            <br>
            <label for="password">密码:</label>
            <input type="password" name="password" id="password" value="">
            <br>
            <label for="password">再次输入密码:</label>
            <input type="password" name="repassword" id="repassword" value="">
            <br>
            <br/>

            <div class="am-cf">
                <input type="submit" name="" value="注 册" class="am-btn am-btn-primary am-btn-sm am-fl">
            </div>
        </form>
        <hr>
    </div>
</div>
</body>
</html>
