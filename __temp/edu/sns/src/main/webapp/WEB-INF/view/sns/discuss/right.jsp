<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="W-main-right">
								<div class="pl20">
								
								<div class="mt20 tac">
								<a href="${ctx}/dis" title="" class="c-green"><u>所有小组&gt;&gt;</u></a><br/>
								<a href="${ctx}/dis/hot" title="" class="c-green"><u>热门小组&gt;&gt;</u></a><br/>
								<c:if test="${loginId!=0}">
								<a href="${ctx}/dis/my" title="" class="c-green"><u>我管理的小组&gt;&gt;</u></a><br/>
								<a href="${ctx}/dis/join" title="" class="c-green"><u>我加入的小组&gt;&gt;</u></a><br/>
								<a href="${ctx}/dis/myart" title="" class="c-green"><u>我的小组话题&gt;&gt;</u></a><br/>
								<a href="${ctx}/dis/classify" title="" class="c-green"><u>创建小组&gt;&gt;</u></a><br/>
								</c:if>
								</div>
								<div>
										<section class="comm-title-2">
											<span class="c-t-2-l">小组搜索&nbsp;<i class="gt-btn"></i></span>
										</section>
										<section class="mt20 pr10">
										<form action="${ctx}/search" method="post" id="dissearchform"> 
											<input type="hidden" name="search.tab"   value="dis">
											<input type="text" name="search.keyword"  id="searchdis" class="boke-search-inp">
											<input type="hidden"   name="page.currentPage" value="1"/>
											<a href="javascript:dissearch()" class="comm-btn-orange"><span>搜索</span></a>
										</form>
										</section>
									</div>
									<div>
										<section class="comm-title-2">
											<span class="c-t-2-l">小组分类</span>
										</section>
										<section class="mt20 pr10">
											<ul class="boke-sort-list">
											<c:forEach items="${disGroupClassify }" var="disclassify">
												<li><a href="${ctx}/dis/classifygroup/${disclassify.id}" title="" class="fsize14 c-orange">${disclassify.name }</a><span class="c-green">（${disclassify.groupNum }）</span></li>
												</c:forEach>
											</ul>
										</section>
									</div>
								</div>
							</div>
