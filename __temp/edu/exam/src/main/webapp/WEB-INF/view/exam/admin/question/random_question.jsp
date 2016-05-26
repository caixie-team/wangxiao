<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>随机添加试题</title>
<script type="text/javascript">
/*
 * 选择专业的change方法发送
 flag:代表选中的select
 */
function getSubjectId(obj,flag){
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
					$("#subjectListTwo").show();
					$("#subjectIdTwo").change();
				}else if(flag==2){
					$("#subjectIdThree").html(str);
					$("#subjectListThree").show();
					loadztree();
				}
			}else{
				if(flag==1){
					$("#subjectListTwo").hide();
					$("#subjectListThree").hide();
					$("#subjectIdTwo").html("");
					$("#subjectIdThree").html("");
				}else if(flag==2){
					$("#subjectListThree").hide();
					$("#subjectIdThree").html("");
				}
				loadztree();
			}
		}
});
}

$(function(){
	$("#subjectIdOne").change();

});
function newPoint(){
	$("#name").val("");
	$("#examFrequency").val("");
	$("#info").val("");
	$("#id").val("0");
	$("#pIdHidden").val(0);
	$("#pIdName").val("");
	$("#level").val(1);
	clearPointFrom();
}
var pointIds = "";
function clickBox(id){
	if($("input[pid=sfId" + id + "]").length==0){
		pointIds+=id+",";
	}
	$.each($("input[pid=sfId" + id + "]"), function () {
	this.checked = $('#sfId' + id).prop("checked");
	clickBox(this.value);
	});
	} 
//加载树
function loadztree(){
	var subjectId = null;
	
	subjectId = $("#subjectIdOne").val();
	var v_subjectId=$("#subjectIdTwo").val();
	if(v_subjectId && v_subjectId>0){
		subjectId=v_subjectId;
	};
	v_subjectId=$("#subjectIdThree").val();
	if(v_subjectId && v_subjectId>0){
		subjectId=v_subjectId;
	};
		
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/point/queryPointList",
		data:{"point.subjectId":subjectId},
		async:false,
		success:function(result){
			//左边考点树展示
			var pointList = result.entity;
			
		}
});
}
function randomPoint(){
	var num = $("#num").val();
	if(pointIds==""){
		alert('请选择考点');
		return false;
	}
	if(num==""){
		alert('请填写数量');
		return false;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/quest/randomQuestion",
		data:{"pointIds":pointIds,"num":num,"qstMiddle.qstType":'${qstMiddle.qstType}'},
		async:false,
		success:function(result){
			if(result.success==true){
				questionList = result.entity;
				//存放数据的数组   
				var myArrayMoveStock=new Array();
				for(var i=0;i<questionList.length;i++){
					question = questionList[i];
					//把试题数据存放到数组中
					myArrayMoveStock.push([question.id,question.qstContent]);
				}
				opener.newWindow(myArrayMoveStock,'${qstMiddle.qstType}','${qstMiddle.complexId}','${qstMiddle.paperMiddleId}');
				quxiao();
			}
		}
	});
	
}
function subjectIdHiddeninit(){
	$(".subjectIdOneHidden").val($("#subjectIdOne").val()!=null?$("#subjectIdOne").val():0);
	$(".subjectIdTwoHidden").val($("#subjectIdTwo").val()!=null?$("#subjectIdTwo").val():0);
	$(".subjectIdThreeHidden").val($("#subjectIdThree").val()!=null?$("#subjectIdThree").val():0);
}

function quxiao(){
	window.close(); 
	}
</script>
<style>
.tdLabel{align="center"}
.label{color:red}
</style>
</head>
<body > 
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>考点管理</span> &gt; <span>考点树</span> </h4>
</div>
<div class="mt20">
<div class="commonWrap">
	<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
		<caption>
			<div class="capHead">
				<div class="clearfix">
					<div class="optionList">
						<span><font>考试项目分类：</font></span>
						<span>
							<s:select name="subjectIdOne" onchange="getSubjectId(this.value,1)" value="subjectIdOne" id="subjectIdOne" list="subjectList"  listKey="subjectId" listValue="subjectName" theme="simple" ></s:select>
						</span>
					</div>
					<div class="optionList" id="subjectListTwo" style="display:none;">
						<span>
						<select id="subjectIdTwo" name="subjectIdTwo" onchange="getSubjectId(this.value,2)">
						</select>
						</span>
					</div>
					<div class="optionList" id="subjectListThree" style="display:none;">
						<span>
						<select id="subjectIdThree" name="subjectIdThree" onchange="getSubjectId(this.value,3)">
						</select>
						</span>
					</div>
					<div class="optionList">
					</div>
				</div>
			</div>
		</caption>
	</table>
	<!-- /pageBar begin -->
	
	<!-- /pageBar end -->
</div><!-- /commonWrap -->

	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th colspan="2" align="left"><span>考点树</span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="35%">
				请选择要从哪个考点里随机抽题
				<div id="d1"></div>
				</td>
				<td width="80%">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th colspan="2" align="left"><span>随机抽题<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;随机抽题数量</td>
							<td>
								<input type="text" value="" id="num"/>
							</td>
						</tr>
						
						<!-- <tr>
							<td align="center"><font color="red">*</font>&nbsp;考点信息</td>
							<td>
								<input type="text" name="point.info" id="info" class="{required:true}"/>
							</td>
						</tr> -->
						<tr>
							<td colspan="2" align="center">
								<input class="btn btn-danger" type="button" onclick="randomPoint()" value="提 交"/>
								<input class="btn ml10" type="button" onclick="quxiao();" value="返 回"/>
							</td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
