<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>添加积分模板</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">积分模板管理</strong> / <small>添加积分模板</small></div>
</div>
<hr>

<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">积分模板</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/integral/template/add" class="am-form" method="post" data-am-validator>
					<div class="am-g am-margin-top am-form-group">
						<label for="name" class="am-u-sm-4 am-u-md-2 am-text-right">
							模板名称
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="name" class="am-input-sm" required placeholder="请填写模板名称" name="userIntegralTemplate.name">
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="type" class="am-u-sm-4 am-u-md-2 am-text-right">
							使用类型
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<select data-am-selected="{btnSize: 'sm'}" style="display: none;" required id="type" name="userIntegralTemplate.type">
								<option value="0">学员</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
					</div>


					<div class="am-g am-margin-top am-form-group">
						<label for="keyword" class="am-u-sm-4 am-u-md-2 am-text-right">
							使用场景
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="keyword" class="am-input-sm" required placeholder="请填写使用场景" name="userIntegralTemplate.keyword">
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="showScore" class="am-u-sm-4 am-u-md-2 am-text-right">
							功能分数
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input id="showScore" type="text" name="knowledge.showScore" onkeyup="this.value=this.value.replace(/\D/g,'')" value="0" class="am-input-sm" placeholder="请填写功能分数" required>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
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
