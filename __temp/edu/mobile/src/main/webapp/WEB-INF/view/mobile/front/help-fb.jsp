<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>帮助反馈</title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>帮助反馈</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div class="i-box" style="margin-bottom: -50px;">
					<div class="help-fb">
						<section class="tjc-box c-sort-box">
							<section class="c-sort">
								<aside class="hfb-title">
									<span>反馈信息</span>
								</aside>
								<div class="lfb-wrap">
									<p class="lfb-tip-txt">为了更好地解决您反馈的问题，请尽量在出现问题的网络环境下进行反馈：</p>
									<form action="" id="feedBackform">
									<section class="u-account-box">
										<!-- 校验样式是给li加 class="amError"; -->
										<ol class="u-account-set">
											<li>
												<span class="u-a-lab">请写下您的意见和感想吧：</span>
												<textarea id="content" style="width: 97%"></textarea>
											</li>
											<li>
												<span class="u-a-lab">您的联系方式：</span>
												<label class="u-a-inp"><input type="text" id="contact" value="" placeholder="QQ/邮箱/或手机号" required="required"></label>
											</li>
										</ol>
										<section class="lr-btn" style="margin-top: 20px;">
											<a href="javascript:void(0)" onclick="addFeedBack()">一 键 反 馈</a>
										</section>
									</section>
									</form>
								</div>
							</section>
						</section>

						<section class="tjc-box c-sort-box">
							<section class="c-sort">
								<aside class="hfb-title">
									<span>常见问题</span>
								</aside>
								<div class="lfb-wrap">
									<ol class="v-c-kcb">
										<li>
											<span class="list-pointer">&nbsp;</span>
											<section class="v-c-kcb-lis">
												<p>观看课程或直播视频会对网络有要求吗？</p>
											</section>
											<section class="v-c-kcb-time">
												<span>建议你在Wifi环境下观看我们的视频，不建议在2G或网络环境不稳定的情况下观看视频。</span>
											</section>
										</li>
										<li>
											<span class="list-pointer">&nbsp;</span>
											<section class="v-c-kcb-lis">
												<p>观看我们的视频会减少手机话费吗？</p>
											</section>
											<section class="v-c-kcb-time">
												<span>我们的视频属于免费服务，使用过程中产生的流量费用由运营商收取。</span>
											</section>
										</li>
										<li>
											<span class="list-pointer">&nbsp;</span>
											<section class="v-c-kcb-lis">
												<p>为什么有时会无法观看正常视频？</p>
											</section>
											<section class="v-c-kcb-time">
												<span>我们的视频属于免费服务，使用过程中产生的流量费用由运营商收取。</span>
											</section>
										</li>
									</ol>
								</div>
							</section>
						</section>
						<!-- 帮助反馈 -->
					</div>
				</div>
			
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
		})
		function addFeedBack(){
			var content=$("#content").val();
			if(content==null||content==''){
				dialog('反馈提示','请填写内容','',0);
				return;
			}
			var contact=$("#contact").val();
			$.ajax({
	    		url:baselocation+"/mobile/feedback/add",
	    		type : "post", 
	    		data:{"userFeedBack.content":content,"contact":contact},
	    		dataType : "json",
	    		async:false,
	    		success: function (result){
			        if(result.success){
			        	dialog('反馈提示','提交成功','',0);
			        	$('#feedBackform')[0].reset();
			        }
	    		}
	    	});
		}
	</script>
</body>
</html>