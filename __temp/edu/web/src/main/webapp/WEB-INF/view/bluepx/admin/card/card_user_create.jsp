<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员卡创建</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>

<script type="text/javascript">
	$(function() {
        $("#addCardForm").validate();
    });

	function addCourse() {
		window.open(
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
			alert("请输入学员卡名称");
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
		if (parseInt($("#totalNum").val()) > 2000) {
			alert("生成虚拟卡数量不能大于2000");
			return;
		}
		if (parseInt($("#totalNum").val()) <= 0) {
			alert("生成虚拟卡数量不能小于0");
			return;
		}
		$(".dialog").show();
		//$("#addCardForm").submit();
		$.ajax({
			url : "${ctx}/admin/card/createUsercard",
			data :{
				"courseIds":$("#courseIdHidden").val(),
				"card.type":3,
				"card.courseName":$("#cardCourseName").val(),
				"card.name":$("#cardTitle").val(),
				"card.money":$("#cardMoney").val(),
				"card.num":$("#totalNum").val(),
				"card.emailPrefix":$("#emailPrefix").val(),
				"card.emailSuffix":$("#emailSuffix").val(),
				"card.remark":$("#info").val()
				},
			post : "post",
			async: false,
			success : function(result) {
				if(result.success==true){
					alert("添加成功");
					window.location.href="${ctx}/admin/card/cardlist";
				}else{
					alert(result.message);
				}
				
			},
			error : function(error) {
				alert(error.responseText);
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
	<aside style="display:none;" class="dialog">
		<span>
		<img height="80" width="80" src="${ctx}/static/edu/images/loading.gif" alt="loading" style="margin-top: 23px;"/>
		</span>
		<h4 style="font-size:20px;">请稍候...</h4>
	</aside>
	<form action="${ctx}/admin/card/createUsercard" method="post" id="addCardForm">
		<input type="hidden" name="courseIds" id="courseIdHidden" /> 
		<input type="hidden" name="card.type" id="cardType" value="3"/> 
		<input type="hidden" name="card.courseName" id="cardCourseName" />

		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>&nbsp;<span>线下卡管理</span> &gt; <span>新建学员卡</span>
			</h4>
		</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>学员卡基本属性<tt class="c_666 ml20 fsize12">
										（<font color="red">*</font>&nbsp;为必填项）
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;学员卡名称</td>
							<td><input type="text" name="card.name" id="cardTitle" class="{required:true}" /></td>
						</tr>
						<tr >
							<td align="center"><font color="red">*</font>&nbsp;学员卡金额</td>
							<td><input type="text" id="cardMoney" name="card.money" class="{required:true,number:true}" />&nbsp;&nbsp;<font color="red"></font></td>
						</tr>
						<tr id="courseTr">
							<td align="center">&nbsp;</td>
							<td><span style="float: left;" id="coursestr"> <a href="javascript:void(0)" onclick="addCourse()" style="cursor: pointer;" class="btn btn-danger">添加课程</a>&nbsp;&nbsp; <a href="javascript:void(0)" style="cursor: pointer;" onclick="clearcourse()" class="btn btn-danger">清空</a>
							</span></td>
						</tr>

						<tr >
							<td align="center"><font color="red">*</font>&nbsp;学员卡编码数量</td>
							<td><input type="text" name="card.num" id="totalNum" class="{required:true,number:true,min:0,max:1000}" />&nbsp;&nbsp;<font color="red">*必须是数字，数字不得大于2000</font></td>
						</tr>
                        <tr >
                            <td align="center"><font color="red">*</font>&nbsp;学员卡邮箱格式设置</td>
                            <td><input type="text" name="card.emailPrefix" id="emailPrefix" class="{required:true}" maxlength="3" style="width:30px" />* * * * * * * * @<input type="text" name="card.emailSuffix" id="emailSuffix" class="{required:true}" style="width:50px"/>.com&nbsp;&nbsp;<font color="red">*请输入生成邮箱的前缀和后缀，生成的学员卡将由此规则生成账号邮箱</font></td>
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
