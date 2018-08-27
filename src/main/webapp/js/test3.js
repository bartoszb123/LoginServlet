$(document).ready(function(){

//function loadscript(){

//alert("uruchomienie skryptu");

   $.ajax({
   			url: 'http://localhost:8080/loginservlet/jsonHTML',
   			method: 'GET',
            mimeType: 'application/json',
   			success: function(data) {

//             var output="<ul class='list-group'><li class='list-group-item active'>YOUR DOCUMENT</li></br>";
//
//            for (var i in data.result)
//            {
//                output+="<li class='list-group-item' id='li"+i+"'>"+data.result[i]+"<span style='margin:25px'></span></li></br>"  ;
//
//            }
//            output+="</ul>";
//
//              $('.dataSearching').hide().html(output).fadeIn(700);
//
//            }

//--------------------------------------------------------------------------------------
// var output="<table class='table'><tr style='background-color:#E6E6FA'><th>ID</th><th>Name</th><th>Content</th><th>Alias</th></tr><tr style='background-color:#F3F4FA'>";
            var output="";
            for (var i in data.result)
            {
                output+="<td>"+data.result[i]+"</td>"
            }

//             output+="</tr>";
              $('tr.dataSearching').css({"margin-left":"50px","margin-right":"50px"});
//              $('tr.dataSearching').hide().html(output).fadeIn(700);
                 $('tr.dataSearching').hide().html(output).fadeIn(1000);


$("tr").not(':first').on('mouseenter',function(){

$(this).addClass('alert alert-info');

}).on('mouseleave',function(){

$(this).removeClass('alert alert-info');

});



            }

   		});

});

