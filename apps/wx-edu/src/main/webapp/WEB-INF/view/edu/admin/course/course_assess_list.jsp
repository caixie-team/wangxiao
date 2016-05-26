<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>课程评论列表</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
	<script type="text/javascript">
		function submitSearch(){
			if(isEmpty($("#startDate").val())){
				dialogFun("课程评论列表","请选择开始日期",0);
				return;
			}
			if(isEmpty($("#endDate").val())){
				dialogFun("课程评论列表","请选择结束日期",0);
				return;
			}
			$("#pageCurrentPage").val(1);
			$("#searchForm").submit();
		}
		function updateIsavalible(id,isavalible){
			var str = "";
			if(isavalible==1){str= "是否隐藏该条信息";}
			if(isavalible==0){str= "是否显示该条信息";}
			dialogFun("课程评论列表",str,2,"javascript:updateIsavalibleAjax("+id+","+isavalible+")");
		}
		function updateIsavalibleAjax(id,isavalible){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/courseAssess/update",
				data:{"courseAssess.id":id,"courseAssess.status":isavalible},
				async:false,
				success:function(result){
					if(result.success){
						if(isavalible==1){
							$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",0)");
							$(".attr"+id).html("<span class='am-icon-pencil-square-o'></span>显示");
							$(".attr"+id).attr("title","显示");
							$("#isavalible"+id).html("隐藏");
							dialogFun("课程评论列表 ","隐藏成功",4);
							closeFun();
						}else{
							$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",1)");
							$(".attr"+id).html("<span class='am-icon-pencil-square-o'></span>隐藏");
							$(".attr"+id).attr("title","隐藏");
							$("#isavalible"+id).html("显示");
							dialogFun("课程评论列表 ","显示成功",4);
							closeFun();
						}
					}
				}
			});
		}
		function clean(){
			$("#nickname,#useremail,#courseName,#pointName").val("");
			//清空时间
			$("input[name='startDate']:first").attr("value", "");
			$("input[name='endDate']:first").attr("value", "");
		}
		function delCourseAssessbatch(){//删除资讯
			var artIds=document.getElementsByName("courseAssessIds");
			var str="";
			var checked=true;
			$(artIds).each(function(){
				if($(this).prop("checked")){
					str+=this.value+",";
					checked=false;
				}
			});
			if(checked){
				dialogFun("课程评论列表 ","请至少选择一条信息",0);
				return;
			}
			strId=str.substring(0,str.length-1);
			dialogFun("课程评论列表","确定删除吗",2,"javascript:delCourseAssessbatchAjax('"+strId+"')");
		}
		function delCourseAssessbatchAjax(strId){
			$.ajax({
				url:"${ctx}/admin/courseAssess/del",
				data:{"courseAssessIds":strId},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						window.location.reload();
					}else{
						dialogFun("课程评论列表","系统繁忙稍后重试",6,"");
						return;
					}
				}
			});
		}
		function allCheck(cb) {
			$("input[name=courseAssessIds]").prop('checked', cb.checked);
		}
	</script>
