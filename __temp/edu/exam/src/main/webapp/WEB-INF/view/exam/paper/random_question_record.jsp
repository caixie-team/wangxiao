<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>试题组卷</title>
<script type="text/javascript" src="${ctximg}/static/exam/js/exam-test.js?v=${v}"></script> 
<script type="text/javascript">
$(function(){
	if('${errorFalg}'=='1'){
		showErrorQuestion();
	}
});

</script>
</head>
<body >
<input type="hidden" name="paper.id" value="${paper.id }"/>
<c:set var="questionNumIndex" value="0" />
<c:set var="numIndex" value="-1" />

<section class="e-main">
	<div class="w1000">
		<section class="s-s-tp">
			<!-- /test paper end -->
			<section class="test-paper">
				<div class="clearfix  pr">
					<section class="left-float-menu">
						<aside class="left-float-wrap">
							<div class="l-f-w-bg">
								<section class="tac mt10">
									<span class="l-f-w-pic-2">&nbsp;</span>
									<div class="mt5">
										<tt class="fsize14 c-333">迷你数据</tt>
									</div>
								</section>
								<section class="l-f-w-menu mt10 pt10">
									<ol>
										<li><a href="${ctx}/paper/getExamPaperReport/${paperRecord.id }" title="查看报告">查看报告</a></li>
										<li class="current"><a href="javascript:void(0);" title="查看解析">查看解析</a></li>
										<li><a href="javascript:void(0)" title="只看错题"><input type="checkbox"  onclick="lookErrorQuestion(this)"/>只看错题</a></li>
									</ol>
								</section>
							</div>
						</aside>
						<!-- /答题卡 开始 -->
						<c:if test="${queryQuestionList!=null&&queryQuestionList.size()>0}">
					    <aside class="answer">
							<section>
								<div class="answer-bar" title="展开答题卡">答题卡</div>
								<div class="answer-jx answer-list">
									<ol class="clearfix">
										<c:set var="daTiKaNumIndex" value="0" />
										<c:forEach items="${queryQuestionList}" varStatus="paperMiddleListindex" var="question">
											<c:set var="daTiKaNumIndex" value="${daTiKaNumIndex+1}" />
											<li id="datikaCurrent<c:out value="${daTiKaNumIndex}"/>" class="${question.qstRecordstatus==0?'a-right':'a-error' }"><a href="javascript:void(0)" onclick="javascript:datikaAnchor(${paperMiddleListindex.index },<c:out value="${daTiKaNumIndex}"/>)" title="第<c:out value="${daTiKaNumIndex}"/>题"><c:out value="${daTiKaNumIndex}"/></a></li>
										</c:forEach>
									</ol>
								</div>
								<div class="clear"></div>
							</section>
						</aside>
						</c:if>
						<!-- /答题卡 结束 -->
					</section>
					<article class="t-paper-wrap">
						<section class="t-paper b-fff">
							<div class="t-p-title">
								<em class="icon30 t-p-t-icon-1">&nbsp;</em>
								<tt class="vam c-333 fsize18 f-fM nextTitleAnchor">${paper.name}</tt>
							</div>
							<c:if test="${queryQuestionList==null||queryQuestionList.size()==0}">
							<!-- 无数据时，提示 -->
							<div class="no-data-tips">
								<em class="n-d-t-icon">&nbsp;</em>
								<p class="disIb c-666 fsize14 ml10 vam">本试卷还没有上传试题，小编正在努力整理中 . . .</p>
							</div>
							<!-- 无数据时，提示 -->
							</c:if>
							<div class="clear"></div>
						</section>
						<section class="t-paper-box">
								<div >
									<c:forEach items="${queryQuestionList}" var="question">
									<c:set var="questionNumIndex" value="${questionNumIndex+1}" />
												
									<!-- /试卷列表开始 -->
									<article class="t-paper-one  datikaQstAnchor<c:out value="${questionNumIndex}"/> ${question.qstRecordstatus==0?' rightQuestion':' errorQuesetion'}" >
										<section class="t-paper-topic">
											<span class="t-p-topic-num"><tt><c:out value="${questionNumIndex}"/></tt></span>
											<p><tt class="fsize12 c-orange">
												<c:if test="${question.qstType==1}">（单选题）</c:if>
												<c:if test="${question.qstType==2}">（多选题）</c:if>
												<c:if test="${question.qstType==3}">（判断题）</c:if>
												<c:if test="${question.qstType==5}">（不定项题）</c:if>
												</tt>${question.qstContent }</p>
										</section>
										<section class="t-p-options t-p-o-analysis">
											<ol>
											<c:forEach items="${question.options}" var="option">
												<c:set var="optOrder" value="${option.optOrder }"/>
												<c:set var="isAsr" value="${question.isAsr}"/>
												<li><a href="javascript:void(0);" class="${fn:indexOf(optOrder,isAsr )<0?'':'c-green'}"  title="${option.optOrder }、${option.optContent }">${option.optOrder }、${option.optContent }</a></li>
											</c:forEach>
											</ol>
										</section>
										<section class="is-options">
											<div class="fr">
											<span class="pr10 c-999">
														<!-- <em class="icon18 collect-icon">&nbsp;</em> -->
														<a href="javascript:void(0)" onclick="checkAnswer(${paper.id},${question.id },this)" qstId="${question.id }" title="我要纠错" class="vam c-666 ml5">我要纠错</a>
											</span>
												<span class="pr10 c-999">
												<em class="icon18 collect-icon">&nbsp;</em>
												<c:choose>
													<c:when test="${question.favoritesId==null || question.favoritesId==0}">
														<a href="javascript:void(0)" onclick="favorite(${question.id },this)" qstId="${question.id }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
													</c:when>
													<c:otherwise>
														<a href="javascript:void(0)" onclick="notFavorite(${question.id },this)" title="取消收藏试题" qstId="${question.id }" class="vam c-666 ml5">取消收藏试题</a>
													</c:otherwise>
												</c:choose>
												</span>|&nbsp;&nbsp;&nbsp;
												<span class="jx-show-btn"><a href="javascript:void(0)" class="c-666 vam">收起解析</a><em class="icon14 jx-icon">&nbsp;</em></span>
											</div>
											<div class="fl t-p-is-options c-fsize12 c-333">
												<tt><font class="vam">正确答案是：</font><font class="fsize16 c-green f-fM vam">${question.isAsr }</font></tt>
												<tt class="ml10"><font class="vam">您的答案是：</font>
													<font class="fsize16 c-red f-fM vam">
														<c:choose>
															<c:when test="${question.userAnswer==''}">你没有回答这道题 </c:when>
															<c:otherwise>${question.userAnswer}</c:otherwise>
														</c:choose>
													</font>
												</tt>
												<tt class="ml10 vam">
													<c:choose>
														<c:when test="${question.userAnswer==''}"></c:when>
														<c:when test="${question.qstRecordstatus==0}">回答正确  </c:when>
														<c:otherwise>回答错误 </c:otherwise>
													</c:choose>
												</tt>
											</div>
											<div class="clear"></div>
											<section class="analysis-wrap">
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-jx">&nbsp;</em><font class="fsize14 ml5">解析</font></span></dt>
													<dd>
														<p>${question.qstAnalyze }</p>
													</dd>
												</dl>
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-kd">&nbsp;</em><font class="fsize14 ml5">考点</font></span></dt>
													<dd>
														<div class="pr a-kj-txt a-kj-show" pointId="${question.parentId}" >
															<span class="a-kj-title">${question.pointName }</span>
															<section class="a-kj-desc">
																<article class="pr b-fff pl10 pr10">
																	<em class="a-kj-sj">&nbsp;</em>
																	<div class="fsize12 c-666 notetext">
																		
																	</div>
																</article>
															</section>
														</div>
													</dd>
												</dl>
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-bjing">&nbsp;</em><font class="fsize14 ml5">笔记</font></span></dt>
													<dd>
													
														<c:choose>
															<c:when test="${question.noteId==null || question.noteId==0}">
																<dd>
																<p class="noteContent">${question.noteContent }</p>
																<span  style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" onclick="addNote(this)" qstId="${question.id }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
															</dd>
															</c:when>
															<c:otherwise>
																<dd>
																	<p class="noteContent">${question.noteContent }</p>
																	<span  style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" style="margin-left:425px" onclick="updateNote(this)" qstId="${question.id }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
																</dd>
															</c:otherwise>
														</c:choose>
													
													</dd>
												</dl>
											</section>
										</section>
									</article>
									</c:forEach>
								</div>
						</section>
						<!-- /page bar begin -->
						<section class="page-bar">
						</section>
						<!-- /page bar end -->
					</article>
				</div>
			</section>
			<!-- /test paper end -->
		</section>
	</div>
</section>
<!-- e-main end -->
</body>
</html>
