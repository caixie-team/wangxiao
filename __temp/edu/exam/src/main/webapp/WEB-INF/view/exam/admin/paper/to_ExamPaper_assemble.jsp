<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试题组卷</title>

<link rel="stylesheet" href="${ctximg}/kindeditor/themes/simple/simple.css" />
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript">
	var paperId ='${paper.id}';
	initEditor("justdonouse");//先调用1次后动态才可用。暂未发现解决办法。添加材料题
	$().ready(function(){
		initEditorClass("textarea");
		 $("textarea[name^='complexContent']").each(function(){
				initEditor($(this).attr("id"));
		});
		
	});
//选中已有试题小窗口
function showwin(type,complexId,paperMiddleId){
	if(paperMiddleId==null){
		alert("取值错误");
		return;
	}
	window.open('${ctx}/admin/quest/toQuestionListByType?complexId='+complexId+'&queryQuestion.qstType='+type+'&qstMiddle.paperMiddleId='+paperMiddleId,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
}

/**
 * 子页面回调
 myArrayInStock传来的数组
 type 试题类型
 complexId 复合体的id
 paperMiddleId 中间表exam_paper_middle_tbl.id
 */

function newWindow(myArrayInStock,type,complexId,paperMiddleId){
		//去重复时要有一个主键，这里试题号
		var qstId  = document.getElementsByName(paperMiddleId+"qstIds"); 
		//当complexId不等于0的时候 说明是在添加材料题
		for(var i=0;i<qstId.length;i++){   
			for(var j=0;j<myArrayInStock.length;j++){   
				var id = myArrayInStock[j];   
				if(qstId[i].value==id){   
					myArrayInStock.splice(j,1);//删除重复的
				};
			};
		}
		//插入到表格中   
		qstAdddanxuan(myArrayInStock,type,complexId,paperMiddleId);
}
//添加试题到页面中
function qstAdddanxuan(myArray,type,complexId,paperMiddleId){
	var qstIds =myArray.join(",");
	if(qstIds!=""){
		$.ajax({
			type:"POST",
			dataType:"text",
			url:"${ctx}/admin/ajax/paper/addExamQstMiddleBatch",
			data:{"qstMiddle.paperId":paperId,
				"qstMiddle.qstType":type,
				"qstIds":qstIds,
				"qstMiddle.complexId":complexId,
				"qstMiddle.paperMiddleId":paperMiddleId},
			async:false,
			success:function(result){
				$("#ajaxContent").html(result);
			},
			error:function(error){
				alert("error:"+error);
			}
		});
	}
}


var editor;
//加载编辑器
function initEditorClass(id){
		editor = KindEditor.create('textarea[class="'+id+'"]', {
				resizeType  : 1,
				 width:"810px",
			       height:"60px",
			       minWidth:"30px",
			       minHeight:"30px",
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       readonlyMode : true,
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
		       allowFileManager : false,
		       items : [],
		       afterBlur : function() {
		    	   editor.sync();
		    	   editor.readonly();
				}
		});
		//editor.readonly(true);
}
//移除编辑器
function removeEditorClass(id){
	KindEditor.ready(function(K) {
		K.remove('textarea[class="'+id+'"]');
	});
}


//删除1个试题
function delqstMiddleLi(papermiddleid,qstid){
	if(confirm("是否删除该试题?")){ 
		$.ajax({
			type:"POST",
			dataType:"text",
			url:"${ctx}/admin/ajax/quest/delQstMiddleById",
			data:{"qstMiddle.id":qstid,"qstMiddle.paperMiddleId":papermiddleid,"paperId":paperId},
			async:false,
			success:function(result){
				$("#ajaxContent").html(result);
			}
		});
	} 
}


//显示更新大题
function doUpdatePaperMiddle(paperMiddleId){
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/paper/doUpdatePaperMiddle/"+paperMiddleId,
		async:false,
		success:function(result){
			$("#updateMainQst").html(result);
		}
	});
}

