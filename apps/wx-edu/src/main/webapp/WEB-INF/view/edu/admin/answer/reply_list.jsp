<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<title>回复列表</title>
<script type="text/javascript">
function updateStatus(id,status,answerId,obj){
	$.ajax({
		type:"post",
		dataType:"json",
		data:{"answerReply.id":id,"answerReply.status":status,"answerReply.answerId":answerId},
		url:"${ctx}/admin/answer/updateReplyStatus",
		async:false,
		success:function(result){
			if(result.message=="success"){
				if(status==1){
					$("#status"+id).html("作废");
					$(obj).html("恢复");
					$(obj).attr("onclick","updateStatus("+id+","+0+","+answerId+",this)");
					alert("已作废该回答");
				}else{
					$("#status"+id).html("正常");
					$(obj).html("作废");
					$(obj).attr("onclick","updateStatus("+id+","+1+","+answerId+",this)");
					alert("已恢复该回答");
				}
			}else{
				alert("系统繁忙，请稍后重试");
			}
		}
		
	});
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
				<form action="${ctx}/admin/answer/replyList" method="post" name="searchForm" id="searchForm">
					<input type="hidden" name="answerId" value="${answerId}"> 
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
					<table class="commonTab01" width="100%" cellspacing="0"
						cellpadding="0" border="0">
						<caption>
							<div class="">
								<div class="">
									<div class="optionList">
										<a class="am-btn am-btn-danger" href="${ctx}/admin/answer/answerQuestionList?answerQuestion.type=course" >返回</a>
									</div>
								</div>
								</div>
						</caption>
						<thead>
							<tr>
								<th width="3%"><span>ID</span></th>
								<th width="6%"><span>用户名称</span></th>
								<th width="4%"><span>答疑ID</span></th>
								<th width="20%"><span>回复内容</span></th>
								<th width="6%"><span>回复时间</span></th>
								<th width="4%"><span>状态</span></th>
								<th width="5%"><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02">
							<c:if test="${fn:length (replyList)==0 }">
								<tr class="">
									<td align="center" colspan="16">
										<div class="tips">
											<span>对不起，此答疑还没有回复！</span>
										</div>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${replyList }" var="reply">
								<tr id="rem${reply.id }" class="">
									<td align="center">${reply.id }</td>
									<td align="center">${reply.showName }</td>
									<td align="center">${reply.answerId }</td>
									<td align="center">${reply.content}</td>
									<td align="center"><fmt:formatDate type="both" value="${reply.addTime }" ></fmt:formatDate></td>
									<td align="center">
										<span id="status${reply.id }">
										<c:if test="${reply.status==0}">
											正常
										</c:if> 
										<c:if test="${reply.status==1}">
											作废
										</c:if> 
										</span>
									</td>
									<td class="c_666 czBtn" align="center">
									<c:if test="${reply.userId!=0 }">
										<c:if test="${reply.status==0}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)" title="作废" onclick="updateStatus(${reply.id},1,${answerId },this)">作废</a>
										</c:if> 
										<c:if test="${reply.status==1}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)" title="恢复" onclick="updateStatus(${reply.id},0,${answerId },this)">恢复</a>
										</c:if> 
										
									</c:if>
										
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