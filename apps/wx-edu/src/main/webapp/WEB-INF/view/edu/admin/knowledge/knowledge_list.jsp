<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>知识体系列表</title>
<script type="text/javascript">
	function clean(){
		$("#knowledgeId,#knowledgeName").val("");
		$("#startTime,#endTime").attr("value","");
	}
	$(function() {
		$('.confirm-delete').on('click', function() {
			$('#my-confirm').modal({
				relatedTarget: this,
				onConfirm: function(options) {
					var $link = $(this.relatedTarget);
					$.ajax({
						url:'${ctx}/admin/knowledge/deleteKnowledge',
						type:'post',
						data:{"id":$link.data('id')},
						dataType:"json",
						success:function(result){
							if(result.success){
								$("#successBtn").click();
							}
						}
					});
				},
				// closeOnConfirm: false,
				onCancel: function() {

				}
			});
		});
	});

	function flush(){
		window.location.href="${ctx}/admin/knowledge/getKnowledgeList";
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">知识体系</strong> / <small>知识体系列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/knowledge/getKnowledgeList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="knowledge.id" value="${knowledge.id}" id="knowledgeId">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="knowledge.name" value="${knowledge.name}" id="knowledgeName">
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					开始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" data-am-datepicker readonly name="knowledge.startTime" value="${knowledge.startTime}" id="startTime">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" data-am-datepicker readonly name="knowledge.endTime" value="${knowledge.endTime}" id="endTime">
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</div>
	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6">
				<div class="am-btn-toolbar">
					<div class="am-btn-group am-btn-group-xs">
						<button class="am-btn am-btn-success" onclick="window.location.href='${ctx}/admin/knowledge/toAddKnowledge'" type="button" title="新增"><span class="am-icon-plus"></span> 新增</button>
					</div>
				</div>
			</div>

			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
					<span class="am-input-group-btn">
						<button type="submit" class="am-btn am-btn-warning">
							<span class="am-icon-search"></span> 搜索
						</button>
						<button type="button" class="am-btn am-btn-danger" onclick="clean()">
							清空
						</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</form>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
				<tr>
					<th class="table-title" width="12%">序号</th>
					<th class="table-title" width="12%">ID</th>
					<th class="table-title" width="12%">知识体系名称</th>
					<th class="table-title" width="12%">简介</th>
					<th class="table-title" width="12%">图片路径</th>
					<th class="table-title" width="12%">创建时间</th>
					<th class="table-title" width="12%">排序</th>
					<th class="table-title" width="12%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty knowledgeList}">
					<c:forEach var="knowledge" items="${knowledgeList}" varStatus="index">
						<tr>
							<td>${index.index+1}</td>
							<td>${knowledge.id}</td>
							<td>${knowledge.name}</td>
							<c:choose>
								<c:when test="${not empty knowledge.description and fn:length(knowledge.description)>20}">
									<td>${fn:substring(knowledge.description, 0, 20)}...</td>
								</c:when>
								<c:otherwise><td>${knowledge.description}</td></c:otherwise>
							</c:choose>
							<td>${knowledge.picture}</td>
							<td><fmt:formatDate value="${knowledge.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/></td>
							<td>${knowledge.sort}</td>
							<td>
								<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/knowledge/toUpdateKnowledge/${knowledge.id}'"><span class="am-icon-pencil-square-o"></span> 修改</button>
								<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only confirm-delete" data-id="${knowledge.id}"><span class="am-icon-trash-o"></span> 删除</button>
								<button class="am-btn am-btn-default am-btn-xs am-hide-sm-only" onclick="window.location.href='${ctx}/admin/knowledge/getKnowledgeCatalogList?knowledgeId=${knowledge.id}'"><span class="am-icon-list"></span> 目录</button>
							<%--<div data-am-dropdown="" class="am-dropdown">
									<a data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></a>
									<ul class="am-dropdown-content">
										<li><a href="${ctx}/admin/knowledge/getKnowledgeCatalogList?knowledgeId=${knowledge.id}">目录</a></li>
										<li><a href="${ctx}/admin/knowledge/toUpdateKnowledge/${knowledge.id}">修改</a></li>
										<li><a href="javascript:void(0)" class="confirm-delete" data-id="${knowledge.id}">删除</a></li>
									</ul>
								</div>--%>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty knowledgeList}">
					<tr>
						<td colspan="8">
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
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
	<div class="am-modal-dialog">
		<div class="am-modal-hd">知识体系</div>
		<div class="am-modal-bd">
			你，确定要删除这条记录吗？
		</div>
		<div class="am-modal-footer">
			<span class="am-modal-btn" data-am-modal-cancel>取消</span>
			<span class="am-modal-btn" data-am-modal-confirm>确定</span>
		</div>
	</div>
</div>
<input type="hidden" data-am-modal="{target: '#my-alert'}" id="successBtn"/>
<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
	<div class="am-modal-dialog">
		<div class="am-modal-hd">知识体系</div>
		<div class="am-modal-bd">
			删除成功
		</div>
		<div class="am-modal-footer">
			<span class="am-modal-btn" onclick="flush()">确定</span>
		</div>
	</div>
</div>
</body>
</html>
