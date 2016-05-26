<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>积分列表</title>
<script type="text/javascript">
function updateIntegralTemplate(id,status,obj){//更新状态
	$.ajax({
		url:"${ctx}/admin/integral/template/updatestatus",
		data:{"userIntegralTemplate.id":id,"userIntegralTemplate.status":status},
		dataType:"json",
		type:"post",
		success:function(result){
			if(result.success){
				if(status==0){//正常
					 $("#status"+id).html("正常");
					/* $(obj).html("停止");
					$(obj).attr("title","停止");
					$(obj).attr("onclick","updateIntegralTemplate("+id+",1,this)");  */
					dialogFun("积分管理","已恢复正常",5,"");
				}else{//停止
					 $("#status"+id).html("停止");
					/* $(obj).html("正常");
					$(obj).attr("title","正常");
					$(obj).attr("onclick","updateIntegralTemplate("+id+",0,this)");  */
					dialogFun("积分管理","已停止使用",5,"");
				}
			}else{
				dialogFun("积分管理","系统繁忙，请稍后重试",0);
				return;
			}
		}
	});
}
function updateig(id){
	window.location.href="${ctx}/admin/integral/template/toupdate/"+id;
	
}

function updateigpwd(id){
	window.location.href="${ctx}/admin/user/toupdatepwd/"+id;
	
}

</script>
</head>
<body  >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">积分管理</strong> / <small>积分列表</small>
		</div>
	</div>
	<hr/>
	<!-- 公共右侧样式 -->
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>ID</th>
							<th>模板名称</th>
							<th>使用类型</th>
							<th>使用场景</th>
							<th>功能分数</th>
							<th>创建时间</th>
							<th>修改人</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty integralTempateList }">
							<c:forEach items="${integralTempateList}" var="ig" >
								<tr>
									<td>${ig.id }</td>
									<td>${ig.name}</td>
									<td><c:if test="${ig.type==0}">学员</c:if></td>
									<td>${ig.keyword }</td>
									<td>${ig.showScore}</td>
									<td><fmt:formatDate value="${ig.createTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${ig.createUser}</td>
									<td id="status${ig.id}">
										<c:if test="${ig.status==0}">正常</c:if>
										<c:if test="${ig.status==1}">停止</c:if>
									</td>
									<td>
										<c:if test="${ig.status==1}">
											<button class="am-btn am-btn-default am-btn-xs am-text-success" onclick="updateIntegralTemplate(${ig.id},0,this)">
												<span class="am-icon-pencil-square-o"></span>正常
											</button>
										</c:if>
										<c:if test="${ig.status==0}">
											<button class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="updateIntegralTemplate(${ig.id},1,this)">
												<span class="am-icon-pencil-square-o"></span>停止
											</button>
										</c:if>
										<button class="am-btn am-btn-default am-btn-xs am-hide-sm-only am-text-warning" onclick="updateig(${ig.id})">
											<span class="am-icon-copy"></span>修改
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty integralTempateList }">
							<tr>
								<td align="center" colspan="16">
									<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
										<center>
											<big>
												<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
												<span class="vam ml10">还没有积分信息！</span>
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
</body>
</html>
