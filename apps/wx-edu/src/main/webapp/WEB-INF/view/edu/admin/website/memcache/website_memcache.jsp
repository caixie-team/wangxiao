<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redis管理</title>
<script type="text/javascript">
function selectRedis(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function cleanEmpty(memId){
	if(memId==0||memId==''){
		dialogFun("提示","该Redis-key不存在",4);
		return;
	}
	dialogFun("提示","确定清空缓存吗？",2,"javascript:cleanAction("+memId+")");
	
}
function cleanAction(memId){
	$.ajax({
		url:"${ctx}/admin/websitemem/removeMemKey",
		type:"post",
		dataType:"json",
		data:{"id":memId},
		success:function(result){
			if(result.success){
				//dialogFun("提示","清除成功",4);
				window.location.reload();
			}else{
				dialogFun("提示","请刷新重试",4);
				return;
			}
		}
	});
}
 function deladmin(id){
		dialogFun("提示","确定删除吗？",2,"javascript:delAction("+id+")");
	} 
 function delAction(id){
	$.ajax({
			url:"${ctx}/admin/websitemem/del",
			type:"post",
			dataType:"json",
			data:{"id":id},
			cache:true,
			async:false,
			success:function(result){
				if(result.success){
					$("#rem"+id).remove();
				}else{
					dialogFun("提示","请刷新重试",4);
					return;
				}
			}
		});
	 
 }
</script>
</head>
<body  >
<!-- 公共右侧样式 -->
<div class="am-cf">
     <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">Redis管理</strong> / <small>Redis列表</small></div>
 </div>
<hr/>
<!-- 公共右侧标题样式 结束-->

<form class="am-form" action="${ctx}/admin/websitemem/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
   <div class="mt20 am-padding admin-content-list">
   	<div class="am-tab-panel am-fade am-active am-in">
        <div class="am-g am-margin-top am-u-sm-5">
            <div class="am-u-sm-4 am-text-right">
            	 Redis-Key
            </div>
            <div class="am-u-sm-8">
              <input type="text" class="am-input-sm" name="websiteMemcache.memKey" value="${websiteMemcache.memKey }" id="name"/>
            </div>
        </div>
		<div class="am-g am-margin-top am-u-sm-5">
            <div class="am-u-sm-8">
               <button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
            	<span class="am-icon-search"></span> 查询
            	</button>
            </div>
        </div>
        <div class="mt20 clear"></div>
     </div>
   </div>
   <div class="mt20">
	   	<div class="am-g">
	      <div class="am-u-md-6">
	        <div class="am-btn-toolbar">
	          <div class="am-btn-group am-btn-group-xs">
	            <button class="am-btn am-btn-success" type="button" onclick="window.location.href='${ctx}/admin/websitemem/toAdd'" title="新增"><span class="am-icon-plus"></span> 新增</button>
	          </div>
	        </div>
	      </div>
	    </div>
	</div>
   </form>
   <!-- 表格样式 二   开始-->
   	<div class="am-g">
      <div class="mt20">
          <table class="am-table am-table-striped am-table-hover table-main">
				<thead>
					<tr>
						<th width="10%"><span>ID</span></th>
						<th width="30%"><span>Redis值</span></th>
						<th width="40%"><span>描述</span></th>
						<th width="20%"><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:forEach items="${websiteMemcacheList}" var="mem">
						<tr id="rem${mem.id }">
							<td>${mem.id}</td>
							<td>${mem.memKey}</td>
							<td>${mem.memDesc}</td>
							<td>
	                            <div class="am-btn-toolbar">
				                  <div class="am-btn-group am-btn-group-xs">
				                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="cleanEmpty(${mem.id })"><span class="am-icon-pencil-square-o"></span> 清空</button>
				                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deladmin(${mem.id})"><span class="am-icon-trash-o"></span> 删除</button>
				                  </div>
				                </div>
                            </td>
						</tr>
					</c:forEach>
					<c:if test="${websiteMemcacheList.size()==0||websiteMemcacheList==null}">
						<tr>
	                		<td colspan="16">
              					<div data-am-alert=""
									class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
											style="font-size: 34px;"></i> <span class="vam ml10">还没有Redis信息！</span></big>
									</center>
								</div>
							</td>
				        </tr>
					</c:if>
				</tbody>
			</table>
           <!-- /pageBar begin -->
	        <jsp:include page="/WEB-INF/view/common/admin_page.jsp"/>
	       <!-- /pageBar end -->
      </div>
    </div>
   <!-- 表格样式 二   结束-->
</body>
</html>