<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>试卷列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>

<script type="text/javascript">

/*
 * 删除试卷
 *delFalg为-1时候批量删除，其他为右边单个删除的试卷id
 */
function delPaperListBatch(delFalg){
	var str ="";
	//当等于-1的时候是左边多选批量删除其他为右边操作时的单个删除
	if(delFalg==-1){
		var ischeck=false;
		$(".paperIds").each(function(){
			if($(this).prop("checked")){
				str+=$(this).attr("id")+",";
				ischeck=true;
			}
		});
		if(!ischeck){
			alert("未选中试卷");
			return;
		}
	}else{
		str = delFalg;
	}
	if(!confirm("确认删除试题")){
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/paper/delPaperListBatch",
		data:{"paperIds":str},
		async:false,
		success:function(result){
			if(result.success==true){
				location.reload();
			}
		}
	});
}
//查询
function query(){
	if($("#id").val()!='' && $("#id").val()!=0){
		$("#level").val(0);
		$("#type").val(0);
		//$("#subjectId").val(0);
		$("#idHidden").val($("#id").val());
	}else{
		$("#idHidden").val(0);
	}
	goPage(1);
}
//清除查询条件
function cleanquery(){
	$("#level").val(0);
	$("#type").val(0);
	$("#professionalSelect").val(0);
	$("#subjectLi").hide();
	$("#subjectNameBtn").val("");
	$("#idHidden").val(0);
	$("#id").val("");
}
//根据专业id查询科目
function getSubjectByProId(obj){
	if(obj!=0){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/subj/querySubjectByProId",
			data:{"querySubject.professionalId":obj},
			async:false,
			success:function(result){
				if(result.success==true){
					var subjectList = result.entity;
					var str = "";
					for(var i =0;i<subjectList.length;i++){
						str +='<option value="'+subjectList[i].subjectId+'"">'+subjectList[i].subjectName+'</option>';
					}
					$("#subjectSelect").html(str);
					$("#subjectLi").show();
				}
			}
		});
	}else{
		$("#subjectLi").hide();
		$("#subjectSelect").html("");
	}
}
$().ready(function() {
	//上次搜索条件显示
	$("#level").val('${queryPaper.level}');
	$("#type").val('${queryPaper.type}');
	$("#professionalSelect").val('${queryPaper.professionalId}');
	if('${queryPaper.id}'!='0'){
		$("#id").val('${queryPaper.id}');
		$("#idHidden").val('${queryPaper.id}');
	}else{
		$("#id").val('');
		$("#idHidden").val(0);
	}
	var professionalId = '${queryPaper.professionalId}';
	if(professionalId>=0){
		getSubjectByProId(professionalId); 
		$("#subjectSelect").val('${queryPaper.subjectId}');
	}
	
});
</script>
</head>
<body >

<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>试卷管理</span> &gt; <span>试卷列表</span> </h4>
	</div>
<form action="${ctx}/admin/paper/listAllPaper" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>

<div class="mt20">
		<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>ID：</font></span>
									<input type="text"  id="id" onkeyup="value=value.replace(/[^\d]/g,'')"/>
									<input type="hidden" value="0" id="idHidden" name="queryPaper.id"/>
								</li>
								<li>
									<span class="ddTitle"><font>专业：</font></span>
									<select name="queryPaper.professionalId" id="professionalSelect" onchange="getSubjectByProId(this.value)">
									<option value="0">请选择</option>
									<c:forEach items="${professionalList}" var="professional">
										<option value="${professional.professionalId}">${professional.professionalName}</option>
									</c:forEach>
									</select>
								</li>
								<li id="subjectLi" style="display: none;">
									<span class="ddTitle"><font>科目：</font></span>
									<select id="subjectSelect" name="queryPaper.subjectId" >
									</select>
								</li>
								
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>试卷类型：</font></span>
									<select name="queryPaper.type" id="type">
										<option value="0">-- 全部 --</option>
                                        <c:forEach items="${paperTypeList}" var="paperType">
                                            <option value="${paperType.id}">${paperType.title}</option>
                                        </c:forEach>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>试卷难度：</font></span>
									<select name="queryPaper.level" id="level">
										<option value="0">-- 全部 --</option>
										<option value="1">简单</option>
										<option value="2">中等</option>
										<option value="3">困难</option>
									</select>
								</li>
								<li>
									<input class="btn btn-danger ml10" type="button" onclick="query()" value="查询">
									<input class="btn btn-danger ml10" type="button" onclick="cleanquery()" value="清空">
								</li>
							</ul>
						</div>
						<div class="w50pre fl" align="center">
							<ul class="ddBar">
							<!-- 	<li>
									<input class="btn btn-danger ml10" type="button" onclick="query()" value="查询">
									<input class="btn btn-danger ml10" type="button" onclick="cleanquery()" value="清空">
								</li> -->
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span><a href="${ctx}/admin/paper/toAddExamPaper" title="新建试卷"><em class="icon14 new">&nbsp;</em>新建试卷</a></span>
							<span class="ml10"><a href="javascript:delPaperListBatch(-1);" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span>
						</p>
						<p class="fr c_666"><span>试卷列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="10%"><span>ID</span></th>
						<th><span>试卷名</span></th>
						<th><span>科目</span></th>
						<th><span>考试描述</span></th>
						<th><span>试卷难度</span></th>
						<th><span>试卷总分</span></th>
						<th><span>类型</span></th>
						<th><span>答题时间</span></th>
						<th><span>创建时间</span></th>
						<th><span>考试人数</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${paperList.size()>0}">
				<c:forEach items="${paperList}" var="trpaper">
					<tr>
						<td><input type="checkbox" class="paperIds" id="${trpaper.id }"/>&nbsp;${trpaper.id }</td>
						<td>${trpaper.name }</td>
						<td>${trpaper.subjectName }</td>
						<td>${trpaper.info }</td>
						<td>
						<c:if test="${trpaper.level==1}">简单</c:if>
						<c:if test="${trpaper.level==2}">中等</c:if>
						<c:if test="${trpaper.level==3}">困难</c:if>
						</td>
						<td>${trpaper.score }</td>
						<td>
						<c:if test="${trpaper.type==1}">${paperTypeName.title1}</c:if>
						<c:if test="${trpaper.type==2}">${paperTypeName.title2}</c:if>
						<c:if test="${trpaper.type==3}">${paperTypeName.title3}</c:if>
						</td>
						<td>${trpaper.replyTime }</td>
						<td><fmt:formatDate  value="${trpaper.addTime}" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate> </td>
						<td>${trpaper.joinNum }</td>
						<td class="c_666 czBtn" align="center">
							<a class="btn smallbtn btn-y" href="${ctx}/admin/paper/showExamPaperById?paper.id=${trpaper.id }" title="查看">查看</a>
							<a class="btn smallbtn btn-y" href="${ctx}/admin/paper/toUpdateExamPaper?paper.id=${trpaper.id }" title="修改">修改</a>
							<a class="btn smallbtn btn-y" href="javascript:delPaperListBatch(${trpaper.id})" title="删除">删除</a>
							<a class="btn smallbtn btn-y" href="${ctx}/admin/paper/toExamPaperAssemble?paper.id=${trpaper.id }" title="试题组卷">试题组卷</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${paperList.size()==0 }">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有试卷数据！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div><!-- /commonWrap -->
	</div>
</form>
</body>
</html>
