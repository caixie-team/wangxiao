<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>更新设置</title>
</head>
<body>
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>更新管理</span> &gt; <span>更新设置</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">

										<thead>

											<tr>
												<th><span>ID</span></th>
												<th><span>类型</span></th>
												<th><span>下载链接</span></th>
												<th><span>版本号</span></th>
												<th><span>更新说明</span></th>
												<th><span>操作</span></th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">

												<c:forEach items="${list}" var="cou">
												<tr>
													<td>${cou.id}</td>
													<td>${cou.KType}</td>
													<td>${cou.downloadUrl}</td>
													<td>${cou.versionNo}</td>
													<td>${cou.depict}</td>
													<td><a href="${ctx}/admin/app/initupdate/${cou.id}">修改</a></td>
												</tr>
												</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</table>
					</div>
				</div>
</body>
</html>
