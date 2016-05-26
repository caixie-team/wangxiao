<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>添加课时</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />

<script type="text/javascript">
	var content;
$(function(){
	$("#videotypeId").change(function() {
		$(".playType").hide();
		$(".playTypeLETV").hide();
		if ($(this).val() == 'FIVESIX') {
			$(".playType").show();
		}
		if($(this).val() == 'LETV'){
			$(".playTypeLETV").show();
		}
		if($(this).val()=="CC"){
			$("#videoUrlTitle").html("CC播放地址");
		}else{
			$("#videoUrlTitle").html("视频地址");
		}
	});
	var submitType = '${submitType}';
	if(submitType=='add'){
		$("#addKpointBtn").show();
		$("#Graphic").click();
	}else if(submitType=='update'){
		$("#updateKpointBtn").show();
	}
	changeType();
	// 图文编辑器初始化
	content = UE.getEditor('courseTextGraphic', {
		maximumWords:50000,
		initialFrameWidth:500,
		initialFrameHeight:200,
		serverUrl:"${imageUrl}/ueditor/controller.jsp?fromdomain=${ctx}",
		toolbars: [ueditor_simpletoolbar]
	});

	// 试听开关监听
	$('#isfree').on('switchChange.bootstrapSwitch', function(event, state) {
		if(state){
			$('#isfreeVal').val(1);
		}else{
			$('#isfreeVal').val(2);
		}
	});
});



//PDF上传
$().ready(function() {
	$("#fileuploadPDF").uploadify({
		'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
		'method' : 'GET',
		'script'  :'<%=uploadServerUrl%>/pdf2swf',
		'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"toPDF","flag":"pdf"},
		'queueID':'fileQueuePDF',
		'fileDataName':'fileupload',
		'width' : 220,
		'height' : 50,
		'auto':true,
		'multi':false,
		'hideButton':false,
		'buttonText':'Browse',
		'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
		'simUploadLimit' : 3,
		'sizeLimit'      : 512000000,
		'queueSizeLimit' : 2,
		'fileDesc'       : '支持格式:pdf/ppt/xls/doc',
		'fileExt'        : '*.pdf;',//'*.pdf;*.ppt;*.xls;*.doc;
		'folder' : '/upload',
		'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
		onSelect:function(event, queueID, fileObj){
			$('#fileuploadPDF').uploadifyUpload();
			fileuploadIndex=1;
			$("#fileQueuePDF").html("");
			if(fileObj.size>512000000){
				dialogFun("PDF上传","文件太大最大上传512M",0);
				return;
			}
		},
		onComplete: function (event, queueID, fileObj, response, data){
			var type = "";
			var msg = "";
			if(response =='pdfnotexists'){
				type = "am-alert-danger";
				msg = "pdf文件不存在";
			}else if(response =='swfexists'){
				type = "am-alert-warning";
				msg = "已经存在不需要转换";
			}else if(response ==null){
				type = "am-alert-danger";
				msg = "转换错误";
			}else{
				type = "am-alert-success";
				msg = "上传成功";
			}
			var html = '<div data-am-alert="" class="am-alert '+type+'">'+
					'<button class="am-close" type="button">×</button>'+
					'<p>'+msg+'</p>'+
					'</div>';
			$("#messagePDF").html(html);
			$("#videourlId").val(response);
		},
		onError : function(event, queueID,fileObj, errorObj) {
			$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
		}
	});
});



var fileuploadIndex=0;
function uploadifyUpload(){
	 if(fileuploadIndex==0)
	 {
		 dialogFun("添加课时","请选择文件 ",0);
		 return;
	 }
	$('#uploadify').uploadifyUpload();

}

