$.fn.slideToLeft = function(data) {
    var width = parseInt(this.css('width'));
	var transfer = $('.frame_transfer');
	if(transfer.length == 0) {
    	transfer = $('<div class="frame_transfer"></div>').css({ 'width': (2 * width) + 'px', 'z-index': '0' });
    	var current = $('<div class="frame_current"></div>').css({ 'width': width + 'px', 'left': '0', 'float': 'left' }).html(this.html());
    	var next = $('<div class="frame_next"></div>').css({ 'width': width + 'px', 'left': width + 'px', 'float': 'left' }).html(data);
		transfer.append(current).append(next);
		this.html('').append(transfer);
	} else {
		var next = $('.frame_next');
		next.html(data);
	}
	//current.html(this.html());


    transfer.animate({ 'margin-left': '-' + width + 'px' }, 300, function () {
        //$(this).parent().html(data);
    });
}

$.fn.slideToRight = function(data) {
    //var width = parseInt(this.css('width'));
    //var transfer = $('<div class="transfer"></div>').css({ 'width': (2 * width) + 'px', 'margin-left': '-' + width + 'px', 'z-index': '0' });
    //var previous = $('<div class="previous"></div>').css({ 'width': width + 'px', 'left': '-' + width + 'px', 'float': 'left' }).html(data);
    //var current = $('<div class="current"></div>').css({ 'width': width + 'px', 'left': '0', 'float': 'left' }).html(this.html());
    //transfer.append(previous).append(current);
    //this.html('').append(transfer);
	var transfer = $('.frame_transfer');
    transfer.animate({ 'margin-left': '0px' }, 300, function () {
		//alert($('.previous').html());
        //$(this).parent().html(previous.html());
        $('.frame_current').html(data);
		$('.frame_next').html('');
    });
}
