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
		onClick: treeOnclick
	}
};
var subject_treedata=${subjectList};

function treeOnclick(e,treeId, treeNode) {
	$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

//取消全部选中
function checkNodeFalse() {
	var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
	zTree.checkAllNodes(false);
}
$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
});

$(function(){
	$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
})



$().ready(function() {
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
	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#id").val("");
		$("#name").val("");
		$("#subjectNameBtn").val("");

		$("#sellType").val("");
		$("#sellType").find('option').eq(0).attr('selected', true);
		$("#isavaliable").val("");
		$("#isavaliable").find('option').eq(0).attr('selected', true);
		$("#membertype").val("");
		$("#membertype").find('option').eq(0).attr('selected', true);
	}
	//添加课程
	function toAddCourseGroup(){
		var reqType ='${reqType}';
		var groupid='${queryCourse.groupId}';
		if(reqType=='sys'){
			window.open('/admin/sys/cou/groupCourseList/?groupId='+groupid,
					+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=100,left=200,width=1300,height=800'
				);
		}else if(reqType=='circle'){
			window.open('/admin/cou/groupCourseList/?groupId='+groupid,
					+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=100,left=200,width=1300,height=800'
				);	
		}
	}
	var ids = "";
	// 删除小组课程
	function toDeleteCourseGroup(){
		var artIds=document.getElementsByName("couIds");
		var checked=true;

		$(artIds).each(function(){
			if($(this).prop("checked")){
				ids+=this.value+",";
				checked=false;
			}
		});

		if(ids.substring(ids.length-1)==','){
			ids = ids.substring(0,ids.length-1);
		}
		if(ids==null){
			if(checked){
				dialogFun("岗位课程管理 ","请至少选择一条信息",0);
				return;
			}
		}
		dialogFun("岗位课程管理 ","确定删除吗",2,"javascript:_deleteCourseGroup()");
	}
	function _deleteCourseGroup(){
		$.ajax({
			url:"${ctx}/admin/cou/deleteCourseGroup",
			type:"post",
			data:{"groupId":$("#groupId").val(),"ids":ids},
			dataType:"json",
			success:function(result){
				if(result.success){
					setTimeout(function(){
						dialogFun("提示","删除成功",5,window.location.href);
					},300);
				}else{
					setTimeout(function(){
						dialogFun("提示","删除失败",0);
					},300);
				}
			}
		});
	}

	function addCourseReload(){
		window.location.reload();
	}
	//上下架
	function avaliable(id,isavaliable){
		var str="";
		if(isavaliable==0){str="是否上架该课程";}
		if(isavaliable==1){str="是否下架该课程";}
		dialogFun("课程列表",str,2,"javascript:_avaliable("+id+","+isavaliable+")");
	}
	function _avaliable(id,isavaliable){
		$.ajax({
			url : baselocation+"/admin/cou/updateIsavaliable",
			data : {"id" : id,"isavaliable" :　isavaliable},
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.success){
					if(isavaliable==0){
						$("#isavaliable"+id).html("上架");
						$(".attr"+id).attr("title","下架");
						$(".attr"+id).attr("onclick","avaliable("+id+",1)");
						$(".attr"+id).html("下架");
					}else{
						$("#isavaliable"+id).html("下架");
						$(".attr"+id).attr("title","上架");
						$(".attr"+id).attr("onclick","avaliable("+id+",0)");
						$(".attr"+id).html("上架");
					}
				}
			}
		})
	}
	function checkAll(box){
		$("input[name=couIds]").prop("checked",box.checked);
	}
	function avaliableBatch(type){
		var check;
		var couIdsEle=$("input[name=couIds]:checked");
		var couIds="";
		for(var i=0;i<couIdsEle.length;i++){
			if(i==couIdsEle.length-1){
				couIds+=couIdsEle[i].value;
			}else{
				couIds+=couIdsEle[i].value+",";
			}
			check=true;
		}
		if(!check){
			dialogFun("课程管理 ","请至少选择一个课程",0);
			return;
		}
		$.ajax({
			url : baselocation+"/admin/cou/updateIsavaliableBatch",
			data : {"couIds" :　couIds,"isavaliable" : type},
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.success){
					if(type==0){
						dialogFun("课程管理 ","课程批量上架成功",5,"javascript:goPage(1)");
					}else if(type=1){
						dialogFun("课程管理 ","课程批量下架成功",5,"javascript:goPage(1)");
					}
				}else{
					dialogFun("课程管理 ","系统出错",0);
				}
			}
		})
		
	}




	//批量删除课程
	function deluserbatch(id){
		/* if(isNotEmpty(id)){
			ids += id;
		} */
		if(id!='' && id!=undefined){
			ids += id;
		}
		var checked=true;
		$("input[name='couIds']:checked").each(function(){
			ids+=this.value+",";
			checked=false;
		});

		if(ids.substring(ids.length-1)==','){
			ids = ids.substring(0,ids.length-1);
		}
		if(ids==null || ids==''){
			if(checked){
				dialogFun("课程管理 ","请至少选择一条信息",0);
				return;
			}
		}
		dialogFun("课程管理 ","确定删除吗",2,"javascript:deluserbatchs()");
	}
	
	function deluserbatchs(){
		$.ajax({
			url:"${ctx}/admin/cou/del",
			data:{"ids":ids},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					$("input[name='couIds']").prop("checked",false);
				 	window.location.reload();
				}else{
					dialogFun("课程管理 ","系统繁忙稍后重试",0);
				}
			}
		});
		ids="";
	}

	$(function(){
		$("input[type='checkbox']").prop("checked",false);
	})
