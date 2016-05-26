<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
<title>考试系统</title>
<script type="text/javascript" src="${ctximg}/static/exam/js/exam-test.js?v=${v}"></script> 

<script type="text/javascript">

var  titleheiddenNum_size=${paperMiddleList.size()};
var maxtime =100;//最大时间每个页面要自己覆盖此值

$(function(){
    $('input:radio').click(function(){
        var numinder = $(this).parent().attr("numinder");
        if(numinder!=null){
            var numinder1 = numinder*1+1;
            $('#datikaCurrent'+numinder1+" a").click();
        }
    });
	//单题模式初始化
	danti($("#checkboxdanti"));
	if(${paperMiddleList.size()}>0){
		cookiesName = "test_time"+${paper.id};
	}
	if(getCookie(cookiesName)==null){
		maxtime = ${paper.replyTime}*60; //一个小时，按秒计算，自己调整!  
	}else{
		maxtime=getCookie(cookiesName);
	}
	timer = setInterval("CountDown()",1000);   
});


</script>
</head>
<body>
<form action="${ctx}/paper/addPaperRecord" method="post" id="addPaperRecord">
<input type="hidden" name="paperRecord.type" id="paperRecordType" value="2"/>
<input type="hidden" name="paperRecord.subjectId" value="1"/>
<input type="hidden" name="paperRecord.testTime" id="testTime" value="0"/>
<input type="hidden" name="paperRecord.epId" value="${paper.id }"/>
<c:set var="questionNumIndex" value="0" />
<c:set var="numIndex" value="-1" />
		<!-- e-main begin -->
		<section class="e-main">
			<div class="w1000">
				<section class="s-s-tp">
					<!-- /test paper end -->
					<section class="test-paper">
						<div class="clearfix pr">
							<section class="left-float-menu">
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
												<li><a title="单题模式" href="javascript:void(0)">
												<input type="checkbox" onclick="danti(this)" id="checkboxdanti">
												单题模式
												</a>
												</li>
											</ol>
										</section>
									</div>
								</aside>
								<!-- /答题卡 开始 -->
								<c:if test="${paperMiddleList!=null&&paperMiddleList.size()>0 }">
							    <aside class="answer">
									<section>
										<div class="answer-bar" title="展开答题卡">答题卡</div>
										<div class="answer-list">
											<ol class="clearfix">
											<c:set var="daTiKaNumIndex" value="0" />
												<c:forEach items="${paperMiddleList }" varStatus="paperMiddleListindex" var="paperMiddle" >
													<c:if test="${paperMiddle.type==4 }">
													<c:forEach items="${paperMiddle.complexList}" var="complex">
														<c:forEach items="${complex.queryQstMiddleList}">
														<c:set var="daTiKaNumIndex" value="${daTiKaNumIndex+1}" />
														<li id="datikaCurrent<c:out value="${daTiKaNumIndex}"/>"><a href="javascript:void(0)" onclick="javascript:datikaAnchor(${paperMiddleListindex.index },<c:out value="${daTiKaNumIndex}"/>)" title="第<c:out value="${daTiKaNumIndex}"/>题"><c:out value="${daTiKaNumIndex}"/></a></li>
														</c:forEach>
													</c:forEach>
													</c:if>
													<c:if test="${paperMiddle.type!=4 }">
													<c:forEach items="${paperMiddle.qstMiddleList}">
														<c:set var="daTiKaNumIndex" value="${daTiKaNumIndex+1}" />
														<li id="datikaCurrent<c:out value="${daTiKaNumIndex}"/>"><a href="javascript:void(0)" onclick="javascript:datikaAnchor(${paperMiddleListindex.index },<c:out value="${daTiKaNumIndex}"/>)" title="第<c:out value="${daTiKaNumIndex}"/>题"><c:out value="${daTiKaNumIndex}"/></a></li>
													</c:forEach>
													</c:if>
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
										<tt class="vam c-333 fsize18 f-fM nextTitleAnchor"> ${paper.name}</tt>
										<input value="${paper.name}" type="hidden" name="paperRecord.paperName"/>
									</div>
									<c:if test="${paperMiddleList==null||paperMiddleList.size()==0 }">
										<!-- 无数据时，提示 -->
										<div class="no-data-tips">
											<em class="n-d-t-icon">&nbsp;</em>
											<p class="disIb c-666 fsize14 ml10 vam">本试卷还没有上传试题，小编正在努力整理中 . . .</p>
										</div>
										<!-- 无数据时，提示 -->
									</c:if>
									<div class="t-p-sub-title-wrap">
										<div class="t-p-sub-title">
											<ul>
												<c:forEach items="${paperMiddleList }" varStatus="index" var="paperMiddle">
													<c:if test="${index.index==0 }">
													<li class="current">
													<a class="titleHeiddenAndShow${index.index}" papermiddleid="${paperMiddle.id }" indexNum="${index.index }" titleValue="${paperMiddleList[index.index].title}[&nbsp;每小题${paperMiddleList[index.index].score }分&nbsp;]" href="javascript:void(0)" title="${paperMiddle.name }" onclick="titleHeiddenAndShow(${paperMiddle.id },this)"><strong>${paperMiddle.name }</strong>
													</a></li></c:if>
													<c:if test="${index.index!=0}">
													<li><a href="javascript:void(0)" class="titleHeiddenAndShow${index.index}" indexNum="${index.index }" title="${name }" titleValue="${paperMiddleList[index.index].title}[&nbsp;每小题${paperMiddleList[index.index].score }分&nbsp;]" onclick="titleHeiddenAndShow(${paperMiddle.id },this)"><strong>${paperMiddle.name }</strong>
													</a></li>
													</c:if>
												</c:forEach>
											</ul>
										</div>
									</div>
									<div class="clear"></div>
									<div class="pt10 pb10 pl20 pr20">
											<h6 class="hLh30 of unFw ml15"><font class="c-333 fsize12" id="showTitleValue">
											<c:if test="paperMiddleList!=null&&paperMiddleList.size>0">
											${paperMiddleList[0].title }[&nbsp;每小题${paperMiddleList[0].score }分&nbsp;]</c:if></font></h6>
										</div>
								</section>
								<div>
								<c:forEach items="${paperMiddleList}" varStatus="paperMiddleListindex" var="paperMiddle">
								<section class="t-paper-box" id="titleHidden${paperMiddle.id }" <c:if test="${paperMiddleListindex.index!=0 }">style="display:none"</c:if>>
									<c:if test="${paperMiddle.type==4 }">
									<c:forEach items="${paperMiddle.complexList}" var="complexList">
										<article class=" ">
											<section class="analyze-box t-paper-topic cailiaohiddle">
												<span class="t-p-topic-img">&nbsp;</span>
												<p><tt class="fsize12 c-orange">（材料题）</tt>${complexList.complexContent }</p>
											</section>
											
											<c:forEach items="${complexList.queryQstMiddleList}"  var="qstMiddle">
											<article class="datikaQstAnchor<c:out value="${questionNumIndex+1}"/> t-paper-one">
											<c:set var="questionNumIndex" value="${questionNumIndex+1 }" />
											<c:set var="numIndex" value="${numIndex+1 }" />
											<input type="hidden" value="${qstMiddle.qstType }" name="record[${numIndex}].qstType"/>
											<input type="hidden" value="${qstMiddle.pointId }" name="record[${numIndex}].pointId"/>
											<input type="hidden" value="${qstMiddle.qstId }" name="record[${numIndex}].qstIdsLite"/>
											<input type="hidden" value="${qstMiddle.isAsr }" name="record[${numIndex}].answerLite"/>
											<input type="hidden" value="${paperMiddle.score}" name="record[${numIndex}].score"/>
											<input type="hidden" value="${qstMiddle.paperMiddleId }" name="record[${numIndex}].paperMiddle"/>
											<section class="datikaQstAnchor<c:out value="${questionNumIndex}"/> t-paper-topic ">
												<span class="t-p-topic-num"><tt><c:out value="${questionNumIndex}"/></tt></span>
												<p><tt class="fsize12 c-orange">
												<c:if test="${qstMiddle.questionType==1}">（单选题）</c:if>
												<c:if test="${qstMiddle.questionType==2}">（多选题）</c:if>
												<c:if test="${qstMiddle.questionType==3}">（判断题）</c:if>
												<c:if test="${qstMiddle.questionType==5}">（不定项题）</c:if>
												</tt>${qstMiddle.qstContent }</p>
											</section>
											<section class="t-p-options">
												<ol>
													<c:forEach items="${qstMiddle.optionList }" var="option">
													<li><a href="javascript:void(0);">${option.optOrder }、${option.optContent }</a></li>
													</c:forEach>
												</ol>
											</section>
											<section class="is-options clearfix">
												<div class="fr">
													<em class="icon18 collect-icon">&nbsp;</em>
													<c:choose>
													  <c:when test="${qstMiddle.favoritesId ==null || qstMiddle.favoritesId==0 }">
															<a href="javascript:void(0)" onclick="favorite(${qstMiddle.qstId },this)" qstId="${qstMiddle.qstId }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
													  </c:when>
													  <c:otherwise>
														<a href="javascript:void(0)" onclick="notFavorite(${qstMiddle.qstId },this)" title="取消收藏试题" qstId="${qstMiddle.qstId }" class="vam c-666 ml5">取消收藏试题</a>
													  </c:otherwise>
													</c:choose>
													
												</div>
												<div class="fl t-p-is-options">
												<c:forEach items="${qstMiddle.optionList }" var="option">
												
													<c:choose>
										  				<c:when test="${option.qstType==1||option.qstType==3 }">
											  				<label for="<c:out value="${questionNumIndex}"/>_${option.optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
																<em class="icon18 o-radio">&nbsp;</em>
																<input type="radio" name="record[${numIndex}].userAnswer" id="<c:out value="${questionNumIndex}"/>_${option.optOrder }" value="${option.optOrder }"/>
																<span>${option.optOrder }</span>
															</label>
										  				
										  				</c:when>
													  	<c:otherwise>
													  		<label for="<c:out value="${questionNumIndex}"/>_${optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
																<em class="icon18 o-checkbox">&nbsp;</em>
																<input type="checkbox" name="record[${numIndex}].userAnswer" id="<c:out value="${questionNumIndex}"/>_${option.optOrder }" value="${option.optOrder }"/>
																<span>${option.optOrder }</span>
															</label>
													  	</c:otherwise>
													</c:choose>
												</c:forEach>
												<input class="ml10" value="" type="hidden" name="record[${numIndex}].userAnswer"/>
												</div>
											</section>
											<input value="下一题"  class="comm-btn c-btn-1 fr mt10 mr10 mb10 nextoneqst" type="button" onclick="nextqst(${numIndex+2})" />
											</article>
											</c:forEach>
										</article>
									<!-- /材料分析题结束 -->
									</c:forEach>
									</c:if>
									<c:if test="${paperMiddle.type!=4 }">
										<div>
										<c:forEach items="${paperMiddle.qstMiddleList }" var="qstMiddle">
										<c:set var="questionNumIndex" value="${questionNumIndex+1}" />
										<c:set var="numIndex" value="${numIndex+1}" />
										<input type="hidden" value="${qstMiddle.qstType }" name="record[${numIndex}].qstType"/>
											<input type="hidden" value="${qstMiddle.pointId }" name="record[${numIndex}].pointId"/>
										<input type="hidden" value="${qstMiddle.qstId }" name="record[${numIndex}].qstIdsLite"/>
										<input type="hidden" value="${qstMiddle.isAsr }" name="record[${numIndex}].answerLite"/>
										<input type="hidden" value="${paperMiddle.score}" name="record[${numIndex}].score"/>
										<input type="hidden" value="${qstMiddle.paperMiddleId }" name="record[${numIndex}].paperMiddle"/>
										<!-- /试卷列表开始 -->
										<article class="datikaQstAnchor<c:out value="${questionNumIndex}"/> t-paper-one ">
											<section class="t-paper-topic">
												<span class="t-p-topic-num"><tt><c:out value="${questionNumIndex}"/></tt></span>
												<p><tt class="fsize12 c-orange">
												<c:if test="${qstMiddle.questionType==1}">（单选题）</c:if>
												<c:if test="${qstMiddle.questionType==2}">（多选题）</c:if>
												<c:if test="${qstMiddle.questionType==3}">（判断题）</c:if>
												<c:if test="${qstMiddle.questionType==5}">（不定项题）</c:if>
												<c:if test="${qstMiddle.questionType==6}">（主观题）</c:if>
												</tt>${qstMiddle.qstContent }</p>
											</section>
											<section class="t-p-options">
											<!-- 主观题没有选项 -->
											<c:if test="${paperMiddle.type!=6}">
												<ol>
													<c:forEach items="${qstMiddle.optionList}" var="option">
													<li><a href="javascript:void(0);">${option.optOrder }、${option.optContent }</a></li>
													</c:forEach>
												</ol>
											</c:if>
											</section>
											<section class="is-options clearfix">
												
												<div class="fr">
													<em class="icon18 collect-icon">&nbsp;</em>
													<c:choose>
													  <c:when test="${qstMiddle.favoritesId==null ||  qstMiddle.favoritesId==0}">
														<a href="javascript:void(0)" onclick="favorite(${qstMiddle.qstId },this)" qstId="${qstMiddle.qstId }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
													  </c:when>
													  <c:otherwise>
														<a href="javascript:void(0)" onclick="notFavorite(${qstMiddle.qstId },this)" title="取消收藏试题" qstId="${qstMiddle.qstId }" class="vam c-666 ml5">取消收藏试题</a>
													  </c:otherwise>
													</c:choose>
												</div>
												<!-- 主观题出现的是输入框没有选项 -->
												<c:if test="${paperMiddle.type==6}">
												<textarea  style="width:682px;height:140px;" id="" name="record[${numIndex}].userAnswer" ></textarea>
												</c:if>
												<c:if test="${paperMiddle.type!=6}">
												<div class="fl t-p-is-options">
													<c:forEach items="${qstMiddle.optionList}" var="option">
														<c:choose>
								                            <c:when test="${option.qstType==1||option.qstType==3}">
									                            <label for="<c:out value="${questionNumIndex}"/>_${option.optOrder}" numInder="<c:out value="${questionNumIndex}"/>">
																	<em class="icon18 o-radio">&nbsp;</em>
																	<input type="radio" name="record[${numIndex}].userAnswer" id="<c:out value="${questionNumIndex}"/>_${option.optOrder }" value="${option.optOrder }"/>
																	<span>${option.optOrder }</span>
																</label>
								                            </c:when>
								                            <c:otherwise>
									                            <label for="<c:out value="${questionNumIndex}"/>_${option.optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
																	<em class="icon18 o-checkbox">&nbsp;</em>
																	<input type="checkbox" name="record[${numIndex}].userAnswer" id="<c:out value="${questionNumIndex}"/>_${option.optOrder }" value="${option.optOrder }"/>
																	<span>${option.optOrder}</span>
																</label>
								                            </c:otherwise>
							                         	</c:choose>
														
													</c:forEach>
													<input class="ml10" value="" type="hidden" name="record[${numIndex}].userAnswer"/>
												</div>
												</c:if>
												
											</section>
											<input value="下一题"  class="comm-btn c-btn-1 fr mt10 mr10 mb10 nextoneqst" type="button" onclick="nextqst(${numIndex+2})" />
										</article>
										</c:forEach>
									</div>
									</c:if>
								</section>
								</c:forEach>
								</div>
								<!-- /page bar begin -->
								<section class="page-bar">
									<span class="comm-btn-wrap"><a href="javascript:nexttitleshow()" title="点击进入下一部分" class="comm-btn c-btn-1">点击进入下一部分</a></span>
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
</form>
 
</body>
</html>
