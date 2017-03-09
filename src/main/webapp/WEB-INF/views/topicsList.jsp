<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 25.02.2017
  Time: 17:48
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

<div id="content_container">
    <div id="header">
        <div class="header_content_mainline">
            Добро пожаловать в наш блог)
        </div>
        <div id="header_content_tagline">
            Какой-то приветственный текст!
        </div>
    </div>
    <div id="header_lower">
        <ol class="pills">
            <c:forEach items="${topics}" var="topic">
                <li><a href="/articles?id=${topic.id}"><c:out value="${topic.name}"></c:out></a></li>
            </c:forEach>
        </ol>
    </div>
</div>


</body>
</html>
