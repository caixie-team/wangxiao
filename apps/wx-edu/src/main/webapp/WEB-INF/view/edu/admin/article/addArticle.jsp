<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>新建资讯</title>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/common/uploadify.js" ></script>
<script type="text/javascript">
var context;//全局变量提交表单时用于验证
$(document).ready(function() {
	//编译器处理
	context = UE.getEditor('ArticleContent', {
		maximumWords:50000,
		initialFrameWidth:600,
		initialFrameHeight:400,
		serverUrl:"${imageUrl}/ueditor/controller.jsp?fromdomain=${ctx}",
		toolbars: [ueditor_simpletoolbar]
	});
	//上传图片
	initUploadify("fileupload","imgShowId","imgHiddrenId","fileQueue","article");
});

function SubmitVerification(){
	var title = $("#title").val();
	if(title==''||title==null){
		$("#title").addClass("am-form-field");
		$("#titlediv").addClass("am-form-group am-form-error am-form-icon am-form-feedback");
		$("#title").attr("placeholder","此项不能为空");
		$("#titlediv").append('<span class="am-icon-times"></span>');
		return false;
	}else{
		$("#titlediv").removeClass();
		$("#titlediv").addClass("am-form-group am-form-success am-form-icon am-form-feedback");
		$("#title").addClass("am-form-field");
		$("#titlediv").append('<span class="am-icon-check"></span>');
		return false;
	}
	return true;
}

function submitFrom(){
	var img = document.getElementById("imgShowId").src;
	if($("#title").val()==''||$("#title").val()==null){
		   dialogFun("提示信息","请填写标题",0);
		   return false;
	}
	if($("#meta").val()==''||$("#meta").val()==null){
		  dialogFun("提示信息","请填写标签",0);
		   return false;
	}
	if($("#author").val()==''||$("#author").val()==null){
		  dialogFun("提示信息","请填写作者",0);
		   return false;
	}
	if(img=="<%=imagesPath %>/static/edu/images/NotAvailable.png"){
		  dialogFun("提示信息","请上传图片",0);
		   return false;
	}
	if($("#clickTimes").val()==''||$("#clickTimes").val()==null){
		  dialogFun("提示信息","请填写点击量",0);
		   return false;
	}
	 var number = /^[0-9]*$/ ;
	if(!$("#clickTimes").val().match(number)){
		  dialogFun("提示信息","点击量只能填写数字",0);
		   return false;
	}
	//context.hasContents(['span'])固定写法判断编辑中是否存在内容返回类型布尔类型
	if(!context.hasContents(['span'])){
		  dialogFun("提示信息","请填写内容",0);
		   return false;
	}
	
	return true;
	
}

function ArticleForm(){
	if(submitFrom()){
		$("#addArticleForm").submit();
	}
}

</script>
</head>
<body >
<form action="${ctx}/admin/article/add" method="post" id="addArticleForm" class="am-form" >
<input type="hidden" name="article.picture" id="imgHiddrenId"  />
		<div class="am-cf">
			<strong class="am-text-primary am-text-lg">资讯管理</strong> / <small>资讯添加</small>
		</div>
		<hr/>
<div class="mt20">
<div class="am-tab-panel am-fade am-active am-in">
	   <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;标题 </div>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="article.title" id = "title" class="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		  <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">	 <font color="red">*</font>&nbsp;标签 </div>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="article.meta" id="meta" class="am-input-sm" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top am-form-group">
          <div class="am-u-sm-4 am-u-md-2 am-text-right"> <font color="red">*</font>&nbsp;类型 </div>
           <div class="am-u-sm-8 am-u-md-6">
               <select data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" id="doc-vld-keyWord" name="article.type">
                   <c:forEach items="${classifyList}" var="classify">
                       <option value="${classify.name}">${classify.explain }</option>
                   </c:forEach>
               </select>
           </div>
           	<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
       </div>
		  <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"> <font color="red">*</font>&nbsp;作者 </div>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="article.author" id="author" class="am-input-sm" value="${websitemap.web.author}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"> <font color="red">*</font>&nbsp;资讯封面图片 </div>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="file" id="fileupload"/>
				<div id="fileQueue"></div>
				<img  src="<%=imagesPath %>/static/edu/images/NotAvailable.png" alt="" width="400px" height="300px" id="imgShowId" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"> <font color="red">*</font>&nbsp;点击量 </div>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="article.clickTimes" id="clickTimes" class="am-input-sm" value="0" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"> <font color="red">*</font>&nbsp;内容 </div>
			<div class="am-u-sm-8 am-u-md-6">
			<script id="ArticleContent" name="article.content" type="text/plain">
			</script>
				<!--
			<textarea rows="" cols="" name="article.content" class="am-input-sm" id="ArticleContent" ></textarea> -->
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
		 <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
			 <div class="am-u-sm-8 am-u-md-6" style="text-align:center">
			 <a class="am-btn am-btn-danger"  onclick="ArticleForm()">提交</a>
			 <a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
	</div>
</div>
</form>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/lang/zh-cn/zh-cn.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.mine.js"> </script>

</body>
</html>
