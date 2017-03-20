<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.03.2017
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Blog</title>
    <link href="../../../CSS/stylesheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="top_bar_black">
    <div id="logo_container">
        <div id="logo_image">
        </div>
        <div id="nav_block">
            <a class="nav_button" href="/welcomePage">Home</a>
            <a class="nav_button" href="/user/editProfile">Edit profile</a>
            <a class="nav_button" href="/user/userArticles">My articles</a>
            <a class="nav_button" href="/logout">Logout</a>
        </div>
    </div>
</div>
<div id="content_container_article">
    <form action="/user/addNewArticle" method="post" class="article">
        <input type="text" name="title" class="avtor" placeholder="Write title">
        <textarea type="text" id="text"
                  name="text" class="comment_form_wrapper" placeholder="Write text article"></textarea>
        <select name="menu" size="1">
            <c:forEach items="${topics}" var="topics">
            <option value="${topics.name}"><c:out value="${topics.name}"/></option>
            </c:forEach>
        </select>
        <input type="submit" value="Add" class="button25">
    </form>
</div>
</body>
</html>
