<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>订单流水操作记录</title>
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
        $("#trxorderId,#trxorderDetailId,#optusername,#type,#startDate,#endDate").val("");
    }
    function info(){
        $("#type").val('${trxdetailOptRecord.type}');
    }
</script>
</head>
<body style="background: #F5F5F5;">
		<form action="${ctx}/admin/order/getOrderDetailOptRecordList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>
						&nbsp;
						<span>订单流水管理</span>
						&gt;
						<span>操作记录</span>
					</h4>
				</div>
				<div class="mt20">
                    <div class="commonWrap">
                        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                <div class="capHead">
                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>订单号：</font></span>
                                                <input type="text" name="trxdetailOptRecord.trxorderId" value="${trxdetailOptRecord.trxorderId}" id="trxorderId" />
                                            </li>
                                            <li>
                                                <span class="ddTitle"><font>操作人：</font></span>
                                                <input type="text" name="trxdetailOptRecord.optusername" value="${trxdetailOptRecord.optusername}" id="optusername" />
                                            </li>
                                            <li>
                                                <span class="ddTitle"><font>开始日期：</font></span>
                                                <input type="text" name="trxdetailOptRecord.startDate" readonly  value="${trxdetailOptRecord.startDate}" id="startDate" class="AS-inp"/>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>流水号：</font></span>
                                                <input type="text" name="trxdetailOptRecord.trxorderDetailId" value="${trxdetailOptRecord.trxorderDetailId}" id="trxorderDetailId" />
                                            </li>
                                            <li>
                                                <span class="ddTitle"><font>类型：</font></span>
                                                <select name="trxdetailOptRecord.type" id="type">
                                                    <option value="">请选择</option>
                                                    <option value="退费">退费</option>
                                                    <option value="审核">审核</option>
                                                    <option value="关闭课程">关闭课程</option>
                                                </select>
                                            </li>
                                            <li>
                                                <span class="ddTitle"><font>结束日期：</font></span>
                                                <input type="text" name="trxdetailOptRecord.endDate" readonly value="${trxdetailOptRecord.endDate}"  id="endDate" class="AS-inp"/>
                                            </li>

                                            <li>
                                                <input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="goPage(1)"/>
                                                <input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </caption>
                            <thead>
                                <tr>
                                    <th width="10%">
                                        <span>ID</span>
                                    </th>
                                    <th width="10%">
                                        <span>订单号</span>
                                    </th>
                                    <th width="10%">
                                        <span>流水号</span>
                                    </th>
                                    <th width="10%">
                                        <span>类型</span>
                                    </th>
                                    <th width="10%">
                                        <span>操作人</span>
                                    </th>
                                    <th width="20%">
                                        <span>时间</span>
                                    </th>
                                    <th width="30%">
                                        <span>描述</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="tabS_02" align="center">
                                <c:if test="${trxdetailOptRecordList.size()>0}">
                                    <c:forEach items="${trxdetailOptRecordList}" var="optRecord">
                                        <tr id="rem${cou.id }">
                                            <td>${optRecord.id}</td>
                                            <td>${optRecord.trxorderId}</td>
                                            <td>${optRecord.trxorderDetailId}</td>
                                            <td>${optRecord.type}</td>
                                            <td>${optRecord.optusername}</td>
                                            <td><fmt:formatDate type="both" value="${optRecord.createTime }" /></td>
                                            <td>${optRecord.desc}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${trxdetailOptRecordList.size()==0||trxdetailOptRecordList==null}">
                                    <tr>
                                        <td align="center" colspan="16">
                                            <div class="tips">
                                                <span>还没有相关数据！</span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                        <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
                    </div>
				</div>
		</form>
</body>
</html>
