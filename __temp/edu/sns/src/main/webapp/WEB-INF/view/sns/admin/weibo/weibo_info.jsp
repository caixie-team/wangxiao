<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>回复管理</title>
<script type="text/javascript">
function delComment(commentId,weiboId){
	if(confirm("是否删除?")){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/weiBo/delCommentById",
			data:{"comment.id":commentId,"comment.weiboId":weiboId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$("#del"+commentId).remove();
					alert("成功");
				}
				if(result.message=="false"){
					alert("失败");
				}
			}
		});
	 }
}
function fanhui(){
	window.history.go(-1);
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>观点管理</span> &gt; <span>观点管理</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/weiBo/toWeiBoInfo" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<input id="pageCurrentPage" type="hidden" name="comment.weiboId" value="${comment.weiboId}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
			<div class="optionList">
				<a class="btn btn-danger" href="${ctx}/admin/weiBo/toWeiBoList">返回</a>
			</div>
			</caption>
			<thead>
				<tr>
					<th width="3%"><span>ID</span></th>
					<th width="25%"><span>评论内容</span></th>
					<th width="10%"><span>评论发表人</span></th>
					<th width="10%"><span>发表时间</span></th>
					<th width="10%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02">
				<c:forEach items="${queryCommentList}" var="qwbl">
				<tr id="del${qwbl.id }">
					<td>${qwbl.id }</td>
					<td>${qwbl.shortContent }</td>
					<td>${qwbl.showname }</td>
					<td><fmt:formatDate value="${qwbl.addTime}" pattern="yyyy/MM/dd  HH:mm:ss" ></fmt:formatDate></td>
					<td class="c_666 czBtn" align="center">
						<a class="btn smallbtn btn-y" href="javascript:delComment(${qwbl.id },${qwbl.weiboId })" title="删除">删除</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${queryCommentList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有评论数据！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		</form>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- /tab2 end-->
</body>
</html>