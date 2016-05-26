<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css"
          href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
    <link rel="stylesheet" type="text/css"
          href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}"/>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript">
        //日历空间
        $(function () {
            $("#startTime,#endTime")
                    .datetimepicker({
                        regional: "zh-CN",
                        changeYear: true,
                        changeMonth: true,
                        dateFormat: "yy-mm-dd",
                        timeFormat: 'HH:mm:ss',
                        timeFormat: 'HH:mm:ss'
                    });

        });

        function submitSearch() {
            $("#searchForm").submit();
        }

        function clean(){
            $("#brand").val("");
            $("#email,#ip,#type,#startTime,#endTime,#size").val("");

        }
    </script>
</head>
<body>

<div class="am-cf">
    <strong class="am-text-primary am-text-lg">登陆记录</strong>
    /
    <small>登陆列表</small>
</div>


<form  action="${ctx}/admin/login/list" name="searchForm" id="searchForm" method="post"  class="am-form">
    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
    <div class="mt20 am-padding admin-content-list">
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    邮箱
                </div>
                <div class="am-u-sm-8">
                    <input type="text" name="websiteLogin.email" value="${websiteLogin.email}" id="email" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    品牌
                </div>
                <div class="am-u-sm-8">
                    <input type="text" name="websiteLogin.brand" value="${websiteLogin.brand}" id="brand"/>
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>

        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    IP
                </div>
                <div class="am-u-sm-8">
                    <input type="text" name="websiteLogin.ip" value="${websiteLogin.ip}" id="ip" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    型号
                </div>
                <div class="am-u-sm-8">
                    <input type="text" name="websiteLogin.type" value="${websiteLogin.type}" id="type"/>
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    开始时间
                </div>
                <div class="am-u-sm-8">
                    <input type="text" id="startTime" name="websiteLogin.startTime"        value="<fmt:formatDate value='${websiteLogin.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    尺寸
                </div>
                <div class="am-u-sm-8">
                    <input type="text" name="websiteLogin.size" value="${websiteLogin.size}" id="size"/>
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    结束时间
                </div>
                <div class="am-u-sm-8">
                    <input type="text" name="websiteLogin.endTime" id="endTime"        value="<fmt:formatDate value='${websiteLogin.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  />
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>
    </div>
	<div class="mt20">
			<div class="am-g">
				<div class="am-u-md-6">
				</div>
				<div class="am-u-sm-12 am-u-md-3">
					<div class="am-input-group am-input-group-sm">
						<span class="am-input-group-btn">
							<button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">查询</button>
							<button type="button" class="am-btn am-btn-primary" onclick="clean()">清空</button>
						</span>
					</div>
				</div>
			</div>
		</div>
</form>


<div class="mt20">
    <div class="doc-example">
        <div class="am-scrollable-horizontal">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
            <table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
                <thead>
                <tr>
                    <th>
                        <label class="am-checkbox">
                            <input name="articleIds" type="checkbox" data-am-ucheck onclick="allCheck(this)"/>
                        </label>
                    </th>
                    <th width="10%"><span>&nbsp;ID</span></th>
                    <th width="10%"><span>邮箱</span></th>
                    <th width="20%"><span>IP</span></th>
                    <th width="20%"><span>型号</span></th>
                    <th width="10%"><span>尺寸</span></th>
                    <th width="20%"><span>登陆时间</span></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${articleList.size()>0}">
                    <c:forEach items="${articleList}" var="art">
                        <tr id="rem${art.id }">
                            <td>${websiteLogin.id }</td>
                            <td>${websiteLogin.email}</td>
                            <td>${websiteLogin.ip }</td>
                            <td>${websiteLogin.brand }</td>
                            <td>${websiteLogin.type }</td>
                            <td>${websiteLogin.size}</td>
                            <td><fmt:formatDate value='${websiteLogin.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${articleList.size()==0||articleList==null}">
                    <tr>
                        <td align="center" colspan="16">
                            <div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
                                <center>
                                    <big>
                                        <i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
                                        <span class="vam ml10">还没有登陆记录信息！</span>
                                    </big>
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
<!-- /pageBar begin -->
<jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
<!-- /pageBar end -->


</body>
</html>