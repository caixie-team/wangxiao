<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程详情</title>
	
</head>
<body>
						<!-- 课程目录 -->
							<!-- 课程套餐 开始-->
							<div class="kc-bao" style="padding-top: 10px;">
								<ul class="clearfix">
									<c:forEach items="${coursePackageList }" var="courseDto">
									<li <c:if test="${ courseDto.id==currentCourseId}">class="current"</c:if>>
										<a href="javascript:void(0)" onclick="changeCatalog(${ courseDto.id})" >
											<em>&nbsp;</em>
											<span>${courseDto.name}</span>	
										</a>
									</li>
									</c:forEach>
								</ul>
							</div>
							<!-- 课程套餐 结束 -->
							<div class="courese-menu-tree">
								<c:forEach items="${courseKpoints }" var="courseKpoint">
								<c:if test="${courseKpoint.type==0 }">
								<dl>
									<dd>
										<ol class="c-m-t-level-2">
											<li class="kpointLi" id="${courseKpoint.id}li" onclick="getPlayerBox(${courseKpoint.id})"><c:if test="${ courseKpoint.isfree==1}"><img src="/static/mobile/img/mianf.jpg"  width="68" height="12" class="freeImg"></c:if><tt class="cmt-time">${courseKpoint.courseMinutes }分${courseKpoint.courseSeconds }秒</tt><a href="javascript:void(0)" title=""><em>&nbsp;</em><span>${courseKpoint.name }</span></a></li>
										</ol>
									</dd>
								</dl>
								</c:if>
								<c:if test="${courseKpoint.type==1 }">
								<dl>
									<dt>
										<section class="c-m-t-level-1">
											<h4>
												<em>&nbsp;</em>
												<span>${courseKpoint.name }</span>
											</h4>
											<i class="jJ ji">&nbsp;</i>
										</section>
									</dt>
									<dd>
										<ol class="c-m-t-level-2">
											<c:forEach items="${courseKpoint.subKpoints}" var="subKpoint">
											<li class="kpointLi" id="${subKpoint.id}li" onclick="getPlayerBox(${subKpoint.id})"><c:if test="${ subKpoint.isfree==1}"><img src="/static/mobile/img/mianf.jpg"  width="68" height="12" class="freeImg"></c:if><tt class="cmt-time">${subKpoint.courseMinutes }分${subKpoint.courseSeconds }秒</tt><a href="javascript:void(0)" title=""><em>&nbsp;</em><span>${subKpoint.name }</span></a></li>
											</c:forEach>
										</ol>
									</dd>
								</dl>
								</c:if>
								</c:forEach>
							</div>
							<input type="hidden" value="${defaultKpointId }" id="defaultKpointId"/>
						<!-- /课程目录 -->

</body>
</html>