﻿<!DOCTYPE HTML>
<!--<jsp:useBean id="student" scope="page" class="com.sean.model.Student"/>  
<jsp:setProperty property="sex" name="student" value="${sessionScope.student.sex}"/>
<jsp:setProperty property="name" name="student" value="${sessionScope.student.name}"/>
<jsp:setProperty property="department" name="student" value="${sessionScope.student.department}"/>
<jsp:setProperty property="s_class" name="student" value="${sessionScope.student.s_class}"/>
<jsp:setProperty property="telnum" name="student" value="${sessionScope.student.telnum}"/>
-->
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="../lib/html5shiv.js"></script>
<script type="text/javascript" src="../lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<!--[if IE 6]>
<script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<style type="text/css">
.layer_info{
margin:0 auto;margin-top:20px;border:1px solid #000;width:210px;height:110px
}
</style>
<title>辽宁大学创新实验室管理员端</title>
</head>
<body>


<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="">辽宁大学创新实验室管理员端</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml"></a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>
				<jsp:getProperty property="department" name="student"/>
				</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">
	<jsp:getProperty property="name" name="student"/>
<i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li> 
						<li><a href="rectify_pwd.jsp">修改密码</a></li>
						<li><a href="../AdministratorServlet?action=exit">切换账户</a></li>
						<li><a href="../AdministratorServlet?action=exit">注销</a></li>
						
				</ul>
			</li>
				
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 个人信息<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="rectify_pwd.jsp" data-title="修改密码" href="javascript:;">修改密码</a></li>
					<!-- <li><a data-href="my_course.jsp" data-title="已预约课程" href="javascript:;">已预约课程</a></li>
					<li><a data-href="Ability.jsp" data-title="个人能力" href="javascript:;">个人能力</a></li> -->
			</ul>
		</dd>
	</dl>


		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 人员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${pageContext.request.contextPath}/tea/select" data-title="教师管理" href="javascript:void(0)">教师管理</a></li>
					
					<li><a data-href="${pageContext.request.contextPath}/student/select" data-title="学生管理" href="javascript:void(0)">学生管理</a></li>
					
					<!-- <li><a data-href="match.jsp" data-title="匹配实验队友" href="javascript:void(0)">匹配实验队友</a></li>
					<li><a data-href="check_studentInfo" data-title="查找学生信息" href="javascript:void(0)">查找学生信息</a></li> -->
			</ul>
		</dd>
	</dl>
		<dl id="menu-studentInfo">
			<dt><i class="Hui-iconfont">&#xe616;</i> 查找信息<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="check_studentInfo.jsp" data-title="查找学生信息" href="javascript:void(0)">查找学生信息</a></li>
					<li><a data-href="check_teacherInfo.jsp" data-title="查找教师信息" href="javascript:void(0)">查找教师信息</a></li>
					<li><a data-href="check_expRoomInfo.jsp" data-title="查找实验室信息" href="javascript:void(0)">查找实验室信息</a></li>
			</ul>
		</dd>
	</dl>
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 实验管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="${pageContext.request.contextPath}/notification/select" data-title="实验室公告管理" href="javascript:void(0)">实验室公告管理</a></li>
					<li><a data-href="${pageContext.request.contextPath}/expRoom/select" data-title="实验室管理" href="javascript:void(0)">实验室管理</a></li>
					<li><a data-href="check_exp_admin.jsp" data-title="审核实验课程申请" href="javascript:void(0)">审核实验课程申请</a></li>
					
			</ul>
		</dd>
	</dl>
		
	
		
	
</div>
</aside>


<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="欢迎使用" data-href="welcome.jsp">欢迎使用</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.jsp"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
	/*$("#min_title_list li").contextMenu('Huiadminmenu', {
		bindings: {
			'closethis': function(t) {
				console.log(t);
				if(t.find("i")){
					t.find("i").trigger("click");
				}		
			},
			'closeall': function(t) {
				alert('Trigger was '+t.id+'\nAction was Email');
			},
		}
	});*/
});
//全局的ajax访问，处理ajax清求时sesion超时 
$.ajaxSetup({
  contentType : "application/x-www-form-urlencoded;charset=utf-8",
  complete : function(XMLHttpRequest, textStatus) {
    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
    if (sessionstatus == "timeout") {
      // 如果超时就处理 ，指定要跳转的页面
      window.location.replace("/login.jsp");
    }
  }
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		
	    content: '<div class="layer_info"><ul><li>姓名：${administrator.name}</li><li>性别：${administrator.sex}</li><li>电话：${administrator.phoneNumber}</li><li>邮箱：${administrator.email}</li></ul></div>'
	});
}

</script> 
</body>
</html>