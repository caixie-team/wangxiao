<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript" src="${ctximg}/static/common/page.js?v=<%=version%>"></script>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPage(1);
<form action="/xx.do" name="searchForm" id="searchForm" method="post">
<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/> --%>
<c:if test="${page != null && page.totalPageSize>1}">
	<section class="paginationWrap">
		<div class="pagination pagination-large">
			<ul id="pageFlag">
				<c:choose>
					<c:when test="${page.first}">
						<li class="disabled" id="backpage">
							<a href="javascript:void(0)">← 上一页</a>
						</li>
					</c:when>
					<c:otherwise>
						<li id="backpage">
							<a href="javascript:void(0)" onclick="goPage(${page.currentPage-1})">← 上一页</a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.last}">
						<li class="disabled" id="nextpage">
							<a href="javascript:javascript:void(0)">下一页 →</a>
						</li>
					</c:when>
					<c:otherwise>
						<li id="nextpage">
							<a href="javascript:void(0)" onclick="goPage(${page.currentPage+1 })">下一页 →</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
			<span class="c-666 fize14 ml30">第${page.currentPage}页/共${page.totalPageSize }页</span>
		</div>
	</section>
</c:if>
<script type="text/javascript">	
var totalPageSize=${page.totalPageSize};
var currentPage = ${page.currentPage-1}<1?1:${page.currentPage};
var totalPage = ${page.totalPageSize};
//showPageNumber();
</script>