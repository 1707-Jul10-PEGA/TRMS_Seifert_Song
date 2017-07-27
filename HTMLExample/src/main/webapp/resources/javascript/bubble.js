/**
 * 
 */

function bubble(element) {
	event.stopPropagation();
	element.style.backgroundColor = "green";
	alert(element.id);
	
}

window.onload = function() {
	document.getElementById("A").addEventListener("click", function(){bubble(this)}, false );
	document.getElementById("B").addEventListener("click", function(){bubble(this)}, false );
	document.getElementById("Blake").addEventListener("click", function(){bubble(this)}, false );
	document.getElementById("Not Blake").addEventListener("click", function(){bubble(this)},false );
}