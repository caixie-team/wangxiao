<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
<script type="text/javascript">
	function updateSubmit(){
		if(isNaN($("#orderNum").val())){
			alert("课程数量只能为数字");
			return;
		}
		$("#updateWebsiteCourseDetailForm").submit();
	}
</script>
</head>
<body>

	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">推荐课程管理</strong> / <small>推荐课程修改</small></div>
	</div>
	<hr>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">推荐课程基本属性</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form action="${ctx}/admin/appWebsite/updateWebsiteCourseDetail" method="post" id="updateWebsiteCourseDetailForm" class="am-form" data-am-validator>
						<input type="hidden" name="appWebsiteCourseDetail.id" value="${appWebsiteCourseDetailDTO.id}"/>
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								课程名称
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" readonly value="${appWebsiteCourseDetailDTO.courseName }">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								分类
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" readonly value="${appWebsiteCourseDetailDTO.recommendName }">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top am-form-group">
							<label for="orderNum" class="am-u-sm-4 am-u-md-2 am-text-right">
								排序值
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" name="appWebsiteCourseDetail.orderNum" required onkeyup="this.value=this.value.replace(/\D/g,'')" value="${appWebsiteCourseDetailDTO.orderNum }" id="orderNum">
							</div>
							<div class="am-hide-sm-only am-u-md-6"><span class="am-text-danger">倒序</span> </div>
						</div>
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-2 am-text-right">
								&nbsp;
							</label>
							<div class="am-u-sm-8 am-u-md-4">
								<a class="am-btn am-btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
								<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>