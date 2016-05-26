<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>讲义列表</title>

<script type="text/javascript">
    function delHandout(id){
        if(id!=null&&id!=''){
            dialogFun("讲义列表","是否确认删除讲义?",2,"javascript:_delHandout("+id+")");
        }else{
            dialogFun("讲义列表","讲义ID为空",0);
        }
    }
    function _delHandout(id){
        $.ajax({
            url:"${ctx}/admin/cou/deleteCourseHandout",
            type:"post",
            data:{"ids":id+","},
            dataType:"json",
            success:function(result){
                if(result.success){
                    setTimeout(function(){
                        dialogFun("讲义列表","删除成功！",5,"${ctx}/admin/cou/getCourseHandoutList?courseId=${course.id}");
                    },300);
                }else{
                    setTimeout(function(){
                        dialogFun("讲义列表","删除失败！",0);
                    },300);
                }
            }
        });
    }
    function submitSearch(){
        $("#pageCurrentPage").val(1);
        $("#searchForm").submit();
    }
    function submitclear(){
        $("#startTime,#endTime").val("");
    }
</script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程管理</strong> / <small>课程讲义</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/cou/getCourseHandoutList" name="searchForm" id="searchForm" method="post">
    <input type="hidden" name="handout.courseId" value="${course.id}" />
    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
    <div class="mt20 am-padding admin-content-list">
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    课程名称
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" readonly value="${course.name}">
                </div>
            </div>
            <div class="mt20 clear"></div>
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    开始时间
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" data-am-datepicker readonly name="handout.startTime" value="${handout.startTime}" id="startTime">
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-5">
                <div class="am-u-sm-4 am-text-right">
                    结束时间
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" data-am-datepicker readonly name="handout.endTime" value="${handout.endTime}" id="endTime">
                </div>
            </div>
            <div class="mt20 clear"></div>
        </div>
    </div>
    <div class="mt20">
        <div class="am-g">
            <div class="am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button class="am-btn am-btn-success" onclick="window.location.href='${ctx}/admin/cou/toAddCourseHandout/${course.id}'" type="button" title="新建讲义"><span class="am-icon-plus"></span> 新建讲义</button>
                    </div>
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
					<span class="am-input-group-btn">
						<button type="submit" class="am-btn am-btn-warning" onclick="submitSearch()">
                            <span class="am-icon-search"></span> 搜索
                        </button>
						<button type="button" class="am-btn am-btn-danger" onclick="submitclear()">
                            清空
                        </button>
					</span>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="mt20">
    <div class="doc-example">
        <div class="am-scrollable-horizontal">
            <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
                <thead>
                <tr>
                    <th width="8%">
                        <span>&nbsp;ID</span>
                    </th>
                    <th>
                        <span>讲义名称</span>
                    </th>
                    <th>
                        <span>路径</span>
                    </th>
                    <th>
                        <span>添加时间</span>
                    </th>
                    <th>
                        <span>操作</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty courseHandoutList}">
                    <c:forEach items="${courseHandoutList}" var="handout">
                        <tr>
                            <td>${handout.id}</td>
                            <td>${handout.name }</td>
                            <td>${handout.path }</td>
                            <td>
                                <fmt:formatDate type="both" value="${handout.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td class="c_666 czBtn" align="center">
                                <a class="am-btn am-btn-default am-btn-xs am-text-secondary" title="修改" href="${ctx}/admin/cou/toUpdateCourseHandout/${handout.id}">修改</a>
                                <a class="am-btn am-btn-default am-btn-xs am-text-danger" title="删除" onclick="delHandout(${handout.id})">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty courseHandoutList}">
                    <tr>
                        <td colspan="8">
                            <div data-am-alert=""
                                 class="am-alert am-alert-secondary mt20 mb50">
                                <center>
                                    <big> <i class="am-icon-frown-o vam"
                                             style="font-size: 34px;"></i> <span class="vam ml10">还没有相关数据！</span></big>
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
