<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>考试成绩折线图</title>
<script type="text/javascript"
	src="${ctximg}/static/edu/js//highChart/highcharts.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/admin/js/jquery.epiclock.js"></script>
<script type="text/javascript" language="javascript">
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
<style>
label.epiClock {
	display: inline;
}
</style>
</head>
<body>
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>部门分数分布图
		</h4>
	</div>
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
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>部门分数详细数据&nbsp&nbsp&nbsp
										   最高分：${Max}分&nbsp|&nbsp&nbsp
										   最低分：${Min}分&nbsp|&nbsp&nbsp
										   应参加人数：${number} 人&nbsp|&nbsp&nbsp
										   实际参加人数：${attend} 人&nbsp|&nbsp&nbsp
										   未参加人数：${notattend}人&nbsp&nbsp&nbsp
		</h4>
	</div>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >				
					<thead>
						<tr>
							<th><span>任务记录主键</span></th>
							<th><span>员工名称</span></th>
							<th><span>任务名称</span></th>
							<th><span>部门名称</span></th>
							<th><span>试卷名称</span></th>
							<th><span>分数</span></th>
							<th><span>提交时间</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${taskRecordGroudUser.size()>0}">
							<c:forEach  items="${taskRecordGroudUser}" var="record" >
						  <tr>
							 <td><span>${record.id}</span></td>
							 <td><span><a href="${ctx}/admin/task/exam/papers/${record.userId}">${record.nickname}</a></span></td>
							 <td><span>${record.taskname}</span></td>
							 <td><span>${record.groupname}</span></td>
							 <td><span>${record.exampaperName}</span></td>
							 <td><span>${record.score}</span></td>
							 <td>
							 <fmt:formatDate type="both" value="${record.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							 </td>
						  </tr>
							</c:forEach>
						</c:if>
						<c:if test="${taskRecordGroudUser.size()==0||taskRecordGroudUser==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有成绩信息！</span>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
</body>
</html>


