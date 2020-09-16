$(window).resize(function(){
	$("#background").css("height",$(window).height()+"px");
	$("#background-img").css("top",$(window).height()/2-$(window).width()*0.62/2 + "px");
	$("#wrap").css("height",$(window).height()+"px");
	//为表格页提供额外高度计算
	$(".table-scroll").css("height",$(window).height()-160+"px");
});


$(function(){

	if($('title').length>1)
	{
		$(".function-contents").addClass("ajax-login");
		$(".function-contents").removeClass("function-contents");
	}


	$("#background").css("width",$(window).width() +"px");
	$("#background").css("height",$(window).height() +"px");
	$("#background-img").css("width",$(window).width() +"px");
	$("#background-img").css("top",$(window).height()/2-$(window).width()*0.62/2 + "px");
	$("#wrap").css("height",$(window).height()+"px");

	//为表格页提供额外高度计算
	$(".table-scroll").css("height",$(window).height()-160+"px");
	$(".table").show();

	$("input").focus(function(event) {
		$(this).addClass('focus-input');
	});
	$("input").blur(function(event) {
		$(this).removeClass('focus-input');
	});

	$("[name='snoorusername']").focus();

	$("#posswordeye").click(function(){
		if($(this).hasClass('glyphicon-eye-open')){
			$(this).removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');//密码可见
			$("[name='password']").prop('type','password');
		}else{
			$(this).removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');//密码不可见
			$("[name='password']").prop('type','text');
		};
	});

	$("[name='password']").keyup(function() {
		if (event.keyCode == "13") {	//keyCode=13是回车键
			$("#login-button").click();
		}
	});

	

	$("#regard-btn").click(function(){
		if($("#forget-password-wrap").hasClass('hide')){
			if($("#regard-wrap").hasClass('hide')){
				$("#regard-wrap").removeClass("hide");
				$(this).addClass("a-eee-active");
			} else {
				$("#regard-wrap").addClass("hide");
				$(this).removeClass("a-eee-active");
			}
		}else {
			$("#forget-password-wrap").addClass("hide");
			$("#forget-password-btn").removeClass("a-eee-active");
			$("#regard-wrap").removeClass("hide");
			$(this).addClass("a-eee-active");
		}
	});

	// ajax提交反馈信息
	$("#regard-form").submit(function(){
		if($("#msg").val() == ""){
			$(".error-msg-regard").html("发送失败，反馈信息不能为空");
			$(".error-msg-regard").show();
		} else {
			$.ajax({
				url:'/Activisys/system/feedbackWithNoLogin',
				data:$("#regard-form").serialize(),
				success: function(data){
					if(data == '发送成功'){
						$("[name='contactInfo']").val("");
						$("[name='msg']").val("");
						$("#regard-wrap").addClass('hide');
						$("#regard-success-wrap").removeClass('hide');

						var second = parseInt(2);
						$("#second").html(3);
						var iCount = window.setInterval(function() {
							$("#second").html(second);
							second--;
							if(second == -1){
								clearInterval(iCount);
								$("#regard-success-wrap").addClass('hide');
								$("#regard-btn").removeClass("a-eee-active");
							}
						}, 1000);
					}
				}
			});
		}
		return false;
	});


	// 忘记密码的废话
	$("#a-joke").click(function(){
		$("#forget-fir").hide(); 
		$("#forget-thi").show();
	});

	// 点击[发送邮件验证]事件成功后 #send-email-btn,用户名输入框改成只读
	$("#send-email-btn").click(function(){
		if($("[name='forgetemail']").val() == ""){
			$(".error-msg-forget").html("绑定邮箱不能为空，请重新填写");
			$(".error-msg-forget").show();
			return false;
		}
		$("[name='forgetemail']").attr("readonly","readonly");
		$.ajax({
			async: false,
			url:"/Activisys/login/forgetPassword",
			type:"post",
			data: {
				"forgetemail" : $("[name='forgetemail']").val()
			},
			success: function(data){
				if(data == "sendsuccess"){
					$("#send-email-btn").html("<b> 重新发送</b>");
					$(".error-msg-forget").hide();
				}else if(data == "noemail"){
					$(".error-msg-forget").html("该邮箱不存在于系统中，无法完成验证");
					$(".error-msg-forget").show();
				}else if(data=="error"){
					$(".error-msg-forget").html("当前账户信息异常，请联系管理员");
					$(".error-msg-forget").show();

				}
			}
		});

	});


	// 点击[填写好了]事件 #confirm-btn 请求ajax 成功的话 #change-password-area.show,验证码输入框改成只读
	$("#confirm-btn").click(confirmClick);

	//点击注册按钮
	$("#forget-password-btn").click(function(){

		if($("#regard-wrap").hasClass('hide')){
			if($("#forget-password-wrap").hasClass('hide')){
				$("#forget-password-wrap").removeClass("hide");
				$(this).addClass("a-eee-active");
			} else {
				$("#forget-password-wrap").addClass("hide");
				$(this).removeClass("a-eee-active");
			}
		}else {
			$("#regard-wrap").addClass("hide");
			$("#regard-btn").removeClass("a-eee-active");
			$("#forget-password-wrap").removeClass("hide");
			$(this).addClass("a-eee-active");
		}

	});
	// 点击眼睛显示/不显示密码
	$("#student-change-password-eye").click(function(){
		if($(this).hasClass('glyphicon-eye-open')){
			$(this).removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');//密码可见
			$("[name='studentNewPassword']").prop('type','password');
		}else{
			$(this).removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');//密码不可见
			$("[name='studentNewPassword']").prop('type','text');
		};
	});

	$("#user-change-password-eye").click(function(){
		if($(this).hasClass('glyphicon-eye-open')){
			$(this).removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');//密码可见
			$("[name='userNewPassword']").prop('type','password');
		}else{
			$(this).removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');//密码不可见
			$("[name='userNewPassword']").prop('type','text');
		};
	});

	// 点击重置,放开用户名,验证码输入框并清空
	$("#reset-btn").click(function(){
		resetForget();
	});

});

function resetForget(){
	$("#forget-fir").hide();
	$(".error-msg-forget").html("");
	$(".error-msg-forget").hide();
	$("#forget-password-wrap").find("input").val("");
	$("#change-password-area").hide();
	$("[name='verifiCode']").removeAttr("readonly");
	$("[name='forgetemail']").removeAttr("readonly");

	$("#confirm-btn").click(confirmClick);
}


function confirmClick(){
	$.ajax({
		url:"/Activisys/login/confirmVerifiCode",
		type:"post",
		data: {
			"forgetemail" : $("[name='forgetemail']").val(),
			"verifiCode" : $("[name='verifiCode']").val()
		},
		dataType:"json",
		success: function(json){
			if(json.info == "true"){
				$(".error-msg-forget").hide();
				if(json.resultLogin.uid != null){
					$("[name='uid']").val(json.resultLogin.uid);
					$("#user-new-password").show();
				}
				if(json.resultLogin.sid != null){
					$("[name='sid']").val(json.resultLogin.sid);
					$("#student-new-password").show();
				}
				$("#change-password-area").show();
				$("[name='verifiCode']").attr("readonly","readonly");
				$("[name='forgetemail']").attr("readonly","readonly");
				$("#confirm-btn").off("click");
			}else if(json.info == "false"){
				$(".error-msg-forget").html("验证码错误，请进行核对");
				$(".error-msg-forget").show();
			}
		}
	});
}