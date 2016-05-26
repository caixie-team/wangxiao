<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
<script type="text/javascript">
	function doSubmit(){
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var operate=$("#operate").val();
		var url='';
		if(operate==0){
			dialogFun("网站统计操作","请选择操作",0);
			return;
		}else if(operate==1){
			url='/admin/statistics/create/batch';
		}else if(operate==2){
			url='/admin/statistics/del/batch';
		}
		if(startTime==null||startTime==''){
			dialogFun("网站统计操作","请输入开始日期",0);

			return;
		}
		if(endTime==null||endTime==''){
			dialogFun("网站统计操作","请输入结束日期",0);
			return;
		}
		var begin=new Date($("#startTime").val().replace(/-/g,"/"));
	    var end=new Date($("#endTime").val().replace(/-/g,"/"));
		if(begin>end){
			
			dialogFun("网站统计操作","结束日期必须选择开始日期之后的日期",0);
			return;
		}
		var date = new Date();
		date.setDate(date.getDate()-1);
		if(date<end){
			dialogFun("网站统计操作","结束日期不能选择当前时间之前的时间",0);
			
			return;
		}
		$.ajax({
			url:'${ctx}'+url,
			type:"post",
			data:{"startTime":startTime,"endTime":endTime},
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					
					dialogFun("网站统计操作","请输入结束日期",0);
				}else if (result.message=='exists'){
					
					dialogFun("网站统计操作","请输入结束日期",0);
				}
			}
		})
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">网站统计管理</strong> / <small>网站统计操作</small></div>
</div>
<hr>

<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">网站统计基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form">
					<div class="am-g am-margin-top">
						<label for="operate" class="am-u-sm-4 am-u-md-2 am-text-right">
							操作
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<select data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}" id="operate">
								<option value="0">--请选择--</option>
								<option value="1">生成统计</option>
								<option value="2">删除统计</option>
							</select>
							<span class="ml10"></span>
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必选</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="startTime" class="am-u-sm-4 am-u-md-2 am-text-right">
							开始日期
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" readonly="readonly" data-am-datepicker id="startTime" class="am-input-sm" >
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必选</div>
					</div>

					<div class="am-g am-margin-top am-form-group">
						<label for="endTime" class="am-u-sm-4 am-u-md-2 am-text-right">
							结束日期
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" readonly="readonly" data-am-datepicker id="endTime" class="am-input-sm" >
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必选</div>
					</div>


					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="button" onclick="doSubmit()">执行</button>
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