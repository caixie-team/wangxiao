<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
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
				chkboxType : { "Y" : "s", "N" : "s" }
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
				onClick: treeOnclick
			}
		};
		var treedata=${functionList};

		//功能权限红色显示1
		function getFont(treeId, node) {
			return node.functionTypeId==1 ?  {'color':'red'}:{};
		}
		//单击事件
		function treeOnclick(e,treeId, treeNode) {
			window.location.href="${ctx}/admin/func/toUpdateFunction?function.functionId="+treeNode.functionId;
		}

		function treeonCheck(e,treeId, treeNode) {
			/* var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
			checkCount = zTree.getCheckedNodes(true).length,
			nocheckCount = zTree.getCheckedNodes(false).length,
			changeCount = zTree.getChangeCheckedNodes().length;
			var getCheckedNodes=zTree.getCheckedNodes(true);
			 $.each(getCheckedNodes,function(index,val){
			});
			*/
		}

		//取消全部选中
		function checkNodeFalse() {
			var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
			checkCount = zTree.getCheckedNodes(true).length;
			if(checkCount>0){
				zTree.checkAllNodes(false);
			}else{
				dialogFun("提示","未选中权限",0);
			}
		}

		$().ready(function() {
			$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		});
	
		//ztree end


		function addFunction() {
			window.location.href = "${ctx}/admin/func/toAddFunction";
		}
	
		function delFunctions() {
			dialogFun("修改权限","确定删除选中的权限及子权限？",2,"javascript:deleteFun()");
		}

		function deleteFun(){
			var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
					checkCount = zTree.getCheckedNodes(true).length;
			if(checkCount>0){
				var checkedNodes=zTree.getCheckedNodes(true);
				var arrayObj = new Array()
				$.each(checkedNodes,function(index,val){
					arrayObj.push(val.functionId);
				});
				$("#ids").val(arrayObj.join(","));
				$("#updateRoleFunctionForm").submit();
			}else{
				dialogFun("提示","未选中权限",0);
			}
		}
	</script>
</head>
<body >
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>权限设置</small></div>
	</div>
	<hr/>
	<div class="mt20">
		<form action="${ctx}/admin/func/delFunctions" method="post" id="updateRoleFunctionForm">
			<input type="hidden" id="ids" name="ids" value=""/>
		</form>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>权限设置</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%" >
						<div id="ztreedemo" class="ztree"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-8">
								<button class="am-btn am-btn-success " onclick="addFunction()"><span class="am-icon-pencil-square-o"></span> 新建</button>
								<button class="am-btn am-btn-danger " onclick="delFunctions()"><span class="am-icon-trash-o"></span> 删除</button>
								<button class="am-btn am-btn-secondary " onclick="checkNodeFalse()"><span class="am-icon-recycle"></span> 清空</button>
								<font color="red">(*红字为功能权限，其他为菜单权限)</font>
							</div>
							<div class="am-u-sm-12 am-u-md-6"></div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
