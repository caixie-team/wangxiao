<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>答疑列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	$("#anStatus").val('${answerQuestion.status}');
	$("#anIsReply").val('${answerQuestion.isReply}');
});

function checkdate(startTime,endTime){   
	 //得到日期值并转化成日期格式，replace(/\-/g, "\/")是根据验证表达式把日期转化成长日期格式，这样
	//再进行判断就好判断了
	    var sDate = new Date(startTime.replace(/\-/g, "\/"));
	    var eDate = new Date(endTime.replace(/\-/g, "\/"));
	    if(sDate > eDate){
	     dialogFun("题库答疑 ","结束日期不能小于开始日期",0);
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
	$("input[name='answerQuestion.beginTime']:first").attr("value", "");
	$("input[name='answerQuestion.endTime']:first").attr("value", "");
	$("#anStatus").val('-1');
	$("#anIsReply").val('-1');
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
	$("#anStatus").find('option').eq(0).attr('selected', true);
	$("#anIsReply").find('option').eq(0).attr('selected', true);
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
					$(obj).html("<span class='am-icon-pencil-square-o'>显示");
					$(obj).attr("onclick","updateStatus("+id+","+0+",this)");
					dialogFun("题库答疑 ","已使该提问隐藏",1);
				}else{
					$("#showStatus"+id).html("显示");
					$(obj).html("<span class='am-icon-pencil-square-o'>隐藏");
					$(obj).attr("onclick","updateStatus("+id+","+1+",this)");
					dialogFun("题库答疑 ","已使该提问显示",1);
				}
			}else{
				dialogFun("题库答疑 ","系统繁忙，请稍后重试",6,"");
			}
		}
	});
}
function deleteAnswer(id){
	dialogFun("题库答疑 ","确定要删除此项吗？",2,"javascript:deleteAnswerAjax("+id+")");
}
function deleteAnswerAjax(id){
		$.ajax({
			type:"post",
			dataType:"json",
			data:{"answerId":id},
			url:"${ctx}/admin/answer/deleteAnswer",
			async:false,
			success:function(result){
				if(result.message=="success"){
					$("#answer"+id).remove();
					dialogFun("题库答疑 ","删除成功",5,"");
					closeFun();
				}else{
					dialogFun("题库答疑 ","系统繁忙，请稍后重试",6,"");
					closeFun();
				}
			}
			
		});
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
	function checkAll(box){
		$("input[name=couIds]").prop("checked",box.checked);
	}
	
	
	
	
	
	
	
	function delCourseGroup(){
		  
	    var ids= document.getElementsByName("couIds");
	    var courseId="";
	    
		var checked=true;
		$(ids).each(function(){
			
			if($(this).prop("checked")){
				courseId+=this.value+",";
				
				checked=false;
			}
		});
	    
	      
	      if(courseId==null || courseId==''){
	      	dialogFun("题库答疑 ","请选择要删除的提问",6,"");
	      	return;
	      }
	      courseId=courseId.substring(0,courseId.length-1);
	      dialogFun("题库答疑 ","确认删除吗?",2,"javascript:delCourseGroupAjax('"+courseId+"')");
		  }
	function delCourseGroupAjax(courseId){
	  	$.ajax({
				url : baselocation + "/admin/answer/deleteAnswers",
				data : {"answerId":courseId},
				type : "post",
				dataType : "json",
				success : function(result) {
					
					
					window.location.reload();
					attr("checked",false);
				},
				error : function(error) {
				}
			});
	}
	
function answerInfo(id){
	var type = $("#answertype").val();
	window.location.href="${ctx }/admin/answer/answerInfo?answerId="+id+"&type="+type+"";
}	
	
	
</script>
</head>
<body  >
<div class="am-cf">
<div class="am-fl am-cf">
<strong class="am-text-primary am-text-lg">答疑管理</strong>
/
<small>题库答疑</small>	
</div>
</div>
<form action="${ctx}/admin/answer/answerQuestionList/${answerQuestion.courseType}" name="searchForm" id="searchForm" method="post" class="am-form">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input id="answerType" type="hidden" name="answerQuestion.type" value="${answerQuestion.type}"/>
<input type="hidden" id="subjectId" name="answerQuestion.subjectId" value="${answerQuestion.subjectId }"/>
<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户邮箱
				</div>
				<div class="am-u-sm-8 am-u-end">
				<input type="text" name="answerQuestion.email" value="${answerQuestion.email}" id="email" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					起始时间
				</div>
				<div class="am-u-sm-8 am-u-end">
				<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly="readonly" name="answerQuestion.beginTime" value="${answerQuestion.beginTime}" id="beginTime" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					结束时间
				</div>
				<div class="am-u-sm-8 am-u-end">
				<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly="readonly" name="answerQuestion.endTime" value="${answerQuestion.endTime}" id="endTime" />
				</div>
			</div>
 			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					专业
				</div>
				<div class="am-u-sm-8 am-u-end">
					<div id="doc-dropdown-justify-js" >
								  <div class="am-dropdown" id="doc-dropdown-js">
								    <input type="text" class="am-input-sm am-dropdown-toggle" id="subjectNameBtn" readonly="readonly"/>
								    <div class="am-dropdown-content">
								    	<div id="subject_ztreedemo" class="ztree" ></div>
								    </div>
								  </div>
					</div>
				</div>
			</div> 
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					状态
				</div>
				<div class="am-u-sm-8 am-u-end">
				<select data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"  style="display: none;"  name="answerQuestion.status" id="anStatus">
										<option value="-1">全部</option>
										<option value="0">显示</option>
										<option value="1">隐藏</option>
				</select>
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					是否回复
				</div>
				<div class="am-u-sm-8 am-u-end">
				<select data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"  style="display: none;"  name="answerQuestion.isReply" id="anIsReply">
									<option value="-1">全部</option>
									<option value="0">未回复</option>
									<option value="1">已回复</option>
				</select>
				</div>
			</div>
			<div class="clearfix"></div>
</div>
</div>
</form>
			<div class="mt20">
	    	<div class="am-g">
		      <div class="am-u-md-6">
		        <div class="am-btn-toolbar">
		          <div class="am-btn-group am-btn-group-xs">
		          <p class="fl czBtn"></p>
										<span><a class="am-btn am-btn-success" href="javascript:delCourseGroup();" title="批量删除"><span class="am-icon-trash-o"></span>批量删除</a></span>
		          </div>
		        </div>
		      </div>
		      <div class="am-u-sm-12 am-u-md-3">
		        <div class="am-input-group am-input-group-sm">
		          <span class="am-input-group-btn">
		          <button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">
					            	  <span class="am-icon-search"></span> 搜索
					             </button>
				<input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
		          </span>
		        </div>
		      </div>
		    </div>
	    </div>
			<!-- 	<div class="mt10 clearfix">
					
				</div> -->
<div class="mt20">
<div class="doc-example">
<div class="am-scrollable-horizontal">
<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
								<tr>
								    <th>
						                <label class="am-checkbox">
						                <input type="checkbox" data-am-ucheck onclick="checkAll(this)"/>
						                </label>
						            </th>
									<th>ID</th>
									<th><span>邮箱</span></th>
									<th><span>内容</span></th>
									<th><span>状态</span></th>
									<th><span>回复次数</span></th>
									<th><span>课程名称</span></th>
									<th><span>章节名称</span></th>
									<th><span>添加时间</span></th>
									<th><span>是否回复</span></th>
									<th><span>操作</span></th>
								</tr>
							</thead>
			<tbody id="tabS_02" align="left">
			<c:if test="${answerQuestionList.size()>0}">
			<c:forEach  items="${answerQuestionList}" var="answer" >
				<tr id="answer${answer.id }">
				<td>
                       <label class="am-checkbox">
                       	<input type="checkbox" data-am-ucheck name="couIds" value="${answer.id}"/>
					   </label>
                     </td>
					 <td>${answer.id}</td>
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
					<td  class="c_666 czBtn" align="left">
					<c:if test="${answer.status==0 }">
					<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="updateStatus(${answer.id},1,this)">
						<span class="am-icon-pencil-square-o"></span>
						隐藏
						</button>
					</c:if>
					<c:if test="${answer.status==1 }">
					<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="updateStatus(${answer.id},0,this)">
						<span class="am-icon-pencil-square-o"></span>
						显示
						</button>
					</c:if>
					    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"  type="button" onclick="answerInfo(${answer.id})">
						<span class="am-icon-pencil-square-o"></span>
						回复
						</button>
						<input type="hidden" value="${answer.type}" id="answertype"/>
						<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"  type="button" onclick="deleteAnswer(${answer.id})">
						<span class="am-icon-trash-o"></span>
						删除
						</button>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${answerQuestionList.size()==0||answerQuestionList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
						<center>
						<big>
							<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
								<span class="vam ml10">还没有答疑信息！</span>
						</big>
						</center>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
					</table>
					</div>
					</div>
					</div>
		<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
				
</body>
</html>
