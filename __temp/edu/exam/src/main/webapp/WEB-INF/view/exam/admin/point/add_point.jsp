<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>考点树</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>

<script type="text/javascript">
var load_subject=0;
//subject ztree start
var subject_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		showTitle :true
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'subjectId',
			pIdKey:'parentId',
			rootPid:''
		},
		 key:{
			name:'subjectName',
			title:'title'
		} 
	}/* ,
	async: {
		enable: true,
		type: "post",
		contentType: "application/json",
		dataType :"json",
		url:"${ctx}/admin/subj/getAllSubjectList",
		otherParam: { },
		dataFilter: ajaxDataFilterSubject
	} */,
	callback: {
		onClick: subject_treeOnclick
	}
};

function ajaxDataFilterSubject(treeId, parentNode, responseData){
	var _responseData = responseData.entity;
	   if (_responseData) {
	      for(var i =0; i < _responseData.length; i++) {
	    	  _responseData[i].title = (_responseData[i].subjectName+_responseData[i].subjectId);
	    	  _responseData[i].subjectName = (_responseData[i].subjectName+" id:"+_responseData[i].subjectId);
	      }
	    } 
	 return _responseData;
}

//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
	//查找是否有子级节点
	var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
	var subtreeNode = zTree.getNodeByParam("parentId", treeNode.subjectId, null);
	if(subtreeNode==null){//没有子节点时可以选中
		clearPointFrom();
		$("#subject_distree").hide();
		$("#subjectId").val(treeNode.subjectId);
		$("#subjectName").val(treeNode.subjectName);
		loadztree();//加载考点树
	}else{
		//有子节点的直接返回
		return false;
	}
}

$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting);
});
function subject_showZtree(){
	$("#subject_distree").show();
}
function subject_closetree(){
	$("#subject_distree").hide();
}

// subject ztree end

//left point ztree start

function load2left(subjectId){
	
	var left_point_setting = {
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
				idKey:'id',
				pIdKey:'parentId',
				rootPid:''
			},
			key:{
				name:'name',
				title:'title'
			}
		},
		async: {
			enable: true,
			type: "post",
			contentType: "application/json",
			dataType :"json",
			url:"${ctx}/admin/point/queryPointList?point.subjectId="+subjectId,
			otherParam: { "point.subjectId":load_subject},
			dataFilter: ajaxDataFilter
		},
		callback: {
			onClick: left_point_treeOnclick
		}
	};
	
	$.fn.zTree.init($("#left_point_ztreedemo"), left_point_setting);
}


function ajaxDataFilter(treeId, parentNode, responseData){
	var _responseData = responseData.entity;
	   if (_responseData) {
	      for(var i =0; i < _responseData.length; i++) {
	    	  _responseData[i].title = (_responseData[i].name+_responseData[i].id);
	    	  _responseData[i].name = (_responseData[i].name+" id:"+_responseData[i].id);
	      }
	    } 
	 return _responseData;
}
function left_point_treeOnclick(e,treeId, treeNode) {
	$("#id").val(treeNode.id);
	$("#name").val(treeNode.name);
	$("#examFrequency").val(treeNode.examFrequency);
	var zTree = $.fn.zTree.getZTreeObj("right_point_ztreedemo");
	var parentNode = zTree.getNodeByParam("id", treeNode.parentId, null);
	if(parentNode!=null){
		$("#pIdHidden").val(parentNode.id);
		$("#pIdName").val(parentNode.name);
	}else{//没有父级
		$("#pIdHidden").val(0);
		$("#pIdName").val("根目录");
	}
	closerighttree();
}
	
// left point ztree end

//right point ztree start
var right_point_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'id',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'name',
			title:'title'
		}
	},
	callback: {
		onClick: right_point_treeOnclick
	}
};
//右侧选择时赋值
function right_point_treeOnclick(e,treeId, treeNode) {
	$("#pIdHidden").val(treeNode.id);
	$("#pIdName").val(treeNode.name);
	closerighttree();
}

function selectrighttreeroot(){
	$("#pIdHidden").val(0);
	$("#pIdName").val("根目录");
	closerighttree();
	closerighttree();
}
function showrightZtree(){
	$("#disrighttree").show();
}
function closerighttree(){
	$("#disrighttree").hide();
}	
// right point ztree end

$().ready(function() {
	$("#savePointForm").validate();
	clearPointFrom();
});

//切换专业时加载树
function loadztree(){
	var subjectId  = $("#subjectSelect").val();
	if(subjectId != null){
		load2left(subjectId);
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/point/queryPointList",
			data:{"point.subjectId":subjectId},
			async:false,
			success:function(result){
				//$.fn.zTree.init($("#left_point_ztreedemo"), left_point_setting, result.entity);
				$.fn.zTree.init($("#right_point_ztreedemo"), right_point_setting, result.entity);
			}
		});
	}	
}
//根据专业id查询科目
function getSubjectByProId(obj){
	if(obj>0){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/subj/querySubjectByProId",
			data:{"querySubject.professionalId":obj},
			async:false,
			success:function(result){
				if(result.success==true){
					var subjectList = result.entity;
					var str = "";
					for(var i =0;i<subjectList.length;i++){
						str +='<option value="'+subjectList[i].subjectId+'"">'+subjectList[i].subjectName+'</option>';
					}
					$("#subjectSelect").html(str);
					$("#subjectlist").show();
				}
			}
		});
	}else{
		$("#subjectlist").hide();
	}
	loadztree();
}

