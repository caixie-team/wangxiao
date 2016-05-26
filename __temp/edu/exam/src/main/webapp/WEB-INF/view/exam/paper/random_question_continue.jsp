<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
	<title>试题组卷</title>
<script type="text/javascript" src="${ctximg}/static/exam/js/exam-test.js?v=${v}"></script> 
<script type="text/javascript">

var testTime=100;
$(function(){
	if(${queryQuestionList.size()}>0){
		cookiesName = "test_time"+${queryQuestionList[0].id};
	}
	if(getCookie(cookiesName)==null){
		maxtime = ${paper.replyTime}*60-testTime; //一个小时，按秒计算，自己调整!  
	}else{
		maxtime=getCookie(cookiesName);
	}
	timer = setInterval("CountDown()",1000);   
});

</script>
</head>
<body >
<form action="${ctx}/paper/addPaperRecord" method="post" id="addPaperRecord"> 
<input type="hidden" name="paperRecord.type" value="1"/>
<input type="hidden" name="paperRecord.testTime" id="testTime" value="${PaperRecord.testTime}"/>
<input type="hidden" name="paperRecord.subjectId" value="1"/>
<input type="hidden" name="paperRecord.id" value="${paperRecord.id }"/>
<input type="hidden" name="paperRecord.epId" value="${paperRecord.epId }"/>
<input type="hidden" name="paperRecord.replyTime" value="${paper.replyTime}"/>
<c:set var="questionNumIndex" value="0" />
<c:set var="numIndex" value="-1" />

	<!-- e-main begin -->
	<section class="e-main">
		<div class="w1000">
			<section class="s-s-tp">
				<!-- /test paper end -->
				<section class="test-paper">
					<div class="clearfix">
						<section class="left-float-menu  pr">
							<aside class="left-float-wrap">
								<div class="l-f-w-bg">
									<section class="tac mt10">
										<span class="l-f-w-pic-1">&nbsp;</span>
										<div class="mt5">
											<tt class="fsize14 c-333">剩余时间：<span id="timer"></span></tt>
										</div>
									</section>
									<section class="l-f-w-menu mt10 pt10">
										<ol>
											<li><a href="javascript:void(0)" title="我要交卷"  onclick="formSubmit()" >我要交卷</a></li>
											<li><a href="javascript:void(0)" onclick="timePause()" title="暂停">暂停</a></li>
											<li><a href="javascript:void(0)" title="下次再做" onclick="addPaperRecord(1);">下次再做</a></li>
										</ol>
									</section>
								</div>
							</aside>
							<!-- /答题卡 开始 -->
							<c:if test="${queryQuestionList!=null&&queryQuestionList.size()>0 }">
						    <aside class="answer">
								<section>
									<div class="answer-bar" title="展开答题卡">答题卡</div>
									<div class="answer-list">
										<ol class="clearfix">
										<c:set var="daTiKaNumIndex" value="0" />
											<c:forEach items="${queryQuestionList}" varStatus="paperMiddleListindex">
												<c:set var="daTiKaNumIndex" value="${daTiKaNumIndex+1}" />
												<li id="datikaCurrent<c:out value="${daTiKaNumIndex}"/>"><a href="javascript:void(0)" onclick="javascript:datikaAnchor(${paperMiddleListindex.index },<c:out value="${daTiKaNumIndex}"/>)" title="第<c:out value="${daTiKaNumIndex}"/>题"><c:out value="${daTiKaNumIndex}"/></a></li>
											</c:forEach>
										</ol>
									</div>
									<div class="clear"></div>
								</section>
							</aside>
							</c:if>
							<!-- /答题卡 结束 -->
						</section>
						
						<article class="t-paper-wrap" >
							<section class="t-paper b-fff">
								<div class="t-p-title">
									<em class="icon30 t-p-t-icon">&nbsp;</em>
									<tt class="vam c-333 fsize18 f-fM nextTitleAnchor">专项智能练习</tt>
								</div>
								<c:if test="${queryQuestionList==null||queryQuestionList.size()==0}">
								<!-- 无数据时，提示 -->
								<div class="no-data-tips">
									<em class="n-d-t-icon">&nbsp;</em>
									<p class="disIb c-666 fsize14 ml10 vam">本考点下还没有上传试题，小编正在努力整理中 . . .</p>
								</div>
								<!-- 无数据时，提示 -->
								</c:if>
								<div class="clear"></div>
							</section>
							<div>
							<section class="t-paper-box" >
									<div>
									<c:forEach items="${queryQuestionList }" varStatus="index" var="question">
									<c:set var="questionNumIndex" value="${questionNumIndex+1}" />
									<c:set var="numIndex" value="${numIndex+1}" />
									
									<input type="hidden" value="${question.qstType }" name="record[${numIndex}].qstType"/>
									<input type="hidden" value="${question.pointId }" name="record[${numIndex}].pointId"/>
									<input type="hidden" value="${question.id }" name="record[${index.index }].qstIdsLite"/>
									<input type="hidden" value="${question.isAsr }" name="record[${index.index }].answerLite"/>
									<input type="hidden" value="20" name="record[${index.index }].score"/>
									<!-- /试卷列表开始 -->
									<article class="t-paper-one datikaQstAnchor<c:out value="${questionNumIndex}"/>">
										<section class="t-paper-topic">
											<span class="t-p-topic-num"><tt><c:out value="${questionNumIndex}"/></tt></span>
											<p><tt class="fsize12 c-orange">
											<c:if test="${question.qstType==1}">（单选题）</c:if>
											<c:if test="${question.qstType==2}">（多选题）</c:if>
											<c:if test="${question.qstType==3}">（判断题）</c:if>
											<c:if test="${question.qstType==5}">（不定项题）</c:if>
											</tt>${question.qstContent}</p>
										</section>
										<section class="t-p-options">
											<ol>
												<c:forEach items="${question.options}" var="option">
												<li><a href="javascript:void(0);">${option.optOrder }、${option.optContent }</a></li>
												</c:forEach>
											</ol>
										</section>
										<section class="is-options clearfix">
											<div class="fr">
												<em class="icon18 collect-icon">&nbsp;</em>
												<c:choose>
													<c:when test="${question.favoritesId==null || question.favoritesId==0}">
														<a href="javascript:void(0)" onclick="favorite(${question.id },this)" qstId="${question.id }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
													</c:when>
													<c:otherwise>
														<a href="javascript:void(0)" onclick="notFavorite(${id },this)" title="取消收藏试题" qstId="${question.id }" class="vam c-666 ml5">取消收藏试题</a>
													</c:otherwise>
												</c:choose>
												
											</div>
											<div class="fl t-p-is-options">
												<c:forEach items="${question.options}" var="option">
													<c:choose>
														<c:when test="${option.qstType==1||option.qstType==3}">
															<label for="<c:out value="${questionNumIndex}"/>_${option.optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
																<em class="icon18 o-radio">&nbsp;</em>
																<input type="radio" class="answer<c:out value="${numIndex}"/>" name="record[<c:out value="${numIndex}"/>].userAnswer" id="<c:out value="${questionNumIndex}"/>_${option.optOrder }" value="${option.optOrder }"/>
																<span>${option.optOrder }</span>
															</label>
														</c:when>
														<c:otherwise>
															<label for="<c:out value="${questionNumIndex}"/>_${option.optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
															<em class="icon18 o-checkbox">&nbsp;</em>
															<input type="checkbox" class="answer<c:out value="${numIndex}"/>"  name="record[<c:out value="${numIndex}"/>].userAnswer"  id="<c:out value="${questionNumIndex}"/>_${option.optOrder }" value="${option.optOrder }"/>
															<span>${option.optOrder }</span>
														</label>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<input class="ml10" value="" type="hidden" name="record[${numIndex}].userAnswer" />
												<script type="text/javascript">
												$(".answer${numIndex}").each(function(){
													var userAnswer = '${question.userAnswer }'.split(",");
													for(var i=0;i<userAnswer.length;i++){
														if($(this).val()==userAnswer[i].trim()){
															$(this).attr("checked","checked");
														}
													}
												});
											</script>
											</div>
										</section>
									</article>
									</c:forEach>
								</div>
								<!-- /多选试卷列表结束 -->
							</section>
							</div>
							<!-- /page bar begin -->
							<!-- /page bar end -->
						</article>
					</div>
				</section>
				<!-- /test paper end -->
			</section>
		</div>
	</section>
	<!-- e-main end -->
</form>
</body>
</html>
