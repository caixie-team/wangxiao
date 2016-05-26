<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学员列表</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员视频权限</small></div>
</div>
<hr>
<div class="mt20">
	<div data-am-tabs="" class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="#tab1">课程购买信息</a></li>
			<li class=""><a href="#tab2">内部课程信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<div class="am-g">
					<div class="mt20">
						<form class="am-form">
							<table class="am-table am-table-striped am-table-hover table-main">
								<thead>
								<tr>
									<th class="table-title" width="20%">部门</th>
									<th class="table-title" width="20%">课程名称</th>
									<th class="table-title" width="20%">售卖形式</th>
									<th class="table-title" width="20%">上下架</th>
									<th class="table-title" width="20%">价格</th>
								</tr>
								</thead>
								<tbody>
									<c:if test="${not empty buycourses}">
										<c:forEach items="${buycourses}" var="cou">
											<tr id="rem${cou.id }">
												<td>${cou.id}</td>
												<td>${cou.name }</td>
												<td><c:if test="${cou.sellType=='COURSE'}">课程</c:if> <c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if> <c:if test="${cou.sellType=='LIVE'}">直播</c:if></td>
												<td><c:if test="${cou.isavaliable==0}">上架</c:if> <c:if test="${cou.isavaliable==1}">下架</c:if></td>
												<td>${cou.currentprice}</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty buycourses}">
										<tr>
											<td colspan="5">
												<div data-am-alert=""
													 class="am-alert am-alert-secondary mt20 mb50">
													<center>
														<big> <i class="am-icon-frown-o vam"
																 style="font-size: 34px;"></i> <span class="vam ml10">还没购买课程相关数据！</span></big>
													</center>
												</div>
											</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>

			<div id="tab2" class="am-tab-panel am-fade am-in">
				<div class="am-g">
					<div class="mt20">
						<form class="am-form">
							<table class="am-table am-table-striped am-table-hover table-main">
								<thead>
								<tr>
									<th class="table-title" width="20%">部门</th>
									<th class="table-title" width="20%">课程名称</th>
									<th class="table-title" width="20%">售卖形式</th>
									<th class="table-title" width="20%">上下架</th>
									<th class="table-title" width="20%">价格</th>
								</tr>
								</thead>
								<tbody>
								<c:if test="${not empty innerCourseDtos}">
									<c:forEach items="${innerCourseDtos}" var="group">
										<tr>
										<c:forEach items="${group.courseDtoArrayList}" var="cou">
											<td>${group.name }</td>
											<td>${cou.name }</td>
											<td><c:if test="${cou.sellType=='COURSE'}">课程</c:if> <c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if> <c:if test="${cou.sellType=='LIVE'}">直播</c:if></td>
											<td><c:if test="${cou.isavaliable==0}">上架</c:if> <c:if test="${cou.isavaliable==1}">下架</c:if></td>
											<td>${cou.currentprice}</td>
											</tr>
										</c:forEach>
									</c:forEach>
								</c:if>
								<c:if test="${empty innerCourseDtos}">
									<tr>
										<td colspan="5">
											<div data-am-alert=""
												 class="am-alert am-alert-secondary mt20 mb50">
												<center>
													<big> <i class="am-icon-frown-o vam"
															 style="font-size: 34px;"></i> <span class="vam ml10">还没内部课程相关数据！</span></big>
												</center>
											</div>
										</td>
									</tr>
								</c:if>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
