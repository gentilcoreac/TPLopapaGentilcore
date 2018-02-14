<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Elemento"%>
<%@page import="business.entities.Persona"%>
<%@page import="business.entities.TipoDeElemento"%>
<%@page import="tools.Campo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Lista Elementos</title>
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
	  <%if(!request.getAttribute("eselbusqueda").equals("ocultar")){ %>
	  $(document).ready(function(){
		  /* $('#busquedaavanzada').collapse({'toggle': hide}); */  
		  $('#eselbusqueda').val('<%=request.getAttribute("eselbusqueda")%>');
		  $.viewMap = {
				    'POR_ID' : $('#porid'),
				    'POR_NOMBRE' : $('#pornombre'),
				    'POR_TIPO' : $('#portipo'),
				    'POR_NOMBRE_Y_TIPO' : $('#pornombreytipo'),
				    'POR_TIPO_Y_FH' : $('#portipoyfh'),
				    'TRAER_TODOS' : $('#traertodos')
				  };
		  
		  $.viewMap[$("#eselbusqueda").val()].show();
		});
	  <%}
	  else{%>
	  $(document).ready(function(){
		  $('#eselbusqueda').val('POR_ID');
		  $('#porid').show();
	  });
	  <%}%>
  </script>
  <script>
	  $(document).ready(function() {
		   $.viewMap = {
			    'POR_ID' : $('#porid'),
			    'POR_NOMBRE' : $('#pornombre'),
			    'POR_TIPO' : $('#portipo'),
			    'POR_NOMBRE_Y_TIPO' : $('#pornombreytipo'),
			    'POR_TIPO_Y_FH' : $('#portipoyfh'),
			    'TRAER_TODOS' : $('#traertodos')
			  };
	
		  $('#eselbusqueda').change(function() {
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
  <h2>Lista de Elementos</h2>
  <br>
  <%if(categoria.equals("Administrador")){ %>
  <form method="post" action="ServletFormsElementos?accion=alta">
  	<button class="btn btn-success btn-md nuevo" type="submit">
  		<span class="glyphicon glyphicon-plus" >&nbspNuevo</span>
  	</button>
  </form>
  <%} %>
  <br>
  
  <button id="btnbusquedaavanzada" data-toggle="collapse" data-target="#busquedaavanzada">Busqueda Avanzada</button>
    <div id="busquedaavanzada" class="collapse" >
    	<div id="contenedor" class="contenedor">
    	<form name="formba" id="formba" method="post" action="" class="form">
      	 <table>
       	  <tr>
       	  <td>
	       <select class="form-control" id="eselbusqueda" name="eselbusqueda">
	       <%for(Campo.TipoBusquedaE tb:Campo.TipoBusquedaE.values()){ %>
		    <option value="<%=tb.name() %>" ><%= tb %></option>
		    <%} %>
		    <!-- <option selected value="porid">Por Id</option>
		    <option value="pornombre">Por Nombre</option>
		    <option value="portipo">Por Tipo</option>
		    <option value="pornombreytipo">Por Nombre y Tipo</option>
		    <option value="portipoyfh">Por Tipo y Fecha Hora</option>
		    <option value="traertodos">Traer Todos</option> -->
		   </select>      
	      </td>
	   	  <td align="center" valign="bottom">
	   	   &nbsp
	       <button  id="btnlupa" formnovalidate type="submit" class="btn btn-default btn-md" onclick="javascript: submitForm('ServletListaElementos?accion=consulta','formba');">
	       <span class="glyphicon glyphicon-search"></span>
	       </button>
	      </td>
		  </tr>
         </table>
         <br>    
         <div id="porid" class="collapse">
          <label for="inputbeporid">ID:</label>
		  <input name="beporid" type="text" class="form-control" id="inputbeporid" value="<%=request.getAttribute("beporid")%>" pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" >
		 </div>
		 <div id="pornombre"  class="collapse">
		  <label for="inputbepornombre">Nombre:</label>
		  <input name="bepornombre" type="text" class="form-control" id="inputbepornombre" value="<%=request.getAttribute("bepornombre") %>" >
		 </div>
		 <div id="portipo"  class="collapse">
		 <label for="inputbeportipo">Tipo:</label>
		  <select class="form-control" name="beportipo" id="inputbeportipo">
		  <%for(TipoDeElemento te:(ArrayList<TipoDeElemento>)request.getAttribute("tiposelementos")){ %>
		    <option value=<%=te.getNombre() %> <%if(request.getAttribute("beportipo").equals(te.getNombre())){ %>selected<%} %>><%= te.getNombre() %></option>
		    <%} %>
		  </select>
		 </div>
		 <div id="pornombreytipo"  class="collapse">
		  <label for="inputbepornombreytipo">Nombre:</label>
		  <input name="bepornombreytipo" type="text" class="form-control" id="inputbepornombreytipo" value="<%=request.getAttribute("bepornombreytipo") %>">
		  <label for="inputbespornombreytipo">Tipo:</label>
		  <select class="form-control" name="bespornombreytipo" id="inputbespornombreytipo">
		  <%for(TipoDeElemento te:(ArrayList<TipoDeElemento>)request.getAttribute("tiposelementos")){ %>
		    <option value=<%=te.getNombre() %> <%if(request.getAttribute("bespornombreytipo").equals(te.getNombre())){ %>selected<%} %>><%= te.getNombre() %></option>
		    <%} %>
		  </select>
		 </div>
		 
		 <div id="portipoyfh"  class="collapse">    
		   <label for="inputbedtportipoyfh">Fecha y Hora:</label>       
           <div class='input-group date' id='datetimepicker1'>
               <input type='text' class="form-control" name="bedtportipoyfh" id="inputbedtportipoyfh" <%if(request.getAttribute("bedtportipoyfh")!=null){ %>value="<%=request.getAttribute("bedtportipoyfh")%>"<%} %>/>
               <span class="input-group-addon">
                   <span class="glyphicon glyphicon-calendar"></span>
               </span>
           </div>
           <label for="inputbesportipoyfh">Tipo:</label>
		   <select class="form-control" name="besportipoyfh" id="inputbesportipoyfh">
		   <%for(TipoDeElemento te:(ArrayList<TipoDeElemento>)request.getAttribute("tiposelementos")){ %>
		    <option value=<%=te.getNombre() %> <%if(request.getAttribute("besportipoyfh").equals(te.getNombre())){ %>selected<%} %>><%= te.getNombre() %></option>
		    <%} %>
		  </select>
		 </div>
		 <div id="traertodos"  class="collapse">
		 </div>
     </form><!-- fin form -->
	 </div><!-- fin contenedor -->
   
	<hr />
	</div><!-- fin busqueda avanzada -->
  
  <br><br>
  <p><b>Busque por algun dato del Elemento:</b></p>  
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
 

  
  <table class="table table-striped" >  
      <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <th>Restringido a Encargados?</th>
      </tr>
    </thead>
    <tbody id="myTable">
		<%
			ArrayList<Elemento> listaElementos= (ArrayList<Elemento>)request.getAttribute("listaElementos");
			for(Elemento e : listaElementos){
		%>
		<tr>
			<td><%=e.getId_elemento() %></td>			
			<td><%=e.getNombre() %></td>
			<td><%=e.getTipo().getNombre() %></td>
			<td><%=e.getTipo().isOnly_encargados()?"Si":"No" %></td>											
			<td>
			
				<form  id=<%=e.getId_elemento()%> name="myForm" action="" method="post" >
					<input type="hidden" name="id" value="<%=e.getId_elemento() %>" >
					<input type="hidden" name="nombre" value="<%=e.getNombre()%>" >		
					<input type="hidden" name="tipoelemento" value="<%=e.getTipo().getNombre()%>" >			
					<input type="hidden" name="limite_horas_res" value="<%=e.getTipo().getLimite_horas_res() %>" >		
					<input type="hidden" name="dias_max_anticipacion" value="<%=e.getTipo().getDias_max_anticipacion() %>" >		
					<%if(!categoria.equals("Encargado") && e.getTipo().isOnly_encargados()){ %>
					<button class="btn reservar btn-md"  type="button" data-toggle="tooltip" title="necesita ser encargado para reservar este elemento"><span class="glyphicon glyphicon-calendar"></span></button>	
					<%}else{ %>
					<button class="btn btn-primary reservar btn-md" type="submit"  onclick="javascript: submitForm('ServletFormsReservas?accion=alta',<%=e.getId_elemento()%>)" data-toggle="tooltip" title="reservar elemento"><span class="glyphicon glyphicon-calendar"></span></button>	
					<%} %>
					<%if(categoria.equals("Administrador")){ %>
					<button class="btn btn-info btn-md editar" type="submit"  onclick="javascript: submitForm('ServletFormsElementos?accion=modificacion',<%=e.getId_elemento()%>)" data-toggle="tooltip" title="modificar"><span class="glyphicon glyphicon-pencil"></span></button>	
					<button class="btn  btn-danger eliminar" type="submit" onclick="javascript: submitForm('ServletABMCElemento?accion=baja',<%=e.getId_elemento()%>)"  data-toggle="tooltip" title="eliminar"><span class="glyphicon glyphicon-trash"></span></button>	
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
