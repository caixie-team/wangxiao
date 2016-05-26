<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量导入试题</title>
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
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>excel批量导入</span> </h4>
</div>

<div class="mt20">
		<form action="/admin/quest/importExcel" method="post" id="importP" enctype="multipart/form-data">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>&nbsp;</caption>
					<thead>
						<tr>
							<th align="left" colspan="2"><span>excel批量导入数据</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;试题描述</td>
							<td>
									1、必须是excel格式,下载的模版中第sheet2中有说明<br>
									2、多选时，最少4个选项，即ABCD必填<br>
									3、导入标识只是为了方便搜索本次导入的试题，无其他作用，建议用日期加几个字母<br>
									4、多选请至少写2个答案选项<br>
									5、多选最多支持七个选项<br>
									6、记录要挨着输入，不能有空行<br>
									7、多项选择题答案至少有两个，如ABD<br>
									8、点击下载<a href="${ctx}/static/common/admin/question.xls" title="excel试题模板" style="color: blue;">excel试题模板</a><br>
							</td>
						</tr>
						<tr>
							<td align="center">上传</td>
							<td>
								<span class="ml10"><input id="myFile" type="file" value="" name="myFile"/><input type="button" value="提交" class="btn btn-danger"  onclick="importExcel()"/>
								<a href="/admin/quest/toQuestionList" title="返回" class="btn btn-danger">返回</a>
								</span>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
</body>
</html>