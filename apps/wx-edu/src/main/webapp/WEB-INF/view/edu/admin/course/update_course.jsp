<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>修改课程</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
	<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
	<script type="text/javascript"src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}"/>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
	<script src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>

	<script src="${ctximg}/static/edu/js/admin/course/course.js?v=${v}"></script>
	<script type="text/javascript">
		var context;
		$(function(){
			context = UE.getEditor('context', {
				maximumWords:50000,
				initialFrameWidth:500,
				initialFrameHeight:200,
				serverUrl:"${imageUrl}/ueditor/controller.jsp?fromdomain=${ctx}",
				toolbars: [ueditor_simpletoolbar]
			});
		})
		//subject ztree start
		var subject_setting = {
			view:{
				showLine: true,
				showIcon: true,
				selectedMulti: false,
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey:'subjectId',
					pIdKey:'parentId',
					rootPid:''
				},
				key:{
					name:'subjectName',
					title:'subjectName'
				}
			},
			callback: {
				onClick: subject_treeOnclick
			}
		};
		var subject_treedata=${subjectList};

		//切换专业时操作
		function subject_treeOnclick(e,treeId, treeNode) {
			$("#doc-dropdown-js").dropdown('close');//隐藏下拉菜单；
			$("#subjectId").val(treeNode.subjectId);
			$("#subjectNameBtn").val(treeNode.subjectName);
		}

		$().ready(function() {
			$('#doc-dropdown-js').dropdown({justify: '#doc-dropdown-justify-js'});
			$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
			//专业名称显示
			if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
				var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
				var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
				if(treeNode!=null){
					$("#subjectNameBtn").val(treeNode.subjectName);
				}
			}
		});
		$(function() {
			var sellType ='${course.sellType}';
			if(sellType!=null && sellType !=""){
				if(sellType=='COURSE' || sellType=='PACKAGE' ||sellType=='LIVE'){
					$("#sellType").val(sellType);

				}else{
					$("#sellType").val('COURSE');
				}
			}
			if(sellType!='LIVE'){
				$(".showLive").hide();
			}
			$(window).load(function(){
				$("#sellType").next().children("div").remove();
				var groups = $("#groupIds").val();
				$("input[name='groupId']").each(function(){
					var _this = $(this);
					if(groups.indexOf(_this.val())!=-1){
						_this.prop("checkbox",true);
					}
				});
			});
			$("#isavaliable").val(${course.isavaliable});
			if('${course.isRestrict}'=='0'){
				$("#restrictNumDiv").hide();
			}
			if('${course.isPay}'=='0'){
				$(".price").hide();
			}else{
				$(".price").show();
			}
			$("#losetype").val(${course.losetype});
			// 过期类型切换时间绑定
			$("#losetype").change(function() {
				$(".loseTimeShow").hide();
				$(".loseAbsTimeShow").hide();
				if ($(this).val() == 1) {
					$(".loseTimeShow").show();
					$("#loseAbsTime").val('');
				}
				if ($(this).val() == 0) {
					$(".loseAbsTimeShow").show();
					$("#loseTime").val('');
				}
			});
			querytecher();
			//上传图片
			initUploadify("fileupload","imgUrl","courseLogo","fileQueue","course");

			// 内部课程开关监听
			$('#companySellType').on('switchChange.bootstrapSwitch', function(event, state) {
				if(state){
					$('#companySellTypeVal').val("OUTTER");
				}else{
					$('#companySellTypeVal').val("INNER");
				}
			});
		})
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">课程管理</strong> / <small>修改课程</small></div>
</div>
<hr>
<form action="${ctx}/admin/cou/update" method="post" id="courseForm" class="am-form" data-am-validator>
	<input id="tcIds" type="hidden" value="${course.teacherIds}" name="course.teacherIds" />
	<input type="hidden" value="${course.id}" name="course.id" />
	<input name="course.subjectId" id="subjectId" type="hidden" value="${course.subjectId }"/>
	<input name="course.logo" id="courseLogo" type="hidden" value="${course.logo}"/>
	<input name="course.groupIds" type="hidden" id="groupIds" value="${course.groupIds}"/>
	<input type="hidden" value="${course.sellType}" name="course.sellType" />
	<div class="mt20">
		<div data-am-tabs="" class="am-tabs">
			<div class="am-g am-margin-top am-form-group">
				<label for="name" class="am-u-sm-4 am-u-md-2 am-text-right">
					销售形式
				</label>
				<div class="am-u-sm-8 am-u-md-6">
					<select name="course.sellType" disabled id="sellType" data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
						<option value="COURSE">课程</option>
						<option value="PACKAGE">套餐</option>
						<option value="LIVE">直播</option>
					</select>
				</div>
				<div class="am-hide-sm-only am-u-md-4"></div>
			</div>
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="#tab1">基本信息</a></li>
				<li class="showLive"><a href="#tab2">直播信息</a></li>
				<li class=""><a href="#tab3">售卖信息</a></li>
				<li class=""><a href="#tab4">统计信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程名称
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="name" value="${course.name}" class="am-input-sm" required placeholder="请填写课程名称" name="course.name">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 专业分类
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<div id="doc-dropdown-justify-js" style="width: 200px">
								<div class="am-dropdown" id="doc-dropdown-js">
									<input type="text" class="am-input-sm am-dropdown-toggle" required id="subjectNameBtn" value="" readonly="readonly"/>
									<div class="am-dropdown-content" style="overflow: auto; max-height: 300px;">
										<div id="subject_ztreedemo" class="ztree"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 添加教师
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<div id="tchtml"></div>
							<a href="javascript:showNewwin()" title="添加教师" class="am-btn am-btn-primary">添加教师</a>
							<a href="javascript:clearTeacher()" title="清空" class="am-btn am-btn-primary">清空</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程简介
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea id="title" name="course.title">${course.title}</textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程详情
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input id="hidContext" type="hidden" name="course.context" />
							<script id="context" type="text/plain">${course.context}</script>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程图片
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<c:if test="${not empty course.logo}">
								<img id="imgUrl" src="<%=staticImageServer%>/${course.logo}" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
							</c:if>
							<c:if test="${empty course.logo}">
								<img id="imgUrl" src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
							</c:if>
							<input type="file" id="fileupload">
							<span style="vertical-align: top;color:red;">图片大小：540x320</span>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 学时(分钟)
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="number" id="minutes" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.minutes}" required placeholder="请填写学时" name="course.minutes">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 上架状态
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<select name="course.isavaliable" id="isavaliable" data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
								<option value="0">上架</option>
								<option value="1">下架</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 总课时数
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="number" id="totalLessionnum" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.totalLessionnum}" required placeholder="请填写总课时数" name="course.totalLessionnum">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 对外开放
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="hidden" value="${course.companySellType}" name="course.companySellType" id="companySellTypeVal" />
							<input id="companySellType" type="checkbox" <c:if test="${course.companySellType=='OUTTER'}">checked</c:if> data-size="sm" data-am-switch data-off-color="danger">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							所属岗位
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<c:forEach var="group" items="${userGroupList}" varStatus="count">
							<div class="am-u-sm-6 am-u-md-6 am-u-end">
								<label class="am-checkbox-inline">
									<input type="checkbox" <c:if test="${group.check==1}">checked</c:if> name="groupId" value="${group.id}" data-am-ucheck> ${group.name}
								</label>
							</div>
							</c:forEach>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 排序
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="number" id="order" class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.order}" required placeholder="请填写排序" name="course.order">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top ">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="下一步" href="javascript:nextStep(1)">下一步</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</div>
				<div id="tab2" class="am-tab-panel am-fade am-in showLive">
					<input type="hidden" name="courseMember" value=""/>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 是否限定人数
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<label class="am-radio-inline">
								<input type="radio" value="0" name="course.isRestrict" <c:if test="${course.isRestrict==0}">checked</c:if> onchange="restrictChange(0)" data-am-ucheck> 否
							</label>
							<label class="am-radio-inline">
								<input type="radio" value="1" name="course.isRestrict" <c:if test="${course.isRestrict==1}">checked</c:if> onchange="restrictChange(1)" data-am-ucheck> 是
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top" id="restrictNumDiv">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 直播限定人数
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input value="${course.restrictNum}" type="text" class="am-input-sm" name="course.restrictNum" id="restrictNum" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 直播开始时间
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input value="<fmt:formatDate  type='both' value='${course.liveBeginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" id="liveBeginTime" class="form-datetime-lang am-form-field" name="course.liveBeginTime" readonly/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 直播结束时间
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input value="<fmt:formatDate  type='both' value='${course.liveEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" id="liveEndTime" class="form-datetime-lang am-form-field" name="course.liveEndTime"  readonly/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 直播编码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="freeurl" class="am-input-sm" name="course.freeurl" value="${course.freeurl}"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							教师直播编码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="teacherVideoUrl" class="am-input-sm" name="course.teacherVideoUrl" value="${course.teacherVideoUrl}"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							教师直播密码
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="videoPassword" class="am-input-sm" name="course.videoPassword" value="${course.videoPassword}" />
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top" id="reserve">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							是否预约
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<label class="am-radio-inline">
								<input type="radio" value="0" name="course.isReserve" data-am-ucheck <c:if test="${course.isReserve==0}">checked</c:if> /> 否
							</label>
							<label class="am-radio-inline">
								<input type="radio" value="1" name="course.isReserve" data-am-ucheck <c:if test="${course.isReserve==1}">checked</c:if> />是
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top ">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="下一步" href="javascript:nextStep(2)">下一步</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</div>
				<div id="tab3" class="am-tab-panel am-fade am-in">
					<c:if test="${salemap.sale.verifyMember=='ON'}">
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">
								<font class="am-text-danger">*</font> 会员类型
							</div>
							<div class="am-u-sm-8 am-u-md-6">
								<c:forEach items="${memberTypes }" var="memberType">
									<div class="am-u-sm-6 am-u-md-6 am-u-end">
										<label class="am-checkbox">
											<input type="checkbox" name="courseMember"
											   <c:if test="${not empty course.memberTypes}">
												   <c:forEach items="${course.memberTypes}" var="member">
														<c:if test="${member.id==memberType.id}">
															checked
														</c:if>
												   </c:forEach>
											   </c:if>
											   data-am-ucheck value="${memberType.id }" />${memberType.title }
										</label>
									</div>
								</c:forEach>
							</div>
							<div class="am-hide-sm-only am-u-md-4"></div>
						</div>
					</c:if>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程收费
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<label class="am-radio-inline">
								<input type="radio" value="0" name="course.isPay" onchange="payChange(0)" data-am-ucheck <c:if test="${course.isPay==0}">checked</c:if> /> 免费
							</label>
							<label class="am-radio-inline">
								<input type="radio" value="1" name="course.isPay" onchange="payChange(1)" data-am-ucheck <c:if test="${course.isPay==1}">checked</c:if>/> 收费
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top price">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程原价格
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="course.sourceprice" value="${course.sourceprice}" class="am-input-sm" id="sourceprice" />
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top price">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 课程销售价格
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="course.currentprice" value="${course.currentprice}" class="am-input-sm" id="currentprice"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top ">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 有效期类型
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<select name="course.losetype" id="losetype" data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
								<option value="0">到期时间</option>
								<option value="1">按天数</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top loseAbsTimeShow">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 到期时间
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="loseAbsTime" class="form-datetime-lang am-form-field" value="<fmt:formatDate  type='both' value='${course.loseAbsTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" name="course.loseAbsTime" readonly/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top loseTimeShow undis">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 按天数
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="number" id="loseTime" class="am-input-sm" value="${course.loseTime}" name="course.loseTime" />
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top ">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<a class="am-btn am-btn-danger" title="下一步" href="javascript:nextStep(3)">下一步</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</div>
				<div id="tab4" class="am-tab-panel am-fade am-in">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 假的销售数量
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="number" id="pageBuycount" class="am-input-sm" name="course.pageBuycount" required onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.pageBuycount}" placeholder="请输入销售数量"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 假的浏览数量
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="number" id="pageViewcount" class="am-input-sm" name="course.pageViewcount" required onkeyup="this.value=this.value.replace(/\D/g,'')" value="${course.pageViewcount}" placeholder="请输入浏览数量"/>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" title="提 交" onclick="submit">提 交</button>
							<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.mine.js"></script>
</body>
</html>
