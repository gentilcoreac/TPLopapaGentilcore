<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Elemento"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Lista Elementos</title>
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

<input class="btn btn-danger pull-right" type="button" value="Crear Reserva">
<!-- Abre modal -->
<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">Crear Elemento</button>






<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Crear Elemento</h4>
        </div>
        <div class="modal-body">
            <form name="signin" action="ServletABMCElemento" method="post"> 
		    <div class="form-group">
		      <label for="inputnombre">Nombre:</label>
		      <input name="nombre" type="text" class="form-control" id="inputnombre" >
		    </div>
		    <div class="form-group">
		      <label for="inputtipo_elemento">Tipo de elemento</label>
		      <select name="tipo_elemento" class="form-control" id="inputtipo_elemento">
		        <option>1</option>
		        <option>2</option>
		        <option>3</option>
		        <option>4</option>
		      </select>
		    </div>   
			<div>	 
				<input name="opcion" type="hidden" id="opcionElegida" value="agregar">		
			</div>
		    <br> 
		    <br> 
			<h5>  faltaria corregir de que tipo de elemento es(q se actualice solo la lista de arriba)</h5> 
			<br> <br> 
		       <button class="btn btn-lg btn-primary btn-block" type="submit">Crear</button>		
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
        <th>Nombre</th>
        <th>Tipo de elemento</th>
        <th></th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Elemento> listaElem = (ArrayList<Elemento>)request.getAttribute("listaElementos");
			for(Elemento e: listaElem)
			{
		%>
		<tr>
			<td><%= e.getId_elemento() %></td>			
			<td><%= e.getNombre()%></td>
			<td><%= e.getTipo().getId()%></td>
			<td><%= e.getTipo().getNombre()%></td>
			<td>
				<form action="ServletABMCElemento" method="post" name="formOpcion" id="formB">
					<input name="ID_eliminar" type="hidden" id="inputEliminar" value="<%=e.getId_elemento()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="eliminar">					
					<button class="btn" type="submit"><img alt="Icono de eliminar"  width="20px" height="20px" src="https://cdn2.iconfinder.com/data/icons/perfect-flat-icons-2/256/Delete_remove_close_exit_trash_cancel_cross.png"></button>					
				</form>
			</td>
			<td>
				<form action="ServletABMCElemento" method="post" name="formOpcion" id="formM">
					<input name="IDPEditar" type="hidden" id="inputEditar" value="<%=e.getId_elemento()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="buscarPeditar">		
					<button class="btn" type="submit"><img alt="Icono de editar" width="20px" height="20px" src="https://cdn1.iconfinder.com/data/icons/hawcons/32/698873-icon-136-document-edit-512.png"></button>					
				</form>
			</td>
			<td>
				<form action="ServletABMCReserva" method="post" name="formOpcion" id="formM">
					<input name="IDPCrear" type="hidden" id="inputCrear" value="<%=e.getId_elemento()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="buscarPCrear">		
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



<script>
// Cuando se baja mas de 20px, aparece el botón para subir al top
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtn").style.display = "block";
    } else {
        document.getElementById("myBtn").style.display = "none";
    }
}

// Cuando el usuario hace click en el botón, se vuelve arriba de todo del documento
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
</script>

</body>
</html>