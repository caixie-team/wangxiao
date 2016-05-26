$(function(){
	$(window).load(function(){
		$(".chartProgress").each(function(){
			var _this = $(this);
			var _id = _this.attr("id");
			var _total = _this.attr("total");
			var _complete = _this.attr("complete");
			_this.css("width","140px");
			_this.css("height","140px");
			init(_id,_total,_complete);
		});
	});
});
// 初始化echart
function init(id,totalTime,completeTime){
	var myChart = echarts.init(document.getElementById(id));
	var option = {
		tooltip: {
			trigger: 'item',
			formatter: "{b}: {d}%"
		},
		series: [
			{
				name:'完成进度',
				type:'pie',
				radius: ['60%', '80%'],
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: true,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				labelLine: {
					normal: {
						show: false
					}
				},
				data:[
					{
						value:totalTime-completeTime,
						name:'未完成',itemStyle:{
							normal:{color:'#CFD8E6'}
						}
					},
					{
						value:completeTime,
						name:'完成',
						itemStyle:{
							normal:{color:'#F60'}

						}
					}
				]
			}
		]
	};
	myChart.setOption(option);
}
function startArrangeExam(paperId,arrangeRecordId,isComplete,isRepeat){
	if(isComplete==1&&isRepeat==0){//已完成, 不可重复作答
		dialogFun("提示","此测评不可重复做答",0);
		return;
	}
	var beginTime = $("#beginTime"+arrangeRecordId).val();
	var endTime = $("#endTime"+arrangeRecordId).val();
	var now = new Date();
	if(now.getTime()>new Date(beginTime).getTime()&&now.getTime()<new Date(endTime).getTime()){
		window.location.href=baselocationexam+"/paper/toExamPaper/"+paperId+"?arrangeRecordId="+arrangeRecordId+"&ifarrange=1";
	}else {
		dialogFun("提示","当前时间不在答题时间内",0);
	}
}