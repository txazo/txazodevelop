// http.js

var http = require('http');

http.createServer(function(req, res) {
    res.writeHead(200, {'Content-Type' : 'text/html'});
	res.write('<h1>Node.js</h1>');
	res.end('<p>Hello World</p>');
}).listen(3000);

console.log("Http Server is listening at port 3000.");

// http.Server
var server = new http.Server();
server.on('request', function(req, res) {
    res.writeHead(200, {'Content-Type' : 'text/html'});
	res.write('<h1>Node.js</h1>');
	res.end('<p>Hello World</p>');
});
server.listen(4000);

console.log("Http Server is listening at port 4000.");