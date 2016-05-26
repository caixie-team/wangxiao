<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优惠券添加</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}" />
<script
	src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script
	src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>
<%-- <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script> --%>
<script type="text/javascript">
	//subject ztree start
	/* var subject_setting = {
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
	var subject_treedata=${subjectList}; */

	//切换专业时操作
	/* 	function subject_treeOnclick(e,treeId, treeNode) {
	 hideSubjectMenu();
	 $("#subjectId").val(treeNode.subjectId);
	 $("#subjectNameBtn").val(treeNode.subjectName);
	 } */

	//清空专业的查询条件时同时清除考点
	/* 	function subject_cleantreevalue(){
	 hideSubjectMenu();
	 $("#subjectId").val(0);
	 $("#subjectNameBtn").val("");
	 }
	 function showSubjectMenu() {
	 var cityObj = $("#subjectNameBtn");
	 var cityOffset = $("#subjectNameBtn").offset();
	 $("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	 $("body").bind("mousedown", onBodyDown);
	 }
	 function hideSubjectMenu() {
	 $("#subjectmenuContent").fadeOut("fast");
	 $("body").unbind("mousedown", onBodyDown);
	 }
	 function onBodyDown(event) {
	 if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
	 hideSubjectMenu();
	 }
	 } */
	/* 	
	 $().ready(function() {
	 $.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	 //专业名称显示
	 if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
	 var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
	 var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
	 if(treeNode!=null){
	 $("#subjectNameBtn").val(treeNode.subjectName);
	 }
	 }
	 }); */
	/* 	$(function(){
	 $( "#startTime,#endTime" ).datepicker(
	 {regional:"zh-CN",
	 changeMonth: true,
	 dateFormat:"yy-mm-dd "
	 });
	
	 }); */
	$(function() {
		changeType($('input[name="type"]:checked').val());//初始化优惠券类型
		changeRange($('input[name="range"]:checked').val());//初始化优惠券限制范围
		changeUseType($('input[name="useType"]:checked').val());//初始化次数限制
	});
	function changeType(val) {
		$('input[name="range"]:eq(0)').prop("checked", true);
		changeRange(1);
		if (val == 1) {
			$("#rangeTr").show();
			$("#reduceTr").css("display", "none");
			$("#preferentialTr").css("display", "");
			$("#reduceInput").attr("name", "");
			$("#preferentialSelect").attr("name", "coupon.amount");
		} else if (val == 2) {
			$("#rangeTr").show();
			$("#preferentialTr").css("display", "none");
			$("#reduceTr").css("display", "");
			$("#preferentialSelect").attr("name", "");
			$("#reduceInput").attr("name", "coupon.amount");
		} else if (val == 3) {//会员定额券无范围限制
			$("#rangeTr").hide();
			$("#preferentialTr").css("display", "none");
			$("#reduceTr").css("display", "");
			$("#preferentialSelect").attr("name", "");
			$("#reduceInput").attr("name", "coupon.amount");
		}
	}
	function changeUseType(val) {
		if (val == 2) {
			$("#totalNumTr").css("display", "");
			$("#totalNum").attr("name", "coupon.totalNum");
		} else {
			$("#totalNumTr").css("display", "none");
			$("#totalNum").attr("name", "");
		}
	}
	function changeRange(val) {
		if (val == 1) {//所有范围
			//subject_cleantreevalue();//清空专业
			$("#courseIdHidden").val("");
			$("#limitSubject").css("display", "none");
			$("#limitCourse").css("display", "none");
			$("#subjectSelect").val(-1);
		} else if (val == 2) {//单选项目
			$("#courseIdHidden").val("");
			$("#limitSubject").css("display", "");
			$("#limitCourse").css("display", "none");
		} else if (val == 3) {//多选课程
			//subject_cleantreevalue();//清空专业
			$("#limitSubject").css("display", "none");
			$("#limitCourse").css("display", "");
			$("#subjectSelect").val(-1);
		}
	}

	function addCourse() {
		window
				.open(
						'${cxt}/admin/cou/couponCourseList?page.currentPage=1',
						+'newwindow',
						'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802');
	}
	function getCourseList(CourseList) {
		//p对象的name获取已存在的课程集合，去除提交过来的重复对象
		$("p[name='courseName']").each(function(i, val) {
			for (var j = 0; j < CourseList.length; j++) {
				var id = CourseList[j];
				if (val.id == 'coursespan' + id[0]) {
					CourseList.splice(j, 1);
				}
			}
		});
		//插入到表格中
		coursePAdd(CourseList);
	}

	function coursePAdd(myArray) {
		var str = "";
		var courseIds = $("#courseIdHidden").val();
		for (var i = 0; i < myArray.length; i++) {
			var arr = myArray[i];
			str += "<p style='width:100%;margin: 0 0 0em' id='coursespan"
					+ arr[0]
					+ "' name='courseName' value='"
					+ arr[0]
					+ "' title='"
					+ arr[1]
					+ "'>"
					+ arr[1]
					+ "&nbsp;&nbsp;<a href='javascript:void(0)' class='am-btn am-btn-danger' onclick=delcourse('"
					+ arr[0] + "','" + arr[1] + "')>删除</a></p>";
			courseIds += arr[0] + ",";
		}
		$("#coursestr").prepend(str);
		$("#courseIdHidden").val(courseIds);
	}
	function delcourse(courseId, title) {
		if (confirm("确定要删除【" + title + "】")) {
			$("#coursespan" + courseId).remove();
			var ids = $("#courseIdHidden").val();
			ids = ids.replace(courseId + ",", "");
			$("#courseIdHidden").val(ids);
		}
		;
	}
	function clearcourse() {
		$("#courseIdHidden").val("");
		$("#coursestr>p").remove();
	}
	function addSubmit() {
		//验证优惠券名称
		if ($("#couponTitle").val() == null || $("#couponTitle").val() == '') {
			dialogFun('添加优惠券','优惠券名称',0);
			return;
		}
		//验证优惠券使用限额
		if ($("#limitAmount").val() == null || $("#limitAmount").val() == '') {
			dialogFun('添加优惠券','请输入优惠券使用限额',0);
			return;
		}
		//验证优惠券类型
		var type = $('input[name="type"]:checked').val();
		if (type == 1) {
			if ($("#preferentialSelect").val() < 0) {
				dialogFun('添加优惠券','请选择优惠折扣',0);
				return;
			}
		} else {
			if ($("#reduceInput").val() == null
					|| $("#reduceInput").val() == '') {
				dialogFun('添加优惠券','请输入优惠金额',0);
				return;
			}
			if (parseFloat($("#limitAmount").val()) <= parseFloat($(
					"#reduceInput").val())) {
				dialogFun('添加优惠券','使用限额必须大于优惠金额',0);
				return;
			}
		}

		//验证优惠券限制范围
		var range = $('input[name="range"]:checked').val();
		if (range == 2) {//单选项目
			if ($("#subjectId").val() == null || $("#subjectId").val() == 0) {
				dialogFun('添加优惠券','请选择优惠券限制项目',0);
				return;
			}
		} else if (range == 3) {//多选课程
			if ($("#courseIdHidden").val() == null
					|| $("#courseIdHidden").val() == '') {
				dialogFun('添加优惠券','使用限额必须大于优惠金额',0);
				return;
			}
		}
		//优惠券使用开始至截至日期
		if ($("#startTime").val() == null || $("#startTime").val() == '') {
			dialogFun('添加优惠券','请选择使用起始日期',0);
			return;
		}

		if ($("#endTime").val() == null || $("#endTime").val() == '') {
			dialogFun('添加优惠券','请选择使用截止日期',0);
			return;
		}
		var begin = new Date($("#startTime").val().replace(/-/g, "/"));
		var end = new Date($("#endTime").val().replace(/-/g, "/"));

		if (begin > end) {
			dialogFun('添加优惠券','截止日期必须大于起始日期',0);
			return;
		}
		//验证优惠券生成数量
		var useType = $('input[name="useType"]:checked').val();
		if (useType == 2) {//适用人群为个人时验证生成数量，所有人只生成一个优惠券编码
			if ($("#totalNum").val() == null || $("#totalNum").val() == '') {
				dialogFun('添加优惠券','请输入优惠券编码生成数量',0);
				return;
			}
			if (isNaN($("#totalNum").val())) {
				dialogFun('添加优惠券','生成优惠券编码数量只能为数字',0);
				return;
			}
			if (parseInt($("#totalNum").val()) > 5000) {
				dialogFun('添加优惠券','生成优惠券编码数量不能大于5000',0);
				return;
			}
			if (parseInt($("#totalNum").val()) <= 0) {
				dialogFun('添加优惠券','生成优惠券编码数量不能小于0',0);
				return;
			}
		}
		$("#addCouponForm").submit();
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">优惠券管理</strong> / <small>优惠券添加</small>
		</div>
	</div>
	<hr />
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="#tab1">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form action="${ctx}/admin/coupon/add" method="post"
						class="am-form am-form-inline" id="addCouponForm"
						data-am-validator>
						<input type="hidden" name="coupon.subjectId" id="subjectId"
							value="0" /> <input type="hidden" name="limitCourseId"
							id="courseIdHidden" />
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券名称</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input class="am-input-sm" required type="text"
									name="coupon.title" id="couponTitle" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠券类型</div>
						<div class="am-u-sm-8 am-u-md-4">
							<label class="am-radio-inline">
								<input type="radio" name="type" value="1" data-am-ucheck onclick="changeType(1)"/>
								折扣券
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="type" checked value="2" data-am-ucheck onclick="changeType(2)"/>
								定额券
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="type" checked value="3" data-am-ucheck onclick="changeType(3)"/>
								会员定额券
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">*会员定额券只能在开会员时使用</div>
					</div>
						<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">次数限制</div>
						<div class="am-u-sm-8 am-u-md-4">
							<label class="am-radio-inline">
								<input type="radio" name="useType" value="2" data-am-ucheck onclick="changeUseType(2)"/>
								正常
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="useType" checked value="1" data-am-ucheck onclick="changeUseType(1)"/>
								无限
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">*'无限'优惠券在有效期内可以被任意多人使用多次，'正常'优惠券使用一次后不能继续使用</div>
					</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用限额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" required id="limitAmount"
									name="coupon.limitAmount" class="am-input-sm">
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入'300'</div>
						</div>
						<div class="am-g am-margin-top" id="preferentialTr">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠折扣</div>
							<div class="am-u-sm-8 am-u-md-10">
								<select class="am-form-field" id="preferentialSelect"
									data-am-selected="{maxHeight: 100}">
									<option value="-1">--请选择优惠折扣--</option>
									<option value="9.5">9.5</option>
									<option value="9.0">9.0</option>
									<option value="8.5">8.5</option>
									<option value="8.0">8.0</option>
									<option value="7.5">7.5</option>
									<option value="7.0">7.0</option>
									<option value="6.5">6.5</option>
									<option value="6.0">6.0</option>
									<option value="5.5">5.5</option>
									<option value="5.0">5.0</option>
									<option value="4.5">4.5</option>
									<option value="4.0">4.0</option>
									<option value="3.5">3.5</option>
									<option value="3.0">3.0</option>
									<option value="2.5">2.5</option>
									<option value="2.0">2.0</option>
									<option value="1.5">1.5</option>
									<option value="1.0">1.0</option>
									<option value="0.5">0.5</option>
								</select>
							</div>
						</div>

						<div id="reduceTr" class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">优惠金额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" required id="reduceInput" class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*该优惠券金额，如5元优惠券，则请输入'5'</div>
						</div>
						
					<div class="am-g am-margin-top am-form-group" id="rangeTr">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">适用范围</div>
						<div class="am-u-sm-8 am-u-md-4">
							<label class="am-radio-inline">
								<input type="radio" name="range" value="2" data-am-ucheck onclick="changeRange(1)"/>
								所有课程
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="range" checked value="1" data-am-ucheck onclick="changeRange(2)"/>
								单选项目
							</label>
							<label class="am-radio-inline">
								<input type="radio" name="range" checked value="1" data-am-ucheck onclick="changeRange(3)"/>
								多选课程
							</label>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">*'无限'优惠券在有效期内可以被任意多人使用多次，'正常'优惠券使用一次后不能继续使用</div>
					</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用起始日期</div>
							<div class="am-u-sm-8 am-u-md-10">
								<div class="am-form-group am-form-icon">
									<i class="am-icon-calendar"></i> <input type="text"
										name="coupon.startTime" id="startTime" required readonly
										placeholder="使用起始日期" class="form-datetime-lang am-form-field">
								</div>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用截止日期</div>
							<div class="am-u-sm-8 am-u-md-10">
								<div class="am-form-group am-form-icon">
									<i class="am-icon-calendar"></i> <input type="text"
										name="coupon.endTime" id="endTime" required readonly
										placeholder="使用截止日期" class="form-datetime-lang am-form-field">
								</div>
							</div>
						</div>

						<div id="totalNumTr" class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">生成优惠编码数量</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" required name="coupon.totalNum" id="totalNum"
									class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*必须是数字，数字不得大于5000</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">备注</div>
							<div class="am-u-sm-8 am-u-md-4 am-u-end">
								<textarea rows="4"  cols="80" name="coupon.info"
									id="info"></textarea>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-4">
								<button class="am-btn am-btn-danger" type="button"
									onclick="addSubmit()">提交</button>
								<button class="am-btn am-btn-danger" type="button"
									onclick="window.history.go(-1)">返回</button>
							</div>
							<div class="am-u-sm-12 am-u-md-6"></div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /tab4 end -->
</body>
</html>
