var windsShowState=0;
$(function() {
	showuserLoginInfo();
	lMenu();//打开关闭左侧菜单导航
    menuControl();//左侧 跟随效果
	uPosition();//明星老师定位 首页 我的课程 收藏中 
	//lMenuFun();//左侧目录跟随滚动固定
});

/**
 * 个人中心显示邮箱
 * 头像
 */
function showuserLoginInfo(){
	var bannerUrl='';//获取图片做为背景图片
	$.ajax({
		url : baselocation+"/user/loginuser",
		data : {},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.success){
				var entity = result.entity;
                if(isNotNull(entity)){
                    $("#unameheader").html(entity.showname);
                    $("#unameright").text(entity.showname);
                    if(entity.avatar && isNotEmpty(entity.avatar)){
                        $("#cusImg").attr("src",staticImageServer+entity.avatar);
                        $("#userImgId").attr("src",staticImageServer+entity.avatar);
                    }else{
                        $("#cusImg").attr("src","/static/common/images/user_default.jpg");
                        $("#userImgId").attr("src","/static/common/images/user_default.jpg");
                    }
                    bannerUrl=entity.bannerUrl;//背景图片
                }
			}
		}
	});
    //******
    //获取Cookie中的图片做为背景图片
    var imgUrl =getCookie('usercookieuserimg');
    if(isNotEmpty(imgUrl) && imgUrl!='notimg'&&imgUrl!='undefined'){
        imgUrl='url("'+staticImageServer+imgUrl+'") no-repeat scroll 50% 50px #FAFAFA';
        $('body').css("background",imgUrl);
    }else if(bannerUrl!=''&&typeof(bannerUrl)!='undefined'){
    	imgUrl='url("'+staticImageServer+bannerUrl+'") no-repeat scroll 50% 50px #FAFAFA';
    	SetCookie('usercookieuserimg',bannerUrl);
        $('body').css("background",imgUrl);
    }else{//如果Cookie中没有图片就是使用默认图片
        imgUrl='url('+imagesPath+'/static/edu/images/u-center/u-banner/u-b-1.jpg) no-repeat 50% 50px #FAFAFA';
        $('body').css("background",imgUrl);
    }

}

 	function getScore(){
 		$.ajax({  
			url : baselocation+"/cus/uc!getScore.action",  
			data : { },  // 参数  
			type : "post",  
			cache : false,    
			dataType : "json",  //返回json数据 
			success : function(result) {
				var scoreAndFlag = result.entity;
				var score = scoreAndFlag.split(",")[0];
				$("#score").empty();
				$("#score").append(score);
				if(scoreAndFlag.split(",")[1]==1){
					$("#jifenhref").attr("style","background-position:-71px -350px;");
					$("#jifenhref").unbind('click').removeAttr('onclick');
				}
			},
			error: function(){ 
			}
		});
 	}

	function uPosition() {
		$(".u-teacher-list>li:nth-child(3n)").css({"margin-right" : "0px"});
		$(".t-q-list-cel>tr").hover(function() {
			$(this).toggleClass("hover");
		});
		$(".o-answer-list").hover(function() {
			$(this).find(".answer-cn-btn").toggle();
		});
	};

	function changeCard(oTitle, oCont, current) {
		var oTitle = $(oTitle),
			oCont = $(oCont),
			_index;
		oTitle.click(function() {
			_index = oTitle.index(this);
			$(this).addClass(current).siblings().removeClass(current);
			oCont.eq(_index).show().siblings().hide();
		}).eq(0).click();
	}
	
	
	function stopFun(e) {
		document.all ? event.cancelBubble = true : e.stopPropagation();
	}
	
	function switching(em,type){
		if(type==1){
			upImagesUrl='';
			$("#upfouleImages").attr('src',baselocation+'/static/edu/images/u-center/u-banner/ubm_default.jpg');
		}else if(type==2){
			$('#templete1 .templete_list').children(".current").removeClass('current');
		}
		setBodyImg(ba_image,ba_color);
		
	}
	
	/**
	 * 设置个性化图片
	 */
	var skinChange = function() {
		if(windsShowState!=0){
			return false;
		}
		
		windsShowState++;
		ba_image =$('body').css("background-image");//得到原有的背景图片
		ba_color = $('body').css("background-color");//得到原有的背景颜色
		ba_repeat = $('body').css("background-repeat");//得到背景图片是否可重复
		ba_position = $('body').css("background-position");//得到背景图片的位置
		
		var dialogEle = $('<div id="winId" class="bChange dWrap dialog-shadow" style="z-index: 99999;"><div class="b-fff"><div class="hLh30 pr b-333"><h4 class="hLh30 pl10 pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span class="f-fM c-fff">个性化封面图设置</span></h4></div><div id="skinWrap" class="pb10 of"></div></div></div>').appendTo($("body")).addClass("animate-enter animated flyIn5"),
		 	dTxt = $('<div><header class="setTitle"><ul id="setT"><li class="current" onclick="switching(this,1)"><a href="javascript:void(0)">模板</a></li><li onclick="switching(this,2)"><a href="javascript:void(0)">自定义</a></li></ul></header><div id="setCont"><section class="dis" id="templete1">1</section><section class="undis" id="templete2">2</section></div></div>').prependTo($("#skinWrap"));
		var tempL = "";
			tempL += '<div class="templete_list">';
			tempL += '<div class="clear"></div></div>';
			tempL += '<div class="sPageBar tac hLh20">';
			tempL += '<a onclick="pageGo(1)" href="javascript:void(0)" title="上一页" class="sUp"></a><a onclick="pageGo(2)" href="javascript:void(0)" title="下一页" class="sDown"></a></div>';
			tempL += '<div class="mt10 ml30 mr30 tLine2 pt10 pb10"><div class="tar">';
			tempL += '<a href="javascript:void(0);" onclick="applicationImg(0)" class="disIb skinBtn c-fff">应用</a><a href="javascript:void(0)" class="disIb cancel_sBtn c-666 ml10">取消</a></div></div>';
		$("#templete1").html(tempL);
		getUserPersonalityImages(1);
		var tempC = "";
			tempC += '<div class="templete_custom">';
			tempC += '<h6 class="hLh30 unFw ml10 mr10"><span class="c_666">自定义封面背景图</span></h6>';
			tempC += '<div class="clearfix tLine2 pt20 ml10 mr10">';
			tempC += '<div class="fl tcPic"><span><img id="upfouleImages" src="/static/edu/images/u-center/u-banner/zdy_default.jpg" width="120" height="60"></span></div>';
			tempC += '<div class="temCustom">';
			tempC += '<div><input type="button" id="fileuploadButton"	value="上传"/></div>';
			tempC += '<div class="mt50"><div class="u-tip pl10 pr10 of">';
			tempC += '<font class="vam c-666">图片最小宽度为:1000象素，高度为:340象素。支持不超过1M的jpg、gif、png图片上传</font>';
			tempC += '</div></div></div>';
			tempC += '<div class="clear"></div>';
			tempC += '<div class="mt20 tLine2 pt10 pb10"><div class="tar">';
			tempC += '<a href="javascript:void(0)" onclick="applicationImg(1)" class="disIb skinBtn c-fff">保存</a><a href="javascript:void(0)" class="disIb cancel_sBtn c-666 ml10">取消</a></div></div>';
			tempC += '</div>';
			tempC += '</div>';
		$("#templete2").html(tempC);
		//初始化上传图片
		initSimpleImageUpload('fileuploadButton','upfouleImages','');
			var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
			 	dH = dialogEle.height();
			dialogEle.css("top" , dTop-(dH/2));
			changeCard("#setT>li", "#setCont>section", "current");
			$(".dClose ,.cancel_sBtn").click(function() {
				windsShowState=0;
				dialogEle.removeClass("animate-enter animated flyIn5").remove();
				setBodyImg(ba_image,ba_color);
				upImagesUrl='';
			});
		//个性化封面图设置选中状态
		var imgUrl = getCookie('usercookieuserimg');
		if(imgUrl==null||imgUrl=='notimg'){
			$("#sysImg0").parent().addClass("current");//
		}else{
			derId=imgUrl.split('\|')[2];
			$("#sysImg"+derId).parent().addClass("current");//
		}
	};
	
	//针对课程卡激活 弹出框方法(dTitle弹出框标题  coent内容  index下标)
	function dialog(dTitle,coent,index,url) {
		
		var dialogEle = $('<div class="dialog-shadow"><div class="dialog-ele"><h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span class="d-s-head-txt">'+dTitle+'</span></h4><div id="dcWrap" class="pt20 pb20 pl20 pr20 of" style=""></div></div></div>').appendTo($("body")).addClass("animate-enter animated flyIn5");
		$.ajax({
			url:baselocation+"/common/dialog",
			data:{"dialog.title":dTitle,
				  "dialog.conent":coent,
				  "dialog.index":index,
				  "dialog.url":url
				  },
			type:"post",
			dataType:"text",
			async:false,
			success:function(result){
				$("#dcWrap").html(result);
			}
		});
		/*var dCont = [
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a id='tisbutt' href='javascript:void(0)' title='' class='dClose order-submit'>确定</a></div></div>",
			"<div class='d-tips-4'><ul class='l-r-w-Inpt'><li><label class='vam'>卡号：</label><input type='text' class='lTxt' value='' id='cardCourseCode' name='cardCourse.cardCourseCode'></li><li class='mt20'><label class='vam'>密码：</label><input type='password' class='lTxt' value='' id='cardCoursePassword' name='cardCourse.cardCoursePassword'></li><li class='mt20'><label class='vam'>&nbsp;</label><span class='login-btn'><input type='button' onclick='jihuo()' value='激 活' style='margin-left: 0;'></span></li></ul></div></div>",
			"<div class='d-tips-4'><ul class='l-r-w-Inpt'><li><label class='vam'>订单编号：</label><strong class='c-orange' id='contractId'>"+coent+"</strong></li><li class='mt20'><label class='vam'>激&nbsp;活&nbsp;码&nbsp;：</label><input maxlength='4' class='activationOrdersWin' type='text'  id='contractCDkey1'/>-<input class='activationOrdersWin' maxlength='4' id='contractCDkey2' type='text'/>-<input maxlength='4' class='activationOrdersWin' type='text' id='contractCDkey3'/>-<input maxlength='4' class='activationOrdersWin' type='text' id='contractCDkey4'/></li><li class='mt2 ntsyl c-666'>每框输入4位激活码，激活成功后可以学习其中的课程</li><li class='mt20'><label class='vam'>&nbsp;</label><span class='login-btn'><input type='button' onclick='actCourse()' value='激 活' style='margin-left: 0;'></span></li></ul></div></div>",
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a href='"+url+"' title='确定' class='d-cancel order-submit'>确定</a><a href='javascript:void(0)' title='' class='d-cancel goBack-btn ml10' id='qujiao'>取消</a></div></div>",
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a id='tisbutt' href='javascript:void(0)' title='' class='dClose order-submit'>确定</a></div></div>",
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a href='"+url+"' title='确定' class='d-cancel order-submit'>确定</a><a href='javascript:void(0)' title='' class='d-cancel goBack-btn ml10' id='qujiao'>取消</a></div></div>",
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-2'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a id='tisbutt' href='javascript:void(0)' title='' class='dClose order-submit'>确定</a></div></div>",
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-2'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a id='tisbutt' href='"+url+"' title='' class='dClose order-submit'>确定</a></div></div>",
			"<div class='d-tips-2'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"+coent+"</p><div class='tac mt30'><a id='tisbutt' href='"+url+"' title='' class='dClose order-submit'>确定</a></div></div>"
		];
		$("#dcWrap").html(dCont[index]);*/
		var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
			dH = dialogEle.height(),
			dW = dialogEle.width(),
			dHead = $(".dialog-ele>h4");
		dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
		dHead.css({"width" : (dW-"12")}); //ie7下兼容性;
		$(".dClose,.d-cancel").bind("click", function() {
			$("#activateCardCourse").show();
			var acwin = $("#actdivWinId");
			if(acwin!=null && acwin!=''){
				$(acwin).hide();
			}
			dialogEle.removeClass("animate-enter animated flyIn5").remove();
			//oBg.remove(); 
		});
	}
	
	/**
	 * 应用图片
	 */
	function applicationImg(reqType){
		var imagesUrl='';
		var type=reqType;
		var imgData;
		var imgDataArr;
		//判断是上传的图片还是系统的图片
		if(type==1){//上传图片
			imagesUrl=upImagesUrl;
			imageId=0;
			imagesUrl= imagesUrl.replace(staticImageServer,'');
			if(upImagesUrl==null|| upImagesUrl.trim()==''){
				dialog('提示','请上传图片',1,'','');
				return false;
			}
		}else{//系统图片
			imgData = $('#templete1 .templete_list').children(".current").children(":hidden").val();
			if(imgData!=null && imgData!=''){
				imgDataArr = imgData.split('\|');
				imagesUrl=imgDataArr[0];//新图片的路径
				if(imagesUrl.indexOf(imagesPath)==-1)//判断是不是默认图片
				{
					imagesUrl= imagesUrl.replace(staticImageServer,'');
				}
				else//默认图片不上传图片地址
				{
					imagesUrl="";
				}
			}else{
				dialog('提示','请选择图片',1,'','');
				return false;
			}
		}
		$.ajax({
			type:'post',
			dataType:'json',
			data:{
				'bannerUrl':imagesUrl//图片图上URL//'queryImagesCondition.type':type//发送类型
			},
			url:baselocation+'/uc/updateusercover',
			success:function (result){
				if(result.success){
					$("#winId").remove();
					windsShowState=0;
					var obj = result.entity;
					//重设Cookie
					var newUrl = obj;
					if(newUrl!="")//非默认图片
					{
						SetCookie('usercookieuserimg', newUrl);
					}
					else//默认图片
					{
						SetCookie('usercookieuserimg', "notimg");
					}
					dialog('应用成功','个性化封面图设置应用成功',15);
				}
			},
			error:function (error){
				dialog('提示','系统繁忙，请稍后再操作！',1,'','');
			}
		});
		
	}
	
	/**
	 * 获取系统个性化图片
	 * @returns {String}
	 */
	function getUserPersonalityImages(pageCount){
		var imgConent ='<a><input id="sysImg0" type="hidden" value="'+imagesPath+'/static/edu/images/u-center/u-banner/u-b-1.jpg|#FAFAFA|0"/><img src="'+imagesPath+'/static/edu/images/u-center/u-banner/ubm_default.jpg" width="120" height="60" /><span class="tplname">风清云淡</span></a>';
		var pageNo = pageCount;
		$.ajax({
			type:'post',
			dataType:'json',
			async:true,
			data:{'page.currentPage':pageNo},
			url:baselocation+'/uc/usercover',
			success:function (result){
				if(result.success){
					var obj = result.entity;
					totalPage=obj.page.totalPageSize< 1 ?1 :obj.page.totalPageSize;//总页数
					totalRecord=obj.page.totalResultSize;//总记录数
					currentPage=obj.page.currentPage;//当前页数
					var list = obj.websiteImagesList;
					for(var i=0;i<list.length;i++){
						imgConent+='<a><input type="hidden" id="sysImg'+list[i].id+'" value="'+staticImageServer+list[i].imagesUrl+'|'+list[i].color+'|'+list[i].id+'"/><img src="'+staticImageServer+list[i].previewUrl+'" width="120" height="60" /><span class="tplname">'+list[i].title+'</span></a>';
					}
					$(".templete_list").html(imgConent);
					$("#templete1 .templete_list>a").click(function() {
						$(this).addClass("current").siblings().removeClass("current");
						previewImg($(this).children(':hidden').val());
					});
					if(totalPage<=1){
						$(".sPageBar").addClass('undis');
					}
				}else{
					dialog('错误提示','请求数据错误！',1);
					return;
				}
			},
			error:function (error){
				dialog('错误提示','系统繁忙，请稍后再操作！',1);
			}
		});
	}
	
	
	/**
	 * 背景图片预览效果
	 */
	function previewImg(data){
		var imgDataArr=data.split("\|");
		setBodyImg('url(\"'+imgDataArr[0]+'\")','#FAFAFA');
	}
	/**
	 * 设置背景图片
	 * @param imgUrl
	 * @param color
	 */
	function setBodyImg(imgUrl,color){
		$('body').css("background-image",imgUrl);
		$('body').css("background-color",color);
	}
	
	/**
	 * 翻页
	 * @param type
	 */
	function pageGo(type){
		var page = currentPage;
		page = parseInt(page);
		if(type=='1'){
			if(page >1 && (page-1)>0 ){
				getUserPersonalityImages(page-1);
			}
		}else if(type=='2'){
			if(page<totalPage && (page+1)<=totalPage){
				getUserPersonalityImages(page+1);
			}
		}
	}
	
	function getAcmentTop(subjectId){
		$("#side_ann").empty();
		if(subjectId == null || typeof(subjectId) == undefined || subjectId==""){
			subjectId=getCookie("subjectId");
		}
		$.ajax({
				url : baselocation + "/cus/cusweb!ajaxAcment.action",
				type : "get",
				dataType : "json",
				data : {"subjectIds":subjectId},
				cache : true,
				success : function(result){
					var data = "";
					if(result.jumpUrl!="")
					{
					 data = eval("("+result.jumpUrl+")");
					}
					var buffer = new StringBuffer();
					buffer.append("<h2 class='side_title'>网站公告</h2>");
					$.each(data,function(name,value){
						var subtitle = value.title;
						buffer.append("<ul><li>");
						buffer.append("<a href='"+baselocation+"/cms/acmentweb!view.action?id="+value.id+"&subjectIds="+subjectId+"' title="+value.title+" target='_blank'>");
						if(subtitle.length>10){
							subtitle = subtitle.substring(0,10);
						}else if(subtitle.length>12){
							subtitle = subtitle.substring(0,12);
						}
						buffer.append(""+subtitle+"");
						buffer.append("</a>");
						if(name<3){
							buffer.append("<img alt='"+value.title+"' src='"+imagesPath+"/images/usercenter/new_icon.png' />");
						}
						buffer.append("</li></ul>");
					});
					$("#side_ann").html(buffer.toString());
				}
			});
			
	}

	function clickHandler(event){
		$("div.user_pop").empty();
		var input = new StringBuffer();
		input.append("<span class='user_pop_jiao'></span><span class='user_pop_close'>关闭</span>");
		input.append("<dl class='user_pop_info'><dt class='user_pop_pic'><img src="+$(this).attr('phone')+" width='67' height='63'></dt>");
		input.append("<dd>昵 称："+$(this).attr('userName')+"</dd>");
		input.append("<dd>性 别："+$(this).attr('sex')+"</dd>");
		input.append("<dd>课 程："+$(this).attr('otherName')+"</dd></dl>");
		
		$.ajax({
			url : baselocation + "/cus/cusweb!ajaxOtherCusInfo.action",
			type : "get",
			data : {"id":$(this).attr('cusId')},
			dataType : "json",
			cache : true,
			async:false,
			success : function(result){
				if(result.returnMessage == "success"){
					var data = eval("("+result.entity+")");
					input.append(getSellResult(data['sellResult'],data['sellSize']));
					input.append(getCourseResult(data['courseResult'],data['courseSize']));
				}
			},
			error:function(error){
			}//
		});
		
		$("div.user_pop").append(input.toString());
		$("div.user_pop").css({"top":(event.pageY-60)+"px"}).fadeIn();
		hidenHandler();
		
	}

	function getSellResult(result,size){
		var dataResult = new StringBuffer();
		dataResult.append("<div class='user_pop_course'>");
		dataResult.append("<h4>所学课程</h4>");
		dataResult.append("<p>");
		$.each(result,function(name,value){
			var symbol="";
			if(name  == 4 ){
				return false;
			}
			if((name+1)%2!=1){
				symbol = "</br>";
			}else{
				if(size>1){
					symbol = "&nbsp;|&nbsp;";
				}
			}
			dataResult.append("<a href='javascript:void(0)'>"+value.publicName+"</a> "+symbol+"");
		});
		dataResult.append("</p></div>");
		return dataResult.toString();
	}

	function getCourseResult(result,size){
		var dataResult = new StringBuffer();
		dataResult.append("<div class='user_pop_exam'>");
		dataResult.append("<h4>在做试卷</h4>");
		dataResult.append("<p>");
		$.each(result,function(name,value){
			var symbol="";
			if(name ==4 ){
				return false;
			}
			if((name+1)%2!=1){
				symbol = "</br>";
			}else{
				if(size>1){
					symbol = "&nbsp;|&nbsp;";
				}
			}
			dataResult.append("<a target='_blank' href='"+baselocation+"/exam/qstManager!getExamPaperInfo.action?epid="+value.id+"'>"+value.publicName+"</a> "+symbol+"");
		});
		dataResult.append("</p></div>");
		return dataResult.toString();
	}

	function hidenHandler(){
		$(".user_pop_close").click(function() {
	        $(this).parents("div.user_pop").hide();
	    });
	}

	function openActCouWin(contractid) {
		var maskBg = $("<div class='maskBg'></div>").appendTo($("body"));
		var divs="<div class='maskWrap'><div class='mask'><div class='pr'><h4 class='cTipsTitle hLh30 f-fM pl10 pr10 of'><a href='javascript: void(0)' title='关闭' class='cTipsClose fsize16 fr grayCol3'></a><span><strong class='fsize14 grayCol3'>提示信息</strong></span></h4><div class='clearfix'><div class='cTipsTxt2'><div class='dis'><h6><center class='fsize16 f-fM hLh30 greenCol'>请输入课程激活码</center></h6><p class='mt10'><span class='disIb w25per'><b class='f-fM fsize14 grayCol3'>订单编号：</b></span><span class='disIb w75per'><tt class='f-fM fsize14'id='contractId'>"+contractid+"</tt></span></p><p class='mt10'><span class='disIb w25per'><b class='f-fM fsize14 grayCol3'>激&nbsp;&nbsp;活&nbsp;&nbsp;码：</b></span><span class='disIb w75per cIn'>"

		+"<input type='text' onkeyup='if(this.value.length==4)document.getElementById(\"contractCDkey2\").focus()' maxlength='4' class='w40' id='contractCDkey1'> -"
		+"<input type='text' onkeyup='if(this.value.length==4)document.getElementById(\"contractCDkey3\").focus()' maxlength='4' class='w40' id='contractCDkey2'> -"
		+"<input type='text' onkeyup='if(this.value.length==4)document.getElementById(\"contractCDkey4\").focus()' maxlength='4' class='w40' id='contractCDkey3'> -"
		+"<input type='text' maxlength='4' class='w40' id='contractCDkey4'>"
			
		+"</span></p><p class='mt5'><span class='disIb w25per'>&nbsp;</span><span class='disIb  w71per'><tt class='grayCol2'>每框输入4位激活码，输入激活码后开始学习</tt></span></p><center class='uFunc mt20'><input type='button' onClick='javascript:actCourse()' value='确&nbsp;定' class='commBtn bgGreen w75'></center></div><div class='undis'><h6><center class='fsize16 f-fM hLh30 greenCol'>课程卡激活</center></h6><p class='mt10'><span class='disIb w25per'><b class='f-fM fsize14 grayCol3'>输入卡号：</b></span><span class='disIb w75per cIn'><input type='text' class='w200' /></span></p><p class='mt10'><span class='disIb w25per'><b class='f-fM fsize14 grayCol3'>输入密码：</b></span><span class='disIb w75per cIn'><input type='text' class='w200' /></span></p><center class='uFunc mt20'><input type='button' onClick='javascript:actCourse()' value='确&nbsp;定' class='commBtn bgGreen w75'><tt class='ml10 orangeCol'>密码错误</tt></center></div></div></div></div></div></div>";
		var maskEle = $(divs).appendTo($("body")).addClass("animate-enter animated flyIn5");
		
		var maskEleTop = (parseInt(document.documentElement.clientHeight,10)/2) + (parseInt(document.documentElement.scrollTop+document.body.scrollTop,10));
		maskEle.css("top" , maskEleTop - 150);

		$(".cTipsClose").click(function() {$(".maskWrap,.maskBg").removeClass("animate-enter animated flyIn5").remove();});
	};

	//改为动态效果
	var studyProgress_p=0;//百分比
	var studyProgress_i=0;//循环计数
	var timpid=0;//定时id
	function studyProgress(sellId){
		studyProgress_i=0;
		var cusId = 10001;//getUserId();
		var studyProgress = getCookie('studyProgress'+sellId);
		if(studyProgress!=null||studyProgress==""){
			studyProgress_p=Number(studyProgress);
			//timpid=window.setInterval("setstuProgressbar()",18);
		}else{
		$.ajax({
			url : baselocation+"/cou/sellwayweb!studyProgress.action",
			type : "post",
			data : {
				"cusId" : cusId,
				"sellId":sellId
			},
			async: true,
			dataType : "json",
			success : function(result) {
				if(result != 'undefined' && result != null){
					studyProgress_p=Number(studyProgress);
					SetCookieOutTime('studyProgress'+sellId,result.entity,'h1');
					timpid=window.setInterval("setstuProgressbar()",18);
				}else{
					sentMsg(obj,"",argSeconds);
				}
			},
			error : function(error) {
			}
		
		});
		}
	}
	function setstuProgressbar(){
		if(studyProgress_i<=studyProgress_p){
			$(".jdBar").width(studyProgress_i+"%");
			$("#percent").text(studyProgress_i+"%");
			studyProgress_i=studyProgress_i+1;
		}else{
			 window.clearInterval(timpid);//销毁循环执行
		}
		
	}

