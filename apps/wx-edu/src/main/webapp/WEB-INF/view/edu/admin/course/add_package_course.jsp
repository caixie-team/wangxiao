<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建套餐</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>

<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">

//subject ztree start
var subject_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false
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
			title:'subjectName'
		}
	},
	callback: {
		onClick: subject_treeOnclick
	}
};
var subject_treedata=${subjectList};




var class_type_setting = {
        view:{
            showLine: true,
            showIcon: true,
            selectedMulti: false,
            dblClickExpand: false
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
                title:'subjectName'
            }
        },	
        callback: {
            onClick: class_type_treeOnclick
        }
    };
    var class_type_treedata=${classTypeList};

    
    
    //班级类型切换操作
    function class_type_treeOnclick(e,treeId, treeNode) {
        hideSubjectMenu(3);
        $("#class_type_id").val(treeNode.subjectId);
        $("#ClassTypeBtn").val(treeNode.subjectName);
    } 
    
    
//检索课程
function checkCourse(typeId){
	$("#offlineClassTypeId").val(typeId);
	$("#offlineClassId").html("");
    $.ajax({
        type:'post',
        dataType:'json',
        url:baselocation+'/admin/cou/toGetOfflineClass/'+typeId,
        async:false,
        success:function (result){
        	var message ='';
        	var entity = result.entity;
        	if(result.success){
        		if(entity.length>0){
	        		for(var i = 0;i<entity.length;i++){
        			message +='<option value="'+entity[i].ID+'">'+entity[i].PROJECTNAME+'</option>';
	        		}
        		}else{
        			message ='<option value="">无课程信息<option/>'
        		}
        		$("#offlineClassId").html(message);
        	}
        }
    });
}

function  getClassId(classId){
	$("#offlineClassId").val(classId);
}

var type_subject_setting = {
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false,
			dblClickExpand: false
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
				title:'subjectName'
			}
		},
		callback: {
			onClick: type_subject_treeOnclick
		}
	};
var type_subject_treedata=${type_subjectList};


//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
	hideSubjectMenu(1);
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}


//切换专业时操作
function type_subject_treeOnclick(e,treeId, treeNode) {
	hideSubjectMenu(2);
	$("#typeSubjectId").val(treeNode.subjectId);
	$("#typeSubjectNameBtn").val(treeNode.subjectName);
}


function switchshowType(value){
	if(value=='ONLINE'){
		$(".offlineClass").hide();
	}else if(value=='UPLINE'){
		$(".offlineClass").show();
	}
}



//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	hideSubjectMenu(1);
	hideSubjectMenu(2);
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function showSubjectMenu(type) {
	if(type==1){
		var cityObj = $("#subjectNameBtn");
		var cityOffset = $("#subjectNameBtn").offset();
		$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}else if(type==2){
		var cityObj = $("#typeSubjectNameBtn");
		var cityOffset = $("#typeSubjectNameBtn").offset();
		$("#typeSubjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", typeonBodyDown);
	}else if(type==3){
        var cityObj = $("#ClassTypeBtn");
        var cityOffset = $("#ClassTypeBtn").offset();
        $("#ClassTypemenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", classTypeBodyDown);
    }
}


function classTypeBodyDown(event) {
    if (!(event.target.id == "ClassTypeBtn" || event.target.id == "ClassTypemenuContent" || $(event.target).parents("#ClassTypemenuContent").length>0)) {
        hideSubjectMenu(3);
    }
}


function hideSubjectMenu(type) {
 	if(type==1){
	$("#subjectmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
	}else if(type==2){
		$("#typeSubjectmenuContent").fadeOut("fast");
		$("body").unbind("mousedown", typeonBodyDown);
	}else if(type==3){
        $("#ClassTypemenuContent").fadeOut("fast");
        $("body").unbind("mousedown", classTypeBodyDown)
    }
 	
}
function showSubjectMenu(type) {
	if(type==1){
		var cityObj = $("#subjectNameBtn");
		var cityOffset = $("#subjectNameBtn").offset();
		$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}else if(type==2){
		var cityObj = $("#typeSubjectNameBtn");
		var cityOffset = $("#typeSubjectNameBtn").offset();
		$("#typeSubjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", typeonBodyDown);
	}else if(type==3){
        var cityObj = $("#ClassTypeBtn");
        var cityOffset = $("#ClassTypeBtn").offset();
        $("#ClassTypemenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", classTypeBodyDown);
    }
}
function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
		hideSubjectMenu(1);
	}
}

