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

//When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};
function scrollFunction() {
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        document.getElementById("btngototop").style.display = "block";
    } else {
        document.getElementById("btngototop").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

/*para mostrar loader*/
$(document).ready(function () {
	$("a:not(a[href='#usudesplegable'])").click(function () {
	    $('.container').hide();
	    $('.loader').show();
	});
});

$(document).ready(function () {
	$("#btnlupa").click(function () {
	    $('.container').hide();
	    $('.loader').show();
	});
});
/* fin loader*/