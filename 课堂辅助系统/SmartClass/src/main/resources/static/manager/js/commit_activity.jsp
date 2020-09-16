<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/commit_activity.js"></script>
<div class="real-contents all-contents">
	<div class="clearfix">
		<span class="rectangle left-min-margin"></span> <span class="title before-choose-text">选择一个正在报名中的活动</span> <span
			class="title before-choose-text glyphicon glyphicon-question-sign btn-warning"
		></span> <span class="title after-choose-text">确认选择的活动无误并开始填写报名信息</span> <span
			class="title after-choose-text glyphicon glyphicon-ok-sign btn-success"
		></span> <span class="title after-choose-text" id="reselect-js">点我重新选择活动</span>
	</div>
	<div class="commit-activity-ajax">
		<table class="show-table show-table-activities">
			<tr class="show-tr">
				<th>正在加载活动信息...</th>
			</tr>
		</table>
	</div>
	<div class="clearfix lllll">
		<span class="rectangle left-min-margin"></span> <span class="title">这个人想参加！此处搜索并添加报名学生</span>
	</div>
	<ul class="input-ul lllll">
		<li class="input-li"><span class="input-title">输入信息：</span> <span class="input-border"> <input type="text"
					class="input-text input-sno" name="queryStudentValue" placeholder="141403201 / 刘之帅"
				/>
		</span> <a class="input-border search-button left-margin" id="bysno">按学号查询</a> <a class="input-border search-button" id="byname">按名字查询</a>
		<a class="input-border search-button" id="packup">收起</a></li>
	</ul>
	<span class="info" lastclick></span>
	<span class="info" lastinput></span>
	<div class="ajax lllll"></div>
	<div class="clearfix" id="party-title-js">
		<span class="rectangle left-min-margin"></span> <span class="title">本次活动报名名单</span>
	</div>
	<table class="show-table-commit control-name-width" id="party-table-js">
		<tr class="show-tr">
			<th>序号</th>
			<th>学院</th>
			<th>年级</th>
			<th>专业</th>
			<th>学号</th>
			<th>姓名</th>
			<th><span class="left-margin">操作</span></th>
		</tr>
	</table>
	<button class="submit" type="button" id="commit-activity-button">提交报名表</button>
	<form class="info" action="/Activisys/activity/commit_activity" id="commit-activity-form" method="post">
		<input type="text" id="aid" name="aid" />
	</form>
</div>
