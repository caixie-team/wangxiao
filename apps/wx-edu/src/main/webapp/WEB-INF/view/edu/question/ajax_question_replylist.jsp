<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${fn:length(sugSuggestReplyList)==0 }">
	<section class="comm-tips-1">
		<p>
			<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">还没有评论，快去抢沙发~</font>
		</p>
	</section>
</c:if>
	<ul>
<c:forEach items="${sugSuggestReplyList}" var="br" varStatus="index">
		<li class="pr">
			<aside class="noter-pic">
				<img width="50" height="50" src="<%=staticImageServer%>${br.userExpandDto.avatar}"
					class="picImg">

			</aside>
			<div class="of">
				<span class="fl"> <font class="fsize14 c-blue1">
						${br.showname}</font>
				</span> <span class="fr c-666 fsize12"><font>${br.modelStr} </font></span>
			</div>
			<div class="noter-txt mt5">
				<p>${br.content}</p>
			</div>
			<div class="tar">
				<a href="javascript:void(0)" class="c-666 fsize14" onclick="moreStudents(${index.count})" title="回复"><em
					class="icon18 c-reply"></em></a>
			</div>
			<div class="n-reply mt5" id="replyDiv${index.count}" style="display: none;">
					<section class="n-reply-wrap">
						<fieldset>
							<textarea onkeyup="checklengh(this,${index.count})" id="sugSuggestReplyContent" name=""></textarea>
						</fieldset>
						<p class="of mt5 tar pl10 pr10">
							<!-- <span class="fl"> <em class="icon12 msg-e-icon"></em>
							<tt class="c-red com-err-info vam ml5">111</tt></span> -->
							 <span class="c-999 fsize14">还可以输入<tt class="c-red" id="chara${index.count }">500</tt>字
							</span> <a class="lh-reply-btn ml15" onclick="moreStudents(${index.count})" title="回复" href="">回复</a>
						</p>
					</section>
					<div class="mt10 reply-list">
						<section class="review-box mt15">
							<ul>
								<li class="pr">
									<aside class="noter-pic">
										<img width="50" height="50"
											src="http://demo1.inxedu.com/images/upload/customer/20151204/1449196673280.jpg"
											class="picImg">
									</aside>
									<div class="of">
										<span class="fl"> <font class="fsize14 c-blue1">
												因酷教育</font>
										</span> <span class="fr c-666 fsize12"><font>1014-03-23
										</font></span>
									</div>
									<div class="noter-txt mt5">
										<p>的使用，将使内容创作者更加语义地创建文档，之前的开发者在这些场合是一律使用的。</p>
									</div>
									<div class="tar">
										<a href="" class="c-666 fsize14" title="回复"><em
											class="icon18 c-reply"></em></a>
									</div>
								</li>
							</ul>
						</section>
					</div>
				</div>
		</li>
		<!-- <li class="pr">
				<aside class="noter-pic">
					<img width="50" height="50"
						src="http://demo1.inxedu.com/images/upload/customer/20151204/1449196673280.jpg"
						class="picImg">
				</aside>
				<div class="of">
					<span class="fl"> <font class="fsize14 c-blue1"> 因酷教育</font>
					</span> <span class="fr c-666 fsize12"><font>1小时前</font></span>
				</div>
				<div class="noter-txt mt5">
					<p>的使用，将使内容创作者更加语义地创建文档，之前的开发者在这些场合是一律使用的。</p>
				</div>
				<div class="tar">
					<a href="" class="c-666 fsize14" title="回复"><em
						class="icon18 c-reply"></em></a>
				</div>
			</li> -->
</c:forEach>
	</ul>
		<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />
