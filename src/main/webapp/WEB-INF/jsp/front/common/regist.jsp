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
    <title>用户注册</title>
</head>
<body>

  <form action="/user/regist" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="password" name="repassword">
    <input type="submit" value="Submit" />
  </form>
</body>
</html>
