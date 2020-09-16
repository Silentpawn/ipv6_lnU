$(function(){
	$.ajax({
		url:"/SmartClass/studentAJAX/queryStudent",
		async:false,
		success:function(a){
			$('#ajax_result').html(a);
		}
	});
	
	$("#nowpagecopy").text($("#nowPage").text());
	$("#totalpagescopy").text($("#totalPages").text());
	var nowPage = parseInt($("#nowPage").text());
	var totalPages = parseInt($("#totalPages").text());
	
	$("#lastPage").removeAttr("disabled");
	$("#nextPage").removeAttr("disabled");
	$("#firstPage").removeAttr("disabled");
	$("#previousPage").removeAttr("disabled");
	
	if(nowPage == 1){
		$("#firstPage").attr("disabled",true);
		$("#previousPage").attr("disabled",true);
	}
	
	if(nowPage == totalPages){
		$("#lastPage").attr("disabled",true);
		$("#nextPage").attr("disabled",true);
	}
	
	$("[group='1']").click(function(){
		if($(".group").hasClass("active"))
			$(".group").removeClass("active");
		$(".group").hide();
		$(".group1").show();
		$(".group1[profession='all']").addClass("active");
		$("[group]").removeClass("active");
		$(this).addClass("active");
	});
	$("[group='2']").click(function(){
		if($(".group").hasClass("active"))
			$(".group").removeClass("active");
		$(".group").hide();
		$(".group2").show();
		$(".group2[profession='all']").addClass("active");
		$("[group]").removeClass("active");
		$(this).addClass("active");
	});
	$("[group='3']").click(function(){
		if($(".group").hasClass("active"))
			$(".group").removeClass("active");
		$(".group").hide();
		$(".group3").show();
		$(".group3[profession='all']").addClass("active");
		$("[group]").removeClass("active");
		$(this).addClass("active");
	});
	$("[group='4']").click(function(){
		if($(".group").hasClass("active"))
			$(".group").removeClass("active");
		$(".group").hide();
		$(".group4").show();
		$(".group4[profession='all']").addClass("active");
		$("[group]").removeClass("active");
		$(this).addClass("active");
	});

	$(".group1").click(function(){
		$(".group1").removeClass("active");
		$(this).addClass("active");
	});
	$(".group2").click(function(){
		$(".group2").removeClass("active");
		$(this).addClass("active");
	});
	$(".group3").click(function(){
		$(".group3").removeClass("active");
		$(this).addClass("active");
	});
	$(".group4").click(function(){
		$(".group4").removeClass("active");
		$(this).addClass("active");
	});

	$(".group5").click(function(){
		$(".group5").removeClass("active");
		$(this).addClass("active");
	});
	var grade= "all";
	var profession= "all";
	$('[grade]').click(function(){
		profession= "all";
		var orderby=$('.active[orderby]').attr("orderby");
		grade=$(this).attr("grade");;
		$.ajax({
			url:"/SmartClass/studentAJAX/queryStudent",
			async:false,
			type:"POST",
			data:{
				"grade":grade,
				"orderby":orderby
			},
			success:function(a){
				//alert_text(a);
				$('#ajax_result').html(a);
			}
		});
		$("#nowpagecopy").text($("#nowPage").text());
		$("#totalpagescopy").text($("#totalPages").text());
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		$("#lastPage").removeAttr("disabled");
		$("#nextPage").removeAttr("disabled");
		$("#firstPage").removeAttr("disabled");
		$("#previousPage").removeAttr("disabled");
		
		if(nowPage == 1){
			$("#firstPage").attr("disabled",true);
			$("#previousPage").attr("disabled",true);
		}
		
		if(nowPage == totalPages){
			$("#lastPage").attr("disabled",true);
			$("#nextPage").attr("disabled",true);
		}
	});

	$('[profession]').click(function(){
		profession=$(this).attr("profession");
		grade=$('.active[grade]').attr("grade");
		var orderby=$('.active[orderby]').attr("orderby");
		$.ajax({
			url:"/SmartClass/studentAJAX/queryStudent",
			async:false,
			type:"POST",
			data:{
				"grade":grade,
				"profession":profession,
				"orderby":orderby
			},
			success:function(a){
				//alert_text(a);
				$('#ajax_result').html(a);
			}
		});
		$("#nowpagecopy").text($("#nowPage").text());
		$("#totalpagescopy").text($("#totalPages").text());
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		$("#lastPage").removeAttr("disabled");
		$("#nextPage").removeAttr("disabled");
		$("#firstPage").removeAttr("disabled");
		$("#previousPage").removeAttr("disabled");
		
		if(nowPage == 1){
			$("#firstPage").attr("disabled",true);
			$("#previousPage").attr("disabled",true);
		}
		
		if(nowPage == totalPages){
			$("#lastPage").attr("disabled",true);
			$("#nextPage").attr("disabled",true);
		}
	});
	$('[orderby]').click(function(){
		var orderby=$(this).attr("orderby");
		$.ajax({
			url:"/SmartClass/studentAJAX/queryStudent",
			async:false,
			type:"POST",
			data:{
				"grade":grade,
				"profession":profession,
				"orderby":orderby
				
			},
			success:function(a){
				//alert_text(a);
				$('#ajax_result').html(a);
			}
		});
		$("#nowpagecopy").text($("#nowPage").text());
		$("#totalpagescopy").text($("#totalPages").text());
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		$("#lastPage").removeAttr("disabled");
		$("#nextPage").removeAttr("disabled");
		$("#firstPage").removeAttr("disabled");
		$("#previousPage").removeAttr("disabled");
		
		if(nowPage == 1){
			$("#firstPage").attr("disabled",true);
			$("#previousPage").attr("disabled",true);
		}
		
		if(nowPage == totalPages){
			$("#lastPage").attr("disabled",true);
			$("#nextPage").attr("disabled",true);
		}
	});	
	
	$(".kkkkkk").click(function(){
		$("[name='toPgae']").focus();
	});
	
	//以下为翻页设计
	
	function page_ajax(nowPage,totalPages,orderby){
		$.ajax({
			url:"/SmartClass/studentAJAX/queryStudent",
			async:false,
			type:"POST",
			data:{
				"grade":grade,
				"profession":profession,
				"orderby":orderby,
				"nowPage":nowPage
			},
			success:function(a){
				//alert_text(a);
				$('#ajax_result').html(a);
			}
		});
		$("#nowpagecopy").text(nowPage);
		$("#totalpagescopy").text(totalPages);
		
		$("#lastPage").removeAttr("disabled");
		$("#nextPage").removeAttr("disabled");
		$("#firstPage").removeAttr("disabled");
		$("#previousPage").removeAttr("disabled");
		
		if(nowPage == 1){
			$("#firstPage").attr("disabled",true);
			$("#previousPage").attr("disabled",true);
		}
		
		if(nowPage == totalPages){
			$("#lastPage").attr("disabled",true);
			$("#nextPage").attr("disabled",true);
		}
	}
	
	//下一页
	$('#nextPage').click(function(){
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		if(nowPage < totalPages) {
			nowPage = nowPage + 1;
		}
		
		var orderby=$('.active[orderby]').attr("orderby");
		
		page_ajax(nowPage,totalPages,orderby);
		
	});
	
	//上一页
	$('#previousPage').click(function(){
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		if(nowPage > 1) {
			nowPage--;
		}
		
		var orderby=$('.active[orderby]').attr("orderby");
		page_ajax(nowPage,totalPages,orderby);
	});
	
	//首页
	$('#firstPage').click(function(){
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		nowPage = 1;
		
		var orderby=$('.active[orderby]').attr("orderby");
		page_ajax(nowPage,totalPages,orderby);
	});
	
	//尾页
	$('#lastPage').click(function(){
		
		var totalPages = parseInt($("#totalPages").text());
		
		var nowPage = totalPages;
		
		var orderby=$('.active[orderby]').attr("orderby");
		page_ajax(nowPage,totalPages,orderby);
	});
	
	//指定页
	$('#toPage-button').click(function(){
	
		var toPage = parseInt($("[name='toPgae']").val());
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
//		alert_text(toPage+" "+nowPage+" "+totalPages);
		
		if(toPage == nowPage) {
			return;
		} else if(toPage < 1 || toPage > totalPages) {
			alert_text("warning","输入页码范围要在 1-"+totalPages+" 之间");
		} else if(isNaN(toPage)) {
			alert_text("warning","输入页码应为数字");
		} else {
			nowPage = toPage;
		}
		
		var orderby=$('.active[orderby]').attr("orderby");
		page_ajax(nowPage,totalPages,orderby);
	});
	
});

