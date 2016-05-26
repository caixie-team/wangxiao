<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>

<script type="text/javascript">
function selectIntegral(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
 $(function(){//初始化时间
	if('${userIntegralRecord.status}'==null ||'${userIntegralRecord.status}'==''){
		$("#handle").val(-1);
	}else{
		$("#handle").val('${userIntegralRecord.status}');
	}
}); 
function cleanIntegral(){
	$("#name").val("");
	//清空时间
	$("input[name='startDate']:first").attr("value", "");
	$("input[name='endDate']:first").attr("value", "");
	$("#handle").val(-1);
	$("#handle").find('option').eq(0).attr('selected', true);
}
function toAdd(){
	  window.location.href="${ctx}/admin/websitemem/toAdd";	
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">兑换记录</strong> / <small>积分兑换记录</small>
		</div>
	</div>
	<hr/>
	<!-- 公共右侧样式 -->
							<form action="${ctx}/admin/user/exchange" method="post" id="searchForm" class="am-form">
						<div class="mt20 am-padding admin-content-list">
							<div class="am-tab-panel am-fade am-active am-in">
							       <div class="am-g am-margin-top am-u-sm-5">
		                             <div class="am-u-sm-4 am-text-right">
										<span class="ddTitle"><font>用户邮箱:</font></span>
									 </div>
		            				  <div class="am-u-sm-8">
									<input type="text" name="email" id="name" value="${userIntegralRecord.email}"/>
									 </div>
									</div>
									<div class="am-g am-margin-top am-u-sm-5">
		                              <div class="am-u-sm-4 am-text-right">
									<span class="ddTitle"><font>开始日期:</font></span>
									 </div>
		            				<div class="am-u-sm-8">
									<input type="text" name="startDate" data-am-datepicker="{format: 'yyyy-mm-dd'}"  value="${userIntegralRecord.startDate}" readonly="readonly" id="startDate" class="AS-inp"/>
									</div>
									</div>
									 <div class="mt20 clear"></div>
									<div class="am-g am-margin-top am-u-sm-5">
		                             <div class="am-u-sm-4 am-text-right">
									<span class="ddTitle"><font>处理状态:</font></span>
									</div>
		            				  <div class="am-u-sm-8">
									   <select name="status" id="handle" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
										<option value="-1">--全部--</option>
										<option value="0" <c:if test="${userIntegralRecord.status==0 }"></c:if>>未处理</option>
										<option value="1" <c:if test="${userIntegralRecord.status==1 }"></c:if>>已处理</option>
									</select>
									 </div>
									</div>
									<div class="am-g am-margin-top am-u-sm-5">
		                              <div class="am-u-sm-4 am-text-right">
									<span class="ddTitle"><font>结束日期:</font></span>
									</div>
		            				  <div class="am-u-sm-8">
									<input type="text" name="endDate" data-am-datepicker="{format: 'yyyy-mm-dd'}"  value="${userIntegralRecord.endDate}"  readonly="readonly" id="endDate" class="AS-inp"/>
									 </div>
									</div>
									<div class="am-g am-margin-top am-u-sm-5">
		                              <div class="am-u-sm-4 am-text-right">
									 </div>
		            				  <div class="am-u-sm-8">
									 </div>
									</div>
									<div class="am-g am-margin-top am-u-sm-5">
		                              <div class="am-u-sm-4 am-text-right">
									</div>
		            				  <div class="am-u-sm-8">
						   			<button type="button" class="am-btn am-btn-warning" onclick="selectIntegral()">
					            	  <span class="am-icon-search"></span> 搜索
					             	  </button>
									<input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="cleanIntegral()"/>
									 </div>
									</div>
						<div class="mt20"> </div>
				    	<div class="am-g"> </div>
					  </div>	
					</div>
					</form>
					<div class="clearfix"></div>
				<!-- <div class="mt10 clearfix">
					<button class="am-btn am-btn-success" title="新增" type="button" onclick="toAdd()">
								<span class="am-icon-plus"></span>
								新建资讯
					</button>
					</div>  -->
					<div class="mt20"></div>
		<table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
					<thead>
						<tr>
							<th style="text-align:center;"><span>兑换礼品</span></th>
							<th style="text-align:center;"><span>用户邮箱</span></th>
							<th style="text-align:center;"><span>时间</span></th>
							<th style="text-align:center;"><span>收货地址</span></th>
							<th style="text-align:center;"><span>状态</span></th>
						</tr>
					</thead>
		
		<tbody>
		<c:if test="${userIntegralRecordList.size()>0}">
		<c:forEach items="${userIntegralRecordList}" var="igRecord">
							<tr style="text-align:center;">
								<td>${igRecord.giftName}</td>
								<td>${igRecord.email}</td>
								<td><fmt:formatDate value="${igRecord.createTime }" type="both"/></td>
								<td>${igRecord.address}</td>
								<td>
								<c:if test="${igRecord.courseId==0 }">
								<c:if test="${igRecord.status==0 }"><a class="btn smallbtn btn-y" href="${ctx }/admin/user/editrecord/${igRecord.id}">未处理</a></c:if>
								<c:if test="${igRecord.status==1 }"><a class="btn smallbtn btn-y" href="javascript:void(0)">标记为已处理</a></c:if>
								</c:if>
								<c:if test="${igRecord.courseId!=0 }">
								<a class="ml10 btn smallbtn" href="javascript:void(0)" style="color:#666666">兑换课程完成</a>
								</c:if>
								</td>
								<%-- <td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y" onclick="deladmin(${ig.id})" href="javascript:void(0)"
									title="查看详情">查看详情</a></td> --%>
							</tr>
						</c:forEach>
		</c:if>
		<c:if test="${userIntegralRecordList.size()==0||userIntegralRecordList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
						<center>
						<big>
							<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
								<span class="vam ml10">还没有积分兑换记录！</span>
						</big>
						</center>
						</div>
					</td>
				</tr>
				</c:if>
		</tbody>
		</table>
		<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
</body>
</html>