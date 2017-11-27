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
  
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/formPersona.css" >
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/buscatabla.js"></script>
    <script type="text/javascript">
    	function submitForm(met,id) {
    		//document.myForm.action=met;
    		document.getElementById(id).action =met;
    		document.getElementById(id).submit();
        }
    </script>
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






<!-- 
<div class="container-form-per">
  <h2>Formulario de Persona</h2>
  <p>Ingrese los datos y a continuación presione crear usuario:</p>
  
  <form name="form-per" class="form-per" action="ServletABMCPersona" method="post"> 
  
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr" required="">
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="password" class="form-control" id="inputpwd" required="" >
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select name="categoria" class="form-control" id="inputCategoriaLista" required="" >
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
    </div>   
    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input name="apellido" type="text" class="form-control" id="inputapel" required="" >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required="" >
    </div>
    <div class="form-group">
      <label for="inputdni">Dni:</label>
      <input name="dni" type="text" class="form-control" id="inputdni"  required="" >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail"  required="" >
    </div>
	<div class="checkbox">
	  <label for="inputhabilitado">Habilitado:</label>
	  <input name="habilitado" type="checkbox" value="1" id="inputhabilitado">
	</div>   
       <button class="btn btn-lg btn-primary btn-block" type="submit">Crear Usuario</button>		
	</form>
</div>


	<h3>  faltaria corregir el habilitado, Y validar q los campos esten completos y no mande vacios. Comprobar que el    </h3> 

 -->










<div class="container">

  <h2>Lista de usuarios</h2>
  <form method="post" action="ServletFormsUsuarios?accion=alta">
  	<button style="float:right;" class="btn btn-success btn-md" type="submit">
  		<span class="glyphicon glyphicon-plus">&nbspNuevo</span>
  	</button>
  </form>
  <br>
  <p>Busque por algun dato del usuario:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
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
        <th>Categoria</th>        
        <th>Habilitado</th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Persona> listaPers= (ArrayList<Persona>)request.getAttribute("listaPersonas");
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
			<td><%=p.getCategoria().getDescripcion() %></td>																		
			<td><%=p.isHabilitado() %></td>											
			<td>
			
				<form  id=<%=p.getId()%> name="myForm" action="" method="post">
					<input type="hidden" name="id" value=<%=p.getId() %> >
					<input type="hidden" name="dni" value=<%=p.getDni()%> >		
					<input type="hidden" name="apellido" value=<%=p.getApellido() %> >		
					<input type="hidden" name="nombre" value=<%=p.getNombre() %> >		
					<input type="hidden" name="usuario" value=<%=p.getUsuario() %> >		
					<input type="hidden" name="contrasenia" value=<%=p.getContrasenia() %> >		
					<input type="hidden" name="email" value=<%=p.getEmail() %> >		
					<input type="hidden" name="categoria" value=<%=p.getCategoria() %> >		
					<input type="hidden" name="habilitado" value=<%=p.isHabilitado() %> >		
					<button class="btn" type="submit" onclick="javascript: submitForm('ServletABMCPersona?accion=baja',<%=p.getId()%>)" >Eliminar</button>	
					<button  class="btn btn-info btn-md" type="submit"  onclick="javascript: submitForm('ServletFormsUsuarios?accion=modificacion',<%=p.getId()%>)"><span class="glyphicon glyphicon-pencil"></span></button>	
								
				</form>
		
			</td>
		</tr>
		<%
			}
		%>
		
    </tbody>
  </table>
</div>



</body>
</html>
