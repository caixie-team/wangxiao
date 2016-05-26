<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>部门任务</title>
	<script type="text/javascript">
		$(function(){
			//加载ajax分页
			ajaxPage("${ctx}/admin/ajax/planStudyInfo/"+$("#planId").val(),"",1,function(result){
				$("#studyInfo").html(result);
			},true);
			//当前计划概要
			summarySchedule();
		});

		//计划概要
		function summarySchedule() {
			var planId = $("#planId").val();
			$.ajax({
				url: "${ctx}/admin/phase/summarySchedule/" + planId,
				data: {},
				dataType: "json",
				type: "post",
				async: "true",
				success: function (result) {
					if (result.success == true) {
						var phase = result.entity;
						var knowledgeNo = phase.knowledgeNo;
						var studyTimeNo = phase.studyTimeNo;
						var examNo = phase.examNo;
						//课程数
						var courseNo = knowledgeNo*1-examNo*1;
						$("#courseNo").html(courseNo);
						$("#knowledgeNo").html(knowledgeNo);
						$("#studyTimeNo").html(studyTimeNo);
						$("#examNo").html(examNo);
					}
				}
			});
		}
		// 阶段详情
		function phaseDetailProgress(id){
			window.location.href="${ctx}/admin/plan/phaseDetailProgress/"+id;
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">计划管理</strong> / <small>计划进度</small></div>
	</div>
	<hr>
	<input type="hidden" value="${plan.id}" id="planId" />
	<div class="mt20">
		<div data-am-tabs="" class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">

			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-active am-fade am-in">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
						<tr>
							<th class="table-title">①  计划基本信息</th>
						</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div class="am-g am-margin-top am-u-sm-12">
										<div class="am-u-sm-4 am-text-right">
											计划名称
										</div>
										<div class="am-u-sm-8 am-u-end">
											${plan.name}
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="am-g am-margin-top am-u-sm-12">
										<div class="am-u-sm-4 am-text-right">
											计划类型
										</div>
										<div class="am-u-sm-8 am-u-end">
											<c:choose>
												<c:when test="${plan.type==0}">个人计划</c:when>
												<c:otherwise>岗位计划</c:otherwise>
											</c:choose>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="am-g am-margin-top am-u-sm-12">
										<div class="am-u-sm-4 am-text-right">
											计划开始时间
										</div>
										<div class="am-u-sm-8 am-u-end">
											<fmt:formatDate value="${plan.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="am-g am-margin-top am-u-sm-12">
										<div class="am-u-sm-4 am-text-right">
											计划结束时间
										</div>
										<div class="am-u-sm-8 am-u-end">
											<fmt:formatDate value="${plan.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
						<tr>
							<th align="left" colspan="7">
								<span class="">②&nbsp;&nbsp;指定学习内容</span>
							</th>
						</tr>
						<tr>
							<th><span class="">ID</span></th>
							<th><span class="">阶段名称</span></th>
							<th><span class="">阶段描述</span></th>
							<th><span class="">总时长</span></th>
							<th><span class="">平均完成时长</span></th>
							<th><span class="">完成进度</span></th>
							<th><span class="">详细</span></th>
						</tr>
						</thead>
						<tbody id="studyInfo">
						</tbody>
					</table>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
						<tr>
							<th align="left" colspan="2">
								<span class="">③&nbsp;&nbsp;指定学习者</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td width="20%" align="center">此计划指派给谁</td>
							<td>
								<%-- 个人计划 --%>
								<c:if test="${plan.type==0}">
									<c:forEach var="user" items="${userList}" varStatus="count">
										${user.nickname}&nbsp;&nbsp;
									</c:forEach>
								</c:if>
								<%-- 部门计划 --%>
								<c:if test="${plan.type==1}">
										<c:forEach var="group" items="${userGroupList}" varStatus="count">
											${group.name}&nbsp;&nbsp;
										</c:forEach>
									</table>
								</c:if>
							</td>
						</tr>
						</tbody>
					</table>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
						<tr>
							<th align="left" colspan="2">
								<span class="">④&nbsp;&nbsp;计划概要及设置</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;当前计划概要</td>
							<td>
								<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
									<thead>
									<th><span>总知识数</span></th>
									<th><span>试卷数</span></th>
									<th><span>课程数</span></th>
									<th><span>总学时</span></th>
									<th><span>总人数</span></th>
									<th><span>完成人数</span></th>
									<th><span>完成进度</span></th>
									</thead>
									<tbody>
									<tr>
										<td align="center"><font color="red"><span id="knowledgeNo"></span></font>个</td>
										<td align="center"><font color="red"><span id="examNo"></span></font>个</td>
										<td align="center"><font color="red"><span id="courseNo"></span></font>个</td>
										<td align="center"><font color="red"><span id="studyTimeNo"></span></font>分钟</td>
										<td align="center"><font color="red"><span>${plan.peopleNum}</span></font>人</td>
										<td align="center"><font color="red"><span>${plan.completeNum}</span></font>人</td>
										<td align="center"><font color="red"><span>
								<fmt:formatNumber value="${plan.completeNum/plan.peopleNum}" type="percent" />
							</span></font></td>
									</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;当前计划概要</td>
							<td>
								<c:if test="${plan.status==0}">未启动</c:if>
								<c:if test="${plan.status==1}">启动</c:if>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;是否重复</td>
							<td>
								<c:if test="${plan.isRepeat==0}">否</c:if>
								<c:if test="${plan.isRepeat==1}">是</c:if>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<a href="javascript:history.go(-1);" title="返回" class="am-btn am-btn-danger">返回</a>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>