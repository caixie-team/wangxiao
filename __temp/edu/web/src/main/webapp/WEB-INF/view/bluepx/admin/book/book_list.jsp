<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
//subject ztree start
var subject_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'subjectId',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'subjectName',
			title:'subjectName'
		}
	},
	callback: {
		onClick: subject_treeOnclick
	}
};
var subject_treedata=${subjectList};

//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
	hideSubjectMenu();
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	hideSubjectMenu();
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function showSubjectMenu() {
	var cityObj = $("#subjectNameBtn");
	var cityOffset = $("#subjectNameBtn").offset();
	$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideSubjectMenu() {
	$("#subjectmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
		hideSubjectMenu();
	}
}

$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	//专业名称显示
	if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
		if(treeNode!=null){
			$("#subjectNameBtn").val(treeNode.subjectName);
		}
	}
	var isavild='${queryBookCondition.shopState}'==''?-1:'${queryBookCondition.shopState}';
	$("#isavaliable").val(isavild);
});


function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#id").val("");
		$("#name").val("");
		$("#subjectNameBtn").val("");
		$("#isavaliable").val(-1);
	}
	function delBook(bookName,bookId,type) {//冻结
		var text="";
		if(type==1){
			text="确定解冻【"+bookName+"】吗？";
		}else if(type==2){
			text="确定冻结【"+bookName+"】吗？";
		}else{
			text="请注意:你将要删除商品【"+bookName+"】,删除后将不能恢复，请确认删除？";
		}
		if(confirm(text)) {
			$.ajax({
				url:"${ctx}/admin/book/delbook",
				data:{"book.bookId":bookId,"book.status":type},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						window.location.reload();
					}else{
						alert("系统繁忙请稍后重试");
						return;
					}
				}
			});
		}
	}