function getValueOrEmptyVal(type){
    var val= $("#querySellInputId").val();
    if(type==1){
        if(val==derConent){
            $("#querySellInputId").val('');
        }
    }else if(type==2){
        if(val==null||val.trim()==''){
            $("#querySellInputId").val(derConent);
        }
    }
}

function query(){
    var value= $("#querySellInputId").val();
    if(value==derConent){
        return false;
    }
    $("#querySellForm").submit();
}



//个人中心左侧导航定位
var leftUrlAndClassArr = [
  'leftdl_course@/uc/course',
  'leftdl_course@/uc/study',
  'leftdl_course@/front/showcoulist',
  'leftdl_course@/uc/fav',
  'leftdl_course@/uc/note',
  'leftdl_course@/uc/mylive',
  'leftdl_course@/uc/home',
  'leftdl_course@/uc/card',
  'leftdl_course@/uc/myCouAnswer',

  'leftdl_sns@/dis/',
  'leftdl_sns@/weibo',
  'leftdl_sns@/friend',
  'leftdl_sns@/sug',
  'leftdl_sns@/p/',
  'leftdl_sns@/u/',
 
  'leftdl_ques@/uc/ques/add',
  'leftdl_ques@/uc/ques/my',
  'leftdl_ques@/uc/question',
  
  'leftdl_order@/uc/order',
  'leftdl_order@/uc/cash/order',
  'leftdl_order@/uc/address',
  'leftdl_order@/uc/myinte',
  'leftdl_order@/uc/integift',
  'leftdl_order@/gift/doexchange',
  'leftdl_order@/uc/level',
/*  'leftdl_order@/bookOrder/myBookOrderList',*/

  
  'leftdl_account@/uc/acc',
  'leftdl_account@/uc/member',
  'leftdl_account@/uc/uinfo',
  'leftdl_account@/uc/uppwd',
  'leftdl_account@/uc/avatar',
  'leftdl_account@/letter'

];


