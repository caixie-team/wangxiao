<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function submits(){
		$("#updateForm").submit();
	}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>更新设置</span> &gt; <span>修改更新设置</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/app/updateApp" id="updateForm" method="post">
		<input type="hidden" name="appUpdate.id" value="${appUpdate.id}"/>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<tbody>
				<tr>
					<td align="center">类型 </td>
					<td>
						${appUpdate.KType}
					</td>
				</tr>
				<tr>
					<td align="center">下载链接 </td>
					<td>
						<input name="appUpdate.downloadUrl" value="${appUpdate.downloadUrl}"/>
					</td>
				</tr>
				<tr>
					<td align="center">版本号 </td>
					<td>
						<input name="appUpdate.versionNo" value="${appUpdate.versionNo}"/>
					</td>
				</tr>
				<tr>
					<td align="center">更新说明 </td>
					<td>
						<textarea name="appUpdate.depict" style="width: 260px;height: 150;">${appUpdate.depict}</textarea>
					</td>
				</tr>

				
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" onclick="submits()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<!-- /tab4 end -->
</body>
</html>