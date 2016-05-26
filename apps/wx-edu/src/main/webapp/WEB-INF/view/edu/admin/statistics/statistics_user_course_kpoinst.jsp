<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>播放统计图</title>
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
		var strs ='${data}'.split("|");
		var date = strs[0].split(",");
		var playcount = strs[1].split(",");
		var playernum = strs[2].split(",");
		initLineEcharts("container","课程播放走势图","播放次数","观看人数",date,playcount,playernum);
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
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>课程播放统计图</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/statistics/userCourseKpoint" class="am-form" data-am-validator>
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
					<th class="table-title am-text-center" width="34%">时间</th>
					<th class="table-type am-text-center" width="33%">播放次数</th>
					<th class="table-type am-text-center" width="33%">观看人数</th>
				</tr>
				</thead>
				<tbody>
				<c:set var="t_playCount" value="0"></c:set>
				<c:set var="t_playerNum" value="0"></c:set>
				<c:forEach items="${statistics }" var="statistic" varStatus="index">
					<c:set var="t_playCount" value="${t_playCount+statistic.playCount}"></c:set>
					<c:set var="t_playerNum" value="${t_playerNum+statistic.playerNum}"></c:set>
					<tr align="center">
						<td>${year}年
							<c:if test="${month==null||month=='' }">${statistics.size()-index.index }月</c:if>
							<c:if test="${month!=null&&month!='' }">${month }月${statistics.size()-index.index }日</c:if>
						</td>
						<td>${statistic.playCount}</td>
						<td>${statistic.playerNum}</td>
					</tr>
				</c:forEach>
				<tr align="center">
					<td >合计 </td>
					<td>${t_playCount}</td>
					<td>${t_playerNum}</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
</body>
</html>
