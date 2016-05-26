<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>

<script type="text/javascript">
	function updateStatus(id,status){
		$.ajax({
			url:"${cxt}/admin/membertype/status",
			type:"post",
			data:{"memberType.id":id,"memberType.status":status},
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					if(status==0){
						alert("启用成功");
					}else if(status==1){
						alert("停用成功");
					}
					window.location.reload();
				}
			}
		});
			
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>会员类型管理</span> &gt; <span>会员类型列表</span> </h4>
	</div>
			<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<font style="color: red">系统管理-->网站信息管理--><a href="/admin/websiteProfile/sale">售卖方式修改</a>中可以切换会员使用的模式：<br/>
                                        1.独立模式：每种会员只能看自己的课程 ，黄金会员只能看课程中设置为黄金会员的课程<br/> 2.阶梯模式：高级会员可以看低级会员的课程 <br/>如：课程设置为黄金会员可观看，开通了白金和钻石会员也能观看,钻石>白金>黄金
                                        <br/>即：钻石会员可以观看钻石+白金+黄金的所有课程，白金可以观看白金+黄金的所有课程，黄金可以观看黄金会员的所有课程
                                        <br/>会员等级由高到低(以Id)， 3>2>1
                                    </font>
								</li>
							</ul>
							
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn"></p>
						<p class="fr c_666">
						</p>
					</div>
				</caption>
				
				<thead> 	 	 	
					<tr> 	 	 	 	 	
						<th><span>ID</span></th>
                        <th><span>名称</span></th>
                        <th><span>状态</span></th>
                        <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${memberTypeList.size()>0}">
				<c:forEach  items="${memberTypeList}" var="memberType">
					<tr>
						<td>${memberType.id }</td>
						<td>${memberType.title }</td>
						<td>
							<c:if test="${memberType.status==0}">正常</c:if>
							<c:if test="${memberType.status==1}">停用</c:if>
						</td>
						<td>
							<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/membertype/doupdate/${memberType.id}" >修改</a>
							<c:if test="${memberType.status==0}">
								<a class="ml10 btn smallbtn btn-y" href="javascript:updateStatus(${memberType.id},1)">停用</a>
							</c:if>
							<c:if test="${memberType.status==1}">
								<a class="ml10 btn smallbtn btn-y" href="javascript:updateStatus(${memberType.id},0)">启用</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${memberTypeList.size()==0||memberTypeList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有会员类型！</span>
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			</form>
			
		</div><!-- /commonWrap -->
	</div>
<!-- 内容 结束 -->
</body>
</html>
