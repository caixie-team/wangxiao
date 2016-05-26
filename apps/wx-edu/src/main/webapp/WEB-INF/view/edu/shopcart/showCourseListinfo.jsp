<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<dt>
<ul class="c-o-head clearfix of">
	<li class="c-head-li-16"><span>课程</span></li>
	<li class="c-head-li-1"><span>标题</span></li>
	<li class="c-head-li-2"><span>讲师</span></li>
	<li class="c-head-li-3"><span>价格</span></li>
	<li class="c-head-li-4"><span>操作</span></li>
</ul>
</dt>
<dd>
<c:set var="totalMoney" value="0" />
<c:choose>
	<c:when test="${not empty shopcartList}">
		<c:forEach items="${shopcartList }" var="sc">
			<ul class="c-o-tbody clearfix of">
				<li class="c-head-li-16"><div class="c-o-t-img">
					<c:choose>
						<c:when test="${not empty sc.course.logo}">
							<img src="<%=staticImageServer%>${sc.course.logo}" class="img-responsive">
						</c:when>
						<c:otherwise>
							<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" class="img-responsive">
						</c:otherwise>
					</c:choose>
				</div></li>
				<li class="c-head-li-36">
					<div class="c-head-title">
						<h6 class="unFw c-666"> ${sc.course.name }</h6>
						<div class="mt10 u-order-desc">
							<p class="c-999 txtOf"> ${sc.course.title }</p>
						</div>
					</div>
				</li>
				<li>
					<div class="c-t-wz"><span class="c-666">讲师：<c:forEach items="${sc.course.teacherList }" var="teacher">${teacher.name}&nbsp;&nbsp;</c:forEach></span></div>
				</li>
				<li>
					<div class="c-t-wz"><span class="c-666">￥${sc.course.currentprice }</span></div>
				</li>
				<li>
					<div class="c-t-wz"><a class="c-666" href="javascript:deleteid(${sc.id},${sc.goodsid },${sc.type})">取消</a></div>
				</li>
			</ul>
			<c:set var="totalMoney" value="${totalMoney+sc.course.currentprice }" />
		</c:forEach>
	</c:when>
	<c:otherwise>
		<section class="no-data-wrap">
			<em class="no-data-ico cTipsIco">&nbsp;</em>
			<span class="c-666 fsize14 ml10 vam">购物车为空,请先选购课程...</span>
		</section>
	</c:otherwise>
</c:choose>
</dd>
<div class="buyCom_price c-666 fr tar mt10 pr10">
	<span>课程数量：<span id="buyCount" class="fsize14 c-orange mr5">${shopcartList.size()}</span>&nbsp;&nbsp;</span>
	课程金额：<span id="div_totalMoney" class="fsize14 c-orange mr5">￥${totalMoney }</span>
</div>
			
