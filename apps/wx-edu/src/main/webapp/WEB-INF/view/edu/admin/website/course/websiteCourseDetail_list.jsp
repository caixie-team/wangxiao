<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>推荐课程列表</title>
	<script type="text/javascript">
		function clean(){
			$("#websiteCourseDetailName").val("");
			$("#recommemd").val(-1);
			$("#recommemd").find('option').eq(0).attr('selected', true);
		}
		function addCourseReload(){
			goPage(1);
		}
		function del(id){
			dialogFun("推荐课程列表","确定删除该推荐吗？",2,"javascript:_del("+id+")");
		}
		function _del(id){
			$.ajax({
				url:"${cxt}/admin/website/delWebsiteCourseDetailById/"+id,
				type:"post",
				dataType:"json",
				cache:true,
				async:false,
				success:function(result){
					if(result.success){
						goPage(1);
					}
				}

			});
		}
		function choose(){
			window.open('${cxt}/admin/cou/recommendCourseList?page.currentPage=1',
					+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
			);
		}
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">推荐课程管理</strong> / <small>推荐课程列表</small></div>
</div>
<hr>
<form action="${ctx}/admin/website/websiteCourseDetailPage" name="searchForm" id="searchForm" method="post" class="am-form">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top am-u-sm-6 am-text-left">
			<div class="am-u-sm-4 am-text-right">
				课程名称
			</div>
			<div class="am-u-sm-8 am-u-end">
				<input type="text" class="am-input-sm"  name="websiteCourseDetailDTO.courseName" id="websiteCourseDetailName" value="${websiteCourseDetailDTO.courseName}" />
			</div>
		</div>
		<div class="am-g am-margin-top am-u-sm-6 am-text-left">
			<div class="am-u-sm-4 am-text-right">
				分类
			</div>
			<div class="am-u-sm-8 am-u-end">
				<select name="websiteCourseDetailDTO.recommendId" id="recommemd" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
					<option value="-1">--请选择--</option>
					<c:forEach items="${websiteCourses }" var="websiteCourse">
						<option value="${websiteCourse.id}" <c:if test="${ websiteCourseDetailDTO.recommendId==websiteCourse.id}">selected="selected"</c:if>>${websiteCourse.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="mt20 clear"></div>
	</div>
</form>
<div class="mt20">
	<div class="am-g">
		<div class="am-u-md-6">
			<div class="am-btn-toolbar">
				<div class="am-btn-group am-btn-group-xs">
					<button class="am-btn am-btn-success" type="button" onclick="choose()"><span class="am-icon-plus"></span> 新建推荐课程</button>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-4">
			<div class="am-input-group am-input-group-sm">
				<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
				<button type="button" class="am-btn am-btn-danger" onclick="clean()">清空</button>
			</div>
		</div>
	</div>
</div>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal am-scrollable-vertical">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
				<tr>
					<th width="10%">ID</th>
					<th width="50%">课程名称</th>
					<th width="15%">分类</th>
					<th width="10%">排序值</th>
					<th width="15%">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${not empty websiteCourseDetailDTOList}">
					<c:forEach  items="${websiteCourseDetailDTOList}" var="websiteCourseDetail" >
						<tr>
							<td>${websiteCourseDetail.id }</td>
							<td><a target="_blank" href="${ctx}/front/couinfo/${websiteCourseDetail.id }">${websiteCourseDetail.courseName }</a></td>
							<td>${websiteCourseDetail.recommendName }</td>
							<td>${websiteCourseDetail.orderNum }</td>
							<td  class="c_666 czBtn" align="center">
								<a class="am-btn am-btn-primary" href="${cxt}/admin/website/doUpdateWebsiteCourseDetail/${websiteCourseDetail.id}" >修改</a>
								<a class="am-btn am-btn-danger" href="javascript:del(${websiteCourseDetail.id})" >删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty websiteCourseDetailDTOList }">
					<tr>
						<td colspan="5">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有推荐课程！</span></big>
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