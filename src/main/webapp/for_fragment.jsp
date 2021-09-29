<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.09.2021
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul>
    <% int x = 10 ; for( int i = 0; i < x; ++i ) { %>
    <li>Item <%= i + 1 %></li>
    <% } %>
</ul>