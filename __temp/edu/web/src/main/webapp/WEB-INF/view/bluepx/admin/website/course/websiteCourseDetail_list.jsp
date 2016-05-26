<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
function cancel(){
	$("#websiteCourseDetailName").val("");
	$("#recommemd").val(-1);
	$("#isavaliable").val(-1);
}
function addCourseReload(){
	window.location.reload();
}
function del(id){
	if(confirm("确定删除该推荐吗？")){
		$.ajax({
			url:"${cxt}/admin/website/delWebsiteCourseDetailById/"+id,
			type:"post",
			dataType:"json",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="true"){
					alert("删除成功");
					window.location.reload();
				}
			}
			
		});
	}
	
}
function choose(){
	window.open('${cxt}/admin/cou/recommendCourseList?page.currentPage=1',
		+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
	);
}

</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>推荐课程管理</span> &gt; <span>推荐课程列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/website/websiteCourseDetailPage" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>课程名称：</font></span>
										<input type="text" name="websiteCourseDetailDTO.courseName" id="websiteCourseDetailName" value="${websiteCourseDetailDTO.courseName}"/>
									</li>
									<li>
										<span class="ddTitle"><font>是否下架：</font></span>
										<select name="websiteCourseDetailDTO.isavaliable" id="isavaliable">
											<option value="-1">--请选择--</option>
											<option value="1" <c:if test="${ websiteCourseDetailDTO.isavaliable==1}">selected="selected"</c:if>>未下架</option>
											<option value="2" <c:if test="${websiteCourseDetailDTO.isavaliable==2}">selected="selected"</c:if>>已下架</option>
										</select>
									</li>
								</ul>
							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>分类：</font></span>
										<select name="websiteCourseDetailDTO.recommendId" id="recommemd">
											<option value="-1">--请选择--</option>
											<c:forEach items="${websiteCourses }" var="websiteCourse">
												<option value="${websiteCourse.id}" <c:if test="${ websiteCourseDetailDTO.recommendId==websiteCourse.id}">selected="selected"</c:if>>${websiteCourse.name}</option>
											</c:forEach>
										</select>
									</li>
									<li >
									<input type="button"  class="btn btn-danger" value="查询" name="" onclick="goPage(1)"/>
									<input type="button"  class="btn btn-danger" value="清空" name="" onclick="cancel()"/>
								</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn">
								<span class="ml10"><a href="javascript:choose()"><em class="icon14 new">&nbsp;</em>新建推荐课程</a></span>
							</p>
							<p class="fr c_666"><span>推荐课程列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
						</div>
					</caption>
					
				<thead>
					<tr>
						<th  width="8%"><span>ID</span></th>
                           <th><span>课程名称</span></th>
                           <th><span>分类</span></th>
                           <th><span>排序值</span></th>
                           <th><span>课程状态</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${websiteCourseDetailDTOList.size()>0}">
						<c:forEach  items="${websiteCourseDetailDTOList}" var="websiteCourseDetail" >
						<tr>
							<td>${websiteCourseDetail.id }</td>
							<td>${websiteCourseDetail.courseName }</td>
							<td>${websiteCourseDetail.recommendName }</td>
							<td>${websiteCourseDetail.orderNum }</td>
                            <td>${websiteCourseDetail.isavaliable==0?'上架':'下架' }</td>
							<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/website/doUpdateWebsiteCourseDetail/${websiteCourseDetail.id}" >修改</a>
								<a class="ml10 btn smallbtn btn-y" href="javascript:del(${websiteCourseDetail.id})" >删除</a>
							</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${websiteCourseDetailDTOList.size()==0||websiteCourseDetailDTOList==null}">
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