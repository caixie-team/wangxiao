<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>资讯列表</title>
	<meta name="author" content=""/>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<script type="text/javascript">
		$(function(){
			var typeList = '${stringTypeList}';
			typeList = eval('(' + typeList + ')'); //所有的资讯类型列表
			var queryTypeId = '${queryArticle.typeId}';//搜索时传入类型ID
			setCmsType(typeList,queryTypeId);
			$("#cmsType"+queryTypeId).addClass('current');
		});
		//搜索资讯
		function queryArticle(typeId){
			$("#cmsTypeId").val(typeId);
			$("#searchForm").submit();
		}
</script>
</head>
<body>
<section class="o-topBar">
	<!-- 内容，开始 -->
	<div class="container-i">
		<article class="mt20">
		<c:forEach items="${banner }" var="banner">
			<a href='<c:if test="${banner.linkUrl==null||banner.linkUrl=='' }">javascript:void(0);</c:if><c:if test="${banner.linkUrl!=null&&banner.linkUrl!='' }">${banner.linkUrl }</c:if>' title=""><img src="<%=staticImageServer %>${banner.imageUrl}" width="1000" height="120" class="dis" alt=""></a>
		</c:forEach>
		</article>
		<section class="passway">
			<span class="c-999">您的位置：</span>
			<a class="c-999" href="/">首页</a>
			&nbsp;&gt;&nbsp;
			<a class="c-999" href="/static/cms/index.html">资讯中心</a>
			&nbsp;&gt;&nbsp;
			<span class="c-999">资讯列表</span>
		</section>
		<div class="mt20">
		<form action="${ctx}/cms/news/list" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="1"/>
			<input name="queryArticle.title" id="articleName" type="hidden" value="${queryArticle.title}">
			<input type="hidden" id="cmsTypeId" name="queryArticle.typeId" value="${queryArticle.typeId}"/>
		</form>
			<article class="fl" style="width: 780px;">
				<div class="mr30">
					<section>
						<div>
							<div class="commTabTitle"> <ul> <li class="current"><a class="fsize18 f-fM c-master" href="javascript: void(0)" title="">资 讯 中 心 检 索</a></li> </ul> </div>
							<div class="commBox01" id="cms-type-mx">
							<!-- JavaScript设置资讯分类专区 -->
							</div>
						</div>
						<article class="mt20">
							<header class="cms-c-title">
								<h4>资讯列表</h4>
								<div class="clear"></div>
							</header>
						</article>
						<div class="all-n-list">
							<ul>
							<c:forEach items="${articleList}" var="art">
								<li>
									<a href='<c:if test="${art.linkUrl==null||art.linkUrl=='' }">javascript:void(0);</c:if><c:if test="${art.linkUrl!=null&&art.linkUrl!='' }"><%=articleUrl%>/${art.linkUrl }</c:if>'>
										<c:if test="${art.imageUrl==null||art.imageUrl=='' }">
											<img src="/static/edu/images/default/default_goods.jpg" width="275" height="150" class="fl mr20" class="dis" />
										</c:if>
										<c:if test="${art.imageUrl!=null&&art.imageUrl!='' }">
											<img src="<%=staticImageServer %>${art.imageUrl}" width="275" height="150" class="fl mr20" class="dis" />
										</c:if>
									</a>
									<section class="h-c-r-h2">
										<h2 class="unFw">
										<a href='<%=articleUrl%>/${art.linkUrl }' title="" class="c-333 f-fM">${art.title}</a></h2>
										<div class="h-c-r-desc">
											<p>${art.summary }</p>
										</div>
										<div class="mt10 clearfix">
											<span class="c-999 fr">阅读数：${art.lookNum }</span>
											<span class="c-999 fl">发布日期：<fmt:formatDate type="both" value="${art.addTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
										</div>
									</section>
								</li>
							</c:forEach>
							</ul>
						</div>
						<c:if test="${articleList==null || articleList.size()==0}">
							<section class="comm-tips-1">
								<p>
								<em class="vam c-tips-1">&nbsp;</em><font class="c-999 fsize12 vam">没有搜索到相关的资讯！</font>
								</p>
							</section>
						</c:if>
						<!-- 分页 -->
						<div class="pagination pagination-large tac"> 
							<div class="pagination pagination-large">
								<ul>
								<jsp:include page="/WEB-INF/view/common/page.jsp" />
								</ul>
						 	</div> 
						</div>
					</section>
				</div>
			</article>
			<aside class="c-l-200">
				<div>
					<article>
						<header class="cms-c-title">
							<h4 style="width: 120px;">热门新闻</h4>
							<div class="clear"></div>
						</header>
					</article>
					<section>
					<div class="mt20 line1 pb20">
								<c:forEach items="${hotArticle }" var="art" begin="0" end="0">
								<a href='<c:if test="${art.linkUrl==null||art.linkUrl=='' }">javascript:void(0);</c:if><c:if test="${art.linkUrl!=null&&art.linkUrl!='' }"><%=articleUrl%>/${art.linkUrl }</c:if>'>
									<img src="<%=staticImageServer %>${art.imageUrl}" width="220" height="120" class="dis" />
									<h6 class="hLh30 of c-333">${art.title }</h6>
								</a>
								</c:forEach>
								<ul class="cms-clist">
								<c:forEach items="${hotArticle }" var="art" begin="1" end="5">
								<li><a href='<c:if test="${art.linkUrl==null||art.linkUrl=='' }">javascript:void(0);</c:if><c:if test="${art.linkUrl!=null&&art.linkUrl!='' }"><%=articleUrl%>/${art.linkUrl }</c:if>' title="${art.title }"><em>·</em>${art.title }</a></li>
								</c:forEach>
								</ul>
					</div>
						<div class="mt30">
								<c:forEach items="${hotArticle }" var="art" begin="6" end="6">
								<a href='<c:if test="${art.linkUrl==null||art.linkUrl=='' }">javascript:void(0);</c:if><c:if test="${art.linkUrl!=null&&art.linkUrl!='' }"><%=articleUrl%>/${art.linkUrl }</c:if>'>
									<img src="<%=staticImageServer %>${art.imageUrl}" width="220" height="120" class="dis" />
									<h6 class="hLh30 of c-333">${art.title }</h6>
								</a>
								</c:forEach>
							<ul class="cms-clist">
								<c:forEach items="${hotArticle }" var="art" begin="7" end="12">
								<li><a href='<c:if test="${art.linkUrl==null||art.linkUrl=='' }">javascript:void(0);</c:if><c:if test="${art.linkUrl!=null&&art.linkUrl!='' }"><%=articleUrl%>/${art.linkUrl }</c:if>' title="${art.title }"><em>·</em>${art.title }</a></li>
								</c:forEach>																																									
							</ul>
						</div>
					</section>
				</div>
			</aside>
			
			<div class="clear"></div>
			
		</div>
	</div>
	<!-- 内容，结束-->
	</body>
</html>