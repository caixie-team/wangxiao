	$(function() {
		mainContentHeight();
		navFun();
		leftMenu();
		menuControl();
		tdFun();
	});

	$(window).resize(function() {
		navFun();
	});

	function kill_ie6() {
		if (!-[1,] && !window.XMLHttpRequest) {
			alert("您的浏览器版本太低，快升级到IE7及以上版本来访问！");
            window.location.href="/static/common/kill-ie.html";
			return false;
		}
	}

	function mainContentHeight() {	//兼容不同屏幕显示
		var winH = parseInt(document.documentElement.offsetHeight, 10) + parseInt(document.documentElement.scrollTop || document.body.scrollTop),
			mc = $("#main_content"),
			mcH = mc.height();
		if ((mcH + 108) < winH) {	//这里108px是header的高+1px边线
			mc.css({"min-height" : winH-148 , "_height" : winH-148});
		} else {
			mc.css({"min-height" : "auto" , "_height" : "auto"});
		}
	}

	function leftMenuFixed() {	//左侧目录跟随
		var mTop = document.body.scrollTop || document.documentElement.scrollTop;
		if (!-[1,] && !window.XMLHttpRequest) {	//判断是否IE6
			return false;
		} else {
			var lMenu = $("#left_menu");
			function lmfFun() {
				var mTop = document.body.scrollTop || document.documentElement.scrollTop;
				if (mTop > 108) {
					lMenu.css({"position" : "fixed" , "top" : 0});
				} else {
					lMenu.css({"position" : "absolute" , "top" : 118 + "px"});
				};
			}
			$(window).bind("scroll", lmfFun);
			lmfFun();
		}
	}
	//左侧菜单控制
	function menuControl(){
		  var onwUrl =window.location+'';
		  var href =window.location.href+'';
		  var array = $(".admin-sidebar-sub >li a");
		  var flag = false;
		  for(var i=0;i<array.length;i++){
			  var tempAHref = $(array[i]).prop("href");
			  if(onwUrl==tempAHref||href.indexOf(tempAHref)!=-1){
				  $(array[i]).parent().parent().addClass("am-in");
				  $(array[i]).parent().parent().prev("a").removeClass("am-collapsed");
				  flag=true;
				  break;
			  }
		  }
		  if(!flag){
			  var hiddrenArray = $(".admin-sidebar-sub >li:hidden a");
			  for(var i=0;i<hiddrenArray.length;i++){
				  var tempAHref = $(hiddrenArray[i]).prop("href");
				  if(onwUrl==tempAHref||tempAHref.indexOf(href)!=-1){
					  $(hiddrenArray[i]).parent().parent().addClass("am-in");
					  $(hiddrenArray[i]).parent().parent().prev("a").removeClass("am-collapsed");
					  flag=true;
					  break;
				  }
			  } 
		  }
		  
	}
	//leftMenuFixed();

	function slideScroll() {	//头部导航菜单滚动
		var prev = $(".prev"),
			next = $(".next"),
			oUl = $(".navList>ul"),
			w = oUl.find("li").outerWidth(true),
			l = oUl.find("li").length;
			oUl.css("width" , w * l + "px");
		//click left
		prev.click(function() {
			if(!oUl.is(":animated")) {
				oUl.animate({"margin-left" : -w}, function() {
					oUl.find("li").eq(0).appendTo(oUl);
					oUl.css("margin-left" , 0);
				});
			};
		});
		//click right
		next.click(function() {
			if(!oUl.is(":animated")) {
				oUl.find("li:last").prependTo(oUl);
				oUl.css("margin-left" , -w);
				oUl.animate({"margin-left" : 0});
			};
		});
	}

	function navFun() {
		var winW = parseInt(document.documentElement.clientWidth, 10) + parseInt(document.documentElement.scrollLeft || document.body.scrollLeft, 10),
			nlW = winW - 400,
			ulW = $(".navList>ul").width(),
			oPN = $('<a href="javascript: void(0)" title="左" class="prev">&nbsp;</a><a href="javascript: void(0)" title="右" class="next">&nbsp;</a>');
		$(".navList").css("width" , nlW);
		if (nlW > ulW) {
			$(".prev,.next").remove();
		} else {
			oPN.appendTo(".navWrap");	
			slideScroll();
		}
	}
	//navFun();
	function leftMenu() {
		$("#lMenu dl dt").each(function() {
			$(this).click(function() {
				if (!$(this).hasClass("current")) {
					$(this).addClass("current").children(".sjWrap").show();
					$(this).next("dd").slideDown(150);
				} else {
					$(this).removeClass("current").children(".sjWrap").hide();
					$(this).next("dd").slideUp(150);
				}
				return false;
			});
		});
	}

	function tdFun() {
		$("#tabS_01>tr:nth-child(2n)").children("td").css({"background-color" : "#F9F9F9"});
		$("#tabS_02>tr").hover(function() {$(this).toggleClass("hover");});
		$(".testWrapList>li:nth-child(2n)").css({"background-color" : "#EFEFEF"});
		var t_set = $('');
		$(".testPagerTitle>h5").hover(function() {
			$(this).find("span").show();
		}, function() {
			$(".testPagerTitle .czBtn").hide();
		})
		$(".testPagerTitle .czBtn").hide();
	}

	function dialog() {
		var mask = $('<div></div>').appendTo($("body")).addClass("modal-backdrop fade in");
		var oEl = $('<div></div>').appendTo($('body')).addClass("modal fade in");
		var doc = $('<div class="modalWrap"><div class="modal_head"><span class="fr"><a href="javascript:void(0)" title="关闭" class="icon14 closeBtn"></a></span><h4><span>提示信息</span></h4></div><div class="modal_body" id="modalCont">文字位置</div><div class="modal_foot clearfix"><button class="btn btn-success">确定</button><button class="btn ml10 cancelBtn">取消</button></div></div>').prependTo($(".modal"));

		$(".closeBtn,.cancelBtn,.modal-backdrop").bind("click", function() {$(".modal").removeClass("in").remove();$(".modal-backdrop").removeClass("in").remove();});
	}
	
	function kpointDialog(kpoints) {
		var mask = $('<div id="backdrop_dialog"></div>').appendTo($("body")).addClass("modal-backdrop fade in");
		var oEl = $('<div id="course_id_dialog"></div>').appendTo($('body')).addClass("modal fade in");
		var doc = $('<div class="modalWrap"><div class="modal_head"><span class="fr"><a href="javascript:void(0)" title="关闭" class="icon14 closeBtn"></a></span><h4><span>请输入课程id</span></h4></div><div class="modal_body" id="modalCont"><input id="copyCourseId" type="text" style="width:80px;"/><input id="copyKpoints" type="hidden" value="'+kpoints+'"/></div><div class="modal_foot clearfix"><button class="btn btn-success" onclick="copyKpoint()">确定</button><button class="btn ml10 cancelBtn">取消</button></div></div>').prependTo($(".modal"));

		$(".closeBtn,.cancelBtn,.modal-backdrop").bind("click", function() {$(".modal").removeClass("in").remove();$(".modal-backdrop").removeClass("in").remove();});
	}

	
	//公共弹框方法
	/*
	***
	    dTitle : 弹框标题名称,
	    dTxt : 提示内容文字,
	    num : 弹框类型,
	    num == 0 : 错误信息提示,
	    num == 1 : 正确信息提示,
	    num == 2 : 确认信息提示,
	    num == 3 : 发信息弹出框,
	    num == 4 : 渐出信息提示,
	    num == 5 : 创建题纲
	 	url : 地址
	 	userId : 用户id(发送消息)
	***
	*/
	function dialogFun(dTitle,dTxt,num,url,userId) {
		var userName = '';
		if(userId!=null&&userId!=''){
			userName = getUserName(userId);
		}
	    var init = function() {
	        this.dTitle = dTitle;
	        this.dTxt = dTxt;
	        this.num = num;
	        this.winW = document.documentElement.clientWidth;
	        this.winH = document.documentElement.clientHeight;
	        this.sTop = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
	        this.sLeft = document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft;
	        this.start();
	        this.cancel();
	    };
	    init.prototype = {
	        oTimer : null,
	        tTimer : null,
	        bMask : '',
	        dCommEle : '',
	        dContArr : [],
	        dTop : 0,
	        dLeft : 0,

	        start : function() {
	            var _this = this;
	                _this.bMask = $('<div class="bg-mask"></div>').appendTo($("body"));
	                _this.bMask.css({
	                    background : "#000",
	                    width : _this.winW,
	                    height : _this.winH,
	                    position : "fixed",
	                    top : "0px",
	                    left : "0px",
	                    opacity : "0.3",
	                    zIndex : "9999"
	                });
	            _this.dCommEle = $('<div id="dialog-shadow" class="dialog-shadow"><div class="dContent"><header id="dHead" class="dHead"><span class="c-333 ml20">'+_this.dTitle+'</span></header><a href="javascript:void(0)" title="关闭" class="am-close dClose">&times;</a><div id="dcWrap" class="dcWrap">内容区域</div></div></div>').appendTo($("body"));
	            _this.dContArr = [
	                    '<section class="dca dca0"><i class="am-icon-frown-o am-text-danger">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
	                    '<div class="mt20 mb10"><a href="javascript:void(0)" title="" class="am-btn am-btn-danger dCancel">确  定</a></div></section>',

	                    '<section class="dca dca1"><i class="am-icon-smile-o am-text-success">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
	                    '<div class="mt20 mb10"><a href="javascript:void(0)" title="" class="am-btn am-btn-danger dCancel"><span>确 定</span></a></div></section>',

	                    '<section class="dca dca2"><i class="icon26 dAsk">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
	                    '<div class="mt20 mb10"><a href="'+url+'" title="" class="am-btn am-btn-danger dCancel"><span>确 定</span></a><a href="javascript:void(0)" class="am-btn am-btn-default ml10 dCancel"><span>取 消</span></a></div></section>',
	                    
	                    '<section class="dca dca3">'+
	                    '<section><h6><span class="fsize12 c-666">收信人：</span><span class="fsize12 c-666">'+userName+'</span></h6></section>'+
	                    '<section class="mt10 am-form"><textarea maxlength="300" onkeyup="textChange(this)" name="" id="msgContent" class="dTextarea"></textarea></section>'+
	                    '<section class="mt10 tar mb10"><span class="mr10 vam"><tt class="am-text-danger fsize12" id="errorMsg">您还没输入文字！</tt></span><a class="am-btn am-btn-danger dCancel" title="" href="javascript:void(0)" onclick="_sendMessage('+userId+')"><span>发 信</span></a></section>'+
	                    '</section>',
	                    
	                    '<section class="dca dca5"><i class="am-icon-exclamation-triangle am-text-warning">&nbsp;</i><span class="fsize14 am-text-warning">'+_this.dTxt+'</span>'+
	                    '</section>',
	                    
	                    '<section class="dca dca0"><i class="am-icon-smile-o am-text-success">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
	                    '<div class="mt20 mb10"><a href="'+url+'" title="" class="am-btn am-btn-danger dCancel">确  定</a></div></section>',
	                    
	                    '<section class="dca dca0"><i class="am-icon-frown-o am-text-danger">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
	                    '<div class="mt20 mb10"><a href="'+url+'" title="" class="am-btn am-btn-danger dCancel">确  定</a></div></section>',
	                    
	                    '<section class="dca dca3 am-form">'+
	                    '<div class="mt10"><section class="am-u-sm-3 am-text-right">试题类型</section><section class="am-u-sm-9 am-u-end"><select data-am-selected><option value="a">Apple</option><option value="b" selected>Banana</option><option value="d" disabled>禁用鸟</option></select></section></div><div class="clear"></div>'+
	                    '<div class="mt20"><section class="am-u-sm-3 am-text-right">大题目名称</section><section class="am-u-sm-9 am-u-end"><input class="am-input-sm" type="text" value=""></section></div><div class="clear"></div>'+
	                    '<div class="mt20"><section class="am-u-sm-3 am-text-right">每题分数</section><section class="am-u-sm-9 am-u-end"><input class="am-input-sm" type="text" value="" style="width: 60%;display: inline-block;"><span> 分</span></section></div><div class="clear"></div>'+
	                    '<div class="mt20"><section class="am-u-sm-3 am-text-right">大题说明</section><section class="am-u-sm-9 am-u-end"><textarea name="" id="" class="dTextarea"></textarea></section></div><div class="clear"></div>'+
	                    '<div class="mt40 mb10 tac"><a href="'+url+'" title="" class="am-btn am-btn-danger"><span>确 定</span></a><a href="javascript:void(0)" class="am-btn am-btn-default ml10 dCancel"><span>取 消</span></a></div></section>'+
	                    '</section>'
	                    
	                ];

	            $("#dcWrap").html(_this.dContArr[_this.num]);
	            _this.position();
	            _this.drag();
	            window.clearTimeout(_this.oTimer);
	            window.clearTimeout(_this.tTimer);
	        },
	        position : function() {
	            var _this = this,
	                dcW =  _this.dCommEle.width(),
	                dcH =  _this.dCommEle.height(),
	                dHead = _this.dCommEle.find(".dHead");
	            _this.dTop = (parseInt(_this.winH, 10)/2) + (parseInt(_this.sTop, 10));
	            _this.dLeft = (parseInt(_this.winW, 10)/2) + (parseInt(_this.sLeft, 10));
	            _this.dCommEle.css({
	                top : _this.dTop - (dcH/2),
	                left : _this.dLeft - (dcW/2)
	            });
	            dHead.css("width" , dcW + "px");
	        },
	        cancel : function() {
	            var _this = this
	                _dClose = _this.dCommEle.find(".dClose"),
	                _dCancel = _this.dCommEle.find(".dCancel"),
	                closeFun = function() {
	                    //_this.dCommEle.addClass("bounceOut");
	                    _this.bMask.css({
	                        opacity : "0",
	                        zIndex : "-1"
	                    });
	                    _this.oTimer = setTimeout(function() {
	                        _this.dCommEle.remove();
	                        _this.bMask.remove();
	                    }, 100);
	                };
	            /*_this.tTimer = setTimeout(function() {
	                _this.dCommEle.removeClass("bounceIn");
	            }, 1000);*/
	            if(_this.num === 4) {
	                _this.bMask.remove();
	                _this.dCommEle.find(".dHead").remove();
	                _dClose.remove();
	                _this.position();
	                _this.oTimer = setTimeout(function() {
	                    _this.dCommEle.fadeOut(1000);
	                    _this.bMask.fadeOut(1000);
	                }, 3000);
	                _this.tTimer = setTimeout(function() {
	                    closeFun();
	                }, 4000);
	            };
	           _dClose.on("click",function() {closeFun()});
	           _dCancel.on("click",function() {closeFun()});
	        },
	        drag : function() {
	            var _this = this;
	            var eDrag = document.getElementById("dialog-shadow"),
	                oDrag = document.getElementById("dHead"),
	                bDrag = false,
	                disX = disY = 0;
	            oDrag.onmousedown = function(event) {
	                var event = event || window.event;
	                bDrag = true;
	                disX = event.clientX - eDrag.offsetLeft;
	                disY = event.clientY - eDrag.offsetTop;
	                this.setCapture && this.setCapture();  //设置鼠标捕获
	                return false;
	            };
	            document.onmousemove = function(event) {
	                if(!bDrag) return;
	                var event = event || window.event;
	                var dL = event.clientX - disX;
	                var dT = event.clientY - disY;
	                var maxL = document.documentElement.clientWidth + _this.sTop - eDrag.offsetWidth;
	                var maxT = document.documentElement.clientHeight + _this.sLeft - eDrag.offsetHeight;
	                dL = dL < 0 ? 0 : dL;
	                dL = dL > maxL ? maxL : dL;
	                dT = dT <0 ? 0 : dT;
	                dT = dT > maxT ? maxT : dT;
	                eDrag.style.marginTop = eDrag.style.marginLeft = 0;
	                eDrag.style.left = dL + "px";
	                eDrag.style.top = dT + "px";
	                return false;
	            };
	            document.onmouseup = window.onblur = oDrag.onlosecapture = function() {
	                bDrag = false;
	                oDrag.releaseCapture && oDrag.releaseCapture();
	            };
	        }
	    };
	    new init();
	}

	/**
	 * 发送消息
	 * @param userId 用户id
     */
	function _sendMessage(userId){
		var msgContent = $("#msgContent").val();
		$.ajax({
			url:baselocation+"/admin/letter/sendSystemMsgToUser",
			data:{"msgContent":msgContent,"userId":userId},
			type : "post",
			dataType : "json",
			async:false,
			success: function (result){
				setTimeout(function(){
					if(result.success){
						dialogFun("发送消息","发送成功",1);
					}else{
						dialogFun("发送消息",result.message,0);
					}
				},500);

			}
		});
	}

	function textChange(obj){
		var textLength = 300;

		if(obj.value==null||obj.value==''){
			$("#errorMsg").html("您还没输入文字！");
		}else if(obj.value.length<textLength){
			$("#errorMsg").html("还可以输入 "+(textLength-obj.value.length)+" 字");
		}else{
			$("#errorMsg").html("发送消息文字不能超过 "+textLength+" 字");
		}
	}

	/**
	 * 获取用户名
	 * @param userId 用户Id
     */
	function getUserName(userId){
		var userName = '';
		$.ajax({
			url:baselocation+"/ajax/getUserName",
			data: {"id":userId},
			type : "post",
			dataType : "json",
			async:false,
			success: function (result){
				if(result.success){
					userName = result.entity;
				}
			}
		});
		return userName;
	}