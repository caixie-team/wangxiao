/*测试以及下次再做页面用js*/

var maxtime =100;//最大时间每个页面要自己覆盖此值
var cookiesName="test_time";
var timer;
var timeFalg=true;
var testTime=0;	

//倒计时
function CountDown(){
	var date = new Date();
    date.setTime(date.getTime() + (10 * 1000)); 
    SetCookie(cookiesName,maxtime);
	if(maxtime>=0){  
		var minutes = Math.floor(maxtime/60);
        var seconds = Math.floor(maxtime%60);
        var msg = ""+minutes+"分"+seconds+"秒";
		document.all["timer"].innerHTML=msg;  
		if(maxtime == 5*60){
			$("#timer").attr("style","color:red");
		} 
		if(timeFalg){
			--maxtime;
			++testTime;
		}
		$("#testTime").val(testTime);
	}  
	else{  
		clearInterval(timer);
		DeleteCookie(cookiesName); 
		submit();//交卷
	}  
}  

//计时增加
function CountUp(){
	var date = new Date();
    date.setTime(date.getTime() + (10 * 1000));
    var minutes = Math.floor(testTime/60);
    var seconds = Math.floor(testTime%60);
    var msg = ""+minutes+"分"+seconds+"秒";
	document.all["timer"].innerHTML=msg;  
    if(timeFalg){
        ++testTime;
    }
	$("#testTime").val(testTime);
	  
} 

//时间暂停
function timePause(){
	timeFalg=false;
	dialog(6);
}

//时间开始
function timeStart(){
	timeFalg=true;
	$(".bg-shadow").remove();
	$("#dcWrap").remove();
	$(".dialog-shadow").remove();
}


//我要交卷
function formSubmit(){
	dialog(1);
}
//确定交卷
function submit(){
	clearInterval(timer);
	DeleteCookie(cookiesName); 
	addPaperRecord(0);
}
//继续做题
function jixuzuo(){
	$(".d-tips-2").remove();
	$(".dialog-shadow").remove();
	$(".bg-shadow").remove();
}

//点击下一部分
var titleheiddenNum = 0;
var titleheiddenNum_size=1;//最大数量,每个页面要覆盖此值
function nexttitleshow(){
	titleheiddenNum = titleheiddenNum+1;
	if(titleheiddenNum ==titleheiddenNum_size){
		titleheiddenNum = 0;
	}
	$(".titleHeiddenAndShow"+titleheiddenNum).click();
}

function titleHeiddenAndShow(id,obj){
	titleheiddenNum=parseInt($(obj).attr("indexNum"));
	var titleValue = $(obj).attr("titlevalue");
	$("#showTitleValue").html(titleValue);
	$(obj).parent().attr("class","current");
	$(obj).parent().siblings().each(function(){
		$(this).attr("class","");
	});
	$("#titleHidden"+id).show();
	$("#titleHidden"+id).siblings().hide();
	$("html,body").animate({scrollTop: $(".nextTitleAnchor").offset().top}, 0);
}

/**
 * 提交试卷
 * optype 0，提交试卷，1是下次再做
 */
 function addPaperRecord(optype){
 	var serialize = $("#addPaperRecord").serialize();
 	dialog(2);
 	$.ajax({
 		type:"POST",
 		dataType:"json",
 		async:false,
 		url:baselocation+"/paper/addPaperRecord.do",
 		data:serialize+'&optype='+optype,
 		success:function(result){
 			if(result.success){
 				if(timer!=null){
 					clearInterval(timer);
 				}
 				DeleteCookie(cookiesName);
 				if(optype==1){
 					window.location.href=baselocation+"/quest/toQuestionitemList";//去题库练习
 				}else{
 					window.location.href=baselocation+"/paper/getExamPaperReport/"+result.entity;
 				}
 			}else{
 				window.location.href=baselocation+"/quest/toQuestionitemList";//去题库练习
 			}
 		}
 	});
 }
 
