
<%@page import="com.busy.engine.util.Upload"%>
<%@ page import="
java.io.*,
java.util.*,
java.text.*,
javax.servlet.*,
javax.imageio.*,
javax.servlet.http.*, 
com.oreilly.servlet.*,
com.oreilly.servlet.multipart.*,
java.io.IOException" %>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Uploader</title>
    </head>
    <body>
        <h2>You Should not be here!</h2>
    </body>
</html>


<%
int maxFileSize = 30 * 1024 * 1024; // 30MB max
MultipartParser parserFormData = new MultipartParser(request, maxFileSize);

Upload.UploadActivity(parserFormData, "item", "file", getServletContext().getRealPath("/item-files/") );
//response.sendRedirect("items.jsp");
%>


