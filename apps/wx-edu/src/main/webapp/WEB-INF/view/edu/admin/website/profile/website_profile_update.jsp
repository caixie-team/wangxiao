<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>网站配置管理</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">网站配置管理</strong> / <small>修改网站配置</small></div>
</div>
<hr>
	<div class="mt20">
		<div  class="am-tabs">
			<div class="am-tabs-bd">
				<div class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
					<form action="${ctx }/admin/websiteProfile/update" method="post" class="am-form">
						<input type="hidden" name="type" value="${type}"/>
						<%-- 基本信息 --%>
						<c:if test="${type=='web' }">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									网站title(网站头部)
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="title" value="${webSiteMap.web.title}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									网站title(网站头部)
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="title" value="${webSiteMap.web.title}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									网校名称(网站头部)
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="company" value="${webSiteMap.web.company}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									网站作者
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="author" value="${webSiteMap.web.author}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									关键词
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="keywords" value="${webSiteMap.web.keywords}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									描述
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="description" value="${webSiteMap.web.description}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									联系邮箱
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="email" value="${webSiteMap.web.email}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									24小时客服服务热线
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="phone" value="${webSiteMap.web.phone}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									工作时间
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="workTime" value="${webSiteMap.web.workTime}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									版权以及备案号(网站底部)
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="copyright" value="${webSiteMap.web.copyright}">
								</div>
							</div>
						</c:if>

						<%-- 支付宝 --%>
						<c:if test="${type=='alipay' }">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									合作者身份PID
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="alipaypartnerID" value="${webSiteMap.alipay.alipaypartnerID}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									安全校验码key
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="alipaykey" value="${webSiteMap.alipay.alipaykey}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									商家邮箱
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="sellerEmail" value="${webSiteMap.alipay.sellerEmail}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									RSA公钥
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<textarea rows="10" name="publickey">${webSiteMap.alipay.publickey}</textarea>
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									RSA私钥
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<textarea rows="10" name="privatekey">${webSiteMap.alipay.privatekey}</textarea>
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									支付宝选择
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<label class="am-radio">
										<input type="radio" data-am-ucheck="" value="0" name="status" <c:if test="${webSiteMap.alipay.status==0}">checked='checked'</c:if> class="am-ucheck-radio"><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span>
										企业
									</label>
									<label class="am-radio">
										<input type="radio" data-am-ucheck="" value="1" name="status"  <c:if test="${webSiteMap.alipay.status==1}">checked='checked'</c:if> class="am-ucheck-radio"><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span>
										个人&nbsp;&nbsp;&nbsp;&nbsp;<span class="am-text-danger">个人支付宝需定制</span>
									</label>
								</div>
							</div>
						</c:if>

						<%-- 易宝 --%>
						<c:if test="${type=='yee' }">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									商户编号（p1_MerId）
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="p1_MerId" value="${webSiteMap.yee.p1_MerId}">
								</div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-3 am-text-right">
									商户密钥（keyValue）
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="keyValue" value="${webSiteMap.yee.keyValue}">
								</div>
							</div>
						</c:if>

						<%-- 网站功能开关 --%>
						<c:if test="${type=='keyword'}">
							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									手机验证
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifyPhone" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifyPhone=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifyPhone=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									邮箱验证
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifyEmail" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifyEmail=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifyEmail=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									开启敏感词过滤
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifySensitive" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifySensitive=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifySensitive=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									支付宝开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifyAlipay" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifyAlipay=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${websitemap.keyword.verifyAlipay=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									快钱支付开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifykq" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifykq=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifykq=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									易宝开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="yee" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.yee=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.yee=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									微信支付开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifywx" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifywx=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifywx=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									登录开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifyLogin" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifyLogin=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifyLogin=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top undis">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									注册开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifyRegister" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifyRegister=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifyRegister=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									考试开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifyExam" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifyExam=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifyExam=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									SNS开关
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<select name="verifySns" data-am-selected="{btnSize: 'sm'}" style="display: none;">
										<c:if test="${webSiteMap.keyword.verifySns=='ON'}">
											<option value="ON">ON</option>
											<option value="OFF">OFF</option>
										</c:if>
										<c:if test="${webSiteMap.keyword.verifySns=='OFF'}">
											<option value="OFF">OFF</option>
											<option value="ON">ON</option>
										</c:if>
									</select>
								</div>
							</div>
						</c:if>

						<%-- CC视频 --%>
						<c:if test="${type=='cc'}">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									CC appID
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="ccappID" value="${webSiteMap.cc.ccappID}">
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									CC appKEY
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="ccappKEY" value="${webSiteMap.cc.ccappID}">
								</div>
							</div>
						</c:if>

						<%-- 56视频 --%>
						<c:if test="${type=='p56'}">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									56 appId
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="p56appID" value="${webSiteMap.p56.p56appID}">
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									56 appKEY
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="p56appKEY" value="${webSiteMap.p56.p56appKEY}">
								</div>
							</div>
						</c:if>

						<%-- 乐视云 --%>
						<c:if test="${type=='letv'}">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									用户唯一标识码
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="user_unique" value="${webSiteMap.letv.user_unique}">
								</div>
							</div>

							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									密钥
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<input type="text" class="am-input-sm" name="secret_key" value="${webSiteMap.letv.secret_key}">
								</div>
							</div>
						</c:if>

						<%-- 统计代码 --%>
						<c:if test="${type=='censusCode'}">
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">
									统计代码
								</div>
								<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
									<textarea rows="6" cols="60"  name="censusCodeString">${webSiteMap.censusCode.censusCodeString}</textarea>
								</div>
							</div>
						</c:if>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								&nbsp;
							</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<button class="am-btn am-btn-danger" title="提 交" onclick="submit()">提 交</button>
								<button class="am-btn am-btn-danger" title="返 回" onclick="history.go(-1)">返 回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
