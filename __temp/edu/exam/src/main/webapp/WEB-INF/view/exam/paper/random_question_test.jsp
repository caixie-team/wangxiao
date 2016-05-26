<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${paperTitle}</title>
<script type="text/javascript" src="${ctximg}/static/exam/js/exam-test.js?v=${v}"></script> 
<script type="text/javascript">
var maxtime =0;//${testTime};//最大时间每个页面要自己覆盖此值
 
$(function(){
    $('input:radio').click(function(){
        var numinder = $(this).parent().attr("numinder");
        if(numinder!=null){
            var numinder1 = numinder*1+1;
            datikaAnchor(numinder,numinder1);
        }
    });
	//单题模式初始化
	danti($("#checkboxdanti"));
	document.body.onbeforeunload="ConfirmClose";
	var qstsize=parseInt('${queryQuestionList.size()}');
	timer = setInterval("CountUp()",1000);   
});
/* function option(index,optOrder){
	var prac=$("#practice").val();//获得是否是练习模式
	var id=$("#"+index+"_"+optOrder).val();//获得所选id
	var answer=$("#asr"+index).html();//获得正确答案
	if(id!=''&&prac==1){
		$("#analysis"+index).show();
		if(id==answer){
			$("#youasr"+index).html(id+"回答正确");
		}else{
			$("#youasr"+index).html(id+"回答错误");
		}
	}
	var judge=$("#checkboxdanti").prop("checked");
	if(judge){
		$("#analysis"+index).show();
		$("#practice").val(1);
		if(id==answer){
			$("#youasr"+index).html(id+"回答正确");
		}else{
			$("#youasr"+index).html(id+"回答错误");
		}
	}
} */
//正则replaceAll方法
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
}; 
function chk(index){
	var array = new Array();
	$("input[name='record["+index+"].userAnswer']").each(function(){
		if($(this).is(":checked")){
			array.push($(this).val());
			}
	});
	var answer=array.toString().replaceAll(",","");
	var numidex=index+1;
	var rightasr=$("#asr"+numidex).html();//获得正确答案
	if(answer!=''){
		$("#analysis"+numidex).show();
		if(answer.trim()==rightasr.trim()){
			$("#youasr"+numidex).html(answer+"回答正确");
		}else{
			$("#youasr"+numidex).html(answer+"回答错误");
		}
	}else{
		alert("您还没有选择答案");
		 return;
	}
}

