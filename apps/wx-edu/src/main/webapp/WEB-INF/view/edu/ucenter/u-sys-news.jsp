<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>系统消息</title>
</head>
<body>
	<div class="">
		<article class="uc-m-content mb50">
			<header class="uc-com-title">
				<span>系统消息</span>
			</header>
			<div class="i-box">
				<div class="mt40">
					<!-- /无数据提示 开始-->
					<c:if test="${empty queryLetterList }">
					<section class="no-data-wrap">
						<em class="no-data-ico">&nbsp;</em> <span
							class="c-666 fsize14 ml10 vam">您还没有收到消息哦！</span>
					</section>
					</c:if>
					<!-- /无数据提示 结束-->
				</div>
				<c:if test="${not empty queryLetterList}">
				<div class="u-sys-news">
				<form action="${ctx}/uc/letter"name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<c:forEach items="${queryLetterList}" var="qltl">
					<dl>
						<dt>
							<section class="tar">
								<p class="hLh30">
									<b class="fsize14 f-fA c-red">
										<c:choose>
											<c:when test="${qltl.type==3}">评论回复</c:when>
											<c:when test="${qltl.type==4}">问答回复</c:when>
											<c:otherwise>系统消息</c:otherwise>
										</c:choose>
									</b>
								</p>
								<p class="hLh20">
									<span class="f-fA c-666"><fmt:formatDate type="both" value="${qltl.addTime }" pattern="MM月dd日HH:mm"/></span>
									
								</p>
								<p class="hLh20">
									<span class="f-fA c-999"><fmt:formatDate type="both" value="${qltl.addTime }" pattern="yyyy年"/></span>
								</p>
							</section>
						</dt>
						<dd>
							<section class="mt10">
								<div style="line-height: 200%;">
									<p class="u-sys-link">
										<span class="c-666 f-fA">${qltl.content }
										</span>
									</p>
								</div>
								<div class="mt10 tar">
									<a href="javascript:void(0)" title="删除" onclick="delULetter(${qltl.id })" class="c-sys-btn">删除</a>
								</div>
							</section>
						</dd>
					</dl>
					</c:forEach>
					<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
					</form>
				</div>
				</c:if>
			</div>
		</article>
	</div>
	<script>
		function cCardFun() {
			$(".c-caed-body>tr>td>em").each(
					function() {
						var _this = $(this), _cont = _this
								.siblings(".c-csrd-m-wrap");
						_this.click(function() {
							if (_cont.is(":hidden")) {
								_cont.show();
								_this.addClass("cou-arrow-up");
								_this.parent().parent().siblings().find(
										".c-csrd-m-wrap").hide();
							} else {
								_cont.hide();
								_this.removeClass("cou-arrow-up");
							}
						});
					})
		}
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
						dialogFun('提示','删除成功',5,'/uc/letter');
						
					}
				}
			});
		}
	</script>
</body>
</html>