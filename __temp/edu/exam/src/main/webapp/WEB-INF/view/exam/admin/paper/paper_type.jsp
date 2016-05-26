<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试卷列表</title>

<script type="text/javascript">

</script>
</head>
<body >

<!-- 内容 开始  -->
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>试卷类型</span> &gt; <span>试卷类型列表</span> </h4>
	</div>
<form action="${ctx}/admin/paper/listAllPaper" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input type="hidden" id="subjectId" name="queryPaper.subjectId" value="${queryPaper.subjectId }"/>
	<div class="mt20">
		<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<thead>
					<tr>
						<th width="8%"><span>ID</span></th>
						<th width="25%"><span>标题</span></th>
						<th width="25%"><span>描述</span></th>
						<th width="10%"><span>按钮标题</span></th>
						<th width="13%"><span>添加时间</span></th>
						<th width="5%"><span>状态</span></th>
						<th width="5%"><span>排序</span></th>
						<th width="15%"><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${paperTypeList.size()>0}">
				<c:forEach items="${paperTypeList}" var="ptl">
					<tr>
						<td>&nbsp;${ptl.id }</td>
						<td>${ptl.title }</td>
						<td>${ptl.describtion }</td>
						<td>${ptl.buttonTitle }</td>
						<td>
						<fmt:formatDate  value="${ptl.addTime}" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate> 
						</td>
						<td><c:if test="${ptl.state==0}">显示</c:if>
						<c:if test="${ptl.state==1}">隐藏</c:if></td>
						<td>
						${ptl.sort}
						</td>
						<td class="c_666 czBtn" align="center">
							<a class="btn smallbtn btn-y" href="${ctx}/admin/paper/toUpdatePaperType?id=${ptl.id }" title="修改">修改</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${paperTypeList.size()==0 }">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有试卷类型数据！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<!-- /pageBar begin -->
				<%-- <jsp:include page="/WEB-INF/view/common/admin_page.jsp" /> --%>
			<!-- /pageBar end -->
		</div><!-- /commonWrap -->
	</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
