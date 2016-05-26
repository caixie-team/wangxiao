<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/edu/css/u_mygroupuser.css" />
<script type="text/javascript"
	src="${ctximg}/static/edu/js/highChart/highcharts.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/admin/js/jquery.epiclock.js"></script>	
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt"> <li class="current"><a class="vam" title="我的员工" href="javascript:void(0)">我的员工</a></li> </ul>
				<div class="clear"></div>
			</section>
			<div id="divIndexOfDeptManagerTopInfor"  class="comm-tips-1" style="min-height: 150px;">
			 <div id = "group_information ">		  
				    <div id="1" class="cc pull-left depart-details pt5 lh24">
						      <p class="text-grey">
						                    部门名称：<span class="font-size-18 text-normal">  ${groupname } </span>
						      </p>
	                          <p class="text-grey">
						                     部门人数：<span class="font-size-18 text-normal"> ${number }  </span>
						      </p>                                                                                       
	                            
				     </div>			     
				   <div id="2">
						 
						      <h5>
						                    部门任务
						      </h5>
						
	                          <p>
						                     任务总数 ：${tasknumber }
						      </p>   
						          <p>
						                     已完成：${task }
						      </p>                                                                                      
				   </div>
			  </div>
			  <form action="${ctx}/uc/groupuser" id="searchForm" method="post">
			     <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			  </form>
			  <table style="width: 836px;"  border="0" cellspacing="0" cellpadding="0" class="u-order-table">
			 			 <thead>
								<tr>
									<th width="10%"><span>考试名称</span></th>
									<th width="10%"><span>部门</span></th>
									<th width="10%"><span>试卷名</span></th>
									<th width="10%"><span>是否完成</span></th>
									<th width="10%"><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${groupArrangeList.size()>0}">
									<c:forEach items="${groupArrangeList}" var="groupTaskList">
									<tr>
										<td>
											${groupTaskList.name }
										</td>
										<td>
											${groupTaskList.groupName }
										</td>
										<td>
											${groupTaskList.exampaperName }
										</td>
										<c:if test="${groupTaskList.iscomplete==0}">
										 <td> 还未完成</td>
										</c:if>
										<c:if test="${groupTaskList.iscomplete==1}">
										 <td>已完成</td>
										</c:if>
										<c:if test="${groupTaskList.iscomplete==1}">
										<td>
											<a href="${ctx }/uc/grouparragne/${groupTaskList.userGroupId }/${groupTaskList.id }" title="查看" class="btn smallbtn btn-y">查看</a>
										</td>
										</c:if>
										<c:if test="${groupTaskList.iscomplete==0}">
										<td>
											<a href="" title="" class="btn smallbtn btn-y">完成任务</a>
										</td>
										</c:if>
									</tr>
									</c:forEach>
								</c:if>
								<c:if test="${groupArrangeList.size()==0||groupArrangeList==null}">
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
				    	<section>
							<div>
								<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
							</div>
						</section>
			  </div>
	</article>
</body>
</html>
