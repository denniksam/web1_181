<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2021
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String button = request.getParameter( "button" ) ;
    String loginMessage = "" ;
    if( button != null ) {
        String login = request.getParameter( "login" ) ;
        if( login == null || login.isEmpty() ) {
            loginMessage = "Login shell not be empty";
        }
    }
%>

<html>
<head>
    <title>JSP</title>
</head>
<body>
<p>
Задание: разработать форму для регистрации пользователя
Логин
Пароль
Повтор
Реальное имя

Все проверки на сервере. Если данные не приняты - поставить
уведомление напротив соотв. поля (для примера, логины user1
и user2 считать занятыми)
</p>
<form method="post">
    <div>
        <label>Login:</label>
        <input name="login" />
        <i><%= loginMessage %></i>
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password"/>

    </div>
    <div>
        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword"/>
    </div>
    <div>
        <label>Real name:</label>
        <input name="realName" />
    </div>
    <input type="submit" value="Register" name="button" />
</form>
</body>
</html>
