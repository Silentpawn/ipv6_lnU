$(function(){

	$("#delete_user_button").click(function(){
		
		ajaxForm($('#delete_user'),function(){
			alert_text("ok","删除成功",function(){window.location.reload();});
		},function(res){
			$("#error_msg").children("#err").text(res.msg);
			$("#error_msg").removeClass("hidden");
		})
		
	});

});