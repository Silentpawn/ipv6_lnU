$(function() {
	
	$.ajax({
		url : "/Activisys/activity/commit_activity_load",
		async : false,
		type : "post",
		data : {},
		success : function(data) {
			$(".commit-activity-ajax").html(data);
		}	
	});
	
	
	$('[already]').hide();
	
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
		$("#aid").val($(this).children("td[aid='aid']").text());
		$.ajax({
			url : "/Activisys/student/queryActivityStudentOfCommitActivity",
			async : false,
			type : "post",
			data : {
				'aid' : $("#aid").val()
			},
			success : function(data) {
//				alert_text(data);
				$("#party-table-js").html(data);
			}

		});

	});

	// 当点击重新选择活动时，显示所有活动，标题改变
	$("#reselect-js").click(function() {
		$("#commit-activity-button").hide();
		$(".ajax").html("");
		$('[already]').hide();
		$(".lllll").hide();
		$("#party-title-js").hide();
		$("#party-table-js").hide();
		$(".activity-js").show();
		$(".activity-js").removeClass("show-active-hover");
		$(".activity-js").addClass("show-hover");
		$(".before-choose-text").show();
		$(".after-choose-text").hide();
		$("#aid").val("");
		$("[type='checkbox']").remove();
	});



	$("#commit-activity-button").click(function(event) {

		if($("[name='sid_list']").length <= 0)
		{
			alert_text("warning","当前报名表为空,不能提交");
			return;
		}
		// 检查是否选择了活动
		if ($("#aid").val() == "") {
			alert_text("warning","哈哈哈不能提交这个报名表：请指定一次活动，再进行活动报名！");
		} else {
			//数据写入模态窗面板
			$(".selected-aname").text($(".show-active-hover").children("td:nth-child(3)").text());
			$(".count-party-students").text($(".party-tr-js").length);
			// 显示模态窗确认
			$("#commit_modal").modal();
		}

	});

	// 模态窗确认提交报名表按钮，提交表单
	$("#modal-commit-button").click(function(event) {
		$.ajax({
			url:"/Activisys/activity/commit_activity",
			async:false,
			type:"post",
			data:$("#commit-activity-form").serialize(),
			success:function(data){
				$("#modal-commit-button").modal('toggle');
				if(data=="success")
				{
					alert_text("ok","报名表提交成功",function(){history.go(0);});
				}else{
//					alert_text(data);
					alert_text("warning","报名表提交失败");
				}
			}
		});
	});

	$("#byname").click(function() {
		$.ajax({
			url : "/Activisys/student/queryStudentOfCommitActivity",
			async : false,
			type : "post",
			data : {
				'studentPO.sname' : $("input[name='queryStudentValue']").val()
			},
			success : function(data) {
//				alert_text(data);
				$(".ajax").html(data);
			}
		});

	});


	$("#bysno").click(function() {
		$.ajax({
			url : "/Activisys/student/queryStudentOfCommitActivity",
			async : false,
			type : "post",
			data : {
				'studentPO.sno' : $("input[name='queryStudentValue']").val()
			},
			success : function(data) {
//				alert_text(data);
				$(".ajax").html(data);
			}
		});
	});
	
	$("#packup").click(function(){
		$(".ajax").empty();
	});

});

//点击加号时
function plus(which) {


	$('[tips]').remove();
	var isNoRepeat = true;
	var ready_add_sno = $(which).parents(".student-tr-js")
	.children("td:nth-child(5)").text();
	if ($(".party-tr-js").length > 0) {
		$(".party-tr-js").each(function(index, e) {
			if ($(this).children("td:nth-child(5)")
					.text() == ready_add_sno) {
				isNoRepeat = false;
				alert_text("warning","哈哈哈不能重复添加这个人：这个人在报名名单中存在");
			}
		});
	}

	if (isNoRepeat == true) {
		// 将这个学生复制到报名单列表里 //
		//alert_text($(which).parents(".student-tr-js").children("td:nth-child(5)").text());
		var copy = $(which).parents(".student-tr-js").clone();
		$("#party-table-js").append(copy);

		// 设置变量：当前最后一个tr，也就是刚复制过去的这个
		var lasttr = $("#party-table-js").find("tr:last-child");
		//将学号加进form表单
		var plus_sid = lasttr.children("td:first-child").attr("sid");
		$("#commit-activity-form").append("<input type='checkbox' name='sid_list' value='"+ plus_sid +"' checked />");
		// 将class改为party-tr-js
		lasttr.removeClass("student-tr-js");
		lasttr.addClass("party-tr-js");
		var nowindex = parseInt($("#party-table-js").find("tr:nth-last-child(2)").children("td:first-child").text());
		// 将复制过去的列序号改变为倒数第二列序号+1，若报名单中没有列，则为1
		if (nowindex) {
			lasttr.children("td:first-child").text(nowindex + 1);
		} else {
			lasttr.children("td:first-child").text(1);
		}
		// 将复制过去的加号改成减号
		lasttr.children("td:last-child").html("<span class='glyphicon glyphicon-minus left-margin operate operate-min' minus></span>");

		// 显示活动名单三项
		$("#party-title-js").show();
		$("#party-table-js").show();
		$("#commit-activity-button").show();

		// 减号点击事件
		$("[minus]").click(function(event) {
			// 删去这一列
			$(this).parents(".party-tr-js").remove();
			//从表单中删去这个人
			var minus_sid = $(this).parents(".party-tr-js").children("td:first-child").attr("sid");
			$("#commit-activity-form").children("[value='" + minus_sid + "']").remove();
			// 如果活动名单为空，则隐藏下面三项
			if ($(".party-tr-js").length < 1) {
				$("#party-title-js").hide();
				$("#party-table-js").hide();
				$("#commit-activity-button").hide();
			}
			// 重新排列序号
			$(".party-tr-js").each(function(index, e) {
				$(e).children("td:first-child").text(index + 1);
			});

		});
	}

}