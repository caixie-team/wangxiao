<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
function cancel(){
	$("#appWebsiteCourseDetailName").val("");
	$("#isavaliable").val(0);
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
					alert("删除成功");
					window.location.reload();
				}
				else{
					alert("系统繁忙");
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
		alert("请至少选择一条信息");
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
				alert("系统繁忙稍后重试");
				return;
			}
		}
	});
}
function allCheck(cb)
{
	$("input[name=ids]").attr('checked', cb.checked);
}
function choose(){
	window.open('${cxt}/admin/appCourse/selectCourse?page.currentPage=1','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
	);
}

</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>app课程管理</span> &gt; <span>app课程列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/appCourse/getAppCourseList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
										<span class="ddTitle"><font>课程名称：</font></span>
										<input type="text" name="queryAppCourseCondition.courseName" id="appWebsiteCourseDetailName" value="${queryAppCourseCondition.courseName}"/>
										<span class="ddTitle"><font>是否下架：</font></span>
										<select name="queryAppCourseCondition.isavaliable" id="isavaliable">
											<option value="0">--请选择--</option>
											<option value="1" <c:if test="${ queryAppCourseCondition.isavaliable==1}">selected="selected"</c:if>>上架</option>
											<option value="2" <c:if test="${queryAppCourseCondition.isavaliable==2}">selected="selected"</c:if>>下架</option>
										</select>
										<input type="button"  class="btn btn-danger" value="查询" name="" onclick="goPage(1)"/>
										<input type="button"  class="btn btn-danger" value="清空" name="" onclick="cancel()"/>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn">
								<span class="ml10"><a href="javascript:choose()"><em class="icon14 new">&nbsp;</em>新建APP课程</a></span>
							</p>
							<p class="fl czBtn">
								<span class="ml10"><a href="javascript:delBatch()"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
							</p>
							<p class="fr c_666"><span>app课程列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
						</div>
					</caption>
					
				<thead>
					<tr>
						<th width="8%">
						<span><input type="checkbox" onclick="allCheck(this)"/>&nbsp;课程ID</span>
						</th>
                           <th><span>课程名称</span></th>
                           <th><span>总课时</span></th>
                           <th><span>添加时间</span></th>
                           <th><span>课程状态</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${appCourseList.size()>0}">
						<c:forEach  items="${appCourseList}" var="appCourse" >
						<tr>
							<td><input type="checkbox" name="ids" value="${appCourse.courseId}" />&nbsp;${appCourse.courseId }</td>
							<td>${appCourse.courseName }</td>
							<td>${appCourse.lessionnum}</td>
							<td><fmt:formatDate type="both" value="${appCourse.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                            	<c:if test="${appCourse.isavaliable==0}">可用</c:if>
                            	<c:if test="${appCourse.isavaliable==1}">上架</c:if>
                            	<c:if test="${appCourse.isavaliable==2}">下架</c:if>
                            </td>
							<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" href="javascript:del(${appCourse.mainId})" >删除</a>
							</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${appCourseList==null||appCourseList.size()==0}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有推荐课程！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			</form>
			<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div><!-- /commonWrap -->
	</div>
</body>
</html>