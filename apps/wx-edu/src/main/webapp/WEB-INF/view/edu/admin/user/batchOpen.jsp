<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>批量开通账户</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
	<script type="text/javascript">
		$(function(){
			$('#importP').validator({
				validate: function(validity) {
					validity.valid = false;
					// 部门
					if($(validity.field).is('#userGroupName')){
						var _this = $(validity.field);
						var _error = _this.parent().parent().parent().next()
						if(isNotEmpty(_this.val())) {
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("请选择部门");
						}
						return validity;
					}
					// 级别
					else if($(validity.field).is('input[name="level"]')){
						validity.valid = true;
						return validity;
					}
					//
					else if($(validity.field).is('#myFile')){
						var _this = $(validity.field);
						var _error = _this.parent().parent().next()
						if(isNotEmpty(_this.val())){
							_error.html("");
							validity.valid = true;
						}else{
							_error.html("请选择要上传的文件");
						}
						return validity;
					}
				},
				submit: function() {
					var formValidity = this.isFormValid();
					$.when(formValidity).then(function() {
						importExcel();
					}, function() {});
					return false;
				}
			});
		});


		/* level:0员工，1学员 */
		function levelTypeChange(){
			var level=$("input[name='level']:checked").val();
			if(level==0){
				$("#groups").hide();
			}else if(level==1){
				$("#groups").show();
			}
		}

		function importExcel() {
			var groups ='';
			$("input[name='groupId']:checked").each(function () {
				groups +=this.value +",";
			});

			groups=groups.substring(0,groups.length-1);
			$("#groupIds").val(groups);

			var myFile = $("#myFile").val();
			if(myFile==null||myFile.length <= 0){
				dialogFun("批量导入","请选择导入内容",0);
				return;
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
			$("#sysGroupId").val(treeNode.groupId);
			$("#userGroupName").val(treeNode.groupName);
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
		}
		$().ready(function() {
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
			$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		});
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">开通学员账户</strong> / <small>批量开通账户</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息(<i class="am-text-danger">*</i>为必填项)</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form class="am-form" id="importP" method="post" action="${ctx}/admin/user/importExcel" enctype="multipart/form-data">
					<input type="hidden" name="groupIds" id="groupIds">
					<input type="hidden" name="sysGroupId" id="sysGroupId"/>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							信息描述
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							excel模版说明：<br>
							第一列：用户的手机号码,必须是未注册过的)<br>
							第二列：用户的邮箱,必须是未注册过的<font color="red">*</font><br>
							第三列：用户的名称,必须是未注册过的<br>
							第四列：密码 如不填写默认111111,不得填入非法字符例如“. * #”<br>
							<br> （<a href="${ctx }/static/common/import_student.xls" style="color: red;">点击下载模版</a>）<br>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							所属部门
						</div>
						<div class="am-u-sm-8 am-u-md-6 am-form-horizontal">
							<div id="doc-dropdown-justify-js" style="width: 200px">
								<div class="am-dropdown" id="doc-dropdown-js">
									<input type="text" class="am-input-sm am-dropdown-toggle" id="userGroupName" value="" readonly="readonly"/>
									<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
										<div id="ztreedemo" class="ztree"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							级别
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<label class="am-radio-inline">
								<input type="radio" name="level" checked value="0" data-am-ucheck onchange="levelTypeChange()"/>
								学员
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="level" value="1" data-am-ucheck onchange="levelTypeChange()"/>
								员工
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top undis" id="groups">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							所属岗位
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<c:forEach var="group" items="${userGroupList}" varStatus="count">
								<div class="am-u-sm-6 am-u-md-6 am-u-end">
									<label class="am-checkbox">
										<input type="checkbox" name="groupId" value="${group.id}" data-am-ucheck> ${group.name}
									</label>
								</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">上传</div>
						<div class="am-u-sm-8 am-u-md-6">
							<div class="am-form-file">
								<button class="am-btn am-btn-danger am-btn-sm" type="button">
									<i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
								<input type="file" value="" name="myFile" multiple id="myFile">
							</div>
							<div id="file-list"></div>
							<script>
							  $(function() {
							    $('#myFile').on('change', function() {
							      var fileNames = '';
							      $.each(this.files, function() {
							        fileNames += '<span class="am-badge">' + this.name + '</span> ';
							      });
							      $('#file-list').html(fileNames);
							    });
							  });
							</script>
						</div>
						<div class="am-u-sm-12 am-u-md-4 am-text-danger"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>