<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>考试系统</title>
</head>
<body>
<form action="${ctx}/paper/toExamPaperRecordList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input type="hidden" id="subjectId" name="queryQuestion.subjectId" value="${queryQuestion.subjectId}"/>
<input name="queryQuestion.pointId" id="pointId" type="hidden" value="${queryQuestion.pointId}"/>
		<!-- e-main begin -->
		<section class="e-main">
			<div class="w1000">
				<section class="s-s-tp">
					<!-- /test paper end -->
					<section class="test-paper">
						<div class="clearfix my-history">
							<article class="t-paper-wrap">
								<section class="t-paper b-fff">
									<div class="my-history-title pl50">
										<ul>
											<li class="current"><a href="${ctx}/paper/toExamPaperRecordList" title="">练习历史</a></li>
											<li ><a href="${ctx}/quest/toErrorQuestionList" title="">错题记录</a></li>
											<li><a href="${ctx}/quest/toNoteQuestionList" title="">习题笔记</a></li>
											<li ><a href="${ctx}/quest/favoriteQuestion" title="">习题收藏</a></li>
										</ul>
										<div class="clear"></div>
									</div>
									<div class="my-history-cont">

											<ol class="m-h-lx">
											
												<c:choose>
					                              <c:when test="${queryPaperRecordList !=null && queryPaperRecordList.size()>0 }">
						                              	<p class="m-h-cw-title">
															<b class="c-666 fsize14">共有${page.totalResultSize}条练习记录</b>
														</p>
														<c:forEach items="${queryPaperRecordList}" var="paperRecord">
															<li>
															<input type="hidden" class="questionIds" id="${paperRecord.id }"/>
																<p class="c-333 fsize16">${paperRecord.paperName }</p>
																<p class="c-999 mt5">练习时间: <fmt:formatDate value="${paperRecord.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 
																<c:if test="status!=1">
																答题情况:做对${paperRecord.correctNum }道/共${paperRecord.qstCount }道</c:if></p> 
																<span class="m-h-c-right c-999">
																	<c:choose>
											                            <c:when test="${paperRecord.status==1}">
																			未完成    &nbsp; &nbsp;|  <a href="${ctx}/paper/toExamPaperRecord/${paperRecord.id }" title="继续考试" class="c-orange ml10">继续考试</a>
											                            </c:when>
											                            <c:otherwise>
												                            <a href="${ctx}/paper/toExamPaperRecord/${paperRecord.id }" title="" class="c-green mr10">查看解析</a>| 
																			<a href="${ctx}/paper/getExamPaperReport/${paperRecord.id }" title="" class="c-orange ml10">查看报告</a> 
											                            </c:otherwise>
											                         </c:choose>
																</span>
															</li>
														</c:forEach>
					                              </c:when>
					                              <c:otherwise>
					                              	<section class="tac pt30 pb50">
														<span><em class="k-tips-1 icon24">&nbsp;</em><font
															class="c-999 fsize14 ml10">无历史记录！</font></span>
													</section>
					                              </c:otherwise>
					                            </c:choose>
												
											</ol>
										</div>
								</section>
							</article>
						</div>
					</section>
					<!-- /test paper end -->
				</section>
			</div>
		</section>
		<!-- /pageBar begin -->
					<jsp:include page="/WEB-INF/view/exam/common/page.jsp" />
					<!-- /pageBar end -->
		<!-- e-main end -->
	</form>
</body>
</html>