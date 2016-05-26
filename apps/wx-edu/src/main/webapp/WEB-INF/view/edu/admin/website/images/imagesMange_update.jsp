<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>修改图片管理</title>
    <script type="text/javascript">
        function updateImagesMangeSubmit() {
            var bz = 1;
            var imageType = $("#imageType").val();
            var imageKey = $("#imageKey").val();
            if (imageType == null || $.trim(imageType) == '') {
                alert("类型不能为空")
                return false;
            }
            if (imageKey == null || $.trim(imageKey) == '') {
                alert("图片key不能为空")
                return false;
            }
            $.ajax({
                url: '${ctx}/admin/website/checkImagesMange',
                data: {"imageKey": imageKey},
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (result.message == "success") {
                    } else if (result.message == "fail") {
                        alert("已有相同的key值")
                        bz = 2;
                    } else {
                        alert("系统错误")
                        bz = 2;
                    }
                }
            });
            if (bz == 1) {
                $("#updateImagesMange").submit();
            } else {
                return;
            }
        }
    </script>
</head>
<body>
<form class="am-form" action="${ctx}/admin/website/updateImagesMange" method="post" id="updateImagesMange">
    <input type="hidden" name="websiteImageMange.id" value="${websiteImageMange.id}"/>
    <!-- 公共右侧样式 -->
	<div class="am-cf">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">图片类型管理</strong> / <small>修改图片类型</small></div>
    </div>
    <hr/>
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
			              <input class="am-input-sm" value="${websiteImageMange.type}" required type="text" name="websiteImageMange.type" class="{required:true}" id="imageType"/>
			            </div>
			            <div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
			        </div>
		    		<div class="am-g am-margin-top">
			            <div class="am-u-sm-4 am-u-md-2 am-text-right">
			              	图片key值
			            </div>
			            <div class="am-u-sm-8 am-u-md-4">
			              <input class="am-input-sm"  value="${websiteImageMange.image_key}" required type="text" name="websiteImageMange.image_key"  id="imageKey"/>
			            </div>
			            <div class="am-hide-sm-only am-u-md-6 am-text-danger">*必填</div>
			        </div>
			         <div class="am-g am-margin-top">
                     <div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
                     <div class="am-u-sm-8 am-u-md-4">
                         <button class="am-btn am-btn-success" type="button" onclick="updateImagesMangeSubmit()">提 交</button>
                         <button class="am-btn am-btn-danger" type="button" onclick="history.go(-1)">返 回</button>
                     </div>
                     <div class="am-u-sm-12 am-u-md-6"></div>
                 </div>
			       </form> 
		    	</div>
		    </div>
		</div>
	</div>
</form>
</body>
</html>
