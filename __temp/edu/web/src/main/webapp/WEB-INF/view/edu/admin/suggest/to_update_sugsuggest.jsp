<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>修改问题</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript">

var KE;
KindEditor.ready(function(K) {KE=K;});
//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="content"]', {
			resizeType:1,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       syncType : 'auto',
		       width:'500px',
		       minWidth:'10px',
		      minHeight:'10px',
			   height:'200px',
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
		       allowFileManager : false,
		       /* afterFocus:function(){editor.sync();}, */
		       afterBlur:function(){editor.sync();}, 
		       items : ['fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
		       afterChange : function() {
				}
		});
	
	});
	function updateSugSuggest(){
		var title = $("#title").val();
		var content = $("#content").val();
		
		if(title.trim()==""){
			alert("标题不能为空");
			return ;
		}
		if(content.trim()==""){
			alert("内容不能为空");
			return ;
		}
		
		if(title.length>24){
			alert("请输入24个字符以内的标题");
			return ;
		}
		
		if(KE.count("#content","text") >50000){
			alert("请输入50000个字符以内的内容");
			return ;
		}
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/sug/updateSugSuggest",
			data:{"id":"${sugSuggest.id }","title":title,"content":content},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					alert("修改成功");
					$("#title").val("");
					KindEditor.html('#content', "");// 编辑器清空
                    window.location.href="${ctx}/admin/sug/toSugSuggestList";
				}
			}
		});
	}
</script>

</head>
<body  >
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>问题管理</span> &gt; <span>问题修改</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/sug/updateSugSuggest" id="updateSugSuggest" method="post">
	<input id="pageCurrentPage" type="hidden" name="id" value="${sugSuggest.id }"/>
	
		<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>
						<div class="optionList">
							<a class="btn btn-danger" href="javascript:history.go(-1);">返回</a>
						</div>
					</caption>
					<thead>
						<tr>
							<th colspan="2" align="left"><span>修改问题<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;问题标题</td>
							<td>
								<input type="text" name="title" value="${sugSuggest.title }" id="title"/>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;问题内容</td>
							<td>
								<textarea name="content" id="content" style="width: 48%;height: 68px;">${sugSuggest.content }</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<a href="javascript:updateSugSuggest()" title="修 改" class="btn btn-danger">修 改</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div><!-- /commonWrap -->
</div>
<!-- /tab2 end-->
</body>
</html>