<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>员工列表</title>
<script type="text/javascript">
function submitSearch() {
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	
}
//存放数据的数组
var myArrayMoveStock = new Array();
//将小页面被选中的入库明细信息带到大页面
function selectQstList() {
	if (initArray()) {
		//调用父页面的方法
		window.close();
	}
}

function initArray() {
	var qstChecked = $(".questionIds:checked");
	if (qstChecked.length == 0) {
		alert("请选择员工");
		return;
	}
	qstChecked.each(function() {
		toParentsValue($(this).val());
	});
	opener.addnewExamId(myArrayMoveStock);
	quxiao();
}
//把选中的一条记录放到数组中
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}
function quxiao() {
	window.close();
}
</script>
</head>
<body>
		<form action="${ctx}/admin/task/getTaskExamList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li>
												<span class="ddTitle"> 
												<font>试卷ID：</font>
												</span> <input type="text" name="examId" value="" id="questionId" />
											</li> 
											<li>
												<span class="ddTitle"> 
												<font>试卷名称：</font>
												</span> <input type="text" name="examName" value="" id="questionId" />
												<input class="am-btn am-btn-danger" type="button" onclick="submitSearch()" name="" value="查询"/>
											</li> 
										</ul>
									</div>
									<div class="clearfix"></div>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="10%"><span>ID</span></th>
									<th width="10%"><span>试卷名</span></th>
									<th width="10%"><span>科目</span></th>
									<th width="10%"><span>考试描述</span></th>
									<th width="10%"><span>试卷难度</span></th>
									<th width="10%"><span>试卷总分</span></th>
									<th width="10%"><span>类型</span></th>
									<th width="10%"><span>答题时间</span></th>
									<th width="10%"><span>创建时间</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${result.entity.paperList.size()>0}">
									<c:forEach items="${result.entity.paperList}" var="exam">
									<tr id="rem${exam.id }">
										<td>
											<input type="${teacher.checkboxFalg=='radio'?'radio':'checkbox' }" id="<fmt:formatNumber value="${exam.id }" pattern="0"/>" name="checkbox" value="<fmt:formatNumber value="${exam.id }" pattern="0"/>" class="questionIds" /> 
											<fmt:formatNumber value="${exam.id }" pattern="0"/>
										</td>
										<td>
											${exam.name }
										</td>
										<td>
											${exam.subjectName }
										</td>
										<td>
											${exam.info }
										</td>
										<td>
											<c:if test="${exam.level==1}">
												简单
											</c:if>
											<c:if test="${exam.level==2}">
												中等
											</c:if>
											<c:if test="${exam.level==3}">
												困难
											</c:if>
										</td>
										<td>
											<fmt:formatNumber value="${exam.score }" pattern="0"/>
										</td>
										<td>
											<c:if test="${exam.type==0}">
												专项智能练习
											</c:if>
											<c:if test="${exam.type==1}">
												组卷模考
											</c:if>
											<c:if test="${exam.type==2}">
												真题模考
											</c:if>
											<c:if test="${exam.type==3}">
												考前冲刺
											</c:if>
										</td>
										<td>
											<fmt:formatNumber value="${exam.replyTime }" pattern="0"/>
										</td>
										<td>
											${exam.addTime }
										</td>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${result.entity.paperList.size()==0||result.entity.paperList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有员工信息！</span>
											</div>
										</td>
									</tr>
								</c:if>
								<tr class="">
									<td colspan="9"><a href="javascript:selectQstList();" title="确定" class="btn smallbtn btn-y">确定</a> <a href="javascript:quxiao();" title="取消" class="btn smallbtn btn-y">取消</a></td>
								</tr>
							</tbody>
						</table>
						<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
						<!-- /pageBar end -->
					</div>
					<!-- /commonWrap -->
				</div>
			<!-- 内容 结束 -->
		</form>
</body>
</html>
