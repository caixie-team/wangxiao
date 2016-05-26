<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
    //日历空间
    $(function() {
        $("#startTime,#endTime")
                .datetimepicker({
                    regional:"zh-CN",
                    changeMonth: true,
                    dateFormat:"yy-mm-dd",
                    timeFormat : 'HH:mm:ss',
                    timeFormat : 'HH:mm:ss'
                });

    });

    function submitSearch(){
        $("#searchForm").submit();
    }
</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>统计</span> &gt; <span>登陆记录</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
			<div class="commonWrap">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                    <form action="${ctx}/admin/login/list" method="post" id="searchForm">
                        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" class="ml10 hasDatepicker"/>
                    <caption>
                        <div class="capHead">
                            <div class="w50pre fl">
                                <ul class="ddBar">
                                    <li><span class="ddTitle"><font>邮箱：</font></span>
                                        <input type="text" name="websiteLogin.email" value="${websiteLogin.email}" id="email" class="ml10 hasDatepicker"/>
                                    </li>
                                    <li><span class="ddTitle"><font>ip：</font></span>
                                        <input type="text" name="websiteLogin.ip" value="${websiteLogin.ip}" id="ip" class="ml10 hasDatepicker"/>
                                    </li>
                                    <li><span class="ddTitle"><font>开始登陆时间：</font></span>
                                        <input type="text" id="startTime" name="websiteLogin.startTime" value="<fmt:formatDate value='${websiteLogin.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  class="ml10" />
                                    </li>
                                    <li><span class="ddTitle"><font>结束登陆时间：</font></span>
                                        <input type="text" name="websiteLogin.endTime" id="endTime" value="<fmt:formatDate value='${websiteLogin.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  class="ml10" />
                                    </li>
                                </ul>
                            </div>
                            <div class="w50pre fl">
                                <ul class="ddBar">
                                    <li><span class="ddTitle"><font>品牌：</font></span>
                                        <input type="text" name="websiteLogin.brand" value="${websiteLogin.brand}" id="brand" />
                                    </li>
                                    <li><span class="ddTitle"><font>型号：</font></span>
                                        <input type="text" name="websiteLogin.type" value="${websiteLogin.type}" id="type" />
                                    </li>
                                    <li><span class="ddTitle"><font>尺寸：</font></span>
                                        <input type="text" name="websiteLogin.size" value="${websiteLogin.size}" id="size" />
                                    </li>

                                </ul>
                            </div>
                            <div class="w50pre fl" style="text-align: center;">
                                <ul class="ddBar" >
                                    <li>
                                        <input type="button" class="btn btn-danger mt10" value="查询" name="" onclick="submitSearch()" />


                                        </span>
                                    </li>
                                </ul>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </caption>
                    </form>

					<thead>
						<tr>
							<th width="5%"><span>ID</span></th>
                            <th><span>邮箱</span></th>
                            <th><span>ip</span></th>
                            <th><span>品牌</span></th>
                            <th><span>型号</span></th>
                            <th><span>尺寸</span></th>
                            <th><span>登陆时间</span></th>


						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
					<c:if test="${websiteLoginList.size()>0}">
					<c:forEach  items="${websiteLoginList}" var="websiteLogin" >
						<tr>
							<td>${websiteLogin.id }</td>
                            <td>${websiteLogin.email}</td>
							<td>${websiteLogin.ip }</td>
							<td>${websiteLogin.brand }</td>
							<td>${websiteLogin.type }</td>
							<td>${websiteLogin.size}</td>
                            <td><fmt:formatDate value='${websiteLogin.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>

						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty websiteLoginList}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有登陆记录！</span>
								</div>
							</td>
						</tr>
						</c:if>

					</tbody>
				</table>
                <!-- /pageBar begin -->
                <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
                <!-- /pageBar end -->
			</div>
		</div>
</body>
</html>