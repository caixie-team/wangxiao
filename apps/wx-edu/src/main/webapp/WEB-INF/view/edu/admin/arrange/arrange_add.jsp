<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建考试任务</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<script type="text/javascript">

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
//创建任务
function submitAddArrange(status){
	$("#status").val(status);
	if($("#name").val()==""){
		dialogFun("新建个人考试","请填写考试名称",0);
		return;
	}
	if($("#ucIds").val()==""){
		dialogFun("新建个人考试","请添加员工",0);
		return;
	}
	if($("#emIds").val()==""){
		dialogFun("新建个人考试","请添加试卷",0);
		return;
	}
	var beginTimeStr=$("#beginTime").val();
	var endTimeStr=$("#endTime").val();
	if(beginTimeStr==""){
		dialogFun("新建个人考试","请选择考试开始时间",0);
		return;
	}
	if(endTimeStr==""){
		dialogFun("新建个人考试","请选择考试结束时间",0);
		return;
	}
	var current=new Date();
	var beginTime=new Date(beginTimeStr.replace(/\-/g,'/'));
	var endTime=new Date(endTimeStr.replace(/\-/g,'/'));
	if(beginTime<current){
		dialogFun("新建个人考试","考试开始时间应大于当前时间",0);
		return;
	}
	if(endTime<current){
		dialogFun("新建个人考试","考试结束时间应大于当前时间");
		return;
	}
	if(beginTime>=endTime){
		dialogFun("新建个人考试","考试结束时间应大于考试开始时间",0);
		return;
	}
	$("#openarrange").submit();
}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">考试管理</strong> / <small>新建个人考试</small></div>
	</div>
	<hr/>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form id="openarrange" action="${ctx}/admin/arrange/addArrange" class="am-form am-form-inline"  data-am-validator>
						<input id="ucIds" type="hidden" name="arrange.userIds" value="" />
						<input id="emIds" type="hidden" name="arrange.examIds" value="" />
						<input id="emNames" type="hidden" name="arrange.examNames" value="" />
						<input id="type" type="hidden" name="arrange.type" value="0" />
						<input id="status" type="hidden" name="arrange.status" />
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">考试名称</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input required name="arrange.name" type="text" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">考试类型</div>
							<div class="am-u-sm-8 am-u-md-6">
								<select class="am-form-field" name="" id="" data-am-selected="{btnSize: 'sm'}" style="display: none;">
									<option value="0" selected="selected">个人</option>
								</select>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">是否重复考试</div>
							<div class="am-u-sm-8 am-u-md-6">
								<label class="am-radio">
									<input type="radio"  value="1" name="arrange.isRepeat" data-am-ucheck>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可重复
								</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label class="am-radio">
									<input type="radio" value="0" name="arrange.isRepeat" data-am-ucheck checked>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不可重复
								</label>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">添加员工</div>
							<div class="am-u-sm-8 am-u-md-6">
								<div id="uchtml"></div>
								<a href="javascript:showNewEmployees()" title="添加员工" class="am-btn am-btn-primary">添加员工</a>
								<a href="javascript:clearEmployees()" title="清空" class="am-btn am-btn-primary">清空</a>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
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
									<input type="text" name="arrange.beginTime" id="beginTime" required  readonly placeholder="开始时间" class="form-datetime-lang am-form-field am-input-sm">
								</div>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">考试结束时间</div>
							<div class="am-u-sm-8 am-u-md-6">
								<div class="am-form-group am-form-icon">
									<i class="am-icon-calendar"></i>
									<input type="text" name="arrange.endTime" id="endTime" required  readonly placeholder="结束时间" class="form-datetime-lang am-form-field am-input-sm">
								</div>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-6">
								<button class="am-btn am-btn-danger" type="button" onclick="submitAddArrange('NOTRELEASE')">保存</button>
								<button class="am-btn am-btn-danger" type="tutton" onclick="submitAddArrange('RELEASE')">发布</button>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>