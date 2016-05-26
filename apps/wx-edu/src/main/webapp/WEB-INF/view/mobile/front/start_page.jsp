<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<!-- logo && slogan begin -->
	<section class="sp-lo-sl">
		<h1 class="sp-lo"><span>&nbsp;</span></h1>
		<h2 class="sp-sl"><p id="slogan-txt" class="">传播知识的力量</p></h2>
	</section>
	<!-- logo && slogan end -->
	<section class="copyright">
		<p>${websitemap.web.mobilecopyright}</p>
	</section>
	<!-- /copyright -->
	<section class="s-l-bg" id="s-l-bg"></section><!-- /start-login-bg -->

	<script type="text/javascript">
		
	    window.onload = function() {
	    	var sl = document.getElementById("slogan-txt");
	    		sl.className = "animated delay0d5s fadeInUp";
	    }
	    var second =2;
	    setInterval("redirect()", 1000);  //每1秒钟调用redirect()方法一次
	    function redirect()
	    {
	        if (second < 0)
	        {
	            window.location.href = '/';
	        }else{
	        	second--;
	        }
	    }
	</script>
</body>
</html>