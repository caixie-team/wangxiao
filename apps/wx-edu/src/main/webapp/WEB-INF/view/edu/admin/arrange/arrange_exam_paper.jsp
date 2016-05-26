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
	var submitTime = [];
	var exampaperName = [];
	var datas= [];
	var score =  "" ;
 
	  $.ajax({
	        url:"/admin/arrange/web/papersajax",
	        type:"post",
	        data:{"id":${id}},
	        dataType:"json",
	        async:false,
	        success:function(result){
	         	if(result.success){
	         		
	         		var entitys = result.entity
		        	var yourString=result.message;//时间格式转换
		        		var result=yourString.split(",");
		        		for(var i=0;i<result.length;i++){
		        			submitTime[i] = result[i] 
		        		}
		        		
	        		 for(var i = 0;i<entitys.length;i++){//赋值
	        			var d = new Date();
	        			score+=entitys[i].score+","
	        			datas[i] = "\'"+entitys[i].exampaperName+"<br/>"+submitTime[i]+"\'"
	        			exampaperName[i]="\'"+entitys[i].exampaperName+"\<br/>'";
	        		}; 
		        	score=score.substring(0,score.length-1) 
	        	}        	
	            else{
	                alert("请求错误!");
	            } 
	        }  
	    })
    $('#container').highcharts({    
    	 title: {
	            text: '学员考试成绩走势',
	            x: -20
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {  
	        	categories :  eval("["+ datas + "]")
		        },
		     yAxis: [{
		    	 floor: 0,
		         ceiling: 100,
		         title: {
		             text: null
		         },
		         labels: {
		             align: 'left',
		             x: 3,
		             y: 16,
		             format: '{value:.,0f}'
		         },
		         showFirstLabel: false,
		         min:0
		         }, 
		         {
		        	 
		         linkedTo: 0,
		         gridLineWidth: 0,
		         opposite: true,
		         title: {
		             text: null
		         },
		         labels: {
		             align: 'right',
		             x: -3,
		             y: 16,
		             format: '{value:.,0f}'
		         },
		         showFirstLabel: false  ,
		         min:0
		     }],
			       tooltip: {
			    	   headerFormat : '',
			            valueSuffix: '分' 
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			        	name : '得分',
						data : eval("(["+ score + "])")
			        }]
			        
	        });

		});
 


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
			<em class="icon14 i_01"></em>学员考试成绩走势
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
</body>
</html>


