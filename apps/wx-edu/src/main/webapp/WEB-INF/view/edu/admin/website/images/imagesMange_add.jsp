<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>修改图片管理</title>
<%--     <script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
	<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
	<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>  --%>
    <script type="text/javascript">
    /* 	function check(){
	    	return $("#addImagesMange").validate();
    	} */
    	
        function checkImagesMange() {
        	/* if(!check().form()){
        		return;
        	} */
            var imageType = $("#imageType").val();
        	if(imageType==null ||imageType==""){
        		dialogFun("提示","请输入图片类型",0);
        		return ;
        	}
            var imageKey = $("#imageKey").val();
        	if(imageKey==null ||imageKey==""){
        		dialogFun("提示","请输入图片Key",0);
        		return ;
        	}
            $.ajax({
                url: '${ctx}/admin/website/checkImagesMange',
                data: {"imageKey": imageKey},
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (result.message == "success") {
                        addImagesMange();
                    } else if (result.message == "fail") {
                        dialogFun("提示","已有相同的key值",0);
                    } else {
                        dialogFun("提示","系统异常",0);
                    }
                }
            });
        }

        function addImagesMange() {
            var imageKey = $("#imageKey").val();
            var imageType = $("#imageType").val();
            $.ajax({
                url: '${ctx}/admin/website/addImagesMange',
                data: {
                    "websiteImageMange.image_key": imageKey,
                    "websiteImageMange.type": imageType
                },
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (result.message = "success") {
                        window.location.href = "${ctx}/admin/website/imagesMangeList"
                    } else if (result.message = "fail") {
                        dialogFun("提示","系统异常",0);
                    } else {
                        dialogFun("提示","系统异常",0);
                    }
                }
            });
        }
    </script>
</head>
<body>
    <!-- 公共右侧样式 -->
		<div class="am-cf">
	      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图片类型管理</strong> / <small>添加图片类型</small></div>
	    </div>
	    <hr>
	<!-- 公共右侧标题样式 结束-->
	
	<div class="mt20">
	   	 <div  class="am-tabs">
		    <ul class="am-tabs-nav am-nav am-nav-tabs">
		      <li class="am-active"><a href="#tab1">基本信息</a></li>
		    </ul>
		    <div class="am-tabs-bd">
		    	<div id="tab1" class="am-tab-panel am-fade am-active am-in">
	    			<form id="addImagesMange" action="" class="am-form am-form-inline"  data-am-validator>
		    		<div class="am-g am-margin-top">
			            <div class="am-u-sm-4 am-u-md-2 am-text-right">
			              	图片类别
			            </div>
			            <div class="am-u-sm-8 am-u-md-4">
			              <input class="am-input-sm" required type="text" name="websiteImageMange.type" class="{required:true}" id="imageType"/>
			            </div>
			            <div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
			        </div>
		    		<div class="am-g am-margin-top">
			            <div class="am-u-sm-4 am-u-md-2 am-text-right">
			              	图片key值
			            </div>
			            <div class="am-u-sm-8 am-u-md-4">
			              <input class="am-input-sm" required type="text" name="websiteImageMange.image_key"  id="imageKey"/>
			            </div>
			            <div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
			        </div>
			         <div class="am-g am-margin-top">
                     <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
                     <div class="am-u-sm-8 am-u-md-4">
                         <button class="am-btn am-btn-success" type="button" onclick="checkImagesMange()">提 交</button>
                         <button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返 回</button>
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
