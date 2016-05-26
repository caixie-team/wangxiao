<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery.bigcolorpicker.css?v=${v}"/>
    <script type="text/javascript" src="${ctximg}/static/common/jquery.bigcolorpicker.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
    <script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
    <script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#imageColor").bigColorpicker("imageColor", "L", 10);
    		initUploadify("doc-form-file","imgpic","imagesUrl","","website");
        });
    </script>
</head>
<body>
    <div class="am-cf">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">广告图管理</strong> / <small>广告图添加</small></div>
    </div>
    <hr>
    <div class="mt20">
        <div  class="am-tabs">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
            </ul>
            <div class="am-tabs-bd">
                <div id="tab1" class="am-tab-panel am-fade am-active am-in">
                    <form action="${ctx}/admin/website/addImages" class="am-form" data-am-validator>
                        <div class="am-g am-margin-top am-form-group">
                            <label for="doc-vld-keyWord" class="am-u-sm-4 am-u-md-2 am-text-right">类型</label>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select data-am-selected="{btnSize: 'sm'}" required style="display: none;" id="doc-vld-keyWord" name="websiteImages.keyWord">
                                    <option value=""></option>
                                    <c:forEach items="${websiteImageMangeList}" var="imageType">
                                        <option value="${imageType.image_key}">${imageType.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="am-g am-margin-top am-form-group">
                            <label for="doc-vld-imagesTitle" class="am-u-sm-4 am-u-md-2 am-text-right">
                                广告图标题
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" id="doc-vld-imagesTitle" class="am-input-sm" required placeholder="请填写广告图标题" name="websiteImages.title">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6"><font class="am-text-danger">*</font>&nbsp;必填</div>
                        </div>
                        <div class="am-g am-margin-top am-form-group">
                            <label for="imagesUrl" class="am-u-sm-4 am-u-md-2 am-text-right">
                                图片地址
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" id="imagesUrl" class="am-input-sm" required placeholder="请填写图片地址或上传图片" name="websiteImages.imagesUrl">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6"><font class="am-text-danger">*</font>&nbsp;图片链接,可直复制地址或者通过上传</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <label for="imgpic" class="am-u-sm-4 am-u-md-2 am-text-right">
                                广告图
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <img id="imgpic" id="imgpic" src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="360px" height="200px" class="am-img-thumbnail am-radius">
                            </div>
                            <div class="am-hide-sm-only am-u-md-6 am-vertical-align" style="height: 150px;">
                                <div class="am-vertical-align-middle">
                                    网校图片链接，支持JPG、PNG格式，尺寸（宽1000像素，高500像素）<br/>
                                    微站图片链接，支持JPG、PNG格式，尺寸（宽720像素，高360像素)
                                </div>
                            </div>
                        </div>
                        <div class="am-g am-margin-top">
                            <label class="am-u-sm-4 am-u-md-2 am-text-right">
                                &nbsp;
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <div class="am-form-group am-form-file">
                                    <input type="file" multiple="" id="doc-form-file">
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-6"></div>
                        </div>
                        <div class="am-g am-margin-top am-form-group">
                            <label for="doc-vld-linkAddress" class="am-u-sm-4 am-u-md-2 am-text-right">
                                链接URL
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" id="doc-vld-linkAddress" class="am-input-sm" name="websiteImages.linkAddress">
                            </div>
                            <div class="am-u-sm-12 am-u-md-6"></div>
                        </div>
                        <div class="am-g am-margin-top am-form-group">
                            <label for="imageColor" class="am-u-sm-4 am-u-md-2 am-text-right">
                                背景颜色
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" class="am-input-sm" name="websiteImages.color" id="imageColor">
                            </div>
                            <div class="am-u-sm-12 am-u-md-6"></div>
                        </div>
                        <div class="am-g am-margin-top am-form-group">
                            <label for="doc-vld-seriesNumber" class="am-u-sm-4 am-u-md-2 am-text-right">
                                序列号
                            </label>
                            <div class="am-u-sm-8 am-u-md-4">
                                <input type="text" id="doc-vld-seriesNumber" onkeyup="this.value=this.value.replace(/\D/g,'')" class="am-input-sm" placeholder="请输入序列号" required name="websiteImages.seriesNumber" id="seriesNumber" value="0">
                            </div>
                            <div class="am-u-sm-12 am-u-md-6"><font class="am-text-danger">*&nbsp;</font>倒序</div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
                            <div class="am-u-sm-8 am-u-md-4">
                                <button class="am-btn am-btn-danger" type="submit">提交</button>
                                <button class="am-btn am-btn-danger" type="button" onclick="window.history.go(-1)">返回</button>
                            </div>
                            <div class="am-u-sm-12 am-u-md-6"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>