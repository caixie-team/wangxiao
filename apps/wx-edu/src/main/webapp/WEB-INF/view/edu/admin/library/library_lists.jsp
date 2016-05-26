<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>文库列表</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
	<script type="text/javascript">
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
			$("#subjectId").val(treeNode.subjectId);
			$("#subjectNameBtn").val(treeNode.subjectName);
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
		}

		//清空专业的查询条件时同时清除考点
		function subject_cleantreevalue(){
			$("#subjectId").val(0);
			$("#subjectNameBtn").val("");
		}
		$().ready(function() {
			$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, ${subjectList});
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
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

		function dledialog(id){
			dialogFun("文库列表","确认删除吗",2,"javascript:del("+id+")");
		}

		function del(id){
			$.ajax({
				url:"/admin/library/del/"+id,
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.message=="true"){
						dialogFun("文库列表","删除成功",5,"");
						window.location.reload();
						closeFun();
					}
				}
			});
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">文库管理</strong> / <small>文库列表</small></div>
	</div>
	<hr/>

	<div class="mt20 am-padding admin-content-list am-form-horizontal">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/library/list" name="searchForm" id="searchForm" method="post" class="am-form">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<input type="hidden" name="queryLibrary.subjectId" id="subjectId" value="${queryLibrary.subjectId}"/>

				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">项目专业</div>
					<div class="am-u-sm-8  am-form-horizontal">
						<div id="doc-dropdown-justify-js" >
							<div class="am-dropdown" id="doc-dropdown-js">
								<input type="text" class="am-input-sm am-dropdown-toggle" id="subjectNameBtn" readonly="readonly"/>
								<div class="am-dropdown-content">
									<div id="subject_ztreedemo" class="ztree" ></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">文库名称</div>
					<div class="am-u-sm-8">
						<input type="text" class="am-input-sm" name="queryLibrary.name" id="name" value="${queryLibrary.name}" />
					</div>
				</div>
				<div class="mt20 clear"></div>
				<div class="mt20">
					<div class="am-g">
						<div class="am-u-md-6">
							<div class="am-btn-toolbar">
								<div class="am-btn-group am-btn-group-xs">
									<button class="am-btn am-btn-success" type="button" onclick="window.location.href='${cxt}/admin/library/doadd'" title="新增"><span class="am-icon-plus"></span> 新增</button>
								</div>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-input-group am-input-group-sm">
								<span class="am-input-group-btn">
									<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
										<span class="am-icon-search"></span> 搜索
									</button>
									<button type="button" class="am-btn am-btn-danger" onclick="cancel()">
										清空
									</button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
				<thead>
					<tr>
						<th class="th_center" width="10%"><span>ID</span></th>
						<th class="th_center"><span>文库名称</span></th>
						<th class="th_center"><span>所属项目</span></th>
						<th class="th_center"><span>浏览量</span></th>
						<th class="th_center"><span>类型</span></th>
						<th class="th_center"><span>创建时间</span></th>
						<th class="th_center"><span>操作</span></th>
					</tr>
				</thead>
				<tbody align="center">
					<c:if test="${not empty libarys }">
						<c:forEach  items="${libarys}" var="libary" >
							<tr>
								<td>${libary.id }</td>
								<td>${libary.name }</td>
								<td>${libary.subjectName}</td>
								<td>${libary.browseNum}</td>
								<td>
									<c:if test="${libary.type==1 }">PDF阅读</c:if>
									<c:if test="${libary.type==2 }">PIF阅读</c:if>
								</td>
								<td>
									<fmt:formatDate value="${libary.addTime}" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td align="center">
									<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${cxt}/admin/library/doupdate?library.id=${libary.id}'"><span class="am-icon-pencil-square-o"></span> 编辑</button>
									<button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="dledialog(${libary.id})"><span class="am-icon-trash-o"></span> 删除</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty libarys }">
						<tr>
							<td colspan="9">
								<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big>
											<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
											<span class="vam ml10">还没有文库！</span>
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
	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
</body>
</html>
