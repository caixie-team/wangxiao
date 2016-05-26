$(function(){
    if(currentCourseId!=''&&currentKpointId!=''){
        getPlayerHtml(currentCourseId,currentKpointId);
    }
    $("[lang="+currentKpointId+"]").addClass("current");
    var parentIndex = $("[lang="+currentKpointId+"]").attr("parentIndex");
    var kpointIndex = $("[lang="+currentKpointId+"]").attr("kpointIndex");
    $("#chapterTip").html(parentIndex);
    $("#kpointTip").html(kpointIndex);
    $(".n-ctTabs a").click(function(){
        var _this = $(this);
        if(!_this.hasClass("current")){
            _this.addClass("current");
            _this.siblings().removeClass("current");
            $("."+_this.attr("lang")).show();
            $("."+_this.attr("lang")).siblings().hide();
        }
        var index = _this.index();
        if(index==1){//笔记
            queryNote(currentCourseId,currentKpointId);
        }else if(index==2){//答疑
            sugListPage(currentKpointId);
        }else if(index==3){//评论
            assessPage(currentKpointId);
        }
    });
    $(".chap-seclist ul>li").mousemove(function(){
        var _this = $(this);
        if(_this.attr("lang")!=currentKpointId){
            _this.addClass("current");
        }

    });
    $(".chap-seclist ul>li").mouseout(function(){
        var _this = $(this);
        if(_this.attr("lang")!=currentKpointId){
            _this.removeClass("current");
        }
    });
    // 上一节
    $(".nlt-up").click(function(){
        var index = $(".chap-seclist ul>li").index($("[lang="+currentKpointId+"]"));
        if(index>0){
            $(".chap-seclist ul>li").eq(index-1).click();
        }
    });
    // 下一节
    $(".nlt-dw").click(function(){
        var total = $(".chap-seclist ul>li").length;
        var index = $(".chap-seclist ul>li").index($("[lang="+currentKpointId+"]"));
        if(index<total-1){
            $(".chap-seclist ul>li").eq(index+1).click();
        }
    });

});

// 获得播放器的html
function getPlayerHtml(courseId,kpointId) {
    $.ajax({
        url : baselocation + "/front/ajax/getKopintHtml",
        data : {
            "kpointId" : kpointId,
            "courseId" : courseId
        },
        type : "post",
        dataType : "text",
        async:false,
        success : function(result) {
            $("#vedioBox").html(result);
        }
    });
}
// 切换课程
function changeKpoint(id){
    $("#kpointId").val(id);
    $("#changeForm").submit();
}



//笔记
$(function() {
    initKindEditornote();
});
//添加笔记
function addNote() {
    var notesContext = $("#notesContextId").val();
    if (notesContext==null||notesContext=='') {
        dialogFun('提示', '请输入内容', 0);
        return false;
    } else {
        if (notesContext.length > 50000) {
            dialog('提示', '内容过长', 4);
            return false;
        }
        $.ajax({
            url : baselocation + "/front/addnote",
            type : 'post',
            dataType : 'json',
            data : {
                'kpointId' :currentKpointId,
                'courseId':currentCourseId,
                'content' : notesContext
            },
            async:false,
            success : function(result) {
                if (result.message == "success") {
                    dialogFun('成功', '保存成功', 1);
                    $("#notesContextId").val("");
                }
                if (result.message == "false") {
                    dialogFun('失败', '保存失败，请刷新重试', 0);
                }
            }
        });
    }
}
//查询笔记
function queryNote(courseId,kpointId){
    $.ajax({
        url : baselocation+"/front/querynote",
        type : "post",
        dataType : "json",
        data:{'kpointId':kpointId,
            "courseId":courseId},
        success : function(result){
            if(result.courseNote!=undefined){
                KindEditor.html("#notesContextId", result.courseNote.content);
            }else{
                KindEditor.html("#notesContextId",'');
            }
        }
    });
}
/**
 * 笔记编辑器初始化
 */
