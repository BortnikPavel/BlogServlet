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
    <link href="../../CSS/stylesheet.css" rel="stylesheet" type="text/css" />
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
<div id="content_container_article">
    <div class="article">
    <c:out value="${article.textArticle}"></c:out>
    </div>
    <c:forEach items="${comments}" var="comments">
    <div class="comment">
        <div class="avtor">Pasha</div>
        <div class="comment-wrapper"><c:out value="${comments.text}"/></div>
    </div>
    </c:forEach>
</div>
</body>
</html>
