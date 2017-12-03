<%@page import="business.entities.Reserva"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Editar Reserva</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="style/bootstrap-datetimepicker.min.css" rel="stylesheet" >
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>



<div class="container">

  <h2>Editar Reserva</h2>
  <p>Ingrese los datos y a continuación presione actualizar:</p>
      
  <form name="signin" action="ServletABMCReserva" method="post"> 
		<%
			Reserva res = (Reserva)request.getAttribute("res_ret");
		%>  
    <div class="form-group">
      <label for="inputidreserva">ID de la Reserva:</label>
      <input name="id_reserva" type="text" class="form-control" id="inputidreserva" required  readonly="readonly" value="<%=res.getId_reserva() %>" >
    </div>    
    <div class="form-group">
      <label for="inputpersona">Persona:</label>
      <input name="persona" type="text" class="form-control" id="inputpersona" required  readonly="readonly" value="<%=res.getPersona().getId() %>" >
    </div>
    <div class="form-group">
      <label for="inputidelemento">ID Elemento:</label>
      <input name="id_elemento" type="text" class="form-control" id="inputidelemento" required  readonly="readonly" value="<%=res.getElemento().getId_elemento() %>" >
    </div>
    <div class="form-group">
      <label for="inputelemento">Elemento:</label>
      <input name="elemento" type="text" class="form-control" id="inputelemento" required  readonly="readonly" value="<%=res.getElemento().getNombre() %>" >
    </div>
    <div class="form-group">
      <label for="inputdetalle">Detalle:</label>
      <input name="detalle" type="text" class="form-control" id="inputdetalle" required  readonly="readonly" value="<%=res.getDetalle() %>" >
    </div>
    <div class="form-group">
      <label for="inputdesde">Desde:</label>
      <input name="desde" type="date" class="form-control" id="inputdesde" required  readonly="readonly" value="<%=res.getFecha_hora_desde_solicitada() %>" >
    </div>   
    <div class="form-group">
      <label for="inputhasta">Hasta:</label>
      <input name="hasta" type="date" class="form-control" id="inputhasta" required  readonly="readonly" value="<%=res.getFecha_hora_hasta_solicitada() %>" >
    </div>
    <div class="form-group">
      <label for="inputreshecho">Fecha y hora de la reserva hecha:</label>
      <input name="reshecho" type="date" class="form-control" id="inputreshecho" required  readonly="readonly" value="<%=res.getFecha_hora_reserva_hecha()%>" >
    </div>
    <div class="form-group">
      <label for="inputresentregado">Fecha y hora entragado:</label>
      <input name="resentregadoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" type="date" class="form-control" id="inputresentregado" value="<%= res.getFecha_hora_entregado() %>">
    </div>
    
    
    
            <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">Fecha y hora entregado</label>
                <div class="input-group date form_datetime col-md-5" data-date="2017-10-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:mm:ss" data-link-field="dtp_input1">
                    <input class="form-control" size="16" type="text" name="resentregado" value="">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_input1" value="" /><br/>
            </div>
        </fieldset>
    
    
    
    
    
    
	<div>	 
		<input name="opcion" type="hidden" id="opcionElegida" value="editar">		
	</div>
    <br> 
    <br> 
	<h5>  faltaria corregir el tema de la hora cerrada</h5> 
	<br> <br> 
       <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>		
	</form>
</div>







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