/**
* 个人中心打开关闭左侧菜单导航
*/
function lMenu() {
	$(".u-menu-list>dl>dt").each(function() {
		var _this = $(this);
		_this.click(function() {
			if(_this.next("dd").is(":hidden")) {
				_this.addClass("curr");
				_this.next("dd").slideDown(100);
				_this.parent().siblings("dl").children("dt").removeClass("curr");
				_this.parent().siblings("dl").children("dd").slideUp(100);
			} else {
				_this.removeClass("curr");
				_this.next("dd").slideUp(100);
				_this.parent().next("dl").children("dt").addClass("curr");
				_this.parent().next("dl").children("dd").slideDown(100);
			}
		});
	});
};

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
          $("#"+lefturlArr[0]+'>dt').removeClass('current');
          $("#"+lefturlArr[0]+'>dd').show();
          return;
      }else{
          if(statuId!=lefturlArr[0]){
              $("#"+lefturlArr[0]+'>dt').addClass('current');
              $("#"+lefturlArr[0]+'>dd').hide();
          }
      }
  }
}

/**
* 左侧目录菜单定位
* @returns {Boolean}
*/
function lMenuFun() {
	if(window.XMLHttpRequst) {
		return false;
	} else {
		var lMenu = $(".u-m-left");
		var marginBot = 0;
		function lmFun() {
			var sH = document.documentElement.scrollTop + document.body.scrollTop;
			if (document.compatMode === "CSS1Compat") {
				marginBot = document.documentElement.scrollHeight - sH - document.documentElement.clientHeight;
			} else {
				marginBot = document.body.scrollHeight - document.body.scrollTop - document.body.clientHeight;
			};
			if(marginBot <= 120) {
				lMenu.css({"position" : "absolute", "top" : "0px"});
			} else {
				lMenu.css({"position" : "fixed" , "top" : "154px"});
			}
		};
		$(window).bind("scroll" , lmFun);
		lmFun();
	}
}
function initSimpleImageUpload(btnId,urlId,valSet){
		KindEditor.create('');
		var uploadbutton = KindEditor.uploadbutton({
			button : KindEditor('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : uploadSimpleUrl+'&param=ucusercover',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = KindEditor.formatUrl(data.url, 'absolute');//absolute,domain
					KindEditor('#'+urlId+'').attr("src",staticImageServer+data.url);
					$("#"+urlId).show();
					//$('#'+valSet+'').val(url);
					upImagesUrl=url;
					setBodyImg('url("'+staticImageServer+''+upImagesUrl+'")','#FAFAFA');
				} else {
					dialog('提示','系统繁忙，请稍后再操作！',1);
				}
			},
			afterError : function(str) {
				alert('自定义错误信息: ' + str);
			}
		});
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
}
