<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="comm-title-2">
	<span class="c-t-2-l">热门课程</span>
</section>
<section class="v-ranking-list">

	<ol>
		<c:forEach items="${mapCourseList.index_course_6 }" var="gVideo">
			<li><a target="_blank"
				href="${ctxweb }/front/couinfo/${gVideo.id}"
				title="" class="v-ranking-pic"> <c:if
						test="${not empty gVideo.logo  }">
						<img src="<%=staticImageServer%>${gVideo.logo}" width="69"
							height="51" alt="">
					</c:if> <c:if test="${ empty gVideo.logo }">
						<img src="<%=staticImageServer%>/static/edu/images/default/default_goods.jpg" width="69"
							height="51" alt="">
					</c:if>
			</a>
				<h5>
					<a target="_blank"
						href="${ctxweb }/front/couinfo/${gVideo.id}"
						title="${gVideo.name }">${gVideo.name}</a>
				</h5>
				<div class="mt5">
					<a target="_blank" href="javascript:void(0)" title="" class="c-888">课时：${gVideo.lessionnum }课时</a>
				</div></li>
		</c:forEach>
	</ol>
</section>