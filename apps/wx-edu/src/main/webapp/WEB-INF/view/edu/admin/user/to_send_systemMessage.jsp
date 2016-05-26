<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>发送系统消息</title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript">
KindEditor.ready(function(K) {
	window.EditorObject = K.create('textarea[id="message"]', {
			resizeType  : 1,
	       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
	       allowPreviewEmoticons : false,
	       allowUpload : true,//允许上传
	       syncType : 'auto',
	       urlType : 'domain',//absolute
	       newlineTag :'br',//回车换行br|p
	       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
	       allowFileManager : false,
	       afterBlur:function(){EditorObject.sync();}, 
	       items : ['emoticons']
	});
});
	function sendmessage(){
		var content = $("#message").val();
		if(content==null||content.trim()==""){
			alert("请填写消息内容在发送");
			return false;
		}
		 $.ajax({
             url:"${ctx}/admin/letter/sendJoinGroup",
             type:"post",
             data:{"content":content},
             dataType:"json",
             success:function(result){
             	if(result.message=='success'){
             		KindEditor.html('#message', '');
             		 alert("发送成功");
             	}
             }
         });
	}
</script>

</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>发送系统消息</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">发送系统消息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form">
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-3 am-text-right">
							系统消息要发送的内容
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<textarea cols="80" rows="5" id="message"></textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<a href="javascript:void(0)" title="发 送" onclick="sendmessage()" class="am-btn am-btn-danger">发 送</a>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>