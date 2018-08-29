
$(document).ready(function(){

      $(".se-pre-con").fadeOut("slow");;


var data_copy;

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonServlet',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(result) {

              $(".info").html("Hello "+ "<b>"+result.name+"</b>" + " on your profile");
   			}
   		});

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonDoc',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(data) {

              data_copy = data.arr;
              var output="<tr id='tr"+i+"'>";
              for (var i in data.arr)
              {

                if(Number(i)%4==0){
                output+="<tr id='tr"+i+"'>"
                }


                output+="<td id='td"+i+"' style='border:1px solid #E6E6FA;' contenteditable='false'>"+data.arr[i]+"</td>"

                if((Number(i)+1)%4==0){
                output+="<td id='btnrow'><button id='del"+i+"' class='btn btn-warning btnremove' onclick='deleteMe(" + i + ")'>X</button></td><tr/>";
                }
              }
              $('.data').hide().html(output).fadeIn(700);

                  for(var i in data_copy){
                        if((Number(i)+2)%4===0){
                             $("#td"+i).css("text-align","left");

                             if(data_copy[i].length>150){

                             $("#td"+i).addClass('td-wrapper-scroll-y');

                             }

                        }
                  }

            }
   		});


        $("tbody tr").not(':first').on('mouseenter',function(){
        $(this).addClass('alert alert-info');
        }).on('mouseleave',function(){
        $(this).removeClass('alert alert-info');
        });


   		});

function deleteMe(del_id)
    {

            var name= $("#td"+Number(del_id-2)).contents().get(0).nodeValue;

        $.ajax({
            type:'POST',
            url:'http://localhost:8080/loginservlet/deleteDoc',
            data:'delete_name='+name,
            success: function(data)
            {
//                $("#del"+del_id).fadeOut('slow');
//                $("#tr"+del_id).fadeOut('slow');
                location.reload();
            }
        });
    };


