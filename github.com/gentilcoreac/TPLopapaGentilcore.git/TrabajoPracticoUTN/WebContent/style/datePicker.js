$("#botModal").on("click",function(){

	$("#modal-date").modal();
});

$('.fj-date').datetimepicker({
        format: "dd/mm/yyyy",
        todayBtn: true,
        clearBtn: true,
        todayHighlight: true
});

$('#sandbox-container .input-daterange').datepicker({
        format: "dd/mm/yyyy",
        todayBtn: true,
        clearBtn: true,
        todayHighlight: true
        
});