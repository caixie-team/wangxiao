var currentCourseId = 0;
var randomIndex = 1;// 讲师分页获取从第一页开始

$(function() {
	// 等级升级提示层显隐
	$(".levelSpanWrap").hover(function() {
		$(".levelTips").show();
	}, function() {
		$(".levelTips").hide();
	});
});

// u-index
function getNewFreeSell(aelm, type) {// 获得免费课程
	if ($(aelm).parent('li').hasClass('current') == false) {
		if (type == 1) {
			$(aelm).parent('li').addClass('current');
			$(aelm).parent('li').next('li').removeClass('current');
			$("#newWelcomeSellWayListUlId").css('display', 'none');// show();
			$("#newFreeSellWayListUlId").css('display', 'block');
		} else if (type == 2) {
			$(aelm).parent('li').addClass('current');
			$(aelm).parent('li').prev('li').removeClass('current');
			$("#newWelcomeSellWayListUlId").css('display', 'block');
			$("#newFreeSellWayListUlId").css('display', 'none');
		}
	}
}

$(document).ready(function() {
	$(".ckkcBtn").click(function() {
		$(this).children("tt").toggleClass("closeCkkc");
		$(this).parent().parent().parent().siblings(".ugkc").fadeToggle(100);
	});
});
function losedate() {
	dialog('提示', "您的课程已经过期，不能继续观看！", 1);
}

function noCouserMsg() {
	dialog('提示', "温馨提示：课程暂未上传，请耐心等待！", 1);
}

function getupsizeper(psizeper) {
	if (psizeper.indexOf(".") < 0) {
		return psizeper;
	} else {
		return psizeper.substring(0, psizeper.indexOf("."));
	}
}

function switch2Free() {
	$(".home_cou").hide();
	$(".home_coutit").hide();
	$("#freeSell").children().show();
}
// 选项卡切换
$(document)
		.ready(
				function() {
					$("#uTabTitle li")
							.click(
									function() {
										if ($("#uTabTitle li").index(this) == 2) {
											window
													.open(baselocation
															+ "/front/homepage!showSellWayList.action?querySellWayCondition.currentPage=1");
										} else {
											$(this).addClass("uHover")
													.siblings("li")
													.removeClass("uHover");
											$(
													"#uTabCont article:eq("
															+ $("#uTabTitle li")
																	.index(this)
															+ ")")
													.show()
													.siblings(
															"#uTabCont article")
													.hide();
										}
									});
				});
function floatScore() {
	$("#jfNumber").show();
	$("#jfNumber").css("opacity", 1);
	$("#jfNumber").css("bottom", "-20px");
	$("#jfNumber").animate({
		bottom : "50px",
		opacity : 0
	}, 1000);
}
/*
 * //定时查询个人总积分 function chat() { if(isLogin()) { var cusId =
 * 100001;//getUserId() cusId = parseInt(cusId); if(cusId <= 0){ return false; }
 * $.ajax({ type:"POST", dataType:"json",
 * url:baselocation+"/cus/uc!timingQueryIntegral.action",
 * success:function(result){ if(result.entity.length>0){ var i=0;
 * $.each(result.entity,function(key,val){ i=i+2; setTimeout(function (){
 * if(val.score > 0){ $("#score").text("+"+val.score); } if(0 > val.score){
 * $("#score").text(val.score); } $("#integralScore").text(val.currentScore);
 * 
 * floatScore(); },i*1000); }); var score = $("#integralScore").text();
 * SetCookie(usercookieintegral,score); }else{
 * $("#integralScore").text(result.returnMessage);
 * SetCookie(usercookieintegral,result.returnMessage); } if(result.jumpUrl){ var
 * cusLevel = result.jumpUrl; var cusLevels=cusLevel.split("#");
 * $("#levelSpan").css({"background-position" : "-"+(cusLevels[0]*36)+"px
 * center"}); $("#levelTitle").html(cusLevels[1]); //距离下次升级分数 var str = '<div
 * class="DT-arrow"><em>◆</em><span>◆</span></div>';
 * if(parseInt(cusLevels[3])!=0)//最高等级 { var
 * levelExp=parseInt(cusLevels[3])-parseInt(cusLevels[2]); str
 * +='当前经验'+cusLevels[2]+'，距离下一级还需'+levelExp; } else { str
 * +='当前经验'+cusLevels[2]+'，已达到最高级'; }
 * 
 * 
 * $(".nextScore").html(str); SetCookie(cusLevels[4],cusLevel); }
 * },error:function(e){ } }); } }
 */

function alldelete() {
	var idarr = $("input[name='sellIdArr']");
	var id = 0;
	for (var i = 0; i < idarr.length; i++) {
		if (idarr[i].checked) {
			id++;
		}
	}
	if (id == 0) {
		dialog('提示', '请选择要删除的课程', 1);
		return false;
	}
	$("#deleteAllFavorite").submit();
}

function allselect(en) {
	 if(en.checked){    
		$("input[name='sellIdArr']").attr("checked", true);
	} else {
		$("input[name='sellIdArr']").attr("checked", false);
	}
}
function expire() {
	dialog("课程提示", '课程已过期!', 1);
}
function watchCourse(courseId) {
	location.href = baselocation + '/front/playkpoint/' + courseId;
}

// 新手引导四步骤方法
function stepFun() {
	$
			.ajax({
				url : baselocation + "/user/queryUserGuideStatus",
				type : "post",
				async : false,
				success : function(data) {
					if (data.returnMessage == "true") {
						return;
					} else {
						var bShadow = $(
								'<div class="bg-shadow bg-shadow05">&nbsp;</div>')
								.appendTo($('body'));
						var stepEle = $(
								'<div class="step-box"><ul><li class="u-step-1"><span title="下一步">&nbsp;</span></li><li class="u-step-2"><span title="下一步">&nbsp;</span></li><li class="u-step-3"><span title="下一步">&nbsp;</span></li><li class="u-step-4"><span title="我知道了！">&nbsp;</span></li></ul></div>')
								.appendTo($('body'));
						$(".step-box>ul>li.u-step-1>span").click(function() {
							$(this).parent().remove();
							$(".step-box .u-step-2").show();
						});
						$(".step-box>ul>li.u-step-2>span").click(function() {
							$(this).parent().remove();
							$(".step-box .u-step-3").show();
						});
						$(".step-box>ul>li.u-step-3>span").click(function() {
							$(this).parent().remove();
							$(".step-box .u-step-4").show();
						});
						$(".step-box>ul>li.u-step-4>span").click(
								function() {
									$(".step-box,.bg-shadow05").remove();
									$.ajax({
										url : baselocation
												+ "/uc!updateUserGuide.action",
										type : "post",
										success : function(data) {
										}
									});
								});
					}
				}
			});

}

// 随机查询老师
function selectRondomTeacher() {

	$
			.ajax({
				url : baselocation + "/uc/rantech",
				type : "post",
				dataType : "json",
				data : {
					"page.currentPage" : randomIndex
				},
				success : function(result) {
					var entity = result.entity;
					if (isNotNull(entity)) {
						var page = result.entity.page;
						if (page.totalPageSize > 0
								&& randomIndex < page.totalPageSize) {
							randomIndex = randomIndex + 1;
						}
						$("#randomteacher").html(result.entity.html);
						uPosition();
					} else {
						$("#randomteacher").html("");
					}

				}
			});
}
