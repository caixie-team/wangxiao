<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>考试系统</title>
	
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/exam/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript">
	var subjectId = '${subjectId}';
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
			onClick: onClick,
			onAsyncSuccess: zTreeOnAsyncSuccess//异步加载完的回调
		},
		view: {
			addDiyDom: addDiyDom,
			showLine: false
		},
		async: {
			enable: true,
			type: "post",
			url:"${ctx}/point/queryPointAndQuestionRecordListByCusId",
			otherParam: [],
			dataFilter: ajaxDataFilter
		}
	};
	function addDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId + IDMark_A);
			if ($("#diyBtn_"+treeNode.id).length>0) return;
			var qstNum = treeNode.qstCount;
			var rightNum = treeNode.cusRightQstNum;
			var editStr = '<div class="qst-right-num"><span class="qst-num">'+qstNum+'</span><span class="right-num">'+rightNum+'</span></div>';
			aObj.parent().prepend(editStr);
			
		}
	function onClick(e,treeId, treeNode) {
	}
	var IDMark_Span = "_span",IDMark_A = "_a";
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
	
 	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
		trBg();
	}); 
 	//ztree
	</script>
</head>
<body>
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
										<li><a href="${ctx}/paper/toExamPaperRecordList" title="练习历史">练习历史</a></li>
										<li><a href="${ctx}/quest/toErrorQuestionList" title="错误试题">错误试题</a></li>
										<li><a href="${ctx}/quest/toNoteQuestionList" title="习题笔记">习题笔记</a></li>
										<li><a href="${ctx}/quest/favoriteQuestion" title="习题收藏">习题收藏</a></li>									</ol>
								</section>
							</div>
						</aside>
					</section>
					<article class="t-paper-wrap">
						<section class="t-paper b-fff">
							<section class="t-report-jl">
								<div class="mt20">
									<section class="of">
										<div class="a-s-n-title pl50 of">
											<span>综合能力评估：</span>
										</div>
										<div class="test-nl-report answer-sheet-number pr">
											<aside class="t-n-r-01 pa">
												<span class="c-666 fsize12 vam">平均分排名：</span><span class="c-green fsize18 f-fM vam">${AverageScoreRanking }</span>
											</aside>
											<aside class="t-n-r-02 pa">
												<span class="c-666 fsize12 vam">正确题数：</span><span class="c-red fsize18 f-fM vam">${cusDateRecord.rightQstNum }</span>
											</aside>
											<aside class="t-n-r-03 pa">
												<span class="c-666 fsize12 vam">做错题数：</span><span class="c-orange fsize18 f-fM vam">${cusDateRecord.errorQstNum }</span>
											</aside>
											<aside class="t-n-r-04 pa">
												<span class="c-666 fsize12 vam">做过题数：</span><span class="c-blue fsize18 f-fM vam">${cusDateRecord.qstNum }</span>
											</aside>
											<aside class="t-n-r-05 pa">
												<span class="c-666 fsize12 vam">试卷平均分：</span><span class="c-master fsize18 f-fM vam">${cusDateRecord.averageScore }</span>
											</aside>
										</div>
									</section>
								</div>
								<div class="mt20 mb50 pl20 pr20">
									<h5 class="c-666 mb10">考点掌握情况：</h5>
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
</body>
</html>