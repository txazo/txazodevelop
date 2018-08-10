define(function(require, exports, module) {
	var emailRegExp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
	
	exports.checkNull = function(obj) {
		if (!obj) {
			return true;
		}
		return false;
	};
	
	exports.checkEmail = function(email) {
		return emailRegExp.test(email);
	}
});