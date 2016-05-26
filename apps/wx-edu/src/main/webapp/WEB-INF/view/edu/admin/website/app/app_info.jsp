<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>更新设置</title>
    <script type="text/javascript">
        $(function(){
            var msg = '${msg}';
            if(msg=='success'){
                dialogFun("更新设置","修改成功",1);

            }else if(msg=='error'){
                dialogFun("更新设置","系统异常，请稍后重试",0);
            }
        })
    </script>
</head>
<body>
<div class="am-cf">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">更新管理</strong> / <small>更新设置</small></div>
</div>
<hr>
<div class="mt20">
    <div  class="am-tabs">
        <ul class="am-tabs-nav am-nav am-nav-tabs">
            <li class="am-active"><a href="javascript:void(0)">app更新管理</a></li>
        </ul>
        <div class="am-tabs-bd">
            <div id="tab1" class="am-tab-panel am-fade am-active am-in">
                <form action="${ctx }/admin/website/app/update" method="post" class="am-form">
                    <div class="am-g am-margin-top">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            安卓版本号
                        </label>
                        <div class="am-u-sm-8 am-u-md-6">
                            <input type="text" name="android_v" value="${appMap.app.android_v}" class="am-input-sm" placeholder="请填写安卓版本号">
                        </div>
                        <div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            安卓更新地址
                        </label>
                        <div class="am-u-sm-8 am-u-md-6">
                            <input type="text" name="android_url" value="${appMap.app.android_url}" class="am-input-sm" placeholder="请填写安卓更新地址">
                        </div>
                        <div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            IOS版本号
                        </label>
                        <div class="am-u-sm-8 am-u-md-6">
                            <input type="text" name="ios_v" value="${appMap.app.ios_v}" class="am-input-sm" placeholder="请填写IOS版本号">
                        </div>
                        <div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            IOS下载地址
                        </label>
                        <div class="am-u-sm-8 am-u-md-6">
                            <input type="text" name="ios_url" value="${appMap.app.ios_url}" class="am-input-sm" placeholder="请填写IOS下载地址">
                        </div>
                        <div class="am-hide-sm-only am-u-md-4"><font class="am-text-danger">*</font>&nbsp;必填</div>
                    </div>
                    <div class="am-g am-margin-top">
                        <label class="am-u-sm-4 am-u-md-2 am-text-right">
                            &nbsp;
                        </label>
                        <div class="am-u-sm-8 am-u-md-6">
                            <button class="am-btn am-btn-danger" onclick="submit()">修改</button>
                        </div>
                        <div class="am-hide-sm-only am-u-md-4"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
