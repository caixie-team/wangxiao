<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>考试系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/exam/images/exam-img/animate.css">
<style type="text/css">
	.e-footer {margin-top: 0;}
</style>
</head>
<body>
	<!-- 考试专题 开始 -->
	<div id="ei">
		<article class="ei-wrap-3 of">
			<div class="w1000">
				<section class="ei-box-3 eb-7 pr">
					<img src="/static/exam/images/exam-img/ei-txt-6.png" width="500" height="80" class="ei-txt-6 pa" />
					<a href="/quest/examitem" title="点击进入考试" class="go-test-btn pa" style="top: 15em;left: 35em;">&nbsp;</a>
					<img src="/static/exam/images/exam-img/ei-pic-10.png" width="140" height="180" class="ei-pic-10 pa" />
					<img src="/static/exam/images/exam-img/ei-pic-11.png" width="140" height="180" class="ei-pic-11 pa" />
					<img src="/static/exam/images/exam-img/ei-pic-12.png" width="140" height="180" class="ei-pic-12 pa" />
				</section>
			</div>
		</article>
		<article class="ei-wrap-f of">
			<div class="w1000">

			</div>
		</article>
	</div>
	<!-- 考试专题 结束 -->
	<script type="text/javascript">
		$(function() {
			var _ar = $("#ei>article"),
				_eb1 = _ar.find(".eb-1"),
				_eb2 = _ar.find(".eb-2"),
				_eb3 = _ar.find(".eb-3"),
				_eb4 = _ar.find(".eb-4"),
				_eb5 = _ar.find(".eb-5"),
				_eb6 = _ar.find(".eb-6"),
				_eb7 = _ar.find(".eb-7"),
				_eb8 = _ar.find(".eb-8");
			var aFun = function() {
				var _wScoll = $(document).scrollTop();
				_ar.addClass("animate-enter");
				if (_wScoll <= 0) {
					_eb1.find(".ei-ms-2").addClass("animated fadeInLeft");
					_eb1.find(".ei-ms-3").addClass("animated fadeInLeft delay0d5s");
					_eb1.find(".ei-ms-4").addClass("animated fadeInLeft delay1s");
					_eb1.find(".ei-ms-1").addClass("animated scale originbottomRight delay1d5s");
					_eb1.find(".ei-pic-1").addClass("animated fadeInRight");
					_eb1.find(".ei-lr-btn").addClass("animated fadeInRight delay0d5s");
				} else if (_wScoll >=380 && _wScoll < 960) {
					_eb2.find(".ei-txt-1").addClass("animated fadeInLeft");
					_eb2.find(".go-test-btn").addClass("animated fadeInLeft delay0d5s");
					_eb2.find(".ei-pic-3").addClass("animated scaleSmall originbottomLeft");
					_eb2.find(".ei-pic-2").addClass("animated scaleBig delay1d5s");
				} else if (_wScoll >=960 && _wScoll < 1540) {
					_eb3.find(".ei-pic-4").addClass("animated fadeInLeft");
					_eb3.find(".ei-txt-2").addClass("animated fadeInRight delay0d5s");
					_eb3.find(".go-test-btn").addClass("animated fadeInRight delay1s");
				} else if (_wScoll >=1540 && _wScoll < 2060) {
					_eb4.find(".ei-txt-3").addClass("animated fadeInLeft");
					_eb4.find(".go-test-btn").addClass("animated fadeInLeft delay0d5s");
					_eb4.find(".ei-pic-5").addClass("animated fadeInUp");
				} else if (_wScoll >=2060 && _wScoll < 2640) {
					_eb5.find(".ei-pic-6").addClass("animated fadeInLeft");
					_eb5.find(".ei-pic-7").addClass("animated scale originbottomRight delay0d5s");
					_eb5.find(".ei-txt-4").addClass("animated fadeInRight");
					_eb5.find(".go-test-btn").addClass("animated fadeInRight delay0d5s");
				} else if (_wScoll >=2640 && _wScoll < 3100) {
					_eb6.find(".ei-txt-5").addClass("animated fadeInLeft");
					_eb6.find(".go-test-btn").addClass("animated fadeInLeft delay0d5s");
					_eb6.find(".ei-pic-8").addClass("animated scaleSmall originbottomLeft");
					_eb6.find(".ei-pic-9").addClass("animated scaleBig delay1s");
				} else if (_wScoll >=3100 && _wScoll < 3280) {
					_eb7.find(".ei-txt-6").addClass("animated fadeInLeft");
					_eb7.find(".go-test-btn").addClass("animated fadeInDown delay0d5s");
					_eb7.find(".ei-pic-10,.ei-pic-11,.ei-pic-12").addClass("animated scale originTopLeft");
				} else if (_wScoll >=3280 && _wScoll < 3480) {
					_eb8.find(".ei-txt-7").addClass("animated fadeInRight");
					_eb8.find(".ios-and-dl,.ios-and-dl").addClass("animated scale originTopLeft");
				}
			};
			aFun();
			$(window).bind("scroll" , aFun);
		})
	</script>
</body>
</html>