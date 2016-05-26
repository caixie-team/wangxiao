var totalPageSize=0;//总页码
var currentPage =0;
var totalPage =0;

/**
 * ajax分页初始化
 * @param url 访问路径
 * @param dataType 返回类型
 * @param isAccess 加载完成后是否访问
 * @param callback 回调函数
 */
function initAjaxPage(url,dataType,callback,isAccess){
	/**
	 * 访问路径
	 */
	this.url = url;
	/**
	 * 访问类型
	 * @type {string}
	 */
	this.type = "post";
	/**
	 * 返回类型
	 */
	this.dataType=dataType;
	/**
	 * 回调函数
	 */
	this.callback = callback;

	// 加载完成后是否访问
	if(isAccess==true||isAccess=='true'){
		goPageAjax(1);
	}
}
function showPageNumber() {
	var pageHtml = "";
	var maxNum_new = currentPage > 4 ? 6 : 7 - currentPage;//最大显示页码数
	var discnt = 1;
	for ( var i = 3; i > 0; i--) {
		if (currentPage > i) {
			pageHtml = pageHtml + "<li><a href='javascript:goPageAjax("
					+ (currentPage - i) + ")'>" + (currentPage - i)
					+ "</a></li>";
			discnt++;
		}
		;
	}
	pageHtml = pageHtml + '<li class="active"><a href="javascript:void(0)">'
			+ currentPage + '</a></li>';
	for ( var i = 1; i < maxNum_new; i++) {
		if (currentPage + i <= totalPage && discnt < 7) {
			pageHtml = pageHtml + "<li><a href='javascript:goPageAjax("
					+ (currentPage + i) + ")'>" + (currentPage + i)
					+ "</a></li>";
			discnt++;
		} else {
			break;
		}
		;
	}
	$(pageHtml).insertBefore("#nextpage");
}

//到某页方法
function goPageAjax(pageNum){
	$.ajax({
		url:url,
		type:type,
		data: {"page.currentPage":pageNum},
		dataType:dataType,
		success:function(result){
			callback(result);
		}
	});
	//queryAjaxPage(pageNum);
}

