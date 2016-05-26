<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改章</title>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">视频树</strong> / <small>编辑章</small>
		</div>
	</div>
	<hr>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form class="am-form" data-am-validator>
						<div class="am-g am-margin-top am-form-group">
							<label for="name" class="am-u-sm-4 am-u-md-2 am-text-right"> 章标题 </label>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="text" class="am-input-sm" required placeholder="请填写章标题" value="${courseKpoint.name}" id="name">
							</div>
							<div class="am-hide-sm-only am-u-md-4">
								<font class="am-text-danger">*</font>&nbsp;必填
							</div>
						</div>
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-2 am-text-right"> &nbsp; </label>
							<div class="am-u-sm-8 am-u-md-6">
								<button class="am-btn am-btn-warning" type="button" onclick="updateChapter()">保存</button>
								<button class="am-btn am-btn-secondary" type="button" onclick="cancel()">取消</button>
							</div>
							<div class="am-hide-sm-only am-u-md-4"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function updateChapter(){
		if(submitForm()){
			var name = $("#name").val();
			$.ajax({
				url:"${ctx}/admin/kpoint/updatekopintname/"+${courseKpoint.id},
				data:{"name":name},
				type:"post",
				dataType:"json",
				success:function(result){
					window.opener.location.href='${ctx}/admin/kpoint/list/'+${courseId};
					window.close();
				}
			});
		}
	}
	function cancel(){
		window.close();
	}
	//验证表单
	function submitForm(){
		if($("#name").val()==null || $("#name").val()==''){
			dialogFun("编辑章","标题不能为空",0);
			return false;
		}
		return true;
	}
</script>
</body>
</html>
