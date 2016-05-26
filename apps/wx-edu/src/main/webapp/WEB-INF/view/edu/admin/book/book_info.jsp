<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>编辑图书</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script> 
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

	$(function() {
	//属性
 	   var pro="${book.disProperty}";
	   var  pros=pro.split(",");
	    for(var i=0;i<pros.length;i++)
	   {
	    $('input[name="disProperty"]').each(function(){  
				   var str=$(this).val();
	              if(pros[i]==str){
	              $(this).attr("checked","checked");
	              }
				   });  
	   }
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
					syncType : 'form',
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
				editor.readonly(true);
			});
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
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图书管理</strong> / <small>编辑图书</small></div>
</div>
<hr/>
<div class="mt20">
<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
		<div id="tab1" class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/book/update" method="post" id="updatebook" class="am-form">
				<input type="hidden" name="book.bookImg" id="bookImg" value="<%=staticImageServer %>${book.bookImg}"/>
	            <input type="hidden" name="book.bookSmallimg" id="bookSmallimg" value="<%=staticImageServer %>${book.bookSmallimg}"/>
	            <input name="book.bookSubjectid" id="subjectId" type="hidden" value="0"/>
        <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书名称
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text"  value="${book.bookName}" readonly="readonly" name="book.bookName" id="mobile"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				专业分类
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				  <div id="doc-dropdown-justify-js">
					<div class="am-dropdown" id="doc-dropdown-js"  style="width: 100%">
					    <input type="text" class="am-input-sm am-dropdown-toggle" readonly="readonly" value="${book.subjectName }"id="subjectNameBtn" readonly="readonly"/>
					    <div class="am-dropdown-content">
					    	<div id="subject_ztreedemo" class="ztree" ></div>
					    </div>
				  </div>
				  </div>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				商品价格:原价
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm" readonly="readonly" value="${book.price}" name="book.price" id="price"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				商品价格:现价
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm" readonly="readonly" value="${book.nowPrice}"  name="book.nowPrice" id="nowPrice"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				商品价格:折扣价
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" class="am-input-sm"  readonly="readonly" value="${book.rebatePrice}" name="book.rebatePrice" id="rebatePrice"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				分类
			</label>
			<div class="am-u-sm-8 am-u-md-6" >
			<fieldset disabled="disabled">
				<select name="book.bookType" id="bookType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
                                <option value="1">教材</option>
                                <option value="2">教辅</option>
                                <option value="3">大纲</option>
                                <option value="4">套装</option>
                 </select>
                 </fieldset>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
            
        <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书封面
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<span> 
					<img src="<%=staticImageServer %>${book.bookImg}" alt="" width="100px" height="100px" id="imgpic" />
				</span>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div> 
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				缩略图
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<span> 
					<img src="<%=staticImageServer %>${book.bookSmallimg}" alt="" width="100px" height="100px" id="previewpic" />
				</span>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div> 
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				库存
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<fieldset disabled="disabled">
				<select name="book.stockNum" id="stockNum" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
                    <option value="1">有货</option>
                    <option value="2">无货</option>
                </select>
                </fieldset>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
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
				<input type="text" name="book.author"  readonly="readonly" id="author" value="${book.author}"   />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				出版社
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.press" readonly="readonly" id="press" value="${book.press}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				出版时间
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.publishTime" readonly="readonly" value="<fmt:formatDate value="${book.publishTime}" type="both"/>" id="publishTime" readonly="readonly" class="AS-inp" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				版次
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.revision"  readonly="readonly"id="revision"  value="${book.revision}" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				印次
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.printNum" readonly="readonly" id="printNum"  value="${book.printNum}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				页数
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.pageNum" readonly="readonly" id="pageNum" value="${book.pageNum }" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				字数
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.wordNum" readonly="readonly"  id="wordNum"  value="${book.wordNum}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				包装
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.bookPackage" readonly="readonly" id="bookPackage" value="${book.bookPackage}"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
	
	    <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				纸张
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.pager"  readonly="readonly" id="pager" value="${book.pager}"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		 <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				重量
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.weight"  readonly="readonly" id="weight"  class="number" value="${book.weight}"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">
			&nbsp;</div>
		</div>
		
		 <div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				ISBN
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.isbn"  readonly="readonly"  id="isbn" value="${book.isbn}"  />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				开本
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.bookSize"  readonly="readonly"  id="bookSize"  value="${book.bookSize}" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书注释
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<input type="text" name="book.remarks"  readonly="readonly"  id="remarks" value="${book.remarks}" class="required"/>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书简介
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<textarea id="bookInfo"  readonly  rows="5" cols="50" name="book.bookInfo" maxlength="200" >${book.bookInfo}</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-3">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				图书目录
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<textarea id="bookInfo" rows="5" readonly  cols="70" name="book.bookInfo"  >${book.bookInfo}</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-3">&nbsp;</div>
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
				<input type="radio" name="book.shopState" disabled checked="checked" value="1" data-am-ucheck onclick="nodisable()"/>
				上架
			</label>
			<label class="am-radio-inline">
				<input type="radio" name="book.shopState"   disabled value="2" data-am-ucheck  onclick="disable()"/>
				下架
			</label>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				显示属性
			</label>
			<div class="am-u-sm-8 am-u-md-6">
			<div class="am-u-sm-8 am-u-md-6 am-u-end">
			<label class="am-checkbox">
				<input type="checkbox" name="disProperty" disabled value="1" data-am-ucheck/>推荐&nbsp;
			</label>
			</div>
			<div class="am-u-sm-8 am-u-md-6 am-u-end">
			<label class="am-checkbox">
                <input type="checkbox" name="disProperty"  disabled value="2" data-am-ucheck/>特价&nbsp;
            </label>
            </div>
            <div class="am-u-sm-8 am-u-md-6 am-u-end">
            <label class="am-checkbox">
                <input type="checkbox" name="disProperty"  disabled value="3" data-am-ucheck/>精品&nbsp;
            </label>
            </div>
            <div class="am-u-sm-8 am-u-md-6 am-u-end">
            <label class="am-checkbox">
                <input type="checkbox" name="disProperty"  disabled value="4" data-am-ucheck/>畅销
            </label>
            </div>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				支付链接
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="book.payUrl" id="payUrl"   readonly="readonly"  value="${book.payUrl }" />
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<label for="mobile" class="am-u-sm-4 am-u-md-2 am-text-right">
				&nbsp;
			</label>
			<div class="am-u-sm-8 am-u-md-6">
				<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
			</div>
			<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
		</div>
 </form>
 </div>
 </div>
 </div>
 </div>

</body>
</html>
