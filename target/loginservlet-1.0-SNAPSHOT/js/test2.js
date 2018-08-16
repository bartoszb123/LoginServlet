$(document).ready(function(){

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonServlet',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(result) {
                console.log(result);
                console.log(JSON.stringify(result));
              $(".dane").html("Hello "+ "<b>"+result.name+"</b>" + " on your profile");
   			}
   		});

//   	$.get("http://localhost:8080/loginservlet/createDocument",function(result){
//
//   	 $(".doc").html("twoj documento "+ "<b>"+result.name);
//    	alert("nazwa dokumentu: "+result.name);
//
//   	});




   		});