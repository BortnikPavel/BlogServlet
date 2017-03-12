<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="nav_button" href="/editProfile">Edit my profile</a>
            <a class="nav_button" href="/userArticles">My articles</a>
            <a class="nav_button" href="/logout">Logout</a>
        </div>
    </div>
</div>
<div id="content_container">
    <div id="header">
        <div class="header_content_mainline">
            Добро пожаловать <c:out value="${sessionScope.user.firstName} "/>
        </div>
        <div id="header_content_tagline">
            <c:out value="${sessionScope.user.lastName}"/><br>
            <c:out value="${sessionScope.user.email}"/><br>
            <c:out value="${sessionScope.user.nickName}"/><br>
        </div>
    </div>

    <div id="header_lower">
        <ol class="pills">
            <c:forEach items="${articles}" var="articles">
                <li><a href="/userArticle?id=${articles.id}"><c:out value="${articles.title}"/></a></li>
                <a href="/deleteArticle?id=${articles.id}" class="button25">delete</a>
            </c:forEach>
        </ol>
    </div>
</div>

</body>
</html>
