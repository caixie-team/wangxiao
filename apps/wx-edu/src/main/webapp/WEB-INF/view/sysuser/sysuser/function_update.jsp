<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>修改权限</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">




function updateSubmit(){
	if($("#functionName").val()=='' || $("#functionName").val()==null ){
		dialogFun("提示","请填写权限名称",0);
		return;
	}
	/*if($("#functionUrlType").val()=='0' || $("#functionUrlType").val()==null){
		dialogFun("提示","请选择连接平台",0);
		return;
	}*/
	/* if($("#functionUrl").val()=='' || $("#functionUrl").val()==null){
		dialogFun("提示","请填写权限URL",0);
		return;
	} */
/* 	if($("#icon").val()=='' || $("#icon").val()==null){
		dialogFun("提示","请填写logo",0);
		return;
	} */
	if($("#sort").val()=='' || $("#sort").val()==null){
		dialogFun("提示","请填写排序",0);
		return;
	}
	$("#updateForm").submit();
}


//ztree start
var setting = {
	/* check: {
		enable: false,
		chkboxType : { "Y" : "s", "N" : "s" }
	}, */
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
//功能权限 1 红色显示
function getFont(treeId, node) {
	return node.functionTypeId==1 ?  {'color':'red'}:{};
}

function treeOnclick(e,treeId, treeNode) {
	$("#parentName").val(treeNode.functionName);
	//console.log(treeNode);
	$("#parentFunctionId").val(treeNode.functionId);
	$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
}

//取消全部选中
function checkNodeFalse() {
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
	zTree.checkAllNodes(false);
}
$().ready(function() {
	$.fn.zTree.init($("#ztreedemo"), setting, treedata);
});

$(function(){
	$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
	$("#functionUrlType").val('${function.functionUrlType}');
	$("#functionTypeId").val('${function.functionTypeId}');
})

//根目录
function rootTree(){
	$("#parentName").val("根目录");
	$("#parentFunctionId").val(0);
	$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
}
//ztree end
	
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">权限管理</strong> / <small>修改权限</small></div>
</div>
<hr/>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>

		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form class="am-form" data-am-validator action="${ctx}/admin/func/updateFunction" name="updateForm" id="updateForm" method="post">
					<input type="hidden" name="function.functionId" id="functionId" value="<c:out value="${function.functionId }"/>"/>
					<input type="hidden" name="function.parentFunctionId" id="parentFunctionId" value="<c:out value="${function.parentFunctionId }"/>"/>
					<div class="am-g am-margin-top am-form-group">
						<label for="parentName" class="am-u-sm-4 am-u-md-2 am-text-right">
							选择父级权限
						</label>
						<div class="am-u-sm-8 am-u-md-6 am-form-horizontal">
							<div id="doc-dropdown-justify-js" style="width: 200px">
								<div class="am-dropdown" id="doc-dropdown-js">
									<input type="text" placeholder="根目录" class="am-input-sm am-dropdown-toggle" id="parentName" required name="pName" value="系统权限根" readonly="readonly"/>
									<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
										<div class="menu-fir"><a href="javascript:rootTree()" > 根目录</a></div>
										<div id="ztreedemo" class="ztree"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="functionName" class="am-u-sm-4 am-u-md-2 am-text-right">
							权限名称
						</label>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input type="text" class="am-input-sm" name="function.functionName" id="functionName" value="${function.functionName }">
						</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="functionTypeId" class="am-u-sm-4 am-u-md-2 am-text-right">
							权限类型
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<select name="function.functionTypeId" id="functionTypeId" required data-am-selected="{btnSize: 'sm' , maxHeight:'5'}" style="display: none;">
								<option value="2">菜单权限</option>
								<option value="1">功能权限</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							链接平台
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<select id="functionUrlType" name="function.functionUrlType" data-am-selected="{maxHeight: 100}" style="display: none;">
								<option value="0">--请选择--</option>
								<option value="1">网校</option>
								<option value="2">社区</option>
								<option value="3">考试</option>
								<option value="4">视频</option>
								<option value="5">线下</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6">最子节点输入地址的才选择此选项，其他则为请选择</div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							权限URL
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" class="am-input-sm" name="function.functionUrl" id="functionUrl" value="${function.functionUrl }" />
						</div>
						<div class="am-u-sm-12 am-u-md-6"></div>
					</div>

					<%-- <div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							logo
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" class="am-input-sm" name="function.icon" id="icon" value="${function.icon}"/>
						</div>
						<div class="am-u-sm-12 am-u-md-6"></div>
					</div> --%>

					<div class="am-g am-margin-top" am-form-group>
						<label for="sort" class="am-u-sm-4 am-u-md-2 am-text-right">
							显示排序
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" name="function.sort" required id="sort" class="am-input-sm" value="${function.sort }" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
						</div>
						<div class="am-u-sm-12 am-u-md-6">由大到小显示</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" onclick="updateSubmit()">提交</a>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
