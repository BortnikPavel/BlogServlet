<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 26.02.2017
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/CSS/style.css" />
    <script type="text/javascript" src="/JS/placeholder.js"></script>
</head>
<body>
<form action="/login?action=login" method="post" id="slick-login">
    <label for="login">Login:</label><input type="text" id="login" name="login" class="placeholder" placeholder="admin@example.com">
    <label for="password">Password:</label><input type="password" id="password" name="password" class="placeholder" placeholder="Password">
    <input type="submit" value="Login">
</form>
</body>
</html>