</script>
</head>
<body>
	<div class="am-cf">
		<c:if test="${reqType!='sys' }">
			<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程管理</strong> / <small>课程列表</small></div>
		</c:if>
		<c:if test="${reqType=='sys' }">
			<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">岗位管理</strong> / ${userGroup.name } / <small>岗位课程</small></div>
		</c:if>
	</div>
	<hr>

	<form action="${ctx}/admin/sys/cou/groupCourseList" id="toAddSysCourseGroup" class="am-form" method="post">
		<input type="hidden" id="groupId" name="queryCourse.groupId" value="${queryCourse.groupId }"/>
	</form>
	<form action="${ctx}/admin/cou/list" name="searchForm" id="searchForm" method="post" class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId }"/>
		<input type="hidden" id="groupId" name="queryCourse.groupId" value="${queryCourse.groupId }"/>
		<input type="hidden" name="reqType" value="${reqType}"/>

		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						ID
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="number" class="am-input-sm" name="queryCourse.id" value="${course.id}" id="id" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						专业
					</div>
					<div class="am-u-sm-8 am-u-end am-form-horizontal">
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

				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						名称
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="queryCourse.name" value="${course.name}" id="name" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						销售形式
					</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="queryCourse.sellType" id="sellType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" style="display: none;">
							<option value="">--请选择--</option>
							<option value="COURSE">课程</option>
							<option value="PACKAGE">套餐</option>
							<option value="LIVE">直播</option>
						</select>
					</div>
				</div>
				<div class="mt20 clear"></div>

				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						状态
					</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="queryCourse.isavaliable" id="isavaliable" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" style="display: none;">
							<option value="-1">请选择</option>
							<option value="0">上架</option>
							<option value="1">下架</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-6 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						会员类型
					</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="queryCourse.membertype" id="membertype" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" style="display: none;">
							<option value="0">--请选择--</option>
							<c:forEach items="${memberTypes }" var="memberType">
								<option value="${memberType.id }" <c:if test="${memberType.id==queryCourse.membertype }">selected="selected"</c:if>>${memberType.title }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</div>
		</div>
		<div class="mt20">
			<div class="am-g">
				<div class="am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<c:if test="${reqType!='sys' }">
								<button class="am-btn am-btn-success" type="button" onclick="avaliableBatch(0)"><span class="am-icon-plus"></span> 批量上架</button>
								<button class="am-btn am-btn-success" type="button" onclick="avaliableBatch(1)"><span class="am-icon-trash-o"></span> 批量下架</button>
								<button class="am-btn am-btn-success" type="button" onclick="deluserbatch()"><span class="am-icon-trash-o"></span> 批量删除</button>
							</c:if>

							<c:if test="${queryCourse.showType=='UPLINE'}">
								<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/cou/toAddLineCourse'"><span class="am-icon-plus"></span> 新建线下课程</button>
							</c:if>
							<c:if test="${queryCourse.showType=='ONLINE' and queryCourse.sellType=='COURSE'}">
								<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/cou/toAddCourse'"><span class="am-icon-plus"></span> 新建课程</button>
							</c:if>
							<c:if test="${queryCourse.showType=='ONLINE' and queryCourse.sellType=='LIVE'}">
								<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/cou/toAddCourse?sellType=LIVE'"><span class="am-icon-plus"></span> 新建直播课程</button>
							</c:if>
							<c:if test="${queryCourse.showType=='ONLINE' and queryCourse.sellType=='PACKAGE'}">
								<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/cou/toAddCourse?sellType=PACKAGE'"><span class="am-icon-plus"></span> 新建套餐课程</button>
							</c:if>
							<c:if test="${reqType=='sys' }">
								<button class="am-btn am-btn-success" type="button" onclick="toAddCourseGroup()"><span class="am-icon-plus"></span> 添加小组课程</button>
								<button class="am-btn am-btn-success" type="button" onclick="toDeleteCourseGroup()"><span class="am-icon-trash-o"></span> 删除小组课程</button>
							</c:if>
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
		<div class="mt20">
			<div class="doc-example">
				<div class="am-scrollable-horizontal am-scrollable-vertical">
					<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
						<thead>
						<tr>
							<th>
								<label class="am-checkbox">
									<input type="checkbox" value="" onclick="checkAll(this)" data-am-ucheck>ID
								</label>
							</th>
							<th>名称</th>
							<th>专业</th>
							<th>会员类型</th>
							<th>上下架</th>
							<th>价格</th>
							<th>购买数</th>
							<th>浏览数</th>
							<c:if test="${course.groupId==''||course.groupId==null}">
								<th>操作</th>
							</c:if>
						</tr>
						</thead>
						<tbody>
						<c:if test="${courseList.size()>0}">
							<c:forEach items="${courseList}" var="cou">
								<tr>
									<td>
										<label class="am-checkbox">
											<input type="checkbox" name="couIds" value="${cou.id }" data-am-ucheck>${cou.id}
										</label>
									<td><a target="_blank" href="${ctx}/front/couinfo/${cou.id }">${cou.name }</a></td>
									<td>${cou.subjectName}</td>
									<td>
										<c:if test="${cou.memberTypes.size()>0}">
											<c:forEach items="${cou.memberTypes}" var="couMember">
												${couMember.title }<br/>
											</c:forEach>
										</c:if>
										<c:if test="${cou.memberTypes.size()==0}">--</c:if>
									</td>
									<td id="isavaliable${cou.id }">
										<c:if test="${cou.isavaliable==0}">上架</c:if>
										<c:if test="${cou.isavaliable==1}">下架</c:if>
									</td>
									<td>${cou.currentprice}</td>
									<td>${cou.buycount}</td>
									<td>${cou.viewcount}</td>
									<c:if test="${course.groupId==''||course.groupId==null}">
										<td>
											<c:if test="${cou.sellType=='COURSE'||cou.sellType=='LIVE'}">
												<a class="am-btn am-btn-secondary"  href="${ctx}/admin/kpoint/list/${cou.id}">章节管理</a>
											</c:if>
											<c:if test="${cou.sellType=='PACKAGE'}">
												<a class="am-btn am-btn-secondary"  href="${ctx}/admin/cou/pack/${cou.id}">课程管理</a>
											</c:if>
											<a type="button" class="am-btn am-btn-primary" href="${ctx}/admin/cou/toUpdate/${cou.id}">修改</a>
											<div data-am-dropdown="" class="am-dropdown">
												<button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
												<ul class="am-dropdown-content">
													<li><a href="javascript:void(0)" onclick="deluserbatch(${cou.id})">删除</a></li>
													<c:if test="${cou.isavaliable==0}">
														<li><a href="javascript:void(0)" class="attr${cou.id }" title="下架" onclick="avaliable(${cou.id},1)" >下架</a></li>
													</c:if>
													<c:if test="${cou.isavaliable==1}">
														<li><a href="javascript:void(0)" class="attr${cou.id }" onclick="avaliable(${cou.id},0)" >上架</a></li>
													</c:if>
													<li><a href="${ctx}/admin/cou/getCourseHandoutList?courseId=${cou.id}" >讲义</a></li>
													<li><a href="javascript:void(0)" id="copyCourse" data-id="${cou.id}">复制课程</a></li>
												</ul>
											</div>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${courseList.size()==0||courseList==null}">
							<tr>
								<td colspan="16">
									<div data-am-alert=""
										 class="am-alert am-alert-secondary mt20 mb50">
										<center>
											<big> <i class="am-icon-frown-o vam"
													 style="font-size: 34px;"></i> <span class="vam ml10">还没有相关数据！</span></big>
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
	</form>
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />

	<script type="text/javascript">
		$(function() {
			$('#copyCourse').on('click', function() {
				$('#my-prompt').modal({
					relatedTarget: this,
					onConfirm: function(e) {
						var $link = $(this.relatedTarget);
						var courseName = $(".am-modal-prompt-input").val();
						if(courseName==null||courseName==''){
							dialogFun("课程管理 ","请输入课程名字",0);
							return;
						}
						dialogFun("课程管理 ","确认复制该课程吗？",2,"${ctx}/admin/cou/copyCourse/"+$link.data("id")+"?newCourseName="+$(".am-modal-prompt-input").val());
					},
					onCancel: function(e) {

					}
				});
				$(".am-modal-prompt-input").val("");
			});
		});
	</script>
	<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">课程管理</div>
			<div class="am-modal-bd">
				请输入课程名字
				<input type="text" class="am-modal-prompt-input">
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span>
				<span class="am-modal-btn" data-am-modal-confirm>提交</span>
			</div>
		</div>
	</div>
</body>
</html>
