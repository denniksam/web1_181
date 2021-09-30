<%--
  Created by IntelliJ IDEA.
  User: samoylenko_d
  Date: 30.09.2021
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <h1>Сервлеты</h1>
    <p>
        Сервлеты - специальные классы (объекты)
        в Java, предназначенные для сетевых задач.
        HttpServlet - аналог ApiController (ASP)
    </p>
    <p>
        В HttpServlet заложена маршрутизация
        по методам запроса. Для реализации мы
        перегружаем методы doGet, doPost, doPut,...
        Маршрутизация по URL (ЧПУ) обеспечивается
        а) аннотацией @WebServlet("Ч/П/У")
        б) записью в web.xml
    </p>
<h2>API</h2>
<button onclick="getClick()">GET</button>
<button onclick="postClick()">POST</button>
<button onclick="putClick()">PUT</button>
<button onclick="deleteClick()">DELETE</button>
    <p id="server-result"></p>

<script src="js/hello_view.js"></script>

</body>
</html>