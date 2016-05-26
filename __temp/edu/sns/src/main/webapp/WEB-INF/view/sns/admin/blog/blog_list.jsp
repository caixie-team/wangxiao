<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>博客列表</title>
 <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=<%=version%>">
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=<%=version%>"></script>
 <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=<%=version%>"></script>
<script type="text/javascript">
$(function(){
	  $( "#blogdate" ).datepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd"
	    		});
	
});
function deladmin(id){
	var judge=confirm("确定删除吗，相关回复也会删除？");
	if(judge==true){
	$.ajax({
		url:"${ctx}/admin/blog/deleteAdminBlogBlogById",
		type:"post",
		dataType:"json",
		data:{"id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
				alert("删除成功");
				$("#rem"+id).remove();
				return true;
			}else{
				alert("请刷新重试");
				return false;
			}
		}
	});
	}else{
		return false;
	}
}
//置顶博客
function topadmin(id,obj){
	var judge=confirm("确定置顶吗？");
	if(judge==true){
		$.ajax({
			url:"${ctx}/admin/blog/updateBlogBlogByTop",
			data:{"id":id},
			dataType:"json",
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					//alert("置顶成功");
					$(obj).html("取消置顶");
					$(obj).attr("onclick","Canceladmin("+id+",this)");
					$("#top"+id).html("是");
					
				}
				if(result.message=="false"){
					alert("置顶失败，刷新重试");
				}
			}
			
		});
	}else{
		return false;
	}
}
function Canceladmin(id,obj){
	var judge=confirm("确定取消置顶吗？");
	if(judge==true){
		$.ajax({
			url:"${ctx}/admin/blog/updateCancelBlogBlogByTop",
			data:{"id":id},
			dataType:"json",
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					//alert("成功");
					$(obj).html("置顶");
					$(obj).attr("onclick","topadmin("+id+",this)");
					$("#top"+id).html("否");
				}
				if(result.message=="false"){
					alert("置顶失败，刷新重试");
				}
			}
		});
	}else{
		return false;
	}
}
$(function(){
	$("#typeId").val('${blogBlog.type}');
});
function submitform(){
	$("#pageCurrentPage").val(1);//页数为1
	$("#searchForm").submit();//提交表单
}
function clean(){
	$("#showName,#title,#blogdate,#blogId").val('');
	$("#typeId").val(0);
}
//同步财经文章
function syncfin(blogId,obj){
	var judge=confirm("是否同步该财经文章");
	if(judge==true){
		$.ajax({
			url:"/admin/blog/syncFinBlog",
			type:"post",
			data:{"blogId":blogId},
			dataType:"json",
			success:function(result){
				if(result.message=="success"){
					alert("同步成功");
					$(obj).html("已同步");
					$(obj).attr("href","javascript:void(0)");
					$("#caina"+blogId).html("是");
					return;
				}else{
					alert("系统繁忙，请稍后重试");
					return;
				}
			}
		});
	}
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>博客列表</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/blog/getAdminBlogBlogList" method="post" name="searchForm" id="searchForm">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
					<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
						<%-- <form action="${ctx}/admin/disgroup/queryDisGroupListByName.do" method="post"></form> --%>
						<caption>
							<div class="capHead">
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
											<span> <font>&nbsp;&nbsp;ID：</font>
											</span> <span> <input type="text" name="blogBlog.id" value="" id="blogId"></span>
										</li>
										<li>
											<span> <font>标题：</font></span>
											 <span> <input type="text" name="blogBlog.title" value="${blogBlog.title}" id="title"></span>
										</li>
										<li> 
											<span> <font>分类：</font>
										</span><select name="blogBlog.type" id="typeId">
											<option value="0">--请选择分类--</option>
											<c:forEach items="${artclassify}" var="art">
												<option value="${art.artId}">${art.name}</option>
											</c:forEach>
										</select>
										</li>
									</ul>
								</div>
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
											<span> <font>创建人：</font></span>
										 	<span> <input type="text" name="blogBlog.showName" value="${blogBlog.showName}" id="showName"></span>
										</li>
										<li>
											<span> <font>时&nbsp;&nbsp;间：</font></span>
											 <span> <input type="text"  name="blogBlog.strTime" value="${blogBlog.strTime}" id="blogdate" readonly="readonly" class="AS-inp"></span>
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
								<th width="5%"><span>ID</span></th>
								<th width="16%"><span>标题</span></th>
								<th width="10%"><span>博文分类</span></th>
								<th width="10%"><span>创建人</span></th>
								<th width="10%"><span>发表时间</span></th>
								<!-- <th width="8%"><span>财经采纳</span></th> -->
								<th width="8%"><span>置顶博文</span></th>
								<th width="25%"><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02">
							<c:if test="${fn:length (blogBlogList)==0 }">
								<tr class="">
									<td align="center" colspan="16">
										<div class="tips">
											<span>对不起，没有此博文！</span>
										</div>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${blogBlogList }" var="blog">
								<tr id="rem${blog.id }" class="">
									<td align="center">${blog.id }</td>
									<td align="center"><a href="${ctx}/blog/info/${blog.id}" title="${blog.title}" target="_blank">${fn:substring(blog.title,0,25) }</a></td>
									<td align="center">${blog.articleName }</td>
									<td align="center">${blog.showName }</td>
									<td align="center"><fmt:formatDate type="both" value="${blog.addTime }"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
									<%-- <td align="center" id="caina${blog.id}"><c:if test="${blog.isBest==0 }">否
								</c:if> <c:if test="${blog.isBest==1 }">是</c:if></td> --%>
									<td id="top${blog.id}" align="center"><c:if test="${blog.top==0 }">否</c:if>
										<c:if test="${blog.top==1 }">是</c:if></td>
									<td class="c_666 czBtn" align="center"><a
										class="btn smallbtn btn-y"
										href="${ctx}/admin/blog/getAdminBlogBlogById/${blog.id}">修改</a>
										<a class="btn smallbtn btn-y" href="javascript:void(0)"
										title="删除" onclick="deladmin(${blog.id})">删除</a> <c:if
											test="${blog.top==0 }">
											<a class="btn smallbtn btn-y" href="javascript:void(0)"
												onclick="topadmin(${blog.id},this)">置顶</a>
										</c:if> <c:if test="${blog.top==1 }">
											<a class="btn smallbtn btn-y" href="javascript:void(0)"
												onclick="Canceladmin(${blog.id},this)">取消置顶</a>
										</c:if> <a class="btn smallbtn btn-y"
										href="${ctx}/admin/blog/getAdminBlogReplyByBlogId/${blog.id}"
										title="回复管理">回复管理</a>
										<%-- <c:if test="${blog.type==2}">
										<c:if test="${blog.flag==0}">
										<a class="btn smallbtn btn-y"
										href="javascript:void(0)" onclick="syncfin(${blog.id},this)"
										title="同步">同步</a>
										</c:if>
										<c:if test="${blog.flag==1 }">
										<a class="btn smallbtn btn-y"
										href="javascript:void(0)"
										title="已同步">已同步</a>
										</c:if>
										</c:if> --%>
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
</html>