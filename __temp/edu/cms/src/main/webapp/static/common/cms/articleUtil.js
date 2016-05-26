var allTypeList = null;//所有的资讯分类List
var linkArr = new Array();//修改功能能中，被修改文章的分类的所有的父级链

/**
 * 初始化添加页面的类型
 * @param id 初始下拉框的ID
 * @param parentId 文章类型系统默认父ID
 * @param typeList 文章的所有类型List
 */
function initArticleType(id,parentId,typeList,infoTypeId,infoTypeLinkId){
	if(typeList!=null && typeList!=''){
		allTypeList = eval('(' + typeList + ')'); 
		var content=$("#"+id).html();
		for(var i=0;i<allTypeList.length;i++){
			if(parentId==allTypeList[i].parentId){
				content+='<option value="'+allTypeList[i].typeId+'">'+allTypeList[i].typeName+'</option>';
			}
		}
		$("#"+id).html(content);
		$("#"+id).attr('onchange','changesTypeQueryList(this,"'+infoTypeId+'","'+infoTypeLinkId+'")');
	}
}


/**
 * 初始化修改页面文章分类
 * @param id 当前有的下拉框ID
 * @param parentId 系统默认的根ID
 * @param typeList 所有文章分类List
 * @param thisVal 文章原有的分类ID
 */
function initUpdatePageType(id,parentId,typeList,thisVal,infoTypeId,infoTypeLinkId){
	if(typeList!=null && typeList!=''){
		allTypeList =  eval('(' + typeList + ')'); 
		var childList ='<select onchange="changesTypeQueryList(this,\''+infoTypeId+'\',\''+infoTypeLinkId+'\')"><option value="0">-选择分类-</option>';
		var thisEm=null;
		var index=0;
		//得到当前被修改文章分类的子级分类下拉框
		for(var i=0;i<allTypeList.length;i++){
			if(allTypeList[i].typeId==thisVal){
				thisEm=allTypeList[i];
			}
			if(allTypeList[i].parentId==thisVal){
				index++;
				childList+='<option value="'+allTypeList[i].typeId+'">'+allTypeList[i].typeName+'</option>';
			}
		}
		childList+='</select>';
		//如果当前子级分类没有下拉框，则设置为“”
		if(index<=0)childList='';
		index=0;
		
		//获取当前文章分类的同级分类下拉框
		var sameList='<select onchange="changesTypeQueryList(this,\''+infoTypeId+'\',\''+infoTypeLinkId+'\')"><option value="0">-选择分类-</option>';
		if(thisEm!=null){
			for(var i=0;i<allTypeList.length;i++){
				if(allTypeList[i].parentId==thisEm.parentId){
					if(allTypeList[i].typeId==thisEm.typeId){
						sameList+='<option selected value="'+allTypeList[i].typeId+'">'+allTypeList[i].typeName+'</option>';
					}else{
						sameList+='<option value="'+allTypeList[i].typeId+'">'+allTypeList[i].typeName+'</option>';
					}
					index++;
				}
			}
		}
		sameList+='</select>';
		if(index<=0)sameList='';
		
		//递归当前被修改文章分类，得到当前文章分类的所有的父级连 
		relationship(allTypeList,thisEm);
		var linkContent='';
		if(linkArr!=null && linkArr.length>0){
			for(var i=linkArr.length-1;i>=0;i--){
				linkContent+='<select onchange="changesTypeQueryList(this,\''+infoTypeId+'\',\''+infoTypeLinkId+'\')"><option value="0">-选择分类-</option>';
				for(var j=0;j<allTypeList.length;j++){
					if(allTypeList[j].parentId==linkArr[i].parentId){
						if(linkArr[i].typeId==allTypeList[j].typeId){
							linkContent+='<option selected value="'+allTypeList[j].typeId+'">'+allTypeList[j].typeName+'</option>';
						}else{
							linkContent+='<option value="'+allTypeList[j].typeId+'">'+allTypeList[j].typeName+'</option>';
						}
					}
				}
				linkContent+='</select>';
			}
		}
		
		var content = linkContent+sameList+childList;
		//如果前文章所属的分类不存在的话，就显示最上级分类（父ID为-1的）
		if(linkContent=='' && sameList=='' && childList==''){
			content='<select onchange="changesTypeQueryList(this,\''+infoTypeId+'\',\''+infoTypeLinkId+'\')"><option value="0">-选择分类-</option>';
			for(var j=0;j<allTypeList.length;j++){
				if(allTypeList[j].parentId == -1){
					content+='<option value="'+allTypeList[j].typeId+'">'+allTypeList[j].typeName+'</option>';
				}
			}
			content+='</select>';
		}
		$("#"+id).parent().html(content);
	}
}

/**
 * 递归获取修改页面文件原有分类的所有的父级分类连
 * @param typeList 系统所有的分类List
 * @param thisEm 当前的分类
 */
function relationship(typeList , thisEm){
	if(typeList!=null && typeList.length>0 && thisEm!=null){
		for(var i=0;i<typeList.length;i++){
			
			if(thisEm.parentId==typeList[i].typeId && typeList[i].typeId !=-1){
				linkArr.push(typeList[i]);
				relationship(typeList,typeList[i]);
			}
		}
	}
}

/**
 * 当下拉框的改变时，获取被选中选项的子级类型，如果选中的类型的值是0的话就把后面所有的下拉框删除
 * @param thisEm 传入当前下拉框
 */
function changesTypeQueryList(thisEm,infoTypeId,infoTypeLinkId){
	var obj = $(thisEm);
	//清空原有select
	$(thisEm).nextAll().remove('select');
	if(obj.val()>0 && allTypeList!=null){
		var content='<select onchange="changesTypeQueryList(this,\''+infoTypeId+'\',\''+infoTypeLinkId+'\')"><option value="0">-选择分类-</option>';
		var index=0;
		//循环获取子级分类 
		for(var i=0;i<allTypeList.length;i++){
			if(allTypeList[i].parentId==obj.val()){
				index++;
				content+='<option value="'+allTypeList[i].typeId+'">'+allTypeList[i].typeName+'</option>';
			}
		}
		content+='</select>';
		//如果有子级分类则追加
		if(index>0){
			var parentEm = obj.parent();
			$(parentEm).append(content);
		}
	}
	//获取所有下拉框的值
	getAllTypeSelectVal(thisEm,infoTypeId,infoTypeLinkId);
}

/**
 * 当其中一个下拉框改变的时候，会重新获取所有下拉框的值，并把值放入一人Array中
 * @param em 传当前在改变的下拉对象
 * @returns 返回Array当前选中的分类数组
 */
function getAllTypeSelectVal(em,infoTypeId,infoTypeLinkId){
	var thisEm = $(em);
	//得到当前被改变的下拉框之前的所有select元素
	var prevArr = thisEm.prevAll('select');
	var value ='';
	//如果当前select前面有select元素则获取这些元素的value
	if(prevArr.length>0){
		for(var i=prevArr.length-1;i>=0;i--){
			if(prevArr[i].value>0){
				value+=prevArr[i].value+',';
			}
		}
	}
	if(thisEm.val()>0){
		value+=thisEm.val()+',';
		//如果当前被选中的下拉选项的值大于0
		$("#"+infoTypeId).val(thisEm.val());
	}else{
		if(prevArr.length>0){
			//获取第一个元素
			$("#"+infoTypeId).val(prevArr[0].value);
		}else{
			$("#"+infoTypeId).val(0);
		}
	}
	if(value!=''){
		$("#"+infoTypeLinkId).val(','+value);
	}else{
		$("#"+infoTypeLinkId).val('');
	}
}