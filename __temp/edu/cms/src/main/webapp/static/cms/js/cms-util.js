//处理资讯类型
function setCmsType(typeList,typeId){
	var content='';
	if(typeId <= 0){//如果类型ID是0，则只显示出来根级别的资讯类型
		content+='<dl class="A-sort clearfix"><dt><strong>资讯分类：</strong></dt><dd>';
		//如果类型是0，则设置“全部”的样式被选中
		content+='<a id="cmsType0" href="javascript:void(0)" title="全部" onclick="queryArticle(0)">全部</a>';
		for(var i=0;i<typeList.length;i++){
			if(typeList[i].parentId ==-1){
				content+='<a id="cmsType'+typeList[i].typeId+'" href="javascript:void(0)" title="'+typeList[i].typeName+'" onclick="queryArticle('+typeList[i].typeId+')">'+typeList[i].typeName+'</a>';
			}
		}
		content+='</dd></dl>';
		$("#cms-type-mx").html(content);
	}else{
		var thisType;
		//得到当被搜索的类型对象，得到后退出循环
		for(var i=0;i<typeList.length;i++){
			if(typeId==typeList[i].typeId){
				thisType=typeList[i];
				break;
			}
		}
		if(thisType!=null){
			//递归得到父类
			recursiveCmsType(typeList,thisType.parentId);
			//设置多级分类
			setMultilevelType(typeList,typeArr,thisType);
		}else{//如果当前类型不存在 
			setCmsType(typeList,0);
		}
	}
}
//递归后得到的父级类型数组
var typeArr = new Array();
//递归资讯类型，得到当前被搜索的资讯类型的所有的父级类型
function recursiveCmsType(typeList,parentId){
	for(var i=0;i<typeList.length;i++){
		if(parentId==typeList[i].typeId){
			typeArr.push(typeList[i]);
			recursiveCmsType(typeList,typeList[i].parentId);
		}
	}
}
//设置多级类型
function setMultilevelType(typeList,parentArr,thisType){
	$("#cms-type-mx").html('');
	var content='';
	if(parentArr.length>0){//如果有父级类型
		parentArr.splice(0,0,thisType);//把当前被搜索的类型插入到数组的第一位
		setAllParentType(typeList,parentArr);
	}else{//如果没有父级类型
		setCmsType(typeList,0);//获取第一级类型
	}
	
	var index=0;
	//得到当前被搜索类型的子级分类
	content='<dl class="A-sort clearfix"><dt><strong>子级分类：</strong></dt><dd>';
	for(var i=0;i<typeList.length;i++){
		if(typeList[i].parentId==thisType.typeId){
			index++;
			content+='<a href="javascript:void(0)" title="'+typeList[i].typeName+'" onclick="queryArticle('+typeList[i].typeId+')">'+typeList[i].typeName+'</a>';
		}
	}
	content+='</dd></dl>';
	if(index>0){//如果有子级类型就追加
		$("#cms-type-mx").append(content);
	}
}
//拼出所有父级类型
function setAllParentType(list,arr){
	var content='';
	for(var i=arr.length-1;i>=0;i--){
		if(i==(arr.length-1)){
			content+='<dl class="A-sort clearfix"><dt><strong>资讯分类：</strong></dt><dd>';
			content+='<a id="cmsType0" href="javascript:void(0)" title="全部" onclick="queryArticle(0)">全部</a>';
		}else{
			content+='<dl class="A-sort clearfix"><dt><strong>子级分类：</strong></dt><dd>';
		}
		for(var j=0;j<list.length;j++){
			if(arr[i].parentId==list[j].parentId){
				if(arr[i].typeId==list[j].typeId){
					content+='<a class="current" id="cmsType'+list[j].typeId+'" href="javascript:void(0)" title="'+list[j].typeName+'" onclick="queryArticle('+list[j].typeId+')">'+list[j].typeName+'</a>';
				}else{
					content+='<a id="cmsType'+list[j].typeId+'" href="javascript:void(0)" title="'+list[j].typeName+'" onclick="queryArticle('+list[j].typeId+')">'+list[j].typeName+'</a>';
				}
			}
		}
		content+='</dd></dl>';
	}
	$("#cms-type-mx").html(content);
}