$(function(){

	$("#edit_user").click(function(){

		if($("[name='username']").val() == "") {
			$("#error_msg").children("#err").text("请填写用户登录名,用户登录名不能为空");
			$("#error_msg").removeClass("hidden");
		} else if($("[name='password']").val() == "") {
			$("#error_msg").children("#err").text("请填写用户密码，用户密码不能为空");
			$("#error_msg").removeClass("hidden");
		} else if($("[name='password']").val().length <8 ||$("[name='password']").val().length >16) {
			$("#error_msg").children("#err").text("用户密码不合法，请设置8-16位的密码");
			$("#error_msg").removeClass("hidden");
		} else {
//			alert_text("before ajax");
			
			ajaxForm($('#update_user'),function(){
				$("#util-modal").modal("toggle");
				alert_text("ok","修改成功",function(){window.location.reload();});
				
			},function(){
				
			})
		}

	});

	$("#reset").click(function(){
		$('#update_user')[0].reset();
		$("[disabled]").children("input").attr("disabled", true);
		$("[disabled]").addClass("disabled");
	});


});