<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>一些常用表格样式</title>
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
				          <p><span class="am-icon-tag"></span> 链接</p>
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
					      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表格</strong> / <small>一些常用表格样式</small></div>
					    </div>
					    <hr>
					    <!-- 公共右侧标题样式 结束-->
					    <div class="mt20">
					    	<div class="am-tab-panel am-fade am-active am-in">
						        <form class="am-form">
						          <div class="am-g am-margin-top am-u-sm-5">
						            <div class="am-u-sm-4 am-text-right">
						              文章标题
						            </div>
						            <div class="am-u-sm-8">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
									<div class="am-g am-margin-top am-u-sm-5">
						            <div class="am-u-sm-4 am-text-right">
						              文章标题
						            </div>
						            <div class="am-u-sm-8">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
						          <div class="mt20 clear"></div>
						  <div class="am-g am-margin-top am-u-sm-5">
						            <div class="am-u-sm-4 am-text-right">
						              文章标题
						            </div>
						            <div class="am-u-sm-8">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
									<div class="am-g am-margin-top am-u-sm-5">
						            <div class="am-u-sm-4 am-text-right">
						              文章标题
						            </div>
						            <div class="am-u-sm-8">
						              <input type="text" class="am-input-sm">
						            </div>
						          </div>
						          <div class="mt20 clear"></div>
						        </form>
						      </div>
					    </div>
					    <div class="mt20">
					    	<div class="am-g">
						      <div class="am-u-md-6">
						        <div class="am-btn-toolbar">
						          <div class="am-btn-group am-btn-group-xs">
						            <button class="am-btn am-btn-success" type="button" title="新增"><span class="am-icon-plus"></span> 新增</button>
						            <button class="am-btn am-btn-success" type="button" title="保存"><span class="am-icon-save"></span> 保存</button>
						            <button class="am-btn am-btn-success" type="button" title="审核"><span class="am-icon-archive"></span> 审核</button>
						            <button class="am-btn am-btn-success" type="button" title="删除"><span class="am-icon-trash-o"></span> 删除</button>
						          </div>
						        </div>
						      </div>
						      <div class="am-u-sm-12 am-u-md-3">
						       	<select data-am-selected="{btnWidth: '100%', btnSize: 'sm'}">
								  <option value="a">Apple</option>
								  <option value="b">Banana</option>
								  <option value="o">Orange</option>
								  <option value="m">Mango</option>
								</select>
						      </div>
						      <div class="am-u-sm-12 am-u-md-3">
						        <div class="am-input-group am-input-group-sm">
						          <input type="text" class="am-form-field">
						          <span class="am-input-group-btn">
						            <button type="button" class="am-btn am-btn-warning">搜索</button>
						          </span>
						        </div>
						      </div>
						    </div>
					    </div>
					    <!-- 表格样式 一   开始-->
					    <div class="am-g">
					      <div class="mt20 am-scrollable-horizontal">
						    <div class="mt10">
						        <table class="am-table am-table-bd am-table-striped admin-content-table">
						          <thead>
						          <tr>
						            <th>ID</th><th>用户名</th><th>最后成交任务</th><th>成交订单</th><th>日期</th><th>管理</th>
						          </tr>
						          </thead>
						          <tbody>
						          <tr><td>1</td><td>John Clark</td><td><a href="#">Business management</a></td> <td><span class="am-badge am-badge-success">+20</span></td><td>2015-12-12 12:00</td>
						            <td>
						              <div data-am-dropdown="" class="am-dropdown">
						                <button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
						                <ul class="am-dropdown-content">
						                  <li><a href="#">1. 编辑</a></li>
						                  <li><a href="#">2. 下载</a></li>
						                  <li><a href="#">3. 删除</a></li>
						                </ul>
						              </div>
						            </td>
						          </tr>
						          <tr><td>2</td><td>风清扬</td><td><a href="#">公司LOGO设计</a> </td><td><span class="am-badge am-badge-danger">+2</span></td><td>2015-12-12 12:00</td>
						            <td>
						              <div data-am-dropdown="" class="am-dropdown">
						                <button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
						                <ul class="am-dropdown-content">
						                  <li><a href="#">1. 编辑</a></li>
						                  <li><a href="#">2. 下载</a></li>
						                  <li><a href="#">3. 删除</a></li>
						                </ul>
						              </div>
						            </td>
						          </tr>
						          <tr><td>3</td><td>詹姆斯</td><td><a href="#">开发一款业务数据软件</a></td><td><span class="am-badge am-badge-warning">+10</span></td><td>2015-12-12 12:00</td>
						            <td>
						              <div data-am-dropdown="" class="am-dropdown">
						                <button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
						                <ul class="am-dropdown-content">
						                  <li><a href="#">1. 编辑</a></li>
						                  <li><a href="#">2. 下载</a></li>
						                  <li><a href="#">3. 删除</a></li>
						                </ul>
						              </div>
						            </td>
						          </tr>
						          <tr><td>4</td><td>云适配</td><td><a href="#">适配所有网站</a></td><td><span class="am-badge am-badge-secondary">+50</span></td><td>2015-12-12 12:00</td>
						            <td>
						              <div data-am-dropdown="" class="am-dropdown">
						                <button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
						                <ul class="am-dropdown-content">
						                  <li><a href="#">1. 编辑</a></li>
						                  <li><a href="#">2. 下载</a></li>
						                  <li><a href="#">3. 删除</a></li>
						                </ul>
						              </div>
						            </td>
						          </tr>
						
						          <tr>
						            <td>5</td><td>呵呵呵</td>
						            <td><a href="#">基兰会获得BUFF</a></td>
						            <td><span class="am-badge">+22</span></td><td>2015-12-12 12:00</td>
						            <td>
						              <div data-am-dropdown="" class="am-dropdown">
						                <button data-am-dropdown-toggle="" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
						                <ul class="am-dropdown-content">
						                  <li><a href="#">1. 编辑</a></li>
						                  <li><a href="#">2. 下载</a></li>
						                  <li><a href="#">3. 删除</a></li>
						                </ul>
						              </div>
						            </td>
						          </tr>
						          </tbody>
						        </table>
					        </div>
					      </div>
					    </div>
					    <!-- 表格样式 一   结束-->
					    
					    
					    <!-- 表格样式 二   开始-->
					    	<div class="am-g">
						      <div class="mt20">
						        <form class="am-form">
						          <table class="am-table am-table-striped am-table-hover table-main">
						            <thead>
						              <tr>
						                <th class="table-check"><input type="checkbox"></th><th class="table-id">ID</th><th class="table-title">标题</th><th class="table-type">类别</th><th class="table-author am-hide-sm-only">作者</th><th class="table-date am-hide-sm-only">修改日期</th><th class="table-set">操作</th>
						              </tr>
						          </thead>
						          <tbody>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>1</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>2</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>3</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>4</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>5</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>6</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>7</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>8</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>9</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>10</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>11</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>12</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>13</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>14</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试14号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						            <tr>
						              <td><input type="checkbox"></td>
						              <td>15</td>
						              <td><a href="#">Business management</a></td>
						              <td>default</td>
						              <td class="am-hide-sm-only">测试1号</td>
						              <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
						              <td>
						                <div class="am-btn-toolbar">
						                  <div class="am-btn-group am-btn-group-xs">
						                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
						                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
						                  </div>
						                </div>
						              </td>
						            </tr>
						          </tbody>
						        </table>
						          <div class="am-cf">
						  共 15 条记录
						  <div class="am-fr">
						    <ul class="am-pagination">
						      <li class="am-disabled"><a href="#">«</a></li>
						      <li class="am-active"><a href="#">1</a></li>
						      <li><a href="#">2</a></li>
						      <li><a href="#">3</a></li>
						      <li><a href="#">4</a></li>
						      <li><a href="#">5</a></li>
						      <li><a href="#">»</a></li>
						    </ul>
						  </div>
						</div>
						          <hr>
						          <p>注：.....</p>
						        </form>
						      </div>
						
						    </div>
					    <!-- 表格样式 二   结束-->
					    
					    <!-- 表格样式 三   开始-->
					    <div class="mt20">
					    	<div class="doc-example">
					            <div class="am-scrollable-horizontal">
					                <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
					                    <thead>
					                        <tr>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                            <th>
					                                -= 表格标题 =-
					                            </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                        <tr>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                        </tr>
					                        <tr>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                        </tr>
					                        <tr>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                            <td>
					                                表格数据
					                            </td>
					                        </tr>
					                    </tbody>
					                </table>
					            </div>
					        </div>
					    </div>
					    <!-- 表格样式 三   结束-->
					    
					    <!-- 表格样式 四   开始-->
					    <div class="mt20">
					    	<div class="doc-example">
				            <table class="am-table am-table-bordered am-table-striped am-table-hover">
				                <thead>
				                    <tr>
				                        <th>
				                            网站名称
				                        </th>
				                        <th>
				                            网址
				                        </th>
				                        <th>
				                            创建时间
				                        </th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<tr>
				                		<td colspan="4">
				                			<div data-am-alert="" class="am-alert am-alert-secondary mt20 mb50"> <center><big><i class="am-icon-frown-o vam" style="font-size: 34px;"></i> <span class="vam ml10">没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。</span></big></center> </div>
				                		</td>
				                	</tr>
				                    <tr>
				                        <td>
				                            Amaze UI
				                        </td>
				                        <td>
				                            http://amazeui.org
				                        </td>
				                        <td>
				                            2012-10-01
				                        </td>
				                    </tr>
				                    <tr>
				                        <td>
				                            Amaze UI
				                        </td>
				                        <td>
				                            http://amazeui.org
				                        </td>
				                        <td>
				                            2012-10-01
				                        </td>
				                    </tr>
				                    <tr class="am-active">
				                        <td>
				                            Amaze UI(Active)
				                        </td>
				                        <td>
				                            http://amazeui.org
				                        </td>
				                        <td>
				                            2012-10-01
				                        </td>
				                    </tr>
				                    <tr>
				                        <td>
				                            Amaze UI
				                        </td>
				                        <td>
				                            http://amazeui.org
				                        </td>
				                        <td>
				                            2012-10-01
				                        </td>
				                    </tr>
				                    <tr>
				                        <td>
				                            Amaze UI
				                        </td>
				                        <td>
				                            http://amazeui.org
				                        </td>
				                        <td>
				                            2012-10-01
				                        </td>
				                    </tr>
				                </tbody>
				            </table>
				        </div>
					    </div>
					    <!-- 表格样式 四   结束-->
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
	
	<!--[if lt IE 9]>
	<script src="${ctximg}/static/common/admin/js/modernizr.js"></script>
	<script src="${ctximg}/static/common/admin/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->
	<script src="${ctximg}/static/common/admin/js/amazeui.min.js"></script>
	<script>
		//开启全屏模式
		(function($) {
		  'use strict';
		  $(function() {
		    var $fullText = $('.admin-fullText');
		    $('#admin-fullscreen').on('click', function() {
		      $.AMUI.fullscreen.toggle();
		    });
		    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
		      $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
		    });
		  });
		})(jQuery);
	</script>
</body>
</html>