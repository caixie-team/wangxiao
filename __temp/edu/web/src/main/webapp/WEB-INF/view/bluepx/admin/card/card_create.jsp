<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>虚拟卡创建</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
	$(function() {
		//changeType($('input[name="type"]:checked').val());//初始化优惠券类型
		
		var addType="${addType}";
		if (addType == "course") {
			$("#courseTr").css("display", "");
			$("#cardType").val(1);
		} else{
			$("#courseTr").css("display", "none");
			$("#cardType").val(2);
		}
	});

	$(function() {

		$( "#startTime,#endTime" ).datepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd "
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
					+ "&nbsp;&nbsp;<a href='javascript:void(0)' class='btn btn-danger' onclick=delcourse('"
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
		}
		;
	}
	function clearcourse() {
		$("#courseIdHidden").val("");
		$("#coursestr>p").remove();
	}
	function addSubmit() {
		var courseNames = "";
		$("p[name='courseName']").each(function() {
			courseNames += $(this).attr('title') + ",";
		});
		$("#cardCourseName").val(courseNames);
		//验证优惠券名称
		if ($("#cardTitle").val() == null || $("#cardTitle").val() == '') {
			alert("请输入虚拟卡名称");
			return;
		}

		//验证优惠券类型
		//var type = $('input[name="type"]:checked').val();
		var type=$("#cardType").val();
		//课程卡
		if (type == 1) {
			if ($("#courseIdHidden").val().trim() == '') {
				alert("请添加关联的课程");
				return;
			}
			//充值卡
		} else {
			//验证优惠券使用限额
			if ($("#cardMoney").val() == null || $("#cardMoney").val() == '') {
				alert("请输入充值卡额度");
				return;
			}
		}
		//优惠券使用开始至截至日期
		if ($("#startTime").val() == null || $("#startTime").val() == '') {
			alert("请选择使用起始日期");
			return;
		}

		if ($("#endTime").val() == null || $("#endTime").val() == '') {
			alert("请选择使用截止日期");
			return;
		}
		var begin=new Date($("#startTime").val().replace(/-/g,"/"));
	    var end=new Date($("#endTime").val().replace(/-/g,"/"));
		if(begin>end){
			alert("截止日期必须大于起始日期");
			return;
		}
		
		//验证优惠券生成数量
		if ($("#totalNum").val() == null || $("#totalNum").val() == '') {
			alert("请输入虚拟卡生成数量");
			return;
		}
		if (isNaN($("#totalNum").val())) {
			alert("生成虚拟卡只能为数字");
			return;
		}
		if (parseInt($("#totalNum").val()) > 1000) {
			alert("生成虚拟卡数量不能大于1000");
			return;
		}
		if (parseInt($("#totalNum").val()) <= 0) {
			alert("生成虚拟卡数量不能小于0");
			return;
		}

		$("#addCardForm").submit();
	}
</script>
</head>
<body>
	<form action="${ctx}/admin/card/createcard" method="post" id="addCardForm">
		<input type="hidden" name="courseIds" id="courseIdHidden" /> <input type="hidden" name="card.type" id="cardType" /> <input type="hidden" name="card.courseName" id="cardCourseName" />

		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>虚拟卡管理</span> &gt; <span>新建虚拟卡</span>
			</h4>
		</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>虚拟卡基本属性<tt class="c_666 ml20 fsize12">
										（<font color="red">*</font>&nbsp;为必填项）
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;虚拟卡名称</td>
							<td><input type="text" name="card.name" id="cardTitle" class="{required:true}" /></td>
							
						</tr>
						<!-- <tr>
							<td width="20%" align="center"><font color="red">*</font>虚拟卡类型</td>
							<td width="80%"><input type="radio" value="1" name="type" checked="checked" onclick="changeType(1)" />课程卡&nbsp;&nbsp; <input type="radio" value="2" name="type" onclick="changeType(2)" />充值卡</td>
						</tr> -->
						<tr >
							<td align="center"><font color="red">*</font>&nbsp;虚拟卡额度</td>
							<td><input type="text" id="cardMoney" name="card.money" class="{required:true,number:true,min:0,max:1000}" />&nbsp;&nbsp;<font color="red"></font></td>
						</tr>
						<tr id="courseTr">
							<td align="center">&nbsp;</td>
							<td><span style="float: left;" id="coursestr"> <a href="javascript:void(0)" onclick="addCourse()" style="cursor: pointer;" class="btn btn-danger">添加课程</a>&nbsp;&nbsp; <a href="javascript:void(0)" style="cursor: pointer;" onclick="clearcourse()" class="btn btn-danger">清空</a>
							</span></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;使用起始日期</td>
							<td><input type="text" name="card.beginTime" readonly="readonly" id="startTime" class="AS-inp"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;使用截止日期</td>
							<td><input type="text" name="card.endTime" readonly="readonly" id="endTime" class="AS-inp"/></td>
						</tr>
						<tr id="totalNumTr">
							<td align="center"><font color="red">*</font>&nbsp;虚拟卡编码数量</td>
							<td><input type="text" name="card.num" id="totalNum" class="{required:true,number:true,min:0,max:1000}" />&nbsp;&nbsp;<font color="red">*必须是数字，数字不得大于1000</font></td>
						</tr>
						<tr>
							<td align="center">备注</td>
							<td><textarea rows="3" cols="80" name="card.remark" id="info"></textarea></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">提 交</a> <a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- /tab4 end -->
	</form>
</body>
</html>
