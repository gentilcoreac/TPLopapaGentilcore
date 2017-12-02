<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/formPersona.css" >
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
  <script type="text/javascript">
   	function submitForm(met) {
   		//document.myForm.action=met;
        /* document.getElementbyName("contrasenia").val(document.getElementbyName("passqueseborra"));
        document.getElementbyName("contraseborra").remove();
        document.getElementbyName("passqueseborra").remove(); */

   		document.getElementById("myForm").action =met;
   		document.getElementById("myForm").submit();
       }

  </script>
<title>Formulario</title>
</head>
<body>

<%
switch(request.getParameter("accion")){
	
case "alta":%>
<div class="container-form-per">
  <h2>Agregar Persona</h2>

  
  <form name="myForm" id="myForm" class="form-per" action="" method="post" autocomplete="off"> 
  	

  	<div class="form-group">
      <label for="inputdni">Dni:</label>
      <input name="dni" type="text" class="form-control" id="inputdni"  required="" >
    </div>

    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input name="apellido" type="text" class="form-control" id="inputapel" required=""    >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required=""   >
    </div>
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr" required=""   >
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="text" autocomplete="off" class="form-control" id="inputpwd" required >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail"  required pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Formato de email invalido')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select name="categoria" class="form-control" id="inputCategoriaLista" required=""   >
        <option>Administrador</option>
        <option>Encargado</option>
        <option>Usuario</option>
      </select>
    </div>   
	<div class="checkbox">
	  <label for="inputhabilitado"></label>
	  <input name="habilitado" type="checkbox"  id="inputhabilitado">Habilitado</input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCPersona?accion=alta')">Agregar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('<%=request.getAttribute("urlcancelar")%>')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "modificacion":
%>
<div class="container-form-per">
  <h2>Modificar Persona</h2>

  
  <form name="myForm" id="myForm" class="form-per" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputdni">Dni:</label>
      	  <input name="dni" type="text" class="form-control" id="inputdni"  required="" value=<%=request.getAttribute("dni")%> >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCPersona?accion=consulta&fin=modificacion')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
<%--     <div class="form-group">
      <label for="inputid">Id</label><input name="id" type="text" class="form-control" id="inputid"  required="" value=<%=request.getAttribute("id")%> >
  	</div> --%>
    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input name="apellido" type="text" class="form-control" id="inputapel" required=""  value=<%=request.getAttribute("apellido") %> >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input name="nombre" type="text" class="form-control" id="inputnombre"  required=""  value=<%=request.getAttribute("nombre") %> >
    </div>
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input name="usuario" type="text" class="form-control" id="inputusr" required="" value=<%=request.getAttribute("usuario") %> >
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input name="contrasenia" type="text" class="form-control" id="inputpwd" required=""  value=<%=request.getAttribute("contrasenia") %> >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input name="email" type="text" class="form-control" id="inputemail"  required=""  value=<%=request.getAttribute("email") %> >
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select name="categoria" class="form-control" id="inputCategoriaLista" required=""  value=<%=request.getAttribute("categoria") %> >
        <option>Administrador</option>
        <option>Encargado</option>
        <option>Usuario</option>
      </select>
    </div>   
	<div class="checkbox">
	  <label for="inputhabilitado"></label>
	  <input name="habilitado" type="checkbox" value=<%=request.getAttribute("habilitado") %> <%if(request.getAttribute("habilitado").equals("true")){ %>checked<%} %> id="inputhabilitado">Habilitado</input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCPersona?accion=modificacion')">Guardar Cambios</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('<%=request.getAttribute("urlcancelar")%>')">Cancelar</button>
	</div>
	</form>
</div>
<% 
break;
case "baja":
%>
<div class="container-form-per">
  <h2>Eliminar Persona</h2>

  
  <form name="myForm" id="myForm" class="form-per" action="" method="post"> 
  	

  	<div class="form-group">
      <table>
       <tr><td>
      	  <label for="inputdni">Dni:</label>
      	  <input name="dni" type="text" class="form-control" id="inputdni"  required="" value=<%=request.getAttribute("dni")%> >
		  </td>
	   	  <td align="center" valign="bottom">
	   	  &nbsp
	   	  <button formnovalidate onclick="javascript: submitForm('ServletABMCPersona?accion=consulta&fin=baja')" type="submit" class="btn btn-default btn-lg">
	      	<span class="glyphicon glyphicon-search"></span>
	      </button>
	   </td></tr> 
      </table>
    </div>
    <div class="form-group">
      <label for="inputapel">Apellido:</label>
      <input disabled name="apellido" type="text" class="form-control" id="inputapel" required=""  value=<%=request.getAttribute("apellido") %> >
    </div>
    <div class="form-group">
      <label for="inputnombre">Nombre:</label>
      <input disabled name="nombre" type="text" class="form-control" id="inputnombre"  required=""  value=<%=request.getAttribute("nombre") %> >
    </div>
    <div class="form-group">
      <label for="inputusr">Usuario:</label>
      <input disabled name="usuario" type="text" class="form-control" id="inputusr" required="" value=<%=request.getAttribute("usuario") %> >
    </div>
    <div class="form-group">
      <label for="inputpwd">Contraseña:</label>
      <input disabled name="contrasenia" type="text" class="form-control" id="inputpwd" required=""  value=<%=request.getAttribute("contrasenia") %> >
    </div>
    <div class="form-group">
      <label for="inputemail">Email:</label>
      <input disabled name="email" type="text" class="form-control" id="inputemail"  required=""  value=<%=request.getAttribute("email") %> >
    </div>
    <div class="form-group">
      <label for="inputCategoriaLista">Categoria</label>
      <select disabled name="categoria" class="form-control" id="inputCategoriaLista" required=""  value=<%=request.getAttribute("categoria") %> >
        <option>Administrador</option>
        <option>Encargado</option>
        <option>Usuario</option>
      </select>
    </div>   
	<div class="checkbox">
	  <label for="inputhabilitado"></label>
	  <input disabled name="habilitado" type="checkbox" value=<%=request.getAttribute("habilitado") %> <%if(request.getAttribute("habilitado").equals("true")){ %>checked<%} %> id="inputhabilitado">Habilitado</input>
	</div>
	<div class="botones">
    	<button class="boton btn btn-lg btn-primary " onclick="javascript: submitForm('ServletABMCPersona?accion=baja')">Eliminar</button>		
		<button class="boton btn btn-lg btn-default " formnovalidate onclick="javascript: submitForm('<%=request.getAttribute("urlcancelar")%>')">Cancelar</button>
	</div>
	</form>
</div>
<%	
break;
default:
	response.sendRedirect("ServletListaUsuarios");
	break;
}

%>
</body>
</html>