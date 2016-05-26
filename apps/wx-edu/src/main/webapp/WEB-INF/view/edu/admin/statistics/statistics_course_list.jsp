<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
	//清空
	function clean(){
		$("#id").val("");
		$("#name").val("");
		$("#isavaliable").val(-1);
		$("#sellType").val("");
		$("#searchForm select").each(function(){
			var _this = $(this);
			_this.find('option').eq(0).attr('selected', true);
		});
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>课程列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/statistics/course/list" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${queryCourse.id}" id="id">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryCourse.name" value="${queryCourse.name}" id="name">
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					销售形式
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryCourse.sellType" id="sellType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="" <c:if test="${empty queryCourse.sellType}" >selected</c:if>>--请选择--</option>
						<option value="COURSE" <c:if test="${queryCourse.sellType=='COURSE'}" >selected</c:if>>课程</option>
						<option value="PACKAGE" <c:if test="${queryCourse.sellType=='PACKAGE'}" >selected</c:if>>套餐</option>
						<option value="LIVE" <c:if test="${queryCourse.sellType=='LIVE'}" >selected</c:if>>直播</option>
					</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					状态
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryCourse.isavaliable" id="isavaliable" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="-1" <c:if test="${queryCourse.isavaliable=='-1'}" >selected</c:if>>请选择</option>
						<option value="0" <c:if test="${queryCourse.isavaliable=='0'}" >selected</c:if>>上架</option>
						<option value="1" <c:if test="${queryCourse.isavaliable=='1'}" >selected</c:if>>下架</option>
					</select>
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					&nbsp;
				</div>
				<div class="am-u-sm-8">
					<button type="submit" class="am-btn am-btn-warning">
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
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
					<tr>
						<th class="table-title" width="5%">ID</th>
						<th class="table-title" width="20%">课程名称</th>
						<th class="table-title" width="8%">售卖形式</th>
						<th class="table-title" width="8%">上下架</th>
						<th class="table-title" width="8%">价格</th>
						<th class="table-title" width="8%">课程节数</th>

						<th class="table-title" width="7%">观看次数</th>
						<th class="table-title" width="7%">购买人数</th>
						<th class="table-title" width="8%">观看人数</th>
						<th class="table-title" width="11%">观看时长(单位：分)</th>
						<th class="table-set" width="10%">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${not empty courseList}">
						<c:forEach items="${courseList}" var="cou">
							<tr id="rem${cou.id }">
								<td>${cou.id}</td>
								<td><a target="_blank" href="${ctx}/front/couinfo/${cou.id }">${cou.name }</a></td>
								<td>
									<c:if test="${cou.sellType=='COURSE'}">课程</c:if>
									<c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
									<c:if test="${cou.sellType=='LIVE'}">直播</c:if>
								</td>
								<td>
									<c:if test="${cou.isavaliable==0}">上架</c:if>
									<c:if test="${cou.isavaliable==1}">下架</c:if>
								</td>
								<td>${cou.currentprice}</td>
								<td>${cou.lessionnum}</td>
								<td>${cou.playcount}</td>
								<td>${cou.buycount}</td>
								<td>${cou.watchpersoncount}</td>
								<td>${cou.playTime}</td>
								<td>
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="${ctx}/admin/statistics/courseKpoint/list?courseKpoint.courseId=${cou.id}"><span class="am-icon-info-circle"></span> 课程小节</a>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty courseList}">
						<tr>
							<td colspan="11">
								<div data-am-alert=""
									 class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
												 style="font-size: 34px;"></i> <span class="vam ml10">还没有相关数据！</span></big>
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
</form>
</body>
</html>
