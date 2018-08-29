
function sendContent(){



 var id = $("#td0").html();
  var name=$("#td1").html();
 var content=$("#myInstance1").html();
 var alias = $("#td3").html();


   $.ajax({
   			url: 'http://localhost:8080/loginservlet/findby',
   			method: 'POST',
   			data: {"content_edit": content, "id_edit": id , "name_edit":name, "alias_edit":alias},
   			success: function(data) {

             $("#editor").fadeOut(1000).toggle();
               $("#inputID").attr('value',id);
               $("#inputNAME").attr('value',name);
                $("#btnclick").click();
//               location.reload();

            }

});


}