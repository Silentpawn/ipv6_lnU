$(function() {
	$(".big-modal-content").css("height",$(window).height()-60 +"px");
	$(".big-modal-body").css("height",$(window).height()-128 +"px");
	$('.list-group-item.first').each(function(i,e){
		//alert_text($(e).text());
		if($($(e).attr('data-target')).find(".list-group-item.second").length<=0)
			$(e).hide();
	});

	$(".first").on("click",function() {
		if ($(this).children("span").attr("class") == "glyphicon glyphicon-triangle-bottom arrow") {

			$(this).children("span").attr("class","glyphicon glyphicon-triangle-right arrow");

		} else {
			$(this).children("span").attr("class","glyphicon glyphicon-triangle-bottom arrow");
		}
	});

	// 点击侧边栏页面
	$(".second").on("click", function() {
		$(".click").removeClass("click");
		$(this).addClass("click");

		// 得到字符串 例:create_activity 设置新的url
		var newurl = "welcome#" + $(this).attr("idstring");

		window.history.pushState({}, 0, newurl);

		var idstring = window.location.hash.substr(1);

		var loadurl = idstringToloadurl(idstring);

		$(".function-contents").empty();
		$(".function-contents").load(loadurl);

	});

	// 监听地址栏hash值的变化//兼容ie8+和手机端
	$(window).on("hashchange", function() {

		var idstring = window.location.hash.substr(1);

		var loadurl = idstringToloadurl(idstring);

		$(".function-contents").empty();
		$(".function-contents").load(loadurl);

		$(".click").removeClass("click");
		$("[idstring='" + idstring + "']").addClass("click");

	});

	//	//屏蔽F5刷新
	//	$(document).bind("keydown",function(e){ 
	//	e=window.event||e; 
	//	if(e.keyCode==116){ 
	//	e.keyCode = 0; 
	//	return false; 
	//	} 
	//	}); 

	//以下是刷新逻辑
	//idstring和url#后的部门是相同的
	var idstring = window.location.hash.substr(1);
	var loadurl = idstringToloadurl(idstring);
	//alert_text(loadurl);
	$(".function-contents").empty();

	if(loadurl != null){
		$(".function-contents").load(loadurl);
		$(".click").removeClass("click");
		$("[idstring='" + idstring + "']").addClass("click");

		//为空说明没有对应关系，要么没开发，要么打错了，所以打回首页去，首页被点击，ajax放东西进去，url修改
	} else {
		$(".function-contents").load("/SmartClass/welcomeHTML/welcome_content");
		$("[idstring='welcome_content']").addClass("click");
		window.history.pushState({}, 0, "welcome");
	}

});

function idstringToloadurl(idstring){
	console.log(idstring);
	var urls=['/SmartClass/taskHTML/assign_task_page','/SmartClass/taskHTML/query_task_page','/SmartClass/taskHTML/create_task_page','/SmartClass/userHTML/editPassword','/SmartClass/studentHTML/query_students_page','/SmartClass/userHTML/create_user_page','/SmartClass/userHTML/query_users_page','/SmartClass/courseHTML/create_course_page','/SmartClass/courseHTML/query_course_page','/SmartClass/courseHTML/student_course_page','welcome'];
	for(var i=0;i<urls.length;i++){
		if(idstring==urls[i])
			return idstring;
	}
	return null;
}


function alert_text(type,content,func){
	//alert(1);
	if(type == "warning"){
		$(".sign").removeClass("glyphicon-ok-sign sign-ok");
		$(".sign").addClass("glyphicon-exclamation-sign sign-warning");
	} else if(type == "ok") {
		$(".sign").removeClass("glyphicon-exclamation-sign");
		$(".sign").removeClass("sign-warning");
		$(".sign").addClass("glyphicon glyphicon-ok-sign");
		$(".sign").addClass("sign-ok");
	}else if(type == "waiting"){
		$("#alert-modal").find(".modal-submit").hide();
		$("#alert-modal-closer").hide();
		$("#loading").show();
		$(".modal").each(function(i,e){
			$(e).modal('hide');
		});
		$(".alert-modal-text").text(content);
		$('#alert-modal').modal('show');
		return;
	}
	
	$("#alert-modal").find(".modal-submit").show();
	$("#alert-modal-closer").show();
	$("#loading").hide();
	
	$(".modal").each(function(i,e){
		$(e).modal('hide');
	});
	$(".alert-modal-text").text(content);
	$("#alert-modal").modal('show');
	$("#alert-modal").find(".modal-submit").one('click',function(){
		$("#alert-modal").modal('hide');
		if(func)
			func();
	});
}

function message_show(message_element,message_text){
	if(message_element.parents('.input-li').children(".message").length < 1){
		message_element.parents('.input-li').append("<span class='message'><span class='glyphicon glyphicon-exclamation-sign'>" +
				"</span> <span content>" + message_text + "</span></span>");
	}else{
		message_element.parents('.input-li').find("span[content]").text(message_text);
	}
}

function message_clear(message_element){
	message_element.parents(".input-li").children(".message").remove();
}

function confirm_text(content,which,func){
	$(".confirm-modal-text").text(content);
	$("#confirm-modal").modal();
	$("#confirm-modal").find(".submit").one('click',function(){
		func(which);
	});
}
