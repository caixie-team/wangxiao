<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新建礼品</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=${v}"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//上传图片
			initUploadify("fileupload","subjcetpic","imagesUrl","fileQueue","gift");
		});
		var giftType=1;//默认积分礼品
		function addArticleFormSubmit(){
			if($("#giftName").val()==''){
				 dialogFun("添加礼品","请填写礼品名称",0);
				return ;
			 }
			if($("#score").val()==''){
			   dialogFun("添加礼品","请填写使用积分",0);
				return ;
			 }
			if(!/^[0-9]*$/.test($("#score").val())){//验证数字
				dialogFun("添加礼品","请输入数字",0);
				return;
			}
			if(giftType==1){
				if($("#imagesUrl").val()==''){
					 dialogFun("添加礼品","请上传礼品图片",0);
					return ;
				 }
				if($("#ArticleContent").val()==''){
					 dialogFun("添加礼品","请填写内容",0);
					return ;
				}
			}else{
				if($("#courseId").val()==0){
					dialogFun("添加礼品","请选择课程",0);
					return;
				 }
			}
			submitForm();
		}
		function submitForm(){
			$("#addIntegralForm").submit();
		}

		function chooseGift(id){
			if(id==1){//积分礼品
				$(".courseGift").show();
				$("#addCourse").hide();
				$("#addCourse").prev("input").removeAttr("readonly");
				$("#courseId").val(0);
				$("#giftName").val("");
				$("#score").val("");
				giftType=1;
			}else{
				$(".courseGift").hide();
				$("#addCourse").show();//展示添加课程标签
				$("#addCourse").prev("input").attr("readonly","readonly");
				$("#giftName").val("");
				$("#score").val("");
				giftType=2;
			}
		}
		function addCourse(){
			window.open('${ctx}/admin/cou/openerlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
		}
		function openerCourse(courseArray){
			$("#courseId").val(courseArray[0]);
			$("#giftName").val(courseArray[1]);
			var str = "";
			str+='<div class="am-panel am-panel-default admin-sidebar-panel"><div class="am-panel-bd">';
			str+='<button class="am-btn am-btn-default ml10 mt10" onclick="cleanCourse()" type="button">'+courseArray[1]+'<a onclick="cleanCourse()" class="am-close ml5" title="关闭" href="javascript:void(0)">&times;</a></button>';
			str+='</div></div>';
			$("#uchtml").html(str);
		}
		function cleanCourse(){
			$("#courseId").val(0);
			$("#uchtml").html("");
		}
	</script>
</head>
<body >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">礼品管理</strong> / <small>新建礼品</small>
		</div>
	</div>
	<hr/>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息(<span class="am-text-danger">*</span>为必填项)</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div class="am-tab-panel am-fade am-active am-in">
					<form action="${ctx}/admin/user/addgift" method="post" id="addIntegralForm" class="am-form" >
						<input type="hidden" name="userIntegralGift.logo" id="imagesUrl"  />
						<input type="hidden" name="userIntegralGift.courseId" id="courseId"  value="0"/>

						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><span class="am-text-danger">*</span>&nbsp;礼品类型</div>
							<div class="am-u-sm-8 am-u-md-6">
								<select name="userIntegralGift.flag" data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}" onchange="chooseGift(this.value)" id="giftType">
									<option value="1">积分礼品</option>
									<option value="2">课程礼品</option>
								</select>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><span class="am-text-danger">*</span>&nbsp;积分礼品名称</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="text" name="userIntegralGift.name" class="am-input-sm"  id="giftName"/>
								<a class="am-btn am-btn-danger" title="提 交" href="javascript:addCourse()">添加课程</a>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top undis" id="addCourse">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><span class="am-text-danger">*</span>&nbsp;积分礼品名称</div>
							<div class="am-u-sm-8 am-u-md-6">
								<div id="uchtml"></div>

								<a href="javascript:clearCourse()" title="清空" class="am-btn am-btn-primary">清空</a>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><span class="am-text-danger">*</span>&nbsp;使用积分</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="number" name="userIntegralGift.score" class="am-input-sm" id="score"/>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><span class="am-text-danger">*</span>&nbsp;礼品图片</div>
							<div class="am-u-sm-8 am-u-md-6">
								<img src="<%=imagesPath %>/static/edu/images/NotAvailable.png" alt="" id="subjcetpic" width="178px" height="133px" class="am-input-sm" />
								<div id="fileQueue" style="margin-top: 30px; border: 0px;"></div>
								<span class="am-text-danger">图片链接，支持JPG、PNG格式，尺寸（宽178像素，高133像素）小于256kb</span>
							</div>
							<div class="am-hide-sm-only am-u-md-4">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-6">
								<input type="file" id="fileupload" class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">详细内容</div>
							<div class="am-u-sm-8 am-u-md-6">
								<textarea rows="5" cols="20" name="userIntegralGift.content" id="integralContent" class="am-input-sm" ></textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-6">
								<button type="button" class="am-btn am-btn-danger" onclick="addArticleFormSubmit()">提交</button>
								<button type="button" class="am-btn am-btn-success" title="返 回" onclick="history.go(-1);">返 回</button>
							</div>
							<div class="am-hide-sm-only am-u-md-4 am-text-danger">&nbsp;</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
