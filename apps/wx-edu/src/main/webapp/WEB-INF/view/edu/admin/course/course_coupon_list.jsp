<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
	//存放数据的数组   
	var myArrayMoveStock = new Array();
	//将小页面被选中的入库明细信息带到大页面   
	function selectList() {
		var courseChecked = document.getElementsByName("ids");
		// 定义是否有产品被选中   
		var notSelect = true;
		var overdue = false;
		var courseNames = "";
		// 把被选中的入库明细传入数组   
		myArrayMoveStock = new Array();
		for (var i = 0; i < courseChecked.length; i++) {
			if (courseChecked[i].checked == true) {
				var records = courseChecked[i].value;
				var instockmsg = new Array();
				instockmsg = records.split("#");//以#分开获得数组   
				var courseId = instockmsg[0];
				var courseName = instockmsg[1];
				var minutes = instockmsg[2];
				$.ajax({
					url : "${ctx}/admin/cou/courseOverdue",
					data : {
						"courseId" : courseId
					},
					type : "post",
					dataType : "json",
					async : false,
					success : function(result) {
						if (result.message == 'overdue') {
							courseChecked[i].checked = false;
							overdue = true;
							courseNames += courseName + ",";
						}
					}
				});

				if (courseChecked[i].checked == true) {
					toParentsValue(courseId, courseName, minutes);
					notSelect = false;
				}

			}
		}

		if (overdue) {
			courseNames = courseNames.substring(0, courseNames.length - 1);
			dialogFun("课程列表","您选择的课程'" + courseNames + "'已过期，请重新选择",0)
			return;
		}
		//没有入库明细被选择 
		if (notSelect) {
			dialogFun("课程列表","请选择课程",0);
			return;
		}

		//调用父页面的方法  
		window.opener.getCourseList(myArrayMoveStock);
		window.close();
	}
	// 把选中产品的一条记录放到数组中   
	function toParentsValue(courseId, courseName, minutes) {
		myArrayMoveStock.push([ courseId, courseName, minutes ]);
	}
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}
	function clean(){
		$("#queryCourseId,#queryCourseName").val("");
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">知识体系</strong> / <small>知识体系列表</small></div>
</div>
<hr>
	<div class="mt20 am-padding admin-content-list">
	<form action="${ctx}/admin/cou/couponCourseList" name="searchForm" id="searchForm" method="post" class="am-form">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="queryCourseId">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryCourse.name" value="${course.name}" id="queryCourseName">
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
		</form>
	</div>
<div class="mt20">
	<div class="am-g">
		<div class="am-u-md-6"></div>
		<div class="am-u-sm-12 am-u-md-4">
			<div class="am-input-group am-input-group-sm">
				<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
				<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
			</div>
		</div>
	</div>
</div>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th width="20%">
							<label class="am-checkbox">
								<input name="checkbox" type="checkbox" data-am-ucheck onclick="allCheck(this)"/>全选
							</label>
						</th>
						<th width="20%">课程ID</th>
						<th width="20%">课程名称</th>
						<th width="20%">价格</th>
						<th width="20%">添加时间</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${courseList.size()>0}">
					<c:forEach items="${courseList}" var="cou">
						<tr>
							<td>
								<label class="am-checkbox">
									<input type="checkbox" name="ids" data-am-ucheck value="${cou.id}#${cou.name}#${cou.minutes}"/>
								</label>
							</td>
							<td>${cou.id}</td>
							<td>${cou.name }</td>
							<td>${cou.currentprice}</td>
							<td><fmt:formatDate type="both" value="${cou.addtime }" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${courseList.size()==0||courseList==null}">
					<tr>
						<td align="center" colspan="16">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有课程数据！</span></big>
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
		<div class="am-g am-margin-top am-u-sm-12">
			<a href="javascript:selectList();" title="确定" class="am-btn am-btn-success">确定</a>
			<a href="javascript:close();" title="取消" class="am-btn am-btn-danger">取消</a>
		</div>
	</div>
</div>
</body>
</html>
