<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
	function cancel(){
		$("#email").val("");
		$("#memberType").val(0);
	}
	function updateMemberStatus(memId,type){
		var message="";
		if(type==1){//关闭
			message="确定关闭该用户会员吗?";
		}else{
			message="确定开启该用户会员吗";
		}
		if(confirm(message)){
			$.ajax({
				url:"${ctx}/admin/member/close",
				type:"post",
				data:{"memberRecord.id":memId,"memberRecord.status":type},
				dataType:"json",
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}else{
						alert(result.message);
						return;
					}
				}
			})
		}
	}
</script>

</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>会员开通管理</span> &gt; <span>会员开通列表</span> </h4>
	</div>
			<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/memberrecord/memberrecords" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>用户邮箱：</font></span>
									<input type="text" name="memberRecordDTO.email" id="email" value="${memberRecordDTO.email}"/>
									<span class="ddTitle"><font>会员类型：</font></span>
									<select name="memberRecordDTO.memberType" id="memberType">
										<option value="0">--请选择--</option>
										<c:forEach items="${memberTypes }" var="memberType">
											<option value="${memberType.id }" <c:if test="${memberType.id==memberRecordDTO.memberType }">selected="selected"</c:if>>${memberType.title }</option>
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
						<p class="fl czBtn"></p>
						<p class="fr c_666">
							<span>会员开通记录</span>
							<span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
						</p>
					</div>
				</caption>
				
				<thead> 	 	 	
					<tr> 	 	 	 	 	
						<th width="10%"><span>ID</span></th>
                           <th><span>用户email</span></th>
                           <th><span>会员类型</span></th>
                           <th><span>到期时间</span></th>
                           <th><span>首次开通时间</span></th>
                            <th><span>状态</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${memberRecordDTOs.size()>0}">
				<c:forEach  items="${memberRecordDTOs}" var="memberRecord" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${memberRecord.email }</td>
						<td>${memberRecord.memberTitle}</td>
						<td><fmt:formatDate value="${memberRecord.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${memberRecord.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
						<c:if test="${memberRecord.status==0}">
						正常
						</c:if>
						<c:if test="${memberRecord.status==1}">
						关闭
						</c:if>
						</td>
						<td>
						<c:if test="${memberRecord.status==0}">
						<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="updateMemberStatus(${memberRecord.id},1)">关闭</a>
						</c:if>
						<c:if test="${memberRecord.status==1}">
						<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="updateMemberStatus(${memberRecord.id},0)">开启</a>
						</c:if>
						<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/member/mrecordinfo/${memberRecord.id}" >查看详情</a></td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${memberRecordDTOs.size()==0||memberRecordDTOs==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有会员开通！</span>
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
<!-- 内容 结束 -->
</body>
</html>
