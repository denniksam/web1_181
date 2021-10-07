<%@ page import="step.java.web1.models.Picture" %>
<%@ page import="step.java.web1.util.Db" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: samoylenko_d
  Date: 04.10.2021
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/gallery.css" />
    <title>Gallery</title>
</head>
<body>
    <h1>Галерея</h1>
    <%  Object pics = request.getAttribute( "pictures" ) ;
        ArrayList<Picture> pictures = null ;
        if( pics instanceof ArrayList )
            pictures = (ArrayList<Picture>) pics ;

        if( pictures != null )
        for( Picture pic : pictures ) { %>
    <div class="picture">
        <img src="uploads/<%= pic.getName() %>" />
        <p><%= pic.getDescription() %></p>
        <i><%= pic.getMoment() %></i>
        <tt><%= pic.getId() %></tt>
    </div>
    <% } %>
    <form method="post"
          enctype="multipart/form-data"
          action="" >

        <input type="file" name="galleryfile" />
        <br/>
        <label> Description:
                <textarea name="galleryDescription">Новое изображение</textarea>
        </label>
        <input type="submit" value="Send" />
    </form>
<%  String uploadMessage = (String)
        request.getAttribute( "uploadMessage" ) ;
    if( uploadMessage != null ) { %>

   <b><%= uploadMessage %></b>

<% } %>

</body>
</html>
