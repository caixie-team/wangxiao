<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
<script type="text/javascript">
function clean(){
	$("#appWebsiteCourseDetailName").val("");
	$("#isavaliable").val(-1);
	$("#isavaliable").find('option').eq(0).attr('selected', true);
}
function addCourseReload(){
	window.location.reload();
}
function del(id){
	if(confirm("确定删除该课程吗？")){
		$.ajax({
			url:"${cxt}/admin/appCourse/deleteAppCourseById/"+id,
			type:"post",
			dataType:"json",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="true"){
					dialogFun("app课程列表","删除成功",1);
					window.location.reload();
				}
				else{
					dialogFun("app课程列表","系统繁忙",0);
				}
			}
			
		});
	}
	
}

function delBatch(){//批量删除预约
	var ids=document.getElementsByName("ids");
	var str="";
	var checked = true;
	$(ids).each(function(){
		if($(this).prop("checked")){
			str += this.value+",";
			checked = false;
		}
	});
	if(checked){
		dialogFun("app课程列表","请至少选择一条信息",0);
		return;
	}
	$.ajax({
		url:"${ctx}/admin/appCourse/deleteAppCourseBatch",
		data:{"ids":str},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.success){
				window.location.reload();
				$("input[type='checkbox']").prop("checked", false);
			}else{
				dialogFun("app课程列表","系统繁忙稍后重试",0);
				return;
			}
		}
	});
}
function allCheck(cb)
{
	$("input[name=ids]").prop('checked', cb.checked);
}
function choose(){
	window.open('${cxt}/admin/appCourse/selectCourse?page.currentPage=1','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
	);
}
function newCourse(){
	window.location.href='${ctx}/admin/cou/toAddCourse?type=app';
}
</script>
</head>
<body>

	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">app课程管理</strong> / <small>app课程列表</small></div>
	</div>
	<hr>

	<form action="${ctx}/admin/appCourse/getAppCourseList" name="searchForm" id="searchForm" method="post" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="am-g am-margin-top am-u-sm-6 am-text-left">
			<div class="am-u-sm-4 am-text-right">
				课程名称
			</div>
			<div class="am-u-sm-8 am-u-end">
				<input type="text" class="am-input-sm" name="queryAppCourseCondition.courseName" id="appWebsiteCourseDetailName" value="${queryAppCourseCondition.courseName}" />
			</div>
		</div>
		<div class="am-g am-margin-top am-u-sm-6 am-text-left">
			<div class="am-u-sm-4 am-text-right">
				是否下架
			</div>
			<div class="am-u-sm-8 am-u-end">
				<select name="queryAppCourseCondition.isavaliable" id="isavaliable" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
					<option value="-1">--请选择--</option>
					<option value="0" <c:if test="${ queryAppCourseCondition.isavaliable==0}">selected="selected"</c:if>>上架</option>
					<option value="1" <c:if test="${queryAppCourseCondition.isavaliable==1}">selected="selected"</c:if>>下架</option>
				</select>
			</div>
		</div>
		<div class="mt20 clear"></div>
	</form>
	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6">
				<div class="am-btn-toolbar">
					<div class="am-btn-group am-btn-group-xs">
						<button class="am-btn am-btn-success" type="button" onclick="newCourse()"><span class="am-icon-plus"></span> 新建课程</button>
						<button class="am-btn am-btn-success" type="button" onclick="delBatch()" title="批量新建用户"><span class="am-icon-trash-o"></span> 批量删除</button>
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
						<th width="8%">
							<label class="am-checkbox">
								<input type="checkbox" onclick="allCheck(this)" data-am-ucheck>&nbsp;课程ID
							</label>
						</th>
						<th>课程名称</th>
						<th>总课时</th>
						<th>添加时间</th>
						<th>课程状态</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
						<c:if test="${appCourseList.size()>0}">
							<c:forEach  items="${appCourseList}" var="appCourse" >
								<tr>
									<td>
										<label class="am-checkbox">
											<input type="checkbox" name="ids" value="${appCourse.courseId}" data-am-ucheck>&nbsp;${appCourse.courseId }
										</label>
									</td>
									<td>${appCourse.courseName }</td>
									<td>${appCourse.lessionnum}</td>
									<td><fmt:formatDate type="both" value="${appCourse.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<c:if test="${appCourse.isavaliable==0}">上架</c:if>
										<c:if test="${appCourse.isavaliable==1}">下架</c:if>
									</td>
									<td>
										<button class="am-btn am-btn-danger" onclick="del(${appCourse.courseId})" >删除</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${appCourseList==null||appCourseList.size()==0}">
							<tr>
								<td colspan="6">
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
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			</div>
		</div>
	</div>
</body>
</html>