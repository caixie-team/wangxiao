<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>短信详情</title>

	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
	$(function(){
		  var type="userMobileMsg.type";
		  var status="userMobileMsg.status";
		  if(type!=2&&status!=2){
			  //编辑器禁用
			  editor.EditorDocument.body.disabled = true;
		  }
	});

	//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="textareamobile"]',{
			resizeType : 1,
			filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
			allowPreviewEmoticons : false,
			allowUpload : true,//允许上传
			syncType : 'auto',
			width : '720px',
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

</script>
</head>
<body >
<!-- 公共右侧样式 -->
<div class="am-cf">
  <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">短信管理</strong> / <small>短信详情</small></div>
</div>
<hr/>
<!-- 公共右侧标题样式 结束-->
<div class="mt20">
	<div class="am-tabs">
	    <ul class="am-tabs-nav am-nav am-nav-tabs">
	      <li class="am-active"><a href="#tab1">短信详情</a></li>
	    </ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form class="am-from" action="${ctx}/admin/user/updateUserMsg"
						method="post" id="addPaperForm">
						<input name="userMobileMsg.id" id="id" type="hidden"
							value="${userMobileMsg.id}" />
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">短信内容</div>
							<div class="am-u-sm-8 am-u-md-4">
								<textarea class="am-form-field" readonly="readonly" name="userMobileMsg.content" id="content"> ${userMobileMsg.content }</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">创建时间</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input class="am-form-field" type="text" readonly="readonly"
									value="<fmt:formatDate value="${userMobileMsg.createTime }" type="both"/>" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">发送时间</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input class="am-form-field" type="text" readonly="readonly" id="sendDate"
									value="<fmt:formatDate value="${userMobileMsg.sendTime }" type="both"/>" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">发送状态</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${userMobileMsg.status==1 }">
									<input class="am-form-field" type="text" value="已发送" readonly="readonly" />
								</c:if>
								<c:if test="${userMobileMsg.status==2 }">
									<input class="am-form-field" type="text" value="未发送" readonly="readonly" />
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">发送人</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input class="am-form-field" type="text" value="${userMobileMsg.loginName }"
									readonly="readonly" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">接收人</div>
							<div class="am-u-sm-8 am-u-md-4">
								<textarea class="am-form-field" 
									readonly="readonly" >${userMobileMsg.mobile}</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">发送方式</div>
							<div class="am-u-sm-8 am-u-md-4">
								<c:if test="${userMobileMsg.type==1 }">
									<input class="am-form-field" type="text" value="正常发送" readonly="readonly" />
								</c:if>
								<c:if test="${userMobileMsg.type==2 }">
									<input class="am-form-field" type="text" value="定时发送" readonly="readonly" />
								</c:if>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
		                     <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
		                     <div class="am-u-sm-8 am-u-md-4">
		                         <button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返回</button>
		                     </div>
		                     <div class="am-u-sm-12 am-u-md-6"></div>
		                 </div>
					</form>
				</div>
			</div>
		</div>
</div>
<!-- /tab4 end -->
</body>
</html>
