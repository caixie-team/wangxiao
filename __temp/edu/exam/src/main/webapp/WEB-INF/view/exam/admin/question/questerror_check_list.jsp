<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>纠错管理</title>
<script type="text/javascript">
function remError(id){
	var judge=confirm("确定删除此问题？");
	if(judge==true){
		$.ajax({
			url:"/admin/quest/delQuestErrorCheckById",
			data:{"questErrorCheck.id":id},
			dataType:"json",
			type:"post",
			async:false,
			cache:false,
			success:function(result){
				if(result.message=="success"){
					alert("删除成功");
					$("#rem"+id).remove();
					return;
				}else{
					alert("删除失败，请稍后重试");
					return;
				}
			}
		});
	}
}
function submitSearch(){
	$("#pageCurrentPage").val(1);
	var qstId=$("input[name='questErrorCheck.questionId']").val();
	if(qstId.trim()==''||qstId==null){
		$("input[name='questErrorCheck.questionId']").val(0);
	}
	$("#searchForm").submit();
	}
</script>
</head>
<body>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>纠错管理</span> &gt; <span>纠错列表</span> </h4>
</div>

<form action="${ctx}/admin/quest/queryQuestErrorCheckList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20">
		<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>试题Id：</font></span>
									<input type="text" name="questErrorCheck.questionId" <c:if test="${questErrorCheck.questionId!=0 }">value="${questErrorCheck.questionId }"</c:if> id="questionId" />
								</li>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
								</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="5%"><span>ID</span></th>
						<th width="10%"><span>试题Id</span></th>
						<th width="10%"><span>试卷名称</span></th>
						<th><span>试题内容</span></th>
						<th><span>纠错答案</span></th>
						<th><span>添加时间</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${questErrorCheckList.size()>0}">
				<c:forEach  items="${questErrorCheckList}" var="pec" >
					<tr id="rem${pec.id }">
						<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
						<td>${pec.id }</td>
						<td>${pec.questionId }</td>
						<td>${pec.paperName }</td>
						<td>${pec.qstName }</td>
						<td width="30%">${pec.content }</td>
						<td><fmt:formatDate type="both" value="${pec.addTime }"/></td>
						<td  class="c_666 czBtn" align="center">
						<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${pec.questionId}">修改</a>
						<a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:void(0)" onclick="remError(${pec.id })">删除</a>
						</td>
						<%--<td class="c_666 czBtn" align="center">
							<a href="${ctx}/admin/quest/lookQuestion?queryQuestion.id=${trquestion.id }" title="查看" class="btn smallbtn btn-y">查看</a>
							<a href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${trquestion.id }" title="修改" class="btn smallbtn btn-y">修改</a>
							<a href="javascript:delQuestionListBatch(${trquestion.id })" title="删除" class="btn smallbtn btn-y">删除</a>
						</td> --%>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${questErrorCheckList.size()==0||questErrorCheckList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有纠错信息！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
</form>
</body>
</html>
