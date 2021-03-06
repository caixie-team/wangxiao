<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>短信列表</title>

<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd "
	    		});
	  var type="${userMobileMsg.type}";
	  if(type!=null&&type!=""){
		  $("#type").val('${userMobileMsg.type}');
	  }
	  var status="${userMobileMsg.status}";
	  if(status!=null && status!=""){
		  $("#status").val('${userMobileMsg.status}');
	  }
	  
	  
});
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function clean(){
	$("#nickname,#useremail,#mobile,#startDate,#endDate").val("");
	$("#type,#status").val(0);
}

function delMsg(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			url : "${ctx}/admin/user/delMsgById/"+id,
			data : {},
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.message);
                if(result.success==true){
                    window.location.href="/admin/user/sendMsglist";
                }
			}
		})
	}
}
</script>
</head>
<body  >


<!-- 内容 开始  -->

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>短信管理</span> &gt; <span>短信列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="${ctx}/admin/user/sendMsglist" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >

						<caption>
							<div class="capHead">
								<div class=" fl">
									<ul class="ddBar">
										<li style="width: 100%">
											<span class="ddTitle"><font>手机号：</font></span>
											<input type="text" name="userMobileMsg.mobile" value="${userMobileMsg.mobile}" id="mobile" />
											<span class="ddTitle"><font>类型：</font></span>
                                            <select name="userMobileMsg.type" id="type">
                                                <option value="0" selected="selected">请选择</option>
                                                <option value="1">普通</option>
                                                <option value="2">定时</option>
                                            </select>
                                            <span class="ddTitle"><font>状态：</font></span>
                                            <select name="userMobileMsg.status" id="status">
                                                <option value="0" selected="selected">请选择</option>
                                                <option value="1">已发送</option>
                                                <option value="2">未发送</option>
                                            </select>
										</li>
										<li>
											<span class="ddTitle"><font>创建时间：</font></span>
											<input type="text" name="userMobileMsg.startDate" value="${userMobileMsg.startDate}"  id="startDate" class="AS-inp"/>
											<span class="ddTitle"><font>结束时间：</font></span>
											<input type="text" name="userMobileMsg.endDate" value="${userMobileMsg.endDate}"  id="endDate" class="AS-inp"/>
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
										</li>
									</ul>

								</div>
								<div class="clearfix"></div>
							</div>
						<div class="mt10 clearfix">
								<p class="fl czBtn">
									<span><a href="${ctx}/admin/user/toMsg" title="发送短信"><em class="icon14 new">&nbsp;</em>发送短信</a></span>
								</p>
							</div>
						</caption>
						<thead>
							<tr>
								<th><span>ID</span></th>
                                <th><span>类型</span></th>
                                <th><span>短信内容</span></th>
								<th><span>手机号</span></th>
								<th><span>创建时间</span></th>
                                <th><span>发送时间</span></th>
								<th><span>状态</span></th>
								<th><span>操作人</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="msg" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${msg.id }</td>
                                 <td>${msg.type==1?'普通':'定时'}</td>
								<td>${fn:substring(msg.content,0,50) }</td>
								<td>${fn:substring(msg.mobile,0,55) }<c:if test="${msg.mobile.length()>55 }">......</c:if></td>
								<td><fmt:formatDate value="${msg.createTime }" type="both"/></td>
                                <td><fmt:formatDate value="${msg.sendTime }" type="both"/></td>
								<td>
									<c:if test="${msg.status==1 }">已发送</c:if>
									<c:if test="${msg.status==2 }">未发送</c:if>
								</td>
								<td>${msg.loginName}</td>
								<td  class="c_666 czBtn" align="center">
							    <a class="ml10 btn smallbtn btn-y" title="查看" href="${ctx}/admin/user/sendMsgInfo/${msg.id }">查看</a>
							    <c:if test="${msg.status==2&&msg.type==2 }">
							    	<a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:delMsg(${msg.id })">删除</a>
							    </c:if>
								<%-- <a class="ml10 btn smallbtn btn-y" title="修改密码" href="${ctx}/admin/user/toupdatepwd/${user.id}">修改密码</a> --%>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有短信信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
</body>
</html>
