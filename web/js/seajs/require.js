define(function(require, exports, module) {
	
	var util = require('util');
	console.log(util.checkNull(''));
	
	require.async('util', function(util) {
		console.log(util.checkEmail('784990655@qq.com'));
	});
	
	console.log(require.resolve('util'));
	
});