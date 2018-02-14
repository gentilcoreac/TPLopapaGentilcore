<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Elemento"%>
<%@page import="business.entities.Persona"%>
<%@page import="business.entities.TipoDeElemento"%>
<%@page import="business.entities.Reserva"%>
<%@page import="business.logic.CtrlReservaLogic"%>
<%@page import="tools.Campo"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Reservas</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
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
  <h2>Lista de Reservas</h2>
  <br>
  <br>
  <br>
<!--   <form method="post" action="ServletFormsReservas?accion=alta">
  	<button class="btn btn-success btn-md nuevo" type="submit">
  		<span class="glyphicon glyphicon-plus" >&nbspNueva</span>
  	</button>
  </form> -->
  
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
	       <button id="btnlupa" formnovalidate type="submit" class="btn btn-default btn-md" onclick="javascript: submitForm('ServletListaReservas?accion=consulta','formba')">
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
  <p><b>Busque por algun dato de la Reserva:</b></p> 
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
			<%if(((Persona)session.getAttribute("user")).getCategoria().getDescripcion().equals("Administrador")){ %>			
			<td><a href="ServletListaUsuarios?accion=consulta&dni=<%=r.getPersona().getDni()%>"><%=r.getPersona().getId() %></a></td>
			<%}else{ %>
			<td><%=r.getPersona().getId()%></td>
			<%} %>
			<td><a href="ServletListaElementos?accion=consulta&eselbusqueda=POR_ID&beporid=<%=r.getElemento().getId_elemento()%>"><%=r.getElemento().getId_elemento() %></a></td>
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
				    <input type="hidden" name="nombreusulistadores" value="<%=r.getPersona().getNombre()%>" >
				    <input type="hidden" name="apellidousulistadores" value="<%=r.getPersona().getApellido()%>" >
				    <input type="hidden" name="dniusulistadores" value="<%=r.getPersona().getDni()%>" >
				    <input type="hidden" name="nombreelelistadores" value="<%=r.getElemento().getNombre()%>" >
				    <input type="hidden" name="tipoelelistadores" value="<%=r.getElemento().getTipo().getNombre()%>" >									
					<%if(categoria.equals("Administrador")){ %>
					<button class="btn btn-info btn-md cerrar" type="submit"  onclick="javascript: submitForm('ServletFormsReservas?accion=cerrar',<%=r.getId_reserva()%>)" data-toggle="tooltip" title="cerrar"><span class="glyphicon glyphicon-folder-open"></span></button>	
					<%} %>
					<%if(categoria.equals("Administrador") || new CtrlReservaLogic().isReservaPendiente(r)){ %>
					<button class="btn  btn-danger eliminar" type="submit" onclick="javascript: submitForm('ServletABMCReserva?accion=baja',<%=r.getId_reserva()%>)"  data-toggle="tooltip" title="eliminar"><span class="glyphicon glyphicon-trash"></span></button>	
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