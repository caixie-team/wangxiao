<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>网站概况统计图</title>
<script type="text/javascript" src="${ctximg}/static/edu/js//highChart/highcharts.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
    <script type="text/javascript" src="${ctximg}/static/common/admin/js/jquery.epiclock.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js" charset="UTF-8"></script>
<script type="text/javascript" language="javascript">
	Date.prototype.Format = function(fmt)
	{ //author: meizz
		var o = {
			"M+" : this.getMonth()+1,                 //月份
			"d+" : this.getDate(),                    //日
			"h+" : this.getHours(),                   //小时
			"m+" : this.getMinutes(),                 //分
			"s+" : this.getSeconds(),                 //秒
			"q+" : Math.floor((this.getMonth()+3)/3), //季度
			"S"  : this.getMilliseconds()             //毫秒
		};
		if(/(y+)/.test(fmt))
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		for(var k in o)
			if(new RegExp("("+ k +")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		return fmt;
	}

	$(function(){
    	aEffect(); //animation fun 
        $('.epiClock').epiclock({ format : ' Y年F月j日　G : i : s　D' });
        $.epiclock();
    });

	function echartsMember(name,value){
		obj = new Object();
		obj.name = name;
		obj.value = value;
		return obj;
	}

	$(function() {
		// 课程订单量
		var list = new Array();
		list.push(echartsMember("支付成功订单",${statistics.coursePayNum}));
		list.push(echartsMember("未支付订单",${statistics.courseNum-statistics.coursePayNum}));
		initPieEcharts("coursePay","课程订单量",list);

		// 课程购买人数
		list = new Array();
		list.push(echartsMember("购买课程",${statistics.userPayCourseNum}));
		list.push(echartsMember("未购买课程",${statistics.registerNum-statistics.userPayCourseNum}));
		initPieEcharts("courseOrder","课程购买人数",list);

		// 会员订单
		list = new Array();
		list.push(echartsMember("会员支付订单",${statistics.memberPayNum}));
		list.push(echartsMember("会员未支付订单",${statistics.memberNum-statistics.memberPayNum}));
		initPieEcharts("memberOrder","会员订单",list);

		// 会员开通人数
		list = new Array();
		list.push(echartsMember("开通会员人数",${statistics.userPayMemberNum}));
		list.push(echartsMember("未开通会员",${statistics.registerNum-statistics.userPayMemberNum}));
		initPieEcharts("memberPay","会员开通人数",list);

		drawCartogramLogin(15);
		drawCartogram_order();
		drawCartogram_user();
	});
	// 生成饼状图
	function initPieEcharts(id,title,list){
		var titleArr = getTitle(list);
		var dataArr = getData(list);
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById(id));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text: "",
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				x: 'left',
				data: titleArr
			},
			series : [
				{
					name: title,
					type: 'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:dataArr,
					itemStyle: {
						emphasis: {
							shadowBlur: 20,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}
			]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
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

	// 获取标题
	function getTitle(list){
		var html = '[';
		for(var i=0;i<list.length;i++){
			if(i==0){
				html+="'"+list[i].name+"'";
			}else{
				html+=",'"+list[i].name+"'";
			}
		}
		html+="]";
		html = eval(html);
		return html;
	}
	// 获取数据
	function getData(list){
		var html = '[';
		for(var i=0;i<list.length;i++){
			if(i==0){
				html+="{value:'"+list[i].value+"', name:'"+list[i].name+"'}";
			}else{
				html+=",{value:'"+list[i].value+"', name:'"+list[i].name+"'}";
			}
		}
		html+="]";
		html = eval(html);
		return html;
	}


	function aEffect() {
		var db = $(".dashBoradWrap"),
			dbu = db.children("ul"),
			dbli = dbu.children("li");
		db.addClass("animate-enter");
		dbli.addClass("animated scale originTopLeft");
	};

	//活跃度按天数
	function drawCartogramLogin(days) {
        var dateTime = '';
        var studentNum;
        $.ajax({
            url:"/admin/statistics/web/detailajax",
            type:"post",
            data:{"days":days,"type":"LOGIN_NUM"},
            dataType:"json",
            async:false,
            success:function(result){
                if(result.success){
                    dateTime=result.entity;
                    studentNum= result.message.split(",");
                }else{
                    alert("请求错误!");
                }
            }
        })
		initLineEcharts('container','最近'+days+'天活跃学员',"活跃人数",dateTime,studentNum);
	}

// 今天登录人数
function logintoday(){
	var myChart = echarts.init(document.getElementById("container"));
	var option = {
		title : {
			text: '今天活跃人数',
			x: "center"
		},
		tooltip : {
			trigger: 'axis'
		},
		calculable : true,
		xAxis : [
			{
				type : 'category',
				data : [new Date().Format("yyyy-MM-dd")]
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [
			{
				name:'活跃人数',
				type:'bar',
				data:[${todayloginnum}]
			},
		]
	};
	myChart.setOption(option);
}
function drawCartogram_user() {
	var dateTime = '';
	var studentNum =  "" ;
	$.ajax({
		url:"/admin/statistics/web/detailajax",
		type:"post",
		data:{"days":30,"type":"REGISTER_NUM"},
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success){
				dateTime=result.entity;
				studentNum=result.message.split(",");
			}else{
				alert("请求错误!");
			}
		}
	})
	initLineEcharts('container_user','注册学员',"学员",dateTime,studentNum);
}

function drawCartogram_order() {
	var dateTime = '';
	var studentNum =  "" ;
	$.ajax({
		url:"/admin/statistics/web/detailajax",
		type:"post",
		data:{"days":30,"type":"COURSE_NUM"},
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success){
				dateTime=result.entity;
				studentNum= result.message.split(",");
			}else{
				alert("请求错误!");
			}
		}
	})
	initLineEcharts('container_order','课程订单',"订单",dateTime,studentNum);
}


</script>
    <style>
        label.epiClock {
            display: inline;
        }


    </style>
</head>
<body >
			<!-- 内容 开始  -->
            <div class="page_head" style=" position: relative; padding: 10px 20px;">
                <h4><font><c:out value="${sysuser.userName}" /></font>&nbsp;欢迎您，今天是&nbsp;&nbsp;<span class="epiClock"></span> </h4>
            </div>

            <div>
            	<!-- 仪表盘 开始 -->
            	<div class="dashBoradBox">
	            	<div class="dashBoradWrap">
	            		<ul class="clearfix">
	            			<li>
	            				<div class="dash-box">
		            				<a href="javascript: void(0)" class="db-ico-1">
	            						<span class="db-ico">&nbsp;</span>
	            						<p class="db-txt-1">${websitemap.web.company}<br>后台管理平台</p>
	            					</a>
            					</div>
	            			</li>
	            			<li>
	            				<div class="dash-box">
		            				<a href="javascript: void(0)" class="db-ico-2">
	            						<span class="db-ico">&nbsp;</span>
	            						<p class="db-txt-2"><span class="">历史订单总数：</span><span class="db-num-big"><fmt:formatNumber pattern="#0" value="${courseOrderNum}"/></span></p>
	            						<p class="db-txt-3"><span>今日新增订单数：</span><tt>${todayOrderNum}</tt><span> 单</span></p>
	            					</a>
	            				</div>
	            			</li>
	            			<li>
		            			<div class="dash-box">
		            				<a href="javascript: void(0)" class="db-ico-3">
	            						<span class="db-ico">&nbsp;</span>
	            						<p class="db-txt-2"><span class="">网站课时总数：</span><span class="db-num-big">${kpointNum}</span></p>
	            						<p class="db-txt-3"><span>今日新增：</span><tt>${todayKpointNum }</tt><span> 课时</span></p>
	            					</a>
	            				</div>
	            			</li>
	            			<li>
		            			<div class="dash-box">
		            				<a href="javascript: void(0)" class="db-ico-4">
	            						<span class="db-ico">&nbsp;</span>
	            						<p class="db-txt-2"><span class="">历史注册会员数：</span><span class="db-num-big">${historyRegisterNum }</span></p>
	            						<p class="db-txt-3"><span>今日新增会员数：</span><tt>${todayRegisterNum }</tt><span> 位</span></p>
	            					</a>
	            				</div>
	            			</li>
	            			<li>
		            			<div class="dash-box">
		            				<a href="javascript: void(0)" class="db-ico-5">
	            						<span class="db-ico">&nbsp;</span>
	            						<p class="db-txt-2"><span class="">历史课程评论数：</span><span class="db-num-big">${historyAssesNum }</span></p>
	            						<p class="db-txt-3"><span>今日新增评论数：</span><tt>${todayAssesNum }</tt><span> 条</span></p>
	            					</a>
	            				</div>
	            			</li>
	            			<li>
	            				<div class="dash-box">
		            				<a href="javascript: void(0)" class="db-ico-6">
	            						<span class="db-ico">&nbsp;</span>
	            						<p class="db-txt-2"><span class="">历史互动答疑数：</span><span class="db-num-big">${historyQuesNum }</span></p>
	            						<p class="db-txt-3"><span>今日新增答疑数：</span><tt>${todayQuesNum }</tt><span> 条</span></p>
	            					</a>
	            				</div>
	            			</li>
	            		</ul>

	            	</div>
            	</div>
            	<!-- 仪表盘 结束 -->


            	 <div class="page_head">
                					<h4><em class="icon14 i_01"></em>学员活跃度走势
                					</h4>
				</div>
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<span>
									<button type="button" class="am-btn am-btn-primary" onclick="logintoday()">今天</button>
									<button type="button" class="am-btn am-btn-secondary" onclick="drawCartogramLogin(7)">最近7天</button>
									<button type="button" class="am-btn am-btn-primary" onclick="drawCartogramLogin(30)">最近30天</button>
								</span>
							</caption>
							<tbody >
							<tr align="center">
								<td>
									<div id="container" style="width: 85%;margin: auto;height: 260px;"></div>
								</td>
							</tr>
							</tbody>
						</table>
					</div><!-- /commonWrap -->
				</div>



            	<!-- 订单会员数据分析-->
            	 <div class="page_head">
						<h4><em class="icon14 i_01"></em>订单会员数据分析
						</h4>
				</div>
                <div class="commonWrap">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                        <caption></caption>
                        <thead>
                        <tr>
                            <th><span>课程订单量</span></th>
                            <th><span>课程购买人数</span></th>
                            <th><span>会员开通人数</span></th>
                            <th><span>会员订单</span></th>
                            <%--<th><span>账号来源</span></th>--%>
                        </tr>
                        </thead>

                        <tbody id="tabS_01">
                        <tr align="center">
                            <td>
                                <c:if test="${userPayCourseChart!=''}">
                                   <%-- margin: 0 20px;--%>
                                    <span id="coursePay" style="min-width: 210px; height: 220px; max-width: 600px; display: inline-block; "></span>
                                </c:if>
                                <c:if test="${userPayCourseChart==''}">
                                    <div class="tips">
                                        <span>暂无购买课程人数统计数据。</span>
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${courseOrderChart!=''}">
                                    <span id="courseOrder" style="min-width: 210px;height: 220px; max-width: 600px; margin: 0 20px;display: inline-block;  "></span>
                                </c:if>
                                <c:if test="${courseOrderChart==''}">
                                    <div class="tips">
                                        <span>暂无开通课程人数统计数据。</span>
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${userPayMemberChart!=''}">
                                    <span id="memberPay" style="min-width: 210px;height: 220px; max-width: 600px; margin: 0 20px;display: inline-block; "></span>
                                </c:if>
                                <c:if test="${userPayMemberChart==''}">
                                    <div class="tips">
                                        <span>暂无会员订单统计数据.</span>
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${memberOrderChart!=''}">
                                    <span id="memberOrder" style="min-width: 210px;height: 220px; max-width: 600px; margin: 0 20px;display: inline-block;  "></span>
                                </c:if>
                               <c:if test="${memberOrderChart==''}">
                                    <div class="tips">
                                        <span>暂无会员订单统计数据.</span>
                                    </div>
                                </c:if>
                            </td>
                            <%--<td>
                                <c:if test="${registerFromChar!=''}">
                                    <span id="registerFrom" style="min-width: 210px;height: 220px; max-width: 600px; margin: 0 20px;display: inline-block;  "></span>
                                </c:if>
                               <c:if test="${registerFromChar==''}">
                                    <div class="tips">
                                        <span>暂无会员注册来源统计数据.</span>
                                    </div>
                                </c:if>
                            </td>--%>
                        </tr>
                        </tbody>
                    </table>
                </div><!-- /commonWrap -->
            </div>
            <!-- /tab1 end-->

<!--社区活跃度走势 -->
<%--<c:if test="${defaultSnsKey == 'on'}">
<div class="mt20">
	<form action="${ctx}/admin/statistics/sns" name="searchForm" id="searchForm" method="post">
		<!-- 内容 开始  -->
		<div class="page_head">
			<h4>
				<em class="icon14 i_01"></em>社区活跃度走势
			</h4>
		</div>
		<!-- /tab1 begin-->
		<div class="mt20">
			<div class="commonWrap">
				<table cellspacing="0"  cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
					</caption>
					<thead>
					</thead>
					<tbody>
						<tr><td><div id="containersns"></div></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</div>
</c:if>--%>


 		<div class="page_head">
				<h4><em class="icon14 i_01"></em>学员注册下单走势
				</h4>
		</div>
		<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
						</caption>
						<tbody >
						<tr>
							<td>
								<div id="container_user" style="height: 360px;"></div>
							</td>
						</tr>
						<tr>
							<td width="50%">
								<div id="container_order" style="height: 360px;"></div>
							</td>
						</tr>
						</tbody>
					</table>
				</div><!-- /commonWrap -->
			</div>


<div class="page_head">
	<h4></h4>
</div>

</body>
</html>


