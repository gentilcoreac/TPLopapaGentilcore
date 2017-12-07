/* busca tabla*/
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});





$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
/* fin busca tabla*/

/*habilita tooltips*/
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
/* fin tooltips*/

window.addEventListener('resize', function(event){
	
    $("@media (min-width: 768px)").css("position", "fixed");

});/*para que cuando modifique el tamaño de la ventana el navbar deje de estar fijo*/



function submitForm(met,id) {
		//document.myForm.action=met;
		document.getElementById(id).action =met;
		document.getElementById(id).submit();
  }/*para usar varios botones en un form*/

$(document).ready(function() {
	$('#datetimepicker1').datetimepicker({

		defaultDate: new Date($.now()+5*60*1000),
	    format: 'DD/MM/YYYY HH:mm:ss ',
	    sideBySide: true
	    
	    
	});
});/*para que ande el datetimepicker y tenga el formato que necesitamos*/

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});/*para los tooltips*/

//$(function(){
//	$('#selbusqueda').val("POR_ID");
//});/*para seleccionar id en busqueda avanzada cada vez que se cargue la pagina*/