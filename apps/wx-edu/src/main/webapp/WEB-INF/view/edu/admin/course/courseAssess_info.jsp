<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>课程评论详情</title>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程评论管理</strong> / <small>课程评论</small></div>
</div>
<div class="mt20">
<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
	<div class="am-tabs-bd">
   	<div id="tab1" class="am-tab-panel am-fade am-active am-in">
   	<form action="${ctx}/admin/teacher/add" method="post" id="addTeacherForm" class="am-form">
		<input type="hidden" name="teacher.picPath" id="imagesUrl"  />
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				学员名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="queryCourseAssess.nickname" value="${queryCourseAssess.nickname}" disabled="disabled"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"></div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				学员账号
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="queryCourseAssess.email"  value="${queryCourseAssess.email}" disabled="disabled"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				课程名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="queryCourseAssess.courseName" value="${queryCourseAssess.courseName}" disabled="disabled"/>	
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				视频名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="queryCourseAssess.content" disabled="disabled" value="${queryCourseAssess.pointName}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				评论时间
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input disabled="disabled" type="text" value="<fmt:formatDate value="${queryCourseAssess.createTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				评论内容
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input disabled="disabled" type="text" value="${queryCourseAssess.content}" class="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				&nbsp; 
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		</form>
   	</div>
	</div>
</div>
</div>
</body>
</html>
