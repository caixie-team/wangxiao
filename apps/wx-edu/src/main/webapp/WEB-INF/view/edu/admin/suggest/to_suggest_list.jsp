<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>问题列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
	<script type="text/javascript">
		function cancel(){
			$( "#title" ).val('');
			//清空时间
			$("input[name='querySugSuggest.addTimeStr']:first").attr("value", "");
			$( "#showname" ).val('');
			$( "#suggestId" ).val('');
			$("#selcommon").val('');
			$("#selcommon").find('option').eq(0).attr('selected', true);
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
						dialogFun("问题列表 ","成功",5,"javascript:void(0)");
					}
					if(result.message=="false"){
						dialogFun("问题列表 ","请刷新重试",5,"");
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
						dialogFun("问题列表 ","成功",5,"javascript:void(0)");
					}
					if(result.message=="false"){
						dialogFun("问题列表 ","请刷新重试",5,"");
					}
				}
			});
		}
		$(function(){
			$("#type").val("${querySugSuggest.type}");
			$("#top").val("${querySugSuggest.top}");
		});
		function delSugSuggest(suggestId){
			dialogFun("问题列表 ","确定删除这条信息么？",2,"javascript:delSugSuggestAjax("+suggestId+")");
		}
		function delSugSuggestAjax(suggestId){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/sug/delSugSuggest",
				data:{"sugSugestId":suggestId},
				cache:true,
				async:false,
				success:function(result){
					if(result.success){
						dialogFun("问题列表 ","删除成功",5,"");
					}else {
						dialogFun("问题列表","请刷新重试",5,"");
					}
				}
			});
		}
		function chickdelCommons(suggestId,common){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"/admin/sug/delCommons",
				data:{"sugSugestId":suggestId,"common":common},
				cache:true,
				async:false,
				success:function(result){
					if(common==0){
						dialogFun("问题列表 ","标记成功",5,"");
					}
					if(common==1){
						dialogFun("问题列表 ","取消成功",5,"");
					}
				}
			});
		}
		function submitform(){
			$("#pageCurrentPage").val(1);//页数为1
			$("#searchForm").submit();//提交表单
		}
		$(function(){
				$("#selcommon").val('${querySugSuggest.common}');

		});
		function checkAll(box){
			$("input[name=couIds]").prop("checked",box.checked);
		}
		function updateSuggest(id){
			window.location.href="${ctx}/admin/sug/toUpdateSugSuggest?sugSuggestId="+id;
		}
		function replySuggest(id){
			window.location.href="${ctx}/admin/sug/toSugSuggestReplyList?sugSugestId="+id;
		}
	</script>
</head>
<body  >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">问题管理</strong> / <small>问题列表</small>
		</div>
	</div>
	<hr/>
	<form action="${ctx}/admin/sug/toSugSuggestList" name="searchForm" id="searchForm" method="post"  class="am-form">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="mt20 am-padding admin-content-list">
			<div class="am-tab-panel am-fade am-active am-in">
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						问题id
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="querySugSuggest.id" value="${querySugSuggest.id }" id="suggestId"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						问题标题
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="querySugSuggest.title" value="${querySugSuggest.title }" id="title"/>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						发表时间
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" data-am-datepicker="{format: 'yyyy-mm-dd'}"  id="addtime"  name="querySugSuggest.addTimeStr" readonly="readonly"  value="${querySugSuggest.addTimeStr }" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						发表人
					</div>
					<div class="am-u-sm-8 am-u-end">
						<input type="text" class="am-input-sm" name="querySugSuggest.showname" value="${querySugSuggest.showname }" id="showname" />
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						常见问题
					</div>
					<div class="am-u-sm-8 am-u-end">
						<select name="querySugSuggest.common" class="am-input-sm" id="selcommon"  onclick="" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"  style="display: none;">
							<option value=" ">全部</option>
							<option value="1">常见</option>
							<option value="0">不常见</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top am-u-sm-4 am-text-left">
					<div class="am-u-sm-4 am-text-right">
						&nbsp;
					</div>
					<div class="am-u-sm-8 am-u-end">
						<button class="am-btn am-btn-warning" onclick="submitform()" type="button">
							<span class="am-icon-search"></span>搜索
						</button>
						<button class="am-btn am-btn-danger" onclick="cancel()" type="button">
							清空
						</button>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</form>
	<div class="mt20">
		<div class="doc-example">
			<div class="am-scrollable-horizontal">
				<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th><span>ID</span></th>
							<th><span>问题标题</span></th>
							<th><span>发表人</span></th>
							<th><span>发表时间</span></th>
							<th><span>回复数</span></th>
							<th><span>浏览次数</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${sugSuggestList}" var="ssl">
						<tr>
							<td>${ssl.id}</td>
							<td>${ssl.title }</td>
							<td>${ssl.showname }</td>
							<td><fmt:formatDate value="${ssl.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${ssl.replycount }</td>
							<td>${ssl.browseNum }</td>
							<td>
								<button class="am-btn am-btn-primary" onclick="updateSuggest(${ssl.id})">
									<span class="am-icon-edit"></span> 修改
								</button>
								<button class="am-btn am-btn-secondary" onclick="replySuggest(${ssl.id})">
									回复管理
								</button>
								<c:if test="${ssl.top==0}">
									<button class="am-btn am-btn-warning" onclick="updateTop(${ssl.id },this)">
										置顶
									</button>
								</c:if>
								<c:if test="${ssl.top==1}">
									<button class="am-btn am-btn-warning" onclick="quxiaoTop(${ssl.id },this)">
										取消置顶
									</button>
								</c:if>
								<button class="am-btn am-btn-danger" onclick="delSugSuggest(${ssl.id })">
									<span class="am-icon-trash-o"></span> 删除
								</button>
								<c:if test="${ssl.common==0}">
									<button class="am-btn am-btn-warning" onclick="chickdelCommons(${ssl.id },${ssl.common})">
										标记为常见问题
									</button>
								</c:if>
								<c:if test="${ssl.common==1}">
									<button class="am-btn am-btn-warning" onclick="chickdelCommons(${ssl.id },${ssl.common})">
										取消标记
									</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				<c:if test="${sugSuggestList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
						<center>
						<big>
							<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
								<span class="vam ml10">还没有观点数据！</span>
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
	<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	 <!-- /pageBar end -->
</body>
</html>