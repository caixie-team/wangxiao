<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试题组卷</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
	<link rel="stylesheet" href="${ctximg}/kindeditor/themes/simple/simple.css" />
<script type="text/javascript">
	var paperId =${paper.id};
	$().ready(function(){
		initEditorClass("textarea");
		 $("#paperMiddleForm").validate();
		 //显示各题的试题数量
		 <c:forEach items="${paperMiddleList}" var="jspaperMiddle">
			var qstId  = document.getElementsByName("${jspaperMiddle.id}qstIds"); 
			$(".trueNum${jspaperMiddle.id}").html(qstId.length);
		</c:forEach>
		//根据标识如果为6则只显示主观题其他则全部显示
		if('${type}'=='6'){
			$(".type6").show();
			$(".type6").siblings().hide();
		}
	});

	function tijiao(qstrcdId,score){
		var inputscore = $("#score"+qstrcdId).val();
		if(inputscore>score){
			alert("输入的分数不能超过"+score+"分");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/paper/updateExampaperRecordScore",
			data:{"score":inputscore,"qstrcdId":qstrcdId,"qstScore":score},
			async:false,
			success:function(result){
				if(result.success){
					$(".status"+qstrcdId).html(inputscore);
					alert("阅卷成功");
				}else{
					alert("阅卷失败请刷新重试");
				}
			}
		});
	}
	
//加载编辑器
var editor;
function initEditorClass(id){
	KindEditor.ready(function(K) {
		editor = K.create('textarea[class="'+id+'"]', {
				resizeType  : 0,
				 width:"810px",
			       height:"60px",
			       minWidth:"30px",
			       minHeight:"30px",
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       readonlyMode : true,
		       allowUpload : true,//允许上传
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       uploadJson : 'http://image.268xue.com/imgk4?param=question',//图片上传路径
		       allowFileManager : false,
		       items : []
		       
		});
		//editor.readonly(true);
	});
}


</script>
</head>
<body >
<div class="page_head">
	<h4>
		<em class="icon14 i_01"></em>&nbsp;<span>试题管理</span> &gt; <span>试题记录</span>
	</h4>
