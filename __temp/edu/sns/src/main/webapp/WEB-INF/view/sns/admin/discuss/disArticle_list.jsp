<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台小组话题列表</title>
<script type="text/javascript">
$(function(){
	if('${disArticle.artClassifyId}'==null||'${disArticle.artClassifyId}'==''){
		$("#artClassifyId").val(0);
	}else{
		$("#artClassifyId").val('${disArticle.artClassifyId}');
	}
});
function submitform(){
	$("#pageCurrentPage").val(1);//页数为1
	$("#searchForm").submit();//提交表单
}
function clean(){
	$("#cusId,#groupName,#showName,#title").val('');
	$("#artClassifyId").val(0);
}
function permitChat(groupId,artId,obj){//允许回应
	$.ajax({
		type:"post",
		dataType:"json",
		data:{"disArticle.groupId":groupId,"disArticle.id":artId,"type":0},
		url:"${ctx}/admin/disArticle/updatestatus",
		async:false,
		success:function(result){
			if(result.message=="success"){
				$("#disAllowStatus"+artId).html("允许回应");
				$(obj).html("禁止回应");
				$(obj).attr("onclick","prohibitChat("+groupId+","+artId+",this)");
				alert("已允许小组成员回应该话题");
				}else{
					alert("系统繁忙，请稍后重试");
				}
		}
		
	});
	
}
function prohibitChat(groupId,artId,obj){//禁止回应
	$.ajax({
		type:"post",
		dataType:"json",
		data:{"disArticle.groupId":groupId,"disArticle.id":artId,"type":1},
		url:"${ctx}/admin/disArticle/updatestatus",
		async:false,
		success:function(result){
			if(result.message=="success"){
				$("#disAllowStatus"+artId).html("禁止回应");
				$(obj).html("允许回应");
				$(obj).attr("onclick","permitChat("+groupId+","+artId+",this)");
				alert("已禁止小组成员回应该话题");
			}else{
				alert("系统繁忙，请稍后重试");
			}
		}
		
	});
}
//取消置顶
function disCancel(id,obj){
	$.ajax({
		data:{"articleId":id},
		dataType:"json",
		type:"post",
		url:"${ctx}/admin/disArticle/cancel",
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
				$("#disTopStatus"+id).html("未置顶");
				$(obj).html("添加置顶");
				$(obj).attr("onclick","distop("+id+",this)");
				alert("取消成功");
			}
			if(result.message=="false"){
				alert("置顶失败，刷新重试");
			}
		}
	});

}
//置顶
function distop(id,obj){
		$.ajax({
			data:{"articleId":id},
			dataType:"json",
			type:"post",
			url:"${ctx}/admin/disArticle/top",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					$("#disTopStatus"+id).html("置顶");
					$(obj).html("取消置顶");
					$(obj).attr("onclick","disCancel("+id+",this)");
					alert("置顶成功");
				}
				if(result.message=="false"){
					alert("取消失败，刷新重试");
				}
			}
			
		});
}
//删除
function delarticle(artId,groupId){
	if(confirm("确定删除该小组话题?")){
		$("#del").attr("href","javascript:deldisarticle("+artId+","+groupId+")");
	}
}
function deldisarticle(id,groupId){//删除小组文章
	$.ajax({
		url:"${ctx}/dis/delart",
		type:"post",
		dataType:"json",
		data:{"disArticle.id":id,"disArticle.groupId":groupId},//根据小组文章id 文章id删除该文章
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
				alert("删除成功");
				location.reload();
			}else{
				alert("请刷新重试",5);//删除失败稍后重试
				return false;
			}
		}
	});
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>小组列表</span>
		</h4>
	</div>
	<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/disArticle/getDisArticleList" name="searchForm" id="searchForm" method="post">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
					<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
						<caption>
							<div class="capHead">
							
							<div class="w50pre fl">
									<ul class="ddBar">
										<li>
											<span> <font>作者ID：</font></span>
											 <span> <input type="text" name="disArticle.cusId" value="${disArticle.cusId}" id="cusId"></span> </li>
										 <li>
											<span> <font>作&nbsp;&nbsp;者：</font></span>
										 	<span> <input type="text" name="disArticle.showName" value="${disArticle.showName}" id="showName"></span> </li>
										<li>
											<span> <font>分&nbsp;&nbsp;类：</font> </span>
											<select name="disArticle.artClassifyId" id="artClassifyId">
												<option value="0">--请选择分类--</option>
												<c:forEach items="${disGroupClassify}" var="dgl">
													<option value="${dgl.id}">${dgl.name}</option>
												</c:forEach>
											</select>
										</li>
									</ul>
							</div>
							<div class="w50pre fl">
									<ul class="ddBar">
										<li>
											<span> <font>小组名称：</font>
											</span> <span> <input type="text" name="disArticle.groupName" value="${disArticle.groupName }" id="groupName">
											</span> 
										</li>
										<li>
											<span> <font>题&nbsp;&nbsp;&nbsp;&nbsp;目：</font>
											</span> <span> <input type="text" name="disArticle.title" value="${disArticle.title }" id="title">
											</span> 
										</li>
										<li>
											<input class="btn btn-danger" type="button" value="查询" onclick="submitform()">
											<input class="btn ml10" type="button" value="重置" onclick="clean()">
										</li>
									</ul>
							</div>
							<div class="clear"></div>
							</div>
						</caption>

						<thead>
							<tr>
								<th><span>ID</span></th>
								<th><span>小组名称</span></th>
								<th><span>作者</span></th>
								<th><span>分类</span></th>
								<th><span>题目</span></th>
								<th><span>创建时间</span></th>
								<th><span>浏览数量</span></th>
								<th><span>回复数量</span></th>
								<th><span>是否置顶</span></th>
								<th><span>类型</span></th>
								<th><span>状态</span></th>
								<th><span>喜欢人数</span></th>
								<th><span>推荐人数</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02">
						<c:if test="${fn:length (disArticleList)==0 }">
							<tr class="">
								<td align="center" colspan="16">
									<div class="tips">
										<span>对不起，没有该小组话题信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
							<c:forEach items="${disArticleList }" var="dis">
								<tr id="rem${dis.id }" class="">
									<td align="center">${dis.id }</td>
									<td align="center">${dis.groupName }</td>
									<td align="center">${dis.showName }</td>
									<td align="center">${dis.classifyName }</td>
									<td align="center">${fn:substring(dis.title,0,20)}</td>
									<td align="center"><fmt:formatDate value="${dis.createTime}" type="both" ></fmt:formatDate></td>
									<td align="center">${dis.countView}</td>
									<td align="center">${dis.reNum}</td>
									<td align="center" id="disTopStatus${dis.id}">
										<c:if test="${dis.top==1 }">
											置顶
										</c:if>
										<c:if test="${dis.top==0 }">
											未置顶
										</c:if>
									</td>
									<td align="center">
										<c:if test="${dis.selType==0 }">
											话题
										</c:if>
										<c:if test="${dis.selType==1 }">
											线下活动
										</c:if>
									</td>
									<td align="center" id="disAllowStatus${dis.id}">
										<c:if test="${dis.status==0 }">
											允许回应
										</c:if>
										<c:if test="${dis.status==1 }">
											禁止回应
										</c:if>
									</td>
									<td align="center">${dis.favorCount }</td>
									<td align="center">${dis.recomCount }</td>
									<td align="center">
										<a class="btn smallbtn btn-y" href="${ctx }/admin/disArticle/artail/${dis.id}/${dis.groupId}">修改</a>
										<c:if test="${dis.top==0}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="distop(${dis.id},this)" title="添加置顶">添加置顶</a>
										</c:if>
										<c:if test="${dis.top==1}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)" title="取消置顶" onclick="disCancel(${dis.id},this)">取消置顶</a>
										</c:if>
										<c:if test="${dis.status==0}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)" title="禁止回应" onclick="prohibitChat(${dis.groupId},${dis.id },this)">禁止回应</a>
										</c:if>
										<c:if test="${dis.status==1}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)" title="允许回应" onclick="permitChat(${dis.groupId},${dis.id},this)">允许回应</a>
										</c:if>
										<a class="btn smallbtn btn-y" href="javascript:void(0)" id="del" title="删除" onclick="delarticle('${dis.id}','${dis.groupId }',this)">删除</a>
										<a class="btn smallbtn btn-y" href="${ctx }/admin/disArticle/replyList/${dis.id}/${dis.groupId}" id="del" title="回复管理">回复管理</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				<!-- /pageBar end -->
			</div>
		</div>
</body>
</body>
</html>