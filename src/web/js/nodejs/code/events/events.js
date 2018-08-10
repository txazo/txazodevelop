// events.js

var events = require('events');
var emitter = new events.EventEmitter();

// 注册事件监听
emitter.on('someEvent', function(arg1, arg2) {
    console.log('listener1', arg1, arg2);
});

emitter.on('someEvent', function(arg1, arg2) {
    console.log('listener2', arg1, arg2);
});

emitter.once('someEvent', function(arg1, arg2) {
    console.log('onceListener', arg1, arg2);
});

// 发射事件
emitter.emit('someEvent', '1990', 'txazo');
emitter.emit('someEvent', '1990', 'txazo');

emitter.emit('error');