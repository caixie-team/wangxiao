<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>直播课程购买 </title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/live/list" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>购买直播</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="comm-main animated fadeIn" id="order_init">
				<div>
					<div class="oc-live-wrap chare-live-infor">
						<article class="oc-live-list">
							<dl class="goLive chare-live">
								<dd>
									<h2 class="oc-live-title">
										<span>${live.name }</span>
									</h2>
									<section class="oc-live-body">
										<h5 class="oc-l-t-name">讲师：
											<c:forEach items="${live.teacherList }" var="teacher">
												${teacher.name}&nbsp;&nbsp;
											</c:forEach>
										</h5>
										<div class="oc-l-timer">
											<section class="oc-l-t-d">
												<span>12:00-13:30</span>
											</section>
											<section class="oc-l-t-y-m">
												<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
											</section>
											<div class="clear"></div>
										</div>
										<h6 class="oc-l-time">所剩时间：${live.begin.day}天${live.begin.hour}时${live.begin.min}分</h6>
										<div class="oc-l-ks-price">
											<ol>
												<li class="b-line"><tt>${live.lessionnum}课时</tt></li>
												<li><tt>${live.subjectName }</tt></li>
											</ol>
											<aside class="ks-price-num">
												<small>￥</small>
												<big>${live.currentprice }</big>
											</aside>
										</div>
									</section>
								</dd>
							</dl>
						</article>
					</div>
					<!-- /课程确定 -->
						<div>
							<section class="v-card-txt-title">
								<span>选择支付方式</span>
							</section>
							<section class="pay-method">
								<ol>
									<c:if test="${isWeixin==null }">
									<li class="current">
										<a href="javascript:void(0)" title="">
											<img src="${ctximg }/static/mobile/img/zfb.jpg" width="40" height="40" alt="支付宝钱包支付">
											<h6>支付宝钱包支付</h6>
											<p>推荐支付宝用户使用</p>
											<span class="pm-ico"><em>&nbsp;</em></span>
										</a>
									</li>
									</c:if>
									<c:if test="${isWeixin!=null }">
									<li class="current">
										<a href="javascript:void(0)" title="">
											<img src="${ctximg }/static/mobile/img/wx.jpg" width="40" height="40" alt="微信支付">
											<h6>微信支付</h6>
											<p>推荐已安装微信的用户使用</p>
											<span class="pm-ico"><em>&nbsp;</em></span>
										</a>
									</li>
									</c:if>
								</ol>
							</section>
						</div>
						<!-- /选择支付方式 -->
				</div>
				<!-- /直播课程购买确认 -->
				<div class="ci-bt-box">
					<section class="ci-bt-elem">
						<div class="buy-btn">
							<a title="" href="javascript:void(0)" onclick="addOrder()">提交订单</a>
						</div>
					</section>
					<input type="hidden" id="courseHiddenId" value="${live.id }"/>
				</div>
			</section>
			<form action="/mobile/order/bank" id="bankForm">
				<input type="hidden" id="orderId" name="orderId"/>
				<c:if test="${isWeixin==null }">
				<input type="hidden" id="payType" name="payType" value="ALIPAY"/>
				</c:if> 
				<c:if test="${isWeixin!=null }">
				<input type="hidden" id="payType" name="payType" value="WEIXIN"/>
				</c:if> 
			</form>
			
			<form action="/weixinJsPay/weixinPay.jsp" id="weixinBankForm">
				<input type="hidden" id="weixinOrderId" name="orderId"/>
				<input type="hidden" id="weixinRequestId" name="requestId"/>
				<input type="hidden" id="weixinAmount" name="amount"/>
				<input type="hidden" id="weixinBalance" name="balance"/>
				<input type="hidden" id="weixinBankAmount" name="bankAmount"/>
				<input type="hidden" name="courseId" value="${course.id }"/>
			</form>
			<section class="link-error-wrap" style="padding-top: 10%;display: none" id="order_success" >
				<span><img src="${ctximg }/static/mobile/img/undata.png" alt="" class="vam"></span>
				<span class="vam" style="font-size: 1.2rem;color: #cb4040;">下单成功！</span>
				<ol class="up-ol">
					<li>
						<span>订单号：</span>
						<tt id="orderId_success"></tt>
					</li>
					<li>
						<span>订单总额：</span>
						<tt id="amount_success"></tt>
					</li>
					<li>
						<span>账户余额：</span>
						<tt id="balance_s"></tt>
					</li>
					<li>
						<span>还需充值：</span>
						<tt id="bankAmount_s"></tt>
					</li>
				</ol>
				<p><a href="javascript:void(0)" onclick="goToBank()" title="" style="background: #cb4040;width: auto;font-size: 1.2rem;height: 36px;line-height: 36px;" class="u-o-c-btn u-o-c-btn-gre">立即支付</a></p>
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			ltop(); //选项卡区域定位
		})
	    // li-top fixed absolute
	    var ltop = function() {
	    	var ltEle = $(".v-card-title");
	    	function ltFun() {
	    		var sTop = document.documentElement.scrollTop + document.body.scrollTop;
	    		if (sTop > 240) {
	    			ltEle.css({"position" : "fixed" , "top" : "0"});
	    		} else {
	    			ltEle.css({"position" : "absolute"});
	    		};
	    	}
	    	$(window).bind("scroll" , ltFun);
	    	ltFun();
	    }
		//判断订单,提交订单
		function addOrder() {
		    if(!isLogin()){
		        dialog('提示','请登录','/mobile/login',1);
		        return ;
		    }
		    var courseId=$("#courseHiddenId").val();
		    if(courseId==''||courseId==0){
		    	dialog('提示','请选择购买课程','/mobile/live/list',1);
		        return;
		    }
		    var state=0;//判断是否已购买该直播
		    $.ajax({
		        url: "/mobile/checkBuy",
		        data:{"id":courseId},
		        type:"post",
		        dataType: "json",
		        async : false,
		        success: function(result) {
		            if(result.success){
		            	state=1;//用户已购买可以直接观看
						dialog('提示',"您已购买该直播，请等待直播开始!",'/mobile/live/list',1);
		            }
		        },
		        error : function() {
		            alert("error");
		        }
		   });
		  
		    if(state!=1){
			    //订单提交，服务端要做验证，下单时重新查询数据库
			    $.ajax({
			        url: "/mobile/order?command=buy",
			        data:{"courseId":courseId,"payType":"ALIPAY","type":2},
			        type:"post",
			        dataType: "json",
			        async : false,
			        success: function(result) {
			            if(result.success){
			                if(isNotNull(result.entity.order)){
			                	$("#courseHiddenId").val("");
			                    if(payType=='ALIPAY'){
				                    //金额页面只作为显示用，以服务端提交时重新取数据库为准
				                    $("#orderId").val(orderId);
				                    //显示提交成功的DIV
				                    $("#orderId_success").html(result.entity.order.requestId);
				                    $("#amount_success").html(fmtprice(result.entity.order.amount));
				                    $("#balance_s").html("￥"+result.entity.balance);
				                    if(isNotEmpty(result.entity.bankAmount)){
				                        $("#bankAmount_s").html("￥"+result.entity.bankAmount);
				                    }else{
				                        $("#bankAmount_s").html("￥0.00");
				                    }
				                    $("#order_init").hide();
				                    $("#order_success").show();
			                	}else if(payType=='WEIXIN'){
			                		$("#weixinOrderId").val(orderId);
				                    //显示提交成功的DIV
				                    $("#weixinRequestId").val(result.entity.order.requestId);
				                    $("#weixinAmount").val(fmtprice(result.entity.order.amount));
				                    $("#weixinBalance").val("￥"+result.entity.balance);
				                    if(isNotEmpty(result.entity.bankAmount)){
				                        $("#weixinBankAmount").val("￥"+result.entity.bankAmount);
				                    }else{
				                        $("#weixinBankAmount").val("￥0.00");
				                    }
				                    $("#weixinBankForm").submit();
			                	}
			                }else if(result.entity.msg=='param'){
			                    dialog('提示',"参数错误，无法购买!",'',0);
			                }else if(result.entity.msg=='amount'){
			                    dialog('提示',"课程金额错误，无法购买!",'',0);
			                }else if(result.entity.msg=='courselosedate'){
			                    dialog('提示',"课程已过期，无法购买!",'/mobile/live/list',1);
			                }else if(result.entity.msg=='ending'){
			                    dialog('提示',"直播已结束，无法购买!",'/mobile/live/list',1);
			                }else if(result.entity.msg=='havebuy'){
			                    dialog('提示',"您已购买该直播，请等待直播开始!",'/mobile/live/list',1);
			                }
			            }else{
			            	 dialog('提示',"下单异常，请稍后再试!",'',0);
			            }
	
			        },
			        error : function() {
			            alert("error");
			        }
			    });
		    }
		}
		/**
		 * 跳转到银行
		 */
		function goToBank(){
			$("#bankForm").submit();
		}
		//格式化价格
		function fmtprice(price){
			 if(typeof(price) == 'undefined' || price == null || price ==""|| isNaN(price) ||price == Infinity){
			        return "￥0.00";
			    }else{
			        return "￥"+parseFloat(price).toFixed(2);
			    }
		}
	</script>
</body>
</html>