<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 26.02.2017
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
    <h1>Articles List</h1>
    <table border="1">
        <c:forEach items="${articles}" var="articles">
            <tr>
                <td><c:out value="${articles.title}"></c:out></td>
                <td><c:out value="${articles.datePublication}"></c:out></td>
                <td><c:out value="${articles.user.nickName}"></c:out></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
