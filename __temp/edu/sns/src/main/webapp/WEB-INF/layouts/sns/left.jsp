<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- 左侧公共部分-->
<aside class="W-main-l">
	<section class="W-main-l-fixed">

	    <jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	
	    <div class="l-o-menu">
	        <section class="l-level">
	            <a id="leftdl_sns_index" href="${ctx}/u/home" title="首页"  class="lev-22 leftindex current">
	                <i class="icon22">&nbsp;</i>
	                首页
	            </a>
	        </section>
	        <h5 class="l-o-m-title mt5"><span class="c-555 fsize12"><b>&nbsp;互&nbsp;动</b></span></h5>
	        <div class="mt20">
	            <section class="l-level">
	                <a id="leftdl_sns_dis"  href="${ctx}/dis/home" title="小组"  class="lev-23 leftdisgroup">
	                    <i class="icon22">&nbsp;</i>
	                    小组
	                </a>
	            </section>
	            <section class="l-level">
	                <a id="leftdl_sns_weibo"  href="${ctx}/weibo/" title="观点" class="lev-24 leftweiBo">
	                    <i class="icon22">&nbsp;</i>
	                    观点
	                </a>
	            </section>
	            <section class="l-level">
	                <a id="leftdl_sns_friend"  href="${ctx}/friend" title="关注" class="lev-32 leftfriend">
	                    <i class="icon22">&nbsp;</i>
	                    学友
	                </a>
	            </section>
	            <section class="l-level">
	                <a  id="leftdl_sns_sug" href="${ctx}/sug" title="问答"  class="lev-33 leftsug">
	                    <i class="icon22">&nbsp;</i>
	                    问答
	                </a>
	            </section>
	            <section class="l-level">
	                <a  id="leftdl_sns_blog" href="${ctx}/blog" title="博客"  class="lev-25 leftblog">
	                    <i class="icon22">&nbsp;</i>
	                    博文
	                </a>
	            </section>
	            <section class="l-level">
	                <a  id="leftdl_sns_letter" href="${ctx}/letter" title="消息"  class="lev-26 leftletter">
	                    <i class="icon22">&nbsp;</i>
	                    消息
	                </a>
	            </section>
	
	        </div>
	    </div>
	</section>

</aside>
<!-- 左侧目录区域 -->
