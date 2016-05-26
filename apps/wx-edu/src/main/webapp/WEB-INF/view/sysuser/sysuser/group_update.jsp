<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>部门修改</title>

<link rel="stylesheet" type="text/css"
	href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<link rel="stylesheet"
	href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}"
	type="text/css" />
<script type="text/javascript"
	src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
	//ztree start
	var setting = {
		check : {
			enable : true,
			chkboxType : {
				"Y" : "s",
				"N" : "s"
			}
		},
		view : {
			showLine : true,
			showIcon : true,
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : 'groupId',
				pIdKey : 'parentGroupId',
				rootPid : ''
			},
			key : {
				name : 'groupName',
				title : 'groupName'
			}
		},
		callback : {
			onClick : treeOnclick
		}
	};

	var treedata = ${groupList};

	function treeOnclick(e, treeId, treeNode) {
		$("#group\\.parentGroupId").val(treeNode.groupId);
		$("#group_pname").val(treeNode.groupName);
		$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
	}
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		$('#doc-dropdown-js').dropdown({
			justify : '#doc-dropdown-justify-js'
		});
		validateForm("updateForm");

	});

	function selectRoot() {
		$("#group\\.parentGroupId").val(0);
		$("#group_pname").val("根目录");
		$("#distree").hide();
	}
	//ztree end

	var groupId = "${group.groupId}";

	function checkSubmit() {
		var fid = $("#group\\.parentGroupId").val();
		if (fid == groupId) {

			dialogFun("部门修改", "父级不能选择本身!", 1);
			return false;
		}
		return true;
	}
	//根目录
	function rootTree(){
		$("#group_pname").val("根目录");
		$("#parentGroupId").val(0);
		$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
	}
</script>
</head>
<body>

	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">部门管理</strong> / <small>部门修改</small>
		</div>
	</div>
	<div class="mt20">
	<form action="${ctx}/admin/group/updateGroup" name="updateForm" id="updateForm" class="am-form" method="post">
	<input type="hidden" name="group.parentGroupId" id="parentGroupId" value="${group.parentGroupId}" /> 
	<input type="hidden" name="group.level" id="group.level" value="<c:out value="${group.level}"/>" /> 
	<input type="hidden" value="<c:out value="${group.groupId}"/>" name="group.groupId" id="group.groupId" />
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">部门修改</a></li>
			</ul>
			<div class="am-tabs-bd am-scrollable-vertical">
		      <div id="tab1" class="am-tab-panel am-fade am-active am-in ">
				<div class="am-g am-margin-top am-form-group">
					<div class="am-u-sm-4 am-u-md-2 am-text-right"> 选择部门父级 </div>
					<div class="am-u-sm-8 am-u-md-6">
							<div id="doc-dropdown-justify-js">
							  <div class="am-dropdown" id="doc-dropdown-js">
							    <input required data-foolish-msg="父级不能为空" type="text" placeholder="根目录" value="${parentGroup.groupName}" class="am-input-sm am-dropdown-toggle" id="group_pname" readonly="readonly"/>
							    <div class="am-dropdown-content">
							    	<div class="menu-fir"><a href="javascript:rootTree()" > 根目录</a></div>
							    	<div id="ztreedemo" class="ztree" ></div>
							    </div>
							  </div>
							</div>
					</div>
					<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
				</div>
				<div class="am-g am-margin-top am-form-group">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">部门名称</div>
					<div class="am-u-sm-8 am-u-md-6">
						<input type="text" required data-foolish-msg="部门名称不能为空" name="group.groupName" id="group.groupName"
							value="<c:out value='${group.groupName}'/>" />
					</div>
					<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
				</div>
				<div class="am-g am-margin-top ">
	                     <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
	                     <div class="am-u-sm-8 am-u-md-4">
	                         <button class="am-btn am-btn-secondary" type="submit">提交</button>
	                         <button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返回</button>
	                     </div>
	                     <div class="am-u-sm-12 am-u-md-6"></div>
	            </div>
	            </div>
	            </div>
		</div>
		<!-- /tab4 end -->
	</form>
	</div>
</body>
</html>
