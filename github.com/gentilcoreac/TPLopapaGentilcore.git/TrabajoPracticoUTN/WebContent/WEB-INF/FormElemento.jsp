<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.TipoDeElemento" %>
<%@page import="business.entities.Persona" %>
<!DOCTYPE html >
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEycHgiIGhlaWdodD0iNTEycHgiPgo8ZyB0cmFuc2Zvcm09Im1hdHJpeCgxLjI1IDAgMCAtMS4yNSAwIDQ1KSI+Cgk8Zz4KCQk8Zz4KCQkJPHBhdGggc3R5bGU9ImZpbGw6IzNCODhDMzsiIGQ9Ik00MDkuNi0zMjguMDg5YzAtMjUuMTM0LTIwLjM3OC00NS41MTEtNDUuNTExLTQ1LjUxMUg0NS41MTFDMjAuMzc4LTM3My42LDAtMzUzLjIyMiwwLTMyOC4wODkgICAgIFYtOS41MTFDMCwxNS42MjIsMjAuMzc4LDM2LDQ1LjUxMSwzNmgzMTguNTc4QzM4OS4yMjIsMzYsNDA5LjYsMTUuNjIyLDQwOS42LTkuNTExVi0zMjguMDg5eiIvPgoJCQk8cGF0aCBzdHlsZT0iZmlsbDojRkZGRkZGOyIgZD0iTTE3NS41MzYtMTUzLjg5NWgzNi4zMjljMTkuMzk5LDAsMzIuODI1LDExLjYzOSwzMi44MjUsMzEuMzkxICAgICBjMCwyMC4xMDUtMTMuNDI2LDMxLjA1LTMyLjgyNSwzMS4wNWgtMzYuMzI5Vi0xNTMuODk1eiBNMTIyLjYxOC03MC4yOTFjMCwxNi45Myw5LjUyMywyNy41MjMsMjcuMTU5LDI3LjUyM2g2Mi4wODkgICAgIGM1NC4zMjksMCw4Ny44NDgtMjQuMzQ4LDg3Ljg0OC03OS43MzVjMC0zOC43OTgtMjkuMjg2LTYxLjAzLTY1LjI3NC02Ni42NzRsNTkuOTg0LTY0LjIwNSAgICAgYzQuOTI3LTUuMjkxLDcuMDQzLTEwLjU4MSw3LjA0My0xNS41MTljMC0xMy43NTYtMTAuOTM0LTI3LjE1OS0yNi40NDItMjcuMTU5Yy02LjM2LDAtMTQuODI1LDIuNDY5LTIwLjQ2OSw5LjE3bC03OC4zMjUsOTQuODkxICAgICBoLTAuNjk0di03Ni41NWMwLTE3LjY0Ny0xMS4yODctMjcuNTExLTI2LjQ2NS0yNy41MTFjLTE1LjE2NywwLTI2LjQ1Myw5Ljg2NS0yNi40NTMsMjcuNTExICAgICBDMTIyLjYxOC0yNjguNTQ5LDEyMi42MTgtNzAuMjkxLDEyMi42MTgtNzAuMjkxeiIvPgoJCTwvZz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K">
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

            <%Persona per=((Persona)request.getSession().getAttribute("user"));
           	  String categoria=per.getCategoria().getDescripcion();
              %>
        <li >
		    <a href="ServletListaReservas">Reservas</a>
		</li>    
        <li >
          <a href="ServletListaElementos">Elementos</a>
        </li>
    <%	
    	if(categoria.equals("Administrador")){ %>
		<li >
          <a href="ServletListaUsuarios">Usuarios</a>
        </li>
    <%}%>
        <li >
          <a href="ServletListaTiposDeElementos">Tipos de Elementos</a>
        </li>
        
	
    </ul>

    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false"><span class="glyphicon glyphicon-user"><%=" "+per.getNombre()+" "+per.getApellido()%></span><span class="caret"></span>
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
  <h2>Agregar Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post" autocomplete="off"> 
  	

  	<div class="form-group">
     <label>ID:&nbsp<%=request.getAttribute("proxid") %></label>
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required   >
    </div>
    <div class="form-group">
      <label for="inputTipoLista">Categoria</label>
      <select name="tipo" class="form-control" id="inputTipoLista" required   >
       <%for(TipoDeElemento te:(ArrayList<TipoDeElemento>)request.getAttribute("tipos")){ %>  
        <option><%=te.getNombre() %></option>
      <%} %>
      </select>
    </div>   
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCElemento?accion=alta')">Agregar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "modificacion":
%>
<div class="container-form">
  <h2>Modificar Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputid">ID:</label>
      	  <input name="id" type="text" class="form-control" id="inputid"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("id")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCElemento?accion=consulta&fin=modificacion')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
        <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required value="<%=request.getAttribute("nombre") %>"  >
    </div>
    <div class="form-group">
      <label for="inputTipoLista">Categoria</label>
      <select name="tipo" class="form-control" id="inputTipoLista" required   >
       <%for(TipoDeElemento te:(ArrayList<TipoDeElemento>)request.getAttribute("tipos")){ %>  
        <option <%if(request.getAttribute("tipoelemento").equals(te.getNombre())){ %>selected<%} %>><%=te.getNombre() %></option>
      <%} %>
      </select>
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCElemento?accion=modificacion')">Guardar Cambios</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "baja":
%>
<div class="container-form">
  <h2>Eliminar Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputid">ID:</label>
      	  <input name="id" type="text" class="form-control" id="inputid"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("id")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCElemento?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input readonly name="nombre" type="text" class="form-control" id="inputnombre"  required value="<%=request.getAttribute("nombre") %>"  >
    </div>
    <div class="form-group">
      <label for="inputTipoLista">Categoria</label>
      <select disabled  name="tipo" class="form-control" id="inputTipoLista" required  value="<%=request.getAttribute("tipoelemento") %>" >
       <%for(TipoDeElemento te:(ArrayList<TipoDeElemento>)request.getAttribute("tipos")){ %>  
        <option <%if(request.getAttribute("tipoelemento").equals(te.getNombre())){ %>selected<%} %>><%=te.getNombre() %></option>
      <%} %>
      </select>
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCElemento?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<%	
break;
default:
	response.sendRedirect("ServletListaElementos");
	break;
}

%>
<br><br>
</body>
</html>