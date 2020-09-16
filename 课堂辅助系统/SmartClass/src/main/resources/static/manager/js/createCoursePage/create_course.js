//var aname = $('input[name="name"]');
//var applyStartTime = $('input[name="applyStartTime"]');
//var applyEndTime = $('input[name="applyEndTime"]');
//var activityStartTime = $('input[name="courseStartTime"]');
//var activityEndTime = $('input[name="courseEndTime"]');
//var minEnrollment = $('input[name="minEnrollment"]');
//var maxEnrollment = $('input[name="maxEnrollment"]');
//var college = $('input[name="college"]');
//var grade = $('input[name="grade"]');
//var profession = $('input[name="profession"]');
//var info = $('[name="info"]');
$(function() {

	$(".js-time").datetimepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd hh:ii',
		autoclose: true,
		minuteStep: 5,
		maxView: 2,
		startDate: '2017-03-24'
	});

	$(".js-endtime").datetimepicker().on('changeDate', function() {

		$(".js-starttime").datetimepicker('setEndDate', new Date($(this).val() + ":00"));
		$(".js-starttime1").datetimepicker('setStartDate', new Date($(this).val() + ":00"));
	});

	$(".js-starttime").datetimepicker().on('changeDate', function() {

		$(".js-endtime").datetimepicker('setStartDate', new Date($(this).val() + ":00"));
	});

	$(".js-starttime1").datetimepicker().on('changeDate', function() {

		$(".js-endtime").datetimepicker('setEndDate', new Date($(this).val() + ":00"));
	});

	/* ---------- input.onchange触发 -------------- */ 

	$(".js-time").on('change', function() {  

		if($(this).val().length>16)
		{
			var a = $(this).val();
			$(this).val(a.substr(0,16));
		}
		//
		var reg =  /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d$/;
		var _txt = $(this).val();
		if (!reg.test(_txt)) {
			$(this).val("");
		}

	});

	$(".js-endtime").on('change', function() {  
		var starttime = new Date($(".js-starttime").val() + ":00");
		var endtime = new Date($(".js-endtime").val() + ":00");
		var starttime1 = new Date($(".js-starttime1").val() + ":00");

		if($(".js-starttime").val()!="" && $(".js-endtime").val()!= "" && starttime >= endtime){
			message_show($(this),"报名结束时间应晚于报名开始时间，请重新填写报名结束时间");
			$(this).val("");
		}
		if($(this).val() != ""){
			$(".js-starttime").datetimepicker('setEndDate', new Date($(this).val() + ":00"));
			$(".js-starttime1").datetimepicker('setStartDate', new Date($(this).val() + ":00"));
			if($(".js-starttime").val()!=""){
				message_clear($(this));
			}
		}
	});

	$(".js-starttime").on('change', function() {  

		if($(".js-endtime").val()!="" && $(".js-starttime").val()!= ""){
			var starttime = new Date($(".js-starttime").val() + ":00");
			var endtime = new Date($(".js-endtime").val() + ":00");
			if(starttime >= endtime){
				message_show($(this),"报名开始时间应早于报名结束时间，请重新填写报名开始时间");
				$(this).val("");
			}
		}
		if($(this).val() != ""){
			$(".js-endtime").datetimepicker('setStartDate', new Date($(this).val() + ":00"));
			if($(".js-endtime").val()!=""){
				message_clear($(this));
			}
		}
	});



	$("#hidden-button").click(function() {
		if ($(".hidden11").css("display") == "none") {
			$(".hidden11").css("display", "inline");
		} else {
			$(".hidden11").css("display", "none");
		}
	});

	$(".change-color-button").click(function() {
		$(".hidden22").css("display", "inline");
		$(".change-color-button").removeClass("change-color");
		$(this).addClass("change-color");
	});

	$(".search-button").click(function() {
		$(".search").css("display", "block");
	});

//	//按钮点击变色通用函数
//	function changeColor(element) {
//		if (element.hasClass("active"))
//			element.removeClass("active");
//		else
//			element.addClass("active");
//	}

	//选择面向对象相关js代码
