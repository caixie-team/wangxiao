<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<script type="text/javascript" src="${ctximg}/static/common/page.js?v=<%=version%>"></script>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPage(1);
<form action="/xx.do" name="searchForm" id="searchForm" method="post">
<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/> --%>
<c:if test="${page != null && page.totalResultSize>0}">
	<!-- 公共分页 开始 -->
					<div class="paging">
						<c:choose>
						   <c:when test="${page.first}">
							  <a class="undisable page-side" href="javascript:;" title="首页">首</a>
						   </c:when>
						   <c:otherwise>
						  	  <a class="page-side active" href="javascript:goPage(1);" title="首页">首</a>
						   </c:otherwise>
						</c:choose>
						<c:choose>
						   <c:when test="${page.first}">
							  <a id="backpage" class="undisable" href="javascript:;" title="上一页"><em class="icon12 page-prev"></em></a>
						   </c:when>
						   <c:otherwise>
						  	  <a id="backpage" class="active" href="javascript:goPage(${page.currentPage-1 });" title="上一页"><em class="icon12 page-prev"></em></a>
						   </c:otherwise>
						</c:choose>
						<c:choose>
						   <c:when test="${page.last}">
							 <a id="nextpage" class="undisable" href="javascript:;" title="下一页"><em class="icon12 page-next"></em></a>
						   </c:when>
						   <c:otherwise>
						 	 <a id="nextpage" class="active" href="javascript:goPage(${page.currentPage+1 })" title="下一页"><em class="icon12 page-next"></em></a>
						   </c:otherwise>
						</c:choose>
						<c:choose>
						   <c:when test="${page.last}">
							 <a class="undisable page-side" href="javascript:;" title="末页">末</a>
						   </c:when>
						   <c:otherwise>
						   	 <a class="page-side active" href="javascript:goPage(${page.totalPageSize })" title="末页">末</a>
						   </c:otherwise>
						</c:choose>
						<div class="clear"></div>
					</div>
						
	<!-- 公共分页 结束 -->
</c:if>
<script type="text/javascript">	
	var totalPageSize=${page.totalPageSize};
	var currentPage = ${page.currentPage-1}<1?1:${page.currentPage};
	var totalPage = ${page.totalPageSize};
	showPageNumber();
</script>