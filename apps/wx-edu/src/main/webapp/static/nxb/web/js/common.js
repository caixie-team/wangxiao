//导航切换方法
var wmNavFun = function() {
    var wmBtn = $(".mw-nav-btn"),
        hmMask = $(".h-mobile-mask"),
        wH = $(window).height();
    $(".head-mobile").css("height", wH+"px");
    wmBtn.click(function() {
        if (!wmBtn.hasClass("mw-tap")) {
            $(this).addClass("mw-tap");
            $("html").addClass("active");
            hmMask.show().css("opacity","1");
        } else {
            $(this).removeClass("mw-tap");
            $("html").removeClass("active");
            hmMask.css("opacity","0").hide();
        };
    });
    hmMask.click(function() {
        if(!hmMask.is(":hidden")) {
            wmBtn.removeClass("mw-tap");
            $("html").removeClass("active");
            hmMask.css("opacity","0").hide();
        }
    });
};
//公共弹框方法
/*
***
    dTitle : 弹框标题名称,
    dTxt : 提示内容文字,
    num : 弹框类型,
    num == 0 : 错误信息提示,
    num == 1 : 正确信息提示,
    num == 2 : 确认信息提示,
    num == 3 : 渐出信息提示,
    num == 4 : 意见反馈弹框,
    num == 5 : 跳转弹窗
    num == 6 : 充值卡
***
*/
function dialogFun(dTitle,dTxt,num,url) {
    var init = function() {
        this.dTitle = dTitle;
        this.dTxt = dTxt;
        this.num = num;
        this.url = url;
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
            _this.dCommEle = $('<div id="dialog-shadow" class="dialog-shadow"><div class="dContent"><header id="dHead" class="dHead"><span class="c-333 ml20">'+_this.dTitle+'</span></header><a href="javascript:void(0)" title="关闭" class="dClose">&nbsp;</a><div id="dcWrap" class="dcWrap">内容区域</div></div></div>').appendTo($("body"));
            _this.dContArr = [
                    '<section class="dca dca0"><i class="icon30 mr10 dError tips-icon">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
                    '<div class="dcFoot"><a href="javascript:void(0)" title="" class="cBtn cb-send dCancel"><span>确 定</span></a></div></section>',

                    '<section class="dca dca1"><i class="icon30 mr10 dRight tips-icon">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
                    '<div class="dcFoot"><a href="javascript:void(0)" title="" class="cBtn cb-send dCancel"><span>确 定</span></a></div></section>',

                    '<section class="dca dca2"><i class="icon30 mr10 dAsk tips-icon">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
                    '<div class="dcFoot"><a href="'+this.url+'" title="" class="cBtn cb-send col-50 fl dCancel"><span>确 定</span></a><a href="javascript:void(0)" class="dCancel cBtn col-50 fl"><span>取 消</span></a></div></section>',
                    
                    '<section class="dca dca5"><i class="icon30 mr10 dFade tips-icon">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
                    '</section>',

                    '<form action="${ctx}/front/addfreeback" id="freeBack" method="post">' +
                    '<section class="dca dca3">'+
                    '<section class="mt10"><h6><span class="fsize12 c-666">反馈信息：</span><span class="fsize12 c-999">（请您详细描述反馈的对象及内容，以保证您的反馈得到及时准确的处理 500个字以内）</span></h6></section>'+
                    '<section class="mt10"><textarea name="userFeedback.content" id="content" class="dTextarea"></textarea></section>'+
                    '<ul class="mt10 l-r-w-Inpt fk-i-inpt">' +
	                    '<li>' +
		                    '<label>手机号码：</label>' +
		                    '<input type="text" name="userFeedback.mobile" id="mobile" class="lTxt"/>' +
	                    '</li>' +
	                    '<li class="mt15">' +
	                    	'<label>电子邮箱：</label>' +
		                    '<input type="text" name="userFeedback.email" id="email"  class="lTxt"/>' +
	                    '</li>' +
	                    '<li class="mt15">' +
	                    	'<label>QQ：</label>' +
		                    '<input type="text" name="userFeedback.qq" id="qq" class="lTxt"/>' +
	                    '</li>' +
	                    '<li class="mt15">' +
		                    '<label>姓名：</label>' +
		                    '<input type="text" name="userFeedback.name" id="name" class="lTxt" />' +
	                    '</li>' +
                    '</ul>'+
                    	'<section class="dcFoot"><span class="mr10 vam fr"><tt class="c-red fsize12" id="contentErrorClass">您还没输入文字！</tt></span><a class="cBtn cb-send yes-btn" title="" href="javascript:void(0)" onclick="sumbitFeed()"><span>确 定</span></a></section>'+
                    '</section>' +
                    '</form>',

                    '<section class="dca dca1"><i class="icon30 mr10 dRight tips-icon">&nbsp;</i><span class="fsize14 c-666">'+_this.dTxt+'</span>'+
                    '<div class="dcFoot"><a href="'+this.url+'" title="" class="cBtn cb-send dCancel"><span>确 定</span></a></div></section>',

                    "<div class='d-tips-7'><ul class='l-r-w-Inpt'><li><label class='vam'>卡号：</label><input type='text' id='cardCourseCode' name='' value='' class='lTxt'></li><li class='mt20'><label class='vam'>密码：</label><input type='password' id='cardCoursePassword' name='' value='' class='lTxt'></li><li class='mt20'><label class='vam'>&nbsp;</label><span class='login-btn'><input type='button' class='dCancel' style='margin-left: 0;' value='激 活' onclick='activeCard()'></span></li></ul></div>",
                    
                    '<section class="dca dca2"><h3 class="mt10"><span class="fsize12 c-666">解除绑定后将不再使用<tt class="c-333 fsize14" id="typeName">QQ</tt>登录在线教育平台软件</span></h3>'+
                    '<ul class="mt15 l-r-w-Inpt fk-i-inpt bind-inpt">' +
	                    '<li>' +
		                    '<label>密码：</label>' +
		                    '<input type="password" id="excludeBundingPwd" class="lTxt"/>' +
	                    '</li>' +
                    '</ul>'+
                    '<div class="dcFoot"><a href="'+this.url+'" title="" class="cBtn cb-send col-50 fl dCancel"><span>确 定</span></a><a href="javascript:void(0)" class="dCancel cBtn col-50 fl"><span>取 消</span></a></div></section>',
                    
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
                _dCancel = _this.dCommEle.find(".dCancel,.ddCancel"),
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
           /* _this.tTimer = setTimeout(function() {
                _this.dCommEle.removeClass("bounceIn");
            }, 1000);*/
            if(_this.num === 3) {
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
           _dClose.on("click",function() {closeFun();});
           _dCancel.on("click",function() {closeFun();});
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
                var maxL = document.documentElement.clientWidth + _this.sLeft - eDrag.offsetWidth;
                var maxT = document.documentElement.clientHeight + _this.sTop - eDrag.offsetHeight;
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
};
//placeholder兼容IE方法
function placeholderFun() {
  //判断浏览器是否支持placeholder属性
  supportPlaceholder='placeholder'in document.createElement('input');

  //当浏览器不支持placeholder属性时，调用placeholder函数
  if(!supportPlaceholder){
    $("input").not("input[type='password']").each(//把input绑定事件 排除password框  
          function(){  
              if($(this).val()=="" && $(this).attr("placeholder")!=""){  
                  $(this).val($(this).attr("placeholder"));  
                  $(this).focus(function(){  
                      if($(this).val()==$(this).attr("placeholder")) $(this).val("");  
                  });  
                  $(this).blur(function(){  
                      if($(this).val()=="") $(this).val($(this).attr("placeholder"));  
                  });  
              }  
      });  
      //对password框的特殊处理
      var pwdField    = $("input[type=password]");
      pwdField.each(function() {
          var _this = $(this),
              index = _this.index(),
              pwdVal = _this.attr('placeholder');
          _this.after('<input id="pwdPlaceholder'+index+'" type="text" value='+pwdVal+' autocomplete="off" />');
          var pwdFieldColn = _this.next();  
          pwdFieldColn.show();  
          _this.hide();
            
          pwdFieldColn.focus(function(){  
              pwdFieldColn.hide();  
              _this.show();  
              _this.focus();  
          });  
            
          _this.blur(function(){  
              if(_this.val() == '') {  
                  pwdFieldColn.show();  
                  _this.hide();  
              }  
          });
      });
  }
}
function goTop(){
    var goTopEle = $("#goTop");
    goTopEle.click(function() {$("html, body").animate({scrollTop : 0}, 500);});
    var goTopF = function() {
        var scrH = $(document).scrollTop(),
            winH = $(window).height();
        (scrH > 0) ? goTopEle.fadeIn(50) : goTopEle.fadeOut(50);
    }
    $(window).bind("scroll" , goTopF);
}
function footOpen(){
    var _fBtn = $(".f-h-w-box>dl>dt>a");
    _fBtn.each(function(){
        var _this = $(this);
        _this.click(function(){
            if(!_this.hasClass("f-dd-close")){
                _this.addClass("f-dd-close");
                _this.parent().siblings().hide();
            }else{
                _this.removeClass("f-dd-close");
                _this.parent().siblings().show();
            }
        })
    })
}
//公共选项卡方法
function cardChange(oTitle, oCont, current, callback) {
    var oTitle = $(oTitle),
        oCont = $(oCont),
        _index;
    oTitle.click(function() {
        _index = oTitle.index(this);
        $(this).addClass(current).siblings().removeClass(current);
        oCont.eq(_index).show().siblings().hide();
        if (typeof callback === "function") {callback(_index);};
    }).eq(0).click();
}
//判断移动端设备
var browserRedirect = function() {
    var bFlage = false;
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
        bFlage = true;
    };
    return bFlage;
};
//当移动端设备运行以下方法
var mbFun = function() {
    if (browserRedirect()) {
        wmNavFun();
        footOpen();
    };
}
var fcFun = function() {
    $("#c-i-tabTitle").click(function(){
        var fcB = $("#c-r-content").offset().top;
        $("html,body").animate({"scrollTop" : fcB},1000);
    })
}
//分享鼠标划过时的效果
var shareFun = function() {
    var _box=$(".share-box");
    $("#c-share").hover(function(){
            _box.show();
        },function(){
             _box.hide();
        })
}
//课程列表的展开收起
var cSortFun = function() {
    $(".c-s-more>a").each(function() {
        var _this = $(this),
            _uList = _this.parent().siblings("ul"),
            _uLw = _uList.height();
        if (_uLw <= "40") {
            _this.hide();
        } else {
            _uList.css("height","40px");
            _this.click(function() {
                if(_this.html() == "[展开]") {
                    _uList.css("height","auto");
                    _this.text("[收起]");
                } else {
                    _uList.css("height" , "40px");
                    _this.text("[展开]");
                }
            })
        }
    });
}
//讲师列表的课程名显示
function effect() {
        $(".teacher-courses-tj .t-c-tj").hover(function() {
            $(this).find(".tea-cou-name").animate({"bottom" : "0px"}, 200);
        }, function() {
            $(this).find(".tea-cou-name").animate({"bottom" : "-30px"}, 200);
        });
    }
function enterDym(){
        $(".com-dynamic-list>li .c-d-l-wrap").hover(function() {
            $(this).children(".c-d-l-desc").animate({"bottom" : "0px"}, 200);
        }, function() {
            $(this).children(".c-d-l-desc").animate({"bottom" : "-45px"}, 200);
        });
    }
//个人中心目录菜单
function lmenu() {
    $(".uc-m-l-menu>dl>dt").each(function() {
        var _this = $(this);
        _this.click(function() {
            if(_this.next("dd").is(":hidden")) {
                _this.addClass("open");
                _this.next("dd").slideDown(150);
                _this.parent().siblings("dl").children("dt").removeClass("open");
                _this.parent().siblings("dl").children("dd").slideUp(150);
            } else {
                _this.removeClass("open");
                _this.next("dd").slideUp(100);
                _this.parent().next("dl").children("dt").addClass("open");
                _this.parent().next("dl").children("dd").slideDown(150);
            }
        });
    });
};

//快捷登录/注册
function lrFun() {
    var qq = "'QQ'";
    var wx = "'WEIXIN'";
    var sina = "'SINA'";
    var oBg = $('<div class="bMask"></div>').appendTo($("body")),
        dialogEle = $('<div class="dialogWrap" style="position: absolute;"><div class="dialog-ele"><h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span id="d-s-head-tab" class="d-s-head-tab"><a href="javascript:void(0)" class="current">登录</a><!--<a href="javascript:void(0)">注册</a>--></span></h4><div class="of"><div id="lrEleWrap" class="mt10 mb20 ml20 mr20"></div></div></div></div>').appendTo($("body")),
        rlEle = [
            '<div id="d-s-head-cont" class="lrWrap">'+
                '<section class="dis e-login-ele">'+
                    '<div class="mt10 undis" id="error_div">'+
                        '<p class="e-l-jy"><font class="fsize12 c-red">用户名和密码不匹配！</font></p>'+
                    '</div>'+
                    '<div>'+
                        '<ol class="e-login-options">'+
                            '<li>'+
                                '<input id="u-email" type="text" placeholder="请输入登录邮箱"  name="" value="" onblur="checkEmail()">'+
                                '<p class="lr-tip-wrap"><span class="c-red undis"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
                            '</li>'+
                            '<li>'+
                                '<input id="u-password" type="password" placeholder="请输入密码" maxlength="20"  name="" value="" onblur="checkPassword()">'+
                                '<p class="lr-tip-wrap"><span class="c-red undis"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的密码！</span></p>'+
                            '</li>'+
                        '</ol>'+
                        '<section class="hLh30 of"><span class="fr"><a href="'+baselocation+'/register" class="c-master fsize12">没有账号？去注册→</a></span>'+
                        '<span class="fl"><label class="hand c-999 vam"><input type="checkbox" id="autoThirty" style="vertical-align: -2px;">自动登录</label><a class="vam ml10 c-blue" title="" href="'+baselocation+'/front/forget_passwd">忘记密码?</a></span></section>'+
                        '<section class="mt20 tac">'+
                            '<a href="javascript: void(0)" title="登 录" class="e-login-btn" onclick="doLogin()">登 录</a>'+
                        '</section>'+
                        '<section class="mt20 sf-lr-wrap tac">'+
                            '<h6 class="hLh20 mb15"><span class="c-666 fsize14">第三方快捷登录</span></h6>'+
                            '<a href="javascript:oauthLogin('+qq+')" title="QQ登录" class="qq-sf">&nbsp;</a>'+
                            '<a href="javascript:oauthLogin('+wx+')" title="微信登录" class="wx-sf">&nbsp;</a>'+
                            '<a href="javascript:oauthLogin('+sina+')" title="微博登录" class="wb-sf">&nbsp;</a>'+
                        '</section>'+  
                    '</div>'+
                '</section>'/*+
                '<section class="undis e-login-ele">'+
	                '<div class="sj-login-tab">'+
						'<ul>'+
							'<li class="current">'+
								'<a onclick="" href="javascript:void(0)">手机注册</a>'+
							'</li>'+
							'<li>'+
								'<a onclick="" href="javascript:void(0)">邮箱注册</a>'+
							'</li>'+
						'</ul>'+
					'</div>'+
					'<div class="register-box">'+
                    '<section>'+
						 '<div class="mt10">'+
	                        '<p class="e-l-jy"><font class="fsize12 c-red">用户名和密码不匹配！</font></p>'+
	                    '</div>'+
	                    '<div>'+
	                        '<ol class="e-login-options">'+
	                            '<li>'+
	                                '<input id="u-email" type="text" placeholder="请输入手机号"  name="" value="">'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                            '<li class="clearfix">'+
	                                '<input id="u-password" class="fl" style="width: 199px;" type="text" placeholder="请输入验证码"  name="" value="">'+
	                                '<a href="" title="" class="vam ml10 disIb fl yzmget">获取验证码</a>'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                            '<li>'+
	                                '<input id="u-password" type="text" placeholder="请输入密码"  name="" value="">'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                            '<li>'+
	                                '<input id="u-password" type="text" placeholder="请再输入一次密码"  name="" value="">'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                        '</ol>'+
	                        '<section class="mt20 tac">'+
	                            '<a href="javascript: void(0)" title="注 册" class="e-login-btn">注 册</a>'+
	                        '</section>'+
	                        '<section class="mt20 sf-lr-wrap tac">'+
	                            '<h6 class="hLh20 mb15"><span class="c-666 fsize14">第三方快捷登录</span></h6>'+
	                            '<a href="" title="QQ登录" class="qq-sf">&nbsp;</a>'+
	                            '<a href="" title="微信登录" class="wx-sf">&nbsp;</a>'+
	                            '<a href="" title="微博登录" class="wb-sf">&nbsp;</a>'+
	                        '</section>'+  
	                    '</div>'+
	                 '</section>'+
	                 '<section >'+
						 '<div class="mt10">'+
	                        '<p class="e-l-jy"><font class="fsize12 c-red">用户名和密码不匹配！</font></p>'+
	                    '</div>'+
	                    '<div>'+
	                        '<ol class="e-login-options">'+
	                            '<li>'+
	                                '<input id="u-email" type="text" placeholder="请输入登录邮箱"  name="" value="">'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                            '<li class="clearfix">'+
	                                '<input id="u-password" class="fl" style="width: 199px;" type="text" placeholder="请输入验证码"  name="" value="">'+
	                                '<a href="" title="" class="vam ml10 disIb fl yzmget">获取验证码</a>'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                            '<li>'+
	                                '<input id="u-password" type="text" placeholder="请输入密码"  name="" value="">'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                            '<li>'+
	                                '<input id="u-password" type="text" placeholder="请再输入一次密码"  name="" value="">'+
	                                '<p class="lr-tip-wrap"><span class="c-red"><em class="icon18 login-err-icon">&nbsp;</em>请输入正确的邮箱！</span></p>'+
	                            '</li>'+
	                        '</ol>'+
	                        '<section class="mt20 tac">'+
	                            '<a href="javascript: void(0)" title="注 册" class="e-login-btn">注 册</a>'+
	                        '</section>'+
	                        '<section class="mt20 sf-lr-wrap tac">'+
	                            '<h6 class="hLh20 mb15"><span class="c-666 fsize14">第三方快捷登录</span></h6>'+
	                            '<a href="" title="QQ登录" class="qq-sf">&nbsp;</a>'+
	                            '<a href="" title="微信登录" class="wx-sf">&nbsp;</a>'+
	                            '<a href="" title="微博登录" class="wb-sf">&nbsp;</a>'+
	                        '</section>'+  
	                    '</div>'+
                 '</section>'+
                 '</div>'+
                '</section>'+
            '</div>'*/
        ];
    $("#lrEleWrap").html(rlEle[0]);
    cardChange("#d-s-head-tab>a", "#d-s-head-cont>section", "current");
    cardChange(".sj-login-tab>ul>li", ".register-box>section", "current");
    var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
        dH = dialogEle.height(),
        dW = dialogEle.width(),
        dHead = $(".dialog-ele>h4");
    dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
    dHead.css({"width" : (dW-"33")}); //ie7下兼容性;
    $(".dClose").bind("click", function() {dialogEle.remove();oBg.remove();});
}

function checkEmail(){
    var check = false;
    var _this = $("#u-email");
    if(!isEmail(_this.val())){
        _this.next().children().show();
    }else{
        _this.next().children().hide();
        check = true;
    }
    return check;
}
function checkPassword(){
    var check = false;
    var _this = $("#u-password");
    var length = _this.val().length;
    if(length>=6){
        _this.next().children().hide();
        check = true;
    }else{
        _this.next().children().show();
    }
    return check;
}
function doLogin(){
    if(checkEmail()&&checkPassword()){
        $.ajax({
            url:baselocation+"/dologin",
            type:"post",
            data:{
                "userForm.email":$("#u-email").val(),
                "userForm.password":$("#u-password").val(),
                "autoThirty":$("#autoThirty").val()
            },
            dataType:"json",
            success:function(result){
                var msg = "";
                if(result.success){
                    history.go(0);
                }else if(result.message=='inputIllegal'){
                    msg="输入数据不合法";
                }else if(result.message=='formDataNot'){
                    msg="账号不存在";
                }else if(result.message=='false'){
                    msg="密码错误";
                }else {
                    msg="系统异常";
                }
                $("#error_div").find("font").html(msg);
                $("#error_div").show();
            }
        });
    }
}
//scrollLoad 滚动响应加载调用图片方法
var scrollLoad = (function (options) {
    var defaults = (arguments.length == 0) ? { src: 'xSrc', time: 500} : { src: options.src || 'xSrc', time: options.time ||500};
    var camelize = function (s) {
        return s.replace(/-(\w)/g, function (strMatch, p1) {
            return p1.toUpperCase();
        });
    };
    this.getStyle = function (element, property) {
        if (arguments.length != 2) return false;
        var value = element.style[camelize(property)];
        if (!value) {
            if (document.defaultView && document.defaultView.getComputedStyle) {
                var css = document.defaultView.getComputedStyle(element, null);
                value = css ? css.getPropertyValue(property) : null;
            } else if (element.currentStyle) {
                value = element.currentStyle[camelize(property)];
            }
        }
        return value == 'auto' ? '' : value;
    };
    var _init = function () {
        var offsetPage = window.pageYOffset ? window.pageYOffset : window.document.documentElement.scrollTop,	//滚动条滚动高度
            offsetWindow = offsetPage + Number(window.innerHeight ? window.innerHeight : document.documentElement.clientHeight),
            docImg = document.getElementById("aCoursesList").getElementsByTagName("img"),			//通过ID查找获取图片节点
            _len = docImg.length;
        if (!_len) return false;
        for (var i = 0; i < _len; i++) {
            var attrSrc = docImg[i].getAttribute(defaults.src),
                o = docImg[i], tag = o.nodeName.toLowerCase();
            if (o) {
                postPage = o.getBoundingClientRect().top + window.document.documentElement.scrollTop + window.document.body.scrollTop;
                postWindow = postPage + Number(this.getStyle(o, 'height').replace('px', ''));	
                if ((postPage > offsetPage && postPage < offsetWindow) || (postWindow > offsetPage && postWindow < offsetWindow)) {	//判断元素始终在可见区域内
                    if (tag === "img" && attrSrc !== null) {
                        o.setAttribute("src", attrSrc);
                    }
                    o = null;
                }
            }
        };
        window.onscroll = function () {
            setTimeout(function () {
                _init();
            }, defaults.time);
        }
    };
    return _init();
});


//global-nav 当前导航图标与字体变色
function gnFun() {
    var cC = "#e94c2a",
        oC = "#888888",
        oL = $("#gn-ul>li"),
        timer = null;
    oL.each(function() {
        var _this = $(this),
            _svg = _this.find("svg"),
            _sFi = _svg.find("path");
        if (_this.hasClass("current")) {
            _sFi.attr("fill" , cC);
        } else {
            _sFi.attr("fill" , oC);
        };
    })
}