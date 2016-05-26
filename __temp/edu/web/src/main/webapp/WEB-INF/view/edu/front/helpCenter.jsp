<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>帮助中心</title>
</head>

<body>
	<!-- 帮助中心 开始 -->
	<div class="w1000">
		<div class="mt20 pr">
			<div class="pathwray unBr">
				<ol class="clearfix c-master f-fM fsize14">
					<li><a class="c-master" title="首页" href="/">首页</a> &gt;</li>
					<li><span>帮助中心</span></li>
				</ol>
			</div>
		</div>
		<div class="clearfix mb50">
			<!-- 左侧目录区域 -->
			<div class="hLeft">
				<div class="mt">
					<h2>帮助中心</h2>
				</div>
				<div class="mc">
					<c:forEach items="${helpMenus}" var="helpMenu1" varStatus="index">
						<dl>
							<c:if test="${helpMenu1.size()==1}">
							<dt><a title="${helpMenu1[0].name}" class="xs-1" href="${ctx}/help?id=${helpMenu1[0].id}">${helpMenu1[0].name}</a></dt>
							</c:if>
							<c:if test="${helpMenu1.size()>1}">
							<dt>${helpMenu1[0].name}</dt>
							</c:if>
							<c:if test="${helpMenu1.size()>1}">
								<dd>
									<c:forEach items="${helpMenu1}" var="helpMenu" varStatus="index1">
										<c:if test="${index1.index!=0}">
										<div class="hItem">
											<a title="${helpMenu.name}" class="xs-1" href="${ctx}/help?id=${helpMenu.id}">${helpMenu.name}</a>
										</div>
										</c:if>
									</c:forEach>
								</dd>
							</c:if>
						</dl>
					</c:forEach>
				</div>
			</div>
			<!-- 左侧目录区域 -->
			<!-- 中间内容区域 -->
			<div class="hRight">
				<section class="ml20">
					<div class="hContent">
						<section class="pl20 of pr20">
						<h4 class="mt10 hLh30 commLine03">
							<b class="grayCol3 fsize16">${helpMenuContent.name}</b>
						</h4>
							<div>
							${helpMenuContent.content}
							</div>
						</section>
					</div>
				</section>
			</div>
			<!-- 中间内容区域 -->
		</div>
	</div>
	
</body>
</html>
