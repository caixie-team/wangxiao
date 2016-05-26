<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>部门管理</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>

<script type="text/javascript">

//ztree start
var setting = {
	check: {
		enable: true,
		chkboxType : { "Y" : "s", "N" : "s" }
	},
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
	window.location.href="${ctx}/admin/group/toUpdateGroup?groupId="+treeNode.groupId;
}

function treeonCheck(e,treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
	checkCount = zTree.getCheckedNodes(true).length,
	nocheckCount = zTree.getCheckedNodes(false).length,
	changeCount = zTree.getChangeCheckedNodes().length;
	var getCheckedNodes=zTree.getCheckedNodes(true);
  	/* $.each(getCheckedNodes,function(index,val){  
    });  */
}

//取消全部选中
function checkNodeFalse() {
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
	zTree.checkAllNodes(false);
}

$().ready(function() {
	$.fn.zTree.init($("#ztreedemo"), setting, treedata);
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
	zTree.expandAll(false);//展开全部
	
	//$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNodeFalse);
});
	
//ztree end

	//删除组提交
	function submitForm(){
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length;
		
		if(checkCount<=0){
	    	dialogFun("部门管理","未选中部门",0);
	    	return;
		    } 
		 dialogFun("部门管理","确认删除该部门？",2,"javascript:submitForms()");
	}
	
	
	function submitForms(){
		
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length;
		var checkedNodes=zTree.getCheckedNodes(true);
		
		var arrayObj = new Array();
	  	$.each(checkedNodes,function(index,val){  
	  		arrayObj.push(val.groupId);  
        }); 
		$("#groupIds").val(arrayObj.join(","));
		
		$("#groupForm").submit();
	}
	
	//跳转到增加部门页面
	function toAddGroup(){
		window.location.href="${ctx}/admin/group/toAddGroup";
	}
	function optOpen(){
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		var ms=$("#optOpen").html();
		if(ms=='展开全部'){
			zTree.expandAll(true);//展开全部
			$("#optOpen").html("收起全部");
		}else{
			zTree.expandAll(false);//收起全部
			$("#optOpen").html("展开全部");
		}
		
	};
	
</script>
</head>
<body >

<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>部门管理</small></div>
</div>
<hr />
<div class="mt20">
<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">部门信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
<table width="100%" cellspacing="0" cellpadding="0" border="0" >
			<tbody>
				<tr>
					<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;<b><a id="optOpen" href="javascript:optOpen();">展开全部</a>
						| （点击组名称可修改）
						</b></td>
				</tr>
				<tr>
					<td>
						<div class="ztree" id="ztreedemo"></div>
						
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<form action="${ctx}/admin/group/deleteGroup" method="post" name="groupForm" id="groupForm">
						<input  type="hidden" name="groupIds" id="groupIds"/>
						<input  class="am-btn am-btn-primary" type="button" value="新建部门" onclick="toAddGroup()" class="btn btn-danger"/>
						<input class="am-btn am-btn-danger" type="button" value="删除选中组" onclick="submitForm()" class="btn btn-danger"/>
						<input class="am-btn am-btn-warning"  type="button" onclick="history.go(-1)" value="返回" class="btn ml10"/>

						
						</form>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		</div></div>
</div>
</body>
</html>
