<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建话题</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<script type="text/javascript">
$(function(){
	$("#addCourse").validate();
	initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
});
	function addCourseFormSubmit() {
		$("#addCourse").submit();
	}
	function initSimpleImageUpload(btnId,urlId,valSet){
		KindEditor.ready(function(K) {
			var uploadbutton = K.uploadbutton({
				button : K('#'+btnId+'')[0],
				fieldName : "fileupload",
				url : '<%=uploadSimpleUrl%>&param=article',
				afterUpload : function(data) {
					if (data.error === 0) {
						var url = K.formatUrl(data.url, 'absolute');//absolute,domain
						K('#'+urlId+'').attr("src",'<%=staticImageServer%>'+data.url);
						$("#"+urlId).show();
						$('#'+valSet+'').val(url);
					} else {
						alert(data.message);
					}
				},
				afterError : function(str) {
					alert('自定义错误信息: ' + str);
				}
			});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
		});
	}
</script>
</head>
<body >
		<form action="${ctx}/admin/appTopic/add" method="post" id="addCourse">
			<div class="page_head"><input type="hidden" name="appTopic.imageUrl" id="imagesUrl" value="" />
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>话题管理</span> &gt; <span>新建话题</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 话题基本属性 <tt class="c_666 ml20 fsize12">
										（ <font color="red">*</font> &nbsp;为必填项）
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;话题标题</td>
							<td>
								<input type="text" name="appTopic.topicTitle" class="{required:true}"/>
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;话题详情</td>
							<td>
							<textarea rows="5" cols="50" name="appTopic.topicContent" id="content" class="{maxlength:50}"></textarea></td>
						</tr>
						<tr>
							<td align="center"> &nbsp;话题图片</td>
							<td>
								<img src="<%=imagesPath %>/static/edu/images/NotAvailable.png" alt="" id="subjcetpic" width="400px" height="300px"/>
								<span id="imgBtn"><input type="button" value="上传" id="fileuploadButton"/></span>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:addCourseFormSubmit()">提 交</a> <a
								class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
