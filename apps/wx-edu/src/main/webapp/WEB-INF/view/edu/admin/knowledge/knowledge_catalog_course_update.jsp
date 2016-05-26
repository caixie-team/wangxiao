<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>修改目录课程排序</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">知识体系目录课程</strong> / <small>修改课程排序</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/knowledge/updateKnowledgeCatalogMiddleCourse" class="am-form" data-am-validator>
					<input type="hidden" value="${catalogCourse.id}" name="catalogCourse.id" />
					<input type="hidden" value="${catalogCourse.catalogId}" name="catalogCourse.catalogId" />
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							课程名称
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" class="am-input-sm" readonly value="${catalogCourse.courseName}">
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="sort" class="am-u-sm-4 am-u-md-2 am-text-right">
							排序
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input id="sort" type="text" name="catalogCourse.sort" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${catalogCourse.sort}" class="am-input-sm" placeholder="请填写排序号" required>
						</div>
						<div class="am-u-sm-12 am-u-md-6"><font class="am-text-danger">*&nbsp;</font>倒序</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-4">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-6"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>