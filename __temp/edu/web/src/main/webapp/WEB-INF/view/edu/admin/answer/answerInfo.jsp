<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>答疑列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
function formSubmit(){
	var answerId=$("#answerId").val();
	var content=$("#content").val();
	if(content==null || content==""){
		$("#contentMessage").html("回复内容不得为空");
		return;
	}else if(content.length>255){
		$("#contentMessage").html("回复内容最长为255个字");
		return;
	}
	$("#contentMessage").html("");
	$.ajax({
		url:'${ctx}/admin/answer/addAnswerReply',
		type:'post',
		dataType:'json',
		data:{"answerReply.content":content,"answerReply.answerId":answerId},
		success:function (result){
			if(result.success){
				window.location="${ctx}/admin/answer/answerInfo?answerId="+answerId+"&type=${answer.type}";
			}else{
				alert("系统异常，请重新提交");
			}
		}
	});
}

function updateStatus(id,status,answerId,obj){
	$.ajax({
		type:"post",
		dataType:"json",
		data:{"answerReply.id":id,"answerReply.status":status,"answerReply.answerId":answerId},
		url:"${ctx}/admin/answer/updateReplyStatus",
		async:false,
		success:function(result){
			if(result.message=="success"){
				if(status==1){
					$("#status"+id).html("隐藏");
					$(obj).html("显示");
					$(obj).attr("onclick","updateStatus("+id+","+0+","+answerId+",this)");
					alert("已使该回答隐藏");
				}else{
					$("#status"+id).html("显示");
					$(obj).html("隐藏");
					$(obj).attr("onclick","updateStatus("+id+","+1+","+answerId+",this)");
					alert("已使该回答显示");
				}
			}else{
				alert("系统繁忙，请稍后重试");
			}
		}
		
	});
}
function updateAnswer(id){
	var content=$("#answerConetent").val();
	if(content==null || content==""){
		$("#answerContentMessage").html("内容不得为空");
		return;
	}else if(content.length>255){
		$("#answerContentMessage").html("内容最长为255个字");
		return;
	}
	$("#answerContentMessage").html("");
	$.ajax({
		type:"post",
		dataType:"json",
		data:{"answerQuestion.id":id,"answerQuestion.content":content},
		url:"${ctx}/admin/answer/updateAnswer",
		async:false,
		success:function(result){
			if(result.message=="success"){
				alert("修改成功");
			}else{
				alert("系统繁忙，请稍后重试");
			}
		}
		
	});
}

