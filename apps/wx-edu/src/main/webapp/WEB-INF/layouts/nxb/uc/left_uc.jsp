<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- 公共左侧目录区 开始 -->
<aside class="uc-m-left">
	<div class="uc-m-l-wrap">
		<menu class="uc-m-l-menu">
			<c:if test="${hasRole}">
				<dl>
					<dt><a href="${ctx}/admin" title="我的账户">系统管理</a></dt>
				</dl>
			</c:if>
			<dl id="leftdl_task">
				<dt><em class="ucm-ja icon16"></em><a href="javascript: void(0)" title="我的培训">我的培训</a></dt>
				<dd class="undis">
					<ol class="l-menu-sub-ol">
						<li><a href="${ctx}/uc/myArrangeExam" title="我的测评">我的测评</a></li>
						<li><a href="${ctx}/uc/planRecordList" title="学习计划">学习计划</a></li>
					</ol>
				</dd>
			</dl>

			<dl id="leftdl_course">
				<dt><em class="ucm-ja icon16"></em><a href="javascript: void(0)" title="我的课程">我的课程</a></dt>
				<dd>
					<ol class="l-menu-sub-ol">
						<li class="current"><a href="${ctx}/uc/course" title="我的课程">我的课程</a></li>
						<li><a href="${ctx}/uc/mylive" title="我的直播">我的直播</a></li>
						<li><a href="${ctx}/uc/study" title="学习轨迹">学习轨迹</a></li>
						<li><a href="${ctx}/uc/fav" title="我的收藏">我的收藏</a></li>
						<li><a href="${ctx}/uc/note" title="我的笔记">我的笔记</a></li>
					</ol>
				</dd>
			</dl>

			<c:if test="${keywordmap.keyword.verifyExam=='ON' }">
				<dl id="leftdl_exam">
					<dt><em class="ucm-ja icon16"></em><a href="javascript: void(0)" title="一问一答">我的考核</a></dt>
					<dd class="undis">
						<ol class="l-menu-sub-ol">
						   <li><a target="_balnk"  href="${ctxexam}/quest/toQuestionitemList" title="考点练习">我的任务</a></li>
						   <li><a target="_balnk"  href="${ctxexam}/quest/toQuestionitemList" title="真题模考">自我测评</a></li>
						</ol>
					</dd>
				</dl>
			</c:if>
			<dl id="leftdl_ques">
				<dt><em class="ucm-ja icon16"></em><a href="javascript: void(0)" title="互动问答">互动问答</a></dt>
				<dd class="undis">
					<ol class="l-menu-sub-ol">
						<li><a href="${ctx}/uc/myCouAnswer" title="课程答疑">课程答疑</a></li>
						<li ><a href="${ctx}/uc/ques/my" title="我的问答">我的问答</a></li>
						<li ><a target="_blank" href="${ctx}/front/articlelist/notice" title="网站公告">网站公告</a></li>
					</ol>
				</dd>
			</dl>
			<dl id="leftdl_order">
				<dt><em class="ucm-ja icon16"></em><a href="javascript: void(0)" title="积分与账单">积分账单</a></dt>
				<dd class="undis">
					<ol class="l-menu-sub-ol">
						<li ><a href="${ctx}/uc/order" title="课程订单">课程订单</a></li>
						<li><a href="${ctx}/uc/cash/order" title="充值订单">充值订单</a></li>
						<li><a href="${ctx}/uc/address">我的地址</a></li>
						<li><a href="${ctx}/uc/myinte">我的积分</a></li>
						<li><a href="${ctx}/uc/integift" title="积分兑换">积分兑换</a></li>
					</ol>
				</dd>
			</dl>
			<dl id="leftdl_account">
				<dt><em class="ucm-ja icon16"></em><a href="javascript: void(0)" title="积分与账单">账号管理</a></dt>
				<dd class="undis">
					<ol class="l-menu-sub-ol">
						<li><a href="${ctx}/uc/acc" title="我的账户">我的账户</a></li>
						<li><a href="${ctx}/uc/member" title="我的会员">我的会员</a></li>
						<li><a href="${ctx}/uc/card" title="课程卡">课程卡</a></li>
						<li><a href="${ctx}/uc/uinfo" title="个人设置">个人设置</a></li>
						<li><a href="${ctx}/uc/myProfile" title="第三方账号">第三方账号</a></li>
						<li><a href="${ctx}/uc/letter" title="系统消息">系统消息</a></li>
					</ol>
				</dd>
			</dl>

		</menu>
	</div>
</aside>
<!-- 公共左侧目录区 结束 -->