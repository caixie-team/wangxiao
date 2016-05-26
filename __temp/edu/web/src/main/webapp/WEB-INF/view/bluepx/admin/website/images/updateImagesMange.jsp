<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改图片管理</title>
<script type="text/javascript">
function updateImagesMangeSubmit(){
	var bz=1;
	var websiteImageMangetype=$("#websiteImageMangetype").val();
	var websiteImageMangeimage_key=$("#websiteImageMangeimage_key").val();
	if(websiteImageMangetype==null ||$.trim(websiteImageMangetype)=='' ){
		alert("类型不能为空")
		return false;
	}
	if(websiteImageMangeimage_key==null ||$.trim(websiteImageMangeimage_key)=='' ){
		alert("图片key不能为空")
		return false;
	}
	$.ajax({
		url:'${ctx}/admin/website/checkImagesMange',
		data:{"imagekey":websiteImageMangeimage_key},
		type:'post',
		dataType:'json',
	    async:false,  
		success:function(result){
	if(result.message=="success"){
	 }else if(result.message=="fail"){
		alert("相同key值")
		bz=2;
	}else {
		alert("系统错误")
		bz=2;
	}
		}});
	if(bz==1){
		$("#updateImagesMange").submit();
	}else{
		return ;
	}
	
}
</script> 
</head>
<body>
		<form action="${ctx}/admin/website/updateImagesMange" method="post" id="updateImagesMange">
			<input type="hidden" name="websiteImageMange.id" value="${websiteImageMange.id}" /> 
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>图片类型管理</span> &gt; <span>修改图片类型</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;图片类别</td>
							<td><input type="text" name="websiteImageMange.type" class="{required:true}" id="websiteImageMangetype" value="${websiteImageMange.type}" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>图片key值</td>
							<td width="80%"><input type="text" name="websiteImageMange.image_key" style="width: 300px;" id="websiteImageMangeimage_key" class="{required:true}" value="${websiteImageMange.image_key}" /></td>
						</tr>
					
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:updateImagesMangeSubmit()">提 交</a> <a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