//取消收藏试题
function notFavorite(id,obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/quest/notFavorite/"+id,
		data:{"favorite.qstId":id},
		async:false,
		success:function(result){
			if(result.success==true){
				$(obj).parent().html('<em class="icon18 collect-icon">&nbsp;</em><a href="javascript:void(0)" onclick="favorite('+id+',this)" qstId="'+id+'" title="收藏试题" class="vam c-666 ml5">收藏试题</a>');
			}
		}
});
}
//收藏试题
function favorite(id,obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/quest/toFavorite/"+id,
		data:{"favorite.qstId":id},
		async:false,
		success:function(result){
			if(result.success==true){
				$(obj).parent().html('<em class="icon18 collect-icon">&nbsp;</em><a href="javascript:void(0)" onclick="notFavorite('+id+',this)" title="取消收藏试题" qstId="'+id+'" class="vam c-666 ml5">取消收藏试题</a>');
			}
		}
	});
}
//答题卡点击
function datikaAnchor(titleheiddenNum,num){
	$(".titleHeiddenAndShow"+titleheiddenNum).click();
   /* alert($(".datikaQstAnchor"+num).offset().top);*/
	$("html,body").animate({scrollTop: $(".datikaQstAnchor"+num).offset().top-50}, 50);

}

//选择是否单题模式
function danti(obj){
	 if($(obj).prop("checked")){
		 showdanti();
	 }else{
		 showduoti();
	 } 
	 //默认到第一题
	 datikaAnchor(0,1);
}
//下一题
function nextqst(num){
	//选择答题卡  点击答题卡
	if($("#datikaCurrent"+num).children("a")[0]){
		$("#datikaCurrent"+num).children("a")[0].click();
	}else{
		$("#datikaCurrent1").children("a")[0].click();
	}
}
//展示单题模式
function showdanti(){
	$('input:radio').unbind("click");
	//头部部分的html隐藏
	$(".t-p-sub-title-wrap").hide();
	//底部下一部分隐藏
	$(".page-bar").hide();
	//全部试题隐藏
	$("article[class^='datikaQstAnchor']").hide();
	//第一题展示
	$(".datikaQstAnchor1").show();
	//单题模式下一题按钮展示
	$(".nextoneqst").show();
	datikaAnchor = function(titleheiddenNum,num){
		//隐藏材料题
		$(".cailiaohiddle").hide();
		$(".titleHeiddenAndShow"+titleheiddenNum).click();
		//全部试题隐藏
		$("article[class^='datikaQstAnchor']").hide();
		//如果有下一题则展示下一题没有则展示第一题
		if($(".datikaQstAnchor"+num)!=null){
			$(".datikaQstAnchor"+num).show();
			$(".datikaQstAnchor"+num).siblings(".cailiaohiddle").show();
		}else{
			$(".datikaQstAnchor1").show();
		}
	};
}
//展示多题模式
function showduoti(){
	var paperType=$("#paperRecordType").val();
	if(paperType==1){
		 $('input:radio').click(function(){
		        var numinder = $(this).parent().attr("numinder");
		        if(numinder!=null){
		            var numinder1 = numinder*1+1;
		            datikaAnchor(numinder,numinder1);
		        }
		    });
	}
	if(paperType==2){
		$('input:radio').click(function(){
	        var numinder = $(this).parent().attr("numinder");
	        if(numinder!=null){
	            var numinder1 = numinder*1+1;
	            $('#datikaCurrent'+numinder1+" a").click();
	        }
	    });
	}
	$(".t-p-sub-title-wrap").show();
	$(".page-bar").show();
	//单题模式下一题按钮隐藏
	$(".nextoneqst").hide();
	//全部试题隐藏
	$("article[class^='datikaQstAnchor']").show();
	//材料显示
	$(".cailiaohiddle").show();
	datikaAnchor = function(titleheiddenNum,num){
		//显示部分
		$(".titleHeiddenAndShow"+titleheiddenNum).click();
		//定位到题的位置
		$("html,body").animate({scrollTop: $(".datikaQstAnchor"+num).offset().top-50}, 50);
	};
}

