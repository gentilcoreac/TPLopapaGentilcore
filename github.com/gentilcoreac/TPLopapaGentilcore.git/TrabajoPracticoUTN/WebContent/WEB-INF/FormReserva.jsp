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
  <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/formulario.css" >
  <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" href="css/listado.css">
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
				
				/* defaultDate: new Date(),  */
			    format: 'DD/MM/YYYY HH:mm:ss ',
			    sideBySide: true
			});
		});
  </script>
<title>Formulario</title>
</head>
<body>

<%
if(session.getAttribute("user")==null){
	response.sendRedirect("Login.html");
}
%>
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
    <div class="form-group">
     <label>ID Persona:&nbsp<%=((Persona)session.getAttribute("user")).getId() %></label>
     <label>Dni:&nbsp<%=((Persona)session.getAttribute("user")).getDni() %></label>
     <label>Apellido y Nombre:&nbsp<%=((Persona)session.getAttribute("user")).getApellido()+","+((Persona)session.getAttribute("user")).getNombre() %></label>
     <input name="idpersona" type="hidden" class="form-control" id="inputidpersona"  value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    </div>
    <%-- <%if(per.getCategoria().getDescripcion().equals("Administrador")){ %>
    <div class="form-group">
      <label for="inputidpersona">ID Persona:</label>
      <input name="idpersona" type="text" class="form-control" id="inputidpersona"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    </div>
    <%}else{ %>
	  <input name="idpersona" type="hidden" class="form-control" id="inputidpersona"  value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    <%} %> --%>
    <div class="form-group">
     <label>ID Elemento:&nbsp<%=request.getAttribute("idelelistado") %></label>
     <label>Nombre:&nbsp<%=request.getAttribute("nombreelelistado") %></label>
     <label>Tipo:&nbsp<%=request.getAttribute("tipoelelistado") %></label>
     <input name="idelemento" type="hidden" class="form-control" id="inputidelemento" value="<%=request.getAttribute("idelelistado") %>" >    
    </div>
    <div class="form-group">
     <label>Tiempo maximo de reserva:&nbsp<%=request.getAttribute("limite_horas_res") %>hs</label>
     <label>Cantidad maxima de dias de anticipacion:&nbsp<%=request.getAttribute("dias_max_anticipacion") %>&nbspdias</label>    
    </div>
    <%-- <div class="form-group">
      <label for="inputidelemento">ID Elemento:</label>
      <input name="idelemento" type="text" class="form-control" id="inputidelemento"  required pattern="[1-9][0-9]*" oninvalid="setCustomValidity('Id invalido')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("idelelistado") %>" >    
    </div>   --%>
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
      <textarea name="detalle"  maxlength="140" rows="2" class="form-control" id="inputdetalle"  ></textarea>		 
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCReserva?accion=alta')">Reservar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript:window.history.go(-1);return false;">Cancelar</button>
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
	   	  <button  id="btnlupa" formnovalidate onclick="javascript: submitForm('ServletABMCReserva?accion=consulta&fin=cerrar')" type="submit" class="btn btn-default btn-lg">
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
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript:window.history.go(-1);return false;">Cancelar</button>
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
	   	  <button  id="btnlupa" formnovalidate onclick="javascript: submitForm('ServletABMCReserva?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <%if(per.getCategoria().getDescripcion().equals("Administrador")){ %>
    <div class="form-group">
      <label for="inputidpersona">ID Persona:</label>
      <input readonly name="idpersona" type="text" class="form-control" id="inputidpersona"   value="<%=request.getAttribute("idpersona")%>" >    
    </div>
    <%}else{ %>
	  <input name="idpersona" type="hidden" class="form-control" id="inputidpersona"  value="<%=((Persona)request.getSession().getAttribute("user")).getId()%>" >    
    <%} %>
    <div class="form-group">
      <label for="inputidelemento">ID Elemento:</label>
      <input readonly name="idelemento" type="text" class="form-control" id="inputidelemento"   value="<%=request.getAttribute("idelemento") %>" >    
    </div>
    <div class="form-group">
      <label for="inputfechareservadesde">Fecha-Hora Desde:</label>       
      <div class='input-group date' id='dtpdesdeformeliminar'>
          <input readonly  type='text' class="form-control" name="fechareservadesde" id="inputfechareservadesde" <%if(request.getAttribute("fechareservadesde")!=null){ %>value="<%=request.getAttribute("fechareservadesde")%>"<%}%>/>
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div>  
    <div class="form-group">
      <label for="inputfechareservahasta">Fecha-Hora Hasta:</label>       
      <div class='input-group date' id='datetimepickerhasta'>
          <input readonly  type='text' class="form-control" name="fechareservahasta" id="inputfechareservahasta" <%if(request.getAttribute("fechareservahasta")!=null){ %>value="<%=request.getAttribute("fechareservahasta")%>"<%} %> />
          <span class="input-group-addon">
              <span class="glyphicon glyphicon-calendar"></span>
          </span>
      </div>
    </div> 
    <div class="form-group">
      <label for="inputdetalle">Detalle:</label>
      <textarea readonly name="detalle"  maxlength="140" rows="2" class="form-control" id="inputdetalle"  ><%=request.getAttribute("detalle") %></textarea>		 
    </div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCReserva?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript:window.history.go(-1);return false;">Cancelar</button>
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
<div class="loader"></div>
</body>
</html>