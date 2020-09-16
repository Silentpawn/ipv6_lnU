<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<s:if test="activityStudentVOs.size() > 0">
	<table class="table" style="width: 95%;margin: 17px auto;">
		<tr class="show-tr">
			<th>序号</th>
			<th>活动名</th>
			<th>活动开始时间</th>
			<th>获得量化</th>
		</tr>
		<s:iterator value="activityStudentVOs" status="st">
			<tr class="">
				<td sid="<s:property value="sid"/>"><s:property value="#st.count" /></td>
				<td><s:property value="aname" /></td>
				<td><s:date name="activityStartTime" format="yyyy-MM-dd HH:mm" /></td>
				<td class="change_quantization" style="text-align: center;"><s:property value="addQuantization" /></td>
			</tr>
		</s:iterator>
	</table>
</s:if>
<s:else>
	<table class="">
		<tr class="">
			<th>没有该学生报名的活动记录</th>
		</tr>
	</table>
</s:else>

<div class="text-center" style="margin-bottom :20px"><a style="color: -webkit-link;text-decoration: underline;" target="_blank" href="/Activisys/student/getStudentTranscript?studentPO.sid=<s:property value="studentPO.sid" />">点击生成该学生的第二课堂成绩单<a></div>