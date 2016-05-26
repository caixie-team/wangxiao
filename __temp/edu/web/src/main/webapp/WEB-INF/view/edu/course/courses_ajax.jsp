<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${ empty courseList}">
<!-- 没有课程时的样式 -->
</c:if>
<c:forEach items="${courseList}" var="subjectCourse">
	<li>
		<div class="pr s-c-pics" style="cursor: pointer;" >
            <a href='${ctx}/front/couinfo/${subjectCourse.id }' target="_blank" title="${subjectCourse.name}">
			<c:choose>
				<c:when test="${ not empty subjectCourse.logo}">
					<img xSrc="<%=staticImageServer%>${subjectCourse.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.gif" height="165" width="220" alt="${subjectCourse.name}">
				</c:when>
				<c:otherwise>
					<img xSrc="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.gif" height="165" width="220" alt="${subjectCourse.name}">
				</c:otherwise>
			</c:choose>
            </a>
			<div class="pa s-c-name">
				<a target="_blank"  href="${ctx}/front/couinfo/${subjectCourse.id}" title="${subjectCourse.name}" class="fsize14 c-fff">${subjectCourse.name}</a>
			</div>
		</div>
		<section class="pl10 pr10 of">
			<div class="s-c-desc pt10 pb10">
				<p class="c-666">${subjectCourse.title}</p>
			</div>
			<div class="of mt10 mb20">
				<section class="fl w50pre">
					<div class="tac">
						<p class="c-999">课时：${subjectCourse.lessionnum}</p>
						<p class="c-999">
							点击量：<b title="点击量：${subjectCourse.viewcount}"
								class="star-1-3">${subjectCourse.viewcount}</b>
						</p>
						<p class="mt10">
							<a class="gray-btn" title="开通课程"  target="_blank"  href="${ctx}/front/couinfo/${subjectCourse.id}" >
							<tt class="vam c-333">开通课程</tt>
							<em class="ml5 r-arrow icon16 vam">&nbsp;</em></a>
						</p>
					</div>
				</section>
				<section class="fl w50pre">
					<div class="tac">
						<p class="c-999">价格</p>
						<p>
							<strong class="c-master">${subjectCourse.currentprice}￥</strong>
						</p>
						<p class="mt10">
							<a class="c-orange" title="收藏课程" href="javascript:void(0)" onclick="house(${subjectCourse.id})">收藏课程</a>
						</p>
					</div>
				</section>
			</div>
		</section>
	</li>
</c:forEach>
