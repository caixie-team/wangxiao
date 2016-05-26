<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>线下资讯分类管理</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
<script type="text/javascript">
	var nodeIds='';
	var setting = {
		 check: {
				enable: true,
				chkboxType : {"Y" : "", "N" : ""}
			}, 
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'typeId',
				pIdKey:'parentId',
				rootPid:''
			},
			key:{
				name:'typeName',
				title:'typeName'
			}
		},
		callback: {
			onRename: updateTypeName,
			beforeRemove: removeSubject,
			onDrop: updataTypeParentId,
			//有些节点不能拖拽
			beforeDrag:function(treeId, treeNodes){
				if(treeNodes[0].typeId==-1){
					return false;
				}else{
					return true;
				}
			}
		},
		edit: {
			enable: true,
			showRemoveBtn: showRemoveBtnFun,
			removeTitle: "删除分类",
			renameTitle:'修改分类名'
		}
	};
	
	//有些节点不能显示删除按钮
	function showRemoveBtnFun(treeId, treeNode){
		if(treeNode.typeId==-1){
			return false;
		}else{
			return true;
		}
	}
	
	//用于修改被拖拽节点的父ID
	function updataTypeParentId(event, treeId, treeNodes, targetNode, moveType){
		var typeId = treeNodes[0].typeId;
		var parentId = targetNode.typeId;
		$.ajax({
			url:'${ctx}/admin/line/updateTypeParentId',
			type:'post',
			dataType:'json',
			data:{
				'articleType.typeId':typeId,
				'articleType.parentId':parentId
			},
			success:function(result){if(result.success==false){alert(result.message)}}
		});
	}
	
	//修改专业名
	function updateTypeName(event, treeId, treeNode, isCancel){
		$.ajax({
			url:'${ctx}/admin/line/updateTypeName',
			type:'post',
			dataType:'json',
			data:{
				'articleType.typeId':treeNode.typeId,
				'articleType.typeName':treeNode.typeName
			},
			success:function(result){if(result.success==false){alert(result.message)}}
		});
	}
	//删除专业
	function removeSubject(treeId, treeNode){
		if(confirm("确认要删除【"+treeNode.typeName+"】及下面的子分类？")){
			var ids = treeNode.typeId;
			if(treeNode.isParent){
				recursiveNode(treeNode);
				ids+=','+nodeIds;
			}
			$.ajax({
				url:'${ctx}/admin/line/deleteType',
				type:'post',
				dataType:'json',
				data:{'ids':ids},
				success:function(result){
					if(result.success==false){
						alert(result.message)
					}
				}
			});
			return true;
		}else{
			return false;
		}
	}
	function recursiveNode(node){
		var childNodes = node.children;
		if(childNodes!=null && childNodes.length>0){
			for(var i=0;i<childNodes.length;i++){
				nodeIds+=childNodes[i].typeId+',';
				if(childNodes[i].isParent){
					recursiveNode(childNodes[i]);
				}
			}
		}
	}
	
	$(this).ready(function() {
		//得到json格式的数据
		var treedata=${typeList};
		//初始化树结构
		var treeObj = $.fn.zTree.init($("#ztreedemo"), setting, treedata);
		treeObj.expandAll(true);
		var nodes = treeObj.getNodesByParam('isnav',1,null);
		if(nodes!=null && nodes.length>0){
			for(var i=0;i<nodes.length;i++){
				treeObj.checkNode(nodes[i],true,false);
			}
		}
	});
	//保存显示在导航栏的分类
	function checkNav(){
		var treeObj = $.fn.zTree.getZTreeObj("ztreedemo");//树控件
		var nodes = treeObj.getCheckedNodes();//获取所有选择的节点
		 if(nodes==null || nodes.length==0){
			 alert("你没有选中任何节点！");
			 return;
		 }else{
			 var ids='';
			 for(var i=0;i<nodes.length;i++){
				 ids+=nodes[i].typeId+',';
			 }
			 $.ajax({
					url:'${ctx}/admin/line/showNav',
					type:'post',
					dataType:'json',
					data:{'ids':ids},
					success:function(result){
						alert(result.message)
					}
				});
		 }
	}
	//添加专业（资讯分类）
	function addType(){
		//得到树结构 
		var treeObj = $.fn.zTree.getZTreeObj("ztreedemo");
		//获取被选中的节点
		var nodes = treeObj.getSelectedNodes();
		var node=null;
		if(nodes!=null && nodes.length>0){
			node = nodes[0];
		}
		var parentId=-1;
		if(node){
			parentId =node.typeId;
		}
		
		$.ajax({
			url:'${ctx}/admin/line/createType',
			type:'post',
			dataType:'json',
			data:{
				'articleType.typeName':'新建分类',
				'articleType.parentId':parentId
				
			},
			success:function(result){
				if(result.success==true){
					var obj = result.entity;
					treeObj.addNodes(node,[obj])
				}
				alert(result.message)
			},
			error:function(error){
				alert("系统系统，请稍后再操作！");
			}
		});
	}
	
</script>
</head>
<body >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>线下资讯分类管理</span> &gt; <span>分类设置 </span> </h4>
</div>
<div class="mt20">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>分类设置</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					
					<td width="20%" >
					<div id="ztreedemo" class="ztree">
					
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="将选中分类显示到导航栏" onclick="checkNav()"  class="btn btn-danger"/>
						<input type="button" value="添加分类" onclick="addType()"  class="btn btn-danger"/>
					</td>
				</tr>
			</tbody>
		</table>
</div>
</body>
</html>
