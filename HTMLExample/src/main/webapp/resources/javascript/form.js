/**
 * 
 */
function add(){
	var id = document.getElementById("t_id");
	var name = document.getElementById("t_name");
	var major = document.getElementById("t_major");
	
	console.log("id of student: " + id.value);
	console.log("name of student: " + name.value);
	console.log("major of student: " + major.value);
	
	var row = document.createElement("tr");
	var idCell = document.createElement("th");
	var nameCell = document.createElement("th");
	var majorCell = document.createElement("th");
	idCell.innerHTML = id.value;
	nameCell.innerHTML = name.value;
	majorCell.innerHTML = major.value;
	row.appendChild(idCell);
	row.appendChild(nameCell);
	row.appendChild(majorCell);
	document.getElementById("students").appendChild(row);
}

window.onload = function(){
	document.getElementById("add").addEventListener("click", add, false);
	
	var idLabel = document.getElementsByTagName("label");
	idLabel[0].innerHTML = "new id label";
	
	var idInput = document.getElementById("t_id");
	idInput.setAttribute("placeholder", "enter your id here");
	idInput.setAttribute("type", "number");
	
	var nameInput = document.getElementById("t_name");
	nameInput.setAttribute("placeholder", "enter your name here");
	
	var majorInput = document.getElementById("t_major");
	majorInput.setAttribute("placeholder", "enter your major here");
}