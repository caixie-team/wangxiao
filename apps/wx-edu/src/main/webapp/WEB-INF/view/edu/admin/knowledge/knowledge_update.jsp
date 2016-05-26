<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>修改知识体系</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
	<script type="text/javascript">
		$(function() {
			initUploadify("fileupload","imgUrl","picture","","knowledge");
		});
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">知识体系</strong> / <small>修改知识体系</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/knowledge/updateKnowledge" class="am-form" data-am-validator>
					<input type="hidden" value="${knowledge.id}" name="knowledge.id" />
					<input type="hidden" value="<fmt:formatDate value="${knowledge.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/>" name="knowledge.createTime" />
					<div class="am-g am-margin-top am-form-group">
						<label for="knowledgeName" class="am-u-sm-4 am-u-md-2 am-text-right">
							名称
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="knowledgeName" class="am-input-sm" required placeholder="请填写体系名称" value="${knowledge.name}" name="knowledge.name">
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							图片
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<c:if test="${not empty knowledge.picture}">
								<img src="<%=staticImageServer%>${knowledge.picture}" alt="" width="360" height="200" id="imgUrl" class="am-img-thumbnail am-radius"/>
							</c:if>
							<c:if test="${empty knowledge.picture}">
								<img src="${ctximg}/static/edu/images/NotAvailable.png" alt="" width="360" height="200" id="imgUrl" class="am-img-thumbnail am-radius"/>
							</c:if>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<label class="am-u-sm-4 am-u-md-2 am-text-right">
							&nbsp;
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<div class="am-form-group am-form-file">
								<input type="file" multiple="" id="fileupload">
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="picture" class="am-u-sm-4 am-u-md-2 am-text-right">
							图片路径
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" name="knowledge.picture" class="am-input-sm" id="picture" value="${knowledge.picture}" required />
						</div>
						<div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;请上传图片或直接填写图片路径</div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="description" class="am-u-sm-4 am-u-md-2 am-text-right">
							简介
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea id="description" name="knowledge.description" placeholder="请填写体系简介" required cols="60" rows="4">${knowledge.description}</textarea>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<label for="sort" class="am-u-sm-4 am-u-md-2 am-text-right">
							排序
						</label>
						<div class="am-u-sm-8 am-u-md-6">
							<input id="sort" type="text" name="knowledge.sort" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${knowledge.sort}" class="am-input-sm" placeholder="请填写排序号" required>
						</div>
						<div class="am-u-sm-12 am-u-md-4"><font class="am-text-danger">*&nbsp;</font>倒序</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="submit">提交</button>
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