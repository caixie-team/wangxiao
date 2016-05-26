<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改资讯</title>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/common/uploadify.js" ></script>

<script type="text/javascript">
var context;
$(document).ready(function() {
	  //实例化编辑器
		context = UE.getEditor('editor', {
		maximumWords:50000,
		initialFrameWidth:600,
		initialFrameHeight:400,
		serverUrl:"${imageUrl}/ueditor/controller.jsp?fromdomain=${ctx}",
		toolbars: [ueditor_simpletoolbar]
	});
$("#type").val('${article.type}');
//上传图片
initUploadify("fileupload","imgShowId","imgHiddrenId","fileQueue","article");
});

//表单验证
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

//提交表单
function updateArticleFormSubmit(){
	if(submitFrom()){
		$("#updateArticleForm").submit();
	}
}
</script>
</head>
<body >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">资讯管理</strong> / <small>资讯修改</small>
		</div>
	</div>
	<hr/>
	<div class="mt20">
		<form action="${ctx}/admin/article/updateArticle" method="post" id="updateArticleForm" class="am-form" >
		<input type="hidden" name="article.picture" id="imgHiddrenId"  value="${article.picture}"/>
		<input type="hidden" name="article.id" value="${article.id}"/>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;标题</div>
			<div class="am-u-sm-8 am-u-md-6">
				<input  type="text" name="article.title" id="title" value="${article.title }" class="am-input-sm" required placeholder="请填写菜单名称"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;标签</div>
			<div class="am-u-sm-8 am-u-md-6">
				<input  type="text" name="article.meta" id="meta" value="${article.meta }" class="am-input-sm" required placeholder="请填写标签"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;分类</div>
			<div class="am-u-sm-8 am-u-md-6">
				<select name="article.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" class="am-input-sm">
					<c:forEach items="${classifyList }" var="classify">
						<option value="${classify.name }">${classify.explain }</option>
					</c:forEach>
				</select>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>	
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;作者</div>
			<div class="am-u-sm-8 am-u-md-6">
				<input   type="text" name="article.author" id="author" value="${article.author}" class="am-input-sm" required placeholder="请填写作者"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;资讯封面图片</div>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="file" id="fileupload"/>
				<div id="fileQueue" ></div>
				<c:if test="${empty article.picture}">
					<img src="<%=imagesPath %>/static/edu/images/NotAvailable.png" alt="" width="400px" height="300px" id="imgShowId" />
				</c:if>
				<c:if test="${not empty article.picture}">
					<img src="<%=staticImageServer%>${article.picture}" alt="" width="400px" height="300px" id="imgShowId" />
				</c:if>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;点击量</div>
			<div class="am-u-sm-8 am-u-md-6">
				<input  type="text" name="article.clickTimes" id="clickTimes" value="${article.clickTimes}" pattern="^[0-9]*$" class="am-input-sm" required placeholder="请填写点击量"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;内容</div>
			<div class="am-u-sm-8 am-u-md-6">
			<textarea id="editor" name="article.content">${article.content}</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div> 
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
			<div class="am-u-sm-8 am-u-md-6">
				<button class="am-btn am-btn-danger" type="button" onclick=updateArticleFormSubmit()>提交</button>
			 <a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		</form>
	</div>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/lang/zh-cn/zh-cn.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.mine.js"> </script>

</body>
</html>
