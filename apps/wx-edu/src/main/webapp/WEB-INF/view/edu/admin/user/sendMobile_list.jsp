<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>短信列表</title>

<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>


<script type="text/javascript">
$(function(){
	 
	  var type="${userMobileMsg.type}";
	  if(type!=null&&type!=""){
		  $("#type").val('${userMobileMsg.type}');
	  }
	  var status="${userMobileMsg.status}";
	  if(status!=null && status!=""){
		  $("#status").val('${userMobileMsg.status}');
	  }
	  
	  
});
function submitSearch(){
	 if(!$("#startDate").val()==null||!$("#startDate").val()==''){
 		 if($("#endDate").val()==''||$("#endDate").val()==null){
 			dialogFun("短信列表","请选择结束日期",0);	
 			return;
 		 }
	}
	if(!$("#endDate").val()==null||!$("#endDate").val()==''){
		if($("#startDate").val()==''||$("#startDate").val()==null){
			dialogFun("短信列表","请选择开始日期",0);	
			return;
		}
	} 

	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function clean(){
	$("#nickname,#useremail,#mobile").val("");
	$("input[name='userMobileMsg.startDate']:first").attr("value", "");
	$("input[name='userMobileMsg.endDate']:first").attr("value", "");
	$("#type,#status").val(0);
	$('#type,#status').each(function() {
		var _this = $(this);
		_this.find('option').eq(0).attr('selected', true);
	});
}

function delMsg(id){
	dialogFun("短信列表","确定要删除吗？",2,"javascript:delMsgs("+id+")");
	
}

function delMsgs(id){
	$.ajax({
		url : "${ctx}/admin/user/delMsgById/"+id,
		data : {},
		type : "post",
		dataType : "json",
		success : function(result){
			
			dialogFun("短信列表",result.message,1);
            if(result.success==true){
                window.location.href="/admin/user/sendMsglist";
            }
		}
	})
}

</script>
</head>
<body  >


<!-- 内容 开始  -->

<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">短信管理</strong> / <small>短信列表</small></div>
</div>
<hr />
			<!-- /tab1 begin-->
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/user/sendMsglist" class="am-form"
				name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage"
					value="${page.currentPage}" />
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						<span class="ddTitle"><font>手机号：</font></span>
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" name="userMobileMsg.mobile"
							value="${userMobileMsg.mobile}" id="mobile" />
					</div>
				</div>

				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						<span class="ddTitle"><font>状态：</font></span>
					</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="userMobileMsg.status" id="status">
							<option value="0" selected="selected">请选择</option>
							<option value="1">已发送</option>
							<option value="2">未发送</option>
						</select>
					</div>
				</div>

				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						<span class="ddTitle"><font>类型：</font></span>
					</div>
					<div class="am-u-sm-8 am-u-end">
						<select
							data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"
							name="userMobileMsg.type" id="type">
							<option value="0" selected="selected">请选择</option>
							<option value="1">普通</option>
							<option value="2">定时</option>
						</select>
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						<span class="ddTitle"><font>创建时间</font></span>
					</div>
					<div class="am-u-sm-8 am-u-end">

						<input type="text" readonly="readonly"
							name="userMobileMsg.startDate"
							data-am-datepicker="{format: 'yyyy-mm-dd'}" class=" am-input-sm"
							value="${userMobileMsg.startDate}" id="startDate" />
					</div>
				</div>
				
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						<span class="ddTitle"><font>结束时间</font></span>
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" readonly="readonly"
							name="userMobileMsg.endDate"
							data-am-datepicker="{format: 'yyyy-mm-dd'}" class=" am-input-sm"
							value="${userMobileMsg.endDate}" id="endDate" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-8 am-u-end">
						<input type="button"  class="am-btn am-btn-warning"  value="查询" name="" onclick="submitSearch()"/>
						<input type="button" class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
					</div>
				</div>
				<div class="clearfix"></div>
			</form>
		</div>
	
</div>
<div class="mt20">
	<div class="am-g">
				<div class="am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<span><a href="${ctx}/admin/user/toMsg" class="am-btn am-btn-success" title="发送短信">发送短信</a></span>
						</div>
					</div>
				</div>
	</div>
	<div class="mt20 ">
		<div class="am-scrollable-horizontal">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th class="table-title" width="5%"><span>ID</span></th>
						<th class="table-title" width="10%"><span>类型</span></th>
						<th class="table-title" width="15%"><span>短信内容</span></th>
						<th class="table-title" width="10%"><span>手机号</span></th>
						<th class="table-title" width="15%"><span>创建时间</span></th>
						<th class="table-title" width="15%"><span>发送时间</span></th>
						<th class="table-title" width="10%"><span>状态</span></th>
						<th class="table-title" width="10%"><span>操作人</span></th>
						<th class="table-title" width="10%"><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${list.size()>0}">
						<c:forEach items="${list}" var="msg">
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${msg.id }</td>
								<td>${msg.type==1?'普通':'定时'}</td>
								<td>${fn:substring(msg.content,0,50) }</td>
								<td>${fn:substring(msg.mobile,0,55) }<c:if
										test="${msg.mobile.length()>55 }">......</c:if></td>
								<td><fmt:formatDate value="${msg.createTime }" type="both" /></td>
								<td><fmt:formatDate value="${msg.sendTime }" type="both" /></td>
								<td><c:if test="${msg.status==1 }">已发送</c:if> <c:if
										test="${msg.status==2 }">未发送</c:if></td>
								<td>${msg.loginName}</td>
								<td class="c_666 czBtn" align="center"><a
									class="am-btn am-btn-primary" title="查看"
									href="${ctx}/admin/user/sendMsgInfo/${msg.id }">查看</a> <c:if
										test="${msg.status==2&&msg.type==2 }">
										<a class="am-btn am-btn-warning" title="删除"
											href="javascript:delMsg(${msg.id })">删除</a>
									</c:if> <%-- <a class="ml10 btn smallbtn btn-y" title="修改密码" href="${ctx}/admin/user/toupdatepwd/${user.id}">修改密码</a> --%>
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
											style="font-size: 34px;"></i> <span class="vam ml10">还没有短信信息！</span></big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
					
				</tbody>
			</table>
			
		</div>	
		<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				<!-- /pageBar end -->
	</div>
</div>
</body>
</html>