function typeonBodyDown(event) {
	if (!(event.target.id == "typeSubjectNameBtn" || event.target.id == "typeSubjectmenuContent" || $(event.target).parents("#typeSubjectmenuContent").length>0)) {
		 hideSubjectMenu(2); 
	}
}

function classTypeBodyDown(event) {
    if (!(event.target.id == "ClassTypeBtn" || event.target.id == "ClassTypemenuContent" || $(event.target).parents("#ClassTypemenuContent").length>0)) {
        hideSubjectMenu(3);
    }
}


$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	//专业名称显示
	if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
		if(treeNode!=null){
			$("#subjectNameBtn").val(treeNode.subjectName);
		}
	}
	
	
	$.fn.zTree.init($("#type_subject_ztreedemo"), type_subject_setting, type_subject_treedata);
	//专业名称显示
	if($("#typeSubjectId").val()!="" && $("#typeSubjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("type_subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("typeSubjectId",$("#typeSubjectId").val(),null);
		if(treeNode!=null){
			$("#typeSubjectNameBtn").val(treeNode.subjectName);
		}
	}
	
    $.fn.zTree.init($("#class_type_ztreedemo"), class_type_setting, class_type_treedata);
    //专业名称显示
    if($("#class_type_id").val()!="" && $("#class_type_id").val()!=0){
        var zTree = $.fn.zTree.getZTreeObj("class_type_ztreedemo");
        var treeNode=zTree.getNodeByParam("class_type_id",$("#class_type_id").val(),null);
        if(treeNode!=null){
            $("#ClassTypeBtn").val(treeNode.subjectName);
        }
    }
});
//类型change
function sellTypeChange(){
    $("#sellType").change(function() {
        $(".showLive").hide();
        if ($(this).val() == 'LIVE') {
            $(".showLive").show();
        }
    });
    $("#sellType").change();
}
	$(function() {

		$(".offlineClass").hide();
        var sellType =getParameter("sellType");

        if(sellType!=null && sellType !=""){
            if(sellType=='COURSE' || sellType=='PACKAGE' ||sellType=='LIVE'){
                $("#sellType").val(sellType);
            }else{
                $("#sellType").val('COURSE');
            }
        }

        //类型
        sellTypeChange();
        //时间插件
        $("#liveBeginTime,#liveEndTime,#lectureTime").datetimepicker(
                {regional:"zh-CN",
                	changeYear: true,
                    changeMonth: true,
                    dateFormat:"yy-mm-dd ",
                    timeFormat: "HH:mm:ss"
                });

		datepicker("#loseAbsTime");
		$("#subjectIdOne").change();
		$("#addCourse").validate();
		querytecher();
		losetypeShow();
		$("#losetype").change();
		
		$("#fileupload").uploadify({
			'uploader' : '${ctximg}/static/common/uploadify/uploadify.swf',
		  'script'  :'<%=uploadServerUrl%>/goswf',
                  'scriptData':{"base":"mavendemo","param":"course"},
			'queueID' : 'fileQueue',
			'fileDataName' : 'fileupload',
			'auto' : true,
			'multi' : false,
			'hideButton' : false,
			'buttonText' : 'Browse',
			'buttonImg' : '${ctximg}/static/common/uploadify/liulan.png',
			'width' : 105,
			'simUploadLimit' : 3,
			'sizeLimit' : 51200000,
			'queueSizeLimit' : 2,
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'folder' : '/upload',
			'cancelImg' : '${ctximg}/static/common/uploadify/cancel.png',
			onSelect : function(event, queueID,
					fileObj) {
				// $('#fileupload').uploadifyUpload();
				fileuploadIndex = 1;
				$("#fileQueue").html("");
				if (fileObj.size > 51200000) {
					alert("文件太大最大上传51200kb");
					return;
				}

			},
			onComplete : function(event,queueID, fileObj, response,data) {
				$("#imgUrl").attr("src","<%=staticImageServer%>" + response);
								$("#logo").val(response);
								$("#imgUrl").show();
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#fileQueue").html(
							"<br/><font color='red'>"
									+ fileObj.name + "上传失败</font>");
				}
		});
		$("#mobile_fileupload").uploadify({
			'uploader' : '${ctximg}/static/common/uploadify/uploadify.swf',
		 	'script'  :'<%=uploadServerUrl%>/goswf',
	        'scriptData':{"base":"mavendemo","param":"course"},
			'queueID' : 'mobile_fileQueue',
			'fileDataName' : 'fileupload',
			'auto' : true,
			'multi' : false,
			'hideButton' : false,
			'buttonText' : 'Browse',
			'buttonImg' : '${ctximg}/static/common/uploadify/liulan.png',
			'width' : 105,
			'simUploadLimit' : 3,
			'sizeLimit' : 51200000,
			'queueSizeLimit' : 2,
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'folder' : '/upload',
			'cancelImg' : '${ctximg}/static/common/uploadify/cancel.png',
			onSelect : function(event, queueID,fileObj) {
				// $('#fileupload').uploadifyUpload();
				fileuploadIndex = 1;
				$("#mobile_fileQueue").html("");
				if (fileObj.size > 51200000) {
					alert("文件太大最大上传51200kb");
					return;
				}

			},
			onComplete : function(event,
					queueID, fileObj, response,
					data) {
								$("#mobile_imgUrl").attr("src","<%=staticImageServer%>" + response);
								$("#mobile_imgUrl").show();
							},
							onError : function(event, queueID, fileObj,
									errorObj) {
								$("#mobile_fileQueue").html(
										"<br/><font color='red'>"
												+ fileObj.name + "上传失败</font>");
							}
						});

	});
	
	//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
				editor = K.create('textarea[id="context"]',{
					resizeType : 1,
					filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
					allowPreviewEmoticons : false,
					allowUpload : true,//允许上传
					syncType : 'auto',
					width : '620px',
					minWidth : '10px',
					minHeight : '10px',
					height : '300px',
					urlType : 'domain',//absolute
					newlineTag : 'br',//回车换行br|p
					uploadJson : '<%=keImageUploadUrl%>' + '&param=course',// 图片上传路径
					allowFileManager : false,
					afterBlur : function() {
						editor.sync();
					},
					items : [ 'fontname', 'fontsize', '|',
							'forecolor', 'hilitecolor', 'bold',
							'italic', 'underline',
							'removeformat', '|', 'justifyleft',
							'justifycenter', 'justifyright',
							'insertorderedlist',
							'insertunorderedlist', '|',
							'emoticons', 'image', 'link' ],
					afterChange : function() {
					}
				});
			});
	function addCourseFormSubmit() {
        var groups ='';
        $("input[name='groupId']:checked").each(function () {
            groups +=this.value +",";
        });
        $("#groupIds").val(groups);
        
/*         if($("#subjectId").val()=="0"){
			alert("请选择关键词分类");
			return;
		} */
        
/* 		if($("#typeSubjectId").val()=="0"){
			alert("请选择专业");
			return;
		} */
		
/* 		if($("#tcIds").val()==""){
			alert("请添加教师");
			return;
		} */
		
		if($("#showType").val()==''){
			alert("请选择展示类型");
			return;
		}
		if($("#logo").val()==""){
			alert("请上传课程图片");
			return;
		}
		
		if($("#showType").val()=='UPLINE'){
			if($("#offlineClassId").val()==''){
				alert("请选择线下班级");
				return;
			}
		}
		$("#addCourse").submit();
	}

	//时间类型change事件
	function losetypeShow() {
		$("#losetype").change(function() {
			$(".loseTimeShow").hide();
			$(".loseAbsTimeShow").hide();
			if ($(this).val() == 1) {
				$(".loseTimeShow").show();
				$("#loseAbsTime").val('');
			}
			if ($(this).val() == 0) {
				$(".loseAbsTimeShow").show();
				$("#loseTime").val('');
			}
		});
	}
	//选择教师
	function showNewwin(){
		window.open('${ctx}/admin/teacher/selectlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	} 
	function addnewTeacherId(newTcIdArr){
		var tcIds = $("#tcIds").val().split(",");
		tcIds = tcIds.concat(newTcIdArr);  
		tcIds = tcIds.unique();
		$("#tcIds").val(tcIds);
		querytecher();
	}

	function querytecher(){
		var ids = $("#tcIds").val();
		if(ids!=null&&ids!=""){
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${ctx}/admin/teacher/queryByIds",
				data : {
					"ids" : ids
				},
				async : false,
				success : function(result) {
					if (result.success == true) {
						var str = "";
						var teacherList = result.entity;
						for(var i=0;i<teacherList.length;i++){
							var tc = teacherList[i];
							str+='<p style="width:100%;margin: 0 0 0em">'+tc.name+'&nbsp;&nbsp;<a onclick="delTeacher('+tc.id+')" class="btn smallbtn btn-y" href="javascript:void(0)"> 删除</a></p>';
							}
						$("#tchtml").html(str);
					} 
				}
			});
		}else{
			$("#tchtml").html("");
		}
	}
	//删除老师
	function delTeacher(id){
		var tcIds = $("#tcIds").val();
		var pattern = id+"";
		tcIds = tcIds.replace(new RegExp(pattern), "");
		tcIds = tcIds.split(",").unique();
		$("#tcIds").val(tcIds);
		querytecher();
	}
	
	function clearTeacher(){
		$("#tcIds").val("");
		$("#tchtml").html("");
	}
