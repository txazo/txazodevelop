// events.js

var events = require('events');
var emitter = new events.EventEmitter();

// ע���¼�����
emitter.on('someEvent', function(arg1, arg2) {
    console.log('listener1', arg1, arg2);
});

emitter.on('someEvent', function(arg1, arg2) {
    console.log('listener2', arg1, arg2);
});

emitter.once('someEvent', function(arg1, arg2) {
    console.log('onceListener', arg1, arg2);
});

// �����¼�
emitter.emit('someEvent', '1990', 'txazo');
emitter.emit('someEvent', '1990', 'txazo');

emitter.emit('error');