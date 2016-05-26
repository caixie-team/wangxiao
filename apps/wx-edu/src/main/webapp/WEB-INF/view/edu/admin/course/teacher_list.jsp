<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>老师列表</title>
<script type="text/javascript">
$(function(){//初始化数据
	if('${teacher.isStar}'==null||'${teacher.isStar}'==''){
		$("#isStar").val(-1);	
	}else{
		$("#isStar").val('${teacher.isStar}');	
	}
	$("#tcName").val('${teacher.name}');
})
function remError(id){
	dialogFun("讲师列表","确定删除该讲师?",2,"${ctx}/admin/teacher/delete/"+id);
}
function submitSearch(){//搜索
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	function tcClean(){//清空
		$("#tcName").val("");
		$("#isStar").val(-1);
		$("#isStar").find('option').eq(0).attr('selected',true);
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">讲师管理</strong> / <small>讲师列表</small></div>
</div>
<hr>
<form action="${ctx}/admin/teacher/list" name="searchForm" id="searchForm" method="post" class="am-form">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					讲师名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="teacher.name" id="tcName">
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					讲师头衔
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="teacher.isStar" id="isStar" data-am-selected="{btnSize: 'sm'}" style="display: none;">
						<option value="-1">--请选择--</option>
						<option value="0">高级讲师</option>
						<option value="1">首席讲师</option>
					</select>
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
						<button class="am-btn am-btn-success" onclick="window.location.href='${ctx}/admin/teacher/toAdd'" type="button" title="新建讲师"><span class="am-icon-plus"></span> 新建讲师</button>
					</div>
				</div>
			</div>

			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
					<span class="am-input-group-btn">
						<button type="button" onclick="submitSearch()" class="am-btn am-btn-warning">
							<span class="am-icon-search"></span> 搜索
						</button>
						<button type="button" class="am-btn am-btn-danger" onclick="tcClean()">
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
						<th width="8%"><span>ID</span></th>
						<th width="8%"><span>名称</span></th>
						<th width="8%"><span>头衔</span></th>
						<th><span>资历</span></th>
						<th><span>简介</span></th>
						<th><span>添加时间</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${teacherList.size()>0}">
					<c:forEach  items="${teacherList}" var="tc" >
						<tr id="rem${tc.id }">
							<td>${tc.id }</td>
							<td>${tc.name }</td>
							<td>
								<c:if test="${tc.isStar==0 }">高级讲师</c:if>
								<c:if test="${tc.isStar==1 }">首席讲师</c:if>
							</td>
							<td>${tc.education }</td>
							<td>${fn:substring(tc.career,0,30)}</td>
							<td><fmt:formatDate type="both" value="${tc.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<button class="am-btn am-btn-primary" title="修改" onclick="window.location.href='${ctx}/admin/teacher/toUpdate/${tc.id}'">修改</button>
								<button class="am-btn am-btn-danger" title="删除" onclick="remError(${tc.id})">删除</button>
								<button class="am-btn am-btn-success" title="所讲课程" onclick="window.location.href='${ctx}/admin/teacher/selectCourseList?teacherId=${tc.id}'">所讲课程</button>
							</td>
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
</body>
</html>
