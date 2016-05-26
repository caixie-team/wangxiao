<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户账户信息</title>
	<script type="text/javascript">
	var isTrue=false;
	var reAmount=0;
	function validAmount(amount){//验证
		var reg=/^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
		if(!reg.test(amount)){
			isTrue=false;
			alert("请输入正确的金额");
			return;
		}
		isTrue=true;
		reAmount=amount;
	}
	function rechargeForm(userId,optFlag){
		if(isTrue){
			var optInfo="";
			var optSure="";
			if(optFlag=="credit"){//充值 信息赋值
				optInfo="充值金额必须大于0";
				optSure="确定充值吗,充值后不得修改?";
			}else{
				optInfo="扣款金额必须大于0";
				optSure="确认扣款吗,操作后不得修改";
			}
			if(reAmount<=0){
				alert(optInfo);
				return;
			}
			if(confirm(optSure)){
				$.ajax({
					url:"${ctx}/admin/account/recharge",
					data:{"userId":userId,"flag":optFlag,"balance":reAmount},
					dataType:"json",
					type:"post",
					success:function(result){
						if(result.success){
							alert(result.message);
							window.location.href="${ctx}/admin/account/list";
						}else{
							alert(result.message);
						}
					}
				});
			}
		}else{
			alert("请输入正确的金额");
			return;
		}
	}
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户账户管理</strong> / <small>账户充值</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">账户基本属性</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form class="am-form">
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							学员ID
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.userId }"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							昵称
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.nickName }"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							邮箱
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.email }"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							创建时间
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="<fmt:formatDate value="${userAccountDTO.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							更新时间
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="<fmt:formatDate value="${userAccountDTO.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							账户余额
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.balance}"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							冻结金额
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.forzenAmount}"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							银行入账
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.cashAmount}"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							课程卡入账
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="${userAccountDTO.vmAmount}"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							账户状态
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" readonly value="
								<c:if test="${userAccountDTO.accountStatus=='FROZEN'}">冻结</c:if>
								<c:if test="${userAccountDTO.accountStatus=='ACTIVE'}">正常</c:if>
							"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							${flag=='credit'?'充值金额':'扣款金额'}
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<input type="text" value="0" name="rechargeAmount" onblur="validAmount(this.value)"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>

					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-4">
							<c:if test="${flag=='credit'}">
								<a class="am-btn am-btn-danger" title="充值" href="javascript:rechargeForm(${userAccountDTO.userId },'credit')">充值</a>
							</c:if>
							<c:if test="${flag=='debit'}">
								<a class="am-btn am-btn-danger" title="扣款" href="javascript:rechargeForm(${userAccountDTO.userId },'debit')">扣款</a>
							</c:if>
								<a class="am-btn am-btn-primary" title="返 回" href="javascript:history.go(-1);">返 回</a>
						</div>
						<div class="am-hide-sm-only am-u-md-6"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
