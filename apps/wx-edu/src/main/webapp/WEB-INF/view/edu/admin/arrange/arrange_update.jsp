<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改考试任务</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	 queryUser();//查询员工
	 queryExam();//查询试卷
})
//选择员工
function showNewEmployees(){
	window.open('${ctx}/admin/user/groupList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1250,height=600');
}
//选择试卷
function showNewExam(){
	window.open('${ctx}/admin/arrange/getArrangeExamList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1250,height=600');
}
//添加员工
function addnewUserId(newUcIdArr){
	var groupIds = $("#ucIds").val().split(",");
	groupIds = groupIds.concat(newUcIdArr);  
	groupIds = groupIds.unique();
	$("#ucIds").val(groupIds);
	queryUser();
}

//查找员工
function queryUser(){
	var ids = $("#ucIds").val();
	if(ids!=null&&ids!=""){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${ctx}/admin/user/querybyIds",
			data : {
				"ids" : ids
			},
			async : false,
			success : function(result) {
				if (result.success == true) {
					var str = "";
						str+='<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
					var userList = result.entity;
					for(var i=0; i < userList.length;i++){
						var uc = userList[i];
						str+='<button class="am-btn am-btn-default ml10 mt10" onclick="delUsers('+uc.id+')" type="button">'+uc.nickname+'<a onclick="delUsers('+uc.id+')" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
					}
					str+='</div></div>';
					$("#uchtml").html(str);
				} 
			}
		});
	}else{
		$("#uchtml").html("");
	}
}

