<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>会员商品列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
function del(id){
	dialogFun("会员商品列表 ","确定删除吗",2,"javascript:delmembersale("+id+")");
}
function delmembersale(id){
		$.ajax({
			url:"${cxt}/admin/membersale/del/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					window.location.reload();
					closeFun();
				}
			}
		})
}
function cancel(){
	$("#type").val(0);
	$("#type").find('option').eq(0).attr('selected', true);
}
       
</script>
</head>
<body  >
<form action="${ctx}/admin/membersale/list" name="searchForm" id="searchForm" method="post" class="am-form">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">会员商品管理</strong> / <small>会员商品列表</small></div>
</div>	
<hr/>
<div class="mt20 am-padding admin-content-list">
   	<div class="am-tab-panel am-fade am-active am-in">
   	  <div class="am-g am-margin-top am-u-sm-5">
			            <div class="am-u-sm-4 am-text-right">
			            	<span class="ddTitle"><font>会员类型：</font></span>
			            </div>
			            <div class="am-u-sm-8">
			              <select name="queryMemberSale.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
										<option value="0">--请选择--</option>
										<c:forEach items="${memberTypes }" var="memberType">
											<option value="${memberType.id }" <c:if test="${memberType.id==queryMemberSale.type }">selected="selected"</c:if>>${memberType.title }</option>
										</c:forEach>
						   </select>
			            </div>
		 		</div>
		        <div class="mt20 clear"></div>
			            <div class="mt20">
				    	<div class="am-g">
					      <div class="am-u-md-6">
					        <div class="am-btn-toolbar">
					          <div class="am-btn-group am-btn-group-xs">
					            <button type="button" class="am-btn am-btn-success" onclick="window.location.href='${cxt}/admin/membersale/doadd'">
					            	<span class="am-icon-plus"></span> 新建会员价格
					            </button>
					          </div>
					        </div>
					      </div>
					      <div class="am-u-sm-12 am-u-md-3">
					        <div class="am-input-group am-input-group-sm">
					          <span class="am-input-group-btn">
					            <button type="button" class="am-btn am-btn-warning" onclick="goPage(1)">
					            	<span class="am-icon-search"></span> 搜索
					            </button>
					            <button type="button" class="am-btn am-btn-danger" onclick="cancel()">                
					            	清空
					            </button>
					          </span>
					        </div>
					      </div>
					    </div>
				    </div>
	</div>
</div>
</form>
 <div class="am-g">
		      <div class="mt20">
		          <table class="am-table am-table-striped am-table-hover table-main am-table-bordered">
		          <thead>
					<tr>
						<th class="th_center"><span>ID</span></th>
                        <th class="th_center"><span>商品名称</span></th>
                        <th class="th_center"><span>类型</span></th>
                        <th class="th_center"><span>价格</span></th>
                        <th class="th_center"><span>时长（天）</span></th>
                        <th class="th_center"><span>排序</span></th>
                        <th class="th_center"><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${memberSaleList.size()>0}">
				<c:forEach  items="${memberSaleList}" var="memberSale" >
					<tr>
						<td>${memberSale.id }</td>
						<td>${memberSale.name }</td>
						<td>${memberSale.title }</td>
						<td>${memberSale.price }</td>
						<td>${memberSale.days }</td>
						<td>${memberSale.sort}</td>
						<td  class="c_666 czBtn" align="center">
						 <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${cxt}/admin/membersale/doupdate/${memberSale.id}'">
						 <span class="am-icon-pencil-square-o"></span> 
							修改
						 </button>
						 <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="del(${memberSale.id})">
						 <span class="am-icon-trash-o"></span> 
							删除
						 </button>
                        </td>
						
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${memberSaleList.size()==0||memberSaleList==null}">
					<tr>
						<td colspan="9">
              					<div data-am-alert=""
									class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
											style="font-size: 34px;"></i> <span class="vam ml10">还没有会员商品！</span></big>
									</center>
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
</body>
</html>
