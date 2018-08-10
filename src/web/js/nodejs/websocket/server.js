var app = require('http').createServer(handler)
  , io = require('socket.io').listen(app)
  , fs = require('fs');

app.listen(80);

function handler(req, res) {
    fs.readFile('index.html', function(err, data) {
	    if (err) {
		    res.writeHead(500);
			return res.end('Error loading index.html');
		}
		
		res.writeHead(200);
		res.end(data);
	});
}

io.sockets.on('connection', function(socket) {
    socket.emit('news', {hello : 'world'});
	socket.on('otherEvent', function(data) {
	    console.log(data);
	});
});