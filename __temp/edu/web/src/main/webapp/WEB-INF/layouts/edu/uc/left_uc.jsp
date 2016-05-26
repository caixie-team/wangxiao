<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<menu class="u-m-left" >
	<div class="u-elephant">
		<aside>
			<img id="userImgId" height="134" width="134" alt="">
			<a href="${ctx}/uc/avatar" title="修改头像" class="c-fff">修改头像</a>
		</aside>
	</div>
	<section class="uMenuFixed">
				<div class="u-menu-head">
					<a href="${ctx}/uc/home" title="个人中心">
						<em class="icon-2-18 u-m-icon-1">&nbsp;</em>
						<strong class="c-fff ml5 fsize14 vam">个人中心</strong>
					</a>
				</div>
				<div class="u-menu-list">
					<dl id='leftdl_course'>
						<dt class="curr"><em class="icon-2-18 u-m-icon-2">&nbsp;</em><span class="c-333 ml10 fsize14 vam">我的课程</span><em class="icon-2-14 u-up-down ml15">&nbsp;</em></dt>
						<dd style="display: block;">
							<ol>
								<li><a href="${ctx}/uc/course" title="课程管理">课程管理</a></li>
								<li><a href="${ctx}/uc/card" title="课程卡">课程卡</a></li>
								<li><a href="${ctx}/uc/study" title="学习记录">学习记录</a></li>
								<li><a href="${ctx}/front/showcoulist" title="选课中心" target="_blank" >选课中心</a></li>
								<li><a href="${ctx}/uc/fav" title="我的收藏">我的收藏</a></li>
								<li><a href="${ctx}/uc/note" title="我的笔记">我的笔记</a></li>
								<li><a href="${ctx}/uc/mylive" title="我的直播">我的直播</a></li>
								<li><a href="${ctx}/uc/myCouAnswer" title="课程答疑">课程答疑</a></li>
								
							</ol>
						</dd>
					</dl>
					<c:if test="${keywordmap.keyword.verifyExam=='ON' }">
					<dl id="leftdl_exam">
						<dt><em class="icon-2-18 u-m-icon-3">&nbsp;</em><span class="c-333 ml10 fsize14 vam">考试中心</span><em class="icon-2-14 u-up-down ml15">&nbsp;</em></dt>
						<dd>
							<ol>
                                <li><a target="_balnk"  href="${ctxexam}/quest/toQuestionitemList" title="考点练习">考点练习</a></li>
                                <li><a target="_balnk"  href="${ctxexam}/quest/toQuestionitemList" title="真题模考">真题模考</a></li>
								<li><a target="_balnk"  href="${ctxexam}/quest/toQuestionitemList" title="随机测试">随机测试</a></li>
								<li><a target="_balnk"  href="${ctxexam}/quest/toQuestionitemList" title="错题练习">错题练习</a></li>
							</ol>
						</dd>
					</dl>
					</c:if>
					<c:if test="${keywordmap.keyword.verifySns=='ON' }">
					<dl id="leftdl_sns">
						<dt><em class="icon-2-18 u-m-icon-6">&nbsp;</em><span class="c-333 ml10 fsize14 vam">社交圈子</span><em class="icon-2-14 u-up-down ml15">&nbsp;</em></dt>
						<dd>
							<ol>
								<li><a href="${ctxsns}/dis/home" title="">小组</a></li>
								<li><a href="${ctxsns}/weibo/" title="">观点</a></li>
								<li><a href="${ctxsns}/friend">学友</a></li>
								<li><a href="${ctxsns}/sug" title="">问答</a></li>
								<li><a href="${ctxsns}/blog" title="">博文</a></li>
							</ol>
						</dd>
					</dl>
					</c:if>
					<dl id="leftdl_ques">
						<dt><em class="icon-2-18 u-m-icon-7">&nbsp;</em><span class="c-333 ml10 fsize14 vam">一问一答</span><em class="icon-2-14 u-up-down ml15">&nbsp;</em></dt>
						<dd>
							<ol>
                                <li><a href="${ctx}/uc/question" title="">同学问题</a></li>
                                <li><a href="${ctx}/uc/ques/my" title="">我的问题</a></li>
                                <li><a href="${ctx}/uc/ques/add" title="">我要提问</a></li>
							</ol>
						</dd>
					</dl>
					<dl id="leftdl_order">
						<dt><em class="icon-2-18 u-m-icon-4">&nbsp;</em><span class="c-333 ml10 fsize14 vam">订单与积分</span><em class="icon-2-14 u-up-down ml15">&nbsp;</em></dt>
						<dd>
							<ol>
								<li><a href="${ctx}/uc/order" title="">课程订单</a></li>
								<li><a href="${ctx}/uc/cash/order" title="">充值订单</a></li>
								<%-- <li><a href="${ctx}/bookOrder/myBookOrderList">图书订单</a></li> --%>
								<li><a href="${ctx}/uc/address">收货地址</a></li>
								<li><a href="${ctx}/uc/myinte" title="">我的积分</a></li>
								<li><a href="${ctx}/uc/integift" title="">积分兑换</a></li>
								<%-- <li><a href="${ctx}/uc/getUserIntegralRecord" title="我的推广">我的推广</a></li> --%>
							</ol>
						</dd>
					</dl>
					<dl id="leftdl_account">
						<dt><em class="icon-2-18 u-m-icon-5">&nbsp;</em><span class="c-333 ml10 fsize14 vam">账号管理</span><em class="icon-2-14 u-up-down ml15">&nbsp;</em></dt>
						<dd>
							<ol>
								<li><a href="${ctx}/uc/acc" title="我的账户">我的账户</a></li>
								<c:if test="${salemap.sale.verifyMember=='ON'}">
									<li><a href="${ctx}/uc/member" title="我的会员">我的会员</a></li>
								</c:if>
								<li><a href="${ctx}/uc/uinfo" title="基本信息">基本信息</a></li>
								<%--<li><a href="${ctx}/uc/uppwd" title="修改密码">修改密码</a></li>
								<li><a href="${ctx}/uc/avatar" title="修改头像">修改头像</a></li>--%>
								<li><a href="${ctx}/uc/letter" title="系统消息">系统消息</a></li>
							</ol>
						</dd>
					</dl>
					<!-- <dl>
						<dt><em class="icon-2-18 u-m-icon-6">&nbsp;</em><span class="c-333 ml10 fsize14 vam">用户手册</span></dt>
					</dl> -->
				</div>
				</section>
			</menu>
