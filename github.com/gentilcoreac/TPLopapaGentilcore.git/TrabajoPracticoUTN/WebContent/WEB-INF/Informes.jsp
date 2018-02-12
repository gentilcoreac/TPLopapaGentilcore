<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html lang="en">
<head>
	<title><%=request.getAttribute("Titulo")%></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/informes.css">
    <link rel="icon" href="imagenes/bookingsFavicom.ico" />        
  	<script src="scripts/jquery.min.js"></script>
  	<script src="scripts/bootstrap.min.js"></script>
    
	<script type="text/javascript">
		function goBack(){
			window.history.go(-1);
			return false;
		}
	</script>
  </head>
<body>
	<%
	 if(session.getAttribute("user")==null && request.getAttribute("loginError")==null){
		response.sendRedirect("Login.html");
	} 
	switch(request.getParameter("tipo").toLowerCase()){
		case "info":
		%>
		<div class="alert alert-info">
		 	 <%=request.getAttribute("Mensaje")%>
		</div>
		<br>
		<form action="javascript:goBack();" method="post">  
		<button class="btn btn-default" type="submit" >Volver</button>
        </form>
		<br>
		<img  src="imagenes/404.gif"  alt="imagen info"></img>
		<% 
		break;
		case "error":%>
		<div class="alert alert-danger">
		  	<strong>Error:</strong> <pre><%=request.getAttribute("Mensaje")%></pre>
		</div>
		<br>
		<form action="javascript:goBack();" method="post">
		<button class="btn btn-default" type="submit">Volver</button>
		</form>
		<br>
		<img  src="imagenes/404.gif"  alt="imagen error"></img>
		<% 
		break;
		case "exito":
		%>
		<div class="alert alert-success">
		 	 <%=request.getAttribute("Mensaje")%>
		</div>
		<br>
		<form action=<%=request.getAttribute("urlvolver") %> method="post">
		<button class="btn btn-default" type="submit">Ir al Listado</button>
		</form>
		<%
		break;
	}
	%>

</body>
</html>