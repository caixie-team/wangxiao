<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>博客列表</title>
<script type="text/javascript">

function create(){
	$("#msg").html("正在生成中，请等候。。。");
	$.ajax({
		url:"${ctx}/admin/lucenecreat",
		type:"post",
		dataType:"json",
		cache:false,
		async:true,
		success:function(result){
			$("#msg").html(result.msg);
		}
	}); 
}

</script>
</head>
<body  >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>索引</span> &gt; <span>索引全部重新生成</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="clearfix">
						<div class="optionList">
							<span>
							<font>索引全部重新生成：</font>
							</span>
							<span>
							</span>
						</div>
						<div class="optionList">
							<input class="btn btn-danger" type="button" onclick="create()" value="开始生成"/>
						</div>
					</div>
				</div>
				
			</caption>
			
		</table>
		<div class="tips">
				<span id="msg">点击开始生成索引</span>
		</div>
	</div>
</div>

</body>
</html>