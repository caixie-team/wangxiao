<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>主观题考试记录</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	//查询 
	function query(){
		//判断结束时间不能小于开始时间
		if(checkdate($("#startTime").val(),$("#endTime").val())){
			$("#pageCurrentPage").val(1);
			$("#searchForm").submit();
		}
	}
	$(function(){
		$("#type").val('${queryPaperRecord.type}');
		 $( "#startTime,#endTime" ).datetimepicker(
		    		{regional:"zh-CN",
		    	   		changeMonth: true,
		    	   		dateFormat:"yy-mm-dd "
		    		});
	});
	
	 function checkdate(startTime,endTime){   
	 //得到日期值并转化成日期格式，replace(/\-/g, "\/")是根据验证表达式把日期转化成长日期格式，这样
	//再进行判断就好判断了
	       var sDate = new Date(startTime.replace(/\-/g, "\/"));
	    var eDate = new Date(endTime.replace(/\-/g, "\/"));
	    if(sDate > eDate){
	     alert("结束日期不能小于开始日期");
	     return false;
	    }
	    return true;
	   }
	 function cleanquery(){
		 $("#email,#paperName,#startTime,#endTime").val("");
		 $("#type").val(0);
	 }
</script>
</head>
<body  >
<form action="${ctx}/admin/paper/toSubjectivePaperRecord" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>主观题考试记录</span> &gt; <span>主观题考试记录</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>用户邮箱：</font></span>
								<input id="email" name="queryPaperRecord.email" type="text" value="${queryPaperRecord.email}"  />
							</li>
							<li>
								<span class="ddTitle"><font>试卷名：</font></span>
								<input type="text" id="paperName" name="queryPaperRecord.paperName" value="${queryPaperRecord.paperName}"/>
							</li>
						</ul>
					</div>
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>交卷时间开始：</font></span>
								<input readonly="readonly" id="startTime" type="text" name="queryPaperRecord.startTime" value="${queryPaperRecord.startTime}" />
							</li>
							<li>
								<span class="ddTitle"><font>交卷时间结束：</font></span>
								<input readonly="readonly" id="endTime" name="queryPaperRecord.endTime" type="text" value="${queryPaperRecord.endTime}" />
							</li>
						</ul>
					</div>
					<div class="w50pre fl" style="text-align: center;">
						<ul class="ddBar">
							<li>
								<input class="btn btn-danger ml10" type="button" onclick="query()" value="查询">
								<input class="btn btn-danger ml10" type="button" onclick="cleanquery()" value="清空">
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
			<%-- 	<div class="mt10 clearfix">
					<p class="fl czBtn">
						<span class="ml10"><a href="${cxt}/admin/website/doAddImages"><em class="icon14 new">&nbsp;</em>添加</a></span>
						<span class="ml10"><a href="javascript:deleteImg()"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
					</p>
					<p class="fr c_666"><span>主观题考试记录列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
				</div> --%>
			</caption>
			<thead>
				<tr>
					<th width="8%"><span>ID</span></th>
					<th><span>试卷名</span></th>
					<th><span>用户邮箱</span></th>
					<th><span>分数</span></th>
					<th><span>正确题数</span></th>
					<th><span>总题数</span></th>
					<th><span>答题时间</span></th>
					<th><span>交卷时间</span></th>
					<th><span>正确率</span></th>
					<th><span>试卷类型</span></th>
					<th><span>是否审阅</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${queryPaperRecordList.size()>0}">
			<c:forEach items="${queryPaperRecordList}" var="prl">
				<tr>
					<td>&nbsp;${prl.id }</td>
					<td>${prl.paperName }</td>
					<td>${prl.email }</td>
					<td>${prl.userScore }</td>
					<td>
					${prl.correctNum }
					</td>
					<td>${prl.qstCount }</td>
					<td>
						${prl.fmtTestTime }分钟
					</td>
					<td><fmt:formatDate  value="${prl.addTime}" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate> </td>
					<td>${prl.fmtAccuracy } </td>
					<td>
						<c:if test="${prl.type==1 }">随机试卷</c:if>
						<c:if test="${prl.type==2 }">系统试卷</c:if>
					 </td>
					 <td>
						<c:if test="${prl.state==1 }">是</c:if>
						<c:if test="${prl.state==0 }">否</c:if>
						
					 </td>
					<td class="c_666 czBtn" align="center">
						<a class="btn smallbtn btn-y" href="${ctx}/admin/paper/toPaperRecordInfo?id=${prl.id }&type=6" title="阅卷">阅卷</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty queryPaperRecordList}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有考试记录数据！</span>
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
