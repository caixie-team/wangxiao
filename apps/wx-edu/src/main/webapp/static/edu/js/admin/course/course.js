/**
 * Description:添加或修改课程、套餐、直播
 * Author:
 * Update: (2016/4/9)
 */
$(function(){
    $('#courseForm').validator({
        submit: function() {
            var formValidity = this.isFormValid();
            if(formValidity){
                courseFormSubmit();
            }else{

            }
            return false;
        }
    });
});

// 课程详情
function checkCourseInfo(){
    var check = true;
    if($("#name").val()==null || $("#name").val()==''){
    	dialogFun("添加课程","请添课程名称",0);
        check = false;
    }else if($("#subjectId").val()==0){
    	dialogFun("添加课程","请添专业分类",0);
        check = false;
    }else if($("#tcIds").val()==""){
        dialogFun("添加课程","请添加教师",0);
        check = false;
    }else if($("#title").val()=="" || $("#title").val()==null){
        dialogFun("添加课程","请添课程简介",0);
        check = false;
    }else if(context.getContent()==""){
        dialogFun("添加课程","请填写课程详情",0);
        check = false;
    }else if($("#courseLogo").val()==""){
        dialogFun("添加课程","请上传课程图片",0);
        check = false;
    }else if($("#totalLessionnum").val()==""){
        dialogFun("添加课程","请填写总课时",0);
        check = false;
    }
    $("#hidContext").val(context.getContent());

    return check;
}

// 直播信息
function checkCourseLive(){
    var check = true;
    var isRestrict = $("input[name='course.isRestrict']:checked").val();
    if(isRestrict==1){
        if($("#restrictNum").val()>0){

        }else{
            dialogFun("添加课程","直播限定人数必须大于0",0);
            check = false;
        }
    }else if($("#liveBeginTime").val()==''){
        dialogFun("添加课程","直播开始时间不能为空",0);
        check = false;
    }else if(new Date($("#liveBeginTime").val())<=new Date()){
        dialogFun("添加课程","直播开始时间不能小于等于当前时间",0);
        check = false;
    }else if($("#liveEndTime").val()==''){
        dialogFun("添加课程","直播结束时间不能为空",0);
        check = false;
    }else if(new Date($("#liveEndTime").val())<=new Date($("#liveBeginTime").val())){
        dialogFun("添加课程","直播开始时间不能小于等于开始时间",0);
        check = false;
//		}else if($("#freeurl").val()==''){
//			dialogFun("添加课程","直播编码不能为空",0);
//			check = false;
//		}else if($("#teacherVideoUrl").val()==''){
//			dialogFun("添加课程","教师直播编码不能为空",0);
//			check = false;
//		}else if($("#videoPassword").val()==''){
//			dialogFun("添加课程","教师直播密码不能为空",0);
//			check = false;
    }
    return check;
}

// 售卖信息
function checkCoursePay(){
    var check = true;
    var isPay = $("input[name='course.isPay']:checked").val();
    if(isPay==1){
        var sourceprice = $("#sourceprice").val();
        var currentprice = $("#currentprice").val();
        if(!isNum(sourceprice)||sourceprice<0.01){
            dialogFun("添加课程","课程原价格最小值为0.01",0);
            check = false;
        }else if(!isNum(currentprice)||currentprice<0.01){
            dialogFun("添加课程","课程销售价格最小值为0.01",0);
            check = false;
        }
    }else{
        $("#sourceprice").val(0.00);
        $("#currentprice").val(0.00);
    }
    if($("#losetype").val()==0){
    	if($("#loseAbsTime").val()==''||$("#loseAbsTime").val()==null){
    		dialogFun("添加课程","请添加课程到期时间",0);
    		check = false;
    	}
        if(new Date($("#loseAbsTime").val().replace(/-/g,"/")).getTime()<=new Date().getTime()){
            dialogFun("添加课程","过期时间不能小于等于当前时间",0);
            check = false;
        }
    }else if($("#losetype").val()==1){
        if($("#loseTime")<=0||$("#loseTime").val()==''){
            dialogFun("添加课程","请输入大于0的天数",0);
            check = false;
        }
    }
    return check;
}


