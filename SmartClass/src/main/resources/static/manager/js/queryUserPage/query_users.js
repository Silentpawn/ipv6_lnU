$(function(){
	$(".group6").click(function(){
		$(".group6").removeClass("active");
		$(this).addClass("active");
	});
	$.ajax({
		url:"/SmartClass/userAJAX/queryUser",
		async:false,
		data:{
			"type":'admin',
		},
		success:function(a){
			$('table.show-table-student').html(a);
		}
	});
	
	$(".kkkkkk").click(function(){
		$("[name='toPgae']").focus();
	});
	
	$("#nowpagecopy").text($("#nowPage").text());
	$("#totalpagescopy").text($("#totalPages").text());
	
	var role = "all";
	$('[role]').click(function(){
		role = $('.active[role]').attr("role");
		$.ajax({
			url:"/SmartClass/userAJAX/queryUser",
			async:false,
			type:"POST",
			data:{
				"type":role,
			},
			success:function(a){
				$('table.show-table-student').html(a);
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
	
//以下为翻页设计
	
	function page_ajax(nowPage,totalPages){
		$.ajax({
			url:"/SmartClass/userAJAX/queryUser",
			async:false,
			type:"POST",
			data:{
				"type":role,
				"nowPage":nowPage
			},
			success:function(a){
				//alert_text(a);
				$('table.show-table-student').html(a);
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
		
		page_ajax(nowPage,totalPages);
	});
	
	//上一页
	$('#previousPage').click(function(){
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		if(nowPage > 1) {
			nowPage--;
		}
		
		page_ajax(nowPage,totalPages);
	});
	
	//首页
	$('#firstPage').click(function(){
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());
		
		nowPage = 1;
		
		page_ajax(nowPage,totalPages);
	});
	
	//尾页
	$('#lastPage').click(function(){
		
		var totalPages = parseInt($("#totalPages").text());
		
		var nowPage = totalPages;
		
		page_ajax(nowPage,totalPages);
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
		
		page_ajax(nowPage,totalPages);
	});
	
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

function edit_user(which){
	role = $('.active[role]').attr("role");
	$.ajax({
		type:"post",
		url:"/SmartClass/userAJAX/edit",
		async:false,
		data:{
			"id":$(which).parents(".show-tr-min").children("[id]").attr("id"),
			"type":role
		},
		success:function(a){
			$('#util-modal').css("margin-top","70px");
			$('#util-modal>.modal-dialog').css("width","800px");
			$('#util-modal').find('[modal-title]').html("修改用户信息");
			$('#util-modal').find('#util-modal-ajax').html(a);
		}
		
	});
	
	$('#util-modal').modal();
	$("[disabled]").children("input").attr("disabled", true);
	$("[disabled]").addClass("disabled");
}

function delete_user(which){
	role = $('.active[role]').attr("role");
	$.ajax({
		type:"post",
		url:"/SmartClass/userAJAX/delete",
		async:false,
		data:{
			"id":$(which).parents(".show-tr-min").children("[id]").attr("id"),
			"type":role
		},
		success:function(a){
			
			$('#util-modal').css("margin-top","70px");
			$('#util-modal>.modal-dialog').css("width","600px");
			$('#util-modal').find('[modal-title]').html("删除用户");
			$('#util-modal').find('#util-modal-ajax').html(a);
		}
		
	});
	
	$('#util-modal').modal();
}


function accept_application(which){
	role = $('.active[role]').attr("role");
	$.ajax({
		type:"post",
		url:"/SmartClass/userAJAX/acceptApplication",
		async:false,
		data:{
			"id":$(which).parents(".show-tr-min").children("[id]").attr("id"),
			"type":role
		},
		success:function(a){
			
			$('#util-modal').css("margin-top","70px");
			$('#util-modal>.modal-dialog').css("width","600px");
			$('#util-modal').find('[modal-title]').html("接受申请");
			$('#util-modal').find('#util-modal-ajax').html(a);
		}
		
	});
	
	$('#util-modal').modal();
}