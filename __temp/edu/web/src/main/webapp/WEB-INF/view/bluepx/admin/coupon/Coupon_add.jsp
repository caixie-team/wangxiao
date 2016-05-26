<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>优惠券添加</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
<script type="text/javascript">
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
		hideSubjectMenu();
		$("#subjectId").val(treeNode.subjectId);
		$("#subjectNameBtn").val(treeNode.subjectName);
	}
	
	//清空专业的查询条件时同时清除考点
	function subject_cleantreevalue(){
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
	}
	
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
	});
	$(function(){
		  $( "#startTime,#endTime" ).datepicker(
		    		{regional:"zh-CN",
		    		changeMonth: true,
		    		dateFormat:"yy-mm-dd "
		    		});
		
	});
	$(function(){
		changeType($('input[name="type"]:checked').val());//初始化优惠券类型
		changeRange($('input[name="range"]:checked').val());//初始化优惠券限制范围
		changeUseType($('input[name="useType"]:checked').val());//初始化次数限制
	});
	function changeType(val){
		$('input[name="range"]:eq(0)').prop("checked",true);
		changeRange(1);
		if(val==1){
			$("#rangeTr").show();
			$("#reduceTr").css("display","none");
			$("#preferentialTr").css("display","");
			$("#reduceInput").attr("name","");
			$("#preferentialSelect").attr("name","coupon.amount");
		}else if(val==2){
			$("#rangeTr").show();
			$("#preferentialTr").css("display","none");
			$("#reduceTr").css("display","");
			$("#preferentialSelect").attr("name","");
			$("#reduceInput").attr("name","coupon.amount");
		}else if(val==3){//会员定额券无范围限制
			$("#rangeTr").hide();
			$("#preferentialTr").css("display","none");
			$("#reduceTr").css("display","");
			$("#preferentialSelect").attr("name","");
			$("#reduceInput").attr("name","coupon.amount");
		}
	}
	function changeUseType(val){
		if(val==2){
			$("#totalNumTr").css("display","");
			$("#totalNum").attr("name","coupon.totalNum");
		}else{
			$("#totalNumTr").css("display","none");
			$("#totalNum").attr("name","");
		}
	}
	function changeRange(val){
		if(val==1){//所有范围
			subject_cleantreevalue();//清空专业
			$("#courseIdHidden").val("");
			$("#limitSubject").css("display","none");
			$("#limitCourse").css("display","none");
			$("#subjectSelect").val(-1);
		}else if(val==2){//单选项目
			$("#courseIdHidden").val("");
			$("#limitSubject").css("display","");
			$("#limitCourse").css("display","none");
		}else if(val==3){//多选课程
			subject_cleantreevalue();//清空专业
			$("#limitSubject").css("display","none");
			$("#limitCourse").css("display","");
			$("#subjectSelect").val(-1);
		}
	}
	
	function addCourse(){
		window.open('${cxt}/admin/cou/couponCourseList?page.currentPage=1',
				+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
			);
	}
	function getCourseList(CourseList){
		//p对象的name获取已存在的课程集合，去除提交过来的重复对象
		$("p[name='courseName']").each(function(i,val){
			for(var j=0;j<CourseList.length;j++){
				var id = CourseList[j];
				if(val.id=='coursespan'+id[0]){
					CourseList.splice(j,1);
				}
			}
		});
		//插入到表格中
		coursePAdd(CourseList);
	}	
	
	function coursePAdd(myArray){
	 	var str="";
	    var courseIds=$("#courseIdHidden").val();
	 	for(var i=0;i<myArray.length;i++){
	 	var arr=myArray[i];
	 	str+="<p style='width:100%;margin: 0 0 0em' id='coursespan"+arr[0]+"' name='courseName' value='"+arr[0]+"' title='"+arr[1]+"'>"+arr[1]+"&nbsp;&nbsp;<a href='javascript:void(0)' class='btn btn-danger' onclick=delcourse('"+arr[0]+"','"+arr[1]+"')>删除</a></p>";
	 	courseIds+=arr[0]+",";
	 	}
	 	$("#coursestr").prepend(str);
		$("#courseIdHidden").val(courseIds);
	}
	function delcourse(courseId,title){
		if(confirm("确定要删除【"+title+"】")){
			$("#coursespan"+courseId).remove();
			var ids= $("#courseIdHidden").val();
			ids=ids.replace(courseId+",","");
			$("#courseIdHidden").val(ids);
		};
	}
	function clearcourse()
	{
		$("#courseIdHidden").val("");
		$("#coursestr>p").remove();
	}
	function addSubmit(){
		//验证优惠券名称
		if($("#couponTitle").val()==null||$("#couponTitle").val()==''){
			alert("优惠券名称");
			return;
		}
		//验证优惠券使用限额
		if($("#limitAmount").val()==null||$("#limitAmount").val()==''){
			alert("请输入优惠券使用限额");
			return;
		}
		//验证优惠券类型
		var type=$('input[name="type"]:checked').val();
		if(type==1){
			if($("#preferentialSelect").val()<0){
				alert("请选择优惠折扣");
				return;
			}
		}else{
			if($("#reduceInput").val()==null||$("#reduceInput").val()==''){
				alert("请输入优惠金额");
				return;
			}
			if(parseFloat($("#limitAmount").val())<=parseFloat($("#reduceInput").val())){
				alert("使用限额必须大于优惠金额");
				return;
			}
		}
		
		//验证优惠券限制范围
		var range=$('input[name="range"]:checked').val();
		if(range==2){//单选项目
			if($("#subjectId").val()==null||$("#subjectId").val()==0){
				alert("请选择优惠券限制项目");
				return;
			}
		}else if(range==3){//多选课程
			if($("#courseIdHidden").val()==null||$("#courseIdHidden").val()==''){
				alert("请选择优惠券限制课程");
				return;
			}
		}
		//优惠券使用开始至截至日期
		if($("#startTime").val()==null||$("#startTime").val()==''){
			alert("请选择使用起始日期");
			return;
		}
		
		if($("#endTime").val()==null||$("#endTime").val()==''){
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
		var useType=$('input[name="useType"]:checked').val();
		if(useType==2){//适用人群为个人时验证生成数量，所有人只生成一个优惠券编码
			if($("#totalNum").val()==null||$("#totalNum").val()==''){
				alert("请输入优惠券编码生成数量");
				return;
			}
			if(isNaN($("#totalNum").val())){
				alert("生成优惠券编码数量只能为数字");
				return;
			}
			if(parseInt($("#totalNum").val())>5000){
				alert("生成优惠券编码数量不能大于5000");
				return;
			}
			if(parseInt($("#totalNum").val())<=0){
				alert("生成优惠券编码数量不能小于0");
				return;
			}
		}
		$("#addCouponForm").submit();
	}
</script>
</head>
<body >
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券管理</span> &gt; <span>优惠券添加</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
			<form action="${ctx}/admin/coupon/add" method="post" id="addCouponForm">
			<input type="hidden" name="coupon.subjectId" id="subjectId" value="0"/>
			<input type="hidden" name="limitCourseId" id="courseIdHidden"/>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>优惠券基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券名称 </td>
						<td>
							<input type="text" name="coupon.title" id="couponTitle" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>优惠券类型</td>
						<td width="80%">
							<input type="radio" value="1" name="type" checked="checked" onclick="changeType(1)"/>折扣券&nbsp;&nbsp;
							<input type="radio" value="2" name="type" onclick="changeType(2)"/>定额券&nbsp;&nbsp;
							<input type="radio" value="3" name="type" onclick="changeType(3)"/>会员定额券&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*会员定额券只能在开会员时使用</font>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;次数限制</td>
						<td>
							<input type="radio" value="2" name="useType" checked="checked" onclick="changeUseType(2)"/>正常&nbsp;&nbsp;
							<input type="radio" value="1" name="useType" onclick="changeUseType(1)"/>无限&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*'无限'优惠券在有效期内可以被任意多人使用多次，'正常'优惠券使用一次后不能继续使用</font>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用限额</td>
						<td>
							<input type="text"  id="limitAmount" name="coupon.limitAmount" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;元以上&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入'300'</font>
						</td>
					</tr>
					<tr id="preferentialTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠折扣</td>
						<td>
							<select id="preferentialSelect">
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
							</select>&nbsp;&nbsp;折&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*该优惠券折扣额，如2折优惠券，则选择'2.0'</font>
						</td>
					</tr>
					<tr id="reduceTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠金额</td>
						<td>
							<input type="text"  id="reduceInput" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;<font color="red">*该优惠券金额，如5元优惠券，则请输入'5'</font>
						</td>
					</tr>
					<tr id="rangeTr">
						<td align="center"><font color="red">*</font>&nbsp;适用范围</td>
						<td>
							<input type="radio" value="1" name="range" checked="checked" onclick="changeRange(1)"/>所有课程&nbsp;&nbsp;
							<input type="radio" value="2" name="range" onclick="changeRange(2)"/>单选项目&nbsp;&nbsp;
							<input type="radio" value="3" name="range" onclick="changeRange(3)"/>多选课程
						</td>
					</tr>
					<tr id="limitSubject">
						<td align="center">&nbsp;</td>
						<td>
							<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showSubjectMenu()"/>
							<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
								<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
							</div>
						</td>
					</tr>
					<tr id="limitCourse">
						<td align="center">&nbsp;</td>
						<td>
							<span style="float: left;" id="coursestr">
	  							<a href="javascript:void(0)" onclick="addCourse()" style="cursor: pointer;" class="btn btn-danger">添加课程</a>&nbsp;&nbsp;
	  							<a href="javascript:void(0)" style="cursor: pointer;" onclick="clearcourse()" class="btn btn-danger">清空</a>
	  						</span>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用起始日期</td>
						<td>
							<input type="text" name="coupon.startTime" readonly="readonly" id="startTime" class="AS-inp"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用截止日期</td>
						<td>
							<input type="text" name="coupon.endTime" readonly="readonly" id="endTime" class="AS-inp"/>
						</td>
					</tr>
					<tr id="totalNumTr">
						<td align="center" ><font color="red">*</font>&nbsp;生成优惠编码数量</td>
						<td>
							<input type="text" name="coupon.totalNum" id="totalNum" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;<font color="red">*必须是数字，数字不得大于5000</font>
						</td>
					</tr>
					<tr>
						<td align="center">备注</td>
						<td>
							<textarea rows="3" cols="80" name="coupon.info" id="info"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">提 交</a>
							<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			</div>
		</div>
		<!-- /tab4 end -->
		
</body>
</html>
