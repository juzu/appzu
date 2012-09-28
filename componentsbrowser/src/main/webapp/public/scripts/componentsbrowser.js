function showComponent(key) {
	
	// jzAjax needs to be applied on an jquery object
	// that's why we apply it on the 'slider' element
	$('#slider').jzAjax({
		url: "Controller.show()",
		data: {"key":key}
	}).done(function(data) {
		$('#slider').slideToLeft(data);
	})
	
}

function backHome() {
	
	// jzAjax needs to be applied on an jquery object
	// that's why we apply it on the 'slider' element
	$('#slider').jzAjax({
		url: "Controller.list()",
		data: {}
	}).done(function(data) {
		$('#slider').slideToRight(data);
	})
	
}