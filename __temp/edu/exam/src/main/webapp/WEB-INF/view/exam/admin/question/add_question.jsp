<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加试题</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>


<script type="text/javascript">


$().ready(function() {
	//模拟触发试题类型
	$("#qstType").change();
	$("#professionalSelect").change();
	
});

//subject ztree end

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

function onBodyDown(event) {
	if (!(event.target.id == "pointNameBtn" || event.target.id == "pointmenuContent" || $(event.target).parents("#pointmenuContent").length>0)) {
		hidepointMenu();
	}
	
}
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

$().ready(function(){
	$("#subjectIdOne").change();
	initEditor('qstContent');
	loadInitEditor();
	 $("#addQuestionForm").validate();
	 initEditor('qstAnalyze');
});
//切换专业时操作
function subjectOnChange(subjectId) {
	
	$("#subjectId").val(subjectId);
	var subjectName = $("#subjectSelect").find("option:selected").text();
	$("#subjectNameBtn").val(subjectName);
	$("#pointNameBtn").val("");
	$("#pointId").val("");
	loadpointtree(subjectId);//加载考点树
}
/*
 * 加载考点树
 */
function loadpointtree(subjectId){
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

function loadInitEditor(){
	$("textarea[name^='asr']").each(function(){
		initEditor($(this).attr("id"));
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
			uploadJson : '<%=keImageUploadUrl%>question',//图片上传路径
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

	$(".zhuguanhide").show();
	var type="radio";
	if(isAsrFlag==6){
		$(".zhuguanhide").hide();
		$("#option").html('<input type="hidden" name="asr"/>');
		return;
	}
	if(isAsrFlag==2||isAsrFlag==5){
		type="checkbox";
	}
	var str ="";
	if(isAsrFlag==3){
		 str +='A.<input type="'+type+'" onclick="qstName(value)" checked="checked" value="A" name="question.isAsr"/>'+
		 ' <textarea  style="width:760px;height:150px;" id="Aasr" name="asr" class="{required:true}"></textarea><br/>'+
		 'B.<input type="'+type+'" onclick="qstName(value)" value="B" name="question.isAsr"/>'+
		  '<textarea  style="width:760px;height:150px;" id="Basr" name="asr" class="{required:true}"></textarea><br/>';
	}else{
		 str+='<div id="appendAsrDiv">';
		 str +='A.<input type="'+type+'" onclick="qstName(value)" checked="checked" value="A" name="question.isAsr"/>'+
		 ' <textarea  style="width:760px;height:150px;" id="Aasr" name="asr" class="{required:true}"></textarea><br/>'+
		 'B.<input type="'+type+'" onclick="qstName(value)" value="B" name="question.isAsr"/>'+
		  '<textarea  style="width:760px;height:150px;" id="Basr" name="asr" class="{required:true}"></textarea><br/>'+
		 'C.<input type="'+type+'" onclick="qstName(value)" value="C" name="question.isAsr"/>'+
		 '<textarea  style="width:760px;height:150px;" id="Casr" name="asr" class="{required:true}"></textarea><br/>'+
		 ' D.<input type="'+type+'" onclick="qstName(value)" value="D" name="question.isAsr"/>'+
		 '<textarea  style="width:760px;height:150px;" id="Dasr" name="asr" class="{required:true}"></textarea>'+
		 ' </div><a href="javascript:void(0);" title="添加选项" onclick="appendAsr()" class="btn btn-danger" >添加选项</a>';
	}
	 $("#option").html(str);
	 loadInitEditor();
}
//添加客观题
function addQuestionFrom(){
	var pointId= $("#pointId").val();
	var professionalId= $("#professionalSelect").val();
	if(professionalId==null||professionalId==0){
		alert("请选择专业");
		return;
	}
	if(pointId==null||pointId==""){
		alert("考点不能为空");
		return;
	}
	
	var qstType = $("#qstType").val();
	if(qstType==6){
		addzhuguan();
	}else{
		addkeguan();
	}
}
//添加主观题
function addzhuguan(){
	$("#addQuestionForm").submit();
}
function addkeguan(){
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
	$("#addQuestionForm").submit();
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
		 '<textarea style="width:760px;height:150px;" id="'+value+'asr" name="asr"></textarea>';
		 //name=asr['+$("textarea[name^='asr']").length+']
	$("#appendAsrDiv").append(str);
	initEditor(value+"asr");
}
function remove(id){
	$("#"+id).remove();
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
</script>
<style>
.tdLabel{align="center"}
.label{color:red}
</style>
</head>
<body>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>添加试题</span> </h4>
</div>
<div class="mt20">
<form action="${ctx}/admin/quest/addQuestion" method="post" id="addQuestionForm">
<input name="question.subjectId" id="subjectId" type="hidden"/>
<input name="question.pointId" id="pointId" type="hidden"/>
<div class="commonWrap">
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
					<option value="6">主观题</option>
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
				<textarea style="width:760px;height:150px;" class="{required:true}" name="question.qstContent" id="qstContent"></textarea>
			</td>
		</tr>
		<tr class="zhuguanhide">
			<td align="center"><font color="red">*</font>&nbsp;选项内容</td>
			<td id="option">
				<div id="appendAsrDiv">
				 A.<input type="radio" onclick="qstName(value)" checked="checked" value="A" name="question.isAsr"/>
				 <textarea  style="width:760px;height:150px;" id="Aasr" name="asr"></textarea><br/>
				  B.<input type="radio" onclick="qstName(value)" value="B" name="question.isAsr"/>
				 <textarea style="width:760px;height:150px;" id="Basr" name="asr"></textarea><br/>
				  C.<input type="radio" onclick="qstName(value)" value="C" name="question.isAsr"/>
				 <textarea style="width:760px;height:150px;" id="Casr" name="asr"></textarea><br/>
				  D.<input type="radio" onclick="qstName(value)" value="D" name="question.isAsr"/>
				 <textarea style="width:760px;height:150px;" id="Dasr" name="asr"></textarea>
				 </div>
				 <a href="javascript:void(0);" title="添加选项" onclick="appendAsr()" class="btn btn-danger" >添加选项</a>
			</td>
		</tr>
		<tr class="zhuguanhide">
			<td align="center"><font color="red">*</font>&nbsp;正确选项</td>
			<td id="ASR">
				A
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;试题分析</td>
			<td>
				<textarea style="width:760px;height:150px;" id="qstAnalyze" name="question.qstAnalyze"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="javascript:void(0);" title="提交" onclick="addQuestionFrom()" class="btn btn-danger tijiao" >提 交</a>
				<a href="${ctx}/admin/quest/toQuestionList" title="返回" class="btn ml10">返 回</a>
			</td>
		</tr>
	</tbody>
</table>
</div>
</form>
</div>
</body>
</html>
