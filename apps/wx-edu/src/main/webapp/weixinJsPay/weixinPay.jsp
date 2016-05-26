<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html>
<html>
	<head>
			<!-- Basic Page Needs
		================================================== -->
		<meta charset="utf-8">
		<title>提交订单 - ${websitemap.web.company}</title>
		<meta name="keywords" content="${websitemap.web.keywords}">
		<meta name="description" content="${websitemap.web.description}">
		<meta name="title" content="${websitemap.web.title}">
		<meta name="author" content="${websitemap.web.author}">
		<meta name="Copyright" content="${websitemap.web.copyright}">
		<!-- <meta name="description" content=""> -->
		<!-- 让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit 内核
		================================================== -->
		<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
		<!-- IOS6全屏 Chrome高版本全屏
		================================================== -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="mobile-web-app-capable" content="yes"> 
		<!-- 让360双核浏览器用webkit内核渲染页面
		================================================== -->
		<meta name="renderer" content="webkit">
		<!-- Mobile Specific Metas
		================================================== -->
		<!-- !!!注意 minimal-ui 是IOS7.1的新属性，最小化浏览器UI -->
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<!-- CSS
		================================================== -->
		<link href="${ctximg}/static/mobile/css/reset.css" rel="stylesheet" type="text/css">
		<link href="${ctximg}/static/mobile/css/style.css" rel="stylesheet" type="text/css">
		<link href="${ctximg}/static/mobile/css/am.css" rel="stylesheet" type="text/css">
		<!-- Favicons
		================================================== -->
		<link rel="shortcut icon" href="favicon.ico" >
		<!-- Android 主屏图标
		================================================== -->	
		<link rel="icon" sizes="196x196" href="${ctximg}/static/mobile/img/apple-touch-icon-140x140.jpg">
		<!-- IOS 主屏图标
		================================================== -->
		<link rel="apple-touch-icon-precomposed" href="${ctximg}/static/mobile/img/apple-touch-icon-76x76.jpg">
		<link rel="apple-touch-icon-precomposed" sizes="76x76" href="${ctximg}/static/mobile/img/apple-touch-icon-76x76.jpg">
		<link rel="apple-touch-icon-precomposed" sizes="120x120" href="${ctximg}/static/mobile/img/apple-touch-icon-120x120.jpg">
		<link rel="apple-touch-icon-precomposed" sizes="140x140" href="${ctximg}/static/mobile/img/apple-touch-icon-140x140.jpg">
		<!-- 页面内容加载未完成之前显示loading.gif图标 
		================================================== -->
		<script src="${ctximg}/static/mobile/js/pageLoading.js" type="text/javascript" charset="utf-8"></script> 
		
		
		<script src="${ctximg}/static/mobile/js/zepto.min.js" type="text/javascript"></script> 
		<script src="${ctximg}/static/mobile/js/wap-js.js" type="text/javascript"></script>
		
		<script src="${ctximg}/static/common/webutils.js?v=<%=version%>" type="text/javascript" ></script>
		
	</head>

	<body >
		<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/course/info/${param.courseId }" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>确认订单</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<input type="hidden" id="orderId" value="${param.orderId }" name="orderId"/>
			
			<section class="link-error-wrap" style="padding-top: 10%;" id="order_success" >
				<span><img src="${ctximg }/static/mobile/img/undata.png" alt="" class="vam"></span>
				<span class="vam" style="font-size: 1.2rem;color: #cb4040;">下单成功！</span>
				<ol class="up-ol">
					<li>
						<span>订单号：</span>
						<tt id="orderId_success">${param.requestId }</tt>
					</li>
					<li>
						<span>订单总额：</span>
						<tt id="amount_success">${param.amount }</tt>
					</li>
					<li>
						<span>账户余额：</span>
						<tt id="balance_s">${param.balance }</tt>
					</li>
					<li>
						<span>还需充值：</span>
						<tt id="bankAmount_s">${param.bankAmount }</tt>
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
		
		/**
		 *微信支付
		 */
		function goToBank(){
			$.ajax({
				url:'/mobile/weixin/order/bank',
				type:'post',
				data:{'orderId':'${param.orderId }'},
				dataType:'json',
				success:function(result){
					if(result.success){
						if(result.message!='success'){//余额未支付成功
							WeixinJSBridge.invoke('getBrandWCPayRequest',{
						  		 "appId" : result.entity.appId,"timeStamp" :result.entity.timeStamp,
						  		 "nonceStr" : result.entity.nonceStr, "package" :result.entity.package,
						  		 "signType" : "MD5", "paySign" : result.entity.paySign 
						   			},function(res){
							            if(res.err_msg == "get_brand_wcpay_request:ok"){  
							            	window.location.href='/mobile/order/pay/success';
							            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
							                
							            }  
									}); 
						}else{
							window.location.href='/mobile/order/pay/success';
						}
						
					}else{
						 dialog('提示',result.message,'',0);
					}
				}
				
			 }); 
			
		}
		
	</script>
	</body>
</html>