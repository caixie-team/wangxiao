<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>回复管理</title>
<script type="text/javascript">
function quxiaoRecommend(sugSuggestReplyId){
	dialogFun("问题回复","是否取消推荐?",2,"javascript:quxiaoRecommendAjax("+sugSuggestReplyId+")");	
}

function quxiaoRecommendAjax(sugSuggestReplyId){
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
				dialogFun("问题回复","成功",5,"${ctx}/admin/sug/toSugSuggestReplyList?sugSugestId=${sugSuggest.id}");	
				window.location.reload();
				closeFun();
			}
			if(result.message=="false"){
				dialogFun("问题回复","失败",6,"");
				window.location.reload();
				closeFun();
			}
		}
	});
}
function recommend(sugSuggestReplyId){//推荐回复
	dialogFun("问题回复","否推荐回复?",2,"javascript:recommendAjax("+sugSuggestReplyId+")");	
}
function recommendAjax(sugSuggestReplyId){
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
				dialogFun("问题回复","成功",5,"${ctx}/admin/sug/toSugSuggestReplyList?sugSugestId=${sugSuggest.id}");	
				window.location.reload();
				closeFun();
			}
			if(result.message=="false"){
				dialogFun("问题回复","失败",6,"");
				window.location.reload();
				closeFun();
			}
		}
	});
}

function delSugSuggestReplyBySugSuggestReplyId(sugSuggestReplyId){
	dialogFun("问题回复","是否删除回复?",2,"javascript:delSugSuggestReplyBySugSuggestReplyIdAjax("+sugSuggestReplyId+")");	
}

function delSugSuggestReplyBySugSuggestReplyIdAjax(sugSuggestReplyId){
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
				dialogFun("问题回复","成功",5,"");	
				closeFun();
			}
			if(result.message=="false"){
				dialogFun("问题回复","失败",5,"");	
				closeFun();
			}
		}
	});
}
</script>
</head>
<body  >
<form action="${ctx}/admin/sug/toSugSuggestReplyList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<input  type="hidden" name="sugSugestId" value="${sugSuggest.id}"/>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">问题管理</strong> / <small>问题回复</small></div>
</div>
<hr/>
<div class="optionList">
				<a class="am-btn am-btn-success" href="javascript:history.go(-1)">返回</a>
</div>
<div class="mt20">
<div class="doc-example">
<div class="am-scrollable-horizontal">
<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
			<caption>
			
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
							<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="javascript:void(0)" onclick="quxiaoRecommend(${ssrl.id })" title="取消采纳"><span class="am-icon-pencil-square-o"></span>取消采纳</a>
						</c:if>
						<c:if test="${sugSuggest.status==0 }">
						<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="javascript:void(0)" onclick="recommend(${ssrl.id })" title="采纳"><span class="am-icon-pencil-square-o"></span>采纳</a>
						</c:if>
					<c:if test="${ssrl.isbest==0 }">
					<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="javascript:void(0)" onclick="delSugSuggestReplyBySugSuggestReplyId(${ssrl.id })" title="删除"><span class="am-icon-trash-o"></span>删除</a>
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
		
		</table>
		</div>
		</div>
	</div><!-- /commonWrap -->
<!-- /tab2 end-->
<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
</form>		
</body>
</html>