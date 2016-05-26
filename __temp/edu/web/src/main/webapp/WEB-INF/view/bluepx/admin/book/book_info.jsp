<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建图书</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script> 
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

	$(function() {
		datepicker("#publishTime");
		$("#subjectIdOne").change();
		$("#subjectId").val('${book.bookSubjectid}');
	    $("#bookType").val('${book.bookType}');
	   	$("#stockNum").val('${book.stockNum}');
	  	$("#shopState").val('${book.shopState}');   

	   var shopState=${book.shopState };
	   if(shopState==1){
	   $("#upjia").attr("checked","checked");
	   }
	   if(shopState==2){
	   $("#downjia").attr("checked","checked");
	     disable();
	   }

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


	$('input').each(function(){             
	              $(this).attr("disabled","disabled");
				   });  
	$('select').each(function(){             
	              $(this).attr("disabled","disabled");
				   });  
	$('textarea').each(function(){             
	              $(this).attr("disabled","disabled");
				   });  
		//$("#addBook").validate();
		//initSimpleImageUpload("imgButton","imgpic","bookImg");
        //initSimpleImageUpload("previewButton","previewpic","bookSmallimg");
	});
	function initSimpleImageUpload(btnId,urlId,valSet){
		KindEditor.ready(function(K) {
			var uploadbutton = K.uploadbutton({
				button : K('#'+btnId+'')[0],
				fieldName : "fileupload",
				url : '<%=uploadSimpleUrl%>&param=bannerImages',
				afterUpload : function(data) {
					if (data.error === 0) {
						var url = K.formatUrl(data.url, 'absolute');//absolute,domain
						K('#'+urlId+'').attr("src",'<%=staticImageServer%>'+data.url);
						$("#"+urlId).show();
						$('#'+valSet+'').val(url);
					} else {
						alert(data.message);
					}
				},
				afterError : function(str) {
					alert('自定义错误信息: ' + str);
				}
			});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
		});
	}
	//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
				editor = K.create('#bookInfo,#context',{
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
					readonlyMode : true,
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
		if($("#subjectId").val()=="0"){
			alert("请选择专业");
			return;
		}
		if($("#bookImg").val()==""){
			alert("请上传图书封面");
			return;
		}
		if ($("#bookSmallimg").val() == ""){
	        alert("请选择商品缩略图片");
	        return;
	    }
		var price = $("#price").val();
	    if (/^[1-9]*[0-9]{1}\./.test(price)){
	        if (!/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(price)){
	            alert("原价只保留两位小数");
	            return false;
	        }
	    }
	    var nowPrice = $("#nowPrice").val();
	    if (/^[1-9]*[0-9]{1}\./.test(nowPrice)){
	        if (!/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(nowPrice)){
	            alert("现价只保留两位小数");
	            return false;
	        }
	    }
	    var rebatePrice = $("#rebatePrice").val();
	    if (/^[1-9]*[0-9]{1}\./.test(rebatePrice)){
	        if (!/^[1-9]*[0-9]{1}\.[0-9]{1,2}$/.test(rebatePrice)){
	            alert("现价只保留两位小数");
	            return false;
	        }
	    }
	    var publishTime = $("#publishTime").val();
	    if ((new Date(publishTime.replace(/-/g, "/"))) > (new Date())){
	        alert("出版时间不能大于当前日期");
	        return false;
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
		<form action="${ctx}/admin/book/add" method="post" id="addBook">
			<input type="hidden" name="book.bookImg" id="bookImg" value=""/>
            <input type="hidden" name="book.bookSmallimg" id="bookSmallimg" value=""/>
            <input name="book.bookSubjectid" id="subjectId" type="hidden" value="${book.bookSubjectid}"/>
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>图书管理</span> &gt; <span>新建图书</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 图书基本属性 <tt class="c_666 ml20 fsize12">
										（ <font color="red">*</font> &nbsp;为必填项）
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;图书名称</td>
							<td><input type="text" name="book.bookName" class="required" value="${book.bookName}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font> &nbsp;专业分类</td>
							<td width="80%">
								<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
								<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
									<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
								</div>
								</td> 
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;商品价格:原价</td>
							<td><input type="text" name="book.price" class="required number"  value="${book.price}" disabled="disabled"/></td>
						</tr>
						<tr>
						<td align="center"><font color="red">*</font> &nbsp;商品价格:现价</td>
						<td><input type="text" name="book.nowPrice" class="required number"  value="${book.nowPrice}" disabled="disabled"/></td>
						</tr>
						<tr>
						<td align="center"><font color="red">*</font> &nbsp;商品价格:折扣价</td>
						<td><input type="text" name="book.rebatePrice" class="required number"  value="${book.rebatePrice}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;分类</td>
							<td><select name="book.bookType" id="bookType">
                                <option value="1">教材</option>
                                <option value="2">教辅</option>
                                <option value="3">大纲</option>
                                <option value="4">套装</option>
                            </select></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>图书封面</td>
							<td width="80%">
								<span> 
									<img src="<%=staticImageServer %>${book.bookImg}" alt="" width="100px" height="100px" id="imgpic" />
								</span>
								<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
									<input type="button" value="上传" id="imgButton" class="required"/>
									<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽100像素，高100像素）小于256kb
								</span>
							</td>
						</tr>
						<tr>
						<td align="center"><font color="red">*</font>&nbsp;缩略图</td>
						<td>
							<span> 
								<img src="<%=staticImageServer %>${book.bookSmallimg}" alt="" width="100px" height="100px" id="previewpic" />
							</span>
							<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
								<input type="button" value="上传" id="previewButton" class="required"/>
								<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽100像素，高100像素）小于256kb
							</span>
						</td>
					</tr>
					<tr>
							<td align="center"><font color="red">*</font> &nbsp;库存</td>
							<td><select name="book.stockNum" id="stockNum">
                                <option value="1">有货</option>
                                <option value="2">无货</option>
                            </select></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 图书属性信息 
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;作者</td>
							<td><input type="text" name="book.author" class="required" maxlength="60" value="${book.author}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;出版社 </td>
							<td><input type="text" name="book.press" id="press" maxlength="60" value="${book.press}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;出版时间 </td>
							<td><input type="text" name="book.publishTime" id="publishTime" readonly="readonly" value="<fmt:formatDate value="${book.publishTime}" type="both"/>"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;版次 </td>
							<td><input type="text" name="book.revision" id="revision" maxlength="60" value="${book.revision}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;印次 </td>
							<td><input type="text" name="book.printNum" id="printNum" maxlength="60" class="required number" value="${book.printNum}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;页数 </td>
							<td><input type="text" name="book.pageNum" id="pageNum" class="required number" value="${book.pageNum }" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;字数 </td>
							<td><input type="text" name="book.wordNum" id="wordNum" class="required number" value="${book.wordNum}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;包装 </td>
							<td><input type="text" name="book.bookPackage" id="bookPackage" maxlength="60" value="${book.bookPackage}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;纸张 </td>
							<td><input type="text" name="book.pager" id="pager" maxlength="60" value="${book.pager}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;重量 </td>
							<td><input type="text" name="book.weight" id="weight" class="number" value="${book.weight}" disabled="disabled"/><select name="gram">
                                <option value="千克">千克</option>
                                <option value="克">克</option>
                            </select></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;ISBN </td>
							<td><input type="text" name="book.isbn" id="isbn" class="number" value="${book.isbn}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;开本 </td>
							<td><input type="text" name="book.bookSize" id="bookSize" value="16" maxlength="20" class="number" value="${book.bookSize}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;图书注释 </td>
							<td><input type="text" name="book.remarks" id="remarks" class="required" value="${book.remarks}" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;图书简介</td>
							<td><textarea id="bookInfo" rows="5" cols="70" name="book.bookInfo" maxlength="200" class="required" disabled="disabled">${book.bookInfo}</textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;图书目录</td>
							<td><textarea id="context" rows="5" cols="70" name="book.directory" class="required" disabled="disabled">${book.directory}</textarea></td>
						</tr>
					</tbody>
					</table>
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 图书应用属性信息 
							</span></th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<td align="center"><font color="red">*</font> &nbsp;上架属性 </td>
							<td><input type="radio" name="book.shopState" value="1" checked="checked"
                                                                         onclick="nodisable()"/>上架<input name="book.shopState" type="radio" value="2"
                                                                                                         onclick="disable()"/>下架</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;显示属性 </td>
							<td><input type="checkbox" name="disProperty" value="1"/>推荐&nbsp;
				                <input type="checkbox" name="disProperty" value="2"/>特价&nbsp;
				                <input type="checkbox" name="disProperty" value="3"/>精品&nbsp;
				                <input type="checkbox" name="disProperty" value="4"/>畅销</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;支付链接 </td>
							<td><input type="text" name="book.payUrl" id="payUrl" value="${book.payUrl }" disabled="disabled"/></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:addCourseFormSubmit()">提 交</a> <a
								class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
