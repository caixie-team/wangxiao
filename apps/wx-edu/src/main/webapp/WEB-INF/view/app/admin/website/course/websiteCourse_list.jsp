<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>推荐课程分类列表</title>
<script type="text/javascript">
	function del(id){
		if(confirm("确定删除该分类吗？")){
			$.ajax({
				url:"${cxt}/admin/appWebsite/delWebsiteCourseById/"+id,
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
	<!-- 公共右侧样式 -->
		<div class="am-cf">
	      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">推荐课程分类管理</strong> / <small>推荐课程分类列表</small></div>
	    </div>
	    <hr/>
	<!-- 公共右侧标题样式 结束-->
	  <div class="mt20">
	    	<div class="am-g">
		      <div class="am-u-md-6">
		        <div class="am-btn-toolbar">
		          <div class="am-btn-group am-btn-group-xs">
		            <button class="am-btn am-btn-success" type="button" onclick="window.location.href='${cxt}/admin/appWebsite/doAddWebsiteCourse'" title="新增"><span class="am-icon-plus"></span> 新增</button>
		          </div>
		        </div>
		      </div>
		   </div>
	   </div>
	<div class="am-g">
		      <div class="mt20">
		          <table class="am-table am-table-striped am-table-hover table-main">
					<thead>
						<tr>
							<th width="10%">ID</th>
                            <th width="10%">分类名称</th>
                            <th width="20%">更多跳转</th>
                            <th width="10%">课程数量</th>
                            <th width="30%">描述</th>
                            <th width="20%">操作</th>
						</tr>
					</thead>
					<tbody >
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
							<td>
                           <div class="am-btn-toolbar">
			                  <div class="am-btn-group am-btn-group-xs">
			                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${cxt}/admin/appWebsite/doUpdateWebsiteCourse/${websiteCourse.id}'"><span class="am-icon-pencil-square-o"></span> 编辑</button>
			                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="del(${websiteCourse.id})"><span class="am-icon-trash-o"></span> 删除</button>
			                  </div>
			                </div>
                         	</td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${websiteCourseList.size()==0||websiteCourseList==null}">
						<tr>
	                		<td colspan="16">
              					<div data-am-alert=""
									class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
											style="font-size: 34px;"></i> <span class="vam ml10">还没有推荐课程分类！</span></big>
									</center>
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