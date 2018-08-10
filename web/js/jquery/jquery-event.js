// 一次性事件
$("#id").one("click", function() {
	
});

// 阻止事件冒泡
$("#id").bind("click", function(event) {
	event.stopPropagation();
	// return false;
});

// 阻止默认行为
event.preventDefault();
// return false;

$('#id').bind('click', function() {
	
});

$('#id').click(function() {
	
});

// 常见事件
blur
change
click
delegate
focus
live
mouseover
mouseout
one
scroll
select
submit
trigger