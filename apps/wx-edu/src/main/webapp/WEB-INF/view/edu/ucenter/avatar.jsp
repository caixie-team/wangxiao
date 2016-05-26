<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- 新增 -->
<head>
<title>修改头像</title>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<jsp:include page="/WEB-INF/view/edu/ucenter/avatar_resource.jsp"></jsp:include>
<style>
.ke-upload-area {
    min-width: 54px;
    width: auto;
}
.ke-upload-area .ke-button-common, .ke-upload-area .ke-button-common input {
    background: #fff7f3 none repeat scroll 0 0;
    border: 1px solid #ff5f16;
}
.ke-upload-area .ke-button-common input {
    border: medium none;
    color: #ff5f16;
    font-weight: bold;
    width: 100px;
}
.ke-upload-area .ke-button-common input:hover {
    background: #fff2ec none repeat scroll 0 0;
}
</style>
</head>
<body>
	<!-- 中间内容 -->
	<div class="">
		<article>
			<header class="uc-com-title">
				<span>修改头像</span>
			</header>
			<div class="u-title-box u-user-title">
				<ol class="js-tap clearfix">
					<li><a href="${ctx}/uc/uinfo" title="个人资料">个人资料</a></li>
					<li class="current"><a href="javascript:void(0)" title="修改头像">修改头像</a></li>
					<li><a href="${ctx}/uc/uppwd" title="密码设置">密码设置</a></li>
					<li><a href="${ctx}/uc/updateMobile" title="手机号">手机号</a></li>
					<li><a href="${ctx}/uc/updateEmail" title="Email">Email</a></li>
				</ol>
			</div>
				<!-- 头像修改/ -->
				<section class="ukindeditor">
					<div class="u-a-g-list c-u-sett-box">
					<form name="tempPicForm" id="tempPicForm" method="post" action="${ctx}/cus/uc!uploadPhoto.action" enctype="multipart/form-data">
							<div class="g-zl-wrap g-zl-dl">
							<!--头像上传控件-->
							<section class="uploadBtnWrap mb20 pr">
								<input   type="button" id="fileupload" value="上传头像" width="70" />
							</section>
							<!--头像上传控件-->
							<!--头像裁切控件-->
							<div class="fl jc-demo-box pr">
								<c:if test="${empty queryUser.avatar}">
									<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
										width="300" height="300" alt="" class="dis pictureWrap"
										id="picture" />
								</c:if>
								<c:if test="${not empty queryUser.avatar}">
									<c:if test="${fn:contains(queryUser.avatar,'http' )}">
										<img src="${queryUser.avatar}"
											 width="300" height="300" alt="" class="dis pictureWrap"
											 id="picture" />
									</c:if>
									<c:if test="${!fn:contains(queryUser.avatar,'http' )}">
										<img src="<%=staticImageServer%>${queryUser.avatar}"
											 width="300" height="300" alt="" class="dis pictureWrap"
											 id="picture" />
									</c:if>
								</c:if>
								<div></div>
								<div id="preview-pane" class="preview-pane1">
									<div class="preview-container">
									<c:if test="${empty queryUser.avatar}">
									<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
											class="jcrop-preview" alt="Preview"
											style="width: 180px;height:180px"/>
									</c:if>
									<c:if test="${not empty queryUser.avatar}">
										<c:if test="${fn:contains(queryUser.avatar,'http' )}">
											<img src="${queryUser.avatar}"
												 class="jcrop-preview" alt="Preview"
												 style="width: 180px;height:180px" />
										</c:if>
										<c:if test="${!fn:contains(queryUser.avatar,'http' )}">
											<img src="<%=staticImageServer%>${queryUser.avatar}"
												 class="jcrop-preview" alt="Preview"
												 style="width: 180px;height:180px" />
										</c:if>
									</c:if>
									</div>
									<p class="c-666">大尺寸头像，180x180像素</p>
								</div>
								<div id="preview-pane" class="preview-pane2">
									<div class="preview-container">
										<c:if test="${empty queryUser.avatar}">
										<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
											class="jcrop-preview" alt="Preview"
											style="width: 80px;height: 80px;" />
										</c:if>
										<c:if test="${not empty queryUser.avatar}">
											<c:if test="${fn:contains(queryUser.avatar,'http' )}">
												<img src="${queryUser.avatar}"
													 class="jcrop-preview" alt="Preview"
													 style="width: 80px;height: 80px;" />
											</c:if>
											<c:if test="${!fn:contains(queryUser.avatar,'http' )}">
												<img src="<%=staticImageServer%>${queryUser.avatar}"
													 class="jcrop-preview" alt="Preview"
													 style="width: 80px;height: 80px;" />
											</c:if>
										</c:if>
									</div>
									<p class="c-666">中尺寸头像，80x80像素</p>
								</div>
								<div id="preview-pane" class="preview-pane3">
									<div class="preview-container">
										<c:if test="${empty queryUser.avatar}">
										<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
											class="jcrop-preview" alt="Preview"
											style="width: 50px;height: 50px;" />
										</c:if>
										<c:if test="${not empty queryUser.avatar}">
											<c:if test="${fn:contains(queryUser.avatar,'http' )}">
												<img src="${queryUser.avatar}"
													 class="jcrop-preview" alt="Preview"
													 style="width: 50px;height: 50px;" />
											</c:if>
											<c:if test="${!fn:contains(queryUser.avatar,'http' )}">
												<img src="<%=staticImageServer%>${queryUser.avatar}"
													 class="jcrop-preview" alt="Preview"
													 style="width: 50px;height: 50px;" />
											</c:if>
										</c:if>
									</div>
									<p class="c-666">小尺寸头像，50x50像素</p>
								</div>
							</div>
							<!--头像裁切控件-->
							<div class="fr uploadWrap">
								<p class="c-orange">您上传的图片将会自动生成三种尺寸的清晰头像，请注意小尺寸的头像是否清晰。</p>
							</div>
							<section class="clear"></section>
								<section class="tac mt50 ava-btn" >
									<a onclick="catparams()" href="javascript:void(0)" title="修改" class="bm-lr-btn u-user-btn">修&nbsp;改</a>
									<a id="quxiao" style="display: none" href="javascript:void(0)" title="取消" class="bm-lr-btn u-user-btn">取&nbsp;消</a>
								</section>
							</div>
							</form>
				</div>
					</section>
					</article>
					<div style="display: none">
								<c:if test="${empty queryUser.avatar}">
								<input id="photoPath" name="photoPrams.photoPath"/>
								</c:if>
								<c:if test="${!empty queryUser.avatar}">
								<input id="photoPath" 
										name="photoPrams.photoPath"
										value="${queryUser.avatar}" />
								</c:if>
								<input name="photoPrams.txt_width" value="1" id="picture_width"
									style="display: none; background: none" /> <br /> <input
									name="photoPrams.txt_height" value="1" id="picture_height"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_top" value="82" id="txt_top"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_left" value="73" id="txt_left"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_DropWidth" value="120" id="txt_DropWidth"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_DropHeight" value="120"
									id="txt_DropHeight" style="display: none" /> <br /> <input
									name="photoPrams.txt_Zoom" id="txt_Zoom" style="display: none" />
							</div>
					</div>
	
	<%-- <article class="u-m-c-w837 u-m-center" style="margin-left: 0;">
		<section class="u-m-c-wrap">
			<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="" title="">账号管理</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<!-- /u-m-sub-head -->
						<div class="u-m-sub-head">
							<ol class="clearfix pl50">
								<li><a href="${ctx}/uc/uinfo"
									title="基本信息">基本信息</a></li>
								<li><a href="${ctx}/uc/uppwd"
									title="修改密码">修改密码</a></li>
								<li class="current"><a
									href="javascript:void(0)" title="">修改头像</a></li>
							</ol>
						</div>
						<!-- /u-m-sub-head -->
						<!-- 中间内容开始 -->
						<article>
							<section>
								<div class="mt30 ml20 mr20">

									<section>
										<form name="tempPicForm" id="tempPicForm" method="post"
											action="${ctx}/cus/uc!uploadPhoto.action"
											enctype="multipart/form-data">

											<div class="mb50">
												<section class="uBoxBg of">
													<section class="clearfix">
														<!--头像上传控件-->
														<section class="uploadBtnWrap mb20 pr">
															<!-- <input id="fileupload" type="file" width="133"
																height="45" name="" class="pa"> -->
																<input   type="button" id="fileupload" value="浏览" width="70" />
														</section>
														<!--头像上传控件-->
														<!--头像裁切控件-->
														<div class="fl jc-demo-box pr">
															<c:if test="${queryUser.avatar==null||queryUser.avatar==''}">
															<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
																width="300" height="300" alt="" class="dis pictureWrap"
																id="picture" />
															</c:if>
															<c:if test="${queryUser.avatar!=null&&queryUser.avatar!=''}">
															<img src="<%=staticImageServer%>${queryUser.avatar}"
																width="300" height="300" alt="" class="dis pictureWrap"
																id="picture" />
															</c:if>
															<div></div>
															<div id="preview-pane" class="preview-pane1">
																<div class="preview-container">
																<c:if test="${queryUser.avatar==null||queryUser.avatar==''}">
																<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
																		class="jcrop-preview" alt="Preview"
																		style="width: 180px;height:180px"/>
																</c:if>
																<c:if test="${queryUser.avatar!=null&&queryUser.avatar!=''}">
																<img src="<%=staticImageServer%>${queryUser.avatar}"
																		class="jcrop-preview" alt="Preview"
																		style="width: 180px;height:180px" />
																</c:if>
																</div>
																<p class="c-666">大尺寸头像，180x180像素</p>
															</div>
															<div id="preview-pane" class="preview-pane2">
																<div class="preview-container">
																	<c:if test="${queryUser.avatar==null||queryUser.avatar==''}">
																	<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
																		class="jcrop-preview" alt="Preview"
																		style="width: 80px;height: 80px;" />
																	</c:if>
																	<c:if test="${queryUser.avatar!=null&&queryUser.avatar!=''}">
																	<img src="<%=staticImageServer%>${queryUser.avatar}"
																		class="jcrop-preview" alt="Preview"
																		style="width: 80px;height: 80px;" />
																	</c:if>
																</div>
																<p class="c-666">中尺寸头像，80x80像素</p>
															</div>
															<div id="preview-pane" class="preview-pane3">
																<div class="preview-container">
																	<c:if test="${queryUser.avatar==null||queryUser.avatar==''}">
																	<img src="<%=imagesPath%>/static/edu/images/default/uploadDefaultPic.jpg"
																		class="jcrop-preview" alt="Preview"
																		style="width: 50px;height: 50px;" />
																	</c:if>
																	<c:if test="${queryUser.avatar!=null&&queryUser.avatar!=''}">
																	<img src="<%=staticImageServer%>${queryUser.avatar}"
																		class="jcrop-preview" alt="Preview"
																		style="width: 50px;height: 50px;" />
																	</c:if>
																</div>
																<p class="c-666">小尺寸头像，50x50像素</p>
															</div>
														</div>
														<!--头像裁切控件-->
														<div class="fr uploadWrap">
															<p class="c-orange">您上传的图片将会自动生成三种尺寸的清晰头像，请注意小尺寸的头像是否清晰。</p>
														</div>
														<section class="clear"></section>
														<div class="uploadBc of mt20">
															<center>
																<span id="save_message" class="orangeCol">&nbsp;</span>
															</center>
															<center>
																<div class="uFunc">
																	<label class="u-a-set-btn"><input type="button"
																		value="保&nbsp;存" onclick="catparams()"></label> <input
																		type="button" class="commBtn bgGreen w80 ml50"
																		value="取&nbsp;消" id="quxiao" style="display: none">
																</div>
															</center>
														</div>
													</section>
												</section>

											</div>
										</form>
									</section>
								</div>
							</section>

							<div style="display: none">
								<c:if test="${empty queryUser.avatar}">
								<input id="photoPath" name="photoPrams.photoPath"/>
								</c:if>
								<c:if test="${!empty queryUser.avatar}">
								<input id="photoPath" 
										name="photoPrams.photoPath"
										value="${queryUser.avatar}" />
								</c:if>
								<input name="photoPrams.txt_width" value="1" id="picture_width"
									style="display: none; background: none" /> <br /> <input
									name="photoPrams.txt_height" value="1" id="picture_height"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_top" value="82" id="txt_top"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_left" value="73" id="txt_left"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_DropWidth" value="120" id="txt_DropWidth"
									style="display: none" /> <br /> <input
									name="photoPrams.txt_DropHeight" value="120"
									id="txt_DropHeight" style="display: none" /> <br /> <input
									name="photoPrams.txt_Zoom" id="txt_Zoom" style="display: none" />
							</div>
						</article>
					</section>
				</section>
			</article>
		</section>

	</article> --%>
	<!-- 中间内容结束 -->
</body>