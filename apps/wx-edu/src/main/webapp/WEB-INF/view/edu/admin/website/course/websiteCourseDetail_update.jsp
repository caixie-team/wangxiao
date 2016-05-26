<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>推荐课程修改</title>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">推荐课程管理</strong> / <small>推荐课程修改</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">推荐课程基本属性（<i class="am-text-danger">*</i>为必填项）</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/website/updateWebsiteCourseDetail" method="post" class="am-form" data-am-validator>.
					<input type="hidden" name="websiteCourseDetail.id" value="${websiteCourseDetailDTO.id}"/>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i>课程名称
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							${websiteCourseDetailDTO.courseName }
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i>分类
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							${websiteCourseDetailDTO.recommendName }
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="orderNum" class="am-u-sm-4 am-u-md-2 am-text-right">
							<i class="am-text-danger">*</i>排序值
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="websiteCourseDetail.orderNum" value="${websiteCourseDetailDTO.orderNum }" onkeyup="this.value=this.value.replace(/\D/g,'')" id="orderNum" required/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提 交</button>
							<button class="am-btn am-btn-danger" type="button" onclick="history.go(-1);">返 回</button>
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