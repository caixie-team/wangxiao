<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提示信息</title>
<script type="text/javascript" >
	$().ready(function() {
		$("li>img").click(function (){
			var ra = $(this).prev(':radio');
			if(ra!=null){
				$(':radio').attr('checked',false);
				$(ra).attr('checked',true);
			}
		});
 	});
</script>
</head>
<body class="scrol">

	<div class="mb50">
		<section class="container">
            <section class="path-wrap txtOf hLh30"> 
				<a class="c-999 fsize14" title="" href="http://127.0.0.1">首页</a>
				 \<span class="c-333 fsize14">订单支付</span> 
			</section>
			 <!--提示信息-->
			 <article class="mt30" id="order_success">
				 <div class="order-table pb20"  >
				 	<section class="mg20">
						<div class="orderSuccess pr ml20 order-error"  >
							<ol>
								<li><h2 class="line3 pb10"><strong class="c-333 fsize18"><font class="ml5 mr5 c-orange" id="orderId_success">提示信息：</font>${msg}</strong></h2></li>
								<li class="mt20">
									<span class="c-333 fsize14">您可以：
										<span class="vam">
                                            <a class="order-submit" title="返回首页" href="${ctx }/">首页</a>
											<a class="order-submit" title="进入学习中心" href="${ctx }/uc/home">学习中心</a>
											<c:if test="${type==null||type==''}">
										 		<a class="order-submit" title="我的订单" href="${ctx }/uc/order">我的订单</a>
										 	</c:if>
										 	<c:if test="${type!=null||type=='member'}">
										 		
										 	</c:if>
											<c:if test="${type!=null||type=='cash'}">
												<a class="order-submit" title="我的订单" href="${ctx }/uc/cash/order">我的订单</a>
											</c:if>
										 </span>
									</span>
								</li>
							</ol>
							<span class="succIcon pa"></span>
						</div>
					</section>
				</div>
			</article>
				
		</section>
	</div>
</body>
</html>
