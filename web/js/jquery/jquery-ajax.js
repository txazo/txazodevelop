// $.get()
$.get('/index.jsps', {'param' : value}, function(data) {
	
}, 'json');

$.ajax({
	url: url,
	data: data,
	success: success,
	dataType: dataType
});

// $.post()
$.post('/index.jsps', {'param' : value}, function(data) {
	
}, 'json');

$.ajax({
	type: "POST",
	url: url,
	data: data,
	success: success,
	dataType: dataType
});

// $.ajax()
$.ajax({
	url: '/index.jsps',
	type: 'post',
	data: {
		'param': value
	},
	dataType: 'json',
	success: function(data) {
		
	}
});