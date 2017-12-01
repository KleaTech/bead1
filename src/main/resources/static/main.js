function newPencil(caller) {
    var newfield = $(caller).siblings(".newfield").get(0);
    if (newfield.style.display === "none") newfield.style.display = "inline-block";
    else newfield.style.display = "none";
}

function addPencil(caller) {
    var color	     =	$(caller).siblings("[name='color']").val();
    var brand	     =	$(caller).siblings("[name='brand']").val();
    var length	     =	$(caller).siblings("[name='length']").val();
    var sharpness    =	$(caller).siblings("[name='sharpness']").val();
    var pencilCaseId =	$(caller).siblings("[name='pencilCaseId']").val();
    var data = {
		color	     : color,
		brand	     : brand,
		length	     : length,
		sharpness    : sharpness,
		pencilCaseId : pencilCaseId
		};
    $.ajax({
	url: '/pencil',
	type: 'POST',
	data: JSON.stringify(data),
	headers: {
	    'Accept': 'application/json',
	    'Content-Type': 'application/json'
	},
	dataType: "json",
	success: function(data, textStatus, xhr){
	    location.reload(true);
	},
	error: function(data, textStatus, xhr){
	    alert(data.responseText);
	}
    });
}

function deletePencil(caller) {
    var id = $(caller).siblings("[name='pencilId']").val();
    $.get("/pencil"
	,{id:id}
	,function(data){location.reload(true);}
	,"json")
    .fail(function(data){alert(data.responseText);});
}