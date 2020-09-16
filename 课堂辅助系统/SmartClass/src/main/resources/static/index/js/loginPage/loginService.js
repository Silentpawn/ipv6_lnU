$(function(){
	//点击登录按钮
	$("#login-button").click(function(event) {
		
			ajaxForm($('#login-form'),function(res){
				window.location.href = '/SmartClass/manager/welcome'; 
			},function(res){//失败
				$(".error-msg").text(res.msg);
				$(".error-msg").show();
			});

	});

	// 注册
	// 点击[注册]事件 #change-password
	$("#change-password").click(function(){
		$(".error-msg-forget").hide();
		$("#forget-password-wrap").addClass('hide');
		ajaxForm($('#register-form'),function(res){
			 alert("已提交申请");
		},function(res){//失败
			$(".error-msg-forget").html(res.msg);
			$(".error-msg-forget").show();
		});

	});


})