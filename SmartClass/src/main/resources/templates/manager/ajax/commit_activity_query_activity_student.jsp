<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<s:if test="studentPOList.size() > 0">
		<tr class="show-tr">
			<th>序号</th>
			<th>学院</th>
			<th>年级</th>
			<th>专业</th>
			<th>学号</th>
			<th>姓名</th>
			<th>
				<span class="left-margin">操作</span>
			</th>
		</tr>
		<s:iterator value="studentPOList" status="st">
			<tr class="show-tr-min student-tr-js" " areadytr>
				<td sid="<s:property value="sid"/>">
					<s:property value="#st.count" />
				</td>
				<td>
					<s:property value="college" />
				</td>
				<td>
					<s:property value="grade" />
				</td>
				<td>
					<s:property value="profession" />
				</td>
				<td>
					<s:property value="sno" />
				</td>
				<td>
					<s:property value="sname" />
				</td>
				<td>
					<span class='glyphicon glyphicon-minus left-margin operate operate-min' minus aready></span>
				</td>
			</tr>
		</s:iterator>
	<script type="text/javascript">
		$(function() {
			$("[aready]").each(function(i,e){
				plus(e);
				$(e).parents("tr").remove();
			});
		});
	</script>
</s:if>
<s:else>
	<table class="show-table-commit control-name-width">
		<tr class="show-tr" tips>
			<th>您班级还没有报名的学生</th>
		</tr>
	</table>
</s:else>
