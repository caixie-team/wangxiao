<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">

function show(id)
{
	var title=$("#"+id+"id").attr("title");
	if(title=="展开")
	{
		$("#"+id+"id").attr("title","收起");
		$("#"+id+"id").html("-&nbsp;&nbsp;");
		$('tr[name="'+id+'ids"]').show();
		$("#"+id+"show").text("收起");
	}
	else
	{
		$("#"+id+"id").attr("title","展开");
		$("#"+id+"id").html("+&nbsp;&nbsp;");
		$('tr[name="'+id+'ids"]').hide();
		$("#"+id+"show").text("展开");
	}
	
}
//冻结 解冻 type=1专业，type=2科目
function updateStatus(id,status,type){
	var str = "";
	if(type==1){
		
	if(status==1){str= "是否冻结该专业";}
	if(status==0){str= "是否恢复该专业";}
	if(confirm(str)){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/professional/updateStatus",
			data:{"professional.professionalId":id,"professional.status":status},
			async:false,
			success:function(result){
				if(result.success){
					if(status==1){
						$("#professionalS"+id).text("恢复");
						$("#professionalS"+id).attr("href","javascript:updateStatus("+id+",0,1)");
						alert("冻结成功！");
					}else{
						$("#professionalS"+id).text("冻结");
						$("#professionalS"+id).attr("href","javascript:updateStatus("+id+",1,1)");
						alert("恢复成功！");
					}
				}
			}
		});
	}
	}else{
		if(status==1){str= "是否冻结该科目";}
		if(status==0){str= "是否恢复该科目";}
		if(confirm(str)){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/professional/updateSubjectStatus",
				data:{"examSubject.subjectId":id,"examSubject.status":status},
				async:false,
				success:function(result){
					if(result.success){
						if(status==1){
							$("#subjectS"+id).text("恢复");
							$("#subjectS"+id).attr("href","javascript:updateStatus("+id+",0,2)");
							alert("冻结成功！");
						}else{
							$("#subjectS"+id).text("冻结");
							$("#subjectS"+id).attr("href","javascript:updateStatus("+id+",1,2)");
							alert("恢复成功！");
						}
					}
				}
			});
	}
	}
}
/* function longMenu(id)
{
	
	$("#"+id).attr("onclick","shortMenu('"+id+"')");
	$("#"+id).html("-&nbsp;&nbsp;");
	
}
function doDel(id)
{
	if(confirm("确认删除？")){
		$.ajax({
			url:"${ctx}/admin/helpMenu/del/"+id,  
			type: "post",
			dataType:"json",
			success:function(data){
				if(data.message=='true'){
					alert("删除菜单成功");
					window.location.reload();
				}
			}
		});
	}
		
} */
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>考试专业管理</span> &gt; <span>专业科目</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/" name="searchForm" id="searchForm" method="post">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<!-- <div class="capHead">
						<div class="clearfix">
							<div class="optionList">
								<font color="red">
						1.一级菜单下有子菜单时，该菜单不可删除。<br/>
					    2.在编辑中，二级菜单可以移动至其它一级菜单下。 </frot>
							</div>
						</div>
					</div> -->
					<div class="mt10 clearfix">
						<p class="fl czBtn"><span class="ml10"><a href="${cxt}/admin/professional/toAddProfessional"><em class="icon14 new">&nbsp;</em>添加专业</a></span></p>
						<p class="fr c_666"></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th><span>专业</span></th>
                        <th><span>排序</span></th>
                        <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${fn:length(professionalList)>0}">
				<c:forEach items="${professionalList}" var="professional" varStatus="index">
					<tr>
						<td style="background-color:#f3f3f3" width="30%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="${professional.professionalId}id" style="cursor: pointer;" onclick="javascript:show('${professional.professionalId}')" title="收起">
								-&nbsp;&nbsp;
							</span>
							${professional.professionalName}
							<font style="color: #999">（${fn:length(professional.subjectList)}个科目）</font>
						</td>
						<td style="background-color:#f3f3f3;color:#6e6e6e">${professional.sort}</td>
						<td  class="c_666 czBtn" align="center" width="30%" style="background-color:#f3f3f3;">
							<a class="ml10 btn smallbtn btn-y"  href="${ctx}/admin/professional/toUpdateProfessional/${professional.professionalId}">修改</a>
							<a class="ml10 btn smallbtn btn-y"  href="${ctx}/admin/professional/toAddExamSubejct/${professional.professionalId}">增加科目</a>
							<c:if test="${professional.status==0}">
							<a id="professionalS${professional.professionalId}" class="ml10 btn smallbtn btn-y"  href="javascript:updateStatus(${professional.professionalId},1,1)">冻结</a>
							</c:if>
							<c:if test="${professional.status==1}">
							<a id="professionalS${professional.professionalId}" class="ml10 btn smallbtn btn-y"  href="javascript:updateStatus(${professional.professionalId},0,1)">恢复</a>
							</c:if>
							<a id="${professional.professionalId}show" class="ml10 btn smallbtn btn-y"  href="javascript:show('${professional.professionalId}')">收起</a>
						</td>
						<c:if test="${fn:length(professional.subjectList)>0}">
						<c:forEach items="${professional.subjectList}" var="subject" varStatus="subjectIndex">
						<tr name="${subject.professionalId}ids">
						<td align="left" style="padding-left: 40px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${subject.subjectName}(ID:${subject.subjectId })</td>
						<td>${subject.sort }</td>
						<td>
							<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/professional/toUpdateSubject/${subject.subjectId}">修改</a>
							<c:if test="${subject.status==0}">
							<a id="subjectS${subject.subjectId }" class="ml10 btn smallbtn btn-y" href="javascript:updateStatus(${subject.subjectId},1,2)">冻结</a>
							</c:if>
							<c:if test="${subject.status==1}">
							<a id="subjectS${subject.subjectId }" class="ml10 btn smallbtn btn-y" href="javascript:updateStatus(${subject.subjectId},0,2)">恢复</a>
							</c:if>
						</td>
						</tr>
						</c:forEach>
						</c:if>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${professionalList.size()==0||professionalList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有专业！</span>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			</form>
		</div>
	</div>
</body>
</html>