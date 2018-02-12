<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="business.entities.Persona" %>
<!DOCTYPE html >
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/listado.css">
  <link rel="stylesheet" href="css/formulario.css" >
  <link rel="icon" href="imagenes/bookingsFavicom.ico" />      
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
  <h2>Agregar Persona</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post" autocomplete="off"> 
  	

  	<div class="form-group">
      <label for="inputdni">Dni:</label>
      <input name="dni" type="text" class="form-control" id="inputdni"  required pattern="[0-9]+" oninvalid="setCustomValidity('Dni invalido')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>

    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input name="apellido" type="text" class="form-control" id="inputapel" required    >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required   >
    </div>
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr" required   >
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="text" autocomplete="off" class="form-control" id="inputpwd" required >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail"  required pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Formato de email invalido')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select name="categoria" class="form-control" id="inputCategoriaLista" required   >
        <option>Administrador</option>
        <option>Encargado</option>
        <option>Usuario</option>
      </select>
    </div>   
	<div class="checkbox">
	  <label for="inputhabilitado"></label>
	  <input name="habilitado" type="checkbox"  checked id="inputhabilitado">Habilitado</input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCPersona?accion=alta')">Agregar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "modificacion":
%>
<div class="container-form">
  <h2>Modificar Persona</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputdni">Dni:</label>
      	  <input name="dni" type="text" class="form-control" id="inputdni"  required pattern="[0-9]+" oninvalid="setCustomValidity('Dni invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("dni")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button  id="btnlupa" formnovalidate onclick="javascript: submitForm('ServletABMCPersona?accion=consulta&fin=modificacion')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
<%--     <div class="form-group">
      <label for="inputid">Id</label><input name="id" type="text" class="form-control" id="inputid"  required="" value=<%=request.getAttribute("id")%> >
  	</div> --%>
    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input name="apellido" type="text" class="form-control" id="inputapel" required  value="<%=request.getAttribute("apellido") %>" >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required  value="<%=request.getAttribute("nombre") %>" >
    </div>
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr" required value="<%=request.getAttribute("usuario") %>" >
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="text" class="form-control" id="inputpwd" required  value="<%=request.getAttribute("contrasenia") %>" >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail"  required pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Formato de email invalido')" onchange="try{setCustomValidity('')}catch(e){}"  value="<%=request.getAttribute("email") %>" >
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select name="categoria" class="form-control" id="inputCategoriaLista" required    >
        <option <%if(request.getAttribute("categoria").equals("Administrador")){ %>selected<%} %>>Administrador</option>
        <option <%if(request.getAttribute("categoria").equals("Encargado")){ %>selected<%} %>>Encargado</option>
        <option <%if(request.getAttribute("categoria").equals("Usuario")){ %>selected<%} %>>Usuario</option>
      </select>
    </div>   
	<div class="checkbox">
	  <label for="inputhabilitado"></label>
	  <input name="habilitado" type="checkbox" value=<%=request.getAttribute("habilitado") %> <%if(request.getAttribute("habilitado").equals("true")){ %>checked<%} %> id="inputhabilitado">Habilitado</input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCPersona?accion=modificacion')">Guardar Cambios</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "baja":
%>
<div class="container-form">
  <h2>Eliminar Persona</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputdni">Dni:</label>
      	  <input name="dni" type="text" class="form-control" id="inputdni"  required pattern="[0-9]+" oninvalid="setCustomValidity('Dni invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("dni")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button  id="btnlupa" formnovalidate onclick="javascript: submitForm('ServletABMCPersona?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input readonly name="apellido" type="text" class="form-control" id="inputapel" required=""  value="<%=request.getAttribute("apellido") %>" >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input readonly name="nombre" type="text" class="form-control" id="inputnombre"  required=""  value="<%=request.getAttribute("nombre") %>" >
    </div>
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input readonly name="usuario" type="text" class="form-control" id="inputusr" required="" value="<%=request.getAttribute("usuario") %>" >
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input readonly name="contrasenia" type="text" class="form-control" id="inputpwd" required=""  value="<%=request.getAttribute("contrasenia") %>" >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input readonly name="email" type="text" class="form-control" id="inputemail"  required=""  value="<%=request.getAttribute("email") %>" >
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select disabled name="categoria" class="form-control" id="inputCategoriaLista" required=""  >
        <option <%if(request.getAttribute("categoria").equals("Administrador")){ %>selected<%} %>>Administrador</option>
        <option <%if(request.getAttribute("categoria").equals("Encargado")){ %>selected<%} %>>Encargado</option>
        <option <%if(request.getAttribute("categoria").equals("Usuario")){ %>selected<%} %>>Usuario</option>
      </select>
    </div>   
	<div class="checkbox">
	  <label for="inputhabilitado"></label>
	  <input disabled name="habilitado" type="checkbox" value=<%=request.getAttribute("habilitado") %> <%if(request.getAttribute("habilitado").equals("true")){ %>checked<%} %> id="inputhabilitado">Habilitado</input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCPersona?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<%	
break;
default:
	response.sendRedirect("ServletListaUsuarios");
	break;
}

%>
<br><br>
<div class="loader"></div>
</body>
</html>