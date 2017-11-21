<%@page import="business.entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">MyReserva</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Reservas<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Agregar Reserva</a></li>
            <li><a href="#">Cerrar Reserva</a></li>
            <li><a href="#">Borrar Reserva</a></li>
          </ul>
        </li>	
      
      	<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Elementos<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Agregar Elemento</a></li>
            <li><a href="#">Editar Elemento</a></li>
            <li><a href="#">Borrar Elemento</a></li>
          </ul>
        </li>
        
    <%	Persona per=((Persona)request.getSession().getAttribute("user"));
    	String categoria=per.getCategoria().getDescripcion();
    	if(categoria.equals("Administrador")){ %>
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Usuarios <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Agregar usuario</a></li>
            <li><a href="#">Editar usuario</a></li>
            <li><a href="#">Borrar usuario</a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false">Tipos de Elementos<span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Listado</a></li>
            <li class="divider"></li>            
            <li><a href="#">Agregar Tipo de Elemento</a></li>
            <li><a href="#">Editar Tipo de Elemento</a></li>
            <li><a href="#">Borrar Tipo de Elemento</a></li>
          </ul>
        </li>
	<%}%>
    </ul>

    <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                aria-expanded="false"><span class="glyphicon glyphicon-user"><%=" "+per.getNombre()+","+per.getApellido()%></span><span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="Perfil.jsp" >Perfil</a></li>
            <li class="divider"></li>            
            <li><a href="Login.html">Salir</a></li>
          </ul>
        </li>
      <!--  <li><a href="Login.html"><span class="glyphicon glyphicon-user"><%=" "+per.getNombre()+","+per.getApellido()%></span> </a></li>-->
    </ul>
  </div>
</nav>

<div class="container">
  <h2>Novedades</h2>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">

      <div class="item active">
        <img  class="img-responsive img-rounded" src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Auto_union.jpg/1200px-Auto_union.jpg" alt="Auto" style="width:100%;height:500px">
        <div class="carousel-caption">
          <h3>Auto en reparación</h3>
          <p>En el mes de diciembre ya vuelve con nosotros, lo estamos poniendo en condiciones estupendas!</p>
        </div>
      </div>

      <div class="item">
        <img class="img-responsive img-rounded" src="imagenes/homerocompu.jpg" alt="Compu Asus" style="width:100%;height:500px">
        <div class="carousel-caption">
          <h3>Tenemos 10 Asus nuevas!</h3>
          <p>Luego de un gran esfuerzo por parte de la empresa compramos 10 estupendas notebooks. Ya podes reservar la tuya!!</p>
        </div>
      </div>
    
      <div class="item">
        <img  class="img-rounded" src="http://www.digitalavmagazine.com/wp-content/uploads/2012/09/Pro9000.jpg" alt="New York" style="width:100%;height:500px">
        <div class="carousel-caption">
          <h3>Un proyector para el SUM</h3>
          <p>Incorporamos un proyector Epson para el SUM!</p>
        </div>
      </div>
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
</body>
</html>