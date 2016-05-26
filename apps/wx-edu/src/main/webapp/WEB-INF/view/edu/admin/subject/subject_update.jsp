<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>更新项目</title>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/common/ztree.js"></script>

	<script type="text/javascript">
		//ztree start
		$(function(){
			//专业id
			var treeIdKey = 'subjectId';
			//专业父级id
			var treePIdKey = 'parentId';
			//专业名称
			var treeKeyName='subjectName';
			//数据对象
			var treeDate = ${subjectList};

			//setting树结构设置
			var setting=  initTreeSetting(treeIdKey,treePIdKey,treeKeyName);
			//ztree树结构初始化
			$.fn.zTree.init($("#pztree"), setting, treeDate);
			validateForm("updateSubjectForm");
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});


			// 开关监听
			$('#showIndex').on('switchChange.bootstrapSwitch', function(event, state) {
				if(state){
					$('#showIndexVal').val(1);
				}else{
					$('#showIndexVal').val(0);
				}
			});
		});
		//回到方法
		function treeOnclick(e,treeId, treeNode) {
			$("#subjectpid").val(treeNode.subjectId);
			$("#subjectpname").val(treeNode.subjectName);
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
		}
		//根目录
		function rootTree(){
			$("#subjectpname").val("");
			$("#subjectpid").val(0);
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
		}

	</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">项目管理</strong> / <small>更新项目 </small></div>
	</div>
	<hr/>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">更新项目</a></li>
			</ul>
			<div class="am-tabs-bd am-scrollable-vertical">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in ">
					<form id="updateSubjectForm" class="am-form" action="${ctx}/admin/subj/updateSubject" method="post">
						<fieldset>
							<input type="hidden" name="subject.image" id="imagesUrl" />
							<input type="hidden" name="subject.parentId" id="subjectpid" value="${thissubject.parentId}" />
							<input type="hidden" value="${thissubject.subjectId }" name="subject.subjectId" />
							<input type="hidden" name="subject.level" id="subjectlevel" value="${thissubject.level}" />
							<input type="hidden" name="subject.status" value="0" />
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">上级</div>
								<div class="am-u-sm-8 am-u-md-6">
									<div id="doc-dropdown-justify-js" style="width: 400px">
										<div class="am-dropdown" id="doc-dropdown-js">
											<input type="text" placeholder="根目录" class="am-input-sm am-dropdown-toggle" value="${parentSubject.subjectName}" id="subjectpname" readonly="readonly"/>
											<div class="am-dropdown-content">
												<div class="menu-fir"><a href="javascript:rootTree()" > 根目录</a></div>
												<div id="pztree" class="ztree" ></div>
											</div>
										</div>
									</div>
								</div>
								<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">项目名称</div>
								<div class="am-u-sm-8 am-u-md-4">
									<input class="am-input-sm" required data-foolish-msg="项目名称不能为空" maxlength="20" value="${thissubject.subjectName}" name="subject.subjectName" id="subject.subjectName" type="text" />
								</div>
								<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">排序</div>
								<div class="am-u-sm-8 am-u-md-4">
									<input type="number" class="am-input-sm" data-foolish-msg="请输入有效的数字" required  name="subject.sort" value="${thissubject.sort}" />
								</div>
								<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填(系数越高,排名越优先)</div>
							</div>
							<div class="am-g am-margin-top am-form-group">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">首页显示</div>
								<div class="am-u-sm-8 am-u-md-4">
									<input type="hidden" value="${thissubject.showIndex}" name="subject.showIndex" id="showIndexVal" />
									<input id="showIndex" type="checkbox" <c:if test="${thissubject.showIndex==1}">checked</c:if> data-size="sm" data-am-switch data-off-color="danger">
								</div>
								<div class="am-hide-sm-only am-u-md-4"></div>
							</div>
							<div class="am-g am-margin-top">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
								<div class="am-u-sm-8 am-u-md-4">
									<button class="am-btn am-btn-secondary" type="submit">提交</button>
									<button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返回</button>
								</div>
								<div class="am-u-sm-12 am-u-md-6"></div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
