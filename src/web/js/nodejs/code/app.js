// app.js

var http = require('http');

http.createServer(function(req, resp) {
    resp.writeHead(200, {'Content-Type' : 'text/html'});
	resp.write('<h1>Node.js</h1>');
	resp.end('<p>Hello World</p>');
}).listen(3000);

console.log("Http Server is listening at port 3000.");