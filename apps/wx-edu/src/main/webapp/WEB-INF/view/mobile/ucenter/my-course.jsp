<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的课程</title>
	
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/uc/home" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>我的课程</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div>
					<div class="v-card-title-box">
						<div class="v-card-title" style="position: absolute;">
							<ul class="col-2" id="item-l-ul">
								<li class="current"><a href="javascript: void(0)" title="">录播课程</a></li>
								<li><a href="/mobile/uc/mylive" title="">直播课程</a></li>
							</ul>
						</div>
					</div>
				</div>
				<c:if test="${buycourses==null||buycourses.size()==0 }">
					<!-- 无数据时提示 开始 -->
					<div class="undataBox">
						<span class="undata-icon">&nbsp;</span>
						<span>你还没有购买课程记录</span>
					</div>
				</c:if>
				<c:if test="${buycourses!=null&&buycourses.size()>0 }">
				<!-- 无数据时提示 结束 -->
				<div class="i-box" style="margin-bottom: -50px;">
					<div>
						<section>
							<div class="order-tab">
								<table cellspacing="0" cellpadding="0" border="0" width="100%">
									<thead>
										<tr>
											<th style="text-align: left" width="50%">名称</th>
											<th width="20%" align="center">时间</th>
											<th width="20%" align="center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${buycourses }" var="course">
										<tr>
											<td align="left">
												<div>
													<span class="u-o-c-p"><img src="<%=staticImageServer %>${course.logo }" width="80" alt=""></span>
													<h5 class="u-o-c-n">${course.name }</h5>
													<%-- <section class="c-price">
														<span>￥${course.name }</span>
													</section> --%>
													<div class="clear"></div>
												</div>
											</td>
											<td style="text-align: center"><p><fmt:formatDate value="${course.authTime }" pattern="yyyy-MM-dd"/></p><p>剩<span style="color:#f06060;">${course.remainDays }</span>天</p></td>
											<td style="text-align: center">
												<a href="/mobile/course/info/${course.id}" title="" class="u-o-c-btn u-o-c-btn-gre">观看</a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /order table -->
						</section>
						<!-- /录播课程 -->
					</div>
				</div>
				</c:if>
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			//scrollLoad(); //滚动响应加载课程图片
		})
	</script>
</body>
</html>