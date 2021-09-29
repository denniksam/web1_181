<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2021
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP</title>
</head>
<body>
    <h1>Передача параметров</h1>
    <p>
        Browser(client) -> запрос ->
        Listener(Tomcat) {
            Разбор запроса,
            создание "переменных окружения",
            (request, response)
            запуск приложения
        } -> JVM ->
        Java код {
            анализируем request,
            формируем response
        } -> Результат -> Tomcat -> HTTP
        -> Ответ клиенту
    </p>
    <form method="post" action="?userval1=GET">
        <input name="userval" />
        <input type="submit" value="Отправить"/>
    </form>
    <p>
        На сегодня стандартизированны 9 методов
        запроса:
        CONNECT, GET, POST, PUT, HEAD, DELETE,
        PATCH, OPTIONS, TRACE.
        Передача параметров, традиционно,
        называется "get" и "post", подразумевая
        включение параметров а) в состав URL
        (адресной строки) и б) в тело запроса
        В НТТР запросе параметры могут быть
        как в строке, так и в теле
    </p>

<%
    String userval = request.getParameter( "userval" ) ;
%>
<p>
    Параметр из формы:
    <% if( userval == null ) {  %>
    Не передан
    <% } else { %>
    Передан <%= userval %>
    <% } %>
</p>
<p>
    Данные "get" обязательно формируются по принципу
    ?name1=val1 & name2=val2
    К данным в теле запроса жестких требований нет.
    Но!
    Если пакет содержит тело, то нужно указывать
    заголовок "Content-Type", определяющий как декодировать
    содержимое. Для данных формы это
    "application/x-www-form-urlencoded" - по умолчанию,
    "multipart/form-data" - если есть файлы.
</p>
</body>
</html>
