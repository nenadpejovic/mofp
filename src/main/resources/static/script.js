function processing() {
	$.get("/processing", function(data) {
		if(data=="start"){
			$("#msg").text("Stop processing");
		} else {
			$("#msg").text("Start processing");
		}
	});
}

function changeText(){
	 $('#chbx').change(function() {
	        if($(this).is(":checked")) {
	        	$("#msg").text("Stop processing");
	        } else {
	        	$("#msg").text("Start processing");
	        }
	       
	 });
}