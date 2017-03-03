<%--
  Created by IntelliJ IDEA.
  User: Olga
  Date: 27.02.2017
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8" />
  <title>Главная</title>
</head>
<body>
<p>Текущий пользователь: ${username}</p>
<p><a href="${pageContext.servletContext.contextPath}/registration">Регистрация</a></p>
<p><a href="${pageContext.servletContext.contextPath}/login">Вход</a></p>
<p><a href="${pageContext.servletContext.contextPath}/logout">Выход</a></p>
<%--<p><a href="${pageContext.servletContext.contextPath}/edit-user">Редактирование профиля</a></p>--%>
<p><a href="${pageContext.servletContext.contextPath}/workouts">Тренировки</a></p>
</body>
</html>
