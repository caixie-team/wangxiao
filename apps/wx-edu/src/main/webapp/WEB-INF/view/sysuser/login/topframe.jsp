<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta charset="utf-8" http-equiv="Content-Type"/>
<title>${websitemap.web.company}-${websitemap.web.title}</title>
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"/> 
<meta name="application-name" content=""/> 
<meta name="description" content=""/> 
<meta name="application-url" content=""/>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
<script src="${ctximg}/static/common/admin/js/admin-mine-n.js?v=${v}" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<!-- 头部 -->
  
		<!-- 全局公共头部+导航  开始 -->
	<header class="NXB-header">
		<div class="NXB-container">
			<!-- logo end -->
			<h1 id="logo">
				<a title="xxx公司" href="/">
					<img alt="xxx公司" src="/static/common/admin/images/new-logo.png">
				</a>
			</h1>
			<!-- logo end -->
			<aside class="h-top-r">
				<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
			      <li data-am-dropdown="" class="am-dropdown">
			        <a href="javascript:;" data-am-dropdown-toggle="" class="am-dropdown-toggle">
			          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
			        </a>
			        <ul class="am-dropdown-content">
			          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
			          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
			          <li><a href="/admin/sys/logout"><span class="am-icon-power-off"></span> 退出</a></li>
			        </ul>
			      </li>
			      <li class="am-hide-sm-only"><a id="admin-fullscreen" href="javascript:;"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
			    </ul>
			</aside>
			<div class="clear"></div>
		</div>
	</header>
		<nav class="NXB-nav">
		<div class="NXB-container">
			<ul class="NXB-nav-ul fl">
						<c:forEach items="${topTabList}" var="tfunction" varStatus="status">
							<c:if test="${tfunction.parentFunctionId==0 && tfunction.functionTypeId==2}">
								<c:if test="${status.index<7}">
								<li><a href="${ctx}/admin/func/showchild?parentId=${tfunction.functionId}" target="leftFrame" class=" ${tfunction.functionName }"> ${tfunction.functionName }</a></li>
								</c:if>
							</c:if>
						</c:forEach>
									<c:if test="${topTabList.size()>7}">
									<li>
											<div data-am-dropdown="" class="am-dropdown">
											<button data-am-dropdown-toggle="" class="am-btn am-btn-primary am-dropdown-toggle">更多 <span class="am-icon-caret-down"></span></button>
												<ul class="am-dropdown-content">
												<c:forEach items="${topTabList}" var="tfunction" varStatus="status">
													<c:if test="${status.index>=7}">
													<li><a href="${ctx}/admin/func/showchild?parentId=${tfunction.functionId}">${tfunction.functionName }</a></li>
													</c:if>
												</c:forEach>	
												</ul>
											</div>
									</li>
									</c:if>
			</ul>
			<div class="clear"></div>
		</div>
	</nav>
	
</body>
</html>