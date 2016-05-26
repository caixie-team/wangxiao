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
		alert("请选择试卷");
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
	<form class="am-form" action="${ctx}/admin/arrange/getArrangeExamList" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						试卷ID
					</div>
					<div class="am-u-sm-8">
						<input type="text" class="am-input-sm" name="examId" value="${map.id }" id="examId" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6">
					<div class="am-u-sm-4 am-text-right">
						试卷名称
					</div>
					<div class="am-u-sm-8">
						<input type="text" class="am-input-sm" name="examName" value="${map.examName }" id="examName" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-2">
					<div class="am-u-sm-6 am-u-end  am-text-center">
						<button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">
							<span class="am-icon-search"></span> 搜索
						</button>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</div>
	</form>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
					<tr>
						<th width="5%"><span>ID</span></th>
						<th width="10%"><span>试卷名</span></th>
						<th width="10%"><span>科目</span></th>
						<th width="15%"><span>考试描述</span></th>
						<th width="8%"><span>试卷难度</span></th>
						<th width="8%"><span>试卷总分</span></th>
						<th width="10%"><span>类型</span></th>
						<th width="12%"><span>答题时间</span></th>
						<th width="12%"><span>创建时间</span></th>
					</tr>
				</thead>
				<tbody  align="center">
					<c:if test="${result.entity.paperList.size()>0}">
						<c:forEach items="${result.entity.paperList}" var="exam">
							<tr id="rem${exam.id }">
								<td>
									<label class="am-radio">
										<input type="radio" data-am-ucheck id="<fmt:formatNumber value="${exam.id }" pattern="0"/>" name="checkbox" value="<fmt:formatNumber value="${exam.id }" pattern="0"/>" class="questionIds" />
										<fmt:formatNumber value="${exam.id }" pattern="0"/>
									</label>
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
									<c:if test="${exam.level==1}">简单</c:if>
									<c:if test="${exam.level==2}">中等</c:if>
									<c:if test="${exam.level==3}">困难</c:if>
								</td>
								<td>
									<fmt:formatNumber value="${exam.score }" pattern="0"/>
								</td>
								<td>
									<c:if test="${exam.type==0}">专项智能练习</c:if>
									<c:if test="${exam.type==1}">组卷模考</c:if>
									<c:if test="${exam.type==2}">真题模考</c:if>
									<c:if test="${exam.type==3}">考前冲刺</c:if>
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
					<c:if test="${empty result.entity.paperList }">
						<tr>
							<td colspan="16">
								<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam" style="font-size: 34px;"></i> <span class="vam ml10">还没有试卷信息！</span></big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</div>
	<div class="mt20">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-12">
				<div class="am-u-sm-12 am-text-center">
					<a href="javascript:selectQstList();" title="确定" class="am-btn am-btn-success">确定</a>
					<a href="javascript:quxiao();" title="取消" class="am-btn am-btn-danger">取消</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
