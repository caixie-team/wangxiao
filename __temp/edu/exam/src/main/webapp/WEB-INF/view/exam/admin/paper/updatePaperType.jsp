<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改试卷</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script> 
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script> 
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.js?v=${v}"></script> 
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=${v}"></script> 
<script type="text/javascript">
$(document).ready(function() {
$("#addPaperForm").validate();

$("#fileupload").uploadify({
    'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
    'method' : 'GET', 
    'script'  :'<%=uploadSwfUrl%>',
    'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"exambanner"},
    'queueID':'fileQueue',
    'fileDataName':'fileupload',
    'auto':true,
    'multi':false,
    'hideButton':false,
    'buttonText':'Browse',
    'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
    'width':105,
    'simUploadLimit':3,
    'sizeLimit':512000,
    'queueSizeLimit':2,
    'fileDesc':'支持格式:jpg/gif/jpeg/png/bmp.',
    'fileExt':'*.jpg;*.gif;*.jpeg;*.png;*.bmp',
    'folder':'/upload',
    'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
    onSelect:function (event, queueID, fileObj)
    {
        // $('#fileupload').uploadifyUpload();
        fileuploadIndex = 1;
        $("#fileQueue").html("");
        if (fileObj.size > 512000)
        {
            alert("文件太大最大上传512kb");
            return;
        }

    },
    onComplete:function (event, queueID, fileObj, response, data)
    {
        $("#bookpic").attr("src", "<%=staticImageServer%>" + response);
        $("#imgUrl").val(response);
        $("#bookpic").show();
    },
    onError:function (event, queueID, fileObj, errorObj)
    {
        $("#fileQueue").html("<br/><font color='red'>" + fileObj.name + "上传失败</font>");
        // alert("文件:" + fileObj.name + "上传失败");
        //}
    }/* ,
     onCancel: function(event, queueID, fileObj)
     {
     alert("取消了" + fileObj.name);
     } */
});
});


function uploadifyUpload()
{
    if (fileuploadIndex == 0)
    {
        alert("请选择图片 ");
        return;
    }
    /*
     var num = $('#fileupload').uploadifySettings('queueSize');
     if (num == 0) {
     alert("请选择图片 ");
     return;
     }	*/
    $('#fileupload').uploadifyUpload();
}
function addpaperTypeSubmit(){
	if($("#imgUrl").val().trim()==""){
		alert("请选择图片");
		return;
	}
	$("#addpaperTypeForm").submit();
}
</script>
</head>
<body >
<form action="${ctx}/admin/paper/updatePaperType" method="post" id="addpaperTypeForm">
<input type="hidden" name="paperType.id" value="${paperType.id }"/>
<input type="hidden" name="paperType.imgUrl" value="${paperType.imgUrl }" id="imgUrl"/>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试卷类型</span> &gt; <span>修改试卷类型</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>修改试卷类型<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;标题</td>
				<td>
					<input type="text" name="paperType.title" class="{required:true}" value="${paperType.title }"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;描述</td>
				<td width="80%"  id="describe">
					<input type="text" name="paperType.describtion" class="{required:true}" value="${paperType.describtion }"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>按钮title</td>
				<td width="80%">
					<input type="text" name="paperType.buttonTitle" class="{required:true}" value="${paperType.buttonTitle }"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;图片</td>
				<td>
					<input type="file" id="fileupload" style="float: left" name="Filedata"/>
           	   <div id="fileQueue" style="margin-top: 30px;border:0px;"></div>
               <span style="float:left;">
               <c:if test="${paperType.imgUrl=='' }">
               <img src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="126px" height="114px" style="display: none;padding-left: 80px" id="bookpic"/>
               </c:if>
                <c:if test="${paperType.imgUrl!='' }">
                <img src="<%=staticImageServer%>${paperType.imgUrl }" alt="" width="126px" height="114px" style="padding-left: 80px" id="bookpic"/>
                </c:if>
               </span>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;是否显示</td>
				<td>
					<select name="paperType.state" id="state">
						<option value="0">显示</option>
						<option value="1">隐藏</option>
					</select>
					<script >
						var state = '${paperType.state}';
						$("#state").val(state);
					</script>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;排序</td>
				<td>
					<input type="text"  name="paperType.sort" class="{required:true,number:true}" value="${paperType.sort }"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:addpaperTypeSubmit();">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<!-- /tab4 end -->
</form>
</body>
</html>
