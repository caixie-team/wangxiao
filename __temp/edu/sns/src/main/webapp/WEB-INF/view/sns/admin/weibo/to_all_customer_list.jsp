<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>用户列表</title>
<script type="text/javascript">
	$(function(){
		$("#top").val("${queryWeiBo.top}");
	});
	function submitform(){
		 $("#pageCurrentPage").val(1);//页数为1
		 $("#searchForm").submit();//提交表单
	} 
	function cancel(){
		 $("#cusId").val("");
		 $("#email").val("");
	}
</script>

</head>
<body  >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>SNS用户管理</span> &gt; <span>用户管理</span> </h4>
	</div>
	<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/cus/toAllCustomerList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="clearfix">
						<div class="optionList">
							<span><font>用户id：</font></span>
							<span>
								<input type="text" name="userExpand.cusId" value="${userExpand.cusId }" id="cusId"/>
							</span>
						</div>
						<div class="optionList">
							<span><font>邮箱：</font></span>
							<span>
								<input type="text" name="userExpand.email" value="${userExpand.email }" id="email"/>
							</span>
						</div>
						<div class="optionList">
							<input type="button" onclick="submitform()" class="btn btn-danger" value="查询" name=""/>
							<input type="button" onclick="cancel()" class="btn btn-danger" value="清空" name=""/>
						</div>
					</div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
					</p>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="10%"><span>ID</span></th>
					<th><span>用户email</span></th>
					<th><span>昵称</span></th>
					<th><span>观点数</span></th>
					<th><span>粉丝数</span></th>
					<th><span>关注人数</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody  id="tabS_02" align="center">
				<c:forEach items="${queryCustomerList}" var="qwbl">
				<tr id="del${qwbl.id }">
					<td>${qwbl.id }</td>
					<td>${qwbl.email }</td>
					<td>${qwbl.nickname }</td>
					<td>${qwbl.weiBoNum }</td>
					<td>${qwbl.fansNum }</td>
					<td id="top${qwbl.cusId }">${qwbl.attentionNum }
					</td>
					<td class="c_666 czBtn" align="center">
						<a class="btn smallbtn btn-y" href="${ctx}/admin/cus/toFriendManage?friend.cusId=${qwbl.cusId }" title="好友管理">好友管理</a>
						<a class="btn smallbtn btn-y" href="${ctx}/admin/black/queryblackList?blackList.cusId=${qwbl.cusId }" title="黑名单管理">黑名单管理</a>
						<a class="btn smallbtn btn-y" href="${ctx}/admin/cus/toFansManage?id=${qwbl.cusId }" title="粉丝管理">粉丝管理</a>
						<a class="btn smallbtn btn-y" href="${ctx}/admin/letter/toSendSystemMessagesByCusId?cusId=${qwbl.cusId }" title="发消息">发消息</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${queryCustomerList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有用户数据！</span>
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