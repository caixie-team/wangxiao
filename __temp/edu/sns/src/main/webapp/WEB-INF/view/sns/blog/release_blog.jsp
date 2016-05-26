<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发布博文</title>
<script type="text/javascript" src="${ctximg}/static/sns/js/blog/blog.js?v=<%=version%>"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript">
window.onload=function(){//编辑器初始化
	initKindEditor_addblog('blogContent','576px','400px');
}
</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
			<!-- 左侧目录区域 -->
			<section class="W-main-c fl">
				<div class="W-main-cc">
					<section class="W-main-center">
						<div class="pl20">
							<section class="mt10">
								<section class="comm-title-3">
									<span class="c-t-2-r"><a class="c-blue" title=""
										href="${ctx}/blog">博文列表&gt;&gt;</a></span> <span
										class="c-t-2-l"><tt class="c-orange">发表博文</tt></span>
									<div class="c-t-line">&nbsp;</div>
								</section>
							</section>
							<!-- /挑战信息 -->
							<div class="mt20">
								<section>
									<dl class="boke-release-wrap">
										<dt>
											<span class="c-555 fsize14">标题</span>
										</dt>
										<dd>
										<select id="selType" class="commSelect" style="width: 80px;">
										<option value="0">请选择</option>
										<option value="1">原创</option>
										<option value="2">转载</option>
										</select>
											<input type="text" name="blogBlog.title" class="commInput-1"
												onblur="limit()" id="text"/> <span class="ml10 c-bbb">标题长度不得超过50个字符</span>
										</dd>
									</dl>
									<dl class="boke-release-wrap">
										<dd>
											<textarea name="blogBlog.content" class="commTextarea"
												id="blogContent" ></textarea>
											<p class="mt5 c-bbb">
												您当前输入<span id="blogFont">0</span>个字,不得少于5个字;
											</p>

										</dd>
									</dl>
									<dl class="boke-release-wrap">
										<dt>
											<span class="c-555 fsize14">分类</span>
										</dt>
										<dd>
											<select name="blogBlog.type" id="blogType" class="commSelect">
												<option value="0">--请选择分类--</option>
												<c:forEach items="${artClassifyList}" var="ac">
													<option value="${ac.artId}">${ac.name}</option>
												</c:forEach>
											</select>
										</dd>
									</dl>
								</section>
								<section class="mt20 tac pt20">
									<a href="javascript:void(0)" title="" class="comm-btn-orange"><span
										style="padding: 5px 40px; font-size: 18px;" onclick="tijiao()">发布</span></a>
										<a href="javascript:history.go(-1)" title="" class="comm-btn-gray"><span
										style="padding: 5px 40px; font-size: 18px;">取消</span></a>
								</section>
							</div>
						</div>

					</section>
					<section class="W-main-right">
						<div class="pl20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l"><i class="icon18 zysx">&nbsp;</i>
										<tt class="c-orange">注意事项</tt></span>
								</section>
								<section class="mt20 pr10">
									<div class="zysx-txt">
										请不要在文章中发布招聘和求职信息，违规文章将被删除。本站有正规的发布招聘和求职信息渠道，经过认证的猎头和HR可以在招聘中心和观点中发布招聘信息，普通会员可以在观点中发布求职信息。
									</div>
								</section>
							</div>
						</div>
					</section>
				</div>
			</section>
			<!-- 主体区域 -->
</body>
</html>