$(function(){
	shareFun();
	cardChange(".expandClass",".publicClass","current",function(index){
		var _this = $(".expandClass").eq(index);
		commonSelect(_this.attr("lang"),index);
	});
})
function commonSelect(type,index){
	// 课程目录
	if(type=='catalog'){
		ajaxPage("/ajax/course/kpointList/"+courseId, "", "", function(result){
			$(".publicClass").eq(index).html(result);
		});
	}
	// 评论
	else if(type=="assess"){
		ajaxPage("/front/ajax/assess", "&courseId=" + courseId, 1, function(result){
			$(".publicClass").eq(index).html(result);
		});
	}
	// 咨询
	else if(type=="answer"){
		ajaxPage("/front/ajax/answer_list","&kpointId="+courseId,1,function(result){
			$(".publicClass").eq(index).html(result);
		});
	}
}

// 评论咨询发表后局部刷新
function ajaxFlush(index){
	$(".expandClass").eq(index).find("a").click();
}


// 添加评论
function addAssess() {
	if(!isLogin()){
		lrFun();
		return;
	}
	var commentContent = $("#commentContent").val();
	if (commentContent == null || commentContent.trim() == '') {
		$("#commentContent").val('');
		return false;
	} else {
		$.ajax({
			url : baselocation + "/front/addAssess",
			type : "post",
			dataType : "json",
			data : {
				"courseId" : courseId,
				"kpointId" : 0,
				"content" : commentContent
			},
			success : function(result) {
				if (result.success) {
					$("#commentContent").val('');
					dialogFun("评论课程","发表成功",5,window.location.href);
				} else {
					dialogFun("评论课程","系统异常,请稍后重试",0);
				}
			}
		});
	}
}

// 添加问答
function addProblemFun() {
	if (!isLogin) {
		lrFun();
		return;
	}
	var problemcontext = $("#problemContextTextareaId").val();
	if (problemcontext == null || problemcontext.trim() == '') {
		$("#notProblemTTId").show();
		$("#problemContextTextareaId").val('');
		return false;
	} else {
		if (problemcontext.length > 255) {
			dialogFun('失败', '输入的内容不能超过255个字', 0);
			return;
		}
		$.ajax({
			url : baselocation + '/front/addAnswerQuestion',
			type : "post",
			dataType : "json",
			async : false,
			data : {
				"answerQuestion.type" : "course",
				"answerQuestion.content" : problemcontext,
				"answerQuestion.parentId" : currentCourseId,
				"answerQuestion.sonId" : currentKpointId
			},
			success : function(result) {
				$("#notProblemTTId").hide();
				KindEditor.html("#problemContextTextareaId", "");
				getCourse('gswd', 2);
			}
		});
	}
}

function showReplyDis(id){
	var obj = $("#rePrlblemId"+id);
	if(obj.css("display")=='none'){
		obj.slideDown(150);
		queryReply(id);
	}else{
		obj.slideUp(150);
	}
}

