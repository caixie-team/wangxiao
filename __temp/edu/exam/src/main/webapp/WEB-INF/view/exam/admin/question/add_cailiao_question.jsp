<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加试题</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>

<script type="text/javascript">

function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
	}
	if (!(event.target.id == "pointNameBtn" || event.target.id == "pointmenuContent" || $(event.target).parents("#pointmenuContent").length>0)) {
	}
	
}

$().ready(function() {
	//模拟触发试题类型
	$("#qstType").change();
});


//point ztree start
var point_setting = {
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
			title:'name'
		}
	},
	callback: {
		onClick: point_treeOnclick,
		beforeClick: point_beforeClick
	}
};

function point_treeOnclick(e,treeId, treeNode) {
	hidepointMenu();
	$("#pointId").val(treeNode.id);
	$("#pointNameBtn").val(treeNode.name);
}

function point_cleantreevalue(){
	point_closetree();
	$("#pointId").val(0);
	$("#pointName").val("");
}
function showpointMenu() {
	var cityObj = $("#pointNameBtn");
	var cityOffset = $("#pointNameBtn").offset();
	$("#pointmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hidepointMenu() {
	$("#pointmenuContent").fadeOut("fast");
	//$("body").unbind("mousedown", onBodyDown);
}
//
function point_beforeClick(treeId, treeNode) {
	 var check = (treeNode && !treeNode.isParent);
	if (!check) alert("只能选择子节点");
	return check; 
}
//point ztree end
$(function(){
	$("#subjectIdOne").change();
	initEditor('qstContent');
	loadInitEditor();
	 $("#addQuestionForm").validate();
	 initEditor('qstAnalyze');
});
function loadInitEditor(){
	$("textarea[name^='asr']").each(function(){
		initEditor($(this).attr("id"));
	});
}
/*
 * 加载考点树
 */
function loadpointtree(){
	var subjectId = $("#subjectId").val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/point/queryPointListByPIdAndSubjectId",
		data:{"point.subjectId":subjectId},
		async:false,
		success:function(result){
			if(result.entity!=null&&result.entity.length>0){
				var pointdata = result.entity;
				$.fn.zTree.init($("#point_ztreedemo"), point_setting, pointdata);
			}else{
				alert("该专业没有考点请添加考点之后在添加考题");
				$("#point_ztreedemo").html("");
				$("#subjectId").val("");
				$("#subjectNameBtn").val("");
			}
		}
});
}
/*
 * 选择专业的change方法发送
 flag:代表选中的select
 */
function getSubjectId(obj,flag){
	// clearPointFrom();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/subj/querySubjectByPid",
		data:{"subject.parentId":obj},
		async:false,
		success:function(result){
			if(result.success==true){
				var subjectList = result.entity;
				var str = "";
				for(var i =0;i<subjectList.length;i++){
					str +='<option value="'+subjectList[i].subjectId+'"">'+subjectList[i].subjectName+'</option>';
				}
				if(flag==1){
					$("#subjectIdTwo").html(str);
					$("#subjectIdTwo").show();
					$("#subjectIdTwo").change();
				}else if(flag==2){
					$("#subjectIdThree").html(str);
					$("#subjectIdThree").show();
					loadPoint(0,0);
				}
			}else{
				if(flag==1){
					$("#subjectIdTwo").hide();
					$("#subjectIdThree").hide();
					$("#subjectIdTwo").html("");
					$("#subjectIdThree").html("");
				}else if(flag==2){
					$("#subjectIdThree").hide();
					$("#subjectIdThree").html("");
				}
				loadPoint(0,0);
			}
		}
});
}

/*
 * 选择考点的change方法发送
 pointFlag:代表选中的select发送的值
 */
function loadPoint(pointId,pointFlag){
	var subjectId = $("#subjectIdOne").val();
	var pId = pointId;
	var v_subjectId=$("#subjectIdTwo").val();
	if(v_subjectId && v_subjectId>0){
		subjectId=v_subjectId;
	};
	v_subjectId=$("#subjectIdThree").val();
	if(v_subjectId && v_subjectId>0){
		subjectId=v_subjectId;
	};
	$("#subjectId").val(subjectId);
	if(pointFlag==0){
		pId = 0;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/point/queryPointListByPIdAndSubjectId",
		data:{"point.subjectId":subjectId,
			"point.parentId":pId},
		async:false,
		success:function(result){
			if(result.entity!=null&&result.entity.length>0){
				var point = result.entity;
				var str = "";
				for(var i =0;i<point.length;i++){
					str +='<option value="'+point[i].id+'"">'+point[i].name+'</option>';
				}
				if(pointFlag==0){
					$("#pointListOne").html(str);
					$("#pointListOne").show();
					$("#pointListOne").change();
				}
				if(pointFlag==1){
					$("#pointListTwo").html(str);
					$("#pointListTwo").show();
					$("#pointListTwo").change();
				}
				if(pointFlag==2){
					$("#pointListThree").html(str);
					$("#pointListThree").show();
					//loadPoint();
				}
			}else{
				if(pointFlag==0){
					if(confirm("该专业没有考点请添加考点之后在添加考题")){ 
						window.location.href = "${ctx}/admin/point/toSavePoint"; 
					}else{
						window.location.href = "${ctx}/admin/point/toSavePoint";
					}
				}
				if(pointFlag==1){
					$("#pointListTwo").hide();
					$("#pointListThree").hide();
					$("#pointListTwo").html("");
					$("#pointListThree").html("");
				}
				if(pointFlag==2){
					$("#pointListThree").hide();
					$("#pointListThree").html("");
				}
			}
		}
});
}

