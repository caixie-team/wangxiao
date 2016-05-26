<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>一些常用模块样式</title>
</head>
<body>
	<!-- 全局公共头部+导航  开始 -->
	<header class="NXB-header">
		<div class="NXB-container">
			<!-- logo end -->
			<h1 id="logo">
				<a title="xxx公司" href="/">
					<img alt="xxx公司" src="/static/common/admin/images/new-logo.png">
				</a>
			</h1>
			<!-- logo end -->
			<aside class="h-top-r">
				<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
			      <li data-am-dropdown="" class="am-dropdown">
			        <a href="javascript:;" data-am-dropdown-toggle="" class="am-dropdown-toggle">
			          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
			        </a>
			        <ul class="am-dropdown-content">
			          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
			          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
			          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
			        </ul>
			      </li>
			      <li class="am-hide-sm-only"><a id="admin-fullscreen" href="javascript:;"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
			    </ul>
			</aside>
			<div class="clear"></div>
		</div>
	</header>
	<nav class="NXB-nav">
		<div class="NXB-container">
			<ul class="NXB-nav-ul fl">
				<li class="current"><a href="" class="首页">首 页</a></li>
				<li><a href="" class="系统设置">系统设置</a></li>
				<li><a href="" class="课程管理">课程管理</a></li>
				<li><a href="" class="测试测评">测试测评</a></li>
				<li><a href="" class="问答管理">问答管理</a></li>
				<li><a href="" class="资讯公告">资讯公告</a></li>
				<li>
					<div data-am-dropdown="" class="am-dropdown">
						<button data-am-dropdown-toggle="" class="am-btn am-btn-primary am-dropdown-toggle">更多 <span class="am-icon-caret-down"></span></button>
						<ul class="am-dropdown-content">
							<li><a href="#">线下活动</a></li>
							<li><a href="#">名家讲师</a></li>
							<li><a href="#">精彩专题</a></li>
						</ul>
					</div>
				</li>
			</ul>
			<aside class="nav-r fr mt5">
				<a href="" title="返回前台"><span class="am-icon-map-signs"></span> 返回前台</a>
			</aside>
			<div class="clear"></div>
		</div>
	</nav>
	<!-- 全局公共头部+导航  结束 -->
	
	<!-- 全局主体区  开始 -->
	<div class="NXB-main">
		<div class="NXB-container">
			<div class="NXB-bCrumb">
				<ol class="am-breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">分类</a></li>
				  <li class="am-active">内容</li>
				</ol>
			</div>
			<div class="am-cf NXB-m-box">
				<!-- 全局公共左侧菜单区  开始 -->
				<div id="admin-offcanvas" class="admin-sidebar am-offcanvas">
				    <div class="am-offcanvas-bar admin-offcanvas-bar">
				      <ul id="collapase-nav-1" class="NXB-l-menu am-list admin-sidebar-list">
						<li class="am-panel"><a href="#/" class="am-collapsed"><i class="am-icon-home"></i> 首页</a></li>
						<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#user-nav'}" class=""><i class="am-icon-users"></i> 人员管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="user-nav" class="am-list admin-sidebar-sub am-collapse am-in" style="">
							<li><a href="#/userAdd"><i class="am-icon-user am-margin-left-sm"></i> 添加人员</a></li>
							<li><a href="#/userList/0"><i class="am-icon-user am-margin-left-sm"></i> 人员列表</a></li>
						</ul>
						</li>
						<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#company-nav'}" class="am-collapsed"><i class="am-icon-table"></i> 单位（部门）管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="company-nav" class="am-list admin-sidebar-sub am-collapse" style="height: 0px;">
							<li><a href="#/companyAdd"><span class="am-icon-table am-margin-left-sm"></span> 添加单位（部门）</a></li>
							<li><a href="#/companyList/0"><span class="am-icon-table am-margin-left-sm"></span> 单位（部门）列表</a></li>
						</ul>
						</li>
						<li class="am-panel"><a data-am-collapse="{parent: '#collapase-nav-1', target: '#role-nav'}" class="am-collapsed"><i class="am-icon-table"></i> 角色管理 <i class="am-icon-angle-right am-fr am-margin-right"></i></a>
						<ul id="role-nav" class="am-list admin-sidebar-sub am-collapse" style="height: 0px;">
							<li><a href="#/roleAdd"><span class="am-icon-table am-margin-left-sm"></span> 添加角色</a></li>
							<li><a href="#/roleList/0"><span class="am-icon-table am-margin-left-sm"></span> 角色列表</a></li>
						</ul>
						</li>
						<li><a href="admin-table.html" class="am-collapsed"><span class="am-icon-table"></span> 表格</a></li>
			       	 	<li><a href="admin-form.html" class="am-collapsed"><span class="am-icon-pencil-square-o"></span> 表单</a></li>
			        	<li><a href="#" class="am-collapsed"><span class="am-icon-sign-out"></span> 注销</a></li>
					  </ul>
				      <div class="am-panel am-panel-default admin-sidebar-panel">
				        <div class="am-panel-bd">
				          <p><span class="am-icon-bookmark"></span> 公告</p>
				          <p>时光静好，与君语；细水流年，与君同。&mdash;&mdash; Amaze UI</p>
				        </div>
				      </div>
				
				      <div class="am-panel am-panel-default admin-sidebar-panel">
				        <div class="am-panel-bd">
				        </div>
				      </div>
				    </div>
				  </div>
				<!-- 全局公共左侧菜单区  结束 -->
				<!-- 全局公共右内容区  开始 -->
				<div class="admin-content">
					<div class="am-margin">
						<!-- 公共右侧标题样式 -->
						<div class="am-cf">
					      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>一些常用模块</small></div>
					    </div>
					    <hr>
					    <!-- 公共右侧标题样式 结束-->
					    <div class="mt20">
						    <ul class="am-avg-sm-1 am-avg-md-4 am-padding am-text-center admin-content-list ">
						      <li><a class="am-text-success" href="#"><span class="am-icon-btn am-icon-file-text"></span><br>新增页面<br>2300</a></li>
						      <li><a class="am-text-warning" href="#"><span class="am-icon-btn am-icon-briefcase"></span><br>成交订单<br>308</a></li>
						      <li><a class="am-text-danger" href="#"><span class="am-icon-btn am-icon-recycle"></span><br>昨日访问<br>80082</a></li>
						      <li><a class="am-text-secondary" href="#"><span class="am-icon-btn am-icon-user-md"></span><br>在线用户<br>3000</a></li>
						    </ul>
					    </div>
					    <!-- 一组按钮样式   开始-->
					    <div class="am-g">
					    	<div class="mt20">
					    		<button type="button" class="am-btn am-btn-default">默认样式</button>
								<button type="button" class="am-btn am-btn-primary">主色按钮</button>
								<button type="button" class="am-btn am-btn-secondary">次色按钮</button>
								<button type="button" class="am-btn am-btn-success">绿色按钮</button>
								<button type="button" class="am-btn am-btn-warning">橙色按钮</button>
								<button type="button" class="am-btn am-btn-danger">红色按钮</button>
								<a class="am-btn am-btn-link">链接</a>
					    	</div>
					    </div>
					    <!-- 一组按钮样式   结束-->
					    <!-- 表单样式组 一   开始-->
					    <div class="am-g">
					      <div class="mt20">
					      	<section>
					      		<div class="am-form-group am-form-file">
								  <button type="button" class="am-btn am-btn-danger am-btn-sm">
								    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
								  <input id="doc-form-file" type="file" multiple>
								</div>
								<div id="file-list"></div>
					      	</section>
					      	<section class="mt20">
					      		<form class="am-form">
								  <fieldset disabled>
								    <div class="am-form-group">
								      <label for="doc-ds-ipt-1">禁用的文本框</label>
								      <input type="text" id="doc-ds-ipt-1" class="am-form-field" placeholder="禁止输入">
								    </div>
								
								    <div class="am-form-group">
								      <label for="oc-ds-select-1">禁用的下拉选框</label>
								      <select id="oc-ds-select-1" class="am-form-field">
								        <option>禁止选择我</option>
								      </select>
								    </div>
								
								    <div class="am-checkbox">
								      <label>
								        <input type="checkbox"> 无法选中的复选框
								      </label>
								    </div>
								
								    <button type="submit" class="am-btn am-btn-primary">Submit</button>
								  </fieldset>
								</form>
					      	</section>
					      	<section class="mt20">
					      		<form action="" class="am-form am-form-inline">
								  <div class="am-form-group am-form-icon">
								    <i class="am-icon-calendar"></i>
								    <input type="text" class="am-form-field" placeholder="日期">
								  </div>
								
								  <div class="am-form-group am-form-icon">
								    <i class="am-icon-clock-o"></i>
								    <input type="text" class="am-form-field" placeholder="时间">
								  </div>
								</form>
					      	</section>
					      	<section class="mt20">
					      		<div class="am-g">
								  <div class="am-u-sm-6">
								    <h3>复选框</h3>
								    <label class="am-checkbox">
								      <input type="checkbox" value="" data-am-ucheck> 没有选中
								    </label>
								    <label class="am-checkbox">
								      <input type="checkbox" checked="checked" value="" data-am-ucheck checked>
								      已选中
								    </label>
								    <label class="am-checkbox">
								      <input type="checkbox" value="" data-am-ucheck disabled>
								      禁用/未选中
								    </label>
								    <label class="am-checkbox">
								      <input type="checkbox" checked="checked" value="" data-am-ucheck disabled
								             checked>
								      禁用/已选中
								    </label>
								  </div>
								
								  <div class="am-u-sm-6">
								    <h3>单选框</h3>
								    <label class="am-radio">
								      <input type="radio" name="radio1" value="" data-am-ucheck>
								      未选中
								    </label>
								    <label class="am-radio">
								      <input type="radio" name="radio1" value="" data-am-ucheck checked>
								      已选中
								    </label>
								
								    <label class="am-radio">
								      <input type="radio" name="radio2" value="" data-am-ucheck disabled>
								      禁用/未选中
								    </label>
								    <label class="am-radio">
								      <input type="radio" name="radio2" value="" data-am-ucheck checked
								             disabled>
								      禁用/已选中
								    </label>
								  </div>
								</div>
					      	</section>
					      	<section class="mt20">
					      		<form class="am-form">
								  <div class="am-form-group am-form-success am-form-icon am-form-feedback">
								    <input type="text" class="am-form-field" placeholder="验证成功">
								    <span class="am-icon-check"></span>
								  </div>
								  <div class="am-form-group am-form-warning am-form-icon am-form-feedback">
								    <input type="text" class="am-form-field" placeholder="验证警告">
								    <span class="am-icon-warning"></span>
								  </div>
								  <div class="am-form-group am-form-error am-form-icon am-form-feedback">
								    <input type="text" class="am-form-field" placeholder="验证失败">
								    <span class="am-icon-times"></span>
								  </div>
								</form>
					      	</section>
					      	
					      </div>
					    </div>
					    <!-- 表单样式组 一   结束-->
					    
					     <!-- 选项卡   开始-->
					    <div class="mt20">
					    
					   	 <div data-am-tabs="" class="am-tabs">
						    <ul class="am-tabs-nav am-nav am-nav-tabs">
						      <li class="am-active"><a href="#tab1">基本信息</a></li>
						      <li class=""><a href="#tab2">详细描述</a></li>
						      <li class=""><a href="#tab3">SEO 选项</a></li>
						    </ul>
						
						    <div class="am-tabs-bd">
						      <div id="tab1" class="am-tab-panel am-fade am-active am-in">
						        <div class="am-g am-margin-top">
						          <div class="am-u-sm-4 am-u-md-2 am-text-right">所属类别</div>
						          <div class="am-u-sm-8 am-u-md-10">
						            <select data-am-selected="{btnSize: 'sm'}" style="display: none;">
						              <option value="option1">选项一...</option>
						              <option value="option2">选项二.....</option>
						              <option value="option3">选项三........</option>
						            </select><div data-am-dropdown="" id="am-selected-kcfxt" class="am-selected am-dropdown ">  <button class="am-selected-btn am-btn am-dropdown-toggle am-btn-sm am-btn-default" type="button">    <span class="am-selected-status am-fl">选项一...</span>    <i class="am-selected-icon am-icon-caret-down"></i>  </button>  <div class="am-selected-content am-dropdown-content">    <h2 class="am-selected-header"><span class="am-icon-chevron-left">返回</span></h2>       <ul class="am-selected-list">                     <li data-value="option1" data-group="0" data-index="0" class="am-checked">         <span class="am-selected-text">选项一...</span>         <i class="am-icon-check"></i></li>                                 <li data-value="option2" data-group="0" data-index="1" class="">         <span class="am-selected-text">选项二.....</span>         <i class="am-icon-check"></i></li>                                 <li data-value="option3" data-group="0" data-index="2" class="">         <span class="am-selected-text">选项三........</span>         <i class="am-icon-check"></i></li>            </ul>    <div class="am-selected-hint"></div>  </div></div>
						          </div>
						        </div>
						
						        <div class="am-g am-margin-top">
						          <div class="am-u-sm-4 am-u-md-2 am-text-right">显示状态</div>
						          <div class="am-u-sm-8 am-u-md-10">
						            <div data-am-button="" class="am-btn-group">
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="radio" id="option1" name="options"> 正常
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="radio" id="option2" name="options"> 待审核
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="radio" id="option3" name="options"> 不显示
						              </label>
						            </div>
						          </div>
						        </div>
						
						        <div class="am-g am-margin-top">
						          <div class="am-u-sm-4 am-u-md-2 am-text-right">推荐类型</div>
						          <div class="am-u-sm-8 am-u-md-10">
						            <div data-am-button="" class="am-btn-group">
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="checkbox"> 允许评论
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="checkbox"> 置顶
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="checkbox"> 推荐
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="checkbox"> 热门
						              </label>
						              <label class="am-btn am-btn-default am-btn-xs">
						                <input type="checkbox"> 轮播图
						              </label>
						            </div>
						          </div>
						        </div>
						
						        <div class="am-g am-margin-top">
						          <div class="am-u-sm-4 am-u-md-2 am-text-right">
						            发布时间
						          </div>
						          <div class="am-u-sm-8 am-u-md-10">
						            <form class="am-form am-form-inline" action="">
						              <div class="am-form-group am-form-icon">
						                <i class="am-icon-calendar"></i>
						                <input type="text" placeholder="时间" class="am-form-field am-input-sm">
						              </div>
						            </form>
						          </div>
						        </div>
						
						      </div>
						
						      <div id="tab2" class="am-tab-panel am-fade">
						        <form class="am-form">
						          <div class="am-g am-margin-top">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              文章标题
						            </div>
						            <div class="am-u-sm-8 am-u-md-4">
						              <input type="text" class="am-input-sm">
						            </div>
						            <div class="am-hide-sm-only am-u-md-6">*必填，不可重复</div>
						          </div>
						
						          <div class="am-g am-margin-top">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              文章作者
						            </div>
						            <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
						
						          <div class="am-g am-margin-top">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              信息来源
						            </div>
						            <div class="am-u-sm-8 am-u-md-4">
						              <input type="text" class="am-input-sm">
						            </div>
						            <div class="am-hide-sm-only am-u-md-6">选填</div>
						          </div>
						
						          <div class="am-g am-margin-top">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              内容摘要
						            </div>
						            <div class="am-u-sm-8 am-u-md-4">
						              <input type="text" class="am-input-sm">
						            </div>
						            <div class="am-u-sm-12 am-u-md-6">不填写则自动截取内容前255字符</div>
						          </div>
						
						          <div class="am-g am-margin-top-sm">
						            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">
						              内容描述
						            </div>
						            <div class="am-u-sm-12 am-u-md-10">
						              <textarea placeholder="请使用富文本编辑插件" rows="10"></textarea>
						            </div>
						          </div>
						
						        </form>
						      </div>
						
						      <div id="tab3" class="am-tab-panel am-fade">
						        <form class="am-form">
						          <div class="am-g am-margin-top-sm">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              SEO 标题
						            </div>
						            <div class="am-u-sm-8 am-u-md-4 am-u-end">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
						
						          <div class="am-g am-margin-top-sm">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              SEO 关键字
						            </div>
						            <div class="am-u-sm-8 am-u-md-4 am-u-end">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
						
						          <div class="am-g am-margin-top-sm">
						            <div class="am-u-sm-4 am-u-md-2 am-text-right">
						              SEO 描述
						            </div>
						            <div class="am-u-sm-8 am-u-md-4 am-u-end">
						              <textarea rows="4"></textarea>
						            </div>
						          </div>
						        </form>
						      </div>
						
						    </div>
						  </div>
					    </div>
					    <!-- 选项卡   结束-->
					    
				    </div>
				</div>
				<!-- 全局公共右内容区  开始 -->
			</div>
		</div>
	</div>
	<!-- 全局主体区  开始 -->
	
	<!-- 全局尾部区  开始 -->
	<footer class="NXB-footer">
		<div class="NXB-container">
	  		<hr>

	 	 </div>
	</footer>
	<!-- 全局尾部区  结束 -->
</body>
</html>