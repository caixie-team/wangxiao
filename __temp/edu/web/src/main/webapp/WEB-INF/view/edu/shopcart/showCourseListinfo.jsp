<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<table width="100%" cellspacing="0" cellpadding="0" border="0" >
	<thead>
		<tr>
			<th width="40%" align="left">课程名称</th>
			<th width="20%" align="center">主讲讲师</th>
			<th width="10%" align="center">课时</th>
			<th width="15%" align="center">价格</th>
			<th width="15%" align="center">操作</th>
		</tr>
	</thead>
	<tbody >
		<c:set var="totalMoney" value="0"></c:set>
		<c:choose>
			<c:when test="${not empty  shopcartList}">
				<c:forEach items="${shopcartList }" var="sc">
				<tr>
					<td>
                    <c:choose>
                        <c:when test="${not empty sc.course.logo}">
                            <img src="<%=staticImageServer%>${sc.course.logo}" width="50" height="38" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" width="50" height="38" alt="">
                        </c:otherwise>
                    </c:choose>
					${sc.course.name }</td>
					<td align="center">
					<c:forEach items="${sc.course.teacherList }" var="teacher">
					${teacher.name}&nbsp;
					</c:forEach> </td>
					<td align="center">${sc.course.lessionnum }</td>
					<td align="center">￥<samp id="sampCourse_${sc.course.id}">${sc.course.currentprice }</samp></td>
					<td align="center"><a href="javascript:deleteid(${sc.id},${sc.goodsid },${sc.type})" class="n_del c-999">删除</a></td>
					<c:set var="totalMoney" value="${totalMoney+sc.course.currentprice }"></c:set>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<td colspan="5" align="center">购物车为空,请先选购课程</td>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<div class="buyCom_price c-666 fr tar">
	<span>课程数量：<span class="fsize14 c-orange mr5" id="buyCount">${shopcartList.size()}</span>&nbsp;&nbsp;</span> 
	课程金额：<span class="fsize14 c-orange mr5" id="div_totalMoney">￥${totalMoney }</span>
</div>
			
			
