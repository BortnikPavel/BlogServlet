<%@ page import="com.models.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 26.02.2017
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Blog</title>
    <link href="/CSS/stylesheet.css" rel="stylesheet" type="text/css" />
    <link href="/CSS/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<sec:authentication property="principal" var="user"/>
<div id="top_bar_black">
    <div id="logo_container">
        <div id="logo_image">
        </div>
        <div id="nav_block">
            <a class="nav_button" href="/welcomePage">Home</a>
            <a class="nav_button" href="/topic">Topics</a>
            <c:if test="${user=='guest'}">
                <a class="nav_button" href="/login">Login</a>
                <a class="nav_button" href="/registration">Registration</a>
            </c:if>
            <c:if test="${user=null}">
                <a class="nav_button" href="/logout">Logout</a>
                <a class="nav_button" href="/user/myPage">Your page</a>
            </c:if>
        </div>
    </div>
</div>
<form action="/j_spring_security_check" method="post" id="slick-login">
    <label for="login">Login:</label><input type="text" id="login" name="login" class="placeholder" placeholder="admin@example.com">
    <label for="password">Password:</label><input type="password" id="password" name="password" class="placeholder" placeholder="Password">
    <input type="submit" value="Login">
</form>
</body>
</html>
