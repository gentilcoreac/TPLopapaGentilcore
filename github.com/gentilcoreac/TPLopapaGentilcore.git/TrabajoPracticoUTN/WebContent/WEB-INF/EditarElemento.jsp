<%@page import="business.entities.Elemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Editar elemento</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<div class="container">

  <h2>Editar Persona</h2>
  <p>Ingrese los datos y a continuación presione actualizar:</p>
      
  <form name="signin" action="ServletABMCElemento" method="post"> 
		<%
			Elemento ele = (Elemento)request.getAttribute("ele_ret");
		%>  
    <div class="form-group">
      <label for="inputid">ID del elemento:</label>
      <input name="id_elemento" type="text" class="form-control" id="inputid" required  readonly="readonly" value="<%=ele.getId_elemento() %>" >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre" required  value="<%=ele.getNombre() %>">
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
		<input name="opcion" type="hidden" id="opcionElegida" value="editar">		
	</div>
    <br> 
    <br> 
	<h5>  faltaria corregir de que tipo de elemento es(q se actualice solo la lista de arriba)</h5> 
	<br> <br> 
       <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>		
	</form>
</div>


</body>
</html>