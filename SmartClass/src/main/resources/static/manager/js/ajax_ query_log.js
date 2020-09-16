$(function(){
	/*---------init---------*/
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var url = $("#url").text();
	
	/*-------------------------翻页组件-----------------------------------------------*/
	//初始化翻页组件
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
	
	
	//以下为翻页设计
	function page_ajax(nowPage,totalPages,orderby){
		console.log(starttime);
		console.log(endtime);
		console.log(url);
		//在这里修改ajax目标地址和目标元素
		$.ajax({
			url:url,
			async:false,
			type:"POST",
			data:{
				"starttime":starttime,
				"endtime":endtime,
				"pageVO.nowPage":nowPage
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

