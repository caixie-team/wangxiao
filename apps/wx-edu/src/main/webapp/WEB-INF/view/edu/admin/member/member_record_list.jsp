<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/center.css"/>
<title></title>
<script type="text/javascript">
	function cancel(){
		$("#email").val("");
		$("#memberType").val(0);
		$("#memberType").find('option').eq(0).attr('selected', true);
	}
	function updateMemberStatus(memId,type){
		var message="";
		if(type==1){//关闭
			message="确定关闭该用户会员吗?";
		}else{
			message="确定开启该用户会员吗";
		}
		dialogFun("会员开通管理 ",message,2,"javascript:updatemember("+memId+","+type+")");
	}
	function updatemember(memId,type){
			$.ajax({
				url:"${ctx}/admin/member/close",
				type:"post",
				data:{"memberRecord.id":memId,"memberRecord.status":type},
				dataType:"json",
				success:function(result){
					if(result.success){
						dialogFun("会员开通管理 ",result.message,5,"");
						closeFun();
					}else{
						dialogFun("会员开通管理 ",result.message,6,"");
						closeFun();
						return;
					}
				}
			})
	}
</script>

</head>
<body>
		<!-- 公共右侧样式 -->
		<div class="am-cf">
	      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">会员开通管理</strong> / <small>会员开通列表</small></div>
	    </div>
	    <hr/>
	    <form action="${ctx}/admin/memberrecord/memberrecords" name="searchForm" id="searchForm" method="post" class="am-form">
	    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	   <div class="mt20 am-padding admin-content-list">
	    	<div class="am-tab-panel am-fade am-active am-in">
			        <div class="am-g am-margin-top am-u-sm-5">
			            <div class="am-u-sm-4 am-text-right">
			            	<span class="ddTitle"><font>用户邮箱：</font></span>
			            </div>
			            <div class="am-u-sm-8">
			              <input type="text" name="memberRecordDTO.email" id="email" value="${memberRecordDTO.email}"/>
			            </div>
			        </div>
			        <div class="am-g am-margin-top am-u-sm-5">
			            <div class="am-u-sm-4 am-text-right">
			            	<span class="ddTitle"><font>会员类型：</font></span>
			            </div>
			            <div class="am-u-sm-8">
			              	<select name="memberRecordDTO.memberType" id="memberType" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
										<option value="0">--请选择--</option>
										<c:forEach items="${memberTypes }" var="memberType">
											<option value="${memberType.id }" <c:if test="${memberType.id==memberRecordDTO.memberType }">selected="selected"</c:if>>${memberType.title }</option>
										</c:forEach>
							</select>
			            </div>
			        </div>
			        <div class="mt20">
			    	<div class="am-g">
				      <div class="am-u-md-6">
				        <div class="am-btn-toolbar">
				          <div class="am-btn-group am-btn-group-xs">
                           &nbsp;
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
							   <th width="10%" class="th_center"><span>ID</span></th>
	                           <th class="th_center"><span>用户email</span></th>
	                           <th class="th_center"><span>会员类型</span></th>
	                           <th class="th_center"><span>到期时间</span></th>
	                           <th class="th_center"><span>首次开通时间</span></th>
	                           <th class="th_center"><span>状态</span></th>
	                           <th class="th_center"><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
				<c:if test="${memberRecordDTOs.size()>0}">
				<c:forEach  items="${memberRecordDTOs}" var="memberRecord" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${memberRecord.email }</td>
						<td>${memberRecord.memberTitle}</td>
						<td><fmt:formatDate value="${memberRecord.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${memberRecord.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
						<c:if test="${memberRecord.status==0}">
						正常
						</c:if>
						<c:if test="${memberRecord.status==1}">
						关闭
						</c:if>
						</td>
						<td>
						<c:if test="${memberRecord.status==0}">
						 <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="updateMemberStatus(${memberRecord.id},1)">
						 <span class="am-icon-pencil-square-o"></span> 
						 关闭
						 </button>
						</c:if>
						<c:if test="${memberRecord.status==1}">
						 <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="updateMemberStatus(${memberRecord.id},0)">
						 <span class="am-icon-pencil-square-o"></span> 
						 开启
						 </button>
						</c:if>
						 <button class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='${cxt}/admin/member/mrecordinfo/${memberRecord.id}'">
						 <span class="am-icon-search"></span> 
						 查看详情
						 </button>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${memberRecordDTOs.size()==0||memberRecordDTOs==null}">
					<tr>
						<td colspan="9">
              					<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam"
											style="font-size: 34px;"></i> <span class="vam ml10">还没会员开通！</span></big>
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
