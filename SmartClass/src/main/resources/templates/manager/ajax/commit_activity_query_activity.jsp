<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="activityVO_list.size()>0">
	<table class="show-table show-table-activities">
		<tr class="show-tr">
			<th>序号</th>
			<th>活动名称</th>
			<th>开始报名时间</th>
			<th>截止报名时间</th>
			<th>面向年级专业</th>
			<th>参与人数</th>
			<th>活动负责人</th>
			<th>量化统计负责人</th>
			<th>活动状态</th>
		</tr>
		<s:iterator value="activityVO_list" status="st">
			<tr class="show-tr show-hover activity-js">
				<td><s:property value="#st.count" /></td>
				<td aid="aid" style="display: none"><s:property value="aid" /></td>
				<td><s:property value="briefAname" /></td>
				<td><s:date name="applyStartTime" format="yyyy-MM-dd HH:mm" /></td>
				<td><s:date name="applyEndTime" format="yyyy-MM-dd HH:mm" /></td>
				<td><s:property value="briefTarget" /></td>
				<td><s:property value="countStudents" /></td>
				<td><s:property value="managerName" /></td>
				<td><s:property value="statisticsManagerName" /></td>
				<td><s:property value="status" /></td>
			</tr>
		</s:iterator>
	</table>
</s:if>
<s:else>
	<table class="show-table show-table-activities">
		<tr class="show-tr">
			<th>当前没有正在报名的面向<s:property value="#session.student.college" /> <s:property value="#session.student.grade" /> <s:property
					value="#session.student.profession"
				/>的活动。
			</th>
		</tr>
	</table>
</s:else>