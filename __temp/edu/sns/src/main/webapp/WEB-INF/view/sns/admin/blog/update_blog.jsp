<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改博客</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script type="text/javascript">
	$(function(){
		initKindEditor_editblog('blogContent','500px','400px');
	});
	var EditorObject;
	var blogpath="blog";
	// 添加博文页面编辑器
	function initKindEditor_editblog(id,width,height){
		EditorObject = KindEditor.create('textarea[id='+id+']', {
				resizeType  : 1,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       syncType : 'auto',
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		   	   width : width,
			   height : height,
		       minWidth:'10px',
				minHeight : '10px',
				uploadJson : '<%=keImageUploadUrl%>&param='+blogpath,//图片上传路径
		       afterBlur:function(){
		    	   EditorObject.sync();}, 
		       allowFileManager : false,
		       items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
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
	</script>
</head>

<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>修改博客信息</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
			<form action="${ctx}/admin/blog/updateAdminBlogBlog" method="post" id="form1">
			<table class="commonTab01" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<thead>
					<tr>
						<th align="left" colspan="2"><span> 修改博客信息<tt
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
							 <input type="text" value="${blogBlog.title}"
							name="blogBlog.title" id="title">
						</td>

					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>内容</td>
						<td width="80%">
							 <textarea name="blogBlog.content" id="blogContent">${blogBlog.content}</textarea>
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>分类</td>
						<td width="80%">${blogBlog.articleName}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>创建人</td>
						<td width="80%">${blogBlog.showName }</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>创建时间</td>
						<td width="80%"><fmt:formatDate type="both"
								value="${blogBlog.addTime }" ></fmt:formatDate></td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>财经采纳</td>
						<td width="80%"><c:if test="${blogBlog.isBest==0 }">否</c:if>
						<c:if test="${blogBlog.isBest==1 }">是</c:if>
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>置顶博文</td>
						<td width="80%"><c:if test="${blogBlog.top==1 }">置顶</c:if>
						<c:if test="${blogBlog.top==0 }">未置顶</c:if>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="hidden"
							name="blogBlog.id" value="${blogBlog.id }"> <input
							class="btn btn-danger" type="button" value="提交" onclick="tiSub()"> <input
							class="btn ml10" type="button" onclick="history.go(-1)"
							value="返回"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

</body>
</html>