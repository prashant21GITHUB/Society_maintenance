console.log("start");
(function ($) {
    
    $(document).ready(function () {
        console.log("I am ready");
        var dataType = "json";
        var URL = "http://localhost:8080/maintenance/requests";
        var callback = function (data, status) {
            if (status == "success") {
                console.log(data);
                var row = '';
                if (data.length > 0) {
                    $.each(data, function (key, value) {
                        row +=
                                '<tr><td class="reqId">' + value.reqId +
                                '</td><td>' + value.date +
                                '</td><td class="flatNumber">' + value.flatNumber +
                                '</td><td class="reqCategory">' + value.category +
                                '</td><td class="status-column">' + value.status +
                                '</td><td>' + value.requestDetails +
                                '</td></tr>';
                    });
                    $("#no-requests").hide();
                } else {
                    console.log("No maintenance requests");
                    
                }
                
                //                   
                $("#requests").append(row);
                
            } else {
                ("no requests");
                alert("service failed to retrieve data");
            }
        }
        $.get(URL, callback, dataType);
        
        var statuses = [];
        
        $("#requests").on("click", "td.status-column", function () {
            //                   alert($(this).text());
            
            var row = $(this).closest("tr");    // Find the row
            var reqId = row.find(".reqId").text(); // Find the text
            var flatNo = row.find(".flatNumber").text();
            var category = row.find(".reqCategory").text();
            var currStatus = row.find(".status-column").text();
            var statusElement = row.find(".status-column");
            
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
                $.get("http://localhost:8080/maintenance/status", function (data, status) {
                    if (status == 'success') {
                        statuses = data;
                        var statusR = getStatusRadioBtns(data);
                        $("#status-options").html('<h4>Request Id: ' + reqId + ' <br>Flat no: ' + flatNo + ' <br>Category: ' + category + '<br>Current Status: ' + currStatus + '</h4><br>' + statusR);
                        $("#id01").show();
                    }
                    
                }, "json");
            } else {
                console.log("already have statuses");
                var statusRow = getStatusRadioBtns(statuses);
                $("#status-options").html('<h4>Request Id: ' + reqId + ' <br>Flat no: ' + flatNo + ' <br>Category: ' + category + '<br>Current Status: ' + currStatus + '</h4><br>' + statusRow);
                $("#id01").show();
            }
            
            function showToast(id, message) {
                var x = $(id);
                x.html(message);
                x.className = "show";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 3000);
            }
            
            function updateCurrentStatus(newValue) {
                statusElement.text(newValue);
            }
            
            $("#submitBtn").click(function (e) {
                
                $.ajax({
                    url: "http://localhost:8080/maintenance/requests/update",
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    
                    //                data: $(this).serializeArray,
                    data: JSON.stringify({
                        reqId: reqId,
                        status: $("input[name=statusName]:checked", "#update-status-form").val()
                    }),
                    success: function (data, textStatus, jQxhr) {
                        console.log(data);
                        console.log(data.success);
                        //                    var jsonResponse = JSON.parse(data);
                        ////                  if("true" == )
                        
                        if (data.success == true) {
                            console.log("success data: " + data);
                            var newStatusVal = $("input[name=statusName]:checked", "#update-status-form").val();
                            updateCurrentStatus(newStatusVal);
                            console.log("newStatusVal: " + newStatusVal);
                            $("#id01").hide(200);
                            showToast("#statusUpdateToast", "Status updated successfully.");
                        } else {
                            console.log("failure data: " + data);
                            showToast("#statusUpdateToast", "Status updated failed. Try Again !!");
                        }
                    },
                    error: function (jqXhr, textStatus, errorThrown) {
                        showToast("#statusUpdateToast", "Status updated failed. Try Again !!");
                        console.log("errorThrown: " + errorThrown + " textStatus: " + textStatus);
                    }
                });
                
                e.preventDefault();
            });
        });
        
        
        
        function openForm() {
            
            var w = window.open("true", "popupWindow", "width=600, height=400, scrollbars=yes");
            var $w = $(w.document.body);
            $w.html("<textarea></textarea>");
        }
        
        //               $("#dialog").dialog({
        //                autoOpen : false, modal : true, show : "blind", hide : "blind"
        //              });
        
    })
})(jQuery);