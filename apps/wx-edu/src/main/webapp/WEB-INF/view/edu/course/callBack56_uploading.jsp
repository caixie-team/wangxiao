<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>选择课程节点所属课程</title>
 <script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v="></script> 
		<script type="text/javascript">
		$(function(){
			var vid = getParameter("vid");
			var subject = getParameter("subject");
			var url = getParameter("url");
			var result = getParameter("result");
			var player = getParameter("player");
            var iframe_player = getParameter("iframe_player");
			var chk = getParameter("chk");
			var cover = getParameter("cover");
			var coop_public = getParameter("coop_public");
			var forbid = getParameter("forbid");
			var coopid = getParameter("coopid");
			var sid = getParameter("sid");
			var category = getParameter("category");
			var attach = getParameter("attach");
			var tags = getParameter("tags");
			var content = getParameter("content");
			var item_1 = getParameter("item_1");
			var msg = getParameter("msg");
			
			window.parent.callback(vid,subject,url,result,player,chk,cover,
					coop_public,forbid,coopid,sid,category,attach,tags,content,item_1,msg,iframe_player);
			
		});
		function getParameter(val) {
			var uri = window.location.search;
			var re = new RegExp("" + val + "=([^&?]*)", "ig");
			return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
		}
		</script>
	</head>
	<body>
	</body>
</html>