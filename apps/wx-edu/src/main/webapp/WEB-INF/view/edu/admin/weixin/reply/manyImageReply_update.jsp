<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改多图文素材</title>
	<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/weixin/weixin.js?v=${v}"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//初始化 Kindeditor
			KindEditor.create('#content',{
			   resizeType : 1,
			   allowPreviewEmoticons : false,
			   allowUpload : true,
			   syncType : 'auto',
			   urlType : 'absolute',
			   width : '500px',
				minWidth : '10px',
				minHeight : '10px',
				height : '200px',
			   uploadJson : '<%=keImageUploadUrl%>&param=weixin',
				  afterBlur : function() {
					this.sync();
				  },
			   allowFileManager : false,
			   items : ['source','cut', 'copy', 'paste','plainpaste','|','fontname', 'fontsize', '|','forecolor', 'hilitecolor',
						'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
						'justifyleft', 'justifycenter', 'justifyright','justifyfull',
						'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
						'image', 'link']
			});
			initUploadify("fileupload","weixinpic","imageUrl","fileQueue","weixin");
		});
		$(function(){
			queryImage();//查询image
		});

		function updateSubmit(){
			if($("#keyword").val()==null||$("#keyword").val()==""){
				dialogFun("提示","关键词不能为空",0);
				return;
			}
			if($("#title").val()==null||$("#title").val()==""){
				dialogFun("提示","标题不能为空",0);
				return;
			}
			if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
				dialogFun("提示","请上传封面",0);
				return
			}
			if($("#content").val()==null||$("#content").val()==""){
				dialogFun("提示","内容不能为空",0);
				return;
			}if($("#imageIdHidden").val()==null||$("#imageIdHidden").val()==""){
				dialogFun("提示","请添加子图文",0);
				return;
			}
			$("#updateForm").submit();
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">微信管理</strong> / <small>修改多图文素材</small></div>
	</div>
	<hr/>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form class="am-form" action="${ctx}/admin/weixin/update" method="post" id="updateForm">
						<input type="hidden" name="weixinReply.imageUrl" id="imageUrl" value="${weixinReply.imageUrl}"/>
						<input type="hidden" name="weixinReply.manyImageIds" id="imageIdHidden" value="${weixinReply.manyImageIds}"/>
						<input type="hidden" name="weixinReply.id" value="${weixinReply.id}"/>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">关键词</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="weixinReply.keyword" value="${weixinReply.keyword}" id="keyword"/>
							</div>
							<div class="am-hide-sm-only am-u-md-2 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">标题</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="weixinReply.title" value="${weixinReply.title}" id="title" />
							</div>
							<div class="am-hide-sm-only am-u-md-2 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">封面</div>
							<div class="am-u-sm-8 am-u-md-8">
								<span>
									<img src="<%=staticImageServer%>${weixinReply.imageUrl}" alt="" width="360px" height="200px" id="weixinpic" />
									<div id="fileQueue" style="margin-top: 30px; border: 0px"></div>
								</span>
								<font color="red">*图片链接，支持JPG、PNG格式，尺寸（宽360像素，高200像素）小于512kb</font>
								<input type="file" id="fileupload"/>
							</div>
							<div class="am-hide-sm-only am-u-md-2 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">内容</div>
							<div class="am-u-sm-8 am-u-md-8">
								<textarea id="content" name="weixinReply.content">${weixinReply.content}</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-2 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">子图文</div>
							<div class="am-u-sm-8 am-u-md-8">
							<div id="uchtml"></div>
								<a href="javascript:addImage()" title="添加员工" class="am-btn am-btn-primary">添加图文</a>
								<a href="javascript:clearImage()" title="清空" class="am-btn am-btn-primary">清空</a>
							</div>
							<div class="am-hide-sm-only am-u-md-2 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-8">
								<a class="am-btn am-btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
								<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</div>
							<div class="am-u-sm-12 am-u-md-2"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>	