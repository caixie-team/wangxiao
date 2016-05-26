$(function() {
	loadsubject();
});

//ztree start
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId",
				rootPId: 0
			}
		},
		callback: {
			onClick: onClick,
			onAsyncSuccess: zTreeOnAsyncSuccess//异步加载完的回调
		},
		view: {
			addDiyDom: addDiyDom,
			dblClickExpand: false,
			showLine: false
		},
		async: {
			enable: true,
			type: "post",
			url:baselocation+"/point/queryPointList",
			otherParam: [],
			//autoParam:["id=id","Pid=Pid","name=name"],
			dataFilter: ajaxDataFilter
		}
	};
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		heightAdjust();//调整谈框高度
		$(".loading").remove();
		$.fn.zTree.getZTreeObj("treeDemo").expandAll(true);//展开全部节点
		var ZTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
		if(ZTreeObj.getNodes()==""){
			$("#treeDemo").html("暂无数据，请等待上传");
		}
	};
	
	function onClick(e,treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandNode(treeNode);
	}
	var IDMark_Span = "_span",IDMark_A = "_a";
	function addDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId + IDMark_A);
			if ($("#diyBtn_"+treeNode.id).length>0) return;
			var editStr = "<span id='diyBtn_space_" +treeNode.id+ "' class='ztree-right-btn'><label><input type='button' treeId="+treeNode.id+" value='随机练习' onclick='randomPointIds(this,30)'/></label></span>";
			aObj.append(editStr);
		}
	
	//ztree end
	var pointIds="";//随机组题的考点 全局变量
	function randomPointIds(obj,num){
		pointIds="";
		var dteeId = $(obj).attr("treeid");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node = treeObj.getNodeByParam("id", dteeId, null);
		getPointIds(node);
		$("#randompointIds").val(pointIds);
		$("#randomnum").val(num);
		$("#randomtest").submit();
	}
	
	function getPointIds(treeNode){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeNode = treeObj.getNodeByParam("id", treeNode.id, null);
		var childrenNode = treeNode.children;
		if(childrenNode==undefined){
			pointIds+=treeNode.id+",";
		}
		$.each($(childrenNode), function () {
			getPointIds(this);
		});
		} 
	function ajaxDataFilter(treeId, parentNode, responseData) {
		responseData = responseData.entity;
	   if (responseData) {
	      for(var i =0; i < responseData.length; i++) {
	    	  responseData[i].name += " (共"+responseData[i].qstCount+"道题)";
	      }
	    } 
	    return responseData;
	};

	function heightAdjust(){//谈框高度调整
		var dialogEle = $(".dialog-shadow");
		var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
		dH = dialogEle.height(),
		dW = dialogEle.width();
	dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
	}
	function zhuanxiang(){
		dialog(3);
		$.fn.zTree.init($("#treeDemo"), setting);
		heightAdjust();
	}
	
	var examtype;
	var examtitle;
	function zujuanmokao(type,title){
		dialog(4);
		examtype=type;
		examtitle=title;
		queryPaperListByType(examtype,1,examtitle);
		heightAdjust();
	}
	function queryAjaxPage(pageNum){
		queryPaperListByType(examtype,pageNum,examtitle);
	}
	function queryPaperListByType(Type,currentPage,title){
		$.ajax({
			type:"POST",
			dataType:"text",
			url:baselocation+"/paper/ajax/queryPaperListByType",
			data:{"queryPaper.type":Type,"page.currentPage":currentPage},
			async:false,
			success:function(result){
				$(".paperTypeName").html(title+"-试卷列表");
				$(".pageAfter").html(result);
			}
	});
	}
	function addPage(page,Type){
		str="";
		var currentPage = page.currentPage-1<1?1:page.currentPage;
    	var totalPage = page.totalPageSize;
		if(currentPage==1){
			str=str+'<li class="disabled"><a href="###">← 上一页</a></li>';
		}else{
			var num = page.currentPage-1;
			str=str+'<li><a href="javascript:queryPaperListByType('+Type+','+num+')">← 上一页</a></li>';
		}
    	var maxNum_new = currentPage>4?4:6-currentPage;//最大显示页码数
    	var discnt=1;
    	for(var i=3; i>0; i--) {
    		if(currentPage>i) {
    			str =str+ "<li><a href='javascript:queryPaperListByType("+Type+","+(currentPage-i)+")'>"+ (currentPage-i) +"</a></li>";
    			discnt++;
    		};
    	}
    	str = str + '<li class="active"><a href="javascript:void(0)">'+currentPage+'</a></li>';
    	for(var i=1; i<maxNum_new; i++) {
    		if(currentPage+i<=totalPage && discnt<5) {
    			str = str + "<li><a href='javascript:queryPaperListByType("+Type+","+(currentPage+i)+")'>"+ (currentPage+i) +"</a></li>";
    			discnt++;
    		} else {
    			break;
    		};
    	}
    	if(totalPage==currentPage){
    		str=str+'<li class="disabled"><a href="###">下一页 →</a></li>';
    	}else{
    		str=str+'<li><a href="javascript:queryPaperListByType('+Type+','+(page.currentPage+1)+')">下一页 →</a></li>';
    	}
		$("#pageFlag").html(str);
	}
	function kuaisuzhineng(num){
		
		dialog(4);
		$(".paperTypeName").html("错题练习");
		
		var str="";
		str+='<ul class="moka-list paperList">';
		str +='<li><div class="c-666 fsize16">顺序练习</div><p class="c-999 mt5"><span class="mr10"></span><span>根据错题先后顺序的进行练习（从最近的错题开始练习）</span></p><span class="moka-btn"><a href="javascript:void(0)" onclick="errorLianxi(1)" title="">考试模式</a>'
			+'&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="errorpractice(1)" title="">练习模式</a></span></li>';
		str +='<li><div class="c-666 fsize16">随机练习</div><p class="c-999 mt5"><span class="mr10"></span><span>根据自己所做错题随机抽取进行练习</span></p><span class="moka-btn"><a href="javascript:void(0)" onclick="errorLianxi(2)" title="">考试模式</a>'
			+'&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="errorpractice(2)" title="">练习模式</a></span></li>';
		str+='</ul>';
		$(".pageAfter").html(str);
		heightAdjust();
	}
	//falg 1为顺序练习2随机练习
	function errorLianxi(falg){
		window.location.href=baselocation+"/paper/getRandomQuestion?falg="+falg+"&prac=0";
	}
	function errorpractice(falg){
		window.location.href=baselocation+"/paper/getRandomQuestion?falg="+falg+"&prac=1";
	}
