<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Usuarios</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="WEB-INF/Inicio.jsp">Inicio</a></li>
      <li><a href="#">Reservar</a></li>
      <li><a href="#">Elementos</a></li>
     
         
        <li class="dropdown">
          <a href="FormularioABMCPersona.html" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Usuarios <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li class="active"><a href="#">Ver listado</a></li>
            <li class="divider"></li>            
            <li><a href="FormularioABMCPersona.html">Agregar usuario</a></li>
            <li><a href="#">Editar usuario</a></li>
            <li><a href="#">Borrar usuario</a></li>
          </ul>
        </li>
        
    </ul>
    <form class="navbar-form navbar-left">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit">
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
  </div>
</nav>


<div class="container">

  <h2>Lista de usuarios</h2>
  <p>Tipea algo en el campo de entrada para buscar en la tabla para buscar por alguno de los campos:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  
  <table class="table table-striped">
  
      <thead>
      <tr>
        <th>DNI</th>
        <th>Apellido</th>
        <th>Nombre</th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Persona>listaPers= (ArrayList<Persona>)request.getAttribute("listaPersonas");
			for(Persona p : listaPers){
		%>
		<tr>
			<td><%=p.getDni() %></td>
			<td><%=p.getApellido() %></td>
			<td><%=p.getNombre() %></td>
		</tr>
		<%
			}
		%>
    </tbody>
  </table>
</div>

<!--  Como buena practica el script me parece que no va acá. Probé funcionalidades con esto-->
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>


</body>
</html>
