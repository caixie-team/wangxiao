<%@ page import="com.atdld.os.sns.constants.SnsConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改小组话题</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	
	<script type="text/javascript">
		var mydomain ="<%=mydomain%>";//主站域
		var usercookiekey="<%=usercookiekey%>";//用户key,只存key，不存实际信息
		var baselocation = "${ctx}";
		var baselocationweb = "${ctxweb}";
		var baselocationexam = "${ctxexam}";
		var imagesPath="${ctximg}";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var disFaceUpUrl="<%=disFaceUpUrl%>";//小组头像上传路径
	    var staticImageServer="<%=staticImageServer%>";//上传后返回路径
		var uploadServerUrl="<%=uploadServerUrl%>";
		var uid="${uid}";
		var max_text_length=<%=SnsConstants.MAX_TEXT_LENGTH%>;//博文，建议的最多次文字数量
	</script>
	<script type="text/javascript">
		$().ready(function() {
			initKindEditor_editDisArticle('disArticleContent','576px','400px');
	     });
		//发表小组文章
		 var EditorObject;
		 var disArticlePath="disArticle";//图片路径
		 function initKindEditor_editDisArticle(id,width,height){
				EditorObject = KindEditor.create('textarea[id='+id+']', {
					   resizeType  : 1,
				       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
				       allowPreviewEmoticons : false,
				       allowUpload : true,//允许上传
				       syncType : 'auto',
				       urlType : 'domain',//absolute
				       newlineTag :'br',//回车换行br|p
				       minWidth:'30px',
				       width:width,
				       height:height,
				       uploadJson : keImageUploadUrl+'&param'+disArticlePath,//图片上传路径
				       afterBlur:function(){EditorObject.sync();}, 
				       allowFileManager : false,
				       items : [
								'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline','formatblock','lineheight',
								'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
								'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
						afterChange:function(){
						var num=$("#blogFont").html(this.count('text'));//字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字
						if(num>=5){
							return true;
						}else{
							return false;
						}
						}		
				});
		}
		function tiSub(){
			var title=$("#title").val().trim();
			var blogContent=$("#blogContent").val().trim();
			if(title==null||title==''){
				alert("标题不能为空");
				return;
			}else if(blogContent==null||blogContent==''){
				alert("内容不能为空");
				return;
			}else {
				$("#form1").submit();
			};
		}
		function limit(){
			var count=$("#text").val().length;//获得标题长度
			if(count>50){
				dialog_sns("对不起您已输入超过50个字",5);
				return false;
			}else{
				return true;
			}
			
		}
		//修改小组文章
		function baocun(){
			var count=$("#disArticleContent").val().length;//判断内容字数
			if(count<5){
				alert("您输入的内容不能少于5个字");
				return;
			}else if($("#text").val()==null||$("#text").val().trim()==''){//判断标题是否为空
				alert("标题不能为空！");
				return;
			}
			
			$("#updateForm").submit();
		}
	</script>
</head>

<body class="W-body">
	<!-- 主体 开始-->
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>修改小组文章信息</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
			<form action="${ctx}/admin/disArticle/updateMyArticle" method="post" name="updateForm" id="updateForm">
				<input type="hidden" name="disArticle.id" value="${disArticleDetail.id}" />
				<input type="hidden" name="disArticle.groupId" value="${disArticleDetail.groupId}" />
				<input type="hidden" name="disArticle.selType" value="${disArticleDetail.selType}" />
			<table class="commonTab01" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<thead>
					<tr>
						<th align="left" colspan="2"><span> 修改小组文章信息<tt
									class="c_666 ml20 fsize12">
									（ <font color="red">*</font> 为必填项）
								</tt>
						</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>标题</td>
						<td width="80%">
							 <input type="text" name="disArticle.title" class="commInput-1" onblur="limit()" id="text" value="${disArticleDetail.title }"> <span
										class="ml10 c-bbb">标题长度不得超过50个字符</span>
						</td>

					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>内容</td>
						<td width="80%">
							 <textarea class="commTextarea" name="disArticle.content" id="disArticleContent" style="width: 576px;height: 402px;">${disArticleDetail.content }</textarea>
									<p class="mt5 c-bbb">您当前输入<span id="blogFont">0</span>个字,不得少于5个字;</p>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input class="btn btn-danger" type="button" value="提交" onclick="baocun()" value="提交"> 
							<input class="btn ml10" type="button" onclick="history.go(-1)" value="返回">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>