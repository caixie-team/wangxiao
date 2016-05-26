<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的课程</title>
<script type="text/javascript">
// 课程卡激活
function activationCard(){
	var cardCode = $("#cardCode").val();
	var cardCodePwd = $("#cardCodePwd").val();
	if(cardCode == ''){
		dialog("激活提示", "课程卡号不能为空", "", 1);
		return;
	}
	if(cardCodePwd == ''){
		dialog("激活提示", "课程卡密码不能为空", "", 1);
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "${ctx}/mobile/course/activationcard/1",
		data : {"cardCode.cardCode":cardCode, "cardCode.cardCodePassword":cardCodePwd},
		async : false,
		success : function(result) {
			if(result.message == ""){
				dialog("激活提示", "您的课程卡已激活", "/mobile/uc/home", 1);
			} else if(result.message == "typeError"){
				dialog("激活提示", "您输入的信息类型不匹配，请重新输入", "/mobile/uc/course/exchange", 1);
			} else if(result.message == "passwordError"){
				dialog("激活提示", "你输入的卡号或密码错误，请重新输入", "/mobile/uc/course/exchange", 1);
			} else if(result.message == "alreadyUse"){
				dialog("激活提示", "该课程卡已被使用，请重新输入", "/mobile/uc/course/exchange", 1);
			} else if(result.message == "close"){
				dialog("激活提示", "您的课程卡已关闭，请重新输入", "/mobile/uc/course/exchange", 1);
			} else if(result.message == "overDue"){
				dialog("激活提示", "您的课程卡已过期，请重新输入", "/mobile/uc/course/exchange", 1);
			} else if(result.message == "dateError"){
				dialog("激活提示", "您的课程卡不在有效期内，请重新输入", "/mobile/uc/course/exchange", 1);
			} else {
				dialog("激活提示", "您的请求有误，请重新输入", "/mobile/uc/course/exchange", 1);
			}
		}
	})
}
</script>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/uc/home" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>课程卡兑换</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div class="i-box">
			<!-- body main -->
			<section class="animated fadeIn">
				<div class="help-fb">
					<section class="tjc-box c-sort-box">
						<section class="c-sort">
							<aside class="hfb-title"> <span>有新的课程卡？</span> </aside>
							<div>
								<svg width="100px" height="100px" viewBox="0 0 1024 1024" enable-background="new 0 0 100 100" xml:space="preserve">
								  <path fill="#0A59C9" d="M786.171904 179.222272 555.685888 179.222272c-6.86592 0-12.388352-5.537792-12.388352-12.372992 0-6.849536 5.522432-12.393472 12.388352-12.393472l230.486016 0c6.851584 0 12.388352 5.542912 12.388352 12.393472C798.559232 173.68448 793.023488 179.222272 786.171904 179.222272zM877.50144 612.930304 147.187712 612.930304c-43.062272 0-78.117888-35.033088-78.117888-78.09536l0-470.375424c0-43.026432 35.055616-78.08512 78.117888-78.08512l730.313728 0c43.058176 0 78.08 35.058688 78.08 78.08512L955.58144 534.835968C955.58144 577.897216 920.559616 612.930304 877.50144 612.930304zM147.187712 550.952704l730.313728 0c8.91392 0 16.146432-7.20896 16.146432-16.116736l0-73.065472L131.023872 461.770496l0 73.065472C131.023872 543.743744 138.27584 550.952704 147.187712 550.952704zM877.50144 48.327424 147.187712 48.327424c-8.911872 0-16.16384 7.273472-16.16384 16.13312L131.023872 326.930176l762.624 0 0-262.468608C893.647872 55.600896 886.41536 48.327424 877.50144 48.327424z" transform="translate(0, 812) scale(1, -1)"/>
								</svg>
							</div>
							<div class="lfb-wrap">
								<ol class="u-account-set">
									<li>
										<label class="u-a-inp" style="margin: 10px 0 0 0;"><input type="text" id="cardCode" value="" name="cardCode.cardCode" placeholder="输入课程卡号" required="required"></label>
									</li>
									<li>
										<label class="u-a-inp" style="margin: 10px 0 0 0;"><input type="text" id="cardCodePwd" value="" name="cardCode.cardCodePassword" placeholder="输入课程卡密码" required="required"></label>
									</li>
								</ol>
								<section class="lr-btn" style="margin-top: 20px;"> <a href="javascript:activationCard()" style="background: #DF7E7E;">一 键 激 活</a> </section>
							</div>
						</section>
					</section>
				</div>
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			//scrollLoad(); //滚动响应加载课程图片
		})
	</script>
</body>
</html>