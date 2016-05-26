<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${article.title}</title>
	<style type="text/css">
		* {margin: 0;padding: 0;}
		.newsInfor {padding: 10px 15px 20px;overflow: hidden;}
		.ni-title {padding-bottom: 10px;font: 1.2rem/140% 'SimHei';color: #111;margin-bottom: 10px;}
		.ni-attr {padding-bottom: 10px;border-bottom: 1px solid #E4E3D3;text-align: center;overflow: hidden;clear: both}
		.ni-attr span {margin: 0 5px;float: left;}
		.ni-attr span {color: #888;font-size: 0.8rem;font-family: 'SimHei';}
		.articleText * {font: 1rem/180% 'SimHei';color: #4e4e4e;text-align: justify;}
		.articleText img {max-width: 100%;}
	</style>

</head>
<body>
<!-- /资讯列表 -->
<div>
	<article class="newsInfor">

		<div>
			<div class="articleText">
				${course.context}
			</div>
		</div>

	</article>

</div>
</body>
</html>
