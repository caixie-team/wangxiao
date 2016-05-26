<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE>
<html>
<head>
	<title>添加章节</title>
<script type="text/javascript">
	//提交方法
	function formSubmit(){
		if(formCheck()){
			$.ajax({
				url : "${ctx}/admin/course/addcourse",
				data : $("#addArticleForm").serialize(),
				type : "post",
				dataType : "json",
				async:false,
				success : function(result) {
					if (result.success) {
						dialogFun("添加章节","添加成功",1);
						window.opener.location.href='${ctx}/admin/kpoint/list/${courseId}';
						window.close();
					}else{
						dialogFun("添加章节","系统繁忙,请稍后重试",0);
					}
				}
			});
	    }
	}

	function formCheck(){
		if($("#name").val()==null || $("#name").val()==''){
			dialogFun("添加章","名称不能为空",0);
			return false;
		}
		return true;
	}

</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课时管理</strong> / <small>章节添加</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form id="addArticleForm" class="am-form">
					<input type="hidden" name="courseKpoint.courseId" value="${courseId}"/>
					<input type="hidden" name="courseKpoint.type" value="1" />
					<input type="hidden" name="courseKpoint.sort" value="-1"/>
					<input type="hidden" name="courseKpoint.parentId" value="0"/>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							名称
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="courseKpoint.name" value="${courseKpoint.name }" id="name" class="am-input-sm" />
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">

						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="提 交"  href="javascript:formSubmit()">提 交</a>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:window.close();">返 回</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
