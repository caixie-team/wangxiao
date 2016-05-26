<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
    <script type="text/javascript">
        $(function(){
            $("#startTime").datetimepicker({
                regional:"zh-CN",
                changeYear: true,
                changeMonth: true,
                dateFormat:"yy-mm-dd",
                timeFormat : 'HH:mm:ss',
                timeFormat : 'HH:mm:ss'
            });
        });

        //加载编辑器
        var editor;
        KindEditor.ready(function(K) {
            editor = K.create('textarea[id="content,textareaemail"]',{
                resizeType : 1,
                filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
                allowPreviewEmoticons : false,
                allowUpload : true,//允许上传
                syncType : 'auto',
                width : '500px',
                minWidth : '10px',
                minHeight : '10px',
                height : '300px',
                urlType : 'domain',//absolute
                newlineTag : 'br',//回车换行br|p
                uploadJson : '<%=keImageUploadUrl%>' + '&param=sendEmail',// 图片上传路径
                allowFileManager : false,
                afterBlur : function() {
                    editor.sync();
                },
                afterChange : function() {
                }
            });
        });

        function update(){
            var content = $("#content").val();
            var title = $("#title").val();

            if(title.trim()==''){
                alert('邮件标题不能为空！');
                return false;
            }
            if(content.trim()==''){
                alert('邮件内容不能为空！');
                return false;
            }

            if(confirm('确定发送?')==false){
                return false;
            }
            var startTime = $("#startTime").val();
            $.ajax({
                url : "${ctx}/admin/sendEmailMsg/update",
                data : {
                    "id" : '${userEmailMsg.id}',
                    "content" : content,
                    "title" : title,
                    "sendTime" : startTime
                },  // 参数
                type : "post",
                async : false,
                dataType : "json",  //返回json数据
                success:function (result){
                    alert(result.message);
                    if(result.message=='成功'){
                        window.location.href="/admin/user/sendEmaillist";
                    }
                }
            });
        }
    </script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">邮件管理</strong> / <small>邮件详情</small></div>
</div>
<!-- /tab4 begin -->
<div class="mt20">
<div class="am-tabs">
	    <ul class="am-tabs-nav am-nav am-nav-tabs">
	      <li class="am-active"><a href="#tab1">邮件详情</a></li>
	    </ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
	<form action="${ctx}/admin/user/updatePwd" class="am-form" method="post" id="addPaperForm">
	<input name="user.id" id="id" type="hidden" value="${user.id}"/>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>邮件标题：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<input class="am-form-field" readonly="readonly"  type="text" id="title" value="${userEmailMsg.title }"/>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>邮件类型：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<input class="am-form-field" readonly="readonly"  type="text" id="title" value="${userEmailMsg.type==1?'普通':'定时' }"/>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>是否发送：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<input class="am-form-field" readonly="readonly"  type="text" id="title" value="${userEmailMsg.status==1?'已发送':'未发送' }"/>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>定时发送时间：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<input class="am-form-field" type="text" class="" readonly="readonly" <c:if test="${userEmailMsg.status!=2&&userEmailMsg.type!=2 }">disabled="disabled"</c:if> value="<fmt:formatDate value="${userEmailMsg.sendTime}" type="both"/>" id="startTime" name=""/>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>邮件内容：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<textarea class="am-form-field" id="content" rows="6" cols="80" > ${userEmailMsg.content }</textarea>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>创建时间：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<input class="am-form-field" type="text" disabled="disabled" value="<fmt:formatDate value="${userEmailMsg.createTime }" type="both"/>"/>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>发件人：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<input class="am-form-field" type="text" value="${userEmailMsg.loginName }" disabled="disabled"/>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-2 am-text-right">
			<span class="ddTitle"><font>接收人：</font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			<textarea class="am-form-field" id="textareaemail">${userEmailMsg.email}</textarea>
		</div>
	</div>
	<div class="am-g am-margin-top">
		<div class="am-u-sm-4 am-text-right">
			<span class="ddTitle"><font></font></span>
		</div>
		<div class="am-u-sm-6 am-u-end">
			      <c:if test="${userEmailMsg.type==2&&userEmailMsg.status==2}">
                      <a class="am-btn am-btn-danger" title="修 改" href="javascript:update()">修 改</a>
                  </c:if>
				<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
		</div>
	</div>
	</form>
	</div>
	</div>
	</div>
</div>
<!-- /tab4 end -->
</body>
</html>
