<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>知识体系目录课程列表</title>
<script type="text/javascript">
	function addCourse(){
		window.open('${ctx}/admin/cou/openerlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	}
	function openerCourse(courseArray){
		if(courseArray[0]!=''&&courseArray[0]>0){
			$.ajax({
				url:'${ctx}/admin/knowledge/addCourse',
				type:"post",
				data:{"courseId":courseArray[0],"catalogId":$("#catalogId").val()},
				dataType:"json",
				success:function(result){
					if(result.success){
						dialogFun("提示","添加成功",5,window.location.href);
					}
				}
			});
		}
	}


	function deleteCourse(id){
		dialogFun("提示","是否确定要删除课程?",2,"javascript:delCourse("+id+")");
	}

	function delCourse(id){
		$.ajax({
			url:'${ctx}/admin/knowledge/deleteCourse',
			type:'post',
			data:{"ids":id},
			dataType:"json",
			success:function(result){
				if(result.success){
					flush();
				}
			}
		});
	}

	function flush(){
		window.location.href="${ctx}/admin/knowledge/getKnowledgeCatalogCourseList?catalogId="+$("#catalogId").val();
	}

	function goBackCatalog(){
		window.location.href="${ctx}/admin/knowledge/getKnowledgeCatalogList?knowledgeId="+$("#knowledgeId").val();
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">知识体系目录课程</strong> / <small>课程列表</small></div>
</div>
<hr>
<form class="am-form">
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					体系名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" readonly value="${knowledge.name}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					目录名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="knowledge.name" readonly value="${knowledgeCatalog.name}" />
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
						<button class="am-btn am-btn-success" onclick="addCourse()" type="button" title="添加课程"><span class="am-icon-plus"></span> 添加课程</button>
					</div>
				</div>
			</div>
			<div class="am-u-sm-12 am-u-md-3">
				<button type="button" class="am-btn am-btn-primary" onclick="goBackCatalog()">返回</button>
			</div>
		</div>
	</div>
</form>
<div class="am-g">
	<div class="mt20">
		<form class="am-form" action="" name="searchForm" id="searchForm" method="post">
			<input type="hidden" value="${knowledgeCatalog.id}" id="catalogId" />
			<input type="hidden" value="${knowledge.id}" id="knowledgeId" />
		</form>
		<table class="am-table am-table-striped am-table-hover table-main">
			<thead>
			<tr>
				<th class="table-title" width="5%">序号</th>
				<th class="table-title" width="5%">ID</th>
				<th class="table-title" width="30%">课程名称</th>
				<th class="table-title" width="25%">创建时间</th>
				<th class="table-title" width="10%">排序</th>
				<th class="table-title" width="25%">操作</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${not empty knowledgeCatalogCourseList}">
				<c:forEach var="knowledgeCatalogCourse" items="${knowledgeCatalogCourseList}" varStatus="index">
					<tr>
						<td>${index.index+1}</td>
						<td>${knowledgeCatalogCourse.id}</td>
						<td>${knowledgeCatalogCourse.courseName}</td>
						<td><fmt:formatDate value="${knowledgeCatalogCourse.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/></td>
						<td>${knowledgeCatalogCourse.sort}</td>
						<td>
							<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/knowledge/toUpdateKnowledgeCatalogMiddleCourse/${knowledgeCatalogCourse.id}'"><span class="am-icon-pencil-square-o"></span> 修改</button>
							<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteCourse(${knowledgeCatalogCourse.id})"><span class="am-icon-trash-o"></span> 删除</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty knowledgeCatalogCourseList}">
				<tr>
					<td colspan="6">
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
</div>
</body>
</html>
