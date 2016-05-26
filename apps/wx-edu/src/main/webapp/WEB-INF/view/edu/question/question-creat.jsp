<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>提问</title>
</head>
<body>
<div class="">
	<div class="bg-fa of">
		<!-- /课程列表 开始 -->
		<section class="container">
			<section class="path-wrap txtOf hLh30"> 
				<a class="c-999 fsize14" title="" href="${ctx}">首页</a> \
				<a class="c-999 fsize14" title="" href="${ctx}/front/question">问答</a> \
				<span class="c-333 fsize14">提问题</span> 
			</section>
			<div class="clearfix">
				<div class="fl col-75">
					<div class="mr20 mb20">
						<div class="mt10 i-box">
							<div class="q-c-list mt10">
							<form action="" id="addProblem">
								<dl>
								<dt><span class="c-999 fsize14">标题：</span></dt>
								<dd class="pr">
									<label class=""><input type="text" maxlength="50" name="sugSuggest.title" id="title" placeholder="问题标题不少于2个字" onblur="titleCheck()" value=""></label>
									<aside class="q-c-jy" id="errorTitle">
									</aside>	
								</dd>
							</dl>
							<dl>
								<dt><span class="c-999 fsize14">内容：</span></dt>
								<dd class="pr">
									<textarea name="sugSuggest.content" maxlength="500" id="suggestContent" onblur="contextCheck()" placeholder="简洁，明了，能引起思考和讨论的知识性的内容。"></textarea>
									<aside class="q-c-jy" id="errorContent">
									</aside>
								</dd>
							</dl>
					<!-- 		<dl>
								<dt><span class="c-999 fsize14">&nbsp;</span></dt>
								<dd class="pr">
									<label class=""><input type="text" style="width: 80px;" name="" placeholder="输入验证码" value=""></label>
									<div class="v-code-pic">
										<img src="" widht="80" height="34" class="vam" alt="">
										<span class="c-999">看不清</span>
										<br>
										<a href="" title="" class="c-green">
											换一换
										</a>
									</div>
								</dd>
							</dl> -->
							<dl>
								<dt><span class="c-999 fsize14">&nbsp;</span></dt>
								<dd>
									<section class="pt10">
										<a href="javascript:void(0)" onclick="submitwushi();" title="提 问" class="bm-lr-btn ques-submit">提 问</a>
									</section>
								</dd>
							</dl>
							</form>
							</div>
						</div>
					</div>
				</div>
				<div class="fr col-25 mb30">
						<div class="mt10 pl10">
						<section class="q-tip-pic col-3">
							<img width="100%" alt="亲，您要提问吧？" src="${ctx}/static/nxb/web/img/tipQe.png">
						</section>
						<h5 class="pt10"><span class="fsize18 c-333 vam">亲，您要提问吧？<br><br>要知道这些哦！</span></h5>
						<div class="clear"></div>
						<dl class="mt10">
							<dt><h6><strong class="fsize14 c-666">一、需要了解的事情：</strong></h6></dt>
							<dd class="pl10">
								<p class="c-999 mt10">1、您是想来吐槽的吧，没事，随便发吧。有人会跟你一起吐槽的。</p>
								<p class="c-999 mt10">2、您是来解决问题？请先搜索是否已经有同类问题吧。这样您就省心少打字。</p>
								<p class="c-999 mt10">3、没找到是么？就在发问题时精确描述你的问题，不要写与问题无关的内容哟。</p>
								<p class="c-999 mt10">4、互动问答更热衷于解达您想要的答案。能引起思考和讨论的知识性问题；</p>
							</dd>
						</dl>
						<dl class="mt20">
							<dt><h6><strong class="fsize14 c-666">二、要注意的事情：</strong></h6></dt>
							<dd class="pl10">
								<p class="c-999 mt10">1、禁止发布求职、交易、推广、广告类与问答无关信息将一律清理。</p>
								<p class="c-999 mt10">2、尽可能详细描述您的问题，如标题与内容不符，或与问答无关的信息将被关闭。</p>
								<p class="c-999 mt10">3、问答刷屏用户一律冻结帐号。</p>
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</section>
	</div>
	</div>
	<script src="${ctximg}/static/edu/js/front/question/suggest.js"type="text/javascript"></script>
	<script>
		$(function() {
			tagFun();//标签选择交互
			shareFun();
		})
		// 标签选择交互
		var tagFun = function() {
			var tLi = $("#js-tags>a"),
				tCon = $("#tags-content"),
				tSp = $("#label-default"),
				tCol = '<em title="关闭" class=" ml5 dClose"></em>';
			tLi.each(function() {
				var _this = $(this);
				_this.one("click",function() {
					var _tDom = _this.clone(true);
					tSp.hide();
					if (tCon.find(".list-tag").length <= 2) {
						_this.addClass("onactive");
						_tDom.appendTo(tCon).append(tCol);
					};
				})
			})
			tCon.on("click",".list-tag",function() {
				var _this = $(this),
					_lt = _this.attr("data-id");
				_this.remove();
				$('#js-tags>a[data-id='+_lt+']').removeClass("onactive");
				if (tCon.find(".list-tag").length === 0) {
					tSp.show();
				};
			})
		}
	</script>
</body>
</html>
