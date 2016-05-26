<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>角色列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>

<script type="text/javascript">

function initUpdateName(em,roleId){
	var nowName = $.trim($(em).text());
	var parent = $(em).parent('td');
	$(parent).html('<input type="text" onblur="updateName(this,\''+nowName+'\','+roleId+')" value="'+nowName+'"/>');
	$($(parent).children('input')[0]).focus();
}

function updateName(em,nowName,id){
	var newName = em.value;
	if(newName!=null && $.trim(newName)!=''){
		$.ajax({
			url:'${ctx}/admin/role/updateRoleName',
			type:'post',
			dataType:'json',
			data:{'updateRoleName':newName,'updateRoleId':id},
			async:false,
			success:function(result){
				if(result.success==false){
					alert(reslt.message);
					$($(em).parent('td')).html('<div title="点击修改角色名" onclick="initUpdateName(this,'+id+')" style="cursor: pointer;color: blue;">'+nowName+'</div>');
				}
			}
		});
	}else{
		newName=nowName;
	}
	
	$($(em).parent('td')).html('<div title="点击修改角色名" onclick="initUpdateName(this,'+id+')" style="cursor: pointer;color: blue;">'+newName+'</div>');
}


function delRole(roleId) {
	
     dialogFun("角色管理","确定删除角色 ？",2,"javascript:delRoles("+roleId+")");
 }
 function delRoles(roleId){
	 document.location.href = "${ctx}/admin/role/delRole/"+roleId;
 }

        
</script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>角色管理</small></div>
</div>

<div class="mt20">
		<div>
		<input class="am-btn am-btn-success" value="创建角色" type="button" id="addRole"/>
		</div>
		<div class="mt20" />
		<div class="am-scrollable-horizontal am-scrollable-vertical">
		<form class="am-form">
		<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
			<thead>
				<tr>
					<th width="300px"><span>角色名</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_01">
				<c:forEach items="${roleList}" var="role" varStatus="index">
	                <tr align="center">
	                	<td>
	                		<div style="cursor: pointer;color: blue;" onclick="initUpdateName(this,${role.roleId})" title="点击修改角色名">${role.roleName}</div>
	                	</td>
	                	<td>
	                	<a class="am-btn am-btn-warning" href="${ctx}/admin/role/putRoleFunction/${role.roleId}">设置角色权限</a>
	                	<a  class="am-btn am-btn-danger"onclick="delRole(${role.roleId})" href="javascript:void(0)" >删除</a>
	                	
	                	</td>
		            </tr>
	            </c:forEach>
			</tbody>
			
		</table>
		</form>
		</div>
</div>
	<script type="text/javascript">
		$(function() {
			$('#addRole').on('click', function() {
				$('#my-prompt').modal({
					relatedTarget: this,
					onConfirm: function(e) {
						var roleName = $(".am-modal-prompt-input").val();
						if(roleName==null||roleName==''){
							dialogFun("提示 ","请输入课程名字",0);
							return;
						}
						$.ajax({
							url:'${ctx}/admin/role/addRole',
							type:'post',
							dataType:'json',
							data:{'role.roleName':roleName},
							success:function(result){
								if(result.success==false){
									dialogFun("提示","result.message",0);
								}else{
									dialogFun("提示","创建成功",4);
									var obj = result.entity;
									$("#tabS_01").append('<tr align="center"> <td><div style="cursor: pointer;color: blue;" onclick="initUpdateName(this,47)" title="点击修改角色名">'+obj.roleName+'</div></td> <td> <a class="am-btn am-btn-warning" href="${ctx}/admin/role/putRoleFunction/'+obj.roleId+'">设置角色权限</a> <a class="am-btn am-btn-danger" href="javascript:void(0)" onclick="delRole('+obj.roleId+',this)">删除</a> </td> </tr>');
								}
							}
						});
					},
					onCancel: function(e) {

					}
				});
				$(".am-modal-prompt-input").val("");
			});
		});
	</script>
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">添加角色</div>
			<div class="am-modal-bd">
				请输入角色名称
				<input type="text" class="am-modal-prompt-input">
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span>
				<span class="am-modal-btn" data-am-modal-confirm>提交</span>
			</div>
		</div>
	</div>


</body>
</html>
