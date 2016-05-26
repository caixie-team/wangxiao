<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>观点列表</title>
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
		$( "#weiboId" ).val('');
		$( "#cusName" ).val('');
		$( "#content" ).val('');
		$( "#top" ).val(-1);
		
	}
	function updateTop(weiBoId,obj){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/weiBo/upateWeiBoTop",
			data:{"weiBo.id":weiBoId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$(obj).attr("onclick","quxiaoTop("+weiBoId+",this)");
					$(obj).html("取消置顶");
					$("#top"+weiBoId).html("是");
					alert("成功");
				}
				if(result.message=="false"){
					alert("请刷新重试");
				}
			}
		});
	}
	function quxiaoTop(weiBoId,obj){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/weiBo/quxiaoUupateWeiBoTop",
			data:{"weiBo.id":weiBoId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$(obj).attr("onclick","updateTop("+weiBoId+",this)");
					$(obj).html("置顶");
					$("#top"+weiBoId).html("否");
					alert("成功");
				}
				if(result.message=="false"){
					alert("请刷新重试");
				}
			}
		});
	}
	$(function(){
		$("#top").val("${queryWeiBo.top}");
	});
	function delweibo(weiboId,cusId){
		if(confirm("是否删除?")){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/weiBo/delWeiBo",
				data:{"weiBo.id":weiboId,"weiBo.cusId":cusId},
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){ 
						$("#del"+weiboId).remove();
					}
					if(result.message=="false"){
						alert("请刷新重试");
					}
				}
			});
		 }
	}
	function submitFrom(){
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
</script>

</head>
<body  >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>观点管理</span> &gt; <span>观点列表</span> </h4>
	</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/weiBo/toWeiBoList" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>观点id：</font></span>
								<input type="text" name="queryWeiBo.id" value="${queryWeiBo.id }" id="weiboId"/>
							</li>
							<li>
								<span class="ddTitle"><font>观点内容：</font></span>
								<input type="text" name="queryWeiBo.content"  value="${queryWeiBo.content }" id="content"/>
							</li>
							<li>
								<span class="ddTitle"><font>发表人：</font></span>
								<input type="text" name="queryWeiBo.cusName" value="${queryWeiBo.cusName }" id="cusName"/>
							</li>
						</ul>
					</div>
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>发表时间：</font></span>
								<input type="text" name="queryWeiBo.addTimeStr" id="addtime" value="${queryWeiBo.addTimeStr }" />
							</li>
							<li>
								<span class="ddTitle"><font>是否置顶：</font></span>
								<select name="queryWeiBo.top" style="width: 100px;" id="top">
									<option value="-1">-全部-</option>
									<option value="0">未置顶</option>
									<option value="1">置顶</option>
								</select>
							</li>
                            <li >
                                <input class="btn btn-danger ml10" type="button" onclick="submitFrom()" value="查询">
                                <input class="btn btn-danger ml10" type="button" onclick="cancel()" value="清空">
                            </li>
							
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn"></p>
					<p class="fr c_666"><span>观点列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="10%"><span>ID</span></th>
					<th><span>观点内容</span></th>
					<th><span>发表人</span></th>
					<th><span>发表时间</span></th>
					<th><span>评论数</span></th>
					<th><span>是否置顶</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody  id="tabS_02" align="center">
				<c:forEach items="${queryWeiBoList}" var="qwbl">
				<tr id="del${qwbl.id }">
					<td>${qwbl.id }</td>
					<td>${qwbl.shortContent }</td>
					<td>${qwbl.showname }</td>
					<td><fmt:formatDate value="${qwbl.addTime}" pattern="yyyy/MM/dd  HH:mm:ss" ></fmt:formatDate></td>
					<td>${qwbl.commentNum }</td>
					<td id="top${qwbl.id }"><c:if test="${qwbl.top==0}">否</c:if>
					<c:if test="${qwbl.top==1}">是</c:if>
					</td>
					<td class="c_666 czBtn" align="center">
						<a class="btn smallbtn btn-y" href="${ctx}/admin/weiBo/toWeiBoInfo?comment.weiboId=${qwbl.id }" title="修改">回复管理</a>
						<a class="btn smallbtn btn-y" href="javascript:delweibo(${qwbl.id },${qwbl.cusId })" title="删除">删除</a>
						<c:if test="${qwbl.top==0}">
						<a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="updateTop(${qwbl.id },this)" title="置顶">置顶</a>
						</c:if>
						<c:if test="${qwbl.top==1}">
						<a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="quxiaoTop(${qwbl.id },this)" title="取消置顶">取消置顶</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${queryWeiBoList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
							<span>还没有观点数据！</span>
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