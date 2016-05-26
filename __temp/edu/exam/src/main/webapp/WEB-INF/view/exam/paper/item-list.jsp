<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>考试系统</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/exam/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctximg}/static/exam/js/item.js"></script>
</head>
<body>
		<!-- e-main begin -->
		<section class="e-main">
			<div class="w1000">
				<section class="s-s-tp">
					<div class="s-km-list">
						<ul class="clearfix">
							<li>
								<section class="s-km-item">
									<div class="clearfix">
										<section class="s-km-img"><span class="s-km-i-1">&nbsp;</span></section>
										<!-- <div class="pt50">
											<h2 class="s-km-name">错题智能学习</h2>
											<div class="s-km-desc">
												随机出题，快速测试，提升您的综合能力
											</div>
											<section class="mt15 tac">
												<a href="javascript:void(0)" onclick="kuaisuzhineng(15)" title="" class="s-km-btn">
													<em class="icon20 s-k-btn-1">&nbsp;</em>
													<font class="c-fff f-fM fsize14 vam ml5">来15道</font>
												</a>
											</section>
										</div> -->
										<div class="pt50">
											<h2 class="s-km-name">专项智能学习</h2>
											<div class="s-km-desc">
												针对具体章节，详细到考点，快速击破
											</div>
											<section class="mt15 tac" onclick="zhuanxiang();">
												<a href="javascript:void(0)" title="" class="s-km-btn">
													<em class="icon20 s-k-btn-2">&nbsp;</em>
													<font class="c-fff f-fM fsize14 vam ml5">开始练习</font>
												</a>
											</section>
										</div>
									</div>
								</section>
							</li>
							<li>
								<section class="s-km-item row-line">
									<div class="clearfix">
										<section class="s-km-img"><span class="s-km-i-2">&nbsp;</span></section>
										<div class="pt50">
											<h2 class="s-km-name">错题智能练习</h2>
											<div class="s-km-desc">
												历史错题，针对性测评，打破至酷！
											</div>
											<section class="mt15 tac" onclick="kuaisuzhineng(15);">
												<a href="javascript:void(0)" title="" class="s-km-btn">
													<em class="icon20 s-k-btn-2">&nbsp;</em>
													<font class="c-fff f-fM fsize14 vam ml5">开始练习</font>
												</a>
											</section>
										</div>
									</div>
								</section>
							</li>
							<c:forEach items="${ paperTypeList}" var="ptl" varStatus="index">
							<li>
								<section class="s-km-item ${index.count==2?'row-line-2':''}">
									<div class="clearfix">
										<section class="s-km-img"><span style="background: url('');"><img src="<%=staticImageServer%>${ptl.imgUrl }" alt="" width="126px" height="114px"/></span></section>
										<div class="pt50">
											<h2 class="s-km-name">${ptl.title }</h2>
											<div class="s-km-desc">
												${ptl.describtion }
											</div>
											<section class="mt15 tac">
												<a href="javascript:zujuanmokao(${ptl.id },'${ptl.title }')" title="${ptl.buttonTitle }" class="s-km-btn">
													<em class="icon20 s-k-btn-3">&nbsp;</em>
													<font class="c-fff f-fM fsize14 vam ml5">${ptl.buttonTitle }</font>
												</a>
											</section>
										</div>
									</div>
								</section>
							</li>
							</c:forEach>

							<li class="unBr">
								<section class="s-km-item row-line-1">
									<div class="clearfix col-dotted-line">
										<section class="s-km-img"><span class="s-km-i-6">&nbsp;</span></section>
										<div>
											<h2 class="s-km-name tar"><font class="fsize14">习题笔记</font></h2>
											<div>
												<c:choose>
							                        <c:when test="${queryNoteQuestionitemList!=null && queryNoteQuestionitemList.size()>0 }">
							                          <c:forEach items="${queryNoteQuestionitemList}" var="dinotquestion">
							                            <input type="hidden" class="questionIds" id="${dinotquestion.id }"/>
							                            <section class="bj-link">
							                              <a href="${ctx}/quest/parse/${dinotquestion.id}">
								                              <c:choose>
																	<c:when test="${dinotquestion.qstContent.length()>100}">
																		${fn:substring(dinotquestion.qstContent, 0, 100)}
																	</c:when>
																	<c:otherwise>
																		${dinotquestion.qstContent}
																	</c:otherwise>
															</c:choose>
							                              </a>
							                              <tt class="pa b-time c-999"><fmt:formatDate value="${dinotquestion.addTime}"  pattern="yyyy-MM-dd HH:mm:ss"/> </tt>
							                            </section>
							                          </c:forEach>
					                          				<p class="tar"><a href="${ctx}/quest/toNoteQuestionList" title="查看更多" class="c-master">查看更多&gt;&gt;</a></p>
							                          </c:when>
					                        			<c:otherwise>您还没有笔记</c:otherwise>
					                      </c:choose>
											</div>
										</div>
									</div>
									<div class="clearfix">
										<section class="s-km-img"><span class="s-km-i-7">&nbsp;</span></section>
										<div class="pt10">
											<h2 class="s-km-name tar"><font class="fsize14">错题记录</font></h2>
											<div>
												<c:choose>
												  <c:when test="${queryQuestionitemList!=null && queryQuestionitemList.size()>0}">
												  	<c:forEach items="${queryQuestionitemList}" var="queryQuestionitem">
							                        <input type="hidden" class="questionIds" id="${queryQuestionitem.id }"/>
							                        <section class="bj-link">
							                          <a href="${ctx}/quest/parse/${queryQuestionitem.id }">
							                          
							                          <c:choose>
															<c:when test="${queryQuestionitem.qstContent.length()>100}">
																${fn:substring(queryQuestionitem.qstContent, 0, 100)}
															</c:when>
															<c:otherwise>
																${queryQuestionitem.qstContent}
															</c:otherwise>
													 </c:choose>
														
							                          </a>
							                          <tt class="pa b-time c-999"><fmt:formatDate value="${queryQuestionitem.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></tt>
							                        </section>
							                        </c:forEach>
							                        <p class="tar"><a href="${ctx}/quest/toErrorQuestionList" title="查看更多" class="c-master">查看更多&gt;&gt;</a></p>
												  </c:when>
												  <c:otherwise>&nbsp;&nbsp;您还没有错题</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</section>
							</li>
						</ul>
					</div>
				</section>
			</div>
		</section>
		<!-- e-main end -->
		
		<div style="display: none">
		<form action="${ctx}/paper/getRandomQuestionByPointIds" id="randomtest" name="randomtest" method="post">
			<input type="hidden" id="randompointIds" name="pointIds">
			<input type="hidden" id="randomnum" name="randomnum">
		</form>
		</div>	
</body>
</html>