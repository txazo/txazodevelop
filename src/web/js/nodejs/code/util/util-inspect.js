// util-inspect.js
// ����ת��Ϊ�ַ���

var util = require('util');

function Person() {
    this.name = 'Base';
	this.getName = function() {
	    return this.name;
	};
}

var obj = new Person();
console.log(util.inspect(obj));
console.log(util.inspect(obj, true));