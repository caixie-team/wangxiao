<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>订单统计图</title>
	<script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js"></script>
<script type="text/javascript" language="javascript">
	$(function() {
		var obj = document.getElementById("nowYear");
		var date = new Date();
		for (var i = 0; i < 5; i++) {
			var opt = document.createElement("option");
			opt.value = date.getFullYear() - i;
			opt.innerHTML = (date.getFullYear() - i)+" 年";
			obj.appendChild(opt);
		}
		if('${year}'!=null){
			$("#nowYear").val('${year}');
		}
		if('${month}'!=null){
			$("#nowMonth").val('${month}');
		}
		var strs ='${chart}'.split("|");
		var dateTime = strs[0].split(",");
		var allOrders = strs[1].split(",");
		var payOrders = strs[2].split(",");
		initLineEcharts("container","订单信息走势图","下单量","成交量",dateTime,allOrders,payOrders);
	});

	// 生成折线图
	function initLineEcharts(id,title,text1,text2,xlist,vlist1,vlist2) {
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById(id));
		var option = {
			title : {
				text: title,
				x: "center"
			},
			tooltip : {
				trigger: 'axis'
			},
			legend:{
				data:[text1,text2],
				x: "left"
			},
			calculable : true,
			xAxis : [
				{
					type : 'category',
					boundaryGap : false,
					data : xlist
				}
			],
			yAxis : [
				{
					type : 'value',
					axisLabel : {
						formatter: '{value}'
					}
				}
			],
			series : [
				{
					name:text1,
					type:'line',
					data:vlist1,
					markPoint : {
						data : [
							{type : 'max', name: '最大值'},
							{type : 'min', name: '最小值'}
						]
					},
					markLine : {
						data : [
							{type : 'average', name : '平均值'}
						]
					}
				},
				{
					name:text2,
					type:'line',
					data:vlist2,
					markPoint : {
						data : [
							{type : 'max', name: '最大值'},
							{type : 'min', name: '最小值'}
						]
					},
					markLine : {
						data : [
							{type : 'average', name : '平均值'}
						]
					}
				}
			]
		};
		myChart.setOption(option);
	}
</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>学员订单量统计图</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/statistics/order/course" class="am-form" data-am-validator>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">时间:</div>
						<div class="am-u-sm-8 am-u-md-10">
							<select data-am-selected="{btnSize: 'sm'}" style="display: none;" id="nowYear" name="year">
								<option value=""></option>
							</select>
							<select data-am-selected="{btnSize: 'sm',maxHeight: 150}" style="display: none;" id="nowMonth" name="month">
								<option value="">月份</option>
								<option value="1">1月</option>
								<option value="2">2月</option>
								<option value="3">3月</option>
								<option value="4">4月</option>
								<option value="5">5月</option>
								<option value="6">6月</option>
								<option value="7">7月</option>
								<option value="8">8月</option>
								<option value="9">9月</option>
								<option value="10">10月</option>
								<option value="11">11月</option>
								<option value="12">12月</option>
							</select>
							<button type="submit" class="am-btn am-btn-warning">
								<span class="am-icon-search"></span> 查询
							</button>
						</div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div id="container" style="max-width:  96%;margin: auto;height: 240px"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="am-g">
	<div class="mt20">
		<form class="am-form">
			<table class="am-table am-table-striped am-table-hover table-main">
				<thead>
				<tr>
					<th class="table-title am-text-center" width="25%">时间</th>
					<th class="table-title am-text-center" width="25%">下单量</th>
					<th class="table-title am-text-center" width="25%">成交量</th>
					<th class="table-title am-text-center" width="25%">实付金额</th>
				</tr>
				</thead>
				<tbody>
				<c:set var="t_courseNum" value="0"></c:set>
				<c:set var="t_coursePayNum" value="0"></c:set>
				<c:set var="t_coursePayAmount" value="0"></c:set>
				<c:forEach items="${statistics }" var="statistic" varStatus="index">
					<c:set var="t_courseNum" value="${t_courseNum+statistic.courseNum}"></c:set>
					<c:set var="t_coursePayNum" value="${t_coursePayNum+statistic.coursePayNum}"></c:set>
					<c:set var="t_coursePayAmount" value="${t_coursePayAmount+statistic.coursePayAmount}"></c:set>
					<tr align="center">
						<td>${year}年
							<c:if test="${month==null||month=='' }">${statistics.size()-index.index }月</c:if>
							<c:if test="${month!=null&&month!='' }">${month }月${statistics.size()-index.index }日</c:if>
						</td>
						<td>${statistic.courseNum}</td>
						<td>${statistic.coursePayNum}</td>
						<td>${statistic.coursePayAmount}</td>
					</tr>
				</c:forEach>
				<tr align="center">
					<td >合计 </td>
					<td>${t_courseNum}</td>
					<td>${t_coursePayNum}</td>
					<td>${t_coursePayAmount}</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>
