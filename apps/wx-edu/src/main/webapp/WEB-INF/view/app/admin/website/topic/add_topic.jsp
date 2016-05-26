<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>新建话题</title>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>	
<script type="text/javascript">
	$(function(){
		//上传图片
		initUploadify("fileupload","subjcetpic","imagesUrl","","topic");
	});
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">话题管理</strong> / <small>新建话题</small></div>
</div>
<hr>

<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">话题基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/appTopic/add" method="post" id="addCourse" class="am-form" data-am-validator>
					<input type="hidden" name="appTopic.imageUrl" id="imagesUrl" value="" />
					<div class="am-g am-margin-top am-form-group">
						<label for="topicTitle" class="am-u-sm-4 am-u-md-2 am-text-right">
							话题标题
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="topicTitle" class="am-input-sm" required placeholder="请填写话题标题" name="appTopic.topicTitle">
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							话题详情
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea rows="5" cols="50" name="appTopic.topicContent" ></textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							话题图片
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<img src="${cxt}/static/edu/images/NotAvailable.png" id="subjcetpic" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<div class="am-form-group am-form-file">
								<input type="file" multiple="" id="fileupload">
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
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
