<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datetimepicker({
	    regional:"zh-CN",
	    changeYear: true,
        changeMonth: true,
        dateFormat:"yy-mm-dd ",
        timeFormat: "HH:mm:ss"
      });
      info();
});
function clean(){
	$("#userId,#optusername,#type,#bizId,#startDate,#endDate").val("");
}
function userExcel(){
	$("#searchForm").prop("action","${ctx}/admin/user/export");
	$("#searchForm").submit();
	$("#searchForm").prop("action","${ctx}/admin/user/list");
}
function info(){
    $("#type").val('${userOptRecord.type}');
}
</script>
</head>
<body>
    <div class="page_head">
        <h4><em class="icon14 i_01"></em>&nbsp;<span>学员管理</span> &gt; <span>记录列表</span> </h4>
    </div>
    <div class="mt20">
        <div class="commonWrap">
            <form action="${ctx}/admin/user/getUserOptRecordList" name="searchForm" id="searchForm" method="post">
                <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
                    <caption>
                        <div class="capHead">
                            <div class="w50pre fl">
                                <ul class="ddBar">
                                    <li>
                                        <span class="ddTitle"><font>学员ID：</font></span>
                                        <input type="text" name="userOptRecord.userId" value="${userOptRecord.userId}" id="userId" />
                                    </li>
                                    <li>
                                        <span class="ddTitle"><font>操作人：</font></span>
                                        <input type="text" name="userOptRecord.optusername" value="${userOptRecord.optusername}" id="optusername" />
                                    </li>
                                    <li>
                                        <span class="ddTitle"><font>开始日期：</font></span>
                                        <input type="text" name="userOptRecord.startDate" readonly value="${userOptRecord.startDate}"  id="startDate" class="AS-inp"/>
                                    </li>
                                </ul>
                            </div>
                            <div class="w50pre fl">
                                <ul class="ddBar">
                                    <li>
                                        <span class="ddTitle"><font>类型：</font></span>
                                        <select name="userOptRecord.type" id="type">
                                            <option value="">请选择</option>
                                            <option value="ADMINLOAD">后台充值</option>
                                            <option value="ADMINREFUND">后台扣款</option>
                                            <option value="GIVECOURSE">赠送课程</option>
                                            <option value="CLOSED">关闭课程</option>
                                            <option value="AUDIT">审核</option>
                                            <option value="REFUND">退费</option>
                                            <option value="ACTIVE">启用</option>
                                            <option value="DISABLE">禁用</option>
                                            <option value="CHANGEPWD">修改密码</option>
                                        </select>
                                    </li>
                                    <li>
                                        <span class="ddTitle"><font>业务ID：</font></span>
                                        <input type="text" name="userOptRecord.bizId" value="${userOptRecord.bizId}"  id="bizId"/>
                                    </li>
                                    <li>
                                        <span class="ddTitle"><font>结束日期：</font></span>
                                        <input type="text" name="userOptRecord.endDate" readonly value="${userOptRecord.endDate}"  id="endDate" class="AS-inp"/>
                                    </li>
                                    <li><input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="goPage(1)"/>
                                        <input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
                                    </li>
                                </ul>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </caption>
                    <thead>
                        <tr>
                            <th width="8%"><span>ID</span></th>
                            <th width="8%"><span>用户ID</span></th>
                            <th><span>操作类型</span></th>
                            <th><span>操作人</span></th>
                            <th><span>业务ID</span></th>
                            <th><span>时间</span></th>
                            <th><span>描述</span></th>
                        </tr>
                    </thead>
                    <tbody id="tabS_02" align="center">
                        <c:if test="${userOptRecordList.size()>0}">
                            <c:forEach  items="${userOptRecordList}" var="optRecord" >
                                <tr>
                                    <td>${optRecord.id }</td>
                                    <td>${optRecord.userId }</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${optRecord.type=='ADMINLOAD' }">后台充值</c:when>
                                            <c:when test="${optRecord.type=='ADMINREFUND' }">后台扣款</c:when>
                                            <c:when test="${optRecord.type=='AUDIT' }">审核</c:when>
                                            <c:when test="${optRecord.type=='REFUND' }">退费</c:when>
                                            <c:when test="${optRecord.type=='GIVECOURSE' }">赠送课程</c:when>
                                            <c:when test="${optRecord.type=='DISABLE' }">禁用</c:when>
                                            <c:when test="${optRecord.type=='ACTIVE' }">启用</c:when>
                                            <c:when test="${optRecord.type=='CLOSED' }">关闭课程</c:when>
                                            <c:when test="${optRecord.type=='CHANGEPWD' }">修改密码</c:when>
                                        </c:choose>
                                    </td>
                                    <td>${optRecord.optusername }</td>
                                    <td>${optRecord.bizId }</td>
                                    <td><fmt:formatDate value="${optRecord.createTime }" type="both"/></td>
                                    <td>
                                        ${optRecord.description }
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${userOptRecordList.size()==0||userOptRecordList==null}">
                            <tr>
                                <td align="center" colspan="16">
                                    <div class="tips">
                                    <span>还没有相关数据信息！</span>
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
        </div>
    </div>
</body>
</html>