</script>
</head>
<body >
			<!-- 内容 开始  -->
            <div class="page_head">
                <h4><em class="icon14 i_01"></em>&nbsp;<span>图书管理</span> &gt; <span>图书列表</span> </h4>
            </div>
            <!-- /tab1 begin-->
            <div class="mt20">
                <div class="commonWrap">
                        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                <form action="${ctx}/admin/book/list" name="searchForm" id="searchForm" method="post">
                                    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
                                    <input type="hidden" id="subjectId" name="queryBookCondition.bookSubjectid" value="${queryBookCondition.bookSubjectid}"/>

                                    <div class="capHead">

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>图书id：</font></span><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryBookCondition.bookId" value="${queryBookCondition.bookId}" id="id" /></li>
                                            <li><span class="ddTitle"><font>图书名称/作者：</font></span><input type="text" name="queryBookCondition.keyword" value="${queryBookCondition.keyword}" id="name" /></li>
                                        </ul>
                                    </div>

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>专业：</font></span>
                                                <input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
                                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
                                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
                                                </div>
                                            </li>

                                            <li>
                                                <span class="ddTitle"><font>状态：</font></span>
                                                <select name="queryBookCondition.shopState" id="isavaliable" >
                                                    <option value="-1">请选择</option>
                                                    <option value="0">上架</option>
                                                    <option value="1">下架</option>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="optionList">
                                        <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()">
                                        <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                            <%--     <div class="mt10 clearfix">
                                    <p class="fl czBtn">
                                        <span>
                                            <a href="${ctx}/admin/cou/toAddCourse" title="新建">
                                                <em class="icon14 new">&nbsp;</em>
                                                新建课程
                                            </a>
                                        </span>
                                    </p>
                                </div> --%>
                                </form>
                            </caption>

                            <thead>
                                <tr>
                                <th width="5%">ID</th>
								<th width="9%"><span>商品名称</span></th>
								<th width="6%"><span>作者</span></th>
								<th width="9%"><span>出版社</span></th>
								<!-- <th width="10%"><span>出版时间</span></th> -->
								<th width="8%"><span>所属项目</span></th>
								<th width="5%"><span>分类</span></th>
								<th width="10%"><span>添加时间</span></th>
								<!-- <th width="10%"><span>上架时间</span></th> -->
								<th width="8%"><span>图书价格</span></th>
								<th width="8%"><span>是否在售</span></th>
								<!-- <th width="4%"><span>推荐</span></th>
								<th width="4%"><span>特价</span></th>
								<th width="4%"><span>精品</span></th>
								<th width="4%"><span>畅销</span></th> -->
								<th width="6%"><span>状态</span></th>
								<th width="20%"><span>操作</span></th>
                                </tr>
                            </thead>
                            <tbody id="tabS_02" align="center">
                                <c:if test="${bookList.size()>0}">
                                    <c:forEach items="${bookList}" var="book">
								<tr>
									<td>${book.bookId}</td>
									<td>${fn:substring(book.bookName,0,12)}</td>
									<td>${fn:substring(book.author,0,10)}</td>
										<td>${book.press }</td>
											<%-- <td>
											<fmt:formatDate value="${book.publishTime}" type="both"/></td> --%>
									<td>${book.subjectName }</td>

									<td>
									<c:if test="${book.bookType==1 }">
										教材
										</c:if>
									<c:if test="${book.bookType==2 }">
										教辅
										</c:if>
										<c:if test="${book.bookType==3 }">
										大纲
										</c:if>
										<c:if test="${book.bookType==4 }">
										套装
										</c:if>
									</td>
									<td><fmt:formatDate value="${book.addTime}" type="both"/></td>
									<%-- <td><fmt:formatDate value="${book.upTime}" type="both"/></td> --%>
									<td>${book.nowPrice }</td>
									<td><c:if test="${book.shopState==2}">
											否/下架时间: <br />
											<fmt:formatDate value="${book.dropTime}" type="both"/>
										</c:if> <c:if test="${book.shopState==1}">
											是
										</c:if></td>
								<%-- 	<td>
									<c:choose>
									  <c:when test="${fn:indexOf(book.disProperty,'1')!=-1}">
										★
										</c:when><c:otherwise>
										/
										</c:otherwise>
										</c:choose>
									</td>
									<td>	<c:choose>
									  <c:when test="${fn:indexOf(book.disProperty,'2')!=-1}">
										★
										</c:when><c:otherwise>
										/
										</c:otherwise>
										</c:choose></td>
									<td>	<c:choose>
									  <c:when test="${fn:indexOf(book.disProperty,'3')!=-1}">
										★
										</c:when><c:otherwise>
										/
										</c:otherwise>
										</c:choose></td>
									<td>	<c:choose>
									  <c:when test="${fn:indexOf(book.disProperty,'4')!=-1}">
										★
										</c:when><c:otherwise>
										/
										</c:otherwise>
										</c:choose></td>--%>
									
								
										<td> 
										<c:if test="${book.status==1 }">
											正常
										</c:if>
										<c:if test="${book.status==2 }">
											冻结
										</c:if>
										</td>
									<td><a class="ml10 btn smallbtn btn-y" title="详情" href="${ctx}/admin/book/info/${book.bookId}">详情</a>
										<a class="ml10 btn smallbtn btn-y" title="编辑" href='${ctx}/admin/book/toupdate/${book.bookId}'>编辑</a>
										<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="delBook('${book.bookName }',${book.bookId },'3')">删除</a>
										<c:if test="${book.status==2 }">
											<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="delBook('${book.bookName }',${book.bookId},'1')">解冻</a>
										</c:if> <c:if  test="${book.status==1 }">
											<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="delBook('${book.bookName }',${book.bookId},'2')">冻结</a>
										</c:if>
								
									</td>

								</tr>
							</c:forEach>
                                </c:if>
                                <c:if test="${bookList.size()==0||bookList==null}">
                                    <tr>
                                        <td align="center" colspan="16">
                                            <div class="tips">
                                                <span>还没有相关数据！</span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                        <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
                </div>
            </div>

</body>
</html>