</head>
<body  >
<form action="${ctx}/admin/courseAssess/list" name="searchForm" id="searchForm" method="post"  class="am-form">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<div class="am-cf">
			<div class="am-fl am-cf">
				<strong class="am-text-primary am-text-lg">课程评论管理</strong> / <small>课程评论列表</small>
			</div>
		</div>
		<hr/>
		<div class="mt20 am-padding admin-content-list">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					用户名：
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm"  id="nickname" type="text" name="queryCourseAssess.nickname" value="${queryCourseAssess.nickname}" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					课程名：
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm" name="queryCourseAssess.courseName" value="${queryCourseAssess.courseName}" id="courseName"  />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					开始日期
				</div>
				<div class="am-u-sm-8 am-u-end">
				<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" id="startDate" name="startDate" readonly="readonly" class="am-input-sm"  value="${queryCourseAssess.startDate}" />
				</div>
			</div>
			<div class="mt20 clear"></div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					邮箱：
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text" class="am-input-sm"   name="queryCourseAssess.email" value="${queryCourseAssess.email}" id="useremail"  />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					视频名：
				</div>
				<div class="am-u-sm-8 am-u-end">
					<input type="text"  class="am-input-sm"  name="queryCourseAssess.pointName" value="${queryCourseAssess.pointName}" id="pointName" />
				</div>
			</div>
			<div class="am-g am-margin-top am-u-sm-4 am-text-left">
				<div class="am-u-sm-4 am-text-right">
					结束日期
				</div>
				<div class="am-u-sm-8 am-u-end">
				<input type="text" data-am-datepicker="{format: 'yyyy-mm-dd'}" id="endDate"  name="endDate"  readonly="readonly" class="am-input-sm" value="${queryCourseAssess.endDate}"  />
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
		         <button class="am-btn am-btn-success" onclick="delCourseAssessbatch()" type="button" title="删除">
				<span class="am-icon-trash-o"></span>
				删除
				</button>
		          </div>
		        </div>
		      </div>
		      <div class="am-u-sm-12 am-u-md-3">
		        <div class="am-input-group am-input-group-sm">
		          <span class="am-input-group-btn">
		           <button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">
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
			<table class="am-table am-table-bordered am-table-striped am-text-nowrap">
			<thead>
				<tr>
				    <th>
		                <label class="am-checkbox">
		                <input  type="checkbox" data-am-ucheck onclick="allCheck(this)"/><!-- name="courseAssessIds" -->
		                </label>
		            </th>
					<th><span>&nbsp;ID</span></th>
					<!-- <th  width="30%"><span>试题内容</span></th> -->
					<th><span>学员昵称</span></th>
					<th><span>学员邮箱</span></th>
					<!-- <th><span>学员年龄</span></th> -->
					<th><span>课程名称</span></th>
					<th><span>视频名称</span></th>
					<th><span>评论内容</span></th>
					<!-- <th><span>学员学校</span></th> -->
					<th><span>评论时间</span></th>
					<th><span>状态</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${courseAssessList.size()>0}">
			<c:forEach  items="${courseAssessList}" var="cou" >
				<tr>
					<td>
                      <label class="am-checkbox">
                       	<input type="checkbox"  data-am-ucheck name="courseAssessIds"value="${cou.id}"/>
					  </label>
                     </td>
					<td>${cou.id }</td>
					<td>${cou.nickname }</td>
					<td>${cou.email }</td>
					<td>${cou.courseName }</td>
					<td>${cou.pointName }</td>
					<td>
					${fn:substring(cou.shortContent,0,50)}


					</td>
					<td><fmt:formatDate value="${cou.createTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="isavalible${cou.id}">
					<c:if test="${cou.status==0 }">显示</c:if>
					<c:if test="${cou.status==1 }">隐藏</c:if>
					</td>
					<td  class="c_666 czBtn" >
					<c:if test="${cou.status==0}">
				        <a class="am-btn am-btn-default am-btn-xs am-text-secondary attr${cou.id}" title="隐藏" href="javascript:updateIsavalible('${cou.id}',1)"><span class="am-icon-pencil-square-o"></span>隐藏</a>
				     </c:if>
				      <c:if test="${cou.status==1 }">
				        <a class="am-btn am-btn-default am-btn-xs am-text-secondary attr${cou.id}" title="显示" href="javascript:updateIsavalible('${cou.id}',0)"><span class="am-icon-pencil-square-o"></span>显示</a>
				     </c:if> 
					 <a class="am-btn am-btn-default am-btn-xs am-text-secondary" title="查看" href="${ctx}/admin/courseAssess/info/${cou.id}"><span class="am-icon-copy"></span>查看</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${courseAssessList.size()==0||courseAssessList==null}">
					<tr>
					<td align="center" colspan="16">
						<div class="am-alert am-alert-secondary mt20 mb50" data-am-alert="">
						<center>
						<big>
							<i class="am-icon-frown-o vam" style="font-size: 34px;"></i>
								<span class="vam ml10">还没有课程评论信息！</span>
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
</form>
</body>
</html>