//删除错题
function delErrorQuestion(id,obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/quest/delerrorQuestion/"+id,
		async:false,
		success:function(result){
			if(result.success==true){
				$(obj).parent().parent().parent().parent().remove();
			}
		}
	});
}

var qstIdNote="";//笔记内容
//添加笔记
function addNote(obj){
	dialog(7);
	qstIdNote = $(obj).attr("qstId");
	$("#notesubmit").click(function(){
		var noteContent = $("#noteContent").val();
		if(noteContent.length>200){
			alert("你最多能输入200个字");
			return false;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			url:baselocation+"/quest/insertNote.do",
			data:{"queryNoteQuestion.noteContent":noteContent,"queryNoteQuestion.qstId":qstIdNote},
			async:false,
			success:function(result){
				if(result.success==true){
					$(".dtClose").click();
					$(obj).attr("onclick","updateNote(this)");
					$(obj).prev().html(noteContent);
					if(noteContent==""){
						$(obj).attr("style","margin-left:0px");
					}else{
						$(obj).attr("style","margin-left:425px");
					}
				}
			}
		});
	});
}
//笔记重置
function chongzhi(){
	$("#noteContent").val("");
	$("#checkContent").val("");
	$("#notetips").html("您还可以输入"+200+"个字");
}
//更新笔记
function updateNote(obj){
	dialog(7);
	qstIdNote = $(obj).attr("qstId");
	 $("#noteContent").val($(obj).prev().html());
	$("#notesubmit").click(function(){
		var noteContent = $("#noteContent").val();
		if(noteContent.length>200){
			alert("你最多能输入200个字");
			return false;
		}
		$.ajax({
			type:"POST",
			dataType:"json",
			url:baselocation+"/quest/updateNote.do",
			data:{"queryNoteQuestion.noteContent":noteContent,"queryNoteQuestion.qstId":qstIdNote},
			async:false,
			success:function(result){
				if(result.success==true){
					$(".dtClose").click();
					$(obj).prev().html(noteContent);
					if(noteContent==""){
						$(obj).attr("style","margin-left:0px");
					}else{
						$(obj).attr("style","margin-left:425px");
					}
				}
			}
		});
	});
}

//笔记内容变化时提示
function changetnote(){
	var len=$("#noteContent").val().length;
	//alert(len);
	$("#notetips").html("您还可以输入"+(200-len)+"个字");
}
//解析时显示正确错题
function showErrorQuestion(){
	$(".rightQuestion").hide();
}
//解析时显示所有
function showAllQuestion(){
	$(".rightQuestion").show();
}
//解析时显示错题
function lookErrorQuestion(obj){
	if($(obj).prop("checked")){
		showErrorQuestion();
	}else{
		showAllQuestion();
	}
}
function changecheck(){
	var len=$("#checkContent").val().length;
	if(len==0){
		$("#notetips").html("请输入内容");
		return;
	}
	$("#notetips").html("您还可以输入"+(200-len)+"个字");
}
function checkAnswer(paperId,qstId,obj){
	dialog(8);
	$("#checksubmit").click(function(){
		var content=$("#checkContent").val();
		if(content==null||content.trim()==''){
			alert("内容不能为空");
			return false;
		}
		if(content.length>200){
			alert("你最多能输入200个字");
			return false;
		}else{
			$.ajax({
				url:baselocation+"/quest/addQuestErrorCheck",
				data:{"questErrorCheck.paperId":paperId,"questErrorCheck.questionId":qstId,"questErrorCheck.content":content},
				dataType:"json",
				type:"post",
				async:false,
				success:function(result){
					if(result.message=="success"){
						$(".dtClose").click();
						alert("成功提交，等待处理");
					}else{
						alert("失败，请稍后重试");
					}
				}
			});
			
		}	
	});
}

