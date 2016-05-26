<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>搜索词列表</title>
<script type="text/javascript">
function delblackList(id){
	if(confirm("是否删除?")){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/search/delSearchWord",
			data:{"id":id},
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$("#del"+id).remove();
					alert("成功");
				}
			}
		});
	 }
}

function submitFrom(){
	$("#searchForm").submit();
}

$(function(){
	$("#type").val("${type}");
});
function toupdate(type,word){//表单提交到更新页面  防止中文乱码
	$("#updatetype").val(type);
	$("#updateword").val(word);
	$("#toupdate").submit();
}
</script>
</head>
<body  >
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>观点管理</span> &gt; <span>观点管理</span> </h4>
	</div>
<form action="${ctx}/admin/search/toupdatesearchWord" id="toupdate" method="post">
<input type="hidden" name="type" id="updatetype"/>
<input type="hidden" name="word" id="updateword"/>
</form>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/search/searchWord" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="clearfix">
						<div class="optionList">
							<span>
							<font>搜索类型：</font>
							</span>
							<span>
							<select name="type" id="type">
							<option value="weibo">观点</option>
							<option value="blog">博文</option>
							<option value="sug">问题</option>
							<option value="dis">小组</option>
							<!-- <option value="sug">挑战</option>
							<option value="video">视频</option>
							<option value="fin">财经文章</option>
							<option value="cus">用户</option> -->
							</select>
							</span>
						</div>
						<div class="optionList">
							<span>
							<font>搜索词：</font>
							</span>
							<span>
							<input type="text" name="word" value="${word }"/>
							</span>
						</div>
						<div class="optionList">
							<input class="btn btn-danger" type="button" onclick="submitFrom()" value="查询"/>
						</div>
					</div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
					<span>
					<a title="新建搜索词" href="${ctx}/admin/search/toaddsearchWord">
					<em class="icon14 new"> </em>
					新建搜索词
					</a>
					</span>
					</p>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="25%"><span>搜索词</span></th>
					<th width="25%"><span>搜索次数</span></th>
					<th width="25%"><span>置顶</span></th>
					<th width="25%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02">
				<c:forEach items="${searchWordList}" var="sw">
				<tr id="del${sw.id }">
					<td align="center" valign="middle">${sw.word }</td>
					<td align="center" valign="middle">${sw.count }</td>
					<td align="center" valign="middle">
					<c:if test="${sw.top==0 }">否</c:if>
					<c:if test="${sw.top==1 }">是</c:if>
					</td>
					</td>
					<td class="c_666 czBtn" align="center">
					<a class="btn smallbtn btn-y" href="javascript:toupdate('${sw.type }','${sw.word }');" title="修改">修改</a>
					<a class="btn smallbtn btn-y" href="javascript:void(0)"
										onclick="delblackList(${sw.id })" title="删除">删除</a> 
										
					</td>
				</tr>
				</c:forEach>
				<c:if test="${searchWordList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有搜索词数据！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		</form>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- /tab2 end-->
</body>
</html>