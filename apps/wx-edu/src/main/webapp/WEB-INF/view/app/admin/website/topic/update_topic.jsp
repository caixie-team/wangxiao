<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>话题修改</title>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>	
<script type="text/javascript">
$(function(){
	//上传图片
	initUploadify("fileupload","subjcetpic","imagesUrl","fileQueue","topic");
});
	function addCourseFormSubmit() {
		$("#addCourse").submit();
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">话题管理</strong> / <small>话题修改</small></div>
</div>
<hr>
<div class="am-tabs">
	<ul class="am-tabs-nav am-nav am-nav-tabs">
		<li class="am-active"><a href="javascript:void(0)">话题基本属性</a></li>
	</ul>
	<div class="am-tabs-bd">
		<div id="tab1" class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/appTopic/update" method="post" id="addCourse" class="am-form" data-am-validator>
				<input type="hidden" name="appTopic.topicId" value="${appTopic.topicId }"/>
				<input type="hidden" name="appTopic.imageUrl" id="imagesUrl" value="${appTopic.imageUrl}" />
				<div class="am-g am-margin-top ">
					<label class="am-u-sm-4 am-u-md-2 am-text-right">
						话题发布人
					</label>
					<div class="am-u-sm-8 am-u-md-6">
						<input type="text" class="am-input-sm" value="${appTopic.userName }" readonly>
					</div>
					<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
				</div>
				<div class="am-g am-margin-top am-form-group">
					<label for="topicTitle" class="am-u-sm-4 am-u-md-2 am-text-right">
						话题标题
					</label>
					<div class="am-u-sm-8 am-u-md-6">
						<input type="text" id="topicTitle" value="${appTopic.topicTitle }" class="am-input-sm" required placeholder="请填写话题标题" name="appTopic.topicTitle">
					</div>
					<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
				</div>
				<div class="am-g am-margin-top am-form-group">
					<label for="topicContent" class="am-u-sm-4 am-u-md-2 am-text-right">
						话题详情
					</label>
					<div class="am-u-sm-8 am-u-md-6">
						<textarea name="appTopic.topicContent" id="topicContent" required>${appTopic.topicContent }</textarea>
					</div>
					<div class="am-hide-sm-only am-u-md-4"></div>
				</div>
				<div class="am-g am-margin-top">
					<label class="am-u-sm-4 am-u-md-2 am-text-right">
						话题图片
					</label>
					<div class="am-u-sm-8 am-u-md-6">
						<c:if test="${not empty appTopic.imageUrl}">
							<img src="<%=staticImageServer %>${appTopic.imageUrl }" id="subjcetpic" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
						</c:if>
						<c:if test="${empty appTopic.imageUrl}">
							<img src="${cxt}/static/edu/images/NotAvailable.png" id="subjcetpic" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
						</c:if>
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
					<label class="am-u-sm-4 am-u-md-2 am-text-right">
						话题发布时间
					</label>
					<div class="am-u-sm-8 am-u-md-6">
						<fmt:formatDate type="both" value="${appTopic.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
					<div class="am-u-sm-12 am-u-md-4"></div>
				</div>
				<div class="am-g am-margin-top">
					<label class="am-u-sm-4 am-u-md-2 am-text-right">
						话题状态
					</label>
					<div class="am-u-sm-8 am-u-md-6">
						<label class="am-radio-inline">
							<input type="radio" name="appTopic.states" value="DEFAULT" <c:if test="${appTopic.states=='DEFAULT'}">checked="checked"</c:if>/>默认
						</label>
						<label class="am-radio-inline">
							<input type="radio" name="appTopic.states" value="HIDDEN" <c:if test="${appTopic.states=='HIDDEN'}">checked="checked"</c:if>/>隐藏
						</label>
					</div>
					<div class="am-u-sm-12 am-u-md-4"></div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
					<div class="am-u-sm-8 am-u-md-6">
						<button class="am-btn am-btn-danger" type="button" onclick="addCourseFormSubmit()">提交</button>
						<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
					</div>
					<div class="am-u-sm-12 am-u-md-4"></div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
