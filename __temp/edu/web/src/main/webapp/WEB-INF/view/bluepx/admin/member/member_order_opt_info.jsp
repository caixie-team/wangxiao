<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水详情</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function(){
		$( "#loseTime" ).datetimepicker(
	    		{regional:"zh-CN",
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
			alert("延期时间不能为空！");
			return false;
		}
		var daoqishijian = document.getElementById('daoqishijian').value;
		if (stopTime < daoqishijian) {
			alert('延期时间必须大于到期时间！');
			return false;
		}
		var detailId = '${memberRecordDTO.id}';
		if (detailId == null || detailId == '') {
			alert("会员信息错误");
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
					alert("已延期");
					$("#hideSpan").hide();
					$(".delaybtn").show();
					window.location.reload();
				}
			},
			error : function(error) {
				alert("error");
			}

		});

	}
	function clickStopTImequxiao(obj){
		$("#hideSpan").hide();
		$(".delaybtn").show();
		$( "#loseTime" ).val("");
	}
</script>

</head>
<body>
		 <input type="hidden" id="daoqishijian" value="<fmt:formatDate value="${memberRecordDTO.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>会员开通记录管理</span> &gt; <span>会员开通记录详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
							<th align="left" colspan="2"><span>开通记录信息<tt class="c_666 ml20 fsize12" ></tt>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;用户邮箱</td>
							<td>${memberRecordDTO.email}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;会员类型</td>
							<td>${memberRecordDTO.memberTitle}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;过期时间</td>
							<td><fmt:formatDate value="${memberRecordDTO.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp; <input class="btn btn-danger delaybtn" type="button" name="delay" onclick="delayCourse(this)" value="延期" />
								<dd id="hideSpan" style="display: none">
									<font>过期时间:</font> <input  readonly="readonly" id="loseTime" type="text" name="queryTrxorderDetail.authTime" value="" style="width: 140px;" /> <a href="####" onclick="clickStopTIme()">提交</a> &nbsp;&nbsp; <a href="####" onclick="clickStopTImequxiao(this)">取消</a>
								</dd></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;开通时间</td>
							<td><fmt:formatDate value="${memberRecordDTO.beginDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2">
							<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
</body>
</html>
