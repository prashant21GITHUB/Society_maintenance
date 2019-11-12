console.log("start");
(function ($) {
    
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }
    
    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
    
    function checkCookie() {
        var user = getCookie("username");
        if (user == "") {
            window.location.replace("../index.html");
        }
    }
    
    checkCookie();
    
    $(document).ready(function () {
        console.log("I am ready");
        
        function initTable() {
            var date = currentDate();
            var dataType = "json";
            var URL = context + "/requests";
            console.log("requests URL: " + URL);
            var callback = function (data, status) {
                $(".loader-div").hide();
                if (status == "success") {
                    populateTable(data);
                    showPagination(date);
                } else {
                    $("#no-requests").show();
                    alert("service failed to retrieve data");
                    $(".prev-next").hide();
                }
            }
            $(".loader-div").show();
            
            post(URL, JSON.stringify({ dateCreation : date, resultSize : getMaxResultSize() }), callback);
        }
        
        //Populate table for first time
        initTable();
        
        $("#home").click(function(){
           $(".topnav-div").hide();
            $("#home-div").show();
            $(".topnav-menu").removeClass("active-top");
             $("#home").addClass("active-top");
            $("#filters-div").show();
        });
        
//        $("#table-header").click(initTable);
//        
//         $('#table-header').hover(
//            function(){
//                $(this).next().show();
//            },
//            function(){
//                $(this).next().hide();   
//            }
//        );   
        
        var statuses = [];
        
        var showToast = function(id, message) {
                console.log("toast: "+ message);
                var x = $(id);
                x.html(message);
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 3000);
        }
        
        
        $("#requests").on("click", "td.status-column", function () {
            //                   alert($(this).text());
            
            var row = $(this).closest("tr");    // Find the row
            var reqNo = row.find(".reqId").text(); // Find the text
            console.log("rowId: "+ reqNo)
            var flatNo = row.find(".flatNumber").text();
            var category = row.find(".reqCategory").text();
            var currStatus = row.find(".status-column").text();
            var statusElement = row.find(".status-column");
            var dateStatusUpdate = new Date(row.find(".date-column").text());
//            var date = row.find(".date-column").text();
//            var day    = date.substring(0,2);
//            var month  = date.substring(3,5);
//             var year   = date.substring(6,10);
            console.log("date before parsing: "+ dateStatusUpdate);
//            date = year + '' + month + '' + day;
            dateStatusUpdate = $.datepicker.formatDate("yymmdd", dateStatusUpdate);
            console.log("parsed date for status: " + dateStatusUpdate);
            $("#statusUpdateToast").hide();
            var getStatusRadioBtns = function (data) {
                var statusR = '';
                $.each(data, function (key, value) {
                    var checkedStr = '';
                    if (value == currStatus) {
                        checkedStr = 'checked="checked"';
                    }
                    statusR +=
                            '<input type="radio" name="statusName" value="' + value + '" ' + checkedStr + ' />' + value + '<br>';
                });
                console.log(statusR);
                return statusR;
            }
            
            if (statuses.length == 0) {
                $.get(context + "/status", function (data, status) {
                    if (status == 'success') {
                        statuses = data;
                        var statusR = getStatusRadioBtns(data);
                        $("#status-options").html('<h4>Request Id: ' + reqNo + ' <br>Flat no: ' + flatNo + ' <br>Category: ' + category + '<br>Current Status: ' + currStatus + '</h4><br>' + statusR);
                        $("#id01").show();
                    }
                    
                }, "json");
            } else {
                console.log("already have statuses");
                var statusRow = getStatusRadioBtns(statuses);
                $("#status-options").html('<h4>Request Id: ' + reqNo + ' <br>Flat no: ' + flatNo + ' <br>Category: ' + category + '<br>Current Status: ' + currStatus + '</h4><br>' + statusRow);
                $("#id01").show();
            }
            
            
            
            function updateCurrentStatus(newValue) {
                statusElement.html(newValue);
            }
            
            $("#submitBtn").unbind().click(function (e) {
                
               console.log("submit call: ID: " + reqNo)
                
                var URL = context + "/requests/update";
                var data = JSON.stringify({
                    reqNo: reqNo,
                    date: dateStatusUpdate,
                    status: $("input[name=statusName]:checked", "#update-status-form").val()
                });
                var callback = function (data) {
                    console.log(data);
                    console.log(data.success);
                    if (data.success == true) {
                        console.log("success data: " + data);
                        var newStatusVal = $("input[name=statusName]:checked", "#update-status-form").val();

                        console.log("newStatusVal: " + newStatusVal);

                        updateCurrentStatus(newStatusVal);
                        showToast("#statusUpdateToast", "Status updated successfully.");
                        $("#statusUpdateToast").html("Status updated successfully.");
                        $("#statusUpdateToast").show();
                    } else {
                        console.log("failure data: " + data);
                        showToast("#statusUpdateToast", "Status updated failed. Try Again !!");
                    }
                    $("#id01").hide(100);
                };
                
                post(URL, data, callback);
                
                e.preventDefault();
            });
        });
        
        $("#menu").click(function() {
            var x = $(".myTopnav");
            console.log("menu clicked");
            if (x.className === "topnav") {
                x.className += " responsive";
            } else {
                x.className = "topnav";
            }
        });
        
        function openForm() {
            
            var w = window.open("true", "popupWindow", "width=600, height=400, scrollbars=yes");
            var $w = $(w.document.body);
            $w.html("<textarea></textarea>");
        }
        
        $("#reports").click(function () {
            $(".topnav-div").hide();
            
            $(".topnav-menu").removeClass("active-top");
            $("#reports").addClass("active-top");
            $("#filters-div").show();
            $("#home-div").hide();
        });
        
        //               $("#dialog").dialog({
        //                autoOpen : false, modal : true, show : "blind", hide : "blind"
        //              });
        
    })
})(jQuery);