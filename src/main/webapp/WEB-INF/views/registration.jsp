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
            <a class="nav_button" href="/topic">Topics</a>
            <c:if test="${sessionScope.user.firstName==null}">
                <a class="nav_button" href="/login">Login</a>
                <a class="nav_button" href="/registration">Registration</a>
            </c:if>
            <c:if test="${sessionScope.user.firstName!=null}">
                <a class="nav_button" href="/logout">Logout</a>
                <a class="nav_button" href="/">Your page</a>
            </c:if>
        </div>
    </div>
</div>
<form action="/registration" method="post" id="slick-login" class="registration">
    <label for= "firstName">FirstName:</label>
    <input type="text" name="firstName" id="firstName" value="" class="placeholder" placeholder="Firstname">
    <br>
    <label for="lastName">LastName:</label>
    <input type="text" name="lastName" id="lastName" value="" class="placeholder" placeholder="Lastname">
    <br>
    <label for="email">Email:</label>
    <input type="text" name="email" id="email" value="" class="placeholder" placeholder="Email">
    <br>
    <label for="nickName">NickName:</label>
    <input type="text" name="nickName" id="nickName" value="" class="placeholder" placeholder="Nickname">
    <br>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="" class="placeholder" placeholder="Password">
    <input type="submit" value="Sign up" formmethod="post">
</form>
</body>
</html>
