// 文档加载完执行
$(function() {
});

$(document).ready(function() {
});

// 页面载入后执行
$(window).load(function() {
});

// 元素选择器(优先使用ID选择器，或从最近的ID继承)
// ID选择器 > 标签选择器 > Class选择器
$("#form input[type='button']")

$(".login", $("#form"))
$("#form").find(".login")

// 子选择器和后代选择器(子选择器 > 后代选择器)
$("#form > li")
$("#form li")

// 存储临时变量
$("#form").data("key")
$("#form").data("key", "value")

// 事件绑定
$("button").bind("mouseenter mouseleave", function() {
	$(this).toggleClass("show");
});

// 缓存jQuery对象
var $form_login = $("#form input.login");
$form_login.bind("click", function() {});
$form_login.show();

//链式代码
$("#main").find(".login")
	.addClass("hover")
	.css("color", "green")
	.show();

// 一次事件绑定
$("button").one("click", function() {
});

// 事件冒泡
$("#form li").bind("mouseenter", function() {
	$(this).addClass("show");
}).bind("mouseleave", function() {
	$(this).removeClass("show");
});

$("#form").bind("mouseenter", function(e) {
	var $cell = $(e.target);
	$cell.addClass("show");
}).bind("mouseleave", function(e) {
	var $cell = $(e.target);
	$cell.removeClass("show");
});

// Tab缩进

// 空格
$.post("/index.jsps", {data : data, id : id}, function() {
});

// 代码块用花括号括起来
if (true) {
}

var Object = {
	id : "id",
	show : function() {
	}
};

// jQuery查找，尽量使用#id为前缀，元素尽量具体
$("#form input.login")

// toggleClass
$(".button").toggleClass("blue");

// 验证元素为空
if (!$('#form').html()) {
}

if ($('#form').is(":empty")) {
}

// 查找元素的索引号

// 函数绑定到事件
$("#submit").click(function() {
});

$("#submit").bind("click", function() {
});

// 追加html到元素
$("#form").append("<span>html</span>");

// 属性过滤
$("#form input[data=1][value=1]")