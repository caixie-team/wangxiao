<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小组的分类</title>
<script type="text/javascript">
	function delclassify(id) {
		var click=confirm("确定删除吗？");
		if (click==true) {
			$.ajax({
				url:"/admin/disgroup/deleteclassify",
				data:{"classifyId":id},
				dataType:"json",
				type:"post",
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
		} else {
			return false;
		}

	}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>小组分类</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0"
					cellpadding="0" border="0">
					<caption>
					<input type="button" class="btn btn-danger" value="添加"
											onclick="window.location.href='${ctx}/admin/disgroup/toadd'" />
						<%-- <div class="capHead">
							<div class="clearfix">
								<form
									action="${ctx}/admin/disgroup/addclassify" id="addDisGroupClassify"
									method="post">
									<input type="hidden" name="disGroupClassify.status" value="0">
									<div class="optionList">
										<span><font>添加分类：</font></span> <span> <input
											type="text" name="disGroupClassify.name" id="groupList" onblur="isExsit()"/>
										</span>
										<span><font>分类代码：</font></span> <span> <input
											type="text" name="disGroupClassify.code" id="code" onblur="isCode()"/>
										</span>
									</div>
									<div class="optionList">
										<input type="button" class="btn btn-danger" value="添加"
											onclick="Valempty()" />
									</div>
								</form>
							</div>
						</div> --%>
					</caption>
					<thead>
						<tr>
							<th width="3%"><span>ID</span></th>
							<th width="8%"><span>分类</span></th>
							<!-- <th width="8%"><span>分类代码</span></th> -->
							<th width="8%"><span>排序值（由大到小显示）</span></th>
							<th width="10%"><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${disGroupList}" var="dis">
							<tr id="rem${dis.id}">
								<td>${dis.id}</td>
								<td>${dis.name}</td>
								<%-- <td>${dis.code}</td> --%>
								<td>${dis.sort }</td>
								<td class="c_666 czBtn" align="center"><a
									class="btn smallbtn btn-y"
									href="${ctx}/admin/disgroup/queryclassify/${dis.id}"
									title="修改">修改</a> <a class="btn smallbtn btn-y" onclick="delclassify(${dis.id})"
									href="javascript:void(0)"
									title="删除">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>