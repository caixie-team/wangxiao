<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<div class="">
    <div class="">
        <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                阶段名称：
            </div>
            <div class="am-u-sm-8 am-u-md-4">
                <input name="phase.phaseName" type="text" required value="${phase.phaseName}" maxlength="18"
                       id="txtPhaseName" class="textbox am-input-sm" style="width:350px;"
                       onblur="PhaseChangeFiled(this,${phase.id},'phaseName');">
                <input type="hidden" name="phase.id" value="${phase.id}">
                <input type="hidden" id="phaseRank" name="phase.phaseRank" value="${phase.phaseRank}">
                <input type="hidden" id="status" name="phase.status" value="${phase.status}">
            </div>
            <div class="am-hide-sm-only am-u-md-6"></div>

            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                描述：
            </div>
            <div class="am-u-sm-8 am-u-md-4">
                <textarea name="phase.phaseDescribe" rows="3" cols="20" id="txtSummry" class="input" style="width:540px;"
                          onblur="PhaseChangeFiled(this,${phase.id},'phaseDescribe');">${phase.phaseDescribe}</textarea>
            </div>
            <div class="am-hide-sm-only am-u-md-6"></div>
        </div>
    </div>
    <br>
    <div class="am-pagination-centered">
        <a name="addKng" id="addKng" class="am-btn am-btn-danger" onclick="AddKnowledge()">
            添加知识
        </a>
        <a name="addTest" type="button" id="addTest" class="am-btn am-btn-warning" onclick="showNewExam()">
            添加考试
        </a>
        <span class="" style="">仅支持一般考试类型。</span>
    </div>
    <br>
    <div class="am-g">
        <div class="mt20">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th class="table-title" width="15%">序号</th>
                    <th class="table-title" width="25%">名称</th>
                    <th class="table-title" width="15%">学时(分钟)</th>
                    <th class="table-title" width="15%">类型</th>
                    <th class="table-title" width="30%">操作</th>
                </tr>
                </thead>
                <tbody id="tabS_02">
                <c:if test="${phaseDetailList.size() > 0}">
                    <c:forEach var="phaseDetail" items="${phaseDetailList}" varStatus="status">
                        <tr id="phaseDetail${status.count}" class="">
                            <input type='hidden' class='detailCount'/>
                            <input type='hidden' id='id${status.count}' name='id${status.count}' value="${phaseDetail.id}">
                            <input type='hidden' id='phaseId${status.count}' name='phaseId${status.count}' value="${phase.id}">
                            <input type='hidden' id='otherId${status.count}' name='otherId${status.count}' value="${phaseDetail.otherId}">
                            <input type='hidden' id='type${status.count}' name='type${status.count}' value="${phaseDetail.type}">
                            <input type='hidden' id='detailName${status.count}' name='detailName${status.count}' value="${phaseDetail.detailName}">
                            <input type='hidden' id='hours${status.count}' name='hours${status.count}' value="${phaseDetail.hours}">
                            <input type='hidden' id='detailRank${status.count}' name='detailRank${status.count}' value="${status.count}">
                            <td>${phaseDetail.detailRank}</td>
                            <td>${phaseDetail.detailName}</td>
                            <td>${phaseDetail.hours}</td>
                            <td>
                                <c:if test="${phaseDetail.type==1}">
                                    试卷
                                </c:if>
                                <c:if test="${phaseDetail.type==2}">
                                    课程
                                </c:if>
                            </td>
                            <td>
                                <a class="am-btn am-btn-link" onclick="" style="display:none;">考试设置</a>&nbsp;&nbsp;
                                <a class="am-btn am-btn-link" onclick="phaseDetailRank('cup',${phaseDetail.id},${status.count},${phaseDetail.phaseId});">上移</a>&nbsp;&nbsp;
                                <a class="am-btn am-btn-link" onclick="phaseDetailRank('cdown',${phaseDetail.id},${status.count},${phaseDetail.phaseId});">下移</a>&nbsp;&nbsp;
                                <a class="am-btn am-btn-link" onclick="delPhaseDetailRank(${phaseDetail.id},${phaseDetail.phaseId});">移除</a>&nbsp;&nbsp;
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    summarySchedule();
</script>