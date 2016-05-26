<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>员工组修改</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
		//注册新用户 
	 	function submitupdateUserGroup() {
	        var groups ='';
	        $("input[name='groupId']:checked").each(function () {
	            groups +=this.value +",";
	        });
	        $("#groupIds").val(groups);
		
	        $("#updateUserGroup").submit();
	        
	 	}
		 //ztree start
		var setting = {
			view:{
				showLine: true,
				showIcon: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey:'groupId',
					pIdKey:'parentGroupId',
					rootPid:''
				},
				key:{
					name:'groupName',
					title:'groupName'
				}
			},
			callback: {
				onClick: treeOnclick
			}
		};
		
		var treedata=${groupList};
		
		function treeOnclick(e,treeId, treeNode) {
			$("#userGroupId").val(treeNode.groupId);
			$("#userGroupName").val(treeNode.groupName);
			$("#distree").hide();
		}
		
		//取消全部选中
		function checkNodeFalse() {
			var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
			zTree.checkAllNodes(false);
		}
		$().ready(function() {
			$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		});
		
		function showZtree(){
			$("#distree").show();
		}
		function closetree(){
			$("#distree").hide();
		}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>员工组修改</small></div>
	</div>
	<hr>
	<div class="mt20">
		<div  class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form action="${ctx}/admin/user/updateUserGroup" class="am-form" data-am-validator id="updateUserGroup">
						<input name="user.groupIds" type="hidden" id="groupIds">
						<input name="user.id" type="hidden" value="${user.id}">
						<input type="hidden" name="user.sysGroupId" id="userGroupId" value="${user.sysGroupId }"/>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								手机号
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" readonly value="${user.mobile}">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								名称
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" readonly value="${user.nickname}">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								邮箱
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" readonly value="${user.email}">
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								级别
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<label class="am-radio-inline">
									<input type="radio" <c:if test="${user.level==0}">checked="checked"</c:if> name="user.level" value="0" data-am-ucheck>
									学员
								</label>
								<label class="am-radio-inline">
									<input type="radio" <c:if test="${user.level==1}">checked="checked"</c:if> name="user.level" value="1" data-am-ucheck>
									员工
								</label>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								所属部门
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" class="am-input-sm" id="userGroupName" readonly value="${user.sysGroupName}" readonly="readonly" onclick="showZtree()">
								<div id="distree"  style="display: none">
									<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
									<div id="ztreedemo" class="ztree" ></div>
								</div>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								所属岗位
							</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:forEach var="group" items="${userGroupList}" varStatus="count">
								<div class="am-u-sm-6 am-u-md-6 am-u-end">
									<label class="am-checkbox-inline">
									<c:if test="${group.check==1}">
										<input type="checkbox" checked="checked" name="groupId"  data-am-ucheck value="${group.id}" /> ${group.name}&nbsp;&nbsp;&nbsp;&nbsp;
									</c:if>
									<c:if test="${group.check!=1}">
										<input type="checkbox" name="groupId" data-am-ucheck value="${group.id}" /> ${group.name}&nbsp;&nbsp;&nbsp;&nbsp;
									</c:if>
									</label>
								</div>
								</c:forEach>
							</div>
							<div class="am-hide-sm-only am-u-md-6"></div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-4">
								<button class="am-btn am-btn-danger" onclick="submitupdateUserGroup()" type="submit">提交</button>
								<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
							</div>
							<div class="am-u-sm-12 am-u-md-6"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>