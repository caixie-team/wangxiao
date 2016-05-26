<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>资讯列表</title>
<script type="text/javascript" src="${ctximg}/static/common/cms/articleUtil.js"></script>
<script type="text/javascript">
	$(function(){
		initUpdatePageType('articleId',-1,'${stringTypeList}','${queryArticle.typeId}','infoTypeId','infoTypeLinkId');
	});
	function createHtml(id){
		var judge=confirm("确定生成html？");
		if(judge){
			$.ajax({
				url:"${ctx}/admin/line/article/createArticleHtml?artId="+id,
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						alert("生成完毕");
						window.location.reload();
					}else{
						alert("系统繁忙稍后重试");
						return;
					}
				},
				error:function(result){
					alert(result);
				}
			});
		}
		
	}

	function submitSearch(){
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	function clean(){
		$("#title").val("");
		$("#artype").val(0);
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
		//去除最后的逗号
		str=str.substring(0,str.length-1);

		if(checked){
			alert("请至少选择一条信息");
			return;
		}
		if(confirm("确定删除吗")){
			$.ajax({
				url:"${ctx}/admin/line/article/delArticleBatch",
				data:{"artIds":str},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						alert("删除成功");
						window.location.reload();
						$("checked").attr("checked",false);
					}else{
						alert("系统繁忙稍后重试");
						return;
					}
				}
			});
		}
	}
	function createArticleListHtml(){
		$.ajax({
			url:"${ctx}/admin/article/createArticleListHtml",
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					alert("生成完毕");
					window.location.reload();
				}else{
					alert("系统繁忙稍后重试");
					return;
				}
			}
		});
	}
	function createArticleHtmlbatch(){//生成资讯html
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
			alert("请至少选择一条信息");
			return;
		}
		$.ajax({
			url:"${ctx}/admin/line/article/createArticleHtmlBatch",
			data:{"artIds":str},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					alert("生成成功");
					window.location.reload();
				}else{
					alert("系统繁忙稍后重试");
					return;
				}
			}
		});
	}
	function allCheck(cb){
		$("input[name=articleIds]").prop('checked', cb.checked);
	}
	
	function pushIndex(){
		if(confirm("确认要发布资讯首页？")){
			$.ajax({
				url:'${ctx}/admin/line/article/pushIndex',
				type:'post',
				dataType:'json',
				success:function(result){
					alert(result.message);
				}
			});
		}
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/line/article/toArticleList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
<h4><em class="icon14 i_01"></em>&nbsp;<span>资讯管理</span> &gt; <span>资讯列表</span> </h4>
</div>
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
				<div class="capHead">
					<div class="fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>标题：</font></span>
								<input type="text" name="queryArticle.title" value="${queryArticle.title}" id="title" />
								<span class="ddTitle"><font>分类：</font></span>
								<input type="hidden" id="id" name="queryArticle.articleId" value="0"/>
								<input type="hidden" id="infoTypeId" name="queryArticle.typeId" value="${queryArticle.typeId }"/>
						    	<input type="hidden" id="infoTypeLinkId" name="queryArticle.typeLink" value="${queryArticle.typeLink }"/>
								<span>
									<select id="articleId">
						    			<option value="0">全部</option>
						    		</select>
		    					</span>
								
								<span class="ddTitle"><font>状态：</font></span>
								<select name="queryArticle.pushStates" id="status">
								<option value="">全部</option>
								<option value="UNPUBLISHED" <c:if test="${queryArticle.pushStates=='UNPUBLISHED'}">selected="selected"</c:if>>未生成</option>
								<option value="PUBLISH" <c:if test="${queryArticle.pushStates=='PUBLISH'}">selected="selected"</c:if>>已生成</option>
								</select>
								<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
								<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
							</li>
						</ul>
						
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
						<span><a href="${ctx}/admin/line/article/toAdd" title="添加资讯"><em class="icon14 new">&nbsp;</em>添加资讯</a></span>
						<span class="ml10"><a href="javascript:delArticlebatch();" title="批量删除"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
						<span class="ml10"><a href="javascript:createArticleHtmlbatch();" title="批量生成"><em class="icon14 new">&nbsp;</em>批量生成</a></span>
						<span class="ml10"><a href="javascript:pushIndex();" title="生成资讯首页"><em class="icon14 new">&nbsp;</em>生成资讯首页</a></span>
					</p>
				</div> 
			</caption>
			<thead>
				<tr>
					<th><span><input type="checkbox" onclick="allCheck(this)"/>ID</span></th>
					<th><span>标题</span></th>
					<th><span>类别</span></th>
					<th><span>点击数</span></th>
					<th><span>状态</span></th>
					<th><span>添加时间</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${articleList.size()>0}">
			<c:forEach  items="${articleList}" var="art" >
				<tr id="rem${art.articleId }">
					<td><input type="checkbox" name="articleIds" value="${art.articleId }"/>&nbsp;${art.articleId }</td>
					<td>${art.title }</td>
					<td>${art.typeName}</td>
					<td>${art.lookNum}</td>
					<td>
						<c:if test="${art.pushStates=='UNPUBLISHED' }">未生成</c:if>
						<c:if test="${art.pushStates=='PUBLISH' }">已生成</c:if>
					</td>
					<td><fmt:formatDate type="both" value="${art.addTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td  class="c_666 czBtn" align="center">
					<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/line/article/toUpdate/${art.articleId}">修改</a>
					<c:if test="${art.pushStates=='PUBLISH' }">
						<a class="ml10 btn smallbtn btn-y" title="访问" href="${articleUrl }/${art.linkUrl }" target="_blank">访问</a>
					</c:if>	
					<a class="ml10 btn smallbtn btn-y" title="生成" href="javascript:createHtml(${art.articleId})">生成</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${articleList.size()==0||articleList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有资讯信息！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div>
</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
