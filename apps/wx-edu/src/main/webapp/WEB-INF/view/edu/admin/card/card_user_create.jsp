<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员卡创建</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>

<script type="text/javascript">
	$(function() {
		$("#addCardForm").validate();
	});

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
		str += '<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
		for (var i = 0; i < myArray.length; i++) {
			var arr = myArray[i];

			str += "<button type='button' onclick=delcourse('"
					+ arr[0]
					+ "','"
					+ arr[1]
					+ "')  id='coursespan"
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
			courseNamestr = arr[1] + ",";
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
			if (ids == null || ids == "") {
				$("#coursestr").html("");
			}
		}
		;

	}
	function clearcourse() {
		$("#courseIdHidden").val("");
		$("#coursestr").html("");
	}
	function addSubmit() {
		var courseNames = "";
		$("p[name='courseName']").each(function() {
			courseNames += $(this).attr('title') + ",";
		});
		$("#cardCourseName").val(courseNames);
		//验证优惠券名称
		if ($("#cardTitle").val() == null || $("#cardTitle").val() == '') {
			dialogFun("新建学员卡",'请输入学员卡名称',0);
			return;
		}
		//验证学员卡金额
		if($("#cardMoney").val()==null || $("#cardMoney").val()==''){
			dialogFun("新建学员卡",'请输入学员卡金额',0);
			return;
		}
		if($("#courseIdHidden").val()==null || $("#courseIdHidden").val()==''){
			dialogFun("新建学员卡",'请添加课程',0);
			return;
		}
		//验证优惠券生成数量
		if ($("#totalNum").val() == null || $("#totalNum").val() == '') {
			dialogFun("新建学员卡",'请输入虚拟卡生成数量',0);
			return;
		}
		if (isNaN($("#totalNum").val())) {
			dialogFun("新建学员卡",'生成虚拟卡只能为数字',0);
			return;
		}
		if (parseInt($("#totalNum").val()) > 2000) {
			dialogFun("新建学员卡",'生成虚拟卡数量不能大于2000',0);
			return;
		}
		if (parseInt($("#totalNum").val()) <= 0) {
			dialogFun("新建学员卡",'生成虚拟卡数量不能小于0',0);
			return;
		}
		$(".dialog").show();
		//$("#addCardForm").submit();
		$.ajax({
			url : "${ctx}/admin/card/createUsercard",
			data : {
				"courseIds" : $("#courseIdHidden").val(),
				"card.type" : 3,
				"card.courseName" : $("#cardCourseName").val(),
				"card.name" : $("#cardTitle").val(),
				"card.money" : $("#cardMoney").val(),
				"card.num" : $("#totalNum").val(),
				"card.emailPrefix" : $("#emailPrefix").val(),
				"card.emailSuffix" : $("#emailSuffix").val(),
				"card.remark" : $("#info").val()
			},
			post : "post",
			async : false,
			success : function(result) {
				if (result.success == true) {
					window.location.href = "${ctx}/admin/card/cardlist";
				} else {
					dialogFun("新建学员卡",result.message,0);
				}

			},
			error : function(error) {
				dialogFun("新建学员卡",error.responseText,0);
			}
		})
	}
</script>
<style>
.dialog {
	background: none repeat scroll 0 0 rgba(0, 0, 0, 0.68);
	border-radius: 8px;
	height: 160px;
	left: 50%;
	margin: -40px 0 0 -40px;
	overflow: hidden;
	position: absolute;
	text-align: center;
	top: 50%;
	width: 160px;
}
</style>
</head>
<body>
	<aside style="display:none;" class="dialog"> <span> <img
		height="80" width="80" src="${ctx}/static/edu/images/loading.gif"
		alt="loading" style="margin-top: 23px;" />
	</span>
	<h4 style="font-size: 20px;">请稍候...</h4>
	</aside>
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">线下卡管理</strong> / <small>新建学员卡</small>
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
					<form action="${ctx}/admin/card/createUsercard" method="post"
						class="am-form am-form-inline" id="addCardForm" data-am-validator>
						<input type="hidden" name="courseIds" id="courseIdHidden" /> <input
							type="hidden" name="card.type" id="cardType" value="3" /> <input
							type="hidden" name="card.courseName" id="cardCourseName" />

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">学员卡名称</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="card.name" id="cardTitle" required
									class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">学员卡金额</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" id="cardMoney" name="card.money" required
									class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top" id="courseTr">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">添加课程</div>
							<div class="am-u-sm-8 am-u-md-4">
								<div id="coursestr"></div>
								<a href="javascript:void(0)" onclick="addCourse()"
									class="am-btn am-btn-primary">添加课程</a> <a
									href="javascript:void(0)" onclick="clearcourse()"
									class="am-btn am-btn-primary">清空</a>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">学员卡编码数量</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="card.num" id="totalNum" required
									class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
						</div>

						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">备注</div>
							<div class="am-u-sm-8 am-u-md-4 am-u-end">
								<textarea rows="3" cols="80" name="card.remark" id="info"></textarea>
							</div>
						</div>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-4">
								<button class="am-btn am-btn-danger" type="button"
									onclick="addSubmit()">提交</button>
								<button class="am-btn am-btn-danger" type="button"
									onclick="history.go(-1)">返回</button>
							</div>
							<div class="am-u-sm-12 am-u-md-6"></div>
						</div>


						<!-- 
		<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<tbody>

                        <tr >
                            <td align="center"><font color="red">*</font>&nbsp;学员卡邮箱格式设置</td>
                            <td><input type="text" name="card.emailPrefix" id="emailPrefix" class="{required:true}" maxlength="3" style="width:30px" />* * * * * * * * @<input type="text" name="card.emailSuffix" id="emailSuffix" class="{required:true}" style="width:50px"/>.com&nbsp;&nbsp;<font color="red">*请输入生成邮箱的前缀和后缀，生成的学员卡将由此规则生成账号邮箱</font></td>
                        </tr>
					</tbody>
				</table>
			</div>
		</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
