	$(function() {
		eBg();
		effect();
		allItem();
		leftMenuScroll();
		subTitleScroll();
		answerFun();
		analysis();
		answerScroll();
		goTop();
		showusername();
	});
	
	/**
	 * 显示用户名
	 */
	function showusername(){
        $.ajax({
            url : "/user/loginuser",
            data : {},
            type : "post",
            dataType : "json",
            cache : false,
            async : false,
            success : function(result) {
                if(result.success){
                    $(".newsLi,.outLi,.userNameLi").removeClass('undis');
                    $("#loginMsgOn").show();
                    $("#loginMsgOFF").hide();
                    var entity = result.entity;
                    if(isNotNull(entity)){
                        var username="";
                        if( entity && entity.nickname && isNotEmpty(entity.nickname)){//昵称
                            username=( entity.nickname);
                        }else{
                            username=(entity.email);//邮箱
                        }
                        if(isNotEmpty(username)){
                            $("#userName").attr("title",username);
                            username = username.substring(0,14);
                            $("#userName").html(username);
                        }
                    }
                }else{
                    $(".forgetPasswordLi,.registerLi,.loginLi").removeClass('undis');
                }
            }
        });
	}
	function eBg() {
		var winW = parseInt(document.documentElement.offsetWidth, 10) + parseInt(document.documentElement.scrollLeft || document.body.scrollLeft, 10);
		$(".e-bg>img").css({"width" : winW});
	}

	function effect() {
		//隔行色
		$(".m-h-lx li:odd").css("background-color" , "#f8f8f8");
		//考过历史记录
		$(".kg-wrap").hover(function() {
			$(this).children(".k-btn").addClass("hover");
			$(this).children(".kg").show();
		}, function() {
			$(this).children(".k-btn").removeClass("hover");
			$(this).children(".kg").hide();
		});
		
		$(function(){
			//input["radio"]
			$(".t-p-is-options label .o-radio").each(function() {
				$(this).parent().click(function() {
					if ($(this).children("input").is(":checked")) {
						$(this).addClass("current").siblings().removeClass("current");
						var numInder = $(this).attr("numInder");
						$("#datikaCurrent"+numInder).addClass("current");
					} else {
						$(this).removeClass("current");
					};
				});
				//如果该选项是选择状态按钮选择答题卡选中
				if ($(this).parent().children("input").is(":checked")) {
					// 选项选中
					$(this).parent().addClass("current").siblings().removeClass("current");
					//答题卡选中
					var numInder = $(this).parent().attr("numInder");
					$("#datikaCurrent"+numInder).addClass("current");
				} else {
					$(this).parent().removeClass("current");
				};
			});
			//input["checkebox"]
			$(".t-p-is-options label .o-checkbox").each(function() {
				$(this).parent().click(function() {
					if ($(this).children("input").is(":checked")) {
						$(this).addClass("current");
						var numInder = $(this).attr("numInder");
						$("#datikaCurrent"+numInder).addClass("current");
					} else {
						$(this).removeClass("current");
					};
				});
				//如果该选项是选择状态按钮选择答题卡选中
				if ($(this).parent().children("input").is(":checked")) {
					$(this).parent().addClass("current");
					var numInder = $(this).parent().attr("numInder");
					$("#datikaCurrent"+numInder).addClass("current");
				} else {
					$(this).parent().removeClass("current");
				};
			});
		});
		
	
		
		//考点详细描述层
		$(".a-kj-show").hover(function(){
			str = "";
			var pointId = $(this).attr("pointId");
			var akjshow = $(this);
			if(pointId!=0){
				queryPointsNameByParentId(pointId);
				if(str.trim()!=""){
					akjshow.children(".a-kj-desc").show();
					akjshow.children(".a-kj-desc").children(".b-fff").children(".notetext").html(str);
				}
			}
		}, function(){
			$(this).children(".a-kj-desc").hide();
		});
		//所有二级三级科目
		$(".p-i-s-n-list dl").each(function() {
			$(this).hover(function() {
				$(this).addClass("hover");
				$(this).children("dd").show();
			}, function() {
				$(this).removeClass("hover");
				$(this).children("dd").hide();
			});
		});
	}
	var point="";
	var str = "";
	function queryPointsNameByParentId(pointId){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:baselocation+"/point/queryPointsNameByParentId.do",
			data:{"point.id":pointId},
			async:false,
			cache:false,
			success:function(result){
				if(result.success==true){
					pointName = result.entity;
					if(str==null&&str.trim()==""){
						return false;
					}else{
						str=pointName;
					}
				}
			}
	});
	}
		//左右滚动
	function slideScroll(oBox, prev, next, oUl, speed, autoplay) {
		var oBox = $(oBox),
			prev = $(prev),
			next = $(next),
			oUl = $(oUl).find("ul"),
			ulW = oUl.find("li").outerWidth(true),
			oLi = oUl.find("li").length;
			s = speed,
			timer = null;
			oUl.css("width" , oLi * ulW + "px");
		//click prev
		prev.click(function() {
			oUl.animate({"margin-left" : -ulW}, function() {
				oUl.find("li").eq(0).appendTo(oUl);
				oUl.css("margin-left" , 0);
			});
		});
		//click next
		next.click(function() {
			oUl.find("li:last").prependTo(oUl);
			oUl.css("margin-left", -ulW);
			oUl.animate({"margin-left" : 0});
		});
		//autoplay
		if (autoplay === true) {
			timer = setInterval(function() {
				prev.click();
			}, s * 1000);
			oBox.hover(function() {
				clearInterval(timer);
			}, function() {
				timer = setInterval(function() {
					prev.click();
				}, s * 1000);
			});
		};
	}
		//点击全部科目类
	function allItem() {
		var oAi = $(".poin-item-sub-nav");
		document.onclick = function(e) {
			$(".poiter-item").removeClass("hover");
		};
		
		$(".poiter-item>a").click(function(e) {
			loadsubject(true);
			stopFunc(e);
		});
		
		
		/*$(".poiter-item>a").click(function(e) {
			if (oAi.is(":hidden")) {
				$(this).parent().addClass("hover");
			} else {
				$(this).parent().removeClass("hover");
			};
			stopFunc(e);
		});*/
		oAi.click(function(e) {
			stopFunc(e);
		});
	}
		//事件监听
	function stopFunc(e) {
		document.all ? event.cancelBubble = true : e.stopPropagation();
	}
	//leftMenu scroll
	function leftMenuScroll() {
		var lM = function() {
			var sTop = parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10);
			if (sTop > 185) {
				$(".left-float-wrap").css("position" , "fixed");
			} else {
				$(".left-float-wrap").css("position" , "absolute");
			};
		};
		$(window).bind("scroll" , lM);
	}
	//item subTitle scroll
	function subTitleScroll() {
		var sT = function() {
			var sTop = parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10);
			if (sTop > 235) {
				$(".t-p-sub-title").addClass("t-fixed");
			} else {
				$(".t-p-sub-title").removeClass("t-fixed");
			};
		};
		$(window).bind("scroll" , sT);
	}
	//answer scroll
	function answerScroll() {
		var marginBot = 0;
		var aS = function() {
			if (document.compatMode === "CSS1Compat") {
				marginBot = document.documentElement.scrollHeight - (document.documentElement.scrollTop + document.body.scrollTop) - document.documentElement.clientHeight;
			} else {
				marginBot = document.body.scrollHeight - document.body.scrollTop - document.body.clientHeight;
			};
			if (marginBot <= 190) {
				$(".answer").css({"position" : "absolute", "bottom" : "0"});
			} else {
				$(".answer").css({"position" : "fixed", "bottom" : "0"});
			};
		};
		$(window).bind("scroll" , aS);
	}

	//答题卡展开关闭
	function answerFun() {
		var aBar = $(".answer-bar");
		aBar.click(function() {
			if ($(".answer-list").is(":hidden")) {
				aBar.addClass("open").attr("title" , "关闭答题卡");
				$(".answer").animate({width : "802px"},100);
				$(".answer-list").show();
			} else {
				aBar.removeClass("open").attr("title" , "展开答题卡");
				$(".answer").animate({width : "42px"},100);
				$(".answer-list").hide();
			};
		});
	}
	//打开关闭试题解析层
	function analysis() {
		$(".jx-show-btn>a").click(function() {
			if (!$(this).parent().parent().siblings(".analysis-wrap").is(":hidden")) {
				$(this).text("展开解析");
				$(this).parent().addClass("a-close");
				$(this).parent().parent().siblings(".analysis-wrap").slideUp(150);
			} else {
				$(this).text("收起解析");
				$(this).parent().removeClass("a-close");
				$(this).parent().parent().siblings(".analysis-wrap").slideDown(150);
			};
		});
	}
	//登录注册切换
	function loginChage() {
		$(".go-e-register").click(function() {
			$(".e-login-ele").slideUp(220, function() {
				$(".e-register-ele").slideDown(220);
			});
		});
		$(".go-e-login").click(function() {
			$(".e-register-ele").slideUp(220, function() {
				$(".e-login-ele").slideDown(220);
			});
		});
	}
	//返回顶部
	function goTop() {
		var goTopElm = $('<div class="goTopBtn"><a href="javascript: void(0)">&nbsp;</a></div>').appendTo($("body"));
		$(".goTopBtn>a").click(function() {$("html,body").animate({"scrollTop" : 0}, 100);});
		var goTopFun = function() {
			var oSt = $(document).scrollTop(), winH = $(window).height();
			(oSt > 0) ? goTopElm.fadeIn(50) : goTopElm.fadeOut(50);
			if (!window.XMLHttpRequest) {goTopElm.css({"top" : oSt + winH - 200});};
		};
		$(window).bind("scroll", goTopFun);
	}
	//公共弹出框
	function dialog(num) {
		
		//先删除之前的
		$(".d-tips-2").remove();
		$(".dialog-shadow").remove();
		$(".bg-shadow").remove();
		
		var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")),
			dialogEle = $('<div class="dialog-shadow"><div class="dialog-ele"><div id="dcWrap" class="pt20 pb20 pl20 pr20 of">内容位置</div></div></div>').appendTo($("body")).addClass("animate-enter animated flyIn5");

		var dCont = [
			'<div class="d-tips-1">' +
			'<section class="tac"><p class="c-333 fsize14">欢迎进入《会计基础考试系统》，是否开始答题</p></section>'+
			'<section class="mt30 tac"><span class="comm-btn-wrap"><a class="comm-btn c-btn-1" title="开始答题" href="">开始答题</a></span></section>' +
			'<section class="mt10 tac"><span class="comm-btn-wrap"><a class="comm-btn c-btn-1" title="取消本次答题" href="">取消本次答题</a></span>' +
			'</section></div>',
			///
			'<div class="d-tips-2">' +
			'<section class="tac"><p class="c-333 fsize14"><span class="qj-icon">&nbsp;</span>确认要交卷吗？</p></section>'+
			'<section class="mt30 tac"><span class="comm-btn-wrap"><a class="comm-btn c-btn-1" title="确认交卷" href="javascript:submit()">确认交卷</a></span></section>' +
			'<section class="mt10 tac"><span class="comm-btn-wrap"><a class="comm-btn c-btn-1" title="谢谢提醒，继续做" href="javascript:jixuzuo()">谢谢提醒，继续做</a></span>' +
			'</section></div>',
			///
			'<div class="d-tips-3">' +
			'<section class="tac"><p class="c-333 fsize14 dialogText"><span class="bc-icon">&nbsp;</span>正在保存试卷请稍等...</p></section></div>',
			///
			'<div class="d-tips-4">' +
			'<section>' +
			'<h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">专项智能练习</span></h4>' +
			'<div class="mt20 mb20 tac loading" style="margin-top:250px;margin-bottom:-250px"><img src="/static/exam/images/loading.gif" width="32" height="32"></div><section class="mt10 ztree" id="treeDemo"></section>' +
			'</section></div>',
			///
			'<div class="d-tips-5">' +
			'<section>' +
			'<h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt paperTypeName"></span></h4>' +
			'<section class="mt10 pageAfter"></section>' +
			'</section></div>',
			///
			'<div class="d-tips-6">' +
			'<section>' +
			'<h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt" id="headSubject">会计初级考试&nbsp;&gt;&nbsp;会试初级考试系列一</span></h4>' +
			'<section class="mt10"><ul class="thr-itme-list clearfix" id="addLi">' +
			'<li><em class="thr-itme-icon">&nbsp;</em><a href="" title="" class="vam c-666 fsize14" >会试初级考试A卷江苏省</a></li>' +
			'</ul></section></section></div>',
			///继续做题
			'<div class="d-tips-7">' +
			'<section class="tac"><span class="comm-btn-wrap"><a class="comm-btn c-btn-1" title="继续做题" href="javascript:void(0)" onclick="timeStart()">继续做题</a></span></section></div>',
			'<div class="d-tips-8">' +
			///编辑笔记
			'<h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">记录笔记</span></h4>' +
			'<section class="mt10 ana-bj-textarea"><textarea name="" id="noteContent" onkeyup="changetnote();" ></textarea></section>' +
			'<section class="mt5 clearfix"><span class="fr"><label class="ana-bj-submit"><input type="button" id="notesubmit" name="" value="提交"></label><label class="ana-bj-reset"><input type="button" onclick="chongzhi()" "name="" value="重置"></label></span><span class="fl c-666" id="notetips">你最多能输入200个字</span>' +
			'</section></div>',
			//纠正答案
			'<h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">我要纠错</span></h4>' +
			'<section class="mt10 ana-bj-textarea"><textarea name="" id="checkContent" onkeyup="changecheck();" ></textarea></section>' +
			'<section class="mt5 clearfix"><span class="fr"><label class="ana-bj-submit"><input type="button" id="checksubmit" name="" value="提交"></label><label class="ana-bj-reset"><input type="button" onclick="chongzhi()" "name="" value="重置"></label></span><span class="fl c-666" id="notetips">你最多能输入200个字</span>' +
			'</section></div>'
			];
		$("#dcWrap").html(dCont[num]);

		var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
			dH = dialogEle.height(),
			dW = dialogEle.width(),
			timer = null,
			dClose;
		dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
		dClose = function() {dialogEle.removeClass("animate-enter animated flyIn5").remove();oBg.remove();};
		
		$(".dtClose").bind("click", dClose);
	}
	
	

	
	function trBg() {
		$("#treeDemo>li:odd").css({"background-color" : "#f4f4f4"});
	}
	
	//加载观看记录
	function querypaperRecordByCusIdAndSubjectId(){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:baselocation+"/paper/querypaperRecordByCusIdAndSubjectId",
			data:{},
			async:false,
			success:function(result){
				if(result.success==true){ 
					var str = '<div class="kg-list png" ><section class="dis tac"><em class="k-tips-1 icon24">&nbsp;</em><font class="c-fff fsize12 vam">你还没有进行过测试！</font></section></div>';
					var paperRecordList = result.entity;
					if(paperRecordList!=null&&paperRecordList.lenght!=0){
						str = '<div class="kg-list png" ><section class="dis" >';
						str+='<dl>';
						for(var i=0;i<paperRecordList.length;i++){
							var paperRecordNow = paperRecordList[i];
							var dateNow = paperRecordNow.addTime;
							dateNow = dateNow.substring(5, 10);
							var dateLast ="";
							if(i!=0){
								dateLast = paperRecordList[i-1].addTime;
								dateLast = dateLast.substring(5, 10);
							}
							if(dateNow!=dateLast){
								str+='<dt><span title="'+dateNow+'">'+dateNow+'</span></dt>';
							}
							if(paperRecordNow.status==1){
								str+='<dd><a class="m-c-go fr" title="继续测试" href="/paper/toExamPaperRecord/'+paperRecordNow.id+'">继续测试</a>';
							}else{
								str+='<dd><a class="m-c-go fr" title="已测试完" href="/paper/toExamPaperRecord/'+paperRecordNow.id+'">已测试完</a>';
							}
							str +='<a class="m-c-link" title="'+paperRecordNow.paperName+'" href="/paper/toExamPaperRecord/'+paperRecordNow.id+'">'+paperRecordNow.paperName+'</a>';
							str+='</dd>';
						}
						str+='</dl><div class="kg-more"><a href="/paper/toExamPaperRecordList.do" title="更多">更多&gt;&gt;</a></div></section></div>';
						$("#paperRecordList").html(str);
					}
				}
				
			}
		});
	}
	
	//公共弹出框
	function dialoghtml(html,change) {
		//先删除之前的
		$(".dialog-shadow").remove();
		$(".bg-shadow").remove();
		var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")),
			dialogEle = $('<div class="dialog-shadow"><div class="dialog-ele"><div id="dcWrap" class="pt20 pb20 pl20 pr20 of">内容位置</div></div></div>').appendTo($("body")).addClass("animate-enter animated flyIn5");
		$("#dcWrap").html(html);
		var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
			dH = dialogEle.height(),
			dW = dialogEle.width(),
			timer = null,
			dClose;
		dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
		dClose = function() {dialogEle.removeClass("animate-enter animated flyIn5").remove();oBg.remove();};
		if(change){
			$(".dtClose").bind("click", dClose);
		}else{
			$(".dtClose").hide();
		}
	}
	
	//判断是否已经选择了专业，未选择弹出
	function loadsubject(change){
		var subject =getCookie("e.subject");
		if(change || isNull(subject)){
			$.ajax({
				type:"post",
				dataType:"text",
				url:baselocation+"/subj/ajax/getSubject",
				data:{},
				async:false,
				success:function(result){
					dialoghtml(result,change);
				},error:function(error){
					alert("加载错误");
				}
			});
		}
	}