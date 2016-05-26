
function initTreeSetting(treeIdKey,treePIdKey,treeKeyName){
	 var setting = {
			view:{
				showLine: true,
				showIcon: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey:treeIdKey,
					pIdKey:treePIdKey,
					rootPid:'1'
				},
				key:{
					name:treeKeyName,
					title:treeKeyName
				}
			},
			callback: {
				onClick: treeOnclick
			}
		};
	return setting;
}





