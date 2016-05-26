<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>分类管理</title>
<script type="text/javascript">
function submit(){
	var name = $("#name").val();
	var explain=$("#explain").val();
	if(name.trim()==""){
		dialogFun("添加分类","关键词不能为空",0);
		return;
	}
	if(explain.trim()==""){
		dialogFun("添加分类","请输入说明",0);
		return;
	}
	var type=$("#type").val();
	 var judge=confirm("确定添加？");
	 if(judge==true){
		 $.ajax({
				url:"${ctx}/admin/websiteClassify/add",
				data:{"websiteClassify.name":name,"websiteClassify.explain":explain,"websiteClassify.type":type},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(!result.success){
						dialogFun("添加分类",result.message,0);
					}else{
						window.location.href="${ctx}/admin/websiteClassify/list"
					}
				}
			});
	 }else{
		 return false;
	 }
	//$("#addMemForm").submit();
}
</script>
</head>
<body>

<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong> / <small>添加分类</small></div>
</div>
<hr>

<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">添加分类</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form class="am-form"  method="post">
					<div class="am-g am-margin-top am-form-group">
						<label for="name" class="am-u-sm-4 am-u-md-2 am-text-right">
							关键词
						</label>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" class="am-input-sm" required placeholder="请填写关键词" name="websiteClassify.name" id="name">
						</div>
						<div class="am-hide-sm-only am-u-md-6"><span class="am-text-danger">例：</span>notice</div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="explain" class="am-u-sm-4 am-u-md-2 am-text-right">
							说明
						</label>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" class="am-input-sm" required placeholder="请填写说明" name="websiteClassify.explain" id="explain">
						</div>
						<div class="am-hide-sm-only am-u-md-6"><span class="am-text-danger">例：</span>公告</div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="type" class="am-u-sm-4 am-u-md-2 am-text-right">
							所属类型
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<select name="websiteClassify.type" id="type" required data-am-selected="{btnSize: 'sm'}" style="display: none;">
								<option value="article">资讯</option>
								<option value="teacher">讲师</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
					<div class="am-g am-margin-top" am-form-group>
						<label for="sort" class="am-u-sm-4 am-u-md-2 am-text-right">
							排序值
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" name="websiteClassify.sort" required id="sort" class="am-input-sm" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
						</div>
						<div class="am-u-sm-12 am-u-md-6">由大到小显示</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger ml20"  onclick="submit()">提 交</a>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
