<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html lang="en">
<head>
	<title><%=request.getAttribute("Titulo")%></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
 	<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/informes.css">
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