(function ($) {

    $(document).ready(function () {
        
        $("#generate-report").click(function (){
             var URL = context + "/requests/report";
            var data = JSON.stringify({
                "dateCreation" : "20202020"
            });
            var callback = function (data) {
                console.log(data.prototype);
                $(".loader-div").hide();
//               
            };
            console.log("URL for filters: " + URL);
//            $.ajax({
//                url: URL,
//                dataType: 'json',
//                type: 'POST',
//                //                crossDomain: true,
//                contentType: 'application/json',
//                data: data,
//                success: callback,
//                error: function (jqXhr, textStatus, errorThrown) {
//                    console.log("error thrown: "+errorThrown);
//                    console.log("text status: "+ textStatus);
//                    console.log("response: " + (typeof jqXhr.responseText));
//                    alert('Service call failed');
//                    var blob = new Blob(jqXhr.responseText);
//                    var link = document.createElement('a');
//                    link.href = window.URL.createObjectURL(blob);
//                    link.download = "<FILENAME_TO_SAVE_WITH_EXTENSION>";
//                    link.click();
//                }
//            });

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var blob = this.response;
                    console.log("respnse: "+ blob);
                    document.getElementById("demo").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", URL, true);
            xhttp.send();
        });
    
    })

})(jQuery);

