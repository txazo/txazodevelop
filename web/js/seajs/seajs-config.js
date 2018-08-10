seajs.config({
	base: '../../js',
	charset: 'utf-8',
	preload: ['jquery', 'preload'],
	vars: {
		'locale': 'zh-cn'
	},
	paths: {
		'txazo': 'http://www.txazo.com/js'
	},
	alias: {
		'jquery': 'jquery/jquery/jquery-1.11.1.min.js',
		'util': 'seajs/util.js',
		'preload': 'seajs/preload.js',
		'require': 'seajs/require.js',
		'exports1': 'seajs/exports1.js',
		'exports2': 'seajs/exports2.js',
		'module': 'seajs/module.js'
	}
});