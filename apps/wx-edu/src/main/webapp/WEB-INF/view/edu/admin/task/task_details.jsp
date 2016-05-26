<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>详情</title>
    <script type="text/javascript">
        $(function () {
            var status = ${taskOld.status};
            $("#status").val(status);
            var isRepeat = ${taskOld.isRepeat};
            $("#isRepeat").val(isRepeat);

        });

        $(function () {
            var taskId = $("#taskId").val();
            if (isNotEmpty(taskId)) {
                getPhaseListAjax(taskId);
            }
        });

        $(function () {
            var phaseId = $("#phaseId").val();
            if (isNotEmpty(phaseId)) {
                getPhaseById(phaseId);
            }
        });

        //当前计划概要:
        $(function () {
            summarySchedule();
        });

        //选中某个阶段
        function SelectStudyPhase(phaseId) {
            $("#phaseId").val(phaseId);
            getPhaseById(phaseId);
        }

        //计划概要
        function summarySchedule() {
            var taskId = $("#taskId").val();
            $.ajax({
                url: "${ctx}/admin/phase/summarySchedule/" + taskId,
                data: {},
                dataType: "json",
                type: "post",
                async: "true",
                success: function (result) {
                    if (result.success == true) {
                        var phase = result.entity;
                        var knowledgeNo = phase.knowledgeNo;
                        var studyTimeNo = phase.studyTimeNo;
                        var examNo = phase.examNo;
                        //课程数
                        var courseNo = knowledgeNo*1-examNo*1;
                        $("#courseNo").html(courseNo);
                        $("#knowledgeNo").html(knowledgeNo);
                        $("#studyTimeNo").html(studyTimeNo);
                        $("#examNo").html(examNo);
                    }
                }
            });
        }

        //阶段列表
        function getPhaseListAjax(taskId) {
            $.ajax({
                url: "${ctx}/admin/phase/getPhaseListForInfo/" + taskId,
                data: {},
                dataType: "text",
                type: "post",
                async: "true",
                success: function (result) {
                    $("#phaseAjax").html(result);
                }
            });
        }

        //阶段详情列表
        function getPhaseById(phaseId) {
            $.ajax({
                url: "${ctx}/admin/phase/getPhaseForInfo/" + phaseId,
                data: {},
                dataType: "text",
                type: "post",
                async: "true",
                success: function (result) {
                    $("#phaseDetailInfo").html(result);
                }
            });
        }
    </script>
</head>
<body>
<div class="page_head">
    <h4>
        <em class="icon14 i_01"></em>&nbsp;<span>任务管理</span> &gt; <span>任务详情</span>
    </h4>
</div>
<div class="mt20">
    <div class="commonWrap">
        <input type="hidden" id="taskId" name="task.id" value="${taskOld.id}"/>
        <input type="hidden" id="phaseId" name="phase.id" value="${phaseOld.id}"/>
        <input type="hidden" id="phaseListSize"/>
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
            <thead>
            <tr>
                <th align="left" colspan="2">
                    <span class="">①&nbsp;&nbsp;计划基本信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;任务名称</td>
                <td>
                    <input name="" value="${taskOld.name}" readonly="readonly">
                </td>
            </tr>
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;任务开始时间</td>
                <td>
                    <input name="" readonly="readonly"
                           value="<fmt:formatDate value="${taskOld.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
                </td>
            </tr>
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;任务结束时间</td>
                <td>
                    <input name="task.endTime" id="endTime" readonly="readonly"
                           value="<fmt:formatDate value="${taskOld.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
                    <span style="color: red;" id="emailError"></span>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
            <thead>
            <tr>
                <th align="left" colspan="2">
                    <span class="">②&nbsp;&nbsp;指定学习内容</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div class="" id="phaseAjax"></div>
                </td>
                <td>
                    <div class="" id="phaseDetailInfo">
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
            <thead>
            <tr>
                <th align="left" colspan="2">
                    <span class="">③&nbsp;&nbsp;指定学习者</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td width="20%" align="center">此计划指派给谁</td>
                <td>
                    <table>
                        <tr>
                            <c:forEach var="group" items="${userGroupList}" varStatus="count">
                            <td>
                                <input type="checkbox" name="" value="${group.id}"
                                       <c:if test="${group.check ==1 }">checked</c:if>/>
                                    ${group.name}
                            </td>
                            </c:forEach>
                        <tr/>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
            <thead>
            <tr>
                <th align="left" colspan="2">
                    <span class="">④&nbsp;&nbsp;计划概要及设置</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td width="20%" align="center"><font color="red">*</font>&nbsp;当前计划概要</td>
                <td>
                    <div id="UpdatePanel2">
                        <div class="">
                            <div class="">
                                <p class=""><span class="">总知识数</span></p>
                                <p class=""><span id="knowledgeNo" class="">0</span>个</p>
                            </div>
                            <div class="">
                                <p class=""><span class="">总学时数</span></p>
                                <p class=""><span id="studyTimeNo" class="">0</span>分钟</p>
                            </div>
                            <div class="">
                                <p class=""><span class="">试卷数</span></p>
                                <p class=""><span id="examNo" class="">0</span>个</p>
                            </div>
                            <div class="">
                                <p class=""><span class="">课程数</span></p>
                                <p class=""><span id="courseNo" class="">0</span>个</p>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="20%" align="center"><font color="red">*</font>&nbsp;当前计划概要</td>
                <td>
                    <select name="task.status" id="status" disabled="disabled">
                        <option value="0" selected="selected">未启动</option>
                        <option value="1">启动</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="20%" align="center"><font color="red">*</font>&nbsp;是否重复</td>
                <td>
                    <select name="task.isRepeat" id="isRepeat" disabled="disabled">
                        <option value="0" selected="selected">不可重复</option>
                        <option value="1">可以重复</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <a href="javascript:history.go(-1);" title="返回" class="am-btn am-btn-danger">返回</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>