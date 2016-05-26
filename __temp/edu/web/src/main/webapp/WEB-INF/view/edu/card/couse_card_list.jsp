<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程卡</title>
<script type="text/javascript">
</script>
</head>
<body>
<form id="searchForm" action="${ctx}/front/articlelist/${type}" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
 </form>
					
								<c:forEach items="${articleList}" var="Atricle" varStatus="index">
									<li>
										<a class="aPlot" title="${Atricle.title}" href="${ctx}/front/toArticle/${Atricle.id}">
										<c:if test="${Atricle.picture!=null && Atricle.picture!=''}">
											<img alt="${Atricle.title}" height="100" width="133" src="<%=staticImageServer%>${Atricle.picture}"/>
										</c:if>
										</a>
										<h5 class="hLh30 of unFw"><em class="vam mr5 icon30 a-t-icon">&nbsp;</em><a title="${Atricle.title}" href="${ctx}/front/toArticle/${Atricle.id}" class="c-4e fsize16 vam">${Atricle.title}</a></h5>
										<div class="a-l-desc-txt"> ${Atricle.description} </div>
										<section class="of mt10">
											<span class="c-999 vam disIb" title="时间"><em class="icon14 a-time">&nbsp;</em><fmt:formatDate value="${Atricle.updateTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
											<span class="disIb c-999 vam ml50" title="查看"><em class="icon14 a-read">&nbsp;</em> ${Atricle.clickTimes}人</span>
										</section>
									</li>
								</c:forEach>		 
</body>
</html>
