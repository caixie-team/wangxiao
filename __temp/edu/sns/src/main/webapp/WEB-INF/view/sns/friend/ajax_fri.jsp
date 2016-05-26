<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${ empty customerList}">
	<div class="Prompt">
		<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
		<p class="vam c-555 fsize14 disIb">您还没有关注的人呢</p>
	</div>
</c:if>
<c:forEach items="${customerList}" var="cusList">
	<article class="myF-l-box" id="del${cusList.cusId }">
		<div class="F-face">
			<a href="${ctx}/p/${cusList.cusId}/home"> 
				<c:if test="${cusList.avatar!=null&&cusList.avatar!='' }">
					<img src="<%=staticImageServer%>${cusList.avatar }" width="50" height="50" alt="">
				</c:if> 
				<c:if test="${cusList.avatar==null||cusList.avatar=='' }">
					<img src="${ctximg}/static/sns/pics/user.jpg" width="50" height="50" alt="">
				</c:if>
			</a>
		</div>
		<ul class="F-info">
			<li>
				<a href="${ctx}/p/${cusList.cusId}/home" class="c-blue customerName${cusList.cusId}"> 
					<c:choose>
						<c:when test="${not empty cusList.remarks}">
							${cusList.remarks}
						</c:when>
						<c:otherwise>
							${cusList.showname}
						</c:otherwise>
					</c:choose>
				</a>
			</li>
			<li class="of">
				<span class="fr" style="width: 67px" title="共同好友:${cusList.commonFriendNum }">共同好友 
					<tt class="c-blue vam f-fM">:${cusList.commonFriendNum }</tt></span> 
				<span class="c-888"> </span>
			</li>
			<li><span class="c-888"></span><span class="c-888 ml10"></span></li>
		</ul>
		<div class="F-intor"></div>
		<div class="clearfix mt5">
			<span class="fr"> 
				<a href="javascript:void(0)" title="取消关注" onclick="quxiaoAttentionCustomer(${cusList.cusId })" class="F-gz">
					<i class="icon12 mr5">&nbsp;</i>取消关注</a> 
					<a href="javascript:void(0)" title="发消息" onclick="addLetterInput('${cusList.showname }',${cusList.cusId },this)"
				class="F-fxx sendmessage${cusList.cusId }"><i class="icon12 mr5">&nbsp;</i>发消息</a>
				<c:if test="${cusList.friendId==0 }">
					<a href="javascript:void(0)" title="加关注"
						onclick="addFriend(${cusList.cusId })" class="F-add"><i
						class="icon12 mr5">&nbsp;</i>加关注</a>
				</c:if><a href="javascript:void(0)" title="备注"
				id="buttonremarks${cusList.cusId }"
				onclick="toaddremarks(${cusList.cusId },this)" class="F-bz"><i
					class="icon12 mr5">&nbsp;</i>备注</a></span>
		</div>
	</article>
</c:forEach>
<div class="clear"></div>
<!-- 公共分页 开始 -->
<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp"></jsp:include>
<!-- 公共分页 结束 -->