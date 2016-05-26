<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试题列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>

<script type="text/javascript">




//切换专业时操作
function subjectOnChange(subjectId) {
	
	$("#subjectId").val(subjectId);
	var subjectName = $("#subjectSelect").find("option:selected").text();
	$("#subjectNameBtn").val(subjectName);
	$("#pointNameBtn").val("");
	$("#pointId").val("");
	loadpointtree(subjectId);//加载考点树
}


function subject_showZtree(){
	$("#dissubjectztree").show();
}
function subject_closetree(){
	$("#dissubjectztree").hide();
}
//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	subject_closetree();
	$("#subjectId").val(0);
	$("#subjectName").val("");
	point_cleantreevalue();
	$("point_ztreedemo").html("");
}
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
		onClick: point_treeOnclick
	}
};

function point_treeOnclick(e,treeId, treeNode) {
	$("#pointId").val(treeNode.id);
	$("#pointName").val(treeNode.name);
	point_closetree();
}

function point_showZtree(){
	$("#dispointztree").show();
}
function point_closetree(){
	$("#dispointztree").hide();
}
function point_cleantreevalue(){
	point_closetree();
	$("#pointId").val(0);
	$("#pointName").val("");
}

// point ztree end


$().ready(function() {
	//初始化显示查询条件
	$("#qstType").val('${queryQuestion.qstType}');
	$("#level").val('${queryQuestion.level}');
	var qsid='${queryQuestion.id}';
	var professionalId = '${queryQuestion.professionalId}';
	if(professionalId>0){
		$("#professionalSelect").val('${queryQuestion.professionalId}');
		getSubjectByProId(professionalId);
		$("#subjectSelect").val('${queryQuestion.subjectId}');
		$("#subjectLi").show();
	}
	
	if(qsid!='' && qsid!='0'){
		$("#id").val(qsid);
	}
	//显示选中的专业和考点
	if($("#subjectSelect").val()!=0 && $("#subjectSelect").val()!=""&&$("#subjectSelect").val()!=null){
			//如果初始化了专业再初始化考点树
			loadpointtree($("#subjectSelect").val());
			if($("#pointId").val()!=0 && $("#pointId").val()!=""){
				var point_zTree = $.fn.zTree.getZTreeObj("point_ztreedemo");
				var pointNode = point_zTree.getNodeByParam("id",$("#pointId").val(), null);
				if(pointNode!=null){
					$("#pointName").val(pointNode.name);
				}
			}
	}
})

/*
 * 加载树
 */
function loadpointtree(subjectId){
	point_closetree();
	$("#point_ztreedemo").html("");
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/point/queryPointListByPIdAndSubjectId",
		data:{"point.subjectId":subjectId},
		async:false,
		success:function(result){
			if(result.entity!=null&&result.entity.length>0){
				$.fn.zTree.init($("#point_ztreedemo"), point_setting, result.entity);
			}else{
				$("#point_ztreedemo").html("该专业下未录入考点");
			}
		}
	});
}

/**
 * 删除试题操作
 *delFalg等于-1的时是左边批量删除,其他为右边单个删除的试题id
 */
function delQuestionListBatch(delFalg){
	var str ="";
	if(delFalg==-1){
		var ischeck=false;
		$(".questionIds").each(function(){
			if($(this).prop("checked")){
				str+=$(this).attr("id")+",";
				ischeck=true;
			}
		});
		if(!ischeck){
			alert("未选中试题");
			return;
		}
	}else{
		str = delFalg;
	}
	if(!confirm("确认删除试题")){
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/quest/delQuestionListBatch",
		data:{"questionIds":str},
		async:false,
		success:function(result){
			if(result.success==true){
				location.reload();
			}
		}
	});
}
//根据专业id查询科目
function getSubjectByProId(obj){
	if(obj!=0){
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
						str +='<option value="'+subjectList[i].subjectId+'"">'+subjectList[i].subjectName+'-'+subjectList[i].subjectId+'</option>';
					}
					$("#subjectSelect").html(str);
					$("#subjectLi").show();
					loadpointtree($("#subjectSelect").val());
				}
			}
		});
	}else{
		$("#subjectLi").hide();
		$("#subjectSelect").html("");
	}
	
}
//查询按钮
function query(){
	if($("#id").val()!='' && $("#id").val()!=0){
		$("#idHidden").val($("#id").val());
		//$("#subjectId").val(0);
		$("#pointId").val(0);
		$("#qstType").val(0);
		$("#queryQuestion_flag").val("");
		$("#level").val(0);
	}else{
		//alert("2:"+$("#searchForm").serialize());
		$("#idHidden").val(0);
	}
	goPage(1);
}
 
