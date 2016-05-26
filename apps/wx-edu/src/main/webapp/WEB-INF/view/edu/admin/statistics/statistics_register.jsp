<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员注册量统计图</title>
<script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js"></script>
<script type="text/javascript" language="javascript">
	$(function() {
		var obj = document.getElementById("nowYear");
		var date = new Date();
		for (var i = 0; i < 5; i++) {
			var opt = document.createElement("option");
			opt.value = date.getFullYear() - i;
			opt.innerHTML = (date.getFullYear() - i)+" 年";;
			obj.appendChild(opt);
		}
		if('${year}'!=null){
			$("#nowYear").val('${year}');
		}
		if('${month}'!=null){
			$("#nowMonth").val('${month}');
		}
        var strs = '${chart}'.split("|");
        var dateTime = strs[0].split(",");
        var studentNum = strs[1].split(",");
        initLineEcharts("container","学员注册走势图","学员",dateTime,studentNum);
	});
	function parseDay(val){
		if(val<10){
			return '0'+val;
		}else{
			return val;
		}
	}

    // 生成折线图
    function initLineEcharts(id,title,text,xlist,vlist) {
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
                data:[text],
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
                    name:text,
                    type:'line',
                    data:vlist,
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
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>学员注册量统计图</small></div>
</div>
<hr>
<div class="mt20">
    <div  class="am-tabs">
        <div class="am-tabs-bd">
            <div id="tab1" class="am-tab-panel am-fade am-active am-in">
                <form action="${ctx}/admin/statistics/register" class="am-form" data-am-validator>
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
                                <span class="am-icon-search"></span> 搜索
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
                    <th class="table-title am-text-center" width="50%">时间</th>
                    <th class="table-type am-text-center" width="50%">注册人数</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="t_registerNum" value="0"></c:set>
                <c:forEach items="${statistics }" var="statistic" varStatus="index">
                    <c:set var="t_registerNum" value="${t_registerNum+statistic.registerNum}"></c:set>
                    <tr align="center">
                        <td width="50%">${year}年
                            <c:if test="${month==null||month=='' }">${statistics.size()-index.index }月</c:if>
                            <c:if test="${month!=null&&month!='' }">${month }月${statistics.size()-index.index }日</c:if>
                        </td>
                        <td width="50%">${statistic.registerNum}</td>
                    </tr>
                </c:forEach>
                <tr align="center">
                    <td >合计 </td>
                    <td>${t_registerNum}</td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>


