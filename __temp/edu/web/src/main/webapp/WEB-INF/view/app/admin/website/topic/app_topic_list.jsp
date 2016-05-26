<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
function cancel(){
	$("#appWebsiteCourseDetailName").val("");
	$("#isavaliable").val("");
}
function addCourseReload(){
	window.location.reload();
}
function del(id){
	if(confirm("确定删除该话题吗？")){
		$.ajax({
			url:"/admin/appTopic/delAppTopic/"+id,
			type:"post",
			dataType:"json",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="true"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("系统繁忙。");
				}
			}
			
		});
	}
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
				alert("操作成功");
				window.location.reload();
			}else{
				alert("系统繁忙。");
			}
		}
		
	});
}
function allCheck(cb)
{
	$("input[name=courseIds]").prop('checked', cb.checked);
}
function delArticlebatch(){//删除资讯
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
		alert("请至少选择一条信息");
		return;
	}
	if(confirm("确定删除选中的话题吗？")){
		$.ajax({
			url:"/admin/appTopic/delAppTopic/"+str,
			type:"post",
			dataType:"json",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="true"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("系统繁忙。");
				}
			}
			
		});
	}
}
</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>话题管理</span> &gt; <span>话题列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/appTopic/queryAppTopic" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
						<div class="capHead">
							<div class="w50pre fl">
										<span class="ddTitle"><font>话题标题：</font></span>
										<input type="text" name="queryAppTopicCondition.topicTitle" id="appWebsiteCourseDetailName" value="${queryAppTopicCondition.topicTitle}"/>
										<span class="ddTitle"><font>状态：</font></span>
										<select name="queryAppTopicCondition.states" id="isavaliable">
											<option value="">--请选择--</option>
											<option value="DEFAULT" <c:if test="${queryAppTopicCondition.states=='DEFAULT' }"> selected="selected"</c:if>>默认</option>
											<option value="HIDDEN" <c:if test="${queryAppTopicCondition.states=='HIDDEN' }"> selected="selected"</c:if>>隐藏</option>
										</select>
										<input type="submit"  class="btn btn-danger" value="查询" name=""/>
										<input type="button"  class="btn btn-danger" value="清空" name="" onclick="cancel()"/>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn">
								<span class="ml10"><a href="/admin/appTopic/toAdd"><em class="icon14 new">&nbsp;</em>新建话题</a></span>
									<span class="ml10"><a href="javascript:delArticlebatch();" title="批量删除"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
							</p>
							<p class="fr c_666"><span>话题列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
						</div>
					</caption>
					
				<thead>
					<tr>
						<th width="8%"><span><input type="checkbox" onclick="allCheck(this)">ID</span></th>
                           <th><span>话题标题</span></th>
                           <th><span>发布人</span></th>
                           <th><span>发布时间</span></th>
                           <th><span>状态</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${appTopicList.size()>0}">
						<c:forEach  items="${appTopicList}" var="appTopic" >
						<tr>
							<td><input type="checkbox" name="courseIds" value="${appTopic.topicId}"/>&nbsp;${appTopic.topicId }</td>
							<td>${appTopic.topicTitle }</td>
							<td>${appTopic.userName }</td>
							<td><fmt:formatDate type="both" value="${appTopic.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                            	<c:if test="${appTopic.states=='DEFAULT'}"><a class="ml10 btn smallbtn btn-y" href="javascript:hiddenAndDefault(${appTopic.topicId},'HIDDEN')" >默认</a></c:if>
                            	<c:if test="${appTopic.states=='HIDDEN'}"><a class="ml10 btn smallbtn btn-y" href="javascript:hiddenAndDefault(${appTopic.topicId},'DEFAULT')" >隐藏</a></c:if>
                         	</td>
							<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/appTopic/toUpdate/${appTopic.topicId}" >修改</a>
								<a class="ml10 btn smallbtn btn-y" href="javascript:del(${appTopic.topicId})" >删除</a>
							</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${appTopicList.size()==0||appTopicList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有app话题！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			</form>
			<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div><!-- /commonWrap -->
	</div>
</body>
</html>