<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPage(1);
<form action="${ctx}/admin" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/> --%>
<script type="text/javascript" src="${ctximg}/static/common/page.js?v=<%=version%>"></script>
<c:if test="${page != null && page.totalResultSize>0}">
<section class="mt50 mb50">
	<div class="pagination pagination-large tac">
					<ul>
						<c:choose>
                          <c:when test="${page.first}">
                          	<li class="disabled"><a href="#">首页</a></li>
                          </c:when>
                          <c:otherwise>
                          	 <li><a href="javascript:goPage(1);">首页</a></li>
                          </c:otherwise>
                        </c:choose>
		                
		                <c:choose>
                              <c:when test="${page.first}">
                               <li id="backpage" class="disabled"><a href="javascript:void(0)">←上一页</a></li>
                              </c:when>
                              <c:otherwise>
                              	<li id="backpage"><a href="javascript:goPage(<c:out value="${page.currentPage-1 }"/>);">←上一页</a></li>
                              </c:otherwise>
                            </c:choose>
                      
						
						<c:choose>
                              <c:when test="${page.last}">
                              	<li id="nextpage" class="disabled"><a href="javascript:void(0)">下一页→</a></li>
                              </c:when>
                              <c:otherwise>
                              	<li id="nextpage" ><a href="javascript:goPage(<c:out value="${page.currentPage+1 }"/>)">下一页→</a></li>
                              </c:otherwise>
                            </c:choose>
			             
			             <c:choose>
                              <c:when test="${page.last}">
					             	<li class="disabled"><a href="javascript:void(0)">尾页 </a></li>
                              </c:when>
                              <c:otherwise>
								 	<li ><a href="javascript:goPage(<c:out value="${page.totalPageSize}"/>)">尾页 </a></li>
                              </c:otherwise>
                            </c:choose>
			            <%-- <li class="c_333">
			             	<tt class="ml10 disIb">第</tt>
			             	<input id="pageNoIpt" type="text" size="4" style="height:16px; margin-top:2px; width:24px; border:1px solid #999999;" /><tt class="ml10 disIb">页</tt>
			             	<input class="btn btn-y ml10 analysis-tb" type="button" onclick="goPageByInput()" value="确定" name="确定">
			             	&nbsp;&nbsp;
			         	</li> --%>
					</ul>
        </div>
        </section>

</c:if>

<script type="text/javascript">
    var totalPageSize=${page.totalPageSize};
    var currentPage = ${page.currentPage-1}<1?1:${page.currentPage};
    //alert(currentPage);
    var totalPage = ${page.totalPageSize};
    showPageNumber();
</script>