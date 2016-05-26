<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>推荐课程列表</title>
<script type="text/javascript">
function clean(){
	$("#appWebsiteCourseDetailName").val("");
	$("#recommemd").val(-1);
	$("#recommemd").find('option').eq(0).attr('selected', true);
	$("#isavaliable").val(-1);
	$("#isavaliable").find('option').eq(0).attr('selected', true);
}
function addCourseReload(){
	window.location.reload();
}
function del(id){
	dialogFun("推荐课程列表","确定删除该推荐吗？",2,"javascript:_del("+id+")");
}
function _del(id){
	$.ajax({
		url:"${cxt}/admin/appWebsite/delWebsiteCourseDetailById/"+id,
		type:"post",
		dataType:"json",
		cache:true,
		async:false,
		success:function(result){
			if(result.success){
				setTimeout(function(){
					dialogFun("推荐课程列表","删除成功",5,"");
				},300);
			}
		}

	});
}
function choose(){
	window.open('${cxt}/admin/appWebsite/selectCourse?page.currentPage=1',
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
	<form action="${ctx}/admin/appWebsite/websiteCourseDetailPage" name="searchForm" id="searchForm" method="post" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					课程名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm"  name="appWebsiteCourseDetailDTO.courseName" id="appWebsiteCourseDetailName" value="${appWebsiteCourseDetailDTO.courseName}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					是否下架
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="appWebsiteCourseDetailDTO.isavaliable" id="isavaliable" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="-1">--请选择--</option>
						<option value="1" <c:if test="${ appWebsiteCourseDetailDTO.isavaliable==1}">selected="selected"</c:if>>未下架</option>
						<option value="2" <c:if test="${appWebsiteCourseDetailDTO.isavaliable==2}">selected="selected"</c:if>>已下架</option>
					</select>
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					分类
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="appWebsiteCourseDetailDTO.recommendId" id="recommemd" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="-1">--请选择--</option>
						<c:forEach items="${appWebsiteCourses }" var="appWebsiteCourse">
							<option value="${appWebsiteCourse.id}" <c:if test="${ appWebsiteCourseDetailDTO.recommendId==appWebsiteCourse.id}">selected="selected"</c:if>>${appWebsiteCourse.name}</option>
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
						<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/user/toBatchOpen'" title="批量新建用户"><span class="am-icon-plus"></span> 批量新建用户</button>
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
						<th width="10%">ID></th>
						<th width="18%">课程名称</th>
						<th width="18%">分类</th>
						<th width="18%">排序值</th>
						<th width="18%">课程状态</th>
						<th width="18%">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${appWebsiteCourseDetailDTOList.size()>0}">
						<c:forEach  items="${appWebsiteCourseDetailDTOList}" var="appWebsiteCourseDetail" >
							<tr>
								<td>${appWebsiteCourseDetail.id }</td>
								<td>${appWebsiteCourseDetail.courseName }</td>
								<td>${appWebsiteCourseDetail.recommendName }</td>
								<td>${appWebsiteCourseDetail.orderNum }</td>
								<td>${appWebsiteCourseDetail.isavaliable==0?'上架':'下架' }</td>
								<td>
									<a class="am-btn am-btn-primary" href="${cxt}/admin/appWebsite/doUpdateWebsiteCourseDetail/${appWebsiteCourseDetail.id}" >修改</a>
									<a class="am-btn am-btn-danger" href="javascript:del(${appWebsiteCourseDetail.id})" >删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${appWebsiteCourseDetailDTOList.size()==0||appWebsiteCourseDetailDTOList==null}">
						<tr>
							<td colspan="9">
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
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
		</div>
	</div>
</body>
</html>