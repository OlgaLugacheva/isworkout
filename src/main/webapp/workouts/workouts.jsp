
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Workouts</title>
</head>
<body>
<h1>WORKOUTS</h1>
<a href="/workouts/addworkout?id=0">Добавить</a>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>Id</th>
        <th>Id_exersise</th>
        <th>Date</th>
        <th>Duration</th>
        <th>Intensity</th>
    </tr>
<c:forEach items="${workouts}" var="workout">

    <tr>
        <td><c:out value="${workout.id}"></c:out></td>
        <td><c:out value="${workout.id_exersise}"></c:out></td>
        <td><c:out value="${workout.date}"></c:out></td>
        <td><c:out value="${workout.duration}"></c:out></td>
        <td><c:out value="${workout.intensity}"></c:out></td>

        <%--<td><a href="/workouts/addworkout?id=${lection.id}">edit</a>--%>
            <%--/--%>
            <%--<a href="/workouts/deleteworkout?id=${lection.id}">del</a></td>--%>
    </tr>
</c:forEach>
</table>
</body>
</html>
