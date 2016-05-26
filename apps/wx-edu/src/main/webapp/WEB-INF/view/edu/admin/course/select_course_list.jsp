<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
	function quxiao() {
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
			alert("请选择课程");
			return;
		}
		qstChecked.each(function() {
			toParentsValue($(this).val());
		});
		opener.addnewArray(myArrayMoveStock);
		quxiao();
	}
	// 把选中试题一条记录放到数组中
	function toParentsValue(obj) {
		myArrayMoveStock.push(obj);
	}
	function clean(){
		$("#id,#name").val("");
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程管理</strong> / <small>选择课程</small></div>
	</div>
	<hr>
	<form class="am-form" action="${ctx}/admin/cou/select" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						ID
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="number" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id">
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						名称
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryCourse.name" value="${course.name}" id="name">
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
						<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
							<span class="am-icon-search"></span> 搜索
						</button>
						<button type="button" class="am-btn am-btn-danger" onclick="clean()">
							清空
						</button>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</div>
	</form>
	<div class="am-g">
		<div class="mt20">
			<form class="am-form">
				<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
						<tr>
							<th class="table-check">
								<input type="checkbox">
							</th>
							<th class="table-id">ID</th>
							<th class="table-title">课程名称</th>
							<th class="table-type">价格</th>
							<th class="table-date am-hide-sm-only">添加时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty courseList }">
							<c:forEach items="${courseList}" var="course">
								<tr>
									<td><input type="checkbox" id="${course.id }" name="checkbox" value="${course.id }" class="questionIds" /></td>
									<td>${course.id}</td>
									<td>${course.name }</td>
									<td>${course.currentprice}</td>
									<td class="am-hide-sm-only"><fmt:formatDate type="both" value="${course.addtime }" pattern="yyyy-MM-dd HH:mm"/></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty courseList }">
							<tr>
								<td colspan="8">
									<div data-am-alert=""
										 class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big>
												<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
												<span class="vam ml10">还没有相关数据！</span>
											</big>
										</center>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			</form>
		</div>
	</div>

	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-8">
				<button type="button" onclick="selectQstList()" title="确定" class="am-btn am-btn-danger">确定</button>
				<button type="button" onclick="quxiao()" title="取消" class="am-btn am-btn-warning">取消</button>
			</div>
		</div>
	</div>
</body>
</html>
