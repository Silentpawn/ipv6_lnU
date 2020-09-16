<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<script type="text/javascript" src="<%=basePath%>js/ajax_ query_log.js"></script>
<s:if test="logs.size() > 0">
	<table class="show-table-appointW table table-condensed ">
		<tr class="show-tr">
			<th style="width:40px;">序号</th>
			<th style="width:100px;">操作人</th>
			<th style="width:650px;">操作内容</th>
			<th style="width:150px;">操作时间</th>
		</tr>
		<s:iterator value="logs" status="st">
			<tr class="show-tr-min student-tr-js padding-tr">
				<td><s:property value="#st.count" /></td>
				<td><s:property value="username" /></td>
				<td><s:property value="content" /></td>
				<td><s:date name="time" format="yyyy-MM-dd HH:mm" /></td>
			</tr>
		</s:iterator>
	</table>
	<ul class="input-ul" changePage>
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
	<table class="show-table-appoint control-name-width">
		<tr class="show-tr">
			<th>数据库中尚无日志信息</th>
		</tr>
	</table>
</s:else>
<div class="info">
	<span id="nowPage"> <s:property value="pageVO.nowPage" />
	</span> <span id="totalPages"> <s:property value="pageVO.totalPages" />
	</span>
	<span id="url"><s:property value="logTargetUrl" /></span>
</div>