</script>
</head>
<body >
		<form action="${ctx}/admin/cou/addCourse" method="post" id="addCourse">
		<input id="tcIds" type="hidden" name="course.teacherIds" value="" />
			<input name="course.subjectId" id="subjectId" type="hidden" value="0"/>
			<input name="course.typeSubjectId" id="typeSubjectId" type="hidden" value="0"/>
			<input name="course.groupIds" type="hidden" id="groupIds" />
			<input name="course.logo" id="logo" type="hidden" />
			<input name="course.offlineClassTypeId" id="offlineClassTypeId" type="hidden"/>
			<input type="hidden" name="course.sellType" value="PACKAGE"/>
			<input type="hidden" name="course.classTypeId"  id="class_type_id"  value="0" />   
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>套餐管理</span> &gt; <span>新建套餐</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 套餐基本属性 <tt class="c_666 ml20 fsize12">
										（ <font color="red">*</font> &nbsp;为必填项）
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;套餐名称</td>
							<td><input type="text" name="course.name" class="required "  /></td>
						</tr>
                        <c:if test="${salemap.sale.verifyMember=='ON'}">
						<tr>
							<td align="center"> &nbsp;会员类型</td>
							<td>
								<c:forEach items="${memberTypes }" var="memberType">
									<input type="checkbox" name="courseMember" value="${memberType.id }"/>${memberType.title }&nbsp;&nbsp;
								</c:forEach>

							</td>
						</tr>
                        </c:if>
                        <input type="hidden" name="courseMember" value=""/>
						<tr>
							<td width="20%" align="center"><!-- <font color="red">*</font> --> &nbsp;课程关键词分类</td>
							<td width="80%">
								<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showSubjectMenu(1)"/>
								<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
									<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
								</div>
								</td> 
						</tr>
						<tr>
							<td width="20%" align="center"><!-- <font color="red">*</font>  -->&nbsp;专业分类</td>
							<td width="80%">
								<input id="typeSubjectNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showSubjectMenu(2)"/>
								<div id="typeSubjectmenuContent" class="menuContent" style="display:none; position: absolute;">
									<ul id="type_subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
								</div>
								</td> 
						</tr>
						
						<tr>
		                    <td width="20%" align="center">&nbsp;班型分类</td>
		                    <td width="80%"><input id="ClassTypeBtn" type="text" readonly="readonly" value="" style="width: 120px;" onclick="showSubjectMenu(3)" />
		                        <div id="ClassTypemenuContent" class="menuContent" style="display: none; position: absolute;">
		                            <ul id="class_type_ztreedemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		                        </div></td>
            		    </tr>
						
						<tr>
							<td width="20%" align="center">&nbsp;套餐收费</td>
							<td width="80%">
								<input type="radio" value="0" name="course.isPay" checked="checked"/>免费
								<input type="radio" value="1" name="course.isPay"/>收费
							</td>
						</tr>
						<tr>
							<td width="20%" align="center">套餐原价格</td>
							<td width="80%"><input type="text" name="course.sourceprice" class="required number" /></td>
						</tr>

						<tr>
							<td align="center"><font color="red">*</font> &nbsp;套餐销售价格</td>
							<td><input type="text" name="course.currentprice" class="required number" /></td>
						</tr>
						<tr>
							<td width="20%" align="center">讲课时间</td>
							<td width="80%"><input type="text" name="course.lectureTime" id="lectureTime"  readonly="readonly"/></td>
						</tr>
						<tr>
							<td align="center">&nbsp;上课方式</td>
							<td>
								<select name="course.showType" id="showType" onchange="switchshowType(this.value)">
									<option value="ONLINE">线上</option>
									<option value="UPLINE">线下面授</option>
								</select>
							</td>
						</tr>
