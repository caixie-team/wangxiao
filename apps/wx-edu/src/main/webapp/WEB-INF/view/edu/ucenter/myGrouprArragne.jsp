<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/edu/css/u_mygroupuser.css" />
<script type="text/javascript"
	src="${ctximg}/static/edu/js/highChart/highcharts.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/admin/js/jquery.epiclock.js"></script>	
	<script type="text/javascript">
$(function () {
	  $('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: ''
			        },
			        tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: false
			                },
			                showInLegend: true
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '百分比',
			            data:  eval("("+'${TaskTecord}'+")")
			            
			        }]
			    });
			}
	);
	 
</script>
</head>
<body>
		<div id = "group_information ">	
			  	 	<h4>
						部门分数分布图
					</h4>
			  	 <div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%"
							class="commonTab01">
							<tbody>
								<tr align="center">
									<td>
										<div id="container"
											style="max-width: 85%; margin: auto; height: 260px;"></div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
		<!-- /commonWrap -->
				</div>
		</div>
		  <section class="pl15 pr15">
		   <div class="couses-tj">
		     <h4>
				  部门学分排行榜
			</h4>
			<c:forEach items="${score }" var="score"> 
			   <ul class="mt20">${score }</ul>			
			</c:forEach>
		   </div>			
		</section>
</body>
</html>