//加载编辑器
function initEditor(id){
	KindEditor.ready(function(K) {
		 K.create('textarea[id="'+id+'"]', {
			resizeType  : 1,
			filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
			allowPreviewEmoticons : false,
			allowUpload : true,//允许上传
			syncType: 'form',
			urlType : 'domain',//absolute
			newlineTag :'br',//回车换行br|p
			uploadJson : '<%=keImageUploadUrl%>?param=question',//图片上传路径
			allowFileManager : false,
			items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
			afterBlur: function(){this.sync();}
		});
	});
}


function loadxuanxiang(isAsrFlag){
	var type="radio";
	if(isAsrFlag==2){
		type="checkbox";
	}
	if(isAsrFlag==5){
		type="checkbox";
	}
	var str ="";
	if(isAsrFlag==3){
		 str +='A.<input type="'+type+'" onclick="qstName(value)" checked="checked" value="A" name="question.isAsr"/>'+
		 ' <textarea  style="width:600px;height:150px;" id="Aasr" name="asr" class="{required:true}"></textarea><br/>'+
		 'B.<input type="'+type+'" onclick="qstName(value)" value="B" name="question.isAsr"/>'+
		  '<textarea  style="width:600px;height:150px;" id="Basr" name="asr" class="{required:true}"></textarea><br/>';
	}else{
		 str+='<div id="appendAsrDiv">';
		 str +='A.<input type="'+type+'" onclick="qstName(value)" checked="checked" value="A" name="question.isAsr"/>'+
		 ' <textarea  style="width:600px;height:150px;" id="Aasr" name="asr" class="{required:true}"></textarea><br/>'+
		 'B.<input type="'+type+'" onclick="qstName(value)" value="B" name="question.isAsr"/>'+
		  '<textarea  style="width:600px;height:150px;" id="Basr" name="asr" class="{required:true}"></textarea><br/>'+
		 'C.<input type="'+type+'" onclick="qstName(value)" value="C" name="question.isAsr"/>'+
		 '<textarea  style="width:600px;height:150px;" id="Casr" name="asr" class="{required:true}"></textarea><br/>'+
		 ' D.<input type="'+type+'" onclick="qstName(value)" value="D" name="question.isAsr"/>'+
		 '<textarea  style="width:600px;height:150px;" id="Dasr" name="asr" class="{required:true}"></textarea>'+
		 ' </div><a href="javascript:void(0);" title="添加选项" onclick="appendAsr()" class="btn btn-danger" >添加选项</a>';
	}
	 $("#option").html(str);
	 loadInitEditor();
}
function addQuestionFrom(){
	var pointId= $("#pointId").val();
	var subjectId= $("#subjectId").val();
	if(pointId==null||pointId==""){
		alert("考点不能为空");
		return;
	}
	if(subjectId==null||pointId==""){
		alert("专业不能为空");
		return;
	}
	
	var asrFalg = "";
	$("textarea[name^='asr']").each(function(){
		if($(this).val()==""){
			alert("选项不能为空");
			asrFalg = 1;
			return false;
		}
	});
	if(asrFalg==1){
		return false;
	}
	
	if($("#qstType").val()==2){
		var isAsr=document.getElementsByName("question.isAsr");//但选中后获取答案；
		var Asr=0;//正确答案
		for(j=0;j<isAsr.length;j++) {
			if(isAsr[j].type=="radio"){
				if(isAsr[j].checked)
				Asr++;
			}
			if(isAsr[j].type=="checkbox"){
				if(isAsr[j].checked){
					Asr++;
				}
			}
		}
		if(Asr<2){
			alert("多选题必须选择1个以上答案");
			return false;
		}
	}
	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/quest/addQuestionAjax",
		data:$("#addQuestionForm").serialize()+"&qstMiddle.paperId=${qstMiddle.paperId}&qstMiddle.complexId=${qstMiddle.complexId}",
		async:false,
		success:function(result){
			if(result.success==true){
				var question = result.entity;
				//存放数据的数组   
				var myArrayMoveStock=new Array();
				//把试题数据存放到数组中
				myArrayMoveStock.push([question.id]);
				opener.newWindow(myArrayMoveStock,question.qstType,'${qstMiddle.complexId}','${qstMiddle.paperMiddleId}');
			}
			quxiao();
		}
});
// 	$("#addQuestionForm").submit();
}
//当试题类型改变时，选择框也跟着更改
function qstName(value){
	var isAsr=document.getElementsByName("question.isAsr");//但选中后获取答案；
	var Asr="";//正确答案
	for(j=0;j<isAsr.length;j++) {
		if(isAsr[j].type=="radio"){
			if(isAsr[j].checked)
			Asr=isAsr[j].value;
		}
		if(isAsr[j].type=="checkbox"){
			if(isAsr[j].checked){
			Asr+=isAsr[j].value;
			}
		}
	}
	$("#ASR").html(Asr);
} 
function qstTypeChange(value){
		loadxuanxiang(value);
}
function appendAsr(){
	str="";
	var type="radio";
	if($("#qstType").val()==2){
		type="checkbox";
	}
	var value="";
	if($("textarea[name^='asr']").length==4){
		value="E";
	}
	if($("textarea[name^='asr']").length==5){
		value="F";
	}
	if($("textarea[name^='asr']").length==6){
		value="G";
	}
	if($("textarea[name^='asr']").length==7){
		alert("选项最多添加7个");
		return false;
	}
	str+=value+'.<input type="'+type+'" onclick="qstName(value)" value="'+value+'" name="question.isAsr"/>'+
		 '<textarea style="width:600px;height:150px;" id="'+value+'asr" name="asr"></textarea>';
	$("#appendAsrDiv").append(str);
	initEditor(value+"asr");
}
function remove(id){
	$("#"+id).remove();
}
function quxiao(){
	window.close(); 
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
		var subjectId = $("#subjectSelect").val();
		subjectOnChange(subjectId);
	}else{
		$("#subjectlist").hide();
	}
	
}
//切换专业时操作
function subjectOnChange(subjectId) {
	
	$("#subjectId").val(subjectId);
	var subjectName = $("#subjectSelect").find("option:selected").text();
	$("#subjectNameBtn").val(subjectName);
	$("#pointNameBtn").val("");
	$("#pointId").val("");
	loadpointtree(subjectId);//加载考点树
}
</script>
<style>
.tdLabel{align="center"}
.label{color:red}
</style>
</head>
<body >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>添加试题</span> </h4>
</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/quest/addQuestion" method="post" id="addQuestionForm">
	<input name="question.subjectId" id="subjectId" type="hidden"/>
	<input name="question.pointId" id="pointId" type="hidden"/>
	<input name="question.complexFalg" type="hidden" value="1"/>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>添加试题<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%" align="center"><font color="red">*</font>&nbsp;试题类型</td>
					<td width="80%">
						<select name="question.qstType" id="qstType" onchange="qstTypeChange(this.value)">
							<option value="1">单选题</option>
							<option value="2">多选题</option>
							<option value="3">判断题</option>
							<option value="5">不定项题</option>
						</select>
					</td>
				</tr>
		<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;所属考试专业分类</td>
				<td width="80%">
					<select onchange="getSubjectByProId(this.value)"  id="professionalSelect">
					<option value="0">请选择</option>
					 <c:forEach items="${professionalList}" var="professional">
					 	<option value="${professional.professionalId}">${professional.professionalName}</option>
					 </c:forEach>
					 </select>
				</td>
		</tr>
		<tr id="subjectlist" style="display: none;">
				<td width="20%" align="center"><font color="red">*</font>&nbsp;所属考试科目</td>
				<td width="80%">
					<select id="subjectSelect" onchange="subjectOnChange(this.value)">
					</select>
				</td>
		</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;选择考点</td>
					<td>
						<input id="pointNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showpointMenu()"/>
						<div id="pointmenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="point_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;试题难度</td>
					<td>
						<select name="question.level">
							<option value="1">一级</option>
							<option value="2">二级</option>
							<option value="3">三级</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;试题内容</td>
					<td>
						<textarea style="width:600px;height:150px;" class="{required:true}" name="question.qstContent" id="qstContent"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;选项内容</td>
					<td id="option">
						<div id="appendAsrDiv">
						 A.<input type="radio" onclick="qstName(value)" checked="checked" value="A" name="question.isAsr"/>
						 <textarea  style="width:600px;height:150px;" id="Aasr" name="asr"></textarea><br/>
						  B.<input type="radio" onclick="qstName(value)" value="B" name="question.isAsr"/>
						 <textarea style="width:600px;height:150px;" id="Basr" name="asr"></textarea><br/>
						  C.<input type="radio" onclick="qstName(value)" value="C" name="question.isAsr"/>
						 <textarea style="width:600px;height:150px;" id="Casr" name="asr"></textarea><br/>
						  D.<input type="radio" onclick="qstName(value)" value="D" name="question.isAsr"/>
						 <textarea style="width:600px;height:150px;" id="Dasr" name="asr"></textarea>
						 </div>
						 <a href="javascript:void(0);" title="添加选项" onclick="appendAsr()" class="btn btn-danger" >添加选项</a>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;正确选项</td>
					<td id="ASR">
						A
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;试题分析</td>
					<td>
						<textarea style="width:600px;height:150px;" name="question.qstAnalyze" id="qstAnalyze" class="{required:true}"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:void(0);" title="提交" onclick="addQuestionFrom()" class="btn btn-danger" >提 交</a>
						<a href="javascript:quxiao()" title="取消" class="btn ml10">取 消</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</div>
</body>
</html>
