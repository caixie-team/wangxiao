<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的问答</title>
</head>
<body>
	<article class="uc-m-content mb50">
		<header class="uc-com-title">
			<span>我的问答</span>
		</header>
		<div class="u-title-box">
			<ol class="js-tap clearfix">
				<li class="current"><a href="javascript:void(0)" onclick="getProblem(this,'mysug')" title="我的提问">我的提问</a></li>
				<li><a href="javascript:void(0)" title="我的回答" onclick="getProblem(this,'mysughd')" style="border: none;">我的回答</a></li>
			</ol>
		</div>
		<div class="i-box questlist"></div>
	</article>
	<script type="text/javascript">
		$(function() {
			var flag = '${flag}';
			if(flag=='mysughd'){
				$(".js-tap li").eq(1).find("a").click();
			}else{
				$(".js-tap li").eq(0).find("a").click();
			}
		});
		function getProblem(obj, status) {
			if (status == 'mysughd') {
				ajaxPage("/uc/ajax/questlist", "&flag=mysughd", 1, mysugResult);
			} else {
				ajaxPage("/uc/ajax/questlist", "&flag=mysug", 1, mysugResult);
			}
			$(obj).parent("li").siblings().removeClass("current");
			$(obj).parent("li").addClass("current");
		}
		function mysugResult(result) {
			$(".questlist").html(result);
		}
		function cCardFun() {
			$(".c-caed-body>tr>td>em").each(function() {
				var _this = $(this), _cont = _this
						.siblings(".c-csrd-m-wrap");
				_this.click(function() {
					if (_cont.is(":hidden")) {
						_cont.show();
						_this.addClass("cou-arrow-up");
						_this.parent().parent().siblings().find(
								".c-csrd-m-wrap").hide();
					} else {
						_cont.hide();
						_this.removeClass("cou-arrow-up");
					}
				});
			});
		}
	</script>
</body>
</html>