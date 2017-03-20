<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="nav_button" href="/user/editProfile">Edit my profile</a>
            <a class="nav_button" href="/user/userArticles">My articles</a>
            <a class="nav_button" href="/user/addNewArticle">Add article</a>
            <a class="nav_button" href="/logout">Logout</a>
        </div>
    </div>
</div>
<div id="content_container">
    <div id="header">
        <div class="header_content_mainline">
            Добро пожаловать <c:out value="${user.username} "/>
        </div>
    </div>

    <div id="header_lower">
        <ol class="pills">
            <c:forEach items="${articles}" var="articles">
                <li><a href="/user/userArticle?id=${articles.id}"><c:out value="${articles.title}"/></a></li>
                <a href="/user/deleteArticle?id=${articles.id}" class="button25">delete</a>
            </c:forEach>
        </ol>
    </div>
</div>

</body>
</html>
