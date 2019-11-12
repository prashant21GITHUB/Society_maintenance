(function($){
    var context = "http://localhost:8080/maintain";
    function processForm( e ){
        console.log($(this).serialize());
        var URL =  context + "/login";
        var data = JSON.stringify({
            username : $("input[name=username]").val(),
            password : $("input[name=password]").val()
        });
        var callback = function(data) {
            console.log(data);
            console.log(data.success);
            
            if(data.success == true) {
                document.cookie = "username=" + $("input[name=username]").val();
                window.open("pages/home.html", "_self");
            } else {
                alert('Login failed.')
            }
        };
        
        post(URL, data, callback);
        e.preventDefault();
    }
    
    $('#loginform').submit( processForm );
})(jQuery);
