<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>

<head>
<title>系统消息</title>
<script type="text/javascript">
function delULetter(id){//删除站内信
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delLetterInbox",
		data:{"msgReceive.id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+id).remove();//
				dialog('提示','删除成功',16,'/uc/letter');
				
			}
		}
	});
}
</script>
</head>
<body>
			<article class="u-m-c-w837 u-m-center">
			 <form action="${ctx}/uc/letter"name="searchForm" id="searchForm" method="post">
			 <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="" title="">系统消息</a><tt class="fsize12 vam"></tt></li>
						</ul>
						<div class="clear"></div>
					</section>
				
					<section class="line1">		
						<c:if test="${empty queryLetterList }">
						<section class="comm-tips-1">
							<p>
								<em class="vam c-tips-1">&nbsp;</em>
								<font class="c-999 fsize12 vam">最近没有新消息！</font>
							</p>
						</section>
						</c:if>
						<s:else>
						<div class="pl15 pr15">
							<section>
								<ul class="u-sys-message">
								 <c:forEach items="${queryLetterList}" var="qltl">
                                     	<li>
                                     	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tbody>
												<tr>
													<td width="30%" valign="top">
                                                        <%--qltl.userExpandDto.showname--%>
														<p class="hLh20 of"><tt class="c-red">系统消息</tt></p>
														<p class="mt5"><tt class="c-999">发送于:<fmt:formatDate type="both" value="${qltl.addTime }"></fmt:formatDate></tt></p>
													</td>
													<td width="60%" valign="top"class="u-s-m-desc">
														<div class="mt5">
															<p class="c-666">
																${qltl.content }
															</p>
														</div>
													</td>
												<td><a class="c-666" href="javascript:void(0)" onclick="delULetter(${qltl.id })">删除</a></td>
												</tr>
											</tbody>
										</table>
                                     	</li>
                                   </c:forEach>
								</ul>
							</section>
							<section class="mt5 mb5">
								<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
							</section>
							</section>
						</div>
						</s:else>			
					</section>
				</section>
				</form>
			</article>
</body>

