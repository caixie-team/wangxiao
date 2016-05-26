<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>计划列表</title>
    <script type="text/javascript">
        $(function(){
            $("#status").val("${plan.status}");
            $("#type").val("${plan.type}");
            // 启动计划
            $('.start-plan').on('click', function() {
                var planId = $(this).attr("data-id");
                dialogFun("学习计划","启动后不可修改，确定启动计划吗?",2,"${ctx}/admin/plan/updatePlanStatus/"+planId);
            });
            // 修改计划
            $(".update-plan").on('click',function() {
                var planId = $(this).attr("data-id");
                window.location.href="${ctx}/admin/plan/toUpdatePlan/"+planId;
            });
            // 复制计划
            $('.copy-plan').on('click', function() {
                $('#my-prompt').modal({
                    relatedTarget: this,
                    onConfirm: function(options) {
                        var $link = $(this.relatedTarget);
                        var planName = options.data;
                        if(planName==null||planName==''){
                            dialogFun("计划管理","计划名称不能为空",0);
                        }
                        window.location.href="${ctx}/admin/plan/copyPlan/"+planName+"/"+$link.data('id');
                    },
                    onCancel: function() {
                        $("#my-prompt").find("input").val("");
                    }
                });
            });
            // 修改计划
            $(".delete-plan").on('click',function() {
                var planId = $(this).attr("data-id");
                dialogFun("学习计划","你确定要作废计划吗?",2,"${ctx}/admin/plan/invalidPlan/"+planId);
            });
        });

        //清空查询条件
        function clean(){
            $("#name").val("");
            $("#status").val("");
            $("#status").find('option').eq(0).attr('selected', true);
            $("#type").val("");
            $("#type").find('option').eq(0).attr('selected', true);
        }
    </script>
</head>
<body>
    <div class="am-cf">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">计划管理</strong> / <small>计划列表</small></div>
    </div>
    <hr>
    <form class="am-form" action="${ctx}/admin/plan/planList" name="searchForm" id="searchForm" method="post">
        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
        <div class="mt20 am-padding admin-content-list">
            <div class="am-tab-panel am-fade am-active am-in">
                <div class="am-g am-margin-top am-u-sm-4">
                    <div class="am-u-sm-4 am-text-right">
                        名称
                    </div>
                    <div class="am-u-sm-8 am-u-end">
                        <input type="text" class="am-input-sm" name="queryPlan.name" value="${plan.name}" id="name">
                    </div>
                </div>
                <div class="am-g am-margin-top am-u-sm-4">
                    <div class="am-u-sm-4 am-text-right">
                        状态
                    </div>
                    <div class="am-u-sm-8 am-u-end">
                        <select name="queryPlan.status" id="status" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
                            <option value="-1">请选择</option>
                            <option value="0">未开启</option>
                            <option value="1">开启</option>
                        </select>
                    </div>
                </div>
                <div class="am-g am-margin-top am-u-sm-4">
                    <div class="am-u-sm-4 am-text-right">
                        类型
                    </div>
                    <div class="am-u-sm-8 am-u-end">
                        <select name="queryPlan.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
                            <option value="-1">请选择</option>
                            <option value="0">个人计划</option>
                            <option value="1">岗位计划</option>
                        </select>
                    </div>
                </div>
                <div class="mt20 clear"></div>
                <div class="am-g am-margin-top am-u-sm-4">
                    <div class="am-u-sm-4 am-text-right">
                        &nbsp;
                    </div>
                    <div class="am-u-sm-8">
                        <button type="button" onclick="goPage(1)" class="am-btn am-btn-warning">
                            <span class="am-icon-search"></span> 搜索
                        </button>
                        <button type="button" class="am-btn am-btn-danger" onclick="clean()">
                            清空
                        </button>
                    </div>
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
                            <th class="table-title">ID</th>
                            <th class="table-title">名称</th>
                            <th class="table-title">开始时间</th>
                            <th class="table-title">结束时间</th>
                            <th class="table-title">发布时间</th>
                            <th class="table-title">状态</th>
                            <th class="table-title">是否重复</th>
                            <th class="table-title">类型</th>
                            <th class="table-title">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty planList}">
                            <c:forEach items="${planList}" var="plan">
                                <tr>
                                    <td>${plan.id }</td>
                                    <td>${plan.name }</td>
                                    <td><fmt:formatDate value="${plan.beginTime }" pattern="yyy-MM-dd HH:mm:ss" /></td>
                                    <td><fmt:formatDate value="${plan.endTime }" pattern="yyy-MM-dd HH:mm:ss" /></td>
                                    <td><fmt:formatDate value="${plan.releaseTime }" pattern="yyy-MM-dd HH:mm:ss" /></td>
                                    <td>
                                        <c:if test="${plan.status==0}">未开启</c:if>
                                        <c:if test="${plan.status==1}">开启</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${plan.isRepeat==0}">不可重复</c:if>
                                        <c:if test="${plan.isRepeat==1}">可重复</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${plan.type==0}">个人计划</c:if>
                                        <c:if test="${plan.type==1}">岗位计划</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${plan.status==0}">
                                            <a class="am-btn am-btn-default start-plan am-text-primary" data-id="${plan.id}">
                                                <span class="am-icon-pencil-square-o"></span> 启动
                                            </a>
                                            <a class="am-btn am-btn-default update-plan am-text-warning" data-id="${plan.id}">
                                                <span class="am-icon-pencil-square-o"></span> 修改
                                            </a>
                                            <a class="am-btn am-btn-default copy-plan am-text-primary" data-id="${plan.id}">
                                                <span class="am-icon-copy"></span> 复制
                                            </a>
                                        </c:if>
                                        <c:if test="${plan.status==1}">
                                            <a href="javascript:;" class="am-btn am-btn-default delete-plan am-text-danger" data-id="${plan.id}">
                                                <span class="am-icon-trash-o"></span> 作废
                                            </a>
                                            <a class="am-btn am-btn-default am-text-primary" href="${ctx}/admin/plan/planProgress/${plan.id}">
                                                <span class="am-icon-list"></span> 计划进度
                                            </a>
                                            <a class="am-btn am-btn-default am-text-secondary" href="${ctx}/admin/plan/planInfo/${plan.id}">
                                                <span class="am-icon-list"></span> 详细
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty planList}">
                            <tr>
                                <td colspan="9">
                                    <div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
                                        <center>
                                            <big>
                                                <i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
                                                <span class="vam ml10">还没有相关数据！</span>
                                            </big>
                                        </center>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
        </div>
    </div>
    <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">Amaze UI</div>
            <div class="am-modal-bd">
                你，确定要删除这条记录吗？
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                <span class="am-modal-btn" data-am-modal-confirm>确定</span>
            </div>
        </div>
    </div>
    <div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">计划管理</div>
            <div class="am-modal-bd">
                请输入新的计划名称
                <input type="text" class="am-modal-prompt-input">
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                <span class="am-modal-btn" data-am-modal-confirm>提交</span>
            </div>
        </div>
    </div>
</body>
</html>
