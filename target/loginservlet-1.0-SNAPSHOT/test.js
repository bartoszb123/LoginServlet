$(document).ready(function(){

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonServlet',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(result) {
                console.log(result);
                console.log(JSON.stringify(result));
              $(".dane").text("Username :"+result.name+ ", Password: "+ result.pass);
   			}
   		});
   		});



