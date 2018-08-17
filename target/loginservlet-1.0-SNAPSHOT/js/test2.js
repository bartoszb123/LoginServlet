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

//   	$.get("http://localhost:8080/loginservlet/jsonDoc",function(result){
//
//   	 $("#doc").html("twoje documenty "+ "<b>"+result.lista);
//    	alert("nazwa dokumentu: "+result.lista);
//
//   	});

 $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonDoc',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(data) {

            var output="<ul>";
            for (var i in data.arr)
            {
                output+="<li>"+data.arr[i]+ "</li>"  ;
            }
            output+="</ul>";

            $('#doc').html(output);

            }

   		});

   		});