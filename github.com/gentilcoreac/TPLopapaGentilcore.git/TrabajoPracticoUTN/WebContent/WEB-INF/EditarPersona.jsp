<%@page import="business.entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<div class="container">

  <h2>Editar Persona</h2>
  <p>Ingrese los datos y a continuación presione actualizar:</p>
      
  <form name="signin" action="ServletABMCPersona" method="post"> 
		<%
			Persona per = (Persona)request.getAttribute("user_ret");
		%>  
    <div class="form-group">
      <label for="inputID">ID:</label>
      <input name="id" type="text" class="form-control" id="inputid" required readonly="readonly" value="<%=per.getUsuario() %>">
    </div>		
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr" required  value="<%=per.getUsuario() %>">
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="password" class="form-control" id="inputpwd" required  value="<%per.getContrasenia(); %>">
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
      <input name="apellido" type="text" class="form-control" id="inputapel" required  value="<%=per.getApellido() %>">
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre" required  value="<%=per.getNombre() %>">
    </div>
    <div class="form-group">
      <label for="inputdni">Dni:</label>
      <input name="dni" type="text" class="form-control" id="inputdni" required  value=<%=per.getDni() %>>
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail" required  value="<%=per.getEmail() %>">
    </div>
	<div class="checkbox">
	  <label for="inputhabilitado">Habilitado:</label>
	  <input name="habilitado" type="checkbox" id="inputhabilitado" value="<%=per.isHabilitado() %>">
	</div>   
	<div>	 
		<input name="opcion" type="hidden" id="opcionElegida" value="editar">		
	</div>
    <br> 
    <br> 
	<h3>  faltaria corregir el habilitado    </h3> 
	<br> <br> 
       <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>		
	</form>
</div>


</body>
</html>