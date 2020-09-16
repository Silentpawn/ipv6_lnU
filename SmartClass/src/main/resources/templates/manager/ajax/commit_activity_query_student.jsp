<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<script type="text/javascript" src="<%=basePath%>js/ajax_commit_activity_query_student.js"></script>
<s:if test="studentPOList.size() > 0">
	<table class="show-table-commit control-name-width">
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
			<tr class="show-tr-min student-tr-js">
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
					<span class="glyphicon glyphicon-plus left-margin operate operate-min" onclick="plus(this)"></span>
				</td>
			</tr>
		</s:iterator>
	</table>
	<ul class="input-ul">
		<li class="input-li">
			<button type="button" class="input-border-no-radius page-margin" id="firstPage">首页</button>
			<button type="button" class="input-border-no-radius" id="previousPage">上一页</button>
			<button type="button" class="input-border-no-radius" id="nextPage">下一页</button>
			<button type="button" class="input-border-no-radius" id="lastPage">尾页</button> <span class="input-title middle-input-title"
			style="letter-spacing: 2px"
		>当前第 <span id="nowpagecopy">1</span> 页，共 <span id="totalpagescopy">1</span> 页，到第
		</span> <span class="input-border-no-radius kkkkkk"> <input type="text" name="toPgae" class="input-text input-number" value="1"
					maxlength="3"
				/>
		</span> <span class="input-title middle-input-title"> 页</span>
			<button type="button" class="input-border-no-radius middle-input-title" id="toPage-button">确定</button>
		</li>
	</ul>
</s:if>
<s:else>
	<table class="show-table-commit control-name-width">
		<tr class="show-tr">
			<th>数据库中没有符合条件的学生。</th>
		</tr>
	</table>
</s:else>
<div class="info">
	<span id="nowPage"> <s:property value="pageVO.nowPage" />
	</span> <span id="totalPages"> <s:property value="pageVO.totalPages" />
	</span>
</div>