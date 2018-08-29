$(document).ready(function(){

var data_copy;

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonHTML',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(data) {

              data_copy=data.result;

              var output="";
              for (var i in data.result)
              {
                output+="<td id='td"+i+"' contenteditable='false'>"+data.result[i]+"</td>"
              }
              output+="<td id='btnrow'><button id='edit' style='display:none;' class='btn btn-warning'></button></td>";
              $('tr.dataSearching').css({"margin-left":"50px","margin-right":"50px"});

              $('tr.dataSearching').hide().html(output).fadeIn(1000);

                $("#td2").css("overflow","scroll"); // SCROLL in CONTENT - X
                $("#td2").addClass('td-wrapper-scroll-y'); // SCROLL in CONTENT - Y
                $("#td2").css("text-align","left");

                         if(data.result.length!=0){

                                $("#edit").css("display","inline");

                         }
//                   $("td").not(":first").attr("contenteditable","true");
                         $("#myInstance1").html(data.result[2]);

                         $("#edit").on('click',function(){

                                  $("#editor").slideToggle("slow",function(){

                                   if ( $("td").not(":even").attr("contenteditable") == "false") {
                                             $("td").not(":even").attr("contenteditable", "true");
                                        } else {
                                             $("td").not(":even").attr("contenteditable", "false");
                                        }

                                  });

                         });

            }// koniec success in Ajax

   		}); // koniec Ajax

//
//   		$("tr").not(':first').on('mouseenter',function(){
//        $(this).addClass('alert alert-info');
//        }).on('mouseleave',function(){
//        $(this).removeClass('alert alert-info');
//        });

});
