$(function(){


	//以下是翻页相关逻辑

	$(".kkkkkk").click(function() {
		$("[name='toPgae']").focus();
	});

	$("#nowpagecopy").text($("#nowPage").text());
	$("#totalpagescopy").text($("#totalPages").text());

	function page_ajax(nowPage, totalPages) {

		var lastclick = 'studentPO.' + $("[lastclick]").text();
		var lastinput = $("[lastinput]").text();

		$.ajax({
			url : "/Activisys/student/queryStudentOfCommitActivity",
			async: false,
			type: "POST",
			data: {
				"pageVO.nowPage": nowPage,
				lastclick : lastinput
			},
			success: function(data) {
				$(".ajax").html(data);
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
	$('#nextPage').click(function() {
		
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());

		if (nowPage < totalPages) {
			nowPage = nowPage + 1;
		}

		page_ajax(nowPage, totalPages);
	});

	//上一页
	$('#previousPage').click(function() {
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());

		if (nowPage > 1) {
			nowPage--;
		}

		page_ajax(nowPage, totalPages);
	});

	//首页
	$('#firstPage').click(function() {
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());

		nowPage = 1;

		page_ajax(nowPage, totalPages);
	});

	//尾页
	$('#lastPage').click(function() {

		var totalPages = parseInt($("#totalPages").text());

		var nowPage = totalPages;

		page_ajax(nowPage, totalPages);
	});

	//指定页
	$('#toPage-button').click(function() {

		var toPage = parseInt($("[name='toPgae']").val());
		var nowPage = parseInt($("#nowPage").text());
		var totalPages = parseInt($("#totalPages").text());

		//		alert_text(toPage+" "+nowPage+" "+totalPages);

		if (toPage == nowPage) {
			return;
		} else if (toPage < 1 || toPage > totalPages) {
			alert_text("warning","输入页码范围要在 1-" + totalPages + " 之间");
		} else if (isNaN(toPage)) {
			alert_text("warning","输入页码应为数字");
		} else {
			nowPage = toPage;
		}

		page_ajax(nowPage, totalPages);
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