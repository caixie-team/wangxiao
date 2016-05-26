<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>选择学员列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}" charset="UTF-8"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>

<script type="text/javascript">
function submitSearch(){
	$("#searchForm").prop("action","${ctx}/admin/user/select_userlist/${type}");
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function clean(){
	$("#userId,#useremail,#mobile,#startDate,#endDate,#courseName").val("");
}

//关闭窗口
function quxiao() {
	window.close();
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
		
		dialogFun("学员管理 ","请选择用户",0);
		return;
	}
	qstChecked.each(function() {
		toParentsValue($(this).val());
	});
	opener.addnewUserId(myArrayMoveStock);
	quxiao();
}
// 把选中用户一条记录放到数组中
function toParentsValue(obj) {
	myArrayMoveStock.push(obj);
}


KindEditor.ready(function(K) {
	window.EditorObject = K.create('textarea[id="message"]', {
			resizeType  : 1,
	       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
	       allowPreviewEmoticons : false,
	       allowUpload : true,//允许上传
	       syncType : 'auto',
	       urlType : 'domain',//absolute
	       newlineTag :'br',//回车换行br|p
	       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
	       allowFileManager : false,
	       afterBlur:function(){EditorObject.sync();}, 
	       items : ['emoticons']
	});
});

//发送
function sendmessage(){
	var content = $("#message").val();
	if(content==null||content.trim()==""){
		
		dialogFun("学员管理 ","请填写消息内容在发送",0);
		return false;
	}
	var listSize="${list.size()}";
	if(listSize==0||listSize==null){
		
		dialogFun("学员管理 ","没有符合条件的会员,请重新查询",0);
		return false;
	}
	var totalSize="${page.totalResultSize}";
	if(totalSize>1000){
		
		dialogFun("学员管理 ","每次发送条数不能超过1000",0);
		return false;
	}
	dialogFun("学员管理 ","当前已选择"+totalSize+"名学员，确认发送吗？",2,"javascript:sendmessages()");
	
	
	
		
	
}

function sendmessages(){
	var content = $("#message").val();
	var listSize="${list.size()}";
	var totalSize="${page.totalResultSize}";
	$.ajax({
        url:"${ctx}/admin/letter/sendSystemInfoByCusIds",
        type:"post",
        data:{"messsageContent":content,
       	"user.email":$("#useremail").val(),
        	"user.id":$("#userId").val(),
        	"user.mobile":$("#mobile").val(),
        	"user.startDate":$("#startDate").val(),
        	"user.endDate":$("#endDate").val(),
        	"page.currentPage":$("#pageCurrentPage").val(),
        	"user.courseName":$("#courseName").val()
        },
        dataType:"json",
        success:function(result){
        	if(result.message=='success'){
        		KindEditor.html('#message', '');
        		 
        		dialogFun("学员管理 ","发送成功",0);
        	}else{
        		
        		dialogFun("学员管理 ",result.message,0);
        	}
        }
    });
}

