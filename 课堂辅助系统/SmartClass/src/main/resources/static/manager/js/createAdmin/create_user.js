$(function() {
	/*表单验证*/
	$('#name').blur(function(){
		var s=$('[name="name"]').val();
		if($(this).val()==""){
			message_show($(this),"请填写用户名,用户名不能为空");
		}else if(!((s<="z"&&s>="a")||(s<="Z"&&s>="A")))
		{
			message_show($(this),"用户名必须以字母开头");
		}else{
			message_clear($(this));
		}
	});
	$('#password').blur(function(){
		var password = $('[name="password"]').val();
		var len=password.length;
		if($(this).val()==""){
			message_show($(this),"请填写密码,密码不能为空");
		}else if(len<8||len>16)
		{
			$('[name="password"]').val("");
			message_show($(this),"请设置8-16位的密码");
		}else {
			message_clear($(this));
		}


	});
	/*End of表单验证*/





	//密码的显示隐藏
	$("#posswordeye").click(function(){
		if($(this).hasClass('glyphicon-eye-open')){
			$(this).removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');//密码可见
			$("[name='password']").prop('type','password');
		}else{
			$(this).removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');//密码不可见
			$("[name='password']").prop('type','text');
		};
	});

	$(".min-submit").click(function(){

		var s=$('[name="username"]').val();
		if(s=="")
		{
			alert_text("warning","请填写用户名，用户名不能为空");
			return;
		}
		if($('[name="password"]').val()=="")
		{
			alert_text("warning","请填写用户的密码,密码不能为空");
			return;
		}
		var password = $('[name="password"]').val();
		var len=password.length;
		//alert_text(len);
		if(len<8||len>16)
		{
			alert_text("warning","请设置8-16位的密码");
			$('[name="password"]').val("");
			$('[name="password"]').focus();
			return;
		}

		
		ajaxForm($('#createUserForm'),function(){
			window.location.href = '/SmartClass/manager/welcome#/SmartClass/userHTML/query_users_page';
			},function(res){
				$("#error_msg").text(res.msg);
				$(".show-table-appoint").show();
			});
	});


});

function addUser(which){

	//隐藏除了点击的行和标题行以外的行
	$(".show-table-appoint").find(".student-tr-js").not($(which).parents(".student-tr-js")).not(".show-tr").hide();
	$(which).addClass("active");

	//隐藏上边一栏
	$("#sel-area").hide();
	$(".before-choose-text").hide();
	$(".after-choose-text").show();
	$("[changePage]").remove();

	$("#sid").val($(which).parents(".student-tr-js").children("[sid]").attr("sid"));
//	$(".hidden").removeClass("hidden");


}

