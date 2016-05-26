<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8">
<script type="text/javascript"
	src="<%=imagesPath%>/static/edu/js/front/course/course_customer.js"></script>
<title>用户意见反馈</title>
<script type="text/javascript">
	$(function() {
		$(".changesClass").keyup(function() {
			$(this).next('span').html('');
		});
		var exState = '${exState}';
		var pageNo = '${queryCustomCourseCondition.currentPage}';
		if (exState == '1') {
			$("#qitEmId").removeClass('c-c-down');
			$("#qitEmId").addClass('c-c-ud');
			$(".c-c-body").show();
		}
	});
	//提交反馈新行
	function sumbitFeed() {
		var content = $("#content").val();
		if (content == "") {
			$(".contentErrorClass")
					.html('<em class="icon18 newIcon18Cs"></em>');
			return false;
		}
		var mobile = $("#mobile").val();
		var qq = $("#qq").val();
		var email = $("#email").val();
		var name = $("#name").val();
		$.ajax({
			url : "${ctx}/front/addfreeback",
			data : {
				"userFeedback.content" : content,
				"userFeedback.mobile" : mobile,
				"userFeedback.qq" : qq,
				"userFeedback.email" : email,
				"userFeedback.name" : name,
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(result) {
				if (result.success == true) {
					dialog('提示', '反馈信息已经添加成功', 4);
					$("#mobile,#qq,#email,#name,#content").val('');
				} else {
					dialog('提示', '系统繁忙，请稍后操作', 1);
				}
			}
		})
	}
</script>
<style type="text/css">
.cssFrom {
	margin: -1px -25px 7px 4px;
}
</style>
</head>
<body>
	<!-- /用户反馈 -->
	<div class="custom-course-wrap">
		<section class="w1000">
			<div class="pathwray">
				<ol class="clearfix c-master f-fM fsize14">
					<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
					<li><span>用户反馈</span></li>
				</ol>
			</div>
			<div class="of">
				<section class="mt20 mb20"></section>
				<section class="custom-course">
					<div>
						<div class="c-c-head of">
							<span class="fr c-c-up-down hand" ></span>
							<h4 class="unFw">
								<em class="icon18 c-c-icon vam mr5">&nbsp;</em><font
									class="c-666 fsize14 vam">请填写你要反馈的信息 （<tt class="c-orange">*</tt>为必填项）
								</font>
							</h4>
						</div>
						<form action="${ctx}/front/addfreeback" id="freeBack" method="post">
							<div class="c-c-body">
								<section class="clearfix">
									<div>
										<ul class="c-c-li">
											<li>
												<p>
													<span><font style="color: red">*</font>反馈信息:</span>
												</p> 
												<textarea id="content" class="changesClass" name="userFeedback.content" style="width: 94%"></textarea> 
												<span class="contentErrorClass"></span>
											</li>
										</ul>
									</div>
								</section>
								<section class="c-c-b-tip">
									<span>反馈信息：我希望收到反馈消息的通知 </span>
								</section>
								<section class="mt30">
									<section class="clearfix">
										<div class="fl w50pre">
											<ul class="c-c-li">
												<li>
													<p>
														<span>手机号码:</span>
													</p> 
													<input type="text" class="changesClass" id="mobile" name="userFeedback.mobile" /> 
													<span class="mobileErrorClass"></span>
												</li>
											</ul>
										</div>
										<div class="fr w50pre">
											<div class="ml20">
												<ul class="c-c-li">
													<li>
														<p>
															<span>电子邮箱:</span>
														</p> <input class="changesClass" type="text" id="email"
														name="userFeedback.email" /> <span
														class="emailErrorClass"></span>
													</li>
												</ul>
											</div>
										</div>
										<div class="fl w50pre">
											<ul class="c-c-li">
												<li>
													<p>
														<span>QQ:</span>
													</p> <input type="text" class="changesClass" id="qq"
													name="userFeedback.qq" /> <span class="mobileErrorClass"></span>
												</li>
											</ul>
										</div>
										<div class="fr w50pre">
											<div class="ml20">
												<ul class="c-c-li">
													<li>
														<p>
															<span>姓名:</span>
														</p> <input class="changesClass" type="text" id="name"
														name="userFeedback.name" /> <span class="emailErrorClass"></span>
													</li>
												</ul>
											</div>
										</div>
									</section>
								</section>
								<section class="mt20 tac">
									<label><input type="button" onclick="sumbitFeed()"
										value="提交反馈" class="c-c-sub-btn"></label>
								</section>
							</div>
						</form>
					</div>
				</section>
			</div>
		</section>
		<div id="intro"></div>
	</div>
</body>
</html>
