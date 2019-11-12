(function ($) {
    
    $(document).ready(function () {
        
        $("#register").click(function() {
            $(".topnav-menu").removeClass("active");
            $("#register").addClass("active");
            $(".topnav-div").hide();
            $("#register-div").show();
            $("#register-div").load("../pages/register_form.html")
        });
        
    });
    
})(jQuery);