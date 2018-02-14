//function submitForm(met) {
//
//	document.getElementById("myForm").action =met;
//	document.getElementById("myForm").submit();
//   }

//submit para chrome q saltea requireds and patterns
function submitForm($acti) {
	   $("#myForm").attr('action', $acti);
	   $(event.currentTarget).click();//click al boton q llama
	}


window.addEventListener('resize', function(event){
	
    $("@media (min-width: 768px)").css("position", "fixed");

});

/*para mostrar loader*/
$(document).ready(function () {
	$("a:not(a[href='#usudesplegable'])").click(function () {
	    $('.container-form').hide();
	    $('.loader').show();
	});
});

$(document).ready(function () {
	$("#btnlupa").click(function () {
	    $('.container-form').hide();
	    $('.loader').show();
	});
});
/*fin loader*/

/*menu responsive*/

$(document).ready(main);
 
var contador = 1;
 
function main(){
	$('.glyphicon-menu-hamburger').click(function(){
		// $('nav').toggle(); 
 
		if(contador == 1){
			$('nav').animate({
				left: '0'
			});
			contador = 0;
		} else {
			contador = 1;
			$('nav').animate({
				left: '-100%'
			});
		}
 
	});
 
};
/*fin menu responsive*/