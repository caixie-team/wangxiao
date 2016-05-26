<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>后台操作记录列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
	<script type="text/javascript">
		function submitSearch(){
			var number = /^[0-9]+\.{0,1}[0-9]{0,2}$/;
			var id = $("#id").val();
			if(id!=""&&!number.test(id)){
	    	    dialogFun("提示 ","只能输入数字",0);
	    	    return;
		    }
			$("#pageCurrentPage").val(1);
			$("#searchForm").submit();
		}
		function clean(){
			$("#id,#optAccount,#optUsername,#startTime,#endTime").val("");
		}
	</script>
</head>
<body >
<!-- 全局公共右内容区  开始 -->
	<!-- 公共右侧样式 -->
	<div class="am-cf">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">操作管理</strong> / <small>操作记录列表</small></div>
    </div>
    <hr>
    <!-- 公共右侧标题样式 结束-->
	<form class="am-form" action="${ctx}/admin/optRecord/getOptRecordList" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20 am-padding admin-content-list">
	    	<div class="am-tab-panel am-fade am-active am-in">
		        <div class="am-g am-margin-top am-u-sm-4">
		            <div class="am-u-sm-4 am-text-right">
		            	 ID
		            </div>
		            <div class="am-u-sm-8">
		            	<input type="text" class="am-input-sm" name="optRecord.id" value="${optRecord.id}" id="id" />
		            </div>
		        </div>
				<div class="am-g am-margin-top am-u-sm-4">
		            <div class="am-u-sm-4 am-text-right">
		              	姓名
		            </div>
		            <div class="am-u-sm-8">
		              <input type="text" class="am-input-sm" name="optRecord.optUsername" value="${optRecord.optUsername}" id="optUsername" />
		            </div>
		        </div>
		        <div class="am-g am-margin-top am-u-sm-4">
		            <div class="am-u-sm-4 am-text-right">
		            	 帐号
		            </div>
		            <div class="am-u-sm-8">
		            	<input type="text" class="am-input-sm" name="optRecord.optAccount" value="${optRecord.optAccount}" id="optAccount" />
		            </div>
		        </div>
		        <div class="mt20 clear"></div>
		 		<div class="am-g am-margin-top am-u-sm-4">
		            <div class="am-u-sm-4 am-text-right">
		             	 开始时间
		            </div>
		            <div class="am-u-sm-8 ">
                        <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" class="am-input-sm" name="optRecord.startTime" value="${optRecord.startTime}" id="startTime" />
					</div>
		        </div>
				<div class="am-g am-margin-top am-u-sm-4">
		            <div class="am-u-sm-4 am-text-right">
		              	结束时间
		            </div>
		            <div class="am-u-sm-8">
		              <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" class="am-input-sm" name="optRecord.endTime" value="${optRecord.endTime}" id="endTime" />
		            </div>
		        </div>
		        <div class="am-g am-margin-top am-u-sm-4">
		          <span class="am-input-group-btn am-u-sm-8 ">
		            <button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">
		            	<span class="am-icon-search"></span> 查询
		            </button>
		            <button type="button" class="am-btn am-btn-danger" onclick="clean()">
		            	清空
		            </button>
		          </span>
		        </div>
		        <div class="mt20 clear"></div>
		        
		     </div>
	    </div>
	 </form>
		    <!-- 表格样式 一   开始-->
					    <div class="am-g">
					      <div class="mt20 am-scrollable-horizontal">
						    <div class="mt10">
						        <table class="am-table am-table-bd am-table-striped admin-content-table">
						          <thead>
						          <tr>
						            <th width="5%">ID</th>
									<th width="18%">操作时间</th>
									<th width="15%">操作账号</th>
									<th width="10%">操作人</th>
									<th width="16%">操作行为</th>
									<th width="18%">操作对象</th>
									<th width="14%">操作Ip</th>
									<th width="5%">详情</th>
						          </tr>
						          </thead>
						          <tbody>
						          <c:if test="${not empty recordList}">
									<c:forEach items="${recordList }" var="record">
						          <tr>
						            <td>${record.id}</td>
									<td><fmt:formatDate value="${record.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${record.optAccount}</td>
									<td>${record.optUsername}</td>
									<td>${record.optName}</td>
									<td>${record.optObj}</td>
									<td>${record.optIp}</td>
									 <td>
						              <div class="am-btn-toolbar">
					                  <div class="am-btn-group am-btn-group-xs">
					                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/optRecord/getOptRecord/${record.id}'"><span class="am-icon-list"></span> 详情</button>
					                  </div>
					                </div>
						            </td>
						          </tr>
						          </c:forEach>
						          </c:if>
						          <c:if test="${empty recordList}">
										<tr>
					                		<td colspan="16">
				              					<div data-am-alert=""
													class="am-alert am-alert-secondary mt20 mb50">
													<center>
														<big> <i class="am-icon-frown-o vam"
															style="font-size: 34px;"></i> <span class="vam ml10">还没有操作记录！</span></big>
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
					  <!-- 表格样式 一   结束-->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>
