


{ 
	"include" : {
		"path" : "src/test/mustache.js"
		"type" : "javascript"
	},
	"include" : "src/test/JSONinput.json",
	"invoke" : { 
		"class" : "Mustache",
		"method" : "render",
		"args" : "bla"
	}
	
}



var tmp = {};

load("src/impl/resoucres/asdfasdf.js")
function main () {
	var obj = {asdf: "asdf"};
	print(JSON.stringify(obj));
	DataRetriever.get(theData);
}
tmp.bla = function(){}
