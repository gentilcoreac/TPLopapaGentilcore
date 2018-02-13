<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="business.entities.Persona" %>
<!DOCTYPE html >
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/formulario.css" >
  <link rel="stylesheet" href="css/listado.css">
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script src="scripts/formulario.js"></script>

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
  <h2>Agregar Tipo De Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post" autocomplete="off"> 
  	

  	<div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required >
    </div>

    <div class="form-group">
      <label for="inputcant_max_res_pen">Max Reservas Pendientes:</label>
      <input name="cant_max_res_pen" type="number" min="1" value="1" class="form-control" id="inputcant_max_res_pen" required  oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}"  >
    </div>
    <div class="form-group">
      <label for="inputlimite_horas_res">Max Horas de Reserva:</label>
      <input name="limite_horas_res" type="number" min="1" value="1" class="form-control" id="inputlimite_horas_res"  required  oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
    <div class="form-group">
      <label for="inputdias_max_anticipacion">Max Dias de Anticipacion:</label>
      <input name="dias_max_anticipacion" type="number" min="1" value="1" class="form-control" id="inputdias_max_anticipacion" required   oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
	<div class="checkbox">
	  <input name="only_encargados" type="checkbox"  id="inputonly_encargados"><b>Restringido a Encargados?</b></input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=alta')">Agregar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript:window.history.go(-1);return false;">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "modificacion": 
%>
<div class="container-form">
  <h2>Modificar Tipo De Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputnombre">Nombre:</label>
      	  <input name="nombre" type="text" class="form-control" id="inputnombre"  required value="<%=request.getAttribute("nombre")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button  id="btnlupa" formnovalidate onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=consulta&fin=modificacion')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputcant_max_res_pen">Max Reservas Pendientes:</label>
      <input name="cant_max_res_pen"  type="number" min="1" class="form-control" id="inputcant_max_res_pen" required oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}"  value="<%=request.getAttribute("cant_max_res_pen") %>" >
    </div>
    <div class="form-group">
      <label for="inputlimite_horas_res">Max Horas de Reserva:</label>
      <input name="limite_horas_res" type="number" min="1"  class="form-control" id="inputlimite_horas_res"  required oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}"  value="<%=request.getAttribute("limite_horas_res") %>" >
    </div>
    <div class="form-group">
      <label for="inputdias_max_anticipacion">Max Dias de Anticipacion:</label>
      <input name="dias_max_anticipacion"  type="number" min="1"  class="form-control" id="inputdias_max_anticipacion" required oninvalid="setCustomValidity('Debe Ingresar un numero mayor a 0')" onchange="try{setCustomValidity('')}catch(e){}" value="<%=request.getAttribute("dias_max_anticipacion") %>" >
    </div>
	<div class="checkbox">
	  <input name="only_encargados" type="checkbox" value=<%=request.getAttribute("only_encargados") %> <%if(request.getAttribute("only_encargados").equals("true")){ %>checked<%} %> id="inputonly_encargados"><b>Restringido a Encargados?</b></input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=modificacion')">Guardar Cambios</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript:window.history.go(-1);return false;">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
default:
	response.sendRedirect("ServletListaTiposDeElementos");
	break;
}
/* case "baja": */
%>
<%-- <div class="container-form">
  <h2>Eliminar Tipo de Elemento</h2>

  
  <form name="myForm" id="myForm" class="form" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputnombre">Nombre:</label>
      	  <input name="nombre" type="text" class="form-control" id="inputnombre"  required value="<%=request.getAttribute("nombre")%>" >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button  id="btnlupa" formnovalidate onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputcant_max_res_pen">Max Reservas Pendientes:</label>
      <input readonly name="cant_max_res_pen"  type="number" min="1"  class="form-control" id="inputcant_max_res_pen" required  value="<%=request.getAttribute("cant_max_res_pen") %>" >
    </div>
    <div class="form-group">
      <label for="inputlimite_horas_res">Max Horas de Reserva:</label>
      <input readonly name="limite_horas_res"  type="number" min="1"  class="form-control" id="inputlimite_horas_res"  required  value="<%=request.getAttribute("limite_horas_res") %>" >
    </div>
    <div class="form-group">
      <label for="inputdias_max_anticipacion">Max Dias de Anticipacion:</label>
      <input readonly name="dias_max_anticipacion"  type="number" min="1"  class="form-control" id="inputdias_max_anticipacion" required value="<%=request.getAttribute("dias_max_anticipacion") %>" >
    </div>
	<div class="checkbox">
	  <input disabled name="only_encargados" type="checkbox" value=<%=request.getAttribute("only_encargados") %> <%if(request.getAttribute("only_encargados").equals("true")){ %>checked<%} %> id="inputonly_encargados"><b>Restringido a Encargados?</b></input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCTipoDeElemento?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript:window.history.go(-1);return false;">Cancelar</button>
	</div>
	</form>
</div>
<%	 --%>
<!-- break;
default:
	response.sendRedirect("ServletListaTiposDeElementos");
	break;
}

%> --%> -->
<br><br>
<div class="loader"></div>
</body>
</html>