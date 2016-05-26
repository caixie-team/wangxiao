<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>答疑列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
 <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	$( "#beginTime,#endTime" ).datepicker(
			{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd "
	   		});
	$("#anStatus").val('${answerQuestion.status}');
	$("#anIsReply").val('${answerQuestion.isReply}');
});

function checkdate(startTime,endTime){   
	 //得到日期值并转化成日期格式，replace(/\-/g, "\/")是根据验证表达式把日期转化成长日期格式，这样
	//再进行判断就好判断了
	    var sDate = new Date(startTime.replace(/\-/g, "\/"));
	    var eDate = new Date(endTime.replace(/\-/g, "\/"));
	    if(sDate > eDate){
	     alert("结束日期不能小于开始日期");
	     return false;
	    }
	    return true;
	  }
function submitSearch(){
	if(checkdate($("#beginTime").val(),$("#endTime").val())){
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
}
function clean(){
	$("#email").val("");
	$("#beginTime").val("");
	$("#endTime").val("");
	$("#anStatus").val('-1');
	$("#anIsReply").val('-1');
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function updateStatus(id,status,obj){
	$.ajax({
		type:"post",
		dataType:"json",
		data:{"answerId":id,"status":status},
		url:"${ctx}/admin/answer/updateAnswerStatus",
		async:false,
		success:function(result){
			if(result.message=="success"){
				if(status==1){
					$("#showStatus"+id).html("隐藏");
					$(obj).html("显示");
					$(obj).attr("onclick","updateStatus("+id+","+0+",this)");
					alert("已使该提问隐藏");
				}else{
					$("#showStatus"+id).html("显示");
					$(obj).html("隐藏");
					$(obj).attr("onclick","updateStatus("+id+","+1+",this)");
					alert("已使该提问显示");
				}
			}else{
				alert("系统繁忙，请稍后重试");
			}
		}
		
	});
}
function deleteAnswer(id){
	if(confirm("确定删除该提问？")){
		$.ajax({
			type:"post",
			dataType:"json",
			data:{"answerId":id},
			url:"${ctx}/admin/answer/deleteAnswer",
			async:false,
			success:function(result){
				if(result.message=="success"){
					$("#answer"+id).remove();
				}else{
					alert("系统繁忙，请稍后重试");
				}
			}
			
		});
	}else{
		return;
	}
	
}

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
</script>
</head>
<body  >
<form action="${ctx}/admin/answer/answerQuestionList/${answerQuestion.courseType}" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input id="answerType" type="hidden" name="answerQuestion.type" value="${answerQuestion.type}"/>
<input type="hidden" id="subjectId" name="answerQuestion.subjectId" value="${answerQuestion.subjectId }"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span><c:if test="${answerQuestion.courseType == 1}">答疑</c:if><c:if test="${answerQuestion.courseType == 2}">咨询</c:if>管理</span> &gt; 
				<span>课程<c:if test="${answerQuestion.courseType == 1}">答疑</c:if><c:if test="${answerQuestion.courseType == 2}">咨询</c:if></span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
				<div class="capHead">
					<div class="fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>用户邮箱：</font></span>
								<input type="text" name="answerQuestion.email" value="${answerQuestion.email}" id="email" />
								
								<span class="ddTitle"><font>专业：</font></span>
                                  <input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
                                  <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
                                      <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
                                  </div>
								<span class="ddTitle"><font>状态：</font></span>
								<select name="answerQuestion.status" id="anStatus">
									<option value="-1">全部</option>
									<option value="0">显示</option>
									<option value="1">隐藏</option>
								</select>
							</li>
							<li>
								<span class="ddTitle"><font>起始时间：</font></span>
								<input type="text" name="answerQuestion.beginTime" value="${answerQuestion.beginTime}" id="beginTime" />
								<span class="ddTitle"><font>结束时间：</font></span>
								<input type="text" name="answerQuestion.endTime" value="${answerQuestion.endTime}" id="endTime" />
								<span style="color:red;" id="message"></span>
								<span class="ddTitle"><font>是否回复：</font></span>
								<select name="answerQuestion.isReply" id="anIsReply">
									<option value="-1">全部</option>
									<option value="0">未回复</option>
									<option value="1">已回复</option>
								</select>
							</li>
							<li style="float:right;">
								<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
								<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
							</li>
						</ul>
						
					</div>
					<div class="clearfix"></div>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="3%"><span>&nbsp;ID</span></th>
					<th width="11%"><span>邮箱</span></th>
					<th width="10%"><span>内容</span></th>
					<th width="4%"><span>状态</span></th>
					<th width="5%"><span>回复次数</span></th>
					<th width="12%"><span>课程名称</span></th>
					<th width="12%"><span>章节名称</span></th>
					<th width="9%"><span>添加时间</span></th>
					<th width="5%"><span>是否回复</span></th>
					<th width="9%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${answerQuestionList.size()>0}">
			<c:forEach  items="${answerQuestionList}" var="answer" >
				<tr id="answer${answer.id }">
					<td>&nbsp;${answer.id }</td>
					<td>${answer.email}</td>
					<td>${fn:substring(answer.content,0,10)}</td>
					<td>
						<span id="showStatus${answer.id}">
						<c:if test="${answer.status==0 }">显示</c:if>
						<c:if test="${answer.status==1 }">隐藏</c:if>
						</span>
					</td>
					<td>${answer.replyCount}</td>
					<td>${answer.parentName }</td>
					<td>${answer.sonName }</td>
					<td><fmt:formatDate type="both" value="${answer.addTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:if test="${answer.isReply==0 }">未回复</c:if>
						<c:if test="${answer.isReply==1 }">已回复</c:if>
					</td>
					<td  class="c_666 czBtn" align="center">
					<c:if test="${answer.status==0 }">
						<a class="btn smallbtn btn-y" title="隐藏" onclick="updateStatus(${answer.id},1,this)" href="javaScript:void(0)">隐藏</a>
					</c:if>
					<c:if test="${answer.status==1 }">
						<a class="btn smallbtn btn-y" title="显示" onclick="updateStatus(${answer.id},0,this)"href="javaScript:void(0)">显示</a>
					</c:if>
						<a class="btn smallbtn btn-y" title="回复" href="${ctx }/admin/answer/answerInfo?answerId=${answer.id}&type=${answer.type}">回复</a>
						<a class="btn smallbtn btn-y" title="删除" onclick="deleteAnswer(${answer.id})" href="javaScript:void(0)">删除</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${answerQuestionList.size()==0||answerQuestionList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有答疑信息！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
