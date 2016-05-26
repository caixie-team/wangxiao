<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的测评</title>
</head>
<body>
<article class="uc-m-content mb50">
	<header class="uc-com-title">
		<span>我的测评</span>
	</header>
	<div class="i-box">
		<div>
			<div class="">
				<c:if test="${empty recordList}">
					<section class="no-data-wrap">
						<em class="no-data-ico cTipsIco">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">还没有评测...</span>
					</section>
				</c:if>
				<c:if test="${not empty recordList}">
					<section class="uc-c-table u-homework-box">
						<ol class="uc-c-table-col-2 uc-cTab-th clearfix uc-test">
							<li class="fl col-25">
								<div class="hLh30 txtOf tal">
									<span class="fsize14 ml10">测试名称</span>
								</div>
							</li>
							<li class="fl col-25">
								<div class="hLh30 txtOf tal">
									<span class="fsize14 ml10">试卷名称</span>
								</div>
							</li>
							<li class="fl col-25">
								<div class="hLh30 txtOf tac">
									<span class="fsize14 ml10">结果</span>
								</div>
							</li>
							<li class="fl col-25 tac">
								<span class="fsize14 ml10">操作</span>
							</li>
						</ol>
						<c:forEach items="${recordList}" var="record">
							<ol class="uc-c-table-col-2 clearfix">
								<input type="hidden" class="ids" value="${record.exampaperId}"/>
								<input type="hidden" id="beginTime${record.exampaperId}" value="${record.beginTimeNum}"/>
								<input type="hidden" id="endTime${record.exampaperId}" value="${record.endTimeNum}"/>
								<li class="fl col-25">
									<div class="hLh30 txtOf pl10">
										<span class="fsize14 c-666">${record.arrangeName}</span>
									</div>
								</li>
								<li class="fl col-25 u-test-align">
									<div class="hLh30 txtOf pl10">
										<span class="fsize14 c-666">${record.exampaperName}</span>
									</div>
								</li>
								<li class="fl col-25 tar">
									<div class="hLh30 txtOf">
										<c:if test="${record.isComplete==0}">
											<span class="fsize12 c-666 am-text-left format-date" data-diff="${record.beginTimeNum }">未完成</span>
										</c:if>
										<c:if test="${record.isComplete==1}">
											<span class="fsize12 c-red">${record.score}分</span>
											<span class="fsize12 c-666"> / <fmt:formatDate value="${record.submitTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></span>
										</c:if>
									</div>
								</li>
								<li class="fl col-25 tac u-test-align">
									<c:if test="${record.isComplete==0}">
										<a href="javascript:void(0)" onclick="startArrangeExam(${record.exampaperId},${record.id},${record.isComplete },${record.isRepeat})" title="" class="c-blue fsize12 vam c-hk-learn">开始评测</a>
									</c:if>
									<c:if test="${record.isComplete==1}">
										<c:if test="${record.isRepeat==1}">
											<a href="javascript:void(0)" onclick="startArrangeExam(${record.exampaperId},${record.id},${record.isComplete },${record.isRepeat})" title="" class="c-blue fsize12 vam c-hk-learn">开始评测</a>
										</c:if>
										<a href="${ctxexam}/paper/getExamPaperReport/${record.examRecordId}" title="" class="c-green fsize12 vam c-hk-look">查看报告</a>
									</c:if>
								</li>
							</ol>
						</c:forEach>
						<div class="clear"></div>
					</section>
				</c:if>
				<form action="${ctx}/uc/myArrangeExam" method="post" id="searchForm" name="searchForm">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				</form>
				<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
			</div>
		</div>
	</div>
</article>
<script type="text/javascript" src="${ctximg }/static/common/jquery.countdown.js"></script>
<script type="text/javascript">
	/**
	 * 判断是否可重复做 ，isRepeat 0不可重复，1可重复
	 * 判断是否可重复做 ，isComplete 0未完成，1已完成
	 * ifarrange 判断是否是考试任务
	 */
	function startArrangeExam(paperId,arrangeRecordId,isComplete,isRepeat){
		if(isComplete==1&&isRepeat==0){//已完成, 不可重复作答
			dialogFun("提示","此测评不可重复做答",0);
			return;
		}
		var beginTimeNum = $("#beginTime"+paperId).val();
		var endTimeNum = $("#endTime"+paperId).val();
		if(beginTimeNum<=0&&endTimeNum>0){
			window.open(baselocationexam+"/paper/toExamPaper/"+paperId+"?arrangeRecordId="+arrangeRecordId+"&ifarrange=1");
		}else {
			dialogFun("提示","当前时间不在答题时间内",0);
		}
	}

	$(function(){
		//倒计时
		$('.format-date').countdown({
			tmpl : '还有：<span>%{h}</span>时<span>%{m}</span>分<span>%{s}</span>秒开始测评',
			end : function(){
				$(".format-date").html("未完成");
				var id = $(".ids").eq($(".format-date").index()).val();
				$("#beginTime"+id).val(0);
			}
		});
	})
</script>
</body>
</html>