<%-- 					<tr class="offlineClass">
							<td width="20%" align="center"><font color="red">*</font> &nbsp;线下班类型</td>
							<td width="80%">
							<select onchange="checkCourse(this.value)" >
							<option  value="" >--请选择--</option>	
							<c:forEach var="type" items="${typeList}">
									<option  value="${type.id}">${type.name}</option>
							</c:forEach>
							</select>
							</td>
					</tr>
					<tr class="offlineClass">
							<td width="20%" align="center"><font color="red">*</font> &nbsp;线下班</td>
							<td width="80%">
							<select id="offlineClassId" onchange="getClassId(this.value)" >
							</select>
							</td>
					</tr> --%>
					<tr>
							<td align="center">&nbsp;所属小组(圈子)</td>
							<td>
							<table>
							<tr>
								<c:forEach var="group" items="${userGroupList}" varStatus="count">
									<td>	
									<input  type="checkbox"  name="groupId" value="${group.id}"/> ${group.name}&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<c:if test="${count.count%5==0}">
										</tr><tr>
									</c:if>
								</c:forEach>
							</table>
							</td>
					</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;套餐简介</td>
							<td><textarea rows="2" cols="82" name="course.title" maxlength="200" class="required "></textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;套餐详情</td>
							<td><textarea id="context" rows="5" cols="70" name="course.context" required ></textarea></td>
						</tr>
						<tr>
							<td align="center">上架状态</td>
							<td><select name="course.isavaliable" id="isavaliable">
									<option value="0">上架</option>
									<option value="1">下架</option>
							</select></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;套餐图片(400*300)</td>
							<td>
								<input type="file" id="fileupload" class="vam" />
								<font color="red vam ml10">请上传宽400、高300的图片</font> 
								<div class="ml10 mt10"> 
									<img src="" alt="" width="400" height="300" style="display: none;" id="imgUrl" />
								</div>
								<div id="fileQueue" class="mt10"></div> 
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;微站套餐图片(380*180)</td>
							<td>
								<input type="file" id="mobile_fileupload" class="vam" />
								<font color="red vam ml10">请上传宽380、高180的图片</font> 
								<div class="ml10 mt10"> 
									<img src="" alt="" width="380" height="180" style="display: none;" id="mobile_imgUrl" />
								</div>
								<div id="mobile_fileQueue" class="mt10"></div> 
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;总课时</td>
							<td><input type="text" name="course.lessionnum" class="required number" /></td>
						</tr>
						<tr>
							<td align="center"> &nbsp;有效期类型</td>
							<td><select name="course.losetype" id="losetype">
									<option value="1">按天数</option>
									<option value="0">到期时间</option>
							</select></td>
						</tr>
						<tr class="loseTimeShow">
							<td align="center"><font color="red">*</font> &nbsp;按天数</td>
							<td><input type="text" name="course.loseTime" id="loseTime" class="required number" value="0" />天</td>
						</tr>
						<tr class="loseAbsTimeShow">
							<td align="center"> <font color="red">*</font>&nbsp;到期时间</td>
							<td><input type="text" id="loseAbsTime" readonly="readonly" name="course.loseAbsTime" class="required"/></td>
						</tr>
						<tr>
							<td align="center"> &nbsp;初始化假的销售数量</td>
							<td><input type="text" name="course.pageBuycount" class="required number" value="0" /></td>
						</tr>
						<tr>
							<td align="center">&nbsp;初始化假的浏览数量</td>
							<td><input type="text" name="course.pageViewcount" class="required number" value="0" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="am-btn am-btn-danger" title="提 交" href="javascript:addCourseFormSubmit()">提 交</a> <a
								class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
