<%@page import="business.entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Perfil</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Redireccionador?destino=WEB-INF/Inicio.jsp">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Reservas<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Hacer Reserva</a></li>
            <%Persona per=((Persona)request.getSession().getAttribute("user"));
           	  String categoria=per.getCategoria().getDescripcion();
           	if(categoria.equals("Administrador")){
              %>
            <li><a href="#">Cerrar Reserva</a></li>
            <% }%>
            <li><a href="#">Borrar Reserva</a></li>
          </ul>
        </li>	
      
      	<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Elementos<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <%if(categoria.equals("Administrador")){ %>
            <li class="divider"></li>            
            <li><a href="#">Agregar Elemento</a></li>
            <li><a href="#">Editar Elemento</a></li>
            <li><a href="#">Borrar Elemento</a></li>
            <%} %>
          </ul>
        </li>
        
    <%	
    	if(categoria.equals("Administrador")){ %>
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Usuarios <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Agregar usuario</a></li>
            <li><a href="#">Editar usuario</a></li>
            <li><a href="#">Borrar usuario</a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Tipos de Elementos<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Agregar Tipo de Elemento</a></li>
            <li><a href="#">Editar Tipo de Elemento</a></li>
            <li><a href="#">Borrar Tipo de Elemento</a></li>
          </ul>
        </li>
	<%}%>
    </ul>

    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false"><span class="glyphicon glyphicon-user"><%=" "+per.getNombre()+","+per.getApellido()%></span><span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="Redireccionador?destino=WEB-INF/Perfil.jsp" >Perfil</a></li>
            <li class="divider"></li>            
            <li><a href="Login.html">Salir</a></li>
          </ul>
        </li>
    </ul>
  </div>
</nav>

<div class="container">
  <h2>Datos del Perfil</h2>
          
  <table class="table table-striped table-responsive">
    <tbody>
      <tr>
        <td><b>Id</b></td>
        <td><%=((Persona)session.getAttribute("user")).getId() %></td>
      </tr>
      <tr>
        <td><b>Dni</b></td>
        <td><%=((Persona)session.getAttribute("user")).getDni() %></td>
      </tr>
      <tr>
        <td><b>Nombre</b></td>
        <td><%=((Persona)session.getAttribute("user")).getNombre() %></td>
      </tr>
      <tr>
        <td><b>Apellido</b></td>
        <td><%=((Persona)session.getAttribute("user")).getApellido() %></td>
      </tr>
      <tr>
        <td><b>Usuario</b></td>
        <td><%=((Persona)session.getAttribute("user")).getUsuario() %></td>
      </tr>
      <tr>
        <td><b>Email</b></td>
        <td><%=((Persona)session.getAttribute("user")).getEmail() %></td>
      </tr>
      <tr>
        <td><b>Categoria</b></td>
        <td><%=((Persona)session.getAttribute("user")).getCategoria().getDescripcion() %></td>
      </tr>
    </tbody>
  </table>
</div>

</body>
</html>