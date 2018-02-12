<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Bienvenido a MyReserva</title>
</head>
<body>
 
 <%

 response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
 response.addHeader("Pragma", "no-cache"); 
 response.addDateHeader ("Expires", 0); 
 response.sendRedirect("Login.html"); 

  %>


</body>
</html>