//修改大题
function updatePaperMiddle(){
	if($("#upaperMiddle_name").val()==null||$("#upaperMiddle_name").val()==''){
		alert("请填写大题目名称");
		return;
	}
	if($("#upaperMiddle_num").val()==null||$("#upaperMiddle_num").val()==''){
		alert("请填写内含小题数");
		return;
	}
	if($("#upaperMiddle_score").val()==null||$("#upaperMiddle_score").val()==''){
		alert("请填写每题分值");
		return;
	}
	if($("#upaperMiddle_title").val()==null||$("#upaperMiddle_title").val()==''){
		alert("请填写大题说明");
		return;
	}
	
	//大题修改页
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/paper/updatePaperMiddle",
		data:$("#updatePaperMiddleForm").serialize(),
		async:false,
		success:function(result){
			$("#updateMainQst").html("");
			$("#ajaxContent").html(result);
			
		}
	});
}

function ExamPaperAssembleFormSubmit(){
	$("#ExamPaperAssembleForm").submit();
}

//移动小题操作
function moveQst(qstId,sort,sortCount,keyword,type){
	var moveQstStr="";
	//替换行的id和排序
	if(type=='up'){//上移
		var moveQstStr=$("#"+keyword+(sortCount-1)).val();
	}else{//下移
		var moveQstStr=$("#"+keyword+(sortCount+1)).val();
	}
	if(moveQstStr==null||moveQstStr==''){//替换行不存在返回
		return;
	}
	moveQstId = moveQstStr.split(",")[0];
	moveQstSort = moveQstStr.split(",")[1];
	qstType=moveQstStr.split(",")[2];
	complexId=moveQstStr.split(",")[3];
	paperMiddleId=moveQstStr.split(",")[4];
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/quest/moveUp",
		data:{"oneId":qstId,"oneSort":sort,"twoId":moveQstId,"twotSort":moveQstSort,"qstMiddle.paperId":paperId,"qstMiddle.qstType":qstType,"qstMiddle.complexId":complexId,"qstMiddle.paperMiddleId":paperMiddleId},
		async:false,
		success:function(result){
			$("#ajaxContent").html(result);
		}
	});
}


//随机抽试题小窗口
function openWindowRandomQuestion(type,paperMiddleId){
	window.open('${ctx}/admin/quest/toRandomQuestion?qstMiddle.qstType='+type+'&qstMiddle.paperMiddleId='+paperMiddleId,'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=400');
}

//添加大题
function addPaperMiddle(){
	
	if($("#paperMiddleType").val()==0){
		alert("请选择试题类型");
		return;
	}
	if($("#paperMiddle_name").val()==null||$("#paperMiddle_name").val()==''){
		alert("请填写大题目名称");
		return;
	}
	if($("#paperMiddle_num").val()==null||$("#paperMiddle_num").val()==''){
		alert("请填写内含小题数");
		return;
	}
	if($("#paperMiddle_score").val()==null||$("#paperMiddle_score").val()==''){
		alert("请填写每题分值");
		return;
	}
	if($("#paperMiddle_title").val()==null||$("#paperMiddle_title").val()==''){
		alert("请填写大题说明");
		return;
	}
	$("#createMainQst").hide();
	//提交添加
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/paper/addPaperMiddle",
		data:$("#paperMiddleForm").serialize(),
		async:false,
		success:function(result){
			//清除表单数据
			$('#paperMiddleForm')[0].reset();
			$("#ajaxContent").html(result);
			var bodyH = $("#layout").height();
			$("html,body").animate({"scrollTop" : bodyH}, 500);
			
		}
	});
}



