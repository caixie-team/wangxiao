<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试题组卷</title>
<script type="text/javascript">
	function tijiao(id,qstrcdId,score){
		var inputscore = $("#score"+id).val();
		if(inputscore>score){
			alert("输入的分数不能超过"+score+"分");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/paper/updateExampaperRecordScore",
			data:{"score":inputscore,"id":id,"qstrcdId":qstrcdId},
			async:false,
			success:function(result){
				if(result.success){
					alert("阅卷成功成功");
				}
			}
		});
	}

</script>
</head>
<body >
		<input type="hidden" name="paper.id" value="${paper.id }" />
		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>主观阅卷</span> &gt; <span>主观阅卷</span>
			</h4>
		</div>
		<div class="mt20">
					<div class="testPagerTitle">
						<h5>主观题阅卷</h5>
					</div>
					<c:forEach items="${paperMiddleList }" var="pml">
					
					<div class="testWrap">
						<div>试题分数：${pml.score}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   试题得分：${pml.qstrdScore}
							<div class="cl-txt">
								<p>试题内容：${pml.qstContent}</p>
							</div>
							<div>
								<p>根据以上材料回答以下问题：</p>
							</div>
							<ul class="clearfix testWrapList">
								学生答案：${pml.userAnswer }
							</ul>
						</div>
						分数：<input type="text" value="${pml.qstrdScore}" style="width:40px" id="score${pml.id}"/>
						<input type="button" class="ml10" value="提交" onclick="tijiao(${pml.id},${pml.qstrcdId},${pml.score})"/>
					</div>
					</c:forEach>
				</div>
		<!-- /题型二 -->

</body>
</html>
