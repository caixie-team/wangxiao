$(function (){
	$(window).load(function(){
		$(".chartProgress").each(function(){
			var _this = $(this);
			var _id = _this.attr("id");
			var _total = _this.attr("peopleNum");
			var _complete = _this.attr("completeNum");
			_this.css("width","140px");
			_this.css("height","140px");
			init(_id,_total,_complete);
		});


		$("ul.net-list-head").each(function(){
			var _this = $(this);
			_this.find("li.c-tab-title>a").each(function(i,val){
				var obj = $(this);
				obj.bind('click',function(){
					// TODO: 处理激活查询问题
					//if(obj.hasClass("active")){
					//	return;
					//}
					//obj.addClass("active").siblings().removeClass("active");
					queryCourseBySubjectId(obj.attr("id"),_this);
				});
				if(i==0){
					obj.click();
				}
			});
		});

	});

	//ePosition();
	sSwiperFun(); //幻灯片调取
	nSlideUp(); //公告滚动调取
	courSlide();//岗位课程轮转
	upSlideFun("#staReview"); //员工评价向上滚动
	$(".com-dynamic-list>li:first").addClass("c-d-firstli");
	//enterDym();

	//统计图表
	drawCartogramLogin(30);
	drawCartogramCoursePlay(30);
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


//响应式幻灯片方法
function sSwiperFun() {
    var mySwiper = new Swiper('.i-slide .swiper-container', {
        pagination: '.i-slide .pagination',//设置分页
        loop: true, //无缝连接滚动
        autoplay : 5000, //自动滚动
        autoplayDisableOnInteraction : false, //设置点击后是否继续滚动
        speed:300, //滚动速度
        /*pagination : '.pagination', */
        paginationClickable : true //设置true分页点击执行swiper
    });

    $('.arrow-left').on('click', function(e){
        e.preventDefault();
        mySwiper.swipePrev();
    });
    $('.arrow-right').on('click', function(e){
        e.preventDefault();
        mySwiper.swipeNext();
    })
    /*_sLi = $(".i-slide .swiper-slide>img").eq(0);
    $(".i-slide").css("height",_sLi.height());*/
    $(".imgload").eq(0).get(0).onload=function(){
    	$(".i-slide").css("height",$(".imgload").eq(0).height());
    }
}
//课程轮播swiper
function courSlide(){
    var comSwiper = new Swiper('.course-slide .swiper-container', {
        pagination: '.course-slide .pagination',//设置分页
        loop: true, //无缝连接滚动
        autoplay : false, //自动滚动
        autoplayDisableOnInteraction : false, //设置点击后是否继续滚动
        speed:300, //滚动速度
        /*pagination : '.pagination', */
        paginationClickable : true //设置true分页点击执行swiper
    });
     $('.cou-prev').on('click', function(e){
        e.preventDefault();
        comSwiper.swipePrev();
    });
    $('.cou-next').on('click', function(e){
        e.preventDefault();
        comSwiper.swipeNext();
    })
     _sLi = $(".course-slide .swiper-slide>ul li").eq(0);
    $(".course-slide").css("height",_sLi.height()-32+"px");
    $(".course-slidewrap").css("height",_sLi.height()+"px");
}
// 公告滚动方法
function nSlideUp() {
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
}
//向上滚动方法
var upSlideFun = function(od) {
    var _upWrap = $(od),
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
}
// 获取课程
function queryCourseBySubjectId(subjectId,obj){
	$.ajax({
		type:'post',
		dataType:"text",
		url:"/front/ajax/course",
		data:{"subjectId":subjectId},
		async : false,
		success:function(result){
			$(obj).next().html(result);
			//$(obj).next().children("div").hide();
		}
	});
	//scrollLoad();
}
//活跃度按天数
function drawCartogramLogin(days) {
    var dateTime = '';
    var studentNum;
    $.ajax({
        url:"/statistics/web/detailajax",
        type:"post",
        data:{"days":days,"type":"LOGIN_NUM"},
        dataType:"json",
        async:false,
        success:function(result){
            if(result.success){
                dateTime=result.entity;
                studentNum= result.message.split(",");
				drawLogin('left_container','最近'+days+'天活跃学员',"活跃人数",dateTime,studentNum);
            }else{
                //alert("请求错误!");
            }
        }
    });
}
//课程播放按天数
function drawCartogramCoursePlay(days) {
	var dateTime;
	var playCount;
	$.ajax({
		url:"/statistics/web/getStatisticListByDays",
		type:"post",
		data:{"days":days},
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success){
				dateTime = result.entity.split(",");
				playCount= result.message.split(",");
				drawCoursePlay('right_container','最近'+days+'天播放记录',"播放记录",dateTime,playCount);
			}else{
				//alert("请求错误!");
			}
		}
	});
}

function drawLogin(id,title,text,xlist,vlist){
	var option = {
		title : {
			text: title,
			x: "center"
		},
		tooltip : {
			trigger: 'axis'
		},
		legend:{
			data:[text],
			x: "left"
		},
		calculable : true,
		xAxis : [
			{
				type : 'category',
				boundaryGap : false,
				data : xlist
			}
		],
		yAxis : [
			{
				type : 'value',
				axisLabel : {
					formatter: '{value}'
				}
			}
		],
		series : [
			{
				name:text,
				type:'line',
				data:vlist,
				markPoint : {
					data : [
						{type : 'max', name: '最大值'},
						{type : 'min', name: '最小值'}
					]
				},
				markLine : {
					data : [
						{type : 'average', name : '平均值'}
					]
				}
			}
		]
	};
	initLineEcharts(id,option);
}
function drawCoursePlay(id,title,text,xlist,vlist){
	var option = {
		title : {
			text: title,
			x: "center"
		},
		tooltip : {
			trigger: 'axis'
		},
		legend:{
			data:[text],
			x: "left"
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				boundaryGap : false,
				data : xlist
			}
		],
		yAxis : [
			{
				type : 'value',
				axisLabel : {
					formatter: '{value}'
				}
			}
		],
		series : [
			{
				name:text,
				type:'line',
				data:vlist,
				markPoint : {
					data : [
						{type : 'max', name: '最大值'},
						{type : 'min', name: '最小值'}
					]
				},
				markLine : {
					data : [
						{type : 'average', name : '平均值'}
					]
				}
			}
		]
	};
	initLineEcharts(id,option);
}
//生成折线图
function initLineEcharts(id,option) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(id));
	myChart.setOption(option);
}
// 初始化echart
function init(id,totalTime,completeTime){
	var myChart = echarts.init(document.getElementById(id));
	var percentage = (Math.round(completeTime / totalTime * 10000) / 100.00 + "%");
	var option = {
		title:{
			text: '完成\n'+percentage,
			x: 'center',
			y: 'center',
		},
		tooltip: {
			trigger: 'item',
			formatter: "{b}: {d}%"
		},
		series: [
			{
				name:'完成进度',
				type:'pie',
				radius: ['60%', '80%'],
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: false,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				data:[
					{
						value:totalTime-completeTime,
						name:'未完成',
						itemStyle:{
							normal:{color:'#CFD8E6'}
						}
					},
					{
						value:completeTime,
						name:'完成',
						itemStyle:{
							normal:{color:'#F60'}

						}
					}
				]
			}
		]
	};
	myChart.setOption(option);
}