<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>导航添加</title>
</head>
<body>
<div class="am-cf">
  <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">导航管理</strong> / <small>导航添加</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">导航基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form class="am-form" data-am-validator action="${ctx}/admin/website/addNavigate" id="addNavigateForm" method="post">
					<div class="am-g am-margin-top am-form-group">
						<label for="navigateName" class="am-u-sm-4 am-u-md-2 am-text-right">
							导航名称
						</label>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" class="am-input-sm" required placeholder="请填写导航名称" name="websiteNavigate.name" id="navigateName">
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="navigateUrl" class="am-u-sm-4 am-u-md-2 am-text-right">
							跳转链接
						</label>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" class="am-input-sm" required placeholder="请填写跳转链接" name="websiteNavigate.url" id="navigateUrl">
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="newPage" class="am-u-sm-4 am-u-md-2 am-text-right">
							在新页面打开
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<select name="websiteNavigate.newPage" id="newPage" required data-am-selected="{btnSize: 'sm'}" style="display: none;">
								<option value="1">否</option>
								<option value="0">是</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="type" class="am-u-sm-4 am-u-md-2 am-text-right">
							类型
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<select name="websiteNavigate.type" id="type" required data-am-selected="{btnSize: 'sm'}" style="display: none;">
								<option value="INDEX">首页</option>
								<option value="USER">个人中心</option>
								<option value="FRIENDLINK">尾部友链</option>
								<option value="TAB">尾部标签</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top" am-form-group>
						<label for="orderNum" class="am-u-sm-4 am-u-md-2 am-text-right">
							排序
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" name="websiteNavigate.orderNum" required id="orderNum" class="am-input-sm" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
						</div>
						<div class="am-u-sm-12 am-u-md-6">由大到小显示</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
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