function queryReply(pblId){
	var wordCount = 255;

	var html ='<section class="n-reply-wrap">'+
		'<fieldset>'+
		'<textarea class="" maxlength="'+wordCount+'" name="" id="reProblemInfo'+pblId+'"></textarea>'+
		'</fieldset>'+
		'<p class="of mt5 tar pl10 pr10">'+
		'<span class="c-999 fsize14">还可以输入<tt class="c-red" id="word'+pblId+'">'+wordCount+'</tt>字</span>'+
		'<a class="lh-reply-btn ml15" title="回复" href="javascript:void(0)" id="addReProblemFun'+pblId+'">回复</a>'+
		'</p>'+
		'</section>'
	$.ajax({
		url:baselocation+'/front/ajax/answerReplyList',
		type:'post',
		dataType:'json',
		async:false,
		data:{'answerId':pblId},
		success:function (result){
			if(result.success){
				var obj = result.entity;
				html+='<div class="mt10 reply-list"><section class="review-box mt15"><ul>';
				for(var i=0;i<obj.length;i++){
					if(obj[i].userId!=0){
						var userImg = obj[i].userExpandDto.avatar;
					}else{
						var userImg ="";
					}
					if(userImg!=null&&userImg.trim()!=''){
						if(userImg.indexOf('http')>=0){
							uesrImg = userImg;
						}else{
							uesrImg=staticImageServer+userImg;
						}
					}else{
						uesrImg=baselocation+'/static/common/images/user_default.png';
					}
					var time = new Date(obj[i].addTime).Format("yyyy-MM-dd hh:mm");
					html+='<li class="pr"><aside class="noter-pic"><img width="40" height="40" src="'+uesrImg+'" class="picImg"></aside>';
					html+='<div class="of">' +
						'<span class="fl"><font class="fsize16 c-blue1">'+obj[i].showName+'</font></span>'+
						'<span class="fr c-666 fsize12"><font>'+time+'</font></span>'+
						'</div>'+
						'<div class="noter-txt mt5"><p>'+obj[i].content+'</p></div>'+
						'</li>';
				}

				html+='</ul></section>';
				$("#reply"+pblId).html(html);

				$("#addReProblemFun"+pblId).click(function(){
					pblId = $(this).attr('id').replace('addReProblemFun','');
					var reInfo = $("#reProblemInfo"+pblId).val();
					if(reInfo==null || reInfo.trim()==''){
						dialogFun("答疑回复","回复不能为空",0);
						return;
					}
					$.ajax({
						url:baselocation+'/front/ajax/addAnswerReply',
						type:'post',
						dataType:'json',
						data:{"answerReply.content":reInfo,"answerReply.answerId":pblId},
						success:function (result){
							if(result.success){
								queryReply(pblId);
							}
						}
					});
				});
				$("#reProblemInfo"+pblId).keyup(function(){
					var len = wordCount - $(this).val().length;
					$("#word"+pblId).html(len);
				});
			}
		}
	});
}


// 不是flash
function notFlash(tryUrl) {
	if (tryUrl != null && tryUrl != '') {
		var element = document.getElementById('vedioSpace');
		$(element).html('');
		var script = document.createElement('script');
		script.type = 'text/javascript';// '+tryUrl+'
		script.src = 'http:\//union.bokecc.com\/player?vid=5DAE1A8C8E0897789C33DC5901307461&siteid=4E76CEFF9BBB5FE4&autoStart=true&width=402&height=317&playerid=A98017125E31827D&playertype=1';
		element.appendChild(script);
	}
}
//$(function() {
//	$(".expandClass").click(function() {
//		$(".expandClass").removeClass('current');
//		$(this).addClass('current');
//		var id = $(this).attr('lang');
//		if (id != 'house') {
//			$(".publicClass").hide();
//			$("#" + id).css('display', '');
//		}
//	});
//});
/**
 * 提交课程咨询
 * 
 * @param courseId
 */
function addReview() {
	if (isLogin()) {
		var dyId = $("#dyId").val();
		if (isEmpty(dyId)) {
			dialogFun('提示信息', '请输入咨询内容', 0);
			return;
		}
		$.ajax({
			url : "/front/addAnswerQuestion",
			type : "post",
			dataType : "json",
			data : {
				"answerQuestion.parentId" : courseId,
				"answerQuestion.sonId" : '0',
				"answerQuestion.content" : dyId,
				"answerQuestion.type" : 'COURSE'
			},
			success : function(result) {
				if (result.success) {
					dialogFun('提示信息', '提交成功', 5, window.location.href);
				}else {
					dialogFun('提示信息', '提交失败，请刷新重试', 0);
				}

			}
		});
	} else {
		lrFun();
	}

}
/**
 * 课程大图片点击试听按钮
 */

function openListFree() {
	if (isEmpty(courseId)) {
		dialogFun('试听播放提示', "对不起，该课程暂无试听!", 0,"");
	} else {
		if (isLogin()) {
			//lrFun()
			window.location.href = "/front/playkpoint/" + courseId;
		}else{
			window.location.href=baselocation+'/front/auditionKpoint/'+courseId;
		}

	}
}
/**
 * 个人中心播放页
 */
function doPlay(id) {
	if(id==undefined){
		id=0;
		return;
	}
	if (isLogin()) {
		window.location.href = "/front/playkpoint/" + id;
	}else{
		//lrFun()
		window.location.href=baselocation+'/front/auditionKpoint/'+id;
	}

}

