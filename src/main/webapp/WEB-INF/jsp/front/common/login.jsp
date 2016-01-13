<%--
  Created by IntelliJ IDEA.
  User: Homiss
  Date: 2016/1/12
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
  用户登录
  <form action="/user/login" method="POST">
    <input type="text" name="username" value="Homiss" />
    <input type="password" name="password" value="123456" />
    <input type="submit" value="Submit" >
  </form>
</body>
</html>
