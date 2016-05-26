<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>安排考试</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
	<script type="text/javascript">
		$(function(){
		   $("#status").val("${arrange.status}");
		   $("#arrType").val("${arrange.type}");
		   $("#isRepeat").val("${arrange.isRepeat}");
		});
		//查询任务
		function submitSearch(){
			$("#searchForm").submit();
		}
		//清空查询条件
		function clearSearch(){
			$("#name").val("");
			$("#status").val("");
			$("#releasePeople").val("");
			$("#arrType").val("");
			$("#isRepeat").val("");
			//清空时间
			$("#startTime").val("");
			$("#endTime").val("");
			//清空select
			$('#searchForm select').each(function() {
				var _this = $(this);
				_this.find('option').eq(0).attr('selected', true);
			});
		}
		//考试详情跳转链接
		function arrangeDetails(id){
			window.location.href="${ctx}/admin/arrange/arrangeDetails?arrangeId="+id;
		}
		//任务修改跳转链接
		function updateArrange(id){
			window.location.href="${ctx}/admin/arrange/toUpdateArrange/"+id;
		}
		//作废任务
		function invalidArrange(id){
			dialogFun("安排考试","确定作废吗?",2,"${ctx}/admin/arrange/invalidArrange/"+id);
		}
		//启动任务
		function startArrange(id){
			dialogFun("安排考试","启动后不可修改，确定启动吗?",2,"${ctx}/admin/arrange/startArrange/"+id);
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">安排考试管理</strong> / <small>安排考试列表</small></div>
	</div>
	<hr/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/arrange/arrangeList" class="am-form" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">名称</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text"class="am-input-sm"  name="arrange.name" value="${arrange.name }" id="name" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">开始时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" readonly class="form-datetime-lang am-form-field am-input-sm" name="arrange.beginTime" value="<fmt:formatDate value="${arrange.beginTime }" pattern="yyyy-MM-dd" />"  id="startTime" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
					<div class="am-u-sm-4 am-text-right">结束时间</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" readonly class="form-datetime-lang am-form-field am-input-sm"  name="arrange.endTime"  value="<fmt:formatDate value="${arrange.endTime }" pattern="yyyy-MM-dd" />" id="endTime" />
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">状态</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="arrange.status" id="status" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="">请选择</option>
							<option value="NOTRELEASE">未发布</option>
							<option value="RELEASE">进行中</option>
							<option value="END">已结束</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">类型</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="arrange.type" id="arrType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="">请选择</option>
							<option value="0">个人</option>
							<option value="1">岗位</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">是否重复</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="arrange.isRepeat" id="isRepeat" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="">请选择</option>
							<option value="0">不可重复</option>
							<option value="1">可重复</option>
						</select>
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-8 am-text-left">
					<div class="am-u-sm-6 am-u-end  am-text-center">
						<button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">
							<span class="am-icon-search"></span> 搜索
						</button>
						<button type="button" class="am-btn am-btn-danger" onclick="clearSearch()">
							清空
						</button>
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
						<tr>
							<th>ID</th>
							<th>考试名称</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>发布时间</th>
							<th>状态</th>
							<th>是否重复</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${arrangeList.size()>0}">
							<c:forEach items="${arrangeList}" var="arrangeEntity">
								<tr>
									<td>
										${arrangeEntity.id }
									</td>
									<td>
										${arrangeEntity.name }
									</td>
									<td>
										<fmt:formatDate value="${arrangeEntity.beginTime }" pattern="yyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<fmt:formatDate value="${arrangeEntity.endTime }" pattern="yyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<fmt:formatDate value="${arrangeEntity.releaseTime }" pattern="yyy-MM-dd" />
									</td>
									<td>
										<c:if test="${arrangeEntity.status=='NOTRELEASE'}">
											未发布
										</c:if>
										<c:if test="${arrangeEntity.status=='RELEASE'}">
											进行中
										</c:if>
										<c:if test="${arrangeEntity.status=='END'}">
											已结束
										</c:if>
									</td>
									<td>
										<c:if test="${arrangeEntity.isRepeat==0}">
											不可重复
										</c:if>
										<c:if test="${arrangeEntity.isRepeat==1}">
											可重复
										</c:if>
									</td>
									<td>
										<c:if test="${arrangeEntity.type==0}">
											个人
										</c:if>
										<c:if test="${arrangeEntity.type==1}">
											岗位
										</c:if>
									</td>
									<td>
										<c:if test="${arrangeEntity.status=='NOTRELEASE'}">
											<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="startArrange(${arrangeEntity.id})"><span class="am-icon-pencil-square-o"></span> 发布</button>
											<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="updateArrange(${arrangeEntity.id})"><span class="am-icon-pencil-square-o"></span> 编辑</button>
											<button class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="invalidArrange(${arrangeEntity.id})"><span class="am-icon-trash-o"></span> 作废</button>
										</c:if>
										<c:if test="${arrangeEntity.status=='RELEASE'}">
											<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="arrangeDetails(${arrangeEntity.id})"><span class="am-icon-list"></span> 详情</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty arrangeList }">
							<tr>
								<td colspan="16">
									<div data-am-alert=""
										class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big> <i class="am-icon-frown-o vam"
												style="font-size: 34px;"></i> <span class="vam ml10">还没有安排考试信息！</span></big>
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
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
</body>
</html>
