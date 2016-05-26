<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/edu/css/u_mygroupuser.css" />		
<title>我的任务</title>
<script type="text/javascript">
 function clickisRepeat(examIds,id,userGroupId,isRepeat){
  if(iftime()){
	$.ajax({
		url:'${ctx}/uc/myArrangeForWebisRepeat',
		type:'post',
		data:{'isRepeat':isRepeat,'id':id},
		dataType:'json',
		success:function(result){
			if(result.success==false){
				alert("此任务不可重复做答");
			}
			if(result.success){
				window.location.href="${ctxexam}/paper/toExamPaper/"+examIds+"?taskId="+id+"&groupId="+userGroupId+"&ifarrange=1";
			} 
		}
	});
	 }
} 
 
 Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
}

function iftime(){
	 var begin_time = $("#begin_time").val();
	 var end_time = $("#end_time").val();
	 var time1 = new Date().Format("yyyy-MM-dd hh:mm:ss");  
	 if(begin_time>time1){
		   alert("任务未开始");
		   return false;
		 }
	 if(end_time<time1){
	   alert("任务已结束");
	   return false;
	 }
	 return true;
}

 
</script>
<style type="text/css">
.t{
  width: 20%;
  text-align:center;
}
</style>
</head>
<body>
<article class="u-m-c-w837 u-m-center">
	<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt"> <li class="current"><a class="vam" title="我的任务" href="javascript:void(0)">我的任务</a></li> </ul>
				<div class="clear"></div>
	</section>
	<div>
		<div class="u-m-sub-head" style="padding-left: 0px;">
					<ol class="clearfix tac c-333 fsize14"  >
						<li style="width: 20%;">任务名称</li>
						<li style="width: 20%">任务开始时间</li>
						<li style="width: 20%">任务结束时间</li>
						<li style="width: 20%">可否重复进行</li>
						<li style="width: 20%;">操作</li>
					</ol>
		</div>
			<table style="width: 836px;"  border="0" cellspacing="0" cellpadding="0" class="u-order-table">
			<c:forEach items="${tasks}" var="tasks">
				<tr>
					<td class="t">${tasks.name }</td>
					<td id = "begin_time" class="t"><fmt:formatDate value="${tasks.beginTime }"
							pattern="yyyy-MM-dd：HH:mm:ss" /></td>
					<td id="end_time" class="t"><fmt:formatDate value="${tasks.endTime }"
							pattern="yyyy-MM-dd：HH:mm:ss" /></td>	
					<c:if test="${tasks.isRepeat==0}">
					<td class="t">不可重复</td>
					</c:if>
					<c:if test="${tasks.isRepeat==1}">
					<td class="t">可重复</td>
					</c:if>
					<td class="t"><a onclick="clickisRepeat(${tasks.examIds},${tasks.id},${tasks.userGroupId},${tasks.isRepeat})" href="javascript:void(0)">开始任务</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</article>
</body>

</html>