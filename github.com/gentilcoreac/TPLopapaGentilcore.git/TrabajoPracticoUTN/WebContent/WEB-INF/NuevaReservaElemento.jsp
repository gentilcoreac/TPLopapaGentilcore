<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Reserva"%>
<%@page import="business.entities.Elemento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Reservas</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

  <link href="style/bootstrap-datetimepicker.min.css" rel="stylesheet" >

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <!-- Estilos personalizados - BotÃ³n que retorna a arriba -->
    <link href="style/botonArriba.css" rel="stylesheet">
</head>
<body>

<button onclick="topFunction()" id="myBtn" title="Go to top">Subir</button>


<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">Crear Reserva</button>



<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Crear Reserva</h4>
        </div>
        <div class="modal-body">
			<form name="signin" action="ServletABMCReserva" method="post"> 
		<%
			Elemento ele = (Elemento)request.getAttribute("ele_ret");
		%>  
			    <div class="form-group">
			      <label for="inputidreserva">ID de la Reserva:</label>
			      <input name="id_reserva" type="text" class="form-control" id="inputidreserva" placeholder="no se completa, aca iria automatico el max(id)+1">
			    </div>
			    <div class="form-group">
			      <label for="inputpersona">Persona:</label>
			      <input name="persona" type="text" class="form-control" id="inputpersona" value="<%= request.getSession().getAttribute("user") %>" placeholder="<%= request.getSession().getAttribute("user") %>" required>
			    </div>

			    <div class="form-group">
			      <label for="inputidelemento">ID Elemento:</label>
			      <input name="id_elemento" type="hide" class="form-control" id="inputidelemento"  value="<%=ele.getId_elemento()%>"  >
			    </div>
			    <div class="form-group">
			      <label for="inputelemento">Elemento:</label>
			      <input name="elemento" type="text" class="form-control" id="inputelemento"  value="<%=ele.getNombre() %>"  >
			    </div>
			    <div class="form-group">
			      <label for="inputdetalle">Detalle:</label>
			      <input name="detalle" type="text" class="form-control" id="inputdetalle" placeholder="opcional">
			    </div>			    			    
<!-- COMENTADO ESTE DIV   <div class="form-group">
			      <label for="inputreshecho">Fecha y hora de la reserva hecha:</label>
			      <input name="reshecho" type="date" class="form-control" id="inputreshecho" required value="<%= new java.util.Date()  %> " data-date-format="yyyy-mm-dd HH:ii p">
			    </div>			    			<!--  ARREGLAR ESTO DE LA FECHA Y HORA REALIZADA -->

        <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">Hecho</label>
                <div class="input-group date form_datetime col-md-5" data-date="2017-10-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:mm:ss" data-link-field="dtp_inputDesde">
                    <input class="form-control" size="16" type="text" name="reshecho" value="">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_inputDesde" value="" /><br/>
            </div>
        </fieldset>


        <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">Desde</label>
                <div class="input-group date form_datetime col-md-5" data-date="2017-10-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:mm:ss" data-link-field="dtp_inputDesde">
                    <input class="form-control" size="16" type="text" name="resdesde" value="">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_inputDesde" value="" /><br/>
            </div>
        </fieldset>
        
        <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">Hasta</label>
                <div class="input-group date form_datetime col-md-5" data-date="2017-10-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:mm:ss" data-link-field="dtp_inputHasta">
                    <input class="form-control" size="16" type="text" name="reshasta" value="">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_inputHasta" value="" /><br/>
            </div>
        </fieldset>


			    
			    <div class="form-group">
			      <label for="inputresentregado">Fecha y hora entragado:</label>
			      <input name="resentregado" type="date" class="form-control" id="inputresentregado">
			    </div>

			<div>	 
				<input name="opcion" type="hidden" id="opcionElegida" value="agregar">		
			</div>
		       <button class="btn btn-lg btn-primary btn-block" type="submit">Crear Reserva</button>		
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

  <h2>Lista de Reservas</h2>
  <p>TipeÃ¡ algo en el campo de entrada para buscar en la tabla:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Buscar..">
  <br>
  <table class="table table-striped">  
      <thead>
      <tr>
        <th>ID</th>
        <th>ID Per</th>
        <th>Persona</th>        
        <th>ID Elem</th>
        <th>Elemento</th>
        <th>Desde</th>
        <th>Hasta</th>
        <th>Realizada</th>
        <th>Entregado</th>  
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Reserva>listaRes= (ArrayList<Reserva>)request.getAttribute("listaReservas");
			for(Reserva r : listaRes){
		%>
		<tr>
			<td><%=r.getId_reserva() %></td>
			<td><%=r.getPersona().getId() %></td>
			<td><%=r.getPersona().getUsuario() %></td>			
			<td><%=r.getElemento().getId_elemento() %></td>
			<td><%=r.getElemento().getNombre() %></td>
			<td><%=r.getFecha_hora_desde_solicitada() %></td>
			<td><%=r.getFecha_hora_hasta_solicitada() %></td>
			<td><%=r.getFecha_hora_reserva_hecha() %></td>
			<td><%=r.getFecha_hora_entregado() %></td>			
			
			<td>
				<form action="ServletABMCReserva" method="post" name="formOpcion" id="formB">
					<input name="idReservaeliminar" type="hidden" id="inputEliminar" value="<%=r.getId_reserva()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="eliminar">					
					<button class="btn" type="submit"><img alt="Icono de eliminar"  width="20px" height="20px" src="https://cdn2.iconfinder.com/data/icons/perfect-flat-icons-2/256/Delete_remove_close_exit_trash_cancel_cross.png"></button>					
				</form>
			</td>
			<td>
				<form action="ServletABMCReserva" method="post" name="formOpcion" id="formM">
					<input name="idReservaPeditar" type="hidden" id="inputEditar" value="<%=r.getId_reserva()%>" >
					<input name="opcion" type="hidden" id="opcionElegida" value="buscarPeditar">				<!--  -->
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


<!--  Como buena practica el script me parece que no va acÃ¡. ProbÃ© funcionalidades con esto-->
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
  

<script type="text/javascript" src="style/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="style/bootstrap.min.js"></script>
<script type="text/javascript" src="style/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="style/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
</script>


</body>
</html>