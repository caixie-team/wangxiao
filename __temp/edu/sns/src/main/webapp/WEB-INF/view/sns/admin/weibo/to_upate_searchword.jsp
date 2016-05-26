<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>更新搜索词</title>
<script type="text/javascript">
	function submitFrom(){
		var count  = $("#count").val();
		if(count.trim()==""){
			alert("次数不能为空！");
			return;
		}
		$("#searchForm").submit();
	}
	$(function(){
		$("#top").val("${searchWord.top}");
	});
</script>

</head>
<body  >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>搜索词管理</span> &gt; <span>修改搜索词</span> </h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/search/updateSearchWord"
					name="searchForm" id="searchForm" method="post">
					<div class="mt20">
						<table width="100%" cellspacing="0" cellpadding="0" border="0"
							class="commonTab01">
							<caption>
								<div class="optionList">
									<a class="btn btn-danger" href="${ctx}/admin/search/searchWord">返回</a>
								</div>
							</caption>
							<thead>
								<tr>
									<th colspan="2" align="left"><span>更新搜索词<tt
												class="c_666 ml20 fsize12">
												（<font color="red">*</font>&nbsp;为必填项）
											</tt></span></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td width="20%" align="center"><font color="red">*</font>&nbsp;类型</td>
									<td width="80%">${searchWord.type}
									<input type="hidden" name="searchWord.type" value="${searchWord.type }"/>
									</td>
								</tr>
								<tr>
									<td align="center"><font color="red">*</font>&nbsp;搜索词</td>
									<td>${searchWord.word }<input type="hidden" name="searchWord.word" value="${searchWord.word }"/></td>
								</tr>
								<tr>
									<td align="center"><font color="red">*</font>&nbsp;次数</td>
									<td><input type="text" id="count" name="searchWord.count" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
                                    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" value="${searchWord.count }" />
									</td>
								</tr>
								<tr>
									<td align="center"><font color="red">*</font>&nbsp;是否置顶</td>
									<td><select name="top" id="top">
											<option value="0">否</option>
											<option value="1">是</option>
										</select></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><a href="javascript:submitFrom();" title="提 交"
										class="btn btn-danger">提 交</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<!-- /commonWrap -->
		</div>
	<!-- /tab2 end-->
</body>
</html>