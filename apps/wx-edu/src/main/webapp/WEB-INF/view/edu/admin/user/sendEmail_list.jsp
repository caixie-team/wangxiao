<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>邮箱列表</title>


<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
<script type="text/javascript">
$(function(){
	  

});
function submitSearch(){
	 if(!$("#startDate").val()==null||!$("#startDate").val()==''){
 		 if($("#endDate").val()==''||$("#endDate").val()==null){
 			dialogFun("课程评论列表","请选择结束日期",0);	
 			return;
 		 }
	}
	if(!$("#endDate").val()==null||!$("#endDate").val()==''){
		if($("#startDate").val()==''||$("#startDate").val()==null){
			dialogFun("课程评论列表","请选择开始日期",0);	
			return;
		}
	} 

	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function clean(){
	$("#nickname,#useremail,#email").val("");
	$("input[name='userEmailMsg.startDate']:first").attr("value", "");
	$("input[name='userEmailMsg.endDate']:first").attr("value", "");

    $("#type").val(0);
    $("#status").val(0);
    $('#type').each(function() {
		var _this = $(this);
		_this.find('option').eq(0).attr('selected', true);
	});
    $('#status').each(function() {
		var _this = $(this);
		_this.find('option').eq(0).attr('selected', true);
	});
}
</script>
</head>
<body  >


<!-- 内容 开始  -->

			<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">邮件管理</strong> / <small>邮件列表</small></div>
</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="${ctx}/admin/user/sendEmaillist" name="searchForm"  class="am-form" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20 am-padding admin-content-list">
						<caption>
								<div class=" fl">
										<div class="am-g am-margin-top am-u-sm-6 am-text-left">
											<div class="am-u-sm-6 am-text-right">
												<span class="ddTitle"><font>邮箱：</font></span>
											</div>
											<div class="am-u-sm-6 am-u-end">
												<input type="text"  class=" am-input-sm"   name="userEmailMsg.email" value="${userEmailMsg.email}" id="email" />
											</div>
											</div>
										<div class="am-g am-margin-top am-u-sm-6 am-text-left">
											<div class="am-u-sm-6 am-text-right">
											<span class="ddTitle"><font>发送时间：</font></span>
											</div>
											<div class="am-u-sm-6 am-u-end">
											
											<input type="text" readonly="readonly" name="userEmailMsg.startDate"  data-am-datepicker="{format: 'yyyy-mm-dd'}" class=" am-input-sm"  value="${userEmailMsg.startDate}"  id="startDate" />
										</div>
										</div>
										<div class="am-g am-margin-top am-u-sm-6 am-text-left">
											<div class="am-u-sm-6 am-text-right">
											<span class="ddTitle"><font>结束时间：</font></span>
											</div>
											<div class="am-u-sm-6 am-u-end">
											
											<input type="text" readonly="readonly" name="userEmailMsg.endDate" data-am-datepicker="{format: 'yyyy-mm-dd'}" class=" am-input-sm"     value="${userEmailMsg.endDate}"  id="endDate" />
											</div>
										</div>
										<div class="am-g am-margin-top am-u-sm-6 am-text-left">
												<div class="am-u-sm-6 am-text-right">
                                           		 	<span class="ddTitle"><font>类型：</font></span>
                                           		 </div>
                                         	 <div class="am-u-sm-6 am-u-end">
                                          	  <select  data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" name="userEmailMsg.type" id="type">
                                             	   <option value="0">请选择</option>
                                             	   <option value="1">普通</option>
                                             	   <option value="2">定时</option>
                                           		 </select>
                                           		  <script>
                                               		 $("#type").val('${userEmailMsg.type}');
                                      		      </script>
                                           	 </div>
                                            </div>
                                           <div class="am-g am-margin-top am-u-sm-6 am-u-end am-text-left">
											<div class="am-u-sm-6 am-text-right">
												<span class="ddTitle"><font>是否发送：</font></span>
											</div>
											<div class="am-u-sm-6 am-u-end">
												<select  data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" name="userEmailMsg.status" id="status">
													<option value="0">请选择</option>
													<option value="1">已发送</option>
													<option value="2">未发送</option>
												</select>
												<script>
													$("#status").val('${userEmailMsg.status}');
												</script>
											</div>
											</div>
								</div>
								<div class="clearfix"></div>
							</div>
						
							</caption>
						</div>
		<div class="mt20">
			<div class="am-g">
				<div class="am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<span><a href="${ctx}/admin/user/toEmailMsg"   class="am-btn am-btn-success" title="发送邮件">发送邮件</a></span>
						</div>
					</div>
				</div>
			<div class="am-u-sm-12 am-u-md-4">
				<div class="am-input-group am-input-group-sm">
						<input type="button"  class="am-btn am-btn-warning" value="查询" name="" onclick="submitSearch()"/>
						<input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
					</div>
				</div>
			</div>
		</div>
						
						
				<div class="mt20">
					<div class="doc-example">
						<div class="am-scrollable-horizontal">
						<table class="am-table am-table-bordered am-table-striped am-text-nowrap" >
						<thead>
							<tr>
								<th class="table-title" width="5%" >ID</th>
                                <th class="table-title" width="10%" >邮件类型</th>
                                <th class="table-title" width="10%" >是否发送</th>
								<th class="table-title" width="10%" >邮件标题</th>
								<th class="table-title" width="15%" >邮箱</th>
								<th class="table-title" width="15%" >创建时间</th>
                                <th class="table-title" width="15%" >发送时间</th>
								<th class="table-title" width="10%" >操作人</th>
								<th class="table-set" width="10%" >操作</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="msg" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${msg.id }</td>
                                <td>${msg.type==1?'普通':'定时' }</td>
                                <td>${msg.status==1?'已发送':'未发送' }</td>
								<td>${msg.title }</td>
								<td>${fn:substring(msg.email, 0, 50)}</td>
								<td><fmt:formatDate value="${msg.createTime }" type="both"/></td>
                                    <td><fmt:formatDate value="${msg.sendTime }" type="both"/></td>
								<td>${msg.loginName}</td>
								<td  class="c_666 czBtn" align="center">
                                    <a  class="am-btn am-btn-primary"  title="查看" href="${ctx}/admin/user/sendEmailMsgInfo/${msg.id }">查看</a>
                                    <c:if test="${msg.type==2&&msg.status==2}">
                                    <a class="am-btn am-btn-warning" title="删除" href="${ctx}/admin/sendEmail/del?id=${msg.id }">删除</a>
                                    </c:if>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
						<td colspan="12">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有邮件信息！</span></big>
								</center>
							</div>
						</td>
					</tr>
							</c:if>
						</tbody>
					</table>
					</div>
					</div>
					</div>
					
					</form>
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				</div><!-- /commonWrap -->
			</div>
</body>
</html>
