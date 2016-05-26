<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        function clean(){
            $("#brand,#ip,#type,#size,#startTime,#endTime").val("");
            $("input[name='startTime']:first").attr("value", "");
            $("input[name='endTime']:first").attr("value", "");
        }
    </script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计</strong> / <small>安装记录</small></div>
</div>
<hr>

<div class="mt20 am-padding admin-content-list">
    <form action="${ctx}/admin/install/list" method="post" id="searchForm" class="am-form">
        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    品牌
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteInstall.brand" value="${websiteInstall.brand}" id="brand" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    IP
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteInstall.ip" value="${websiteInstall.ip}" id="ip" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    型号
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteInstall.type" value="${websiteInstall.type}" id="type" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    开始登陆时间
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly class="am-input-sm" id="startTime" name="startTime" value="<fmt:formatDate value='${websiteInstall.startTime}' pattern='yyyy-MM-dd' />" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    尺寸
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="websiteInstall.size" value="${websiteInstall.size}" id="size" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    结束登陆时间
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly class="am-input-sm" id="endTime" name="endTime" value="<fmt:formatDate value='${websiteInstall.endTime}' pattern='yyyy-MM-dd' />" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                &nbsp;
            </div>
            <div class="am-g am-margin-top am-u-sm-6 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    &nbsp;
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <button type="submit" class="am-btn am-btn-danger">查询</button>
                    <button type="button" class="am-btn am-btn-danger" onclick="clean()" >清空</button>
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>
    </form>
</div>
<div class="am-g">
    <div class="mt20 am-scrollable-horizontal">
        <div class="mt10">
            <table class="am-table am-table-bd am-table-striped admin-content-table">
                <thead>
                <tr>
                    <th><span>ID</span></th>
                    <th><span>ip</span></th>
                    <th><span>品牌</span></th>
                    <th><span>型号</span></th>
                    <th><span>尺寸</span></th>
                    <th><span>登陆时间</span></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${websiteInstallList.size()>0}">
                    <c:forEach  items="${websiteInstallList}" var="websiteInstall" >
                        <tr>
                            <td>${websiteInstall.id }</td>
                            <td>${websiteInstall.ip }</td>
                            <td>${websiteInstall.brand }</td>
                            <td>${websiteInstall.type }</td>
                            <td>${websiteInstall.size}</td>
                            <td><fmt:formatDate value='${websiteInstall.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>

                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty websiteInstallList}">
                    <tr>
                        <td colspan="9">
                            <div data-am-alert=""
                                 class="am-alert am-alert-secondary mt20 mb50">
                                <center>
                                    <big> <i class="am-icon-frown-o vam"
                                             style="font-size: 34px;"></i> <span class="vam ml10">还没有安装记录！</span></big>
                                </center>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
        </div>
    </div>
</div>
</body>
</html>