// 提交数据
function courseFormSubmit() {
    // 验证课程信息
    if(!checkCourseInfo()){
        $('.am-tabs-nav li a[href="#tab1"]').click();
        return;
    }
    // 验证直播信息
    if($("#sellType").val().toUpperCase()=='LIVE'){
        // 选项卡切换
        if(!checkCourseLive()){
            $('.am-tabs-nav li a[href="#tab2"]').click();
            return;
        }
    }
    // 验证售卖信息
    if(!checkCoursePay()){
        $('.am-tabs-nav li a[href="#tab3"]').click();
        return;
    }


    // 添加岗位id
    var groups = '';
    $("input[name='groupId']:checked").each(function() {
        groups += this.value + ",";
    });
    // 选中岗位
    if(isNotEmpty(groups)){
        $("#groupIds").val(groups);
    }
    $("#courseForm").submit();
}

// 下一步
function nextStep(step){
    var check = true;
    if(step==1){
        check=checkCourseInfo();
    }else if(step==2){
        check=checkCourseLive();
    }else if(step==3){
        check=checkCoursePay();
    }
    if(!check){
        return;
    }
    step = step+1;
    var isHidden = $('.am-tabs-nav li a[href="#tab'+step+'"]').parent().is(":hidden");
    if(isHidden){
        step+=1;
    }
    $('.am-tabs-nav li a[href="#tab'+step+'"]').click();
}

// 限定人数
function restrictChange(id){
    if(id==1){
        $("#restrictNumDiv").show();
    }else{
        $("#restrictNumDiv").hide();
    }
}

// 课程类型切换
function sellTypeChange(){
    var sellType=$("#sellType").val();
    if(sellType=="LIVE"){
        $(".showLive").show();
    }else{
        $(".showLive").hide();
    }
    $(".am-tabs-nav li").eq(0).children().click();
}

// 收费类型切换
function payChange(isPay){
    var sellType=$("#sellType").val();
    if(sellType=="LIVE"&&isPay==0){
        $("#reserve").show();
    }else{
        $("#reserve").hide();
    }
    if(isPay==0){
        $(".price").hide();
        $("input[name='course.sourceprice']").val(0.00);
        $("input[name='course.currentprice']").val(0.00);
    }else{
        $(".price").show();
        $("input[name='course.sourceprice']").val("");
        $("input[name='course.currentprice']").val("");
    }
}

// 教师开始--------------------------
// 打开新窗口查询教师
function showNewwin(){
    window.open(baselocation+'/admin/teacher/selectlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
}
// 选择教师
function addnewTeacherId(newTcIdArr){
    var tcIds = $("#tcIds").val().split(",");
    tcIds = tcIds.concat(newTcIdArr);
    tcIds = tcIds.unique();
    $("#tcIds").val(tcIds);
    querytecher();
}
// 查询教师
function querytecher(){
    var ids = $("#tcIds").val();
    if(isNotEmpty(ids)){
        $.ajax({
            type : "POST",
            dataType : "json",
            url : baselocation+"/admin/teacher/queryByIds",
            data : {
                "ids" : ids
            },
            async : false,
            success : function(result) {
                if (result.success == true) {
                    var str = '<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
                    var teacherList = result.entity;
                    for(var i=0;i<teacherList.length;i++){
                        var tc = teacherList[i];
                        str+='<button class="am-btn am-btn-default ml10 mt10" onclick="delTeacher('+tc.id+')" type="button">'+tc.name+'<a onclick="delTeacher('+tc.id+')" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
                    }
                    $("#tchtml").html(str);
                }
            }
        });
    }else{
        $("#tchtml").html("");
    }
}
// 删除老师
function delTeacher(id){
    var tcIds = $("#tcIds").val();
    var pattern = id+"";
    tcIds = tcIds.replace(new RegExp(pattern), "");
    tcIds = tcIds.split(",").unique();
    $("#tcIds").val(tcIds);
    querytecher();
}
// 清空教师
function clearTeacher(){
    $("#tcIds").val("");
    $("#tchtml").html("");
}
// 教师结束--------------------------


