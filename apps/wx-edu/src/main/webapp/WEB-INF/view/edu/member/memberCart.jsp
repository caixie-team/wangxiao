<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>购买会员</title>
</head>
<div id="aCoursesList" class="bg-fa of">
	<div class="container">
		<section class="path-wrap txtOf hLh30">
			<a class="c-999 fsize14" title="" href="${ctx}">首页</a>
			\<span class="c-333 fsize14">会员订单</span>
		</section>
		<!-- 内容区开始 -->
		<article class="mt30" id="order_init">
			<!--商品清单开始-->
			<div>
				<header class=""><span class="fsize24 c-333">确认会员</span></header>
				<dl id="shopingcart" class="c-order-list clearfix">
					<dt>
					<ul class=" c-o-head of li" style="padding: 8px 15px;">
						<li class="m-b-li wid70"><span class="c-333">选择会员</span></li>
						<li class="m-b-li wid30"><span class="c-333">价格</span></li>
						<div class="clear"></div>
					</ul>
					</dt>
					<dd>
					<ul class="clearfix mem-buy-ul">
						<li class="m-b-li wid70">
							<input type="hidden" id="cardTypeHidden"/>
							<div class="fl w50pre mt10">
								<span class="disIb vam c-666">选择学段：</span>
								<div class="selectWrap disIb vam ">
									<em>▼</em>
									<span id="defaultType"></span>
									<ul id="type">
										<c:forEach items="${memberTypes }" var="memberType">
											<li onclick="showCardType('${memberType.title }',${memberType.id })">${memberType.title }</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="fl w50pre mt10">
								<span class="disIb vam ml20 c-666">开通时长：</span>
								<div class="selectWrap disIb vam ">
									<em>▼</em>
									<span id="defaultDays"></span>
									<ul id="days">
									</ul>
								</div>
							</div>
							<div class="clear"></div>
							<script>
								$(".selectWrap span").mouseover(function(){
									$(this).siblings("ul").slideDown("fast");
								});
								$(".selectWrap").mouseleave(function(){
									$(this).find("ul").slideUp("fast");
								});
							</script>
						</li>
						<li class="m-b-li wid30 pt15">
							<samp id="memPrice" class="c-666 vam fsize14 mt10 f-fM"></samp>
						</li>
					</ul>
					</dd>
					<div class="buyCom_price c-666 fr tar mt10 pr10">
						您所选会员总价：<span id="div_totalMoney" class="fsize14 c-orange mr5"></span>
					</div>
				</dl>

			</div>
			<!--商品清单结束-->
			<div  style="display: none;">
				<!-- alipay参数 -->
				<form action="${ctx }/memberorder/bank" name="orderForm" id="orderForm" method="post" target="_blank">
					<input id="orderId" name="orderId" type="hidden" value=""/>
					<input id="defaultbank" name="defaultbank" type="hidden" value=""/>
					<input id="payType" name="payType" type="hidden" value="ALIPAY" />
				</form>
			</div>
			<!-- 会员id -->
			<div  style="display: none;">
				<input id="daysHidden" type="hidden" value=""/>
			</div>
			<!--选择支付方式 start-->
			<div class="mt30">
				<header class=""><span class="fsize24 c-333">支付方式</span></header>
				<div class="c-pay-method">
					<div class="of">
						<!--网上支付 -网银 支付开始 -->
						<%-- <header class="c-p-title">网上银行</header>
						<div class="buyB_payPlat">
							<ul class="clearfix">
								<c:if test="${keywordmap.keyword.yee=='ON' }">
									<li><label><input type="radio" value="CEB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /> <img alt="广大银行" src="/static/edu/images/buy/bank_ZGGDYH.png" /></label></li>
									<li><label><input type="radio" value="ICBC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国工商银行" src="/static/edu/images/buy/bank_ZGGSYH.png" /></label></li>
									<li><label><input type="radio" value="CCB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国建设银行" src="/static/edu/images/buy/bank_ZGJSYH.png" /></label></li>
									<li><label><input type="radio" value="ABC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国农业银行" src="/static/edu/images/buy/bank_ZGNYYH.png" /></label></li>
									<li><label><input type="radio" value="CMBCHINA-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="招商银行" src="/static/edu/images/buy/bank_ZSYH.png" /></label></li>
									<li><label><input type="radio" value="BOC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国银行" src="/static/edu/images/buy/bank_ZGYH.png" /></label></li>
									<li><label><input type="radio" value="BOCO-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国交通银行" src="/static/edu/images/buy/bank_JTYH.png" /></label></li>
									<li><label><input type="radio" value="POST-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国邮政储蓄银行" src="/static/edu/images/buy/bank_ZGYZCXYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="CIB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="兴业银行" src="/static/edu/images/buy/bank_XYYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="CMBC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国民生银行" src="/static/edu/images/buy/bank_ZGMSYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="ECITIC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中兴银行" src="/static/edu/images/buy/bank_ZXYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="PAB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="平安银行" src="/static/edu/images/buy/bank_PAYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="SDB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="深圳发展银行" src="/static/edu/images/buy/bank_SZFZYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="SHB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="上海银行" src="/static/edu/images/buy/bank_SHYH.png" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="BJRCB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="北京农商银行" src="/static/edu/images/buy/bank_BJNSYH.png" /></label></li>
								</c:if>
								<c:if test="${keywordmap.keyword.yee=='OFF' }">
									<li><label><input type="radio" value="ICBCB2C" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /> <img alt="工商" src="/static/edu/images/buy/wal_bank07_gongShang.jpg" /></label></li>
									<li><label><input type="radio" value="CCB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="建设" src="/static/edu/images/buy/wal_bank08_jianshe.jpg" /></label></li>
									<li><label><input type="radio" value="BOCB2C" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="中国银行" src="/static/edu/images/buy/wal_bank10_zhongGuo.jpg" /></label></li>
									<li><label><input type="radio" value="CMB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="招商" src="/static/edu/images/buy/wal_bank06_zhaoShang.jpg" /></label></li>
									<li><label><input type="radio" value="COMM" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="交通银行" src="/static/edu/images/buy/wal_bank05_jaotong.jpg" /></label></li>
									<li><label><input type="radio" value="ABC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="农业" src="/static/edu/images/buy/wal_bank09_nongYe.jpg" /></label></li>
									<li><label><input type="radio" value="CIB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="兴业" src="/static/edu/images/buy/wal_bank03_xingYe.jpg" /></label></li>
									<li><label><input type="radio" value="CMBC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="民生" src="/static/edu/images/buy/wal_bank03_minSheng.jpg" /></label></li>
									<li><label><input type="radio" value="CITIC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="中信" src="/static/edu/images/buy/wal_bank03_zhongXin.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="GDB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="广发" src="/static/edu/images/buy/wal_bank03_guangFa.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="CEBBANK" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="光大" src="/static/edu/images/buy/wal_bank03_guangDa.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="SPDB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="浦发" src="/static/edu/images/buy/wal_bank03_fuFa.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="BJBANK" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="北京农商" src="/static/edu/images/buy/wal_bank03_beiNongShang.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="POSTGC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="邮政" src="/static/edu/images/buy/wal_bank04_youZheng.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="SHBANK" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="上海" src="/static/edu/images/buy/wal_bank03_shanghai.jpg" /></label></li>
									<li class="buyB_payPlatNone"><label><input type="radio" value="GCB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="广州" src="/static/edu/images/buy/wal_bank03_guangZhou.jpg" /></label></li>
								</c:if>
							</ul>
						</div> --%>
						<!--网上支付 -网银 支付结束 -->
						<!--网上支付 -第三方开始 -->
						<header class="c-p-title">第三方支付平台</header>
						<div class="buyB_payPlat">
							<ul class="clearfix">
								<%--<c:if test="${keywordmap.keyword.yee=='ON' }">
									<li><label>
										<input type="radio" value="00" name="yeepay"  onclick="checkbank('YEEPAY')" style="margin-top:5px;" />
										<img src="/static/edu/images/buy/buyB_pay_yibao.jpg" alt="易宝"  />
									</label></li>
								</c:if>--%>
								<li><label>
									<input type="radio" value="" name="alipay" checked="checked" onclick="checkbank('ALIPAY')"  />
									<img src="/static/edu/images/buy/buyB_pay_kuaiqian3.jpg"  alt="支付宝" />
								</label></li>
								<%--<c:if test="${keywordmap.keyword.verifykq=='ON' }">
									<li><label><input type="radio" value="00" name="kqBank" onclick="checkbank('KUAIQIAN')"   style="margin-top:5px;" />
										<img src="/static/edu/images/buy/buyB_pay_kuaiqian1.jpg" alt="快钱"  />
									</label></li>
								</c:if>--%>
								<c:if test="${keywordmap.keyword.verifywx=='ON' }">
									<li><label>
										<input type="radio" value="" name="weixin" onclick="checkbank('WEIXIN')"  />
										<img src="/static/edu/images/buy/buyB_pay_wx.jpg"  alt="微信" />
									</label></li>
								</c:if>
							</ul>
						</div>
						<!--网上支付 -第三方结束 -->
					</div>
				</div>
			</div>
			<!--选择支付方式 end-->
			<!--结算信息 start-->
			<div class="mt30">
				<header class=""><span class="fsize24 c-333">结算信息</span></header>
				<div class="c-pay-method c-p-m">
					<div>
						<div class="fl buyCouponWrap">
							<p class="fsize14 c-666">使用代金券可以抵消部分金额哦</p>
							<div class="mt20 coupon-box clearfix">
								<input type="text" placeholder="请输入优惠券编码" name="" onkeyup="inputMemCode()" id="couponCode" onclick="inputMemCode()" class="buyText01 fl">
								<a id="tjcode" href="javascript:addMemcode('')" class="buyCoupon_add2 fl">添加</a>
								<a id="initcode" style="display:none" class="buyCoupon_add2 fl" href="javascript:initPrice()">取消</a>
							</div>
						</div>
						<div class="fr tar p-mt15">
							<p class="fsize12 c-333">订单总价<span id="oldmoney" class="c-master f-fG fsize16">￥19.00</span> - 优惠金额 <span id="yhmoney" class="c-master f-fG fsize16">￥0.00</span> = <span id="paySumPrice" class="c-master f-fG fsize16">￥19.00</span>优惠类型：<span id="yhTypeId" class="c-master">无</span></p>
							<p class="fsize24 c-333 mt20 hLh30">订单支付金额：<span id="payprice" class="c-master fsize36 f-fG">￥19.00</span></p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="tar mt40"> <a class="order-btn" href="javascript:memOrder()">提交订单</a> </div>
				</div>
			</div>
			<!--结算信息 end-->
		</article>
		<!--提交成功 start-->
		<article class="mt30" id="order_success" style="display: none" >
			<div class="order-table pb20"  >
				<section class="mt20 mr20 mb20 ml20">
					<div class="orderSuccess pr ml20"  >
						<ol>
							<li><h2 class="hLh30 line3 pb10"><strong class="c-333 fsize20"><tt>订单号:</tt><font class="ml5 mr5 c-orange" id="orderId_success"></font>下单成功，订单总额<font class="ml5 c-orange" id="amount_success"></font></strong></h2></li>
							<li class="mt10">
								<span class="vam"><a class="order-submit" title="" href="javascript:void(0)" onclick="javascript:goToBank()">立即支付</a></span>
							</li>
							<li class="mt20"><span class="c-333 fsize14">您现在可以：<a class="c-333 mr10" title="重新选择支付方式" href="javascript:repayOrder()" id="repayA" ><u>重新选择支付方式</u></a> | <a class="c-4e ml10 mr10" title="进入学习中心" href="${ctx }/uc/home"><u>进入学习中心</u></a> | <a class="c-4e ml10" title="返回首页" href="${ctx }/"><u>返回首页</u></a></span></li>
						</ol>
						<span class="succIcon pa"></span>
					</div>
				</section>
			</div>
		</article>
		<!--提交成功 end-->
	</div>
</div>
<script type="text/javascript" src="${ctximg}/static/edu/js/front/member/member.js"></script>
<script type="text/javascript" >
	$().ready(function() {
		showCardType('${defaultMemberType.title }','${defaultMemberType.id}');
		$("li>img").click(function (){
			var ra = $(this).prev(':radio');
			if(ra!=null){
				$(':radio').attr('checked',false);
				$(ra).attr('checked',true);
			}
		});
	});
</script>
</body>
</html>
