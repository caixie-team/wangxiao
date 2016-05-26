<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>播放记录</title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/uc/home" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>播放记录</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="comm-main animated fadeIn">
				<div>
					<section class="v-card-txt-title">
						<div class="edit-btn">
							<a href="javascript: void(0)" title="">编辑</a>
						</div>
						<span>播放记录列表</span>
						<div class="clear"></div>
					</section>
				</div>
				<c:if test="${empty  studylist}">
				<!-- 无数据时提示 开始 -->
				<div class="undataBox">
					<span class="undata-icon">&nbsp;</span>
					<span>暂无播放记录</span>
				</div>
				</c:if>
				<!-- 无数据时提示 结束 -->
				<c:if test="${studylist!=null&&studylist.size()>0}">
				<div id="aCoursesList" class="i-box">
					<div class="tjc-box">
						<div class="courese-list-wrap edit-play-record">
							<ul id="studyContent">
								<c:forEach items="${studylist}" var="study" varStatus="index">
								<li value="${study.id}">
									<a href="/mobile/course/info/${study.courseId}" title="">
										<section class="c-l-pic">
											<em>&nbsp;</em>
											<img xsrc="<%=staticImageServer %>/${study.mobileLogo }" src="${ctx}/static/mobile/img/sprite.gif" alt="">
										</section>
										<h2 class="courese-l-title">
											${study.kpointName }
										</h2>
										<section class="c-l-attr">
											<span>
												<tt>${study.courseName}</tt>
											</span>
										</section>
										<section class="c-l-attr">
											<span>
												<tt><fmt:formatDate value="${study.updateTime}" pattern="yyyy-MM-dd HH:mm" /></tt>
											</span>
										</section>
									</a>
								</li>
								</c:forEach>
							</ul>
						</div>
					</div>

					<section>
						<section class="onload-more" style="display: none">
							<a title="加载更多..." href="javascript: void(0)">
								<img src="${ctx }/static/mobile/img/loading.gif">
								<span>正在努力加载中...</span>
							</a>
						</section>
					</section>
				</div>
				</c:if>
				<!-- /课程记录删除确认 -->
				<div class="ci-bt-box edit-b-wrap" style="display: none;">
					<section class="ci-bt-elem">
						<div class="ask-inp-box edit-b-box">
							<section class="c-buy-btn">
								<span class="u-o-c-btn u-o-c-btn-loo all-cheack-btn">全选</span>
								<span class="u-o-c-btn u-o-c-btn-loo del-btn">删除</span>
							</section>
						</div>
					</section>
				</div>
				<!-- /底部按钮区域 -->
				<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			</section>
			<!-- body main -->
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			scrollLoad(); //滚动响应加载课程图片
			editeFun(); //编辑播放记录交互效果
			$("#pageCurrentPage").val(1);
			//加载分页
			$(window).bind('scroll',function(){
				var sTop = document.documentElement.scrollTop + document.body.scrollTop;
				var sHeight = document.documentElement.scrollHeight;
				var windowHeight = $(this).height();
				//当滚动到最底下时加载数据
				if(sHeight==(sTop+windowHeight)){
					var totalPageSize=parseInt($("#totalPageSize").val());//总页数
					var nextPage=parseInt($("#pageCurrentPage").val())+1;//下一页
					if(nextPage<=totalPageSize){
						$("#pageCurrentPage").val(nextPage);
						$(".onload-more").show();
						$.ajax({
							url:'/mobile/uc/course/ajax/study',
							type:"post",
							data:{"page.currentPage":nextPage},
							dataType:"text",
							success:function(result){
								$("#studyContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
		})
		
		function delStudy(){
			var studys=$(".active");
			var ids="";
			studys.each(function() {
				ids+=$(this).val()+",";
			});
			if(ids.length==0){
				dialog('提示','请选择要删除的播放记录','',0);
				return;
			}
			$.ajax({
				url:'/mobile/uc/study/del',
				type:"post",
				data:{"ids":ids},
				dataType:"json",
				success:function(result){
					if(result.success){
						dialog('提示','删除播放记录成功','/mobile/uc/course/study',1);
					}else{
						dialog('提示','删除失败，请稍候再试','/mobile/uc/course/study',1);
					}
				}
			});
		}
		
		function editeFun() {
			//点击编辑交互
			var _eLi = $(".edit-play-record>ul>li"),
				_eBox = $(".edit-b-wrap");
			$(".edit-btn>a").click(function() {
				var _this = $(this),
					_acb = _eBox.find(".all-cheack-btn"),
					_db = _eBox.find(".del-btn");

				if (_this.text() == "编辑") {
					_this.text("完成");
					_eLi.find(".c-l-pic>em").show();
					_eBox.show();
				} else {
					_eLi.removeClass("active");
					_acb.text("全选");
					_db.removeClass("u-o-c-btn-gre").addClass("u-o-c-btn-loo").text("删除");
					_db.attr("onclick","");
					_this.text("编辑");
					_eLi.find(".c-l-pic>em").hide();
					_eBox.hide();
				};

				_eLi.each(function() {
					 var _this = $(this);
					 _this.click(function() {
					 	if (!_this.hasClass("active")) {
					 		_this.addClass("active");
					 		var aLen = _eLi.filter(".active").size();
							_db.removeClass("u-o-c-btn-loo").addClass("u-o-c-btn-gre").text("删除（"+aLen+")");
							_db.attr("onclick","delStudy()");
					 	} else {
					 		_this.removeClass("active");
					 		var aLen = _eLi.filter(".active").size();
							_db.removeClass("u-o-c-btn-loo").addClass("u-o-c-btn-gre").text("删除（"+aLen+")");
							_db.attr("onclick","delStudy()");
					 	}
					 	return false;
					 })
				})

				_acb.click(function() {
					if (!_eLi.hasClass("active")) {
						_acb.text("取消全选");
						_eLi.addClass("active");
						var aLen = _eLi.filter(".active").size();
						_db.removeClass("u-o-c-btn-loo").addClass("u-o-c-btn-gre").text("删除（"+aLen+")");
						_db.attr("onclick","delStudy()");
					} else {
						_acb.text("全选");
						_eLi.removeClass("active");
						var aLen = _eLi.filter(".active").size();
						_db.removeClass("u-o-c-btn-gre").addClass("u-o-c-btn-loo").text("删除");
						_db.attr("onclick","");
					};
				})
			})
		}
	</script>
</body>
</html>