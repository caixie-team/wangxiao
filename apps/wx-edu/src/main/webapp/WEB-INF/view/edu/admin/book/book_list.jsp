<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
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
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
	$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
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
}); 

$(function(){
	$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
})

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
		$("#isavaliable").val("-1");
		$("#isavaliable").find('option').eq(0).attr('selected', true);
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
		dialogFun("图书列表 ",text,2,"javascript:delBookAjax("+bookId+","+type+")");
	}
	function delBookAjax(bookId,type){
			$.ajax({
				url:"${ctx}/admin/book/delbook",
				data:{"book.bookId":bookId,"book.status":type},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						window.location.reload();
					}else{
						dialogFun("图书列表 ","系统繁忙请稍后重试",5,"");
						return;
					}
				}
			});
	}	

	
</script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图书管理</strong> / <small>图书列表</small></div>
</div>
<hr/>
 <form action="${ctx}/admin/book/list" name="searchForm" id="searchForm" method="post"  class="am-form">
  <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
  <input type="hidden" id="subjectId" name="queryBookCondition.bookSubjectid" value="${queryBookCondition.bookSubjectid}"/>
 <div class="mt20 am-padding admin-content-list">
  	<div class="am-tab-panel am-fade am-active am-in">
		        <div class="am-g am-margin-top am-u-sm-6">
		            <div class="am-u-sm-4 am-text-right">
		            	图书id：
		            </div>
		            <div class="am-u-sm-8">
		              <input class="am-input-sm" type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryBookCondition.bookId" value="${queryBookCondition.bookId}" id="id" />
		            </div>
		        </div>
		        <div class="am-g am-margin-top am-u-sm-6">
		            <div class="am-u-sm-4 am-text-right">
		            	书名/作者：
		            </div>
		            <div class="am-u-sm-8">
		           <input class="am-input-sm" type="text" name="queryBookCondition.keyword" value="${queryBookCondition.keyword}" id="name" />
		            </div>
		        </div>
		         </div>
		         <div class="mt20"></div>
		  <div class="am-tab-panel am-fade am-active am-in">
		        <div class="am-g am-margin-top am-u-sm-6">
		            <div class="am-u-sm-4 am-text-right">
		            	专业：
		            </div>
		            <div class="am-u-sm-8">
		           <div id="doc-dropdown-justify-js" >
							  <div class="am-dropdown" id="doc-dropdown-js"  style="width: 100%">
							    <input type="text" class="am-input-sm am-dropdown-toggle"  id="subjectNameBtn" readonly="readonly"/>
							    <div class="am-dropdown-content">
							    	<div id="subject_ztreedemo" class="ztree" ></div>
							    </div>
							  </div>
					</div>
		            </div>
		        </div>
		        <div class="am-g am-margin-top am-u-sm-6">
		            <div class="am-u-sm-4 am-text-right">
		            	状态：
		            </div>
		            <div class="am-u-sm-8">
		           <select name="queryBookCondition.shopState" id="isavaliable" class="am-input-sm"  data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}" >
                                                    <option value="-1">请选择</option>
                                                    <option value="0">上架</option>
                                                    <option value="1">下架</option>
                    </select>
		            </div>
		            </div>
	 </div>
	 
	  <div class="mt20"></div>
	   <div class="clearfix"></div>
 </div>
</form>
 <div class="mt20">
	    	<div class="am-g">
		      <div class="am-u-md-6">
		        <div class="am-btn-toolbar">
		          <div class="am-btn-group am-btn-group-xs">
		            <button class="am-btn am-btn-success" type="button" onclick="window.location.href='${cxt}/admin/book/toadd'" title="新增"><span class="am-icon-plus"></span> 新增</button>
		          </div>
		        </div>
		      </div>
		      <div class="am-u-sm-12 am-u-md-3">
		        <div class="am-input-group am-input-group-sm">
		     			<button class="am-btn am-btn-warning" onclick="submitSearch()" type="button">
						<span class="am-icon-search"></span>
						搜索
						</button>
				       <input type="button" name="" value="清空" class="am-btn am-btn-danger" onclick="submitclear()"/>
		        </div>
		      </div>
		    </div>
</div>

<div class="mt20">
		      <div class="doc-example">
				<div class="am-scrollable-horizontal">
		         <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
		          <thead>
                                <tr>
                                <th width="5%">ID</th>
								<th width="9%"><span>商品名称</span></th>
								<th width="6%"><span>作者</span></th>
								<th width="9%"><span>出版社</span></th>
								 <th width="10%"><span>出版时间</span></th> 
								<th width="8%"><span>所属项目</span></th>
								<th width="5%"><span>分类</span></th>
								<th width="10%"><span>添加时间</span></th>
								<th width="10%"><span>上架时间</span></th> 
								<th width="8%"><span>图书价格</span></th>
								<th width="8%"><span>是否在售</span></th>
								<th width="4%"><span>推荐</span></th>
								<th width="4%"><span>特价</span></th>
								<th width="4%"><span>精品</span></th>
								<th width="4%"><span>畅销</span></th> 
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
											<td>
											<fmt:formatDate value="${book.publishTime}" type="both"/></td>
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
									<td><fmt:formatDate value="${book.upTime}" type="both"/></td>
									<td>${book.nowPrice }</td>
									<td><c:if test="${book.shopState==2}">
											否/下架时间: <br />
											<fmt:formatDate value="${book.dropTime}" type="both"/>
										</c:if> <c:if test="${book.shopState==1}">
											是
										</c:if></td>
									<td>
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
										</c:choose></td>
									
								
										<td> 
										<c:if test="${book.status==1 }">
											正常
										</c:if>
										<c:if test="${book.status==2 }">
											冻结
										</c:if>
										</td>
									<td>
									    <a class="am-btn am-btn-default am-btn-xs am-text-secondary" title="详情" href="${ctx}/admin/book/info/${book.bookId}"><span class="am-icon-copy"></span>详情</a>
										<a class="am-btn am-btn-default am-btn-xs am-text-secondary" title="编辑" href='${ctx}/admin/book/toupdate/${book.bookId}'><span class="am-icon-pencil-square-o"></span>编辑</a>
										<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" href="javascript:void(0)" onclick="delBook('${book.bookName }',${book.bookId },'3')"><span class="am-icon-trash-o"></span>删除</a>
										<c:if test="${book.status==2 }">
											<a class="am-btn am-btn-default am-btn-xs am-text-secondary" href="javascript:void(0)" onclick="delBook('${book.bookName }',${book.bookId},'1')">解冻</a>
										</c:if> <c:if  test="${book.status==1 }">
											<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" href="javascript:void(0)" onclick="delBook('${book.bookName }',${book.bookId},'2')">冻结</a>
										</c:if>
								
									</td>

								</tr>
							</c:forEach>
                                </c:if>
                                <c:if test="${bookList.size()==0||bookList==null}">
                                   <tr>
									<td colspan="17">
										<div data-am-alert=""
											 class="am-alert am-alert-secondary mt20 mb50">
											<center>
												<big> <i class="am-icon-frown-o vam"
														 style="font-size: 34px;"></i> <span class="vam ml10">还没有相关数据！</span></big>
											</center>
										</div>
									</td>
								</tr>
                                </c:if>
                            </tbody>         
		         </table>
   </div>
 </div>
</div>
<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
</body>
</html>
