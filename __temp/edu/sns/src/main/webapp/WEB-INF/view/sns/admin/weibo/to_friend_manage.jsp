<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>好友管理</title>
<script type="text/javascript">
function delFriend(cusId){
	if(confirm("是否删除?")){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/cus/delFriend",
			data:{"friend.cusId":"${friend.cusId}",
				"friend.cusFriendId":cusId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$("#del"+cusId).remove();
					alert("成功");
				}
				if(result.message=="false"){
					alert("失败");
				}
			}
		});
	 }
}

</script>
</head>
<body  >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>SNS用户管理</span> &gt; <span>好友管理</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/cus/toFriendManage" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<input id="" type="hidden" name="friend.cusId" value="${friend.cusId}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="optionList">
				<a class="btn btn-danger" href="javascript:history.go(-1);">返回</a>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="5%"><span>用户ID</span></th>
					<th width="25%"><span>会员名</span></th>
					<th width="5%"><span>观点数</span></th>
					<th width="5%"><span>粉丝数</span></th>
					<th width="7%"><span>关注人数</span></th>
					<th width="10%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02">
				<c:forEach items="${queryCustomerList}" var="qwbl">
				<tr id="del${qwbl.cusId }">
					<td>${qwbl.cusId }</td>
					<td valign="middle" align="center">${qwbl.showname }</td>
					<td valign="middle" align="center">${qwbl.weiBoNum }</td>
					<td valign="middle" align="center">${qwbl.fansNum }</td>
					<td valign="middle" align="center" id="top${qwbl.cusId }">${qwbl.attentionNum }
					</td>
					<td class="c_666 czBtn" align="center">
						<a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="delFriend(${qwbl.cusId })" title="删除好友">删除</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${queryCustomerList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有用户好友数据！</span>
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
<!-- /tab2 end-->
</body>
</html>