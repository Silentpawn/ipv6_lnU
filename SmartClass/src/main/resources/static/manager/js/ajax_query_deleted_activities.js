$(function(){

	//删除活动deleteActivity
	$(".glyphicon.glyphicon-trash ").click(function(){
		confirm_text("确认彻底删除 ["+ $(this).parents(".show-tr").children("[aname]").attr("aname") +"] 活动？",$(this),function(which){
			$.ajax({
				type:'post',
				url:"/Activisys/activity/deleteActivityReal",
				dataType: 'text', 
				data: {
					'activity.aid': $(which).parents(".show-tr").children("[aid]").attr("aid"),
				},
				async: false,
				success: function(data) {
					//alert_text(data);
					if(data=="success")
					{
						alert_text("ok","删除成功",function(){history.go(0);});
					}


				}
			});
		});


	});

	//恢复删除的活动
	$(".glyphicon-refresh").click(function(){
	
		var aid = $(this).parents(".show-tr").children("[aid]").attr("aid");
		alert_text("warning", "活动恢复后,报名信息不会改变,但需重置统计负责人账户", function(){
			
			$('#reset_statisticsManager').find("#reset-submit").attr("aid",aid);
			//alert(aid);
			$('#reset_statisticsManager').modal();
			
			$('#reset_statisticsManager').find("#reset-submit").off('click').on('click',function(){
				var info;
				var s=$('#reset_StatisticsManager_user').val();
				s=s.substr(0,1);


				if($('#reset_StatisticsManager_password').val().length <8 ||$('#reset_StatisticsManager_password').val().length >16){
					$("#reset_warning-message").text("统计负责人密码长度应在8-16之间");
					$("#reset_warning-message").parents(".warmsg").show();
					return;
				}



				if(!((s<="z"&&s>="a")||(s<="Z"&&s>="A"))) {
					$("#reset_warning-message").text("用户名必须以字母开头");
					$("#reset_warning-message").parents(".warmsg").show();
					return;
				}else if($('#reset_StatisticsManager_password').val()==""){
					$("#reset_warning-message").text("统计负责人密码不能为空");
					$("#reset_warning-message").parents(".warmsg").show();
					return;
				}


				$.ajax({
					url:"/Activisys/user/IsUserExist",async:false,
					data:{'userPO.username':$('#reset_StatisticsManager_user').val()},
					success:function(a){
						info = a;
					}
				});

				if(info!="success"){

					$("#reset_warning-message").text("该账户已经存在,更改账户重新注册");
					$("#reset_warning-message").parents(".warmsg").show();

					return;
				}
				
				//ajax 恢复活动
				$.ajax({
					type:'post',
					url:"/Activisys/activity/recovery_activity",
					dataType: 'text', 
					data: {
						'activity.aid': $(this).attr("aid"),
						'statisticsManagerUser':$('#reset_StatisticsManager_user').val(),
						'statisticsManagerPassword':$('#reset_StatisticsManager_password').val()
					},
					async: false,
					success: function(data) {
						//alert_text(data);
						if(data=="success")
						{
							alert_text("ok","恢复成功",function(){history.go(0);});
						}
	
	
					}
				});
				
				
			});
			
		});

	});

	
	
	$("[disable]").off("click");
	$("[disable]").attr("style","color:#ddd");

});
