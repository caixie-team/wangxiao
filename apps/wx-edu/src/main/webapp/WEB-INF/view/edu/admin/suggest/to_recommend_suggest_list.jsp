<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>完成问题列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function(){
		 $( "#addtime" ).datepicker(
    		{regional:"zh-CN",
    		changeMonth: true,
    		dateFormat:"yy-mm-dd"
	    });
	});	
	function cancel(){
		$( "#addtime" ).val('');
		$( "#title" ).val('');
		$( "#showname" ).val('');
		$( "#suggestId" ).val('');
		$( "#top" ).val(-1);
	}
	function updateTop(sugSugestId,obj){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/sug/upateSugSuggestTop",
			data:{"sugSugestId":sugSugestId,"top":1},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$(obj).attr("onclick","quxiaoTop("+sugSugestId+",this)");
					$(obj).html("取消置顶");
					$("#top"+sugSugestId).html("是");
					alert("成功");
				}
				if(result.message=="false"){
					alert("请刷新重试");
				}
			}
		});
	}
	function quxiaoTop(sugSugestId,obj){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/sug/upateSugSuggestTop",
			data:{"sugSugestId":sugSugestId,"top":0},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$(obj).attr("onclick","updateTop("+sugSugestId+",this)");
					$(obj).html("置顶");
					$("#top"+sugSugestId).html("否");
					alert("成功");
				}
				if(result.message=="false"){
					alert("请刷新重试");
				}
			}
		});
	}
	$(function(){
		$("#type").val("${querySugSuggest.type}");
		$("#top").val("${querySugSuggest.top}");
	});
	function delSugSuggest(suggestId){
		if(confirm("是否删除?")){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/sug/delSugSuggest",
				data:{"sugSugestId":suggestId},
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){ 
						$("#del"+suggestId).remove();
						alert("成功");
					}
					if(result.message=="false"){
						alert("请刷新重试");
					}
				}
			});
		 }
	}
	function submitform(){
		$("#pageCurrentPage").val(1);//页数为1
		$("#searchForm").submit();//提交表单
	}
</script>

</head>
<body  >

	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>问题管理</span> &gt; <span>完成问题列表</span> </h4>
	</div>
	<hr/>
			
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/sug/toRecommendSugSuggestList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>问题id：</font></span>
								<input type="text" name="querySugSuggest.id" value="${querySugSuggest.id }" id="suggestId"/>
							</li>
							<li>
								<span class="ddTitle"><font>问题标题：</font></span>
								<input type="text" name="querySugSuggest.title" value="${querySugSuggest.title }" id="title"/>
							</li>
							<li>
								<span class="ddTitle"><font>置顶：</font></span>
								<select name="querySugSuggest.top" style="width: 100px;" id="top">
									<option value="-1">-全部-</option>
									<option value="0">未置顶</option>
									<option value="1">置顶</option>
								</select>
							</li>
						</ul>
					</div>
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>发表人：</font></span>
								<input type="text" name="querySugSuggest.showname" value="${querySugSuggest.showname }" id="showname" />
							</li>
							<li>
								<span class="ddTitle"><font>发表时间：</font></span>
								<input type="text" id="addtime" name="querySugSuggest.addTimeStr" value="${querySugSuggest.addTimeStr }" />
							</li>
						</ul>
					</div>
					<div class="w50pre fl" style="text-align: center;">
						<ul class="ddBar">
							<li>
								<input class="am-btn am-btn-danger ml10" type="button" onclick="submitform()" value="查询">
								<input class="am-btn am-btn-danger ml10" type="button" onclick="cancel()" value="清空">
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn"></p>
					<p class="fr c_666"><span>完成问题列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
				</div>
			</caption>
			<thead>
				<tr>
					<th><span>ID</span></th>
					<th><span>问题标题</span></th>
					<!-- <th width="19%"><span>问题内容</span></th> -->
					<th><span>发表人</span></th>
					<th><span>发表时间</span></th>
					<!-- <th width="3%"><span>热心</span></th>
					<th width="3%"><span>智慧</span></th>
					<th width="3%"><span>类型</span></th> -->
					<th><span>是否置顶</span></th>
					<th><span>回复数</span></th>
					<th><span>浏览次数</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody  id="tabS_02" align="center">
				
				<c:forEach items="${sugSuggestList}" var="ssl">
				<tr id="del${ssl.id }">
					<td>${ssl.id }</td>
					<td>${ssl.title }</td>
					<%-- <td>${ssl.content }</td> --%>
					<td>${ssl.showname }</td>
					<td><fmt:formatDate value="${ssl.addtime}" pattern="yyyy/MM/dd  HH:mm:ss" ></fmt:formatDate></td>
				<%-- 	<td>${ssl.hotNum }</td>
					<td>${ssl.wisdomNum }</td>
					<td>
					<c:if test="${ssl.type==1}">务实</c:if>
					<c:if test="${ssl.type==2}">意境</c:if>
					</td> --%>
					<td id="top${ssl.id }">
					<c:if test="${ssl.top==1}">是</c:if>
					<c:if test="${ssl.top==0}">否</c:if>
					</td>
					<td>${ssl.replycount }</td>
					<td>${ssl.browseNum }</td>
					<td class="c_666 czBtn" align="center">
						<a class="btn smallbtn btn-y" href="${ctx}/admin/sug/toUpdateSugSuggest?sugSuggestId=${ssl.id }" title="修改">修改</a>
						<a class="btn smallbtn btn-y" href="${ctx}/admin/sug/toSugSuggestReplyList?sugSugestId=${ssl.id }" title="回复管理">回复管理</a>
						<c:if test="${ssl.top==0}">
						<a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="updateTop(${ssl.id },this)" title="置顶">置顶</a>
						</c:if>
						<c:if test="${ssl.top==1}">
						<a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="quxiaoTop(${ssl.id },this)" title="取消置顶">取消置顶</a>
						</c:if>
						<a class="btn smallbtn btn-y" href="javascript:delSugSuggest(${ssl.id })" title="删除">删除</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${sugSuggestList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有数据！</span>
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