<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<%@ page buffer="1000kb"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
<script src="${ctximg}/static/common/admin/js/admin-mine-n.js?v=${v}" type="text/javascript" charset="utf-8"></script>
</head>
<body style="background: #393c40; overflow-x: hidden;">
	<div id="" style="top: 0;">
		<%-- 	<menu type="context toolbar" id="lMenu">
		<dl>
			<c:forEach items="${leftTabList}" varStatus="status"  var="tabList" >
				<c:choose>
					<c:when test="${status.index==0}"><!-- 第一个展开 -->
						<dt class="current"><a href="" title=""><tt class="icon12">&nbsp;</tt><span>${tabList[0].functionName}</span></a><em class="sjWrap">◆</em></dt>
						<dd style="display:block ">
							<ol>
								<c:forEach items="${tabList}" var="tfunction" varStatus="tfstatus" >
									<c:if test="${tfstatus.index>0 && tfunction.parentFunctionId!=0 && tfunction.functionTypeId==2 && tfunction.functionUrl!=null &&  tfunction.functionUrl!=''}">
										<li><a href="${tfunction.functionUrl}" target="rightFrame" title="${tfunction.functionName}">${tfunction.functionName}</a></li>
									</c:if>
								</c:forEach>
							</ol>
						</dd>
					</c:when>
	
				</c:choose>
			</c:forEach>
		</dl>
	</menu> --%>
	</div>
	<!-- /left_menu -->
	<div class="am-offcanvas-bar admin-offcanvas-bar">
		<ul id="collapase-nav-1" class="NXB-l-menu am-list admin-sidebar-list">
			<c:forEach items="${leftTabList}" varStatus="status" var="tabList">
				<c:if test="${status.index==0}">
					<!-- 第一个展开 -->
					<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#left-${tabList[0].functionId}'}" class=""><i class="am-icon-users"></i>${tabList[0].functionName}<i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="left-${tabList[0].functionId}" class="am-list admin-sidebar-sub am-collapse am-in" style="">
							<c:forEach items="${tabList}" var="tfunction" varStatus="tfstatus">
								<c:if test="${tfstatus.index>0 && tfunction.parentFunctionId!=0 && tfunction.functionTypeId==2 && tfunction.functionUrl!=null &&  tfunction.functionUrl!=''}">
									<li><a href="${tfunction.functionUrl}"><span class="am-icon-user am-margin-left-sm"></span> ${tfunction.functionName}</a></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:if>
				<c:if test="${status.index!=0}">
					<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#left-${tabList[0].functionId}'}" class="am-collapsed"><i class="am-icon-users"></i>${tabList[0].functionName}<i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="left-${tabList[0].functionId}" class="am-list admin-sidebar-sub am-collapse am-in" style="height: 0px;">
							<c:forEach items="${tabList}" var="tfunction" varStatus="tfstatus">
								<c:if test="${tfstatus.index>0 && tfunction.parentFunctionId!=0 && tfunction.functionTypeId==2 && tfunction.functionUrl!=null &&  tfunction.functionUrl!=''}">
									<li><a href="${tfunction.functionUrl}"><span class="am-icon-user am-margin-left-sm"></span> ${tfunction.functionName}</a></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
