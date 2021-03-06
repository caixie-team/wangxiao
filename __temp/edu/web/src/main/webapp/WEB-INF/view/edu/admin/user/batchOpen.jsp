<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量导入学员</title>

<script type="text/javascript">
function importExcel(){
	
	var myFile = $("#myFile").val();
	if(myFile.length <= 0){
	alert("请选择导入内容");
	return false;
	}
	$("#importP").submit();
}
</script>
</head>
<body>
<div class="page_head">
    <h4><em class="icon14 i_01"></em>&nbsp;<span>学员管理</span> &gt; <span>批量导入</span> </h4>
</div>
<div class="mt20">
    <div class="commonWrap">
		<form action="/admin/user/importExcel" method="post" id="importP" enctype="multipart/form-data">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>&nbsp;</caption>
					<thead>
						<tr>
							<th align="left" colspan="2"><span>批量导入数据   </span> </th> 
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;信息描述</td>
							<td>excel模版说明：<br>
                                第一列：用户的电子邮箱,必须是未注册过的<br>
                                第二列：课程id,多个课程中间使用英文逗号“,”隔开 例如 55,12,<br>
                                第三列：密码 如不填写默认111111,不得填入非法字符例如“. * #”<br>
                                （<a href="${ctx }/static/common/import_student.xls"  style="color: red;">点击下载模版</a>）<br>
							</td>
						</tr>
						<tr>
							<td align="center">上传</td>
							<td>
								<span class="ml10"><input id="myFile" type="file" value="" name="myFile"/><input type="button" value="提交" class="btn btn-danger"  onclick="importExcel()"/>
								<a href="javascript:history.go(-1);" title="返回" class="btn btn-danger">返回</a>
								</span>
							</td>
						</tr>
					</tbody>
				</table>
				</form>

			</div>
    </div>
</body>
</html>