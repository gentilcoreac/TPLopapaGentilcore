<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="business.entities.Persona" %>
<!DOCTYPE html >
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEycHgiIGhlaWdodD0iNTEycHgiPgo8ZyB0cmFuc2Zvcm09Im1hdHJpeCgxLjI1IDAgMCAtMS4yNSAwIDQ1KSI+Cgk8Zz4KCQk8Zz4KCQkJPHBhdGggc3R5bGU9ImZpbGw6IzNCODhDMzsiIGQ9Ik00MDkuNi0zMjguMDg5YzAtMjUuMTM0LTIwLjM3OC00NS41MTEtNDUuNTExLTQ1LjUxMUg0NS41MTFDMjAuMzc4LTM3My42LDAtMzUzLjIyMiwwLTMyOC4wODkgICAgIFYtOS41MTFDMCwxNS42MjIsMjAuMzc4LDM2LDQ1LjUxMSwzNmgzMTguNTc4QzM4OS4yMjIsMzYsNDA5LjYsMTUuNjIyLDQwOS42LTkuNTExVi0zMjguMDg5eiIvPgoJCQk8cGF0aCBzdHlsZT0iZmlsbDojRkZGRkZGOyIgZD0iTTE3NS41MzYtMTUzLjg5NWgzNi4zMjljMTkuMzk5LDAsMzIuODI1LDExLjYzOSwzMi44MjUsMzEuMzkxICAgICBjMCwyMC4xMDUtMTMuNDI2LDMxLjA1LTMyLjgyNSwzMS4wNWgtMzYuMzI5Vi0xNTMuODk1eiBNMTIyLjYxOC03MC4yOTFjMCwxNi45Myw5LjUyMywyNy41MjMsMjcuMTU5LDI3LjUyM2g2Mi4wODkgICAgIGM1NC4zMjksMCw4Ny44NDgtMjQuMzQ4LDg3Ljg0OC03OS43MzVjMC0zOC43OTgtMjkuMjg2LTYxLjAzLTY1LjI3NC02Ni42NzRsNTkuOTg0LTY0LjIwNSAgICAgYzQuOTI3LTUuMjkxLDcuMDQzLTEwLjU4MSw3LjA0My0xNS41MTljMC0xMy43NTYtMTAuOTM0LTI3LjE1OS0yNi40NDItMjcuMTU5Yy02LjM2LDAtMTQuODI1LDIuNDY5LTIwLjQ2OSw5LjE3bC03OC4zMjUsOTQuODkxICAgICBoLTAuNjk0di03Ni41NWMwLTE3LjY0Ny0xMS4yODctMjcuNTExLTI2LjQ2NS0yNy41MTFjLTE1LjE2NywwLTI2LjQ1Myw5Ljg2NS0yNi40NTMsMjcuNTExICAgICBDMTIyLjYxOC0yNjguNTQ5LDEyMi42MTgtNzAuMjkxLDEyMi42MTgtNzAuMjkxeiIvPgoJCTwvZz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/formulario.css" >
  <link rel="stylesheet" href="css/listado.css">
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/formulario.js"></script>

<title>Formulario</title>
</head>
<body>

<%
if(session.getAttribute("user")==null){
	response.sendRedirect("Login.html");
}
%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Start">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
        <li >
		    <a href="ServletListaReservas"><span class="glyphicon glyphicon-calendar"></span>Reservas</a>
		</li>    
        <li >
          <a href="ServletListaElementos"><span class="glyphicon glyphicon-th-large"></span>Elementos</a>
        </li>
    <%	Persona per=((Persona)request.getSession().getAttribute("user"));
 		  String categoria=per.getCategoria().getDescripcion();
    	if(categoria.equals("Administrador")){ %>
		<li >
          <a href="ServletListaUsuarios"><span class="glyphicon glyphicon-phone"></span>Usuarios</a>
        </li>
    <%}%>    
        <li >
          <a href="ServletListaTiposDeElementos"><span class="glyphicon glyphicon-list"></span>Tipos de Elementos</a>
        </li>
        
	
    </ul>

    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
          <a href="#usudesplegable" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false"><span class="glyphicon glyphicon-user"></span><%=" "+per.getNombre()+" "+per.getApellido()%><span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="ServletPerfil" >Perfil</a></li>
            <li class="divider"></li>            
            <li><a href="ServletLogout">Salir</a>  
            </li>
            	
          </ul>
        </li>
    </ul>
  </div>
