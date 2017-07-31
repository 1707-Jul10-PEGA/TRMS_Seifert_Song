/**
 * 
 */

//function handleEvent(){
//	
//	var xhr = new XMLHttpRequest();
//	xhr.onreadystatechange;
//	xhr.open("GET", "exampleServlet", true);
//	xhr.send();
//}
var ID;
var PW;
function testEvent(){
	$(document).ready(function(){

		   // jQuery methods go here...
		$.get("exampleServlet", true);

		});
}

function postEvent(){
	ID = document.getElementById('LoginID');
	PW = document.getElementById('Password');
	var data = "user="+JSON.stringify(makeLoginObject(ID.value, PW.value));
	console.log(data);
	$(document).ready(function(){
		
		   // jQuery methods go here...
		$.post("exampleServlet", data, welcomeErrorMessage);

		});
}
function makeLoginObject(ID, PW)
{
	var User = {};
	User.userName = ID;
	User.password = PW;
	return User;
}

function welcomeErrorMessage(){
	document.getElementById("welcomeError").innerHTML("ERROR");
}

window.onload = function(){
	document.getElementById("loginButton").addEventListener("click", postEvent, event);
}