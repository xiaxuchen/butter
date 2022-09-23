
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("/user_assets/img/backgrounds/2.jpg");

    var $pwd = $("#form-password");
    var $confirm = $("#password-confirm");
    var $hint = $("#hint");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() === "") {
    			e.preventDefault();
    			$(this).addClass('input-error');
                $hint.text("用户名或密码不能为空！")
    		}else if( $pwd.val() !== $confirm.val())
			{
                e.preventDefault();
                $pwd.addClass('input-error');
                $confirm.addClass('input-error');
                $hint.text("两次密码不匹配！")
			}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
    
});
