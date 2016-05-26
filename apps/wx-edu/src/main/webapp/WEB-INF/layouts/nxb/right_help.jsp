<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="r-fixed-wrap">
	<ul class="r-fixed-ul">
		<li id="goTop" class="undis">
			<a class="bg-orange" href="javascript: void(0)" title="返回顶部">
				<em class="r-f-icon-1"> </em>
				<span>返回顶部</span>
			</a>
		</li>
		<li class="foot-zixun">
			<a class="bg-orange" href="${ctx}/shopcart?command=queryShopCart" title="购物车">
				<em class="r-f-icon-2"> </em>
				<span>购物车</span>
			</a>
		</li>
		<li class="foot-zixun">
			<a class="bg-orange" href="${ctx}/uc/fav" title="我的收藏">
				<em class="r-f-icon-3"> </em>
				<span>我的收藏</span>
			</a>
		</li>
		<li class="foot-zixun">
			<a class="bg-orange" href="javascript:void(0)" onclick="feedback()" title="反馈">
				<em class="r-f-icon-4"> </em>
				<span>反馈</span>
			</a>
		</li>
	</ul>
</div>