<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>岗位任务</title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/plan/plan.css?v=${v}"/>
    <script src="${ctximg}/static/nxb/web/js/plan/plan.js?v=${v}"></script>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
    <script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
    <script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">计划管理</strong> / <small>新建岗位计划</small></div>
</div>
<hr>
<form action="/admin/plan/updatePlan" method="post" class="am-form" data-am-validator id="addPlan">
    <input type="hidden" name="plan.type" id="planType" value="1">
    <input id="emIds" type="hidden" name="queryPlan.examIds" value="${queryPlan.examIds}"/>
    <input type="hidden" name="courseIds" id="courseIdHidden"/>
    <input id="emNames" type="hidden" name="plan.examNames" value=""/>
    <input name="queryPlan.groupIds" type="hidden" id="groupIds">

    <input type="hidden" name="plan.id" id="planId" value="${planOld.id}">
    <input type="hidden" name="plan.submit" id="planSubmit" value="${planOld.submit}">
    <input type="hidden" name="phase.id" id="phaseId" value="${phaseOld.id}">
    <input type="hidden" id="phaseRankCount">
    <input type="hidden" id="phaseListSize">
    <div class="mt20">
        <div data-am-tabs="" class="am-tabs">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">计划基本信息</a></li>
                <li class=""><a href="#tab2">指定学习内容</a></li>
                <li class=""><a href="#tab3">指定学习者</a></li>
                <li class=""><a href="#tab4">计划概要及设置</a></li>
            </ul>
            <div class="am-tabs-bd">
                <div id="tab1" class="am-tab-panel am-fade am-active am-in">
                    <div class="am-g am-margin-top am-form-group">
                        <label for="planName" class="am-u-sm-4 am-u-md-2 am-text-right">
                            计划名称
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <input type="text" name="plan.name" id="planName" class="am-input-sm" required placeholder="请填写计划名称" value="${planOld.name}">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <label for="beginTime" class="am-u-sm-4 am-u-md-2 am-text-right">
                            计划开始时间
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <input type="text" name="plan.beginTime" id="beginTime" readonly="readonly" class="form-datetime-lang am-form-field am-input-sm" required value="<fmt:formatDate value="${planOld.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <label for="endTime" class="am-u-sm-4 am-u-md-2 am-text-right">
                            计划结束时间
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <input type="text" name="plan.endTime" id="endTime" readonly="readonly" class="form-datetime-lang am-form-field am-input-sm" required value="<fmt:formatDate value="${planOld.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            &nbsp;
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <a type="submit" class="am-btn am-btn-danger" onclick="nextStep(1)">
                                下一步
                            </a>
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"></div>
                    </div>
                </div>

                <div id="tab2" class="am-tab-panel am-fade am-in">
                    <div class="am-g am-margin-top am-form-group">
                        <div class="am-u-sm-4 am-u-md-2 am-text-right">
                            <span class="iconaddcourse" onclick="AddStudyPhase();" title="添加阶段"></span>
                            <span class="iconupdisable" onclick="studyPhaselRank('cup');" title="上移阶段"></span>
                            <span class="icondowndisable" onclick="studyPhaselRank('cdown');" title="下移阶段"></span>
                            <span class="icondelete" onclick="delStudyPhaseRank();" title="移除阶段"></span>
                            <div class="" id="phaseAjax"></div>
                        </div>
                        <div>
                            <div class="" id="phaseDetailInfo"></div>
                        </div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            &nbsp;
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <a type="submit" class="am-btn am-btn-danger" onclick="nextStep(2)">
                                下一步
                            </a>
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"></div>
                    </div>
                </div>

                <div id="tab3" class="am-tab-panel am-fade am-in">
                    <div class="am-g">
                        <div class="mt20 am-scrollable-horizontal">
                            <div class="mt10">
                                <table class="am-table am-table-bd am-table-striped admin-content-table">
                                    <tr>
                                        <td width="20%" align="center">此计划指派给谁</td>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td>
                                                    <c:forEach var="group" items="${userGroupList}" varStatus="count">
                                                    <div class="am-u-sm-6 am-u-md-6 am-u-end">
                                                        <label class="am-checkbox">
                                                            <input type="checkbox" data-am-ucheck="" name="groupId" value="${group.id}" class="am-ucheck-checkbox">
                                                            <span class="am-ucheck-icons">
                                                                <i class="am-icon-unchecked"></i>
                                                                <i class="am-icon-checked"></i>
                                                            </span>
                                                            ${group.name}
                                                        </label>
                                                      </div>
                                                    </c:forEach>
                                                    </td>
                                                <tr/>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            &nbsp;
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <a type="submit" class="am-btn am-btn-danger" onclick="nextStep(3)">
                                下一步
                            </a>
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"></div>
                    </div>
                </div>
                <div id="tab4" class="am-tab-panel am-fade am-in">
                    <input type="hidden" value="0" name="plan.status" />
                    <div class="am-g">
                        <div class="mt20 am-scrollable-horizontal">
                            <div class="mt10">
                                <table class="am-table am-table-bd am-table-striped admin-content-table">
                                    <tr>
                                        <td rowspan="2" class="am-text-center">当前计划概要</td><td>总知识数</td><td>总学时数</td><td>试卷数</td><td>课程数</td>
                                    </tr>
                                    <tr>
                                        <td><span id="knowledgeNo" class="">0</span>个</td>
                                        <td><span id="studyTimeNo" class="">0</span>分钟</td>
                                        <td><span id="examNo" class="">0</span>个</td>
                                        <td><span id="courseNo" class="">0</span>个</td>
                                    </tr>
                                    <tr>
                                        <td class="am-text-center">是否重复</td>
                                        <td>
                                            <div class="am-u-sm-12">
                                                <div class="am-u-sm-6">
                                                    <label class="am-radio">
                                                        <input type="radio" data-am-ucheck="" value="0" checked name="plan.isRepeat">
                                                        不可重复
                                                    </label>
                                                </div>
                                                <div class="am-u-sm-6">
                                                    <label class="am-radio">
                                                        <input type="radio" data-am-ucheck="" value="1" name="plan.isRepeat" >
                                                        可以重复
                                                    </label>
                                                </div>
                                            </div>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            &nbsp;
                        </label>
                        <div class="am-u-sm-8 am-u-md-4">
                            <a type="submit" class="am-btn am-btn-danger" onclick="submitAddPlan()">
                                提交
                            </a>
                            <a type="button" class="am-btn am-btn-warning" onclick="window.history.go(-1)">
                                返回
                            </a>
                        </div>
                        <div class="am-hide-sm-only am-u-md-6"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>