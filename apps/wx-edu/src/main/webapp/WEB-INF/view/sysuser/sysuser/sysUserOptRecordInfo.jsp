<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>后台操作记录详细</title>
</head>
<body>
	<!-- 全局公共右内容区  开始 -->
	<!-- 公共右侧样式 -->
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">后台操作记录</strong> / <small>操作记录详细</small>
		</div>
	</div>
	<hr />
	<!-- 公共右侧标题样式 结束-->
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">记录详细</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1"
					class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
					<form class="am-form">
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 序号 </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly="readonly" type="text" class="am-input-sm"
									value="${optRecord.id }" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作时间 </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly type="text" class="am-input-sm"
									value="<fmt:formatDate value="${optRecord.createTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作帐号 </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly type="text" class="am-input-sm"
									value="${optRecord.optAccount }" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作人 </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly type="text" class="am-input-sm"
									value="${optRecord.optUsername }" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作行为 </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly type="text" class="am-input-sm"
									value="${optRecord.optName }" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作对象 </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly type="text" class="am-input-sm"
									value="${optRecord.optObj }" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作IP </label>
							<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
								<input readonly type="text" class="am-input-sm"
									value="${optRecord.optIp }" />
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作前数据 </label>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<textarea readonly>${optRecord.modifyBefore }</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="navigateName"
								class="am-u-sm-4 am-u-md-2 am-text-right"> 操作后数据 </label>
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<textarea readonly>${optRecord.modifyAfter }</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-6">
								<button class="am-btn am-btn-danger" type="button"
									onclick="window.history.go(-1)">返回</button>
							</div>
							<div class="am-u-sm-12 am-u-md-4"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
