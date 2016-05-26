<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>系统消息列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function(){
		 $( "#addtime" ).datepicker(
		    		{regional:"zh-CN",
		    		changeMonth: true,
		    		dateFormat:"yy-mm-dd"
		    });
		 $("#status").val("${msgSystem.status}");
	});
	function delMsgSystem(id){
		if(confirm("是否删除?")){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/letter/delSystemById",
				data:{"id":id},
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){ 
						$("#status"+id).html("删除");
					}
					if(result.message=="false"){
						alert("请刷新重试");
					}
				}
			});
		 }
	}
	function submitFrom(){
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
</script>

</head>
<body  >

	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>系统管理</span> &gt; <span>发送系统消息</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/letter/toSystemList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="clearfix">
						<div class="optionList">
							<span><font>内容：</font></span>
							<span>
								<input type="text" name="msgSystem.content"  value="${msgSystem.content }" />
							</span>
						</div>
						<div class="optionList">
							<span><font>发表时间：</font></span>
							<span>
								<input type="text" name="msgSystem.addTimeStr" id="addtime" value="${msgSystem.addTimeStr }" />
							</span>
						</div>
						<div class="optionList">
							<span><font>状态：</font></span>
							<span>
								<select name="msgSystem.status" style="width: 100px;" id="status">
									<option value="-1">-全部-</option>
									<option value="0">正常</option>
									<option value="1">删除</option>
									<option value="2">过期</option>
								</select>
							</span>
						</div>
						<div class="optionList">
							<input type="submit" class="btn btn-danger" onclick="submitFrom()" value="查询" name=""/>
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
					<th width="5%"><span>ID</span></th>
					<th width="20%"><span>内容</span></th>
					<th width="10%"><span>发表时间</span></th>
					<th width="7%"><span>状态</span></th>
					<th width="15%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02">
				<c:forEach items="${msgSystemList}" var="qwbl">
				<tr id="del${qwbl.id }">
					<td align="center">${qwbl.id }</td>
					<td align="center">${qwbl.content }</td>
					<td valign="middle" align="center"><fmt:formatDate value="${qwbl.addTime}" pattern="yyyy/MM/dd  HH:mm:ss" ></fmt:formatDate></td>
					<td id="status${qwbl.id }" valign="middle" align="center">
					<c:if test="${qwbl.status==0 }">正常</c:if>
					<c:if test="${qwbl.status==1 }">删除</c:if>
					<c:if test="${qwbl.status==2 }">过期</c:if>
					</td>
					<td class="c_666 czBtn" align="center">
						<c:if test="${qwbl.status==0 }">
						<a class="btn smallbtn btn-y" href="javascript:delMsgSystem(${qwbl.id })" title="删除">删除</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${msgSystemList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有数据！</span>
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