<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPage(1);
 --%>
<c:if test="${page != null && page.totalResultSize>0 }">
	<div class="am-cf adm-page-wrap">
		  <div class="am-fl mt20">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</div>
		  <div class="am-fr">
		  <ul class="am-pagination">
		  	<c:choose>
				   <c:when test="${page.first}">
					  <li class="am-disabled"><a href="#">首页</a></li>
				   </c:when>
				   <c:otherwise>
				  <li><a href="javascript:goPage(1);">首页</a></li>
				   </c:otherwise>
			</c:choose>
		    <c:choose>
			   <c:when test="${page.first}">
			   	<li class="am-disabled" id="backpage"><a href="javascript:void(0)">«</a></li>
			   </c:when>
			   <c:otherwise>
			   <li  id="backpage"><a href="javascript:goPage(${page.currentPage-1 });">«</a></li>
			   </c:otherwise>
			</c:choose>
		    <c:choose>
			   <c:when test="${page.last}">
			   	<li id="nextpage" class="am-disabled"><a href="javascript:void(0)">»</a></li>
			   </c:when>
			   <c:otherwise>
			   <li id="nextpage"><a href="javascript:goPage(${page.totalPageSize });">»</a></li>
			   </c:otherwise>
			</c:choose>
			<c:choose>
					<c:when test="${page.last}">
					 <li class="am-disabled"><a href="javascript:void(0)">尾页 </a></li>
				   </c:when>
				   <c:otherwise>
				<li ><a href="javascript:goPage(${page.totalPageSize })">尾页 </a></li>
				   </c:otherwise>
			</c:choose>
		    </ul>
		  </div>
	 </div>
</c:if>

<script type="text/javascript">
	var totalPageSize;//总页码
	if("${page.totalPageSize}"==""){
		totalPageSize=0;
	}else{
		totalPageSize=Number('${page.totalPageSize}');
	}

	
    function goPage(pageNum){
    	if(/^\d+$/.test(pageNum)==false) {
    		return;
    	}
    	if(pageNum < 1) {
    		pageNum = 1;
    	}
    	if(pageNum > totalPageSize) {
    		if(totalPageSize>0){
    			pageNum = totalPageSize;
    		}else{
    			pageNum=1;
    		}
    	}
		$("#pageCurrentPage").val(pageNum);
		$("#searchForm").submit();
    }
    
    function showPageNumber() {
		var page_currentPage;
		if("${page.currentPage}"==""){
			page_currentPage=0;
		}else{
			page_currentPage=Number('${page.currentPage}');
		}
    	var currentPage = (page_currentPage-1)<1?1:page_currentPage;

    	var totalPage;
		if("${page.totalPageSize}"==""){
			totalPage=0;
		}else{
			totalPage=Number('${page.totalPageSize}');
		}

    	var pageHtml="";
    	var maxNum_new = currentPage>4?6:7-currentPage;//最大显示页码数
    	var discnt=1;
    	for(var i=4; i>0; i--) {
    		if(currentPage>i) {
    			pageHtml = pageHtml + "<li><a href='javascript:goPage("+(currentPage-i)+")'>"+ (currentPage-i) +"</a></li>";
    			discnt++;
    		};
    	}
    	pageHtml = pageHtml + '<li class="am-active"><a href="javascript:void(0)">'+currentPage+'</a></li>';
    	for(var i=1; i<maxNum_new; i++) {
    		if(currentPage+i<=totalPage && discnt<6) {
    			pageHtml = pageHtml + "<li><a href='javascript:goPage("+(currentPage+i)+")'>"+ (currentPage+i) +"</a></li>";
    			discnt++;
    		} else {
    			break;
    		};
    	}
    	$(pageHtml).insertBefore("#nextpage");
    }
    //跳转到页面
    function goPageByInput() {
    	var pageNo = document.getElementById("pageNoIpt").value;
    	if(/^\d+$/.test(pageNo)==false) {
    		alert("只能输入整数，请重新输入！");
    		document.getElementById("pageNoIpt").value='';
    		return;
    	}
    	goPage(pageNo);
    };
    //显示中间部分页数
    showPageNumber();
</script>