<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/registration" method="post">
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" value="" placeholder="Input">
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="" placeholder="Input">
    <label for="email">E-MAIL:</label>
    <input type="text" name="email" id="email" value="" placeholder="Input">
    <input type="submit" value="Submit" formmethod="post">
</form>
</body>
</html>
