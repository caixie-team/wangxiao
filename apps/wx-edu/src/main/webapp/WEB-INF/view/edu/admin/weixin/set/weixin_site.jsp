<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>微信配置</title>

	<script type="text/javascript">
		function doUpdate(){
			if($("#wxToken").val()==null||$("#wxToken").val()==""){
				dialogFun("微信配置","token不能为空");
			}
			if($("#wxAppID").val()==null||$("#wxAppID").val()==""){
				dialogFun("微信配置","appId不能为空");
				alert("appId不能为空");
			}
			if($("#wxAppSecret").val()==null||$("#wxAppSecret").val()==""){
				dialogFun("微信配置","appSecret不能为空");
				alert("appSecret不能为空");
			}
			$.ajax({
				url:"${ctx}/admin/weixinsite/update",
				type:"post",
				data:{"wxToken":$("#wxToken").val(),"wxAppID":$("#wxAppID").val(),"wxAppSecret":$("#wxAppSecret").val(),"wxMchId":$("#wxMchId").val(),"wxPayKey":$("#wxPayKey").val(),
					"encodingAESKey":$("#encodingAESKey").val(),"mobileAppId":$("#mobileAppId").val(),"mobileMchId":$("#mobileMchId").val(),"mobilePayKey":$("#mobilePayKey").val(),},
				dataType:"json",
				success:function(result){
					if(result.message=="true"){
						dialogFun("微信设置","修改成功",5,"${ctx}/admin/weixinsite/find/weixin");
					}
				}
			});
		}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>开发者配置</span> </h4>
	</div>
	<div class="mt20">
		<div  class="am-tabs">
			<div class="am-tabs-bd">
				<div class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
					<form class="am-form">
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								Token
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="wxToken" value="${weixinMap.weixin.wxToken}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								AppId
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="wxAppID" value="${weixinMap.weixin.wxAppID}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								AppSecret
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="wxAppSecret" value="${weixinMap.weixin.wxAppSecret}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								EncodingAESKey
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="encodingAESKey" value="${weixinMap.weixin.encodingAESKey}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								PayKey
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="wxPayKey" value="${weixinMap.weixin.wxPayKey}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								MchId
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="wxMchId" value="${weixinMap.weixin.wxMchId}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								移动支付
							</label>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								mobileAppId
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="mobileAppId" value="${weixinMap.weixin.mobileAppId}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								mobileMchId
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="mobileMchId" value="${weixinMap.weixin.mobileMchId}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								mobilePayKey
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input type="text" id="mobilePayKey" value="${weixinMap.weixin.mobilePayKey}" class="am-input-sm"/>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								&nbsp;
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<a class="am-btn am-btn-danger" title="提 交" href="javascript:doUpdate()">提 交</a>
								<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>