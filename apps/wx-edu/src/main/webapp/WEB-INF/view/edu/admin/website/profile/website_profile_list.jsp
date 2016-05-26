<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/base.jsp"%>
<html>
<head>
<title>网站管理</title>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript">
	$(function() {
		if('${type}'=='logo'){
			initUploadify("logoBtn","logoPic","logoUrl","logoQueue","websiteProfile");
		}else if('${type}'=='loadimage'){
			initUploadify("loadImageBtn","loadImagePic","loadImageUrl","loadImageQueue","websiteProfile");
		}else if('${type}'=='courseimage'){
			initUploadify("courseImageBtn","courseImagePic","courseImageUrl","courseImageQueue","websiteProfile");
		}


		$('#icoFile').on('change', function() {
			var fileNames = '';
			$.each(this.files, function() {
				fileNames += '<span class="am-badge">' + this.name + '</span> ';
			});
			$('#file-list').html(fileNames);
		});
	});

	// 更新ico文件
	function updateICO(){
		var icoFile=$("#icoFile").val();
		if(isNotEmpty(icoFile)){
			var fileNames=icoFile.split('.');
			var fileName=fileNames[0];
			var fileNameSuffix=fileNames[1];
			if(!(fileNameSuffix == "ico" || fileNameSuffix == "ICO")) {
				dialogFun("提示","请选择ico格式的图片!",0);
			}else if(fileName.indexOf('favicon')==-1) {
				dialogFun("提示","文件必须命名为favicon",0);
			}else{
				$("#icoForm").submit();
			}
		}else{
			dialogFun("提示","请上传ioc文件",0);
		}
	}

	// 更新logo
	function updateLogo(){
		if(isEmpty($("#logoUrl").val())){
			dialogFun("提示","请上传logo",0);
			return;
		}
		$("#logoForm").submit();
	}

	// 更新加载图片
	function updateLoadImage(){
		if(isEmpty($("#loadImageUrl").val())){
			dialogFun("提示","请上传加载图片",0);
			return;
		}
		$("#loadImageForm").submit();
	}

	// 更新课程默认图片
	function updateCourseImage(){
		if(isEmpty($("#courseImageUrl").val())){
			dialogFun("提示","请上传课程图片",0);
			return;
		}
		$("#courseImageForm").submit();
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">网站管理</strong></div>
</div>
<hr>
<div class="mt20">
	<div class="mt20">
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/web" id="web">基本信息</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/alipay" id="alipay">支付宝</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/weixinsite/find/weixin" id="weixin">微信</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/yee" id="yee">易宝</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/keyword" id="keyword">网站开关配置</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/cc" id="cc">CC视频</a>
		<%--<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/p56" id="p56">56视频</a>--%>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/letv" id="letv">乐视云</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/censusCode" id="censusCode">统计代码</a>
	</div>
	<div class="mt20">
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/ico" id="ico">ico</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/logo" id="">logo</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/loadimage" id="loadimage">加载图片</a>
		<a class="am-btn am-btn-primary" href="${ctx}/admin/websiteProfile/find/courseimage" id="courseimage">课程图片</a>
	</div>

	<%-- 基本信息 --%>
	<c:if test="${type=='web' }">
		<form action="${ctx}/admin/websiteProfile/find/web" method="post" class="am-form">
			<input type="hidden" name="flag" value="flag"/>
			<div class="am-g">
				<div class="mt20 am-scrollable-horizontal">
					<div class="mt10">
						<table class="am-table am-table-bd am-table-striped admin-content-table">
							<thead>
								<tr>
									<th width="30%" class="am-text-center"><span>名称</span></th>
									<th width="70%" class="am-text-center"><span>描述</span></th>
								</tr>
							</thead>
							<tbody id="tabS_web" align="center">
								<tr>
									<td>网站title(网站头部)</td>
									<td>${webSiteMap.web.title}</td>
								</tr>
								<tr>
									<td>网校名称(网站头部)</td>
									<td>${webSiteMap.web.company}</td>
								</tr>
								<tr>
									<td>网站作者</td>
									<td>${webSiteMap.web.author}</td>
								</tr>
								<tr>
									<td>关键词</td>
									<td>${webSiteMap.web.keywords}</td>
								</tr>
								<tr>
									<td>描述</td>
									<td>${webSiteMap.web.description}</td>
								</tr>
								<tr>
									<td>联系邮箱(网站底部)</td>
									<td>${webSiteMap.web.email}</td>
								</tr>
								<tr>
									<td>联系电话(网站底部)</td>
									<td>${webSiteMap.web.phone}</td>
								</tr>
								<tr>
									<td>工作时间(网站底部)</td>
									<td>${webSiteMap.web.workTime}</td>
								</tr>
								<tr>
									<td>版权以及备案号(网站底部)</td>
									<td>${webSiteMap.web.copyright}</td>
								</tr>
								<tr>
									<td colspan="2">
										<button class="am-btn am-btn-danger" type="submit">修 改</button>
										<button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返 回</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</c:if>

	<%-- 支付宝 --%>
	<c:if test="${type=='alipay'}">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div id="tab1" class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/alipay" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									合作者身份PID
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.alipay.alipaypartnerID}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									安全校验码KEY
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.alipay.alipaykey}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									商家邮箱
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.alipay.sellerEmail}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									RSA公钥
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<textarea rows="10" readonly>${webSiteMap.alipay.publickey}</textarea>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									RSA私钥
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<textarea rows="10" readonly>${webSiteMap.alipay.privatekey}</textarea>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									支付宝类型
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="<c:if test="${webSiteMap.alipay.status==0}">企业支付宝</c:if>
									<c:if test="${webSiteMap.alipay.status==1}">个人支付宝</c:if>" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 微信 --%>
	<c:if test="${type=='weixin'}">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/weixinsite/find/weixins" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									Token
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.wxToken}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									AppId
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.wxAppID}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									AppSecret
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.wxAppSecret}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									EncodingAESKey
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.encodingAESKey}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									PayKey
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.wxPayKey}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									MchId
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.wxMchId}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<label class="am-u-sm-4 am-u-md-2 am-text-right">
									移动支付
								</label>
								<div class="am-u-sm-8 am-u-md-6">

								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									mobileAppId
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.mobileAppId}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									mobileMchId
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.mobileMchId}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									mobilePayKey
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${weixinMap.weixin.mobilePayKey}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 易宝 --%>
	<c:if test="${type=='yee'}">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/yee" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									商户编号（p1_MerId）
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.yee.p1_MerId}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									商户密钥（keyValue）
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.yee.keyValue}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 网站功能开关 --%>
	<c:if test="${type=='keyword'}">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/keyword" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<%--<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									邮箱验证
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifyEmail=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifyEmail=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									快钱支付开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifykq=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifykq=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									易宝支付开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.yee=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.yee=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									微信支付开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifywx=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifywx=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									登录开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifyLogin=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifyLogin=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									注册开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifyRegister=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifyRegister=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>--%>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									考试开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifyExam=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifyExam=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									SNS开关
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<select disabled>
										<option <c:if test="${webSiteMap.keyword.verifySns=='ON'}">selected</c:if>>ON</option>
										<option <c:if test="${webSiteMap.keyword.verifySns=='OFF'}">selected</c:if>>OFF</option>
									</select>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- CC视频 --%>
	<c:if test="${type=='cc' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/cc" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									CC appID
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.cc.ccappID}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									CC appKEY
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.cc.ccappKEY}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 56视频 --%>
	<c:if test="${type=='p56' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/p56" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									56 appId
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.p56.p56appID}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									56 appKEY
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.p56.p56appKEY}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 乐视云 --%>
	<c:if test="${type=='letv' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/letv" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									用户唯一标识码
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.letv.user_unique}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									秘钥
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" value="${webSiteMap.letv.secret_key}" class="am-input-sm" readonly/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 统计代码 --%>
	<c:if test="${type=='censusCode' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/find/censusCode" class="am-form">
							<input type="hidden" name="flag" value="flag"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									统计代码
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<textarea rows="6" cols="60" disabled="disabled">${webSiteMap.censusCode.censusCodeString}</textarea>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="submit">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- ICO --%>
	<c:if test="${type=='ico' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/uploadIco" id="icoForm" class="am-form" enctype="multipart/form-data" >
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									ico文件
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<img class="am-radius" src="${ctx}/favicon.ico" width="32px" height="32px"/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									设置新的ico文件
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<span class="am-text-danger">（请美工制作图片的大小标为32*32的ico图片,否则图片会失真）</span>
									<div class="am-form-group am-form-file">
										<button type="button" class="am-btn am-btn-danger am-btn-sm">
											<i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
										<input type="file" name="icoFile" id="icoFile"/>
									</div>
									<div id="file-list"></div>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="button" onclick="updateICO()">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 网站LOGO --%>
	<c:if test="${type=='logo' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/update" id="logoForm" class="am-form">
							<input type="hidden" value="logo" name="type"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									图片
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<img alt="" src="<%=staticImageServer %>${webSiteMap.logo.url}" id="logoPic" />
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									图片地址
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" name="url" id="logoUrl" value="${webSiteMap.logo.url}" class="am-input-sm"/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									&nbsp;
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="file" id="logoBtn"/>
									<div id="logoQueue"></div>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="button" onclick="updateLogo()">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 加载图片 --%>
	<c:if test="${type=='loadimage' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/update" id="loadImageForm" class="am-form">
							<input type="hidden" value="loadimage" name="type"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									图片
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<img alt="" src="<%=staticImageServer %>${webSiteMap.loadimage.url}" id="loadImagePic" class="am-img-thumbnail"/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									图片地址
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" name="url" id="loadImageUrl" value="${webSiteMap.loadimage.url}" class="am-input-sm"/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									&nbsp;
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="file" id="loadImageBtn"/>
									<div id="loadImageQueue"></div>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="button" onclick="updateLoadImage()">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<%-- 课程默认图片 --%>
	<c:if test="${type=='courseimage' }">
		<div class="mt20">
			<div class="am-tabs">
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-active am-in">
						<form action="${ctx}/admin/websiteProfile/update" id="courseImageForm" class="am-form">
							<input type="hidden" value="courseimage" name="type"/>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									图片
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<img alt="" src="<%=staticImageServer %>${webSiteMap.courseimage.url}" id="courseImagePic" class="am-img-thumbnail"/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									图片地址
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="text" name="url" id="courseImageUrl" value="${webSiteMap.courseimage.url}" class="am-input-sm"/>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									&nbsp;
								</div>
								<div class="am-u-sm-8 am-u-md-6">
									<input type="file" id="courseImageBtn"/>
									<div id="courseImageQueue"></div>
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-6">
									<button class="am-btn am-btn-danger" type="button" onclick="updateCourseImage()">修 改</button>
									<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返 回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
</body>
</html>