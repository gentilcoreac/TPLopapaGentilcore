<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.entities.Persona" %>
<%@page import="business.entities.Reserva" %>
<!DOCTYPE html >
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEycHgiIGhlaWdodD0iNTEycHgiPgo8ZyB0cmFuc2Zvcm09Im1hdHJpeCgxLjI1IDAgMCAtMS4yNSAwIDQ1KSI+Cgk8Zz4KCQk8Zz4KCQkJPHBhdGggc3R5bGU9ImZpbGw6IzNCODhDMzsiIGQ9Ik00MDkuNi0zMjguMDg5YzAtMjUuMTM0LTIwLjM3OC00NS41MTEtNDUuNTExLTQ1LjUxMUg0NS41MTFDMjAuMzc4LTM3My42LDAtMzUzLjIyMiwwLTMyOC4wODkgICAgIFYtOS41MTFDMCwxNS42MjIsMjAuMzc4LDM2LDQ1LjUxMSwzNmgzMTguNTc4QzM4OS4yMjIsMzYsNDA5LjYsMTUuNjIyLDQwOS42LTkuNTExVi0zMjguMDg5eiIvPgoJCQk8cGF0aCBzdHlsZT0iZmlsbDojRkZGRkZGOyIgZD0iTTE3NS41MzYtMTUzLjg5NWgzNi4zMjljMTkuMzk5LDAsMzIuODI1LDExLjYzOSwzMi44MjUsMzEuMzkxICAgICBjMCwyMC4xMDUtMTMuNDI2LDMxLjA1LTMyLjgyNSwzMS4wNWgtMzYuMzI5Vi0xNTMuODk1eiBNMTIyLjYxOC03MC4yOTFjMCwxNi45Myw5LjUyMywyNy41MjMsMjcuMTU5LDI3LjUyM2g2Mi4wODkgICAgIGM1NC4zMjksMCw4Ny44NDgtMjQuMzQ4LDg3Ljg0OC03OS43MzVjMC0zOC43OTgtMjkuMjg2LTYxLjAzLTY1LjI3NC02Ni42NzRsNTkuOTg0LTY0LjIwNSAgICAgYzQuOTI3LTUuMjkxLDcuMDQzLTEwLjU4MSw3LjA0My0xNS41MTljMC0xMy43NTYtMTAuOTM0LTI3LjE1OS0yNi40NDItMjcuMTU5Yy02LjM2LDAtMTQuODI1LDIuNDY5LTIwLjQ2OSw5LjE3bC03OC4zMjUsOTQuODkxICAgICBoLTAuNjk0di03Ni41NWMwLTE3LjY0Ny0xMS4yODctMjcuNTExLTI2LjQ2NS0yNy41MTFjLTE1LjE2NywwLTI2LjQ1Myw5Ljg2NS0yNi40NTMsMjcuNTExICAgICBDMTIyLjYxOC0yNjguNTQ5LDEyMi42MTgtNzAuMjkxLDEyMi42MTgtNzAuMjkxeiIvPgoJCTwvZz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/formulario.css" >
  <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/formulario.js"></script>
  <script src="scripts/moment.min.js"></script>
  <script src="scripts/bootstrap-datetimepicker.min.js"></script>
  <script>
	  $(document).ready(function() {
			$('#datetimepickerdesde').datetimepicker({
	
				defaultDate: new Date($.now()+5*60*1000),
			    format: 'DD/MM/YYYY HH:mm:ss ',
			    sideBySide: true
			});
			$('#datetimepickerhasta').datetimepicker({
				
				/* defaultDate: new Date(), */
			    format: 'DD/MM/YYYY HH:mm:ss ',
			    sideBySide: true
			});
			$('#datetimepickercierre').datetimepicker({
				
				defaultDate: new Date(), 
			    format: 'DD/MM/YYYY HH:mm:ss ',
			    sideBySide: true
			});
			$('#dtpdesdeformeliminar').datetimepicker({
				
				defaultDate: new Date(), 
			    format: 'DD/MM/YYYY HH:mm:ss ',
			    sideBySide: true
			});
		});
  </script>
<title>Formulario</title>
</head>
<body>

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
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Hacer Reserva</a></li>
            <%Persona per=((Persona)request.getSession().getAttribute("user"));
           	  String categoria=per.getCategoria().getDescripcion();
           	if(categoria.equals("Administrador")){
              %>
            <li><a href="#">Cerrar Reserva</a></li>
            <% }%>
            <li><a href="#">Borrar Reserva</a></li>
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
            <li><a href="Redireccionador?destino=WEB-INF/Perfil.jsp" >Perfil</a></li>
            <li class="divider"></li>            
            <li>
	            <form name="myForm" action="Redireccionador" method="post">
	            <input type="hidden" value="si" name="logout">
	            <button  class="btn btn-info">Salir&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
	            </form>
	      	<!--<a href="Redireccionador?destino=Logout.jsp&logout=si">Salir</a>  -->
            </li>
            	
          </ul>
        </li>
    </ul>
  </div>