// 直播倒计时
var timerInterval;
function timer(intDiff, i) {
	timerInterval = window.setInterval(function() {
		var day = 0, hour = 0, minute = 0, second = 0;// 时间默认值
		if (intDiff > 0) {
			day = Math.floor(intDiff / (60 * 60 * 24));
			hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(intDiff) - (day * 24 * 60 * 60)
					- (hour * 60 * 60) - (minute * 60);
		} else {
			clearInterval(timerInterval);
			if (minute == 0) {
				window.location.reload();
			}
		}
		if (minute <= 9)
			minute = '0' + minute;
		if (second <= 9)
			second = '0' + second;
		$('#day_show' + i).html(day);
		$('#hour_show' + i).html(hour);
		$('#minute_show' + i).html(minute);
		$('#second_show' + i).html(second);
		intDiff--;
	}, 1000);
}

/**
 * 评论编辑器初始化
 */
function initcourseAss() {// 编辑器初始化
	KindEditor.create('textarea[id="dyId"]', {
		resizeType : 0,
		filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
		pasteType : 0,// 设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
		allowPreviewEmoticons : false,
		syncType : 'auto',
		width : '99%',
		minWidth : '10px',
		minHeight : '10px',
		height : '150px',
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		uploadJson : keImageUploadUrl + '&param=suggest',// 图片上传路径
		allowFileManager : false,
		afterBlur : function() {
			this.sync();
		},
		items : [ 'forecolor', 'emoticons' ],
		afterChange : function() {

		}
	});
};

/**
 * 评论编辑器初始化
 */
function initKindEditor(id, height) {
	KindEditor.create('textarea[id="' + id + '"]', {
		resizeType : 0,
		filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
		pasteType : 1,// 设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
		allowPreviewEmoticons : false,
		syncType : 'auto',
		width : '99%',
		minWidth : '10px',
		minHeight : '10px',
		height : height,
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		uploadJson : keImageUploadUrl + '&param=note',// 图片上传路径
		allowFileManager : false,
		afterBlur : function() {
			this.sync();
		},
		items : [ 'forecolor', 'emoticons' ],
		afterChange : function() {

		}
	});
}

function checkWordNum(obj){
	var _this = $(obj);
	var wordNum = _this.val().length;
	var word = _this.val();
	var error = _this.next().children("tt");
	var htmlNum = _this.next().children("span").eq(1).find("tt");
	if(wordNum>225){
		htmlNum.html(0)
		error.html("字数长度不得大于225")
		error.parent().show();
	}else{
		htmlNum.html(225-wordNum);
		error.html("");
		error.parent().hide();
	}
	$(obj).val(word.substring(0,225));
}

//购买不同状态判断不同的购买方式
function buySellType(){
	if(!isLogin()){
		lrFun();
		return;
	}

	loseTimeTime = new Date(loseTimeTime.replace("-", "/").replace("-", "/"));
	//非直播课程直接购买
	if(loseType=='0'){
		var nowDAte = new Date();
		if(loseTimeTime>nowDAte){
			BuyNow(courseId);
		}else{
			dialogFun('提示',"对不起，课程已过期!",0);
		}
	}else{
		BuyNow(courseId);
	}
}
// 切换课程节点
function changeKpoint(id){
	$("#kpointId").val(id);
	$("#changeForm").submit();
}
// 预约直播
function reseve(id){
	if(!isLogin()){
		lrFun();
		return;
	}
	$.ajax({
		url : baselocation+"/course/reserve",
		data : {'courseId' : id},
		dataType : "json",
		type : "post",
		async:false,
		success : function(result){
			if(result.success){
				dialogFun("直播","预约成功！",1);
			} else if(result.message=='reserve') {
				dialogFun("直播","您的预约已通过审核",5,window.location.href);
			} else if(result.message=='notCheck'){
				dialogFun("直播","您已预约,请等待审核",1);
			} else if(result.message=='false'){
				dialogFun('直播',"对不起，直播预约人数已满!",0);
			} else if(result.message=='notLogin'){
				dialogFun("直播","请登录后操作",0);
			} else{
				dialogFun("直播","系统繁忙,请稍后重试",0);
			}
		}
	})
}