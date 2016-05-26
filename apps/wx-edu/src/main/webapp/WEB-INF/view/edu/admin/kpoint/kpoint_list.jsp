<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>视频树</title>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">视频管理</strong> / <small>视频树</small></div>
</div>
<hr>
<div class="am-panel-bd am-collapse am-in" id="collapse-panel-1" data-force="30">
	<input type="hidden" id="pointIds"/>
	<c:if test="${not empty kpointList}">
		<button type="button" class="am-btn am-btn-success" onclick="addChapter()">
			<i class="am-icon-plus"></i> 添加章节
		</button>
		<button type="button" class="am-btn am-btn-success" onclick="addKpoint()">
			<i class="am-icon-plus"></i> 添加课时
		</button>
		<ul id="sortable" class="am-list admin-content-file">
			<c:set value="0" var="sum" />
			<c:set value="0" var="Upsum" />
			<c:forEach items="${kpointList}" var="kpoint" varStatus="index">
				<li id="${kpoint.id}" style="word-break: break-all;"<c:if test="${kpoint.type==1}">class="am-text-primary"</c:if>>
					<div class="am-text-left" style="float:left;">
						<c:if test="${kpoint.type==0}">
							<div class="item-line"></div>
						</c:if>
						<c:if test="${kpoint.type==1}">
							<c:set value="${sum-sum}" var="sum" />
							<c:set value="${Upsum+1}" var="Upsum" />
							<div class="item-content">
								第 <span class="number">${Upsum}</span> 章： ${kpoint.name}
							</div>
						</c:if>
						<c:if test="${kpoint.type==0}">
							<c:set value="${sum+1}" var="sum" />
							<div class="item-content ml50">
								课时<span class="number">${sum}</span> ： ${kpoint.name}
							</div>
						</c:if>
					</div>

					<div class="am-text-right am-l">
						<form id="delPointForm" name="delPointForm">
							<input type="hidden" id="id${index.index}" name="id" value="${kpoint.id}"/>
						</form>
						<c:if test="${kpoint.type==0}">
							<div class="item-actions prs">
								<button type="button" class="am-btn am-btn-secondary" onclick="updateKpoint(${kpoint.id})">
									<i class="am-icon-edit"></i> 编辑
								</button>
								<button type="button" class="am-btn am-btn-danger" onclick="beforeRemove(${kpoint.id},${kpoint.courseId})">
									<i class="am-icon-trash-o"></i> 删除
								</button>
							</div>
						</c:if>
						<c:if test="${kpoint.type==1}">
							<div class="item-actions prs" >

								<button type="button" class="am-btn am-btn-secondary" onclick="updateChapter(${kpoint.id})">
									<i class="am-icon-edit"></i> 编辑
								</button>
								<button type="button" class="am-btn am-btn-danger" onclick="beforeRemove(${kpoint.id},${kpoint.courseId})">
									<i class="am-icon-trash-o"></i> 删除
								</button>
							</div>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${empty kpointList}">
		<h2>这个课程还没有章节！</h2>
		<button class="am-btn am-btn-success" onclick="addChapter()"><i class="am-icon-plus"></i> 添加数据</button>
	</c:if>
</div>
<script>
	$(function() {
		$("#sortable" ).sortable();
		$("#sortable" ).disableSelection();
		$("#sortable").bind('sortupdate', function(event, ui) {
			var id="";
			$("#sortable li").each(function(){
				id = id + $(this).attr("id")+",";
			})
			id = id.substring(0,id.length-1);
			$.ajax({
				url:"${ctx}/admin/kpoint/updatesortable/"+${courseId},
				data:{"id":id},
				type:"post",
				dataType:"json",
				success:function(result){
					window.location.reload();
				}
			});
		});
	});

	// 添加章节
	function addChapter(){
		window.open('${ctx}/admin/course/updateChapter?submitType=add&courseId=${courseId}','newwindow');
	}
	// 修改章节
	function updateChapter(id){
		window.open('${ctx}/admin/course/updateChapter?submitType=update&id='+id+'&courseId=${courseId}','newwindow');
	}
	// 添加课时
	function addKpoint(){
		window.open('${ctx}/admin/course/updateKpoint?submitType=add&courseId=${courseId}','newwindow');
	}
	// 修改课时
	function updateKpoint(id){
		window.open('${ctx}/admin/course/updateKpoint?kpointId='+id+'&submitType=update&courseId=${courseId}','newwindow');
	}

	//删除
	function beforeRemove(kpointId,courseId) {
		dialogFun("提示","确认删除吗？",2,"javascript:deleteKpoint("+kpointId+","+courseId+")");
	}

	function deleteKpoint(kpointId,courseId){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${ctx}/admin/kpoint/delCourseKpoint/"+kpointId+"/"+courseId,
			data : $("#delPointForm").serialize(),
			async : false,
			success : function(result) {
				if (result.success) {
					window.location.href='${ctx}/admin/kpoint/list/'+${courseId};
				}
			}
		});
	}
</script>
</body>
</html>
