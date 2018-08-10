// 对象
var module1 = {};
module1.init = function() {
	console.log('module1 init');
};

// 对象
var module2 = new Object({
	init: function() {
		console.log('module2 init');
	}
});

//对象
var module3 = {
	init: function() {
		console.log('module3 init');
	}
};

// 立即执行函数
var module4 = (function() {
	var init = function() {
		console.log('module4 init');
	};
	return {
		init: init
	}
})();

// 放大模式
var module5 = (function(module) {
	module.exec = function() {
		console.log('module5 exec');
	};
	return module;
})(module1);

// 宽放大模式
var module6 = (function(module) {
	module.exec = function() {
		console.log('module6 exec');
	};
	return module;
})(window.module2 || {});

var module7 = (function($) {
	return {
		init: function() {
			console.log('module7 init');
		}
	};
})(jQuery);