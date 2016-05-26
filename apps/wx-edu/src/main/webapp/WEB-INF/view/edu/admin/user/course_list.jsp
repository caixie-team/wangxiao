<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">

$(function(){
	var userId = ${userId};
	$("#userId").val(userId);
})

var subject_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'subjectId',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'subjectName',
			title:'subjectName'
		}
	},
	callback: {
		onClick: subject_treeOnclick
	}
};
var subject_treedata=${subjectList};

//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
    $("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

$().ready(function() {
    $('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	//专业名称显示
	if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
		if(treeNode!=null){
			$("#subjectNameBtn").val(treeNode.subjectName);
		}
	}
});

	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#id").val("");
		$("#name").val("");
		$("#subjectNameBtn").val("");
	}
	
	//赠送课程
	function toFreeCourse(courseId){
        dialogFun("赠送课程","确定赠送课程吗?",2,"javascript:_toFreeCourse("+courseId+")");
	}

    function _toFreeCourse(courseId){
        var userId = $("#userId").val();
        $.ajax({
            url:"${ctx}/admin/user/freeCourse",
            data:{"userId":userId, "courseId":courseId},
            dataType:"json",
            success:function (result){
                setTimeout(function(){
                    if(result.message=="赠送成功"){
                        dialogFun("赠送课程","赠送成功！",1);
                    }else if(result.message=="overdue"){
                        dialogFun("赠送课程","该课程已过期！",0);
                        return;
                    }
                },1000)
            },
            error:function (error){
                dialogFun("赠送课程","服务器繁忙，请稍后再试",0);
            }
        })
    }
	

	
</script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程管理</strong> / <small>课程列表</small></div>
</div>
<hr>
<div class="mt20 am-padding admin-content-list">
    <div class="am-tab-panel am-fade am-active am-in">
        <form action="${ctx}/admin/user/courseList" class="am-form" name="searchForm" id="searchForm" method="post">
            <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
            <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId }"/>
            <input type="hidden" id="userId" name="queryCourse.userId" value="${userId}"  />
            <div class="am-g am-margin-top am-u-sm-5 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    课程ID
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')"  name="queryCourse.id" value="${queryCourse.id}" id="id" />
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-5 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    课程名称
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <input type="text" class="am-input-sm" name="queryCourse.name" value="${queryCourse.name}" id="name" />
                </div>
            </div>
            <div class="mt20 clear"></div>

            <div class="am-g am-margin-top am-u-sm-5 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    专业
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <div id="doc-dropdown-justify-js" style="width: 200px">
                        <div class="am-dropdown" id="doc-dropdown-js">
                            <input type="text" class="am-input-sm am-dropdown-toggle" id="subjectNameBtn" value="" readonly="readonly"/>
                            <div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
                                <div id="subject_ztreedemo" class="ztree"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-g am-margin-top am-u-sm-5 am-text-left">
                <div class="am-u-sm-4 am-text-right">
                    &nbsp;
                </div>
                <div class="am-u-sm-8 am-u-end">
                    <button type="button" class="am-btn am-btn-danger" onclick="goPage(1)">查询</button>
                    <button type="button" class="am-btn am-btn-danger" onclick="submitclear()">清空</button>
                </div>
            </div>
            <div class="mt20 clear"></div>
        </form>
    </div>
</div>
<div class="mt20">
    <div class="doc-example">
        <div class="am-scrollable-horizontal am-scrollable-vertical">
            <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
                <thead>
                    <tr>
                        <th width="8%">
                            <span>Id</span>
                        </th>
                        <th width="15%">
                            <span>课程名称</span>
                        </th>
                        <th width="5%">
                            <span>类型</span>
                        </th>
                        <th width="5%">
                            <span>价格</span>
                        </th>
                        <th width="5%">
                            <span>购买数</span>
                        </th>
                        <th width="20%">
                            <span>添加时间</span>
                        </th>
                        <th width="13%">
                            <span>操作</span>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty courseList }">
                        <c:forEach items="${courseList}" var="cou">
                            <tr>
                                <td>${cou.id}</td>
                                <td>${cou.name }</td>
                                <td>
                                    <c:if test="${cou.sellType=='COURSE'}">课程</c:if>
                                    <c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
                                    <c:if test="${cou.sellType=='LIVE'}">直播</c:if>
                                </td>
                                <td>${cou.currentprice}</td>
                                <td>${cou.buycount}</td>
                                <td>
                                    <fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <button type="button" class="am-btn am-btn-primary" onclick="toFreeCourse(${cou.id})">赠送课程</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty courseList }">
                        <tr>
                            <td colspan="9">
                                <div data-am-alert=""
                                     class="am-alert am-alert-secondary mt20 mb50">
                                    <center>
                                        <big> <i class="am-icon-frown-o vam"
                                                 style="font-size: 34px;"></i> <span class="vam ml10">还没有任务信息！</span></big>
                                    </center>
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
