<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户账户信息</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
	<script type="text/javascript">
		function editAccount(userId,status){
			var judge="";
			if(status=="ACTIVE"){//正常
				judge="确定恢复该账户吗？";
			}else{
				judge="确定冻结该账户吗？";
			}
			dialogFun("用户账户信息",judge,2,"javascript:_editAccount('"+userId+"','"+status+"')");
		}
		function _editAccount(userId,status){
			$.ajax({
				url:"${ctx}/admin/account/update/"+userId,
				data:{"status":status},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						if(status=="ACTIVE"){
							$("#edit"+userId).html("冻结");
							$("#edit"+userId).attr("onclick","editAccount("+userId+",'FROZEN')");
							$("#status"+userId).html("正常");
						}else{
							$("#edit"+userId).html("正常");
							$("#edit"+userId).attr("onclick","editAccount("+userId+",'ACTIVE')");
							$("#status"+userId).html("冻结");
						}
						setTimeout(function(){
							dialogFun("用户账户信息",result.message,1);
						},300);

					}
				}
			});
		}
		function clean(){
			$("#nickname,#email,#mobile,#startDate,#endDate").val("");
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员账户</small></div>
	</div>
	<hr>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/account/list" class="am-form" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						手机号
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text"class="am-input-sm" pattern="^1[3-9]\d{9}$" maxlength="11" name="user.mobile" value="${user.mobile}" id="mobile" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						注册开始日期
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="form-datetime-lang am-form-field am-input-sm" name="user.startDate" id="startDate" value="${user.startDate}" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-u-end am-text-left">
					<div class="am-u-sm-4 am-text-right">
						注册结束日期
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="form-datetime-lang am-form-field am-input-sm"  name="user.endDate" id="endDate" value="${user.endDate}" />
					</div>
				</div>
				<div class="mt20 clear"></div>

				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						用户名
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm"  name="user.nickname" value="${user.nickname}"  id="nickname" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						邮箱
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="email" class=" am-input-sm" name="user.email" value="${user.email}" id="email" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-12 am-u-end  am-text-center">
						<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)()">
							<span class="am-icon-search"></span> 搜索
						</button>
						<button type="button" class="am-btn am-btn-danger" onclick="clean()">
							清空
						</button>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</form>
		</div>
	</div>
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal am-scrollable-vertical">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th class="table-title" width="10%">学员ID</th>
							<th class="table-title" width="10%">用户名</th>
							<th class="table-title" width="11%">邮箱</th>
							<th class="table-title" width="11%">手机</th>
							<th class="table-title" width="11%">创建时间</th>
							<th class="table-title" width="11%">更新时间</th>
							<th class="table-title" width="10%">账户余额</th>
							<th class="table-title" width="10%">冻结金额</th>
							<th class="table-title" width="11%">账户状态</th>
							<th class="table-set" width="5%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty userAccountList }">
							<c:forEach items="${userAccountList}" var="user">
								<tr>
									<td>${user.userId}</td>
									<td>${user.nickName}</td>
									<td>${user.email}</td>
									<td>${user.mobile}</td>
									<td>
										<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<fmt:formatDate value="${user.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>${user.balance}</td>
									<td>${user.forzenAmount}</td>
									<td id="status${user.userId}">
										<c:if test="${user.accountStatus=='ACTIVE'}">
											正常
										</c:if>
										<c:if test="${user.accountStatus=='FROZEN'}">
											冻结
										</c:if>
									</td>
									<td>
										<a class="am-btn am-btn-primary" href="${ctx}/admin/account/info/${user.userId}/credit">充值</a>
										<a class="am-btn am-btn-primary" href="${ctx}/admin/account/info/${user.userId}/debit">扣款</a>
										<div data-am-dropdown="" class="am-dropdown">
											<button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<c:if test="${user.accountStatus=='ACTIVE'}">
													<li><a href="javascript:void(0)" onclick="editAccount(${user.userId},'FROZEN')" id="edit${user.userId}">冻结</a></li>
												</c:if>
												<c:if test="${user.accountStatus=='FROZEN'}">
												<li><a href="javascript:void(0)" onclick="editAccount(${user.userId},'ACTIVE')">正常</a></li>
												</c:if>
												<li><a href="${ctx}/admin/accout/detailed_list?queryUserAccounthistory.userId=${user.userId}">详情</a></li>
											</ul>
										</div>

									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty userAccountList }">
							<tr>
								<td colspan="10">
									<div data-am-alert=""
										 class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big>
												<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
												<span class="vam ml10">还没有用户账户信息！</span>
											</big>
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
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
</body>
</html>

