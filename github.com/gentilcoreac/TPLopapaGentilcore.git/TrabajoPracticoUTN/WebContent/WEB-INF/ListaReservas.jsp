<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Elemento"%>
<%@page import="business.entities.Persona"%>
<%@page import="business.entities.TipoDeElemento"%>
<%@page import="business.entities.Reserva"%>
<%@page import="tools.Campo"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Reservas</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEycHgiIGhlaWdodD0iNTEycHgiPgo8ZyB0cmFuc2Zvcm09Im1hdHJpeCgxLjI1IDAgMCAtMS4yNSAwIDQ1KSI+Cgk8Zz4KCQk8Zz4KCQkJPHBhdGggc3R5bGU9ImZpbGw6IzNCODhDMzsiIGQ9Ik00MDkuNi0zMjguMDg5YzAtMjUuMTM0LTIwLjM3OC00NS41MTEtNDUuNTExLTQ1LjUxMUg0NS41MTFDMjAuMzc4LTM3My42LDAtMzUzLjIyMiwwLTMyOC4wODkgICAgIFYtOS41MTFDMCwxNS42MjIsMjAuMzc4LDM2LDQ1LjUxMSwzNmgzMTguNTc4QzM4OS4yMjIsMzYsNDA5LjYsMTUuNjIyLDQwOS42LTkuNTExVi0zMjguMDg5eiIvPgoJCQk8cGF0aCBzdHlsZT0iZmlsbDojRkZGRkZGOyIgZD0iTTE3NS41MzYtMTUzLjg5NWgzNi4zMjljMTkuMzk5LDAsMzIuODI1LDExLjYzOSwzMi44MjUsMzEuMzkxICAgICBjMCwyMC4xMDUtMTMuNDI2LDMxLjA1LTMyLjgyNSwzMS4wNWgtMzYuMzI5Vi0xNTMuODk1eiBNMTIyLjYxOC03MC4yOTFjMCwxNi45Myw5LjUyMywyNy41MjMsMjcuMTU5LDI3LjUyM2g2Mi4wODkgICAgIGM1NC4zMjksMCw4Ny44NDgtMjQuMzQ4LDg3Ljg0OC03OS43MzVjMC0zOC43OTgtMjkuMjg2LTYxLjAzLTY1LjI3NC02Ni42NzRsNTkuOTg0LTY0LjIwNSAgICAgYzQuOTI3LTUuMjkxLDcuMDQzLTEwLjU4MSw3LjA0My0xNS41MTljMC0xMy43NTYtMTAuOTM0LTI3LjE1OS0yNi40NDItMjcuMTU5Yy02LjM2LDAtMTQuODI1LDIuNDY5LTIwLjQ2OSw5LjE3bC03OC4zMjUsOTQuODkxICAgICBoLTAuNjk0di03Ni41NWMwLTE3LjY0Ny0xMS4yODctMjcuNTExLTI2LjQ2NS0yNy41MTFjLTE1LjE2NywwLTI2LjQ1Myw5Ljg2NS0yNi40NTMsMjcuNTExICAgICBDMTIyLjYxOC0yNjguNTQ5LDEyMi42MTgtNzAuMjkxLDEyMi42MTgtNzAuMjkxeiIvPgoJCTwvZz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/listado.css">
  <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/listado.js"></script>
  <script src="scripts/moment.min.js"></script>
  <script src="scripts/bootstrap-datetimepicker.min.js"></script>	
  <script>
	  <%if(!request.getAttribute("rselbusqueda").equals("ocultar")){ %>
	  $(document).ready(function(){
		  /* $('#busquedaavanzada').collapse({'toggle': hide}); */  
		  $('#rselbusqueda').val('<%=request.getAttribute("rselbusqueda")%>');
		  $.viewMap = {
				    'POR_IDRESERVA' : $('#poridreserva'),
				    'POR_IDELEMENTO' : $('#poridelemento'),
				    'POR_IDPERSONA' : $('#poridpersona'),
				    'PENDIENTES' : $('#pendientes'),
				    'VENCIDAS' : $('#vencidas'),
				    'TRAER_TODAS' : $('#traertodas')
				  };
		  
		  $.viewMap[$("#rselbusqueda").val()].show();
		});
	  <%}
	  else{%>
	  $(document).ready(function(){
		  $('#rselbusqueda').val('POR_IDRESERVA');
		  $('#poridreserva').show();
	  });
	  <%}%>
  </script>
  <script>
	  $(document).ready(function() {
		  $.viewMap = {
				    'POR_IDRESERVA' : $('#poridreserva'),
				    'POR_IDELEMENTO' : $('#poridelemento'),
				    'POR_IDPERSONA' : $('#poridpersona'),
				    'PENDIENTES' : $('#pendientes'),
				    'VENCIDAS' : $('#vencidas'),
				    'TRAER_TODAS' : $('#traertodas')
				  };
	
		  $('#rselbusqueda').change(function() {
		    // hide all
		    $.each($.viewMap, function() { this.hide(); });
		    // show current
		    $.viewMap[$(this).val()].show();
		    
		  });
		}); /*para hacer aparecer y desaparecer la busqueda avanzada */
  </script>
