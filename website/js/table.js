var totalPages = 0;
var totalRequestsCount = 0;

function populateTable(data) {
    var row = '';

    console.log("populating tables")
    $("#requests").empty();

//    var cols = '<tr id="requests-header">';
//    for (var i = 0; i < data.columns.length; i++) {
//        cols += '<th>' + data.columns[i] + '</th>';
//    }
//    cols += '</tr>';
//    $("#requests").append(cols);
    if (data.requests.length > 0) {
        var cols = '<tr id="requests-header">';
        for (var i = 0; i < data.columns.length; i++) {
            cols += '<th>' + data.columns[i] + '</th>';
        }
        cols += '</tr>';
        $("#requests").append(cols);
        $.each(data.requests, function (key, value) {
            var year = value.date.substring(0, 4);
            var month = value.date.substring(4, 6);
            var day = value.date.substring(6, 8);

            var date = new Date(year, month - 1, day);
//            date = $.datepicker.formatDate("dd-M-yy", date);
            date = formatDate(date);
            row =
                    '<tr><td class="reqId">' + value.reqId +
                    '</td><td class="date-column">' + date +
                    '</td><td class="date-column">' + value.time +
                    '</td><td class="flatNumber">' + value.tower + ' - ' + value.flatNo +
                    '</td><td class="reqCategory">' + value.category +
                    '</td><td class="status-column">' + value.status +
                    '</td><td>' + value.requestDetails +
                    '</td></tr>';
            $("#requests").append(row);
        });
        $("#no-requests").hide();
        $("#top-table").show();
        totalRequestsCount = data.totalRequestsCount;
//        showPagination(data.totalRequestsCount);
    } else {
        console.log("No maintenance requests");
        $("#no-requests").show();
        $("#top-table").hide();
    }
    
    
}

function getTotalRequestsCount() {
    return totalRequestsCount;
}

function showPagination(currentDate) {
    $("#pagination-div").empty();
    var pageSize = $("#page-size option:selected").text();
    var pages = totalRequestsCount / pageSize;
    if (totalRequestsCount % pageSize !== 0) {
        pages = pages + 1;
    }
    
    $("#pagination-div").pagination({
        items: totalRequestsCount,
        itemsOnPage:  pageSize,
        cssStyle: 'light-theme',
        onPageClick: function(pageNumber, event) {
            $(".loader-div").show();
            var status = $("#fstatus").val();
            var category = $("#fcategories").val();
            var tower = $("#ftowers").val();
            var flatNo = $("input[name=fflatNo]").val() + '';
            console.log("onPageClicked: "+ pageNumber);
            var page = $(this).attr("name");
            var firstRow = (pageNumber - 1) * getMaxResultSize();
            
            populateTableDataByPage(null, status, category, tower, flatNo, firstRow, currentDate, currentDate, maxResultSize);
        }
    });
}

function populateTableDataByPage(date, status, category, tower, flatNo, firstRow, startDate, endDate, maxResultSize) {
    if (status == 'All') {
        status = null;
    }
    if (category == 'All') {
        category = null;
    }
    if (tower == 'All') {
        tower = null;
    }
    if (flatNo == '') {
        flatNo = null;
    }
    var URL = context + "/requests/page";
    var data = JSON.stringify({
        dateCreation: date,
        status: null,
        category: null, //$("input[name=mobileNo]").val(),
        tower: null,
        flatNo: flatNo,
        firstRow: firstRow,
        resultSize: getMaxResultSize(),
        startDate: startDate,
        endDate: endDate,
        statuses: status,
        towers: tower,
        categories: category
    });
    var callback = function (data) {
        $(".loader-div").hide();
        populateTableForPage(data);
    };
    console.log("URL for filters: " + URL);
    post(URL, data, callback);
}

function populateTableForPage(data) {
    var row = '';

    console.log("populating tables")
    $("#requests").empty();

    if (data.requests.length > 0) {
        var cols = '<tr id="requests-header">';
        for (var i = 0; i < data.columns.length; i++) {
            cols += '<th>' + data.columns[i] + '</th>';
        }
        cols += '</tr>';
        $("#requests").append(cols);
        $.each(data.requests, function (key, value) {
            var year = value.date.substring(0, 4);
            var month = value.date.substring(4, 6);
            var day = value.date.substring(6, 8);

            var date = new Date(year, month - 1, day);
//            date = $.daterangepicker.format("DD-MMM-YYYY", date);
            date = formatDate(date);
            row =
                    '<tr><td class="reqId">' + value.reqId +
                    '</td><td class="date-column">' + date +
                    '</td><td class="date-column">' + value.time +
                    '</td><td class="flatNumber">' + value.tower + ' - ' + value.flatNo +
                    '</td><td class="reqCategory">' + value.category +
                    '</td><td class="status-column">' + value.status +
                    '</td><td>' + value.requestDetails +
                    '</td></tr>';
            $("#requests").append(row);
        });
        $("#no-requests").hide();
//        $("#pagination-div").show();
//        $("#pagination-div").empty();
//        var pages = data.totalRequestsCount / 5;
//        if (data.totalRequestsCount % 5 !== 0) {
//            pages = pages + 1;
//        }
////        if (pages >= 1) {
////            $("#pagination-div").append('<a class="active-a" name="1">1</a>');
////        }
//        for (var i = 1; i <= pages; i++) {
//            $("#pagination-div").append('<a name=' + i + '>' + i + '</a>');
//        }

    } else {
        console.log("No maintenance requests");
        $("#no-requests").show();
//        $("#pagination-div").hide();
    }


}


