<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>员工列表</title>
<script type="text/javascript">
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	
}
//存放数据的数组
var myArrayMoveStock = new Array();
//将小页面被选中的入库明细信息带到大页面
function selectQstList() {
	if (initArray()) {
		//调用父页面的方法
		window.close();
	}
}

function initArray() {
	var qstChecked = $(".questionIds:checked");
	if (qstChecked.length == 0) {
		alert("请选择员工");
		return;
	}
	qstChecked.each(function() {
		toParentsValue($(this).val());
	});
	opener.addnewUserId(myArrayMoveStock);
	quxiao();
}
//把选中的一条记录放到数组中
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}
function quxiao() {
	window.close();
}
function allCheck(cb) {
    $("input[name=checkbox]").prop('checked', cb.checked);
}
</script>
</head>
<body>
	<form class="am-form" action="${ctx}/admin/user/groupList" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	    <div class="mt20">
	    	<div class="am-tab-panel am-fade am-active am-in">
		        <div class="am-g am-margin-top am-u-sm-4">
		            <div class="am-u-sm-4 am-text-right">
		            	员工ID
		            </div>
		            <div class="am-u-sm-8">
		              <input type="text" class="am-input-sm" name="user.id" id="id" value="${user.id }" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
		            </div>
		        </div>
				<div class="am-g am-margin-top am-u-sm-6">
		            <div class="am-u-sm-4 am-text-right">
		              	员工名称
		            </div>
		            <div class="am-u-sm-8">
		            	<input type="text" class="am-input-sm" name="user.nickname" id="nickname" value="${user.nickname}"/>
		            </div>
		        </div>
		        <div class="am-g am-margin-top am-u-sm-2 am-text-left">
		            <div class="am-u-sm-6 am-u-end  am-text-center">
		               <button type="button" class="am-btn am-btn-warning" onclick="submitSearch()">
			            	<span class="am-icon-search"></span> 搜索
			            </button>
		            </div>
		        </div>
				<div class="mt20 clear"></div>
		     </div>
	    </div>
	</form>
	<div class="am-g">
		<div class="mt20">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
					<tr>
						<th width="20%">
							<label class="am-checkbox">
								<input name="checkbox" type="checkbox" data-am-ucheck onclick="allCheck(this)"/>
								学员ID
							</label>
						</th>
						<th width="20%">员工名称</th>
						<th width="20%">邮箱</th>
						<th width="20%">手机</th>
						<th width="20%">注册时间</th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${groupUsers.size()>0}">
						<c:forEach items="${groupUsers}" var="groupUsers">
							<tr id="rem${groupUsers.id }">
								<td>
									<label class="am-checkbox">
										<input data-am-ucheck type="${teacher.checkboxFalg=='radio'?'radio':'checkbox' }" id="${groupUsers.id }" name="checkbox" value="${groupUsers.id }" class="questionIds" />
										${groupUsers.id }
									</label>
								</td>
								<td>
									${groupUsers.nickname }
								</td>
								<td>
									${groupUsers.email }
								</td>
								<td>
									${groupUsers.mobile }
								</td>
								<td>
									<fmt:formatDate value="${groupUsers.createdate }" pattern="yyyy-MM-dd" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty groupUsers }">
						<tr>
							<td colspan="16">
								<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50">
									<center>
										<big> <i class="am-icon-frown-o vam" style="font-size: 34px;"></i> <span class="vam ml10">还没有员工信息！</span></big>
									</center>
								</div>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</div>
	</div>
	<div class="mt20">
		<div class="am-tab-panel am-fade am-active am-in">
			<div class="am-g am-margin-top am-u-sm-12">
				<div class="am-u-sm-12 am-text-center">
					<a href="javascript:selectQstList();" title="确定" class="am-btn am-btn-success">确定</a>
					<a href="javascript:quxiao();" title="取消" class="am-btn am-btn-danger">取消</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