//删除员工
function delUsers(id){
	var ucIds = $("#ucIds").val();
	var pattern = id+"";
	ucIds = ucIds.replace(new RegExp(pattern), "");
	ucIds = ucIds.split(",").unique();
	$("#ucIds").val(ucIds);
	queryUser();
}
//清除全部员工
function clearEmployees(){
	$("#ucIds").val("");
	$("#uchtml").html("");
}
//添加试卷
function addnewExamId(newEmIdArr){
	//var examIds = $("#emIds").val().split(",");
	var examIds = "";
	examIds = examIds.concat(newEmIdArr);  
	//examIds = examIds.unique();
	$("#emIds").val(examIds);
	queryExam();
}
//调去试卷集合
var myArrayMoveStock = new Array();
function addnewExamName(myArrayMoveStock){
	var emNames = $("#emNames").val().split(",");
	emNames = emNames.concat(myArrayMoveStock);  
	emNames = emNames.unique();
	$("#emNames").val(emNames);
}
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}
//查找试卷
function queryExam(){
	var id = $("#emIds").val();
	if(id!=null&&id!=""){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${ctx}/admin/arrange/queryByExamIds",
			data : {
				"id" : id
			},
			async : false,
			success : function(result) {
				if (result.success == true) {
					var str = "";
						str+='<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
					var names="";
					var examJson = result.entity.entity;
					for(var i=0; i < examJson.length;i++){
						var em = examJson[i];
						str+='<button class="am-btn am-btn-default ml10 mt10" onclick="delExam('+em.id+')" type="button">'+em.name+'<a onclick="delExam('+em.id+')" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
						var emNames=$("#emNames").val(em.name);
					 	//遍历试卷获取名称
						emNames.each(function() {
							toParentsValue($(this).val());
						});
						addnewExamName(myArrayMoveStock);
					}
					str+='</div></div>';
					$("#emhtml").html(str);
				} 
			}
		});
	}else{
		$("#emhtml").html("");
	}
}
//删除讲师
function delExam(id){
	var emIds = $("#emIds").val();
	var pattern = id+"";
	emIds = emIds.replace(new RegExp(pattern), "");
	emIds = emIds.split(",").unique();
	$("#emIds").val(emIds);
	queryExam();
}
//清除全部讲师
function clearExam(){
	$("#emIds").val("");
	$("#emhtml").html("");
}
//修改任务
function submitUpdateArrange(status){
	$("#status").val(status);
	var type=${arrange.type}//类型
	if(isEmpty($("#examName").val())){
		dialogFun("修改考试任务","请填写考试名称",0);
		return;
	}
	if(isEmpty($("#emIds").val())){
		dialogFun("修改考试任务","请添加试卷",0);
		return;
	}
	if(type==1){//岗位
		var groups ='';
	    $("input[name='groupId']:checked").each(function () {
	        groups +=this.value +",";
	    });
	    $("#groupIds").val(groups);
	    if(isEmpty($("#groupIds").val())){
			dialogFun("修改考试任务","请选择所属岗位",0);
			return;
		}
	}else if(type==0){//个人
		if(isEmpty($("#ucIds").val())){
			dialogFun("修改考试任务","请添加员工",0);
			return;
		}
	}
	var beginTimeStr=$("#beginTime").val();
	var endTimeStr=$("#endTime").val();
	if(isEmpty(beginTimeStr)){
		dialogFun("修改考试任务","请选择考试开始时间",0);
		return;
	}
	if(isEmpty(endTimeStr)){
		dialogFun("修改考试任务","请选择考试结束时间",0);
		return;
	}
	var current=new Date();
	var beginTime=new Date(beginTimeStr.replace(/\-/g,'/'));
	var endTime=new Date(endTimeStr.replace(/\-/g,'/'));
	if(beginTime<current){
		dialogFun("修改考试任务","考试开始时间应大于当前时间",0);
		return;
	}
	if(endTime<current){
		dialogFun("修改考试任务","考试结束时间应大于当前时间",0);
		return;
	}
	if(beginTime>=endTime){
		dialogFun("修改考试任务","考试结束时间应大于考试开始时间",0);
		return;
	}
	$("#openarrange").submit();
}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">考试管理</strong> /<small>编辑考试任务</small>
		</div>
	</div>
	<hr>
	<div class="mt20">
	<div class="am-tabs">
	<ul class="am-tabs-nav am-nav am-nav-tabs">
		<li class="am-active"><a href="#tab1">基本信息</a></li>
	</ul>
	<div class="am-tabs-bd">
		<div id="tab1" class="am-tab-panel am-fade am-active am-in">
			<form id="openarrange" action="${ctx}/admin/arrange/updateArrange" class="am-form am-form-inline"  data-am-validator>
				<input id="ucIds" type="hidden" name="arrange.userIds" value="${arrange.userIds }" />
				<input id="emIds" type="hidden" name="arrange.examIds" value="${arrange.examIds }" />
				<input id="emNames" type="hidden" name="arrange.examNames" value="" />
				<input type="hidden" value="${arrange.id}" name="arrange.id" />
				<input name="arrange.groupIds" type="hidden" id="groupIds">
				<input id="type" type="hidden" name="arrange.type" value="${arrange.type}" />
				<input id="status" type="hidden" name="arrange.status"/>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">考试名称</div>
					<div class="am-u-sm-8 am-u-md-6">
						<input required name="arrange.name" id="examName" value="${arrange.name }" type="text" class="am-input-sm">
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
				</div>
		        <div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">考试类型</div>
					<div class="am-u-sm-8 am-u-md-6">
						<select disabled class="am-input-sm">
							<c:if test="${arrange.type==0}">
								<option value="0">个人</option>
							</c:if>
							<c:if test="${arrange.type==1}">
								<option value="1">岗位</option>
							</c:if>
						</select>
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
		        </div>
	         	<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">是否重复考试</div>
					<div class="am-u-sm-8 am-u-md-6">
						<div class="am-btn-group am-text-right">
							<label class="am-radio am-radio-inline">
								<input type="radio"  value="1" name="arrange.isRepeat" data-am-ucheck <c:if test='${arrange.isRepeat==1}'>checked</c:if>/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可重复
							</label>
							<label class="am-radio am-radio-inline">
								<input type="radio" value="0" name="arrange.isRepeat" data-am-ucheck <c:if test='${arrange.isRepeat==0}'>checked</c:if>/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不可重复
							</label>
						</div>
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
			  	</div>
				<c:if test="${arrange.type==0 }">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">添加员工</div>
						<div class="am-u-sm-8 am-u-md-6">
							<div id="uchtml"></div>
							<a href="javascript:showNewEmployees()" title="添加员工" class="am-btn am-btn-primary">添加员工</a>
							<a href="javascript:clearEmployees()" title="清空" class="am-btn am-btn-primary">清空</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
					</div>
				</c:if>
				<c:if test="${arrange.type==1}">
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">所属岗位</div>
						<div class="am-u-sm-8 am-u-md-4 am-form-group">
							<c:forEach var="group" items="${userGroupList}" varStatus="count">
								<div class="am-u-sm-6 am-u-md-6 am-u-end">
									<c:if test="${group.check==1}">
										<label class="am-checkbox">
											<input type="checkbox" data-am-ucheck checked name="groupId" value="${group.id}" />&nbsp;&nbsp;&nbsp;&nbsp; ${group.name}
										</label>
									</c:if>
									<c:if test="${group.check!=1}">
										<label class="am-checkbox">
											<input type="checkbox" data-am-ucheck name="groupId" value="${group.id}" />&nbsp;&nbsp;&nbsp;&nbsp; ${group.name}
										</label>
									</c:if>
								</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
					</div>
				</c:if>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">添加试卷</div>
					<div class="am-u-sm-8 am-u-md-6">
						<div id="emhtml"></div>
						<a href="javascript:showNewExam()" title="添加试卷" class="am-btn am-btn-primary">添加试卷</a>
						<a href="javascript:clearExam()" title="清空" class="am-btn am-btn-primary">清空</a>
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">考试开始时间</div>
					<div class="am-u-sm-8 am-u-md-6">
						<div class="am-form-group am-form-icon">
							<i class="am-icon-calendar"></i>
							<input type="text" name="arrange.beginTime" value="<fmt:formatDate value="${arrange.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beginTime" required  readonly placeholder="开始时间" class="form-datetime-lang am-form-field">
						</div>
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">考试结束时间</div>
					<div class="am-u-sm-8 am-u-md-6">
						<div class="am-form-group am-form-icon">
							<i class="am-icon-calendar"></i>
							<input type="text" name="arrange.endTime" value="<fmt:formatDate value="${arrange.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="endTime" required  readonly placeholder="结束时间" class="form-datetime-lang am-form-field ">
						</div>
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
					<div class="am-u-sm-8 am-u-md-6">
						<button class="am-btn am-btn-danger" type="button" onclick="submitUpdateArrange('NOTRELEASE')">保存</button>
						<button class="am-btn am-btn-danger" type="button" onclick="submitUpdateArrange('RELEASE')">发布</button>
						<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
					</div>
					<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>