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
								<li ><a href="/mobile/uc/course" title="">录播课程</a></li>
								<li class="current"><a href="javascript: void(0)" title="">直播课程</a></li>
							</ul>
						</div>
					</div>
				</div>
				<c:if test="${buycourses==null||buycourses.size()==0 }">
					<!-- 无数据时提示 开始 -->
					<div class="undataBox">
						<span class="undata-icon">&nbsp;</span>
						<span>你还没有购买直播记录</span>
					</div>
				</c:if>
				<c:if test="${buycourses!=null&&buycourses.size()>0 }">
				<div class="i-box" style="margin-bottom: -50px;">
					<div>
						<section>
							<div class="order-tab">
								<table cellspacing="0" cellpadding="0" border="0" width="100%">
									<!-- <caption>
										<span>直播状态</span>
										<select>
											<option value="">进行中</option>
											<option value="">已结束</option>
											<option value="">未开始</option>
										</select>
									</caption> -->
									<thead>
										<tr>
											<th style="text-align: left" width="50%">名称</th>
											<th width="20%" align="center">开始时间</th>
											<th width="20%" align="center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${buycourses }" var="course">
										<tr>
											<td align="left">
												<div>
													<h5 class="u-o-c-n">${course.name }</h5>
													<!-- <section class="c-price">
														<span>￥7899</span>
													</section> -->
													<div class="clear"></div>
												</div>
											</td>
											<td style="text-align: center"><p><fmt:formatDate value="${course.liveBeginTime}" pattern="yyyy-MM-dd"/></p><p><fmt:formatDate value="${course.liveBeginTime}" pattern="HH:mm:ss"/></td>

											<td style="text-align: center">
												<c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
													<a href="javascript:void(0)" class="u-o-c-btn u-o-c-btn-loo">已结束</a>
												</c:if>
												<c:if test="${live.beginTimeNum!=0&&live.endTimeNum!=0}">
													<a href="javascript:void(0)" class="u-o-c-btn u-o-c-btn-loo">未开始</a>
												</c:if>
												<c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
													<a href="javascript:void(0)" title="" class="u-o-c-btn u-o-c-btn-gre">进入</a>
												</c:if>
												
											</td>
										</tr>
										</c:forEach>	
											
									</tbody>
								</table>
							</div>
							<!-- /order table -->
						</section>
						<!-- /直播课程 -->

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