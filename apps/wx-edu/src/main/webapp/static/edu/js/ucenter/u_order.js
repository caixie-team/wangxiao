//取消课程
function cancleCourse(orderId){
	window.location.href=baselocation + "/cancleoder/"+orderId;
}

$(function(){
	$("querySelect").val('INIT');
})

//订单查询
function queryByState(payState){
	window.location.href=baselocation + "/uc/order?trxStatus="+$(payState).val();
}
//课程详情
function  toDetails(orderId){
	window.location.href=baselocation + "/uc/odetail/"+orderId;
}



