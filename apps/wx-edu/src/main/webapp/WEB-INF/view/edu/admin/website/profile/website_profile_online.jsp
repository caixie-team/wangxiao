<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>在线咨询</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	$("#fileupload").uploadify({
        'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
        'method' : 'GET', 
        'script'  :'<%=uploadSwfUrl%>',
        'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"online"},
        'queueID':'fileQueue',
        'fileDataName':'fileupload',
        'auto':false,
        'multi':false,
        'hideButton':false,
        'buttonText':'Browse',
        'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
        'width':160,
        'simUploadLimit' : 3,
        'sizeLimit'      : 512000,
        'queueSizeLimit' : 2,
        'fileDesc'       : '支持格式:jpg/png.',
        'fileExt'        : '*.jpg;*.png;',
        'folder' : '/upload',
        'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
         onSelect:function(event, queueID, fileObj){$('#fileupload').uploadifyUpload();
         	fileuploadIndex=1;
        	$("#fileQueue").html("");
        	if(fileObj.size>512000){
        		alert("文件太大最大上传512kb");
        		return;
        	}
        	
        	},
        onComplete: function (event, queueID, fileObj, response, data)
        { 
        	$("#onlinepic").attr("src","<%=staticImageServer%>"+response);
        	$("#imageUrl").val(response);	
        },
        onError: function(event, queueID, fileObj,errorObj)
        {
        	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
        }
    });
});
function uploadifyUpload(){
	 if(fileuploadIndex==0)
	 {
		 alert("请选择图片 ");
		 return;
	 }	
	$('#fileupload').uploadifyUpload();

}
function doSubmit(){
	if($("#link").val()==null||$("#link").val()==''){
		alert("请填写咨询链接");
		return;
	}
	if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
		alert("请上传二维码");
		return ;
	 }
	$("#addOnlineForm").submit();
}
$(function(){
	var msg = "${msg}";
	if(msg!=""){
		dialogFun("提示",msg,1);
	}
})
</script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">在线咨询管理</strong> / <small>在线咨询</small></div>
</div>
<hr>

<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">在线咨询基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form class="am-form" action="${ctx}/admin/websiteProfile/online/update" id="addOnlineForm" method="post">
					<input type="hidden" name="onlineImageUrl" id="imageUrl"  value="${websiteonlinemap.online.onlineImageUrl}"/>
					<div class="am-g am-margin-top ">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							咨询开关
						</div>
						<div class="am-u-sm-8 am-u-md-4">
							<select name="onlineKeyWord" id="onlineKeyWord" required data-am-selected="{btnSize: 'sm'}" style="display: none;">
								<option value="ON" <c:if test="${websiteonlinemap.online.onlineKeyWord=='ON'}">selected='selected'</c:if>>ON</option>
								<option value="OFF" <c:if test="${websiteonlinemap.online.onlineKeyWord=='OFF'}">selected='selected'</c:if>>OFF</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							咨询链接
						</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" class="am-input-sm" placeholder="请填写咨询链接" value="${websiteonlinemap.online.onlineUrl}" name="onlineUrl" id="link">
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							二维码
						</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<c:if test="${not empty websiteonlinemap.online.onlineImageUrl}">
								<img id="onlinepic" src="<%=staticImageServer%>${websiteonlinemap.online.onlineImageUrl}" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
							</c:if>
							<c:if test="${empty websiteonlinemap.online.onlineImageUrl}">
								<img id="onlinepic" src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
							</c:if>
						</div>
						<div class="am-hide-sm-only am-u-md-6"><span class="am-text-danger">*</span>图片链接，支持JPG、PNG格式，尺寸（200*200像素）小于512kb</div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<div class="am-form-group am-form-file">
								<input type="file" multiple="" id="fileupload">
								<button class="am-btn am-btn-primary" type="button" onclick="uploadifyUpload()" style="width: 100px; height: 30px; padding-top: 5px;margin-left:5px;position:absolute;">上传</button>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<button class="am-btn am-btn-danger" title="提 交" onclick="doSubmit()" >修 改</button>
<%--
							<button class="am-btn am-btn-danger" title="返 回" onclick="history.go(-1);">返 回</button>
--%>
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
