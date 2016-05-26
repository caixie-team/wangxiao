<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<head>
	<title>站内信收件箱</title>
	<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/weibo/weibo.js?v=<%=version%>" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	function delblackList(id){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/black/delBlackList",
			data:{"blackList.cusBlackListId":id},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					//alert(result.message);
					alert("成功");
				}
			}
		});
	}
	KindEditor.ready(function(K) {
		window.EditorObject = K.create('textarea[id="weiBoContent"]', {
			resizeType:1,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       syncType : 'auto',
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
		       allowFileManager : false,
		       /* afterFocus:function(){editor.sync();}, */
		       afterBlur:function(){EditorObject.sync();}, 
		       items : ['emoticons']
		});
	});
	</script>
</head>     
<body>
	<div class="e-wrap">
<%-- 		<jsp:include page="/WEB-INF/view/weibo/header.jsp"/> --%>
		<!-- /e-header -->
		<!-- e-main begin -->
		<section class="e-main">
			<div class="w1000">
			<textarea rows="3" cols="45" id="weiBoContent"></textarea>
			<input type="button" onclick="addWeiBo()" value="发 布"/>
			</div>
		</section>
		<!-- e-main end -->
		<!-- e-footer begin -->
		<!-- e-footer end -->
		<!-- e-footer end -->
	</div>
</body>