function updateReply(id){
	var content=$("#replyContent"+id).val();
	if(content==null || content==""){
		$("#replyContentMessage"+id).html("回复内容不得为空");
		return;
	}else if(content.length>255){
		$("#replyContentMessage"+id).html("回复内容最长为255个字");
		return;
	}
	$("#replyContentMessage"+id).html("");
	$.ajax({
		url:'${ctx}/admin/answer/updateReply',
		type:'post',
		dataType:'json',
		data:{"answerReply.content":content,"answerReply.id":id},
		success:function (result){
			if(result.success){
				alert("修改成功");
				//window.location="${ctx}/admin/answer/answerInfo?answerId="+id+"&type=${answer.type}";
			}else{
				alert("系统异常，请重新提交");
			}
		}
	});
}
function deleteReply(id,answerId){
	if(confirm("确定删除该回复？")){
		$.ajax({
			url:'${ctx}/admin/answer/deleteReply',
			type:'post',
			dataType:'json',
			data:{"answerReply.id":id,"answerReply.answerId":answerId},
			success:function (result){
				if(result.success){
					window.location="${ctx}/admin/answer/answerInfo?answerId="+answerId+"&type=${answer.type}";
				}else{
					alert("系统异常，请重新提交");
				}
			}
		});
	}else{
		return;
	}
}
</script>
</head>
<body  >
<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>查看答疑详情并回复</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
			
			<table class="commonTab01" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<thead>
					<tr>
						<th align="left" colspan="2"><span> 答疑详情<tt class="c_666 ml20 fsize12">
						</tt>
						</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="100%" colspan="3">
						<div style="margin-left: 15px;">
							<span style="margin-right: 15px;">
								<b><font size="2">分类：</font>
								</b>
								<c:if test="${answer.type=='course' }">
									课程
								</c:if>
								<c:if test="${answer.type=='examPaper' }">
									考试
								</c:if>
							</span>
							<span style="margin-right: 10px;">
								<b><font size="2">提问者：</font>
								</b>
								<c:if test="${answer.nickName!=null &&  answer.nickName!=''}">
										${answer.nickName}
									</c:if>
									<c:if test="${answer.nickName==null ||  answer.nickName==''}">
										${answer.email}
									</c:if>
							</span>
							<span style="margin-right: 10px;">
								<b><font size="2">提问时间：</font>
								</b><fmt:formatDate type="both" value="${answer.addTime}" ></fmt:formatDate>
							</span>
							<span style="margin-right: 10px;">
								<b><font size="2">回复次数：</font>
								</b>${answer.replyCount}
							</span>
							
							<span style="margin-right: 10px;">
								<b><font size="2">老师是否回复：</font>
								</b><c:if test="${answer.isReply==0 }">
									未回复
								</c:if>	
								<c:if test="${answer.isReply==1 }">
									已回复
								</c:if>	
							</span>
							
							<span style="margin-right: 10px;">
								<b><font size="2">状态：</font>
								</b><c:if test="${answer.status==0 }">
									显示
								</c:if>	
								<c:if test="${answer.status==1 }">
									隐藏
								</c:if>
							</span>
						</div>			
						</td>
						
					</tr>
					<c:if test="${answer.type=='course' }">
						<tr>
							<td width="10%" align="center"><font color="red"></font>疑问课程</td>
							<td width="90%" colspan="2">${answer.parentName}</td>
						</tr>
						<tr>
							<td width="10%" align="center"><font color="red"></font>疑问章节</td>
							<td width="90%" colspan="2">${answer.sonName}</td>
						</tr>
					</c:if>
					<c:if test="${answer.type=='examPaper' }">
						<tr>
							<td width="10%" align="center"><font color="red"></font>疑问试卷</td>
							<td width="90%" colspan="2">${answer.parentName}</td>
						</tr>
						<tr>
							<td width="10%" align="center"><font color="red"></font>疑问试题</td>
							<td width="90%" colspan="2">${answer.sonName} <span style="float: right;margin-right: 30px;"><a href="${ctx }/quest/parse/${answer.sonId}" class="ml10 btn smallbtn btn-y" target="_blank" title="问题详情">问题详情</a></span></td>
						</tr>
					</c:if>
					<tr>
						<td width="10%" align="center"><font color="red"></font>提问内容</td>
						<td width="80%">
							 <textarea style="width: 100%;height: 100px;" id="answerConetent">${answer.content }</textarea>
							 <span id="answerContentMessage" style="color:red;"></span>
						</td>
						<td width="10%">
							<a class="ml10 btn smallbtn btn-y" title="显示" onclick="updateAnswer(${answer.id})" href="javaScript:void(0)">修改</a>
						</td>
					</tr>
					<c:forEach var="reply" items="${replyList }" varStatus="index">
					<tr>
						<td width="10%" align="center"><font color="red"></font>回复${index.index+1 }:</td>
						<td width="80%">
							<div align="left">
								<span style="margin-right: 10px;">
									<b>
									<font size="2">回复者：</font>
									</b>
										${reply.showName }
								</span>
								
								<span style="margin-right: 10px;">
									<b>
									<font size="2">回复时间：</font>
									</b>
									<fmt:formatDate type="both" value="${reply.addTime}" ></fmt:formatDate>
								</span>
								<span style="margin-right: 10px;">
									<b>
									<font size="2">状态：</font>
									</b>
									<span id="status${reply.id }">
										<c:if test="${reply.status==0 }">
											显示
										</c:if>	
										<c:if test="${reply.status==1 }">
											隐藏
										</c:if>
									</span>
								</span>
								<br/>
								<textarea style="width: 100%;height: 100px;" id="replyContent${reply.id }">${reply.content }</textarea>
								<span id="replyContentMessage${reply.id }" style="color:red;"></span>
							</div>
						</td>
						<td width="10%">
							<div>
							<br/>
							<c:if test="${reply.status==0 }">
								<a class="ml10 btn smallbtn btn-y" title="隐藏" onclick="updateStatus(${reply.id},1,${answer.id },this)" href="javaScript:void(0)">隐藏</a><br/><br/>
							</c:if>
							<c:if test="${reply.status==1 }">
								<a class="ml10 btn smallbtn btn-y" title="显示" onclick="updateStatus(${reply.id},0,${answer.id },this)" href="javaScript:void(0)">显示</a><br/><br/>
							</c:if>
								<a class="ml10 btn smallbtn btn-y" title="修改" onclick="updateReply(${reply.id})" href="javaScript:void(0)">修改</a><br/><br/>
								<a class="ml10 btn smallbtn btn-y" title="删除" onclick="deleteReply(${reply.id},${answer.id })" href="javaScript:void(0)">删除</a>
							</div>
						</td>
						</tr>
					</c:forEach>
					<tr>
						<td width="10%" align="center"><font color="red">*</font>回复答疑：</td>
						<td width="90%" colspan="2">
						<input type="hidden" name="answerReply.answrId" id="answerId" value="${answer.id }"/>
							 <textarea name="answerReply.content" id="content" style="width: 100%;height: 100px;"></textarea>
							 <br/>
							 <span id="contentMessage" style="color:red;"></span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="3">
						<input type="hidden" name="" value="" /> 
						<input class="btn btn-danger" type="button" value="提交" onclick="formSubmit()"/> 
						<input class="btn ml10" type="button" onclick="history.go(-1)" value="返回" /></td>
					</tr>
				</tbody>
			</table>
		
	</div>
</div>
</body>
</html>
