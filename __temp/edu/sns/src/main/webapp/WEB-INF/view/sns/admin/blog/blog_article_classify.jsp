<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章的分类</title>
<script type="text/javascript">
 function addArt(){
	var name = $("#name").val().trim();
	if(name==null||name==''){
		alert("不能输入为空");
		return false;
	}else{
		 var judge=confirm("确定添加？");
		 if(judge==true){
		$("#form").submit();
		 }else{
			 return false;
		 }
	}
}
 function deladmin(id){
		var judge=confirm("确定删除吗？");
		if(judge==true){
		$.ajax({
			url:"${ctx}/admin/blog/deleteArticleClassifyById",
			type:"post",
			dataType:"json",
			data:{"artId":id},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					alert("删除成功");
					$("#rem"+id).remove();
					return true;
				}else{
					alert("请刷新重试");
					return false;
				}
			}
		});
		}else{
			return false;
		}
	} 
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>文章分类</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0"
					cellpadding="0" border="0">
					<caption>
					<input type="button" class="btn btn-danger" value="添加"
											onclick="window.location.href='${ctx}/admin/blog/toaddArticleClassify'" />
						<%-- <div class="capHead">
							<div class="clearfix">
							<form action="${ctx}/admin/blog/addArticleClassify" method="post" id="form">
									<div class="optionList">
										<span><font>添加分类：</font></span> <span> <input
											type="text" name="artclassify.name" id="name" onblur="isExsit()"/>
										</span>

									</div>
									<div class="optionList">
										<input type="button" class="btn btn-danger" value="添加"
											onclick="addArt()" />
									</div>
									</form>
							</div>
						</div> --%>
					</caption>
					<thead>
						<tr>
							<th width="3%"><span>ID</span></th>
							<th width="8%"><span>分类</span></th>
							<th width="8%"><span>排序值（由大到小显示）</span></th>
							<th width="8%"><span>添加时间</span></th>
							<th width="10%"><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${artclassify}" var="art">
							<tr id="rem${art.artId }">
								<td>${art.artId}</td>
								<td>${art.name}</td>
								<td>${art.sort}</td>
								<td><fmt:formatDate type="both" value="${art.addTime}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
								<td class="c_666 czBtn" align="center">
									<a class="btn smallbtn btn-y" href="${ctx}/admin/blog/getArticleClassifyById/${art.artId}" title="修改">修改</a> 
									<a class="btn smallbtn btn-y" onclick="deladmin(${art.artId})" href="javascript:void(0)" title="删除">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>