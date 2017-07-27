/**
 * 
 */
function doSomething(){
//	var id = document.getElementById("t_id");
//	var name = document.getElementById("t_name");
//	var major = document.getElementById("t_major");
	var number1 = document.getElementById("1_num");
	var number2 = document.getElementById("2_num");
	
	var first;
	var second;
	if (number1.value < number2.value)
		{
			first = number1.value;
			second = number2.value;
		}
	else
		{
			first = number2.value;
			second = number1.value;
		}
	
	var i; 
	var series = "";
	for (i = first; i <= second; i++)
		{
		if ( (i % 3 == 0) && (i % 5 == 0) )
				{
					series  += "fizzbuzz-"
					console.log("fizzbuzz");
					continue;
				}
			if ( i % 3 == 0 )
				{
					series += "fizz-"
				console.log("fizz");
					continue;
				}
			if ( i % 5 == 0 )
				{
					series += "buzz-"
				console.log("buzz");
					continue;
				}
			series += i
			console.log(i);
		}
	console.log(series);
	var row = document.createElement("tr");
	var idCell = document.createElement("th");
	idCell.innerHTML = series;
	row.appendChild(idCell);
	document.getElementById("students").appendChild(row);
	series = "";
}

window.onload = function(){
	document.getElementById("doSomething").addEventListener("click", doSomething, false);
	
}