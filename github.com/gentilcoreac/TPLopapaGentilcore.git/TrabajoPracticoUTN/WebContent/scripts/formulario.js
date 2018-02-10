function submitForm(met) {
	//document.myForm.action=met;
    /* document.getElementbyName("contrasenia").val(document.getElementbyName("passqueseborra"));
    document.getElementbyName("contraseborra").remove();
    document.getElementbyName("passqueseborra").remove(); */

	document.getElementById("myForm").action =met;
	document.getElementById("myForm").submit();
   }

window.addEventListener('resize', function(event){
	
    $("@media (min-width: 768px)").css("position", "fixed");

});

/*para mostrar loader*/
$(document).ready(function () {
	$("a:not(a[href='#usudesplegable'])").click(function () {
	    $('form').hide();
	    $('.loader').show();
	});
});