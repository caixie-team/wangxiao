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
		<div>
			<!-- /全局头部位置 header -->
			<header id="header">
				<section class="h-wrap">
					<div class="menu-btn">
						<a href="/mobile/course/list" title="" class="go-history">&nbsp;</a>
					</div>
					<h2 class="commHeadTitle"><span>课程详情</span></h2>
					<!-- commHeadTitle -->
					<div class="ht-r">
						<aside class="search-icon">
							<svg onclick="$('#fenxiangDiv').show()" x="0px" y="0px" width="26px" height="26px" viewBox="0 0 1024 1024" enable-background="new 0 0 26 26" xml:space="preserve">
							  <path fill="#ffffff" d="M836.061867 743.8016c-75.400533 0-136.533333-61.149867-136.533333-136.533333 0-13.090133 1.9456-25.719467 5.376-37.700267l-398.8992-167.304533c-23.6032 40.891733-67.669333 68.471467-118.237867 68.471467-75.383467 0-136.533333-61.149867-136.533333-136.618667 0-75.656533 61.149867-136.789333 136.533333-136.789333 39.492267 0 75.008 16.878933 99.925333 43.7248l257.109333-184.849067c-10.001067-19.012267-15.7184-40.6528-15.7184-63.6416 0-75.4176 60.893867-136.5504 136.277333-136.5504s136.533333 61.1328 136.533333 136.5504c0 75.383467-61.149867 136.516267-136.533333 136.516267-40.106667 0-76.066133-17.425067-100.9664-44.9536l-256.682667 184.541867c10.564267 19.421867 16.571733 41.728 16.571733 65.450667 0 12.7488-1.860267 25.002667-5.102933 36.6592l399.2064 167.441067c23.722667-40.328533 67.464533-67.498667 117.6576-67.498667 75.400533 0 136.533333 61.1328 136.533333 136.5504C972.5952 682.651733 911.4624 743.8016 836.061867 743.8016zM665.3952 94.926933c56.4736 0 102.4-45.9264 102.4-102.382933 0-56.4736-45.9264-102.417067-102.4-102.417067s-102.314667 45.943467-102.314667 102.417067C563.080533 49.000533 608.938667 94.926933 665.3952 94.926933zM187.784533 231.460267c-56.4736 0-102.4 45.9264-102.4 102.656 0 56.541867 45.9264 102.485333 102.4 102.485333s102.4-45.9264 102.4-102.485333C290.184533 277.403733 244.258133 231.460267 187.784533 231.460267zM836.061867 504.8512c-56.4736 0-102.4 45.943467-102.4 102.417067s45.9264 102.4 102.4 102.4 102.4-45.9264 102.4-102.4S892.535467 504.8512 836.061867 504.8512z" transform="translate(0, 812) scale(1, -1)"/>
							</svg>
						</aside>
						<aside class="record-icon" onclick="window.location.href='/mobile/uc/course/study'">
							<svg x="0px" y="0px" width="32px" height="32px" viewBox="0 0 1024 1024" enable-background="new 0 0 32 32" xml:space="preserve">
							  <path fill="#ffffff" d="M512 629.333c-188.512 0-341.333-152.821-341.333-341.333s152.821-341.333 341.333-341.333c188.515 0 341.333 152.821 341.333 341.333s-152.819 341.333-341.333 341.333zM512-10.667c-164.947 0-298.667 133.717-298.667 298.667 0 164.947 133.72 298.667 298.667 298.667s298.667-133.72 298.667-298.667c0-164.949-133.72-298.667-298.667-298.667zM512.541 458.307h-42.667v-213.336h213.331v42.667h-170.664z" transform="translate(0, 812) scale(1, -1)"/>
							</svg>
						</aside>
						<div class="clear"></div>
					</div>
					<!-- /ht-r -->
				</section>
			</header>
			<!-- /全局头部位置 header -->
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
						<input type="hidden" id="isok" value="${isok }"/>
						<c:if test="${isok==false&&course.isPay>0 }">
						<div class="buy-btn">
							<c:if test="${APPID!=null}">
								<a title="" href="${weixin_url}">立即购买</a>
							</c:if>
							<c:if test="${APPID==null}">
								<a title="" href="/mobile/order/add?courseId=${course.id}">立即购买</a>
							</c:if>
						</div>
						</c:if>
						<c:if test="${isok==true||course.isPay==0 }">
							<input type="hidden" value="yes" id="playPower"/>
						</c:if>
					</section>
				</div>
				<!-- /底部按钮区域 -->
			
			</section>
			<!-- body main -->
		</div>
	</div>
	<!-- 分享弹出框 -->
	<div id="fenxiangDiv" style="display: none">
		<div class="bg-shadow" onclick="$('#fenxiangDiv').hide()"></div>
		<div class="dialog-ele animated0d8s bounceInDown" style="margin-top: -60px;border-radius:8px;">
			<header class="d-head">
				<h5>我要分享到：</h5>
				<a href="javascript:void(0)" onclick="$('#fenxiangDiv').hide()" class="close">
					<i class="icon-close">&nbsp;</i>
				</a>
			</header>
			<div class="dcWrap">
				<div class="d-tips-0">
					<div>
						<div class="">
							<span class="share-wrap fl">
								<a target="_blank" href="http://service.weibo.com/share/mobile.php?url=${ctx}/mobile/course/info/${course.id }&searchPic=true&pic=<%=staticImageServer%>${course.logo }&type=icon&appkey=3687700982&language=zh_cn&title=%23改变，从“心”开始%23${course.name }，一门非常棒的心理学课程—育宁网校" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博" style="position: relative;width:50px;height:50px;background: #D25E52 none;text-align: center;border-radius:50px;line-height:46px;text-indent:0;display:inline-block;">
									<i class="icon-weibo">&nbsp;</i>
								</a>
								<div class="clear"></div>
								<p>新浪微博</p>
							</span>
							<span class="share-wrap fl">
								<a target="_blank" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=${ctx}/mobile/course/info/${course.id }&showcount=0&summary=${course.title}&desc=我在育宁网校看到一门非常不错的课程，可能适合你哟。&title=${course.name }&pics=<%=staticImageServer%>${course.logo}" class="bds_tsina" data-cmd="tsina" style="position: relative;width:50px;height:50px;background: #e9aa3b none;text-align: center;border-radius:50px;line-height:46px;text-indent:0;display:inline-block;">
									<i class="icon-qqkj">&nbsp;</i>
								</a>
								<div class="clear"></div>
								<p>QQ空间</p>
							</span>
							<span class="share-wrap fl">
								<a target="_blank" href="http://share.v.t.qq.com/index.php?c=share&a=index&url=${ctx}/mobile/course/info/${course.id }&title=%23改变，从“心”开始%23${course.name }，一门非常棒的心理学课程—育宁网校——${course.title}&pic=<%=staticImageServer%>${course.logo}" class="bds_tsina" data-cmd="tsina" style="position: relative;width:50px;height:50px;background: #49aef6 none;text-align: center;border-radius:50px;line-height:46px;text-indent:0;display:inline-block;">
									<i class="icon-txwb">&nbsp;</i>
								</a>
								<div class="clear"></div>
								<p>腾讯微博</p>
							</span>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
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
	    			ltEle.css({"position" : "fixed" , "top" : "54px"});
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
			var isok=$("#isok").val();
			$.ajax({
				url:'/mobile/course/ajax/info/'+$("#hiddenCourseId").val()+'/'+currentCourseId+'/'+isok,
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
			getPlayerBox(0,1);
		}


		/**
		 * 获得播放器的html
		 * @param kpointId节点id
		 * @param courseId 课程id
		 * @param name 弹窗显示的课程名称（title）
		 */
		function getPlayerBox(kpointId,isfree){
			if(kpointId==0){
				kpointId=$("#defaultKpointId").val();
			}
			$(".kpointLi").removeClass("current");
			$("#"+kpointId+"li").addClass("current");
			var isok=$("#isok").val();
			if(kpointId==0&&isok=='true'){
				dialog('提示','暂无可以播放的视频','',0);
				return;
			}else if(kpointId==0&&isok=='false'){
				dialog('提示','您未报考此证书课程，试听请前往试听专区','',0);
				return;
			}else if(isfree != 1&&isok=='false'){
				dialog('提示', "您未报考此证书课程，试听请前往试听专区",'',0);
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
					$("#playBox").html(result);
					$("#playBox").addClass("v-play-swf");
					window.location.href='#playBox';
					addPlayTimes($("#hiddenCourseId").val(),kpointId);
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