<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>考试系统</title>
<script type="text/javascript" src="${ctximg}/static/exam/js/exam-test.js?v=${v}"></script> 
<script type="text/javascript">
//删除错题
function delErrorQuestion(id,obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/quest/delerrorQuestion/"+id,
		async:false,
		success:function(result){
			if(result.success==true){
				$(obj).remove();
			}
		}
	});
}
</script>
</head>
<body>
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
											<span class="l-f-w-pic-3">&nbsp;</span>
											<div class="mt5">
												<tt class="fsize14 c-333">我的练习</tt>
											</div>
										</section>
										<section class="l-f-w-menu mt10 pt10">
											<ol>
												<li><a href="${ctx}/paper/toExamPaperRecordList" title="练习历史">练习历史</a></li>
												<li><a href="${ctx}/quest/toErrorQuestionList" title="错误试题">错误试题</a></li>
												<li><a href="${ctx}/quest/toNoteQuestionList" title="习题笔记">习题笔记</a></li>
												<li><a href="${ctx}/quest/favoriteQuestion" title="习题收藏">习题收藏</a></li>
											</ol>
										</section>
									</div>
								</aside>
							</section>
							<article class="t-paper-wrap">
								<section class="t-paper b-fff">
									<div class="t-p-title">
										<em class="icon30 t-p-t-icon-1">&nbsp;</em>
										<tt class="vam c-333 fsize18 f-fM">正在查看试题详情</tt>
									</div>
								</section>
								<section class="t-paper-box">
									<div>
										<!-- /试卷列表开始 -->
										<article class="t-paper-one">
											<section class="t-paper-topic">
												<span class="t-p-topic-num"><tt>01</tt></span>
												<p>${queryQuestion.qstContent }</p>
											</section>
											<section class="t-p-options t-p-o-analysis">
												<c:if test="${queryQuestion.qstType!=6 }">
												<ol>
													<c:forEach items="${queryQuestion.options }" var="option">
													<c:set var="optOrder" value="${option.optOrder }"/><c:set var="isAsr" value="${queryQuestion.isAsr}"/>
													<li><a href="javascript:void(0);" class="${fn:indexOf(optOrder,isAsr )<0?'':'c-green'}">${option.optOrder }、${option.optContent }</a></li>
													</c:forEach>
												</ol>
												</c:if>
											</section>
											<section class="is-options">
												<div class="fr">
													<c:if test="${queryQuestion.errorQuestionId!=0}">
														<span class="c-999 mr10"><em class="icon14 delete-test">&nbsp;</em><a class="c-666 mr10 fsize14 vam" title="" href="javascript:void(0)" onclick="delErrorQuestion(${queryQuestion.errorQuestionId},this)">删除错题</a>|</span>
													</c:if>
													<span class="pr10 c-999"><em class="icon18 collect-icon">&nbsp;</em>
													
													<c:choose>
														<c:when test="${queryQuestion.favoritesId==null || queryQuestion.favoritesId==0 }">
															<a href="javascript:void(0)" onclick="favorite(${queryQuestion.id },this)" qstId="${queryQuestion.id }" title="收藏试题" class="vam c-666 ml5">收藏试题</a>
														</c:when>
														<c:otherwise>
															<a href="javascript:void(0)" onclick="notFavorite(${queryQuestion.id },this)" title="取消收藏试题" qstId="${queryQuestion.id }" class="vam c-666 ml5">取消收藏试题</a>
														</c:otherwise>
													</c:choose>
												</span>|&nbsp;&nbsp;&nbsp;
													<span class="jx-show-btn"><a href="javascript:void(0)" class="c-666 vam">收起解析</a><em class="icon14 jx-icon">&nbsp;</em></span>
												</div>
												<c:if test="${queryQuestion.qstType!=6 }">
												<div class="fl t-p-is-options c-fsize12 c-333">
													<tt><font class="vam">正确答案是：</font><font class="fsize16 c-green f-fM vam">${queryQuestion.isAsr }</font></tt>
													<tt class="ml10"></tt>
												</div>
												</c:if>
												<div class="clear"></div>
												<section class="analysis-wrap pb50">
													<dl class="clearfix">
														<dt><span class="analysis-tb"><em class="icon24 ana-jx">&nbsp;</em><font class="fsize14 ml5">解析</font></span></dt>
														<dd>
															<p>${queryQuestion.qstAnalyze }</p>
														</dd>
													</dl>
													<dl class="clearfix">
													<dt><span class="analysis-tb"><em class="icon24 ana-kd">&nbsp;</em><font class="fsize14 ml5">考点</font></span></dt>
													<dd>
														<div class="pr a-kj-txt a-kj-show" pointId="${queryQuestion.parentId }">
															<span class="a-kj-title">${queryQuestion.pointName }</span>
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
														<c:when test="${queryQuestion.noteId==null || queryQuestion.noteId==0}">
															<dd>
																<p class="noteContent">${queryQuestion.noteContent }</p>
																<span style="margin-left:425px"   class="analysis-tbing hand" title="点击编辑笔记内容" onclick="addNote(this)" qstId="${queryQuestion.id }"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
															</dd>
														</c:when>
														<c:otherwise>
															<dd>
																<p class="noteContent">${queryQuestion.noteContent }</p>
																<span style="margin-left:425px"  class="analysis-tbing hand" title="点击编辑笔记内容" onclick="updateNote(this)" qstId="${queryQuestion.id}"><em class="icon24 ana-bj">&nbsp;</em><font class="fsize14 ml5">编辑笔记</font></span>
															</dd>
														</c:otherwise>
													</c:choose>
												</dl>
												</section>

											</section>
										</article>
									</div>
									<!-- /单选试卷列表结束 -->
								</section>
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