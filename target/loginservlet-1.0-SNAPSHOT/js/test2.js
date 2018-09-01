var data_copy;

$(document).ready(function(){

      $(".se-pre-con").fadeOut("slow");;



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

              loadTable(data_copy);

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


//        $("tbody tr").not(':first').on('mouseenter',function(){
//        $(this).addClass('alert alert-info');
//        }).on('mouseleave',function(){
//        $(this).removeClass('alert alert-info');
//        });

$("#sel1").on('change',function(){

     if($( "#sel1 option:selected" ).text()=="Name"){

        var sortByName = sortArray(data_copy)
            loadTable(sortByName);


     }

       if($( "#sel1 option:selected" ).text()=="NoSort"){
                 loadTable(data_copy);
          }

       if($( "#sel1 option:selected" ).text()=="Alias"){  // to nie aktywne
                 alert("nie aktywne");
//                 loadTable(data_copy);
          }



});

//$("option").click(function(){
//
//alert($(this).text());
//
//});



   		});  // koniec ready

function sortArray(data){

var dataAll = makeArray(data);
var namesArray = makeArrayNames(data);
var namesSort =sortStrWithNr_v2(namesArray);
var tempTab = new Array();

              for (i = 0; i < namesSort.length; i++) {    // stworzenie array na podstawie posortowanych names
                  for (j = 0; j < namesSort.length ; j++) {
                           if(dataAll[j][1].toString()==namesSort[i].toString() )   {
                                 tempTab.push(dataAll[j]);
                           }
                  }
              }

                   var SortTable = new Array();

                   for (i = 0; i < tempTab.length; i++) {   // stworzenie ogolnego arraya ze wszystkim
                       for (j = 0; j < 4 ; j++) {
                               SortTable.push(tempTab[i][j]);

                       }
                   }


return SortTable;
}



function loadTable(data){

 var output="<tr id='tr"+i+"'>";
              for (var i in data)
              {

                if(Number(i)%4==0){
                output+="<tr id='tr"+i+"'>"
                }


                output+="<td id='td"+i+"' style='border:1px solid #E6E6FA;' contenteditable='false'>"+data[i]+"</td>"    // data.arr[i]

                if((Number(i)+1)%4==0){
                output+="<td id='btnrow'><button id='del"+i+"' class='btn btn-warning btnremove' onclick='deleteMe(" + i + ")'>X</button></td><tr/>";
                }
              }
              $('.data').hide().html(output).fadeIn(700);

}

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


function sortStrWithNr_v1(data_array1){ // sposob nr 1 na sortowanie stringa z numerem


function pad(n) { return ("00000000" + n).substr(-8); }
function natural_expand(a) { return a.toString().replace(/\d+/g, pad) };
function natural_compare(a, b) {
    return natural_expand(a).localeCompare(natural_expand(b));
}
data_array1.sort(natural_compare);
return data_array1;
}

function sortStrWithNr_v2(data_array2){ // sposob nr 2 na sortowanie stringa z numerem

var collator = new Intl.Collator(undefined, {numeric: true, sensitivity: 'base'});
data_array2.sort(collator.compare);

return data_array2;
}


function sortStrWithNr_v3(data_array3){  // sposob nr 3 na sortowanie stringa z numerem

   data_array3.sort(function(a,b){
           return a.toString().localeCompare(b.toString(), undefined, {numeric: true, sensitivity: 'base'})
           });

     return data_array3;
}

function makeArray(data_all){

           var j = 0;
           var oldArr = Array.from(data_all);
           var newArr =new Array();
           newArr[j]= new Array();
           var amountArrays=oldArr.length/4;
           var result = new Array();
                   for (i = 0; i < oldArr.length; i++) {

                     if(i%4!=0|i==0){
                      newArr[j].push(oldArr[i]);
                     } else{
                        j++
                        newArr[j]= new Array();
                             if(i!=0){
                                newArr[j].push(oldArr[i]);
                             }
                     }
                   }
                    for (i = 0; i < amountArrays; i++) {
                            result.push(newArr[i])
                    }
 return result;
}


function makeArrayNames(data_n){

var ar = makeArray(data_n);
var temp = new Array();

          for (j = 0; j < ar.length; j++) {
                  temp.push(ar[j][1]);
          }

return temp;
}

