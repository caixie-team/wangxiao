<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>微信默认回复列表</title>
<script type="text/javascript">
function cancel(){
	
	$("#keyword").val("");
	$("#msgType").val(-1);
	$('#msgType').find('option').eq(0).attr('selected', true);
}
function selectWeixin(){
	var num=$("input[type='radio']:checked").length;
	if(num>0){
		var weixinVal=$("input[type='radio']:checked").val();
		//调用父页面的方法  
        window.opener.weixinAdd(weixinVal);   
        window.close(); 
	}else{
		dialogFun("微信素材列表","请选择回复素材",0);
	}
}
$(function(){
	$("#msgType").val("${queryWeixinReply.msgType}");
})
</script>
</head>
<!-- 内容 开始  -->
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">微信管理</strong> /<small>微信素材列表</small>
		</div>
	</div>
	<hr />
	<!-- /tab1 begin-->
	<div class="mt20 admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/weixin/defaultpage" class="am-form"
				name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage"
					value="${page.currentPage}" />
						<div class="mt20 am-padding">
							<div class="am-g am-margin-top am-u-sm-4 am-text-left">
								<div class="am-u-sm-4 am-text-right">类型：</div>
								<div class="am-u-sm-8 am-u-end">
									<select name="queryWeixinReply.msgType" id="msgType"
										data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
										<option value="-1">请选择</option>
										<option value="1">文本</option>
										<option value="2">图文</option>
										<option value="3">多图文</option>
									</select>
								</div>
							</div>

							<div class="am-g am-margin-top am-u-sm-4 am-text-left">
								<div class="am-u-sm-4 am-text-right">关键字</div>
								<div class="am-u-sm-8 am-u-end">
									<input type="text" name="queryWeixinReply.keyword" id="keyword"
										value="${queryWeixinReply.keyword}" />
								</div>
							</div>

							<div class="am-g am-margin-top am-u-sm-12 am-text-left">
								<div class="am-u-sm-12 am-u-end am-text-center">
									<input type="button" class="am-btn am-btn-danger" value="查询"
										onclick="goPage(1)" /> <input type="button"
										class="am-btn am-btn-danger" value="清空" onclick="cancel()" />
								</div>
							</div>
						</div>

						<div class="clearfix"></div>
			</div>
	</div>
	<div class="mt20">
					
					<div class="am-scrollable-horizontal am-scrollable-vertical">
						<table
							class="am-table am-table-bordered am-table-striped am-text-nowrap">
							<thead>
								<tr>
									<th><span>&nbsp;</span></th>
									<th width="8%">ID</th>
									<th><span>关键字</span></th>
									<th><span>图文标题</span></th>
									<th><span>类型</span></th>
									<th><span>创建时间</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${weixinReplys.size()>0}">
									<c:forEach items="${weixinReplys}" var="weixinReply">
										<tr>
											<td>
											<label class="am-radio">
											<input data-am-ucheck type="radio"
												value="${weixinReply.id }#${weixinReply.title }#${weixinReply.keyword }#${weixinReply.msgType }"
												name="idStr" />
												
											</label>
												</td>
											<td>${weixinReply.id }</td>
											<td>${weixinReply.keyword }</td>
											<td>${weixinReply.title }<c:if
													test="${weixinReply.msgType==1 }">--</c:if></td>
											<td><c:if test="${weixinReply.msgType==1 }">文本</c:if> <c:if
													test="${weixinReply.msgType==2 }">图文</c:if> <c:if
													test="${weixinReply.msgType==3 }">多图文</c:if></td>
											<td><fmt:formatDate value="${weixinReply.createTime}"
													type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${weixinReplys.size()==0||weixinReplys==null}">
									<tr>
										<td colspan="16">
											<div data-am-alert=""
												class="am-alert am-alert-secondary mt20 mb50">
												<center>
													<big> <i class="am-icon-frown-o vam"
														style="font-size: 34px;"></i> <span class="vam ml10">还没有素材信息！</span></big>
												</center>
											</div>
										</td>
									</tr>
								</c:if>
								<tr>
									<td align="center" colspan="6"><a
										class="am-btn am-btn-danger" title="确定"
										href="javascript:selectWeixin()">确定</a> <a
										class="am-btn am-btn-danger" title="返 回"
										href="javascript:window.close();">取消</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</table>
			</form>
			<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div>
		<!-- /commonWrap -->
	</div>
</body>
</html>