//移动大题操作
function paperMiddleMove(paperMiddleId,sort,sortCount,type){
	//替换行的id和排序
	var movePaperMiddleStr='';
	if(type=='up'){//上移
		movePaperMiddleStr=$("#paperMiddleSort"+(sortCount-1)).val();
	}else{//下移
		movePaperMiddleStr=$("#paperMiddleSort"+(sortCount+1)).val();
	}
	if(movePaperMiddleStr==null||movePaperMiddleStr==''){//替换行不存在返回
		return;
	}
	movePaperMiddleId = movePaperMiddleStr.split(",")[0];
	movePaperMiddleSort = movePaperMiddleStr.split(",")[1];
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/paper/paperMiddleMoveUp",
		data:{"oneId":paperMiddleId,"oneSort":sort,"twoId":movePaperMiddleId,"twotSort":movePaperMiddleSort,"paperId":paperId},
		async:false,
		success:function(result){
			$("#ajaxContent").html(result);
		}
	});
}


//删除大题
function delPaperMiddle(paperMiddleId){
	if(confirm("确认删除该大题？")){
		$.ajax({
			type:"POST",
			dataType:"text",
			url:"${ctx}/admin/ajax/paper/delPaperMiddle",
			data:{"paperMiddle.id":paperMiddleId,
				"paperMiddle.paperId":paperId},
			async:false,
			success:function(result){
				$("#ajaxContent").html(result);
			}
		});
	}
}

//修改显示名称
function xiugaiName(papermiddleid){
	objValue=$.trim($("#spanname"+papermiddleid).html());
	if(objValue=="请输入显示名称"){objValue="";}
	var str='<input type="text" value="'+objValue+'" id="name'+papermiddleid+'" onblur="updatedName('+papermiddleid+');" style="width:150px;"/>';
	$("#spanname"+papermiddleid).html($.trim(str));
}
function updatedName(id){
	$("#spanname"+id).html($("#name"+id).val());
	updatedanxuanPaperMiddle(id);
}


//删除材料题
function delCaiLiaoQuestion(id){
	if(confirm("确认删除该题？")){
		$.ajax({
			type:"POST",
			dataType:"text",
			url:"${ctx}/admin/ajax/paper/delCaiLiaoQuestion",
			data:{"complexId":id,"paperId":paperId},
			async:false,
			success:function(result){
				$("#ajaxContent").html(result);
			}
		});
	}
}

//添加材料题
function addcailiaoHtml(paperMiddleId){
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/paper/addCaiLiaoContent",
		data:{"complex.paperId":paperId,
			"complex.paperMiddleId":paperMiddleId,
			"complex.complexContent":"请输入材料题内容"},
		async:false,
		success:function(result){
			$("#ajaxContent").html(result);
		}
	});
}

/**
 * 更新材料题的内容
 * id 材料题id
 */
function updateComplexContent(id){
	var complexContent = $("#complexContent"+id).val();
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"${ctx}/admin/ajax/paper/updateCaiLiaoContent",
		data:{"complex.id":id,"complex.complexContent":complexContent,"paperId":paperId},
		async:false,
		success:function(result){
			$("#ajaxContent").html(result);
		}
	});
}

//材料题打开选择试题小页面
function openWindowQuestion(complexId,paperMiddleId){
	window.open('${ctx}/admin/quest/toAddCaiLiaoQuestion?qstMiddle.paperId=' + paperId
							+ '&qstMiddle.paperMiddleId=' + paperMiddleId+"&qstMiddle.complexId="+complexId,
					'newwindow',
					'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width='
							+ (window.screen.availWidth - 10) + ',height='
							+ (window.screen.availHeight - 30) + '');
}


//切换试题类型操作
function paperMiddleTypechange(obj){
	if(obj.value==1){
		$("#paperMiddle_name").val("单项选择题");
		$("#paperMiddle_title").val("每题有且只有1个正确答案");
	}else if(obj.value==2){
		$("#paperMiddle_name").val("多项选择题");
		$("#paperMiddle_title").val("每题至少2个正确选项");
	}else if(obj.value==3){
		$("#paperMiddle_name").val("判断题");
		$("#paperMiddle_title").val("判断对错");
	}else if(obj.value==4){
		$("#paperMiddle_name").val("材料分析题");
		$("#paperMiddle_title").val("根据材料回答下面的问题");
	}else if(obj.value==5){
		$("#paperMiddle_name").val("不定项选择题");
		$("#paperMiddle_title").val("每题至少有1个正确选项，多则不限");
	}else if(obj.value==6){
		$("#paperMiddle_name").val("主观题");
		$("#paperMiddle_title").val("阅读题干，回答问题");
	}else {
		$("#paperMiddle_name").val("");
		$("#paperMiddle_title").val("");
	}
	$("#paperMiddle_score").val("1");
	$("#paperMiddle_num").val("1");
	 
}

