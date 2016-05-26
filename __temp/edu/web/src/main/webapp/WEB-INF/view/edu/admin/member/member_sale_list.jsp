<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>会员商品列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
function del(id){
	if(confirm("确定删除吗？")){
		$.ajax({
			url:"${cxt}/admin/membersale/del/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("删除成功");
					window.location.reload();
				}
			}
		})
	}	
}
function cancel(){
	$("#type").val(0);
}
       
</script>
</head>
<body  >
<form action="${ctx}/admin/membersale/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>会员商品管理</span> &gt; <span>会员商品列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>会员类型：</font></span>
									<select name="queryMemberSale.type" id="type">
										<option value="0">--请选择--</option>
										<c:forEach items="${memberTypes }" var="memberType">
											<option value="${memberType.id }" <c:if test="${memberType.id==queryMemberSale.type }">selected="selected"</c:if>>${memberType.title }</option>
										</c:forEach>
									</select>
									<input type="button"  class="btn btn-danger ml10" value="查询" name="" onclick="goPage(1)"/>
									<input type="button"  class="btn btn-danger ml10" value="清空" name="" onclick="cancel()"/>
								</li>
							</ul>
							
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span class="ml10"><a href="${cxt}/admin/membersale/doadd"><em class="icon14 new">&nbsp;</em>新建会员价格</a></span>
						</p>
						<p class="fr c_666">
							<span>会员商品列表</span>
							<span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
						</p>
					</div>
				</caption>
				<thead>
					<tr>
						<th><span>ID</span></th>
                        <th><span>商品名称</span></th>
                        <th><span>类型</span></th>
                        <th><span>价格</span></th>
                        <th><span>时长（天）</span></th>
                        <th><span>排序</span></th>
                        <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${memberSaleList.size()>0}">
				<c:forEach  items="${memberSaleList}" var="memberSale" >
					<tr>
						<td>${memberSale.id }</td>
						<td>${memberSale.name }</td>
						<td>${memberSale.title }</td>
						<td>${memberSale.price }</td>
						<td>${memberSale.days }</td>
						<td>${memberSale.sort}</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/membersale/doupdate/${memberSale.id}" >修改</a>
							<a class="ml10 btn smallbtn btn-y" href="javascript:del(${memberSale.id})" >删除</a>
                        </td>
						
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${memberSaleList.size()==0||memberSaleList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有会员商品！</span>
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
