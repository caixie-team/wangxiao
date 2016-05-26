<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试题组卷</title>
<link rel="stylesheet" href="${ctximg}/kindeditor/themes/simple/simple.css" />
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript">
	$().ready(function(){
		initEditorClass("textarea");
		 $("textarea[name^='complexContent']").each(function(){
				initEditor($(this).attr("id"));
		});
	});
	var editor;
	//加载编辑器
	function initEditorClass(id){
			editor = KindEditor.create('textarea[class="'+id+'"]', {
					resizeType  : 1,
					 width:"810px",
				       height:"60px",
				       minWidth:"30px",
				       minHeight:"30px",
			       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
			       allowPreviewEmoticons : false,
			       allowUpload : true,//允许上传
			       readonlyMode : true,
			       urlType : 'domain',//absolute
			       newlineTag :'br',//回车换行br|p
			       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
			       allowFileManager : false,
			       items : [],
			       afterBlur : function() {
			    	   editor.sync();
			    	   editor.readonly();
					}
			});
			//editor.readonly(true);
	}
</script>
</head>
<body >
	<c:forEach items="${paperMiddleList}" var="divpaperMiddle" varStatus="divpaperMiddle_status">
				<div style="padding: 20px;">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<div class="testPagerTitle">
					<h5>
						<b>第${divpaperMiddle_status.index+1}部分( 
							<c:if test="${divpaperMiddle.type==1}">单选题</c:if> 
							<c:if test="${divpaperMiddle.type==2}">多选题</c:if> 
							<c:if test="${divpaperMiddle.type==3}">判断题</c:if> 
							<c:if test="${divpaperMiddle.type==4}">材料题</c:if> 
							<c:if test="${divpaperMiddle.type==5}">不定项选择</c:if>
							<c:if test="${divpaperMiddle.type==6}">主观题</c:if>  )：
						</b>
						<span id="spanname${divpaperMiddle.id }" class="name${divpaperMiddle.id }">
							 <c:choose>
								<c:when test="${divpaperMiddle.name==null || divpaperMiddle.name.trim()==''}">请输入显示名称</c:when>
								<c:otherwise>${divpaperMiddle.name}</c:otherwise>
							</c:choose>
						</span> 
						<span style="margin-right: 2%; float: right;">
							<c:if test="${divpaperMiddle.type!=4 }">
								<a class="btn btn-danger" title="填充试题" href="javascript:void(0);" onclick="showwin(${divpaperMiddle.type},0,${divpaperMiddle.id });">填充试题</a>
							</c:if>
							<c:if test="${divpaperMiddle.type==4 }">
								<a class="btn btn-danger" title="添加子材料题" href="javascript:addcailiaoHtml('${divpaperMiddle.id }')">添加子题</a>
							</c:if>
							<a class="btn btn-danger" title="修改" onclick="doUpdatePaperMiddle('${divpaperMiddle.id }');" href="javascript:void(0);">修改大题目</a>
							<a class="btn btn-danger" title="删除" onclick="delPaperMiddle('${divpaperMiddle.id }');" href="javascript:void(0);">删除大题目</a>
							<a class="btn ml10" title="上移" onclick="paperMiddleMove(${divpaperMiddle.id },${divpaperMiddle.sort },${divpaperMiddle_status.count},'up')" href="javascript:void(0);">上移</a> 
							<a class="btn ml10" title="下移" onclick="paperMiddleMove(${divpaperMiddle.id },${divpaperMiddle.sort },${divpaperMiddle_status.count},'down');" href="javascript:void(0);">下移</a>
							<input type="hidden" id="paperMiddleSort${divpaperMiddle_status.count}" value="${divpaperMiddle.id},${divpaperMiddle.sort }"/>
						</span>
						</h5>
						<input type="hidden" value="<c:out value="type"/>" class="${divpaperMiddle.id}type" />
					</div>
				<c:if test="${divpaperMiddle.type!=4 }">	
				<thead>
					<tr>
						<th><span>题号</span></th>
						<th><span>试题/材料名称</span></th>
						<th><span>分值</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				</c:if>
				<tbody id="tabS_01">
						<!-- 循环大题 -->
						<c:choose>
							<c:when test="${divpaperMiddle.type==4 }">
								<c:forEach items="${divpaperMiddle.complexList}" var="divcomplexList" varStatus="divcomplexList_sattus">
									<tr>
										<td colspan="6">
											<a class="btn btn-danger" onclick="showwin(0,${divcomplexList.id },${divpaperMiddle.id });" href="javascript:void(0);" title="选择已有试题">填充试题</a>
											<a class="btn btn-danger" onclick="openWindowQuestion(${divcomplexList.id },${divpaperMiddle.id });" href="javascript:void(0);" title="添加材料新试题">添加材料新试题</a>
											<a class="btn btn-danger" href="javascript:delCaiLiaoQuestion(${divcomplexList.id })" title="删除材料题">删除材料题</a>
											<a class="btn btn-danger" href="javascript:updateComplexContent(${divcomplexList.id })" title="保存材料题内容">保存内容</a>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<b>材料题${divcomplexList_sattus.index+1}：</b>
											<div class="cl-txt"> 
												<textarea title="${divcomplexList.id}" id="complexContent${divcomplexList.id}"  name="complexContent${divcomplexList.id}">${divcomplexList.complexContent}</textarea>
											</div>
											<div>
												<p>根据以上材料回答以下问题：</p>
											</div>
										</td>
									</tr>
									<tr align="center">
										<td><span>题号</span></td>
										<td><span>试题/材料名称</span></td>
										<td><span>分值</span></td>
										<td><span>操作</span></td>
									</tr>
									<c:forEach items="${divcomplexList.queryQstMiddleList}" var="ulQueryQstMiddleList" varStatus="index">
										<tr align="center">
											<td>
												<input value="${ulQueryQstMiddleList.qstId }" name="${ulQueryQstMiddleList.paperMiddleId}qstIds" type="hidden" />
												<span class="c_333">${index.count}</span>
												<input value="${ulQueryQstMiddleList.qstId }" name="cailiaoqstIds" type="hidden" />
											</td>
											<td>
												<span class="c_777">
													<textarea class="textarea" style="width: 100%; ">${ulQueryQstMiddleList.qstContent}</textarea>
												</span>
											</td>
											<td>${divpaperMiddle.score}</td>
											<td>
												<span class="span">
													<a class="btn ml10" title="删除" href="javascript:delqstMiddleLi(${divpaperMiddle.id},'${ulQueryQstMiddleList.id}');">删除</a>
													<a class="btn ml10" title="上移" onclick="moveQst(${ulQueryQstMiddleList.qstId},${ulQueryQstMiddleList.sort},${index.count},'${ulQueryQstMiddleList.complexId }complexQstSort','up');" href="javascript:void(0);">上移</a> 
													<a class="btn ml10"　title="下移" onclick="moveQst(${ulQueryQstMiddleList.qstId},${ulQueryQstMiddleList.sort},${index.count},'${ulQueryQstMiddleList.complexId }complexQstSort','down');" href="javascript:void(0);">下移</a> 
													<input type="hidden" id="${ulQueryQstMiddleList.complexId }complexQstSort${index.count}" value="${ulQueryQstMiddleList.qstId},${ulQueryQstMiddleList.sort },${ulQueryQstMiddleList.qstType },${ulQueryQstMiddleList.complexId },${divpaperMiddle.id }"/>
												</span>
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${divpaperMiddle.qstMiddleList}" var="ulqstMiddleList" varStatus="index">
									<tr align="center">
										<td>
											<input value="${ulqstMiddleList.qstId }" name="${ulqstMiddleList.paperMiddleId}qstIds" type="hidden" />
											<span class="c_333">${index.count }</span>
										</td>
										<td>
											<span class="c_777">
												<textarea class="textarea"style="width: 100%; ">${ulqstMiddleList.qstContent}</textarea>
											</span>
										</td>
										<td>${divpaperMiddle.score}</td>
										<td>
											<span class="span">
												<a class="btn ml10" title="删除" href="javascript:void(0);" onclick="delqstMiddleLi(${divpaperMiddle.id},'${ulqstMiddleList.id }')">删除</a>
												<a class="btn ml10" title="上移" onclick="moveQst(${ulqstMiddleList.qstId},${ulqstMiddleList.sort},${index.count},'${divpaperMiddle.id}qstSort','up');" href="javascript:void(0);">上移</a> 
												<a class="btn ml10"　title="下移" onclick="moveQst(${ulqstMiddleList.qstId},${ulqstMiddleList.sort},${index.count},'${divpaperMiddle.id}qstSort','down');" href="javascript:void(0);">下移</a> 
												<input type="hidden" id="${divpaperMiddle.id}qstSort${index.count}" value="${ulqstMiddleList.qstId},${ulqstMiddleList.sort },${ulqstMiddleList.qstType },0,${divpaperMiddle.id }"/>
											</span>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</tbody>
				
			</table>
			</div>
			</c:forEach>
</body>
</html>
