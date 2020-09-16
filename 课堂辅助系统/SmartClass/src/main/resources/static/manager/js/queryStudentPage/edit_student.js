$(function(){

	var quantichange = false;
	$("#quantization").on('input',function(){
		quantichange = true;
	});
	
	$("#edit_student").click(function(){
		
		if($("[name='college']").val() == "") {
			$("#error_msg").children("#err").text("请填写学生所属学院,学院信息不能为空");
			$("#error_msg").removeClass("hidden");
		} else if($("[name='profession']").val() == "") {
			$("#error_msg").children("#err").text("请填写学生所属专业，专业信息不能为空");
			$("#error_msg").removeClass("hidden");
		} else if($("[name='classname']").val() == "") {
			$("#error_msg").children("#err").text("请填写学生所在班级，班级信息不能为空");
			$("#error_msg").removeClass("hidden");
		} else {
			
			$("#edit_student").off("click");
			
			$.ajax({
				url:"/SmartClass/studentAPI/editStudent",
				async:false,
				type:"post",
				data: $("#update_student").serialize(),
				
				success:function(obj){
//					var obj = JSON.parse(info); //使用这个方法解析json  
					$("#util-modal").modal("toggle");
					if(obj.status=='ok'){
					 	$(".modal-dialog").css("width","600px");
	                	alert_text("ok","修改成功",function(){window.location.reload();});
					}


					
	             
				}
			});
			
//			$("#util-modal").modal("toggle");
		}
		
	});
	
	$("#reset").click(function(){
		$("#update_student")[0].reset();
	});
	$("[name='id']").val();
	
	
});