<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程笔记详情</title>
<script type="text/javascript" src="${ctximg}/static/edu/js/common/uploadify.js" ></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
window.onload=function(){//编辑器初始化
	initKindEditor_addblog('ArticleContent','576px','400px');
};
</script>
</head>
<body>
<div class="am-cf">
<div class="am-fl am-cf">
<strong class="am-text-primary am-text-lg">课程笔记管理</strong>
/
<small>课程笔记</small>
</div>
</div>
<form action="?" method="post" id="addTeacherForm">
<input type="hidden" name="teacher.picPath" id="imagesUrl" />
<div class="mt20">
<div class="am-tabs">
<div class="am-tabs-bd">
 		<div id="tab1" class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				学员名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="queryCourseNote.nickname" value="${queryCourseNote.nickname}" disabled="disabled" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				学员账号
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="queryCourseNote.email" value="${queryCourseNote.email}" disabled="disabled" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				视频名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="queryCourseNote.content" disabled="disabled" value="${queryCourseNote.pointName}" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				笔记时间
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<fmt:formatDate value="${queryCourseNote.updateTime }" type="both" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				笔记内容
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<textarea rows="6" cols="80" name="queryCourseNote.content" disabled="disabled" id="ArticleContent">${queryCourseNote.content}</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-5 am-text-right">
				&nbsp;
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
</div>
</div>
</div>
</div>
</form>
</body>
</html>
