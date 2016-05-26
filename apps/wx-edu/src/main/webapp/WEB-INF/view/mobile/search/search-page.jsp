<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>搜索</title>
	
</head>
<body>
	<div class="search-box">
		<section class="s-inp-wrap">
			<label><input type="text" name="" value="" placeholder="输入搜索内容" required="required" id="s-inp"></label>
			<aside class="s-r-cancel">
				<a href="javascript: void(0)" title="取消">取消</a>
			</aside>
		</section>
		<!-- /顶部搜索输入区域 -->
		<article class="search-elem">
			

			<!-- 未搜索时推荐内容 -->
			<div class="unSearch-content animated delay0d5s zoomIn">
				<h3>热门搜索</h3>
				<c:forEach items="${mapCourseList.index_course_10}" var="courseHot">
					<h5><a href="/mobile/course/info/${courseHot.id}">${courseHot.name }</a></h5>
				</c:forEach>
				<!-- 当未输入搜索时显示出5条热门搜索语 -->
			</div>
			<!-- 未搜索时推荐内容 -->
			
			<!-- /搜索结果区域 -->
			<div class="search-jg-wrap">
				
			</div>
			<!-- /搜索结果区域 -->

		</article>
		<!-- /搜索结果区域 -->
	</div>
	<script type="text/javascript">
		$(function() {
			fouFun(); //搜索框光标
		})
		//获得光标与失去光标
		function fouFun() {
			$("#s-inp").focusin(function() {
				$(this).parent().addClass("curr");
				var _usc = $(".unSearch-content");
				_usc.addClass("bounceOut");
				setTimeout(function() {
	                _usc.remove();
	            }, 2000);
			})
			$("#s-inp").focusout(function() {
				var content=$("#s-inp").val();
				if(content==null||content==''){
					return;
				}
				$(this).parent().removeClass("curr");
				$.ajax({
		    		url:baselocation+"/mobile/search/result",
		    		type : "post", 
		    		data:{"content":content},
		    		dataType : "text",
		    		async:false,
		    		success: function (result){
				        $(".search-jg-wrap").html(result);
		    		}
		    	});
			})
		}
	</script>
</body>
</html>
