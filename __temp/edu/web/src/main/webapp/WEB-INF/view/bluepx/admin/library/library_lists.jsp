<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>文库列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
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
});

function cancel(){
	subject_cleantreevalue();
	$("#name").val("");
}
function del(id){
	if(confirm("确认删除吗")){
		$.ajax({
			url:"/admin/library/del/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					alert("删除成功");
					window.location.reload();
				}
			}
		});
	}
}     
</script>
</head>
<body  >
<form action="${ctx}/admin/library/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input type="hidden" name="queryLibrary.subjectId" id="subjectId" value="${queryLibrary.subjectId}"/>
<!-- 内容 开始  -->
<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>文库管理</span> &gt; <span>文库列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>项目专业：</font></span>
									<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
									<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
										<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
									</div>
									<span class="ddTitle"><font>文库名称：</font></span>
									<input type="text" name="queryLibrary.name" id="name" value="${queryLibrary.name}" />
									<input type="button"  class="btn btn-danger ml10" value="查询" name="" onclick="goPage(1)"/>
									<input type="button"  class="btn btn-danger ml10" value="清空" name="" onclick="cancel()"/>
								</li>
							</ul>
							
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span class="ml10"><a href="${cxt}/admin/library/doadd"><em class="icon14 new">&nbsp;</em>新建文库</a></span>
						</p>
						<p class="fr c_666">
							<span>文库列表</span>
							<span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
						</p>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="10%"><span>ID</span></th>
                        <th><span>文库名称</span></th>
                        <th><span>所属项目</span></th>
                        <th><span>浏览量</span></th>
                        <th><span>类型</span></th>
                        <th><span>创建时间</span></th>
                        <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${libarys.size()>0}">
				<c:forEach  items="${libarys}" var="libary" >
					<tr>
						<td>${libary.id }</td>
						<td>${libary.name }</td>
						<td>
							${libary.subjectName}
						</td>
						<td>${libary.browseNum}</td>
						<td>
							<c:if test="${libary.type==1 }">PDF阅读</c:if>
							<c:if test="${libary.type==2 }">PIF阅读</c:if>
						</td>
						<td>
							<fmt:formatDate value="${libary.addTime}" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/library/doupdate?library.id=${libary.id}">修改</a>
							<a class="ml10 btn smallbtn btn-y" href="javascript:del(${libary.id})">删除</a>
						</td>
						
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${libarys.size()==0||libarys==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有文库！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div><!-- /commonWrap -->
	</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
