<%@page import="business.entities.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.TipoDeElemento" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Tipos Elementos</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEycHgiIGhlaWdodD0iNTEycHgiPgo8ZyB0cmFuc2Zvcm09Im1hdHJpeCgxLjI1IDAgMCAtMS4yNSAwIDQ1KSI+Cgk8Zz4KCQk8Zz4KCQkJPHBhdGggc3R5bGU9ImZpbGw6IzNCODhDMzsiIGQ9Ik00MDkuNi0zMjguMDg5YzAtMjUuMTM0LTIwLjM3OC00NS41MTEtNDUuNTExLTQ1LjUxMUg0NS41MTFDMjAuMzc4LTM3My42LDAtMzUzLjIyMiwwLTMyOC4wODkgICAgIFYtOS41MTFDMCwxNS42MjIsMjAuMzc4LDM2LDQ1LjUxMSwzNmgzMTguNTc4QzM4OS4yMjIsMzYsNDA5LjYsMTUuNjIyLDQwOS42LTkuNTExVi0zMjguMDg5eiIvPgoJCQk8cGF0aCBzdHlsZT0iZmlsbDojRkZGRkZGOyIgZD0iTTE3NS41MzYtMTUzLjg5NWgzNi4zMjljMTkuMzk5LDAsMzIuODI1LDExLjYzOSwzMi44MjUsMzEuMzkxICAgICBjMCwyMC4xMDUtMTMuNDI2LDMxLjA1LTMyLjgyNSwzMS4wNWgtMzYuMzI5Vi0xNTMuODk1eiBNMTIyLjYxOC03MC4yOTFjMCwxNi45Myw5LjUyMywyNy41MjMsMjcuMTU5LDI3LjUyM2g2Mi4wODkgICAgIGM1NC4zMjksMCw4Ny44NDgtMjQuMzQ4LDg3Ljg0OC03OS43MzVjMC0zOC43OTgtMjkuMjg2LTYxLjAzLTY1LjI3NC02Ni42NzRsNTkuOTg0LTY0LjIwNSAgICAgYzQuOTI3LTUuMjkxLDcuMDQzLTEwLjU4MSw3LjA0My0xNS41MTljMC0xMy43NTYtMTAuOTM0LTI3LjE1OS0yNi40NDItMjcuMTU5Yy02LjM2LDAtMTQuODI1LDIuNDY5LTIwLjQ2OSw5LjE3bC03OC4zMjUsOTQuODkxICAgICBoLTAuNjk0di03Ni41NWMwLTE3LjY0Ny0xMS4yODctMjcuNTExLTI2LjQ2NS0yNy41MTFjLTE1LjE2NywwLTI2LjQ1Myw5Ljg2NS0yNi40NTMsMjcuNTExICAgICBDMTIyLjYxOC0yNjguNTQ5LDEyMi42MTgtNzAuMjkxLDEyMi42MTgtNzAuMjkxeiIvPgoJCTwvZz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/listado.css">
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/listado.js"></script>

<body>

<%
if(session.getAttribute("user")==null){
	response.sendRedirect("Login.html");
}
%><nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Start">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">

        <li >
		    <a href="ServletListaReservas">Reservas</a>
		</li>    
        <li >
          <a href="ServletListaElementos">Elementos</a>
        </li>
    <%	Persona per=((Persona)request.getSession().getAttribute("user"));
 		  String categoria=per.getCategoria().getDescripcion();
    	if(categoria.equals("Administrador")){ %>
		<li >
          <a href="ServletListaUsuarios">Usuarios</a>
        </li>
        
        <li >
          <a href="ServletListaTiposDeElementos">Tipos de Elementos</a>
        </li>
        
	<%}%>
    </ul>

    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false"><span class="glyphicon glyphicon-user"><%=" "+per.getNombre()+" "+per.getApellido()%></span><span class="caret"></span>
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
  <h2>Lista de Tipos de Elementos</h2>
  <br>
  <form method="post" action="ServletFormsTiposDeElementos?accion=alta">
  	<button class="btn btn-success btn-md nuevo" type="submit">
  		<span class="glyphicon glyphicon-plus" >&nbspNuevo</span>
  	</button>
  </form>
  <br>
  <p>Busque por algun dato del Tipo de Elemento:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  
  <table class="table table-striped">  
      <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Max Reservas Pendientes&nbsp<span class="glyphicon glyphicon-info-sign"  data-toggle="tooltip" title="es la cantidad máxima de elementos de este tipo que cada usuario puede tener pendiente a futuro">
        </span></th>
        <th>Max Horas de Reserva&nbsp<span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" title="límite máximo de tiempo de reserva (en horas) de este tipo de elemento">
        </span></th>
        <th>Max Dias de Anticipacion&nbsp<span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" title="cantidad máxima de días de anticipación para reservar este tipo de elemento">
        </span></th>
        <th>Restringido a Encargados?</th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<TipoDeElemento> listaTipos= (ArrayList<TipoDeElemento>)request.getAttribute("listaTipos");
			for(TipoDeElemento te : listaTipos){
		%>
		<tr>
			<td><%=te.getId() %></td>			
			<td><%=te.getNombre() %></td>
			<td><%=te.getCant_max_res_pen() %></td>
			<td><%=te.getLimite_horas_res() %></td>	
			<td><%=te.getDias_max_anticipacion() %></td>	
			<td><%=te.isOnly_encargados()?"Si":"No" %></td>											
			<td>
			
				<form  id=<%=te.getId()%> name="myForm" action="" method="post">
					<input type="hidden" name="id" value="<%=te.getId() %>" >
					<input type="hidden" name="nombre" value="<%=te.getNombre()%>" >		
					<input type="hidden" name="cant_max_res_pen" value="<%=te.getCant_max_res_pen() %>" >		
					<input type="hidden" name="limite_horas_res" value="<%=te.getLimite_horas_res() %>" >		
					<input type="hidden" name="dias_max_anticipacion" value="<%=te.getDias_max_anticipacion() %>" >		
					<input type="hidden" name="only_encargados" value="<%=te.isOnly_encargados() %>" >				
					<button  class="btn btn-info btn-md editar" type="submit"  onclick="javascript: submitForm('ServletFormsTiposDeElementos?accion=modificacion',<%=te.getId()%>)" data-toggle="tooltip" title="modificar"><span class="glyphicon glyphicon-pencil"></span></button>	
					<button class="btn btn-danger eliminar" type="submit" onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=baja',<%=te.getId()%>)"  data-toggle="tooltip" title="eliminar"><span class="glyphicon glyphicon-trash"></span></button>	
			
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

</body>
</html>