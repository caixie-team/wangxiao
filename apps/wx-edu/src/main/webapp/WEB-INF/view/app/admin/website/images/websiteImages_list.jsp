<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
<script type="text/javascript">
function deleteImg(){
	var imgIds = document.getElementsByName("ids");
	var num=0;
	var ids ='';
	for(var i=0;i<imgIds.length;i++){
		if(imgIds[i].checked==true){
			num++;
			ids +=imgIds[i].value;
			if(i!=imgIds.length-1){
				ids +=",";
			}
		}
	}
	if(num==0){
		alert("请选择要删除的广告图");
		return;
	}
	if (window.confirm("确认删除这些数据吗？")){
		$.ajax({
			url:"${ctx}/admin/appWebsite/delImages/"+ids,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("删除成功");
					window.location.reload();
				}
			}
		});
	}
}

function allCheck(cb)
{
	$("input[name=ids]").attr('checked', cb.checked);
}


function cancel(){
	$("#title").val("");
	$("#imagesUrl").val("");
	$("#courseId").val("");
	$("#keyWord").val("");
	$("#keyWord").find('option').eq(0).attr('selected', true);
}
function delImg(id){
	if(confirm("确认删除吗？")){
		$.ajax({
			url:"${ctx}/admin/appWebsite/delImages/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("删除成功");
					window.location.reload();
				}
			}
		});
	}
}        
</script>

</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">广告图管理</strong> / <small>广告图列表</small></div>
	</div>
	<hr/>
	<form class="am-form" action="${ctx}/admin/appWebsite/imagesPage" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">
						标题
					</div>
					<div class="am-u-sm-8">
						<input type="text" class="am-input-sm" name="appWebsiteImages.title" id="title" value="${appWebsiteImages.title}"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">
						图片URL
					</div>
					<div class="am-u-sm-8">
						<input type="text" class="am-input-sm" name="appWebsiteImages.imagesUrl" id="imagesUrl" value="${appWebsiteImages.imagesUrl}"/>
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">
						类型
					</div>
					<div class="am-u-sm-8 ">
						<select name="appWebsiteImages.keyWord" id="keyWord" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="">--请选择关键字--</option>
							<option value="indexCenterBanner" <c:if test="${appWebsiteImages.keyWord=='indexCenterBanner'}">selected="selected"</c:if>>首页Banner图片</option>
							<option value="loadBanner">APP加载图片</option>
							<option value="appLogo">APP-LOGO图片</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">
						课程ID
					</div>
					<div class="am-u-sm-8">
						<input type="text" class="am-input-sm" name="appWebsiteImages.courseId" id="courseId" value="${appWebsiteImages.courseId}"/>
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
							<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${cxt}/admin/appWebsite/doAddImages'"><span class="am-icon-plus"></span> 新建广告图</button>
							<button class="am-btn am-btn-success" type="button" onclick="deleteImg()"><span class="am-icon-trash-o"></span> 批量删除</button>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-3">
					<div class="am-input-group am-input-group-sm">
		          <span class="am-input-group-btn">
		            <button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
						<span class="am-icon-search"></span> 搜索
					</button>
		            <button type="button" class="am-btn am-btn-danger" onclick="cancel()">
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
			<div class="am-scrollable-horizontal am-scrollable-vertical">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
					<tr>
						<th>
							<label class="am-checkbox">
								<input type="checkbox" data-am-ucheck onclick="allCheck(this)"/>
							</label>
						</th>
						<th width="10%">图片标题</th>
						<th width="25%">图片URL</th>
						<th width="20%">课程</th>
						<th width="15%">类型</th>
						<th width="10%">序号</th>
						<th width="20%">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${websiteImagesList.size()>0}">
						<c:forEach  items="${websiteImagesList}" var="images" >
							<tr>
								<td>
									<label class="am-checkbox">
										<input type="checkbox" data-am-ucheck name="ids" value="${images.id}"/>
									</label>
								</td>
								<td>${images.title }</td>
								<td>${images.imagesUrl }</td>
								<td>
									<c:choose>
										<c:when test="${images.name==null}">
											--
										</c:when>
										<c:otherwise>
											${images.name}
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:if test="${images.keyWord=='indexCenterBanner'}">首页banner图轮播</c:if>
									<c:if test="${images.keyWord=='loadBanner'}">APP加载图片</c:if>
									<c:if test="${images.keyWord=='appLogo'}">APP-LOGO图片</c:if>
								</td>
								<td>${images.seriesNumber}</td>
								<td>
									<div data-am-dropdown="" class="am-dropdown">
										<button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
										<ul class="am-dropdown-content">
											<li><a href="${ctx}/admin/appWebsite/doUpdateImages/${images.id}">修改</a></li>
											<li><a href="${staticUrl}${images.imagesUrl}">预览</a></li>
											<li><a href="javascript:void(0)" onclick="delImg(${images.id})">删除</a></li>
										</ul>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${websiteImagesList.size()==0||websiteImagesList==null}">
						<tr>
							<td colspan="9">
								<div data-am-alert=""
									 class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
												 style="font-size: 34px;"></i> <span class="vam ml10">还没有广告图！</span></big>
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