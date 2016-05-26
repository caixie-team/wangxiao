<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>Redis管理</title>
<script type="text/javascript">
	function submit() {
		var memKey = $("#memKey").val();
		if (memKey == null || memKey == "") {
			dialogFun("提示", "请输入Redis-Key", 0);
			return;
		}
		var memDesc = $("#memDesc").val();
		if (memDesc == null || memDesc == "") {
			dialogFun("提示", "请输入描述", 0);
			return;
		}
		var memKey = $("#memKey").val();
		var memDesc = $("#memDesc").val();
		$.ajax({
			url : "${ctx}/admin/websitemem/add",
			data : {
				"websiteMemcache.memKey" : memKey,
				"websiteMemcache.memDesc" : memDesc
			},
			dataType : "json",
			type : "post",
			cache : true,
			async : false,
			success : function(result) {
				if (!result.success) {
					dialogFun("提示", "该key已存在", 0);
				} else {
					window.location.href = "${ctx}/admin/websitemem/list"
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">Redis管理</strong> / <small>添加Redis</small>
		</div>
	</div>
	<hr />
	<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">Redis基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="?" method="post" id="addMemForm" class="am-form"
				data-am-validator>
				<div class="am-g am-margin-top am-form-group">
					<label for="memKey" class="am-u-sm-4 am-u-md-2 am-text-right"><font
						color="red">*</font>&nbsp;Redis-key</label>
					<div class="am-u-sm-8 am-u-md-4">
						<input type="text" name="websiteRedis.memKey" id="memKey"
							style="width: 450px" class="am-input-sm" required
							placeholder="请填写Redis-key" />
					</div>
					<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
				</div>
				<div class="am-g am-margin-top am-form-group">
					<label for="memDesc" class="am-u-sm-4 am-u-md-2 am-text-right"><font
						color="red">*</font>&nbsp;描述</label>
					<div class="am-u-sm-8 am-u-md-4">
						<input type="text" name="websiteRedis.memDesc" id="memDesc"
							style="width: 450px" class="am-input-sm" required
							placeholder="请填写描述" />
					</div>
					<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
					<div class="am-u-sm-8 am-u-md-4">
						<a class="am-btn am-btn-danger" title="提 交"
							href="javascript:submit()">提 交</a> <a
							class="am-btn am-btn-success" title="返 回"
							href="javascript:history.go(-1)">返 回</a>
					</div>
					<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
