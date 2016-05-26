<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>图片管理列表</title>
<script type="text/javascript">
function addImagesMange(){
   var imagekey=$("#imagekey").val();
   var imagetype=$("#imagetype").val();
   var bz=1;
	$.ajax({
		url:'${ctx}/admin/website/checkImagesMange',
		data:{"imagekey":imagekey},
		type:'post',
		dataType:'json',
	    async:false,  
		success:function(result){
		if(result.message=="success"){
			$.ajax({
				url:'${ctx}/admin/website/addImagesMange',
				data:{"websiteImageMange.image_key":imagekey,"websiteImageMange.type":imagetype},
				type:'post',
				dataType:'json',
			    async:false,  
				success:function(result){
				if(result.message="success"){
					alert("添加成功")
					window.location.href="${ctx}/admin/website/imagesMange"
				}else if(result.message="fail"){
					alert("系统异常")
				}else{
					alert("系统异常")
				}
				}
				});
		}
		else if(result.message=="fail"){	
			alert("已有相同的key值")
		}else{
			alert("系统异常")
		}
		}
		});
  }
  function deleteImagesMange(id){
	  if(confirm("确定要删除么")){
	  window.location.href="${ctx}/admin/website/deleteImagesMange/"+id;
	  }
  }

</script>
</head>
<body  >
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>图片类型管理</span> &gt; <span>图片类型列表</span> </h4>
				<input type="hidden" name="imagekey" id="imagekey" value="The default imagetype"></input>
		   <input type="hidden" name="imagetype" id="imagetype" value="图片类型"></input>
				
</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="mt10 clearfix">
								<p class="fl czBtn">
								 <span><a href="javascript:void(0)"  onclick=" addImagesMange()"   title="新建图片"><em class="icon14 new">&nbsp;</em>新建图片类型</a></span>
								</p>
							</div> 
						</caption>
						<thead>
							<tr>
								<th width="10%"><span>ID</span></th>
								<th width="25%"><span>类型</span></th>
								<th width="25%"><span>key值</span></th>
								<th width="20%"><span>类型</span></th>
								<th width="20%"><span>操作</span></th>
						
							
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${websiteImageMangeList.size()>0}">
						<c:forEach  items="${websiteImageMangeList}" var="tc" >
							<tr id="rem${tc.id }">
								<td>${tc.id }</td>
								<td>${tc.type }</td>
								<td>${tc.image_key }</td>
								<td><fmt:formatDate type="both" value="${tc.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/website/toUpdateImagesMange/${tc.image_key}"> 修改</a>
								<a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:void(0)" onclick="deleteImagesMange(${tc.id})">删除</a>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${websiteImageMangeList.size()==0||websiteImageMangeList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有图片信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div><!-- /commonWrap -->
			</div>
<!-- 内容 结束 -->

</body>
</html>
