<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
	function addSubmit() {

		var imgIds = document.getElementsByName("ids");
		var num = 0;
		var ids = '';
        var name="";
		for (var i = 0; i < imgIds.length; i++) {
			if (imgIds[i].checked == true) {
				num++;
				ids += imgIds[i].value ;
                name = $(imgIds[i]).attr("courseName") ;
			}
		}
		if (num == 0) {
			dialogFun("课程列表","请选择要推荐的课程",0);
			return;
		}
        window.opener.addCourse(ids,name);
        window.close();
	}
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}

	function clean(){
		$("#id,#name").val("");
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">选取课程</strong> / <small>课程列表</small></div>
	</div>
	<hr>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/appWebsite/CourseList" name="searchForm" id="searchForm" method="post" class="am-form">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						课程ID
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						课程名称
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryCourse.name" value="${course.name}" id="name" />
					</div>
				</div>
				<div class="mt20 clear"></div>

				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					&nbsp;
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="button" name="" value="查询" class="am-btn am-btn-danger" onclick="goPage(1)" />
						<input type="button" name="" value="清空 " class="am-btn am-btn-danger" onclick="clean()" />
					</div>
				</div>
				<div class="mt20 clear"></div>
			</form>
		</div>
	</div>
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal am-scrollable-vertical">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<th><span></span></th>
						<th><span>ID</span></th>
						<th><span>课程名称</span></th>
						<th><span>价格</span></th>
						<th><span>添加时间</span></th>
					</thead>
					<tbody>
					<c:if test="${courseList.size()>0}">
						<c:forEach items="${courseList}" var="cou">
							<tr>
								<td><input type="radio" name="ids" value="${cou.id}" courseName="${cou.name}"/></td>
								<td>${cou.id}</td>
								<td>${cou.name}</td>
								<td>${cou.currentprice}</td>
								<td><fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${courseList.size()==0||courseList==null}">
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
					<tr>
						<td align="center" colspan="5">
							<a class="am-btn am-btn-danger" title="提 交" href="javascript:addSubmit()">确定</a>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:window.close();">取消</a>
						</td>
					</tr>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			</div>
		</div>
	</div>
</body>
</html>
