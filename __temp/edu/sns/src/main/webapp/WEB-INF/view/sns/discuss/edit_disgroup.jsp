<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
<title>编辑小组</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/Jcrop/jquery.Jcrop.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/sns/css/Jcrop/jquery.Jcrop.css" media="screen" />
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
	
<script type="text/javascript">
	//进行信息验证
	$(function(){
		initFileUpload("uploadButton","imgcrop","fileupload");
		var counts="${count}";
	    if(counts>=10){
	     dialog_sns("对不起你已创建二十个小组,请返回",0);
	     $(".queding0").attr("href","javascript:history.back(-1);");
	    }
	    $("#groupListId").val(${disgroup.disclassifyId});
	    $("#disHint").hide();
	$('.commSelect').change(function(){
		if($("#groupListId").val()==0){
			$("#disclassify").show();
		}else{
			$("#disclassify").hide();
		}
	});
});
	</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20">
								<section class="mt10">
									<section class="comm-title-3">
										<span class="c-t-2-r"><a class="c-888" title="" href="${ctx}/dis">所有小组&gt;&gt;</a></span>
										<span class="c-t-2-l"><tt class="c-green">编辑小组</tt></span>
										<div class="c-t-line">&nbsp;</div>
									</section>
								</section>
								<!-- /挑战信息 -->
								<div class="mt20">
									<section>
										<dl class="boke-release-wrap">
											<dt style="width: 56px;">
												<span class="c-555 fsize14">小组名称</span>
											</dt>
											<dd>
												<input type="text" name="disGroup.name" class="commInput-1"
													id="disName" onkeyup="disNameHint()" value="${disgroup.name }"> <span
													class="ml10 c-red" id="disHint">小组名称不能为空！</span>
											</dd>
										</dl>
										<dl class="boke-release-wrap">
											<dt style="width: 56px;">
												<span class="c-555 fsize14">小组简介</span>
											</dt>
											<dd>
												<textarea name="disGroup.introduction" class="commTextarea"
													id="disIntroduction">${disgroup.introduction }</textarea>
											</dd>
										</dl>
										<dl class="boke-release-wrap">
											<dt style="width: 56px;">
												<span class="c-555 fsize14">分类</span>
											</dt>
											<dd>
											<c:if test="${disgroup.status==3 }">
												<select name="disGroup.disclassifyId" id="groupListId"
													class="commSelect">
													<option value="0">--请选择分类--</option>
													<c:forEach items="${disGroupClassify}" var="dgl">
														<option value="${dgl.id}">${dgl.name}</option>
													</c:forEach>
												</select> <span class="ml10 c-red" id="disclassify" style="display: none">请选择分类！</span>
												</c:if>
												<c:if test="${disgroup.status==1 }">
												<select name="disGroup.disclassifyId" id="groupListId"
													class="commSelect" disabled="disabled">
													<option value="${disgroup.disclassifyId}">${disgroup.disclasstiy}</option>
												</select>
												</c:if>
											</dd>
										</dl>
										<dl class="boke-release-wrap">
											<dt style="width: 56px;">
												<span class="c-555 fsize14">小组头像</span>
											</dt>
											<dd>
											<input   type="button" id="uploadButton" value="上传" />
											<form  name="imgcropFrom" id="imgcropFrom">
												<img src="<%=staticImageServer %>${disgroup.imageUrl }" alt="" name="imgcrop" id="imgcrop"/>
												<input type="hidden" id="imageUrl" name="imageUrl" value="${disgroup.imageUrl }">
												<input type="hidden" id="disGroupId" name="disGroup.id" value="${disgroup.id }">
												<input id="txtx" type="hidden" name="x" value="" />
												<input id="txty" type="hidden" name="y" value="" />
												<input id="txtx2" type="hidden" name="x2" value="" />
												<input id="txty2" type="hidden" name="y2" value="" />
												<input id="txt_DropWidth" type="hidden" name="w" value="" />
												<input id="txt_DropHeight" type="hidden" name="h" value="" />
											</form>
											
											<%-- <input class="ke-input-text" type="text" id="upurl" value="${disgroup.imageUrl }" readonly="readonly" />
											<input type="button" id="uploadButton" value="Upload" />
												<img src="" alt=""  id="img" width="60px" height="60px"/>
												<input type="hidden" id="url0" value="${disgroup.imageOriginal }">
												<input type="hidden" id="url1" value="${disgroup.imageUrl }">
												<input type="hidden" id="url2" value="${disgroup.imageLittle }"> --%>
											</dd>
										</dl>
									</section>
									<section class="mt20 tac pt20">
										<a href="javascript:void(0)" title="保存"
											class="comm-btn-orange" onclick="saveDisGroup(${disgroup.id})"><span
											style="padding: 5px 40px; font-size: 18px;">保存</span></a>
											<a href="javascript:history.go(-1)" title="" class="comm-btn-gray"><span
										style="padding: 5px 40px; font-size: 18px;">取消</span></a>
									</section>
								</div>
							</div>

						</section>
						<section class="W-main-right">
							<div class="pl20">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l"><i class="icon18 zysx">&nbsp;</i>
										<tt class="c-orange">注意事项</tt></span>
									</section>
									<section class="mt20 pr10">
										<div class="zysx-txt">
											请不要在话题中发布招聘和求职信息，违规话题将被删除。本站有正规的发布招聘和求职信息渠道，经过认证的猎头和HR可以在招聘中心和观点中发布招聘信息，普通会员可以在观点中发布求职信息。
										</div>
									</section>
								</div>
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
</body>
</html>