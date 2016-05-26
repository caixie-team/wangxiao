	
	$(function(){
		//日历控件初始化
	    $( "#blogdate,#weibodate,#sugbodate").datepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd"
	    });
		//tab切换
	    $("#tabs li").bind("click", function () {
            var index = $(this).index();
            $(this).parent().children("li").attr("class", "");//将所有选项置为未选中
            $(this).attr("class", "current"); //设置当前选中项为选中样式
            var tab = $(this).attr("tab");
            if(tab!=null){
            	$("#tab").val(tab);
            }
            displaySearchByTab();
            search();
        });
	    
	   //显示隐高级搜索框
	    $("#asBtn,.advancedSearch .cClose").click(function() {
	 		if(($("#advancedSearch").is(":visible"))){
	 			$(".advancedSearch").slideUp(300);
	 			$("#asBtn").html("高级搜索");
	 			$("#falgshow").val("hide");
	 			$(".search-global-inp").show();
	 			$(".search-global-btn").show();
	 		}else{
	 			$(".search-global-inp").hide();
	 			$(".search-global-btn").hide();
	 			$("#asBtn").html("收起高级");
	 			//根据tab显示不同的高级搜索项目
	 			displaySearchByTab();
	 			$(".advancedSearch").slideDown(300);
	 			$("#falgshow").val("show");
	 		}
	 	});
	});
	//根据tab显示切换高级的条件
	function displaySearchByTab(){
		var tab=$("#tab").val();
		$("[id^=search_]").each(function(index,element){
			$("#"+element.id).hide();
		});
		$("[id^=search_"+tab+"]").each(function(index,element){
			$("#"+element.id).show();
		});
	}
	//搜索按钮提交
	function search(){
		var keyword=$("#keyword").val();
		var tab = $("#searchDaotab").val();
		if(tab=='blog'||tab=='weibo'||tab=='sug'||tab=='dis'){//只有同学问答，微博，博客，小组判断关键字为空
			if(keyword==null || keyword==""){
				dialog_sns("请输入要搜索的关键字",5);
				return;
			}
		}
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	//清空检索条件
	function cleansearch(){
		$("#blogshowname,#blogdate,#weiboshowname,#weibodate,#disshowname,#sugshowname,#sugbodate,#videosn,#pkdate," +
				"#pktimedate,#pkusername,#pkusername2,#fintitle,#finauthor,#chaSn,#chaJudgementchairnames,#chapktimeend,#findate," +
				"#chaLevel,#chaAudience,#chaAcademy,#chaSpecialty,#chaContent,#chaCompany,#chaTopic,"+
				"#videotitle,#vipMember,#specialist,#specialty,#colleges,#pklevel1,#company,#pkuseruid,#videodate,"+
				"#videodate1,#findate,#findate1,#stusn,#stuusername,#studate,#studate1,#level,#specialty,#certificate,#cusMemberSn" +
				",#cusStudentsn,#cusJudgementsn,#cusTrade,#videoType,#vdesc,#sugtitle,#blogtitle").val("");
		$("#disclasstiy,#sugType,#sugRecType,#videoType,#cusType,#cusCertificate,#cusSpecialty,#stuspecialty").val(0);
		$("#cusLevel,#pklevel,#videoType").val(-1);
	}
	