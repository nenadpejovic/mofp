function processing() {
	$.get("/processing", function(data) {
		if(data=="stop"){
			$("#msg").text("Stop processing");
		} else {
			$("#msg").text("Start processing");
		}
	});
}