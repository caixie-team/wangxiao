<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>员工列表</title>
<script type="text/javascript">
	//查询任务
	function submitSearch(){
		$("#searchForm").submit();
	}
	//清空查询条件
	function clearSearch(){
		$("#id").val("");
		$("#name").val("");
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/arrange/getArrangeRecordList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li>
												<span class="ddTitle"> 
												<font>考试ID：</font>
												</span> 
												<input type="text" name="arrangeRecord.arrangeId" value="${arrangeRecord.arrangeId }" id="id" />
											</li> 
											<li>
												<span class="ddTitle"> 
												<font>考试名称：</font>
												</span> 
												<input type="text" name="arrangeRecord.arrangeName" value="${arrangeRecord.arrangeName }" id="name" />
											</li> 
											<li>
												<span class="ddTitle"></span>
												<input class="am-btn am-btn-danger" type="button" onclick="submitSearch()" name="" value="查询"/>
												<span class="ddTitle"></span>
												<input class="am-btn am-btn-danger" type="button" onclick="clearSearch()" name="" value="清空"/>
											</li>
										</ul>
									</div>
									<div class="clearfix"></div>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="10%"><span>考试ID</span></th>
									<th width="10%"><span>考试名称</span></th>
									<th width="10%"><span>员工姓名</span></th>
									<th width="10%"><span>试卷名</span></th>
									<th width="10%"><span>分数</span></th>
									<th width="10%"><span>提交试卷时间</span></th>
									<th width="10%"><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${arrangeRecordList.size()>0}">
									<c:forEach items="${arrangeRecordList}" var="arrangeRecordList">
									<tr>
										<td>
											${arrangeRecordList.arrangeId }
										</td>
										<td>
											${arrangeRecordList.arrangeName }
										</td>
										<td>
											${arrangeRecordList.nickname }
										</td>
										<td>
											${arrangeRecordList.exampaperName }
										</td>
										<td>
											${arrangeRecordList.score }
										</td>
										<td>
											<fmt:formatDate value="${arrangeRecordList.submitTime }" pattern="yyyy-MM-dd" />
										</td>
										<td>
											<a href="${ctx}/admin/arrange/exam/papers/${arrangeRecordList.userId }" title="任务详情" class="btn smallbtn btn-y">查看</a>
										</td>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${arrangeRecordList.size()==0||arrangeRecordList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有考试信息！</span>
											</div>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
						<!-- /pageBar end -->
					</div>
					<!-- /commonWrap -->
				</div>
			<!-- 内容 结束 -->
		</form>
</body>
</html>
