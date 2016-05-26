<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>会员商品修改</title>
	<script type="text/javascript">
		$(function(){
			$('#updateForm').validator({
				validate: function(validity) {
					validity.valid = false;
					// 名称
					if($(validity.field).is('#name')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isEmpty(_this.val())){
							_error.html("请填写名称");
						}else{
							validity.valid = true;
							_error.html("");
						}
						return validity;
					}
					// 会员类型
					else if($(validity.field).is('#type')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isEmpty(_this.val())||_this.val()==0){
							_error.html("请选择会员类型");
						}else{
							validity.valid = true;
							_error.html("");
						}
						return validity;
					}
					// 价格
					else if($(validity.field).is('#price')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(isEmpty(_this.val())||isNaN(_this.val())){
							_error.html("请输入正确的价格");
						}else{
							validity.valid = true;
							_error.html("");
						}
						return validity;
					}
					// 开通天数
					else if($(validity.field).is('#days')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(!isInt(_this.val())){
							_error.html("请输入正确的开通天数");
						}else{
							validity.valid = true;
							_error.html("");
						}
						return validity;
					}
					// 排序
					else if($(validity.field).is('#sort')){
						var _this = $(validity.field);
						var _error = _this.parent().next();
						if(!isInt(_this.val())){
							_error.html("请输入正确的排序");
						}else{
							validity.valid = true;
							_error.html("");
						}
						return validity;
					}
					// 描述
					else if($(validity.field).is('#description')){
						validity.valid = true;
						return validity;
					}
				},
				submit: function() {
					var formValidity = this.isFormValid();
					$.when(formValidity).then(function() {
						$("#updateForm").submit();
					}, function() {});
					return false;
				}
			});
		});
	</script>
</head>
<body >
	<div class="am-cf">
		<strong class="am-text-primary am-text-lg">会员商品管理</strong> / <small>会员商品修改</small>
	</div>
	<hr/>
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息(<i class="am-text-danger">*</i>为必填项)</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div class="am-tab-panel am-fade am-active am-in">
					<form action="${ctx}/admin/membersale/update" method="post" id="updateForm" class="am-form">
						<input type="hidden" value="${memberSale.id }" name="memberSale.id"/>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><i class="am-text-danger">*</i>&nbsp;名称  </div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="memberSale.name" id="name" value="${memberSale.name }"class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><i class="am-text-danger">*</i>&nbsp;类型</div>
							<div class="am-u-sm-8 am-u-md-4">
								<select name="memberSale.type" id="type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
									<option value="0">--请选择--</option>
									<c:forEach items="${memberTypes }" var="memberType">
										<option value="${memberType.id }"  <c:if test="${memberType.id==memberSale.type }">selected="selected"</c:if>>${memberType.title }</option>
									</c:forEach>
								</select>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><i class="am-text-danger">*</i>&nbsp;价格</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="memberSale.price" id="price" value="${memberSale.price }"  class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><i class="am-text-danger">*</i>&nbsp;开通天数</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="memberSale.days" id="days" value="${memberSale.days }" class="am-input-sm"/>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right"><i class="am-text-danger">*</i>&nbsp;排序</div>
							<div class="am-u-sm-8 am-u-md-4">
								<input type="text" name="memberSale.sort" value="${memberSale.sort }" id="sort" class="am-input-sm" />
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;描述</div>
							<div class="am-u-sm-8 am-u-md-4">
								<textarea rows="5" cols="70" name="memberSale.description" id="description" class="{required:true}"  class="am-input-sm" >${memberSale.description }</textarea>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
							<div class="am-u-sm-8 am-u-md-4">
								<button class="am-btn am-btn-danger" title="提 交" type="submit">提 交</button>
								<button class="am-btn am-btn-success" title="返 回" onclick="history.go(-1);">返 回</button>
							</div>
							<div class="am-hide-sm-only am-u-md-6 am-text-danger">&nbsp;</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
