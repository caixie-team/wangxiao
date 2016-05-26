<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试题组卷</title>
</head>
<body >
	<div class="modal-backdrop fade in"></div>
	<div class="modal fade in">
		<div class="modalWrap">
			<div class="modal_head"><span class="fr"><a class="icon14 closeBtn" title="关闭" href="javascript:void(0)" onclick="$('#updateMainQst').html('')"></a></span><h4><span>创建大题目</span></h4></div>
			<div id="modalCont" class="modal_body">
				<form id="updatePaperMiddleForm">
					<input value="${paperMiddle.id }" type="hidden" name="paperMiddle.id" />
					<input value="${paperMiddle.paperId }" type="hidden" name="paperMiddle.paperId" />
					<table width="100%" border="0">
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">试题类型：</span></td>
							<td width="80%">
								<div class="ml10 mt10">
									<select id="paperMiddleType" style="width:380px;" disabled="disabled">
											<option value="1" <c:if test="${paperMiddle.type==1}">selected="selected"</c:if>>单选题</option>
											<option value="2" <c:if test="${paperMiddle.type==2}">selected="selected"</c:if>>多选题</option>
											<option value="3" <c:if test="${paperMiddle.type==3}">selected="selected"</c:if>>判断题</option>
											<option value="4" <c:if test="${paperMiddle.type==4}">selected="selected"</c:if>>材料题</option>
											<option value="5" <c:if test="${paperMiddle.type==5}">selected="selected"</c:if>>不定项选择</option>
											<option value="6" <c:if test="${paperMiddle.type==6}">selected="selected"</c:if>>主观题</option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">大题目名称：</span></td>
							<td width="80%"><div class="ml10 mt10"><input id="upaperMiddle_name" type="text" value="${paperMiddle.name }" name="paperMiddle.name" style="width: 140px;" /></div></td>
						</tr>
						
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">内含小题数：</span></td>
							<td width="80%"><div class="ml10 mt10"><input type="text" id="upaperMiddle_num"  value="${paperMiddle.num }" name="paperMiddle.num" style="width: 140px;" /></div></td>
						</tr>
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">每题：</span></td>
							<td width="80%"><div class="ml10 mt10"><input type="text" id="upaperMiddle_score" value="${paperMiddle.score }" name="paperMiddle.score" style="width: 140px;" /><span class="fsize14 c_666">&nbsp;&nbsp;分</span></div></td>
						</tr>
						
						<tr>
							<td width="20%" align="right"><span class="fsize14 c_666">大题说明：</span></td>
							<td width="80%"><div class="ml10 mt10"><textarea style="width:380px;" id="upaperMiddle_title" name="paperMiddle.title">${paperMiddle.title }</textarea></div></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal_foot clearfix"><button class="btn btn-success" onclick="updatePaperMiddle()">确定</button><button class="btn ml10 cancelBtn" onclick="$('#updateMainQst').html('')">取消</button></div>
		</div>
	</div>
	
</body>
</html>
