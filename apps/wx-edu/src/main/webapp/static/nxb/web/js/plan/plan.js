//当前计划概要:
$(function () {

    summarySchedule();
});
//计划概要
function summarySchedule() {
    var planId = $("#planId").val();
    $.ajax({
        url: baselocation+"/admin/phase/summarySchedule/" + planId,
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
        dialogFun("任务","请填写任务名称",0);
        return;
    }
    if ($("#beginTime").val() == "") {
        dialogFun("任务","请填写考试开始时间",0);
        return;
    }
    if ($("#endTime").val() == "") {
        dialogFun("任务","请填写考试结束时间",0);
        return;
    }
    window.open(baselocation+'/admin/plan/getPlanExamList', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1250,height=600');
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
            url: baselocation+"/admin/plan/queryByExamIds",
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
                            "<a href='' title='下移' class='ml10 btn smallbtn btn-y'>下移</a>" +
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
        dialogFun("任务","请填写任务名称",0);
        return;
    }
    if ($("#beginTime").val() == "") {
        dialogFun("任务","请填写考试开始时间",0);
        return;
    }
    if ($("#endTime").val() == "") {
        dialogFun("任务","请填写考试结束时间",0);
        return;
    }
    window.open(baselocation+'/admin/cou/couponCourseList?page.currentPage=1', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802');
}

function getCourseList(CourseList) {
    summarySchedule();
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
    var j = $(".detailCount").length;
    var courseIds = $("#courseIdHidden").val();
    for (var i = 0; i < myArray.length; i++) {
        j++;
        var arr = myArray[i];
        str += "<tr id='rem" + (i + 1) + "' class=''>" +
            "<input type='hidden' class='detailCount'/>" +
            "<input type='hidden' id='otherId" + j + "' name='otherId" + j + "' value=" + arr[0] + ">" +
            "<input type='hidden' id='type" + j + "' name='type" + j + "' value=" + 2 + ">" +
            "<input type='hidden' id='detailName" + j + "' name='detailName" + j + "' value=" + arr[1] + ">" +
            "<input type='hidden' id='hours" + j + "' name='hours" + j + "' value=" + arr[2] + ">" +
            "<input type='hidden' id='detailRank" + j + "' name='detailRank" + j + "' value=" + j + ">" +
            "<td>" + j + "</td>" +
            "<td>" + arr[1] + "</td>" +
            "<td>" + arr[2] + "</td>" +
            "<td>课程</td>" +
            "<td>" +
            "<a href='javascript:void(0)' title='上移' class='am-btn am-btn-link'>上移</a>" +
            "<a href='javascript:void(0)' title='下移' class='am-btn am-btn-link'>下移</a>" +
            "<a onclick='delCourse(" + arr[0] + "+,+" + arr[1] + ")' href='javascript:void(0)' title='移除' class='am-btn am-btn-link'>移除</a>" +
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
function getPhaseListAjax(planId) {
    $.ajax({
        url: baselocation+"/admin/phase/getPhaseListAjax/" + planId,
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
    var planId = $("#planId").val();
    if (isNotEmpty(planId)) {
        getPhaseListAjax(planId);
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
        url: baselocation+"/admin/phase/getPhaseById/" + phaseId,
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
    var planId = $("#planId").val();
    if (isNotEmpty(planId)) {
        $.ajax({
            url: baselocation+"/admin/phase/addPhase/" + planId,
            data: {},
            dataType: "json",
            type: "post",
            async: "true",
            success: function (result) {
                var phase = result.entity;
                var phaseId = phase.id;
                var planId = phase.planId;
                $("#phaseId").val(phaseId);
                getPhaseListAjax(planId)
                getPhaseById(phaseId);
            }
        });
    }
}

//新增学习计划阶段详情
function AddPhaseDetail() {
    var phaseId = $("#phaseId").val();
    var paymentsData = $("#addPlan").serialize();
    paymentsData = decodeURIComponent(paymentsData, true);
    var _len = $(".detailCount").length;
    if (isNotEmpty(phaseId)) {
        $.ajax({
            url: baselocation+"/admin/phase/addPhaseDetail/" + phaseId + "?len=" + _len,
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
    var planId = $("#planId").val();
    var newValue = $(obj).val();
    if (fieldName == "phaseName") {
        if (newValue == "") {
            dialogFun("任务","请输入阶段名称",0);
            return;
        }
    } else if (fieldName == "phaseDescribe") {
        if (newValue != "" && newValue.length > 2000) {
            dialogFun("任务","学习阶段的描述不得超过2000个，请核实。",0);
            return;
        }
    }
    $.ajax({
        url: baselocation+"/admin/phase/updatePhase/" + phaseId,
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
                getPhaseListAjax(planId);
            }
        }
    });
}
//阶段按钮操作
function studyPhaselRank(type) {
    var planId = $("#planId").val();
    var phaseId = $("#phaseId").val();
    var phaseRankCount = $("#phaseRankCount").val();
    var newId = 0;
    var newCount = 0;
    if (type == 'cup') {//上移
        newCount = phaseRankCount * 1 - 1
        if (phaseRankCount == 1) {
            dialogFun("任务","已是最顶端",0);
            return;
        } else {
            newId = $("#phaseId" + newCount).val();
        }
    } else {//下移
        var _len = $("#phaseListSize").val();
        newCount = phaseRankCount * 1 + 1
        if (phaseRankCount == _len) {
            dialogFun("任务","已是最底端",0);
            return;
        } else {
            newId = $("#phaseId" + newCount).val();
        }
    }
    $.ajax({
        url: baselocation+"/admin/phase/updatePhaseRank",
        data: {
            "theId": phaseId,
            "newId": newId
        },
        dataType: "json",
        type: "post",
        async: "true",
        success: function (result) {
            if (result.success == true) {
                var phase = result.entity;
                var phaseId = phase.id;
                var phaseRank = phase.phaseRank;
                $("#phaseId").val(phaseId);
                $("#phaseRankCount").val(phaseRank);
                getPhaseListAjax(planId);
            }
        }
    });
}
//阶段按钮操作
function delStudyPhaseRank() {
    var planId = $("#planId").val();
    var phaseId = $("#phaseId").val();
    $.ajax({
        url: baselocation+"/admin/phase/delPhaseRank/" + planId,
        data: {
            "theId": phaseId
        },
        dataType: "json",
        type: "post",
        async: "true",
        success: function (result) {
            if (result.success == true) {
                var planId = $("#planId").val();
                getPhaseListAjax(planId);
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
            dialogFun("部门任务","已是最顶端",0);
            return;
        } else {
            newId = $("#id" + newCount).val();
        }
    } else {//下移
        var _len = $("#tabS_02 tr").length;
        newCount = count * 1 + 1
        if (count == _len) {
            dialogFun("部门任务","已是最底端",0);
            return;
        } else {
            newId = $("#id" + newCount).val();
        }
    }
    $.ajax({
        url: baselocation+"/admin/phase/updatePhaseDetailRank",
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
        url: baselocation+"/admin/phase/delPhaseDetailRank/" + phaseId,
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
//选择员工
function showNewEmployees(){
    window.open(baselocation+'/admin/user/groupList','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=1250,height=600');
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
            url : baselocation+"/admin/user/querybyIds",
            data : {
                "ids" : ids
            },
            async : false,
            success : function(result) {
                if (result.success == true) {
                    var str = "";
                    str+='<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
                    var userList = result.entity;
                    for(var i=0; i < userList.length;i++){
                        var uc = userList[i];
                        str+='<button class="am-btn am-btn-default ml10 mt10" onclick="delUsers('+uc.id+')" type="button">'+uc.nickname+'<a onclick="delUsers('+uc.id+')" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
                    }
                    str+='</div></div>';
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
function nextStep(num){
    $(".am-tabs-nav").find("a").eq(num).click();
}
//创建任务
function submitAddPlan() {
    var groups = '';
    $("input[name='groupId']:checked").each(function () {
        groups += this.value + ",";
    });
    $("#groupIds").val(groups);
    if ($("#name").val() == "") {
        dialogFun("部门任务","请填写任务名称",0);
        nextStep(0);
        return;
    }
    if ($("#beginTime").val() == "") {
        dialogFun("部门任务","请填写考试开始时间",0);
        nextStep(0);
        return;
    }
    if ($("#endTime").val() == "") {
        dialogFun("部门任务","请填写考试结束时间",0);
        nextStep(0);
        return;
    }
    $("#addPlan").submit();
}
function nextStep(num){
    $(".am-tabs-nav").find("a").eq(num).click();
}