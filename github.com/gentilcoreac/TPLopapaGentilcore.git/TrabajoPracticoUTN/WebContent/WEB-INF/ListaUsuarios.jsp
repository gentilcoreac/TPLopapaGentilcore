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
      <!-- Estilos personalizados - Botón que retorna a arriba -->
    <link href="style/botonArriba.css" rel="stylesheet">
</head>
<body>


<button onclick="topFunction()" id="myBtn" title="Go to top">Subir</button>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="WEB-INF/Inicio.jsp">Inicio</a></li>
      <li><a href="#">Reservar</a></li>
      <li><a href="ServletListaElementos">Elementos</a></li> 
      <li>
               	<form action="ServletListaElementos" method="post" name="formElementos" id="formElementos">
          		 <input class="dropdown-toggle" type="submit" value="Elemento">					
				</form>
      </li>
      <li>
               	<form action="ServletListaReservas" method="post" name="formReservas" id="formReservas">
          		 <input class="dropdown-toggle" type="submit" value="Reserva">					
				</form>
      </li>      
      
      
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Usuarios <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li class="active"><a href="#">Ver listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">...---...</a></li>
            <li><a href="#">...---...</a></li>
            
          </ul>
        </li>
  </div>
</nav>


<input class="btn btn-danger pull-right" type="button" value="Crear Reserva">
<!-- Abre modal -->
<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">Crear usuario</button>







<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Crear usuario</h4>
        </div>
        <div class="modal-body">
            <form name="signin" action="ServletABMCPersona" method="post"> 
  
		    <div class="form-group">
		      <label for="inputusr">Usuario:</label>
		      <input name="usuario" type="text" class="form-control" id="inputusr" required>
		    </div>
		    <div class="form-group">
		      <label for="inputpwd">Contraseña:</label>
		      <input name="contrasenia" type="password" class="form-control" id="inputpwd" required>
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
		      <input name="apellido" type="text" class="form-control" id="inputapel" required>
		    </div>
		    <div class="form-group">
		      <label for="inputnombre">Nombre:</label>
		      <input name="nombre" type="text" class="form-control" id="inputnombre" required>
		    </div>
		    <div class="form-group">
		      <label for="inputdni">Dni:</label>
		      <input name="dni" type="text" class="form-control" id="inputdni" required>
		    </div>
		    <div class="form-group">
		      <label for="inputemail">Email:</label>
		      <input name="email" type="text" class="form-control" id="inputemail" required>
		    </div>
			<div class="checkbox">
			  <label for="inputhabilitado">Habilitado:</label>
			  <input name="habilitado" type="checkbox" id="inputhabilitado">
			</div> 
			<div>	 
				<input name="opcion" type="hidden" id="opcionElegida" value="agregar">		
			</div>	  
		    <br> 
			<h3>  faltaria corregir el habilitado </h3> 
			<br> <br> 
		       <button class="btn btn-lg btn-primary btn-block" type="submit">Crear Usuario</button>		
			</form>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        </div>
      </div>
    </div>
  </div>
</div>


<div class="container">

  <h2>Lista de usuarios</h2>
  <p>Tipeá algo en el campo de entrada para buscar en la tabla:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Buscar..">
  <br>
  <table class="table table-striped">  
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
				<form action="ServletABMCPersona" method="post" name="formOpcion" id="formB">
					<input name="DNIeliminar" type="hidden" id="inputEliminar" value="<%=p.getDni()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="eliminar">					
					<button class="btn" type="submit"><img alt="Icono de eliminar"  width="20px" height="20px" src="https://cdn2.iconfinder.com/data/icons/perfect-flat-icons-2/256/Delete_remove_close_exit_trash_cancel_cross.png"></button>					
				</form>
			</td>
			<td>
				<form action="ServletABMCPersona" method="post" name="formOpcion" id="formM">
					<input name="DNIPeditar" type="hidden" id="inputEditar" value="<%=p.getDni()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="buscarPeditar">				<!-- esta es para  -->
					<button class="btn" type="submit"><img alt="Icono de editar" width="20px" height="20px" src="https://cdn1.iconfinder.com/data/icons/hawcons/32/698873-icon-136-document-edit-512.png"></button>					
				</form>
			</td>
		</tr>
		<%
			}
		%>
		
    </tbody>
  </table>
</div>









<script>
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});
</script>


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
