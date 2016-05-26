<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>修改文库</title>
	<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>

	<script type="text/javascript">
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

		//切换专业时操作
		function subject_treeOnclick(e,treeId, treeNode) {
			$("#subjectId").val(treeNode.subjectId);
			$("#subjectNameBtn").val(treeNode.subjectName);
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
		}

		$(function(){
			$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, ${subjectList});
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
			//专业名称显示
			if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
				var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
				var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
				if(treeNode!=null){
					$("#subjectNameBtn").val(treeNode.subjectName);
				}
			}
		})
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
				'width':170,
				'height':50,
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
						dialogFun("提示","文件太大最大上传512kb",0);
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
				'width':170,
				'height':50,
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
						dialogFun("提示","文件太大最大上传512M",0);
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
				dialogFun("修改文库","请选择专业",0);
				return;
			}
			if($("#libraryName").val()==null||$("#libraryName").val()==''){
				dialogFun("修改文库","请填写名称",0);
				return;
			}
			if($("#intro").val()==null||$("#intro").val()==''){
				dialogFun("修改文库","请填写简介",0);
				return;
			}
			
			if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
				dialogFun("修改文库","请上传封面",0);
				return;
			}
			if($("#urlStr").val()==null||$("#urlStr").val()==''){
				dialogFun("修改文库","请上传PDF或PIC",0);
				return;
			}
			$("#updateForm").submit();
		}
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">文库管理</strong> / <small>修改文库</small></div>
</div>
<hr/>
<form action="${ctx}/admin/library/update" method="post" id="updateForm" class="am-form">
	<input type="hidden" name="library.id" value="${library.id}"/>
	<input type="hidden" name="library.imgUrl" id="imageUrl" value="${library.imgUrl }"/>
	<input type="hidden" name="library.type" value="${library.type }"/>
	<input type="hidden" name="library.subjectId" id="subjectId" value="${library.subjectId }"/>
	<div class="mt20">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;所属项目专业 </div>
				<div class="am-u-sm-8 am-u-md-6">
					<div id="doc-dropdown-justify-js" >
						<div class="am-dropdown" id="doc-dropdown-js">
							<input type="text" class="am-input-sm am-dropdown-toggle" style="width: 270px" id="subjectNameBtn" readonly="readonly"/>
							<div class="am-dropdown-content">
								<div id="subject_ztreedemo" class="ztree" ></div>
							</div>
						</div>
					</div>
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;文库名称  </div>
				<div class="am-u-sm-8 am-u-md-6">
				<input type="text" name="library.name" value="${library.name}" id="libraryName" class="am-input-sm" />
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;简介  </div>
				<div class="am-u-sm-8 am-u-md-6">
				<textarea rows="5" cols="70" name="library.intro" id="intro" maxlength="200" class="required " class="am-input-sm" >${library.intro}</textarea>
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;浏览次数  </div>
				<div class="am-u-sm-8 am-u-md-6">
					<input type="text" name="library.browseNum" id="browseNum"  class="am-input-sm"  value="${library.browseNum}" />
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;封面  </div>
				<div class="am-u-sm-8 am-u-md-6">
					<input type="file" id="fileupload" style="float: left" />
					<div id="fileQueue" style="margin-top: 30px; border: 0px;"></div>
					<span style="float: left;">
						<img src="<%=staticImageServer%>${library.imgUrl}" alt="" width="300px" height="240px" style=" padding-left: 80px" id="imgpic" />
					</span>
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;PDF转SWF  </div>
				<div class="am-u-sm-8 am-u-md-6">
					<input type="text" style="width: 500px;" name="library.pdfUrl" id="urlStr" class="am-input-sm" value="${fn:replace(library.pdfUrl,'.swf','.pdf') }" />(可直接输入地址)
					<div class="mt20 clear"></div>
					<input type="file" id="fileuploadPDF" style="float: left" />
					<div id="fileQueuePDF" style="margin-top: 30px; border: 0px;"></div>
					<div id="messagePDF" style="margin-top: 30px; border: 0px"></div>
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
			<div class="am-g am-margin-top">
				<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
				<div class="am-u-sm-8 am-u-md-6">
					<a class="am-btn am-btn-danger" title="提 交" href="javascript:void(0)" onclick="updateSubmit()">提 交</a>
					<a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</div>
				<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
