<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小组分类</title>
	<script type="text/javascript">
	function isExsit(){
		var disClassify=$('#groupList').val();
		$.ajax({
			url:"/admin/disgroup/queryDisGroupClassifyIsExist",
			data:{"disGroupClassify.name":disClassify},
			dataType:"json",
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					alert("该分类已存在");
				}else{
					return;
				}
			}
		});
	}
	function Valempty() {
		var sort =$("input[name='disGroupClassify.sort']").val().trim();
		if ($('#groupList').val().trim() == null || $('#groupList').val().trim() == '') {
			alert("不能输入空值");
			return false;
		}if(!/^[\u4E00-\u9FA5]+$/.test($('#groupList').val())){
			alert("请输入汉字");
			return;
		}else if(sort==''||sort==null){
			alert("排序值不能为空");
			return;
		}else if(!/^[0-9]*$/.test(sort)){
			alert("请输入数字");
			return;
		}else {
			$("#addDisGroupClassify").submit();
		} 
	}
	</script>
</head>

<body  >
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>小组管理</span> &gt; <span>添加小组分类</span> </h4>
			</div>
			
	<div class="mt20">
				<div class="commonWrap">
	<form action="${ctx}/admin/disgroup/addclassify" method="post" id="addDisGroupClassify">
	<input type="hidden" name="disGroupClassify.status" value="0">
		<table class="commonTab01" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<thead>
				<tr>
					<th align="left" colspan="2"><span> 添加小组分类 <tt
								class="c_666 ml20 fsize12">
								（ <font color="red">*</font> 为必填项）
							</tt>
					</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td width="20%" align="center"><font color="red">*</font> 小组分类</td>
				<td width="80%"><input type="text" value="${disGroupClassify.name}" name="disGroupClassify.name" id="groupList" onblur="isExsit()"></td>
					</tr>
				<%-- 	<tr>
				<td width="20%" align="center"><font color="red">*</font> 分类代码</td>
				<td width="80%"><input type="text" value="${disGroupClassify.code}" name="disGroupClassify.code"></td>
					</tr> --%>
						<tr>
				<td width="20%" align="center"><font color="red">*</font> 设置排序值</td>
				<td width="80%"><input type="text" value="${disGroupClassify.sort}" name="disGroupClassify.sort"></td>
					</tr>
					<tr>
				<td align="center" colspan="2">
				<input class="btn btn-danger" type="button" value="提交" onclick="Valempty()">
				<input class="btn ml10" type="button" onclick="history.go(-1)" value="返回">
				</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</div>

</body>














</html>