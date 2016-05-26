<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty customerList}">
	<div class="Prompt">
		<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
		<p class="vam c-555 fsize14 disIb">您还没有粉丝呢。</p>
	</div>
</c:if>
<c:forEach items="${customerList}" var="cusList">
	<article class="myF-l-box" id="del${cusList.cusId }">
		<div class="F-face">
			<a href="${ctx}/p/${cusList.cusId}/home"> <c:if
					test="${cusList.avatar!=null&&cusList.avatar!='' }">
					<img src="<%=staticImageServer%>${cusList.avatar }"
						width="50" height="50" alt="">
				</c:if> <c:if
					test="${cusList.avatar==null||cusList.avatar=='' }">
					<img src="${ctximg}/static/sns/pics/user.jpg" width="50"
						height="50" alt="">
				</c:if></a>
		</div>
		<ul class="F-info">
			<li><a href="${ctx}/p/${cusList.cusId}/home"
				class="c-blue customerName${cusList.cusId}">${cusList.showname}</a></li>
			<li><span class="c-888"> <c:if
						test="${cusList.gender==0 }">
						<tt class="boy vam">
							<i class="icon12">&nbsp;</i>
						</tt>
					</c:if> <c:if test="${cusList.gender==1 }">
						<tt class="girl vam">
							<i class="icon12">&nbsp;</i>
						</tt>
					</c:if></span></li>
			<li><span class="c-888"></span><span
				class="c-888 ml10"></span></li>
		</ul>
		<!-- <div class="F-intor">
			简介：
		</div> -->
		<div class="clearfix mt5">
			<span class="fr"> <a href="javascript:void(0)" title="发消息"
				onclick="addLetterInput('${cusList.showname }',${cusList.cusId },this)"
				class="F-remove sendmessage${cusList.cusId }"><i
					class="icon12 mr5">&nbsp;</i>发消息</a> 
				 <c:if test="${cusList.mutual==0}">
					<a href="javascript:void(0)" title="关注" onclick="attention(${cusList.cusId },this)" class="F-gz"><i
						class="icon12 mr5">&nbsp;</i>关注</a>
				</c:if>
			</span>
		</div>

	</article>
</c:forEach>
