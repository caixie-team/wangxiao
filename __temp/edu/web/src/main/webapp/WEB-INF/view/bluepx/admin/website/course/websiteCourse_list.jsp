<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
	function del(id){
		if(confirm("确定删除该分类吗？")){
			$.ajax({
				url:"${cxt}/admin/website/delWebsiteCourseById/"+id,
				type:"post",
				dataType:"json",
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="true"){
						alert("删除分类成功");
						window.location.reload();
					}
				}
			});
		}
	}
</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>推荐课程分类管理</span> &gt; <span>推荐课程分类列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
			<div class="commonWrap">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
						<div style="margin-top: 10px;">
							<span>
								<input type="button" value="新建推荐分类" class="btn btn-danger ml10" onclick="window.location.href='${cxt}/admin/website/doAddWebsiteCourse'"/>
							</span>
						</div>
					</caption>
					<thead>
						<tr>
							<th width="10%"><span>ID</span></th>
                            <th><span>分类名称</span></th>
                            <th><span>更多跳转</span></th>
                            <th><span>课程数量</span></th>
                            <th><span>描述</span></th>
                            <th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
					<c:if test="${websiteCourseList.size()>0}">
					<c:forEach  items="${websiteCourseList}" var="websiteCourse" >
						<tr>
							<td>${websiteCourse.id }</td>
							<td>${websiteCourse.name }</td>
							<td>${websiteCourse.link }</td>
							<td>${websiteCourse.courseNum }</td>
							<td>
								<c:if test="${websiteCourse.description.length()<30}">
							  		${websiteCourse.description}
			 					</c:if>
			 					<c:if test="${websiteCourse.description.length()>30}">
			 						${fn:substring(websiteCourse.description,0,30)}…
			 					</c:if>
							</td>
							<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/website/doUpdateWebsiteCourse/${websiteCourse.id}" >修改</a>
								<a class="ml10 btn smallbtn btn-y" href="javascript:del(${websiteCourse.id})" >删除</a>
							</td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${websiteCourseList.size()==0||websiteCourseList==null}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有推荐课程分类！</span>
								</div>
							</td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div><!-- /commonWrap -->
		</div>
</body>
</html>