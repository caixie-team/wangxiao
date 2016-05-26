<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>员工列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
 	$(function(){
	   $("#beginTime,#endTime").datetimepicker(
	         {regional:"zh-CN",
	        	 changeYear: true,
	             changeMonth: true,
	             dateFormat:"yy-mm-dd ",
	             timeFormat: "HH:mm:ss"
	         });
	   $("#status").val("${task.status}");
	});
	//查询任务
	function submitSearch(){
		$("#searchForm").submit();
	}
	//清空查询条件
	function clearSearch(){
		$("#name").val("");
		$("#status").val("");
		$("#beginTime").val("");
		$("#endTime").val("");
	}
	//任务详情跳转链接
	function taskDetails(id){
		window.location.href="${ctx}/admin/task/taskDetailsById/"+id;
	}
	//任务修改跳转链接
	function updateTask(id){
		window.location.href="${ctx}/admin/task/toUpdateTask/"+id;
	}
	//更改任务状态
	function updateTaskStatus(id){
		window.location.href="${ctx}/admin/task/updateTaskStatus/"+id;
	}
	//作废任务
	function invalidTask(id){
		window.location.href="${ctx}/admin/task/invalidTask/"+id;
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/task/taskList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li>
												<span class="ddTitle"> 
												<font>任务名称：</font>
												</span> 
												<input type="text" name="task.name" value="${task.name }" id="name" />
											</li> 
											<li>
												<span class="ddTitle"> 
												<font>状态：</font>
												</span> 
												<select name="task.status" id="status">
													<option value="">请选择</option>
													<option value="0">未开启</option>
													<option value="1">开启</option>
												</select>
											</li>
											<li>
												<span class="ddTitle"> 
												<font>开始时间：</font>
												</span> <input type="text" name="task.beginTime" value="<fmt:formatDate value="${task.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />"  id="beginTime" />
											</li>
											<li>
												<span class="ddTitle"> 
												<font>结束时间：</font>
												</span> <input type="text" name="task.endTime"  value="<fmt:formatDate value="${task.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />" id="endTime" />
											</li>
											<li>
												<span class="ddTitle"></span>
												<input class="am-btn am-btn-danger" type="button" onclick="submitSearch()" name="" value="查询"/>
												<span class="ddTitle"></span>
												<input class="am-btn am-btn-danger" type="button" onclick="clearSearch()" name="" value="清空"/>
											</li>
										</ul>
									</div>
									<div class="clearfix"></div>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="5%"><span>ID</span></th>
									<th width="10%"><span>任务名称</span></th>
									<th width="10%"><span>开始时间</span></th>
									<th width="10%"><span>结束时间</span></th>
									<th width="10%"><span>发布人</span></th>
									<th width="10%"><span>发布时间</span></th>
									<th width="10%"><span>状态</span></th>
									<th width="10%"><span>是否重复</span></th>
									<th width="10%"><span>类型</span></th>
									<th width="13%"><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${taskList.size()>0}">
									<c:forEach items="${taskList}" var="taskList">
									<tr>
										<td>
											${taskList.id }
										</td>
										<td>
											${taskList.name }
										</td>
										<td>
											<fmt:formatDate value="${taskList.beginTime }" pattern="yyy-MM-dd" />
										</td>
										<td>
											<fmt:formatDate value="${taskList.endTime }" pattern="yyy-MM-dd" />
										</td>
										<td>
											${taskList.releasePeople }
										</td>
										<td>
											<fmt:formatDate value="${taskList.releaseTime }" pattern="yyy-MM-dd" />
										</td>
										<td>
											<c:if test="${taskList.status==0}">
												未开启
											</c:if>
											<c:if test="${taskList.status==1}">
												开启
											</c:if>
										</td>
										<td>
											<c:if test="${taskList.isRepeat==0}">
												不可重复
											</c:if>
											<c:if test="${taskList.isRepeat==1}">
												可重复
											</c:if>
										</td>
										<td>
											<c:if test="${taskList.type==0}">
												个人
											</c:if>
											<c:if test="${taskList.type==1}">
												部门
											</c:if>
										</td>
										<td colspan="9">
											<a href="javascript:void(0);" onclick="taskDetails(${taskList.id})" title="详情" class="btn smallbtn btn-y">详情</a>
											<c:if test="${taskList.status==0}">
											<a href="javascript:void(0);" title="作废" onclick="updateTaskStatus(${taskList.id})" class="btn smallbtn btn-y">启动</a>
											<a href="javascript:void(0);" onclick="updateTask(${taskList.id})" title="修改" class="btn smallbtn btn-y">修改</a>
											</c:if>
											<c:if test="${taskList.status==1}">
											<a href="javascript:void(0);" onclick="invalidTask(${taskList.id})" title="作废" class="btn smallbtn btn-y">作废</a>
											</c:if>
										</td>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${taskList.size()==0||taskList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有任务信息！</span>
											</div>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
						<!-- /pageBar end -->
					</div>
					<!-- /commonWrap -->
				</div>
			<!-- 内容 结束 -->
		</form>
</body>
</html>
