<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
	function addSubmit() {

		var imgIds = document.getElementsByName("ids");
		var num = 0;
		var ids = '';
        var name="";
		for (var i = 0; i < imgIds.length; i++) {
			if (imgIds[i].checked == true) {
				num++;
				ids += imgIds[i].value ;
                name = $(imgIds[i]).attr("courseName") ;
			}
		}
		if (num == 0) {
			alert("请选择要推荐的课程");
			return;
		}
        window.opener.addCourse(ids,name);
        window.close();
	}
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/appWebsite/CourseList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>选取课程</span> &gt; <span>课程列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
										<caption>
											<div class="capHead">
												<div class="clearfix">
													<div class="optionList">
														<span> <font>课程id：</font>
														</span> <span> <input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
														</span>
													</div>
                                                    <div class="optionList">
														<span> <font>课程名称：</font>
														</span> <span> <input type="text"  name="queryCourse.name" value="${course.name}" id="name" />
														</span>
                                                    </div>
													<div class="optionList">
														<input type="button" name="" value="查询" class="btn btn-danger" onclick="goPage(1)" />
													</div>

												</div>
												<div class="clearfix"></div>
											</div>
										</caption>

										<thead>

											<tr>
												<th><span></span></th>
												<th><span>ID</span></th>
												<th><span>课程名称</span></th>
												<th><span>价格</span></th>
												<th><span>添加时间</span></th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">

											<c:if test="${courseList.size()>0}">
												<c:forEach items="${courseList}" var="cou">
													<tr>
														<td><input type="radio" name="ids" value="${cou.id}" courseName="${cou.name}"/></td>
														<td>${cou.id}</td>
														<td>${cou.name}</td>
														<td>${cou.currentprice}</td>
														<td><fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd" /></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${courseList.size()==0||courseList==null}">
												<tr>
													<td align="center" colspan="16">
														<div class="tips">
															<span>还没有课程数据！</span>
														</div>
													</td>
												</tr>
											</c:if>
											<tr>
												<td align="center" colspan="5"><a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">确定</a> <a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a></td>
											</tr>
										</tbody>
									</table>
									<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
								</div>
							</div>
						</table>
					</div>
				</div>
		</form>
</body>
</html>
