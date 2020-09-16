$(function() {
	
	$.ajax({
		url : "/SmartClass/courseAJAX/studentCourse",
		async : false,
		type : "post",
		data : {
		},
		success : function(data) {
			$(".finish_onload_ajax").html(data);
		}	
	});
	
	
	
	//$('[already]').hide();
	// 当选择了一个活动时，隐藏其他活动，标题改变
	$(".activity-js").click(function() {
		$(".lllll").show();
		$("#party-title-js").show();
		$("#party-table-js").show();
		$("#commit-activity-button").show();
		$(".activity-js").hide();
		$(this).show();
		$(this).removeClass("show-hover");
		$(this).addClass("show-active-hover");
		$(".before-choose-text").hide();
		$(".after-choose-text").show();
		$("#cid").val($(this).attr("cid"));
		$.ajax({
			url : "/SmartClass/courseAJAX/queryCourseStudent",
			async : false,
			type : "post",
			data : {
				'id' : $("#cid").val()
			},
			success : function(data) {
//				alert_text(data);
				$(".activity_student_ajax").html(data);
			}

		});

	});

	// 当点击重新选择活动时，显示所有活动，标题改变
	$("#reselect-js").click(function() {
		$(".activity-js").show();
		$(".lllll").hide();
		$('.activity_student_ajax').html("<table class='show-table-commit control-name-width'>" +
				"<tr class='show-tr'><th>选择一个课程，在这里可以看到课程名单。</th></tr></table>");
		$(".activity-js").removeClass("show-active-hover");
		$(".activity-js").addClass("show-hover");
		$(".before-choose-text").show();
		$(".after-choose-text").hide();
		$("#aid").val("");
	});


	$("#byname").click(function() {
		$("[lastclick]").text("sname");
		$("[lastinput]").text($("input[name='queryStudentValue']").val());
		
		$.ajax({
			url : "/SmartClass/courseAJAX/queryStudent",
			async : false,
			type : "post",
			data : {
				'name' : $("input[name='queryStudentValue']").val()
			},
			success : function(data) {
//				alert_text(data);
				$(".query_students_ajax").html(data);
			}
		});

	});


	$("#bysno").click(function() {
		$("[lastclick]").text("sno");
		$("[lastinput]").text($("input[name='queryStudentValue']").val());
		
		$.ajax({
			url :  "/SmartClass/courseAJAX/queryStudent",
			async : false,
			type : "post",
			data : {
				'sno' : $("input[name='queryStudentValue']").val()
			},
			success : function(data) {
//				alert_text(data);
				$(".query_students_ajax").html(data);
			}
		});
	});
	
	$("#packup").click(function(){
		$(".query_students_ajax").empty();
	});

	//确认名单
	$("#confirm").click(function(){
		$(".party-tr-js").each(function(){
			var plus_sid = $(this).children("td:first-child").attr("sid");
			
			//将学号加进form表单
			$("#finish-activity-form").append("<input type='checkbox' name='sidList' value='"+ plus_sid +"' checked />");
			
		});
		$.ajax({
			url:"/SmartClass/courseAPI/updateStudentList",
			async:false,
			type:"post",
			data:$("#finish-activity-form").serialize(),
			success:function(info){
				$("#confirm_modal").modal('toggle');
				if(info.status == "ok")
				{
					alert_text("ok","操作成功",function(){history.go(0);});
					
				}else{
					alert_text("warning",info.msg);
				}
			}
		});
		
	});
	

	
	
//	//确认名单并结束活动
//	$("#finish_activity").click(function(){
//		
//		// 检查是否选择了活动
//		if ($("#aid").val() == "") {
//			//绕过js才会到这里
//			alert_text("warning","不要尝试绕过js，请先选择一个活动");
//		} else {
//			
//			//数据写入模态窗面板
//			$(".selected-aname").text($(".show-active-hover").children("td:nth-child(3)").text());
//			$(".count-party-students").text($(".party-tr-js").length);
//			// 显示模态窗确认
//			$("#finish_modal").modal();
//		}
//		
//	});
//	
//	// 模态窗确认提交并结束活动按钮，提交表单
//	$("#modal-finish-button").click(function(event) {
//		
//		//$("#modal-finish-button").off("click");
//		//把活动学生名单转成两个list，写在表单里准备提交
//		
//		$(".party-tr-js").each(function(){
//			var plus_change_quantization = $(this).children(".change_quantization").text();
//			var plus_sid = $(this).children("td:first-child").attr("sid");
//			
//			//将学号加进form表单
//			$("#finish-activity-form").append("<input type='checkbox' name='sid_list' value='"+ plus_sid +"' checked />");
//			$("#finish-activity-form").append("<input type='checkbox' name='change_quantization_list' value='"+ plus_change_quantization +"' checked />");
//			
//		});
//
//		$.ajax({
//			url:"/Activisys/activity/finish_activity",
//			async:true,
//			type:"post",
//			data:$("#finish-activity-form").serialize(),
//			success:function(info){
//				if(info.trim() == "finish_success")
//				{
//					alert_text("ok","操作成功",function(){history.go(0);});
//				}else{
//					alert_text("warning",info);
//				}
//			}
//		});
//		
//		alert_text("waiting","正在发送邮件通知相关学生...该过程耗时较长,请耐心等待.");
//	});
	
});

//点击加号时
function plus(which) {
	
	var isNoRepeat = true;
	var ready_add_sid = $(which).parents(".student-tr-js")
	.children("td:nth-child(1)").attr("sid");
	if ($(".party-tr-js").length > 0) {
		$(".party-tr-js").each(function(index, e) {
			if ($(this).children("td:nth-child(1)")
					.attr("sid") == ready_add_sid) {
				isNoRepeat = false;
				alert_text("warning","不能重复添加这个人");
			}
		});
	}
	
	if (isNoRepeat == true) {
		// 将这个学生复制到报名单列表里 //
		//alert_text($(which).parents(".student-tr-js").children("td:nth-child(5)").text());
		var copy = $(which).parents(".student-tr-js").clone();
		//如果原来活动没有信息，增加表头
		if($("[tips]").length>0){
			$("[tips]").remove();
			$(".party-table-js").html("<tr class='show-tr'><th>序号</th><th>学院</th><th>年级</th><th>专业</th><th>学号</th><th>姓名</th><th><span class='left-margin'>操作</span></th>/tr>");
		}
		$(".party-table-js").append(copy);

		// 设置变量：当前最后一个tr，也就是刚复制过去的这个
		var lasttr = $(".party-table-js").find("tr:last-child");
		
		// 将class改为party-tr-js
		lasttr.removeClass("student-tr-js");
		lasttr.addClass("party-tr-js");
		var nowindex = parseInt($(".party-table-js").find("tr:nth-last-child(2)").children("td:first-child").text());
		// 将复制过去的列序号改变为倒数第二列序号+1，若报名单中没有列，则为1
		if (nowindex) {
			lasttr.children("td:first-child").text(nowindex + 1);
		} else {
			lasttr.children("td:first-child").text(1);
		}
		// 将复制过去的加号改成复选框
		lasttr.children("td:last-child").html("<input class='operate-checkbox left-margin' name='partycheckbox' type='checkbox'>");
		
		lasttr.children("td:nth-child(7)").addClass("change_quantization");

	}

}