//加载编辑器
function initEditor(id){
	KindEditor.ready(function(K) {
		K.create('textarea[id="'+id+'"]', {
			resizeType  : 1,
	       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
	       allowPreviewEmoticons : false,
	       allowUpload : true,//允许上传
	       urlType : 'domain',//absolute
	       newlineTag :'br',//回车换行br|p
	       uploadJson : 'http://127.0.0.1/upimg?base=sns-web&param=question',//图片上传路径
	       allowFileManager : false,
	       items : [
					'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
		 afterBlur: function(){
			  this.sync();
			  this.readonly(true);//只读
		  },
		  afterFocus:function(){
		  	this.readonly(false);//取消只读
		  }
		});
	});
}

</script>
</head>
<body >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>试题组卷</span>
		</h4>
	</div>
		
	<input type="hidden" id="paperIdHidden" name="paper.id" value="${paper.id }" />
	<div class="mt20">
		<div class="commonWrap" >
			
			<div style="padding: 0 20px;" class="mt10">
				<a class="btn btn-danger" title="创建大题目" onclick="$('#createMainQst').show()" href="javascript:void(0);">创建大题目</a>
				<span class="ml10 c_666">先创建试题题目，再向试题题目中添加具体的试题。</span>
			</div>
			<div id="ajaxContent">
			<c:forEach items="${paperMiddleList}" var="divpaperMiddle" varStatus="divpaperMiddle_status">
				<div style="padding: 20px;">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<div class="testPagerTitle">
					<h5>
						<b>第${divpaperMiddle_status.index+1}部分( 
							<c:if test="${divpaperMiddle.type==1}">单选题</c:if> 
							<c:if test="${divpaperMiddle.type==2}">多选题</c:if> 
							<c:if test="${divpaperMiddle.type==3}">判断题</c:if> 
							<c:if test="${divpaperMiddle.type==4}">材料题</c:if> 
							<c:if test="${divpaperMiddle.type==5}">不定项选择</c:if>
							<c:if test="${divpaperMiddle.type==6}">主观题</c:if>  )：
						</b>
						<span id="spanname${divpaperMiddle.id }" class="name${divpaperMiddle.id }">
							 <c:choose>
								<c:when test="${divpaperMiddle.name==null || divpaperMiddle.name.trim()==''}">请输入显示名称</c:when>
								<c:otherwise>${divpaperMiddle.name}</c:otherwise>
							</c:choose>
						</span> 
						<span style="margin-right: 2%; float: right;">
							<c:if test="${divpaperMiddle.type!=4 }">
								<a class="btn btn-danger" title="填充试题" href="javascript:void(0);" onclick="showwin(${divpaperMiddle.type},0,${divpaperMiddle.id });">填充试题</a>
							</c:if>
							<c:if test="${divpaperMiddle.type==4 }">
								<a class="btn btn-danger" title="添加子材料题" href="javascript:addcailiaoHtml('${divpaperMiddle.id }')">添加子题</a>
							</c:if>
							<a class="btn btn-danger" title="修改" onclick="doUpdatePaperMiddle('${divpaperMiddle.id }');" href="javascript:void(0);">修改题目</a>
							<a class="btn btn-danger" title="删除" onclick="delPaperMiddle('${divpaperMiddle.id }');" href="javascript:void(0);">删除题目</a>
							<a class="btn ml10" title="上移" onclick="paperMiddleMove(${divpaperMiddle.id },${divpaperMiddle.sort },${divpaperMiddle_status.count},'up')" href="javascript:void(0);">上移</a> 
							<a class="btn ml10" title="下移" onclick="paperMiddleMove(${divpaperMiddle.id },${divpaperMiddle.sort },${divpaperMiddle_status.count},'down');" href="javascript:void(0);">下移</a>
							<input type="hidden" id="paperMiddleSort${divpaperMiddle_status.count}" value="${divpaperMiddle.id},${divpaperMiddle.sort }"/>
						</span>
						</h5>
						<input type="hidden" value="<c:out value="type"/>" class="${divpaperMiddle.id}type" />
					</div>
				<c:if test="${divpaperMiddle.type!=4 }">	
				<thead>
					<tr>
						<th><span>题号</span></th>
						<th><span>试题/材料名称</span></th>
						<th><span>分值</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				</c:if>
				<tbody id="tabS_01">
						<!-- 循环大题 -->
						<c:choose>
							<c:when test="${divpaperMiddle.type==4 }">
								<c:forEach items="${divpaperMiddle.complexList}" var="divcomplexList" varStatus="divcomplexList_sattus">
									<tr>
										<td colspan="6"><b>材料题${divcomplexList_sattus.index+1}：</b>
											<a class="btn btn-danger" onclick="showwin(0,${divcomplexList.id },${divpaperMiddle.id });" href="javascript:void(0);" title="选择已有试题">填充试题</a>
											<a class="btn btn-danger" onclick="openWindowQuestion(${divcomplexList.id },${divpaperMiddle.id });" href="javascript:void(0);" title="添加材料新试题">添加材料新试题</a>
											<a class="btn btn-danger" href="javascript:delCaiLiaoQuestion(${divcomplexList.id })" title="删除材料题">删除材料题</a>
											<a class="btn btn-danger" href="javascript:updateComplexContent(${divcomplexList.id })" title="保存材料题内容">保存内容</a>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											
											<div class="cl-txt"> 
												<textarea title="${divcomplexList.id}" id="complexContent${divcomplexList.id}"  name="complexContent${divcomplexList.id}">${divcomplexList.complexContent}</textarea>
											</div>
											<div>
												<p>根据以上材料回答以下问题：</p>
											</div>
										</td>
									</tr>
									<tr align="center">
										<td><span>题号</span></td>
										<td><span>试题/材料名称</span></td>
										<td><span>分值</span></td>
										<td><span>操作</span></td>
									</tr>
									<c:forEach items="${divcomplexList.queryQstMiddleList}" var="ulQueryQstMiddleList" varStatus="index">
										<tr align="center">
											<td>
												<input value="${ulQueryQstMiddleList.qstId }" name="${ulQueryQstMiddleList.paperMiddleId}qstIds" type="hidden" />
												<span class="c_333">${index.count}</span>
												<input value="${ulQueryQstMiddleList.qstId }" name="cailiaoqstIds" type="hidden" />
											</td>
											<td>
												<span class="c_777">
													<textarea class="textarea" style="width: 100%; ">${ulQueryQstMiddleList.qstContent}</textarea>
												</span>
											</td>
											<td>${divpaperMiddle.score}</td>
											<td>
												<span class="span">
													<a class="btn ml10" title="删除" href="javascript:delqstMiddleLi(${divpaperMiddle.id},'${ulQueryQstMiddleList.id}');">删除</a>
													<a class="btn ml10" title="上移" onclick="moveQst(${ulQueryQstMiddleList.qstId},${ulQueryQstMiddleList.sort},${index.count},'${ulQueryQstMiddleList.complexId }complexQstSort','up');" href="javascript:void(0);">上移</a> 
													<a class="btn ml10"　title="下移" onclick="moveQst(${ulQueryQstMiddleList.qstId},${ulQueryQstMiddleList.sort},${index.count},'${ulQueryQstMiddleList.complexId }complexQstSort','down');" href="javascript:void(0);">下移</a> 
													<input type="hidden" id="${ulQueryQstMiddleList.complexId }complexQstSort${index.count}" value="${ulQueryQstMiddleList.qstId},${ulQueryQstMiddleList.sort },${ulQueryQstMiddleList.qstType },${ulQueryQstMiddleList.complexId },${divpaperMiddle.id }"/>
												</span>
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${divpaperMiddle.qstMiddleList}" var="ulqstMiddleList" varStatus="index">
									<tr align="center">
										<td>
											<input value="${ulqstMiddleList.qstId }" name="${ulqstMiddleList.paperMiddleId}qstIds" type="hidden" />
											<span class="c_333">${index.count }</span>
										</td>
										<td>
											<span class="c_777">
												<textarea class="textarea"style="width: 100%; ">${ulqstMiddleList.qstContent}</textarea>
											</span>
										</td>
										<td>${divpaperMiddle.score}</td>
										<td>
											<span class="span">
												<a class="btn ml10" title="删除" href="javascript:void(0);" onclick="delqstMiddleLi(${divpaperMiddle.id},'${ulqstMiddleList.id }')">删除</a>
												<a class="btn ml10" title="上移" onclick="moveQst(${ulqstMiddleList.qstId},${ulqstMiddleList.sort},${index.count},'${divpaperMiddle.id}qstSort','up');" href="javascript:void(0);">上移</a> 
												<a class="btn ml10"　title="下移" onclick="moveQst(${ulqstMiddleList.qstId},${ulqstMiddleList.sort},${index.count},'${divpaperMiddle.id}qstSort','down');" href="javascript:void(0);">下移</a> 
												<input type="hidden" id="${divpaperMiddle.id}qstSort${index.count}" value="${ulqstMiddleList.qstId},${ulqstMiddleList.sort },${ulqstMiddleList.qstType },0,${divpaperMiddle.id }"/>
											</span>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</tbody>
				
			</table>
			</div>
			</c:forEach>
			</div>
		</div>
		
				<!-- /题型一 -->
	</div>
	<div id="createMainQst" style="display: none">
	<div class="modal-backdrop fade in"></div>
	<div class="modal fade in">
		<div class="modalWrap">
			<div class="modal_head"><span class="fr"><a class="icon14 closeBtn" title="关闭" href="javascript:void(0)" onclick="$('#createMainQst').hide()"></a></span><h4><span>创建大题目</span></h4></div>
			<div id="modalCont" class="modal_body">
				<form id="paperMiddleForm">
					<input value="${paper.id }" type="hidden" name="paperMiddle.paperId" />
					<table width="100%" border="0">
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">试题类型：</span></td>
							<td width="80%">
								<div class="ml10 mt10">
									<select name="paperMiddle.type" id="paperMiddleType" style="width:380px;" onchange="paperMiddleTypechange(this)">
											<option value="0">-- 选择试题类型--</option>
											<option value="1">单选题</option>
											<option value="2">多选题</option>
											<option value="3">判断题</option>
											<option value="4">材料题</option>
											<option value="5">不定项选择</option>
											<option value="6">主观题</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">大题目名称：</span></td>
							<td width="80%"><div class="ml10 mt10"><input id="paperMiddle_name" type="text" value="" name="paperMiddle.name" style="width: 140px;" /></div></td>
						</tr>
						
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">内含小题数：</span></td>
							<td width="80%"><div class="ml10 mt10"><input type="text" id="paperMiddle_num"  value="1" name="paperMiddle.num" style="width: 140px;" /></div></td>
						</tr>
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">每题：</span></td>
							<td width="80%"><div class="ml10 mt10"><input type="text" id="paperMiddle_score" value="1" name="paperMiddle.score" style="width: 140px;" /><span class="fsize14 c_666">&nbsp;&nbsp;分</span></div></td>
						</tr>
						
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">大题说明：</span></td>
							<td width="80%"><div class="ml10 mt10"><textarea style="width:380px;" id="paperMiddle_title" name="paperMiddle.title"></textarea></div></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal_foot clearfix"><button class="btn btn-success" onclick="addPaperMiddle()">确定</button><button class="btn ml10 cancelBtn" onclick="$('#createMainQst').hide()">取消</button></div>
		</div>
	</div>
	</div>
	<div id="updateMainQst"></div>
</body>
</html>
