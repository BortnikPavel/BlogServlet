<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 25.02.2017
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Blog</title>
    <link href="../../CSS/stylesheet.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="../../CSS/style.css" />
    <script type="text/javascript" src="/JS/placeholder.js"></script>
</head>
<body>
<div id="top_bar_black">
    <div id="logo_container">
        <div id="logo_image">
        </div>
        <div id="nav_block">
            <a class="nav_button" href="/welcomePage">Home</a>
            <a class="nav_button" href="/editProfile">Edit my profile</a>
            <a class="nav_button" href="/userArticles">My articles</a>
            <a class="nav_button" href="/logout">Logout</a>
        </div>
    </div>
</div>
<form action="/editProfile" method="post" id="slick-login" class="registration">
    <input type="hidden" name="oldName" value="${sessionScope.user.nickName}">
    <input type="hidden" name="oldEmail" value="${sessionScope.user.email}">
    <input type="hidden" name="id" value="${sessionScope.user.id}">
    <label for= "firstName">FirstName:</label>
    <input type="text" name="firstName" id="firstName" value="${user.firstName}" class="placeholder" placeholder="Firstname">
    <br>
    <label for="lastName">LastName:</label>
    <input type="text" name="lastName" id="lastName" value="${user.lastName}" class="placeholder" placeholder="Lastname">
    <br>
    <label for="email">Email:</label>
    <input type="text" name="email" id="email" value="${user.email}" class="placeholder" placeholder="Email">
    <br>
    <label for="nickName">NickName:</label>
    <input type="text" name="nickName" id="nickName" value="${user.nickName}" class="placeholder" placeholder="Nickname">
    <br>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="${user.password}" class="placeholder" placeholder="Password">
    <input type="submit" value="Edit" formmethod="post">
</form>
</body>
</html>
