<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>回复列表</title>
<script type="text/javascript">
function deladmin(id,articleId){
	var judge=confirm("确定删除吗？");
	if(judge==true){
	$.ajax({
		url:"${ctx}/admin/disArticle/delartrep",
		data:{"disArticleReply.articleId":articleId,"disArticleReply.id":id},
		dataType:"json",
		type:"post",
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
				alert("删除成功");
				$("#rem"+id).remove();
				location.reload();
			}else{
				alert("请刷新重试");
				return false;
			}
		}
	});
	}else{
		return false;
	}
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>回复列表</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx }/admin/disArticle/replyList/${disArticleId}/${groupId}" method="post" name="searchForm" id="searchForm">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
					<table class="commonTab01" width="100%" cellspacing="0"
						cellpadding="0" border="0">
						<caption>
							<div class="">
								<div class="">
									<div class="optionList">
										<a class="btn btn-danger" 
											href="${ctx}/admin/disArticle/getDisArticleList" >返回</a>
									</div>
								</div>
								</div>
						</caption>
						<thead>
							<tr>
								<th width="3%"><span>ID</span></th>
								<th width="8%"><span>用户id</span></th>
								<th width="8%"><span>话题id</span></th>
								<th width="8%"><span>回复内容</span></th>
								<th width="4%"><span>回复时间</span></th>
								<th width="10%"><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02">
							<c:if test="${fn:length (disArticleReplyList)==0 }">
								<tr class="">
									<td align="center" colspan="16">
										<div class="tips">
											<span>对不起，此话题还没有回复！</span>
										</div>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${disArticleReplyList }" var="dalr">
								<tr id="rem${dalr.id }" class="">
									<td>${dalr.id }</td>
									<td>${dalr.recusId }</td>
									<td>${dalr.articleId }</td>
									<%-- <td>${fn:substring(dalr.replyContent,0,30)}</td> --%>
									<td>${dalr.shortContent}</td>
									<td><fmt:formatDate type="both" value="${dalr.replyTime }" ></fmt:formatDate></td>
									<td class="c_666 czBtn" align="center">
										<a class="btn smallbtn btn-y" href="javascript:void(0)" title="删除" onclick="deladmin(${dalr.id},${dalr.articleId })">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				<!-- /pageBar end -->
			</div>
		</div>
</body>
</html>