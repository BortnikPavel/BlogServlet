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
    <title>Title</title>
</head>
<body>
<h1>Topic List</h1>
<table border="1">
    <c:forEach items="${topics}" var="topic">
        <tr>
            <td><a href="/articles?id=${topic.id}"><c:out value="${topic.name}"></c:out></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