</head>
<body >



<%
if(session.getAttribute("user")==null){
	response.sendRedirect("Login.html");
}
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Start">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Reservas<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="ServletListaReservas">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="ServletFormsReservas?accion=alta">Hacer Reserva</a></li>
            <%Persona per=((Persona)request.getSession().getAttribute("user"));
           	  String categoria=per.getCategoria().getDescripcion();
           	if(categoria.equals("Administrador")){
              %>
            <li><a href="ServletFormsReservas?accion=cerrar">Cerrar Reserva</a></li>
            <% }%>
            <li><a href="ServletFormsReservas?accion=baja">Borrar Reserva</a></li>
          </ul>
        </li>	
      
      	<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Elementos<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="ServletListaElementos">Listado</a></li>
            <%if(categoria.equals("Administrador")){ %>
            <li class="divider"></li>            
            <li><a href="ServletFormsElementos?accion=alta">Agregar Elemento</a></li>
            <li><a href="ServletFormsElementos?accion=modificacion">Editar Elemento</a></li>
            <li><a href="ServletFormsElementos?accion=baja">Borrar Elemento</a></li>
            <%} %>
          </ul>
        </li>
        
    <%	
    	if(categoria.equals("Administrador")){ %>
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Usuarios <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="ServletListaUsuarios">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="ServletFormsUsuarios?accion=alta">Agregar usuario</a></li>
            <li><a href="ServletFormsUsuarios?accion=modificacion">Editar usuario</a></li>
            <li><a href="ServletFormsUsuarios?accion=baja">Borrar usuario</a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Tipos de Elementos<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="ServletListaTiposDeElementos">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="ServletFormsTiposDeElementos?accion=alta">Agregar Tipo de Elemento</a></li>
            <li><a href="ServletFormsTiposDeElementos?accion=modificacion">Editar Tipo de Elemento</a></li>
            <li><a href="ServletFormsTiposDeElementos?accion=baja">Borrar Tipo de Elemento</a></li>
          </ul>
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
  <h2>Lista de Reservas</h2>
  <br>
  
  <form method="post" action="ServletFormsReservas?accion=alta">
  	<button class="btn btn-success btn-md nuevo" type="submit">
  		<span class="glyphicon glyphicon-plus" >&nbspNueva</span>
  	</button>
  </form>
  
  <br>
  
  <button id="btnbusquedaavanzada" data-toggle="collapse" data-target="#busquedaavanzada">Busqueda Avanzada</button>
    <div id="busquedaavanzada" class="collapse" >
    	<div id="contenedor" class="contenedor">
    	<form name="formba" id="formba" method="post" action="" class="form">
      	 <table>
       	  <tr>
       	  <td>
	       <select class="form-control" id="rselbusqueda" name="rselbusqueda">
	       <%for(Campo.TipoBusquedaR tb:Campo.TipoBusquedaR.values()){ 
	    	  if(tb.equals(Campo.TipoBusquedaR.POR_IDPERSONA) && !per.getCategoria().getDescripcion().equals("Administrador")){
		      continue;
		    }
		    else{%>
		      <option value="<%=tb.name() %>" ><%= tb %></option>
		    <% }} %>
		   </select>      
	      </td>
	   	  <td align="center" valign="bottom">
	   	   &nbsp
	       <button formnovalidate type="submit" class="btn btn-default btn-md" onclick="javascript: submitForm('ServletListaReservas?accion=consulta','formba')">
	       <span class="glyphicon glyphicon-search"></span>
	       </button>
	      </td>
		  </tr>
         </table>
         <br>    
         <div id="poridreserva" class="collapse">
          <label for="inputbrporidreserva">ID Reserva:</label>
		  <input name="brporidreserva" type="text" class="form-control" id="inputbrporidreserva" value="<%=request.getAttribute("brporidreserva")%>" pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" >
		 </div>
		 <div id="poridelemento"  class="collapse">
		  <label for="inputbrporidelemento">ID Elemento:</label>
		  <input name="brporidelemento" type="text" class="form-control" id="inputbrporidelemento" value="<%=request.getAttribute("brporidelemento") %>" pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}">
		 </div>
		 <div id="poridpersona"  class="collapse">
		  <label for="inputbrporidpersona">ID Persona:</label>
		  <input name="brporidpersona" type="text" class="form-control" id="inputbrporidpersona" value="<%=request.getAttribute("brporidpersona") %>" pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}">
		 </div>
		 <div id="pendientes"  class="collapse">
		  
		 </div>
		 
		 <div id="vendidas"  class="collapse">    
		   
		 </div>
		 <div id="traertodas"  class="collapse">
		 
		 </div>
     </form><!-- fin form -->
	 </div><!-- fin contenedor -->
   
	<hr />
	</div><!-- fin busqueda avanzada -->
  
  <br><br>
  <p>Busque por algun dato de la Reserva:</p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
 

  
  <table class="table table-striped" >  
      <thead>
      <tr>
        <th>ID Reserva</th>
        <th>ID Persona</th>
        <th>ID Elemento</th>
        <th>Detalle</th>
        <th>Fecha Reserva Hecha</th>
        <th>Fecha Desde</th>
        <th>Fecha Hasta</th>
        <th>Fecha Entrega</th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Reserva> listaReservas= (ArrayList<Reserva>)request.getAttribute("listaReservas");
			for(Reserva r : listaReservas){
		%>
		<tr>
			<td><%=r.getId_reserva() %></td>			
			<td><%=r.getPersona().getId() %></td>
			<td><%=r.getElemento().getId_elemento() %></td>
			<td><button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="<%="#myModal"+String.valueOf(r.getId_reserva())%>">Ver</button>
				<div class="modal fade" id="<%="myModal"+String.valueOf(r.getId_reserva())%>" role="dialog">
			     <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Reserva<%=" "+String.valueOf(r.getId_reserva()) %></h4>
			        </div>
			        <div class="modal-body">
			          <p><%=r.getDetalle().isEmpty()?"Sin detalle":r.getDetalle() %></p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			        </div>
			      </div>
			    </div>
			</td>
			<td><%=String.valueOf(r.getFecha_hora_reserva_hecha()) %></td>
			<td><%=String.valueOf(r.getFecha_hora_desde_solicitada()) %></td>
			<td><%=String.valueOf(r.getFecha_hora_hasta_solicitada()) %></td>
			<td><%=String.valueOf(r.getFecha_hora_entregado()==null?"No Entregado":r.getFecha_hora_entregado()) %></td>	
													
			<td>
			
				<form  id=<%=r.getId_reserva()%> name="myForm" action="" method="post" >
					<input type="hidden" name="idreserva" value="<%=r.getId_reserva() %>" >
					<input type="hidden" name="idpersona" value="<%=r.getPersona().getId()%>" >		
					<input type="hidden" name="idelemento" value="<%=r.getElemento().getId_elemento()%>" >
					<%SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); %>
					<input type="hidden" name="fechareservahecha" value="<%=r.getFecha_hora_reserva_hecha()==null?null:formatter.format(r.getFecha_hora_reserva_hecha()) %>" >
					<input type="hidden" name="fechareservadesde" value="<%=r.getFecha_hora_desde_solicitada()==null?null:formatter.format(r.getFecha_hora_desde_solicitada())%>" >		
					<input type="hidden" name="fechareservahasta" value="<%=r.getFecha_hora_hasta_solicitada()==null?null:formatter.format(r.getFecha_hora_hasta_solicitada())%>" >			
					<input type="hidden" name="fechareservaentrega" value="<%=r.getFecha_hora_entregado()==null?null:formatter.format(r.getFecha_hora_entregado())%>" >		
					<input type="hidden" name="detalle" value="<%=r.getDetalle()%>" >			
					<%if(categoria.equals("Administrador")){ %>
					<button class="btn btn-info btn-md cerrar" type="submit"  onclick="javascript: submitForm('ServletFormsReservas?accion=cerrar',<%=r.getId_reserva()%>)" data-toggle="tooltip" title="cerrar"><span class="glyphicon glyphicon-folder-open"></span></button>	
					<%} %>
					<button class="btn  btn-danger eliminar" type="submit" onclick="javascript: submitForm('ServletABMCReserva?accion=baja',<%=r.getId_reserva()%>)"  data-toggle="tooltip" title="eliminar"><span class="glyphicon glyphicon-trash"></span></button>	
					
								
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