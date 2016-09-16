/**
 * Sitecatalyst centralized JS file
 */

function include(file){
  var script  = document.createElement('script');
  script.src  = file;
  script.type = 'text/javascript';
  script.defer = true;
  document.getElementsByTagName('head').item(0).appendChild(script);
}

/* include any js files here */
include('js/AppMeasurement.js');
include('js/VisitorAPI.js');
testFunction();
	function testFunction() {
			setTimeout(function(){
				first();
			}, 500);
		}

function first(){
	//var s_code=s.t();if(s_code)document.write(s_code)
	s.trackingServer = "//ec2-52-39-59-106.us-west-2.compute.amazonaws.com:5000/omniture?"
	var s_code=s.t();if(s_code)document.write(s_code)
}

