<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>课程笔记列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
	<script type="text/javascript">
		function updateIsavalible(id,isavalible){
			var str = "";
			if(isavalible==1){str= "是否隐藏该条信息";}
			if(isavalible==0){str= "是否显示该条信息";}
			dialogFun("课程笔记列表",str,2,"javascript:updateIsavalibleAjax("+id+","+isavalible+")");
		}
		function updateIsavalibleAjax(id,isavalible){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/courseNote/update",
				data:{"courseNote.id":id,"courseNote.status":isavalible},
				async:false,
				success:function(result){
					if(result.success){
						if(isavalible==1){
							$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",0)");
							$(".attr"+id).html("<span class='am-icon-pencil-square-o'></span>显示");
							$(".attr"+id).attr("title","<span class='am-icon-pencil-square-o'></span>显示");
							$("#isavalible"+id).html("隐藏");
							dialogFun("课程笔记列表","隐藏成功",5,"");
							closeFun();
						}else{
							$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",1)");
							$(".attr"+id).html("<span class='am-icon-pencil-square-o'></span>隐藏");
							$(".attr"+id).attr("title","<span class='am-icon-pencil-square-o'></span>隐藏");
							$("#isavalible"+id).html("显示");
							dialogFun("课程笔记列表","显示成功",5,"");
							closeFun();
						}

					}
				}
			});
		}
		function clean(){
			$("#nickname,#pointName").val("");
			$("input[name='queryCourseNote.startDate']:first").attr("value", "");
			$("input[name='queryCourseNote.endDate']:first").attr("value", "");
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">课程笔记管理</strong> / <small>课程笔记列表</small>
		</div>
	</div>
	<hr/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/courseNote/list" name="searchForm" id="searchForm" method="post" class="am-form">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<div class="am-g am-margin-top am-u-sm-6">
					<div class="am-u-sm-4 am-text-right">用户名/邮箱</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryCourseNote.keyword" value="${queryCourseNote.keyword}" id="nickname" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">视频名</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryCourseNote.pointName" value="${queryCourseNote.pointName}" id="pointName" />
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">开始日期</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm"  readonly="readonly" data-am-datepicker="{format: 'yyyy-mm-dd'}" name="queryCourseNote.startDate" value="${queryCourseNote.startDate}"  id="startDate" class="AS-inp"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">结束日期</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" readonly="readonly" data-am-datepicker="{format: 'yyyy-mm-dd'}" name="queryCourseNote.endDate" value="${queryCourseNote.endDate}"  id="endDate" class="AS-inp"/>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</form>
		</div>
	</div>
	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6"></div>
			<div class="am-u-sm-12 am-u-md-4">
				<div class="am-input-group am-input-group-sm">
					<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
						<span class="am-icon-search"></span> 查询
					</button>
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
							<th width="6%"><span>&nbsp;ID</span></th>
							<th><span>用户名</span></th>
							<th><span>学员邮箱</span></th>
							<th><span>课程名称</span></th>
							<th><span>章节名称</span></th>
							<th><span>笔记内容</span></th>
							<th><span>笔记时间</span></th>
							<th><span>状态</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody align="center">
						<c:if test="${not empty queryCourseNoteList }">
							<c:forEach items="${queryCourseNoteList}" var="cou" >
								<tr>
									<td>${cou.id }</td>
									<td>${cou.nickname }</td>
									<td>${cou.email }</td>
									<td>${cou.courseName }</td>
									<td>${cou.pointName }</td>
									<td>${cou.shortContent }</td>
									<td><fmt:formatDate value="${cou.updateTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td id="isavalible${cou.id}">
									<c:if test="${cou.status==0 }">显示</c:if>
									<c:if test="${cou.status==1 }">隐藏</c:if>
									</td>
									<td align="center">
									<c:if test="${cou.status==0}">
										<a class="am-btn am-btn-default am-btn-xs am-text-secondary attr${cou.id}" title="隐藏" href="javascript:updateIsavalible(${cou.id},1)"><span class="am-icon-pencil-square-o"></span>隐藏</a>
									 </c:if>
									  <c:if test="${cou.status==1 }">
										<a class="am-btn am-btn-default am-btn-xs am-text-secondary attr${cou.id}" title="显示" href="javascript:updateIsavalible(${cou.id},0)"><span class="am-icon-pencil-square-o"></span>显示</a>
									 </c:if>
									<a class="am-btn am-btn-default am-btn-xs am-text-secondary" title="查看" href="${ctx}/admin/courseNote/info/${cou.id}"><span class="am-icon-copy"></span>查看</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty queryCourseNoteList }">
							<tr>
								<td align="center" colspan="16">
									<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
									<center>
									<big>
										<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
											<span class="vam ml10">还没有课程笔记信息！</span>
									</big>
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
