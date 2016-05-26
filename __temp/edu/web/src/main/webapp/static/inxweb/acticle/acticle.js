//好文推荐
function articleRecommend() {
	$.ajax({
		url : baselocation + '/front/ajax/recommend',
		type : 'post',
		async : true,
		dataType : 'text',
		success : function(result) {
			$(".articleRecommend").html(result);
		}
	});
}

// 增加文章浏览量
function updateArticleClickNum() {
	jQuery.ajax({
		url : baselocation+'/front/updateArticleClickNum/' + articleId,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.success == true) {
				jQuery(".clickNum").text(result.entity.clickNum);
				$(".praiseCount").html(result.entity.praiseCount);
			}
		}
	});
}