$(function(){
	var professionalId = $("#professionalSelect").val();
	if(professionalId>=0){
		getSubjectByProId(professionalId);
	}
});

function newPoint(){
	$("#name").val("");
	$("#examFrequency").val("");
	$("#info").val("");
	$("#id").val("0");//0代表添加。否则为更新
	$("#pIdHidden").val(0);
	$("#pIdName").val("");
	clearPointFrom();
}
 
function confirmMessage(str) {
	if(confirm(str)){
	   return true;
	 }else{
		 return false;
	 }
}
function deletePoints(){
	if (confirm("确认删除选中的考点？")) {
    	var zTree = $.fn.zTree.getZTreeObj("left_point_ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length;
    	if(checkCount>0){
    		var checkedNodes=zTree.getCheckedNodes(true);
    		var arrayObj = new Array()
		  	$.each(checkedNodes,function(index,val){  
		  		arrayObj.push(val.id);
	        });
    		$("#pointIds").val(arrayObj.join(","));
    		$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/point/delPointByPointId",
				data:$("#delPointForm").serialize(),
				async:false,
				success:function(result){
					if(result.success==true){
						loadztree();
						alert("提交成功");
					}
				}
			});
    	}else{
    		alert("未选中考点");
    	}
    } else {
        return false;
    } 
	
	
}

function savaPoint(){
	var subjectId  = $("#subjectSelect").val();
	$("#subjectId").val(subjectId);
	if(subjectId==0){alert("请选择专业"); return false;}
	if($("#savePointForm").valid()){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/point/savePoint",
				data : $("#savePointForm").serialize(),
				async : false,
				success : function(result) {
					if (result.success == true) {
						alert("提交成功");
						loadztree();
						clearPointFrom();
					}
				}
			});
		}
	}

	//清空页面数据
	function clearPointFrom() {
		$('#savePointForm')[0].reset();
		$("#id").val(0);
		$("#pIdHidden").val(0);
	}
	
</script>
<style>
	.tdLabel {align ="center"}
	.label {color: red}
</style>
</head>
<body >
		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>考点管理</span> &gt; <span>考点树</span>
			</h4>
		</div>
		<div class="mt20">
		<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="clearfix">
							<div class="optionList">
							
								<font>考试专业分类：</font>
									<select onchange="getSubjectByProId(this.value)"  id="professionalSelect">
									<option value="0">请选择</option>
									 <c:forEach items="${professionalList}" var="professional">
									 	<option value="${professional.professionalId}">${professional.professionalName}</option>
									 </c:forEach>
									 </select>
								<br></br>
								<div id="subjectlist" style="display: none;">
								<font>所属考试科目：</font>
								<select id="subjectSelect" onchange="loadztree();">
								</select>
								</div>
								
							</div>
							<div class="optionList">
								
							</div>
						</div>
					</div>
				</caption>
			</table>
		</div>
		<!-- /commonWrap -->

		<table width="100%" cellspacing="0" cellpadding="0" border="0"
			class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>考点树<font color="red">(*树级结构最多支持二级)</font></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="35%">
						<form action="${ctx}/admin/point/delPointByPointId" method="post" id="delPointForm">
							<input type="hidden" id="pointIds" name="pointIds" value="" />
							<div id="left_point_ztreedemo" class="ztree">请选择专业加载考点！
							</div>
						</form>
					</td>
					<td width="80%">
						<form action="${ctx}/admin/point/savePoint"
							method="post" id="savePointForm">
							<input type="hidden" name="point.subjectId" id="subjectId" value="0" ></input>
							<input type="hidden" name="point.id" value="0" id="id" /> 
							<table width="100%" cellspacing="0" cellpadding="0" border="0"
								class="commonTab01">
								<thead>
									<tr>
										<th colspan="2" align="left"><span>考点
										<tt class="c_666 ml20 fsize12">
													（<font color="red">*</font>&nbsp;为必填项）
										</tt></span>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"><font color="red">*</font>&nbsp;父级</td>
										<td>
											<input type="text" id="pIdName" readonly="readonly"  value="根目录" onclick="showrightZtree()" />
											<input type="hidden" name="point.parentId" value="" id="pIdHidden" class="{required:true}" />
											<div id="disrighttree"  style="display: none">
												<a class="btn smallbtn btn-y" onclick="selectrighttreeroot()">根目录</a>
												<a class="btn smallbtn btn-y" onclick="closerighttree()">关闭</a>
												<div id="right_point_ztreedemo" class="ztree" ></div>
											</div>
											
										</td>
									</tr>
									<tr>
										<td align="center"><font color="red">*</font>&nbsp;考点名称</td>
										<td>
											<input type="text" name="point.name" value="" id="name" class="{required:true}" />
										</td>
									</tr>
									<tr>
										<td align="center"><font color="red">*</font>&nbsp;考频</td>
										<td><select name="point.examFrequency" id="examFrequency">
												<option value="1">一级</option>
												<option value="2">二级</option>
												<option value="3">三级</option>
										</select></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
										<input class="btn btn-danger" type="button" onclick="savaPoint()" value="提 交" /> 
										<input class="btn ml10 btn-danger" type="button" onclick="deletePoints()" value="删 除" />
										<input class="btn ml10" type="button" onclick="newPoint()" value="新建考点" /></td>
									</tr>
								</tbody>
							</table>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