//	var group1 = $("[data-group-type='1']").children("[data-group='1']").click(function() {
//		changeColor($(this));
//		//点击2016 2017等，向input传入参数 TODO
//
//	});

//	var group2 = $("[data-group-type='1']").children("[data-group='2']").click(function() {
//		if (group2.hasClass("active")) {
//			//收起打碎专业，清空之前input传入的参数 TODO
//			$(".smash").children("button").removeClass("active");
//
//			group1.removeAttr("disabled");
//			$('.smash').fadeOut(500, function() {
//				group2.removeClass("active");
//			});
//			group2.removeClass("active");
//		} else {
//			//展开打碎专业，清空之前input传入的参数 TODO
//			if (group1.hasClass("active"))
//				group1.removeClass("active");
//			group1.attr("disabled", true);
//			group2.addClass("active");
//			$('.smash').fadeIn(300, function() {
//				group2.addClass("active");
//			});
//		}
//
//	});

//	//.smash的点击实现
//	$(".smash").children("button").click(function() {
//		changeColor($(this));
//	});
//
//	// 选择活动参与人数，相关js
//	$("[data-group-type='2']").children("button").not("#smash2-button").click(function() {
//		if ($(this).hasClass("active")) {
//			$(this).removeClass("active");
//		} else {
//			$("[data-group-type='2']").children().removeClass("active");
//			$(this).addClass("active");
//		}
//
//	});
//
//	$('#smash2-button').click(function() {
//		if ($(this).hasClass("active")) {
//			$(this).removeClass("active");
//			$("[data-group-type='2']").children().removeAttr("disabled");
//			$("[data-input-control]").parent(".smash2").removeClass("active");
//			$("[data-input-control]").val("");
//			$('.smash2').fadeOut(500, function() {
//				$('#smash2-button').removeClass("active");
//			});
//		} else {
//			$("[data-group-type='2']").children().removeClass("active");
//			$(this).addClass("active");
//			$("[data-group-type='2']").children().not(this).attr("disabled", true);
//
//			$('.smash2').fadeIn(500, function() {
//				$('#smash2-button').addClass("active");
//			});
//		}
//
//	});
//
//	$("[data-input-control]").on('input',function(){
//		if ($(this).val()=="") {
//			$(this).parent(".smash2").removeClass("active");
//		}
//		else{
//			$(this).parent(".smash2").addClass("active");
//		}
//	});
//
//	$('.smash2').click(function(){
//		$(this).children("input").focus();
//	});
//
//
//	$("#packup").click(function(){
//		$(".ajax").empty();
//	});

	/*----------- 表单验证 -------------------*/

	$('#name').blur(function(){
		if($(this).val()==""){
			message_show($(this),"请填写课程名称，课程名称不能为空");
		}else {
			message_clear($(this));
		}
	});

	$('#starttime').blur(function(){
		if($(this).val()==""){
			message_show($(this),"请填写开课时间，开课时间不能为空");
		}
	});
	$('#endtime').blur(function(){
		if($(this).val()==""){
			message_show($(this),"请填写结束时间，结束时间不能为空");
		}
	});
	$('#location').blur(function(){
		if($(this).val()==""){
			message_show($(this),"请填写上课地点，上课地点不能为空");
		}
	});
	$('#studentNumber').blur(function(){
		if($(this).val()==""){
			message_show($(this),"请填写上课人数，上课人数不能为空");
		}
	});
	$('#credit').blur(function(){
		if($(this).val()==""){
			message_show($(this),"请填写课程学分，课程学分不能为空");
		}
	});
	//提交表单
	$('#submit').click(function() {
		//表单验证
		if($('#name').val()=="")
		{
			alert_text("warning","请填写开课时间，开课时间不能为空");
			return;
		}
		if($('#starttime').val()=="")
		{
			alert_text("warning","请填写开课时间，开课时间不能为空");
			return;
		}
		if($('#endtime').val()=="")
		{
			alert_text("warning","请填写结束时间，结束时间不能为空");
			return;
		}
		if($('#location').val()=="")
		{
			alert_text("warning","请填写上课地点，上课地点不能为空");
			return;
		}
		if($('#studentNumber').val()=="")
		{
			alert_text("warning","请填写上课人数，上课人数不能为空");
			return;
		}
		if($('#credit').val()=="")
		{
			alert_text("warning","请填写课程学分，课程学分不能为空");
			return;
		}
		if($('#tno').val()=="")
		{
			alert_text("warning","请填写开课教师的教工号");
			return;
		}

		
		ajaxForm($('#form-hidden'),function(){
			alert("创建成功");
			window.location.href = '/SmartClass/manager/welcome#/SmartClass/courseHTML/query_course_page';
		},function(res){
			alert_text("warning",res.msg);
		})

	});

	
	



});
//var which;

