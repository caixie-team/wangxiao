<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程套餐列表</title>
	<script type="text/javascript">
		// 修改排序
		function updateOrder(id){
			var orderNum = $("#orderNum_"+id).val();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/cou/updateOrderNum",
				data:{"course.id":id,"course.orderNum":orderNum},
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}
				}
			})
		}
		//清空
		function clean(){
			$("#courseId,#courseName").val("");
		}

		//删除课程
		function delCourse(id){
			dialogFun("套餐课程","是否删除课程",2,"javascript:confirmDelete("+id+")");
		}

		function confirmDelete(id){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/cou/delPack/"+id+"/${courseId}",
				cache:true,
				success:function(result){
					if(result.success){
						location.reload() ;
					}
				}
			});
		}
		//选择课程
		function showNewWin(){
			window.open('${ctx}/admin/cou/select','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
		}
		function addnewArray(array){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/cou/addPack",
				data:{"courseId":'${courseId}',"ids":array.toString()},
				cache:true,
				success:function(result){
					if(result.success){
						location.reload() ;
					}
				}
			});
		}


	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程列表</strong> / <small>课程套餐列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/cou/pack/${course.id}" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<input type="hidden" name="course.id" value="${course.id}"/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-5">
				<div class="am-u-sm-4 am-text-right">
					课程名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="course.name" value="${course.name}" id="courseName">
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
						<button class="am-btn am-btn-success" onclick="showNewWin()" type="button" title="添加课程"><span class="am-icon-plus"></span> 添加课程</button>
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
<div class="am-g">
	<div class="mt20">
		<table class="am-table am-table-striped am-table-hover table-main">
			<thead>
				<tr>
					<th>课程ID</th>
					<th>课程名称</th>
					<th>价格</th>
					<th>排序值</th>
					<th>添加时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty courseList}">
					<c:forEach items="${courseList}" var="course">
						<tr>
							<td>${course.id}</td>
							<td>${course.name }</td>
							<td>${course.currentprice}</td>
							<td>
								<form class="am-form">
								<input type="number" name="course.orderNum" value="${course.orderNum}" class="am-input-sm" id="orderNum_${course.id}" />
								</form>
							</td>
							<td><fmt:formatDate type="both" value="${course.addtime }" /></td>
							<td>
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="updateOrder(${course.id})"><span class="am-icon-pencil-square-o"></span> 修改</button>
										<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="delCourse(${course.id})"><span class="am-icon-trash-o"></span> 删除</button>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty courseList}">
					<tr>
						<td colspan="8">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big>
										<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
										<span class="vam ml10">还没有相关数据！</span>
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
</body>
</html>
