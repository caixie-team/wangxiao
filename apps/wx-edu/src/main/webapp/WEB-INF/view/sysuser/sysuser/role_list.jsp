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
//ztree start
var setting = {
	check: {
		enable: true,
		chkboxType : {"Y" : "ps", "N" : "ps"}
	},
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		fontCss: getFont
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'functionId',
			pIdKey:'parentFunctionId',
			rootPid:''
		},
		key:{
			name:'functionName',
			title:'functionName'
		}
	},
	callback: {
		
	}
};
var treedata=${functionList};

//功能权限红色显示1
function getFont(treeId, node) {
	return node.functionTypeId==1 ?  {'color':'red'}:{};
}

//取消全部选中
function checkNodeFalse() {
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
	zTree.checkAllNodes(false);
}

$(this).ready(function() {
	$.fn.zTree.init($("#ztreedemo"), setting, treedata);
	$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNodeFalse);
	checkFunctionSelect(${myfunctionList});
});

//将权限选中
function checkFunctionSelect(functionList){
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
	zTree.checkAllNodes(false);//全部取消
	$.each(functionList,function(index,val){  
		var treeNode = zTree.getNodeByParam("functionId", val.functionId, null);
		zTree.checkNode(treeNode, true, false, false)
  	});
} 

function saveRoleFunction(roleId){
	var treeObj = $.fn.zTree.getZTreeObj("ztreedemo");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = '';
	for(var i=0;i<nodes.length;i++){
		if(i<nodes.length-1){
			ids+=nodes[i].functionId+',';
		}else{
			ids+=nodes[i].functionId;
		}
	}
	$.ajax({
		url:'${ctx}/admin/role/updateRoleFunction/'+roleId,
		type:'post',
		dataType:'json',
		data:{'functionIds':ids},
		success:function(result){
			if(result.success==false){
				alert(result.message);
			}else{
				window.location.href='${ctx}/admin/role/roleList';
			}
		}
	});
}


function updateRole(){
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length;
		if(checkCount>0){
		var checkedNodes=zTree.getCheckedNodes(true);
		var arrayObj = new Array();
	  	$.each(checkedNodes,function(index,val){  
	  		arrayObj.push(val.functionId);  
        }); 
		$("#selectedFunctionIds").val(arrayObj.join(","));
    }
    return true;
	}
</script>
 <!--  添加角色 弹窗  -->   	
 <style type="text/css" >
		#overlay {
			background: #303030;
			opacity: 0.50;
			filter: Alpha(opacity =   50);
			display: none;
			position: absolute;
			top: 0px;
			left: 0px;
			z-index: 100;
		}
		
		#addRole {
			margin-left: auto;
			margin-right: auto;
			border: 2px solid #FFFFFF;
			font-size: 12px;
			font-family: "宋体";
			color: #990000;
			padding-top: 20px;
			width: 400px;
			height: 200px;
			position: absolute;
			z-index: 110;
			display: none;
			background: #e7e7e7;
			left: 35%;
			top: 20%;
			opacity: 0.85;
			filter: Alpha(opacity =   85);
		}
		
		#updateRoleName {
			margin-left: auto;
			margin-right: auto;
			border: 2px solid #FFFFFF;
			font-size: 12px;
			font-family: "宋体";
			color: #990000;
			padding-top: 20px;
			width: 400px;
			height: 200px;
			position: absolute;
			z-index: 110;
			display: none;
			background: #e7e7e7;
			left: 35%;
			top: 20%;
			opacity: 0.85;
			filter: Alpha(opacity =   85);
		}
		
	</style>
	    
</head>
<body >
<!-- 公共右侧样式 -->
<div class="am-cf">
  <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>角色管理</small></div>
</div>
<hr/>
<!-- 公共右侧标题样式 结束-->


<div class="mt20">
<form action="${ctx}/admin/role/updateRoleFunction/${role.roleId}" method="post" id="updateRoleFunctionForm" onsubmit="return updateRole();">
<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
	<thead>
		<tr>
			<th colspan="2" align="left"><span>设置角色权限 <tt class="c_666 ml20 fsize12"></tt></span></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td width="20%" align="center"> 
				设置【${role.roleName}】角色权限
			</td>
			
			<td width="80%">
				<input type="hidden" value="" name="selectedFunctionIds" id="selectedFunctionIds"/>
				<div id="ztreedemo" class="ztree"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<input type="submit"  value="保存设置的权限" class="am-btn am-btn-danger"/>
				<input type="button" value="返回" onclick="window.location.href='${ctx}/admin/role/roleList'" class="am-btn am-btn-primary"/>
				<font color="red">(*红字为功能权限，其他为菜单权限)</font>
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>

</body>
</html>
