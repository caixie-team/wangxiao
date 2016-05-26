var currentPage =0;
var totalPage =0;
function goPage(pageNum) {
	if (/^\d+$/.test(pageNum) == false) {
		return;
	}
	if (pageNum < 1) {
		pageNum = 1;
	}
	if (pageNum > totalPage) {
		if (totalPage > 0) {
			pageNum = totalPage;
		} else {
			pageNum = 1;
		}
	}
	$("#pageCurrentPage").val(pageNum);
	$("#searchForm").submit();
}

function showPageNumber() {
	var pageHtml = "";
	var maxNum_new = currentPage > 4 ? 6 : 7 - currentPage;//最大显示页码数
	var discnt = 1;
	for ( var i = 3; i > 0; i--) { //<a href="" title="">3</a>
		if (currentPage > i) {
			pageHtml = pageHtml + "<a href='javascript:goPage("
					+ (currentPage - i) + ")'>" + (currentPage - i)
					+ "</a>";
			discnt++;
		}
		;
	}
	pageHtml = pageHtml + '<a class="undisable current" href="javascript:void(0)">'
			+ currentPage + '</a>';
	for ( var i = 1; i < maxNum_new; i++) {
		if (currentPage + i <= totalPage && discnt < 7) {
			pageHtml = pageHtml + "<a href='javascript:goPage("
					+ (currentPage + i) + ")'>" + (currentPage + i)
					+ "</a>";
			discnt++;
		} else {
			break;
		}
	}
	$(pageHtml).insertBefore("#nextpage");
}
//跳转到页面
function goPageByInput() {
	var pageNo = document.getElementById("pageNoIpt").value;
	if (/^\d+$/.test(pageNo) == false) {
		alert("只能输入整数，请重新输入！");
		document.getElementById("pageNoIpt").value = '';
		return;
	}
	goPage(pageNo);
};