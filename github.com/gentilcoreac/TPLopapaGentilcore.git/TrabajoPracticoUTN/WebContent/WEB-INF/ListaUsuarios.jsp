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
  <h2>Formulario de Persona</h2>
  <p>Ingrese los datos y a continuación presione crear usuario:</p>
  
  <form name="signin" action="ServletABMCPersona" method="post"> 
  
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr">
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="password" class="form-control" id="inputpwd">
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select name="categoria" class="form-control" id="inputCategoriaLista">
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
    </div>   
    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input name="apellido" type="text" class="form-control" id="inputapel">
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre">
    </div>
    <div class="form-group">
      <label for="inputdni">Dni:</label>
      <input name="dni" type="text" class="form-control" id="inputdni">
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail">
    </div>
	<div class="checkbox">
	  <label for="inputhabilitado">Habilitado:</label>
	  <input name="habilitado" type="checkbox" value="1" id="inputhabilitado">
	</div>   
    <br> 
    <br> 
	<h3>  faltaria corregir el habilitado, Y validar q los campos esten completos y no mande vacios. Comprobar que el    </h3> 
	<br> <br> 
       <button class="btn btn-lg btn-primary btn-block" type="submit">Crear Usuario</button>		
	</form>
</div>















<div class="container">

  <h2>Lista de usuarios</h2>
  <p>Tipeá algo en el campo de entrada para buscar en la tabla para buscar por alguno de los campos:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  
  <table class="table table-striped">
  
  
  <!--.............................................  poner el formulario de ingreso de valores...............................-->
  
      <thead>
      <tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Apellido</th>
        <th>Nombre</th>
        <th>Usuario</th>
        <th>Contraseña</th>
        <th>Email</th>
        <th>Id Categoria</th>        
        <th>Categoria</th>
        <th>Habilitado</th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Persona>listaPers= (ArrayList<Persona>)request.getAttribute("listaPersonas");
			for(Persona p : listaPers){
		%>
		<tr>
			<td><%=p.getId() %></td>			
			<td><%=p.getDni() %></td>
			<td><%=p.getApellido() %></td>
			<td><%=p.getNombre() %></td>	
			<td><%=p.getUsuario() %></td>	
			<td><%=p.getContrasenia() %></td>	
			<td><%=p.getEmail() %></td>	
			<td><%=p.getCategoria().getId() %></td>																		
			<td><%=p.getCategoria().getDescripcion() %></td>
			<td><%=p.isHabilitado() %></td>											
			<td>
				<form action="ABMCPersonaEliminar" method="post" name="formOpcion" id="formB">
					<input name="DNIeliminar" type="hidden" id="inputEliminar" value="<%=p.getDni()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="eliminar">					
					<button class="btn" type="submit">Eliminar</button>					
				</form>
			</td>
			<td>
				<form action="ABMCPersonaEditar" method="post" name="formOpcion" id="formM">
					<input name="DNIeditar" type="hidden" id="inputEditar" value="<%=p.getDni()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="editar">				<!-- esta es para  -->
					<button class="btn" type="submit">Editar</button>					
				</form>
			</td>
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
