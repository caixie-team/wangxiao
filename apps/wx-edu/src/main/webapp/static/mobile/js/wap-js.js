/* web app html5 commJs 2015.03.23 */

//定位上TopBar与下menuBar的位置；
function posFun() {
	var wH = $(window).height();
	$(".m-ptb54").css("min-height" , (wH-118) + "px");
    $(".l-menu-wrap").css("height" , wH + "px"); //左侧目录菜单高度
}
// global-nav 当前导航图标与字体变色
function gnFun() {
    var cC = "#0959C8",
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
// scrollLoad 滚动加载调用图片方法
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
//点击搜索图标在当前页面加载出搜索页面
function searchFun() {
    var _sBtn = $(".search-icon"),
        iBody = $("body").eq(0),
        ibHei = iBody.height();
    _sBtn.click(function() {
    	iBody.prepend('<div class="search-page-wrap" id="search-page">search</div>');
        var _sp = $("#search-page");
    	$.ajax({
    		url:baselocation+"/mobile/search",
    		type : "post", 
    		dataType : "text",
    		async:false,
    		success: function (result){
		        _sp.addClass("animated0d8s bounceInUp").css("height",ibHei + "px").html(result);
    		}
    	});
    	
       
        $(".s-r-cancel").click(function() {
            _sp.addClass("bounceInDownS");
            setTimeout(function() {
                _sp.remove();
            }, 800);
        })
    })
}

//公共弹出框方法
//[0确定关闭,1确定跳转]
function dialog(title,content,url,num) {
    var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")),
        dialogEle = $('<div id="dialog-ele" class="dialog-ele"><header class="d-head"><h5>'+title+'</h5></header><div id="dcWrap" class="dcWrap">内容位置</div></div>').appendTo($("body")).addClass("animated0d8s bounceInDown");
    var dCont = [
            '<div class="d-tips-0">'+
            '<img src="/static/mobile/img/d-tips-icon.png" width="32" height="32" /><span>'+content+'</span>'+
            '<div class="d-t-btn"><a href="javascript: void(0)" class="closeBtn">确 定</a></div>'+
            '</div>',
            '<div class="d-tips-1">'+
            '<img src="/static/mobile/img/d-tips-icon.png" width="32" height="32" /><span>'+content+'</span>'+
            '<div class="d-t-btn-1"><a class="u-o-c-btn u-o-c-btn-gre" href="'+url+'">确 定</a></div>'+
            '</div>',
            '<div class="d-tips-1">'+
            '<img src="/static/mobile/img/d-tips-icon.png" width="32" height="32" /><span>'+content+'</span>'+
            '<div class="d-t-btn-1"><a class="u-o-c-btn u-o-c-btn-gre" href="'+url+'">确 定</a><a href="javascript: void(0)" class="u-o-c-btn u-o-c-btn-loo closeBtn">取 消</a></div>'+
            '</div>'
            ];
    $("#dcWrap").html(dCont[num]);
    var dH = dialogEle.height();
    dialogEle.css("margin-top" , -(dH/2) + "px");
    var closeDe = function() {dialogEle.remove();oBg.remove();}
    $(".closeBtn").click(function() {closeDe()});
}