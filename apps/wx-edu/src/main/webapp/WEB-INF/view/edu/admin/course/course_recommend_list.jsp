<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
	function addSubmit() {
		var recommendId = $("#recommendId").val();
		if (recommendId < 0) {
			dialogFun("课程列表","请选择推荐类型",0);
			return;
		}
		var imgIds = document.getElementsByName("ids");
		var num = 0;
		var ids = '';
		for (var i = 0; i < imgIds.length; i++) {
			if (imgIds[i].checked == true) {
				num++;
				ids += imgIds[i].value + ",";
			}
		}
		if (num == 0) {
			dialogFun("课程列表","请选择要推荐的课程",0);
			return;
		}
		$.ajax({
			url : "${ctx}/admin/website/addCourseDetail",
			data : {
				"ids" : ids,
				"recommendId" : recommendId
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.message == 'true') {
					dialogFun("课程列表","推荐成功",1);
					window.opener.addCourseReload();
					window.close();
				} else if (result.message == 'than') {
					var recommendName = $("#recommendId").find(
							"option:selected").text();
					dialogFun("课程列表",recommendName + "模块只能添加" + result.entity.courseNum
							+ "个课程",0);
				} else {
					dialogFun("课程列表","操作失败",0);
					window.close();
				}
			}
		});

	}
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">选取课程</strong> / <small>课程列表</small></div>
</div>
<hr>
<form action="${ctx}/admin/cou/recommendCourseList" name="searchForm" id="searchForm" method="post" class="am-form">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5 optionList">
				<div class="am-u-sm-4 am-text-right">
					销售形式
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryCourse.sellType" id="sellType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">--请选择--</option>
						<option value="COURSE" <c:if test="${queryCourse.sellType=='COURSE' }">selected="selected"</c:if>>课程</option>
						<option value="PACKAGE" <c:if test="${queryCourse.sellType=='PACKAGE' }">selected="selected"</c:if>>套餐</option>
						<option value="LIVE" <c:if test="${queryCourse.sellType=='LIVE' }">selected="selected"</c:if>>直播</option>
					</select>
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-5 optionList">
				<div class="am-u-sm-4 am-text-right">
					推荐类型
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="recommendId" id="recommendId" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="-1">--请选择--</option>
						<c:forEach items="${websiteCourses }" var="websiteCourse">
							<option value="${websiteCourse.id}">${websiteCourse.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8 am-u-end">
					<button type="button" onclick="goPage(1)" class="am-btn am-btn-danger">查询</button>
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
					<th>
						<label class="am-checkbox">
							<input type="checkbox" onclick="allCheck(this)" data-am-ucheck> 全选
						</label>
					<th>ID</th>
					<th>课程名称</th>
					<th>价格</th>
					<th>添加时间</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty courseList }">
					<c:forEach items="${courseList}" var="cou">
						<tr>
							<td>
								<label class="am-checkbox">
									<input type="checkbox" name="ids" value="${cou.id}" data-am-ucheck>
								</label>
							</td>
							<td>${cou.id}</td>
							<td>${cou.name }</td>
							<td>${cou.currentprice}</td>
							<td><fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>

				</c:if>
				<c:if test="${empty courseList }">
					<tr>
						<td colspan="5">
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
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
</div>
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top am-u-sm-4">
			&nbsp;
		</div>
		<div class="am-g am-margin-top am-u-sm-8">
			<button type="button" class="am-btn am-btn-danger" title="提 交" onclick="addSubmit()">确定</button>
			<button type="button" class="am-btn am-btn-danger" title="返 回" onclick="window.close();">取消</button>
		</div>
		<div class="mt20 clear"></div>
	</div>
</div>
</body>
</html>
