<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>考试系统</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/exam/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
	//ztree
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId",
				rootPId: 0
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess//异步加载完的回调
		},
		view: {
			addDiyDom: addDiyDom,
			showLine: false
		},
		async: {
			enable: true,
			type: "post",
			dataType:"json",
			url:"${ctx}/point/queryPointAndQuestionRecordListByCusId",
			otherParam: {"paperRecordId":${paperRecord.id}},
			dataFilter: ajaxDataFilter
		}
	};
	
	var IDMark_Span = "_span",IDMark_A = "_a";
	
	function addDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId + IDMark_A);
			if ($("#diyBtn_"+treeNode.id).length>0) return;
			var qstNum = treeNode.qstCount;
			var rightNum = treeNode.cusRightQstNum;
			var editStr = '<div class="qst-right-num"><span class="qst-num">'+qstNum+'</span><span class="right-num">'+rightNum+'</span></div>';
			aObj.parent().prepend(editStr);
			
	}
	
	function ajaxDataFilter(treeId, parentNode, responseData) {
		var responseData = responseData.entity;
	   if (responseData) {
	      for(var i =0; i < responseData.length; i++) {
	    	  responseData[i].name += "";
	      }
	    } 
	    return responseData;
	};
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		trBg();
	};
	
 	$().ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
	}); 
 	//ztree
 	
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
											<span class="l-f-w-pic-2">&nbsp;</span>
											<div class="mt5">
												<tt class="fsize14 c-333">迷你数据</tt>
											</div>
										</section>
										<section class="l-f-w-menu mt10 pt10">
											<ol>
												<li class="current"><a href="javascript:void(0)" title="查看报告">查看报告</a></li>
												<li><a href="${ctx}/paper/toExamPaperRecord/${paperRecord.id }" title="查看解析">查看解析</a></li>
											</ol>
										</section>
									</div>
								</aside>
							</section>
							<article class="t-paper-wrap">
								<section class="t-paper b-fff">
									<div class="t-p-title of">
										<div class="fr">
											<span class="ml5">交卷时间：</span>
											<span><fmt:formatDate value ="${paperRecord.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
										</div>
										<em class="icon30 t-p-t-icon-2">&nbsp;</em>
										<tt class="vam c-333 fsize18 f-fM">${paperRecord.paperName}</tt>
									</div>
									<div class="clear"></div>
									<section class="t-report-jl">
									
										<div class="mt20">
											<section class="of">
												<div class="a-s-n-title pl50 of">
													<section class="fr mr30 mt5">
														<tt class="c-333"><em class="exp-a-right">&nbsp;</em>代表正确</tt>
														<tt class="c-333 ml15"><em class="exp-a-error">&nbsp;</em>代表错误</tt>
													</section>
													<span>本次练习${fn:length(queryPaperReport)}道，你答对了：</span>
												</div>
												<div class="answer-sheet-number">
													<section class="clearfix">
														<aside class="answer-pic-num">
															<span><c:out value="${paperRecord.correctNum}"/></span>
														</aside>
														<article class="answer-jg">
															<div class="answer-list">
													<ol class="clearfix">		
													<c:set  var="questionNumIndex" value="0" />													
													<c:forEach items="${queryPaperReport}" var="paperReport">
														<c:set var="questionNumIndex" value="${questionNumIndex+1}" />
														<c:choose>
							                              <c:when test="${paperReport.questionStatus==0 }">
																<li class="a-right"><a title="第<c:out value="${questionNumIndex}"/>题" href="javascript:void(0)"><c:out value="${questionNumIndex}"/></a></li>													
							                              </c:when>
							                              <c:otherwise>
																<li class="a-error"><a title="第<c:out value="${questionNumIndex}"/>题" href="javascript:void(0)"><c:out value="${questionNumIndex}"/></a></li>
							                              </c:otherwise>
							                            </c:choose>
													</c:forEach>
													</ol>
													</div>
													</article>
													</section>
													
													<c:if test="${paperRecord.type==2 }">
													<div>
														<ol class="clearfix zh-test-list">
															<li><span>分数：</span><strong><fmt:formatNumber value="${paperRecord.userScore }" pattern="#" /></strong></li>
															<li><span>客观题分数：</span><strong><fmt:formatNumber value="${paperRecord.objectiveScore }" pattern="#" /></strong></li>
															<li><span>主观题分数：</span><strong><fmt:formatNumber value="${paperRecord.subjectiveScore }" pattern="#" /></strong></li>
															<li><span>正确率：</span><strong><fmt:formatNumber value="${paperRecord.accuracy*100 }" pattern="#" />%</strong></li>
															<li><span>分数排名：</span><strong>${ScoreRanking }</strong></li>
															<li><span>正确题数排名：</span><strong>${CorrectNumRanking }</strong></li>
															
														</ol>
													</div>
													</c:if>
												</div>
											</section>
										</div>
										<div class="mt20 mb50 pl20 pr20">
											
											<h5 class="c-666 mb10">本次练习情况：</h5>
											<table width="100%" border="0" class="infor-section-table">
												<thead>
													<tr>
														<th width="50%" align="left">考点</th>
														<th width="25%">试题数量</th>
														<th width="25%">正确题数</th>
													</tr>
												</thead>
												<tbody class="nl-tab">
													<tr>
														<td colspan="3" style="padding: 0">
														<section id="treeDemo" class="mt10 ztree" style="-moz-user-select: none;">
														</section>
														</td>
													</tr>
												</tbody>
											</table>
									
										</div>
									</section>
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