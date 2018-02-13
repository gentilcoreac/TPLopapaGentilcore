<%@page import="business.entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Perfil</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/listado.css">
  <link rel="shortcut icon" type="image/x-icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAAbwAAAG8B8aLcQwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAFXSURBVDiNpZI/S0JRGMZ/53TtSteoxKCCCLEhiSIIgoigWhoaKmipj9AoBH6CMAgHae4jRFs0WlFttmRg6Q2lgm5KYmqYeRvCBm/kn57lwPOe5wfvc46Y3TlZqZgiCLhpTroUpk+2GAZwV0wRlC2GfyBKraMqkr2NcQaddgCyxTIX8QzhWJpIMmshyFrD06vh7XegqQqqIunvsrM2OUBofYzJoe76ACG+z1SmyPzuOUuhS65SWQSwMOKqD6hV7r1MwigAUK6Ylrmlg6qcmo2txWF6OmxMe5wAHEaeGgdoqsLyRB8A4Via/bMk+kuh8RVSmSLH188AuBztPLy+/3rvzw4CR3dEH3OMDnSyverF1ibqA6o1mSZ8fFbwH9xg5EpMubvZnLP+OQsgYeSJG3lOb9MAZPIl/AdR7tMFXt5KFoCYCZxa36YJSUD/R16XUpi+FiG6FKbvC7k+b0MfkEYWAAAAAElFTkSuQmCC">
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
  <h2>Datos del Perfil</h2>
  <br>
   <div class="card text-center">
	  <img src="imagenes/img_avatar.png" alt="Avatar" style="width:130px;height:130pz">
	  <div class="container">
	    <h4>
	    	<b><%=((Persona)session.getAttribute("user")).getApellido() %>, 
	    		<%=((Persona)session.getAttribute("user")).getNombre() %>
	    	</b>
	    </h4> 
	    <p><b><%=((Persona)session.getAttribute("user")).getCategoria().getDescripcion() %></b></p> 
	  </div>
  </div> 
  <table class="table table-striped table-responsive">
    <tbody>
      <tr>
        <td><b>Id</b></td>
        <td><%=((Persona)session.getAttribute("user")).getId() %></td>
      </tr>
      <tr>
        <td><b>Dni</b></td>
        <td><%=((Persona)session.getAttribute("user")).getDni() %></td>
      </tr>
      <tr>
        <td><b>Nombre</b></td>
        <td><%=((Persona)session.getAttribute("user")).getNombre() %></td>
      </tr>
      <tr>
        <td><b>Apellido</b></td>
        <td><%=((Persona)session.getAttribute("user")).getApellido() %></td>
      </tr>
      <tr>
        <td><b>Usuario</b></td>
        <td><%=((Persona)session.getAttribute("user")).getUsuario() %></td>
      </tr>
      <tr>
        <td><b>Email</b></td>
        <td><%=((Persona)session.getAttribute("user")).getEmail() %></td>
      </tr>
      <tr>
        <td><b>Categoria</b></td>
        <td><%=((Persona)session.getAttribute("user")).getCategoria().getDescripcion() %></td>
      </tr>
    </tbody>
  </table>
</div>

<div class="loader"></div>
</body>
</html>