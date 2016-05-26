<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>修改问题</title>
<script type="text/javascript" src="${ctximg}/static/edu/js/common/uploadify.js" ></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">

$(function(){
	initKindEditor_addblog('content','576px','400px');
})

	function updateSugSuggest(){
		var title = $("#title").val();
		var content = $("#content").val();
		
		if(title.trim()==""){
			dialogFun("问题修改 ","标题不能为空",0);	
			return ;
		}
		if(content.trim()==""){
			dialogFun("问题修改 ","内容不能为空",0);	
			return ;
		}
		
		if(title.length>24){
			dialogFun("问题修改 ","请输入24个字符以内的标题",0);	
			return ;
		}
		
		/* if(KE.count("#content","text") >50000){
			dialogFun("问题修改 ","请输入50000个字符以内的内容",0);	
			return ;
		} */
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/sug/updateSugSuggest",
			data:{"id":"${sugSuggest.id }","title":title,"content":content},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					$("#title").val("");
					KindEditor.html('#content', "");// 编辑器清空
					dialogFun("问题修改 ","修改成功",5,"${ctx}/admin/sug/toSugSuggestList");	
				}
			}
		});
	}
</script>

</head>
<body  >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">问题管理</strong> / <small>问题修改</small></div>
</div>
<hr/>
<div class="mt20">
<div  class="am-tabs">
	<ul class="am-tabs-nav am-nav am-nav-tabs">
		<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
	</ul>
	<div class="am-tabs-bd">
   	<div id="tab1" class="am-tab-panel am-fade am-active am-in">
   	<form action="${ctx}/admin/sug/updateSugSuggest" id="updateSugSuggest" method="post" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="id" value="${sugSuggest.id }"/>
   		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				问题标题
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="title" value="${sugSuggest.title }" id="title"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				问题内容
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<textarea name="content" id="content" style="width: 48%;height: 68px;">${sugSuggest.content }</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-5 am-text-right">
				&nbsp;
			</label>
			<div class="am-u-sm-8 am-u-md-6" style="align:center">
				<a href="javascript:updateSugSuggest()" title="修 改" class="am-btn am-btn-danger">修 改</a>
				<a class="am-btn am-btn-success"  href="javascript:history.go(-1);">返回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp; </div>
		</div>
		</form>
	</div>
	</div>
</div>
</div>
</body>
</html>