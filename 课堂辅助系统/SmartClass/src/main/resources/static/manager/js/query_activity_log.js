$(function(){
	$.ajax({
		url:"/Activisys/activity/query_activity_log",
		async:false,
		success:function(a){
			$('#ajax_result').html(a);
		}
	});
	
	$("#search").click(function(){
		var starttime = $("#starttime").val();
		var endtime = $("#endtime").val();
		$.ajax({
			url:"/Activisys/activity/query_activity_log",
			async:false,
			data:{
				"starttime":starttime,
				"endtime":endtime,
			},
			success:function(a){
				$('#ajax_result').html(a);
			}
		});
	});
	
	/****************************日期组件**********************************************/
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
	
});

