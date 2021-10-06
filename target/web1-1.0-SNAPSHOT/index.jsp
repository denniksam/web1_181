<%@ page import="step.java.web1.util.Db" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Основы веб-разработки</h1>
<p>
    Особенностью веб-приложений является, то, что в ней выделяются
    два "участника": сервер и клиент. Сервер (как роль) - ПО,
    принимающее запрос и выдающее ответ. Клиент - инициатор
    общения, ПО, отправляющее запрос. Для того чтобы разгрузить
    слова "клиент" и "сервер" в стандартах используются термины
    "caller" и "callee".
</p>
<p>
    По "перекосу" на какой стороне основная активность, веб-приложения
    можно поделить на:
    <ul>
        <li>
            Статические - вся активность на клиенте, сервер только
            хранит файлы, которые передаются один-к-одному
        </li>
        <li>
            С серверной активностью (Server Pages): ASP, JSP, PHP -
            основная логика, трудоемкость выполняется на сервере,
            на клиент передается готовый результат, обычно, в HTML
        </li>
        <li>
            Со сбалансированной активностью - взаимодействие с пользователем
            и первичная обработка данных на клиенте, работа с БД,
            окончательная обработка - на сервере. SPA <-> ASP
        </li>
        <li>
            PWA - Progressive Web-Application: распределенные системы
            с несколькими клиентами - мобильное приложение, десктоп, веб-.
            Особую роль отводят API (Application-Program Interface)
            Program -- Server (информационный центр);
            Application(s) -- Client(s);
            Interface (API) -- протокол обмена данными.
        </li>
    </ul>
</p>
<hr/>
<p>
    Эволюция серверных технологий происходила от недостатков HTML.
    Идея РНР (до-обработка HTML) заключалась в
    <ul><li>переменные \ массивы
        <li>выражения \ вычисления
        <li>условные операторы
        <li>цикловые конструкции
        <li>разбиение на файлы\фрагменты</li></ul>
    Решение - "активные" вставки в HTML, подключающие возможности
    другого языка программирования. В JSP это
    <br/>&lt;% инструкции Java %&gt;
    <br/>&lt;%= выражение %&gt; (сокращенная форма вывода)
</p>
<!-- Комментарий, остающийся в HTML -->
<%
    String str = "The string";
%>
<br/>
<%-- Комментарий в JSP, не попадает в HTML --%>
<%= str %>
<br/>
<a href="basics.jsp">Основы JSP</a>
<br/>
<a href="params.jsp">Передача параметров</a>
<br/>
<a href="form.jsp">Форма</a>
<br/><br/>
<a href="hello-servlet">Hello Servlet</a>

<%= Db.getConnection() %>
<% Db.createGallery(); %>
</body>
</html>
