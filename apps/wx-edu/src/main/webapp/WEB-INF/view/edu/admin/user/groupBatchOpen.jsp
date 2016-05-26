<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量导入员工</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
	function importExcel() {
        var groups ='';
        $("input[name='groupId']:checked").each(function () {
            groups +=this.value +",";
        });
		groups = groups.substring(0,groups.length-1);
        $("#groupIds").val(groups);

		var myFile = $("#doc-form-file").val();
		if (myFile.length <= 0) {
			dialogFun("岗位管理","请选择导入内容",0);
			return false;
		}
		$("#importP").submit();
	}
	
	
	//ztree start
	var setting = {
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'groupId',
				pIdKey:'parentGroupId',
				rootPid:''
			},
			key:{
				name:'groupName',
				title:'groupName'
			}
		},
		callback: {
			onClick: treeOnclick
		}
	};
 	var treedata=${groupList};

	function treeOnclick(e,treeId, treeNode) {
		$("#userGroupId").val(treeNode.groupId);
		$("#userGroupName").val(treeNode.groupName);
		$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
	}

	//取消全部选中
	function checkNodeFalse() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);
	}

	$(function(){
		$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);

		$('#doc-form-file').on('change', function() {
			var fileNames = '';
			$.each(this.files, function() {
				fileNames += '<span class="am-badge">' + this.name + '</span> ';
			});
			$('#file-list').html(fileNames);
		});
	})
</script>
</head>
<body>

	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">岗位管理</strong> / <small>批量导入</small></div>
	</div>
	<hr>

	<div class="mt20">
		<div  class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">批量导入数据</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
					<form action="${ctx}/admin/user/importExcel" class="am-form" id="importP" method="post" enctype="multipart/form-data">
						<input type="hidden" name="groupIds" id="groupIds">
						<input type="hidden" name="sysGroupId" id="userGroupId"/>
						<input type="hidden" name="level" value="1" />
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-4 am-text-right am-text-he">
								信息描述
							</label>
							<div class="am-u-sm-8 am-u-md-8">
								excel模版说明：<br>
								第一列：用户的手机号码,必须是未注册过的<br>
								第二列：用户的邮箱,必须是未注册过的<br>
								第三列：用户的名称,必须是未注册过的<br>
								第四列：密码 如不填写默认111111,不得填入非法字符例如“. * #”<br>
								<br> （<a href="${ctx }/static/common/import_student.xls" style="color: red;">点击下载模版</a>）<br>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-4 am-text-right am-text-he">
								所属部门
							</label>
							<div class="am-u-sm-8 am-u-md-8">
								<div id="doc-dropdown-justify-js" style="width: 200px;">
									<div class="am-dropdown" id="doc-dropdown-js">
										<input type="text" class="am-input-sm am-dropdown-toggle" id="userGroupName" value="" readonly="readonly"/>
										<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
											<div id="ztreedemo" class="ztree"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-4 am-text-right am-text-he">
								所属岗位
							</label>
							<div class="am-u-sm-8 am-u-md-8">
								<c:forEach var="group" items="${userGroupList}" varStatus="count">
								<div class="am-u-sm-6 am-u-md-6 am-u-end">
									<label class="am-checkbox-inline">
										<input type="checkbox" name="groupId" value="${group.id}" data-am-ucheck> ${group.name}
									</label>
								</div>
								</c:forEach>
							</div>
						</div>

						<div class="am-g am-margin-top">
							<label class="am-u-sm-4 am-u-md-4 am-text-right am-text-he">
								上传
							</label>
							<div class="am-u-sm-8 am-u-md-8">
								<section>
									<div class="am-form-group am-form-file">
										<button type="button" class="am-btn am-btn-danger am-btn-sm">
											<i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
										<input id="doc-form-file" type="file" name="myFile" multiple>
									</div>
									<div id="file-list"></div>
								</section>
								<button type="button" class="am-btn am-btn-danger" onclick="importExcel()">提交</button>
								<button href="javascript:history.go(-1);" title="返回" class="am-btn am-btn-danger">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>