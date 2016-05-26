<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>部门任务</title>
    <link rel="stylesheet"
          href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
    <script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript">
        $(function () {
            $("#beginTime,#endTime,#releaseTime").datetimepicker({
                regional: "zh-CN",
                changeYear: true,
                changeMonth: true,
                dateFormat: "yy-mm-dd ",
                timeFormat: "HH:mm:ss"
            });
        });

        //当前计划概要:
        $(function () {
            summarySchedule();
        });

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

        //选择试卷
        function showNewExam() {
            if ($("#name").val() == "") {
                alert("请填写任务名称");
                return;
            }
            if ($("#beginTime").val() == "") {
                alert("请填写考试开始时间");
                return;
            }
            if ($("#endTime").val() == "") {
                alert("请填写考试结束时间");
                return;
            }
            window.open('${ctx}/admin/task/getTaskExamList', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1250,height=600');
        }

        //添加试卷
        function addnewExamId(newEmIdArr) {
            /*var examIds = $("#emIds").val().split(",");
             examIds = examIds.concat(newEmIdArr);
             examIds = examIds.unique();*/
            $("#emIds").val(newEmIdArr);
            queryExam();
        }

        //调去试卷集合
        var myArrayMoveStock = new Array();
        function addnewExamName(myArrayMoveStock) {
            var emNames = $("#emNames").val().split(",");
            emNames = emNames.concat(myArrayMoveStock);
            emNames = emNames.unique();
            $("#emNames").val(emNames);
        }

        //查找试卷
        function queryExam() {
            var id = $("#emIds").val();
            if (id != null && id != "") {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx}/admin/task/queryByExamIds",
                    data: {
                        "id": id
                    },
                    async: false,
                    success: function (result) {
                        if (result.success == true) {
                            var str = "";
                            var j = 0;
                            var examJson = result.entity.entity;
                            var _len = $("#tabS_02 tr").length;
                            for (var i = 0; i < examJson.length; i++) {
                                var em = examJson[i];
                                if (_len == 0) {
                                    j = i * 1 + 1;
                                } else {
                                    j = _len * 1 + 1;
                                }
                                str += "<tr id='rem" + (i + 1) + "' class=''>" +
                                        "<input type='hidden' id='id" + j + "' name='id" + j + "' value=" + j + ">" +
                                        "<input type='hidden' id='otherId" + j + "' name='otherId" + j + "' value=" + em.id + ">" +
                                        "<input type='hidden' id='type" + j + "' name='type" + j + "' value=" + 1 + ">" +
                                        "<input type='hidden' id='detailName" + j + "' name='detailName" + j + "' value=" + em.name + ">" +
                                        "<input type='hidden' id='hours" + j + "' name='hours" + j + "' value=" + em.replyTime + ">" +
                                        "<input type='hidden' id='detailRank" + j + "' name='detailRank" + j + "' value=" + j + ">" +
                                        "<td>" + j + "</td>" +
                                        "<td>" + em.name + "</td>" +
                                        "<td>" + em.replyTime + "</td>" +
                                        "<td align='center' class='c_666 czBtn'>" +
                                        "<a href='' title='上移' class='ml10 btn smallbtn btn-y'>上移</a>" +
                                        "<a href='' title='下移'' class='ml10 btn smallbtn btn-y'>下移</a>" +
                                        "<a onclick='delCourse(" + em.id + ")' href='javascript:void(0)' title='移除' class='ml10 btn smallbtn btn-y'>移除</a>" +
                                        "</td>" +
                                        "</tr>";
                            }
                            $("#tabS_02").append(str);
                            AddPhaseDetail();
                        }
                    }
                });
            }
        }

        //添加知识
        function AddKnowledge() {
            if ($("#name").val() == "") {
                alert("请填写任务名称");
                return;
            }
            if ($("#beginTime").val() == "") {
                alert("请填写考试开始时间");
                return;
            }
            if ($("#endTime").val() == "") {
                alert("请填写考试结束时间");
                return;
            }
            window.open('${cxt}/admin/cou/couponCourseList?page.currentPage=1', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802');
        }

        function getCourseList(CourseList) {
            //p对象的name获取已存在的课程集合，去除提交过来的重复对象
            $("p[name='courseName']").each(function (i, val) {
                for (var j = 0; j < CourseList.length; j++) {
                    var id = CourseList[j];
                    if (val.id == 'coursespan' + id[0]) {
                        CourseList.splice(j, 1);
                    }
                }
            });
            //插入到表格中
            coursePAdd(CourseList);
        }

        function coursePAdd(myArray) {
            var str = "";
            var j = 0;
            var courseIds = $("#courseIdHidden").val();
            var _len = $("#tabS_02 tr").length;
            for (var i = 0; i < myArray.length; i++) {
                var arr = myArray[i];
                if (_len == 0) {
                    j = i * 1 + 1;
                } else {
                    j = _len * 1 + 1;
                }
                str += "<tr id='rem" + (i + 1) + "' class=''>" +
                        "<input type='hidden' id='id" + j + "' name='id" + j + "' value=" + j + ">" +
                        "<input type='hidden' id='otherId" + j + "' name='otherId" + j + "' value=" + arr[0] + ">" +
                        "<input type='hidden' id='type" + j + "' name='type" + j + "' value=" + 2 + ">" +
                        "<input type='hidden' id='detailName" + j + "' name='detailName" + j + "' value=" + arr[1] + ">" +
                        "<input type='hidden' id='hours" + j + "' name='hours" + j + "' value=" + arr[2] + ">" +
                        "<input type='hidden' id='detailRank" + j + "' name='detailRank" + j + "' value=" + j + ">" +
                        "<td>" + j + "</td>" +
                        "<td>" + arr[1] + "</td>" +
                        "<td>" + arr[2] + "</td>" +
                        "<td align='center' class='c_666 czBtn'>" +
                        "<a href='' title='上移' class='ml10 btn smallbtn btn-y'>上移</a>" +
                        "<a href='' title='下移' class='ml10 btn smallbtn btn-y'>下移</a>" +
                        "<a onclick='delCourse(" + arr[0] + "+,+" + arr[1] + ")' href='javascript:void(0)' title='移除' class='ml10 btn smallbtn btn-y'>移除</a>" +
                        "</td>" +
                        "</tr>";
                courseIds += arr[0] + ",";
                //courseNamestr = arr[1] + ",";
            }
            $("#courseIdHidden").val(courseIds);
            $("#tabS_02").append(str);
            AddPhaseDetail();
        }

        //阶段列表
        function getPhaseListAjax(taskId) {
            $.ajax({
                url: "${ctx}/admin/phase/getPhaseListAjax/" + taskId,
                data: {},
                dataType: "text",
                type: "post",
                async: "true",
                success: function (result) {
                    $("#phaseAjax").html(result);
                }
            });
        }

        //选中某个阶段
        function SelectStudyPhase(phaseId, count) {
            $("#phaseId").val(phaseId);
            $("#phaseRankCount").val(count);
            getPhaseById(phaseId);
        }

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

        //阶段详情列表
        function getPhaseById(phaseId) {
            $.ajax({
                url: "${ctx}/admin/phase/getPhaseById/" + phaseId,
                data: {},
                dataType: "text",
                type: "post",
                async: "true",
                success: function (result) {
                    $("#phaseDetailInfo").html(result);
                }
            });
        }

        //新增学习计划阶段
        function AddStudyPhase() {
            var taskId = $("#taskId").val();
            if (isNotEmpty(taskId)) {
                $.ajax({
                    url: "${ctx}/admin/phase/addPhase/" + taskId,
                    data: {},
                    dataType: "json",
                    type: "post",
                    async: "true",
                    success: function (result) {
                        var phase = result.entity;
                        var phaseId = phase.id;
                        var taskId = phase.taskId;
                        $("#phaseId").val(phaseId);
                        getPhaseListAjax(taskId)
                        getPhaseById(phaseId);
                    }
                });
            }
        }

        //新增学习计划阶段详情
        function AddPhaseDetail() {
            var phaseId = $("#phaseId").val();
            var paymentsData = $("#addTask").serialize();
            paymentsData = decodeURIComponent(paymentsData, true);
            var _len = $("#tabS_02 tr").length;
            if (isNotEmpty(phaseId)) {
                $.ajax({
                    url: "${ctx}/admin/phase/addPhaseDetail/" + phaseId + "?len=" + _len,
                    data: paymentsData,
                    dataType: "json",
                    type: "post",
                    async: "true",
                    success: function (result) {
                        if (result.success == true) {
                            getPhaseById(phaseId);
                        }
                    }
                });
            }
        }

        //学习计划阶段字段发生改变（在编辑状态下）
        function PhaseChangeFiled(obj, phaseId, fieldName) {
            //只有在在编辑状态下
            var taskId = $("#taskId").val();
            var newValue = $(obj).val();
            if (fieldName == "phaseName") {
                if (newValue == "") {
                    alert("请输入阶段名称。");
                    return;
                }
            } else if (fieldName == "phaseDescribe") {
                if (newValue != "" && newValue.length > 2000) {
                    alert("学习阶段的描述不得超过2000个，请核实。");
                    return;
                }
            }
            $.ajax({
                url: "${ctx}/admin/phase/updatePhase/" + phaseId,
                data: {
                    "newValue": newValue,
                    "fieldName": fieldName
                },
                dataType: "json",
                type: "post",
                async: "true",
                success: function (result) {
                    if (result.success == true) {
                        getPhaseById(phaseId);
                        getPhaseListAjax(taskId);
                    }
                }
            });
        }

        //阶段按钮操作
        function studyPhaselRank(type) {
            var taskId = $("#taskId").val();
            var phaseId = $("#phaseId").val();
            var phaseRankCount = $("#phaseRankCount").val();
            var newId = 0;
            var newCount = 0;
            if (type == 'cup') {//上移
                newCount = phaseRankCount * 1 - 1
                if (phaseRankCount == 1) {
                    alert("已是最顶端");
                    return;
                } else {
                    newId = $("#phaseId" + newCount).val();
                }
            } else {//下移
                var _len = $("#phaseListSize").val();
                newCount = phaseRankCount * 1 + 1
                if (phaseRankCount == _len) {
                    alert("已是最底端");
                    return;
                } else {
                    newId = $("#phaseId" + newCount).val();
                }
            }
            $.ajax({
                url: "${ctx}/admin/phase/updatePhaseRank",
                data: {
                    "theId": phaseId,
                    "newId": newId
                },
                dataType: "json",
                type: "post",
                async: "true",
                success: function (result) {
                    if (result.success == true) {
                        getPhaseListAjax(taskId);
                    }
                }
            });
        }

        //阶段按钮操作
        function delStudyPhaseRank() {
            var taskId = $("#taskId").val();
            var phaseId = $("#phaseId").val();
            $.ajax({
                url: "${ctx}/admin/phase/delPhaseRank/" + taskId,
                data: {
                    "theId": phaseId
                },
                dataType: "json",
                type: "post",
                async: "true",
                success: function (result) {
                    if (result.success == true) {
                        var taskId = $("#taskId").val();
                        getPhaseListAjax(taskId);
                        var phaseId = $("#phaseId1").val();
                        getPhaseById(phaseId);
                        summarySchedule();
                    }
                }
            });
        }

        //阶段详情按钮操作
        function phaseDetailRank(type, phaseDetailId, count, phaseId) {
            var newId = 0;
            var newCount = 0;
            if (type == 'cup') {//上移
                newCount = count * 1 - 1
                if (count == 1) {
                    alert("已是最顶端");
                    return;
                } else {
                    newId = $("#id" + newCount).val();
                }
            } else {//下移
                var _len = $("#tabS_02 tr").length;
                newCount = count * 1 + 1
                if (count == _len) {
                    alert("已是最底端");
                    return;
                } else {
                    newId = $("#id" + newCount).val();
                }
            }
            $.ajax({
                url: "${ctx}/admin/phase/updatePhaseDetailRank",
                data: {
                    "theId": phaseDetailId,
                    "newId": newId
                },
                dataType: "json",
                type: "post",
                async: "true",
                success: function (result) {
                    if (result.success == true) {
                        getPhaseById(phaseId);
                    }
                }
            });
        }

        //阶段详情按钮操作
        function delPhaseDetailRank(phaseDetailId, phaseId) {
            var theId = phaseDetailId;
            $.ajax({
                url: "${ctx}/admin/phase/delPhaseDetailRank/" + phaseId,
                data: {
                    "theId": theId
                },
                dataType: "json",
                type: "post",
                async: "true",
                success: function (result) {
                    if (result.success == true) {
                        getPhaseById(phaseId);
                        summarySchedule();
                    }
                }
            });
        }

        //创建任务
        function submitAddTask() {
            var groups = '';
            $("input[name='groupId']:checked").each(function () {
                groups += this.value + ",";
            });
            $("#groupIds").val(groups);
            if ($("#name").val() == "") {
                alert("请填写任务名称");
                return;
            }
            if ($("#beginTime").val() == "") {
                alert("请填写考试开始时间");
                return;
            }
            if ($("#endTime").val() == "") {
                alert("请填写考试结束时间");
                return;
            }
            if ($("#groupIds").val() == "") {
                alert("请添加部门");
                return;
            }
            $("#addTask").submit();
        }

        //选择员工
        function showNewEmployees(){
            window.open('${ctx}/admin/user/groupList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1250,height=600');
        }

        //添加员工
        function addnewUserId(newUcIdArr){
            var ucIds = $("#ucIds").val().split(",");
            ucIds = ucIds.concat(newUcIdArr);
            ucIds = ucIds.unique();
            $("#ucIds").val(ucIds);
            queryUser();
        }

        //查找员工
        function queryUser(){
            var ids = $("#ucIds").val();
            if(ids!=null&&ids!=""){
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    url : "${ctx}/admin/user/querybyIds",
                    data : {
                        "ids" : ids
                    },
                    async : false,
                    success : function(result) {
                        if (result.success == true) {
                            var str = "";
                            var userList = result.entity;
                            for(var i=0; i < userList.length;i++){
                                var uc = userList[i];
                                str+='<p style="width:100%;margin: 0 0 0em">'+uc.nickname+'&nbsp;&nbsp;<a onclick="delUsers('+uc.id+')" class="btn smallbtn btn-y" href="javascript:void(0)"> 删除</a></p>';
                            }
                            $("#uchtml").html(str);
                        }
                    }
                });
            }else{
                $("#uchtml").html("");
            }
        }

        //删除员工
        function delUsers(id){
            var ucIds = $("#ucIds").val();
            var pattern = id+"";
            ucIds = ucIds.replace(new RegExp(pattern), "");
            ucIds = ucIds.split(",").unique();
            $("#ucIds").val(ucIds);
            queryUser();
        }
        //清除全部员工
        function clearEmployees(){
            $("#ucIds").val("");
            $("#uchtml").html("");
        }
    </script>
    <style type="text/css">
        .iconaddcourse {
            display: inline-block;
            width: 34px;
            height: 33px;
            background: url('${ctximg}/static/edu/images/icons/btn-add.png') no-repeat left 0px;
            margin: 0 5px;
        }

        .iconupdisable {
            display: inline-block;
            width: 34px;
            height: 33px;
            background: url('${ctximg}/static/edu/images/icons/btn-up.png') no-repeat left 0px;
            margin: 0 5px;
        }

        .icondowndisable {
            display: inline-block;
            width: 34px;
            height: 33px;
            background: url('${ctximg}/static/edu/images/icons/btn-down.png') no-repeat left 0px;
            margin: 0 5px;
        }

        .icondelete {
            cursor: pointer;
            display: inline-block;
            width: 34px;
            height: 33px;
            background: url('${ctximg}/static/edu/images/icons/btn-delete.png') no-repeat left top;
            margin: 0 5px;
            padding: 0;
        }
    </style>
