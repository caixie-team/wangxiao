<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>用户等级规则列表</title>

<script type="text/javascript">
	function checkExp(lv){//验证经验值
		var exp=$("#"+lv+"exp").val();//当前填写经验
		var previousExp=$("#"+(lv-1)+"exp").val();//上一级经验
		if(parseInt(exp)<=parseInt(previousExp)){
			$("#"+lv+"Error").html("*必须大于上一等级经验");
		}else{
			$("#"+lv+"Error").html("");
		}
	}
	function checkTitle(lv){//验证头衔
		var title=$("#"+lv+"Title").val();
		var length=title.replace(/[^\x00-\xff]/g,"zh").length;
		if(title==''&&lv<10){
			$("#"+lv+"TitleError").html("等级头衔不能为空");
		}else if(length>12){
			$("#"+lv+"TitleError").html("*不能超过12个字符");
		}else{
			$("#"+lv+"TitleError").html("");
		}
	}
	function userLevelSubmit(){
		var flag=false;
		var levelElements=document.getElementsByName("errorClass");//获得所有exp错误判断
		for(var i=0;i<levelElements.length;i++){
			if(levelElements[i].innerHTML!=""){
				flag=true;
				break;
			}
		}
		var titleElements=document.getElementsByName("titleErrorClass");//获得所有title错误判断
		for(var i=0;i<titleElements.length;i++){
			if(titleElements[i].innerHTML!=""){
				flag=true;
				break;
			}
		}
		if(flag){
			dialogFun('积分管理','请检查页面错误',0);
			return;
		} else {
			$.ajax({
				url:"${ctx}/admin/user/levelupdate",
				data:$("#searchForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.success){
						dialogFun("积分管理",result.message,5,window.location.href);
						
					}else{
						dialogFun("积分管理",result.message,0);
					}
				}
			});
		}
	}
</script>
</head>
<body  >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">用户等级规则管理</strong> / <small>等级规则列表</small>
		</div>
	</div>
	<hr/>
	<div class="mt20">
		<form action="?" name="searchForm" id="searchForm" method="post">
			<div class="mt20">
				<span style="color: red">说明：<br>
				1.用户等级规则数至少为9个，最大12个。即最少9个等级，最大12个等级</br>
				2.设置下一等级所需经验必须大于当前等级经验
				</span>
			</div>
			<div class="mt20">
				<div classs="am-scrollable-horizontal am-scrollable-vertical">
					<table class="am-table am-table-bordered am-table-striped am-text-nowrap" >
						<thead>
							<tr>
								<th width="5%"><span>等級</span></th>
								<th width="55%" align="left"><span>等級头衔(<font color="red">*最多12个字符，英文占一个，中文两个，不能写入符号</font>)</span></th>
								<th width="40%" align="left"><span>所需经验(<font color="red">*正整数</font>)</span></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${userLevelist.size()>0}">
								<c:forEach  items="${userLevelist}" var="level" varStatus="index">
									<input class="am-form-field" type="hidden" name="userLevel[${index.index }].id" value="${level.id}" />
									<input class="am-form-field" type="hidden" name="userLevel[${index.index }].level" value="${level.level}" />
									<tr>
										<c:choose>
											<c:when test="${index.index==0}">
												<td class="am-text-danger">LV${index.index+1}</td>
												<td align="left"><input class="am-form-field" name="userLevel[${index.index }].title" type="text" value="${level.title}"/></td>
												<td align="left"><input class="am-form-field" name="userLevel[${index.index }].exp" type="text" value="${level.exp}" readonly="readonly"/><span style="color:red">*用户初始经验为0，LV1经验不可编辑</span></td>
											</c:when>
											<c:otherwise>
												<td class="am-text-danger">LV${index.index+1}</td>
												<td align="left"><input class="am-form-field" name="userLevel[${index.index }].title" type="text" value="${level.title}" onkeyup="checkTitle(${index.index+1})" id="${index.index+1}Title"/><span style="color:red" name="titleErrorClass" id="${index.index+1}TitleError"></span></td>
												<td align="left"><input class="am-form-field" name="userLevel[${index.index }].exp" type="text" value="${level.exp!=0?level.exp:''}" onkeyup="checkExp(${index.index+1})" id="${index.index+1}exp"/><span style="color:red" name="errorClass" id="${index.index+1}Error"></span></td>
											</c:otherwise>
									</c:choose>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="3" align="center">
										<button type="button" class="am-btn am-btn-danger" onclick="userLevelSubmit()">保&nbsp;存</button>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
	 		</div>
		</form>
	</div>
</body>
</html>