$(document).ready(function() {
    $("#uploadify").uploadify({
    	'uploader' : '/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
		'script'  :'<%=videoUpload%>',
		'scriptData':{"base":"mavendemo","param":"userFeedback"},
		'queueID' : 'audioQueue', //文件队列ID
		'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
		'auto' : true, //选定文件后是否自动上传
		'width' : 220,
		'height' : 50,
		'multi' :false, //是否允许同时上传多文件
		'hideButton' : false,//上传按钮的隐藏
		'buttonText' : 'Browse',//默认按钮的名字
		'buttonImg' : '/static/common/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
		'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
		'sizeLimit' : 51200000,//控制上传文件的大小
		'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
		'fileDesc' : '支持格式:mp3',//出现在上传对话框中的文件类型描述
		'fileExt' : '*.mp3',//支持的格式，启用本项时需同时声明fileDesc
		'folder' : '/upload',//您想将文件保存到的路径
		'cancelImg' : '/static/common/uploadify/cancel.png',
        onSelect:function(event, queueID, fileObj){
        	$('#uploadify').uploadifyUpload();
        	fileuploadIndex=1;
       		$("#fileQueue").html("");
       		if(fileObj.size>10485760000){
       			dialogFun("MP3上传","文件太大,最大上传1G",0);
       			return;
       		}
    	},
        onComplete: function (event, queueID, fileObj, response, data)
        {
        	$("#videourlId").val(response);//音频地址
        	return false;
        },
        onError: function(event, queueID, fileObj,errorObj)
        {
        	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
        }
    });
});
	//类型切换
	function changeType() {
		var intHot = $('input[name="courseType"]:checked').val();
		$("#Graphicdiv").hide();
		$("#mp3div").hide();
		$(".videoType").hide();
		$("#pdfdiv").hide();
		$(".videoUrl").hide();
		$(".playType").hide();
		$(".playTypeLETV").hide();
		$("#Minutes").hide();
		if(intHot=="PDF"){//PDF
			$("#pdfdiv").show();
			$(".videoUrl").show();
			$("#videoUrlTitle").html("pdf地址");
		}else if(intHot=="GRAPHIC"){//图文类型
			$("#Graphicdiv").show();
		}else if(intHot=="MP3"){//音频
			$("#mp3div").show();
			$(".videoUrl").show();
			$("#Minutes").show();
			$("#videoUrlTitle").html("音频地址");
		}else if(intHot=="VIDEO"){//视频
			$(".videoType").show();
			$(".videoUrl").show();
			$("#Minutes").show();
			$("#videoUrlTitle").html("视频地址");
		}
	}
	//提交方法
	function addArticleFormSubmit(){
		if(formsubmit()){
			$.ajax({
				url : "${ctx}/admin/course/addcourse",
				data : $("#addArticleForm").serialize(),
				type : "post",
				dataType : "json",
				async:false,
				success : function(result) {
					if (result.success) {
						dialogFun("添加课时","添加成功",1);
						window.opener.location.href='${ctx}/admin/kpoint/list/${courseId}';
						window.close();
					}
				}
			});
	    }
	}
	function updateArticleFormSubmit(){
		if(formsubmit()){
			$.ajax({
				url : "${ctx}/admin/course/updateuserList",
				data : $("#addArticleForm").serialize(),
				type : "post",
				dataType : "json",
				async:false,
				success : function(result) {
					if (result.success) {
						//dialogFun("添加课时","修改成功",1);
						window.opener.location.href='${ctx}/admin/kpoint/list/${courseId}';
						window.close();
					}
				}
			});
		}

	}
	 //获得56 上传视频的url
	function uploading(){
		$.ajax({
			url : "${ctx}/admin/56/uploadurl",
			data : {},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.success){
					var url = result.entity;
					$("#UploadIframe").attr("src",url);
					$("#UploadIframe").show();
				}
			}
		});
	}
	//上传的回调方法
	function callback(vid,subject,url,result,player,chk,cover,
		coop_public,forbid,coopid,sid,category,attach,tags,content,item_1,msg,iframePlayer){
		if(vid==0){//如果vid等于0则上传失败
			alert(msg);
		}else{
			$("#videourl").val(decodeURIComponent(iframePlayer));
			$("#UploadIframe").hide();
				var values = decodeURIComponent(vid)+","+
				decodeURIComponent(subject)+","+
				decodeURIComponent(url)+","+
				decodeURIComponent(result)+","+
				decodeURIComponent(player)+","+
				decodeURIComponent(chk+cover)+","+
				decodeURIComponent(coop_public)+","+
				decodeURIComponent(forbid)+","+
				decodeURIComponent(coopid)+","+
				decodeURIComponent(sid)+","+
				decodeURIComponent(category)+","+
				decodeURIComponent(attach)+","+
				decodeURIComponent(tags)+","+
				decodeURIComponent(content)+","+
				decodeURIComponent(item_1);
			$("#videojson").val(values);
		}
	}

	var video_unique;//视频唯一标示码
	var video_json;//乐视json
	function initLetv(){//乐视云上传
		if($("#name").val()==null||$("#name").val()==''){
			dialogFun("添加课时","请输入视频名称",0);
			return;
		}
		$.ajax({
			url:"${ctx}/admin/letv/uploadurl",
			type:"post",
			data:{"videoName":$("#name").val(),"courseId":$("#courseId").val()},
			dataType:"json",
			success:function(result){
				if(result.message){
					video_unique=result.entity.letvmap.video_unique;
					video_json=result.entity.letvjson;
					$("#precent").html(result.entity.letvmap.flash_upload);
				}else{
					alert(result.message);
				}
			}
		});
	}
	function letv_callback(){
		$("#videourl").val(video_unique);
		$("#videojson").val(video_json);
		$("#precent").html("");
		}

	function formsubmit(){
		var courseType = $('input[name="courseType"]:checked').val();
		var minute = $("#courseMinutesId").val();
		var second =  $("#courseSecondsId").val();
		var name = $("#name").val();
		var number =/^[0-9]*$/;

		if(isEmpty(name)){
			dialogFun("添加课时","名称不能为空",0);
			return false;
		}
		if(courseType!='GRAPHIC'&&courseType!='PDF'){//非图文和PDF
			if(isEmpty(minute)||isEmpty(second)){
				dialogFun("添加课时","时长不能为空",0);
				return false;
			}
			if(!number.exec(minute)||!number.exec(second)){
				dialogFun("添加课时","请输入数字(零或正整数)",0);
				return false;

			}
		}
		if(courseType=="GRAPHIC"){//图文类型
			var text = content.getContent();
			if(isEmpty(text)){
				dialogFun("添加课时","内容不能为空",0);
				return false;
			}
		}
		if(courseType=="PDF"||courseType=="MP3"||courseType=="VIDEO"){//PDF、音频、视频
			var url = $("#videourlId").val();
			if(isEmpty(url)){
				dialogFun("添加课时","地址不能为空",0);
				return false;
			}
		}
		return true;
	}

