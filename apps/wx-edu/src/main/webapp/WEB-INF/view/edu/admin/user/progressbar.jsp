<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送邮件消息</title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<script type="text/javascript">
    var timeid;
    $(function() {
        $( "#progressbar" ).progressbar({
            value: 0
        });

        timeid = window.setInterval(query, 1000);
    });
    function query(){
        $.ajax({
            url : "${ctx}/admin/query/progressbar",
            data : {
                "type" : ${type}

            },  // 参数
            type : "post",
            async : false,
            dataType : "json",  //返回json数据
            success:function (result){
                var sumNum = result.entity.sumNum;
                var listNum = result.entity.listNum;

                if(listNum==0){
                    $("#num").html("");
                    $("#status").html("发送完成");
                    $( "#progressbar" ).progressbar({
                        value: 100
                    });
                    window.clearInterval(timeid);
                }else{
                    listNum = sumNum - listNum;
                    var num = listNum/sumNum*100;
                    $( "#progressbar" ).progressbar({
                        value: num
                    });

                    $("#num").html(listNum+"/"+sumNum);
                    $("#status").html("发送中");
                }

            }
        });
    }
</script>

</head>
<body  >
	<!-- 公共右侧样式 -->
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">发送管理</strong> / <small>发送进度</small>
		</div>
	</div>
	<hr />
	<!-- 公共右侧标题样式 结束-->

	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="#tab1">发送进度</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div id="tab1" class="am-tab-panel am-fade am-active am-in">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">发送进度</div>
						<div class="am-u-sm-8 am-u-md-10">
							<div id="progressbar"></div>
							<span id="num"></span>
						</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">状态</div>
						<div class="am-u-sm-8 am-u-md-10">
							<div id="status"></div>
						</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right"></div>
						<div class="am-u-sm-8 am-u-md-10">
							<c:if test="${type==1}">
								<a href="/admin/user/sendEmaillist" title="返回"
									class="am-btn am-btn-danger">返回</a>
							</c:if>
							<c:if test="${type==2}">
								<a href="/admin/user/sendMsglist" title="返回"
									class="am-btn am-btn-danger">返回</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>