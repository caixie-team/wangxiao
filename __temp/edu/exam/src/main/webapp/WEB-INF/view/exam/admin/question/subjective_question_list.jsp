<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>主观题试题记录列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>

<script type="text/javascript">
function tijiao(qstrcdId,score){
	var inputscore = $("#score"+qstrcdId).val();
	if(inputscore>score){
		alert("输入的分数不能超过"+score+"分");
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/paper/updateExampaperRecordScore",
		data:{"score":inputscore,"qstrcdId":qstrcdId,"qstScore":score},
		async:false,
		success:function(result){
			if(result.success){
				$(".status"+qstrcdId).html(inputscore);
				alert("阅卷成功");
			}else{
				alert("阅卷失败请刷新重试");
			}
		}
	});
}
 
 function query(){
	 goPage(1);
 }
</script>
</head>
<body  >
<form action="${ctx}/admin/quest/toSubjectiveQstRecordList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input name="queryQuestion.paperRecordId" id="paperRecordId" type="hidden" value="${queryQuestion.paperRecordId}"/>
<!-- 内容 开始  -->
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp; <span>主观题试题记录列表</span> </h4>
</div>
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>内容：</font></span>
									<input type="text"  id="qstContent" value="${queryQuestion.qstContent}"  name="queryQuestion.qstContent" />
								</li>
							</ul>
							
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<input type="button" onclick="query()" class="btn btn-danger" value="查询" name=""/>
								</li>
							</ul>
						</div>
						
						<div class="clearfix"></div>
					</div>
				</caption>
				<thead>
					<tr>
						<th><span>&nbsp;ID</span></th>
						<th width="25%"><span>试题内容</span></th>
						<th><span>试题解析</span></th>
						<th><span>用户答案</span></th>
						<th><span>用户邮箱</span></th>
						<th><span>试题分数</span></th>
						<th><span>用户得分</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${queryQuestionList.size()>0}">
				<c:forEach  items="${queryQuestionList}" var="trquestion" >
					<tr>
						<td><input type="checkbox" class="questionIds" id="${trquestion.qstrdId }"/>&nbsp;${trquestion.qstrdId }</td>
						<td>${trquestion.qstContent }</td>
						<td>${trquestion.qstAnalyze }
						</td>		
						<td>${trquestion.userAnswer }</td>
						<td>${trquestion.email }</td>
						<td>${trquestion.score }</td>
						<td class="status${trquestion.qstrdId }">
						<c:if test="${trquestion.state==0 }">未审阅</c:if>
						<c:if test="${trquestion.state==1 }">${trquestion.userscore }</c:if>
						</td>
						<td class="c_666 czBtn" align="center">
							<input type="text" style="width:40px" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " 
							onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "  id="score${trquestion.qstrdId }" value="${trquestion.userscore}"/>分
							<a href="javascript:tijiao(${trquestion.qstrdId },${trquestion.score })" title="提交" class="ml10 btn smallbtn btn-y">提交</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${queryQuestionList==null||queryQuestionList.size()==0}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有试题数据！</span>
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
<!-- 内容 结束 -->
</form>
</body>
</html>
