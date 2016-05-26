<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPageAjax(1);
<form action="/xx.do" name="searchForm" id="searchForm" method="post">
<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/> --%>
<c:if test="${page != null && page.totalPageSize>1}">
	<div class="pagination pagination-large clearbottom">
		<ul>
			<!-- 上一页 -->
			<c:choose>
			   <c:when test="${page.first}">
				   <li id="" class="disabled"><a href="javascript:void(0)">←上一页</a></li>
			   </c:when>
			   <c:otherwise>
			  <li id=""><a href="javascript:goPageAjax(${page.currentPage-1 });">←上一页</a></li>
			   </c:otherwise>
			</c:choose>
			<!--下一页 -->
			<c:choose>
			   <c:when test="${page.last}">
				 <li id="" class="disabled"><a href="javascript:void(0)">下一页→</a></li>
			   </c:when>
			   <c:otherwise>
			 <li id="" ><a href="javascript:goPageAjax(${page.currentPage+1 });">下一页→</a></li>
			   </c:otherwise>
			</c:choose>
		</ul>
			<span class="c-666 fize14 ml30">第${page.currentPage }页/共${page.totalPageSize }页</span>
	</div>
</c:if>
	
	
	
	
