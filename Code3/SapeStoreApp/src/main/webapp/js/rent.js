/**
 * 
 */

var duration =0;
var cost = 0;
var mindays=0;
var windays=0;
function priceDays(){
	
	var price=document.getElementById("bookprice").innerHTML;
	price1=parseFloat(price);
	
	 duration=parseInt(document.getElementById("days").value); 
	 cost=parseInt(duration)*price1*0.01;
	 var save=price1-cost;
	 document.getElementById("id1").innerHTML=  'Total Book Price :'+price;	
	 document.getElementById("id2").innerHTML=  'Your Saving:'+save;	
	 document.getElementById("totalcostdays").innerHTML=  'Total Rental Price :'+cost;	
	 
	do_change1();
	
}

function priceWeeks(){
	var price=document.getElementById("bookprice").innerHTML;
	
	price1=parseFloat(price);
	windays =document.getElementById("weeks").value;
	duration =windays*7;
	 cost=duration*price1*0.01;
	 var save=price1-cost;
	 document.getElementById("id3").innerHTML=  'Total Book Price :'+price;	
	 document.getElementById("id4").innerHTML=  'Your Saving:'+save;
	 
	 document.getElementById("totalcostweeks").innerHTML=  'Total Rental Cost :'+cost;
	 
	do_change2();
	
}
function priceMonths(){
	var price=document.getElementById("bookprice").innerHTML;
	
	price1=parseFloat(price);
	mindays =document.getElementById("months").value; 
	duration =mindays*30;
	 cost=mindays*price1*0.01;
	 var save=price1-cost;
	 document.getElementById("id5").innerHTML=  'Total Book Price :'+price;	
	 document.getElementById("id6").innerHTML=  'Your Saving:'+save;
	 document.getElementById("totalcostmonths").innerHTML=  'Total Rental Cost :'+cost; 
	do_change3();
	
}

function do_change1(){
	document.getElementById("save1").style.display = "block";
}

function do_change2(){
	document.getElementById("save2").style.display = "block";
}

function do_change3(){
	document.getElementById("save3").style.display = "block";
}

function doAjax(){
	var duration1 = duration;
	var cost1 = cost;
	/*alert(duration);
	alert(cost);*/
	$.ajax({
		type : "GET",
		url  : "/SapeStore/rentDetails",		
		dataType:"html",
		data : {'duration' : duration1,'cost' : cost1},
		success : function(result){
			 $('#container').load("RentCart.jsp");
			
			}
		});
}


function validatedays()
{
	var a=document.getElementById("days");
	if(a.value > 6 )
		{
		alert('Enter days between 1 to 6');
			document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+">Enter days between 1 to 6.</span>";  		
		}
	else if(a.value.length <= 0)
	{
		alert(' Dont leave the field empty.');
		document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+"> Don't leave the field empty.</span>";  		
	}

	else if(isNaN(a.value))
		{
		alert('Enter a number');
		
		document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+">Enter a number.</span>";  		
		
		}
	else{
		priceDays();
	}
}
function validateweeks()
{
	var a=document.getElementById("weeks");
	if(a.value > 3 )
		{
		alert('Enter weeks between 1 to 3');
			document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+">Enter days between 1 to 6.</span>";  		
		}
	else if(a.value.length <= 0)
	{
		alert(' Dont leave the field empty.');
		document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+"> Don't leave the field empty.</span>";  		
	}

	else if(isNaN(a.value))
		{
		alert('Enter a number');
		document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+">Enter a number.</span>";  		
		
		}
	else{
		priceWeeks();
	}
}
function validatemonths()
{
	var a=document.getElementById("months");
	if(a.value > 2 )
		{
		alert('Enter months between 1 to 2');
			document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+">Enter days between 1 to 6.</span>";  		
		}
	else if(a.value.length <= 0)
	{
		alert(' Dont leave the field empty.');
		document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+"> Don't leave the field empty.</span>";  		
	}

	else if(isNaN(a.value))
		{
		alert('Enter a number');
		document.getElementById("errorMessage").innerHTML = "<span class="+"'error'"+">Enter a number.</span>";  		
		
		}
	else{
		priceMonths();
	}
}