</nav>

<br><br><br>
<%
switch(request.getParameter("accion")){
case "alta":%>
<div class="container-form">
  <h2>Hacer Reserva</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post" autocomplete="off"> 
  	

  	<div class="form-group">
     <label>ID Reserva:&nbsp<%=request.getAttribute("proxid") %></label>
    </div>
    <%if(per.getCategoria().getDescripcion().equals("Administrador")){ %>
    <div class="form-group">
      <label for="inputidpersona">ID Persona:</label>
      <input name="idpersona" type="text" class="form-control" id="inputidpersona"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    </div>
    <%}else{ %>
	  <input name="idpersona" type="hidden" class="form-control" id="inputidpersona"  value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    <%} %>
    <div class="form-group">
      <label for="inputidelemento">ID Elemento:</label>
      <input name="idelemento" type="text" class="form-control" id="inputidelemento"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("ideledesdelistado") %>" >    
    </div>  
    <div class="form-group">
      <label for="inputfechareservadesde">Fecha-Hora Desde:</label>       
      <div class='input-group date' id='datetimepickerdesde'>
          <input required type='text' class="form-control" name="fechareservadesde" id="inputfechareservadesde" />
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div>
    <div class="form-group">
      <label for="inputfechareservahasta">Fecha-Hora Hasta:</label>       
      <div class='input-group date' id='datetimepickerhasta'>
          <input required type='text' class="form-control" name="fechareservahasta" id="inputfechareservahasta" />
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div>
    <div class="form-group">
      <label for="inputdetalle">Detalle:</label>
      <textarea name="detalle"  maxlength="140" rows="2" class="form-control" id="inputdetalle"  required ></textarea>		 
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCReserva?accion=alta')">Reservar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "cerrar":
%>
<div class="container-form">
  <h2>Cerrar Reserva</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputidreserva">ID Reserva:</label>
      	  <input name="idreserva" type="text" class="form-control" id="inputidreserva"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("idreserva")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCReserva?accion=consulta&fin=cerrar')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>  
    <div class="form-group">
      <label for="inputfechareservaentrega">Fecha-Hora Cierre:</label>       
      <div class='input-group date' id='datetimepickercierre'>
          <input required type='text' class="form-control" name="fechareservaentrega" id="inputfechareservaentrega" <%if(request.getAttribute("fechareservaentrega")!=null){ %>value="<%=request.getAttribute("fechareservaentrega")%>"<%} %> />
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCReserva?accion=cerrar')">Cerrar Reserva</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
   
   </form>
  </div>
<% 
break;
case "baja":
%>
<div class="container-form">
  <h2>Eliminar Reserva</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputidreserva">ID Reserva:</label>
      	  <input name="idreserva" type="text" class="form-control" id="inputidreserva"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("idreserva")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCReserva?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <%if(per.getCategoria().getDescripcion().equals("Administrador")){ %>
    <div class="form-group">
      <label for="inputidpersona">ID Persona:</label>
      <input disabled name="idpersona" type="text" class="form-control" id="inputidpersona"   value="<%=request.getAttribute("idpersona")%>" >    
    </div>
    <%}else{ %>
	  <input name="idpersona" type="hidden" class="form-control" id="inputidpersona"  value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    <%} %>
    <div class="form-group">
      <label for="inputidelemento">ID Elemento:</label>
      <input disabled name="idelemento" type="text" class="form-control" id="inputidelemento"   value="<%=request.getAttribute("idelemento") %>" >    
    </div>
    <div class="form-group">
      <label for="inputfechareservadesde">Fecha-Hora Desde:</label>       
      <div class='input-group date' id='dtpdesdeformeliminar'>
          <input disabled  type='text' class="form-control" name="fechareservadesde" id="inputfechareservadesde" <%if(request.getAttribute("fechareservadesde")!=null){ %>value="<%=request.getAttribute("fechareservadesde")%>"<%}%>/>
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div>  
    <div class="form-group">
      <label for="inputfechareservahasta">Fecha-Hora Hasta:</label>       
      <div class='input-group date' id='datetimepickerhasta'>
          <input disabled  type='text' class="form-control" name="fechareservahasta" id="inputfechareservahasta" <%if(request.getAttribute("fechareservahasta")!=null){ %>value="<%=request.getAttribute("fechareservahasta")%>"<%} %> />
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div> 
    <div class="form-group">
      <label for="inputdetalle">Detalle:</label>
      <textarea disabled name="detalle"  maxlength="140" rows="2" class="form-control" id="inputdetalle"  ><%=request.getAttribute("detalle") %></textarea>		 
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCReserva?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('javascript:window.history.back();')">Cancelar</button>
	</div>
	</form>
</div>
<%	
break;
default:
	response.sendRedirect("ServletListaElementos");
	break;
}

%>
<br><br>
</body>
</html>