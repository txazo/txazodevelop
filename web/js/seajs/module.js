define('module', ['util'], function(require, exports, module) {
	
	console.log(module.id);
	console.log(module.uri);
	console.log(module.dependencies);
	
	console.log(exports == module.exports);
	
	module.exports = {
		name: 'module',
		init: function() {
			console.log(this.name + ' init');
		}
	};
	
	console.log(exports == module.exports);
	
});