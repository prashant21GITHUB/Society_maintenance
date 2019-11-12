(function ($) {
    
    $(document).ready(function () {
        
        var categories =[];
        var towers =[];
        var status = [];
        var startDate = '';
        var endDate = '';
        
        
        var showHtmlOptions = function(id, data, placeholder, columns) {
            var options = '';
            $(id).html = "";
//            options += '<option value="All">All</option>';
            $.each(data, function(key, value){
                options +=
                        '<option value="'+ value +'">'+ value +'</option>'
            });
            $(id).html(options);
//            $(id).multiselect({
//                includeSelectAllOption: true
//            });
//            if(id != '#fstatus')
            $(id).multiselect({
                columns: columns,
                placeholder: placeholder,
                search: false,
//                searchOptions: {
//                    'default': placeholder
//                },
                texts: {
                        placeholder    : 'Select options', // text to use in dummy input
                        search         : 'Search', // search input placeholder text
                        selectedOptions: ' selected', // selected suffix text
                        selectAll      : 'Select all', // select all text
                        unselectAll    : 'Unselect all', // unselect all text
                        noneSelected   : 'None Selected'   // None selected text
                },
                showCheckbox: true, 

                selectAll: true,
               
                maxWidth: 200
            });
            console.log("options set: " + options)
        };
        
        
//        function showTopTableFilters(statuses) {
//            var statusFilterDiv = $("#status-filter");
//            statusFilterDiv.append('<a class="status-filter-a" name="All">All</a>') 
//            $.each(statuses, function(key, value) {
//                statusFilterDiv.append('<a class="status-filter-a" name="' + value +'">' + value +'</a>')       
//            });
//            var top_refresh_btn = 'top_refresh_btn';
//            statusFilterDiv.append('<a id="top_refresh_btn">Refresh</a>')
//            
//        }
        
        function initFilters() {
            //            $("#filters-div").show();
           
            var URL = context + "/requests/categories";
            var callbackForCategories = function (data, status) {
                if (status == 'success') {
                    console.log("categories: "+ data);
                    categories = data;
                    showHtmlOptions("#fcategories",categories, 'Category',1);
                }
                
            };
            var dataType = "json";
            
            if(categories.length == 0) {
                $.get(URL, callbackForCategories, dataType);
            } else {
                showHtmlOptions("#categories",categories, 'Category',1);
            }
            
            var callBackTowers = function(data, status) {
                if (status == 'success') {
                    console.log("Towers: "+ data);
                    towers = data;
                    showHtmlOptions("#ftowers",towers, 'Tower',2);
                }  
            };
            
            URL = context + "/towers";
            if(towers.length == 0) {
                $.get(URL, callBackTowers, dataType);
            } else {
                showHtmlOptions("#towers",towers, 'Tower',2);
            }
            
            var callBackStatus = function(data, status) {
                if (status == 'success') {
                    console.log("Towers: "+ data);
                    status = data;
                    showHtmlOptions("#fstatus",status, 'Status',1);
//                    showTopTableFilters(data);
                }  
            };
            
            URL = context + "/status";
            if(status.length == 0) {
                $.get(URL, callBackStatus, dataType);
            } else {
                showHtmlOptions("#fstatus",status, 'Status',1);
//                showTopTableFilters(status);
            }
//            
//            $('.multiselect-ui').multiselect({
//                includeSelectAllOption: true
//            });
            
            
//            $('dropdown').multiselect({
//                includeSelectAllOption: true,
//                maxHeight: 400,
//                dropUp: true
//            });
            
            
//            $('.multiselect-ui').multiselect('rebuild');
            
            //            $( "#datepicker" ).datepicker();
            //            $("#datepicker").datepicker("option", "dateFormat","dd-M-yy");
            loadDateRangePicker();
            $('#page-size').change(refreshCallback);
            $("#refreshBtn").unbind().click(refreshCallback);
            $("#clearFilters").unbind().click(resetFilters);
            //                $(document).on("click", "#refreshBtn", refreshCallback);
            //                 $("#refreshBtn").om("click", refreshCallback);
            
        }
        
       
           
        
        function loadDateRangePicker() {
            //            var start = moment().subtract(29, 'days');
            var start = moment();
            var end = moment();
            
            function callback(start, end) {
                console.log(start.format('YYYYMMDD') + ' - ' + end.format('YYYYMMDD'));
                //                $("#fflatNo").html(start.format('YYYYMMDD') + ' - ' + end.format('YYYYMMDD'));
                $('#datepicker').val(start.format('DD MMM, YYYY') + ' - ' + end.format('DD MMM, YYYY'));
                startDate = start.format('YYYYMMDD');
                endDate = end.format('YYYYMMDD');
            }
            
            $('#datepicker').daterangepicker({
                startDate: moment(),
                endDate: moment(),
                //                minDate: '01/01/2012',
                //                maxDate: '12/31/2014',
                dateLimit: {days: 60},
//                showDropdowns: true,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Last 3 Days': [moment().subtract('days', 2), moment()],
                    'Last 7 Days': [moment().subtract('days', 6), moment()],
                    'Last 15 Days': [moment().subtract('days', 14), moment()],
                    'Last 30 Days': [moment().subtract('days', 29), moment()],
//                    'This Month': [moment().startOf('month'), moment().endOf('month')],
//                    'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                buttonClasses: ['btn btn-default'],
                applyClass: 'btn-small btn-primary',
                cancelClass: 'btn-small',
                autoApply: false,
                
                separator: ' to ',
                locale: {
                    applyLabel: 'Apply',
                    fromLabel: 'From',
                    toLabel: 'To',
                    customRangeLabel: 'Custom Range',
                    daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                    monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    firstDay: 1,
                    format: 'DD MMM, YYYY'
                }
            }, callback);
            
            callback(start, end);
        }
        
        $("#filters-div").load("../pages/filters.html", initFilters);
        
        
        var refreshCallback = function(e) {
            $(".loader-div").show();
            var status = $( "#fstatus" ).val();
            var category = $( "#fcategories" ).val();
            var tower = $("#ftowers").val();
            var flatNo = $("input[name=fflatNo]").val() + '';
            console.log("Statuses: "+ status + "  Towers: "+ tower + "  Categories: "+ category);
            //            var date = $("#datepicker").datepicker("getDate");
            //            if(date == null) {
            //                date = new Date();
            //            }
            //            console.log("Datepicker date: " + date);
            //            date = $.datepicker.formatDate("yymmdd", date);
            //            console.log("Parsed date: " + date+" Refresh clicked");
            //            var dateRngPicker = $('#datepicker').data('daterangepicker');
            populateTableDataByFilters(null, status, category, tower, flatNo, startDate, endDate);
            
            e.preventDefault();
        };
        
        function resetFilters(e) {
//            $("#ftowers  option").attr("selected",false);
//            $(".dropdown").val("");
            $(".dropdown").multiselect('reset');
//            $("#fcategories").val("");
//            $("#fcategories").multipleSelect().uncheckAll();
//            $("#ftowers").multipleSelect().uncheckAll();
//            $("#ftowers").val("");
            $("input[name=fflatNo]").val('');
            $("#datepicker").val('');
            $(".loader-div").show();
            var date = currentDate();
            populateTableDataByFilters(date, null, null, null, null, null, null);
        }
        
        $('#fflatNo').keyup(function(e)
        {
            if (/\D/g.test(this.value))
            {
                // Filter non-digits from input value.
                this.value = this.value.replace(/\D/g, '');
            }
        });
        
        function populateTableDataByFilters(date, status, category, tower, flatNo, startDate, endDate) {
            if(status == 'All') {
                status = null;
            }
            if(category == 'All') {
                category = null;
            }
            if(tower == 'All') {
                tower = null;
            }
            if(flatNo == '') {
                flatNo = null;
            }
            var URL =  context + "/requests/filters";
            var data = JSON.stringify({
                dateCreation: date,
                status : null,
                category : null, //$("input[name=mobileNo]").val(),
                tower : null,
                flatNo: flatNo,
                startDate: startDate,
                endDate: endDate,
                statuses : status,
                towers : tower,
                categories : category,
                resultSize : getMaxResultSize()
            });
            var callback = function( data ){
                $(".loader-div").hide();
                populateTable(data, maxResultSize);
                showPagination(getTotalRequestsCount());
            };
            console.log("URL for filters: "+ URL);
            post(URL, data, callback);
        }
        
        function showPagination(totalRequestsCount) {
            $("#pagination-div").empty();
            var pageSize = $("#page-size option:selected").text();
            var pages = totalRequestsCount / pageSize;
            if (totalRequestsCount % pageSize !== 0) {
                pages = pages + 1;
            }
            //    totalPages = pages;
            //    if (pages >= 1) {
            //        $("#pagination-div").append('<a id="prev-a">&laquo;</a>');
            //        $("#pagination-div").append('<a class="active-a page-a" name="1">1</a>');
            //    }
            //    for (var i = 2; i <= pages; i++) {
            //        $("#pagination-div").append('<a class="page-a" name=' + i + '>' + i + '</a>');
            //    }
            //    if (pages > 0) {
            //        $("#pagination-div").append('<a id="next-a">&raquo;</a>');
            //    }

            $("#pagination-div").pagination({
                items: totalRequestsCount,
                itemsOnPage: pageSize,
                cssStyle: 'light-theme',
                onPageClick: function (pageNumber, event) {
                    $(".loader-div").show();
                    var status = $("#fstatus").val();
                    var category = $("#fcategories").val();
                    var tower = $("#ftowers").val();
                    var flatNo = $("input[name=fflatNo]").val() + '';
                    //            var date = $("#datepicker").datepicker("getDate");
//            var date = $('#datepicker').data('daterangepicker');
//            console.log("date in pagination: " + startDate);
//            if (date == null) {
//                date = new Date();
//            }
//            console.log("Datepicker date: " + date);
                    //            date = $.datepicker.formatDate("yymmdd", date);
//            date = startDate;
                    console.log("onPageClicked: " + pageNumber);
                    var page = $(this).attr("name");
                    var firstRow = (pageNumber - 1) * getMaxResultSize();
                    populateTableDataByPage(null, status, category, tower, flatNo, firstRow,startDate, endDate, maxResultSize);
                }
            });
        }
        
//        var callbackForStatusFilter = function(e) {
//            $(".loader-div").show();
//            var date = currentDate();
//            populateTableDataByFilters(date, $(this).attr("name"), null, null, '');
//            e.preventDefault();
//        }
//        
//        $(document).on("click", ".status-filter-a", callbackForStatusFilter);
//        
        
        //Pagination:
        
//        var maxResultSize = 2;



        var onPageNumberClick = function (e) {
            console.log("pagination anchor clicked: " + $(this).id);
            $(".pagination a").removeClass("active-a");
            $(this).addClass("active-a");
        };

        $(document).on("click", ".page-a", function () {
            $(".loader-div").show();
            var status = $("#fstatus").val();
            var category = $("#fcategories").val();
            var tower = $("#ftowers").val();
            var flatNo = $("input[name=fflatNo]").val() + '';
            //            var date = $("#datepicker").datepicker("getDate");
//            var date = $('#datepicker').data('daterangepicker');
//            console.log("date in pagination: " + startDate);
//            if (date == null) {
//                date = new Date();
//            }
//            console.log("Datepicker date: " + date);
            //            date = $.datepicker.formatDate("yymmdd", date);
//            date = startDate;
//            console.log("Parsed date: " + date + " Refresh clicked");
            var page = $(this).attr("name");
            var firstRow = (page - 1) * maxResultSize;
            populateTableDataByPage(null, status, category, tower, flatNo, firstRow,startDate, endDate, maxResultSize);
            $(".pagination a").removeClass("active-a");
            $(this).addClass("active-a");
            console.log("pagination anchor clicked: " + $(this).attr("name"));
        });
        
        $(document).on("click", "#prev-a", function() {
            
        });
        
        $(document).on("click", "#next-a", function() {
            
        });

        
    });
    
    
    
    
    

})(jQuery);