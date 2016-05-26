<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>虚拟卡创建</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/amazeui/css/amazeui.datetimepicker.css?v=${v}" />
<script
	src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.min.js?v=${v}"></script>
<script
	src="${ctximg}/static/common/amazeui/js/amazeui.datetimepicker.zh-CN.js?v=${v}"></script>


<script type="text/javascript">
	$(function() {
		//changeType($('input[name="type"]:checked').val());//初始化优惠券类型

		var addType = "${addType}";
		if (addType == "course") {
			$("#courseTr").css("display", "");
			$("#cardType").val(1);
		} else {
			$("#courseTr").css("display", "none");
			$("#cardType").val(2);
		}
	});

	$(function() {

		$("#startTime,#endTime").datepicker({
			regional : "zh-CN",
			changeMonth : true,
			dateFormat : "yy-mm-dd "
		});

	});
	function changeType(val) {
		$("#cardType").val(val);
		if (val == 1) {
			$("#moenyTr").css("display", "none");
			$("#courseTr").css("display", "");
			$("#reduceInput").attr("name", "");
			$("#preferentialSelect").attr("name", "coupon.amount");
		} else {
			$("#courseTr").css("display", "none");
			$("#moenyTr").css("display", "");
			$("#preferentialSelect").attr("name", "");
			$("#reduceInput").attr("name", "coupon.amount");
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
		str+='<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
		for(var i=0; i < myArray.length;i++){
			var arr = myArray[i];
			str+='<button class="am-btn am-btn-default ml10 mt10" onclick="delUsers('+arr[0]+')" type="button">'+arr[1]+'<a onclick="delUsers('+arr[0]+')" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
		}
		str+='</div></div>';
		$("#uchtml").html(str);
		courseIds += arr[0] + ",";
		$("#courseIdHidden").val(courseIds);
	}
	function delcourse(courseId, title) {
		$("#coursespan" + courseId).remove();
		var ids = $("#courseIdHidden").val();
		ids = ids.replace(courseId + ",", "");
		$("#courseIdHidden").val(ids);
		if (ids == null || ids == "") {
			$("#coursestr").html("");
		}
	}
	function clearcourse() {
		$("#courseIdHidden").val("");
		$("#uchtml").html("");
	}
	function addSubmit() {
		var courseNames = "";
		$("p[name='courseName']").each(function() {
			courseNames += $(this).attr('title') + ",";
		});
		$("#cardCourseName").val(courseNames);
		//验证优惠券名称
		if ($("#cardTitle").val() == null || $("#cardTitle").val() == '') {
			dialogFun("新建课程卡","请输入虚拟卡名称",0);
			return;
		}

		//验证优惠券类型
		//var type = $('input[name="type"]:checked').val();
		var type = $("#cardType").val();
		//课程卡
		if (type == 1) {
			if ($("#courseIdHidden").val().trim() == '') {
				dialogFun("新建课程卡","请添加关联的课程",0);
				return;
			}
			//充值卡
		} else {
			//验证优惠券使用限额
			if ($("#cardMoney").val() == null || $("#cardMoney").val() == '') {
				dialogFun("新建课程卡","请输入充值卡额度",0);
				return;
			}
		}
		//优惠券使用开始至截至日期
		if ($("#startTime").val() == null || $("#startTime").val() == '') {
			dialogFun("新建课程卡","请选择使用起始日期",0);
			return;
		}

		if ($("#endTime").val() == null || $("#endTime").val() == '') {
			dialogFun("新建课程卡","请选择使用截止日期",0);
			return;
		}
		var begin = new Date($("#startTime").val().replace(/-/g, "/"));
		var end = new Date($("#endTime").val().replace(/-/g, "/"));
		if (begin > end) {
			dialogFun("新建课程卡","截止日期必须大于起始日期",0);
			return;
		}

		//验证优惠券生成数量
		if ($("#totalNum").val() == null || $("#totalNum").val() == '') {
			dialogFun("新建课程卡","请输入虚拟卡生成数量",0);
			return;
		}
		if (isNaN($("#totalNum").val())) {
			dialogFun("新建课程卡","生成虚拟卡只能为数字",0);
			return;
		}
		if (parseInt($("#totalNum").val()) > 1000) {
			dialogFun("新建课程卡","生成虚拟卡数量不能大于1000",0);
			return;
		}
		if (parseInt($("#totalNum").val()) <= 0) {
			dialogFun("新建课程卡","生成虚拟卡数量不能小于0",0);
			return;
		}

		$("#addCardForm").submit();
	}
</script>
</head>
<body>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">虚拟卡管理</strong> / <small>新建虚拟卡</small>
		</div>
	</div>
	<br />
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="#tab1">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<form action="${ctx}/admin/card/createcard" class="am-form am-form-inline" method="post" id="addCardForm" data-am-validator>
						<input type="hidden" name="courseIds" id="courseIdHidden" />
						<input type="hidden" name="card.type" id="cardType" />
						<input type="hidden" name="card.courseName" id="cardCourseName" />
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">虚拟卡名称</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="text" name="card.name" required id="cardTitle"
									class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">虚拟卡额度</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input class="am-input-sm" type="text" required id="cardMoney" onkeyup="this.value=this.value.replace(/\D/g,'')" name="card.money" />
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top" id="courseTr">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">添加课程</div>
							<div class="am-u-sm-8 am-u-md-6">
								<div id="uchtml"></div>
								<a href="javascript:addCourse()" title="添加课程" class="am-btn am-btn-primary">添加课程</a>
								<a href="javascript:clearcourse()" title="清空" class="am-btn am-btn-primary">清空</a>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用起始日期</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="text" name="card.beginTime" required
									readonly="readonly" id="startTime" class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">使用截止日期</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="text" name="card.endTime" required
									readonly="readonly" id="endTime" class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">虚拟卡编码数量</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="text" name="card.num" required onkeyup="this.value=this.value.replace(/\D/g,'')" id="totalNum" />
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">*必须是数字，数字不得大于1000</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">备注</div>
							<div class="am-u-sm-8 am-u-md-6 am-u-end">
								<textarea rows="3" cols="80" name="card.remark" id="info"></textarea>
							</div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-6">
								<button class="am-btn am-btn-danger" type="button" onclick="addSubmit()">提交</button>
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
