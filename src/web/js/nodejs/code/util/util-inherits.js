// util-inherits.js
// ����ԭ�ͼ̳�

var util = require('util');

function Base() {
    this.name = 'Base';
	this.sayHello = function() {
	    console.log('Hello ' + this.name);
	};
}

Base.prototype.showName = function() {
    console.log(this.name);
};

function Sub() {
    this.name = 'Sub';
}

util.inherits(Sub, Base);

var objBase = new Base();
objBase.showName();
objBase.sayHello();

var objSub = new Sub();
objSub.showName();