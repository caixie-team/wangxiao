<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>话题列表</title>
<script type="text/javascript">
function cancel(){
	$("#appWebsiteCourseDetailName").val("");
	$("#isavaliable").val("");
	$("#isavaliable").find('option').eq(0).attr('selected', true);
}
function addCourseReload(){
	window.location.reload();
}
function del(id){
	dialogFun("话题列表","确定删除该话题吗？",2,"javascript:_del("+id+")");
}
function _del(id){
	$.ajax({
		url:"/admin/appTopic/delAppTopic/"+id,
		type:"post",
		dataType:"json",
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="true"){
				$("input[name=courseIds]").prop('checked', false);
				window.location.reload();
			}else{
				dialogFun("话题列表","系统繁忙",0);
			}
		}

	});
}
function hiddenAndDefault(id,is){
	$.ajax({
		url:"/admin/appTopic/updateAppTopicState",
		data:{"id":id,"is":is},
		type:"post",
		dataType:"json",
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="true"){
				dialogFun("话题列表","操作成功",1,"javascript:window.location.reload()");
			}else{
				dialogFun("话题列表","系统繁忙",0);
			}
		}
		
	});
}
function allCheck(cb) {
	$("input[name=courseIds]").prop('checked', cb.checked);
}
function delArticlebatch(){
	var artIds=document.getElementsByName("courseIds");
	var str="";
	var checked=true;
	$(artIds).each(function(){
		if($(this).prop("checked")){
			str+=this.value+",";
			checked=false;
		}
	});
	str=str.substring(0,str.length-1);
	if(checked){
		dialogFun("话题列表","请至少选择一条信息",0);
		return;
	}


	dialogFun("话题列表","确定删除选中的话题吗？",2,"javascript:_delArticlebatch("+str+")")
}

	function _delArticlebatch(str){
		$.ajax({
			url:"/admin/appTopic/delAppTopic/"+str,
			type:"post",
			dataType:"json",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="true"){
					$("input[name=courseIds]").prop('checked', false);
					window.location.reload()
				}else{
					dialogFun("话题列表","系统繁忙",0);
				}
			}

		});
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">话题管理</strong> / <small>话题列表</small></div>
</div>
<hr>
<div class="mt20 am-padding admin-content-list">
	<form action="${ctx}/admin/appTopic/queryAppTopic" class="am-form" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<input type="hidden" name="user.sysGroupId" id="userGroupId" value="${user.sysGroupId}"/>
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-6 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					话题标题
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryAppTopicCondition.topicTitle" id="appWebsiteCourseDetailName" value="${queryAppTopicCondition.topicTitle}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					状态
				</div>
				<div class="am-u-sm-8 am-u-end">
					<select name="queryAppTopicCondition.states" id="isavaliable" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="">--请选择--</option>
						<option value="DEFAULT" <c:if test="${queryAppTopicCondition.states=='DEFAULT' }"> selected="selected"</c:if>>默认</option>
						<option value="HIDDEN" <c:if test="${queryAppTopicCondition.states=='HIDDEN' }"> selected="selected"</c:if>>隐藏</option>
					</select>
				</div>
			</div>
			<div class="mt20 clear"></div>
		</div>
	</form>
</div><div class="mt20">
	<div class="am-g">
		<div class="am-u-md-6">
			<div class="am-btn-toolbar">
				<div class="am-btn-group am-btn-group-xs">
					<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/appTopic/toAdd'" title="新建话题"><span class="am-icon-plus"></span> 新建话题</button>
					<button class="am-btn am-btn-success" type="button" onclick="delArticlebatch()" title="批量删除"><span class="am-icon-trash-o"></span> 批量删除</button>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-4">
			<div class="am-input-group am-input-group-sm">
				<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">查询</button>
				<button type="button" class="am-btn am-btn-danger" onclick="cancel()">清空</button>
			</div>
		</div>
	</div>
</div>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal am-scrollable-vertical">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
				<tr>
					<th width="8%">
						<label class="am-checkbox">
							<input type="checkbox" onclick="allCheck(this)" data-am-ucheck>&nbsp;ID
						</label>
					</th>
					<th><span>话题标题</span></th>
					<th><span>发布人</span></th>
					<th><span>发布时间</span></th>
					<th><span>状态</span></th>
					<th><span>操作</span></th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${appTopicList.size()>0}">
					<c:forEach  items="${appTopicList}" var="appTopic" >
						<tr>
							<td>
								<label class="am-checkbox">
									<input type="checkbox" name="courseIds" value="${appTopic.topicId}" data-am-ucheck>&nbsp;${appTopic.topicId }
								</label>
							</td>
							<td>${appTopic.topicTitle }</td>
							<td>${appTopic.userName }</td>
							<td><fmt:formatDate type="both" value="${appTopic.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<c:if test="${appTopic.states=='DEFAULT'}">
									<a class="am-btn am-btn-secondary" href="javascript:hiddenAndDefault(${appTopic.topicId},'HIDDEN')" >默认</a>
								</c:if>
								<c:if test="${appTopic.states=='HIDDEN'}">
									<a class="am-btn am-btn-warning" href="javascript:hiddenAndDefault(${appTopic.topicId},'DEFAULT')" >隐藏</a>
								</c:if>
							</td>
							<td>
								<a class="am-btn am-btn-primary" href="${cxt}/admin/appTopic/toUpdate/${appTopic.topicId}" >修改</a>
								<a class="am-btn am-btn-danger" href="javascript:del(${appTopic.topicId})" >删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${appTopicList.size()==0||appTopicList==null}">
					<tr>
						<td colspan="9">
							<div data-am-alert=""
								 class="am-alert am-alert-secondary mt20 mb50">
								<center>
									<big> <i class="am-icon-frown-o vam"
											 style="font-size: 34px;"></i> <span class="vam ml10">还没有app话题！</span></big>
								</center>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
		<!-- /pageBar end -->
	</div>
</div>
</body>
</html>