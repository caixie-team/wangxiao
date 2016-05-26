<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/common/uploadify.js" ></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<title>用户积分管理</title>
<script type="text/javascript">
function selectIntegral(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function allCheck(cb) {
    $("input[name=ids]").prop('checked', cb.checked);
}

function toAdd(){
	window.location.href="${ctx}/admin/websitemem/toAdd"
}
function integral(id){
	window.location.href="${ctx }/admin/user/integralrecordlist/"+id;
}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">积分管理</strong> / <small>用户积分管理</small>
		</div>
	</div>
	<hr/>
	<form action="${ctx}/admin/user/integralist" method="post" id="searchForm" class="am-form" >
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
  <div class="mt20 am-padding admin-content-list">
<div class="am-tab-panel am-fade am-active am-in">
										<div class="am-g am-margin-top am-u-sm-5">
		                             <div class="am-u-sm-4 am-text-right">
										<span><font>用户邮箱：</font></span>
									 </div>
		            				  <div class="am-u-sm-8">
													<input class="am-input-sm" type="text" name="userIntegral.email" value="${userIntegral.email}" id="name"/>
									 </div>
									</div>
							<div class="mt20">
					    	<div class="am-g">
						      <div class="am-u-md-6">
						        <div class="am-btn-toolbar">
						          <div class="am-btn-group am-btn-group-xs">
						          &nbsp;
						          </div>
						        </div>
						      </div>
						      <div class="am-u-sm-12 am-u-md-3">
						        <div class="am-input-group am-input-group-sm">
						          <span class="am-input-group-btn">
						   		<button type="button" class="am-btn am-btn-warning" onclick="selectIntegral()">
					            	  <span class="am-icon-search"></span> 搜索
					      		</button>
						          </span>
						        </div>
						      </div>
						    </div>
					    </div>
						<div class="clearfix"></div>
						<!-- <div class="mt10 clearfix">
						<button class="am-btn am-btn-success" title="新增" type="button" onclick="toAdd()">
						<span class="am-icon-plus"></span>
						新建资讯
						</button>
						</div>  -->
</div>
</div>
</form>
	<div class="mt20">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
			<thead>
				<tr>
				<!-- 	<th>
		                 <label class="am-checkbox">
		                 <input type="checkbox" data-am-ucheck onclick="allCheck(this)"/>
		                </label>
		            </th> -->
	  				        <th style="text-align:center;" width="20%"><span>ID</span></th>
							<th style="text-align:center;" width="20%"><span>用户名</span></th>
							<th style="text-align:center;" width="20%"><span>当前积分</span></th>
							<th style="text-align:center;" width="20%"><span>经验值</span></th>
							<th style="text-align:center;" width="20%"><span>操作</span></th>
				</tr>
			</thead>
		<tbody>
		<c:if test="${userIntegralList.size()>0}">
		<c:forEach items="${userIntegralList}" var="ig">
							<tr style="text-align:center;">
						<%-- 	    <td>
			                       <label class="am-checkbox">
			                       	<input type="checkbox" data-am-ucheck name="ids" value="${ig.id}"/>
								   </label>
			                    </td> --%>
								<td>${ig.id}</td>
								<td>${ig.email}</td>
								<td>${ig.currentScore}</td>
								<td>${ig.totalScore}</td>
								<td class="c_666 czBtn" align="center">
								<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="integral(${ig.userId})">
								 <span class="am-icon-search"></span> 
								查看详情
								</button>
							</tr>
		</c:forEach>
				</c:if>
				<c:if test="${userIntegralList.size()==0||userIntegralList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
						<center>
						<big>
							<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
								<span class="vam ml10">还没有用户积分信息！</span>
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
		</div>
</body>
</html>