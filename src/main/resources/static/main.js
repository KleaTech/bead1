function addPencilCase() {
    $.post("/pencilCase"
	,{}
	,function(data){location.reload(true);}
	,"json")
    .fail(function(data){alert(data.responseText);});
}

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

function sharpenPencil(caller) {
    var percentagefield = $(caller).siblings(".percentagefield").get(0);
    if (percentagefield.style.display === "none") {
	$(percentagefield).children("input").attr("name", "sharpenpercentage");
	percentagefield.style.display = "block";
    }
    else percentagefield.style.display = "none";
}

function brakePencil(caller) {
    var id = $(caller).siblings("[name='pencilId']").val();
    $.get("/brake"
	,{id:id}
	,function(data){location.reload(true);}
	,"json")
    .fail(function(data){alert(data.responseText);});
}

function usePencil(caller) {
    var percentagefield = $(caller).siblings(".percentagefield").get(0);
    if (percentagefield.style.display === "none") {
	$(percentagefield).children("input").attr("name", "usepercentage");
	percentagefield.style.display = "block";
    }
    else percentagefield.style.display = "none";
}

function sendPencil(caller) {
    var id = $(caller).parent().siblings("[name='pencilId']").val();
    var usepercentage = $(caller).siblings("[name='usepercentage']");
    if(usepercentage.length>0) {
	$.get("/use"
	    ,{id:id, percentage:usepercentage.val()}
	    ,function(data){location.reload(true);}
	    ,"json")
	.fail(function(data){alert(data.responseText);});
    }
    else {
	var sharpenpercentage = $(caller).siblings("[name='sharpenpercentage']");
	if(sharpenpercentage.length>0) {
	    $.get("/sharpen"
		,{id:id, percentage:sharpenpercentage.val()}
		,function(data){location.reload(true);}
		,"json")
	    .fail(function(data){alert(data.responseText);});
	}
    }
}

function deletePencil(caller) {
    var id = $(caller).siblings("[name='pencilId']").val();
    $.get("/pencil"
	,{id:id}
	,function(data){location.reload(true);}
	,"json")
    .fail(function(data){alert(data.responseText);});
}