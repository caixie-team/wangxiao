<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>新建讲义</title>
	<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
	<script type="text/javascript">
		$(function(){
			$("#fileuploadPDF").uploadify({
				'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
				'method' : 'GET',
				'script'  :'<%=uploadServerUrl%>/pdf2swf',
				'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"toPDF","flag":"pdf"},
				'queueID':'fileQueuePDF',
				'fileDataName':'fileupload',
				'auto':true,
				'multi':false,
				'hideButton':false,
				'buttonText':'Browse',
				'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
				'simUploadLimit' : 3,
				'sizeLimit'      : 512000000,
				'queueSizeLimit' : 2,
				'fileDesc'       : '支持格式:pdf/ppt/xls/doc',
				'fileExt'        : '*.pdf;',//'*.pdf;*.ppt;*.xls;*.doc;
				'folder' : '/upload',
				'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
				onSelect:function(event, queueID, fileObj){
					$('#fileuploadPDF').uploadifyUpload();
					fileuploadIndex=1;
					$("#fileQueuePDF").html("");
					if(fileObj.size>512000000){
						dialogFun("讲义上传","文件太大最大上传512M",0);
						return;
					}
				},
				onComplete: function (event, queueID, fileObj, response, data){
					var type = "";
					var msg = "";
					if(response =='pdfnotexists'){
						type = "am-alert-danger";
						msg = "pdf文件不存在";
					}else if(response =='swfexists'){
						type = "am-alert-warning";
						msg = "已经存在不需要转换";
					}else if(response ==null){
						type = "am-alert-danger";
						msg = "转换错误";
					}else{
						type = "am-alert-success";
						msg = "上传成功";
					}
					var html = '<div data-am-alert="" class="am-alert '+type+'">'+
							'<button class="am-close" type="button">×</button>'+
							'<p>'+msg+'</p>'+
							'</div>';

					$("#messagePDF").html(html);
					$("#urlStr").val(response);
				},
				onError : function(event, queueID,fileObj, errorObj) {
					$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
				}
			});
		});
		function updateCourseHandout() {

			if($("#name").val()==""){
				dialogFun("新建讲义","讲义名称不能为空",0);
				return;
			}

			if($("#urlStr").val()==""){
				dialogFun("新建讲义","请上传PDFs",0);
				return;
			}
			$("#addCourseHandout").submit();
		}

	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">讲义管理</strong> / <small>修改讲义</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">讲义基本属性（ <i class="am-text-danger">*</i> &nbsp;为必填项）</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/cou/updateCourseHandout" method="post" id="addCourseHandout" class="am-form" data-am-validator>
					<input name="handout.courseId" type="hidden" value="${handout.courseId}" />
					<input name="handout.id" type="hidden" value="${handout.id}" />
					<div class="am-g am-margin-top am-form-group">
						<label for="name" class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i>讲义名称
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" class="am-input-sm" required placeholder="请填写讲义名称" value="${handout.name}" name="handout.name" id="name">
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="urlStr" class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i>PDF文件
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" class="am-input-sm" required placeholder="可直接输入地址" value="${handout.path}" name="handout.path" id="urlStr">
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>
					<div class="am-g am-margin-top ">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input id="fileuploadPDF" type="file" multiple>
							<section class="mt20" id="messagePDF"></section>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="提 交" href="javascript:updateCourseHandout()">提 交</a>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
