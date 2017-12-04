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