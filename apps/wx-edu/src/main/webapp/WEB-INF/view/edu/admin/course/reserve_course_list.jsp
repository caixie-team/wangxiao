<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程预约列表</title>
<script type="text/javascript">
	function clean(){
        $("input").val("");
        $("#check").find('option').eq(0).attr('selected',true);
	}
	function checkCourse(id){
        dialogFun("课程预约列表","是否审核通过",2,"javascript:_checkCourse("+id+")")
	}
    function _checkCourse(id){
        $.ajax({
            url : "${ctx}/admin/cou/passReserveCourse",
            data : {"id" : id},
            type : "post",
            dataType : "json",
            success : function(result){
                if(result.success){
                    dialogFun("课程预约列表","审核通过",5,window.location.href);
                }else{
                    dialogFun("课程预约列表","系统错误",0);
                }
            }
        })
    }
</script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程预约管理</strong> / <small>课程预约列表</small></div>
</div>
<hr>
<form class="am-form" action="${ctx}/admin/cou/checkReserveCourse" name="searchForm" id="searchForm" method="post">
    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
    <div class="mt20 am-padding admin-content-list">
        <div class="am-tab-panel am-fade am-active am-in">
            <div class="am-g am-margin-top am-u-sm-4">
                <div class="am-u-sm-4 am-text-right">
                    用户名
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="courseReserveRecord.nickname" value="${courseReserveRecord.nickname}" id="nickname">
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-4">
                <div class="am-u-sm-4 am-text-right">
                    课程名称
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="courseReserveRecord.courseName" value="${courseReserveRecord.courseName}" id="name">
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-4">
                <div class="am-u-sm-4 am-text-right">
                    审核状态
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <select name="courseReserveRecord.check" id="check" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
                        <option value="" <c:if test="${empty courseReserveRecord.check}">selected</c:if>>请选择</option>
                        <option value="0" <c:if test="${courseReserveRecord.check==0}">selected</c:if>>未通过</option>
                        <option value="1" <c:if test="${courseReserveRecord.check==1}">selected</c:if>>通过</option>
                    </select>
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-4">
                <div class="am-u-sm-4 am-text-right">
                    用户ID
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="courseReserveRecord.userId" value="${courseReserveRecord.userId}" id="userId">
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-4">
                <div class="am-u-sm-4 am-text-right">
                    课程ID
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="courseReserveRecord.courseId" value="${courseReserveRecord.courseId}" id="courseId">
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-4">
                <button type="button" onclick="goPage(1)" class="am-btn am-btn-danger">查询</button>
                <button type="button" onclick="clean()" class="am-btn am-btn-danger">清空</button>
            </div>
            <div class="mt20 clear"></div>
        </div>
    </div>
</form>
<div class="mt20">
    <div class="doc-example">
        <div class="am-scrollable-horizontal">
            <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
                <thead>
                    <tr>
                        <th width=""><span>记录ID</span></th>
                        <th width=""><span>用户ID</span></th>
                        <th width=""><span>用户名</span></th>
                        <th width=""><span>课程ID</span></th>
                        <th width=""><span>课程名称</span></th>
                        <th width=""><span>审核</span></th>
                        <th width=""><span>预约时间</span></th>
                        <th width=""><span>操作</span></th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${not empty recordList}">
                    <c:forEach items="${recordList}" var="record">
                        <tr>
                            <td>${record.id}</td>
                            <td>${record.userId}</td>
                            <td>${record.showName}</td>
                            <td>${record.courseId}</td>
                            <td>${record.courseName}</td>
                            <td>
                                <c:if test="${record.check==0}">未通过</c:if>
                                <c:if test="${record.check==1}">通过</c:if>
                            </td>
                            <td><fmt:formatDate type="both" value="${record.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td>
                                <c:if test="${record.check==1 }">
                                    <a class="am-btn am-btn-success" href="javascript:void(0)">已通过</a>
                                </c:if>
                                <c:if test="${record.check==0}">
                                    <a class="am-btn am-btn-danger" href="javascript:void(0)" onclick="checkCourse(${record.id})">审核</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty recordList}">
                    <tr>
                        <td colspan="6">
                            <div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
                                <center><big>
                                        <i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
                                        <span class="vam ml10">还没有相关数据！</span>
                                </big></center>
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
