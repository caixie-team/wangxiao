<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改文库</title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/weixin/weixin.js?v=${v}"></script>

<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
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

//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
	hideSubjectMenu();
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	hideSubjectMenu();
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function showSubjectMenu() {
	var cityObj = $("#subjectNameBtn");
	var cityOffset = $("#subjectNameBtn").offset();
	$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideSubjectMenu() {
	$("#subjectmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
		hideSubjectMenu();
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
});
		$().ready(function() {
 	       $("#fileupload").uploadify({
 	    	   'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
 	           'method' : 'GET', 
 	           'script'  :'<%=uploadSwfUrl%>',
 	           'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"library"},
 	           'queueID':'fileQueue',
 	           'fileDataName':'fileupload',
               'auto':true,
               'multi':false,
               'hideButton':false,
               'buttonText':'Browse',
               'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
               'width':105,
               'simUploadLimit' : 3,
               'sizeLimit'      : 512000,
               'queueSizeLimit' : 2,
               'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
               'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp;',
               'folder' : '/upload',
               'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
                onSelect:function(event, queueID, fileObj){$('#fileupload').uploadifyUpload();
                	fileuploadIndex=1;
	               	$("#fileQueue").html("");
	               	if(fileObj.size>512000){
	               		alert("文件太大最大上传512kb");
	               		return;
	               	}
               	},
               onComplete: function (event, queueID, fileObj, response, data){ 
              		$("#imgpic").attr("src","<%=staticImageServer%>"+response);
              		$("#imgpic").show();
					$("#imageUrl").val(response);
				},
				onError : function(event, queueID,fileObj, errorObj) {
					$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
				}
			});
 	       
 	      $("#fileuploadPDF").uploadify({
 	    	 'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
	           'method' : 'GET', 
	           'script'  :'<%=uploadServerUrl%>/pdf2swf',
	           'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"toPDF","flag":"pdf"},
	           'queueID':'fileQueuePDF',
	           'fileDataName':'fileupload',
             'auto':true,
             'multi':false,
             'hideButton':false,
             'buttonText':'Browse',
             'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
             'width':105,
             'simUploadLimit' : 3,
             'sizeLimit'      : 512000000,
             'queueSizeLimit' : 2,
             'fileDesc'       : '支持格式:pdf/ppt/xls/doc',
             'fileExt'        : '*.pdf;*.ppt;*.xls;*.doc;',
             'folder' : '/upload',
             'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
              onSelect:function(event, queueID, fileObj){
            	$('#fileuploadPDF').uploadifyUpload();
              	fileuploadIndex=1;
             	$("#fileQueuePDF").html("");
             	if(fileObj.size>512000000){
             		alert("文件太大最大上传512M");
             		return;
             	}
             },
             onComplete: function (event, queueID, fileObj, response, data){ 
            	 if(response=='pdfnotexists'){
            		 $("#messagePDF").html("pdf文件不存在");
 				}else if(response=='swfexists'){
 					 $("#messagePDF").html("已经存在不需要转换");
 				}else if(response==null){
 					 $("#messagePDF").html("转换错误");
 				}else{
            	 	$("#messagePDF").html("上传成功");
 				}
				$("#urlStr").val(response);
			},
			onError : function(event, queueID,fileObj, errorObj) {
				$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
			}
		});
 	   
		});      
 	   
		
		function updateSubmit(){
			if($("#subjectId").val()==null||$("#subjectId").val()==0){
				alert("请选择专业");
				return;
			}
			if($("#libraryName").val()==null||$("#libraryName").val()==''){
				alert("请填写名称");
				return;
			}
			if($("#intro").val()==null||$("#intro").val()==''){
				alert("请填写简介");
				return;
			}
			
			if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
				alert("请上传封面");
				return;
			}
			if($("#urlStr").val()==null||$("#urlStr").val()==''){
				alert("请上传PDF或PIC");
				return;
			}
			
			$("#updateForm").submit();
		}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>文库管理</span> &gt; <span>修改文库</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/library/update" method="post" id="updateForm">
		<input type="hidden" name="library.id" value="${library.id}"/>
		<input type="hidden" name="library.imgUrl" id="imageUrl" value="${library.imgUrl }"/>
		<input type="hidden" name="library.type"  value="${library.type }"/>
		<input type="hidden" name="library.subjectId" id="subjectId" value="${library.subjectId }"/>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>文库基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;所属项目专业 </td>
					<td>
						<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
						<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;文库名称 </td>
					<td>
						<input type="text" name="library.name" value="${library.name}" id="libraryName" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;简介 </td>
					<td>
						<textarea rows="5" cols="70" name="library.intro" id="intro" maxlength="200" class="required ">${library.intro}</textarea>
					</td>
				</tr>
				
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;浏览次数</td>
					<td>
						<input type="text" name="library.browseNum" id="browseNum"  class="required number" value="${library.browseNum}" />
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;封面</td>
					<td><input type="file" id="fileupload" style="float: left" />
						<div id="fileQueue" style="margin-top: 30px; border: 0px;"></div> 
						<span style="float: left;"> 
							<img src="<%=staticImageServer%>${library.imgUrl}" alt="" width="300px" height="240px" style=" padding-left: 80px" id="imgpic" />
						</span>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;PDF转SWF</td>
					<td>
                        <p>
                            <input type="text" style="width: 500px;" name="library.pdfUrl" id="urlStr" value="${library.pdfUrl }" />(可直接输入地址)
                        </p>
                        <input type="file" id="fileuploadPDF" style="float: left" />
						<div id="fileQueuePDF" style="margin-top: 30px; border: 0px;"></div> 
						<div id="messagePDF" style="margin-top: 30px; border: 0px"></div>
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<!-- /tab4 end -->
</body>
</html>
