<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程详情</title>
	
</head>
<body>
	<div class="m-ptb54">
		<input type="hidden" value="${course.id }" id="hiddenCourseId">
		<div class="mt-54">
			<!-- body main -->
			<section class="comm-main animated fadeIn">
				<!-- 课程详情 开始 -->
				<article class="v-box">
					<section class="li-top-fixed">
						<!-- 嵌套H5播放器位置 开始 -->
						<div class="v-play-box" id="playBox">
							<aside class="v-p-btn" onclick="defaultPlayer()">&nbsp;</aside>
							<h6 class="v-p-name"><span>${course.name }</span></h6>
						</div>
						<!-- 嵌套H5播放器位置 结束 -->
						<div class="v-card-title-box">
							<div class="v-card-title">
								<ul class="col-3" id="item-l-ul">
									<li class="current"><a href="javascript: void(0)" onclick="changeInfo('info',this)">课程简介</a></li>
									<li id="catalogLi"><a href="javascript: void(0)" onclick="changeInfo('catalog',this)">课程目录</a></li>
									<li><a href="javascript: void(0)" onclick="changeInfo('discuss',this)">讨论区</a></li>
								</ul>
							</div>
						</div>
						<!-- /选项卡标签 -->
					</section>

					<div>
						<!-- 选项卡内容区域 开始 -->
						<section style="display: block;" id="info">
							<div>
								<section class="v-card-txt-title">
									<span>课程详情</span>
								</section>
								<article class="v-c-txt">
									<div class="v-c-txt-p">${course.context }</div>
								</article>
							</div>

							<div>
								<section class="v-card-txt-title">
									<span>名师简介</span>
								</section>
								<article class="v-c-txt">
									<div class="v-teacher-list">
										<ul>
											<c:forEach items="${course.teacherList }" var="teacher">
											<li>
												<div class="oIconDesc" onclick="window.location.href='/mobile/teacher/${teacher.id}'">
													<aside class="oIcon">
														<span class="sm-u-head"><img src="<%=staticImageServer %>${teacher.picPath}" width="50" height="50"></span>
													</aside>
													<h2 class="oIconName">${teacher.name }</h2>
													<section class="oIconTxt">${teacher.education }</section>
												</div>
											</li>
											</c:forEach>
										</ul>
									</div>
								</article>
							</div><!-- /名师简介 -->
						</section>
						<!-- /课程简介 -->
						
						<!-- 课程目录 -->
						<section id="catalog" style="display: none;">
						
						</section>
						<!-- /课程目录 -->
						
						<!-- 讨论区 -->
						<section id="discuss" style="display: none;">
							
						</section>
						<!-- /讨论区 -->

					</div>
					<!-- 选项卡内容区域 开始 -->
				</article>
				<!-- 课程详情 结束 -->

				<div class="ci-bt-box">
					<section class="ci-bt-elem">
						<aside class="go-back">
							<a href="/mobile/course/list" title="">&nbsp;</a>
						</aside>
						<input type="hidden" id="isPay" value="${course.isPay }"/>
						<c:if test="${isok==false&&course.isPay>0 }">
						<div class="buy-btn">
							<a title="" href="/mobile/order/add?courseId=${course.id }">立即购买</a>
						</div>
						</c:if>
					</section>
				</div>
				<!-- /底部按钮区域 -->
			
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			ltop(); //选项卡区域定位
		})
	    // li-top fixed absolute
	    var ltop = function() {
	    	var ltEle = $(".v-card-title");
	    	function ltFun() {
	    		var sTop = document.documentElement.scrollTop + document.body.scrollTop;
	    		if (sTop > 240) {
	    			ltEle.css({"position" : "fixed" , "top" : "0"});
	    		} else {
	    			ltEle.css({"position" : "absolute"});
	    		};
	    	}
	    	$(window).bind("scroll" , ltFun);
	    	ltFun();
	    }
		function changeInfo(id,object){
			if($(object).parent().attr("class")=="current"){
				return;
			}
			$(object).parent().siblings().removeClass("current");
			$(object).parent().addClass("current");
			if(id=='info'){
				$("#catalog").hide();
				$("#discuss").hide();
				$("#info").show();
			}else if(id=="catalog"){
				changeCatalog(0);
			}else{
				changeDiscuss();
			}
		}
		
		function changeCatalog(currentCourseId){
			$.ajax({
				url:'/mobile/course/ajax/info/'+$("#hiddenCourseId").val()+'/'+currentCourseId,
				type:'post',
				dataType:'text',
				async:false,
				success:function(result){
					$("#catalog").html(result);
					$("#discuss").hide();
					$("#info").hide();
					$("#catalog").show();
					cmOc();
				}
			});
		}
		
		function changeDiscuss(){
			$.ajax({
				url:'/mobile/course/ajax/assess?courseId='+$("#hiddenCourseId").val(),
				type:'post',
				dataType:'text',
				async:false,
				success:function(result){
					$("#discuss").html(result);
					$("#catalog").hide();
					$("#info").hide();
					$("#discuss").show();
				}
			});
		}
		
		function addAssess(){
			if(!isLogin()){
		        window.location.href='/mobile/login';
		        return;
		    }
			if($("#content").val()==null||$("#content").val()==''){
				dialog('提示','请输入评论内容','',0);
		        return;
		    }
			$.ajax({
				url:'/mobile/assess/add?courseAssess.kpointId=0&courseAssess.courseId='+$("#hiddenCourseId").val()+'&courseAssess.content='+$("#content").val(),
				type:'post',
				dataType:'json',
				async:false,
				success:function(result){
					if(result.success){
						changeDiscuss();
					}
					
				}
			});
			
		}
		//默认播放
		function defaultPlayer(){
			$("#item-l-ul>li").removeClass("current");
			$("#catalogLi").addClass("current");
			changeCatalog(0);
			getPlayerBox(0);
		}
		
		
		/**
		 * 获得播放器的html
		 * @param kpointId节点id
		 * @param courseId 课程id
		 * @param name 弹窗显示的课程名称（title）
		 */
		function getPlayerBox(kpointId){
			if(kpointId==0){
				kpointId=$("#defaultKpointId").val();
			}
			$(".kpointLi").removeClass("current");
			$("#"+kpointId+"li").addClass("current");
			var isPay=$("#isPay").val();
			if(defaultKpointId==0&&isPay==0){
				dialog('提示','暂无可以播放的视频','',0);
				return;
			}else if(defaultKpointId==0&&isPay>0){
				dialog('提示','暂无可以试听的视频','',0);
				return;
			}
			$.ajax({
				url : "/front/ajax/getKopintHtml",
				data : {
					"kpointId" : kpointId,
					"courseId" : $("#hiddenCourseId").val()
				},
				type : "post",
				dataType : "text",
				success : function(result) {
					if(result=='false'){
						dialog('提示','该课程暂不支持试听，请购买后观看。','',0);
					}else{
						$("#playBox").html(result);
						$("#playBox").addClass("v-play-swf");
						window.location.href='#playBox';
						addPlayTimes($("#hiddenCourseId").val(),kpointId);
					}
					
				}
					
			});
		}
		
		/**
		 * 记录播放次数
		 * @param courseId 课程id
		 * @param kpointId 节点id
		 */
		function addPlayTimes(courseId,kpointId){
			$.ajax({
				url :  baselocation + "/course/playertimes",
				data : {
					"kpointId" : kpointId,
					"courseId" : courseId
				},
				type : "post",
				dataType : "text",
				async:false,
				success : function(result) {
				}
			});
		}
		
		// courese menu tree open and close
	    var cmOc = function() {
	    	$(".courese-menu-tree>dl").each(function() {
	    		var _this = $(this),
	    			_cT = _this.children("dt"),
	    		    _jJ = _this.find(".c-m-t-level-1").children(".jJ"),
	    		    _cD = _this.children("dd");
	    		_cT.click(function() {
	    			if (_jJ.hasClass("ji") && _cD.length > 0) {
	    				_jJ.removeClass("ji").addClass("ja");
	    				_cD.hide();
	    			} else {
	    				_jJ.removeClass("ja").addClass("ji");
	    				_cD.show();
	    			};
	    		})
	    	})
	    };
	</script>
	</script>
</body>
</html>