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
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
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
  <h2>Lista de Tipos de Elementos</h2>
  <br>
  <%if(categoria.equals("Administrador")){ %>
  <form method="post" action="ServletFormsTiposDeElementos?accion=alta">
  	<button class="btn btn-success btn-md nuevo" type="submit">
  		<span class="glyphicon glyphicon-plus" >&nbspNuevo</span>
  	</button>
  </form>
  <%}else{ %>
  <br><br>
  <%} %>
  <br>
  <p><b>Busque por algun dato del Tipo de Elemento:</b></p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  
  <table class="table table-striped">  
      <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Res. Pendientes</th>
        <th>Max Res. Pendientes&nbsp<span class="glyphicon glyphicon-info-sign"  data-toggle="tooltip" title="es la cantidad máxima de elementos de este tipo que cada usuario puede tener pendiente a futuro">
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
			int[] respendientes=(int[])request.getAttribute("respendientes");
			int i=-1;
			for(TipoDeElemento te : listaTipos){
				++i;
		%>
		<tr>
			<td><%=te.getId() %></td>			
			<td><%=te.getNombre() %></td>
			<td><%=respendientes[i] %></td>
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
					<%if(categoria.equals("Administrador")){ %>
					<button  class="btn btn-info btn-md editar" type="submit"  onclick="javascript: submitForm('ServletFormsTiposDeElementos?accion=modificacion',<%=te.getId()%>)" data-toggle="tooltip" title="modificar"><span class="glyphicon glyphicon-pencil"></span></button>	
					<button class="btn btn-danger eliminar" type="submit" onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=baja',<%=te.getId()%>)"  data-toggle="tooltip" title="eliminar"><span class="glyphicon glyphicon-trash"></span></button>	
					<%} %>
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