</nav>

<br><br><br>
<%
switch(request.getParameter("accion")){
	
case "alta":%>
<div class="container-form">
  <h2>Agregar Tipo De Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post" autocomplete="off"> 
  	

  	<div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required >
    </div>

    <div class="form-group">
      <label for="inputcant_max_res_pen">Max Reservas Pendientes:</label>
      <input name="cant_max_res_pen" type="number" min="1" value="1" class="form-control" id="inputcant_max_res_pen" required  oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}"  >
    </div>
    <div class="form-group">
      <label for="inputlimite_horas_res">Max Horas de Reserva:</label>
      <input name="limite_horas_res" type="number" min="1" value="1" class="form-control" id="inputlimite_horas_res"  required  oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
    <div class="form-group">
      <label for="inputdias_max_anticipacion">Max Dias de Anticipacion:</label>
      <input name="dias_max_anticipacion" type="number" min="1" value="1" class="form-control" id="inputdias_max_anticipacion" required   oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
	<div class="checkbox">
	  <input name="only_encargados" type="checkbox"  id="inputonly_encargados"><b>Restringido a Encargados?</b></input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=alta')">Agregar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "modificacion":
%>
<div class="container-form">
  <h2>Modificar Tipo De Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputnombre">Nombre:</label>
      	  <input name="nombre" type="text" class="form-control" id="inputnombre"  required value="<%=request.getAttribute("nombre")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=consulta&fin=modificacion')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputcant_max_res_pen">Max Reservas Pendientes:</label>
      <input name="cant_max_res_pen"  type="number" min="1" class="form-control" id="inputcant_max_res_pen" required oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}"  value="<%=request.getAttribute("cant_max_res_pen") %>" >
    </div>
    <div class="form-group">
      <label for="inputlimite_horas_res">Max Horas de Reserva:</label>
      <input name="limite_horas_res" type="number" min="1"  class="form-control" id="inputlimite_horas_res"  required oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}"  value="<%=request.getAttribute("limite_horas_res") %>" >
    </div>
    <div class="form-group">
      <label for="inputdias_max_anticipacion">Max Dias de Anticipacion:</label>
      <input name="dias_max_anticipacion"  type="number" min="1"  class="form-control" id="inputdias_max_anticipacion" required oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("dias_max_anticipacion") %>" >
    </div>
	<div class="checkbox">
	  <input name="only_encargados" type="checkbox" value=<%=request.getAttribute("only_encargados") %> <%if(request.getAttribute("only_encargados").equals("true")){ %>checked<%} %> id="inputonly_encargados"><b>Restringido a Encargados?</b></input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=modificacion')">Guardar Cambios</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "baja":
%>
<div class="container-form">
  <h2>Eliminar Tipo de Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputnombre">Nombre:</label>
      	  <input name="nombre" type="text" class="form-control" id="inputnombre"  required value="<%=request.getAttribute("nombre")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputcant_max_res_pen">Max Reservas Pendientes:</label>
      <input readonly name="cant_max_res_pen"  type="number" min="1"  class="form-control" id="inputcant_max_res_pen" required  value="<%=request.getAttribute("cant_max_res_pen") %>" >
    </div>
    <div class="form-group">
      <label for="inputlimite_horas_res">Max Horas de Reserva:</label>
      <input readonly name="limite_horas_res"  type="number" min="1"  class="form-control" id="inputlimite_horas_res"  required  value="<%=request.getAttribute("limite_horas_res") %>" >
    </div>
    <div class="form-group">
      <label for="inputdias_max_anticipacion">Max Dias de Anticipacion:</label>
      <input readonly name="dias_max_anticipacion"  type="number" min="1"  class="form-control" id="inputdias_max_anticipacion" required value="<%=request.getAttribute("dias_max_anticipacion") %>" >
    </div>
	<div class="checkbox">
	  <input disabled name="only_encargados" type="checkbox" value=<%=request.getAttribute("only_encargados") %> <%if(request.getAttribute("only_encargados").equals("true")){ %>checked<%} %> id="inputonly_encargados"><b>Restringido a Encargados?</b></input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<%	
break;
default:
	response.sendRedirect("ServletListaTiposDeElementos");
	break;
}

%>
<br><br>
<div class="loader"></div>
</body>
</html>