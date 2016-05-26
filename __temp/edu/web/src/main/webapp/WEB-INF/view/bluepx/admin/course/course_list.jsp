<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
//subject ztree start
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
	hideSubjectMenu();
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	hideSubjectMenu();
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function showSubjectMenu() {
	var cityObj = $("#subjectNameBtn");
	var cityOffset = $("#subjectNameBtn").offset();
	$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideSubjectMenu() {
	$("#subjectmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
		hideSubjectMenu();
	}
}

$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	//专业名称显示
	if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
		if(treeNode!=null){
			$("#subjectNameBtn").val(treeNode.subjectName);
		}
	}
	var isavild='${queryCourse.isavaliable}'==''?-1:'${queryCourse.isavaliable}';
	$("#isavaliable").val(isavild);
    $("#sellType").val('${queryCourse.sellType}');
});


function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	//删除课程
	function delCourse(id){
		if(confirm("是否删除?")){ 
			window.location.href="${ctx}/admin/cou/del/"+id;
		}
	}
	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#membertype").val(0);
		$("#id").val("");
		$("#name").val("");
		$("#subjectNameBtn").val("");
		$("#isavaliable").val(-1);
	}
</script>
</head>
<body >
			<!-- 内容 开始  -->
            <div class="page_head">
                <h4><em class="icon14 i_01"></em>&nbsp;<span>课程管理</span> &gt; <span>课程列表</span> </h4>
            </div>
            <!-- /tab1 begin-->
            <div class="mt20">
                <div class="commonWrap">
                		<form action="${ctx}/admin/cou/list" name="searchForm" id="searchForm" method="post">
                        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
                        <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId }"/>
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                 <div class="capHead">

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>课程id：</font></span><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" /></li>
                                            <li><span class="ddTitle"><font>课程名称：</font></span><input type="text" name="queryCourse.name" value="${course.name}" id="name" /></li>
											<li>
                                                <span class="ddTitle"><font>销售形式：</font></span>
                                                <select name="queryCourse.sellType" id="sellType">
													<option value="">--请选择--</option>
                                                    <option value="COURSE">课程</option>
                                                    <option value="PACKAGE">套餐</option>
                                                    <option value="LIVE">直播</option>
												</select>
                                            </li>
										</ul>
                                    </div>

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>专业：</font></span>
                                                <input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
                                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
                                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
                                                </div>
                                            </li>

                                            <li>
                                                <span class="ddTitle"><font>状态：</font></span>
                                                <select name="queryCourse.isavaliable" id="isavaliable" >
                                                    <option value="-1">请选择</option>
                                                    <option value="0">上架</option>
                                                    <option value="1">下架</option>
                                                </select>
                                            </li>
                                            <li>
                                                <span class="ddTitle"><font>会员类型：</font></span>
                                                <select name="queryCourse.membertype" id="membertype">
                                                    <option value="0">--请选择--</option>
                                                    <c:forEach items="${memberTypes }" var="memberType">
                                                        <option value="${memberType.id }" <c:if test="${memberType.id==queryCourse.membertype }">selected="selected"</c:if>>${memberType.title }</option>
                                                    </c:forEach>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>
                                     <div   align="center">
                                        <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()">
                                        <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                                <div class="mt10 clearfix">
                                    <p class="fl czBtn">
                                        <span>
                                            <a href="${ctx}/admin/cou/toAddCourse" title="新建">
                                                <em class="icon14 new">&nbsp;</em>
                                                新建课程
                                            </a>
                                        </span>
                                    </p>
                                </div>
                                </form>
                            </caption>

                            <thead>
                                <tr>
                                    <th width="8%">
                                        <span>ID</span>
                                    </th>
                                    <th>
                                        <span>课程名称</span>
                                    </th>
                                    <th>
                                        <span>售卖形式</span>
                                    </th>
                                    <th>
                                        <span>会员类型</span>
                                    </th>
                                    <th>
                                        <span>上下架</span>
                                    </th>
                                    <th>
                                        <span>价格</span>
                                    </th>
                                    <th>
                                        <span>购买数</span>
                                    </th>
                                    <th>
                                        <span>浏览数</span>
                                    </th>
                                    <th>
                                        <span>添加时间</span>
                                    </th>
                                    <th>
                                        <span>操作</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="tabS_02" align="center">
                                <c:if test="${courseList.size()>0}">
                                    <c:forEach items="${courseList}" var="cou">
                                        <tr id="rem${cou.id }">
                                            <td>${cou.id}</td>
                                            <td>${cou.name }</td>
                                            <td>
                                            <c:if test="${cou.sellType=='COURSE'}">课程</c:if>
                                            <c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
                                                <c:if test="${cou.sellType=='LIVE'}">直播</c:if>
                                            </td>
                                            <td>
                                            	<c:if test="${cou.memberTypes.size()>0}">
                                            		<c:forEach items="${cou.memberTypes}" var="couMember">
                                            			${couMember.title }<br/>
                                            		</c:forEach>
                                            	</c:if>
                                            	<c:if test="${cou.memberTypes.size()==0}">--</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${cou.isavaliable==0}">上架</c:if>
                                                <c:if test="${cou.isavaliable==1}">下架</c:if>
                                            </td>
                                            <td>${cou.currentprice}</td>
                                            <td>${cou.buycount}</td>
                                            <td>${cou.viewcount}</td>
                                            <td>
                                                <fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </td>
                                            <td class="c_666 czBtn" align="center">
                                                <a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/cou/toUpdate/${cou.id}">修改</a>
                                                <a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:void(0)" onclick="delCourse(${cou.id})">删除</a>
                                                <c:if test="${cou.sellType=='COURSE'||cou.sellType=='LIVE'}">
                                                    <a class="ml10 btn smallbtn btn-y" title="视频管理" href="${ctx}/admin/kpoint/list/${cou.id}">章节管理</a>
                                                </c:if>
                                                <c:if test="${cou.sellType=='PACKAGE'}">
                                                    <a class="ml10 btn smallbtn btn-y" title="课程管理" href="${ctx}/admin/cou/pack/${cou.id}">课程管理</a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${courseList.size()==0||courseList==null}">
                                    <tr>
                                        <td align="center" colspan="16">
                                            <div class="tips">
                                                <span>还没有相关数据！</span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                        <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
                </div>
            </div>

</body>
</html>