</script>
</head>
<body >
<form action="${ctx}/admin/quest/toQuestionList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input name="queryQuestion.pointId" id="pointId" type="hidden" value="${queryQuestion.pointId}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>试题列表</span> </h4>
</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
				<div class="capHead">
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>试题id：</font></span>
								<input type="text"  id="id"  onkeyup="value=value.replace(/[^\d]/g,'')" />
								<input type="hidden" value="0" id="idHidden" name="queryQuestion.id"/>
							</li>
							<li>
								<span class="ddTitle"><font>内容：</font></span>
								<input type="text"  id="qstContent" value="${queryQuestion.qstContent}"  name="queryQuestion.qstContent" />
							</li>
							<li><span class="ddTitle"><font>试题类型：</font></span>
								<select name="queryQuestion.qstType" id="qstType">
									<option value="0">--全部--</option>
									<option value="1">单选题</option>
									<option value="2">多选题</option>
									<option value="3">判断题</option>
									<option value="5">不定项题</option>
									<option value="6">主观题</option>
								</select>
							</li>
							<li>
								<span class="ddTitle"><font>试题难度：</font></span>
								<select name="queryQuestion.level" id="level">
								<option value="0">--请选择--</option>
								<option value="1">一级</option>
								<option value="2">二级</option>
								<option value="3">三级</option>
							</select>
							</li>
							<li style="text-align: center;">
								<input class="btn btn-danger ml10" type="button" onclick="query()" value="查询">
							</li>
						</ul>
						
					</div>
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>选择项目分类：</font></span>
								<select name="queryQuestion.professionalId" id="professionalSelect" onchange="getSubjectByProId(this.value)">
									<option value="0">请选择</option>
									<c:forEach items="${professionalList}" var="professional">
										<option value="${professional.professionalId}">${professional.professionalName}</option>
									</c:forEach>
								</select>
							</li>
							<li id="subjectLi" style="display: none;">
									<span class="ddTitle"><font>科目：</font></span>
									<select id="subjectSelect" name="queryQuestion.subjectId" onchange="subjectOnChange(this.value)" >
									</select>
							</li>
							<li>
								<span class="ddTitle"><font>选择考点：</font></span>
								<input type="text" name="pointName" value=""  id="pointName" class="ml10" onclick="point_showZtree()"></input>
								<div id="dispointztree" style="display: none;padding: 5px 0 0 132px;">
									<input  type="button" onclick="point_cleantreevalue()" value="清空" class="btn smallbtn btn-y"/> 
									<input  type="button" onclick="point_closetree()" class="btn smallbtn btn-y" value="关闭"/>
									<div id="point_ztreedemo"  class="ml10 ztree" >请选择专业加载考点</div>
								</div>
								
							</li>
							<li>
								<span class="ddTitle"><font>批量导入标识：</font></span>
								<input type="text" name="queryQuestion.flag" value="${queryQuestion.flag}" id="queryQuestion_flag" />
							</li>
						</ul>
					</div>
					<div class="w50pre fl" style="text-align: center;">
						<ul class="ddBar">
							
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
						<span><a href="${ctx}/admin/quest/toAddQuestion" title="新建"><em class="icon14 new">&nbsp;</em>新建试题</a></span>
						<span class="ml10"><a href="javascript:delQuestionListBatch(-1);" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span>
						<span class="ml10"><a href="${ctx}/admin/quest/batch" title="excel批量导入试题"><em class="icon14 new">&nbsp;</em>excel批量导入试题</a></span>
						<span class="ml10"><a href="${ctx}/static/common/admin/question.xls" title="excel试题模板"><em class="icon14 new">&nbsp;</em>excel试题模板下载</a></span>
						<span class="ml10"><a href="${ctx}/admin/quest/batchword" title="word批量导入试题"><em class="icon14 new">&nbsp;</em>word批量导入试题</a></span>
						<span class="ml10"><a href="${ctx}/static/common/admin/question.doc" title="word试题模板"><em class="icon14 new">&nbsp;</em>word试题模板下载</a></span>
					</p>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="10%"><span>ID</span></th>
					<th  width="30%"><span>试题内容</span></th>
					<th><span>专业</span></th>
					<th><span>考点</span></th>
					<th><span>正确选项</span></th>
					<th><span>试题类型</span></th>
					<th><span>试题难度</span></th>
					<th><span>添加时间</span></th>
					<th><span>标识</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${questionList.size()>0}">
			<c:forEach  items="${questionList}" var="trquestion" >
				<tr>
					<td><input type="checkbox" class="questionIds" id="${trquestion.id }"/>&nbsp;${trquestion.id }</td>
					<td>${trquestion.shortQstContent }</td>
					<td>${trquestion.subjectName }</td>
					<td>${trquestion.pointName }</td>
					<td>${trquestion.isAsr }</td>
					<td>
					<c:if test="${trquestion.qstType==1}">单选题</c:if>
					<c:if test="${trquestion.qstType==2}">多选题</c:if>
					<c:if test="${trquestion.qstType==3}">判断题</c:if>
					<c:if test="${trquestion.qstType==4}">材料题</c:if>
					<c:if test="${trquestion.qstType==5}">不定项题</c:if>
					<c:if test="${trquestion.qstType==6}">主观题</c:if>
					</td>
					<td>
					<c:if test="${trquestion.level==1}">一级</c:if>
					<c:if test="${trquestion.level==2}">二级</c:if>
					<c:if test="${trquestion.level==3}">三级</c:if>
					</td>		
					<td><fmt:formatDate value="${trquestion.addTime}"  pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
					<td>${trquestion.flag}</td>	
					<td class="c_666 czBtn" align="center">
						<a href="${ctx}/admin/quest/lookQuestion?queryQuestion.id=${trquestion.id }" title="查看" class="btn smallbtn btn-y">查看</a>
						<a href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${trquestion.id }" title="修改" class="btn smallbtn btn-y">修改</a>
						<a href="javascript:delQuestionListBatch(${trquestion.id })" title="删除" class="btn smallbtn btn-y">删除</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${questionList.size()==0}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有试题数据！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
