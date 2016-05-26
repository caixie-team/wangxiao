<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>会员详情</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function(){
		$( "#loseTime" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeYear: true,
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
	})
		 
	/**
	 * 会员进行延期
	 * @param orderState detailState
	 */
	function delayCourse(em) {
			$("#hideSpan").css("display", "block");
			$(em).hide();
	}

	function clickStopTIme() {
		var stopTime = document.getElementById('loseTime').value;
		if (stopTime == "") {
			dialogFun("会员开通记录详情 ","延期时间不能为空",0);
			return false;
		}
		var daoqishijian = document.getElementById('daoqishijian').value;
		if (stopTime < daoqishijian) {
			dialogFun("会员开通记录详情 ","延期时间必须大于到期时间",0);
			return false;
		}
		var detailId = '${memberRecordDTO.id}';
		if (detailId == null || detailId == '') {
			dialogFun("会员开通记录详情 ","延会员信息错误",0);
			return false;
		}
		$.ajax({
			url : "${ctx}/admin/member/updatemrecord",
			data : {
				"memberRecordDTO.id" : detailId,
				"memberRecordDTO.endDate" : stopTime
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(result) {
				if (result.success == true) {
					$("#hideSpan").hide();
					$(".delaybtn").show();
					dialogFun("会员开通记录详情 ","已延期",5,"");
				}
			},
			error : function(error) {
				dialogFun("会员开通记录详情 ","error",6,"");
			}

		});

	}
	function clickStopTImequxiao(obj){
		$("#hideSpan").hide();
		$("#delay").show();
		$( "#loseTime" ).val("");
	}
</script>

</head>
<body>
 <input type="hidden" id="daoqishijian" value="<fmt:formatDate value="${memberRecordDTO.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">会员开通记录管理</strong> / <small>会员开通记录详情</small>
		</div>
	</div>
	<hr/>
	<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
		<div class="am-tab-panel am-fade am-active am-in">
		<form class="am-form">
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;用户邮箱 </div>
			<div class="am-u-sm-8 am-u-md-4">
				<input type="text" value="${memberRecordDTO.email}" class="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;会员类型 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${memberRecordDTO.memberTitle}" class="am-input-sm" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		
		<div class="am-g am-margin-top am-form-group">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;过期时间 </div>
				<div class="am-u-sm-8 am-u-md-4">
					<input type="text" value="<fmt:formatDate value="${memberRecordDTO.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm"/>
					<button class="am-btn am-btn-secondary" id="delay" type="button" onclick="delayCourse(this)" name="delay">延期</button>
						<div id="hideSpan" style="display: none">
							<font>过期时间:</font>
						    <input  readonly="readonly" id="loseTime" type="text" name="queryTrxorderDetail.authTime" value="" class="am-input-sm" />
						    <button class="am-btn am-btn-danger" type="submit" onclick="clickStopTIme()">提交</button>
						    &nbsp;
						    <button class="am-btn am-btn-success" type="submit" onclick="clickStopTImequxiao(this)">取消</button>
						</div>
				</div>
			<div class="am-hide-sm-only am-u-md-6"></div>
		</div>
		
		
		
		
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;开通时间 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="<fmt:formatDate value="${memberRecordDTO.beginDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />" class="am-input-sm"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp; </div>
			<div class="am-u-sm-8 am-u-md-4">
			<button class="am-btn am-btn-primary am-dropdown-toggle" type="button" onclick="history.go(-1)">
			<span class="am-icon-mail-reply"></span>
			返回
			</button>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		</form>
		</div>
</div>
</div>
</div>
</body>
</html>
