<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>资讯列表</title>
	<script type="text/javascript">
		$(function() {
			var keyWord='${article.type}'==''?'':'${article.type}';
			$("#artype").val(keyWord);
		});
		function clean(){
			$("#title").val("");
			$("#artype").val("all");
			$("#artype").find('option').eq(0).attr('selected', true);
		}
		function allCheck(cb) {
			$("input[name=articleIds]").prop('checked', cb.checked);
		}
		function remError(id){
			dialogFun("资讯列表","确定删除此问题？",2,"javascript:remErrorDia("+id+")");
		}
		function remErrorDia(id){
			window.location.href="${ctx}/admin/teacher/delete/"+id+"";
		}
		function delArticlebatch(){//删除资讯
			var artIds=document.getElementsByName("articleIds");
			var str="";
			var checked=true;
			$(artIds).each(function(){
				if($(this).prop("checked")){
					str+=this.value+",";
					checked=false;
				}
			});
			if(checked){
				dialogFun("资讯列表","请至少选择一条信息",0);
				return;
			}
			str=str.substring(0,str.length-1);
			dialogFun("资讯列表","确定删除吗？",2,"javascript:delArticlebatchAjax('"+str+"')");
		}
		function delArticlebatchAjax(str){
			$.ajax({
				url:"${ctx}/admin/article/delArticleBatch",
				data:{"artIds":str},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						window.location.href=window.location.href;
					}else{
						dialogFun("资讯列表","系统繁忙稍后重试",6,"");
						closeFun();
						return;
					}
				}
			});
		}
		function toAdd(){
			window.location.href="${ctx}/admin/article/toAdd"
		}
	</script>
</head>
<body>
	<div class="am-cf">
		<strong class="am-text-primary am-text-lg">资讯管理</strong> / <small>资讯列表</small>
	</div>
	<hr/>
	<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<form action="${ctx}/admin/article/list" name="searchForm" id="searchForm" method="post"  class="am-form">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">标题</div>
					<div class="am-u-sm-8">
						<input type="text" name="article.title" class="am-input-sm" value="${article.title}" id="title" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-5">
					<div class="am-u-sm-4 am-text-right">分类</div>
					<div class="am-u-sm-8">
						<select data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" name="article.type" id="artype">
							<option value="all">全部</option>
							<c:forEach items="${classifyList}" var="classify">
								<option value="${classify.name}">${classify.explain }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="mt20 clear"></div>
			</form>
		</div>
	</div>
	<div class="mt20">
		<div class="am-g">
			<div class="am-u-md-6">
				<div class="am-btn-toolbar">
					<div class="am-btn-group am-btn-group-xs">
						<button class="am-btn am-btn-success" title="新增" type="button" onclick="toAdd()">
							<span class="am-icon-plus"></span> 新建资讯
						</button>
						<button class="am-btn am-btn-success" title="删除" type="button" onclick="delArticlebatch()">
							<span class="am-icon-trash-o"></span> 删除
						</button>
					</div>
				</div>
			</div>
			<div class="am-u-md-5">
				<div class="am-input-group am-input-group-sm">
					<span class="am-input-group-btn">
						<button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
							<span class="am-icon-search"></span> 搜索
						</button>
						<input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal">
				<table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
					<thead>
						<tr>
							<th style="width:5%;">
								<label class="am-checkbox">
									<input  type="checkbox" data-am-ucheck onclick="allCheck(this)"/>
								</label>
							</th>
							<th style="vertical-align:middle;width:5%;"><span>&nbsp;ID</span></th>
							<th style="vertical-align:middle;width:5%;"><span>类型</span></th>
							<th style="vertical-align:middle;width:35%;"><span>标题</span></th>
							<%--<th><span>简介</span></th>--%>
							<th style="vertical-align:middle;width:8%;"><span>点击数</span></th>
							<th style="vertical-align:middle;width:17%;"><span>添加时间</span></th>
							<th style="vertical-align:middle;width:20%;"><span>操作</span></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty articleList }">
							<c:forEach items="${articleList}" var="art" >
								<tr>
									<td>
										<label class="am-checkbox">
											<input type="checkbox"  data-am-ucheck name="articleIds" value="${art.id}"/>
										</label>
									</td>
									<td style="vertical-align:middle;">${art.id }</td>
									<td style="vertical-align:middle;">
										<c:if test="${art.type=='info'}">资讯</c:if>
										<c:if test="${art.type=='notice'}">公告</c:if>
										<c:if test="${art.type=='news'}">动态</c:if>
									</td>
									<td style="vertical-align:middle;">${art.title }</td>
									<%--<td>${fn:substring(art.description,0,50)}</td>--%>
									<td style="vertical-align:middle;">${art.clickTimes}</td>
									<td style="vertical-align:middle;"><fmt:formatDate type="both" value="${art.createTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td style="vertical-align:middle;">
										<div class="am-btn-group am-btn-group-xs">
											<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${ctx}/admin/article/toUpdate/${art.id}'">
												<span class="am-icon-pencil-square-o"></span> 修改
											</button>
											<button class="am-btn am-btn-default am-btn-xs am-hide-sm-only" onclick="window.open('${ctx}/front/toArticle/${art.id}')">
												<span class="am-icon-file-o"></span> 预览
											</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty articleList }">
							<tr>
								<td align="center" colspan="16">
									<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
										<center>
											<big>
												<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
												<span class="vam ml10">还没有资讯信息！</span>
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
</body>
</html>
