<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
	<title>试题组卷</title>
<script type="text/javascript" src="${ctximg}/static/exam/js/exam-test.js?v=${v}"></script> 
<script type="text/javascript">

var  titleheiddenNum_size=${paperMiddleList.size()};

$(function(){
	if('${errorFalg}'=='1'){
		showErrorQuestion();
	}
});
function titleHeiddenAndShow(id){
	$("#titleHidden"+id).show();
	$("#titleHidden"+id).siblings().hide();
}
var titleheiddenNum = 0;
function nexttitleshow(){
	titleheiddenNum = titleheiddenNum+1;
	if(titleheiddenNum == '<c:out value="${paperMiddleList.size()}"/>'){
		titleheiddenNum = 0;
	}
	$(".titleHeiddenAndShow"+titleheiddenNum).click();
}
function titleHeiddenAndShow(id,obj){
	titleheiddenNum=parseInt($(obj).attr("indexNum"));
	
	var titleValue = $(obj).attr("titlevalue");
	$("#showTitleValue").html(titleValue);
	$(obj).parent().attr("class","current");
	$(obj).parent().siblings().each(function(){
		$(this).attr("class","");
	});
	$("#titleHidden"+id).show();
	$("#titleHidden"+id).siblings().hide();
	$("html,body").animate({scrollTop: $(".nextTitleAnchor").offset().top}, 0);
}
 