</head>
<body>
<div class="page_head">
    <h4>
        <em class="icon14 i_01"></em>&nbsp;<span>任务管理</span> &gt; <span>新建任务</span>
    </h4>
</div>
<div class="mt20">
    <div class="commonWrap">
        <form action="/admin/task/updateTask" method="post" id="addTask">
            <input type="hidden" name="task.type" id="taskType" value="0">
            <input id="ucIds" type="hidden" name="task.userIds" value="${taskOld.userIds}" />
            <input type="hidden" name="courseIds" id="courseIdHidden"/>
            <input id="emNames" type="hidden" name="task.examNames" value=""/>

            <input type="hidden" name="task.id" id="taskId" value="${taskOld.id}">
            <input type="hidden" name="task.submit" id="taskSubmit" value="${taskOld.submit}">
            <input type="hidden" name="phase.id" id="phaseId" value="${phaseOld.id}">
            <input type="hidden" id="phaseRankCount">
            <input type="hidden" id="phaseListSize">

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
                        <input name="task.name" id="name" value="${taskOld.name}">
                        <span style="color: red;" id="regMobileError"></span>
                    </td>
                </tr>
                <tr>
                    <td align="center"><font color="red">*</font>&nbsp;考试开始时间</td>
                    <td>
                        <input name="task.beginTime" id="beginTime" readonly="readonly" value="<fmt:formatDate value="${taskOld.beginTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
                        <span style="color: red;" id="nickNameError"></span>
                    </td>
                </tr>
                <tr>
                    <td align="center"><font color="red">*</font>&nbsp;考试结束时间</td>
                    <td>
                        <input name="task.endTime" id="endTime" readonly="readonly" value="<fmt:formatDate value="${taskOld.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
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
                        <div class="">
                            <span class="iconaddcourse" onclick="AddStudyPhase();" title="添加阶段"></span>
                            <span class="iconupdisable" onclick="studyPhaselRank('cup');" title="上移阶段"></span>
                            <span class="icondowndisable" onclick="studyPhaselRank('cdown');" title="下移阶段"></span>
                            <span class="icondelete" onclick="delStudyPhaseRank();" title="移除阶段"></span>
                        </div>
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
                        <div id="uchtml"></div> <a href="javascript:showNewEmployees()" title="添加员工" class="ml10 btn smallbtn btn-y">添加员工</a> <a href="javascript:clearEmployees()" title="清空" class="ml10 btn smallbtn btn-y">清空</a>
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
                        <select name="task.status">
                            <option value="0" selected="selected">未启动</option>
                            <option value="1">启动</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="center"><font color="red">*</font>&nbsp;是否重复</td>
                    <td>
                        <select name="task.isRepeat">
                            <option value="0" selected="selected">不可重复</option>
                            <option value="1">可以重复</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="button" value="提交" class="am-btn am-btn-danger" onclick="submitAddTask()"/>
                        <a href="javascript:history.go(-1);" title="返回" class="am-btn am-btn-danger">返回</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>