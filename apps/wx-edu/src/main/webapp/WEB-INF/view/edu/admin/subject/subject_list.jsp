<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>项目列表</title>
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
			fontCss: setFontCss,
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'subjectId',
				pIdKey:'parentId',
				rootPid:''
			},
			key:{
				name:'subjectNameAndId',
				title:'subjectNameAndId'
			}
		},
		callback: {
			onClick: treeOnclick
			/* 
			onCheck: treeonCheck,
			beforeMouseDown:beforeMouseDown,
			beforeMouseUp: beforeMouseUp,
			beforeRightClick: beforeRightClick,
			onMouseDown: onMouseDown,
			onMouseUp: onMouseUp,
			onRightClick: onRightClick */
		}
	};

	function setFontCss(treeId, treeNode) {
		var color;
		if(treeNode.showIndex==1){
			color = { color:"#14BA4C"}
		}
		return color;
	}

	var treedata=${subjectList};
	
	function treeOnclick(e,treeId, treeNode) {
		window.location.href="${ctx}/admin/subj/toUpdateSubject?subject.subjectId="+treeNode.subjectId;
	}
	
	function treeonCheck(e,treeId, treeNode) {
		/*var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length,
		nocheckCount = zTree.getCheckedNodes(false).length,
		changeCount = zTree.getChangeCheckedNodes().length;
		var getCheckedNodes=zTree.getCheckedNodes(true);
	  	 $.each(getCheckedNodes,function(index,val){  
        });  */
	}
	
	//取消全部选中
	function checkNodeFalse() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);
	}
	
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNodeFalse);
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		var subjectList = zTree.getNodes();
		for(var i=0;i<subjectList.length;i++){
			var subject = subjectList[i];
			var childrenNodes = subject.children;
			for(var j=0;j<childrenNodes.length;j++){
				var childSubject = childrenNodes[j];
				if(childSubject.showIndex==1){
					zTree.expandNode(subject, true, true, true);
					break;
				}
			}
		}
	});
	//ztree end

	function addSubject() {
		window.location.href = "${ctx}/admin/subj/saveSubjectInit";
	}
	
	function delSubjects() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length;
    	if(checkCount>0){
			dialogFun("提示","确定删除选中的项目？",2,"javascript:delNow()");
	    }else{
	    	dialogFun("提示","未选中项目",0);
	    }

	}
	function delNow(){
			var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
			checkCount = zTree.getCheckedNodes(true).length;
    		var checkedNodes=zTree.getCheckedNodes(true);
    		var arrayObj = new Array()
		  	$.each(checkedNodes,function(index,val){
		  		arrayObj.push(val.subjectId);
	        });
    		$("#ids").val(arrayObj.join(","));
	        document.updateSubjectForm.submit();
	}

	// 批量首页显示或不显示
	function showIndex(showIndex){
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo"), checkCount = zTree.getCheckedNodes(true).length;
		if(checkCount>0){
			var checkedNodes=zTree.getCheckedNodes(true);
			var arrayObj = new Array()
			$.each(checkedNodes,function(index,val){
				arrayObj.push(val.subjectId);
			});
			$.ajax({
				url:"${ctx}/admin/ajax/batchShowIndex",
				type:"post",
				data: {"showIndex":showIndex,"ids":arrayObj.join(",")},
				dataType:"json",
				success:function(result){
					if(result.success){
						dialogFun("提示","修改成功",5,window.location.href);
					}else{
						dialogFun("提示","系统繁忙,请稍后重试",0);
					}
				}
			});
		}else{
			dialogFun("提示","未选中项目",0);
		}
	}
  </script>
</head>
<body  >
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">项目管理</strong> / <small>项目列表</small></div>
	</div>
	<hr>
	<div class="mt20">
		<div class="commonWrap">
			<form action="/admin/subj/delSubjects" method="post" id="updateSubjectForm" name="updateSubjectForm">
				<input type="hidden" name="ids" id="ids"/>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th colspan="2" align="left"><span>项目列表(<font color="red">树级结构最多支持二级</font>，<font color="#14BA4C">绿色字体为首页显示</font>)</span></th>
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
								<button type="button" class="am-btn am-btn-primary" onclick="addSubject()">新建项目</button>
								<button type="button" class="am-btn am-btn-danger" onclick="delSubjects()">删除选中项目</button>
								<button type="button" id="checkAllFalse" class="am-btn am-btn-warning">清空选中</button>
								<button type="button" class="am-btn am-btn-primary" onclick="showIndex(1)">首页显示</button>
								<button type="button" class="am-btn am-btn-secondary" onclick="showIndex(0)">首页不显示</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