</script>
</head>
<body  >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">学员管理</strong> / <small>学员列表</small></div>
</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form class="am-form" action="${ctx}/admin/user/select_userlist/${type}" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			
						<caption>
						<div class="mt20 am-padding admin-content-list">
							<div class="am-g am-margin-top am-u-sm-6 am-text-left">
								<div class="am-u-sm-4 am-text-right">
									学员ID：
								</div>
								<div class="am-u-sm-8 am-u-end">
								<input type="text" name="user.id" value="${user.id}" id="userId" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								</div>
							</div>
							<div class="am-g am-margin-top am-u-sm-6 am-text-left">
								<div class="am-u-sm-4 am-text-right">
									注册开始日期：
								</div>
								<div class="am-u-sm-8 am-u-end">
								<input type="text" name="user.startDate" value="${user.startDate}"  id="startDate" class="form-datetime-lang am-form-field"/>
								</div>
							</div>
							<div class="am-g am-margin-top am-u-sm-6 am-text-left">
								<div class="am-u-sm-4 am-text-right">
									手机号：
								</div>
								<div class="am-u-sm-8 am-u-end">
								<input type="text" name="user.mobile" value="${user.mobile}" id="mobile" />
								</div>
							</div>
								<%-- <div class="w50pre fl">
									<ul class="ddBar">
										<li>
										    <span class="ddTitle"><font>学员ID：</font></span>
											<input type="text" name="user.id" value="${user.id}" id="userId" />
										</li>
										<li>
											<span class="ddTitle"><font>注册开始日期：</font></span>
											<input type="text" name="user.startDate" value="${user.startDate}"  id="startDate" class="AS-inp"/>
										</li>
                                        <li>
                                            <span class="ddTitle"><font>手机号：</font></span>
                                            <input type="text" name="user.mobile" value="${user.mobile}" id="mobile" />
                                        </li>
									</ul>
									
								</div> --%>
								<div class="am-g am-margin-top am-u-sm-6 am-text-left">
								<div class="am-u-sm-4 am-text-right">
									邮箱：
								</div>
								<div class="am-u-sm-8 am-u-end">
								 <input type="text" name="user.email" value="${user.email}" id="useremail" />
								</div>
							</div>
							<div class="am-g am-margin-top am-u-sm-6 am-text-left">
								<div class="am-u-sm-4 am-text-right">
									注册结束日期：
								</div>
								<div class="am-u-sm-8 am-u-end">
								 <input type="text" name="user.endDate" value="${user.endDate}"  id="endDate" class="form-datetime-lang am-form-field"/>
								</div>
							</div>
							<div class="am-g am-margin-top am-u-sm-6 am-text-left">
								<div class="am-u-sm-4 am-text-right">
									课程名称：
								</div>
								<div class="am-u-sm-8 am-u-end">
								<input type="text" name="user.courseName" value="${user.courseName}" id="courseName" />
								</div>
							</div>
							
							<div class="am-g am-margin-top am-u-sm-12 am-text-left">
									<div class="am-u-sm-12 am-u-end am-text-center">
										<input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="submitSearch()"/>
                                    <input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
								</div>
								<div class="clearfix"></div>
							</div>
             		                  <%--  <div class="w50pre fl">
                                    <ul class="ddBar">
                                        <li>
                                            <span class="ddTitle"><font>邮箱：</font></span>
                                            <input type="text" name="user.email" value="${user.email}" id="useremail" />
                                        </li>
                                        <li>
                                            <span class="ddTitle"><font>注册结束日期：</font></span>
                                            <input type="text" name="user.endDate" value="${user.endDate}"  id="endDate" class="AS-inp"/>
                                        </li>
                                        <li>
                                        	<span class="ddTitle"><font>课程名称：</font></span>
                                            <input type="text" name="user.courseName" value="${user.courseName}" id="courseName" />
                                        	<input type="button"  class="am-btn am-btn-danger" value="查询" name="" onclick="submitSearch()"/>
                                            <input type="button"  class="am-btn am-btn-danger" value="清空" name="" onclick="clean()"/>
                                            <!-- <input type="button"  class="am-btn am-btn-danger" value="导出Excel" name="" onclick="userExcel()"/> -->
                                        </li>
                                    </ul>

                                </div> --%>
								<div class="clearfix"></div>
							</div>
						</caption>
						<table class="am-table am-table-bd am-table-striped admin-content-table">
						<thead>
							<tr>
								<th width="13%"><span>ID</span></th>
								<th width="8%"><span>昵称</span></th>
								<th><span>邮箱</span></th>
								<th><span>手机</span></th>
								<th><span>注册时间</span></th>
								<th><span>状态</span></th>
							</tr>
						</thead>
						
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="user" >
							<tr>
								<td>
									<c:if test="${type==1 }"><!--1 短信  -->
										<c:if test="${user.mobile!='' && user.mobile!=null && user.mobile.length()==11 }">
											<input type="checkbox" class="questionIds" id="${user.id }" value="${user.mobile }"/>
										</c:if>
									</c:if>
									<c:if test="${type==2}"><!--2邮箱  -->
										<c:if test="${user.email!='' && user.email!=null}">
											<input type="checkbox" class="questionIds" id="${user.id }" value="${user.email }"/>
										</c:if>
									</c:if>
									${user.id }
								</td>
								<td>${user.nickname }</td>
								<td>${user.email }</td>
								<td>${user.mobile }</td>
								<td><fmt:formatDate value="${user.createdate }" type="both"/></td>
								<td id="isavalible${user.id}">
								<c:if test="${user.isavalible==0 }">正常</c:if>
								<c:if test="${user.isavalible==1 }">禁用</c:if>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有学员信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
							<c:if test="${type==1||type==2 }"><!-- 发送短信 ， 发送邮箱 时选择用户 -->
								<tr class="">
									<td colspan="9"><a class="am-btn am-btn-warning" href="javascript:selectQstList();" title="确定" class="btn smallbtn btn-y">确定</a> <a class="am-btn am-btn-warning" href="javascript:quxiao();" title="取消" class="btn smallbtn btn-y">取消</a></td>
								</tr>
							</c:if>
						
					</table>
					</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
				<c:if test="${type==3 }"><!-- 发送系统消息  时选择用户 -->
					<div style="width:100%" class="commonWrap" >
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
							<tr>
								<td colspan="9" align="center"><font color="red">*</font>&nbsp;系统消息要发送的内容(每次发送条数不能超过1000条)</td>
							</tr>
							<tr>
								<td colspan="9" align="center">
									<textarea name="" style="width: 48%;height: 68px;" id="message"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="9" align="center">
									<a href="javascript:void(0)" title="批 量 发 送" onclick="sendmessage()" class="am-btn am-btn-danger">批 量 发 送</a>
								</td>
							</tr>
						</table>
					</div>
				</c:if>
			</div>
</body>
</html>
