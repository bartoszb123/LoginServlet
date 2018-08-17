$(document).ready(function(){


   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonDocServlet',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(result) {

   			alert(result);
//                console.log(result);
//                console.log(JSON.stringify(result));
//              $(".dane").html("Hello "+ "<b>"+result.name+"</b>" + " on your profile");
   			}
   		});
});
