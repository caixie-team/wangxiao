<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
  <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>

<script type="text/javascript">

</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>我的部门</span> </h4>
	</div>
	<div class="mt20">
		<div class="commonWrap">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >				
					<thead>
						<tr>
							<th><span>部门名称</span></th>
							<th><span>任务名称</span></th>
							<th><span>发布时间</span></th>
							<th><span>状态</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${grouplist.size()>0}">
							<c:forEach  items="${grouplist}" var="group" >
						  <tr>
							 <td><span>${group.name}</span></td>
							 <td><span>${group.taskname}</span></td>
							 <td>
							 <fmt:formatDate type="both" value="${group.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							 </td>
							 <td>
							 <c:if test="${group.taskstatus==0}">
							          <span>未启动</span>
							 </c:if>
							  <c:if test="${group.taskstatus==1}">
							          <span>启动</span>
							 </c:if>
							  <c:if test="${group.taskstatus==2}">
							          <span>作废</span>
							 </c:if>							 
							 </td>
							 <td><a class="ml10 btn smallbtn btn-y" title="详情" href="${ctximg}/admin/task/web/groudtask/${group.id}/${group.taskid}">详情</a></td> 
						  </tr>
							</c:forEach>
						</c:if>
						<c:if test="${grouplist.size()==0||grouplist==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有部门信息！</span>
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
	</div>
</body>
</html>
