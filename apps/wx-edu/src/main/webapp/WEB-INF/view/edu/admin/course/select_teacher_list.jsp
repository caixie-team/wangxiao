<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>老师列表</title>
<script type="text/javascript">
	function cancel() {
		window.close();
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
			dialogFun("老师列表","请选择教师",0);
			return;
		}
		qstChecked.each(function() {
			toParentsValue($(this).val());
		});
		opener.addnewTeacherId(myArrayMoveStock);
		cancel();
	}
	// 把选中试题一条记录放到数组中
	function toParentsValue(obj) {
		myArrayMoveStock.push(obj);
	}

	function clean(){
		$("#teacherName").val("");
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">讲师管理</strong> / <small>讲师列表</small></div>
</div>
<hr>
<form action="${ctx}/admin/teacher/selectlist" name="searchForm" id="searchForm" method="post" class="am-form">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					讲师名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="teacher.name" value="${knowledge.id}" id="teacherName">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8 am-u-end">
					<button type="button" class="am-btn am-btn-danger" onclick="goPage(1)">查询</button>
					<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
</form>

<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th width="6%"><span>Id</span></th>
						<th width="10%"><span>讲师名称</span></th>
						<th width="10%"><span>讲师头衔</span></th>
						<th width="20%"><span>资历</span></th>
						<th width="30%"><span>讲师简介</span></th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${teacherList.size()>0}">
					<c:forEach items="${teacherList}" var="tc">
						<tr>
							<td>
								<label class="am-checkbox-inline">
									<input data-am-ucheck type="${teacher.checkboxFalg=='radio'?'radio':'checkbox' }" id="${tc.id }" name="checkbox" value="${tc.id }" class="questionIds" /> ${tc.id }
								</label>
							</td>
							<td>${tc.name }</td>
							<td><c:if test="${tc.isStar==0 }">
								高级讲师
							</c:if> <c:if test="${tc.isStar==1 }">
								首席讲师
							</c:if></td>
							<td>${tc.education }</td>
							<td>${fn:substring(tc.career,0,30)}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${teacherList.size()==0||teacherList==null}">
					<tr>
						<td colspan="8">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有讲师信息！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top am-u-sm-4">&nbsp;</div>
		<div class="am-g am-margin-top am-u-sm-8">
			<button onclick="selectQstList()" class="am-btn am-btn-danger">确定</button>
			<button onclick="cancel()"class="am-btn am-btn-danger">取消</button>
		</div>
		<div class="mt20 clear"></div>
	</div>
</div>
</body>
</html>
