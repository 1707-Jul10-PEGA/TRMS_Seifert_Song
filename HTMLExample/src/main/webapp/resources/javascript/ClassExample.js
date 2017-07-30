/**
 * 
 */

var doStuff;

var a, b, c, d, e, f, g, h, i;

a = 10;
b = "10";
c = true;
d = {};
e = null;
//f = ;
g = (0/0);
h = [];
i = function(){
	var functionScope = 100;
	
	function nestedFunction() {
		var nestedScope = 50;
		
		console.log(functionScope);
	}
	nestedFunction();
}
i();

var book = {
		title: "The fellowship of the Ring",
		year: 1954,
		author: {
			name: "JRR Tolkien",
			dob: 1892
		},

		checkedOut: false,
		
		checkout: function(){
			checkedOut = !checkedOut;
		}
}

var checkTrue = function(d){
	if(d){
		console.log(d + " is true");
	}
	else{
		console.log(d + " is false");
	}
}

function changeString(myVar){
	console.log(myVar);
	myVar="changed that string";
	console.log(myVar);
}

window.onload = function(){
	doStuff = function doStuff(z){
		alert(z + " From an external file");
	}
	
}

function sumNum(){
	console.log("nothing");
	console.log(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);
	console.log(arguments);
	var temp = 0;
	for (let x of arguments){
		temp = temp + x;
	}
	console.log(temp);
}
//function sumNum(x, y, z){
//	console.log(x + y + z);
//}

function doStuff(z){
	alert(z + " From an external file");
}