$(function() {
    menuControl();//左侧 跟随效果
});

/**
* 个人中心左则菜单栏伸展控制
*/
function menuControl(){
  var onwUrl =window.location+'';
  var statuId='';
  for(var i=0;i<leftUrlAndClassArr.length;i++){
      var lefturlArr = leftUrlAndClassArr[i].split('@');
      if(onwUrl.indexOf(lefturlArr[1])!=-1){
          statuId=lefturlArr[0];
          $("#"+lefturlArr[0]+'>dt').addClass('open');
          $("#"+lefturlArr[0]+'>dd').show();
          $(".l-menu-sub-ol li a[href='" + baselocation + lefturlArr[1] + "']").parent().addClass("current");
          return;
      }else{
          if(statuId!=lefturlArr[0]){
              $("#"+lefturlArr[0]+'>dt').removeClass('open');
              $("#"+lefturlArr[0]+'>dd').hide();
              $(".l-menu-sub-ol li a[href='" + baselocation + lefturlArr[1] + "']").parent().removeClass("current");
          }
      }
  }
}

//个人中心左侧导航定位
var leftUrlAndClassArr = [
  
  'leftdl_course@/uc/course',
  'leftdl_course@/uc/study',
  'leftdl_course@/uc/fav',
  'leftdl_course@/uc/note',
  'leftdl_course@/uc/mylive',
  'leftdl_course@/uc/home',
  
  'leftdl_task@/uc/myArrangeExam',
  'leftdl_task@/uc/planRecordList',
  

  'leftdl_sns@/dis/',
  'leftdl_sns@/weibo',
  'leftdl_sns@/friend',
  'leftdl_sns@/sug',
  'leftdl_sns@/p/',
  'leftdl_sns@/u/',
 
  'leftdl_ques@/uc/ques/add',
  'leftdl_ques@/uc/ques/my',
  'leftdl_ques@/uc/question',
  'leftdl_ques@/uc/myCouAnswer',
  
  'leftdl_order@/uc/order',
  'leftdl_order@/uc/cash/order',
  'leftdl_order@/uc/address',
  'leftdl_order@/uc/goUserAddress',
  'leftdl_order@/uc/myinte',
  'leftdl_order@/uc/integift',
  'leftdl_order@/uc/mygift',
  'leftdl_order@/gift/doexchange',
  'leftdl_order@/uc/level',
/*  'leftdl_order@/bookOrder/myBookOrderList',*/

  
  'leftdl_account@/uc/acc',
  'leftdl_account@/uc/card',
  'leftdl_account@/uc/member',
  'leftdl_account@/uc/uinfo',
  'leftdl_account@/uc/uppwd',
    'leftdl_account@/uc/updateMobile',
    'leftdl_account@/uc/updateEmail',
  'leftdl_account@/uc/avatar',
  'leftdl_account@/letter',
  'leftdl_account@/uc/myProfile'

];


/**
* 个人中心打开关闭左侧菜单导航
*/
function lMenu() {
	$(".uc-m-l-menu>dl>dt").each(function() {
		var _this = $(this);
		_this.click(function() {
			if(_this.next("dd").is(":hidden")) {
				_this.addClass("open");
				_this.next("dd").slideDown(100);
				_this.parent().siblings("dl").children("dt").removeClass("open");
				_this.parent().siblings("dl").children("dd").slideUp(100);
			} else {
				_this.removeClass("open");
				_this.next("dd").slideUp(100);
				_this.parent().next("dl").children("dt").addClass("open");
				_this.parent().next("dl").children("dd").slideDown(100);
			}
		});
	});
};