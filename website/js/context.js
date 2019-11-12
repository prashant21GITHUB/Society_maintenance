//    var context = "http://tryinj.mj.milesweb.cloud/maintenance";
     var context = "http://localhost:8080/maintain";
//var context = "http://panoasis-maintenance.ap-south-1.elasticbeanstalk.com";  // AWS 

var maxResultSize = 20;

function getMaxResultSize() {
    return $("#page-size option:selected").text();
}


function currentDate() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var year = date.getFullYear();
    if(month < 10) {
        month = '0' + month;
    }
    if(day < 10) {
        day = '0' + day;
    }
    console.log("year: "+ year +" month: "+ month+ " day: "+ day);
    return year + '' + month + ''+ day;
}

function formatDate(date) {
    var monthNames = [
        "Jan", "Feb", "Mar",
        "Apr", "May", "Jun", "Jul",
        "Aug", "Sep", "Oct",
        "Nov", "Dec"
    ];

    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();
    if(day < 10) {
        day = '0' + day;
    }
    return day + ' ' + monthNames[monthIndex] + ', ' + year;
}

function post(URL, data, successCallback) {
    $.ajax({
        url: URL,
        dataType: 'json',
        type: 'POST',
        //                crossDomain: true,
        contentType: 'application/json',
        data: data,
        success: successCallback,
        error: function( jqXhr, textStatus, errorThrown ){
            
            alert('Service call failed');
        }
    });
}