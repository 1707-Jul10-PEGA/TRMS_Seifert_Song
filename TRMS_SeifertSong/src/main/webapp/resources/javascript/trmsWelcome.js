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
var testdata
function testEvent(){
	$(document).ready(function(){

		   // jQuery methods go here...
		$.get("exampleServlet", true);

		});
}

function postEvent(){
	testdata = makePerson('blake', '7/29/17', 'some major');
	var data = "person="+JSON.stringify(makePerson('blake', '7/29/17', 'some major'))
	$(document).ready(function(){
		
		   // jQuery methods go here...
		$.post("exampleServlet", data, true);

		});
}

function postPerson(){
var head = document.getElementById("head");

var xhr = new XMLHttpRequest();

xhr.onreadystatechange = function(){
	switch(xhr.readyState)
	{
	case 0:
		head.innerHTML = "Request not initialized";
		break;
	case 1:
		head.innerHTML = "Connection Established";
		break;
	case 2:
		head.innerHTML = "Request recieved";
		break;
	case 3:
		head.innerHTML = "Processing Request";
		break;
	case 4:
		if(xhr.status == 200){
			document.getElementById("stuff").innerHTML = "person posted!!!";
			head.innerHTML = "Success";
		}
		else{
			head.innerHTML = "ERROR with Request, response code: " + xhr.status;
		}
		break;
	}
}

xhr.open("POST", "getPerson", true);
xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
var data = "person="+JSON.stringify(makePerson('blake', '7/29/17', 'some major'))
xhr.send(data);
};

function makePerson(name, dob, major){
	var person = {};
	person.name = name;
	person.dob = dob;
	person.major = major;
	return person;
}

window.onload = function(){
	document.getElementById("loginButton").addEventListener("click", postEvent, event);
}