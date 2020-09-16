$(function(){

	//删除活动deleteActivity
	$(".glyphicon.glyphicon-trash ").click(function(){
		confirm_text("确认删除 ["+ $(this).parents(".show-tr").children("[aname]").attr("aname") +"] 课程？",$(this),function(which){
			$.ajax({
				type:'post',
				url:"/SmartClass/courseAPI/deleteCourse",
				data: {
					'id': $(which).parents(".show-tr").attr("cid"),
				},
				async: false,
				success: function(data) {
					//alert_text(data);
					if(data.status=="ok")
					{
						alert_text("ok","删除成功",function(){history.go(0);});
					}else{
						alert_text("warning",data.msg,function(){history.go(0);});
					}


				}
			});
		});


	});

	//修改活动
	$(".glyphicon-pencil").click(function(){
		$.ajax({
			type:'post',
			url:"/SmartClass/courseAJAX/editCourse",
			dataType: 'text', 
			data: {
				'id': $(this).parents(".show-tr").attr("cid"),
			},
			async: false,
			success: function(data) {

				$("#big-util-modal-ajax").html(data);

			}
		});

		$("[modal-title]").text("修改活动信息");
		$("#big-util-modal").modal();

	});

	//查看课程
	$(".show-hover").children("td").not(".op").click(function(){

		$.ajax({
			type:'post',
			url:"/SmartClass/courseAJAX/viewCourse",
			dataType: 'text', 
			data: {
				'id': $(this).parents(".show-hover").attr("cid"),
			},
			async: false,
			success: function(data) {


				$("#big-util-modal-ajax").html(data);

			}
		});

		$("[modal-title]").text("活动详细信息");
		$("#big-util-modal").modal();

	});

	$("[disable]").off("click");
	$("[disable]").attr("style","color:#ddd");

});

