<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery.bigcolorpicker.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery.bigcolorpicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
$(document).ready(function(){
	$("#imageColor").bigColorpicker("imageColor","L",10);
	initSimpleImageUpload("imgButton","imgpic","imagesUrl");
    initSimpleImageUpload("previewButton","previewpic","previewUrl");
});
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=bannerImages',
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
function updateSubmit(){
	if($("#imagesTitle").val()==null||$("#imagesTitle").val()==''){
		alert("请填写标题");
		return;
	}
	if($("#imagesUrl").val()==null||$("#imagesUrl").val()==''){
		alert("请上传广告图");
		return
	}
	if($("#keyWord").val()==null||$("#keyWord").val()==''){
		alert("请选择类型");
		return;
	}
	$("#updateImagesForm").submit();
}
//打开新页面选择课程
function choose(){
    window.open('/admin/appWebsite/CourseList?page.currentPage=1',
            +'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
    );
}
//新页面回调，添加课程
function addCourse(id,name){
    $("#courseId").val(id);
    $("#courseName").html(name);
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>广告图管理</span> &gt; <span>广告图更新</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/appWebsite/updateImages" method="post" id="updateImagesForm">
            <input type="hidden"  name="appWebsiteImages.courseId" id="courseId" value="${appWebsiteImages.courseId}"/>
		<input type="hidden" name="appWebsiteImages.id" value="${appWebsiteImages.id }" />
            <input type="hidden" name="appWebsiteImages.imagesUrl" id="imagesUrl" value="${appWebsiteImages.imagesUrl}" style="width: 485px"/>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>广告图基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;广告图标题 </td>
					<td>
						<input type="text" name="appWebsiteImages.title" value="${appWebsiteImages.title}" id="imagesTitle" class="{required:true}"/>
					</td>
				</tr>

				<tr>
					<td width="20%" align="center"><font color="red">*</font>广告图</td>
					<td width="80%">
						<span> 
							<img src="<%= staticImageServer%>${appWebsiteImages.imagesUrl}" alt="" width="360px" height="200px" id="imgpic" />
							 <div id="imagesUrlFileQueue" style="margin-top: 30px; border: 0px"></div>
						</span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="button" value="上传" id="imgButton"/>
							<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽360像素，高200像素）小于256kb
						</span>
					</td>
				</tr>
                <tr id="previewUrl_tr" style="display: none">
                    <td align="center"><font color="red">*</font>&nbsp;缩略图地址 </td>
                    <td>
                        <input type="text" name="appWebsiteImages.previewUrl" id="previewUrl" value="${appWebsiteImages.previewUrl}" style="width: 485px"/>
                        <font color="red">*图片链接,可直复制地址或者通过上传</font>
                    </td>
                </tr>
                <tr id="previewpic_tr" style="display: none">
					<td align="center"><font color="red">*</font>&nbsp;缩略图</td>
					<td>
						<span> 
							<img src="<%= staticImageServer%>${appWebsiteImages.previewUrl}" alt="" width="360px" height="200px" id="previewpic" />
							 <div id="previewUrlFileQueue" style="margin-top: 30px; border: 0px"></div>
						</span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="button" value="上传" id="previewButton"/>
							<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽360像素，高200像素）小于256kb
						</span>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;类型</td>
					<td>
						<select name="appWebsiteImages.keyWord" id="keyWord">
							<option value="">--请选择关键字--</option>
							<option value="indexCenterBanner" <c:if test="${appWebsiteImages.keyWord=='indexCenterBanner'}">selected="selected"</c:if>>APP首页Banner图片</option>
							<option value="loadBanner" <c:if test="${appWebsiteImages.keyWord=='loadBanner'}">selected="selected"</c:if>>APP加载图片</option>
							<option value="appLogo" <c:if test="${appWebsiteImages.keyWord=='appLogo'}">selected="selected"</c:if>>APP-LOGO图片</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">链接URL</td>
                    <td>
                        <span id="courseName">${course.name}</span>
                        <input type="button" value="添加课程" onclick="choose()"/>
                    </td>
				</tr>
				
				<tr id="backColor" style="display: none;">
					<td align="center">背景颜色</td>
					<td>
						<input type="text" name="appWebsiteImages.color" id="imageColor" value="${appWebsiteImages.color}"/>
					</td>
				</tr>
				<tr>
					<td align="center">序列号</td>
					<td>
						<input type="text" name="appWebsiteImages.seriesNumber" value="${appWebsiteImages.seriesNumber}" class="{required:true,number:true}" id="seriesNumber"/>
                        倒叙
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<!-- /tab4 end -->
</body>
</html>