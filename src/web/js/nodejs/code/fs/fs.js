// fs.js

var fs = require("fs");
fs.readFile('file.txt', 'utf-8', function(err, data) {
    if (err) {
	    console.error(err);
	} else {
	    console.log(data);
	}
});

// fs.readFileSync
// 异步文件读取

fs.open('file.txt', 'r', function(err, fd) {
    if (err) {
	    console.error(err);
		return;
	}
	
	var buf = new Buffer(8);
	fs.read(fd, buf, 0, 8, null, function(err, bytesRead, buffer) {
	    if (err) {
		    console.error(err);
			return;
		}
		console.log('bytesRead: ' + bytesRead);
		console.log(buffer);
	});
});