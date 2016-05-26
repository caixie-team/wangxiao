<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台小组列表</title>
<script type="text/javascript">
//小组审核
function  updateDisGroup(disId,obj){
		$.ajax({
			url:"/admin/disgroup/updateDisGroupStatus",
			dataType:"json",
			data:{"groupId":disId},
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
			    $("#tongguo"+disId).html("审核通过");
			    $(obj).html("小组关闭");
			    $("#member"+disId).html(1);
			    alert("成功");
				}
				if(result.message=="false"){
					alert("请刷新重试");
				}
			}
		});
	}
function groupClose(disId,obj){
		$.ajax({
			url:"${ctx}/admin/disgroup/updateOpenDisGroupStatus",
			dataType:"json",
			data:{"groupId":disId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
			    $(obj).html("小组开启");
			    $("#tongguo"+disId).html("小组关闭");
			    alert("成功");
			    $(obj).attr("onclick","groupOpen("+disId+",this)");
				}
				if(result.message=="false"){
					alert("请刷新重试");
				}
			}
		});
}
function groupOpen(disId,obj){
	$.ajax({
		url:"${ctx}/admin/disgroup/updateCloseDisGroupStatus",
		dataType:"json",
		data:{"groupId":disId},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
		    $(obj).html("小组关闭");
		    $("#tongguo"+disId).html("审核通过");
		    alert("成功");
		    $(obj).attr("onclick","groupClose("+disId+",this)");
			}
			if(result.message=="false"){
				alert("请刷新重试");
			}
		}
	});
}
function refuse(id,status){
	if(status==1||status==2){
		alert("小组处于审核通过状态，无法拒绝！");
		return false;
	}
	if(status==3){
		alert("该小组已处于审核失败！");
		return false;
	}else{
	$.ajax({
		url:"${ctx}/admin/disgroup/updaterefuseDisGroupStatus",
		type:"post",
		dataType:"json",
		data:{"groupId":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
				 $("#tongguo"+id).html("审核失败");
				alert("拒绝成功");
				
			}else{
				alert("请刷新重试");
				return false;
			}
		}
	});
}
}
function del(id){
	var judge=confirm("确定删除吗？");
	if(judge==true){
	$.ajax({
		url:"${ctx}/admin/disgroup/deleteDisGroupById",
		type:"post",
		dataType:"json",
		data:{"groupId":id},
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
$(function(){
	if('${disGroup.disclassifyId}'==null||'${disGroup.disclassifyId}'==''){
		$("#groupListId").val(0);
	}else{
		$("#groupListId").val('${disGroup.disclassifyId}');
	}
	$("#disstatus").val('${disGroup.status}');
});
function submitform(){
	$("#pageCurrentPage").val(1);//页数为1
	$("#searchForm").submit();//提交表单
}
function clean(){
	$("#disname,#showName").val('');
	$("#groupListId").val(0);
	$("#disstatus").val(-1);
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
				<form action="${ctx}/admin/disgroup/queryDisGroupList" name="searchForm" id="searchForm" method="post">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
					<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
						<caption>
							<div class="capHead">
							
							<div class="w50pre fl">
									<ul class="ddBar">
										<li>
											<span> <font>小组名称：</font></span>
											 <span> <input type="text" name="disGroup.name" value="${disGroup.name}" id="disname"></span> </li>
										<li>
											<span> <font>小组分类：</font> </span>
											<select name="disGroup.disclassifyId" id="groupListId">
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
											<span> <font>组长：</font>
											</span> <span> <input type="text" name="disGroup.showName" value="${disGroup.showName}" id="showName">
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
								<th width="8%"><span>ID</span></th>
								<th><span>小组名称</span></th>
								<th><span>小组简介</span></th>
								<th><span>分类</span></th>
								<th><span>小组成员数</span></th>
								<th><span>创建时间</span></th>
								<th><span>组长</span></th>
								<th><span>小组文章数</span></th>
								<th><span>排序值</span></th>
								<!-- <th width="7%"><span>状态</span></th> -->
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02">
						<c:if test="${fn:length (disGroupAllList)==0 }">
							<tr class="">
								<td align="center" colspan="16">
									<div class="tips">
										<span>对不起，没有该小组信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
							<c:forEach items="${disGroupAllList }" var="dis">
								<tr id="rem${dis.id }" class="">
									<td align="center">${dis.id }</td>
									<td align="center">${dis.name }</td>
									<td align="center">${fn:substring(dis.introduction,0,20)}</td>
									<td align="center">${dis.disclasstiy }</td>
									<td id="member${dis.id }" align="center">${dis.memberNum }</td>
									<td align="center"><fmt:formatDate value="${dis.createTime}" type="both" ></fmt:formatDate></td>
									<td align="center">${dis.showName}</td>
									<td align="center">${dis.articleCounts }</td>
									<td align="center">${dis.sort }</td>
									<%-- <td id="tongguo${dis.id}" align="center"><c:if
											test="${dis.status==0}">
						待审核
						</c:if> <c:if test="${dis.status==1}">
						审核通过
						</c:if>
						<c:if test="${dis.status==2}">小组关闭</c:if>
						<c:if test="${dis.status==3}">审核失败</c:if>
						</td> --%>
									<td class="c_666 czBtn" align="center"><%-- <c:if
											test="${dis.status==0 ||dis.status==3}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)"
												onclick="updateDisGroup(${dis.id },this)" title="通过">通过</a>
										</c:if> <c:if test="${dis.status==1}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)"
												title="通过" onclick="groupClose(${dis.id },this)" id="disGroupClose">小组关闭</a>
										</c:if> 
										<c:if test="${dis.status==2}">
											<a class="btn smallbtn btn-y" href="javascript:void(0)"
												title="通过" onclick="groupOpen(${dis.id},this)">小组开启</a>
										</c:if> 
										<a class="btn smallbtn btn-y"
										href="javascript:void(0)" onclick="refuse(${dis.id},${dis.status})">拒绝</a> --%>
										<a class="btn smallbtn btn-y"
										href="${ctx}/admin/disgroup/queryDisGroupById/${dis.id}">修改</a>
										<a class="btn smallbtn btn-y"
										href="javascript:void(0)"
										title="删除" onclick="del(${dis.id})">删除</a></td>
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