</div>
		
	<input type="hidden" name="paper.id" value="${paper.id }" />
	<div class="mt20">
		
		<div class="commonWrap">
			<c:forEach items="${paperMiddleList}" var="divpaperMiddle"
				varStatus="divpaperMiddle_status">
			
				<div class="mt20 type${divpaperMiddle.type}" paperMiddleId="${divpaperMiddle.id}" sort="${divpaperMiddle.sort }">
					<div class="testPagerTitle">
					
					<h5>
						<b>第${divpaperMiddle_status.index+1}部分( <c:if
								test="${divpaperMiddle.type==1}">单选题</c:if> <c:if
								test="${divpaperMiddle.type==2}">多选题</c:if> <c:if
								test="${divpaperMiddle.type==3}">判断题</c:if> <c:if
								test="${divpaperMiddle.type==4}">材料题</c:if> 
								<c:if test="${divpaperMiddle.type==5}">不定项选择</c:if>
								<c:if test="${divpaperMiddle.type==6}">主观题</c:if> )：
						</b>
						<span id="spanname${divpaperMiddle.id }" class="name${divpaperMiddle.id }">
							 <c:choose>
								<c:when test="${divpaperMiddle.name==null || divpaperMiddle.name.trim()==''}">请输入显示名称</c:when>
								<c:otherwise>${divpaperMiddle.name}</c:otherwise>
							</c:choose>
						</span> 
						(<span id="title${divpaperMiddle.id }"><c:out value="${divpaperMiddle.title}" /></span>)
						</h5>
						<input type="hidden" value="<c:out value="type"/>" class="${divpaperMiddle.id}type" />
					</div>
					<!-- 循环大题 -->
					<c:choose>
						<c:when test="${divpaperMiddle.type==4 }">
							<div style="padding-left: 20px; padding-top: 20px;">
								<span class="ml10">
								单题分值 <c:out value="${divpaperMiddle.score}"/>
								</span> 
								<span class="ml10">
								题数<c:out value="${divpaperMiddle.num}"/></span> 
								<span class="ml10">已添加试题数：<span class="trueNum${divpaperMiddle.id}"></span></span>
							</div>
							<div id="paperMiddlediv${divpaperMiddle.id }" paperMiddleId="${divpaperMiddle.id }">
								<c:forEach items="${divpaperMiddle.complexList}"
									var="divcomplexList" varStatus="divcomplexList_sattus">
									<div class="testWrap complex${divcomplexList.id }">
										<div>
											<b>材料题${divcomplexList_sattus.index+1}：</b>
											<div class="cl-txt"> 
													<textarea class="textarea" title="${divcomplexList.id}" id="complexContent${divcomplexList.id}"  name="complexContent${divcomplexList.id}">${divcomplexList.complexContent}</textarea>
											</div>
											<div>
												<p>根据以上材料回答以下问题：</p>
											</div>
											<ul
												class="clearfix cailiao${divcomplexList.id }">
												<c:forEach items="${divcomplexList.queryQstMiddleList}"
													var="ulQueryQstMiddleList">
													<li id="li${ulQueryQstMiddleList.id }">
														<input value="${ulQueryQstMiddleList.qstId }" name="${ulQueryQstMiddleList.paperMiddleId}qstIds" type="hidden" />
														<div class="ml10">试题id：
															<span class="c_333">${ulQueryQstMiddleList.qstId }</span>
														</div>
														<div class="ml10">试题内容：
															<span class="c_777">
															<textarea class="textarea"style="width: 100%; ">${ulQueryQstMiddleList.qstContent}</textarea>
															</span>
														</div>
														<div class="" style="margin: 0 0 10px 30px">
													<c:forEach items="${ulQueryQstMiddleList.optionList}" var="opt">
													${opt.optOrder },${opt.optContent }<br/>
													</c:forEach>
													</div>
													<div class="" style="margin: 0 0 10px 30px">
														用户答案：
														${ulQueryQstMiddleList.userAnswer}
														&nbsp;正确答案：${ulQueryQstMiddleList.isAsr }
														&nbsp;得分：${ulQueryQstMiddleList.score }
													</div>
													<div class="" style="margin: 0 0 10px 30px">
														解析：<br />
														<textarea class="textarea" style="width: 100%; ">${ulQueryQstMiddleList.qstAnalyze }</textarea>
													</div>
														<div class="" style="margin: 0 0 10px 30px">
															<span  qstId="${ulQueryQstMiddleList.qstId }" qstType="${ulQueryQstMiddleList.qstType }"
																complexId="${ulQueryQstMiddleList.complexId }" sort="${ulQueryQstMiddleList.sort }" class="span">
															</span>
														</div></li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<div paperMiddleId="${divpaperMiddle.id }" id="paperMiddlediv${divpaperMiddle.id}">
								<div class="testWrap">
									<div>
										<%-- <a class="btn btn-danger" title="随机抽试题" href="javascript:openWindowRandomQuestion('${divpaperMiddle.type }',${divpaperMiddle.id })">随机抽试题</a> --%>
										<span class="ml10">
										每题分值：<c:out value="${divpaperMiddle.score}"/>
										</span>
										<span class="ml10">试题数：<span class="trueNum${divpaperMiddle.id}"></span></span>
									</div>
									<div>
										<ul class="clearfix mt20 Ul${divpaperMiddle.id }">
											<c:forEach items="${divpaperMiddle.qstMiddleList}"
												var="ulqstMiddleList">
												<li id="li${ulqstMiddleList.id }">
													<input value="${ulqstMiddleList.qstId }" name="${ulqstMiddleList.paperMiddleId}qstIds" type="hidden" />
													<div class="ml10">试题id：
														<span class="c_333">${ulqstMiddleList.qstId }</span>
													</div>
													<div class="ml10">试题内容：
														<span class="c_777 ">
														<textarea class="textarea ml10" style="width: 100%; ">${ulqstMiddleList.qstContent}</textarea>
														</span>
													</div>
													<br/>
													
													<div class="" style="margin: 0 0 10px 30px">
													<c:if test="${divpaperMiddle.type!=6 }">
													<c:forEach items="${ulqstMiddleList.optionList}" var="opt">
													${opt.optOrder },${opt.optContent }<br/>
													</c:forEach>
													</c:if>
													</div>
													<div class="" style="margin: 0 0 10px 30px">
														用户答案：
														<c:if test="${divpaperMiddle.type!=6 }">
														${ulqstMiddleList.userAnswer}
														&nbsp;正确答案：${ulqstMiddleList.isAsr }
														&nbsp;用户得分：${ulqstMiddleList.score }
														</c:if>
														<c:if test="${divpaperMiddle.type==6 }">
														<br />
														<textarea class="textarea" style="width: 100%; ">${ulqstMiddleList.userAnswer}</textarea><br />
														用户得分：<span class="status${ulqstMiddleList.qstrdId }">
														<c:if test="${ulqstMiddleList.state==0 }">未审阅</c:if>
														<c:if test="${ulqstMiddleList.state==1 }">${ulqstMiddleList.score }</c:if></span>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="text" style="width:40px" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " 
														onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "  id="score${ulqstMiddleList.qstrdId }" value="${ulqstMiddleList.score}"/>分
														<a href="javascript:tijiao(${ulqstMiddleList.qstrdId },${divpaperMiddle.score})" title="提交" class="ml10 btn smallbtn btn-y">提交</a>
														</c:if>
													</div>
													<div class="" style="margin: 0 0 10px 30px">
														解析：<br />
														<textarea class="textarea" style="width: 100%; ">${ulqstMiddleList.qstAnalyze }</textarea>
													</div>
													</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- 循环大题 -->
				</div>
				<!-- /题型一 -->
			</c:forEach>
		</div>
		<!-- /题型二 -->
	</div>

</body>
</html>
