<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
	<footer class="footer-i cms-footer-i of">
			<div class="container-i">
				<aside class="w300 fr">
				<div class="f-wx-wb">
					<em class="top-l">&nbsp;</em><em class="top-r">&nbsp;</em><em class="bottom-r">&nbsp;</em><em class="bottom-l">&nbsp;</em><aside class="fl mt50"><img width="120" height="120" class="dis" src="${staticServer}/static/edu/images/lc_official_line/winxinP.jpg">
					<p class="mt5 tac c-fff">
						APP二维码扫描下载
					</p>
					</aside><aside class="fr mt50"><img width="120" height="120" class="dis" src="${staticServer}/static/edu/images/lc_official_line/winxinP.jpg">
					<p class="mt5 tac c-fff">
						理臣官方微博关注
					</p>
					</aside>
					<div class="clear">
					</div>
				</div>
				</aside><article class="fl w700">
				<div class="mr50">
					<section><img width="200" height="60" src="/static/edu/images/logo-white.png"></section><section class="mt30">
					<ul class="f-link">
						<li style="margin-left: -6px;" class="current">
						<c:forEach items="${navigatemap.tabOfficialNavigates}" var="navigate" varStatus="index">
							<c:choose>
								<c:when test="${navigate.newPage==0}">
									<a title="${navigate.name}" target="_blank" href="${navigate.url}" class="mr10 ml5">${navigate.name}</a>
								</c:when>
								<c:otherwise>
									<a title="${navigate.name}" href="${navigate.url}" class="mr10 ml5">${navigate.name}</a>
								</c:otherwise>
							</c:choose>
							<c:if test="${index.index < navigatemap.tabOfficialNavigates.size()-1}">|</c:if>
						</c:forEach>
						</li>
						<li><span>地址：${websitemap.web.address}</span></li>
						<li><span>${websitemap.web.copyright}</span></li>
					</ul>
					</section>
				</div>
				</article>
			</div>
		</footer>
	<!-- 在线咨询 开始 -->
	<div class="onlineConsultBox" id="onlineConsultBox">
		<div class="onlineC-item">
			<ul>
				<li>
					<div class="onlineC-item1" name="350">
						<a href="" target="_blank" title="在线咨询" class="onlineC-ico">
							<span class="onlineC-ico1">&nbsp;</span>
							<p>在线咨询</p>
						</a>
					</div>
				</li>
				<li>
					<div class="onlineC-item2" name="200">
						<a href="javascript: void(0)" title="关注我们" class="onlineC-ico">
							<span class="onlineC-ico2">&nbsp;</span>
							<p>关注我们</p>
						</a>
						<div class="onlineC winxinGz">
							<img src="" width="200" height="200" class="dis" />
							<p class="tac"><span class="fsize14 c-4e">扫一扫，关注我们</span></p>
						</div>
					</div>
				</li>
				<li>
					<div class="onlineC-item3">
						<a href="/front/to_free_back" target="_blank" title="意见反馈" class="onlineC-ico">
							<span class="onlineC-ico5">&nbsp;</span>
							<p>意见反馈</p>
						</a>
					</div>
				</li>
			</ul>
			<div class="onlineC-item4" title="回到顶部 ^" id="goTop">
				<a href="javascript: void(0)" title="关注我们" class="onlineC-ico">
					<span class="onlineC-ico4">&nbsp;</span>
					<p>返回顶部</p>
				</a>
			</div>
		</div>
	</div> 
	<!-- 在线咨询 结束 -->