//编辑学生信息
function edit(which){
	var sid=$(which).attr('sid');
	$.ajax({
		url:"/SmartClass/studentAJAX/editStudent",
		async:false,
		type:"POST",
		data:{
			"id":sid,
		},
		success:function(a){
			$('#util-modal').css("margin-top","70px");
			$('#util-modal>.modal-dialog').css("width","800px");
			$('#util-modal').find('[modal-title]').html("修改学生信息");
			$('#util-modal').find('#util-modal-ajax').html(a);
		}
	});
	$('#util-modal').modal();
	
	$("[disabled]").children("input").attr("disabled", true);
	$("[disabled]").addClass("disabled");
}

//查看学生活动记录
//function view(which){
//	var sid=$(which).attr('sid');
//	//alert("查看信息" + sid);
//	$.ajax({
//		url:"/Activisys/student/queryStudentAttended",
//		async:false,
//		type:"POST",
//		data:{
//			"studentPO.sid":sid,
//		},
//		success:function(a){
//			$('#util-modal').css("margin-top","70px");
//			$('#util-modal>.modal-dialog').css("width","800px");
//			$('#util-modal').find('[modal-title]').html("学生出席活动记录<span style='color:red;font-size:10px;'></span>");
//			$('#util-modal').find('#util-modal-ajax').html(a);
//		}
//	});
//	$('#util-modal').modal();
//
//}
