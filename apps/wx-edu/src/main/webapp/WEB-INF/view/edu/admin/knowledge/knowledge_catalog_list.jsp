<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>知识体系目录列表</title>
<script type="text/javascript">
	$(function() {
		$('.confirm-delete').on('click', function() {
			$('#my-confirm').modal({
				relatedTarget: this,
				onConfirm: function(options) {
					var $link = $(this.relatedTarget);
					$.ajax({
						url:'${ctx}/admin/knowledge/deleteCatalog',
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
		window.location.href="${ctx}/admin/knowledge/getKnowledgeCatalogList?knowledgeId="+$("#knowledgeId").val();
	}

	function goBackKnowledge(){
		window.location.href="${ctx}/admin/knowledge/getKnowledgeList";
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">知识体系目录</strong> / <small>目录列表</small></div>
</div>
<hr>
<form class="am-form">
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" readonly value="${knowledge.id}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="knowledge.name" readonly value="${knowledge.name}" />
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
						<button class="am-btn am-btn-success" onclick="window.location.href='${ctx}/admin/knowledge/toAddKnowledgeCatalog/${knowledge.id}'" type="button" title="新增"><span class="am-icon-plus"></span> 新增</button>
					</div>
				</div>
			</div>
			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
					<span class="am-input-group-btn">
						<button type="button" class="am-btn am-btn-success" onclick="goBackKnowledge()">返回</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</form>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal">
			<form class="am-form" action="${ctx}/admin/knowledge/getKnowledgeCatalogLis" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<input type="hidden" name="knowledgeCatalog.knowledgeId" value="${knowledge.id}" id="knowledgeId"/>
			</form>
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
				<tr>
					<th class="table-title" width="5%">序号</th>
					<th class="table-title" width="5%">ID</th>
					<th class="table-title" width="10%">目录名称</th>
					<th class="table-title" width="20%">简介</th>
					<%--<th class="table-title" width="30%">图片路径</th>--%>
					<th class="table-title" width="10%">创建时间</th>
					<th class="table-title" width="5%">排序</th>
					<th class="table-set" width="15%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty knowledgeCatalogList}">
					<c:forEach var="knowledgeCatalog" items="${knowledgeCatalogList}" varStatus="index">
						<tr>
							<td>${index.index+1}</td>
							<td>${knowledgeCatalog.id}</td>
							<td>${knowledgeCatalog.name}</td>
							<td>${knowledgeCatalog.description}</td>
							<%--<td>${knowledgeCatalog.picture}</td>--%>
							<td><fmt:formatDate value="${knowledgeCatalog.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/></td>
							<td>${knowledgeCatalog.sort}</td>
							<td>
								<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/knowledge/toUpdateKnowledgeCatalog/${knowledgeCatalog.id}'"><span class="am-icon-pencil-square-o"></span> 修改</button>
								<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only confirm-delete" data-id="${knowledgeCatalog.id}"><span class="am-icon-trash-o"></span> 删除</button>
								<button class="am-btn am-btn-default am-btn-xs am-hide-sm-only" onclick="window.location.href='${ctx}/admin/knowledge/getKnowledgeCatalogCourseList?catalogId=${knowledgeCatalog.id}'"><span class="am-icon-list"></span> 课程列表</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty knowledgeCatalogList}">
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
		<div class="am-modal-hd">知识体系目录</div>
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
		<div class="am-modal-hd">知识体系目录</div>
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
