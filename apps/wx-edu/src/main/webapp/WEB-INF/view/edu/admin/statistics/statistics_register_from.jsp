<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>注册来源统计图</title>
<script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js"></script>
<script type="text/javascript" language="javascript">
    $(function(){
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        option = {
            title : {
                text: '注册来源统计图',
                subtext: '注册来源统计图',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['注册用户数量:${userFromStatistics.registerNum}','后台开通用户数量:${userFromStatistics.adminNum}','课程卡用户数量:${userFromStatistics.userCardNum}',
                    'app注册用户数量:${userFromStatistics.appNum}','微站注册用户数量:${userFromStatistics.mobileNum}']
            },
            series : [
                {
                    name: '注册来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:${userFromStatistics.registerNum}, name:'注册用户数量:${userFromStatistics.registerNum}'},
                        {value:${userFromStatistics.adminNum}, name:'后台开通用户数量:${userFromStatistics.adminNum}'},
                        {value:${userFromStatistics.userCardNum}, name:'课程卡用户数量:${userFromStatistics.userCardNum}'},

                        {value:${userFromStatistics.appNum}, name:'app注册用户数量:${userFromStatistics.appNum}'},
                        {value:${userFromStatistics.mobileNum}, name:'微站注册用户数量:${userFromStatistics.mobileNum}'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        var obj = document.getElementById("nowYear");
        var date = new Date();
        for (var i = 0; i < 5; i++) {
            var opt = document.createElement("option");
            opt.value = date.getFullYear() - i;
            opt.innerHTML = (date.getFullYear() - i)+" 年";
            obj.appendChild(opt);
        }
        search();
    });

    function search(){
        var year = $("#nowYear").val();
        var month = $("#nowMonth").val();
        $.ajax({
            url:'${ctx}/admin/ajax/ajaxRegisterFromTable',
            type:'post',
            data:{'year':year,'month':month},
            dataType:'text',
            success:function(result){
                $("#container").html(result);
            }
        });
    }
</script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">统计管理</strong> / <small>注册来源统计图</small></div>
</div>
<hr>
<div id="main" style="width: 1000px;height:500px;"></div>
<div class="mt20">
    <div  class="am-tabs">
        <div class="am-tabs-bd">
            <div id="tab1" class="am-tab-panel am-fade am-active am-in">
                <form action="" class="am-form" data-am-validator>
                    <div class="am-g am-margin-top">
                        <div class="am-u-sm-4 am-u-md-2 am-text-right">时间:</div>
                        <div class="am-u-sm-8 am-u-md-10">
                            <select data-am-selected="{btnSize: 'sm'}" style="display: none;" id="nowYear" name="year">

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
                            <button type="button" onclick="search()" class="am-btn am-btn-warning">
                                <span class="am-icon-search"></span> 查询
                            </button>
                        </div>
                    </div>
                    <div class="am-g am-margin-top am-form-group">
                        <div id="container" style="max-width:  96%;margin: auto;"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>


