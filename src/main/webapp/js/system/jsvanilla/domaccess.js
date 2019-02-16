$(document).ready(function() {
    
   
    $(window).scroll( function(){
    
       
        $('.ocultar').each( function(i){
            
            var bottom_of_object = $(this).position().top + $(this).outerHeight();
            var bottom_of_window = $(window).scrollTop() + $(window).height();
            
           
            if( bottom_of_window > (bottom_of_object+200) ){
                
                $(this).animate({'opacity':'1'},3000);
                    
            }
            
        }); 
    
    });
    
});