var qstIdNote="";
//添加笔记
function addNote(obj){
	dialog(7);
	qstIdNote = $(obj).attr("qstId");
	$("#notesubmit").click(function(){
		var noteContent = $("#noteContent").val();
		if(noteContent.length>200){
			alert("你最多能输入200个字");
			return false;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/quest/insertNote",
			data:{"queryNoteQuestion.noteContent":noteContent,"queryNoteQuestion.qstId":qstIdNote},
			async:false,
			success:function(result){
				if(result.success==true){
					$(".dtClose").click();
					$(obj).attr("onclick","updateNote(this)");
					$(obj).prev().html(noteContent);
					if(noteContent==""){
						$(obj).attr("style","margin-left:0px");
					}else{
						$(obj).attr("style","margin-left:425px");
					}
				}
			}
		});
	});
}
//笔记重置
function chongzhi(){
	$("#noteContent").val("");
}
//更新笔记
function updateNote(obj){
	dialog(7);
	qstIdNote = $(obj).attr("qstId");
	 $("#noteContent").val($(obj).prev().html());
	$("#notesubmit").click(function(){
		var noteContent = $("#noteContent").val();
		if(noteContent.length>200){
			alert("你最多能输入200个字");
			return false;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/quest/updateNote",
			data:{"queryNoteQuestion.noteContent":noteContent,"queryNoteQuestion.qstId":qstIdNote},
			async:false,
			success:function(result){
				if(result.success==true){
					$(".dtClose").click();
					$(obj).prev().html(noteContent);
					if(noteContent==""){
						$(obj).attr("style","margin-left:0px");
					}else{
						$(obj).attr("style","margin-left:425px");
					}
				}
			}
		});
	});
}
function showErrorQuestion(){
	$(".rightQuestion").hide();
	$(".a-right").hide();
	
}
function showAllQuestion(){
	$(".rightQuestion").show();
	$(".a-right").show();
}
$(function(){
	if('${queryErrorQuestion.placeNumber}'!=0){
		$(".errorQuestionAnchor${queryErrorQuestion.placeNumber}").click();
	}
});
function lookErrorQuestion(obj){
	if($(obj).prop("checked")){
		showErrorQuestion();
	}else{
		showAllQuestion();
	}
}
</script>
</head>
<body >
<input type="hidden" name="paper.id" value="${paper.id }"/>
<c:set var="questionNumIndex" value="0" />
<!-- e-main begin -->
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
						<c:if test="${paperMiddleList!=null && paperMiddleList.size()>0 }">
						    <aside class="answer">
								<section>
									<div class="answer-bar" title="展开答题卡">答题卡</div>
									<div class="answer-jx answer-list">
										<ol class="clearfix">
											<c:set var="daTiKaNumIndex" value="0" />
											<c:forEach items="${paperMiddleList }" varStatus="paperMiddleListindex" var="paperMiddle">
					                               
												<c:choose>
					                              <c:when test="${paperMiddle.type==4}">
					                              	<c:forEach items="${paperMiddle.complexList}" var="complex">
														<c:forEach items="${complex.queryQstMiddleList}" >
															<c:set var="daTiKaNumIndex" value="${daTiKaNumIndex+1}" />
															<li id="datikaCurrent<c:out value="${daTiKaNumIndex}"/>" class="${status==0?'a-right':'a-error' }"><a class="errorQuestionAnchor<c:out value="${daTiKaNumIndex}"/>" href="javascript:void(0)" onclick="datikaAnchor(${paperMiddleListindex.index },<c:out value="${daTiKaNumIndex}"/>)" title="第<c:out value="${daTiKaNumIndex}"/>题"><c:out value="${daTiKaNumIndex}"/></a></li>
														</c:forEach>
													</c:forEach>
					                              </c:when>
					                              <c:otherwise>
					                              	<c:forEach items="${paperMiddle.qstMiddleList}" var="qstMiddle">
														<c:set var="daTiKaNumIndex" value="${daTiKaNumIndex+1}" />
														<li id="datikaCurrent<c:out value="${daTiKaNumIndex}"/>" class="${qstMiddle.status==0?'a-right':'a-error' }"><a class="errorQuestionAnchor<c:out value="${daTiKaNumIndex}"/>" href="javascript:void(0)" onclick="datikaAnchor(${paperMiddleListindex.index },<c:out value="${daTiKaNumIndex}"/>)" title="第<c:out value="${daTiKaNumIndex}"/>题"><c:out value="${daTiKaNumIndex}"/></a></li>
													</c:forEach>
					                              </c:otherwise>
					                            </c:choose>
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
										
											<c:choose>
				                              <c:when test="${index.index==0 }">
												<li class="current">
												<a class="titleHeiddenAndShow${index.index}" indexNum="${index.index }" titleValue="${paperMiddleList[index.index].title}[&nbsp;每小题${paperMiddleList[index.index].score }分&nbsp;]" href="javascript:void(0)" title="${paperMiddle.name }" onclick="titleHeiddenAndShow(${paperMiddle.id },this)"><strong>${paperMiddle.name }</strong>
												</a></li>
				                              </c:when>
				                              <c:otherwise>
				                              	<li><a href="javascript:void(0)" class="titleHeiddenAndShow${index.index}" indexNum="${index.index }" title="${paperMiddle.name }" titleValue="${paperMiddleList[index.index].title}[&nbsp;每小题${paperMiddleList[index.index].score }分&nbsp;]" onclick="titleHeiddenAndShow(${paperMiddle.id },this)"><strong>${paperMiddle.name }</strong>
												</a></li>
				                              </c:otherwise>
				                            </c:choose>
											
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="clear"></div>
							<div class="pt10 pb10 pl20 pr20">
								<h6 class="hLh30 of unFw ml15"><font class="c-333 fsize12" id="showTitleValue">${paperMiddleList[0].title }[&nbsp;每小题${paperMiddleList[0].score }分&nbsp;]</font></h6>
							</div>
						</section>
						<section class="t-paper-box">
							<c:forEach items="${paperMiddleList}" varStatus="paperMiddleListindex" var="paperMiddle">
								<div id="titleHidden${paperMiddle.id}" <c:if test="${paperMiddleListindex.index!=0 }">style="display:none"</c:if> >
								<c:choose>
                              		<c:when test="${paperMiddle.type==4}">
										<c:forEach items="${paperMiddle.complexList}" var="complex">
										<article class="t-paper-one ">
											<section class="analyze-box t-paper-topic">
												<span class="t-p-topic-img">&nbsp;</span>
												<p><tt class="fsize12 c-orange">（材料题）</tt>${complex.complexContent }</p>
											</section>
											<c:forEach items="${complex.queryQstMiddleList}" var="queryQstMiddle">
											<c:set var="questionNumIndex" value="${questionNumIndex+1}" />
											<span class="<c:out value="status==0?' rightQuestion':' errorQuestion'"/>">
											<section class="t-paper-topic  datikaQstAnchor<c:out value="${questionNumIndex}"/>">
												<span class="t-p-topic-num"><tt><c:out value="${questionNumIndex}"/></tt></span>
												<p><tt class="fsize12 c-orange">
													<c:if test="${queryQstMiddle.questionType==1}">（单选题）</c:if>
													<c:if test="${queryQstMiddle.questionType==2}">（多选题）</c:if>
													<c:if test="${queryQstMiddle.questionType==3}">（判断题）</c:if>
													<c:if test="${queryQstMiddle.questionType==5}">（不定项题）</c:if>
												</tt>${queryQstMiddle.qstContent }</p>
											</section>
											<section class="t-p-options">
												<ol>
													<c:forEach items="${queryQstMiddle.optionList}" var="option">
													<c:set var="optOrder" value="${option.optOrder }"/>
													<c:set var="isAsr" value="${queryQstMiddle.isAsr}"/>
													<li><a href="javascript:void(0);" class="${fn:indexOf(optOrder,isAsr )<0?'':'c-green'}" >${option.optOrder }、${option.optContent }</a></li>
													</c:forEach>
												</ol>
											</section>
											<section class="is-options">
													<div class="fr">
													<span class="pr10 c-999">
														<!-- <em class="icon18 collect-icon">&nbsp;</em> -->
														<a href="javascript:void(0)" onclick="checkAnswer(${paper.id},${queryQstMiddle.qstId},this)" qstId="${question.id }" title="我要纠错" class="vam c-666 ml5">我要纠错</a>
													</span>
														<span class="pr10 c-999">
														<em class="icon18 collect-icon">&nbsp;</em>
														<c:choose>
							                              <c:when test="${queryQuestion.favoritesId==null || queryQstMiddle.favoritesId==0}">
																<a href="javascript:void(0)" onclick="favorite(${queryQstMiddle.qstId },this)" qstId="${queryQstMiddle.qstId }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
							                              </c:when>
							                              <c:otherwise>
																<a href="javascript:void(0)" onclick="notFavorite(${queryQstMiddle.qstId },this)" title="取消收藏试题" qstId="${queryQstMiddle.qstId }" class="vam c-666 ml5">取消收藏试题</a>
							                              </c:otherwise>
							                            </c:choose>
														</span>|&nbsp;&nbsp;&nbsp;
														<span class="jx-show-btn"><a href="javascript:void(0)" class="c-666 vam">收起解析</a><em class="icon14 jx-icon">&nbsp;</em></span>
													</div>
													<div class="fl t-p-is-options c-fsize12 c-333">
														<tt><font class="vam">正确答案是：</font><font class="fsize16 c-green f-fM vam">${queryQstMiddle.isAsr }</font></tt>
														<tt class="ml10"><font class="vam">您的答案是：</font>
															<font class="fsize16 c-red f-fM vam">
															<c:choose>
								                              <c:when test="${queryQstMiddle.userAnswer=='' }">你没有回答这道题 
								                              </c:when>
								                              <c:otherwise>${queryQstMiddle.userAnswer }</c:otherwise>
								                            </c:choose>
															</font>
														</tt>
														<tt class="ml10 vam">
															<c:choose>
															  <c:when test="${queryQstMiddle.userAnswer==''}"></c:when>
								                              <c:when test="${queryQstMiddle.status==0}">回答正确  
								                              </c:when>
								                              <c:otherwise>回答错误</c:otherwise>
								                            </c:choose>
														</tt>
													</div>
													<div class="clear"></div>
													<section class="analysis-wrap">
														<dl class="clearfix">
															<dt><span class="analysis-tb"><em class="icon24 ana-jx">&nbsp;</em><font class="fsize14 ml5">解析</font></span></dt>
															<dd>
																<p>${queryQstMiddle.qstAnalyze }</p>
															</dd>
														</dl>
														<dl class="clearfix">
															<dt><span class="analysis-tb"><em class="icon24 ana-kd">&nbsp;</em><font class="fsize14 ml5">考点</font></span></dt>
															<dd>
																<div class="pr a-kj-txt a-kj-show" pointId="${queryQstMiddle.parentId }">
																	<span class="a-kj-title">${queryQstMiddle.pointName }</span>
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
															<c:choose>
								                              <c:when test="${queryQstMiddle.noteId==null || queryQstMiddle.noteId==0 }">
									                              <dd>
																	<p class="noteContent">${queryQstMiddle.noteContent }</p>
																	<span style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" onclick="addNote(this)" qstId="${queryQstMiddle.qstId }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
																  </dd>
								                              </c:when>
								                              <c:otherwise>
									                              <dd>
																		<p class="noteContent">${queryQstMiddle.noteContent }</p>
																		<span style="margin-left:425px"  class="analysis-tbing hand" title="点击编辑笔记内容" style="margin-left:425px" onclick="updateNote(this)" qstId="${queryQstMiddle.qstId }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
																  </dd>
								                              </c:otherwise>
								                            </c:choose>
														</dl>
													</section>
												</section>
												</span>
											</c:forEach>
										</article>
									<!-- /材料分析题结束 -->
									</c:forEach>
								</c:when>
                              <c:otherwise>
									<c:forEach items="${paperMiddle.qstMiddleList}" var="qstMiddle">
									<c:set var="questionNumIndex" value="${questionNumIndex+1}" />
									<!-- /试卷列表开始 -->
												
									<article class="t-paper-one  datikaQstAnchor<c:out value="${questionNumIndex}"/> 
									<c:out value="${qstMiddle.status==0?' rightQuestion':' errorQuestion'}"/>">
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
										<section class="t-p-options t-p-o-analysis">
											<c:if test="${paperMiddle.type!=6}">
											<ol>
												<c:forEach items="${qstMiddle.optionList }" var="option">
												<c:set var="optOrder" value="${option.optOrder }"/><c:set var="isAsr" value="${qstMiddle.isAsr}"/>
												<li><a href="javascript:void(0);" class="${fn:indexOf(optOrder,isAsr )<0?'':'c-green'}" >${option.optOrder }、${option.optContent }</a></li>
												</c:forEach>
											</ol>
											</c:if>
										</section>
										<section class="is-options">
											<div class="fr">
											<span class="pr10 c-999">
												<a href="javascript:void(0)" onclick="checkAnswer(${paper.id},${qstMiddle.qstId },this)" qstId="${question.id }" title="我要纠错" class="vam c-666 ml5">我要纠错</a>
											</span>
												<span class="pr10 c-999">
												<em class="icon18 collect-icon">&nbsp;</em>
												<c:choose>
					                              <c:when test="${qstMiddle.favoritesId ==null || qstMiddle.favoritesId==0}">
														<a href="javascript:void(0)" onclick="favorite(${qstMiddle.qstId },this)" qstId="${qstMiddle.qstId }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
					                              </c:when>
					                              <c:otherwise>
														<a href="javascript:void(0)" onclick="notFavorite(${qstMiddle.qstId },this)" title="取消收藏试题" qstId="${qstMiddle.qstId }" class="vam c-666 ml5">取消收藏试题</a>
					                              </c:otherwise>
					                            </c:choose>
												</span>|&nbsp;&nbsp;&nbsp;
												<span class="jx-show-btn"><a href="javascript:void(0)" class="c-666 vam">收起解析</a><em class="icon14 jx-icon">&nbsp;</em></span>
											</div>
											<div class="fl t-p-is-options c-fsize12 c-333">
												<c:if test="${paperMiddle.type!=6}">
												<tt><font class="vam">正确答案是：</font><font class="fsize16 c-green f-fM vam">${qstMiddle.isAsr }</font></tt>
												<tt class="ml10"><font class="vam">您的答案是：</font>
													<font class="fsize16 c-red f-fM vam">
														<c:choose>
							                              <c:when test="${qstMiddle.userAnswer==''}">你没有回答这道题 </c:when>
							                              <c:otherwise>${qstMiddle.userAnswer}</c:otherwise>
							                            </c:choose>
													</font>
												</tt>
												
												<tt class="ml10 vam">
													<c:choose>
													  <c:when test="${qstMiddle.userAnswer==''}"></c:when>
						                              <c:when test="${qstMiddle.status==0}">回答正确 </c:when>
						                              <c:otherwise>回答错误</c:otherwise>
						                            </c:choose>
												</tt>
												</c:if>
												<c:if test="${paperMiddle.type==6}">
												<tt class="ml10"><font class="vam">您的答案是：</font>
													<font class="fsize16 c-red f-fM vam">
														<c:choose>
							                              <c:when test="${qstMiddle.userAnswer==''}">你没有回答这道题 </c:when>
							                              <c:otherwise>${qstMiddle.userAnswer}</c:otherwise>
							                            </c:choose>
													</font>
												</tt>
												<tt class="ml10 vam">
													试题得分：${qstMiddle.score}
												</tt>
												</c:if>
											</div>
											<div class="clear"></div>
											<section class="analysis-wrap">
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-jx">&nbsp;</em><font class="fsize14 ml5">解析</font></span></dt>
													<dd>
														<p>${qstMiddle.qstAnalyze }</p>
													</dd>
												</dl>
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-kd">&nbsp;</em><font class="fsize14 ml5">考点</font></span></dt>
													<dd>
														<div class="pr a-kj-txt a-kj-show" pointId="${qstMiddle.parentId }" >
															<span class="a-kj-title">${qstMiddle.pointName }</span>
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
							                              <c:when test="${qstMiddle.noteId==null || qstMiddle.noteId==0 }">
							                              		<dd>
																	<p class="noteContent">${qstMiddle.noteContent }</p>
																	<span  style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" onclick="addNote(this)" qstId="${qstMiddle.qstId }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
																</dd>
							                              </c:when>
							                              <c:otherwise>
								                              	<dd>
																	<p class="noteContent">${qstMiddle.noteContent }</p>
																	<span  style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" style="margin-left:425px" onclick="updateNote(this)" qstId="${qstMiddle.qstId }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
																</dd>
							                              </c:otherwise>
							                            </c:choose>
													</dd>
												</dl>
											</section>
										</section>
									</article>
									</c:forEach>
								</c:otherwise>
                            </c:choose>
								</div>
							</c:forEach>
						</section>
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
</body>
</html>
