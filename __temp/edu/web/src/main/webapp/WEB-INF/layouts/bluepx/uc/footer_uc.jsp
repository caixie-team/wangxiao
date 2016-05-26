<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!-- / 帮助 -->

<!-- /尾部-->
	<footer class="mt50">

		<section class="foot-link">
			<div class="w1000">
				<div class="pt10 pb20">
					<section class="mt20 mb10">
						<ul class="tac">
							<li id="linkBottom">
								<c:forEach items="${navigatemap.TAB}" var="tabNavigate">
									<a class="mr10 ml5" href="${tabNavigate.url}" title="${tabNavigate.name}" <c:if test="${tabNavigate.newPage==0}">target="_blank"</c:if>>${tabNavigate.name}</a>|
								</c:forEach>
                                <a class="mr10 ml5" target="_blank" href="http://www.268xue.com" title="网站开发技术支持(268教育)">Powered By 268教育</a>
							</li>

                            <li class="mt10">
                                <span class="ml20"> 24小时服务热线：${websitemap.web.phone} Email：${websitemap.web.email}  工作时间：${websitemap.web.workTime} </span>
                            </li>
							<li class="mt10"><font class="fsize12"> ${websitemap.web.copyright} </font></li>
						</ul>
					</section>
				</div>
			</div>
		</section>
	</footer>
	<!-- /尾部 结束-->
