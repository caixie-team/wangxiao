<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程列表</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
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


function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#id").val("");
		$("#name").val("");
		$("#subjectNameBtn").val("");
	}
	
	function submitCou(){
		var courseId=$("#courseId").val();
		if(courseId==null||courseId==""){
			dialogFun("更换课程","请选择课程",0);
			return false;
		}
		dialogFun("更换课程","确定换课吗?",2,"javascript:_changeCourse()");
	}

	function _changeCourse(){
		var courseId=$("#courseId").val();
		var courseName=$("#courseName").val();
		var orderId='${param.orderDetailId}';
		$.ajax({
			url:"${ctx}/admin/order/changgecourse",
			data:{"trxorderDetail.courseId":courseId,"trxorderDetail.id":orderId,"trxorderDetail.courseName":courseName},
			dataType:"json",
			type:"post",
			async:false,
			success:function(result){
				setTimeout(function(){
					if(result.success){
						dialogFun("更换课程",result.message,5,"javascript:reload()");
					}else{
						dialogFun("更换课程",result.message,0);
					}
				},500);
			}
		});
	}

	function reload(){
		window.opener.location.reload();
		closewin();
	}

	function checkCourse(id,name){
		if(id!=null){
			$("#courseId").val(id);
		}
		if(name!=null&&name!=""){
			$("#courseName").val(name);
		}
	}
	function closewin(){
		window.close();
	}
</script>
</head>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程管理</strong> / <small>课程列表</small></div>
</div>
<hr>
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<form action="${ctx}/admin/order/courselist?orderDetailId=${param.orderDetailId}" name="searchForm" id="searchForm" method="post" class="am-form">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId }"/>
			<input type="hidden" id="courseId" name="courseId"/>
			<input type="hidden" id="courseName" name="courseName"/>
			<div class="am-g am-margin-top am-u-sm-6 am-text-left optionList">
				<div class="am-u-sm-4 am-text-right">
					课程ID
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-6 am-text-left optionList">
				<div class="am-u-sm-4 am-text-right">
					课程名称
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryCourse.name" value="${course.name}" id="name" />
				</div>
			</div>
			<div class="mt20 clear"></div>

			<div class="am-g am-margin-top am-u-sm-6 am-text-left optionList">
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
			<div class="mt20 clear"></div>
		</form>
	</div>

	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6">
				<div class="am-btn-toolbar">
					<div class="am-btn-group am-btn-group-xs">
						<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/cou/toAddCourse'"><span class="am-icon-plus"></span> 新建课程</button>
					</div>
				</div>
			</div>
			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
					<span class="am-input-group-btn">
						<button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">查询</button>
						<button type="button" class="am-btn am-btn-primary" onclick="submitclear()">清空</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="mt20">
	<div class="doc-example">
		<div class="am-scrollable-horizontal am-scrollable-vertical">
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
				<thead>
					<tr>
						<th width="10%">
							<span>Id</span>
						</th>
						<th width="30%">
							<span>课程名称</span>
						</th>
						<th width="5%">
							<span>类型</span>
						</th>
						<th width="5%">
							<span>价格</span>
						</th>
						<th width="6%">
							<span>购买数</span>
						</th>
						<th width="6%">
							<span>浏览数</span>
						</th>
						<th width="10%">
							<span>添加时间</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty courseList }">
						<c:forEach items="${courseList}" var="cou">
							<tr id="rem${cou.id }">
								<td>
									<label class="am-radio">
										<input class="checkcou"  type="radio" name="cou.id" value="${cou.id}" onclick="checkCourse('${cou.id}','${cou.name }')" data-am-ucheck/>
										${cou.id}
									</label>
										</td>
								<td>${cou.name }</td>
								<td>
									<c:if test="${cou.sellType=='COURSE'}">课程</c:if>
									<c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
								</td>
								<td>${cou.currentprice}</td>
								<td>${cou.buycount}</td>
								<td>${cou.viewcount}</td>
								<td>
									<fmt:formatDate type="both" value="${cou.addtime }" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty courseList }">
						<tr>
							<td colspan="16">
								<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big>
											<i class="am-icon-frown-o vam"  style="font-size: 34px;"></i>
											<span class="vam ml10">还没有相关数据！</span>
										</big>
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
<div class="mt20 am-padding admin-content-list">
	<div class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-text-right">
				&nbsp;
			</div>
			<div class="am-u-sm-8 am-u-end">
				<button type="button" class="am-btn am-btn-danger" onclick="submitCou()">确认</button>
				<button type="button" class="am-btn am-btn-danger" onclick="closewin()">关闭</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>
