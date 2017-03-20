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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Blog</title>
    <link href="../../../CSS/stylesheet.css" rel="stylesheet" type="text/css" />
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
            <a class="nav_button" href="/logout">Logout</a>
            <a class="nav_button" href="/user/myPage">Your page</a>
        </div>
    </div>
</div>
<div id="content_container_article">
    <div class="article">
        <div class="avtor"><c:out value="${article.title}"/></div>
        <c:out value="${article.textArticle}"></c:out>
    </div>
    <c:forEach items="${comments}" var="comments">
        <div class="comment">
            <div class="avtor">Pasha</div>
            <div class="comment-wrapper"><c:out value="${comments.text}"/></div>
        </div>
    </c:forEach>
    <c:if test="${user!=null}">
        <form action="/user/commentAdd" method="post" class="comment_form">
            <input type="hidden" name="articleId" value="${article.id}">
            <label for="comment"></label>
            <input type="text" id="comment"
                   name="comment" class="comment_form_wrapper">
            <input type="submit" value="Add">
        </form>
    </c:if>
</div>
</body>
</html>
