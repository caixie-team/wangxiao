<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<div class="">
    <div class="">
        <div class="">
            <span class=""><font color="red">*</font></span>
            阶段名称：
        </div>
        <div class="">
            <input name="" type="text" value="${phase.phaseName}" maxlength="18"
                   id="txtPhaseName" class="textbox" style="width:350px;" readonly="readonly">
        </div>
    </div>
    <div class="">
        <div class="">
            描述：
        </div>
        <div class="">
        <textarea name="" rows="3" cols="20" id="txtSummry" class="input" style="width:540px;" readonly="readonly">${phase.phaseDescribe}</textarea>
        </div>
    </div>
    <br>
    <div class="">
        <table class="">
            <thead>
            <tr>
                <th><span>序号</span></th>
                <th><span>名称</span></th>
                <th><span>学时(分钟)</span></th>
                <th><span>类型</span></th>
            </tr>
            </thead>
            <tbody align="center" id="tabS_02">
            <c:if test="${phaseDetailList.size() > 0}">
                <c:forEach var="phaseDetail" items="${phaseDetailList}" varStatus="status">
                    <tr id="phaseDetail${status.count}" class="">
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
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    summarySchedule();
</script>