</script>
</head>
<body  >
<form action="${ctx}/paper/addPaperRecord" method="post" id="addPaperRecord"> 
<input type="hidden" name="paperTitle" value="${paperTitle }"/>
<input type="hidden" name="paperRecord.type" id="paperRecordType" value="1"/>
<input type="hidden" name="paperRecord.subjectId" value="1"/>
<input type="hidden" name="paperRecord.replyTime" value="${testTime }"/>
<input type="hidden" name="paperRecord.testTime" id="testTime" value="0"/>
<input type="hidden" name="" value="${prac}" id="practice"/>
<c:set var="questionNumIndex" value="0" />
<c:set var="numIndex" value="-1" />
<c:if test="${prac==''||prac==null}">
<c:set var="prac" value="1"/>
</c:if>

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
										<span class="l-f-w-pic-1">&nbsp;</span>
										<div class="mt5">
											<tt class="fsize14 c-333">答题时间：<span id="timer"></span></tt>
										</div>
									</section>
									<section class="l-f-w-menu mt10 pt10">
										<ol>
											<li><a href="javascript:void(0)" title="我要交卷"  onclick="formSubmit()" >我要交卷</a></li>
											<li><a href="javascript:void(0)" onclick="timePause()" title="暂停">暂停</a></li>
											<li><a href="javascript:void(0)" title="下次再做" onclick="addPaperRecord(1);">下次再做</a></li>
											<li><a title="单题模式" href="javascript:void(0)"><input type="checkbox" onclick="danti(this)" id="checkboxdanti">
												单题模式</a></li>
										</ol>
									</section>
								</div>
							</aside>
							<!-- /答题卡 开始 -->
							<c:if test="${queryQuestionList!=null && queryQuestionList.size()>0 }">
						    <aside class="answer">
								<section>
									<div class="answer-bar" title="展开答题卡">答题卡</div>
									<div class="answer-list">
										<ol class="clearfix">
										<c:set var="daTiKaNumIndex" value="0" />
											<c:forEach items="${queryQuestionList}" varStatus="paperMiddleListindex" var="queryQuestion_li">
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
									<tt class="vam c-333 fsize18 f-fM nextTitleAnchor">${paperTitle }</tt>
								</div>
								<c:if test="${queryQuestionList==null||queryQuestionList.size()==0 }">
									<!-- 无数据时，提示 -->
									<div class="no-data-tips">
										<em class="n-d-t-icon">&nbsp;</em>
										<c:if test="${t_type==1}">
												<p class="disIb c-666 fsize14 ml10 vam">还没有错题生成，先去测试一下吧 . . .</p>
										</c:if>
										<c:if test="${t_type!=1}">
											<p class="disIb c-666 fsize14 ml10 vam">本试卷还没有上传试题，小编正在努力整理中 . . .</p>
										</c:if>
									</div>
									<!-- 无数据时，提示 -->
								</c:if>
								<div class="clear"></div>
							</section>
							<div>
							<section class="t-paper-box" id="titleHidden${id }"   >
							<%-- <c:if test="${queryQuestionList==null || queryQuestionList.size()==0 }"> style="display:none"</c:if> --%>
									<div>
									<c:forEach items="${queryQuestionList }" varStatus="index" var="queryQuestion">
										<c:set  var="questionNumIndex" value="${questionNumIndex+1}" />
										<c:set var="numIndex" value="${numIndex+1}" />
										
										<input type="hidden" value="${queryQuestion.qstType }" name="record[${numIndex}].qstType"/>
										<input type="hidden" value="${queryQuestion.pointId }" name="record[${numIndex}].pointId"/>
										<input type="hidden" value="${queryQuestion.id }" name="record[${numIndex}].qstIdsLite"/>
										<input type="hidden" value="${queryQuestion.isAsr }" name="record[${numIndex}].answerLite"/>
										<input type="hidden" value="20" name="record[${numIndex}].score"/>
										<input type="hidden" value="20" name="record[${numIndex}].paperMiddle"/>
												
										<!-- /试卷列表开始 -->
										<article class="datikaQstAnchor${questionNumIndex } t-paper-one ">
											<section class="t-paper-topic">
												<span class="t-p-topic-num"><tt>${questionNumIndex}</tt></span>
												<p><tt class="fsize12 c-orange">
												<c:if test="${queryQuestion.qstType==1}">（单选题）</c:if>
												<c:if test="${queryQuestion.qstType==2}">（多选题）</c:if>
												<c:if test="${queryQuestion.qstType==3}">（判断题）</c:if>
												<c:if test="${queryQuestion.qstType==5}">（不定项题）</c:if>
												</tt>${queryQuestion.qstContent }</p>
											</section>
											<section class="t-p-options">
												<ol>
													<c:forEach items="${queryQuestion.options }" var="Questionoption">
													<li><a href="javascript:void(0);">${Questionoption.optOrder }、${Questionoption.optContent }</a></li>
													</c:forEach>
												</ol>
											</section>
											<section class="is-options clearfix">
												<div class="fr">
												<c:if test="${prac==1&&(queryQuestion.qstType==2||queryQuestion.qstType==5)}">
												<span><a href="javascript:void(0)" class="vam c-666 ml5" onclick="chk(${numIndex})">答题完毕</a></span>
												</c:if>
													<em class="icon18 collect-icon">&nbsp;</em>
													<c:choose>
													  <c:when test="${queryQuestion.favoritesId==null || queryQuestion.favoritesId==0 }">
														<a href="javascript:void(0)" onclick="favorite(${queryQuestion.id },this)" qstId="${queryQuestion.id }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
													  </c:when>
													  <c:otherwise>
														<a href="javascript:void(0)" onclick="notFavorite(${queryQuestion.id },this)" title="取消收藏试题" qstId="${queryQuestion.id }" class="vam c-666 ml5">取消收藏试题</a>
													  </c:otherwise>
													</c:choose>
													<!-- <span class="jx-show-btn"><a href="javascript:void(0)" class="c-666 vam">收起解析</a><em class="icon14 jx-icon">&nbsp;</em></span> -->
												</div>
												<div class="fl t-p-is-options">
													<c:forEach items="${queryQuestion.options}" var="doption">
														<c:choose>
														  <c:when test="${doption.qstType==1 || doption.qstType==3 }">
														  	<label for="<c:out value="${questionNumIndex}"/>_${doption.optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
																<em class="icon18 o-radio">&nbsp;</em>
																<input type="radio"  name="record[${numIndex}].userAnswer" id="<c:out value="${questionNumIndex}"/>_${doption.optOrder }" value="${doption.optOrder }" onclick="option(${questionNumIndex},'${doption.optOrder }')"/>
																<span>${doption.optOrder }</span>
															</label>
														  </c:when>
														  <c:otherwise>
														  	<label for="<c:out value="${questionNumIndex}"/>_${doption.optOrder }" numInder="<c:out value="${questionNumIndex}"/>">
																<em class="icon18 o-checkbox">&nbsp;</em>
																<input type="checkbox"  name="record[${numIndex}].userAnswer" id="<c:out value="${questionNumIndex}"/>_${doption.optOrder }" value="${doption.optOrder }"/>
																<span>${doption.optOrder }</span>
															</label>
														  </c:otherwise>
														</c:choose>
													</c:forEach>
													<input class="ml10" value="" type="hidden"  name="record[${numIndex}].userAnswer"/>
												</div>
											</section>
											
											<div id="analysis${questionNumIndex}" style="display: none;">
											<div class=" t-p-is-options c-fsize12 c-333" >
												<tt><font class="vam">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案是：</font><font class="fsize16 c-green f-fM vam" id="asr${questionNumIndex }">${queryQuestion.isAsr }</font></tt>
												<tt class="ml10"><font class="vam">您的答案是：</font>
													<font class="fsize16 c-red f-fM vam" id="youasr${questionNumIndex }">
													</font>
												</tt>
												<span class="jx-show-btn pr10 c-999" style="padding-left: 675px;margin-bottom: 50px"><a href="javascript:void(0)" class="c-666 vam">收起解析</a><em class="icon14 jx-icon">&nbsp;</em></span>
											</div>
											<div class="clear"></div>
												<section class="analysis-wrap">
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-jx">&nbsp;</em><font class="fsize14 ml5">解析</font></span></dt>
													<dd>
														<p>${queryQuestion.qstAnalyze }</p>
													</dd>
												</dl>
												<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-kd">&nbsp;</em><font class="fsize14 ml5">考点</font></span></dt>
													<dd>
														<div class="pr a-kj-txt a-kj-show" pointId="${queryQuestion.parentId}" >
															<span class="a-kj-title">${queryQuestion.pointName}</span>
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
															<c:when test="${queryQuestion.noteId==null || queryQuestion.noteId==0}">
																<dd>
																<p class="noteContent">${queryQuestion.noteContent }</p>
																<span  style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" onclick="addNote(this)" qstId="${queryQuestion.id }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
															</dd>
															</c:when>
															<c:otherwise>
																<dd>
																	<p class="noteContent">${queryQuestion.noteContent }</p>
																	<span  style="margin-left:425px" class="analysis-tbing hand" title="点击编辑笔记内容" style="margin-left:425px" onclick="updateNote(this)" qstId="${queryQuestion.id }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
																</dd>
															</c:otherwise>
														</c:choose>
													
													</dd>
												</dl>
											</section>
											</div>
											<input value="下一题"  class="comm-btn c-btn-1 fr mt10 mr10 mb10 nextoneqst" type="button" onclick="nextqst(${numIndex+2})" />
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