//function addStatisticsManager(a){
//	which=a;
//	$('#create_activity_addStatisticsManager').modal();
//};

//function plus(){
//	statisticsManagerPassword.val($('#StatisticsManager_password').val());
//	statisticsManagerUser.val($('#StatisticsManager_user').val());
//	var info;
//	var s=$('#StatisticsManager_user').val();
//	s=s.substr(0,1);
//
//
//	if($('#StatisticsManager_password').val().length <8 ||$('#StatisticsManager_password').val().length >16){
//		$("#warning-message").text("统计负责人密码长度应在8-16之间");
//		$("#warning-message").parents(".warmsg").show();
//		return;
//	}
//
//
//
//	if(!((s<="z"&&s>="a")||(s<="Z"&&s>="A"))) {
//		$("#warning-message").text("用户名必须以字母开头");
//		$("#warning-message").parents(".warmsg").show();
//		return;
//	}else if(statisticsManagerPassword.val()==""){
//		$("#warning-message").text("统计负责人密码不能为空");
//		$("#warning-message").parents(".warmsg").show();
//		return;
//	}
//
//
//	$.ajax({
//		url:"/Activisys/user/IsUserExist",async:false,
//		data:{'userPO.username':$('#StatisticsManager_user').val()},
//		success:function(a){
//			info = a;
//		}
//	});
//
//	if(info!="success"){
//
//		$("#warning-message").text("该账户已经存在,更改账户重新注册");
//		$("#warning-message").parents(".warmsg").show();
//
//		return;
//	}
//
//
//
//
//
//	if($(".party-tr-js").length > 0){
//		$("#party-table-js").html('<tr class="show-tr"><th>学院</th><th>年级</th><th>专业</th><th>学号</th><th>姓名</th><th><span class="left-margin">操作</span></th></tr>');
//	}
//	//将这个学生复制到报名单列表里 // alert_text("warning",$(this).parents(".student-tr-js").children("td:nth-child(5)").text());
//	var copy = $(which).parents(".student-tr-js").clone();
//	$("#party-table-js").append(copy);
//	//设置变量：当前最后一个tr，也就是刚复制过去的这个
//	var lasttr = $("#party-table-js").find("tr:last-child");
//	//将class改为party-tr-js
//	lasttr.removeClass("student-tr-js");
//	lasttr.addClass("party-tr-js");
//	//删除第一个td
//	lasttr.children("td:first-child").remove();
//	//将复制过去的加号改成减号
//	lasttr.children("td:last-child").html("<span class='glyphicon glyphicon-remove left-margin operate operate-min' minus></span>");
//
//	//显示活动名单三项
//	$("#party-title-js").show();
//	$("#party-table-js").show();
//	$("#commit-activity-button").show();
//
//	// 减号点击事件
//	$("[minus]").click(function(event) {
//		//删去这一列
//		$(this).parents(".party-tr-js").remove();
//		//隐藏下面三项
//		$("#party-title-js").hide();
//		$("#party-table-js").hide();
//		$("#commit-activity-button").hide();
//
//	});
//	which=null;
//	$('#create_activity_addStatisticsManager').modal('toggle');
//	$('#party-title-js .title').text("将要任命其为活动统计负责人（负责人账户为："+statisticsManagerUser.val()+"）");
//}
//
//


