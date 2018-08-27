$(document).ready(function(){

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonServlet',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(result) {
//                console.log(result);
//                console.log(JSON.stringify(result));

              $(".dane").css("align","center");
              $(".dane").html("Username :"+"<b>"+result.name+"</b>"+ ", Password: "+ "<b>"+result.pass+"</b>");

   			}
   		});
   		});



