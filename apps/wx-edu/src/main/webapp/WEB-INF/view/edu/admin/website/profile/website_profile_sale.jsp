<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>售卖方式</title>

<script type="text/javascript">
function updateSubmit(){
	if($("#verifyCourse").val()=='OFF'&&$("#verifyMember").val()=='OFF'){
		dialogFun('提示',"必须开起一种售卖方式",0);
		return;
	}
	var verifyMember=$("#verifyMember").val();
	var verifyCourse=$("#verifyCourse").val();
	var verifyLevel=$("#verifyLevel").val();
	$.ajax({
		url : baselocation+"/admin/websiteProfile/sale/update",
		data : {"verifyMember":verifyMember,"verifyCourse":verifyCourse,"verifyLevel":verifyLevel},
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.success){
				dialogFun('提示',"修改成功",5,'javascript:history.go(0)');
			}else{
				dialogFun('提示',"系统繁忙",0);
			}
		}
	})
}

</script>
</head>
<body >
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">售卖方式管理</strong> / <small>售卖方式</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">售卖方式基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in am-scrollable-vertical">
				<form class="am-form" action="${ctx}/admin/websiteProfile/sale/update" method="post">
					<input type="hidden" name="type" id="id"  value="online"/>
					<input type="hidden" name="onlineImageUrl" id="imagesUrl"  value=""/>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							单课购买开关
						</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<select name="verifyCourse" id="verifyCourse" data-am-selected>
								<option value="OFF" <c:if test="${websitemap.sale.verifyCourse=='OFF'}">selected="selected"</c:if>>OFF</option>
								<option value="ON"  <c:if test="${websitemap.sale.verifyCourse=='ON'}">selected="selected"</c:if>>ON</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">课程可以单独购买观看</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							会员功能开关
						</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<select name="verifyMember" id="verifyMember" data-am-selected>
								<option value="ON" <c:if test="${websitemap.sale.verifyMember=='ON'}">selected="selected"</c:if> >ON</option>
								<option value="OFF" <c:if test="${websitemap.sale.verifyMember=='OFF'}">selected="selected"</c:if> >OFF</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">课程可以通过开通会员的方式观看，需要设置使用模式</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							会员使用模式
						</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<select name="verifyLevel" id="verifyLevel" data-am-selected>
								<c:if test="${websitemap.sale.verifyLevel=='ON'}">
									<option value="ON">阶梯模式</option>
									<option value="OFF">独立模式</option>
								</c:if>
								<c:if test="${websitemap.sale.verifyLevel=='OFF'}">
									<option value="OFF">独立模式</option>
									<option value="ON">阶梯模式</option>
								</c:if>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-6 am-text-danger">1.独立模式：每种会员只能看自己的课程 ，黄金会员只能看课程中设置为黄金会员的课程<br/> 2.阶梯模式：高级会员可以看低级会员的课程 <br/>如：课程设置为黄金会员可观看，开通了白金和钻石会员也能观看,钻石>白金>黄金
							<br/>即：钻石会员可以观看钻石+白金+黄金的所有课程，白金可以观看白金+黄金的所有课程，黄金可以观看黄金会员的所有课程</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="button" onclick="updateSubmit()">修改</button>
							<button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
