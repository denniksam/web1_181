<%--
  Created by IntelliJ IDEA.
  User: samoylenko_d
  Date: 04.10.2021
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Gallery</title>
</head>
<body>
    <form method="post"
          enctype="multipart/form-data"
          action="" >

        <input type="file" name="galleryfile" />
        <br/>
        <label>Description:
        <textarea name="galleryDescription">Новое изображение</textarea></label>
        <input type="submit" value="Send" />
    </form>
<%  String uploadMessage = (String)
        request.getAttribute( "uploadMessage" ) ;
    if( uploadMessage != null ) { %>

   <b><%= uploadMessage %></b>

<% } %>

</body>
</html>
