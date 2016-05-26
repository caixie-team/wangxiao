<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
function clearImageText(){
	$("#replyIdHidden").val(0);
	$("#keyword").html("");
	$("#title").html("");
	$("#msgType").html("");
}
function addWeixinReply(){
	window.open('${cxt}/admin/weixin/defaultpage?page.currentPage=1',
			+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
		);
}

function weixinAdd(replyStr){
	var myArray=replyStr.split("#");
	$("#title").html(myArray[1]);
	$("#keyword").html(myArray[2]);
	var msgType=myArray[3];
	if(msgType==1){
		$("#msgType").html("文本");
		$("#title").html("--");
	}else if(msgType==2){
		$("#msgType").html("图文");
	}else if(msgType==3){
		$("#msgType").html("多图文");
	}  
	
	$("#replyIdHidden").val(myArray[0]);
}
function doSubmit(){
	var setId=$("#setId").val();
	var replyIdHidden=$("#replyIdHidden").val();
	$.ajax({
		url:"${ctx}/admin/default/update",
		type:"post",
		data:{"weixinSetReply.id":setId,"weixinSetReply.replyId":replyIdHidden},
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				dialogFun("提示","修改成功",0);
			}
		}
	});
}
</script>
</head>
<body>

<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">微信管理</strong> / <small>微信常规回复管理</small></div>
</div>
<hr/>
		<div class="mt20">
			<div class="commonWrap">
				<input type="hidden" value="${weixinSetReply.id}" id="setId"/>
				<input type="hidden" id="replyIdHidden" value="${weixinSetReply.replyId}"/>
				<c:if test="${type=='tolerate'}"><input type="hidden" name="weixinSetReply.type" value="tolerate"/></c:if>
				<c:if test="${type=='add'}"><input type="hidden" name="weixinSetReply.type" value="add"/></c:if>
				<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">					
					<div  class="am-tabs">
<div class="mt20">
	<div class="am-g">
		<div class="am-u-md-6">
			<div class="am-btn-toolbar">
				<div class="am-btn-group am-btn-group-xs">
					<a class="am-btn am-btn-warning" href="${ctx}/admin/default/reply/tolerate"><span><font>默认回复</font></span></a>
					<a  class="am-btn am-btn-danger"  href="${ctx}/admin/default/reply/add"><span><font>关注时回复</font></span></a> 
				</div>
			</div>
		</div>
	</div>
</div>
						<div class="mt20">
							<div id="tab1" class="am-tab-panel am-fade am-active am-in">
								<form class="am-form" data-am-validator>
									<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
										<thead>
											<tr>
													<th><span>类型</span></th>
													<th><span>关键字</span></th>
													<th><span>标题</span></th>
													<th><span>素材类型</span></th>
													<th><span>操作</span></th>
											</tr>
									</thead>
									<tbody id="tabS_web" align="center">
						<tr>
							<td>
								<c:if test="${type=='tolerate'}">默认回复</c:if>
								<c:if test="${type=='add'}">关注时回复</c:if>
							</td>
							<td id="keyword">${weixinReply.keyword }</td>
							<td id="title">${weixinReply.title }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
							<td id="msgType">
								<c:if test="${weixinReply.msgType==1 }">文本</c:if>
								<c:if test="${weixinReply.msgType==2 }">图文</c:if>
								<c:if test="${weixinReply.msgType==3 }">多图文</c:if>
							</td>
							<td>
								<a href="javascript:addWeixinReply()" style="cursor: pointer;" class="am-btn am-btn-danger">设置回复</a>
								<a href="javascript:clearImageText()" style="cursor: pointer;" class="am-btn am-btn-danger">删除</a>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="16">
							<a class="am-btn am-btn-danger" title="提 交" href="javascript:doSubmit()">修改</a>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
							</td>
						</tr>
					</tbody>
									</table>
								</form>
							</div>
						</div>
						
					</div>			
			</div>
		</div>
</body>
</html>