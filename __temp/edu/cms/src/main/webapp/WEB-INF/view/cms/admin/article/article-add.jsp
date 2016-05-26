<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>添加资讯</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/cms/articleUtil.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		initArticleType('articleId',-1,'${stringTypeList}','infoTypeId','infoTypeLinkId');
		initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
		initKindEditor_addblog('ArticleContent','576px','400px');
	});
	function addArticleFormSubmit(type){
		var title = $("input[name='article.title']").val();
		if(title==null || title.replace(/ /g,'').replace(/　/g,''))==''){
			alert("请填写资讯标题！");
			return false;
		}
		var infoTypeId = $("#infoTypeId").val();
		if(infoTypeId<=0){
			alert('请选择资讯类型');
			return false;
		}
		var ArticleContent =$("#ArticleContent").val();
		if(ArticleContent==null || ArticleContent.replace(/ /g,'').replace(/　/g,'')==''){
			alert('资讯内容不能为空！');
			return false;
		}
		
		var lookNum = $("input[name='article.lookNum']").val();
		if(lookNum!=null && lookNum!=''){
			var reg = /^\d+$/;
			if(!reg.test(lookNum)){
				alert('点击量必须是正整数！');
				return false;
			}
		}
		if(type==1){
			$("#addArticleForm").attr("action","${ctx}/admin/line/article/addAndHtml");
		}
		$("#addArticleForm").submit();
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
	//添加博文页面编辑器
	function initKindEditor_addblog(id, width, height) {
		EditorObject = KindEditor.create('textarea[id=' + id + ']', {
			resizeType : 1,
			filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
			allowPreviewEmoticons : false,
			allowUpload : true,// 允许上传
			urlType : 'domain',// absolute
			newlineTag : 'br',// 回车换行br|p
			width : width,
			height : height,
			minWidth : '10px',
			minHeight : '10px',
			uploadJson : '<%=keImageUploadUrl%>' + '&param=article',// 图片上传路径
			afterBlur : function() {
				this.sync();
			},
			allowFileManager : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
					'image', 'link','plainpaste' ]
		});
	}
</script>
</head>
<body >
<form action="${ctx}/admin/line/article/add" method="post" id="addArticleForm">
<input type="hidden" name="article.imageUrl" id="imagesUrl"  />
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>资讯管理</span> &gt; <span>资讯添加</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>资讯基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;标题</td>
				<td>
					<input type="text" name="article.title" class="{required:true}"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center">资讯来源</td>
				<td width="80%">
				<input type="text" name="article.source" value=""/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center">文章摘要</td>
				<td width="80%">
				<textarea rows="5" cols="80" name="article.summary" class="{required:true}" id=""></textarea>
				</td>
			</tr>
		
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;分类</td>
				<td>
				<select id="articleId">
    			<option value="0">-选择类型-</option>
	    		</select>
				</td>
			</tr>
				
			<tr>
				<td width="20%" align="center">作者</td>
				<td width="80%">
				<input type="text" name="article.author" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center">&nbsp;资讯封面图片</td>
				<td>
				<img src="<%=imagesPath %>/static/edu/images/NotAvailable.png" alt="" id="subjcetpic" width="400px" height="300px"/>
					<input type="button" value="上传" id="fileuploadButton"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center">点击量</td>
				<td width="80%">
				<input type="text" name="article.lookNum" value="0"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center">推荐状态</td>
				<td width="80%">
					<input checked="checked" type="radio" name="article.property" value="0"/>不推荐
	    			<input type="radio" name="article.property" value="1"/>推荐
	    			<input type="radio" name="article.property" value="2"/>头条
				</td>
		    </tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;内容</td>
				<td>
					<textarea rows="" cols="" name="article.content" class="{required:true}" id="ArticleContent"></textarea>
				</td>
			</tr>
			
			<tr>
				<td align="center" colspan="2">
					<input type="hidden" id="infoTypeId" name="article.typeId" value="0"/>
			    	<input type="hidden" id="infoTypeLinkId" name="article.typeLink"/>
					<a class="btn btn-danger" title="保存" href="javascript:void(0)" onclick="addArticleFormSubmit(0)">保存</a>
					<a class="btn btn-danger" title="保存并生成" href="javascript:void(0)" onclick="addArticleFormSubmit(1)">保存并生成</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<!-- /tab4 end -->
</form>
</body>
</html>
