
	function uploadifyUpload(){
		 if(fileuploadIndex==0)
		 {
			 dialogFun("提示","请选择图片 ",0);
			 return;
		 }	
		$('#fileupload').uploadifyUpload();
	
	}
	function addImage(){
		window.open('/admin/weixin/imagepage?page.currentPage=1',
				+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
			);
	}
	//添加image
	function getImageList(newUcIdArr){
		var imageIds = $("#imageIdHidden").val().split(",");
		imageIds = imageIds.concat(newUcIdArr);  
		imageIds = imageIds.unique();
		$("#imageIdHidden").val(imageIds);
		queryImage();
	}

	//查找image
	function queryImage(){
		var ids = $("#imageIdHidden").val();
		if(ids!=null&&ids!=""){
			$.ajax({
				type : "POST",
				dataType : "json",
				url : baselocation+"/admin/weixin/getMany",
				data : {
					"ids" : ids
				},
				async : false,
				success : function(result) {
					if (result.success == true) {
						var str = "";
							str+='<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
						var newList = result.entity;
						for(var i=0; i < newList.length;i++){
							var uc = newList[i];
							str+='<button class="am-btn am-btn-default ml10 mt10" onclick="delImages('+uc.id+')" type="button">'+uc.title+'<a onclick="delImages('+uc.id+')" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
						}
						str+='</div></div>';
						$("#uchtml").html(str);
					} 
				}
			});
		}else{
			$("#uchtml").html("");
		}
	}

	//删除
	function delImages(id){
		var imageIdHidden = $("#imageIdHidden").val();
		var pattern = id+"";
		imageIdHidden = imageIdHidden.replace(new RegExp(pattern), "");
		imageIdHidden = imageIdHidden.split(",").unique();
		$("#imageIdHidden").val(imageIdHidden);
		queryImage();
	}
	//清除全部image
	function clearImage(){
		$("#imageIdHidden").val("");
		$("#uchtml").html("");
	}