function initKindEditornote(){//编辑器初始化
    KindEditor.create('textarea[id="notesContextId"]', {
        filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
        pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
        allowPreviewEmoticons : false,
        syncType : 'auto',
        width : '99%',
        minWidth : '10px',
        minHeight : '10px',
        height : '300px',
        urlType : 'domain',// absolute
        newlineTag : 'br',// 回车换行br|p
        uploadJson : keImageUploadUrl+'&param=note',//图片上传路径
        allowFileManager : false,
        afterBlur : function() {
            this.sync();
        },
        items : [  'forecolor', 'emoticons'],
        afterChange : function() {
            $('#numweiBoContent').html(140 - this.count('text'));
            var num = 255 - this.count('text');

            if (num >= 0) {
                $(".error").html("您还可以输入"+num+"字");
            } else {
                $(".error").html("您输入的文字超过140字！");
                $('#wenzistrweiBoContent').html("你已经超过");
                num = -num;
            }
            $('#numweiBoContent').html(num);
        }
    });
};
// 答疑
function checkWordroblem(){
    var len = 255 -$("#problemContextTextareaId").val().length;
    $("#problemError").html("还可以输入"+len+"个字");
}
// 添加问答
function addProblemFun(){
    if(!isLogin){
        dialog('登录','',3,'','1');
        return;
    }
    var problemcontext = $("#problemContextTextareaId").val();
    if(problemcontext==null || problemcontext.trim()==''){
        $("#problemContextTextareaId").val('');
        return;
    }else{
        if(problemcontext.length>255){
            dialogFun('失败', '输入的内容不能超过255个字', 0);
            return;
        }
        $.ajax({
            url:baselocation+'/front/addAnswerQuestion',
            type : "post",
            dataType : "json",
            async:false,
            data : {
                "answerQuestion.type":"course",
                "answerQuestion.content":problemcontext,
                "answerQuestion.parentId":currentCourseId,
                "answerQuestion.sonId":currentKpointId
            },
            success:function (result){
                if(result.success){
                    sugListPage(currentKpointId);
                    $("#problemContextTextareaId").val("");
                }else{
                    dialogFun('失败', '系统异常，请刷新重试', 0);
                }
            }
        });
    }
}
//答疑分页
function sugListPage(id) {
    ajaxPage("/front/ajax/answerList", "&kpointId=" + id, 1, callBackSug);
}
// ajax回调方法
function callBackSug(result) {
    $(".n-ct-cont-answer").html(result);
    //答疑回复
    replyFun();
}
//答疑回复
function replyFun() {
    $(".question-list>ul>li").each(function(){
        var _this = $(this);
        queryReply(_this.attr("lang"));
    });
}
function queryReply(pblId){
    var wordCount = 255;

    var html ='<section class="n-reply-wrap">'+
        '<fieldset>'+
         '<textarea class="" maxlength="'+wordCount+'" name="" id="reProblemInfo'+pblId+'"></textarea>'+
         '</fieldset>'+
         '<p class="of mt5 tar pl10 pr10">'+
             '<span class="c-999 fsize14">还可以输入<tt class="c-red" id="word'+pblId+'">'+wordCount+'</tt>字</span>'+
             '<a class="lh-reply-btn ml15" title="回复" href="javascript:void(0)" id="addReProblemFun'+pblId+'">回复</a>'+
        '</p>'+
     '</section>'
    $.ajax({
        url:baselocation+'/front/ajax/answerReplyList',
        type:'post',
        dataType:'json',
        async:false,
        data:{'answerId':pblId},
        success:function (result){
            if(result.success){
                var obj = result.entity;
                html+='<div class="mt10 reply-list"><section class="review-box mt15"><ul>';
                for(var i=0;i<obj.length;i++){
                    if(obj[i].userId!=0){
                        var userImg = obj[i].userExpandDto.avatar;
                    }else{
                        var userImg ="";
                    }
                    if(userImg!=null&&userImg.trim()!=''){
                        if(userImg.indexOf('http')>=0){
                            uesrImg = userImg;
                        }else{
                            uesrImg=staticImageServer+userImg;
                        }
                    }else{
                        uesrImg=baselocation+'/static/common/images/user_default.png';
                    }
                    var time = new Date(obj[i].addTime).Format("yyyy-MM-dd hh:mm:ss");
                    html+='<li class="pr"><aside class="noter-pic"><img width="40" height="40" src="'+uesrImg+'" class="picImg"></aside>';
                    html+='<div class="of">' +
                            '<span class="fl"><font class="fsize16 c-blue1">'+obj[i].showName+'</font></span>'+
                            '<span class="fr c-666 fsize12"><font>'+time+'</font></span>'+
                            '</div>'+
                            '<div class="noter-txt mt5"><p>'+obj[i].content+'</p></div>'+
                            '</li>';
                }
                html+='</ul></section>';
                $("#reply"+pblId).html(html);

                $("#addReProblemFun"+pblId).click(function(){
                    pblId = $(this).attr('id').replace('addReProblemFun','');
                    var reInfo = $("#reProblemInfo"+pblId).val();
                    if(reInfo==null || reInfo.trim()==''){
                        dialogFun("答疑回复","回复不能为空",0);
                        return;
                    }
                    $.ajax({
                        url:baselocation+'/front/ajax/addAnswerReply',
                        type:'post',
                        dataType:'json',
                        data:{"answerReply.content":reInfo,"answerReply.answerId":pblId},
                        success:function (result){
                            if(result.success){
                                queryReply(pblId);
                            }
                        }
                    });
                });

                $("#reProblemInfo"+pblId).keyup(function(){
                    var len = wordCount - $(this).val().length;
                    $("#word"+pblId).html(len);
                });
            }
        }
    });
}

function checkWordAssess(){
    var len = 255-$("#assessInofContextId").val().length;
    $("#assessError").html("还可以输入"+len+"个字");
}
// 添加评论
function addAssess() {
    var assessInofContext = $("#assessInofContextId").val();
    if (assessInofContext == null || assessInofContext.trim() == '') {
    } else {
        var pointId =currentKpointId;// 视频节点ID
        $.ajax({
            url : baselocation + "/front/addAssess",
            type : "post",
            dataType : "json",
            data : {
                "courseId" : currentCourseId,
                "kpointId" : pointId,
                "content" : assessInofContext
            },
            success : function(result) {
                if (result.success) {
                    assessPage(pointId);
                    $("#assessInofContextId").val('');
                } else {
                    dialogFun('失败', '保存失败，请刷新重试', 0);
                }
            }
        });
    }
}
// 课程评价区分页
function assessPage(id) {
    ajaxPage("/front/ajax/assesskpoint", "&kpointId=" + id, 1, callBack);
}
// ajax回调方法
function callBack(result) {
    $(".n-ct-cont-comment").html(result);
}