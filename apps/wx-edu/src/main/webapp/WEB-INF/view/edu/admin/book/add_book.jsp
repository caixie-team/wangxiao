<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建图书</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
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
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
	$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
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

$(function(){
	$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
})

$(function(){
	$( "#publishTime" ).datepicker(
   		{regional:"zh-CN",
   		changeMonth: true,
   		dateFormat:"yy-mm-dd "
   	});
});

	$(function() {
		datepicker("#publishTime");
		$("#subjectIdOne").change();
		$("#imgButton").uploadify({
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
			'width' : 220,
			'height': 50,
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
				$("#imgpic").attr("src","<%=staticImageServer%>" + response);
								$("#bookImg").val(response);
								$("#imgpic").show();
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#fileQueue").html(
							"<br/><font color='red'>"
									+ fileObj.name + "上传失败</font>");
				}
		});
		$("#previewButton").uploadify({
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
			'width' : 220,
			'height': 50,
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
				$("#previewpic").attr("src","<%=staticImageServer%>" + response);
								$("#bookSmallimg").val(response);
								$("#previewpic").show();
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#fileQueue").html(
							"<br/><font color='red'>"
									+ fileObj.name + "上传失败</font>");
				}
		});
	});
	//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
				editor = KindEditor.create('#bookInfo,#bookContext',{
					resizeType : 1,
					filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
					allowPreviewEmoticons : false,
					allowUpload : true,//允许上传
					syncType : 'auto',
					width : '500px',
					minWidth : '10px',
					minHeight : '10px',
					height : '200px',
					urlType : 'domain',//absolute
					newlineTag : 'br',//回车换行br|p
					uploadJson : '<%=keImageUploadUrl%>' + '&param=book',// 图片上传路径
					allowFileManager : false,
					afterBlur : function() {
						this.sync();
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
		 var number = /^[0-9]+\.{0,1}[0-9]{0,2}$/;
		if($("#mobile").val()==""||$("#mobile").val()==null){
			dialogFun("新建图书 ","请填写图书名称",0);
			return;
		}
		if($("#subjectId").val()=="0"){
			dialogFun("新建图书 ","请选择专业",0);
			return;
		}
		var price = $("#price").val();
		if(!number.test(price)){
    	    dialogFun("新建图书 ","原价只能输入数字且只保留两位小数",0);
    	    return;
	    }
	    if(price==null||price==''){
	    	 dialogFun("新建图书 ","请填写原价",0);
	    	 return;
	    }
	    var nowPrice = $("#nowPrice").val();
	    if(!number.test(nowPrice)){
    	    dialogFun("新建图书 ","现价只能输入数字且只保留两位小数",0);
    	    return;
	    }
	    if(nowPrice==null||nowPrice==''){
	    	 dialogFun("新建图书 ","请填写现价",0);
	    	 return;
	    }
	    var rebatePrice = $("#rebatePrice").val();
	    if(!number.test(rebatePrice)){
    	    dialogFun("新建图书 ","折扣价只能输入数字且只保留两位小数",0);
    	    return;
	    }
	    if(rebatePrice==null||rebatePrice==''){
	    	 dialogFun("新建图书 ","请填写折扣价",0);
	    	 return;
	    }
	    if($("#bookImg").val()==""||$("#bookImg").val()==null){
			dialogFun("新建图书 ","请上传图书封面",0);
			return;
		}
		if ($("#bookSmallimg").val() == ""||$("#bookSmallimg").val()==null){
	        dialogFun("新建图书 ","请选择商品缩略图片",0);
	        return;
	    }
	    var publishTime = $("#publishTime").val();
	    if ((new Date(publishTime.replace(/-/g, "/"))) > (new Date())){
	    	dialogFun("新建图书 ","出版时间不能大于当前日期",0);	
	        return;
	    }
	    if($("#author").val()==""||$("#author").val()==null){
			dialogFun("新建图书 ","请填写作者",0);
			return;
		}
	    if($("#press").val()==""||$("#press").val()==null){
			dialogFun("新建图书 ","请填写出版社",0);
			return;
		}
	    if($("#revision").val()==""||$("#revision").val()==null){
			dialogFun("新建图书 ","请填写版次",0);
			return;
		}
	    var nber = /^[0-9]*$/;
	    if($("#printNum").val()==""||$("#printNum").val()==null){
			dialogFun("新建图书 ","请填写印次",0);
			return;
		}
	    if(!nber.test($("#printNum").val())){
	    	dialogFun("新建图书 ","印次只能输入数字",0);
	    	return;
	    }
	    if($("#pageNum").val()==""||$("#pageNum").val()==null){
			dialogFun("新建图书 ","请填写页数",0);
			return;
		}
	    if(!nber.test($("#pageNum").val())){
	    	dialogFun("新建图书 ","页数只能输入数字",0);
	    	return;
	    }
	    if($("#wordNum").val()==""||$("#wordNum").val()==null){
			dialogFun("新建图书 ","请填写字数",0);
			return;
		}
	    if(!nber.test($("#wordNum").val())){
	    	dialogFun("新建图书 ","字数只能输入数字",0);
	    	return;
	    }
	    if($("#bookPackage").val()==""||$("#bookPackage").val()==null){
			dialogFun("新建图书 ","请填写包装",0);
			return;
		}
	    if($("#pager").val()==""||$("#pager").val()==null){
			dialogFun("新建图书 ","请填写纸张",0);
			return;
		}
	    if($("#weight").val()==""||$("#weight").val()==null){
			dialogFun("新建图书 ","请填写重量xx",0);
			return;
		}
	    if(!number.test($("#weight").val())){
	    	dialogFun("新建图书 ","重量只能输入数字且只保留两位小数",0);
	    	return;
	    }
	    if($("#isbn").val()==""||$("#isbn").val()==null){
			dialogFun("新建图书 ","请填写ISBN",0);
			return;
		}
	    if($("#bookSize").val()==""||$("#bookSize").val()==null){
			dialogFun("新建图书 ","请填写开本",0);
			return;
		}
	    if(!nber.test($("#bookSize").val())){
	    	dialogFun("新建图书 ","开本只能输入数字",0);
	    	return;
	    }
	    if($("#remarks").val()==""||$("#remarks").val()==null){
			dialogFun("新建图书 ","请填写图书注释",0);
			return;
		}
	    if($("#bookInfo").val()==""||$("#bookInfo").val()==null){
			dialogFun("新建图书 ","请填写图书简介",0);
			return;
		}
	    if($("#bookContext").val()==""||$("#bookContext").val()==null){
			dialogFun("新建图书 ","请填写图书目录",0);
			return;
		}
	    if($("#payUrl").val()==""||$("#payUrl").val()==null){
			dialogFun("新建图书 ","请填写支付链接",0);
			return;
		}
		$("#addBook").submit();
	} 
	function disable(){
	    $('input[name="disProperty"]').each(function (){
	    	$(this).prop("checked",false);
	    	$(this).prop("disabled",true);
	    });
	}
	function nodisable(){
	    $('input[name="disProperty"]').each(function (){
	        $(this).prop("checked",false);
	        $(this).prop("disabled",false);
	    });
	}
</script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图书管理</strong> / <small>新建图书</small></div>
</div>
<hr/>
<div class="mt20">
<form action="${ctx}/admin/book/add" method="post" id="addBook"  class="am-form">
			<input type="hidden" name="book.bookImg" id="bookImg" value=""/>
            <input type="hidden" name="book.bookSmallimg" id="bookSmallimg" value=""/>
            <input name="book.bookSubjectid" id="subjectId" type="hidden" value="0"/>
            <input name="book.status" id="statusId" type="hidden" value="1"/>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
            <thead>
						<tr>
							<th align="left" colspan="2"><span> 图书基本属性 （ *  为必填项） 
							</span></th>
						</tr>
			</thead>
			</table>
        <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm"  name="book.bookName" id="mobile"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				专业分类
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<div id="doc-dropdown-justify-js" >
							  <div class="am-dropdown" id="doc-dropdown-js"  style="width: 100%">
							    <input type="text" class="am-input-sm am-dropdown-toggle" id="subjectNameBtn" readonly="readonly"/>
							    <div id="subjectmenuContent" class="am-dropdown-content">
							    	<div id="subject_ztreedemo" class="ztree" ></div>
							    </div>
							  </div>
				</div>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				商品价格:原价
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm"   name="book.price" id="price"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				商品价格:现价
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm"  name="book.nowPrice" id="nowPrice"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				商品价格:折扣价
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm"  name="book.rebatePrice" id="rebatePrice"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				分类
			</label>
			<div class="am-u-sm-8 am-u-md-6" >
				<select name="book.bookType" id="bookType" data-am-selected="{btnWidth: '20%', btnSize: 'sm', btnStyle: 'secondary'}">
                                <option value="1">教材</option>
                                <option value="2">教辅</option>
                                <option value="3">大纲</option>
                                <option value="4">套装</option>
                 </select>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
        <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书封面
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<span> 
					<img src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="100px" height="100px" id="imgpic" />
				</span>
				<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
					<input class="am-input-sm" type="button" value="上传" id="imgButton"  />
					<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽100像素，高100像素）小于256kb
				</span>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div> 
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				缩略图
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<span> 
					<img src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="100px" height="100px" id="previewpic" />
				</span>
				<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
					<input class="am-input-sm" type="button" value="上传" id="previewButton"/>
					<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽100像素，高100像素）小于256kb
				</span>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div> 
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				库存
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<select name="book.stockNum" id="stockNum" data-am-selected="{btnWidth: '20%', btnSize: 'sm', btnStyle: 'secondary'}">
                    <option value="1">有货</option>
                    <option value="2">无货</option>
                </select>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>  
		
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 图书属性信息 
							</span></th>
						</tr>
					</thead>
		</table>  
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				作者
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.author" id="author"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				出版社
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.press" id="press" />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				出版时间
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.publishTime" id="publishTime" readonly="readonly" class="AS-inp am-input-sm" />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				版次
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.revision" id="revision"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				印次
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.printNum" id="printNum" />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				页数
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.pageNum" id="pageNum" />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				字数
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.wordNum" id="wordNum"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				包装
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.bookPackage" id="bookPackage" />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
	
	    <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				纸张
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.pager" id="pager"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		 <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				重量
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm" name="book.weight" id="weight" class="number" />
				
			</div>
			<div class="am-hide-sm-only am-u-md-4">
			<select name="gram" data-am-selected="{btnWidth: '20%', btnSize: 'sm', btnStyle: 'secondary'}">
                     <option value="千克">千克</option>
                     <option value="克">克</option>
                 </select>
			<font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		 <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				ISBN
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.isbn" id="isbn" />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				开本
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.bookSize" id="bookSize"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书注释
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input class="am-input-sm" type="text" name="book.remarks" id="remarks" class="required"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书简介
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<textarea id="bookInfo" rows="5" cols="50" name="book.bookInfo" maxlength="200" class="required"></textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-3"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书目录
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<textarea id="bookContext" rows="5" cols="70" name="book.directory" class="required" ></textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-3"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 图书应用属性信息 
							</span></th>
						</tr>
					</thead>
		</table>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				上架属性
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<label class="am-radio-inline">
				<input type="radio" name="book.shopState" checked="checked" value="1" data-am-ucheck onclick="nodisable()"/>
				上架
			</label>
			<label class="am-radio-inline">
				<input type="radio" name="book.shopState"  value="2" data-am-ucheck  onclick="disable()"/>
				下架
			</label>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				显示属性
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<div class="am-u-sm-6 am-u-md-6 am-u-end">
			<label class="am-checkbox">
				<input type="checkbox" name="disProperty" value="1" data-am-ucheck/>推荐&nbsp;
			</label>
			</div>
			<div class="am-u-sm-6 am-u-md-6 am-u-end">
			<label class="am-checkbox">
                <input type="checkbox" name="disProperty" value="2" data-am-ucheck/>特价&nbsp;
            </label>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-end">
            <label class="am-checkbox">
                <input type="checkbox" name="disProperty" value="3" data-am-ucheck/>精品&nbsp;
            </label>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-end">
            <label class="am-checkbox">
                <input type="checkbox" name="disProperty" value="4" data-am-ucheck/>畅销
            </label>
            </div>
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				支付链接
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input class="am-input-sm" type="text" name="book.payUrl" id="payUrl"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				&nbsp;
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			    <a class="am-btn am-btn-danger" href="javascript:void(0)" onclick="addCourseFormSubmit()">提交</a>
			    <a class="am-btn am-btn-success" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
 </form>
 </div>
	<%-- 	
<td align="center" colspan="2"><a class="am-btn am-btn-danger" title="提 交" href="javascript:addCourseFormSubmit()">提 交</a>
--%>
</body>
</html>
