<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2021
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Основы JSP</title>
</head>
<body>
    <% int x = 10 ; %>
    x = <b><%= x %></b>
    <br/>
    <% if( x < 20 ) { %>
    <i>Less than 20</i>
    <% } else { %>
    <i>Not Less than 20</i>
    <% } %>
    <br/>
    <jsp:include page="for_fragment.jsp" >
        <jsp:param name="x" value="<%= x %>"/>
    </jsp:include>

</body>
</html>
