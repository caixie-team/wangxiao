<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>老师列表</title>
<script type="text/javascript">
$(function(){//初始化数据
	if('${teacher.isStar}'==null||'${teacher.isStar}'==''){
		$("#isStar").val(-1);	
	}else{
		$("#isStar").val('${teacher.isStar}');	
	}
})
function remError(id){//删除
	var judge=confirm("确定删除该讲师？");
	if(judge){
		window.location.href="${ctx}/admin/teacher/delete/"+id+"";
	}
}
function submitSearch(){//搜索
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	function tcClean(){//清空
		$("#tcName").val("");
		$("#isStar").val(-1);
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/teacher/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>讲师管理</span> &gt; <span>讲师列表</span> </h4>
</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="capHead">
								<div class="fl">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>讲师名称：</font></span>
											<input type="text" name="teacher.name" value="" id="tcName" />
											<span class="ddTitle"><font>讲师头衔：</font></span>
											<select name="teacher.isStar" id="isStar">
											<option value="-1">--请选择--</option>
											<option value="0">高级讲师</option>
											<option value="1">首席讲师</option>
											</select>
											<%--  <span class="ddTitle"><font>试卷名称：</font></span>
											<input type="text" name="paperErrorCheck.content" value="${paperErrorCheck.content}" id="paperName" /> --%>
											<%--<span class="ddTitle"><font>试题内容：</font></span>
											<input type="text" name="paperErrorCheck.qstName" value="${paperErrorCheck.qstName}" id="qstName" /> --%>
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="tcClean()"/>
										</li>
									</ul>
									
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="mt10 clearfix">
								<p class="fl czBtn">
								 <span><a href="${ctx}/admin/teacher/toAdd" title="新建讲师"><em class="icon14 new">&nbsp;</em>新建讲师</a></span>
								</p>
							</div> 
						</caption>
						<thead>
							<tr>
								<!-- <th width="5%"><span>&nbsp;ID</span></th> -->
								<th width="8%"><span>ID</span></th>
								<th width="8%"><span>名称</span></th>
								<th width="8%"><span>头衔</span></th>
								<th><span>资历</span></th>
								<th><span>简介</span></th>
								<th><span>添加时间</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${teacherList.size()>0}">
						<c:forEach  items="${teacherList}" var="tc" >
							<tr id="rem${tc.id }">
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${tc.id }</td>
								<td>${tc.name }</td>
								<td><c:if test="${tc.isStar==0 }">
								高级讲师
								</c:if>
								<c:if test="${tc.isStar==1 }">
								首席讲师
								</c:if>
								</td>
								<td>${tc.education }</td>
								<td>${fn:substring(tc.career,0,30)}</td>
								<td><fmt:formatDate type="both" value="${tc.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/teacher/toUpdate/${tc.id}">修改</a>
								<a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:void(0)" onclick="remError(${tc.id})">删除</a>
								<a class="ml10 btn smallbtn btn-y" title="所讲课程" href="${ctx}/admin/teacher/selectCourseList?teacherId=${tc.id}">所讲课程</a>
								</td>
								<%--<td class="c_666 czBtn" align="center">
									<a href="${ctx}/admin/quest/lookQuestion?queryQuestion.id=${trquestion.id }" title="查看" class="btn smallbtn btn-y">查看</a>
									<a href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${trquestion.id }" title="修改" class="btn smallbtn btn-y">修改</a>
									<a href="javascript:delQuestionListBatch(${trquestion.id })" title="删除" class="btn smallbtn btn-y">删除</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${teacherList.size()==0||teacherList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有讲师信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
