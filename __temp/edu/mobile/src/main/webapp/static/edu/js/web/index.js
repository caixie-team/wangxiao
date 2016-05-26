var dersellName ='今天你想学什么课程？';
$(function (){
	//ePosition();
	$("#sellName").val(dersellName);
	oSlider(); //首页幻灯片
	//向上滚动公告内容
	var _upWrap = $("ul.newsNotice"),
		  _sTime = 5000,
		  _moving;
	_upWrap.hover(function() {
		clearInterval(_moving);
	}, function() {
		_moving = setInterval(function() {
			var _mC = _upWrap.find("li:first");
			var _mH = _mC.height();
			_mC.animate({"margin-top" : -_mH + "px"}, 600, function() {
				_mC.css("margin-top", 0).appendTo(_upWrap);
			});
		}, _sTime);
	}).trigger("mouseleave");
	//显示所有专业的课程
	querycourseBySubjectId($("#allsubjectcourse"),0);
	
	
	
	
	
});
$(function(){
	var u=window.location.href;
	var us=u.split('?');
	if(us[1]=='false'||us[1]=='false#uPosition'){
		dialog('提示信息','您的帐号已在其它地方登录',15,'/static/web/global/toIndex.html','');
	}
	else{
	if(us[1]!=undefined)
		SetCookie(upUserId, us[1]);
	}
});


//首页全屏适应幻灯片
function oSlider() {
	var Wind = {};
	Wind.Focus = {
		init:function (args){
			this.id = $(args.id);
			this.aBtn = $(args.aBtn);
			this.li = $(args.li);
			this.ms = args.ms;
			this.auto = args.auto?args.auto:false;
			this.on = args.on;
			this.nextTarget = 0;
			this.autoTimer = null;
			this.start();
		},
		start:function (){
				var _this = this;
				var color = this.li.eq(0).find("a").attr("name");
				this.id.siblings(".slideColorBg").show().css("background-color", color);
				this.aBtn.each(function(){
					var index = _this.aBtn.index(this);
					$(this).hover(function(){
						_this.showSlides(index);
					});
				});
				this.autoTimer = setInterval(function(){
					_this.autoPlay();	
				},_this.ms);
				this.id.hover(function(){
					clearInterval(_this.autoTimer);
				},function(){
					_this.autoTimer = setInterval(function(){
						_this.autoPlay();	
					},_this.ms);
				});
		},
		showSlides:function (index){
			this.nextTarget = index;
			var color = this.li.eq(index).find("a").attr("name");
			var _thisId = this.id;
			this.aBtn.eq(index).addClass(this.on).siblings().removeClass(this.on);
			this.li.eq(index).fadeIn(800).siblings().hide();
			_thisId.siblings(".slideColorBg").fadeIn("800", function(){$(this).css("background-color", color)});
		},
		autoPlay:function (){
			this.nextTarget++;
			if(this.nextTarget > this.li.length - 1){
				this.nextTarget = 0;
			}	
			this.showSlides(this.nextTarget);
		}	
	}

	Wind.Focus.init({
		//幻灯片id
		id: "#oSlideFun",
		//按钮
		aBtn: "#oSbtn a",
		//大图li
		li: "#oSlideFun li",
		//按钮鼠标放上时
		on: "on",
		//自动播放的时间
		ms: 3000,
		//是否自动播放
		auto: true
	});
}
function getNewFreeSell(aelm,type){
	if($(aelm).parent('li').hasClass('current')==false){
		if(type==1){
			$(aelm).parent('li').addClass('current');
			$(aelm).parent('li').next('li').removeClass('current');
			$("#newWelcomeSellWayListUlId").show();
			$("#newFreeSellWayListUlId").hide();
		}else if(type==2){
			$(aelm).parent('li').addClass('current');
			$(aelm).parent('li').prev('li').removeClass('current');
			$("#newWelcomeSellWayListUlId").hide();
			$("#newFreeSellWayListUlId").show();
		}
	}
}
//处理老师
function withTeahcer(teacherIds,teacherNames){
	var tNames=teacherNames.replace(/\|/g,' ').trim().replace(/ /g,'-');
	var tId = teacherIds.replace(/\|/g,' ').trim().replace(/ /g,'-');
	tId = tId.replace(/ /g,'-');
	var tIdArr = tId.split("-");
	var tNameArr= tNames.split("-");
	var conent='';
	for(var i=0;i<tIdArr.length;i++){
		if(tIdArr[i]!=null && tIdArr[i]!=''){
			conent+='<a class="c-666" href="'+baselocation+'/front/teacher='+tIdArr[i]+'&querySellWayCondition.currentPage=1" title="'+tNameArr[i]+'">'+tNameArr[i]+'&nbsp;&nbsp;</a>';
		}
	}
	return conent;
}
/**
 * 根据专业id获取课程 首页切换专业用
 * @param em
 * @param subjectId
 * @returns {Boolean}
 */
function querycourseBySubjectId(em,subjectId){
	if($(em).parent('li').hasClass('current')){
		return false;
	}
	$.ajax({
		type:'post',
		dataType:"text",
		url:"/front/ajax/course",
		data:{"subjectId":subjectId},
		async : false,
		success:function(result){
			$("#s_c_list_ol_ID").html(result);
			//$(".s-c-list>li:nth-child(4n)").css({"margin-right" : "0px"});
			$(em).parent('li').parent('ol').find('li').removeClass('current');
			$(em).parent('li').addClass('current');
		}
	});
	scrollLoad();
}

function queryPageIndex(em,type){
	if($(em).parent('li').hasClass('current')){
		return false;
	}
	$(em).parent('li').parent('ul').find('li').removeClass('current');
	$(em).parent('li').addClass('current');
	if(type==1){
		$("#oneRightUlId").hide();
		$("#groomUlId").show();
	}else{
		$("#oneRightUlId").show();
		$("#groomUlId").hide();
	}
}

function search(){
	if($("#sellName").val()!=dersellName){
		$("#sellNamehidden").val(encodeURIComponent($("#sellName").val()));
	}else{
		$("#sellNamehidden").val('');
	}
	$("#sellWayForm").submit();
}

function inputSell(type){
	if(type==1){
		if($("#sellName").val()==dersellName){
			$("#sellName").val('');
		}
	}else if(type==2){
		if($("#sellName").val()==null ||$("#sellName").val().trim()==''){
			$("#sellName").val(dersellName);
		}
	}
}