<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>回复管理</title>
<script type="text/javascript">
function delFriend(cusId){
	if(confirm("是否删除?")){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/cus/delFriend",
			data:{"friend.cusId":"${friend.cusId}",
				"friend.cusFriendId":cusId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$("#del"+cusId).remove();
					alert("成功");
				}
				if(result.message=="false"){
					alert("失败");
				}
			}
		});
	 }
}
function quxiaoRecommend(sugSuggestReplyId){
	if(confirm("是否取消推荐?")){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/sug/updateSugSuggestReplyForIsBest",
		data:{"sugSuggestReplyId":sugSuggestReplyId,
			"sugSuggestId":"${sugSuggest.id}",
			"isBest":0},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				alert("成功");
				window.location.href="${ctx}/admin/sug/toSugSuggestReplyList?sugSugestId=${sugSuggest.id}";
			}
			if(result.message=="false"){
				alert("失败");
			}
		}
	});
	}
}
function recommend(sugSuggestReplyId){//推荐回复
	if(confirm("是否推荐回复?")){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/sug/updateSugSuggestReplyForIsBest",
		data:{"sugSuggestReplyId":sugSuggestReplyId,
			"sugSuggestId":"${sugSuggest.id}",
			"isBest":1},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				alert("成功");
				window.location.href="${ctx}/admin/sug/toSugSuggestReplyList?sugSugestId=${sugSuggest.id}";
			}
			if(result.message=="false"){
				alert("失败");
			}
		}
	});
	}
}

function delSugSuggestReplyBySugSuggestReplyId(sugSuggestReplyId){
	if(confirm("是否删除回复?")){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/sug/delSugSuggestReplyBySugSuggestReplyId",
		data:{"suggestReplyId":sugSuggestReplyId},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+sugSuggestReplyId).remove();
				alert("成功");
			}
			if(result.message=="false"){
				alert("失败");
			}
		}
	});
	}
}
</script>
</head>
<body  >
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>问题管理</span> &gt; <span>问题回复</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/sug/toSugSuggestReplyList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<input  type="hidden" name="sugSugestId" value="${sugSuggest.id}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="optionList">
				<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="10%"><span>回复内容</span></th>
					<th width="5%"><span>回复人</span></th>
					<th width="10%"><span>添加时间</span></th>
					<th width="10%"><span>已采纳</span></th>
					<th width="10%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02">
			<c:forEach items="${sugSuggestReplyList}" var="ssrl">
				<tr id="del${ssrl.id }">
					<td>${ssrl.shortContent }</td>
					<td>${ssrl.showname }</td>
					<td><fmt:formatDate value="${ssrl.addtime}" pattern="yyyy/MM/dd  HH:mm:ss" ></fmt:formatDate></td>
					<td>
					<c:if test="${ssrl.isbest==1 }">是</c:if>
					<c:if test="${ssrl.isbest==0 }">否</c:if>
					</td>
					<td class="c_666 czBtn" align="center">
					<c:if test="${sugSuggest.status==1 &&ssrl.isbest==1}">
							<a class="btn smallbtn btn-y" href="javascript:void(0)"
						onclick="quxiaoRecommend(${ssrl.id })" title="取消采纳">取消采纳</a>
						</c:if>
						<c:if test="${sugSuggest.status==0 }">
						<a class="btn smallbtn btn-y" href="javascript:void(0)"
						onclick="recommend(${ssrl.id })" title="采纳">采纳</a>
						</c:if>
					<c:if test="${ssrl.isbest==0 }">
					<a class="btn smallbtn btn-y" href="javascript:void(0)"
						onclick="delSugSuggestReplyBySugSuggestReplyId(${ssrl.id })" title="删除">删除</a>
					</c:if>
						</td>
				</tr>
			</c:forEach>
			<c:if test="${sugSuggestReplyList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有回复数据！</span>
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