<!DOCTYPE html>
<html lang="es">
<head>
  <title>Bootstrap Example</title>
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
      <li><a href="#">Inicio</a></li>
      <li><a href="#">Reservar</a></li>
      <li><a href="#">Elementos</a></li>
      <li  class="active"><a href="#">Usuarios</a></li>
    </ul>
    <form class="navbar-form navbar-left">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit">
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
  </div>
</nav>

USUARIO GUARDADO CORRECTAMENTE ------ HAY QUE HACER UNA LINDA PAGINA ACA

La fecha y hora del sistema es <%= new java.util.Date() %>



<div class="container">
  <h3>Navbar Forms</h3>
  <p>Use the .navbar-form class to vertically align form elements (same padding as links) inside the navbar.</p>
  <p>The .input-group class is a container to enhance an input by adding an icon, text or a button in front or behind it as a "help text".</p>
  <p>The .input-group-btn class attaches a button next to an input field. This is often used as a search bar:</p>
</div>

</body>
</html>