</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课时管理</strong> / <small>课时添加</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form id="addArticleForm" class="am-form">
					<input type="hidden" name="courseKpoint.courseId" value="${courseId}" id="courseId"/>
					<input type="hidden" name="courseKpoint.parentId" id="parentId" value="${param.id}"/>
					<input type="hidden" name="courseKpoint.type" id="type" value="0" />
					<input type="hidden" name="courseKpoint.Id" id="Id" value="${courseKpoint.id}" />
					<input type="hidden" name="updatecoursetype" id="updatecoursetype" value="${courseKpoint.courseType}" />
					<input type="hidden" name="updatevideotype" id="updatevideotype" value="${courseKpoint.videotype}" />
					<input type="hidden" name="courseKpoint.sort" id="sortId" value="${sort}"/>
					<input type="hidden" name="ids" id="ids" value="${ids}"/>
					<div class="am-g am-margin-top video">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							课节类型
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<label class="am-radio-inline">
								<input <c:if test="${courseKpoint.courseType=='GRAPHIC'}">checked</c:if> type="radio" value="GRAPHIC" name="courseType" id="Graphic" data-am-ucheck onclick="changeType()">&nbsp;&nbsp;图文
							</label>
							<label class="am-radio-inline">
								<input <c:if test="${courseKpoint.courseType=='PDF'}">checked</c:if> type="radio" value="PDF" name="courseType" id="pdf" data-am-ucheck onclick="changeType()">&nbsp;&nbsp;PDF
							</label>
							<label class="am-radio-inline">
								<input <c:if test="${courseKpoint.courseType=='MP3'}">checked</c:if> type="radio" value="MP3" name="courseType" id="MP3" data-am-ucheck onclick="changeType()">&nbsp;&nbsp;mp3
							</label>
							<label class="am-radio-inline">
								<input <c:if test="${courseKpoint.courseType=='VIDEO'}">checked</c:if> type="radio" value="VIDEO" name="courseType" id="video" data-am-ucheck onclick="changeType()">&nbsp;&nbsp;视频
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							名称
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="courseKpoint.name" value="${courseKpoint.name }" id="name" class="am-input-sm" />
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>
					<div class="am-g am-margin-top video videoType">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							视频类型
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<select name="courseKpoint.videotype" id="videotypeId" data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
								<option value="CC">CC</option>
								<option value="FIVESIX">56</option>
								<option value="SWF">SWF</option>
								<option value="LETV">乐视</option>
								<option value="BAOLI">保利威视</option>
								<option value="mp4flv">本地视频</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<!-- 视频 -->
					<div class="am-g am-margin-top video videoUrl">
						<label class="am-u-sm-4 am-u-md-2 am-text-right" id="videoUrlTitle">
							视频地址
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="courseKpoint.videourl" value="${courseKpoint.videourl }" id="videourlId" class="am-input-sm" />
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>
					<!-- MP3 -->
					<div class="am-g am-margin-top video fileupload" id="mp3div" style="display: none;">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							请上传音频
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="file" id="uploadify" />
							<div id="audioQueue"></div>
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>
					<!-- 图文 -->
					<div class="am-g am-margin-top video" id="Graphicdiv" style="display: none;">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							内容
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea name="courseKpoint.courseText" id="courseTextGraphic">${courseKpoint.courseText}</textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<!-- PDF -->
					<div class="am-g am-margin-top video" id="pdfdiv" style="display: none;">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							PDF文件
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="file" id="fileuploadPDF" />
							<div id="fileQueuePDF"></div>
							<section class="mt20" id="messagePDF"></section>
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>

					<div class="am-g am-margin-top video">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							是否试听
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="hidden" value="${courseKpoint.isfree}" name="courseKpoint.isfree" id="isfreeVal" />
							<input id="isfree" type="checkbox" data-size="sm" <c:if test="${courseKpoint.isfree==1}">checked</c:if> data-am-switch data-off-color="danger">
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>

					<div class="am-g am-margin-top video undis" id="Minutes">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							时长
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="courseKpoint.courseMinutes" value="${courseKpoint.courseMinutes }" style="width:50%;" id="courseMinutesId" class="am-input-sm am-inline" />
							分
							<input type="text" name="courseKpoint.courseSeconds" value="${courseKpoint.courseSeconds }" style="width:50%;" id="courseSecondsId" class="am-input-sm am-inline" />
							秒
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>

					<div class="am-g am-margin-top playType undis">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							视频上传
						</label>
						<div class="am-u-sm-8 am-u-md-6 lists_tleft">
							<button type="button" onclick="uploading()" class="am-btn am-btn-danger">视频上传</button>
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>
					<div class="am-g am-margin-top playType undis">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">

						</label>
						<div class="am-u-sm-8 am-u-md-6 lists_tleft">
							<iframe style="display: none;width:462px;height:180px" id="UploadIframe" src="">
							</iframe>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>

					<div class="am-g am-margin-top playTypeLETV undis">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							视频上传
						</label>
						<div class="am-u-sm-8 am-u-md-6 lists_tleft">
							<button type="button" onclick="initLetv()" class="am-btn am-btn-danger">视频上传</button>
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>
					<div class="am-g am-margin-top playTypeLETV undis">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">

						</label>
						<div class="am-u-sm-8 am-u-md-6 lists_tleft" id="precent">
						</div>
						<div class="am-hide-sm-only am-u-md-4">必填</div>
					</div>

					<div class="am-g am-margin-top" id="addKpointBtn" style="display: none;">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">

						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="提 交"  href="javascript:addArticleFormSubmit()">提 交</a>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:window.close();">返 回</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top" id="updateKpointBtn" style="display: none;">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">

						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="修 改"  href="javascript:updateArticleFormSubmit()">修 改</a>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:window.close();">返 回</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/lang/zh-cn/zh-cn.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.mine.js"> </script>
</body>
</html>
