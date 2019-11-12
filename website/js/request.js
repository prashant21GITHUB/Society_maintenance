(function ($) {
    
    $(document).ready(function () {
        
        
        var categories =[];
        var towers =[];
        
        var showHtmlOptions = function(id, data) {
            var options = '';
            
            $(id).html = "";
            $.each(data, function(key, value){
                //                            var opt = $.createElement("option");
                //                            opt.value(value);
                
                //                            $("#request-div form select").append('<option value="'+ value +'">'+ value +'</option>');
                options +=
                        '<option value="'+ value +'">'+ value +'</option>'
            });
            $(id).html(options);
            
        };
        
        $("#request").click(function() {
            $(".topnav-div").hide();
            $("#request-div").show();
            $(".topnav-menu").removeClass("active");
            $("#request").addClass("active");
            var callback = function() {
                var URL = context + "/requests/categories";
                var callbackForCategories = function (data, status) {
                    if (status == 'success') {
                        console.log("categories: "+ data);
                        categories = data;
                        showHtmlOptions("#categories",categories);
                    }
                    
                };
                var dataType = "json";
                
                if(categories.length == 0) {
                    $.get(URL, callbackForCategories, dataType);
                } else {
                    showHtmlOptions("#categories",categories);
                }
                
                var callBackTowers = function(data, status) {
                    if (status == 'success') {
                        console.log("Towers: "+ data);
                        towers = data;
                        showHtmlOptions("#towers",towers);
                    }  
                };
                
                URL = context + "/towers";
                if(towers.length == 0) {
                    $.get(URL, callBackTowers, dataType);
                } else {
                    showHtmlOptions("#towers",towers);
                }
                $('#submitReq').click( function(e){
                    if(validateForm(e)) {
                        createRequest(e);
                    }
                } );
            }
            $("#request-div").load("../pages/request_form.html", callback);
            
            
        });
        
        var onlyNumberAllowed = function() {
            if (/\D/g.test(this.value))
            {
                // Filter non-digits from input value.
                this.value = this.value.replace(/\D/g, '');
            }
        }
        
        $('input[name=flatNo]').keyup(onlyNumberAllowed);
        
        //          $('input[name=mobileNo]').keyup(onlyNumberAllowed);
        
        function createRequest( e ){
            console.log("calling new request");
            $(".loader-div").show();
            var data = JSON.stringify({
                flatNo : $("input[name=flatNo]").val(),
                //                    mobileNo : $("input[name=mobileNo]").val(),
                tower : $("#towers option:selected").val(),
                category: $( "#categories option:selected" ).val(),
                details:  $("#details").val(),
            });
            var URL = context + "/requests/create";
            var callback = function( data ){
                $(".loader-div").hide();
                console.log(data);
                console.log(data.success);
                //                    var jsonResponse = JSON.parse(data);
                ////                  if("true" == )
                if(data.success == true) {
                    console.log(data);
                    console.log("request created success");
                    window.open("home.html", "_self");
                } else {
                    alert('Request creation failed.')
                }
            };
            
            post(URL, data, callback);
            e.preventDefault();
        }
        
        function validateForm(e) {
            var result = true;
            //            var mobile = $("#mobileNo").val();
            if($("#flatNo").val() == "" || $("#details").val() == "") {
                result = false; 
            }  
            //            var myRegEx = new RegExp('[1-9]{1}[0-9]{9}')
            //            
            //            if (mobile != myRegEx.exec(mobile)) {
            //                result = false;
            //            }
            return result;
        }
        
        
        
        
        
    });
    
})(jQuery);