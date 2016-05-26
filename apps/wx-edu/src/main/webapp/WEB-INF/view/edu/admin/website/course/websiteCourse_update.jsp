<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>推荐课程分类修改</title>
	
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />

<script type="text/javascript">
	function addSubmit(){
		if($("#websiteCourseName").val()=='' || $("#websiteCourseName").val()==null ){
			dialogFun("推荐课程分类添加","请填写分类名称",0);
			return;
		}
		if($("#oppWebsiteCourseLink").val()=='' || $("#oppWebsiteCourseLink").val()==null){
			dialogFun("推荐课程分类添加","更多跳转",0);
			return;
		}
		if($("#courseNum").val()=='' || $("#courseNum").val()==null){
			dialogFun("推荐课程分类添加","课程数量",0);
			return;
		}
		if($("#description").val()=='' || $("#description").val()==null){
			dialogFun("推荐课程分类添加","描述",0);
			return;
		}
		$("#updateWebsiteCourseForm").submit();
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">推荐课程分类管理</strong> / <small>推荐课程分类修改</small></div>
</div>
<hr/>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">推荐课程分类基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form" data-am-validator action="${ctx}/admin/website/updateWebsiteCourse" method="post" id="updateWebsiteCourseForm">
					<input type="hidden" name="websiteCourse.id" value="${websiteCourse.id}"/>

					<div class="am-g am-margin-top am-form-group">
						<label for="websiteCourseName" class="am-u-sm-4 am-u-md-2 am-text-right">
							分类名称
						</label>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" value="${websiteCourse.name }" name="websiteCourse.name" id="websiteCourseName" class="am-input-sm" />
						</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="link" class="am-u-sm-4 am-u-md-2 am-text-right">
							更多跳转
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" value="${websiteCourse.link }"  name="websiteCourse.link" id="oppWebsiteCourseLink"  id="link" class="am-input-sm"/>
						</div>
						<div class="am-u-sm-12 am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="courseNum" class="am-u-sm-4 am-u-md-2 am-text-right">
							课程数量
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" value="${websiteCourse.courseNum }" name="websiteCourse.courseNum" id="courseNum" required value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" class="am-input-sm"/>
						</div>
						<div class="am-u-sm-12 am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top-sm">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							描述
						</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end">
							<textarea rows="3" cols="80"  name="websiteCourse.description" id="description">${websiteCourse.description }</textarea>
						</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" type="button" onclick="addSubmit()" >提交</a>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>