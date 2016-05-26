<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>使用记录</title>
    <script type="text/javascript">
        function clean(){
            $("#email,#brand,#ip,#type,#size,#startTime,#endTime").val("");
            $("input[name='startTime']:first").attr("value", "");
            $("input[name='endTime']:first").attr("value", "");
        }
    </script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计</strong> / <small>使用记录</small></div>
</div>
<hr>

<div class="mt20 am-padding admin-content-list">
    <form action="${ctx}/admin/use/list" method="post" id="searchForm" class="am-form">
        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    邮箱
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteUse.email" value="${websiteUse.email}" id="email" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    品牌
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteUse.brand" value="${websiteUse.brand}" id="brand" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    IP
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteUse.ip" value="${websiteUse.ip}" id="ip" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    型号
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteUse.type" value="${websiteUse.type}" id="type" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    开始登陆时间
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly class="am-input-sm" id="startTime" name="websiteUse.startTime" value="<fmt:formatDate value='${websiteUse.startTime}' pattern='yyyy-MM-dd' />" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    尺寸
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteUse.size" value="${websiteUse.size}" id="size" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    结束登陆时间
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly class="am-input-sm" id="endTime" name="websiteUse.endTime" value="<fmt:formatDate value='${websiteUse.endTime}' pattern='yyyy-MM-dd' />" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    &nbsp;
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <button type="button" class="am-btn am-btn-danger" onclick="goPage(1)">查询</button>
                    <button type="button" class="am-btn am-btn-danger" onclick="clean()" >清空</button>
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>
    </form>
</div>
<div class="mt20">
    <div class="doc-example">
        <div class="am-scrollable-horizontal">
            <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
                <thead>
                <tr>
                    <th width="5%"><span>ID</span></th>
                    <th><span>邮箱</span></th>
                    <th><span>ip</span></th>
                    <th><span>品牌</span></th>
                    <th><span>型号</span></th>
                    <th><span>尺寸</span></th>
                    <th><span>创建时间</span></th>
                    <th><span>开始使用时间</span></th>
                    <th><span>结束使用时间</span></th>
                </tr>
                </thead>
                <tbody>
                    <c:if test="${websiteUseList.size()>0}">
                        <c:forEach  items="${websiteUseList}" var="websiteUse" >
                            <tr>
                                <td>${websiteUse.id }</td>
                                <td>${websiteUse.email}</td>
                                <td>${websiteUse.ip }</td>
                                <td>${websiteUse.brand }</td>
                                <td>${websiteUse.type }</td>
                                <td>${websiteUse.size}</td>
                                <td><fmt:formatDate value='${websiteUse.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
                                <td><fmt:formatDate value='${websiteUse.beginTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
                                <td><fmt:formatDate value='${websiteUse.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>

                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty websiteUseList}">
                        <tr>
                            <td colspan="9">
                                <div data-am-alert=""
                                     class="am-alert am-alert-secondary mt20 mb50">
                                    <center>
                                        <big> <i class="am-icon-frown-o vam"
                                                 style="font-size: 34px;"></i> <span class="vam ml10">还没有使用记录！</span></big>
                                    </center>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>