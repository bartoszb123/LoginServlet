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

 $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonDoc',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(data) {

            var output="<ul class='list-group'><li class='list-group-item active'>YOURS DOCUMENTS</li></br>";

            for (var i in data.arr)
            {
                output+="<li class='list-group-item' id='li"+i+"'>"+data.arr[i]+"<span style='margin:25px'></span><button id='btn"+i+"' class='btn btn-default delete' onclick='deleteMe(" + i + ")'>Delete</button></li></br>"  ;

            }
            output+="</ul>";

              $('#doc').hide().html(output).fadeIn(1000);
            }
   		});

   		});

function deleteMe(del_id)
    {

            var name= $("#li"+del_id).contents().get(0).nodeValue;

        $.ajax({
            type:'POST',
            url:'http://localhost:8080/loginservlet/deleteDoc',
            data:'delete_name='+name,
            success: function(data)
            {
                $("#btn"+del_id).fadeOut('slow');
                $("#li"+del_id).fadeOut('slow');
//                location.reload();
            }
        });
    };
