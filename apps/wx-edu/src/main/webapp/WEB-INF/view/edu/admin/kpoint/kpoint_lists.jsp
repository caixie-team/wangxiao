<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>视频学习情况列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeYear: true,
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
});
function clean(){
	$("#courseName,#name,#startDate,#endDate").val("");
}

</script>
</head>
<body  >

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>视频学习情况管理</span> &gt; <span>视频学习情况列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="${ctx}/admin/newkpoint/list" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			
						<caption>
							<div class="capHead">
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
										    <span class="ddTitle"><font>课程名称：</font></span>
											<input type="text" name="courseKpoint.courseName" value="${courseKpoint.courseName}" id="courseName" />
										</li>
										<%-- <li>
											<span class="ddTitle"><font>添加开始日期：</font></span>
											<input type="text" name="kpoint.startDate" value="${kpoint.startDate}"  id="startDate" class="AS-inp"/>
										</li> --%>
									</ul>
									
								</div>
                                <div class="w50pre fl">
                                    <ul class="ddBar">
                                    	<li>
										    <span class="ddTitle"><font>视频名称：</font></span>
											<input type="text" name="courseKpoint.name" value="${courseKpoint.name}" id="name" />
										</li>
										<li>
											<input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="goPage(1)"/>
                                            <input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
										</li>
                                       <%--  <li>
                                            <span class="ddTitle"><font>添加结束日期：</font></span>
                                            <input type="text" name="kpoint.endDate" value="${kpoint.endDate}"  id="endDate" class="AS-inp"/>
                                        </li> --%>
                                       <!--  <li><input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="goPage(1)"/>
                                            <input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
                                        </li> -->
                                    </ul>

                                </div>
								<div class="clearfix"></div>
							</div>
							<%-- <div class="mt10 clearfix">
								<p class="fl czBtn">
									<span><a href="${ctx}/admin/kpoint/toAdd" title="添加视频名称"><em class="icon14 new">&nbsp;</em>添加视频名称</a></span>
									<!-- <span class="ml10"><a href="javascript:delDepartmentbatch();" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span> -->
								</p>
							</div>  --%>
						</caption>
						<thead>
							<tr>
								<th width="8%"><span>ID</span></th>
								<th width="8%"><span>课程名称</span></th>
								<th width="8%"><span>名称</span></th>
								<th width="8%"><span>观看次数</span></th>
								<th width="8%"><span>观看人数</span></th>
								<!-- <th width="8%"><span>操作</span></th> -->
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="kpoint" >
							<tr>
								<td>${kpoint.id }</td>
								<td>${kpoint.courseName }</td>
								<td>${kpoint.name }</td>
								<td>${kpoint.playcount }</td>
								<td>
								<c:if test="${empty kpoint.personNum }">
									0
								</c:if>
								<c:if test="${not empty kpoint.personNum }">
									${kpoint.personNum }
								</c:if>
								</td>
								<%-- <td><fmt:formatDate value="${kpoint.addTime }" pattern="yyyy-MM-dd hh:mm:ss " /></td> --%>
								<%-- <td>
								<a class="ml10 btn smallbtn btn-y" title="修改视频名称" href="${ctx}/admin/kpoint/toUpdate/${kpoint.id}">修改</a>
							    <a class="ml10 btn smallbtn btn-y"  title="删除" href="javascript:del(${kpoint.id })">删除</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有视频学习情况信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
		
</body>
</html>
