<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>查看试题</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>

<script type="text/javascript">
$(function(){
	 initInfo();
	 initEditor('qstContent');
	 loadPoint();
	 loadInitEditor();
	 initEditor('qstAnalyze');
});

//加载考点
function loadPoint(){
	var str ="";
	point= queryPointById('${question.pointId}');
	if(point!=null){
	str += point.name;
	if(point.parentId!=0){
		point= queryPointById(point.parentId);
		str = point.name+"->"+str;
	}
	if(point.parentId!=0){
		point= queryPointById(point.parentId);
		str = point.name+"->"+str;
	}
	$("#point").html(str);
	}
}
//获得考点
function queryPointById(obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/point/queryPointByPointId",
		data:{"point.id":obj,"point.state":0},
		async:false,
		success:function(result){
			if(result.success){
				obj=result.entity;
			}
		}
});
	return obj;
}

function initInfo(){
	$("#qstType").val('${question.qstType}');
	$("#level").val('${question.level}');
}
//加载选项的编辑器
function loadInitEditor(){
	$("textarea[name^='asr']").each(function(){
		initEditor($(this).attr("id"));
	});
}
//加载编辑器
function initEditor(id){
	KindEditor.ready(function(K) {
		K.create('textarea[id="'+id+'"]', {
				resizeType  : 0,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       uploadJson : '<%=keImageUploadUrl%>?param=question',//图片上传路径
		       allowFileManager : false,
		       items : []
		});
	});
}
function addQuestionFrom(){
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
	var pointId = $("#pointListOne").val();
	var v_pointId=$("#pointListTwo").val();
	if(v_pointId && v_pointId>0){
		pointId=v_pointId;
	};
	v_pointId=$("#pointListThree").val();
	if(v_pointId && v_pointId>0){
		pointId=v_pointId;
	};
	$("#pointId").val(pointId);
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
</script>
<style>
.tdLabel{align="center"}
.label{color:red}
</style>
</head>
<body >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>查看试题</span> </h4>
</div>
<div class="mt20">
<div class="commonWrap">
<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
	<thead>
		<tr>
			<th colspan="2" align="left"><span>查看试题<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td width="20%" align="center"><font color="red">*</font>&nbsp;试题类型</td>
			<td width="80%">
				<select name="question.qstType" id="qstType" disabled="disabled">
					<option value="1">单选题</option>
					<option value="2">多选题</option>
					<option value="3">判断题</option>
					<option value="5">不定项题</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;选择专业</td>
			<td id="subject" >
			<select  id="professionalSelect" disabled="disabled">
					<option>${examProfessionalDto.professionalName }</option>
			</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;选择科目</td>
			<td id="subject" >
			<select  id="subjectSelect" disabled="disabled">
					<option>${examProfessionalDto.subjectName }</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;选择考点</td>
			<td id="point">
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;试题难度</td>
			<td>
				<select name="question.level" id="level" disabled="disabled">
					<option value="1">一级</option>
					<option value="2">二级</option>
					<option value="3">三级</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;试题内容</td>
			<td>
				<textarea style="width:600px;height:150px;" class="{required:true}" name="question.qstContent" id="qstContent">${question.qstContent}</textarea>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;选项内容</td>
			<td id="option">
				<div id="appendAsrDiv">
				<c:forEach items="${options}" var="tdoption" >
				${tdoption.optOrder}.<input type="${question.qstType==2?'checkbox':'radio' }" onclick="qstName(value)" id="${tdoption.optOrder}optOrder" value="${tdoption.optOrder}" name="question.isAsr"/>
				<textarea  style="width:600px;height:150px;" id="${tdoption.optOrder}asr" name="asr">${tdoption.optContent}</textarea><br/>
				<script type="text/javascript">
				var checked ="";
				var optAnswer = '${tdoption.optAnswer}';
				optAnswer = optAnswer.split(",");
				//optContent = optContent.replace(/<[^>].*?>/g,"");
				for (var i=0;i<optAnswer.length ;i++ ){   
					if(optAnswer[i].trim()=='${tdoption.optOrder}'){//如果答案和选项相等就选中
						$("#${tdoption.optOrder}optOrder").attr("checked",true);
					}
			    } 
				</script>
				</c:forEach>
				 </div>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;正确选项</td>
			<td id="ASR">
				${question.isAsr }
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;试题分析</td>
			<td>
				<textarea style="width:600px;height:150px;" id="qstAnalyze" name="question.qstAnalyze" class="{required:true}" id="qstAnalyze">${question.qstAnalyze}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="${ctx}/admin/quest/toQuestionList" title="返回" class="btn ml10">返 回</a>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</body>
</html>
