
$(function(){

	$(".js-time").datetimepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd hh:ii',
		autoclose: true,
		minuteStep: 5,
		maxView: 2,
		startDate: '2017-01-10'
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




	/*------------------ copy ----------*/


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
				message_show($(this),"开课时间应早于报名结束时间，请重新填写开课时间");
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

		
		ajaxForm($('#form-hidden'),function(){
			$("#big-util-modal").modal("toggle");
			alert_text("ok","修改成功",function(){window.location.reload();});
		},function(res){
			$("#big-util-modal").modal("toggle");
			alert_text("warning",res.msg);
		})
		

	});

});