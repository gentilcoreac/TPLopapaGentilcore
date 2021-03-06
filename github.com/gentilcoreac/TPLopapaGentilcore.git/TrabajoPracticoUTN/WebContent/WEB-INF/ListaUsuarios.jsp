<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Usuarios</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/listado.css">
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/listado.js"></script>

</head>
<body>


<%
if(session.getAttribute("user")==null){
	response.sendRedirect("Login.html");
}
%>
<!-- <div class="menu_bar">
			<div class="bt-menu">MyReserva<span class="glyphicon glyphicon-menu-hamburger"></span></div>
</div> -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="ServletInicio">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
        <li >
		    <a href="ServletListaReservas"><span class="glyphicon glyphicon-calendar"></span>Reservas</a>
		</li>    
        <li >
          <a href="ServletListaElementos"><span class="glyphicon glyphicon-th-large"></span>Elementos</a>
        </li>
    <%	Persona per=((Persona)request.getSession().getAttribute("user"));
 		  String categoria=per.getCategoria().getDescripcion();
    	if(categoria.equals("Administrador")){ %>
		<li >
          <a href="ServletListaUsuarios"><span class="glyphicon glyphicon-phone"></span>Usuarios</a>
        </li>
    <%}%>    
        <li >
          <a href="ServletListaTiposDeElementos"><span class="glyphicon glyphicon-list"></span>Tipos de Elementos</a>
        </li>
        
	
    </ul>

    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
          <a href="#usudesplegable" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false"><span class="glyphicon glyphicon-user"></span><%=" "+per.getNombre()+" "+per.getApellido()%><span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="ServletPerfil" >Perfil</a></li>
            <li class="divider"></li>            
            <li><a href="ServletLogout">Salir</a>  
            </li>
            	
          </ul>
        </li>
    </ul>
  </div>
</nav>





<div class="container">
  <br>
  <br>
  <h2>Lista de Usuarios</h2>
  <br>
  <form method="post" action="ServletFormsUsuarios?accion=alta">
  	<button class="btn btn-success btn-md nuevo" type="submit">
  		<span class="glyphicon glyphicon-plus" >&nbspNuevo</span>
  	</button>
  </form>
  <br>
  <p><b>Busque por algun dato del usuario:</b></p>  
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
			<td><%=p.isHabilitado()?"Si":"No" %></td>											
			<td>
			
				<form  id=<%=p.getId()%> name="myForm" action="" method="post">
					<input type="hidden" name="id" value="<%=p.getId() %>" >
					<input type="hidden" name="dni" value="<%=p.getDni()%>" >		
					<input type="hidden" name="apellido" value="<%=p.getApellido() %>" >		
					<input type="hidden" name="nombre" value="<%=p.getNombre() %>" >		
					<input type="hidden" name="usuario" value="<%=p.getUsuario() %>" >		
					<input type="hidden" name="contrasenia" value="<%=p.getContrasenia() %>" >		
					<input type="hidden" name="email" value="<%=p.getEmail() %>" >		
					<input type="hidden" name="categoria" value="<%=p.getCategoria().getDescripcion() %>" >		
					<input type="hidden" name="habilitado" value="<%=p.isHabilitado() %>" >		
					<button  class="btn btn-info btn-md editar" type="submit"  onclick="javascript: submitForm('ServletFormsUsuarios?accion=modificacion',<%=p.getId()%>)" data-toggle="tooltip" title="modificar"><span class="glyphicon glyphicon-pencil"></span></button>	
					<button class="btn  btn-danger eliminar" type="submit" onclick="javascript: submitForm('ServletABMCPersona?accion=baja',<%=p.getId()%>)"  data-toggle="tooltip" title="eliminar"><span class="glyphicon glyphicon-trash"></span></button>
			
				</form>
		
			</td>
		</tr>
		<%
			}
		%>
		
    </tbody>
  </table>
  <br><br>
  <button class="btn btn-warning btn-circle btn-md" onclick="topFunction()" id="btngototop" title="Volver Arriba"><span class="glyphicon glyphicon-arrow-up"></span></button> 
  
</div>


<div class="loader"></div> 
</body>
</html>
