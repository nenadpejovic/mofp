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

function sendMessage(){
	var text = $('textarea#message').val();
	
	jQuery.ajax ({
	    url: "/send",
	    type: "POST",
	    data: JSON.stringify({message: text}),
	    contentType: "application/json; charset=utf-8",
	    success: function(){
	    	$('textarea#message').val('');
	    }
	});
}