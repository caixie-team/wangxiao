<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>导航列表</title>
<script type="text/javascript">
	function cancel(){
		$("#type").val("");
		$("#type").find('option').eq(0).attr('selected', true);
	}
	function delNavigate(id){
		dialogFun("提示","确定删除吗？",2,"javascript:delAction('"+id+"')");
	} 
	function freezeNavigate(id,status){
		$.ajax({
			url:"${cxt}/admin/website/freezeNavigate",
			type:"post",
			data:{"websiteNavigate.id":id,"websiteNavigate.status":status},
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					if(status==0){
						dialogFun("导航管理","解冻成功",5,"");
					}else{
						dialogFun("导航管理","冻结成功",5,"");
					}
				}
			}
		});
	}
	function checkAll(box){
		$("input[name='ids']").prop("checked",box.checked);
	}
	function delNavigatebatch(){
		var ids="";
		var check=false;
		var navigateEle=$("input[name='ids']:checked");
		for(var i=0;i<navigateEle.length;i++){
			ids+=navigateEle[i].value;
			if(i!=navigateEle.length-1){
				ids+=",";
			}
			check=true;
		}
		if(!check){
			dialogFun("导航管理","请选择要删除的导航",0);
			return;
		}
		dialogFun("提示","确定删除吗？",2,"javascript:delActions('"+ids+"')");
		
	}
	function delActions(ids){
		$.ajax({
			url : baselocation+"/admin/website/delNavigatebatch",
			data : {"ids":ids},
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.success){
					window.location.reload();
				}else{
					dialogFun("导航管理","系统繁忙",0);
				}
			}
		})
	}
	function delAction(id){
		$.ajax({
			url:"${cxt}/admin/website/delNavigate/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					window.location.reload();
				}
			}
		});
	}
</script>
</head>
<body>
<!-- 全局公共右内容区  开始 -->
	<!-- 公共右侧样式 -->
		<div class="am-cf">
	      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">导航管理</strong> / <small>导航列表</small></div>
	    </div>
	    <hr>
	<!-- 公共右侧标题样式 结束-->
	<form action="${ctx}/admin/website/navigates" name="searchForm" id="searchForm" method="post">
	    <div class="mt20 admin-content-list am-padding">
	    	<div class="am-tab-panel am-fade am-active am-in">
		 		<div class="am-g am-margin-top am-u-sm-5">
		            <div class="am-u-sm-4 am-text-right">
		             	 导航类型
		            </div>
		            <div class="am-u-sm-8 ">
						<select name="websiteNavigate.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
							<option value="">--请选择--</option>
							<option value="INDEX" <c:if test="${websiteNavigate.type=='INDEX'}">selected="selected"</c:if>>首页</option>
							<option value="USER" <c:if test="${websiteNavigate.type=='USER'}">selected="selected"</c:if>>个人中心</option>
							<option value="FRIENDLINK" <c:if test="${websiteNavigate.type=='FRINEDLINK'}">selected="selected"</c:if>>尾部友链</option>
							<option value="TAB" <c:if test="${websiteNavigate.type=='TAB'}">selected="selected"</c:if>>尾部标签</option>
						</select>
					</div>
		        </div>
				<div class="am-g am-margin-top am-u-sm-5">
					<button type="submit" class="am-btn am-btn-warning" >
		            	<span class="am-icon-search"></span> 搜索
		            </button>
		            <button type="button" class="am-btn am-btn-danger" onclick="cancel()">
		            	清空
		            </button>
		        </div>
		        <div class="mt20 clear"></div>
		     </div>
	    </div>
	    <div class="mt20">
	    	<div class="am-g">
		      <div class="am-u-md-6">
		        <div class="am-btn-toolbar">
		          <div class="am-btn-group am-btn-group-xs">
		            <button class="am-btn am-btn-success" type="button" onclick="window.location.href='${cxt}/admin/website/doAddNavigates'" title="新增"><span class="am-icon-plus"></span> 新增</button>
		            <button class="am-btn am-btn-success" type="button" onclick="delNavigatebatch()" title="删除"><span class="am-icon-trash-o"></span> 删除</button>
		          </div>
		        </div>
		      </div>
		    </div>
	    </div>
	  </form>
	<!-- 表格样式 二   开始-->
   	<div class="am-g">
      <div class="mt20">
      	<div class="am-scrollable-horizontal am-scrollable-vertical">
          <table class="am-table am-table-striped am-table-hover table-main am-scrollable-vertical">
				<thead>
					<tr>
						<th width="5%">
						<label class="am-checkbox">
						<input type="checkbox" data-am-ucheck onclick="checkAll(this)"/>
						</label>
						</th>
						<th width="5%">
						ID
						</th>
						<th width="13%">名称</th>
                           <th width="25%">跳转地址</th>
                           <th width="12%">在新页面打开</th>
                           <th width="10%">类型</th>
                           <th width="5%">排序</th>
                           <th width="5%">状态</th>
                           <th width="20%">操作</th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${websiteNavigates.size()>0}">
				<c:forEach  items="${websiteNavigates}" var="navigate" >
					<tr>
						<td>
						<label class="am-checkbox">
						<input type="checkbox" data-am-ucheck name="ids" value="${navigate.id }"/>
						</label>
						</td>
						<td>${navigate.id }</td>
						<td>${navigate.name }</td>
						<td>${navigate.url }</td>
						<td>
							<c:if test="${navigate.newPage==0 }">是</c:if>
							<c:if test="${navigate.newPage==1 }">否</c:if>
						</td>
						<td>
							<c:if test="${navigate.type=='INDEX' }">首页</c:if>
							<c:if test="${navigate.type=='USER' }">个人中心</c:if>
							<c:if test="${navigate.type=='FRIENDLINK' }">尾部友链</c:if>
							<c:if test="${navigate.type=='TAB' }">尾部标签</c:if>
						</td>
						<td>${navigate.orderNum }</td>
						<td>
							<c:if test="${navigate.status==0 }">正常</c:if>
							<c:if test="${navigate.status==1 }">冻结</c:if>
						</td>
						<td>
							<div data-am-dropdown="" class="am-dropdown">
								<button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
								<ul class="am-dropdown-content">
									<li><a href="${cxt}/admin/website/doUpdateNavigate/${navigate.id}">编辑</a></li>
									<c:if test="${navigate.status==0}">
										<li><a href="#" onclick="freezeNavigate(${navigate.id},1)">冻结</a></li>
									</c:if>
									<c:if test="${navigate.status==1}">
										<li><a href="#" onclick="freezeNavigate(${navigate.id},0)">解冻</a></li>
									</c:if>
									<li><a href="#" onclick="delNavigate(${navigate.id})">删除</a></li>
								</ul>
							</div>


                         </td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${websiteNavigates.size()==0||websiteNavigates==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有导航！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			</div>
		</div><!-- /commonWrap -->
	</div>
</body>
</html>