<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<h1>List</h1>
<a href="${pageContext.servletContext.contextPath}/workouts">Тренировки</a>/
<a href="${pageContext.servletContext.contextPath}/adduser">Добавить пользователя</a>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
        <th>Email</th>
    </tr>
<c:forEach items="${userList}" var="users">

    <tr>
    <td><c:out value="${users.id}"></c:out></td>
    <td><c:out value="${users.login}"></c:out></td>
    <td><c:out value="${users.password}"></c:out></td>
    <td><c:out value="${users.role}"></c:out></td>
    <td><c:out value="${users.email}"></c:out></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
